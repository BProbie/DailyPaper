package com.probie.dailypaper.DailyPaper;

import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperEvent;

public class DailyPaperEvent implements IDailyPaperEvent {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperEvent INSTANCE;

    @Override
    public void createEvent() {

    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperEvent();
        }
        return INSTANCE;
    }

}