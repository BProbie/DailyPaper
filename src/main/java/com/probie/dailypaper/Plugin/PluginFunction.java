package com.probie.dailypaper.Plugin;

import com.probie.dailypaper.Plugin.Interface.IPluginFunction;

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