package com.probie.dailypaper.Config;

import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ITempConfig;

public class TempConfig extends Config implements ITempConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static TempConfig INSTANCE;

    @Override
    protected void init() {
        getLocalDB().setFullFilePath(DailyPaper.getInstance().getTempConfigFilePath()+"\\"+DailyPaper.getInstance().getTempConfigFileName());
        getLocalDB().connect();
    }

    /**
     * 获取懒加载的类单例对象a
     * */
    public synchronized static TempConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TempConfig();
            INSTANCE.init();
        }
        return INSTANCE;
    }

}