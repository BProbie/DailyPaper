package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.util.function.Supplier;
import javafx.scene.image.ImageView;
import java.util.concurrent.Executors;
import com.probie.dailypaper.Enum.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperElement;

@Data
public class DailyPaperElement implements IDailyPaperElement {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperElement INSTANCE;

    /**
     * 懒加载的工具类单例对象
     * */
    private volatile static DailyPaper dailyPaper;

    /**
     * Stage 舞台
     * */
    private Stage stage;

    /**
     * Pane 分页
     * */
    private BorderPane rootPane = new BorderPane();
    private VBox chatPane = new VBox();
    private VBox livePane = new VBox();
    private VBox dailyPane = new VBox();
    private VBox hobbyPane = new VBox();
    private VBox settingPane = new VBox();
    private VBox renewPane = new VBox();

    /**
     * Scene 帷幕
     * */
    private Scene scene = new Scene(rootPane);

    /**
     * RootPane 控件
     * */
    private StackPane rootPaneTopPane = new StackPane();
    private HBox rootPaneTitleBar = new HBox();
    private Pane rootPaneTitleBarButtonHolder = new Pane();
    private Button rootPaneTileBarMinButton = new Button();
    private Button rootPaneTitleBarMaxButton = new Button();
    private Button rootPaneTitleBarCloseButton = new Button();
    private StackPane rootPaneLeftPane = new StackPane();
    private VBox rootPaneMenuBar = new VBox();

    private Button rootPaneMenuBarChatButton = new Button();
    private Button rootPaneMenuBarLiveButton = new Button();
    private Button rootPaneMenuBarDailyButton = new Button();
    private Button rootPaneMenuBarHobbyButton = new Button();
    private Button rootPaneMenuBarSettingButton = new Button();
    private Button rootPaneMenuBarRenewButton = new Button();

    private StackPane rootPaneCenterPane = new StackPane();

    /**
     * ChatPane 控件
     * */
    private ScrollPane chatPaneTextShowArea = new ScrollPane();
    private TextArea chatPaneTextInputArea = new TextArea();
    private ArrayList<String> chatPaneUserMessageArrayList = new ArrayList<>();
    private ArrayList<String> chatPaneAgentMessageArrayList = new ArrayList<>();
    private VBox chatPaneMessageVBox = new VBox();

    /**
     * LivePane 控件
     * */
    private HBox livePaneImageInputHBox = new HBox();
    private Button livePaneImageChooseButton = new Button();
    private Label livePaneImageChooseLabel = new Label();
    private FileChooser livePaneImageFileChooser = new FileChooser();
    private HBox livePaneImageShowHBox = new HBox();
    private ImageView livePaneImageShowImageView = new ImageView();
    private HBox livePaneImageSureHBox = new HBox();

    /**
     * DailyPane 控件
     * */
    private VBox dailyPaneVBox = new VBox();
    private HBox dailyPaneLaunchHBox = new HBox();
    private HBox dailyPaneAutoSetWallpaperHBox = new HBox();
    private ToggleGroup dailyPaneAutoSetWallpaperGroup = new ToggleGroup();
    private Label dailyPaneAutoSetWallpaperLabel = new Label();
    private RadioButton dailyPaneAutoSetWallpaperOnButton = new RadioButton();
    private RadioButton dailyPaneAutoSetWallpaperOffButton = new RadioButton();
    private HBox dailyPaneAutoLaunchWallpaperHBox = new HBox();
    private ToggleGroup dailyPaneAutoLaunchWallpaperGroup = new ToggleGroup();
    private Label dailyPaneAutoLaunchWallpaperLabel = new Label();
    private RadioButton dailyPaneAutoLaunchWallpaperOnButton = new RadioButton();
    private RadioButton dailyPaneAutoLaunchWallpaperOffButton = new RadioButton();
    private HBox dailyPaneAutoSetWallpaperSettingHBox = new HBox();
    private HBox dailyPaneAutoSetWallpaperSettingLaunchHBox = new HBox();
    private ToggleGroup dailyPaneAutoSetWallpaperSettingLaunchGroup = new ToggleGroup();
    private Label dailyPaneAutoSetWallpaperSettingLaunchLabel = new Label();
    private RadioButton dailyPaneAutoSetWallpaperSettingLaunchOnButton = new RadioButton();
    private RadioButton dailyPaneAutoSetWallpaperSettingLaunchOffButton = new RadioButton();
    private HBox dailyPaneAutoSetWallpaperSettingDelayHBox = new HBox();
    private Label dailyPaneAutoSetWallpaperSettingDelayLabel = new Label();
    private TextField dailyPaneAutoSetWallpaperSettingDelyTextField = new TextField();
    private VBox dailyPaneHobbyVBox = new VBox();
    private VBox dailyPaneHobbyHobbyVBox = new VBox();
    private Label dailyPaneHobbyHobbyLabel = new Label();
    private ScrollPane dailyPaneHobbyHobbyScrollPane = new ScrollPane();
    private TextArea dailyPaneHobbyHobbyTextArea = new TextArea();

    /**
     * HobbyPane 控件
     * */
    private VBox hobbyPaneVBox = new VBox();
    private Label hobbyPaneInformationLabel = new Label();

    /**
     * SettingPane 控件
     * */
    private VBox settingPaneVBox = new VBox();
    private Label settingPaneInformationLabel = new Label();

    /**
     * RenewPane 控件
     * */
    private VBox renewPaneVBox = new VBox();

    private VBox renewPaneManualRenewVBox = new VBox();
    private Button renewPaneManualCheckRenewButton = new Button();
    private ScrollPane renewPaneManualCheckRenewTextShowScrollPane = new ScrollPane();
    private TextArea renewPaneManualCheckRenewTextShowArea = new TextArea();
    private Button renewPaneManualDownloadRenewButton = new Button();

    private HBox renewPaneAutoRenewHBox = new HBox();
    private HBox renewPaneAutoCheckRenewHBox = new HBox();
    private ToggleGroup renewPaneAutoCheckRenewGroup = new ToggleGroup();
    private Label renewPaneAutoCheckRenewLabel = new Label();
    private RadioButton renewPaneAutoCheckRenewOnButton = new RadioButton();
    private RadioButton renewPaneAutoCheckRenewOffButton = new RadioButton();
    private HBox renewPaneAutoDownloadRenewHBox = new HBox();
    private ToggleGroup renewPaneAutoDownloadRenewGroup = new ToggleGroup();
    private Label renewPaneAutoDownloadRenewLabel = new Label();
    private RadioButton renewPaneAutoDownloadRenewOnButton = new RadioButton();
    private RadioButton renewPaneAutoDownloadRenewOffButton = new RadioButton();

    /**
     * 动态变量
     * */
    private Supplier<String> promptInformationPrompt = () -> "你是DailyPaper日常壁纸软件应用的AI小助手小Day"
            + "\n" + "你的任何回复都不要使用Markdown语法，输出的代码也不要使用代码块格式"
            + "\n" + "现在是%d年%d月%d日%d时%d分%d秒，星期%d".formatted(dailyPaper.getComputerSystem().getDate(Date.YEAR), dailyPaper.getComputerSystem().getDate(Date.MONTH), dailyPaper.getComputerSystem().getDate(Date.DAY), dailyPaper.getComputerSystem().getDate(Date.HOUR), dailyPaper.getComputerSystem().getDate(Date.MINUTE), dailyPaper.getComputerSystem().getDate(Date.SECONDE), dailyPaper.getComputerSystem().getDate(Date.SUNDAY));
    private Supplier<String> promptIfImagePrompt = () -> "请你根据用户输入的上下文信息帮我推测判断出用户当前是否需要生成图片，是则回答单个字“是”，否则回答单个字“否”，无法推断或模糊不清则一律回答单个字“否”，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImagePrompt = () -> "请你根据用户输入的上下文信息整理回复用户想生成图片的描述信息，稍微简短，尽量详备，并只需要回复要生成的图片的描述信息即可，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImageResultPrompt = () -> "请你根据用户输入的提示词信息，返回该图片生成成功的回复，以下是用户输入的提示词信息：";
    private Supplier<String> promptSpawnDailyWallpaperPrompt = () -> "现在是%d年%d月%d日%d时%d分%d秒，星期%d。\n如果有请你根据今天的节日、节气、时节、时令、习俗、风俗、历史、文化、气氛、氛围等因素结合用户的喜爱偏好：%s。\n生成一张电脑桌面高清壁纸。".formatted(dailyPaper.getComputerSystem().getDate(Date.YEAR), dailyPaper.getComputerSystem().getDate(Date.MONTH), dailyPaper.getComputerSystem().getDate(Date.DAY), dailyPaper.getComputerSystem().getDate(Date.HOUR), dailyPaper.getComputerSystem().getDate(Date.MINUTE), dailyPaper.getComputerSystem().getDate(Date.SECONDE), dailyPaper.getComputerSystem().getDate(Date.SUNDAY), DailyPaper.getInstance().getDailyWallpaperHobby().get());

    private Supplier<Integer> rootPaneTitleBarButtonSize = () -> 30;
    private Supplier<Integer> rootPaneMenuBarButtonWidth = () -> 100;
    private Supplier<Integer> rootPaneMenuBarButtonHeight = () -> 50;

    private Supplier<Integer> chatPaneTextInputAreaFontSize = () -> 20;
    private Supplier<Integer> chatPaneTextShowAreaFontSize = () -> 20;
    private Supplier<Integer> chatPaneButtonWidth = () -> 100;
    private Supplier<Integer> chatPaneButtonHeight = () -> 40;

    private Supplier<Integer> livePaneImageInputFontSize = () -> 20;
    private Supplier<Boolean> livePaneImagesShowing = () -> false;
    private Supplier<Boolean> livePaneImagesWallPerShowing = () -> false;

    private Supplier<Integer> dailyPaneFontSize = () -> 15;

    private Supplier<Integer> renewPaneFontSize = () -> 15;

    private volatile long autoDailyWallpaperStartTime = System.currentTimeMillis();
    private Supplier<Boolean> isAutoDailyWallpaperRunning = () -> false;

    private Supplier<Integer> fontSizeLargeLarge = () -> 25;
    private Supplier<Integer> fontSizeLarge = () -> 20;
    private Supplier<Integer> fontSizeMedium = () -> 15;
    private Supplier<Integer> fontSizeSmall = () -> 15;
    private Supplier<Integer> fontSizeSmallSmall = () -> 10;

    private Supplier<Integer> delay = () -> 100;
    private Supplier<Integer> offset = () -> 10;

    /**
     * 静态变量
     * */
    private final ExecutorService agentConnectionPool = Executors.newFixedThreadPool(5);
    private final ExecutorService liveImageShowingPool = Executors.newFixedThreadPool(1);
    private final ExecutorService liveImageWallpaperPool = Executors.newFixedThreadPool(1);

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final Runnable autoDailyWallpaper = () -> {
        if (DailyPaper.getInstance().AutoWallpaper.get() && DailyPaper.getInstance().AutoWallpaperWhenTime.get() >= 1) {
            if (System.currentTimeMillis() - autoDailyWallpaperStartTime > (long) DailyPaper.getInstance().AutoWallpaperWhenTime.get() * 60 * 1000 - 1000) {
                DailyPaperEvent.getInstance().dailyWallpaper();
                setAutoDailyWallpaperStartTime(System.currentTimeMillis());
            }
        }
    };

    @Override
    public void createElement(Stage stage) {
        if (dailyPaper == null) {
            dailyPaper = DailyPaper.getInstance();
        }

        /// 设置 Stage 舞台元素
        setStage(stage);

        /// 创建 ChatPane 分页元素
        createChatPaneElement();

        /// 创建 LivePane 分页元素
        createLivePaneElement();

        /// 创建 DailyPane 分页元素
        createDailyPaneElement();

        /// 创建 HobbyPane 分页元素
        createHobbyPaneElement();

        /// 创建 SettingPane 分页元素
        createSettingPaneElement();

        /// 创建 RenewPane 分页元素
        createRenewPaneElement();

        /// 创建 RootPane 分页元素
        createRootPaneElement();

        /// 创建 Scene 帷幕元素
        createSceneElement();

        /// 创建 Stage 舞台元素
        createStageElement();

    }

    @Override
    public void createStageElement() {
        getStage().setScene(scene);
    }

    @Override
    public void createSceneElement() {
        scene.setRoot(rootPane);
    }

    @Override
    public void createRootPaneElement() {
        HBox.setHgrow(rootPaneTitleBarButtonHolder, Priority.ALWAYS);
        rootPaneTitleBar.getChildren().addAll(rootPaneTitleBarButtonHolder, rootPaneTileBarMinButton, rootPaneTitleBarMaxButton, rootPaneTitleBarCloseButton);
        rootPaneTopPane.getChildren().addAll(rootPaneTitleBar);
        rootPane.setTop(rootPaneTopPane);

        rootPaneMenuBar.getChildren().addAll(rootPaneMenuBarChatButton, rootPaneMenuBarLiveButton, rootPaneMenuBarDailyButton, rootPaneMenuBarHobbyButton, rootPaneMenuBarSettingButton, rootPaneMenuBarRenewButton);
        rootPaneLeftPane.getChildren().addAll(rootPaneMenuBar);
        rootPane.setLeft(rootPaneLeftPane);

        rootPaneCenterPane.getChildren().addAll(chatPane);
        rootPane.setCenter(rootPaneCenterPane);
    }

    @Override
    public void createChatPaneElement() {
        chatPaneTextShowArea.setContent(chatPaneMessageVBox);
        chatPane.getChildren().addAll(chatPaneTextShowArea, chatPaneTextInputArea);
    }

    @Override
    public void createLivePaneElement() {
        livePaneImageInputHBox.getChildren().addAll(livePaneImageChooseButton, livePaneImageChooseLabel);
        livePane.getChildren().addAll(livePaneImageInputHBox);

        livePaneImageShowHBox.getChildren().addAll(livePaneImageShowImageView);
        livePane.getChildren().addAll(livePaneImageShowHBox);

        livePane.getChildren().addAll(livePaneImageSureHBox);
    }

    @Override
    public void createDailyPaneElement() {
        dailyPaneAutoSetWallpaperOnButton.setToggleGroup(dailyPaneAutoSetWallpaperGroup);
        dailyPaneAutoSetWallpaperOffButton.setToggleGroup(dailyPaneAutoSetWallpaperGroup);
        dailyPaneAutoLaunchWallpaperOnButton.setToggleGroup(dailyPaneAutoLaunchWallpaperGroup);
        dailyPaneAutoLaunchWallpaperOffButton.setToggleGroup(dailyPaneAutoLaunchWallpaperGroup);
        dailyPaneAutoSetWallpaperSettingLaunchOnButton.setToggleGroup(dailyPaneAutoSetWallpaperSettingLaunchGroup);
        dailyPaneAutoSetWallpaperSettingLaunchOffButton.setToggleGroup(dailyPaneAutoSetWallpaperSettingLaunchGroup);

        dailyPaneAutoSetWallpaperHBox.getChildren().addAll(dailyPaneAutoSetWallpaperLabel, dailyPaneAutoSetWallpaperOnButton, dailyPaneAutoSetWallpaperOffButton);
        dailyPaneAutoLaunchWallpaperHBox.getChildren().addAll(dailyPaneAutoLaunchWallpaperLabel, dailyPaneAutoLaunchWallpaperOnButton, dailyPaneAutoLaunchWallpaperOffButton);

        dailyPaneAutoSetWallpaperSettingLaunchHBox.getChildren().addAll(dailyPaneAutoSetWallpaperSettingLaunchLabel, dailyPaneAutoSetWallpaperSettingLaunchOnButton, dailyPaneAutoSetWallpaperSettingLaunchOffButton);
        dailyPaneAutoSetWallpaperSettingDelayHBox.getChildren().addAll(dailyPaneAutoSetWallpaperSettingDelayLabel, dailyPaneAutoSetWallpaperSettingDelyTextField);

        dailyPaneAutoSetWallpaperSettingHBox.getChildren().addAll(dailyPaneAutoSetWallpaperSettingLaunchHBox, dailyPaneAutoSetWallpaperSettingDelayHBox);

        dailyPaneLaunchHBox.getChildren().addAll(dailyPaneAutoSetWallpaperHBox, dailyPaneAutoLaunchWallpaperHBox);

        dailyPaneHobbyHobbyScrollPane.setContent(dailyPaneHobbyHobbyTextArea);
        dailyPaneHobbyHobbyVBox.getChildren().addAll(dailyPaneHobbyHobbyLabel, dailyPaneHobbyHobbyTextArea);

        dailyPaneHobbyVBox.getChildren().addAll(dailyPaneHobbyHobbyVBox);

        dailyPaneVBox.getChildren().addAll(dailyPaneLaunchHBox, dailyPaneAutoSetWallpaperSettingHBox, dailyPaneHobbyVBox);

        dailyPane.getChildren().addAll(dailyPaneVBox);
    }

    @Override
    public void createHobbyPaneElement() {
        hobbyPaneVBox.getChildren().addAll(hobbyPaneInformationLabel);
        hobbyPane.getChildren().addAll(hobbyPaneVBox);
    }

    @Override
    public void createSettingPaneElement() {
        settingPaneVBox.getChildren().addAll(settingPaneInformationLabel);
        settingPane.getChildren().addAll(settingPaneVBox);
    }

    @Override
    public void createRenewPaneElement() {
        renewPaneAutoCheckRenewOnButton.setToggleGroup(renewPaneAutoCheckRenewGroup);
        renewPaneAutoCheckRenewOffButton.setToggleGroup(renewPaneAutoCheckRenewGroup);
        renewPaneAutoDownloadRenewOnButton.setToggleGroup(renewPaneAutoDownloadRenewGroup);
        renewPaneAutoDownloadRenewOffButton.setToggleGroup(renewPaneAutoDownloadRenewGroup);
        renewPaneManualCheckRenewTextShowScrollPane.setContent(renewPaneManualCheckRenewTextShowArea);
        renewPaneManualRenewVBox.getChildren().addAll(renewPaneManualCheckRenewButton, renewPaneManualCheckRenewTextShowArea, renewPaneManualDownloadRenewButton);
        renewPaneAutoCheckRenewHBox.getChildren().addAll(renewPaneAutoCheckRenewLabel, renewPaneAutoCheckRenewOnButton,renewPaneAutoCheckRenewOffButton);
        renewPaneAutoDownloadRenewHBox.getChildren().addAll(renewPaneAutoDownloadRenewLabel, renewPaneAutoDownloadRenewOnButton, renewPaneAutoDownloadRenewOffButton);
        renewPaneAutoRenewHBox.getChildren().addAll(renewPaneAutoCheckRenewHBox, renewPaneAutoDownloadRenewHBox);
        renewPaneVBox.getChildren().addAll(renewPaneManualRenewVBox, renewPaneAutoRenewHBox);
        renewPane.getChildren().addAll(renewPaneVBox);
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperElement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperElement();
        }
        return INSTANCE;
    }

}