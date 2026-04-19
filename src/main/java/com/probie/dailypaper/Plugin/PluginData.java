package com.probie.dailypaper.Plugin;

import com.probie.dailypaper.Plugin.Interface.IPluginData;

public class PluginData implements IPluginData {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginData INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginData();
        }
        return INSTANCE;
    }

}