/**
 * SoftWare-Name: DailyPaper
 * SoftWare-Version: 1.4
 * SoftWare-Author: Probie
 * SoftWare-GitHub: <a href="https://github.com/BProbie/DailyPaper">DailyPaper-GitHub</a>
 * */

/**
 * TODO
 * 优化调试
 * 优化参数
 * 优化设置
 * 高级设置
 * 导入插件
 * 异常处理
 * 日志记录
 * UI视觉优化
 * UI功能优化
 * */

package com.probie.dailypaper;

import com.probie.dailypaper.Property.SelectableLabel;

public class Main {
    public static void main(String[] args) {
        com.probie.dailypaper.DailyPaper.DailyPaper.getInstance().launch(args);
        new SelectableLabel("a");
    }
}