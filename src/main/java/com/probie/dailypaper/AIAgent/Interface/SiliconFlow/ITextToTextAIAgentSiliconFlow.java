package com.probie.dailypaper.AIAgent.Interface.SiliconFlow;

public interface ITextToTextAIAgentSiliconFlow {

    /**
     * 提示词生成文本
     * @param prompt 输入的提示词
     * @return 生成的文本
     * */
    String turnTextToText(String prompt);

}