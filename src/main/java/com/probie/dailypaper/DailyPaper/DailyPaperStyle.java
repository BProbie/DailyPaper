package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser;
import javafx.scene.control.ScrollPane;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperStyle;

@Data
public class DailyPaperStyle implements IDailyPaperStyle {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperStyle INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperStyle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperStyle();
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
    public void createStyle() {

        createStageStyle();
        createSceneStyle();
        createRootStyle();
        createChatStyle();
        createLiveStyle();
        createDailyStyle();
        createParamStyle();
        createSettingStyle();
        createRenewStyle();

    }

    @Override
    public void createStageStyle() {
        dailyPaperElement.getStage().setWidth(Math.clamp(Integer.parseInt(String.valueOf(dailyPaper.getDailyPaperStageWidth().get())), 500, 2000));
        dailyPaperElement.getStage().setHeight(Math.clamp(Integer.parseInt(String.valueOf(dailyPaper.getDailyPaperStageHeight().get())), 500, 2000));
        dailyPaperElement.getStage().initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void createSceneStyle() {

    }

    @Override
    public void createRootStyle() {
        /// title
        dailyPaperElement.getRootPaneTopTitleBarHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().widthProperty().divide(6.0).multiply(1.0));
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeLarge().get())));
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().setAlignment(Pos.CENTER_LEFT);

        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarLogoHBox().widthProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarLogoHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setText(dailyPaper.getNAME());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setAlignment(Pos.CENTER_LEFT);

        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().widthProperty().divide(6.0).multiply(5.0));
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmallSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().setAlignment(Pos.CENTER_RIGHT);

        dailyPaperElement.getRootPaneTopTitleBarMinButton().setPrefWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setPrefHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setPrefWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setPrefHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setPrefWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setPrefHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeSizeSmall().get())));

        dailyPaperElement.getRootPaneTopTitleBarMinButton().setText("-");
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setText(dailyPaperElement.getStage().isMaximized() ? "⊟" : "□");
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setText("x");
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setAlignment(Pos.CENTER);

        /// menu
        dailyPaperElement.getRootPaneLeftMenuBarVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getRootPaneLeftMenuBarChooseVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setText("聊天");
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setText("动图");
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setText("每日");
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setText("参数");
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setText("设置");
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setMinWidth(Integer.parseInt(String.valueOf(dailyPaperData.getNodeWidthMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setMinHeight(Integer.parseInt(String.valueOf(dailyPaperData.getNodeHeightMedium().get())));
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setText("更新");
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setAlignment(Pos.CENTER);
    }

    @Override
    public void createChatStyle() {
        dailyPaperElement.getChatVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getChatVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());

        dailyPaperElement.getChatTextShowVBox().prefWidthProperty().bind(dailyPaperElement.getChatVBox().widthProperty());
        dailyPaperElement.getChatTextShowVBox().minHeightProperty().bind(dailyPaperElement.getChatVBox().heightProperty().divide(4.0).multiply(3.0));
        dailyPaperElement.getChatTextShowVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getChatTextShowScrollPane().prefWidthProperty().bind(dailyPaperElement.getChatTextShowVBox().widthProperty());
        dailyPaperElement.getChatTextShowScrollPane().prefHeightProperty().bind(dailyPaperElement.getChatTextShowVBox().heightProperty());
        dailyPaperElement.getChatTextShowScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getChatTextShowScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getChatTextShowMessageVBox().prefWidthProperty().bind(dailyPaperElement.getChatTextShowScrollPane().widthProperty());
        dailyPaperElement.getChatTextShowMessageVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getChatTextShowMessageVBox().getChildren().clear();

        dailyPaperElement.getChatTextInputVBox().prefWidthProperty().bind(dailyPaperElement.getChatVBox().widthProperty());
        dailyPaperElement.getChatTextInputVBox().prefHeightProperty().bind(dailyPaperElement.getChatVBox().heightProperty().divide(4.0).multiply(1.0));

        dailyPaperElement.getChatTextInputScrollPane().prefWidthProperty().bind(dailyPaperElement.getChatTextInputVBox().widthProperty());
        dailyPaperElement.getChatTextInputScrollPane().prefHeightProperty().bind(dailyPaperElement.getChatTextInputVBox().heightProperty().divide(6.0).multiply(5.0));
        dailyPaperElement.getChatTextInputScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getChatTextInputScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getChatTextInputTextArea().prefWidthProperty().bind(dailyPaperElement.getChatTextInputScrollPane().widthProperty());
        dailyPaperElement.getChatTextInputTextArea().prefHeightProperty().bind(dailyPaperElement.getChatTextInputScrollPane().heightProperty());
        dailyPaperElement.getChatTextInputTextArea().setWrapText(true);
        dailyPaperElement.getChatTextInputTextArea().setPromptText("请输入提示词...");
        dailyPaperElement.getChatTextInputTextArea().clear();
        dailyPaperElement.getChatTextInputTextArea().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeLarge().get()))));

        dailyPaperElement.getChatTextInputToolsHBox().prefWidthProperty().bind(dailyPaperElement.getChatTextInputVBox().widthProperty());
        dailyPaperElement.getChatTextInputToolsHBox().prefHeightProperty().bind(dailyPaperElement.getChatTextInputVBox().heightProperty().divide(6.0).multiply(1.0));
        dailyPaperElement.getChatTextInputToolsHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getChatTextInputToolsHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getChatTextInputToolsUploadImageButton().setText("上传图像");
        dailyPaperElement.getChatTextInputToolsUploadImageButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getChatTextInputToolsUploadImageButton().setAlignment(Pos.CENTER);

        dailyPaperData.getChatUserMessageArrayList().clear();
        dailyPaperData.getChatAgentMessageArrayList().clear();
    }

    @Override
    public void createLiveStyle() {
        dailyPaperElement.getLiveVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getLiveVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());

        dailyPaperElement.getLiveImageChooseHBox().prefWidthProperty().bind(dailyPaperElement.getLiveVBox().widthProperty());
        dailyPaperElement.getLiveImageChooseHBox().prefHeightProperty().bind(dailyPaperElement.getLiveVBox().heightProperty().divide(8.0).multiply(1.0));

        dailyPaperElement.getLiveImageChooseButton().prefWidthProperty().bind(dailyPaperElement.getLiveImageChooseHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getLiveImageChooseButton().prefHeightProperty().bind(dailyPaperElement.getLiveImageChooseHBox().heightProperty());
        dailyPaperElement.getLiveImageChooseButton().setText("选择任意壁纸");
        dailyPaperElement.getLiveImageChooseButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getLiveImageChooseButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageChooseLabel().setText(String.valueOf(dailyPaper.getLiveImageChosenFilePath().get()));
        dailyPaperElement.getLiveImageChooseLabel().prefWidthProperty().bind(dailyPaperElement.getLiveImageChooseHBox().widthProperty().divide(5.0).multiply(4.0));
        dailyPaperElement.getLiveImageChooseLabel().prefHeightProperty().bind(dailyPaperElement.getLiveImageChooseHBox().heightProperty());
        dailyPaperElement.getLiveImageChooseLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getLiveImageChooseLabel().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageChooseFileChooser().setInitialDirectory(new File(dailyPaperElement.getLiveImageChooseLabel().getText()));
        dailyPaperElement.getLiveImageChooseFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );

        dailyPaperElement.getLiveImageShowSureVBox().prefWidthProperty().bind(dailyPaperElement.getLiveVBox().widthProperty());
        dailyPaperElement.getLiveImageShowSureVBox().prefHeightProperty().bind(dailyPaperElement.getLiveVBox().heightProperty().divide(8.0).multiply(7.0));
        dailyPaperElement.getLiveImageShowSureVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().bind(dailyPaperElement.getLiveImageShowSureVBox().widthProperty());
        dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().bind(dailyPaperElement.getLiveImageShowSureVBox().heightProperty().divide(7.0).multiply(6.0));
        dailyPaperElement.getLiveImageShowHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageSureHBox().prefWidthProperty().bind(dailyPaperElement.getLiveImageShowSureVBox().widthProperty());
        dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().bind(dailyPaperElement.getLiveImageShowSureVBox().heightProperty().divide(7.0).multiply(1.0));
        dailyPaperElement.getLiveImageSureHBox().setAlignment(Pos.CENTER);

        dailyPaperData.getIsLiveImageShowing().set(false);
        dailyPaperElement.getLiveImageShowImageView().setImage(null);
        dailyPaperElement.getLiveImageSureHBox().getChildren().clear();
    }

    @Override
    public void createDailyStyle() {
        dailyPaperElement.getDailyVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getDailyVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getDailyVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getDailyChooseHBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyChooseHBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyChooseHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyChooseHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperLabel().setText("推荐壁纸");
        dailyPaperElement.getDailyWallpaperLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyWallpaperOnButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyWallpaperOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyWallpaperOffButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyWallpaperOffButton().setAlignment(Pos.CENTER);
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyAutoWallpaper().get()))) {
            dailyPaperElement.getDailyWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyLaunchHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyLaunchHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyLaunchLabel().setText("开机自启");
        dailyPaperElement.getDailyLaunchLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchOnButton().setText("开");
        dailyPaperElement.getDailyLaunchOnButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchOffButton().setText("关");
        dailyPaperElement.getDailyLaunchOffButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchOffButton().setAlignment(Pos.CENTER);
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyPaperAutoLaunch().get()))) {
            dailyPaperElement.getDailyLaunchOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyLaunchOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyWallpaperChooseHBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperChooseHBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyWallpaperChooseHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyWallpaperChooseHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyAutoWallpaper().get())));

        dailyPaperElement.getDailyLaunchWallpaperHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyLaunchWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyLaunchWallpaperLabel().setText("程序开启时推荐壁纸");
        dailyPaperElement.getDailyLaunchWallpaperLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setAlignment(Pos.CENTER);
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyAutoWallpaperWhenLaunch().get()))) {
            dailyPaperElement.getDailyLaunchWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyLaunchWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyTimeWallpaperHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyTimeWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyTimeWallpaperLabel().setText("另外每隔几分钟推荐一次壁纸");
        dailyPaperElement.getDailyTimeWallpaperLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyTimeWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyTimeWallpaperTextField().setText(String.valueOf(dailyPaper.getDailyAutoWallpaperWhenTime().get()));
        dailyPaperElement.getDailyTimeWallpaperTextField().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));

        dailyPaperElement.getDailyWallpaperHobbyVBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyVBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(8.0));
        dailyPaperElement.getDailyWallpaperHobbyVBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyAutoWallpaper().get())));

        dailyPaperElement.getDailyWallpaperHobbyLabel().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyLabel().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyWallpaperHobbyLabel().setText("推荐壁纸喜好描述");
        dailyPaperElement.getDailyWallpaperHobbyLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyWallpaperHobbyLabel().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperHobbyScrollPane().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyScrollPane().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().heightProperty().divide(10.0).multiply(8.0));
        dailyPaperElement.getDailyWallpaperHobbyScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getDailyWallpaperHobbyScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getDailyWallpaperHobbyTextArea().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyScrollPane().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyTextArea().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyScrollPane().heightProperty());
        dailyPaperElement.getDailyWallpaperHobbyTextArea().setWrapText(true);
        dailyPaperElement.getDailyWallpaperHobbyTextArea().setText(String.valueOf(dailyPaper.getDailyImageHobby().get()));
        dailyPaperElement.getDailyWallpaperHobbyTextArea().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));

        dailyPaperElement.getDailyWallpaperHobbyToolsHBox().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyToolsHBox().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyWallpaperHobbyToolsHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getDailyWallpaperHobbyToolsHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setText("结合图像");
        dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setAlignment(Pos.CENTER);
    }

    @Override
    public void createParamStyle() {
        dailyPaperElement.getParamInputScrollPaneVBox().getChildren().clear();

        dailyPaperElement.getParamVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getParamVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getParamVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getParamInputVBox().prefWidthProperty().bind(dailyPaperElement.getParamVBox().widthProperty());
        dailyPaperElement.getParamInputVBox().prefHeightProperty().bind(dailyPaperElement.getParamVBox().heightProperty().divide(10.0).multiply(9.0));
        dailyPaperElement.getParamInputVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getParamInputScrollPane().prefWidthProperty().bind(dailyPaperElement.getParamInputVBox().widthProperty());
        dailyPaperElement.getParamInputScrollPane().prefHeightProperty().bind(dailyPaperElement.getParamInputVBox().heightProperty());
        dailyPaperElement.getParamInputScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        隐藏竖滚动条
//        dailyPaperElement.getParamInputScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getParamInputScrollPaneVBox().prefWidthProperty().bind(dailyPaperElement.getParamInputScrollPane().widthProperty());
        dailyPaperElement.getParamInputScrollPaneVBox().prefHeightProperty().bind(dailyPaperElement.getParamInputScrollPane().prefHeightProperty());
        dailyPaperElement.getParamInputScrollPaneVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeMedium().get())));

        dailyPaperElement.getParamInputScrollPaneVBox().getChildren().addAll(
                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "大小参数"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "主窗口宽度", dailyPaper.getDailyPaperStageWidth()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "主窗口高度", dailyPaper.getDailyPaperStageHeight()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "速度参数"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "动图播放速度", dailyPaper.getLiveImagePlaySpeed()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "聊天参数"),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "单次最大消息容量", dailyPaper.getSendMaxMessage()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "Web请求参数"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "最大连接时间", dailyPaper.getConnectTimeout()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "最大读取时间", dailyPaper.getReadTimeout()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "最大写入时间", dailyPaper.getWriteTimeout()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "AI生成参数"),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "上下文最大容量", dailyPaper.getSendMaxContent()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "图片生成大小", dailyPaper.getSpawnImageSize()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "图片生成数量", dailyPaper.getSpawnImageCount()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "单次最大词元", dailyPaper.getSpawnMaxTokens()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "标记参数"),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "本地图片标符", dailyPaper.getUploadImageFullFilePathMark()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getParamInputScrollPaneVBox(), "字符分割标符", dailyPaper.getSplitMark())
                );

        dailyPaperElement.getParamButtonBarHBox().prefHeightProperty().bind(dailyPaperElement.getParamVBox().widthProperty());
        dailyPaperElement.getParamButtonBarHBox().prefHeightProperty().bind(dailyPaperElement.getParamVBox().heightProperty().divide(10.0));
        dailyPaperElement.getParamButtonBarHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getParamButtonBarResetButton().prefWidthProperty().bind(dailyPaperElement.getParamButtonBarHBox().widthProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getParamButtonBarResetButton().prefHeightProperty().bind(dailyPaperElement.getParamButtonBarHBox().heightProperty());
        dailyPaperElement.getParamButtonBarResetButton().setText("重置");
        dailyPaperElement.getParamButtonBarResetButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getParamButtonBarResetButton().setAlignment(Pos.CENTER);
    }

    @Override
    public void createSettingStyle() {
        dailyPaperElement.getSettingInputScrollPaneVBox().getChildren().clear();

        dailyPaperElement.getSettingVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getSettingVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getSettingVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getSettingInputVBox().prefWidthProperty().bind(dailyPaperElement.getSettingVBox().widthProperty());
        dailyPaperElement.getSettingInputVBox().prefHeightProperty().bind(dailyPaperElement.getSettingVBox().heightProperty().divide(10.0).multiply(9.0));
        dailyPaperElement.getSettingInputVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getSettingInputScrollPane().prefWidthProperty().bind(dailyPaperElement.getSettingInputVBox().widthProperty());
        dailyPaperElement.getSettingInputScrollPane().prefHeightProperty().bind(dailyPaperElement.getSettingInputVBox().heightProperty());
        dailyPaperElement.getSettingInputScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        隐藏竖滚动条
//        dailyPaperElement.getSettingInputScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getSettingInputScrollPaneVBox().prefWidthProperty().bind(dailyPaperElement.getSettingInputScrollPane().widthProperty());
        dailyPaperElement.getSettingInputScrollPaneVBox().prefHeightProperty().bind(dailyPaperElement.getSettingInputScrollPane().prefHeightProperty());
        dailyPaperElement.getSettingInputScrollPaneVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeMedium().get())));

        dailyPaperElement.getSettingInputScrollPaneVBox().getChildren().addAll(
                dailyPaperFunction.createTitleHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "文件设置"),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前文件路径", dailyPaper.getCurrentFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "主根文件路径", dailyPaper.getDailyPaperFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "配置文件路径", dailyPaper.getConfigFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "缓存文件路径", dailyPaper.getTempFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Java环境路径", dailyPaper.getJavaFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "工库文件路径", dailyPaper.getLibFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "动态图片存放路径", dailyPaper.getLiveImageFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "日志文件存放路径", dailyPaper.getLogConfigFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "动图配置文件名称", dailyPaper.getLiveImageConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "参数配置文件名称", dailyPaper.getParamConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "设置配置文件名称", dailyPaper.getSettingConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "更新配置文件名称", dailyPaper.getRenewConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "日志文件名称", dailyPaper.getLogConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "调试日志文件名称", dailyPaper.getLogDebugConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "信息日志文件名称", dailyPaper.getLogInfoConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "警告日志文件名称", dailyPaper.getLogWarnConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "错误日志文件名称", dailyPaper.getLogErrorConfigFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "缓存图片文件名称", dailyPaper.getTempImageFileName()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "AI设置"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "硅基流动API密钥", dailyPaper.getAPIKeySiliconFlow()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Qwen30_8B大模型", dailyPaper.getQwen30_8BModelSiliconFlow()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Kolors大模型", dailyPaper.getKolorsModelSiliconFlow()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Qwen35_4B大模型", dailyPaper.getQwen35_4BModelSiliconFlow()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "GLM_41V_9B_Thinking大模型", dailyPaper.getGLM_41V_9B_ThinkingModelSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前文生文大模型", dailyPaper.getAPIModelTextToTextSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前文生图大模型", dailyPaper.getAPIModelTextToImageSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前图生文大模型", dailyPaper.getAPIModelImageToTextSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前文本解释模型", dailyPaper.getAPIModelTextSiliconFlowAnalysis()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前图像解释模型", dailyPaper.getAPIModelImageSiliconFlowAnalysis()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "文生文URL地址", dailyPaper.getAPIUrlTextToTextSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "文生图URL地址", dailyPaper.getAPIUrlTextToImageSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "图生文URL地址", dailyPaper.getAPIUrlImageToTextSiliconFlow()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "文本解释URL地址", dailyPaper.getAPIUrlTextSiliconFlowAnalysis()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "图像解释URL地址", dailyPaper.getAPIUrlImageSiliconFlowAnalysis()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "更新设置"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Github更新URL地址", dailyPaper.getGithubRenewUri()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Windows更新配置文件URL地址", dailyPaper.getRenewConfigRenewUriWindows()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Linux更新配置文件URL地址", dailyPaper.getRenewConfigRenewUriLinux()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Mac更新配置文件URL地址", dailyPaper.getRenewConfigRenewUriMac()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Android更新配置文件URL地址", dailyPaper.getRenewConfigRenewUriAndroid()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前更新配置文件URL地址", dailyPaper.getRenewConfigRenewUri()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Renew工具本地路径", dailyPaper.getRenewRenewLocalFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Renew工具本地名称", dailyPaper.getRenewRenewLocalFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "DailyPaper本地更新路径", dailyPaper.getDailyPaperRenewLocalFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "DailyPaper本地更新名称", dailyPaper.getDailyPaperRenewLocalFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Windows中DailyPaper更新URL地址", dailyPaper.getDailyPaperRenewUriWindows()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Linux中DailyPaper更新URL地址", dailyPaper.getDailyPaperRenewUriLinux()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Mac中DailyPaper更新URL地址", dailyPaper.getDailyPaperRenewUriMac()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "Android中DailyPaper更新URL地址", dailyPaper.getDailyPaperRenewUriAndroid()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "当前DailyPaper更新URL地址", dailyPaper.getDailyPaperRenewUri()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "DailyPaper更新后是否自动启动", dailyPaper.getDailyPaperRenewAutoOpen()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "记忆设置"),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "聊天图片下载地址", dailyPaper.getChatImageDownloadFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "聊天图片下载名称", dailyPaper.getChatImageDownloadFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "聊天图片上传文件选择默认路径", dailyPaper.getChatTextInputToolsImageUploadImageChosenFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "聊天图片上传文件选择默认名称", dailyPaper.getChatTextInputToolsImageUploadImageChosenFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "动图文件选择默认路径", dailyPaper.getLiveImageChosenFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "动图文件选择默认名称", dailyPaper.getLiveImageChosenFileName()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "软件启动时动图自动启动", dailyPaper.getLiveImageAutoLaunch()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "启用每日壁纸推荐", dailyPaper.getDailyAutoWallpaper()),
                dailyPaperFunction.createLabelHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "开机自动启动", dailyPaper.getDailyPaperAutoLaunch()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "启用程序启动时推送每日壁纸", dailyPaper.getDailyAutoWallpaperWhenLaunch()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "启用每隔一定时间推送每日壁纸", dailyPaper.getDailyAutoWallpaperWhenTime()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "每日壁纸偏好", dailyPaper.getDailyImageHobby()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "选择每日壁纸参考默认选择路径", dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFilePath()),
                dailyPaperFunction.createTextFieldHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "选择每日壁纸参考默认选择名称", dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFileName()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "自动检查更新", dailyPaper.getRenewAutoCheckRenew()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "自动下载更新", dailyPaper.getRenewAutoDownloadRenew()),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "更新后自动启动软件", dailyPaper.getDailyPaperAutoLaunch()),

                dailyPaperFunction.createTitleHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "测试设置"),
                dailyPaperFunction.createChooseButtonHBox(dailyPaperElement.getSettingInputScrollPaneVBox(), "启用测试模式", dailyPaper.getDebug())
                );

        dailyPaperElement.getSettingButtonBarHBox().prefHeightProperty().bind(dailyPaperElement.getSettingVBox().widthProperty());
        dailyPaperElement.getSettingButtonBarHBox().prefHeightProperty().bind(dailyPaperElement.getSettingVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getSettingButtonBarHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getSettingButtonBarHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getSettingButtonBarPluginButton().prefWidthProperty().bind(dailyPaperElement.getSettingButtonBarHBox().widthProperty().divide(10.0));
        dailyPaperElement.getSettingButtonBarPluginButton().prefHeightProperty().bind(dailyPaperElement.getSettingButtonBarHBox().heightProperty());
        dailyPaperElement.getSettingButtonBarPluginButton().setText("插件");
        dailyPaperElement.getSettingButtonBarPluginButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getSettingButtonBarPluginButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getSettingButtonBarResetButton().prefWidthProperty().bind(dailyPaperElement.getSettingButtonBarHBox().widthProperty().divide(10.0));
        dailyPaperElement.getSettingButtonBarResetButton().prefHeightProperty().bind(dailyPaperElement.getSettingButtonBarHBox().heightProperty());
        dailyPaperElement.getSettingButtonBarResetButton().setText("重置");
        dailyPaperElement.getSettingButtonBarResetButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getSettingButtonBarResetButton().setAlignment(Pos.CENTER);
    }

    @Override
    public void createRenewStyle() {
        dailyPaperElement.getRenewVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getRenewVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getRenewVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualRenewVBox().prefWidthProperty().bind(dailyPaperElement.getRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualRenewVBox().prefHeightProperty().bind(dailyPaperElement.getRenewVBox().heightProperty().divide(6.0).multiply(4.0));
        dailyPaperElement.getRenewManualRenewVBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewManualRenewVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualCheckRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualCheckRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualRenewVBox().heightProperty().divide(4.0).multiply(1.0));
        dailyPaperElement.getRenewManualCheckRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewManualCheckRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualCheckRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewManualCheckRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewManualCheckRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewManualCheckRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualCheckRenewButton().setText("检查更新");
        dailyPaperElement.getRenewManualCheckRenewButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewManualCheckRenewButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualShowRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualShowRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualRenewVBox().heightProperty().divide(4.0).multiply(3.0));
        dailyPaperElement.getRenewManualShowRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewManualShowRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewManualShowRenewHBox().setVisible(false);

        dailyPaperElement.getRenewManualShowRenewScrollPane().maxWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().widthProperty().divide(2.0).multiply(1.0));
        dailyPaperElement.getRenewManualShowRenewScrollPane().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualShowRenewScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getRenewManualShowRenewScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getRenewManualShowRenewTextArea().prefWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewScrollPane().widthProperty());
        dailyPaperElement.getRenewManualShowRenewTextArea().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewScrollPane().heightProperty());
        dailyPaperElement.getRenewManualShowRenewTextArea().setEditable(false);
        dailyPaperElement.getRenewManualShowRenewTextArea().setWrapText(true);
        dailyPaperElement.getRenewManualShowRenewTextArea().clear();
        dailyPaperElement.getRenewManualShowRenewTextArea().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));

        dailyPaperElement.getRenewManualDownloadRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().widthProperty());
        dailyPaperElement.getRenewManualDownloadRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().heightProperty().divide(4.0).multiply(1.0));
        dailyPaperElement.getRenewManualDownloadRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewManualDownloadRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewManualDownloadRenewHBox().setVisible(false);

        dailyPaperElement.getRenewManualDownloadRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewManualDownloadRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewManualDownloadRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewManualDownloadRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualDownloadRenewButton().setText("立即更新");
        dailyPaperElement.getRenewManualDownloadRenewButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewManualDownloadRenewButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewVBox().widthProperty());
        dailyPaperElement.getRenewAutoRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewVBox().heightProperty().divide(6.0).multiply(1.0));
        dailyPaperElement.getRenewAutoRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));

        dailyPaperElement.getRenewAutoCheckRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().widthProperty().divide(3.0).multiply(1.0));
        dailyPaperElement.getRenewAutoCheckRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().heightProperty());
        dailyPaperElement.getRenewAutoCheckRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewAutoCheckRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoCheckRenewLabel().setText("自动检测更新");
        dailyPaperElement.getRenewAutoCheckRenewLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoCheckRenewLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setText("开");
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setText("关");
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setAlignment(Pos.CENTER);
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getRenewAutoCheckRenew().get()))) {
            dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewAutoCheckRenewOffButton().setSelected(true);
        }

        dailyPaperElement.getRenewAutoDownloadRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().widthProperty().divide(3.0).multiply(1.0));
        dailyPaperElement.getRenewAutoDownloadRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().heightProperty());
        dailyPaperElement.getRenewAutoDownloadRenewHBox().setSpacing(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get())));
        dailyPaperElement.getRenewAutoDownloadRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoDownloadRenewLabel().setText("自动下载软件");
        dailyPaperElement.getRenewAutoDownloadRenewLabel().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoDownloadRenewLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setText("开");
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setText("关");
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setAlignment(Pos.CENTER);
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getRenewAutoDownloadRenew().get()))) {
            dailyPaperElement.getRenewAutoDownloadRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewAutoDownloadRenewOffButton().setSelected(true);
        }
    }

}