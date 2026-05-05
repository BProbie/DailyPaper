package com.probie.dailypaper.plugin;

import com.probie.dailypaper.plugin.api.IPluginFunction;

public class PluginFunction implements IPluginFunction {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginFunction INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginFunction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginFunction();
        }
        return INSTANCE;
    }

}