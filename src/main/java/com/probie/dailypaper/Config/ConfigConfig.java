package com.probie.dailypaper.Config;

import java.io.File;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.IConfigConfig;

public class ConfigConfig extends Config implements IConfigConfig {

    /**
     * 构造函数
     * */
    public ConfigConfig() {
        init();
    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ConfigConfig INSTANCE;

    @Override
    protected void init() {
        getLocalDB().setFullFilePath(DailyPaper.getInstance().ConfigConfigFilePath+File.separator+DailyPaper.getInstance().getConfigConfigFileName());
        getLocalDB().connect();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static ConfigConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigConfig();
        }
        return INSTANCE;
    }

}