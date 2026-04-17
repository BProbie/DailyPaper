package com.probie.dailypaper.Config.Interface;

public interface ILogConfig {

    /**
     * 日志调试记录
     * @param debugMessage 调试信息
     * */
    void debug(String debugMessage);

    /**
     * 日志提示记录
     * @param infoMessage 提示信息
     * */
    void info(String infoMessage);

    /**
     * 日志警告记录
     * @param warnMessage 警告信息
     * */
    void warn(String warnMessage);

    /**
     * 日志错误记录
     * @param errorMessage 错误信息
     * */
    void error(String errorMessage);

}