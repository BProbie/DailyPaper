package com.probie.dailypaper.Config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ISettingConfig;

@Data
public class SettingConfig extends Config implements ISettingConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static SettingConfig INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static SettingConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SettingConfig();
        }

//        if (!new File(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getSettingConfigFileName().get()).exists()) {
//            new File(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getSettingConfigFileName().get()).mkdirs();
//        }

        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getSettingConfigFileName().get());
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