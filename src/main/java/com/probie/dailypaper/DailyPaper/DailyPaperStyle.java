package com.probie.dailypaper.DailyPaper;

import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.scene.layout.Region;
import javafx.application.Platform;
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
        dailyPaperElement.getRootPaneMenuBarChatButton().setText("Chat");
        dailyPaperElement.getRootPaneMenuBarChatButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarChatButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());
        dailyPaperElement.getRootPaneMenuBarLiveButton().setText("Live");
        dailyPaperElement.getRootPaneMenuBarLiveButton().setMinWidth(dailyPaperElement.getRootPaneMenuBarButtonWidth().get());
        dailyPaperElement.getRootPaneMenuBarLiveButton().setMinHeight(dailyPaperElement.getRootPaneMenuBarButtonHeight().get());
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