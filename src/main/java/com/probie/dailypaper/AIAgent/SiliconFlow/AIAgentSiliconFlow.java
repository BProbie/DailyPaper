package com.probie.dailypaper.AIAgent.SiliconFlow;

import java.io.File;
import java.util.function.Supplier;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.encryption.Encryption.Encryption;
import com.probie.dailypaper.AIAgent.Interface.SiliconFlow.IAIAgentSiliconFlow;

public abstract class AIAgentSiliconFlow extends AIAgent implements IAIAgentSiliconFlow {

    /**
     * 构造函数
     * */
    public AIAgentSiliconFlow() {
        init();
    }

    /**
     * 继承的子类必须重写初始化AI-API参数的方法
     * */
    protected abstract void init();

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static AIAgentSiliconFlow INSTANCE;

    @Override
    public TextToTextAIAgentSiliconFlow getTextToTextAIAgentSiliconFlow() {
        return TextToTextAIAgentSiliconFlow.getInstance();
    }

    @Override
    public TextToImageAIAgentSiliconFlow getTextToImageAIAgentSiliconFlow() {
        return TextToImageAIAgentSiliconFlow.getInstance();
    }

    @Override
    protected Supplier<String> getAPIKey(Supplier<String> APIKey) {
        if (!Encryption.getInstance().isDebug()) {
            if (Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().connect(ClassLoader.getSystemResourceAsStream(new File(Encryption.getFilePath()).getName()))) {
                Encryption.getInstance().getConfigFactory().getKeyConfig().getLocalDB().setIsAutoCommit(false);
                return () -> Encryption.getInstance().getDecrypterFactory().getMapDecrypter().decryptByMap(APIKey.get());
            }
        }
        System.exit(0);
        return null;
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static AIAgentSiliconFlow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AIAgentSiliconFlow() {
                @Override
                protected void init() {
                    /// 空实现
                }
            };
        }
        return INSTANCE;
    }

}