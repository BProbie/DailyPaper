package com.probie.dailypaper.DailyPaper.Interface;

import javafx.stage.Stage;

public interface IDailyPaperElement {

    /**
     * 创建元素
     * @param stage 舞台实例化对象
     * */
    void createElement(Stage stage);

    /**
     * 创建 Stage 舞台元素
     * */
    void createStageElement();

    /**
     * 创建 Scene 帷幕元素
     * */
    void createSceneElement();

    /**
     * 创建 RootPane 分页元素
     * */
    void createRootPaneElement();

    /**
     * 创建 ChatPane 分页元素
     * */
    void createChatPaneElement();

    /**
     * 创建 LivePane 分页元素
     * */
    void createLivePaneElement();

}