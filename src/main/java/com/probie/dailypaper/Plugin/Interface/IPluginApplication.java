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

}