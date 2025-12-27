package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import java.util.function.Supplier;
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import javafx.application.Application;
import com.probie.dailypaper.Enum.Date;
import com.probie.dailypaper.AIAgent.AIAgent;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaper;
import com.probie.dailypaper.AIAgent.SiliconFlow.AIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;

@Data
public class DailyPaper implements IDailyPaper {

    /**
     * DailyPaper 版本参数
     * */
    private final String NAME = "DailyPaper";
    private final String VERSION = "1.0.0";
    private final String AUTHOR = "probie";
    private final String URL = "https://github.com/BProbie/DailyPaper";

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaper INSTANCE;

    /**
     * 默认参数键 KEY
     * */
    /// 动态
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

    public String KeyDailyPaperRenewFilePath = "DailyPaperRenewFilePath";
    public String KeyDailyPaperRenewFileNameWindows = "DailyPaperRenewFileNameWindows";

    public String KeyDailyPaperDownloadFilePath = "DailyPaperDownloadFilePath";
    public String KeyDailyPaperDownloadFileNameWindows = "DailyPaperDownloadFileNameWindows";
    public String KeyDailyPaperDownloadFileIsOpen = "DailyPaperDownloadFileIsOpen";
    public String KeyDailyPaperDownloadUrlWindows = "DailyPaperDownloadUrlWindows";

    public String KeyDailyPaperStageWidth = "DailyPaperStageWidth";
    public String KeyDailyPaperStageHeight = "DailyPaperStageHeight";

    /// 静态
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

    public Supplier<String> DailyPaperRenewFilePath = () -> getConfigConfig().getLocalDB().get(getKeyDailyPaperRenewFilePath(),
            getRootPath().get()).toString();
    public Supplier<String> DailyPaperRenewFileNameWindows = () -> getConfigConfig().getLocalDB().get(getKeyDailyPaperRenewFileNameWindows(),
            "Renew.exe").toString();

    public Supplier<String> DailyPaperDownloadFilePath = () -> getConfigConfig().getLocalDB().get(getKeyDailyPaperDownloadFilePath(),
            getRootPath().get()).toString();
    public Supplier<String> DailyPaperDownloadFileNameWindows = () -> getConfigConfig().getLocalDB().get(getKeyDailyPaperDownloadFileNameWindows(),
            "DailyPaper.exe").toString();
    public Supplier<Boolean> DailyPaperDownloadFileIsOpen = () -> (Boolean) getConfigConfig().getLocalDB().get(getKeyDailyPaperDownloadFileIsOpen(),
            true);
    public Supplier<String> DailyPaperDownloadUrlWindows = () -> getConfigConfig().getLocalDB().get(getKeyDailyPaperDownloadUrlWindows(),
            "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/DailyPaper.exe").toString();

    public Supplier<Integer> DailyPaperStageWidth = () -> (Integer) getConfigConfig().getLocalDB().get(getKeyDailyPaperStageWidth(),
            1200);
    public Supplier<Integer> DailyPaperStageHeight = () -> (Integer) getConfigConfig().getLocalDB().get(getKeyDailyPaperStageHeight(),
            600);

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

    @Override
    public void launch(String[] args) {
        ///  自动检测更新
        if (!DailyPaper.getInstance().getConfig().getRenewConfig().getLocalDB().get("VERSION").equals(DailyPaper.getInstance().getVERSION())) {
            if (DailyPaper.getInstance().getComputerSystem().getHasNetwork()) {
                if (DailyPaper.getInstance().getComputerSystem().getSystemName().toLowerCase().contains("windows")) {
                    System.exit(0);
                    DailyPaper.getInstance().getComputerSystem().runCommand(
                            DailyPaper.getInstance().getDailyPaperRenewFilePath()+File.separator+DailyPaper.getInstance().getDailyPaperRenewFileNameWindows()
                                    +" "+DailyPaper.getInstance().getDailyPaperDownloadUrlWindows()
                                    +" "+DailyPaper.getInstance().getDailyPaperDownloadFilePath()+File.separator+DailyPaper.getInstance().getDailyPaperDownloadFileNameWindows()
                                    +" "+DailyPaper.getInstance().getDailyPaperDownloadFileIsOpen());
                }
            }
        }
        Application.launch(DailyPaperApplication.class, args);
    }

    @Override
    public AIAgent getAIAgent() {
        return AIAgent.getInstance();
    }

    @Override
    public AIAgentSiliconFlow getAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow();
    }

    @Override
    public TextToTextAIAgentSiliconFlow getTextToTextAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow().getTextToTextAIAgentSiliconFlow();
    }

    @Override
    public TextToImageAIAgentSiliconFlow getTextToImageAIAgentSiliconFlow() {
        return getAIAgent().getAIAgentSiliconFlow().getTextToImageAIAgentSiliconFlow();
    }

    @Override
    public NetworkSystem getNetworkSystem() {
        return NetworkSystem.getInstance();
    }

    @Override
    public ComputerSystem getComputerSystem() {
        return ComputerSystem.getInstance();
    }

    @Override
    public FileSystem getFileSystem() {
        return FileSystem.getInstance();
    }

    @Override
    public MathSystem getMathSystem() {
        return MathSystem.getInstance();
    }

    @Override
    public ImageSystem getImageSystem() {
        return ImageSystem.getInstance();
    }

    @Override
    public PictureSystem getPictureSystem() {
        return PictureSystem.getInstance();
    }

    @Override
    public GIFSystem getGIFSystem() {
        return GIFSystem.getInstance();
    }

    @Override
    public Config getConfig() {
        return Config.getInstance();
    }

    @Override
    public LogConfig getLogConfig() {
        return getConfig().getLogConfig();
    }

    @Override
    public TempConfig getTempConfig() {
        return getConfig().getTempConfig();
    }

    @Override
    public ConfigConfig getConfigConfig() {
        return getConfig().getConfigConfig();
    }

    @Override
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