package com.probie.dailypaper.AIAgent.Interface.SiliconFlow;

public interface IImageToTextAIAgentSiliconFlow {

    /**
     * 图片生成描述信息
     * @param fullFilePath 完整本地文件路径
     * @param prompt 提示词
     * @return 图片描述信息, string[0] 是大模型基于图片和提示词的回复信息, string[1] 是大模型对于图片的描述
     * */
    String[] turnImageToText(String fullFilePath, String prompt);

}