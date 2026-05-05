package com.probie.dailypaper.config;

import lombok.Data;
import com.probie.dailypaper.config.api.IConfig;

@Data
public class Config implements IConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static Config INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    private synchronized static Config getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Config();
        }
        return INSTANCE;
    }

}