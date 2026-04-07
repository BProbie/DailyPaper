package com.probie.dailypaper.DailyPaper;

import javafx.scene.text.Font;
import lombok.Data;
import java.io.File;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.application.Platform;
import java.awt.image.BufferedImage;
import com.probie.dailypaper.System.ImageSystem;
import com.probie.dailypaper.Config.RenewConfig;
import javafx.beans.property.SimpleObjectProperty;
import com.probie.dailypaper.System.ComputerSystem;
import com.probie.dailypaper.Config.LiveImageConfig;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperFunction;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;

@Data
public class DailyPaperFunction implements IDailyPaperFunction {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperFunction INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperFunction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperFunction();
        }
        return INSTANCE;
    }

    /**
     * 工具类单例对象
     * */
    private volatile static DailyPaper dailyPaper = DailyPaper.getInstance();
    private volatile static DailyPaperApplication dailyPaperApplication = DailyPaperApplication.getINSTANCE();
    private volatile static DailyPaperElement dailyPaperElement = DailyPaperElement.getInstance();
    private volatile static DailyPaperStyle dailyPaperStyle = DailyPaperStyle.getInstance();
    private volatile static DailyPaperEvent dailyPaperEvent = DailyPaperEvent.getInstance();
    private volatile static DailyPaperData dailyPaperData = DailyPaperData.getInstance();
    private volatile static DailyPaperFunction dailyPaperFunction = DailyPaperFunction.getInstance();

    @Override
    public void launchLiveImageWallpaper() {
        dailyPaper.getLiveImageAutoLaunch().set(true);

        /// 获取本地切片数据
        String[] speeds = LiveImageConfig.getInstance().getLocalDB().get("Speed").toString().split(DailyPaper.getInstance().getSplitMark().get().toString());
        int[] imagesWallpaperSpeed = new int[speeds.length];
        for (int i = 0; i < speeds.length; i++) {
            imagesWallpaperSpeed[i] = Integer.parseInt(speeds[i]);
        }
        String[] imagesWallPaperFullFilePath = new String[imagesWallpaperSpeed.length];
        for (int i = 0; i < imagesWallpaperSpeed.length; i++) {
            imagesWallPaperFullFilePath[i] = dailyPaper.getLiveImageFilePath().get() + File.separator + i + ".png";
        }

        /// 循环播放切片
        dailyPaper.getDailyPaperPool().submit(() -> {
            dailyPaperData.setIsLiveWallpaperShowing(() -> true);
            do {
                for (int i = 0; i < imagesWallPaperFullFilePath.length; i++) {
                    ImageSystem.getInstance().setWallPaper(imagesWallPaperFullFilePath[i]);
                    try {
                        Thread.sleep(imagesWallpaperSpeed[i]);
                    } catch (InterruptedException interruptedException) {
                        throw new RuntimeException(interruptedException);
                    }
                    if (!dailyPaperData.getIsLiveWallpaperShowing().get()) {
                        break;
                    }
                }
            } while (dailyPaperData.getIsLiveWallpaperShowing().get());
        });
    }

    @Override
    public void clearLiveImageWallpaper() {
        DailyPaper.getInstance().getLiveImageAutoLaunch().set(false);
        if (dailyPaperData.getIsLiveWallpaperShowing().get()) {
            dailyPaperData.setIsLiveWallpaperShowing(() -> false);
            waitADelay(10);
        }
    }

    @Override
    public void dailyWallpaper() {
        try {
            DailyPaper.getInstance().getDailyPaperPool().submit(() -> {
                String prompt = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(dailyPaperData.getPromptSpawnImagePrompt().get() + dailyPaperData.getPromptSpawnDailyWallpaperPrompt().get())[0];
                String[] urls = TextToImageAIAgentSiliconFlow.getInstance().turnTextToImage(prompt);
                BufferedImage bufferedImage = ImageSystem.getInstance().turnUrlToBufferedImage(urls[0]);
                if (ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImage, dailyPaper.getTempFilePath().get().toString(), dailyPaper.getTempImageFileName().get().toString())) {
                    if (dailyPaperData.getIsLiveWallpaperShowing().get()) dailyPaperFunction.clearLiveImageWallpaper();
                    ComputerSystem.getInstance().setWallPaper(dailyPaper.getTempFilePath().get().toString(), dailyPaper.getTempImageFileName().get().toString());
                }
            });
        } catch (Exception ignored) {}
    }

    @Override
    public boolean checkRenewDailyPaper() {
        return Double.parseDouble(String.valueOf(RenewConfig.getInstance().getLocalRemoteDB().get("VERSION", dailyPaper.getVERSION()))) > Double.parseDouble(dailyPaper.getVERSION());
    }

    @Override
    public boolean downloadRenewDailyPaper() {

        String systemName = ComputerSystem.getInstance().getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String command = "cmd.exe"+ " " +"/c" + " "
                    + dailyPaper.getJavaFilePath().get() + File.separator + "bin" + File.separator + "java" + " " + "-jar" + " "
                    + dailyPaper.getRenewRenewLocalFilePath().get() + File.separator + dailyPaper.getRenewRenewLocalFileName().get() + " "
                    + dailyPaper.getDailyPaperRenewUri().get() + " "
                    + dailyPaper.getDailyPaperRenewLocalFilePath().get() + File.separator + dailyPaper.getDailyPaperRenewLocalFileName().get() + " "
                    + dailyPaper.getDailyPaperRenewAutoOpen().get();
            if (ComputerSystem.getInstance().runCommand(command, false) != 0) {
                return ComputerSystem.getInstance().runCommand(command, true) == 0;
            }
            return true;
        }
        return false;

//        return Renew.getInstance()
//                .setJavaFilePath(dailyPaper.getJavaFilePath().get() + File.separator + "bin" + File.separator + "java")
//                .setRenewFilePath(dailyPaper.getRenewRenewLocalFilePath().get() + File.separator + dailyPaper.getRenewRenewLocalFileName().get())
//                .setFullFileUrl(dailyPaper.getDailyPaperRenewUri().get())
//                .setFullFilePath(dailyPaper.getDailyPaperRenewLocalFilePath().get() + File.separator + dailyPaper.getDailyPaperRenewLocalFileName().get())
//                .setIsOpen(dailyPaper.getDailyPaperRenewAutoOpen().get())
//                .renew();
        
    }

    @Override
    public void chooseMenu(Node node) {
        if (node != dailyPaperElement.getRootPaneCenterStageBarVBox().getChildren().getFirst()) {
            dailyPaperElement.getRootPaneCenterStageBarVBox().getChildren().clear();
            dailyPaperElement.getRootPaneCenterStageBarVBox().getChildren().add(node);
        } else {
            if (node == dailyPaperElement.getChatVBox()) clearChat();
            else if (node == dailyPaperElement.getLiveVBox()) clearLive();
            else if (node == dailyPaperElement.getDailyVBox()) clearDaily();
            else if (node == dailyPaperElement.getParamVBox()) clearParam();
            else if (node == dailyPaperElement.getSettingVBox()) clearSetting();
            else if (node == dailyPaperElement.getRenewVBox()) clearRenew();
        }
    }

    @Override
    public void clearChat() {
        dailyPaperStyle.createChatStyle();
    }

    @Override
    public void clearLive() {
        dailyPaperStyle.createLiveStyle();
    }

    @Override
    public void clearDaily() {
        dailyPaperStyle.createDailyStyle();
    }

    @Override
    public void clearParam() {
        dailyPaperStyle.createParamStyle();
    }

    @Override
    public void clearSetting() {
        dailyPaperStyle.createSettingStyle();
    }

    @Override
    public void clearRenew() {
        dailyPaperStyle.createRenewStyle();
    }

    @Override
    public void waitADelay() {
        waitADelay(1);
    }

    @Override
    public void waitADelay(int count) {
        try {
            Thread.sleep((long) dailyPaperData.getDelay().get() * count);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

    @Override
    public void scrollToBottom(ScrollPane scrollPane) {
        dailyPaper.getDailyPaperPool().submit(() -> {
            ScrollBar verticalScrollBar = (ScrollBar) scrollPane.lookup(".scroll-bar:vertical");
            waitADelay(10);
            if (verticalScrollBar != null) {
                Platform.runLater(() -> verticalScrollBar.setValue(verticalScrollBar.getMax()));
            } else {
                Platform.runLater(() -> scrollPane.setVvalue(1.0));
            }
        });
    }

    @Override
    public void showButtonInformation(Button button, String information) {
        dailyPaper.getDailyPaperPool().submit(() -> {
            String temp = button.getText();
            Platform.runLater(() -> {
                button.setDisable(true);
                button.setText(information);
            });
            waitADelay(100);
            Platform.runLater(() -> {
                button.setText(temp);
                button.setDisable(false);
            });
        });
    }

    @Override
    public HBox createTextFieldHBox(Pane pane, String information, SimpleObjectProperty<Object> data) {
        HBox hBox = new HBox();
        hBox.prefWidthProperty().bind(pane.widthProperty());
        hBox.prefHeightProperty().bind(pane.heightProperty().divide(10.0));
        hBox.setAlignment(Pos.CENTER);

        Label label = new Label(information);
        label.prefWidthProperty().bind(hBox.widthProperty().divide(4.0));
        label.prefHeightProperty().bind(hBox.heightProperty());
        label.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        label.setAlignment(Pos.CENTER);

        TextField textField = new TextField(data.getValue().toString());
        textField.prefWidthProperty().bind(hBox.widthProperty().divide(4.0));
        textField.prefHeightProperty().bind(hBox.heightProperty());
        textField.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        textField.setAlignment(Pos.CENTER);

        textField.textProperty().addListener((observable, oldValue, newValue) -> data.setValue(newValue));

        hBox.getChildren().addAll(label, textField);

        return hBox;
    }

}