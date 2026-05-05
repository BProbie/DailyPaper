package com.probie.dailypaper.aiagent.api;

import com.probie.dailypaper.aiagent.siliconflow.AIAgentSiliconFlow;

public interface IAIAgent {

    /**
     * 获取 AgentSiliconFlow 代理单例
     * @return AgentSiliconFlow 代理单例
     * */
    AIAgentSiliconFlow getAIAgentSiliconFlow();

}