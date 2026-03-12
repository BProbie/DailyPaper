package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.application.Application;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperApplication;

@Data
public class DailyPaperApplication extends Application implements IDailyPaperApplication {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperApplication INSTANCE;

    /**
     * 启动主窗口
     * */
    @Override
    public void start(Stage stage) {
        /// 启动程序
        DailyPaperElement.getInstance().createElement(stage);
        DailyPaperStyle.getInstance().createStyle();
        DailyPaperEvent.getInstance().createEvent();
        stage.show();

        /// 播放动图
        if (DailyPaper.getInstance().IsLaunchLiveWallpaper.get()) {
            DailyPaperEvent.getInstance().launchLiveImageWallpaper();
        }

        /// 自动更新
        DailyPaper.getInstance().getDailyPaperPool().submit(() -> {
            if (DailyPaper.getInstance().getAutoCheckRenew().get()) {
                if (DailyPaperEvent.getInstance().checkRenewDailyPaper()) {
                    if (DailyPaper.getInstance().getAutoDownloadRenew().get()) {
                        DailyPaperEvent.getInstance().downloadRenewDailyPaper();
                    }
                }
            }
        });
    }

    @Override
    public void stop() {
        DailyPaperElement.getInstance().getAgentConnectionPool().shutdownNow();
        DailyPaperElement.getInstance().getLiveImageShowingPool().shutdownNow();
        DailyPaperElement.getInstance().getLiveImageWallpaperPool().shutdownNow();
        DailyPaper.getInstance().getDailyPaperPool().shutdownNow();
        DailyPaperElement.getInstance().getStage().close();
        DailyPaper.getInstance().close();
        Platform.exit();
        System.gc();
        try {
            super.stop();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperApplication getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperApplication();
        }
        return INSTANCE;
    }

}