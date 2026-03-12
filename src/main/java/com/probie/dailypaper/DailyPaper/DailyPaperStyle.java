package com.probie.dailypaper.DailyPaper;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.layout.Region;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperStyle;

public class DailyPaperStyle implements IDailyPaperStyle {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperStyle INSTANCE;

    /**
     * 懒加载的工具类单例对象
     * */
    private DailyPaper dailyPaper;
    private DailyPaperElement dailyPaperElement;

    @Override
    public void createStyle() {

        /// 初始化工具类单例对象
        if (dailyPaper == null) {
            dailyPaper = DailyPaper.getInstance();
        }
        if (dailyPaperElement == null) {
            dailyPaperElement = DailyPaperElement.getInstance();
        }

        /// 创建 Stage 舞台样式
        createStageStyle();

        /// 创建 Scene 帷幕样式
        createSceneStyle();

        /// 创建 RootPane 分页样式
        createRootPaneStyle();

        /// 创建 ChatPane 分页样式
        createChatPaneStyle();

        /// 创建 LivePane 分页样式
        createLivePaneStyle();

        /// 创建 DailyPane 分页样式
        createDailyPaneStyle();

        /// 创建 HobbyPane 分页样式
        createHobbyPaneStyle();

        /// 创建 SettingPane 分页样式
        createSettingPaneStyle();

        /// 创建 RenewPane 分页样式
        createRenewPaneStyle();

    }

    @Override
    public void createStageStyle() {
        dailyPaperElement.getStage().initStyle(StageStyle.UNDECORATED);
        dailyPaperElement.getStage().setWidth(dailyPaper.getDailyPaperStageWidth().get());
        dailyPaperElement.getStage().setHeight(dailyPaper.getDailyPaperStageHeight().get());
    }

    @Override
    public void createSceneStyle() {

    }

    @Override
    public void createRootPaneStyle() {
        /// 创建 RootPane 标题样式
        dailyPaperElement.getRootPaneTitleBar().setBackground(Background.fill(Color.WHITESMOKE));
        dailyPaperElement.getRootPaneTileBarMinButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneTitleBarMaxButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneTitleBarCloseButton().setBackground(Background.fill(Color.LIGHTGRAY));

        dailyPaperElement.getRootPaneTitleBar().setSpacing(dailyPaperElement.getOffset().get() / 10.0);

        dailyPaperElement.getRootPaneTileBarMinButton().setText("一");
        dailyPaperElement.getRootPaneTitleBarMaxButton().setText(dailyPaperElement.getStage().isMaximized() ? "⊟" : "□");
        dailyPaperElement.getRootPaneTitleBarCloseButton().setText("×");

        dailyPaperElement.getRootPaneTileBarMinButton().setMinWidth(dailyPaperElement.getRootPaneTitleBarButtonSize().get());
        dailyPaperElement.getRootPaneTileBarMinButton().setMinHeight(dailyPaperElement.getRootPaneTitleBarButtonSize().get());
        dailyPaperElement.getRootPaneTitleBarMaxButton().setMinWidth(dailyPaperElement.getRootPaneTitleBarButtonSize().get());
        dailyPaperElement.getRootPaneTitleBarMaxButton().setMinHeight(dailyPaperElement.getRootPaneTitleBarButtonSize().get());
        dailyPaperElement.getRootPaneTitleBarCloseButton().setMinWidth(dailyPaperElement.getRootPaneTitleBarButtonSize().get());
        dailyPaperElement.getRootPaneTitleBarCloseButton().setMinHeight(dailyPaperElement.getRootPaneTitleBarButtonSize().get());

        /// 创建 RootPane 菜单样式
        dailyPaperElement.getRootPaneMenuBar().setBackground(Background.fill(Color.WHITESMOKE));
        dailyPaperElement.getRootPaneMenuBarChatButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneMenuBarLiveButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneMenuBarDailyButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneMenuBarHobbyButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneMenuBarSettingButton().setBackground(Background.fill(Color.LIGHTGRAY));
        dailyPaperElement.getRootPaneMenuBarRenewButton().setBackground(Background.fill(Color.LIGHTGRAY));

        dailyPaperElement.getRootPaneMenuBar().setSpacing(dailyPaperElement.getOffset().get() / 10.0);

        dailyPaperElement.getRootPaneMenuBarChatButton().setText("Chat");
        dailyPaperElement.getRootPaneMenuBarChatButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarChatButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());

        dailyPaperElement.getRootPaneMenuBarLiveButton().setText("Live");
        dailyPaperElement.getRootPaneMenuBarLiveButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarLiveButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());

        dailyPaperElement.getRootPaneMenuBarDailyButton().setText("Daily");
        dailyPaperElement.getRootPaneMenuBarDailyButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarDailyButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());

        dailyPaperElement.getRootPaneMenuBarHobbyButton().setText("Hobby");
        dailyPaperElement.getRootPaneMenuBarHobbyButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarHobbyButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());

        dailyPaperElement.getRootPaneMenuBarSettingButton().setText("Setting");
        dailyPaperElement.getRootPaneMenuBarSettingButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarSettingButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());

        dailyPaperElement.getRootPaneMenuBarRenewButton().setText("Renew");
        dailyPaperElement.getRootPaneMenuBarRenewButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarRenewButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());
    }

    @Override
    public void createChatPaneStyle() {
        /// 创建 ChatPane 控件样式
        dailyPaperElement.getChatPane().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterPane().widthProperty());
        dailyPaperElement.getChatPane().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterPane().heightProperty());

        Platform.runLater(() -> {
            dailyPaperElement.getChatPaneTextShowArea().prefHeightProperty().bind(dailyPaperElement.getChatPane().heightProperty().multiply(4.0 / 5.0));

            dailyPaperElement.getChatPaneTextInputArea().prefHeightProperty().bind(dailyPaperElement.getChatPane().heightProperty().multiply(1.0 / 5.0));
            dailyPaperElement.getChatPaneTextInputArea().setFont(new Font(dailyPaperElement.getChatPaneTextInputAreaFontSize().get()));
            dailyPaperElement.getChatPaneTextInputArea().setPromptText("请输入提示词...");
            dailyPaperElement.getChatPaneTextInputArea().setWrapText(true);

            dailyPaperElement.getChatPaneMessageVBox().prefWidthProperty().bind(dailyPaperElement.getChatPaneTextShowArea().widthProperty().subtract(dailyPaperElement.getOffset().get() * 2.0));
            dailyPaperElement.getChatPaneMessageVBox().setSpacing(dailyPaperElement.getOffset().get() * 2.0);
            dailyPaperElement.getChatPaneMessageVBox().setMinHeight(Region.USE_COMPUTED_SIZE);
        });
    }

    @Override
    public void createLivePaneStyle() {
        dailyPaperElement.getLivePane().prefWidthProperty().bind(dailyPaperElement.getRootPaneCenterPane().widthProperty());
        dailyPaperElement.getLivePane().prefHeightProperty().bind(dailyPaperElement.getRootPaneCenterPane().heightProperty());

        dailyPaperElement.getLivePaneImageInputHBox().prefWidthProperty().bind(dailyPaperElement.getLivePane().widthProperty());
        dailyPaperElement.getLivePaneImageInputHBox().prefHeightProperty().bind(dailyPaperElement.getLivePane().heightProperty().divide(6.0));

        dailyPaperElement.getLivePaneImageChooseButton().setText("选择动态在内的任意壁纸");
        dailyPaperElement.getLivePaneImageChooseButton().prefWidthProperty().bind(dailyPaperElement.getLivePaneImageInputHBox().widthProperty().divide(6.0));
        dailyPaperElement.getLivePaneImageChooseButton().prefHeightProperty().bind(dailyPaperElement.getLivePaneImageInputHBox().heightProperty());

        dailyPaperElement.getLivePaneImageChooseLabel().setText(dailyPaper.getComputerSystem().getHere());
        dailyPaperElement.getLivePaneImageChooseLabel().prefWidthProperty().bind(dailyPaperElement.getLivePaneImageInputHBox().widthProperty().divide(6.0).multiply(5.0));
        dailyPaperElement.getLivePaneImageChooseLabel().prefHeightProperty().bind(dailyPaperElement.getLivePaneImageInputHBox().heightProperty());
        dailyPaperElement.getLivePaneImageChooseLabel().setFont(new Font(dailyPaperElement.getLivePaneImageInputFontSize().get()));
        dailyPaperElement.getLivePaneImageChooseLabel().setAlignment(Pos.CENTER);
        dailyPaperElement.getLivePaneImageChooseLabel().setWrapText(true);

        dailyPaperElement.getLivePaneImageFileChooser().setInitialDirectory(new File(dailyPaper.getComputerSystem().getHere()));
        dailyPaperElement.getLivePaneImageFileChooser().getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL", "*.*"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
        );

        dailyPaperElement.getLivePaneImageShowHBox().maxWidthProperty().bind(dailyPaperElement.getLivePane().widthProperty());
        dailyPaperElement.getLivePaneImageShowHBox().maxHeightProperty().bind(dailyPaperElement.getLivePane().heightProperty().divide(6).multiply(4));

        dailyPaperElement.getLivePaneImageSureHBox().prefWidthProperty().bind(dailyPaperElement.getLivePane().widthProperty());
        dailyPaperElement.getLivePaneImageSureHBox().prefHeightProperty().bind(dailyPaperElement.getLivePane().heightProperty().divide(6));
    }

    @Override
    public void createDailyPaneStyle() {
        dailyPaperElement.getDailyPaneVBox().prefWidthProperty().bind(dailyPaperElement.getDailyPane().widthProperty());
        dailyPaperElement.getDailyPaneVBox().prefHeightProperty().bind(dailyPaperElement.getDailyPane().heightProperty());
        dailyPaperElement.getDailyPaneVBox().setSpacing(dailyPaperElement.getOffset().get());
        dailyPaperElement.getDailyPaneVBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyPaneLaunchHBox().prefWidthProperty().bind(dailyPaperElement.getDailyPaneVBox().widthProperty());
        dailyPaperElement.getDailyPaneLaunchHBox().prefHeightProperty().bind(dailyPaperElement.getDailyPaneVBox().heightProperty().divide(7));
        dailyPaperElement.getDailyPaneLaunchHBox().setSpacing(dailyPaperElement.getOffset().get() * 2);
        dailyPaperElement.getDailyPaneLaunchHBox().setAlignment(Pos.CENTER);

        dailyPaperElement.getDailyPaneAutoSetWallpaperHBox().setSpacing(dailyPaperElement.getOffset().get());
        dailyPaperElement.getDailyPaneAutoSetWallpaperLabel().setText("推荐壁纸");
        dailyPaperElement.getDailyPaneAutoSetWallpaperLabel().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        dailyPaperElement.getDailyPaneAutoSetWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyPaneAutoSetWallpaperOnButton().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        dailyPaperElement.getDailyPaneAutoSetWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyPaneAutoSetWallpaperOffButton().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        if (dailyPaper.getAutoWallpaper().get()) {
            dailyPaperElement.getDailyPaneAutoSetWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyPaneAutoSetWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyPaneAutoLaunchWallpaperHBox().setSpacing(dailyPaperElement.getOffset().get());
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperLabel().setText("开机自启");
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperLabel().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperOnButton().setText("开");
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperOnButton().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperOffButton().setText("关");
        dailyPaperElement.getDailyPaneAutoLaunchWallpaperOffButton().setFont(new Font(dailyPaperElement.getDailyPaneFontSize().get()));
        if (dailyPaper.getAutoLaunch().get()) {
            dailyPaperElement.getDailyPaneAutoLaunchWallpaperOnButton().setSelected(true);
        } else {
            dailyPaperElement.getDailyPaneAutoLaunchWallpaperOffButton().setSelected(true);
        }

        dailyPaperElement.getDailyPaneHobbyVBox().prefWidthProperty().bind(dailyPaperElement.getDailyPaneVBox().widthProperty());
        dailyPaperElement.getDailyPaneHobbyVBox().prefHeightProperty().bind(dailyPaperElement.getDailyPaneVBox().heightProperty().divide(7).multiply(6));
    }

    @Override
    public void createHobbyPaneStyle() {

    }

    @Override
    public void createSettingPaneStyle() {

    }

    @Override
    public void createRenewPaneStyle() {
        dailyPaperElement.getRenewPaneVBox().prefWidthProperty().bind(dailyPaperElement.getRenewPane().widthProperty());
        dailyPaperElement.getRenewPaneVBox().prefHeightProperty().bind(dailyPaperElement.getRenewPane().heightProperty());
        dailyPaperElement.getRenewPaneVBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewPaneVBox().setSpacing(dailyPaperElement.getOffset().get() * 5);

        dailyPaperElement.getRenewPaneManualRenewVBox().prefWidthProperty().bind(dailyPaperElement.getRenewPaneVBox().widthProperty());
        dailyPaperElement.getRenewPaneManualRenewVBox().prefHeightProperty().bind(dailyPaperElement.getRenewPaneVBox().heightProperty().divide(6).multiply(3));
        dailyPaperElement.getRenewPaneManualRenewVBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewPaneManualRenewVBox().setSpacing(dailyPaperElement.getOffset().get());

        dailyPaperElement.getRenewPaneManualCheckRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6));
        dailyPaperElement.getRenewPaneManualCheckRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6));
        dailyPaperElement.getRenewPaneManualCheckRenewButton().setText("检查更新");
        dailyPaperElement.getRenewPaneManualCheckRenewButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));

        dailyPaperElement.getRenewPaneManualCheckRenewTextShowScrollPane().maxWidthProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6).multiply(4));
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowScrollPane().prefHeightProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6).multiply(4));
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowScrollPane().setContent(dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea());
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowScrollPane().setVisible(false);

        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().maxWidthProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6).multiply(4));
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().prefHeightProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6).multiply(4));
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().setWrapText(true);
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().clear();
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().setEditable(false);
        dailyPaperElement.getRenewPaneManualCheckRenewTextShowArea().setVisible(false);

        dailyPaperElement.getRenewPaneManualDownloadRenewButton().prefWidthProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6));
        dailyPaperElement.getRenewPaneManualDownloadRenewButton().prefHeightProperty().bind(dailyPaperElement.getRenewPaneManualRenewVBox().widthProperty().divide(6));
        dailyPaperElement.getRenewPaneManualDownloadRenewButton().setText("立即更新");
        dailyPaperElement.getRenewPaneManualDownloadRenewButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneManualDownloadRenewButton().setVisible(false);

        dailyPaperElement.getRenewPaneAutoRenewHBox().prefWidthProperty().bind(dailyPaperElement.getRenewPaneVBox().widthProperty());
        dailyPaperElement.getRenewPaneAutoRenewHBox().prefHeightProperty().bind(dailyPaperElement.getRenewPaneVBox().heightProperty().divide(6));
        dailyPaperElement.getRenewPaneAutoRenewHBox().setAlignment(Pos.CENTER);
        dailyPaperElement.getRenewPaneAutoRenewHBox().setSpacing(dailyPaperElement.getOffset().get() * 2);

        dailyPaperElement.getRenewPaneAutoCheckRenewHBox().setSpacing(dailyPaperElement.getOffset().get());
        dailyPaperElement.getRenewPaneAutoCheckRenewLabel().setText("自动检测更新");
        dailyPaperElement.getRenewPaneAutoCheckRenewLabel().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneAutoCheckRenewOnButton().setText("开");
        dailyPaperElement.getRenewPaneAutoCheckRenewOnButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneAutoCheckRenewOffButton().setText("关");
        dailyPaperElement.getRenewPaneAutoCheckRenewOffButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        if (dailyPaper.getAutoCheckRenew().get()) {
            dailyPaperElement.getRenewPaneAutoCheckRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewPaneAutoCheckRenewOffButton().setSelected(true);
        }

        dailyPaperElement.getRenewPaneAutoDownloadRenewHBox().setSpacing(dailyPaperElement.getOffset().get());
        dailyPaperElement.getRenewPaneAutoDownloadRenewLabel().setText("自动下载软件");
        dailyPaperElement.getRenewPaneAutoDownloadRenewLabel().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneAutoDownloadRenewOnButton().setText("开");
        dailyPaperElement.getRenewPaneAutoDownloadRenewOnButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        dailyPaperElement.getRenewPaneAutoDownloadRenewOffButton().setText("关");
        dailyPaperElement.getRenewPaneAutoDownloadRenewOffButton().setFont(new Font(dailyPaperElement.getRenewPaneFontSize().get()));
        if (dailyPaper.getAutoDownloadRenew().get()) {
            dailyPaperElement.getRenewPaneAutoDownloadRenewOnButton().setSelected(true);
        } else {
            dailyPaperElement.getRenewPaneAutoDownloadRenewOffButton().setSelected(true);
        }
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperStyle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperStyle();
        }
        return INSTANCE;
    }

}