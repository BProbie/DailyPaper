package com.probie.dailypaper.AIAgent.Interface.SiliconFlow;

import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;

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