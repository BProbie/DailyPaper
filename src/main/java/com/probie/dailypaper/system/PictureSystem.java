package com.probie.dailypaper.system;

import com.probie.dailypaper.system.api.IPictureSystem;

public class PictureSystem extends ImageSystem implements IPictureSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static PictureSystem INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static PictureSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PictureSystem();
        }
        return INSTANCE;
    }

}