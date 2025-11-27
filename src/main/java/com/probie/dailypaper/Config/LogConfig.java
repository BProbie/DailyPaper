package com.probie.dailypaper.Config;

import java.io.File;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ILogConfig;

public class LogConfig extends Config implements ILogConfig {

    /**
     * 构造函数
     * */
    public LogConfig() {
        init();
    }

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static LogConfig INSTANCE;

    @Override
    protected void init() {
        getLocalDB().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath()+File.separator+DailyPaper.getInstance().getLogConfigFileName());
        getLocalDB().connect();
    }

    @Override
    public void info(Object info) {
        getLocalDB().set(DailyPaper.getInstance().getComputerSystem().getCurrentFormatDate()+"Info", info);
    }

    @Override
    public void info(String info, Object... format) {
        info(new StringBuilder().append(String.format(info, format)));
    }

    @Override
    public void warn(Object warn) {
        getLocalDB().set(DailyPaper.getInstance().getComputerSystem().getCurrentFormatDate()+"Warn", warn);
    }

    @Override
    public void warn(String warn, Object... format) {
        warn(new StringBuilder().append(String.format(warn, format)));
    }

    @Override
    public void error(Object error) {
        getLocalDB().set(DailyPaper.getInstance().getComputerSystem().getCurrentFormatDate()+"Error", error);
    }

    @Override
    public void error(String error, Object... format) {
        error(new StringBuilder().append(String.format(error, format)));
    }

    @Override
    public void crush(Object crush) {
        getLocalDB().set(DailyPaper.getInstance().getComputerSystem().getCurrentFormatDate()+"Crush", crush);
    }

    @Override
    public void crush(String crush, Object... format) {
        crush(new StringBuilder().append(String.format(crush, format)));
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static LogConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogConfig();
        }
        return INSTANCE;
    }

}