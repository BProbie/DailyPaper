package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.util.function.Supplier;
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import com.probie.dailypaper.Enum.Date;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.dailypaper.AIAgent.SiliconFlow.AIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;

@Data
public class DailyPaper {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaper INSTANCE;

    /**
     * 默认参数键 KEY
     * */
    public String KeyRootPath = "RootPath";
    public String KeyCurrentDateFormat = "CurrentDateFormat";

    private String KeyConnectTimeout = "ConnectTimeout";
    private String KeyReadTimeout = "ReadTimeout";
    private String KeyWriteTimeout = "WriteTimeout";

    public String KeyKolorsAPIModelSiliconFlow = "KolorsAPIModelSiliconFlow";
    public String KeyQwen3_8BAPIModelSiliconFlow = "Qwen3_8BAPIModelSiliconFlow";

    public String KeyAPIKeySiliconFlow = "APIKeySiliconFlow";
    public String KeyAPIUrlTextToTextSiliconFlow = "APIUrlTextToTextSiliconFlow";
    public String KeyAPIUrlTextToImageSiliconFlow = "APIUrlTextToImageSiliconFlow";
    public String KeyAPIModelTextToTextSiliconFlow = "APIUrlTextToTextSiliconFlow";
    public String KeyAPIModelTextToImageSiliconFlow = "APIUrlTextToImageSiliconFlow";

    public String KeyImageSize = "ImageSize";
    public String KeyImageCount = "ImageCount";

    public String KeyImageFileName = "ImageFileName";

    public String KeyLogConfigFilePath = "LogConfigFilePath";
    public String KeyLogConfigFileName = "LogConfigFileName";

    public String KeyTempConfigFilePath = "TempConfigFilePath";
    public String KeyTempConfigFileName = "TempConfigFileName";

    public String KeyConfigConfigFilePath = "ConfigConfigFilePath";
    public String KeyConfigConfigFileName = "ConfigConfigFileName";

    public String KeyRenewConfigFilePath = "RenewConfigFilePath";
    public String KeyRenewConfigFileName = "RenewConfigFileName";
    public String KeyRenewConfigFileUrl = "RenewConfigFileUrl";

    /**
     * 程序默认参数 - 动态更新
     * */
    public Supplier<String> RootPath = () -> getConfigConfig().getLocalDB().get(getKeyRootPath(),
            getComputerSystem().getHere()).toString();
    public Supplier<String> CurrentDateFormat = () -> getConfigConfig().getLocalDB().get(getKeyCurrentDateFormat(),
            "[yyyy.MM.dd-HH:mm:ss]").toString();

    private Supplier<Integer> ConnectTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyConnectTimeout(),
            60).toString());
    private Supplier<Integer> ReadTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyReadTimeout(),
            180).toString());
    private Supplier<Integer> WriteTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyWriteTimeout(),
            60).toString());

    public Supplier<String> KolorsModelSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyKolorsAPIModelSiliconFlow(),
            "Kwai-Kolors/Kolors").toString();
    public Supplier<String> Qwen3_8BModelSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyQwen3_8BAPIModelSiliconFlow(),
            "Qwen/Qwen3-8B").toString();

    public Supplier<String> APIKeySiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyAPIKeySiliconFlow(),
            "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y").toString();
    public Supplier<String> APIUrlTextToTextSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyAPIUrlTextToTextSiliconFlow(),
            "https://api.siliconflow.cn/v1/chat/completions").toString();
    public Supplier<String> APIUrlTextToImageSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyAPIUrlTextToImageSiliconFlow(),
            "https://api.siliconflow.cn/v1/images/generations").toString();
    public Supplier<String> APIModelTextToTextSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyAPIModelTextToTextSiliconFlow(),
            getQwen3_8BModelSiliconFlow().get()).toString();
    public Supplier<String> APIModelTextToImageSiliconFlow = () -> getConfigConfig().getLocalDB().get(getKeyAPIModelTextToImageSiliconFlow(),
            getKolorsModelSiliconFlow().get()).toString();

    public Supplier<String> ImageSize = () -> getConfigConfig().getLocalDB().get(getKeyImageSize(),
            ((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getWidth()))+"x"+((int) Math.floor(getMathSystem().getFitDimension(getComputerSystem().getDimension()).getHeight()))).toString();
    public Supplier<Integer> ImageCount = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyImageCount(),
            1).toString());

    public Supplier<String> ImageFileName = () -> getConfigConfig().getLocalDB().get(getKeyImageFileName(),
            "Image.png").toString();

    /**
     * 程序默认参数 - 静态存储
     * */
    public String LogConfigFilePath = getConfigConfig().getLocalDB().get(getKeyLogConfigFilePath(),
            getRootPath().get()).toString();
    public String LogConfigFileName = getConfigConfig().getLocalDB().get(getKeyLogConfigFileName(),
            "Log."+getComputerSystem().getDate(Date.YEAR)+"-"+getComputerSystem().getDate(Date.MONTH)+"-"+getComputerSystem().getDate(Date.DAY)+".txt").toString();

    public String TempConfigFilePath = getConfigConfig().getLocalDB().get(getKeyTempConfigFilePath(),
            getRootPath().get()).toString();
    public String TempConfigFileName = getConfigConfig().getLocalDB().get(getKeyTempConfigFileName(),
            "Temp.properties").toString();

    public String ConfigConfigFilePath = getConfigConfig().getLocalDB().get(getKeyConfigConfigFilePath(),
            getRootPath().get()).toString();
    public String ConfigConfigFileName = getConfigConfig().getLocalDB().get(getKeyConfigConfigFileName(),
            "Config.properties").toString();

    public String RenewConfigFilePath = getConfigConfig().getLocalDB().get(getKeyRenewConfigFilePath(),
            getRootPath().get()).toString();
    public String RenewConfigFileName = getConfigConfig().getLocalDB().get(getKeyRenewConfigFileName(),
            "Renew.properties").toString();
    public String RenewConfigFileUrl = getConfigConfig().getLocalDB().get(getKeyRenewConfigFileUrl(),
            "https://raw.githubusercontent.com/BProbie/DailyPaper/refs/heads/master/"+getRenewConfigFileName()).toString();

    /**
     * 获取懒加载的工具类单例对象
     * */
    public AIAgent getAIAgent() {
        return AIAgent.getInstance();
    }

    public AIAgentSiliconFlow getAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow();
    }

    public TextToTextAIAgentSiliconFlow getTextToTextAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow().getTextToTextAIAgentSiliconFlow();
    }

    public TextToImageAIAgentSiliconFlow getTextToImageAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow().getTextToImageAIAgentSiliconFlow();
    }

    public ComputerSystem getComputerSystem() {
        return ComputerSystem.getInstance();
    }

    public NetworkSystem getNetworkSystem() {
        return NetworkSystem.getInstance();
    }

    public FileSystem getFileSystem() {
        return FileSystem.getInstance();
    }

    public MathSystem getMathSystem() {
        return MathSystem.getInstance();
    }

    public ImageSystem getImageSystem() {
        return ImageSystem.getInstance();
    }

    public PictureSystem getPictureSystem() {
        return PictureSystem.getInstance();
    }

    public GIFSystem getGIFSystem() {
        return GIFSystem.getInstance();
    }

    public Config getConfig() {
        return Config.getInstance();
    }

    public LogConfig getLogConfig() {
        return getConfig().getLogConfig();
    }

    public TempConfig getTempConfig() {
        return getConfig().getTempConfig();
    }

    public ConfigConfig getConfigConfig() {
        return getConfig().getConfigConfig();
    }

    public RenewConfig getRenewConfig() {
        return getConfig().getRenewConfig();
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaper();
        }
        return INSTANCE;
    }

}