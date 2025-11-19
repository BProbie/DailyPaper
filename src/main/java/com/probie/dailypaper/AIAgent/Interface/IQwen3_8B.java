package com.probie.dailypaper.AIAgent.Interface;

public interface IQwen3_8B {

    /**
     * 提示词生成文本
     * @param text 输入的提示词
     * @return 生成的文本
     * */
    String turnTextToText(String text);

}