package com.probie.dailypaper.System.Interface;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.System.Interface.Native.User32;

public interface IComputerSystem {

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
        return new SimpleDateFormat(DailyPaper.getInstance().getCurrentDateFormat().get()).format(new Date());
    }

    /**
     * 获取当前程序的路径
     * @return 当前程序的路径
     * */
    default String getHere() {
        return System.getProperty("user.dir");
    }

    /**
     * 获取用户名称
     * @return 用户名称
     * */
    default String getUserName() {
        return System.getenv().get("USERNAME");
    }

    /**
     * 获取当前操作系统名称
     * Windows n
     * Linux
     * Mac
     * @return 操作系统名称
     * */
    default String getSystemName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取当前操作系统架构
     * Windows amd64 | x86
     * Linux amd64 | aarch64
     * Mac aarch64 | x86
     * @return 操作系统架构
     * */
    default String getSystemArch() {
        return System.getProperty("os.arch");
    }

    /**
     * 更改桌面壁纸(Windows)
     * @param filePath 壁纸路径
     * @param fileName 壁纸名称
     * @return 壁纸是否更改成功
     * */
    default boolean setWallPaper(String filePath, String fileName) {
        return setWallPaper(filePath+"\\"+fileName);
    }

    /**
     * 更改桌面壁纸(Windows)
     * @param fullFilePath 完整壁纸路径
     * @return 壁纸是否更改成功
     * */
    default boolean setWallPaper(String fullFilePath) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            File file = new File(fullFilePath);
            if (file.exists()) {
                return User32.INSTANCE.SystemParametersInfo(User32.setWallpaper, 0, file.getAbsolutePath(), User32.updateFile | User32.sendChange);
            }
        }
        return false;
    }

    /**
     * 获取管理员运行指令(Windows)
     * @param command 指令
     * @return 管理员指令
     * */
    default String getOpCommand(String command) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            return String.format("powershell -Command \"Start-Process cmd -ArgumentList '/c %s C:\\Windows' -Verb RunAs\"", command.replace("\\","\\\\\\"));
        }
        return command;
    }

    /**
     * 运行命令行指令
     * @param command 要执行的命令
     * @return 返回从参数 0 代表执行成功
     * */
    default int runCommand(String command) {
        return runCommand(command, false);
    }

    /**
     * 运行命令行指令
     * @param command 要执行的命令
     * @param isOp 是否用管理员权限执行命令(Windows)
     * @return 返回从参数 0 代表执行成功
     * */
    default int runCommand(String command, boolean isOp) {
        try {
            if (isOp) {
                command = getOpCommand(command);
            }
            return new ProcessBuilder(command.split("\\s+")).start().waitFor();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * 添加开机程序自动启动(Windows)
     * @param fullFilePath 完整本地文件路径
     * @return 是否添加成功
     * */
    default boolean addUserAutoLaunch(String fullFilePath) {
        return addUserAutoLaunch(fullFilePath, new File(fullFilePath).getName().substring(0, fullFilePath.lastIndexOf(".")));
    }

    /**
     * 添加开机程序自动启动(Windows)
     * @param fullFilePath 完整本地文件路径
     * @param regName 注册名称
     * @return 是否添加成功
     * */
    default boolean addUserAutoLaunch(String fullFilePath, String regName) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String regPath = "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Run";
            String command = String.format("reg add \"%s\" /v \"%s\" /t REG_SZ /d \"%s\" /f", regPath, regName, fullFilePath);
            if (runCommand(command) != 0) {
                return runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * 删除开机程序自动启动(Windows)
     * @param regName 注册名称
     * @return 是否删除成功
     * */
    default boolean deleteUserAutoLaunch(String regName) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String regPath = "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Run";
            String command = String.format("reg delete \"%s\" /v \"%s\" /f", regPath, regName);
            if (runCommand(command) != 0) {
                return runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * 添加系统启动程序自动启动(Windows)
     * @param fullFilePath 完整本地文件路径
     * @return 是否添加成功
     * */
    default boolean addSystemAutoLaunch(String fullFilePath) {
        return addSystemAutoLaunch(fullFilePath, new File(fullFilePath).getName().substring(0, fullFilePath.lastIndexOf(".")));
    }

    /**
     * 添加系统启动程序自动启动(Windows)
     * @param fullFilePath 完整本地文件路径
     * @param regName 注册名称
     * @return 是否添加成功
     * */
    default boolean addSystemAutoLaunch(String fullFilePath, String regName) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String command = String.format("schtasks /create /tn \"%s\" /tr \"%s\" /sc onstart /ru \"SYSTEM\" /rl highest /f /np", regName, fullFilePath);
            if (runCommand(command) != 0) {
                return runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
    }

    /**
     * 删除系统启动程序自动启动(Windows)
     * @param regName 注册名称
     * @return 是否添加成功
     * */
    default boolean deleteSystemAutoLaunch(String regName) {
        String systemName = getSystemName().toLowerCase();
        if (systemName.contains("windows")) {
            String command = String.format("schtasks /delete /tn \"%s\" /f", regName);
            if (runCommand(command) != 0) {
                return runCommand(command, true) == 0;
            }
            return true;
        }
        return false;
    }


    /**
     * 打开文件
     * @param fullFilePath 完整本地文件路径
     * */
    default void open(String fullFilePath) {
        try {
            Desktop.getDesktop().open(new File(fullFilePath));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

}