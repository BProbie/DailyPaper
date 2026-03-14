package com.probie.dailypaper.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ILiveImageConfig;

@Data
public class LiveImageConfig implements ILiveImageConfig {

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