package com.probie.dailypaper.Config;

import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.IRenewConfig;

public class RenewConfig extends Config implements IRenewConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static RenewConfig INSTANCE;

    @Override
    protected void init() {
        getLocalDB().setFullFilePath(DailyPaper.getInstance().getRenewConfigFilePath()+"\\"+DailyPaper.getInstance().getRenewConfigFileName());
        getLocalDB().connect();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static RenewConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RenewConfig();
        }
        return INSTANCE;
    }

}