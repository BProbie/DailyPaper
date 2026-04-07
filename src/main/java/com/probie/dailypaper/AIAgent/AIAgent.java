package com.probie.dailypaper.AIAgent;

import lombok.Data;
import java.util.concurrent.locks.Lock;
import javafx.beans.property.SimpleObjectProperty;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.dailypaper.AIAgent.Interface.IAIAgent;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import com.probie.dailypaper.AIAgent.SiliconFlow.AIAgentSiliconFlow;

@Data
public abstract class AIAgent implements IAIAgent {

    /**
     * 构造函数
     * */
    public AIAgent() {
        init();
    }

    /**
     * 继承的子类必须重写初始化 AI-API 参数的方法
     * */
    protected abstract void init();

    /**
     * 承的子类必须重写获取真正 APIKey 的方法
     * @param APIKey 被加密过的 APIKey
     * @return 解密后的 APIKey
     * */
    protected abstract SimpleObjectProperty<Object> getAPIKey(SimpleObjectProperty<Object> APIKey);

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static AIAgent INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static AIAgent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AIAgent() {
                @Override
                protected void init() {
                    /// 空实现
                }

                @Override
                protected SimpleObjectProperty<Object> getAPIKey(SimpleObjectProperty<Object> APIKey) {
                    /// 空实现
                    return null;
                }
            };
        }
        return INSTANCE;
    }

    /**
     * 进程读锁和写锁
     * */
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 请求连接参数
     * */
    private SimpleObjectProperty<Object> connectTimeout = DailyPaper.getInstance().getConnectTimeout();
    private SimpleObjectProperty<Object> readTimeout = DailyPaper.getInstance().getReadTimeout();
    private SimpleObjectProperty<Object> writeTimeout = DailyPaper.getInstance().getWriteTimeout();

    /**
     * AI-API 基本参数
     * */
    private SimpleObjectProperty<Object> APIKey;
    private SimpleObjectProperty<Object> APIUrl;
    private SimpleObjectProperty<Object> APIModel;

    @Override
    public AIAgentSiliconFlow getAIAgentSiliconFlow() {
        return AIAgentSiliconFlow.getInstance();
    }

}