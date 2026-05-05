package com.probie.dailypaper.system;

import com.probie.dailypaper.system.api.IGIFSystem;

public class GIFSystem extends ImageSystem implements IGIFSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static GIFSystem INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static GIFSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GIFSystem();
        }
        return INSTANCE;
    }

}