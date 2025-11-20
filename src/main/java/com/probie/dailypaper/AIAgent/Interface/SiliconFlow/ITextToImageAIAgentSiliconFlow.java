package com.probie.dailypaper.AIAgent.Interface.SiliconFlow;

public interface ITextToImageAIAgentSiliconFlow {

    /**
     * 提示词生成图片
     * @param prompt 输入的提示词
     * @return Image 的 URL 地址
     * */
    String[] turnTextToImage(String prompt);

}