package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.util.function.Supplier;
import com.probie.dailypaper.System.*;
import com.probie.dailypaper.Enum.Date;
import com.probie.dailypaper.Config.Config;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.dailypaper.Config.LogConfig;
import com.probie.dailypaper.Config.TempConfig;
import com.probie.dailypaper.AIAgent.KolorsAgent;
import com.probie.dailypaper.Config.ConfigConfig;

@Data
public class DailyPaper {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaper INSTANCE;

    /**
     * 程序默认参数 - 动态更新
     * */
    public String CurrentDateFormat = getConfigConfig().getLocalDB().get("CurrentDateFormat","[yyyy.MM.dd-HH:mm:ss]").toString();

    public Supplier<String> KolorsAPIKey = () -> "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y";
    public Supplier<String> KolorsAPIUrl = () -> "https://api.siliconflow.cn/v1/images/generations";
    public Supplier<String> KolorsModel = () -> "Kwai-Kolors/Kolors";
    private Supplier<Integer> connectTimeout = () -> 60;
    private Supplier<Integer> readTimeout = () -> 180;
    private Supplier<Integer> writeTimeout = () -> 60;
    public Supplier<String> KolorsImageSize = () -> ((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getWidth()))+"x"+((int) Math.floor(getMathSystem().getFitDimension(getComputerSystem().getDimension()).getHeight()));
    public Supplier<Integer> KolorsImageCount = () -> 1;
    public Supplier<String> KolorsFilePath = () -> getComputerSystem().getHere();
    public Supplier<String> KolorsFileName = () -> "Wallpaper.png";

    /**
     * 程序默认参数 - 静态存储
     * */
    public String LogConfigFilePath = getComputerSystem().getHere();
    public String LogConfigFileName = "Log-Dailypaper."+getComputerSystem().getDate(Date.YEAR)+"-"+getComputerSystem().getDate(Date.MONTH)+"-"+getComputerSystem().getDate(Date.DAY)+".txt";

    public String TempConfigFilePath = getComputerSystem().getHere();
    public String TempConfigFileName = "Temp-Dailypaper.properties";

    public String ConfigConfigFilePath = getComputerSystem().getHere();
    public String ConfigConfigFileName = "Config-Dailypaper.properties";

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaper();
        }
        return INSTANCE;
    }

    /**
     * 获取懒加载的工具类单例对象
     * */
    public AIAgent getAIAgent() {
        return AIAgent.getInstance();
    }

    public KolorsAgent getKolorsAgent() {
        return getAIAgent().getKolorsAgent();
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

}