package com.probie.dailypaper.System;

import com.probie.dailypaper.System.Interface.IImageSystem;

public class ImageSystem extends FileSystem implements IImageSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ImageSystem INSTANCE;

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static ImageSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageSystem();
        }
        return INSTANCE;
    }

}