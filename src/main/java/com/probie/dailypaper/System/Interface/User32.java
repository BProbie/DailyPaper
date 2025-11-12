package com.probie.dailypaper.System.Interface;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

/**
 * user32.dll
 * */
public interface User32 extends com.sun.jna.platform.win32.User32 {

    User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

    int setWallpaper = 0x0014;
    int updateFile = 0x01;
    int sendChange = 0x02;

    boolean SystemParametersInfo(int action, int param, String pvParam, int fWinIni);

}