package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ArrayList;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.function.Supplier;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;
import java.util.concurrent.Executors;
import com.probie.dailypaper.Enum.Date;
import javafx.scene.control.ScrollPane;
import java.util.concurrent.ExecutorService;
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
     * 动态变量
     * */
    private Supplier<String> promptInformationPrompt = () -> "你是DailyPaper日常壁纸软件应用的AI小助手小Day"
            + "\n" + "你的回复不要使用任何Markdown语法，输出的代码也不要出现代码块格式"
            + "\n" + "现在是%d年%d月%d日%d时%d分%d秒".formatted(dailyPaper.getComputerSystem().getDate(Date.YEAR), dailyPaper.getComputerSystem().getDate(Date.MONTH), dailyPaper.getComputerSystem().getDate(Date.DAY), dailyPaper.getComputerSystem().getDate(Date.HOUR), dailyPaper.getComputerSystem().getDate(Date.MINUTE), dailyPaper.getComputerSystem().getDate(Date.SECONDE));
    private Supplier<String> promptIfImagePrompt = () -> "请你根据用户输入的上下文信息帮我推测判断出用户现在是否需要生成图片，是则回答单个字“是”，否则回答单个字“否”，无法推断或模糊不清则一律回答单个字“否”，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImagePrompt = () -> "请你根据用户输入的上下文信息整理用户想生成的图片描述信息，尽量简短，以下是用户输入的上下文信息：";
    private Supplier<String> promptSpawnImageResultPrompt = () -> "请你根据用户输入的提示词信息，返回该图片生成成功的信，以下是用户输入的提示词信息：";

    private Supplier<Integer> rootPaneTitleBarButtonSize = () -> 30;
    private Supplier<Integer> rootPaneMenuBarButtonWidth = () -> 100;
    private Supplier<Integer> rootPaneMenuBarButtonHeight = () -> 40;

    private Supplier<Integer> chatPaneTextInputAreaFontSize = () -> 20;
    private Supplier<Integer> chatPaneTextShowAreaFontSize = () -> 20;
    private Supplier<Integer> chatPaneButtonWidth = () -> 100;
    private Supplier<Integer> chatPaneButtonHeight = () -> 40;

    private Supplier<Integer> livePaneImageInputFontSize = () -> 20;
    private Supplier<Boolean> livePaneImagesShowing = () -> false;
    private Supplier<Boolean> livePaneImagesWallPerShowing = () -> false;

    private Supplier<Integer> offset = () -> 10;
    private Supplier<Integer> delay = () -> 100;

    /**
     * 静态变量
     * */
    private final ExecutorService agentConnectPool = Executors.newFixedThreadPool(5);
    private final ExecutorService imagesShowPool = Executors.newFixedThreadPool(1);
    private final ExecutorService imagesWallpaperPool = Executors.newFixedThreadPool(1);

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

    }

    @Override
    public void createHobbyPaneElement() {

    }

    @Override
    public void createSettingPaneElement() {

    }

    @Override
    public void createRenewPaneElement() {

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