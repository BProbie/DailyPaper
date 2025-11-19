package com.probie.dailypaper.Config.Interface;

import com.probie.dailypaper.Config.LogConfig;
import com.probie.dailypaper.Config.TempConfig;
import com.probie.dailypaper.Config.RenewConfig;
import com.probie.dailypaper.Config.ConfigConfig;

public interface IConfig {

    /**
     * 获取 LogConfig 代理单例
     * @return LogConfig 代理单例
     * */
    LogConfig getLogConfig();

    /**
     * 获取 TempConfig 代理单例
     * @return TempConfig 代理单例
     * */
    TempConfig getTempConfig();

    /**
     * 获取 ConfigConfig 代理单例
     * @return ConfigConfig 代理单例
     * */
    ConfigConfig getConfigConfig();

    /**
     * 获取 RenewConfig 代理单例
     * @return RenewConfig 代理单例
     * */
    RenewConfig getRenewConfig();

}