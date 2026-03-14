package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import java.io.File;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperApplication;

@Data
public class DailyPaperApplication extends Application implements IDailyPaperApplication {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperApplication INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperApplication();
        }
        return INSTANCE;
    }

    /**
     * 工具类单例对象
     * */
    private volatile static DailyPaper dailyPaper = DailyPaper.getInstance();
    private volatile static DailyPaperApplication dailyPaperApplication = DailyPaperApplication.getINSTANCE();
    private volatile static DailyPaperElement dailyPaperElement = DailyPaperElement.getInstance();
    private volatile static DailyPaperStyle dailyPaperStyle = DailyPaperStyle.getInstance();
    private volatile static DailyPaperEvent dailyPaperEvent = DailyPaperEvent.getInstance();
    private volatile static DailyPaperData dailyPaperData = DailyPaperData.getInstance();
    private volatile static DailyPaperFunction dailyPaperFunction = DailyPaperFunction.getInstance();

    /**
     * 启动主窗口
     * */
    @Override
    public void start(Stage stage) {
        beforeStart();

        dailyPaperElement.createElement(stage);
        dailyPaperStyle.createStyle();
        dailyPaperEvent.createEvent();
        stage.show();

        afterStart();
    }

    @Override
    public void stop() {
        beforeStop();

        try {
            super.stop();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        afterStop();
    }

    @Override
    public void beforeStart() {
        if (!new File(dailyPaper.getDailyPaperFilePath().get()).exists()) new File(dailyPaper.getDailyPaperFilePath().get()).mkdirs();
        if (!new File(dailyPaper.getConfigFilePath().get()).exists()) new File(dailyPaper.getConfigFilePath().get()).mkdirs();
        if (!new File(dailyPaper.getTempFilePath().get()).exists()) new File(dailyPaper.getTempFilePath().get()).mkdirs();
        if (!new File(dailyPaper.getJavaFilePath().get()).exists()) new File(dailyPaper.getJavaFilePath().get()).mkdirs();
        if (!new File(dailyPaper.getLibFilePath().get()).exists()) new File(dailyPaper.getLibFilePath().get()).mkdirs();
        if (!new File(dailyPaper.getLiveImageFilePath().get()).exists()) new File(dailyPaper.getLiveImageFilePath().get()).mkdirs();
    }

    @Override
    public void afterStart() {
        /// 启动自动推荐壁纸
        if (dailyPaper.getDailyAutoWallpaper().get() && dailyPaper.getDailyAutoWallpaperWhenLaunch().get()) {
            dailyPaperFunction.launchDailyWallpaper();
        }

        /// 时间自动推荐壁纸
        if (dailyPaper.getDailyAutoWallpaper().get() && dailyPaper.getDailyAutoWallpaperWhenTime().get() >= 1) {
            if (!dailyPaperData.getIsAutoDailyWallpaperRunning().get()) {
                dailyPaperData.setAutoDailyWallpaperStartTime(System.currentTimeMillis());
                dailyPaper.getScheduledExecutorService().scheduleAtFixedRate(dailyPaperData.getAutoDailyWallpaper(), 1 , 1, TimeUnit.MINUTES);
                dailyPaperData.setIsAutoDailyWallpaperRunning(() -> true);
            }
        }

        /// 播放动图
        if (dailyPaper.getLiveImageAutoLaunch().get()) {
            dailyPaperFunction.launchLiveImageWallpaper();
        }

        /// 自动更新
        dailyPaper.getDailyPaperPool().submit(() -> {
            if (dailyPaper.getRenewAutoCheckRenew().get()) {
                if (dailyPaperFunction.checkRenewDailyPaper()) {
                    if (dailyPaper.getRenewAutoDownloadRenew().get()) {
                        dailyPaperFunction.downloadRenewDailyPaper();
                    }
                }
            }
        });
    }

    @Override
    public void beforeStop() {

    }

    @Override
    public void afterStop() {
        dailyPaper.getScheduledExecutorService().shutdownNow();
        dailyPaper.getDailyPaperPool().shutdownNow();
        dailyPaperElement.getStage().close();
        dailyPaper.close();
        Platform.exit();
        System.gc();
    }

}