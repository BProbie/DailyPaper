package com.probie.dailypaper.DailyPaper;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import lombok.Data;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser;
import com.probie.dailypaper.Config.ParamConfig;
import com.probie.dailypaper.Config.SettingConfig;
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
        dailyPaperElement.getStage().setWidth(dailyPaper.getDailyPaperStageWidth().get());
        dailyPaperElement.getStage().setHeight(dailyPaper.getDailyPaperStageHeight().get());
        dailyPaperElement.getStage().initStyle(StageStyle.UNDECORATED);
    }

    @Override
    public void createSceneStyle() {

    }

    @Override
    public void createRootStyle() {
        /// title
        dailyPaperElement.getRootPaneTopTitleBarHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().widthProperty().divide(6.0).multiply(1.0));
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().setSpacing(dailyPaperData.getSpacingSizeLarge().get());
        dailyPaperElement.getRootPaneTopTitleBarLogoHBox().setAlignment(Pos.CENTER_LEFT);

        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarLogoHBox().widthProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarLogoHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setText(dailyPaper.getNAME());
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneTopTitleBarLogoLabel().setAlignment(Pos.CENTER_LEFT);

        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().widthProperty().divide(6.0).multiply(5.0));
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneTopTitleBarHBox().heightProperty());
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarChooseHBox().setAlignment(Pos.CENTER_RIGHT);

        dailyPaperElement.getRootPaneTopTitleBarMinButton().setPrefWidth(dailyPaperData.getNodeSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setPrefHeight(dailyPaperData.getNodeSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setPrefWidth(dailyPaperData.getNodeSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setPrefHeight(dailyPaperData.getNodeSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setPrefWidth(dailyPaperData.getNodeSizeSmall().get());
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setPrefHeight(dailyPaperData.getNodeSizeSmall().get());

        dailyPaperElement.getRootPaneTopTitleBarMinButton().setText("-");
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setText(dailyPaperElement.getStage().isMaximized() ? "⊟" : "□");
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setText("×");
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setAlignment(Pos.CENTER);

        /// menu
        dailyPaperElement.getRootPaneLeftMenuBarVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getRootPaneLeftMenuBarChooseVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setText("聊天");
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setText("动图");
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setText("每日");
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setText("参数");
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setText("设置");
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setMinWidth(dailyPaperData.getNodeWidthMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setMinHeight(dailyPaperData.getNodeHeightMedium().get());
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setText("更新");
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setAlignment(Pos.CENTER);
    }

    @Override
    public void createChatStyle() {
        dailyPaperElement.getChatVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getChatVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());

        dailyPaperElement.getChatTextShowVBox().prefWidthProperty().bind(dailyPaperElement.getChatVBox().widthProperty());
        dailyPaperElement.getChatTextShowVBox().minHeightProperty().bind(dailyPaperElement.getChatVBox().heightProperty().divide(5.0).multiply(4.0));
        dailyPaperElement.getChatTextShowVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getChatTextShowScrollPane().prefWidthProperty().bind(dailyPaperElement.getChatTextShowVBox().widthProperty());
        dailyPaperElement.getChatTextShowScrollPane().prefHeightProperty().bind(dailyPaperElement.getChatTextShowVBox().heightProperty());
        dailyPaperElement.getChatTextShowScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getChatTextShowScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getChatTextShowMessageVBox().prefWidthProperty().bind(dailyPaperElement.getChatTextShowScrollPane().widthProperty());
        dailyPaperElement.getChatTextShowMessageVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getChatTextShowMessageVBox().getChildren().clear();

        dailyPaperElement.getChatTextInputVBox().prefWidthProperty().bind(dailyPaperElement.getChatVBox().widthProperty());
        dailyPaperElement.getChatTextInputVBox().prefHeightProperty().bind(dailyPaperElement.getChatVBox().heightProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getChatTextInputVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getChatTextInputVBox().setBackground(Background.fill(Color.GREEN));

        dailyPaperElement.getChatTextInputScrollPane().prefWidthProperty().bind(dailyPaperElement.getChatTextInputVBox().widthProperty());
        dailyPaperElement.getChatTextInputScrollPane().prefHeightProperty().bind(dailyPaperElement.getChatTextInputVBox().heightProperty());
        dailyPaperElement.getChatTextInputScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getChatTextInputScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getChatTextInputTextArea().prefWidthProperty().bind(dailyPaperElement.getChatTextInputScrollPane().widthProperty());
        dailyPaperElement.getChatTextInputTextArea().prefHeightProperty().bind(dailyPaperElement.getChatTextInputScrollPane().heightProperty());
        dailyPaperElement.getChatTextInputTextArea().setWrapText(true);
        dailyPaperElement.getChatTextInputTextArea().setPromptText("请输入提示词...");
        dailyPaperElement.getChatTextInputTextArea().clear();
        dailyPaperElement.getChatTextInputTextArea().setFont(new Font(dailyPaperData.getFontSizeLarge().get()));

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
        dailyPaperElement.getLiveImageChooseButton().setText("选择动态在内的任意壁纸");
        dailyPaperElement.getLiveImageChooseButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getLiveImageChooseButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageChooseLabel().setText(dailyPaper.getLiveImageChosenFilePath().get());
        dailyPaperElement.getLiveImageChooseLabel().prefWidthProperty().bind(dailyPaperElement.getLiveImageChooseHBox().widthProperty().divide(5.0).multiply(4.0));
        dailyPaperElement.getLiveImageChooseLabel().prefHeightProperty().bind(dailyPaperElement.getLiveImageChooseHBox().heightProperty());
        dailyPaperElement.getLiveImageChooseLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getLiveImageChooseLabel().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageChooseFileChooser().setInitialDirectory(new File(dailyPaperElement.getLiveImageChooseLabel().getText()));
        dailyPaperElement.getLiveImageChooseFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );

        dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().bind(dailyPaperElement.getLiveVBox().widthProperty());
        dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().bind(dailyPaperElement.getLiveVBox().heightProperty().divide(8.0).multiply(6.0));
        dailyPaperElement.getLiveImageShowHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getLiveImageSureHBox().prefWidthProperty().bind(dailyPaperElement.getLiveVBox().widthProperty());
        dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().bind(dailyPaperElement.getLiveVBox().heightProperty().divide(8.0).multiply(1.0));
        dailyPaperElement.getLiveImageSureHBox().setAlignment(Pos.CENTER);

        dailyPaperData.setIsLiveImageShowing(() -> false);
        dailyPaperElement.getLiveImageShowImageView().setImage(null);
        dailyPaperElement.getLiveImageSureHBox().getChildren().clear();
    }

    @Override
    public void createDailyStyle() {
        dailyPaperElement.getDailyVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getDailyVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getDailyVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getDailyChooseHBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyChooseHBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyChooseHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyChooseHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyWallpaperLabel().setText("推荐壁纸");
        dailyPaperElement.getDailyWallpaperLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyWallpaperOnButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyWallpaperOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyWallpaperOffButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyWallpaperOffButton().setAlignment(Pos.CENTER);
        if (dailyPaper.getDailyAutoWallpaper().get()) {
            dailyPaperElement.getDailyWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyLaunchHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyLaunchHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyLaunchLabel().setText("开机自启");
        dailyPaperElement.getDailyLaunchLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchOnButton().setText("开");
        dailyPaperElement.getDailyLaunchOnButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchOffButton().setText("关");
        dailyPaperElement.getDailyLaunchOffButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchOffButton().setAlignment(Pos.CENTER);
        if (dailyPaper.getDailyPaperAutoLaunch().get()) {
            dailyPaperElement.getDailyLaunchOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyLaunchOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyWallpaperChooseHBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperChooseHBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyWallpaperChooseHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyWallpaperChooseHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(dailyPaper.getDailyAutoWallpaper().get());

        dailyPaperElement.getDailyLaunchWallpaperHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyLaunchWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyLaunchWallpaperLabel().setText("程序开启时推荐壁纸");
        dailyPaperElement.getDailyLaunchWallpaperLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchWallpaperOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyLaunchWallpaperOffButton().setAlignment(Pos.CENTER);
        if (dailyPaper.getDailyAutoWallpaperWhenLaunch().get()) {
            dailyPaperElement.getDailyLaunchWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyLaunchWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyTimeWallpaperHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyTimeWallpaperHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyTimeWallpaperLabel().setText("另外每隔几分钟推荐一次壁纸");
        dailyPaperElement.getDailyTimeWallpaperLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyTimeWallpaperLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyTimeWallpaperTextField().setText(String.valueOf(dailyPaper.getDailyAutoWallpaperWhenTime().get()));
        dailyPaperElement.getDailyTimeWallpaperTextField().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));

        dailyPaperElement.getDailyWallpaperHobbyVBox().prefWidthProperty().bind(dailyPaperElement.getDailyVBox().widthProperty());
        dailyPaperElement.getDailyWallpaperHobbyVBox().prefHeightProperty().bind(dailyPaperElement.getDailyVBox().heightProperty().divide(10.0).multiply(8.0));
        dailyPaperElement.getDailyWallpaperHobbyVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getDailyWallpaperHobbyVBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(dailyPaper.getDailyAutoWallpaper().get());

        dailyPaperElement.getDailyHobbyLabel().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty());
        dailyPaperElement.getDailyHobbyLabel().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty().divide(10.0).multiply(1.0));
        dailyPaperElement.getDailyHobbyLabel().setText("推荐壁纸喜好描述");
        dailyPaperElement.getDailyHobbyLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getDailyHobbyLabel().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyHobbyScrollPane().prefWidthProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().widthProperty());
        dailyPaperElement.getDailyHobbyScrollPane().prefHeightProperty().bind(dailyPaperElement.getDailyWallpaperHobbyVBox().heightProperty().divide(10.0).multiply(9.0));
        dailyPaperElement.getDailyHobbyScrollPane().setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dailyPaperElement.getDailyHobbyScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        dailyPaperElement.getDailyHobbyTextArea().prefWidthProperty().bind(dailyPaperElement.getDailyHobbyScrollPane().widthProperty());
        dailyPaperElement.getDailyHobbyTextArea().prefHeightProperty().bind(dailyPaperElement.getDailyHobbyScrollPane().heightProperty());
        dailyPaperElement.getDailyHobbyTextArea().setWrapText(true);
        dailyPaperElement.getDailyHobbyTextArea().setText(dailyPaper.getDailyImageHobby().get());
        dailyPaperElement.getDailyHobbyTextArea().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
    }

    @Override
    public void createParamStyle() {
        dailyPaperElement.getParamVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getParamVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getParamVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getParamInformationLabel().setText("> 作者项目有点多，缓慢更新中，请见谅！\n> 另外你可以在更新模块中实时查看新版本的更新情况。\n> 如有需要请参考参数文件：" + ParamConfig.getInstance().getLocalDB().getFullFilePath());
        dailyPaperElement.getParamInformationLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
    }

    @Override
    public void createSettingStyle() {
        dailyPaperElement.getSettingVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getSettingVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getSettingVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getSettingInformationLabel().setText("> 作者项目有点多，缓慢更新中，请见谅！\n> 另外你可以在更新模块中实时查看新版本的更新情况。\n> 如有需要请参考设置文件：" + SettingConfig.getInstance().getLocalDB().getFullFilePath());
        dailyPaperElement.getSettingInformationLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
    }

    @Override
    public void createRenewStyle() {
        dailyPaperElement.getRenewVBox().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().widthProperty());
        dailyPaperElement.getRenewVBox().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterStageBarVBox().heightProperty());
        dailyPaperElement.getRenewVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualRenewVBox().prefWidthProperty().bind(dailyPaperElement.getRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualRenewVBox().prefHeightProperty().bind(dailyPaperElement.getRenewVBox().heightProperty().divide(6.0).multiply(4.0));
        dailyPaperElement.getRenewManualRenewVBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewManualRenewVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualCheckRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualCheckRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualRenewVBox().heightProperty().divide(4.0).multiply(1.0));
        dailyPaperElement.getRenewManualCheckRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewManualCheckRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualCheckRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewManualCheckRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewManualCheckRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewManualCheckRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualCheckRenewButton().setText("检查更新");
        dailyPaperElement.getRenewManualCheckRenewButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewManualCheckRenewButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewManualShowRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualRenewVBox().widthProperty());
        dailyPaperElement.getRenewManualShowRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualRenewVBox().heightProperty().divide(4.0).multiply(3.0));
        dailyPaperElement.getRenewManualShowRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewManualShowRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewManualShowRenewHBox().setVisible(false);

        dailyPaperElement.getRenewManualShowRenewScrollPane().maxWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewManualShowRenewScrollPane().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualShowRenewScrollPane().setVisible(false);

        dailyPaperElement.getRenewManualShowRenewTextArea().maxWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewScrollPane().widthProperty());
        dailyPaperElement.getRenewManualShowRenewTextArea().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewScrollPane().heightProperty());
        dailyPaperElement.getRenewManualShowRenewTextArea().setEditable(false);
        dailyPaperElement.getRenewManualShowRenewTextArea().setWrapText(true);
        dailyPaperElement.getRenewManualShowRenewTextArea().clear();
        dailyPaperElement.getRenewManualShowRenewTextArea().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));

        dailyPaperElement.getRenewManualDownloadRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().widthProperty());
        dailyPaperElement.getRenewManualDownloadRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewManualShowRenewHBox().heightProperty().divide(4.0).multiply(1.0));
        dailyPaperElement.getRenewManualDownloadRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewManualDownloadRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewManualDownloadRenewHBox().setVisible(false);

        dailyPaperElement.getRenewManualDownloadRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewManualDownloadRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewManualDownloadRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewManualDownloadRenewHBox().heightProperty());
        dailyPaperElement.getRenewManualDownloadRenewButton().setText("立即更新");
        dailyPaperElement.getRenewManualDownloadRenewButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewManualDownloadRenewButton().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewVBox().widthProperty());
        dailyPaperElement.getRenewAutoRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewVBox().heightProperty().divide(6.0).multiply(1.0));
        dailyPaperElement.getRenewAutoRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());

        dailyPaperElement.getRenewAutoCheckRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewAutoCheckRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().heightProperty());
        dailyPaperElement.getRenewAutoCheckRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewAutoCheckRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoCheckRenewLabel().setText("自动检测更新");
        dailyPaperElement.getRenewAutoCheckRenewLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoCheckRenewLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setText("开");
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoCheckRenewOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setText("关");
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoCheckRenewOffButton().setAlignment(Pos.CENTER);
        if (dailyPaper.getRenewAutoCheckRenew().get()) {
            dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewAutoCheckRenewOffButton().setSelected(true);
        }

        dailyPaperElement.getRenewAutoDownloadRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().widthProperty().divide(5.0).multiply(1.0));
        dailyPaperElement.getRenewAutoDownloadRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewAutoRenewHBox().heightProperty());
        dailyPaperElement.getRenewAutoDownloadRenewHBox().setSpacing(dailyPaperData.getSpacingSizeSmall().get());
        dailyPaperElement.getRenewAutoDownloadRenewHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getRenewAutoDownloadRenewLabel().setText("自动下载软件");
        dailyPaperElement.getRenewAutoDownloadRenewLabel().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoDownloadRenewLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setText("开");
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoDownloadRenewOnButton().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setText("关");
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
        dailyPaperElement.getRenewAutoDownloadRenewOffButton().setAlignment(Pos.CENTER);
        if (dailyPaper.getRenewAutoDownloadRenew().get()) {
            dailyPaperElement.getRenewAutoDownloadRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewAutoDownloadRenewOffButton().setSelected(true);
        }
    }

}