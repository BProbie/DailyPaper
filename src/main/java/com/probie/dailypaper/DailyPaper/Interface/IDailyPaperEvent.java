package com.probie.dailypaper.DailyPaper.Interface;

public interface IDailyPaperEvent {

    /**
     * 创建事件
     * */
    void createEvent();

    /**
     * 创建 stage 事件
     * */
    void createStageEvent();

    /**
     * 创建 scene 事件
     * */
    void createSceneEvent();

    /**
     * 创建 root 事件
     * */
    void createRootEvent();

    /**
     * 创建 功能模块 分页事件
     * */
    void createChatEvent();
    void createLiveEvent();
    void createDailyEvent();
    void createParamEvent();
    void createSettingEvent();
    void createRenewEvent();

}