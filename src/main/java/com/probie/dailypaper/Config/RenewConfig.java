package com.probie.dailypaper.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.easydb.Database.Local.LocalRemoteDB;
import com.probie.dailypaper.Config.Interface.IRenewConfig;

@Data
public class RenewConfig extends Config implements IRenewConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static RenewConfig INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static RenewConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RenewConfig();
        }

//        if (!new File(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getRenewConfigFileName().get()).exists()) {
//            new File(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getRenewConfigFileName().get()).mkdirs();
//        }

        if (INSTANCE.getLocalRemoteDB() == null) {
            INSTANCE.setLocalRemoteDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalRemoteDB(String.valueOf(DailyPaper.getInstance().getRenewConfigRenewUri().get())));
            INSTANCE.getLocalRemoteDB().setFullFilePath(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getRenewConfigFileName().get());
            INSTANCE.getLocalRemoteDB().setIsAutoCommit(false);
            INSTANCE.getLocalRemoteDB().downloadDatabase(true);
            INSTANCE.getLocalRemoteDB().connect();
        }
        return INSTANCE;
    }

    /**
     * EasyDB 实例对象
     * */
    private volatile LocalRemoteDB localRemoteDB;

}