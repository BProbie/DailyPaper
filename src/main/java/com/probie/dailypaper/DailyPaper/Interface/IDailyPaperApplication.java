package com.probie.dailypaper.DailyPaper.Interface;

public interface IDailyPaperApplication {

    /**
     * 程序启动前执行
     * */
    void beforeStart();

    /**
     * 程序启动后运执行
     * */
    void afterStart();

    /**
     * 程序结束前执行
     * */
    void beforeStop();

    /**
     * 程序结束后运执行
     * */
    void afterStop();


}