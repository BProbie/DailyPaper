package com.probie.dailypaper.System;

import com.probie.dailypaper.System.Interface.IPictureSystem;

public class PictureSystem extends ImageSystem implements IPictureSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static PictureSystem INSTANCE;

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static PictureSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PictureSystem();
        }
        return INSTANCE;
    }

}