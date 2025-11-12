package com.probie.dailypaper.System.Interface;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;
import com.probie.dailypaper.DailyPaper.DailyPaper;

public interface IComputerSystem {

    /**
     * 获取当前程序的路径
     * @return 当前程序的路径
     * */
    default String getHere() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取当前屏幕大小
     * @return 当前屏幕大小
     * */
    Dimension getDimension();

    /**
     * 获取当前时间
     * @param date Date 枚举类型
     * @return 当前时间的 int 类型
     * */
    default int getDate(com.probie.dailypaper.Enum.Date date) {
        return switch (date) {
            case YEAR -> Calendar.getInstance().get(Calendar.YEAR);
            case MONTH -> Calendar.getInstance().get(Calendar.MONTH) + 1;
            case DAY -> Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            case HOUR -> Calendar.getInstance().get(Calendar.HOUR);
            case MINUTE -> Calendar.getInstance().get(Calendar.MINUTE);
            case SECONDE -> Calendar.getInstance().get(Calendar.SECOND);
        };
    }

    /**
     * 获取当前格式化时间
     * @return 当前格式化时间
     * */
    default String getCurrentFormatDate() {
        return new SimpleDateFormat(DailyPaper.getInstance().getCurrentDateFormat()).format(new Date());
    }

    /**
     * 更改桌面壁纸
     * @param filePath 壁纸路径
     * @param fileName 壁纸名称
     * @return 壁纸是否更改成功
     * */
    default boolean setWallPaper(String filePath, String fileName) {
        return setWallPaper(filePath+"\\"+fileName);
    }

    /**
     * 更改桌面壁纸
     * @param fullFilePath 完整壁纸路径
     * @return 壁纸是否更改成功
     * */
    default boolean setWallPaper(String fullFilePath) {
        File file = new File(fullFilePath);
        if (file.exists()) {
            BufferedImage bufferedImage = DailyPaper.getInstance().getImageSystem().turnLocalFileToBufferedImage(fullFilePath);
            return User32.INSTANCE.SystemParametersInfo(User32.setWallpaper, 0, file.getAbsolutePath(), User32.updateFile | User32.sendChange);
        }
        return false;
    }

}