package com.probie.dailypaper.DailyPaper;

import java.awt.*;
import lombok.Data;
import javafx.stage.Stage;
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
        DailyPaperElement.getInstance().createElement(stage);
        DailyPaperStyle.getInstance().createStyle();
        DailyPaperEvent.getInstance().createEvent();
        stage.show();
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