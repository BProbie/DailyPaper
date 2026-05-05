package com.probie.dailypaper.system;

import com.probie.dailypaper.system.api.IImageSystem;

public class ImageSystem extends FileSystem implements IImageSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ImageSystem INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static ImageSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageSystem();
        }
        return INSTANCE;
    }

}