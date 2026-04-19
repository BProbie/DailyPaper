package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import java.io.Closeable;
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.System.*;
import java.util.concurrent.Executors;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import javafx.beans.property.SimpleObjectProperty;
import java.util.concurrent.ScheduledExecutorService;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaper;

@Data
public class DailyPaper implements IDailyPaper, Closeable {

    /**
     * DailyPaper 版本参数
     * */
    private final String NAME = "DailyPaper";
    private final String VERSION = "1.6";

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

    /// AI 生成参数
    private String KeySpawnImageSize = "SpawnImageSize";
    private String KeySpawnImageCount = "SpawnImageCount";
    private String KeySpawnMaxTokens = "SpawnMaxTokens";

    /// 标记参数
    private String KeyUploadImageFullFilePathMark = "UploadImageFullFilePathMark";
    private String KeySplitMark = "SplitMark";

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
    private String KeyLogConfigFilePath = "LogConfigFilePath";

    private String KeyLiveImageConfigFileName = "LiveImageConfigFileName";
    private String KeyParamConfigFileName = "ParamConfigFileName";
    private String KeySettingConfigFileName = "SettingConfigFileName";
    private String KeyRenewConfigFileName = "RenewConfigFileName";
    private String KeyLogConfigFileName = "LogConfigFileName";
    private String KeyLogDebugConfigFileName = "LogDebugConfigFileName";
    private String KeyLogInfoConfigFileName = "LogInfoConfigFileName";
    private String KeyLogWarnConfigFileName = "LogWarnConfigFileName";
    private String KeyLogErrorConfigFileName = "LogErrorConfigFileName";

    private String KeyTempImageFileName = "TempImageFileName";

    /// web 请求参数
    private String KeyConnectTimeout = "ConnectTimeout";
    private String KeyReadTimeout = "ReadTimeout";
    private String KeyWriteTimeout = "WriteTimeout";

    /// AI 参数
    private String KeyAPIKeySiliconFlow = "APIKeySiliconFlow";

    private String KeyQwen30_8BModelSiliconFlow = "Qwen30_8BAPIModelSiliconFlow";
    private String KeyKolorsModelSiliconFlow = "KolorsAPIModelSiliconFlow";
    private String KeyQwen35_4BModelSiliconFlow = "Qwen35_4BAPIModelSiliconFlow";
    private String KeyGLM_41V_9B_ThinkingModelSiliconFlow = "GLM_41V_9B_ThinkingAPIModelSiliconFlow";

    private String KeyAPIModelTextToTextSiliconFlow = "APIModelTextToTextSiliconFlow";
    private String KeyAPIModelTextToImageSiliconFlow = "APIModelTextToImageSiliconFlow";
    private String KeyAPIModelImageToTextSiliconFlow = "APIModelImageToTextSiliconFlow";

    private String KeyAPIModelTextSiliconFlowAnalysis = "APIModelTextSiliconFlowAnalysis";
    private String KeyAPIModelImageSiliconFlowAnalysis = "APIModelImageSiliconFlowAnalysis";

    private String KeyAPIUrlTextToTextSiliconFlow = "APIUrlTextToTextSiliconFlow";
    private String KeyAPIUrlTextToImageSiliconFlow = "APIUrlTextToImageSiliconFlow";
    private String KeyAPIUrlImageToTextSiliconFlow = "APIUrlImageToTextSiliconFlow";

    private String KeyAPIUrlTextSiliconFlowAnalysis = "APIUrlTextSiliconFlowAnalysis";
    private String KeyAPIUrlImageSiliconFlowAnalysis = "APIUrlImageSiliconFlowAnalysis";

    /// 更新参数
    private String KeyGithubRenewUri = "GithubRenewUri";
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
    private String KeyChatTextInputToolsImageUploadImageChosenFilePath = "ChatTextInputToolsImageUploadImageChosenFilePath";
    private String KeyChatTextInputToolsImageUploadImageChosenFileName = "ChatTextInputToolsImageUploadImageChosenFileName";
    private String KeyDailyWallpaperHobbyToolsImageUploadImageChosenFilePath = "KeyDailyWallpaperHobbyToolsImageUploadImageChosenFilePath";
    private String KeyDailyWallpaperHobbyToolsImageUploadImageChosenFileName = "KeyDailyWallpaperHobbyToolsImageUploadImageChosenFileName";

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

    /// 测试参数
    private String KeyDebug = "Debug";

    /**
     * Param 参值
     * */
    /// 大小参数
    private SimpleObjectProperty<Object> DailyPaperStageWidth = new SimpleObjectProperty<>(ParamConfig.getInstance().getLocalDB().get(getKeyDailyPaperStageWidth(), 1200));
    private SimpleObjectProperty<Object> DailyPaperStageHeight = new SimpleObjectProperty<>(ParamConfig.getInstance().getLocalDB().get(getKeyDailyPaperStageHeight(), 700));

    /// 速度参数
    private SimpleObjectProperty<Object> LiveImagePlaySpeed = new SimpleObjectProperty<>(ParamConfig.getInstance().getLocalDB().get(getKeyLiveImagePlaySpeed(), 10));

    /// AI 生成参数
    private SimpleObjectProperty<Object> SpawnImageSize = new SimpleObjectProperty<>(String.valueOf(ParamConfig.getInstance().getLocalDB().get(getKeySpawnImageSize(), ((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getWidth()))+"x"+((int) Math.floor(MathSystem.getInstance().getFitDimension(ComputerSystem.getInstance().getDimension()).getHeight())))));
    private SimpleObjectProperty<Object> SpawnImageCount = new SimpleObjectProperty<>(ParamConfig.getInstance().getLocalDB().get(getKeySpawnImageCount(), 1));
    private SimpleObjectProperty<Object> SpawnMaxTokens = new SimpleObjectProperty<>(ParamConfig.getInstance().getLocalDB().get(getKeySpawnMaxTokens(), 100));

    /// 标记参数
    private SimpleObjectProperty<Object> UploadImageFullFilePathMark = new SimpleObjectProperty<>(String.valueOf(ParamConfig.getInstance().getLocalDB().get(getKeyUploadImageFullFilePathMark(), "#")));
    private SimpleObjectProperty<Object> SplitMark = new SimpleObjectProperty<>(String.valueOf(ParamConfig.getInstance().getLocalDB().get(getKeySplitMark(), " ")));

    /**
     * Setting 参值
     * */
    /// 文件参数
    private SimpleObjectProperty<Object> CurrentFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyCurrentFilePath(), ComputerSystem.getInstance().getHere())));
    private SimpleObjectProperty<Object> DailyPaperFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperFilePath(), getCurrentFilePath().get() + File.separator + "DailyPaper")));
    private SimpleObjectProperty<Object> ConfigFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyConfigFilePath(), DailyPaperFilePath.get())));
    private SimpleObjectProperty<Object> TempFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyTempFilePath(), DailyPaperFilePath.get())));
    private SimpleObjectProperty<Object> JavaFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyJavaFilePath(), DailyPaperFilePath.get() + File.separator + "jdk-21.0.8")));
    private SimpleObjectProperty<Object> LibFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLibFilePath(), DailyPaperFilePath.get() + File.separator + "lib")));
    private SimpleObjectProperty<Object> LiveImageFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageFilePath(), DailyPaperFilePath.get() + File.separator + "LiveImage")));
    private SimpleObjectProperty<Object> LogConfigFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogConfigFilePath(), DailyPaperFilePath.get() + File.separator + "Log")));
    private SimpleObjectProperty<Object> LiveImageConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageConfigFileName(), "liveImage.config")));
    private SimpleObjectProperty<Object> ParamConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyParamConfigFileName(), "param.config")));
    private SimpleObjectProperty<Object> SettingConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeySettingConfigFileName(), "setting.config")));
    private SimpleObjectProperty<Object> RenewConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigFileName(), "renew.config")));
    private SimpleObjectProperty<Object> LogConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogConfigFileName(), "log.config")));
    private SimpleObjectProperty<Object> LogDebugConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogDebugConfigFileName(), "debug.log")));
    private SimpleObjectProperty<Object> LogInfoConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogInfoConfigFileName(), "info.log")));
    private SimpleObjectProperty<Object> LogWarnConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogWarnConfigFileName(), "warn.log")));
    private SimpleObjectProperty<Object> LogErrorConfigFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLogErrorConfigFileName(), "error.log")));

    private SimpleObjectProperty<Object> TempImageFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyTempImageFileName(), "image.png")));

    /// web 请求参数
    private SimpleObjectProperty<Object> ConnectTimeout = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyConnectTimeout(), 180));
    private SimpleObjectProperty<Object> ReadTimeout = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyReadTimeout(), 180));
    private SimpleObjectProperty<Object> WriteTimeout = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyWriteTimeout(), 180));

    /// AI 参数
    private SimpleObjectProperty<Object> APIKeySiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIKeySiliconFlow(), "65$!(4f9(t^!Q854Q5h!t95Q75hEO(R-7RhZ(NN7h^h-NP7O8)y")));

    private SimpleObjectProperty<Object> Qwen30_8BModelSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyQwen30_8BModelSiliconFlow(), "Qwen/Qwen3-8B")));
    private SimpleObjectProperty<Object> KolorsModelSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyKolorsModelSiliconFlow(), "Kwai-Kolors/Kolors")));
    private SimpleObjectProperty<Object> Qwen35_4BModelSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyQwen35_4BModelSiliconFlow(), "Qwen/Qwen3.5-4B")));
    private SimpleObjectProperty<Object> GLM_41V_9B_ThinkingModelSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyGLM_41V_9B_ThinkingModelSiliconFlow(), "THUDM/GLM-4.1V-9B-Thinking")));

    private SimpleObjectProperty<Object> APIModelTextToTextSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelTextToTextSiliconFlow(), getQwen30_8BModelSiliconFlow().get())));
    private SimpleObjectProperty<Object> APIModelTextToImageSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelTextToImageSiliconFlow(), getKolorsModelSiliconFlow().get())));
    private SimpleObjectProperty<Object> APIModelImageToTextSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelImageToTextSiliconFlow(), getQwen35_4BModelSiliconFlow().get())));

    private SimpleObjectProperty<Object> APIModelTextSiliconFlowAnalysis = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelTextSiliconFlowAnalysis(), String.valueOf(getAPIModelTextToTextSiliconFlow().get()))));
    private SimpleObjectProperty<Object> APIModelImageSiliconFlowAnalysis = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIModelImageSiliconFlowAnalysis(), String.valueOf(getAPIModelImageToTextSiliconFlow().get()))));

    private SimpleObjectProperty<Object> APIUrlTextToTextSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlTextToTextSiliconFlow(), "https://api.siliconflow.cn/v1/chat/completions")));
    private SimpleObjectProperty<Object> APIUrlTextToImageSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlTextToImageSiliconFlow(), "https://api.siliconflow.cn/v1/images/generations")));
    private SimpleObjectProperty<Object> APIUrlImageToTextSiliconFlow = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlImageToTextSiliconFlow(), "https://api.siliconflow.cn/v1/chat/completions")));

    private SimpleObjectProperty<Object> APIUrlTextSiliconFlowAnalysis = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlTextSiliconFlowAnalysis(), String.valueOf(getAPIUrlTextToTextSiliconFlow().get()))));
    private SimpleObjectProperty<Object> APIUrlImageSiliconFlowAnalysis = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyAPIUrlImageSiliconFlowAnalysis(), String.valueOf(getAPIUrlImageToTextSiliconFlow().get()))));

    /// 更新参数
    private SimpleObjectProperty<Object> GithubRenewUri = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyGithubRenewUri(), "https://github.com/BProbie/DailyPaper/raw/refs/heads/master" + "/" + "res" + "/")));
    private SimpleObjectProperty<Object> RenewConfigRenewUriWindows = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriWindows(), String.valueOf(getGithubRenewUri().get()) + getRenewConfigFileName().get())));
    private SimpleObjectProperty<Object> RenewConfigRenewUriLinux = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriLinux(), String.valueOf(getGithubRenewUri().get()) + getRenewConfigFileName().get())));
    private SimpleObjectProperty<Object> RenewConfigRenewUriMac = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriMac(), String.valueOf(getGithubRenewUri().get()) + getRenewConfigFileName().get())));
    private SimpleObjectProperty<Object> RenewConfigRenewUriAndroid = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUriAndroid(), String.valueOf(getGithubRenewUri().get()) + getRenewConfigFileName().get())));
    private SimpleObjectProperty<Object> RenewConfigRenewUri = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewConfigRenewUri(), ComputerSystem.getInstance().getSystemName().toLowerCase().contains("windows") ? getRenewConfigRenewUriWindows().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("linux") ? getRenewConfigRenewUriLinux().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("mac") ? getRenewConfigRenewUriMac().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("android") ? getRenewConfigRenewUriAndroid().get() : null)));
    private SimpleObjectProperty<Object> RenewRenewLocalFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewRenewLocalFilePath(), getLibFilePath().get())));
    private SimpleObjectProperty<Object> RenewRenewLocalFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyRenewRenewLocalFileName(), "Renew.jar")));
    private SimpleObjectProperty<Object> DailyPaperRenewLocalFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewLocalFilePath(), getCurrentFilePath().get())));
    private SimpleObjectProperty<Object> DailyPaperRenewLocalFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewLocalFileName(), getNAME() + ".exe")));
    private SimpleObjectProperty<Object> DailyPaperRenewUriWindows = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriWindows(), String.valueOf(getGithubRenewUri().get()) + getDailyPaperRenewLocalFileName().get())));
    private SimpleObjectProperty<Object> DailyPaperRenewUriLinux = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriLinux(), String.valueOf(getGithubRenewUri().get()) + getDailyPaperRenewLocalFileName().get())));
    private SimpleObjectProperty<Object> DailyPaperRenewUriMac = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriMac(), String.valueOf(getGithubRenewUri().get()) + getDailyPaperRenewLocalFileName().get())));
    private SimpleObjectProperty<Object> DailyPaperRenewUriAndroid = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUriAndroid(), String.valueOf(getGithubRenewUri().get()) + getDailyPaperRenewLocalFileName().get())));
    private SimpleObjectProperty<Object> DailyPaperRenewUri = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewUri(), ComputerSystem.getInstance().getSystemName().toLowerCase().contains("windows") ? getDailyPaperRenewUriWindows().get() : ComputerSystem.getInstance().getSystemName().toLowerCase().contains("linux") ? getDailyPaperRenewUriLinux() :  ComputerSystem.getInstance().getSystemName().toLowerCase().contains("mac") ? getDailyPaperRenewUriMac() :  ComputerSystem.getInstance().getSystemName().toLowerCase().contains("android") ? getDailyPaperRenewUriAndroid() : null)));
    private SimpleObjectProperty<Object> DailyPaperRenewAutoOpen = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperRenewAutoOpen(), true));

    /// 记忆参数
    private SimpleObjectProperty<Object> ChatImageDownloadFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyChatImageDownloadFilePath(), getCurrentFilePath().get())));
    private SimpleObjectProperty<Object> ChatImageDownloadFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyChatImageDownloadFileName(), "image.png")));
    private SimpleObjectProperty<Object> ChatTextInputToolsImageUploadImageChosenFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyChatTextInputToolsImageUploadImageChosenFilePath(), getCurrentFilePath().get())));
    private SimpleObjectProperty<Object> ChatTextInputToolsImageUploadImageChosenFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyChatTextInputToolsImageUploadImageChosenFileName(), "image.png")));

    private SimpleObjectProperty<Object> LiveImageChosenFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageChosenFilePath(), getCurrentFilePath().get())));
    private SimpleObjectProperty<Object> LiveImageChosenFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageChosenFileName(), "image.png")));
    private SimpleObjectProperty<Object> LiveImageAutoLaunch = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyLiveImageAutoLaunch(), false));

    private SimpleObjectProperty<Object> DailyAutoWallpaper = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaper(), false));
    private SimpleObjectProperty<Object> DailyAutoWallpaperWhenLaunch = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaperWhenLaunch(), false));
    private SimpleObjectProperty<Object> DailyAutoWallpaperWhenTime = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDailyAutoWallpaperWhenTime(), 0));
    private SimpleObjectProperty<Object> DailyImageHobby = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyImageHobby(), "古风唯美")));
    private SimpleObjectProperty<Object> DailyWallpaperHobbyToolsImageUploadImageChosenFilePath = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyWallpaperHobbyToolsImageUploadImageChosenFilePath(), getCurrentFilePath().get())));
    private SimpleObjectProperty<Object> DailyWallpaperHobbyToolsImageUploadImageChosenFileName = new SimpleObjectProperty<>(String.valueOf(SettingConfig.getInstance().getLocalDB().get(getKeyDailyWallpaperHobbyToolsImageUploadImageChosenFileName(), "image.png")));

    private SimpleObjectProperty<Object> RenewAutoCheckRenew = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyRenewAutoCheckRenew(), false));
    private SimpleObjectProperty<Object> RenewAutoDownloadRenew = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyRenewAutoDownloadRenew(), false));

    private SimpleObjectProperty<Object> DailyPaperAutoLaunch = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDailyPaperAutoLaunch(), false));

    /// 测试参数
    private SimpleObjectProperty<Object> Debug = new SimpleObjectProperty<>(SettingConfig.getInstance().getLocalDB().get(getKeyDebug(), false));

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

        /// AI 生成参数
        ParamConfig.getInstance().getLocalDB().set(KeySpawnImageSize, SpawnImageSize.get());
        ParamConfig.getInstance().getLocalDB().set(KeySpawnImageCount, SpawnImageCount.get());
        ParamConfig.getInstance().getLocalDB().set(KeySpawnMaxTokens, SpawnMaxTokens.get());

        /// 标记参数
        ParamConfig.getInstance().getLocalDB().set(KeyUploadImageFullFilePathMark, UploadImageFullFilePathMark.get());
        ParamConfig.getInstance().getLocalDB().set(KeySplitMark, SplitMark.get());

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
        SettingConfig.getInstance().getLocalDB().set(KeyLogConfigFilePath, LogConfigFilePath.get());

        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageConfigFileName, LiveImageConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyParamConfigFileName, ParamConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeySettingConfigFileName, SettingConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewConfigFileName, RenewConfigFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLogConfigFileName, LogConfigFileName.get());

        SettingConfig.getInstance().getLocalDB().set(KeyTempImageFileName, TempImageFileName.get());

        /// web 请求参数
        SettingConfig.getInstance().getLocalDB().set(KeyConnectTimeout, ConnectTimeout.get());
        SettingConfig.getInstance().getLocalDB().set(KeyReadTimeout, ReadTimeout.get());
        SettingConfig.getInstance().getLocalDB().set(KeyWriteTimeout, WriteTimeout.get());

        /// AI 参数
        SettingConfig.getInstance().getLocalDB().set(KeyAPIKeySiliconFlow, APIKeySiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyQwen30_8BModelSiliconFlow, Qwen30_8BModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyKolorsModelSiliconFlow, KolorsModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyQwen35_4BModelSiliconFlow, Qwen35_4BModelSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyGLM_41V_9B_ThinkingModelSiliconFlow, GLM_41V_9B_ThinkingModelSiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelTextToTextSiliconFlow, APIModelTextToTextSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelTextToImageSiliconFlow, APIModelTextToImageSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelImageToTextSiliconFlow, APIModelImageToTextSiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelTextSiliconFlowAnalysis, APIModelTextSiliconFlowAnalysis.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIModelImageSiliconFlowAnalysis, APIModelImageSiliconFlowAnalysis.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlTextToTextSiliconFlow, APIUrlTextToTextSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlTextToImageSiliconFlow, APIUrlTextToImageSiliconFlow.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlImageToTextSiliconFlow, APIUrlImageToTextSiliconFlow.get());

        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlTextSiliconFlowAnalysis, APIUrlTextSiliconFlowAnalysis.get());
        SettingConfig.getInstance().getLocalDB().set(KeyAPIUrlImageSiliconFlowAnalysis, APIUrlImageSiliconFlowAnalysis.get());

        /// 更新参数
        SettingConfig.getInstance().getLocalDB().set(KeyGithubRenewUri, GithubRenewUri.get());
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
        SettingConfig.getInstance().getLocalDB().set(KeyChatTextInputToolsImageUploadImageChosenFilePath, ChatTextInputToolsImageUploadImageChosenFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyChatTextInputToolsImageUploadImageChosenFileName, ChatTextInputToolsImageUploadImageChosenFileName.get());

        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageChosenFilePath, LiveImageChosenFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageChosenFileName, LiveImageChosenFileName.get());
        SettingConfig.getInstance().getLocalDB().set(KeyLiveImageAutoLaunch, LiveImageAutoLaunch.get());

        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaper, DailyAutoWallpaper.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaperWhenLaunch, DailyAutoWallpaperWhenLaunch.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyAutoWallpaperWhenTime, DailyAutoWallpaperWhenTime.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyImageHobby, DailyImageHobby.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyWallpaperHobbyToolsImageUploadImageChosenFilePath, DailyWallpaperHobbyToolsImageUploadImageChosenFilePath.get());
        SettingConfig.getInstance().getLocalDB().set(KeyDailyWallpaperHobbyToolsImageUploadImageChosenFileName, DailyWallpaperHobbyToolsImageUploadImageChosenFileName.get());

        SettingConfig.getInstance().getLocalDB().set(KeyRenewAutoCheckRenew, RenewAutoCheckRenew.get());
        SettingConfig.getInstance().getLocalDB().set(KeyRenewAutoDownloadRenew, RenewAutoDownloadRenew.get());

        SettingConfig.getInstance().getLocalDB().set(KeyDailyPaperAutoLaunch, DailyPaperAutoLaunch.get());

        SettingConfig.getInstance().getLocalDB().set(KeyDebug, Debug.get());

        if (!new File(String.valueOf(ConfigFilePath.get())).exists()) new File(String.valueOf(ConfigFilePath.get())).mkdirs();

        ParamConfig.getInstance().getLocalDB().commit();
        SettingConfig.getInstance().getLocalDB().commit();
    }

}