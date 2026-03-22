package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import java.io.Closeable;
import java.util.function.Supplier;
import java.util.concurrent.Executors;
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaper;

@Data
public class DailyPaper implements IDailyPaper, Closeable {

    /**
     * DailyPaper 版本参数
     * */
    private final String NAME = "DailyPaper";
    private final String VERSION = "1.1";

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaper INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaper();
        }
        return INSTANCE;
    }

    /**
     * DailyPaper 参值
     * */
    private final ExecutorService dailyPaperPool = Executors.newFixedThreadPool(64);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    /**
     * Parm 参键
     * */

    /// 大小参数
    private String KeyDailyPaperStageWidth = "DailyPaperStageWidth";
    private String KeyDailyPaperStageHeight = "DailyPaperStageHeight";

    /// 速度参数
    private String KeyLiveImagePlaySpeed = "LiveImagePlaySpeed";

    /**
     * Setting 参键
     * */

    /// 文件参数
    private String KeyCurrentFilePath = "CurrentFilePath";
    private String KeyDailyPaperFilePath = "DailyPaperFilePath";
    private String KeyConfigFilePath = "ConfigFilePath";
    private String KeyTempFilePath = "TempFilePath";
    private String KeyJavaFilePath = "JavaFilePath";
    private String KeyLibFilePath = "LibFilePath";
    private String KeyLiveImageFilePath = "LiveImageFilePath";

    private String KeyLiveImageConfigFileName = "LiveImageConfigFileName";
    private String KeyParamConfigFileName = "ParamConfigFileName";
    private String KeySettingConfigFileName = "SettingConfigFileName";
    private String KeyRenewConfigFileName = "RenewConfigFileName";

    private String KeyTempImageFileName = "TempImageFileName";

    /// web 请求参数
    private String KeyConnectTimeout = "ConnectTimeout";
    private String KeyReadTimeout = "ReadTimeout";
    private String KeyWriteTimeout = "WriteTimeout";

    /// AI 参数
    private String KeyAPIKeySiliconFlow = "APIKeySiliconFlow";

    private String KeyKolorsModelSiliconFlow = "KolorsAPIModelSiliconFlow";
    private String KeyQwen30_8BModelSiliconFlow = "Qwen30_8BAPIModelSiliconFlow";
    private String KeyQwen35_4BModelSiliconFlow = "Qwen35_4BAPIModelSiliconFlow";
    private String KeyGLM_41V_9B_ThinkingModelSiliconFlow = "GLM_41V_9B_ThinkingModelSiliconFlow";

    private String KeyAPIUrlTextToTextSiliconFlow = "APIUrlTextToTextSiliconFlow";
    private String KeyAPIUrlTextToImageSiliconFlow = "APIUrlTextToImageSiliconFlow";
    private String KeyAPIUrlImageToTextSiliconFlow = "APIUrlImageToTextSiliconFlow";

    private String KeyAPIModelTextToTextSiliconFlow = "APIModelTextToTextSiliconFlow";
    private String KeyAPIModelTextToImageSiliconFlow = "APIModelTextToImageSiliconFlow";
    private String KeyAPIModelImageToTextSiliconFlow = "APIModelImageToTextSiliconFlow";

    /// AI 生成参数
    private String KeySpawnImageSize = "SpawnImageSize";
    private String KeySpawnImageCount = "SpawnImageCount";
    private String KeySpawnMaxTokens = "SpawnMaxTokens";

    /// 更新参数
    private String KeyRenewConfigRenewUriWindows = "RenewConfigRenewUriWindows";
    private String KeyRenewConfigRenewUriLinux = "RenewConfigRenewUriLinux";
    private String KeyRenewConfigRenewUriMac = "RenewConfigRenewUriMac";
    private String KeyRenewConfigRenewUriAndroid = "RenewConfigRenewUriAndroid";
    private String KeyRenewConfigRenewUri = "RenewConfigRenewUri";
    private String KeyRenewRenewLocalFilePath = "RenewRenewLocalFilePath";
    private String KeyRenewRenewLocalFileName = "RenewRenewLocalFileName";
    private String KeyDailyPaperRenewLocalFilePath = "DailyPaperRenewLocalFilePath";
    private String KeyDailyPaperRenewLocalFileName = "DailyPaperRenewLocalFileName";
    private String KeyDailyPaperRenewUriWindows = "DailyPaperRenewUriWindows";
    private String KeyDailyPaperRenewUriLinux = "DailyPaperRenewUriLinux";
    private String KeyDailyPaperRenewUriMac = "DailyPaperRenewUriMac";
    private String KeyDailyPaperRenewUriAndroid = "DailyPaperRenewUriAndroid";
    private String KeyDailyPaperRenewUri = "DailyPaperRenewUri";
    private String KeyDailyPaperRenewAutoOpen = "DailyPaperRenewAutoOpen";

    /// 记忆参数
    private String KeyChatImageDownloadFilePath = "ChatImageDownloadFilePath";
    private String KeyChatImageDownloadFileName = "ChatImageDownloadFileName";

    private String KeyLiveImageChosenFilePath = "LiveImageChosenFilePath";
    private String KeyLiveImageChosenFileName = "LiveImageChosenFileName";
    private String KeyLiveImageAutoLaunch = "LiveImageAutoLaunch";

    private String KeyDailyAutoWallpaper = "AutoWallpaper";
    private String KeyDailyAutoWallpaperWhenLaunch = "DailyAutoWallpaperWhenLaunch";
    private String KeyDailyAutoWallpaperWhenTime = "DailyAutoWallpaperWhenTime";
    private String KeyDailyImageHobby = "DailyImageHobby";

    private String KeyRenewAutoCheckRenew = "RenewAutoCheckRenew";
    private String KeyRenewAutoDownloadRenew = "RenewAutoDownloadRenew";

    private String KeyDailyPaperAutoLaunch = "DailyPaperAutoLaunch";

    /// 标记参数
    private String KeySplitMark = "SplitMark";

    /**
     * Param 参值
     * */

    /// 大小参数
    private Supplier<Integer> DailyPaperStageWidth = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperStageWidth(), 1200)));
    private Supplier<Integer> DailyPaperStageHeight = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperStageHeight(), 700)));

    /// 速度参数
    private Supplier<Integer> LiveImagePlaySpeed = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImagePlaySpeed(), 10)));

    /**
     * Setting 参值
     * */

    /// 文件参数
    private Supplier<String> CurrentFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyCurrentFilePath(), ComputerSystem.getInstance().getHere()).toString();
    private Supplier<String> DailyPaperFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperFilePath(), getCurrentFilePath().get() + File.separator + "DailyPaper").toString();
    private Supplier<String> ConfigFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyConfigFilePath(), DailyPaperFilePath.get()).toString();
    private Supplier<String> TempFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyTempFilePath(), DailyPaperFilePath.get()).toString();
    private Supplier<String> JavaFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyJavaFilePath(), DailyPaperFilePath.get() + File.separator + "jdk-21.0.8").toString();
    private Supplier<String> LibFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyLibFilePath(), DailyPaperFilePath.get() + File.separator + "lib").toString();
    private Supplier<String> LiveImageFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageFilePath(), DailyPaperFilePath.get() + File.separator + "LiveImage").toString();
    private Supplier<String> LiveImageConfigFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageConfigFileName(), "liveImage.config").toString();
    private Supplier<String> ParamConfigFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyParamConfigFileName(), "param.config").toString();
    private Supplier<String> SettingConfigFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeySettingConfigFileName(), "setting.config").toString();
    private Supplier<String> RenewConfigFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigFileName(), "renew.config").toString();

    private Supplier<String> TempImageFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyTempImageFileName(), "image.png").toString();

    /// web 请求参数
    private Supplier<Integer> ConnectTimeout = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyConnectTimeout(), 180)));
    private Supplier<Integer> ReadTimeout = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getReadTimeout(), 180)));
    private Supplier<Integer> WriteTimeout = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getWriteTimeout(), 180)));

    /// AI 参数
    private Supplier<String> APIKeySiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIKeySiliconFlow(), "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y").toString();

    private Supplier<String> KolorsModelSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyKolorsModelSiliconFlow(), "Kwai-Kolors/Kolors").toString();
    private Supplier<String> Qwen30_8BModelSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyQwen30_8BModelSiliconFlow(), "Qwen/Qwen3-8B").toString();
    private Supplier<String> Qwen35_4BModelSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyQwen35_4BModelSiliconFlow(), "Qwen/Qwen3.5-4B").toString();
    private Supplier<String> GLM_41V_9B_ThinkingModelSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyGLM_41V_9B_ThinkingModelSiliconFlow(), "THUDM/GLM-4.1V-9B-Thinking").toString();

    private Supplier<String> APIUrlTextToTextSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlTextToTextSiliconFlow(), "https://api.siliconflow.cn/v1/chat/completions").toString();
    private Supplier<String> APIUrlTextToImageSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlTextToImageSiliconFlow(), "https://api.siliconflow.cn/v1/images/generations").toString();
    private Supplier<String> APIUrlImageToTextSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlImageToTextSiliconFlow(), "https://api.siliconflow.cn/v1/chat/completions").toString();

    private Supplier<String> APIModelTextToTextSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelTextToTextSiliconFlow(), getQwen30_8BModelSiliconFlow().get()).toString();
    private Supplier<String> APIModelTextToImageSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelTextToImageSiliconFlow(), getKolorsModelSiliconFlow().get()).toString();
    private Supplier<String> APIModelImageToTextSiliconFlow = () -> SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelImageToTextSiliconFlow(), getQwen35_4BModelSiliconFlow().get()).toString();

    /// AI 生成参数
    private Supplier<String> SpawnImageSize = () -> SettingConfig.getInstance().getLocalDB().get(getKeySpawnImageSize(), ((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getWidth()))+"x"+((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getHeight()))).toString();
    private Supplier<Integer> SpawnImageCount = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeySpawnImageCount(), 1)));
    private Supplier<Integer> SpawnMaxTokens = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeySpawnMaxTokens(), 100)));

    /// 更新参数
    private Supplier<String> RenewConfigRenewUriWindows = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriWindows(), "https://raw.githubusercontent.com/BProbie/DailyPaper/refs/heads/master/" + getRenewConfigFileName().get()).toString();
    private Supplier<String> RenewConfigRenewUriLinux = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriLinux(), "https://raw.githubusercontent.com/BProbie/DailyPaper/refs/heads/master/" + getRenewConfigFileName().get()).toString();
    private Supplier<String> RenewConfigRenewUriMac = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriMac(), "https://raw.githubusercontent.com/BProbie/DailyPaper/refs/heads/master/" + getRenewConfigFileName().get()).toString();
    private Supplier<String> RenewConfigRenewUriAndroid = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriAndroid(), "https://raw.githubusercontent.com/BProbie/DailyPaper/refs/heads/master/" + getRenewConfigFileName().get()).toString();
    private Supplier<String> RenewConfigRenewUri = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUri(), ComputerSystem.getInstance().getSystemName().toLowerCase().contains("windows") ? getRenewConfigRenewUriWindows().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("linux") ? getRenewConfigRenewUriLinux().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("mac") ? getRenewConfigRenewUriMac().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("android") ? getRenewConfigRenewUriAndroid().get() : null).toString();
    private Supplier<String> DailyPaperRenewLocalFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewLocalFilePath(), getCurrentFilePath().get()).toString();
    private Supplier<String> RenewRenewLocalFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewRenewLocalFilePath(), getLibFilePath().get()).toString();
    private Supplier<String> RenewRenewLocalFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyRenewRenewLocalFileName(), "Renew.jar").toString();
    private Supplier<String> DailyPaperRenewLocalFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewLocalFileName(), getNAME() + ".exe").toString();
    private Supplier<String> DailyPaperRenewUriWindows = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriWindows(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/" + getDailyPaperRenewLocalFileName().get()).toString();
    private Supplier<String> DailyPaperRenewUriLinux = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriLinux(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/" + getDailyPaperRenewLocalFileName().get()).toString();
    private Supplier<String> DailyPaperRenewUriMac = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriMac(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/" + getDailyPaperRenewLocalFileName().get()).toString();
    private Supplier<String> DailyPaperRenewUriAndroid = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriAndroid(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master/" + getDailyPaperRenewLocalFileName().get()).toString();
    private Supplier<String> DailyPaperRenewUri = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUri(), ComputerSystem.getInstance().getSystemName().toLowerCase().contains("windows") ? getDailyPaperRenewUriWindows().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("linux") ? getDailyPaperRenewUriLinux() :  ComputerSystem.getInstance().getSystemName().toLowerCase().contains("mac") ? getDailyPaperRenewUriMac() :  ComputerSystem.getInstance().getSystemName().toLowerCase().contains("android") ? getDailyPaperRenewUriAndroid() : null).toString();
    private Supplier<Boolean> DailyPaperRenewAutoOpen = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewAutoOpen(), true)));

    /// 记忆参数
    private Supplier<String> ChatImageDownloadFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyChatImageDownloadFilePath(), getCurrentFilePath().get()).toString();
    private Supplier<String> ChatImageDownloadFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyChatImageDownloadFileName(), "image.png").toString();

    private Supplier<String> LiveImageChosenFilePath = () -> SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageChosenFilePath(), getCurrentFilePath().get()).toString();
    private Supplier<String> LiveImageChosenFileName = () -> SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageChosenFileName(), "image.png").toString();
    private Supplier<Boolean> LiveImageAutoLaunch = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageAutoLaunch(), false)));

    private Supplier<Boolean> DailyAutoWallpaper = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaper(), false)));
    private Supplier<Boolean> DailyAutoWallpaperWhenLaunch = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaperWhenLaunch(), false)));
    private Supplier<Integer> DailyAutoWallpaperWhenTime = () -> Integer.parseInt(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaperWhenTime(), 0)));
    private Supplier<String> DailyImageHobby = () -> SettingConfig.getInstance().getLocalDB().get(getKeyDailyImageHobby(), "古风唯美").toString();

    private Supplier<Boolean> RenewAutoCheckRenew = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewAutoCheckRenew(), false)));
    private Supplier<Boolean> RenewAutoDownloadRenew = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewAutoDownloadRenew(), false)));

    private Supplier<Boolean> DailyPaperAutoLaunch = () -> Boolean.parseBoolean(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperAutoLaunch(), false)));

    private Supplier<String> SplitMark = () -> SettingConfig.getInstance().getLocalDB().get(getKeySplitMark(), " ").toString();

    @Override
    public void launch(String[] args) {
        Application.launch(DailyPaperApplication.class, args);
    }

    @Override
    public void close() {

        /**
         * 保存 Param
         * */

        /// 大小参数
        ParamConfig.getInstance().getLocalDB().set(KeyDailyPaperStageWidth, DailyPaperStageWidth.get());
        ParamConfig.getInstance().getLocalDB().set(KeyDailyPaperStageHeight, DailyPaperStageHeight.get());

        /// 速度参数
        ParamConfig.getInstance().getLocalDB().set(KeyLiveImagePlaySpeed, LiveImagePlaySpeed.get());

        /**
         * 保存 Setting
         * */

        /// 文件参数
        SettingConfig.getInstance().getLocalDB().set(KeyCurrentFilePath, CurrentFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperFilePath, DailyPaperFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyConfigFilePath, ConfigFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyTempFilePath, TempFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyJavaFilePath, JavaFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLibFilePath, LibFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageFilePath, LiveImageFilePath.get());

        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageConfigFileName, LiveImageConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyParamConfigFileName, ParamConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeySettingConfigFileName, SettingConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigFileName, RenewConfigFileName.get());

        SettingConfig.getInstance().getLocalDB().set(KeyTempImageFileName, TempImageFileName.get());

        /// web 请求参数
        SettingConfig.getInstance().getLocalDB().set(KeyConnectTimeout, ConnectTimeout.get());
        SettingConfig.getInstance().getLocalDB().set(KeyReadTimeout, ReadTimeout.get());
        SettingConfig.getInstance().getLocalDB().set(KeyWriteTimeout, WriteTimeout.get());

        /// AI 参数
        SettingConfig.getInstance().getLocalDB().set(KeyAPIKeySiliconFlow, APIKeySiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyKolorsModelSiliconFlow, KolorsModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyQwen30_8BModelSiliconFlow, Qwen30_8BModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyQwen35_4BModelSiliconFlow, Qwen35_4BModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyGLM_41V_9B_ThinkingModelSiliconFlow, GLM_41V_9B_ThinkingModelSiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlTextToTextSiliconFlow, APIUrlTextToTextSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlTextToImageSiliconFlow, APIUrlTextToImageSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlImageToTextSiliconFlow, APIUrlImageToTextSiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelTextToTextSiliconFlow, APIModelTextToTextSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelTextToImageSiliconFlow, APIModelTextToImageSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelImageToTextSiliconFlow, APIModelImageToTextSiliconFlow.get());

        /// AI 生成参数
        SettingConfig.getInstance().getLocalDB().set(KeySpawnImageSize, SpawnImageSize.get());
        SettingConfig.getInstance().getLocalDB().set(KeySpawnImageCount, SpawnImageCount.get());
        SettingConfig.getInstance().getLocalDB().set(KeySpawnMaxTokens, SpawnMaxTokens.get());

        /// 更新参数
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigRenewUriWindows, RenewConfigRenewUriWindows.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigRenewUriLinux, RenewConfigRenewUriLinux.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigRenewUriMac, RenewConfigRenewUriMac.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigRenewUriAndroid, RenewConfigRenewUriAndroid.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigRenewUri, RenewConfigRenewUri.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewRenewLocalFilePath, RenewRenewLocalFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewRenewLocalFileName, RenewRenewLocalFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewLocalFilePath, DailyPaperRenewLocalFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewLocalFileName, DailyPaperRenewLocalFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewUriWindows, DailyPaperRenewUriWindows.get());
        SettingConfig.getInstance().getLocalDB().set(KeyCurrentFilePath, CurrentFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewUriLinux, DailyPaperRenewUriLinux.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewUriMac, DailyPaperRenewUriMac.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewUriAndroid, DailyPaperRenewUriAndroid.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewUri, DailyPaperRenewUri.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperRenewAutoOpen, DailyPaperRenewAutoOpen.get());

        /// 记忆参数
        SettingConfig.getInstance().getLocalDB().set(KeyChatImageDownloadFilePath, ChatImageDownloadFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyChatImageDownloadFileName, ChatImageDownloadFileName.get());

        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageChosenFilePath, LiveImageChosenFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageChosenFileName, LiveImageChosenFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageAutoLaunch, LiveImageAutoLaunch.get());

        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaper, DailyAutoWallpaper.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaperWhenLaunch, DailyAutoWallpaperWhenLaunch.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaperWhenTime, DailyAutoWallpaperWhenTime.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyImageHobby, DailyImageHobby.get());

        SettingConfig.getInstance().getLocalDB().set(KeyRenewAutoCheckRenew, RenewAutoCheckRenew.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewAutoDownloadRenew, RenewAutoDownloadRenew.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperAutoLaunch, DailyPaperAutoLaunch.get());

        /// 标记参数
        SettingConfig.getInstance().getLocalDB().set(KeySplitMark, SplitMark.get());

        if (!new File(ConfigFilePath.get()).exists()) new File(ConfigFilePath.get()).mkdirs();

        ParamConfig.getInstance().getLocalDB().commit();
        SettingConfig.getInstance().getLocalDB().commit();

    }

}