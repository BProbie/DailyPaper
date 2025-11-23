package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import java.io.File;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
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

        // TODO ========================================================================================================

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

                    Platform.runLater(() -> textArea.setEditable(false));
                    Platform.runLater(() -> textArea.setText("生成: \n"+text+"\n..."));

                    String prompt = DailyPaper.getInstance().getTextToTextAIAgentSiliconFlow().turnTextToText("我要生成一张电脑桌面壁纸，提示词是："+text.replace("，", "\n").replace(" ", "\n")+"。你帮我优化一下提示词。要求：充分理解用户想要什么，字数不超过100，直接返回提示词给我。")[0];

                    if (prompt != null) {

                        Platform.runLater(() -> textArea.setText("生成: \n"+prompt.replace("，","\n")+"\n..."));

                        String[] strings = DailyPaper.getInstance().getTextToImageAIAgentSiliconFlow().turnTextToImage(prompt.replace("，","\n"));

                        if (strings != null) {

                            BufferedImage bufferedImage = DailyPaper.getInstance().getImageSystem().turnUrlToBufferedImage(strings[0]);

                            Platform.runLater(() -> imageView.setImage(DailyPaper.getInstance().getImageSystem().turnBufferedImageToFXImage(bufferedImage)));

                            if (DailyPaper.getInstance().getImageSystem().turnBufferedImageToLocalFile(DailyPaper.getInstance().getImageSystem().setBufferedImageSize(bufferedImage, (int) DailyPaper.getInstance().getComputerSystem().getDimension().getWidth(), (int) DailyPaper.getInstance().getComputerSystem().getDimension().getHeight()), DailyPaper.getInstance().getRootPath().get(), DailyPaper.getInstance().getImageFileName().get())) {
                                DailyPaper.getInstance().getComputerSystem().setWallPaper(DailyPaper.getInstance().getRootPath().get()+File.separator+DailyPaper.getInstance().getImageFileName().get());
                            }

                            Platform.runLater(() -> textArea.setText("完成: \n"+prompt+"\n!"));

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException interruptedException) {
                                throw new RuntimeException(interruptedException);
                            }

                        }
                    }

                    Platform.runLater(() -> textArea.setText(text));
                    Platform.runLater(() -> textArea.setEditable(true));

                }).start();
            }
        });

        // TODO ========================================================================================================

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