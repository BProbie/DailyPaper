package com.probie.dailypaper.Enum;

import lombok.Getter;

/**
 * 日期时间枚举
 * */
public enum Date {

    YEAR(0, "年"),
    MONTH(1, "月"),
    DAY(2,"日"),
    HOUR(3, "时"),
    MINUTE(4, "分"),
    SECONDE(5, "秒");

    @Getter
    private final int index;

    @Getter
    private final String name;

    Date(int index, String name) {
        this.index = index;
        this.name = name;
    }

}