package com.probie.dailypaper.Plugin.Interface;

import javafx.stage.Stage;

public interface IPluginApplication {

    /**
     * 启动程序
     * */
    void launch();

    /**
     * 启动程序
     * @param stage 程序舞台
     * */
    void start(Stage stage);

    /**
     * 结束程序
     * */
    void stop();

    /**
     * 启动程序前
     * */
    void beforeStart();

    /**
     * 启动程序后
     * */
    void afterStart();

    /**
     * 结束程序前
     * */
    void beforeStop();

    /**
     * 结束程序后
     * */
    void afterStop();

}