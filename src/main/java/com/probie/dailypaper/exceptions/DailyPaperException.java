package com.probie.dailypaper.exceptions;

import com.probie.dailypaper.exceptions.api.IDailyPaperException;

public class DailyPaperException extends RuntimeException implements IDailyPaperException {

    /**
     * DailyPaper 专属错误信息
     * @param message 错误信息
     * */
    public DailyPaperException(String message) {
        super(message);
    }

}