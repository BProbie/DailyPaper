package com.probie.dailypaper.Config.Interface;

public interface ILogConfig {

    /**
     * 记录日志: 信息
     * @param info 普通信息
     * */
    void info(Object info);

    /**
     * 记录日志: 信息
     * @param info 普通信息
     * @param format 格式化填充内容
     * */
    void info(String info, Object... format);

    /**
     * 记录日志: 警告
     * @param warn 警告信息
     * */
    void warn(Object warn);

    /**
     * 记录日志: 警告
     * @param warn 警告信息
     * @param format 格式化填充内容
     * */
    void warn(String warn, Object... format);

    /**
     * 记录日志: 错误
     * @param error 错误信息
     * */
    void error(Object error);

    /**
     * 记录日志: 错误
     * @param error 错误信息
     * @param format 格式化填充内容
     * */
    void error(String error, Object... format);

    /**
     * 记录日志: 崩溃
     * @param crush 崩溃信息
     * */
    void crush(Object crush);

    /**
     * 记录日志: 崩溃
     * @param crush 崩溃信息
     * @param format 格式化填充内容
     * */
    void crush(String crush, Object... format);

}