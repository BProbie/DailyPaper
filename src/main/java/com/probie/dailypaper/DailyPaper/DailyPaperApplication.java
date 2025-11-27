package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import java.awt.image.BufferedImage;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import javafx.application.Application;
import java.util.concurrent.atomic.AtomicReference;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperApplication;

@Data
public class DailyPaperApplication extends Application implements IDailyPaperApplication {

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

        /// TODO ========================================================================================================

        javafx.scene.control.Button choseButton = new javafx.scene.control.Button();
        choseButton.setMinWidth(250);
        choseButton.setMinHeight(100);
        AtomicReference<File> gif = new AtomicReference<>();
        choseButton.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();

            gif.set(fileChooser.showOpenDialog(getStage()));
            choseButton.setText(gif.get().getAbsolutePath());
        });

        javafx.scene.control.Button gifButton = new javafx.scene.control.Button();
        gifButton.setMinWidth(250);
        gifButton.setMinHeight(100);
        gifButton.setOnAction(actionEvent -> {
            BufferedImage[] bufferedImages = DailyPaper.getInstance().getGIFSystem().turnGIFToBufferedImages(gif.get());
            Integer[] gifPlaySpeed = DailyPaper.getInstance().getGIFSystem().getGIFPlaySpeed(gif.get());

            for (int i = 0; i < bufferedImages.length; i++) {
                DailyPaper.getInstance().getImageSystem().turnBufferedImageToLocalFile(bufferedImages[i], gif.get().getParentFile().getAbsolutePath()+File.separator+i+".png");
            }

            ArrayList<File> fileArrayList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(new File(gif.get().getParentFile().getAbsolutePath()).listFiles())));
            for (int i = 0; i < fileArrayList.size(); i++) {
                if (!fileArrayList.get(i).getName().endsWith(".png")) {
                    fileArrayList.remove(i);
                    i--;
                }
            }

            new Thread(() -> {
                while (true) {
                    for (int i = 0; i < fileArrayList.size(); i++) {
                        DailyPaper.getInstance().getComputerSystem().setWallPaper(fileArrayList.get(i).getAbsolutePath());
                        try {
                            Thread.sleep(gifPlaySpeed[i]/2);
                        } catch (InterruptedException interruptedException) {
                            throw new RuntimeException(interruptedException);
                        }
                    }
                }
            }).start();
        });

        HBox hBox = new HBox(choseButton, gifButton);

        ImageView imageView = new ImageView();
        TextArea textArea = new TextArea();
        imageView.setFitWidth(500);
        imageView.setFitHeight(250);
        textArea.setMaxWidth(500);
        textArea.setMaxHeight(250);
        textArea.setFont(new Font(25));

        VBox vBox = new VBox(hBox, imageView, textArea);
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

        /// TODO ========================================================================================================

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