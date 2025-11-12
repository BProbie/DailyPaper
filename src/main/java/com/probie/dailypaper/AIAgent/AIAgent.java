package com.probie.dailypaper.AIAgent;

import lombok.Data;
import java.util.function.Supplier;
import java.util.concurrent.locks.Lock;
import com.probie.dailypaper.AIAgent.Interface.IAIAgent;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Data
public abstract class AIAgent implements IAIAgent {

    /**
     * 继承的子类必须重写初始化AI-API参数的方法
     * */
    protected abstract void init();

    /**
     * 承的子类必须重写获取真正APIKey的方法
     * @param APIKey 被加密过的APIKey
     * */
    protected abstract Supplier<String> getAPIKey(Supplier<String> APIKey);

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static AIAgent INSTANCE;

    /**
     * 进程读锁和写锁
     * */
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * AI-API基本参数
     * */
    private Supplier<String> APIKey;
    private Supplier<String> APIUrl;
    private Supplier<String> Model;

    @Override
    public KolorsAgent getKolorsAgent() {
        return KolorsAgent.getInstance();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static AIAgent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AIAgent() {
                @Override
                protected void init() {
                    /// 空实现
                }

                @Override
                protected Supplier<String> getAPIKey(Supplier<String> APIKey) {
                    /// 空实现
                    return null;
                }
            };
            INSTANCE.init();
        }
        return INSTANCE;
    }

    /**
     * 获取动态参数的静态值
     * */
    public String getAPIKey() {
        return APIKey.get();
    }

    public String getAPIUrl() {
        return APIUrl.get();
    }

    public String getModel() {
        return Model.get();
    }

}