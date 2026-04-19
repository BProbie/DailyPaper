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

        File file = new File(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + INSTANCE.getCurrentDate().get());
        if (!file.exists()) {
            file.mkdirs();
        }

        if (INSTANCE.getLocalDB() == null) {
            INSTANCE.setLocalDB(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getLocalDB().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + DailyPaper.getInstance().getLogConfigFileName().get());
            INSTANCE.getLocalDB().setIsAutoCommit(false);
            INSTANCE.getLocalDB().connect();
        }
        if (INSTANCE.getDebug() == null) {
            INSTANCE.setDebug(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getDebug().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogDebugConfigFileName().get());
            INSTANCE.getDebug().setIsAutoCommit(false);
            INSTANCE.getDebug().connect();
        }
        if (INSTANCE.getInfo() == null) {
            INSTANCE.setInfo(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getInfo().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogInfoConfigFileName().get());
            INSTANCE.getInfo().setIsAutoCommit(false);
            INSTANCE.getInfo().connect();
        }
        if (INSTANCE.getWarn() == null) {
            INSTANCE.setWarn(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getWarn().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogWarnConfigFileName().get());
            INSTANCE.getWarn().setIsAutoCommit(false);
            INSTANCE.getWarn().connect();
        }
        if (INSTANCE.getError() == null) {
            INSTANCE.setError(EasyDB.getInstance().getLocalDatabaseFactory().buildLocalDB());
            INSTANCE.getError().setFullFilePath(DailyPaper.getInstance().getLogConfigFilePath().get() + File.separator + INSTANCE.getCurrentDate().get() + File.separator + DailyPaper.getInstance().getLogErrorConfigFileName().get());
            INSTANCE.getError().setIsAutoCommit(false);
            INSTANCE.getError().connect();
        }
        return INSTANCE;
    }

    /**
     * EasyDB 实例对象
     * */
    private volatile LocalDB localDB;
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
            if (INSTANCE.getDebug().get(key, null) == null) {
                INSTANCE.getDebug().set(key, debugMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        INSTANCE.getDebug().commit();
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
            if (INSTANCE.getInfo().get(key, null) == null) {
                INSTANCE.getInfo().set(key, infoMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        INSTANCE.getInfo().commit();
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
            if (INSTANCE.getWarn().get(key, null) == null) {
                INSTANCE.getWarn().set(key, warnMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        INSTANCE.getWarn().commit();
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
            if (INSTANCE.getError().get(key, null) == null) {
                INSTANCE.getError().set(key, errorMessage);
                flag = true;
            }
            if (flag) {
                break;
            }
            index++;
        }
        INSTANCE.getError().commit();
    }

}