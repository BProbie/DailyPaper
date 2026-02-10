package com.probie.dailypaper.Config;

import java.io.File;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ILiveImageConfig;

public class LiveImageConfig extends Config implements ILiveImageConfig {

    /**
     * 构造函数
     * */
    public LiveImageConfig() {

    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static LiveImageConfig INSTANCE;

    @Override
    protected void init() {
        getLocalDB().setFullFilePath(DailyPaper.getInstance().getLiveImageConfigFilePath().get()+File.separator+DailyPaper.getInstance().getLiveImageConfigFileName().get());
        getLocalDB().connect();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static LiveImageConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LiveImageConfig();
            INSTANCE.init();
        }
        return INSTANCE;
    }
}