package com.probie.dailypaper.DailyPaper.Interface;

import javafx.stage.Stage;

public interface IDailyPaperElement {

    /**
     * 创建元素
     * @param stage 舞台实例化对象
     * */
    void createElement(Stage stage);

    /**
     * 创建 stage 元素
     * */
    void createStageElement();

    /**
     * 创建 scene 元素
     * */
    void createSceneElement();

    /**
     * 创建 root 元素
     * */
    void createRootElement();

    /**
     * 创建 功能模块 元素
     * */
    void createChatElement();
    void createLiveElement();
    void createDailyElement();
    void createParamElement();
    void createSettingElement();
    void createRenewElement();

}