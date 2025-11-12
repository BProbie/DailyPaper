package com.probie.dailypaper.Exception;

public class DailyPaperException extends RuntimeException {

    /**
     * DailyPaper专属错误信息
     * @param message 错误信息
     * */
    public DailyPaperException(String message) {
        super(message);
    }

}