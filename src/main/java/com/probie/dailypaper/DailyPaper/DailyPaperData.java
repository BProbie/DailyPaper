package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.util.ArrayList;
import java.util.function.Supplier;
import com.probie.dailypaper.Enum.Date;
import com.probie.dailypaper.System.ComputerSystem;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperData;

@Data
public class DailyPaperData implements IDailyPaperData {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperData INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperData();
        }
        return INSTANCE;
    }

    /**
     * 工具类单例对象
     * */
    private volatile static DailyPaper dailyPaper = DailyPaper.getInstance();
    private volatile static DailyPaperApplication dailyPaperApplication = DailyPaperApplication.getINSTANCE();
    private volatile static DailyPaperElement dailyPaperElement = DailyPaperElement.getInstance();
    private volatile static DailyPaperStyle dailyPaperStyle = DailyPaperStyle.getInstance();
    private volatile static DailyPaperEvent dailyPaperEvent = DailyPaperEvent.getInstance();
    private volatile static DailyPaperData dailyPaperData = DailyPaperData.getInstance();
    private volatile static DailyPaperFunction dailyPaperFunction = DailyPaperFunction.getInstance();

    /**
     * 参数
     * */
    private Supplier<String> promptInformationPrompt = () -> "你是DailyPaper日常壁纸软件应用的AI小助手小Day"
            + "\n" + "你的任何回复都不要使用Markdown语法，输出的代码也不要使用代码块格式"
            + "\n" + "现在是%d年%d月%d日%d时%d分%d秒，星期%d".formatted(ComputerSystem.getInstance().getDate(Date.YEAR), ComputerSystem.getInstance().getDate(Date.MONTH), ComputerSystem.getInstance().getDate(Date.DAY), ComputerSystem.getInstance().getDate(Date.HOUR), ComputerSystem.getInstance().getDate(Date.MINUTE), ComputerSystem.getInstance().getDate(Date.SECONDE), ComputerSystem.getInstance().getDate(Date.SUNDAY));
    private Supplier<String> promptIfImagePrompt = () -> "请你根据用户输入的上下文信息帮我推测判断出用户现在是否需要生成图片，是则回答单个字“是”，否则回答单个字“否”，无法推断或模糊不清则一律回答单个字“否”，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImagePrompt = () -> "请你根据用户输入的上下文信息整理回复用户想生成图片的描述信息，稍微简短，尽量详备，并只需要回复要生成的图片的描述信息即可，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImageResultPrompt = () -> "请你根据用户输入的提示词信息，返回该图片生成成功的回复，以下是用户输入的提示词信息：";
    private Supplier<String> promptSpawnDailyWallpaperPrompt = () -> "现在是%d年%d月%d日%d时%d分%d秒，星期%d。\n如果有请你根据今天的节日、节气、时节、时令、习俗、风俗、历史、文化、气氛、氛围等因素结合用户的喜爱偏好：%s。\n生成一张电脑桌面高清壁纸。".formatted(ComputerSystem.getInstance().getDate(Date.YEAR), ComputerSystem.getInstance().getDate(Date.MONTH), ComputerSystem.getInstance().getDate(Date.DAY), ComputerSystem.getInstance().getDate(Date.HOUR), ComputerSystem.getInstance().getDate(Date.MINUTE), ComputerSystem.getInstance().getDate(Date.SECONDE), ComputerSystem.getInstance().getDate(Date.SUNDAY), DailyPaper.getInstance().getDailyImageHobby().get());

    private ArrayList<String> chatUserMessageArrayList = new ArrayList<>();
    private ArrayList<String> chatAgentMessageArrayList = new ArrayList<>();

    private Supplier<Boolean> isLiveImageShowing = () -> false;
    private Supplier<Boolean> isLiveWallpaperShowing = () -> false;

    private volatile long autoDailyWallpaperStartTime = System.currentTimeMillis();
    private Supplier<Boolean> isAutoDailyWallpaperRunning = () -> false;
    private final Runnable autoDailyWallpaper = () -> {
        if (dailyPaper.getDailyAutoWallpaper().get() && dailyPaper.getDailyAutoWallpaperWhenTime().get() >= 1) {
            if (System.currentTimeMillis() - autoDailyWallpaperStartTime > (long) dailyPaper.getDailyAutoWallpaperWhenTime().get() * 60 * 1000) {
                dailyPaperFunction.launchDailyWallpaper();
                setAutoDailyWallpaperStartTime(System.currentTimeMillis());
            }
        }
    };

    private Supplier<Integer> nodeSizeLargeLarge = () -> 30;
    private Supplier<Integer> nodeSizeLarge = () -> 25;
    private Supplier<Integer> nodeSizeMedium = () -> 20;
    private Supplier<Integer> nodeSizeSmall = () -> 15;
    private Supplier<Integer> nodeSizeSmallSmall = () -> 10;

    private Supplier<Integer> nodeWidthLargeLarge = () -> 120;
    private Supplier<Integer> nodeHeightLargeLarge = () -> 60;
    private Supplier<Integer> nodeWidthLarge = () -> 100;
    private Supplier<Integer> nodeHeightLarge = () -> 50;
    private Supplier<Integer> nodeWidthMedium = () -> 80;
    private Supplier<Integer> nodeHeightMedium = () -> 40;
    private Supplier<Integer> nodeWidthSmall = () -> 60;
    private Supplier<Integer> nodeHeightSmall = () -> 30;
    private Supplier<Integer> nodeWidthSmallSmall = () -> 40;
    private Supplier<Integer> nodeHeightSmallSmall = () -> 20;

    private Supplier<Integer> spacingSizeLargeLarge = () -> 25;
    private Supplier<Integer> spacingSizeLarge = () -> 20;
    private Supplier<Integer> spacingSizeMedium = () -> 15;
    private Supplier<Integer> spacingSizeSmall = () -> 10;
    private Supplier<Integer> spacingSizeSmallSmall = () -> 5;

    private Supplier<Integer> fontSizeLargeLarge = () -> 25;
    private Supplier<Integer> fontSizeLarge = () -> 20;
    private Supplier<Integer> fontSizeMedium = () -> 15;
    private Supplier<Integer> fontSizeSmall = () -> 10;
    private Supplier<Integer> fontSizeSmallSmall = () -> 5;

    private Supplier<Integer> delay = () -> 10;
    private Supplier<Integer> offset = () -> 10;

}