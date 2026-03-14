package com.probie.dailypaper.DailyPaper.Interface;

public interface IDailyPaperStyle {

    /**
     * 创建样式
     * */
    void createStyle();

    /**
     * 创建 stage 样式
     * */
    void createStageStyle();

    /**
     * 创建 scene 样式
     * */
    void createSceneStyle();

    /**
     * 创建 root 样式
     * */
    void createRootStyle();

    /**
     * 创建 功能模块 样式
     * */
    void createChatStyle();
    void createLiveStyle();
    void createDailyStyle();
    void createParamStyle();
    void createSettingStyle();
    void createRenewStyle();

}