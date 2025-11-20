package com.probie.dailypaper.AIAgent.Interface;

import com.probie.dailypaper.AIAgent.SiliconFlow.AIAgentSiliconFlow;

public interface IAIAgent {

    /**
     * 获取 AgentSiliconFlow 代理单例
     * @return AgentSiliconFlow 代理单例
     * */
    AIAgentSiliconFlow getAIAgentSiliconFlow();

}