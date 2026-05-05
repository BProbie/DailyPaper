package com.probie.dailypaper.plugin;

import com.probie.dailypaper.plugin.api.IPlugin;

public class Plugin implements IPlugin {

    /// 维护一个懒加载的类单例对象
    private volatile static Plugin INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static Plugin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Plugin();
        }
        return INSTANCE;
    }

    @Override
    public void launch() {
        PluginApplication.getInstance().launch();
    }

}