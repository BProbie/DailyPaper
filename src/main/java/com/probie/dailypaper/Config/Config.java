package com.probie.dailypaper.Config;

import lombok.Data;
import com.probie.easydb.EasyDB.EasyDB;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.dailypaper.Config.Interface.IConfig;

@Data
public abstract class Config implements IConfig {

    /**
     * 构造函数
     * */
    public Config() {
        getLocalDB().setIsAutoCommit(false);
    }

    /**
     * 继承的子类必须重写初始化参数方法
     * */
    protected abstract void init();

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Config INSTANCE;

    /**
     * 本地数据库实例化对象
     * */
    private LocalDB localDB = EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB();

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config() {
                @Override
                protected void init() {
                    /// 空实现
                }
            };
        }
        return INSTANCE;
    }

    @Override
    public LogConfig getLogConfig() {
        return LogConfig.getInstance();
    }

    @Override
    public TempConfig getTempConfig() {
        return TempConfig.getInstance();
    }

    @Override
    public ConfigConfig getConfigConfig() {
        return ConfigConfig.getInstance();
    }

    @Override
    public RenewConfig getRenewConfig() {
        return RenewConfig.getInstance();
    }
}