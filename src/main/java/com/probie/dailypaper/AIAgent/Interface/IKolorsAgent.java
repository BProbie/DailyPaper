package com.probie.dailypaper.AIAgent.Interface;

public interface IKolorsAgent {

    /**
     * 文字生成图片
     * @param text 提示词
     * @return Image的URL地址
     * */
    String[] turnTextToImage(String text);

}