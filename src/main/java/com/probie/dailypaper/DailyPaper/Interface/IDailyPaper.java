package com.probie.dailypaper.DailyPaper.Interface;

import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.dailypaper.AIAgent.SiliconFlow.AIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;

public interface IDailyPaper {

    /**
     * 启动应用程序
     * */
    void launch(String[] args);

    /**
     * 获取懒加载的工具类单例对象
     * */
    AIAgent getAIAgent();
    AIAgentSiliconFlow getAIAgentSiliconFlow();
    TextToTextAIAgentSiliconFlow getTextToTextAIAgentSiliconFlow();
    TextToImageAIAgentSiliconFlow getTextToImageAIAgentSiliconFlow();
    ComputerSystem getComputerSystem();
    NetworkSystem getNetworkSystem();
    FileSystem getFileSystem();
    MathSystem getMathSystem();
    ImageSystem getImageSystem();
    PictureSystem getPictureSystem();
    GIFSystem getGIFSystem();
    Config getConfig();
    LogConfig getLogConfig();
    TempConfig getTempConfig();
    ConfigConfig getConfigConfig();
    RenewConfig getRenewConfig();
    LiveImageConfig getLiveImageConfig();

}