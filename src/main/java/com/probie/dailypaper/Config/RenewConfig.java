package com.probie.dailypaper.Config;

import java.io.File;
import com.probie.easydb.EasyDB;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.easydb.Database.Local.LocalRemoteDB;
import com.probie.dailypaper.Config.Interface.IRenewConfig;

public class RenewConfig extends Config implements IRenewConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static RenewConfig INSTANCE;

    @Override
    protected void init() {
        LocalRemoteDB localRemoteDB = EasyDB.getInstance().getLocalDatabaseFactory().buildLocalRemoteDB(DailyPaper.getInstance().getRenewConfigFileUrl());
        localRemoteDB.setFullFilePath(DailyPaper.getInstance().getRenewConfigFilePath()+File.separator+DailyPaper.getInstance().getRenewConfigFileName());
        localRemoteDB.downloadDatabase();
        getLocalDB().setFullFilePath(localRemoteDB.getFullFilePath());
        getLocalDB().connect();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static RenewConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RenewConfig();
            INSTANCE.init();
        }
        return INSTANCE;
    }

}