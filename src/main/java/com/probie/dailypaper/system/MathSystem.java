package com.probie.dailypaper.system;

import com.probie.dailypaper.system.api.IMathSystem;

public class MathSystem extends ComputerSystem implements IMathSystem {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static MathSystem INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static MathSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MathSystem();
        }
        return INSTANCE;
    }

}