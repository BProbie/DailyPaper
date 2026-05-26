package com.probie.dailypaper.exceptions;

import lombok.Getter;
import com.probie.dailypaper.exceptions.api.IDailyPaperExceptionMenu;

public enum DailyPaperExceptionMenu implements IDailyPaperExceptionMenu {

    /**
     * DailyPaper 默认错误
     * */
    DAILY_PAPER_EXCEPTION(0, "DAILY_PAPER_EXCEPTION");

    @Getter
    private final int code;

    @Getter
    private final String message;

    DailyPaperExceptionMenu(int code, String message) {
        this.code = code;
        this.message = message;
    }

}