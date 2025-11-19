package com.probie.dailypaper.AIAgent.Interface;

import com.probie.dailypaper.AIAgent.Qwen3_8BAgent;
import com.probie.dailypaper.AIAgent.KolorsAgent;

public interface IAIAgent {

    /**
     * 获取Kolors代理单例
     * @return Kolors代理单例
     * */
    KolorsAgent getKolorsAgent();

    /**
     * 获取Qwen3_8B代理单例
     * @return Qwen3_8B
     * */
    Qwen3_8BAgent getQwen3_8BAgent();

}