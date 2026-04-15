package com.probie.dailypaper.AIAgent.Interface.SiliconFlow.Analysis;

public interface IImageAIAgentSiliconFlowAnalysis {

    /**
     * 解释图片
     * @param fullFilePath 完整本地文件路径
     * @param prompt 提示词
     * @return 图片描述信息, string[0] 是大模型基于图片和提示词的回复信息, string[1] 是大模型对于图片的描述
     * */
    String[] analysisImage(String fullFilePath, String prompt);

}