package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.util.function.Supplier;
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import com.probie.dailypaper.Enum.Date;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.dailypaper.AIAgent.KolorsAgent;
import com.probie.dailypaper.AIAgent.Qwen3_8BAgent;

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

    public String KeyKolorsAPIKey = "KolorsAPIKey";
    public String KeyKolorsAPIUrl = "KolorsAPIUrl";
    public String KeyKolorsAPIModel = "KolorsAPIModel";
    public String KeyKolorsImageSize = "KolorsImageSize";
    public String KeyKolorsImageCount = "KolorsImageCount";
    public String KeyKolorsFilePath = "KolorsFilePath";
    public String KeyKolorsFileName = "KolorsFileName";

    public String KeyQwen3_8BAPIKey = "Qwen3_8BAPIKey";
    public String KeyQwen3_8BAPIUrl = "Qwen3_8BAPIUrl";
    public String KeyQwen3_8BAPIModel = "Qwen3_8BAPIModel";

    public String KeyLogConfigFilePath = "LogConfigFilePath";
    public String KeyLogConfigFileName = "LogConfigFileName";

    public String KeyTempConfigFilePath = "TempConfigFilePath";
    public String KeyTempConfigFileName = "TempConfigFileName";

    public String KeyConfigConfigFilePath = "ConfigConfigFilePath";
    public String KeyConfigConfigFileName = "ConfigConfigFileName";

    public String KeyRenewConfigFilePath = "RenewConfigFilePath";
    public String KeyRenewConfigFileName = "RenewConfigFileName";

    /**
     * 程序默认参数 - 动态更新
     * */
    public Supplier<String> RootPath = () -> getConfigConfig().getLocalDB().get(getKeyRootPath(),
            getComputerSystem().getHere()).toString();
    public Supplier<String> CurrentDateFormat = () -> getConfigConfig().getLocalDB().get(getKeyCurrentDateFormat(),
            "[yyyy.MM.dd-HH:mm:ss]").toString();

    public Supplier<String> KolorsAPIKey = () -> getConfigConfig().getLocalDB().get(getKeyKolorsAPIKey(),
            "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y").toString();
    public Supplier<String> KolorsAPIUrl = () -> getConfigConfig().getLocalDB().get(getKeyKolorsAPIUrl(),
            "https://api.siliconflow.cn/v1/images/generations").toString();
    public Supplier<String> KolorsModel = () -> getConfigConfig().getLocalDB().get(getKeyKolorsAPIModel(),
            "Kwai-Kolors/Kolors").toString();
    private Supplier<Integer> ConnectTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyConnectTimeout(),
            60).toString());
    private Supplier<Integer> ReadTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyReadTimeout(),
            180).toString());
    private Supplier<Integer> WriteTimeout = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyWriteTimeout(),
            60).toString());
    public Supplier<String> KolorsImageSize = () -> getConfigConfig().getLocalDB().get(getKeyKolorsImageSize(),
            ((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getWidth()))+"x"+((int) Math.floor(getMathSystem().getFitDimension(getComputerSystem().getDimension()).getHeight()))).toString();
    public Supplier<Integer> KolorsImageCount = () -> Integer.valueOf(getConfigConfig().getLocalDB().get(getKeyKolorsImageCount(),
            1).toString());
    public Supplier<String> KolorsFilePath = () -> getConfigConfig().getLocalDB().get(getKeyKolorsFilePath(),
            getRootPath().get()).toString();
    public Supplier<String> KolorsFileName = () -> getConfigConfig().getLocalDB().get(getKeyKolorsFileName(),
            "Wallpaper.png").toString();

    public Supplier<String> Qwen3_8BAPIKey = () -> getConfigConfig().getLocalDB().get(getKeyQwen3_8BAPIKey(),
            "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y").toString();
    public Supplier<String> Qwen3_8BAPIUrl = () -> getConfigConfig().getLocalDB().get(getKeyQwen3_8BAPIUrl(),
            "https://api.siliconflow.cn/v1/chat/completions").toString();
    public Supplier<String> Qwen3_8BModel = () -> getConfigConfig().getLocalDB().get(getKeyQwen3_8BAPIModel(),
            "Qwen/Qwen3-8B").toString();

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

    /**
     * 获取懒加载的工具类单例对象
     * */
    public AIAgent getAIAgent() {
        return AIAgent.getInstance();
    }

    public KolorsAgent getKolorsAgent() {
        return getAIAgent().getKolorsAgent();
    }

    public Qwen3_8BAgent getQwen3_8BAgent() {
        return getAIAgent().getQwen3_8BAgent();
    }

    public ComputerSystem getComputerSystem() {
        return ComputerSystem.getInstance();
    }

    public TrustSystem getTrustSystem() {
        return TrustSystem.getInstance();
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