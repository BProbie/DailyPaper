package com.probie.dailypaper.config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.easydb.EasyDB;
import com.probie.easydb.database.local.LocalDB;
import com.probie.dailypaper.dailypaper.DailyPaper;
import com.probie.dailypaper.config.api.ILiveImageConfig;

@Data
public class LiveImageConfig extends Config implements ILiveImageConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static LiveImageConfig INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static LiveImageConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LiveImageConfig();
        }

        if (!new File(String.valueOf(DailyPaper.getInstance().getLiveImageFilePath())).exists()) {
            new File(String.valueOf(DailyPaper.getInstance().getLiveImageFilePath())).mkdirs();
        }

        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getLiveImageConfigFileName().get());
            INSTANCE.getLocalDB().setIsAutoCommit(false);
            INSTANCE.getLocalDB().connect();
        }

        return INSTANCE;
    }

    /**
     * EasyDB 实例对象
     * */
    private volatile LocalDB localDB;

}