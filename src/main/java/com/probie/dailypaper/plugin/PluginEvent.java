package com.probie.dailypaper.plugin;

import com.probie.dailypaper.plugin.api.IPluginEvent;

public class PluginEvent implements IPluginEvent {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginEvent INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginEvent();
        }
        return INSTANCE;
    }

    @Override
    public void createEvent() {

    }

}