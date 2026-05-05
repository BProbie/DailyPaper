package com.probie.dailypaper.config;

import lombok.Data;
import java.io.File;
import com.probie.easydb.easydb.EasyDB;
import com.probie.easydb.database.local.LocalDB;
import com.probie.dailypaper.dailypaper.DailyPaper;
import com.probie.dailypaper.config.api.IParamConfig;

@Data
public class ParamConfig extends Config implements IParamConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ParamConfig INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static ParamConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ParamConfig();
        }

//        创建文件夹
//        if (!new File(String.valueOf(DailyPaper.getInstance().getConfigFilePath().get())).exists()) {
//            new File(String.valueOf(DailyPaper.getInstance().getConfigFilePath().get())).mkdirs();
//        }

        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(DailyPaper.getInstance().getConfigFilePath().get() + File.separator + DailyPaper.getInstance().getParamConfigFileName().get());
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