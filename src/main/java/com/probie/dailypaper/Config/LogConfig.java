package com.probie.dailypaper.Config;

import lombok.Data;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Supplier;
import com.probie.easydb.EasyDB.EasyDB;
import java.time.format.DateTimeFormatter;
import com.probie.easydb.Database.Local.LocalDB;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.Config.Interface.ILogConfig;

@Data
public class LogConfig extends Config implements ILogConfig {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static LogConfig INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static LogConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogConfig();
        }

        if (!new File(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get()).exists()) {
            new File(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get()).mkdirs();
        }

        if (INSTANCE.getLog() == null) {
            INSTANCE.setLog(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLog().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + DailyPaper.getInstance().getLogConfigFileName().get());
            INSTANCE.getLog().setIsAutoCommit(false);
            INSTANCE.getLog().connect();
        }
        if (INSTANCE.getDebug() == null) {
            INSTANCE.setDebug(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getDebug().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogDebugConfigFileName().get());
            INSTANCE.getDebug().setIsAutoCommit(false);
            INSTANCE.getDebug().connect();
        }
        if (INSTANCE.getInfo() == null) {
            INSTANCE.setInfo(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getInfo().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogInfoConfigFileName().get());
            INSTANCE.getInfo().setIsAutoCommit(false);
            INSTANCE.getInfo().connect();
        }
        if (INSTANCE.getWarn() == null) {
            INSTANCE.setWarn(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getWarn().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogWarnConfigFileName().get());
            INSTANCE.getWarn().setIsAutoCommit(false);
            INSTANCE.getWarn().connect();
        }
        if (INSTANCE.getError() == null) {
            INSTANCE.setError(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getError().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogErrorConfigFileName().get());
            INSTANCE.getError().setIsAutoCommit(false);
            INSTANCE.getError().connect();
        }
        return INSTANCE;
    }

    /**
     * EasyDB 实例对象
     * */
    private volatile LocalDB log;
    private volatile LocalDB debug;
    private volatile LocalDB info;
    private volatile LocalDB warn;
    private volatile LocalDB error;

    private volatile Supplier<Object> currentDate = () -> LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private volatile Supplier<Object> currentTime = () -> LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"));

    @Override
    public void debug(String debugMessage) {
        String time = "[Debug]" + "[" + currentTime.get() + "]";
        String message = time + debugMessage;

        System.out.println(message);

        boolean flag = false;
        int index = 0;

        while (true) {
            String key = time + "-" + index;
            if (getDebug().get(key, null) == null) {
                getDebug().set(key, debugMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        getDebug().commit();
    }

    @Override
    public void info(String infoMessage) {
        String time = "[Info]" + "[" + currentTime.get() + "]";
        String message = time + infoMessage;

        System.out.println(message);

        boolean flag = false;
        int index = 0;

        while (true) {
            String key = time + "-" + index;
            if (getInfo().get(key, null) == null) {
                getInfo().set(key, infoMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        getInfo().commit();
    }

    @Override
    public void warn(String warnMessage) {
        String time = "[Warn]" + "[" + currentTime.get() + "]";
        String message = time + warnMessage;

        System.out.println(message);

        boolean flag = false;
        int index = 0;

        while (true) {
            String key = time + "-" + index;
            if (getWarn().get(key, null) == null) {
                getWarn().set(key, warnMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        getWarn().commit();
    }

    @Override
    public void error(String errorMessage) {
        String time = "[Error]" + "[" + currentTime.get() + "]";
        String message = time + errorMessage;

        System.out.println(message);

        boolean flag = false;
        int index = 0;

        while (true) {
            String key = time + "-" + index;
            if (getError().get(key, null) == null) {
                getError().set(key, errorMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        getError().commit();
    }

}