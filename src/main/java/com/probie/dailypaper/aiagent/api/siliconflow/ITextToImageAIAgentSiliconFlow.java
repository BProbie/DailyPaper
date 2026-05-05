package com.probie.dailypaper.aiagent.api.siliconflow;

public interface ITextToImageAIAgentSiliconFlow {

    /**
     * 提示词生成图片
     * @param prompt 输入的提示词
     * @return Image 的 URL 地址组
     * */
    String[] turnTextToImage(String prompt);

}