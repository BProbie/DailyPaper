package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import java.io.File;
import javafx.stage.Stage;
import javafx.application.Platform;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import com.probie.dailypaper.Config.RenewConfig;
import com.probie.dailypaper.System.NetworkSystem;
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

        /// 播放动图
        if (dailyPaper.getLiveImageAutoLaunch().get()) {
            dailyPaperFunction.launchLiveImageWallpaper();
        }

        /// 启动自动推荐壁纸
        if (dailyPaper.getDailyAutoWallpaper().get() && dailyPaper.getDailyAutoWallpaperWhenLaunch().get()) {
            dailyPaperFunction.dailyWallpaper();
        }

        /// 时间自动推荐壁纸
        if (dailyPaper.getDailyAutoWallpaper().get() && dailyPaper.getDailyAutoWallpaperWhenTime().get() >= 1) {
            if (!dailyPaperData.getIsAutoDailyWallpaperRunning().get()) {
                dailyPaperData.setAutoDailyWallpaperStartTime(System.currentTimeMillis());
                dailyPaper.getScheduledExecutorService().scheduleAtFixedRate(dailyPaperData.getAutoDailyWallpaper(), 1 , 1, TimeUnit.MINUTES);
                dailyPaperData.setIsAutoDailyWallpaperRunning(() -> true);
            }
        }

        /// 自动更新
        dailyPaper.getDailyPaperPool().submit(() -> {
            if (dailyPaper.getRenewAutoCheckRenew().get()) {
                String checkTemp = dailyPaperElement.getRenewManualCheckRenewButton().getText();
                try {
                    Platform.runLater(() -> {
                        dailyPaperElement.getRenewManualShowRenewHBox().setVisible(true);
                        dailyPaperElement.getRenewManualCheckRenewButton().setDisable(true);
                        dailyPaperElement.getRenewManualCheckRenewButton().setText("正在检查");
                    });
                    if (NetworkSystem.getInstance().getHasNetwork()) {
                        Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("嘟嘟检查更新中..."));
                        if (dailyPaperFunction.checkRenewDailyPaper()) {
                            Platform.runLater(() -> {
                                dailyPaperElement.getRenewManualShowRenewTextArea().setText("发现更新版本呀！");
                                dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + RenewConfig.getInstance().getLocalRemoteDB().get("RENEW", "未知更新内容"));
                                dailyPaperElement.getRenewManualDownloadRenewHBox().setVisible(true);

                                if (dailyPaper.getRenewAutoDownloadRenew().get()) {
                                    String downloadTemp = dailyPaperElement.getRenewManualDownloadRenewButton().getText();
                                    try {
                                        Platform.runLater(() -> {
                                            dailyPaperElement.getRenewManualDownloadRenewButton().setDisable(true);
                                            dailyPaperElement.getRenewManualDownloadRenewButton().setText("正在更新");
                                        });
                                        if (NetworkSystem.getInstance().getHasNetwork()) {
                                            if (dailyPaperFunction.downloadRenewDailyPaper()) {
                                                Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("版本更新完成！"));
                                                if (dailyPaper.getDailyPaperRenewAutoOpen().get()) {
//                                                    dailyPaperApplication.stop();
                                                    System.exit(0);
                                                }
                                            } else {
                                                Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("版本更新失败？"));
                                            }
                                        } else {
                                            Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("找不到网络哦？"));
                                        }
                                    } catch (Exception exception) {
                                        dailyPaperElement.getRenewManualShowRenewTextArea().setText("不小心出错了哦？");
                                        dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + exception.getMessage());
                                    } finally {
                                        Platform.runLater(() -> {
                                            dailyPaperElement.getRenewManualDownloadRenewButton().setText(downloadTemp);
                                            dailyPaperElement.getRenewManualDownloadRenewButton().setDisable(false);
                                        });
                                    }
                                }

                            });
                        } else {
                            Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("已是最新版本啦！"));
                        }
                    } else {
                        Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("怎么好像没网络？"));
                    }
                } catch (Exception exception) {
                    Platform.runLater(() -> {
                        dailyPaperElement.getRenewManualShowRenewTextArea().setText("不小心出错了哦？");
                        dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + exception.getMessage());
                    });
                } finally {
                    Platform.runLater(() -> {
                        dailyPaperElement.getRenewManualCheckRenewButton().setText(checkTemp);
                        dailyPaperElement.getRenewManualCheckRenewButton().setDisable(false);
                    });
                }
            }
        });

    }

    @Override
    public void beforeStop() {
        dailyPaper.getScheduledExecutorService().shutdownNow();
        dailyPaper.getDailyPaperPool().shutdownNow();
        dailyPaperElement.getStage().close();
        dailyPaper.close();
        Platform.exit();
    }

    @Override
    public void afterStop() {
        System.gc();
//        System.exit(0);
    }

}