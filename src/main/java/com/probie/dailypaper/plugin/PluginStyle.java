package com.probie.dailypaper.plugin;

import com.probie.dailypaper.plugin.api.IPluginStyle;

public class PluginStyle implements IPluginStyle {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginStyle INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginStyle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginStyle();
        }
        return INSTANCE;
    }

    @Override
    public void createStyle() {

    }

}