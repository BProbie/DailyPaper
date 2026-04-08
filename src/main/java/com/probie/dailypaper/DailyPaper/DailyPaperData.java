package com.probie.dailypaper.DailyPaper;

import javafx.beans.property.SimpleObjectProperty;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;
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
    private SimpleObjectProperty<Object> promptInformationPrompt = new SimpleObjectProperty<>("你是DailyPaper日常壁纸软件应用的AI小助手小Day"
            + "\n" + "你的任何回复都不要使用Markdown语法，输出的代码也不要使用代码块格式"
            + "\n" + "现在是%d年%d月%d日%d时%d分%d秒，星期%d".formatted(ComputerSystem.getInstance().getDate(Date.YEAR), ComputerSystem.getInstance().getDate(Date.MONTH), ComputerSystem.getInstance().getDate(Date.DAY), ComputerSystem.getInstance().getDate(Date.HOUR), ComputerSystem.getInstance().getDate(Date.MINUTE), ComputerSystem.getInstance().getDate(Date.SECONDE), ComputerSystem.getInstance().getDate(Date.SUNDAY)));
    private SimpleObjectProperty<Object> promptIfImagePrompt = new SimpleObjectProperty<>("请你根据用户输入的上下文信息帮我推测判断出用户现在是否需要生成图片，是则回答单个字“是”，否则回答单个字“否”，无法推断或模糊不清则一律回答单个字“否”，以下是用户输入的上下文信息：");
    private SimpleObjectProperty<Object> promptSpawnImagePrompt = new SimpleObjectProperty<>("请你根据用户输入的上下文信息整理回复用户想生成图片的描述信息，稍微简短，尽量详备，并只需要回复要生成的图片的描述信息即可，以下是用户输入的上下文信息：");
    private SimpleObjectProperty<Object> promptSpawnImageResultPrompt = new SimpleObjectProperty<>("请你根据用户当前输入的用于生成图片提示词信息，返回该图片生成成功的回复，以下是用户输入的提示词信息：");
    private SimpleObjectProperty<Object> promptDelineateImagePrompt = new SimpleObjectProperty<>("这是什么？直接回答，稍微简短，尽量详备。");
    private SimpleObjectProperty<Object> promptSpawnDailyWallpaperPrompt = new SimpleObjectProperty<>("现在是%d年%d月%d日%d时%d分%d秒，星期%d。\n如果有的话请你根据今天的节日、节气、时节、时令、习俗、风俗、历史、文化、气氛、氛围等因素结合用户的喜爱偏好：%s。\n生成一张电脑桌面高清壁纸。".formatted(ComputerSystem.getInstance().getDate(Date.YEAR), ComputerSystem.getInstance().getDate(Date.MONTH), ComputerSystem.getInstance().getDate(Date.DAY), ComputerSystem.getInstance().getDate(Date.HOUR), ComputerSystem.getInstance().getDate(Date.MINUTE), ComputerSystem.getInstance().getDate(Date.SECONDE), ComputerSystem.getInstance().getDate(Date.SUNDAY), DailyPaper.getInstance().getDailyImageHobby().get()));
    private SimpleObjectProperty<Object> promptSpawnDailyWallpaperHobbyPrompt = new SimpleObjectProperty<>("请你结合图像内容和用户喜好，帮助用户优化用于生成图像的提示词，特别强调只需要回复要生成的图片的描述信息即可。\n*用户喜好：%s\n*图像内容：%s"); /// %s用户喜好 %s图像内容

    private ArrayList<String> chatUserMessageArrayList = new ArrayList<>();
    private ArrayList<String> chatAgentMessageArrayList = new ArrayList<>();

    private SimpleObjectProperty<Object> isLiveImageShowing = new SimpleObjectProperty<>(false);
    private SimpleObjectProperty<Object> isLiveWallpaperShowing = new SimpleObjectProperty<>(false);

    private volatile long autoDailyWallpaperStartTime = System.currentTimeMillis();
    private SimpleObjectProperty<Object> isAutoDailyWallpaperRunning = new SimpleObjectProperty<>(false);
    private final Runnable autoDailyWallpaper = () -> {
        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyAutoWallpaper().get())) && Integer.parseInt(String.valueOf(dailyPaper.getDailyAutoWallpaperWhenTime().get())) >= 1) {
            if (System.currentTimeMillis() - autoDailyWallpaperStartTime > (long) dailyPaper.getDailyAutoWallpaperWhenTime().get() * 60 * 1000) {
                dailyPaperFunction.dailyWallpaper();
                setAutoDailyWallpaperStartTime(System.currentTimeMillis());
            }
        }
    };

    private ArrayList<String> supportImageFormat = new ArrayList<>(List.of(".png", ".jpg", ".jpeg"));
    private ArrayList<String> supportLiveImageFormat = new ArrayList<>(List.of(".gif"));

    private SimpleObjectProperty<Object> nodeSizeLargeLarge = new SimpleObjectProperty<>(30);
    private SimpleObjectProperty<Object> nodeSizeLarge = new SimpleObjectProperty<>(25);
    private SimpleObjectProperty<Object> nodeSizeMedium = new SimpleObjectProperty<>(20);
    private SimpleObjectProperty<Object> nodeSizeSmall = new SimpleObjectProperty<>(15);
    private SimpleObjectProperty<Object> nodeSizeSmallSmall = new SimpleObjectProperty<>(10);

    private SimpleObjectProperty<Object> nodeWidthLargeLarge = new SimpleObjectProperty<>(100);
    private SimpleObjectProperty<Object> nodeHeightLargeLarge = new SimpleObjectProperty<>(50);
    private SimpleObjectProperty<Object> nodeWidthLarge = new SimpleObjectProperty<>(80);
    private SimpleObjectProperty<Object> nodeHeightLarge = new SimpleObjectProperty<>(40);
    private SimpleObjectProperty<Object> nodeWidthMedium = new SimpleObjectProperty<>(60);
    private SimpleObjectProperty<Object> nodeHeightMedium = new SimpleObjectProperty<>(30);
    private SimpleObjectProperty<Object> nodeWidthSmall = new SimpleObjectProperty<>(40);
    private SimpleObjectProperty<Object> nodeHeightSmall = new SimpleObjectProperty<>(20);
    private SimpleObjectProperty<Object> nodeWidthSmallSmall = new SimpleObjectProperty<>(20);
    private SimpleObjectProperty<Object> nodeHeightSmallSmall = new SimpleObjectProperty<>(10);

    private SimpleObjectProperty<Object> spacingSizeLargeLarge = new SimpleObjectProperty<>(30);
    private SimpleObjectProperty<Object> spacingSizeLarge = new SimpleObjectProperty<>(25);
    private SimpleObjectProperty<Object> spacingSizeMedium = new SimpleObjectProperty<>(20);
    private SimpleObjectProperty<Object> spacingSizeSmall = new SimpleObjectProperty<>(15);
    private SimpleObjectProperty<Object> spacingSizeSmallSmall = new SimpleObjectProperty<>(10);

    private SimpleObjectProperty<Object> fontSizeLargeLarge = new SimpleObjectProperty<>(30);
    private SimpleObjectProperty<Object> fontSizeLarge = new SimpleObjectProperty<>(25);
    private SimpleObjectProperty<Object> fontSizeMedium = new SimpleObjectProperty<>(20);
    private SimpleObjectProperty<Object> fontSizeSmall = new SimpleObjectProperty<>(15);
    private SimpleObjectProperty<Object> fontSizeSmallSmall = new SimpleObjectProperty<>(10);

    private SimpleObjectProperty<Object> delay = new SimpleObjectProperty<>(10);
    private SimpleObjectProperty<Object> offset = new SimpleObjectProperty<>(10);

}