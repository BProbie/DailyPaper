package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.awt.image.BufferedImage;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.application.Application;

@Data
public class DailyPaperApplication extends Application {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperApplication INSTANCE;

    /**
     * 维护一个舞台单例
     * */
    private volatile Stage stage;

    /**
     * 启动主窗口
     * */
    @Override
    public void start(Stage stage) {
        setStage(stage);

        // TODO
        ImageView imageView = new ImageView();
        TextArea textArea = new TextArea();
        imageView.setFitWidth(500);
        imageView.setFitHeight(250);
        textArea.setMaxWidth(500);
        textArea.setMaxHeight(250);
        textArea.setFont(new Font(25));

        javafx.scene.layout.VBox vBox = new VBox(imageView, textArea);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        textArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER && event.isShiftDown() && textArea.isEditable()) {
                new Thread(() -> {
                    String text = textArea.getText();
                    String prop = text.replace("\n",",");
                    textArea.setText("生成: "+prop+"...");
                    textArea.setEditable(false);

                    BufferedImage bufferedImage = DailyPaper.getInstance().getImageSystem().turnUrlToBufferedImage(
                            DailyPaper.getInstance().getKolorsAgent().turnTextToImage(prop)[0]
                    );
                    imageView.setImage(DailyPaper.getInstance().getImageSystem().turnBufferedImageToFXImage(bufferedImage));

                    if (DailyPaper.getInstance().getImageSystem().turnBufferedImageToLocalFile(DailyPaper.getInstance().getImageSystem().setBufferedImageSize(bufferedImage, (int) DailyPaper.getInstance().getComputerSystem().getDimension().getWidth(), (int) DailyPaper.getInstance().getComputerSystem().getDimension().getHeight()), DailyPaper.getInstance().getKolorsFilePath().get(), DailyPaper.getInstance().getKolorsFileName().get())) {
                        DailyPaper.getInstance().getComputerSystem().setWallPaper(DailyPaper.getInstance().getKolorsFilePath().get()+"\\"+DailyPaper.getInstance().getKolorsFileName().get());
                    }

                    textArea.setText("完成: "+prop+"!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        throw new RuntimeException(interruptedException);
                    }
                    textArea.setText(text);
                    textArea.setEditable(true);
                }).start();
            }
        });

        stage.show();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperApplication();
        }
        return INSTANCE;
    }

}