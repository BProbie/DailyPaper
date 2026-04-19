package com.probie.dailypaper.Plugin;

import com.probie.dailypaper.Plugin.Interface.IPluginEvent;

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