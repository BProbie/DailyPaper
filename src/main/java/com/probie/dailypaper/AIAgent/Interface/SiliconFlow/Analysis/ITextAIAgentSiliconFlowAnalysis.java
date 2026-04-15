package com.probie.dailypaper.AIAgent.Interface.SiliconFlow.Analysis;

public interface ITextAIAgentSiliconFlowAnalysis {

    /**
     * 解释文本
     * @param text 文本
     * @return 生成的文本组, string[0] 是大模型返回的内容, string[1] 是大模型返回这个内容的思考过程即原因
     * */
    String[] analysisText(String text);

}