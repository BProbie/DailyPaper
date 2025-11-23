package com.probie.dailypaper.AIAgent.Interface.SiliconFlow;

public interface ITextToTextAIAgentSiliconFlow {

    /**
     * 提示词生成文本
     * @param prompt 输入的提示词
     * @return 生成的文本组, string[0] 是大模型返回的内容, string[1] 是大模型返回这个内容的思考过程即原因
     * */
    String[] turnTextToText(String prompt);

}