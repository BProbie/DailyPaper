package com.probie.dailypaper.exceptions;

import com.probie.dailypaper.exceptions.api.IDailyPaperException;

public class DailyPaperException extends RuntimeException implements IDailyPaperException {

    private final DailyPaperExceptionMenu dailyPaperExceptionMenu;

    /**
     * DailyPaper 错误
     * @param dailyPaperExceptionMenu 错误
     * */
    public DailyPaperException(DailyPaperExceptionMenu dailyPaperExceptionMenu) {
        this.dailyPaperExceptionMenu = dailyPaperExceptionMenu;
    }

    @Override
    public String getMessage() {
        return """
                
                Code: %d
                Message: %s
                """.formatted(
                dailyPaperExceptionMenu.getCode(),
                dailyPaperExceptionMenu.getMessage()
        );
    }

}