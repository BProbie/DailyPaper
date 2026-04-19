package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
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
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.Analysis.TextAIAgentSiliconFlowAnalysis;

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
        String[] speeds = String.valueOf(LiveImageConfig.getInstance().getLocalDB().get("Speed")).split(String.valueOf(DailyPaper.getInstance().getSplitMark().get()));
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
            dailyPaperData.getIsLiveWallpaperShowing().set(true);
            do {
                for (int i = 0; i < imagesWallPaperFullFilePath.length; i++) {
                    ImageSystem.getInstance().setWallPaper(imagesWallPaperFullFilePath[i]);
                    try {
                        Thread.sleep(imagesWallpaperSpeed[i] / Integer.parseInt(String.valueOf(dailyPaper.getLiveImagePlaySpeed().get())));
                    } catch (InterruptedException interruptedException) {
                        throw new RuntimeException(interruptedException);
                    }
                    if (!Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveWallpaperShowing().get()))) {
                        break;
                    }
                }
            } while (Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveWallpaperShowing().get())));
        });
    }

    @Override
    public void clearLiveImageWallpaper() {
        DailyPaper.getInstance().getLiveImageAutoLaunch().set(false);
        if (Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveWallpaperShowing().get()))) {
            dailyPaperData.getIsLiveWallpaperShowing().set(false);
            waitADelay(10);
        }
    }

    @Override
    public void clearLiveImage() {
        dailyPaperData.getIsLiveImageShowing().set(false);
        waitADelay(10);
    }

    @Override
    public void dailyWallpaper() {
        try {
            DailyPaper.getInstance().getDailyPaperPool().submit(() -> {
                String prompt = TextAIAgentSiliconFlowAnalysis.getInstance().analysisText(String.valueOf(dailyPaperData.getPromptSpawnImagePrompt().get()) + dailyPaperData.getPromptSpawnDailyWallpaperPrompt().get())[0];
                String[] urls = TextToImageAIAgentSiliconFlow.getInstance().turnTextToImage(prompt);
                BufferedImage bufferedImage = ImageSystem.getInstance().turnUrlToBufferedImage(urls[0]);
                if (ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImage, String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()))) {
                    if (Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveWallpaperShowing().get()))) dailyPaperFunction.clearLiveImageWallpaper();
                    ComputerSystem.getInstance().setWallPaper(String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()));
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

//        RENEW自带的更新方法
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
//            整页面刷新
//            if (node == dailyPaperElement.getDailyVBox()) {
//                clearDaily();
//            } else if (node == dailyPaperElement.getRenewVBox()) {
//                clearRenew();
//            }
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
            Thread.sleep(Long.parseLong(String.valueOf(dailyPaperData.getDelay().get())) * count);
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
    public HBox createTitleHBox(Pane pane, String information) {
        information = "【" + information + "】";

        HBox hBox = new HBox();
        hBox.prefWidthProperty().bind(pane.widthProperty());
        hBox.prefHeightProperty().bind(pane.heightProperty().divide(10.0));
        hBox.setAlignment(Pos.CENTER);

        Label label = new Label(information);
        label.prefWidthProperty().bind(hBox.widthProperty());
        label.prefHeightProperty().bind(hBox.heightProperty());
        label.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        label.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(label);

        return hBox;
    }

    @Override
    public HBox createLabelHBox(Pane pane, String information, SimpleObjectProperty<Object> data) {
        HBox hBox = new HBox();
        hBox.prefWidthProperty().bind(pane.widthProperty());
        hBox.prefHeightProperty().bind(pane.heightProperty().divide(10.0));
        hBox.setAlignment(Pos.CENTER);

        Label informationLabel = new Label(information);
        informationLabel.prefWidthProperty().bind(hBox.widthProperty().divide(3.0));
        informationLabel.prefHeightProperty().bind(hBox.heightProperty());
        informationLabel.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        informationLabel.setAlignment(Pos.CENTER);

        Label dataLabel = new Label(String.valueOf(data.get()));
        dataLabel.prefWidthProperty().bind(hBox.widthProperty().divide(3.0));
        dataLabel.prefHeightProperty().bind(hBox.heightProperty());
        dataLabel.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dataLabel.setAlignment(Pos.CENTER);

        hBox.getChildren().addAll(informationLabel, dataLabel);

        data.addListener((observable, oldValue, newValue) -> {dataLabel.setText(String.valueOf(newValue));});

        return hBox;
    }

    @Override
    public HBox createTextFieldHBox(Pane pane, String information, SimpleObjectProperty<Object> data) {
        HBox hBox = new HBox();
        hBox.prefWidthProperty().bind(pane.widthProperty());
        hBox.prefHeightProperty().bind(pane.heightProperty().divide(10.0));
        hBox.setAlignment(Pos.CENTER);

        Label label = new Label(information);
        label.prefWidthProperty().bind(hBox.widthProperty().divide(3.0));
        label.prefHeightProperty().bind(hBox.heightProperty());
        label.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        label.setAlignment(Pos.CENTER);

        TextField textField = new TextField(String.valueOf(data.get()));
        textField.prefWidthProperty().bind(hBox.widthProperty().divide(3.0));
        textField.prefHeightProperty().bind(hBox.heightProperty());
        textField.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        textField.setAlignment(Pos.CENTER);

        textField.textProperty().addListener((observable, oldValue, newValue) -> data.setValue(newValue));
        data.addListener((observable, oldValue, newValue) -> textField.setText(String.valueOf(newValue)));

        hBox.getChildren().addAll(label, textField);

        return hBox;
    }

    @Override
    public HBox createChooseButtonHBox(Pane pane, String information, SimpleObjectProperty<Object> data) {
        HBox hBox = new HBox();
        hBox.prefWidthProperty().bind(pane.widthProperty());
        hBox.prefHeightProperty().bind(pane.heightProperty().divide(10.0));
        hBox.setAlignment(Pos.CENTER);

        HBox labelHBox = new HBox();
        labelHBox.prefWidthProperty().bind(pane.widthProperty().divide(3.0));
        labelHBox.prefHeightProperty().bind(pane.heightProperty());
        labelHBox.setAlignment(Pos.CENTER);

        HBox chooseButtonHBox = new HBox();
        chooseButtonHBox.prefWidthProperty().bind(hBox.widthProperty().divide(3.0));
        chooseButtonHBox.prefHeightProperty().bind(hBox.heightProperty());
        chooseButtonHBox.setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        chooseButtonHBox.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();

        Label label = new Label(information);
        label.prefWidthProperty().bind(labelHBox.widthProperty());
        label.prefHeightProperty().bind(labelHBox.heightProperty());
        label.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        label.setAlignment(Pos.CENTER);

        RadioButton onRadioButton = new RadioButton("开");
        onRadioButton.prefWidthProperty().bind(chooseButtonHBox.widthProperty().divide(2.0));
        onRadioButton.prefHeightProperty().bind(chooseButtonHBox.heightProperty());
        onRadioButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        onRadioButton.setAlignment(Pos.CENTER);

        RadioButton offRadioButton = new RadioButton("关");
        offRadioButton.prefWidthProperty().bind(chooseButtonHBox.widthProperty().divide(2.0));
        offRadioButton.prefHeightProperty().bind(chooseButtonHBox.heightProperty());
        offRadioButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        offRadioButton.setAlignment(Pos.CENTER);

        if (Boolean.parseBoolean(String.valueOf(data.get()))) {
            onRadioButton.setSelected(true);
        } else {
            offRadioButton.setSelected(true);
        }

        onRadioButton.setToggleGroup(toggleGroup);
        offRadioButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> data.setValue(newValue == onRadioButton));
        data.addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                onRadioButton.setSelected(true);
            } else {
                offRadioButton.setSelected(true);
            }
        });

        labelHBox.getChildren().addAll(label);
        chooseButtonHBox.getChildren().addAll(onRadioButton, offRadioButton);
        hBox.getChildren().addAll(labelHBox, chooseButtonHBox);

        return hBox;
    }

}