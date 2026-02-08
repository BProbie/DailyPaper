package com.probie.dailypaper.DailyPaper.Interface;

import javafx.scene.layout.Pane;
import javafx.scene.control.ScrollPane;

public interface IDailyPaperEvent {

    /**
     * 创建事件
     * */
    void createEvent();

    /**
     * 创建 Stage 舞台事件
     * */
    void createStageEvent();

    /**
     * 创建 Scene 帷幕事件
     * */
    void createSceneEvent();

    /**
     * 创建 RootPane 分页事件
     * */
    void createRootPaneEvent();

    /**
     * 创建 ChatPane 分页事件
     * */
    void createChatPaneEvent();

    /**
     * 创建 LivePane 分页事件
     * */
    void createLivePaneEvent();

    /**
     * 清理 ChatPane 内容
     * */
    void clearChatPane();

    /**
     * 清理 LivePane 内容
     * */
    void clearLivePane();

    /**
     * 更改 CenterPane 的 Pane
     * @param pane 替换成的 Pane
     * */
    void changeCenterPane(Pane pane);

    /**
     * 将 ScrollPane 的滚轮滚到最下面
     * @param scrollPane ScrollPane 实例化对象
     * */
    void scrollToBottom(ScrollPane scrollPane);

}