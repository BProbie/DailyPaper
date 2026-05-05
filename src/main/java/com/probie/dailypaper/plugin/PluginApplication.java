package com.probie.dailypaper.plugin;

import javafx.stage.Stage;
import com.probie.dailypaper.plugin.api.IPluginApplication;

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
        beforeStart();

        PluginElement.getInstance().createElement(stage);
        PluginStyle.getInstance().createStyle();
        PluginEvent.getInstance().createEvent();
        stage.show();

        afterStart();

        stage.setTitle("敬请期待");
    }

    @Override
    public void stop() {
        beforeStop();

        afterStop();
    }

    @Override
    public void beforeStart() {

    }

    @Override
    public void afterStart() {

    }

    @Override
    public void beforeStop() {

    }

    @Override
    public void afterStop() {

    }

}