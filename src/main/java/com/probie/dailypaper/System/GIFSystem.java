package com.probie.dailypaper.System;

import com.probie.dailypaper.System.Interface.IGIFSystem;

public class GIFSystem extends PictureSystem implements IGIFSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static GIFSystem INSTANCE;

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static GIFSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GIFSystem();
        }
        return INSTANCE;
    }

}