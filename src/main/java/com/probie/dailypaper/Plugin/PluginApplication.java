package com.probie.dailypaper.Plugin;

import javafx.stage.Stage;
import com.probie.dailypaper.Plugin.Interface.IPluginApplication;

public class PluginApplication implements IPluginApplication {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginApplication INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginApplication();
        }
        return INSTANCE;
    }

    @Override
    public void launch() {
        start(PluginElement.getInstance().getStage() == null ? new Stage() : PluginElement.getInstance().getStage());
    }

    @Override
    public void start(Stage stage) {
        PluginElement.getInstance().createElement(stage);
        PluginStyle.getInstance().createStyle();
        PluginEvent.getInstance().createEvent();
//        显示舞台
//        stage.show();
    }

}