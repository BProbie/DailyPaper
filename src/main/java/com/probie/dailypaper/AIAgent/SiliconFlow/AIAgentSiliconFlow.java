package com.probie.dailypaper.AIAgent.SiliconFlow;

import lombok.Data;
import java.io.File;
import com.probie.dailypaper.AIAgent.AIAgent;
import javafx.beans.property.SimpleObjectProperty;
import com.probie.dailypaper.DailyPaper.DailyPaper;
import com.probie.encryption.Encryption.Encryption;
import com.probie.dailypaper.AIAgent.Interface.SiliconFlow.IAIAgentSiliconFlow;

@Data
public abstract class AIAgentSiliconFlow extends AIAgent implements IAIAgentSiliconFlow {

    /**
     * 构造函数
     * */
    public AIAgentSiliconFlow() {
        init();
    }

    /**
     * 继承的子类必须重写初始化 AI-API 参数的方法
     * */
    protected abstract void init();

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static AIAgentSiliconFlow INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static AIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AIAgentSiliconFlow() {
                @Override
                protected void init() {
                    setAPIKey(getAPIKey(DailyPaper.getInstance().getAPIKeySiliconFlow()));
                }
            };
        }
        return INSTANCE;
    }

    @Override
    protected SimpleObjectProperty<Object> getAPIKey(SimpleObjectProperty<Object> APIKey) {
        if (!Encryption.getInstance().isDebug()) {
            if (Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().connect(ClassLoader.getSystemResourceAsStream(new File(Encryption.getFilePath()).getName()))) {
                Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().setIsAutoCommit(false);
                return new SimpleObjectProperty<>(Encryption.getInstance().getDecrypterFactory().getMapDecrypter().decryptByMap(APIKey.get()));
            }
        }
        System.exit(0);
        return null;
    }

}