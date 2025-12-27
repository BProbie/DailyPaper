package com.probie.dailypaper.System;

import java.awt.*;
import com.probie.dailypaper.System.Interface.IComputerSystem;

public class ComputerSystem extends NetworkSystem implements IComputerSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static ComputerSystem INSTANCE;

    /**
     * Computer 的一些相关参数
     * */
    private Dimension dimension;

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static ComputerSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ComputerSystem();
        }
        return INSTANCE;
    }

    @Override
    public Dimension getDimension() {
        if (dimension == null) {
            dimension = Toolkit.getDefaultToolkit().getScreenSize();
        }
        return dimension;
    }

}