package com.probie.dailypaper.AIAgent.Interface;

import com.probie.dailypaper.AIAgent.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.TextToImageAIAgentSiliconFlow;

public interface IAIAgentSiliconFlow {

    /**
     * 获取 TextToTextAIAgentSiliconFlow 代理单例
     * @return TextToTextAIAgentSiliconFlow 代理单例
     * */
    TextToTextAIAgentSiliconFlow getTextToTextAIAgentSiliconFlow();

    /**
     * 获取 TextToImageAIAgentSiliconFlow 代理单例
     * @return TextToImageAIAgentSiliconFlow 代理单例
     * */
    TextToImageAIAgentSiliconFlow getTextToImageAIAgentSiliconFlow();



}