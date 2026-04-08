package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.scene.image.ImageView;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperElement;

@Data
public class DailyPaperElement implements IDailyPaperElement {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperElement INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperElement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperElement();
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
     * stage
     * */
    private Stage stage;

    /**
     * scene
     * */
    private Scene scene;

    /**
     * root
     * */
    private BorderPane rootPane = new BorderPane();

    /**
     * center
     * */
    private VBox rootPaneCenterStageBarVBox = new VBox();

    /**
     * top
     * */
    private HBox rootPaneTopTitleBarHBox = new HBox();

    private HBox rootPaneTopTitleBarLogoHBox = new HBox();
    private Label rootPaneTopTitleBarLogoLabel = new Label();

    private HBox rootPaneTopTitleBarChooseHBox = new HBox();
    private Button rootPaneTopTitleBarMinButton = new Button();
    private Button rootPaneTopTitleBarMaxButton = new Button();
    private Button rootPaneTopTitleBarCloseButton = new Button();

    /**
     * left
     * */
    private VBox rootPaneLeftMenuBarVBox = new VBox();

    private VBox rootPaneLeftMenuBarChooseVBox = new VBox();
    private Button rootPaneLeftMenuBarChatButton = new Button();
    private Button rootPaneLeftMenuBarLiveButton = new Button();
    private Button rootPaneLeftMenuBarDailyButton = new Button();
    private Button rootPaneLeftMenuBarParamButton = new Button();
    private Button rootPaneLeftMenuBarSettingButton = new Button();
    private Button rootPaneLeftMenuBarRenewButton = new Button();

    /**
     * 功能模块
     * */
    private VBox chatVBox = new VBox();
    private VBox liveVBox = new VBox();
    private VBox dailyVBox = new VBox();
    private VBox paramVBox = new VBox();
    private VBox settingVBox = new VBox();
    private VBox renewVBox = new VBox();

    /**
     * chat
     * */
    private VBox chatTextShowVBox = new VBox();
    private ScrollPane chatTextShowScrollPane = new ScrollPane();
    private VBox chatTextShowMessageVBox = new VBox();

    private VBox chatTextInputVBox = new VBox();
    private ScrollPane chatTextInputScrollPane = new ScrollPane();
    private TextArea chatTextInputTextArea = new TextArea();

    private HBox chatTextInputToolsHBox = new HBox();
    private Button chatTextInputToolsUploadImageButton = new Button();
    private FileChooser chatTextInputToolsUploadImageFileChooser = new FileChooser();

    /**
     * live
     * */
    private HBox liveImageChooseHBox = new HBox();
    private Button liveImageChooseButton = new Button();
    private Label liveImageChooseLabel = new Label();
    private FileChooser liveImageChooseFileChooser = new FileChooser();

    private VBox liveImageShowSureVBox = new VBox();

    private HBox liveImageShowHBox = new HBox();
    private ImageView liveImageShowImageView = new ImageView();

    private HBox liveImageSureHBox = new HBox();

    /**
     * daily
     * */
    private HBox dailyChooseHBox = new HBox();

    private HBox dailyWallpaperHBox = new HBox();
    private ToggleGroup dailyWallpaperGroup = new ToggleGroup();
    private Label dailyWallpaperLabel = new Label();
    private RadioButton dailyWallpaperOnButton = new RadioButton();
    private RadioButton dailyWallpaperOffButton = new RadioButton();

    private HBox dailyLaunchHBox = new HBox();
    private ToggleGroup dailyLaunchGroup = new ToggleGroup();
    private Label dailyLaunchLabel = new Label();
    private RadioButton dailyLaunchOnButton = new RadioButton();
    private RadioButton dailyLaunchOffButton = new RadioButton();

    private HBox dailyWallpaperChooseHBox = new HBox();

    private HBox dailyLaunchWallpaperHBox = new HBox();
    private ToggleGroup dailyLaunchWallpaperGroup = new ToggleGroup();
    private Label dailyLaunchWallpaperLabel = new Label();
    private RadioButton dailyLaunchWallpaperOnButton = new RadioButton();
    private RadioButton dailyLaunchWallpaperOffButton = new RadioButton();

    private HBox dailyTimeWallpaperHBox = new HBox();
    private Label dailyTimeWallpaperLabel = new Label();
    private TextField dailyTimeWallpaperTextField = new TextField();

    private VBox dailyWallpaperHobbyVBox = new VBox();
    private Label dailyWallpaperHobbyLabel = new Label();
    private ScrollPane dailyWallpaperHobbyScrollPane = new ScrollPane();
    private TextArea dailyWallpaperHobbyTextArea = new TextArea();

    private HBox dailyWallpaperHobbyToolsHBox = new HBox();
    private Button dailyWallpaperHobbyToolsUploadImageButton = new Button();
    private FileChooser dailyWallpaperHobbyToolsUploadImageFileChooser = new FileChooser();

    /**
     * param
     * */
    private VBox paramInputVBox = new VBox();
    private ScrollPane paramInputScrollPane = new ScrollPane();
    private VBox paramInputScrollPaneVBox = new VBox();
    private HBox paramButtonBarHBox = new HBox();
    private Button paramButtonBarResetButton = new Button();

    /**
     * setting
     * */
    private VBox settingInputVBox = new VBox();
    private ScrollPane settingInputScrollPane = new ScrollPane();
    private VBox settingInputScrollPaneVBox = new VBox();
    private HBox settingButtonBarHBox = new HBox();
    private Button settingButtonBarResetButton = new Button();

    /**
     * renew
     * */
    private VBox renewManualRenewVBox = new VBox();

    private VBox renewManualCheckRenewHBox = new VBox();
    private Button renewManualCheckRenewButton = new Button();

    private VBox renewManualShowRenewHBox = new VBox();
    private ScrollPane renewManualShowRenewScrollPane = new ScrollPane();
    private TextArea renewManualShowRenewTextArea = new TextArea();

    private VBox renewManualDownloadRenewHBox = new VBox();
    private Button renewManualDownloadRenewButton = new Button();

    private HBox renewAutoRenewHBox = new HBox();

    private HBox renewAutoCheckRenewHBox = new HBox();
    private ToggleGroup renewAutoCheckRenewGroup = new ToggleGroup();
    private Label renewAutoCheckRenewLabel = new Label();
    private RadioButton renewAutoCheckRenewOnButton = new RadioButton();
    private RadioButton renewAutoCheckRenewOffButton = new RadioButton();

    private HBox renewAutoDownloadRenewHBox = new HBox();
    private ToggleGroup renewAutoDownloadRenewGroup = new ToggleGroup();
    private Label renewAutoDownloadRenewLabel = new Label();
    private RadioButton renewAutoDownloadRenewOnButton = new RadioButton();
    private RadioButton renewAutoDownloadRenewOffButton = new RadioButton();

    @Override
    public void createElement(Stage stage) {

        setStage(stage);
        createChatElement();
        createLiveElement();
        createDailyElement();
        createParamElement();
        createSettingElement();
        createRenewElement();
        createRootElement();
        createSceneElement();
        createStageElement();

    }

    @Override
    public void createStageElement() {
        getStage().setScene(getScene());
    }

    @Override
    public void createSceneElement() {
        setScene(new Scene(getRootPane()));
    }

    @Override
    public void createRootElement() {
        rootPaneTopTitleBarLogoHBox.getChildren().addAll(rootPaneTopTitleBarLogoLabel);

        rootPaneTopTitleBarChooseHBox.getChildren().addAll(rootPaneTopTitleBarMinButton, rootPaneTopTitleBarMaxButton, rootPaneTopTitleBarCloseButton);

        rootPaneTopTitleBarHBox.getChildren().addAll(rootPaneTopTitleBarLogoHBox, rootPaneTopTitleBarChooseHBox);

        rootPaneLeftMenuBarChooseVBox.getChildren().addAll(rootPaneLeftMenuBarChatButton, rootPaneLeftMenuBarLiveButton, rootPaneLeftMenuBarDailyButton, rootPaneLeftMenuBarParamButton, rootPaneLeftMenuBarSettingButton, rootPaneLeftMenuBarRenewButton);
        rootPaneLeftMenuBarVBox.getChildren().addAll(rootPaneLeftMenuBarChooseVBox);

        rootPaneCenterStageBarVBox.getChildren().add(chatVBox);

        rootPane.setTop(rootPaneTopTitleBarHBox);
        rootPane.setLeft(rootPaneLeftMenuBarVBox);
        rootPane.setCenter(rootPaneCenterStageBarVBox);
    }

    @Override
    public void createChatElement() {
        chatTextShowScrollPane.setContent(chatTextShowMessageVBox);
        chatTextShowVBox.getChildren().addAll(chatTextShowScrollPane);

        chatTextInputScrollPane.setContent(chatTextInputTextArea);
        chatTextInputToolsHBox.getChildren().addAll(chatTextInputToolsUploadImageButton);
        chatTextInputVBox.getChildren().addAll(chatTextInputScrollPane, chatTextInputToolsHBox);

        chatVBox.getChildren().addAll(chatTextShowVBox, chatTextInputVBox);
    }

    @Override
    public void createLiveElement() {
        liveImageChooseHBox.getChildren().addAll(liveImageChooseButton, liveImageChooseLabel);

        liveImageShowHBox.getChildren().addAll(liveImageShowImageView);

        liveImageShowSureVBox.getChildren().addAll(liveImageShowHBox, liveImageSureHBox);

        liveVBox.getChildren().addAll(liveImageChooseHBox, liveImageShowSureVBox);
    }

    @Override
    public void createDailyElement() {
        dailyWallpaperOnButton.setToggleGroup(dailyWallpaperGroup);
        dailyWallpaperOffButton.setToggleGroup(dailyWallpaperGroup);

        dailyLaunchOnButton.setToggleGroup(dailyLaunchGroup);
        dailyLaunchOffButton.setToggleGroup(dailyLaunchGroup);

        dailyLaunchWallpaperOnButton.setToggleGroup(dailyLaunchWallpaperGroup);
        dailyLaunchWallpaperOffButton.setToggleGroup(dailyLaunchWallpaperGroup);

        dailyWallpaperHBox.getChildren().addAll(dailyWallpaperLabel, dailyWallpaperOnButton, dailyWallpaperOffButton);

        dailyLaunchHBox.getChildren().addAll(dailyLaunchLabel, dailyLaunchOnButton, dailyLaunchOffButton);

        dailyChooseHBox.getChildren().addAll(dailyWallpaperHBox, dailyLaunchHBox);

        dailyLaunchWallpaperHBox.getChildren().addAll(dailyLaunchWallpaperLabel, dailyLaunchWallpaperOnButton, dailyLaunchWallpaperOffButton);

        dailyTimeWallpaperHBox.getChildren().addAll(dailyTimeWallpaperLabel, dailyTimeWallpaperTextField);

        dailyWallpaperChooseHBox.getChildren().addAll(dailyLaunchWallpaperHBox, dailyTimeWallpaperHBox);

        dailyWallpaperHobbyScrollPane.setContent(dailyWallpaperHobbyTextArea);
        dailyWallpaperHobbyToolsHBox.getChildren().addAll(dailyWallpaperHobbyToolsUploadImageButton);
        dailyWallpaperHobbyVBox.getChildren().addAll(dailyWallpaperHobbyLabel, dailyWallpaperHobbyScrollPane, dailyWallpaperHobbyToolsHBox);

        dailyVBox.getChildren().addAll(dailyChooseHBox, dailyWallpaperChooseHBox, dailyWallpaperHobbyVBox);
    }

    @Override
    public void createParamElement() {
        paramInputScrollPane.setContent(paramInputScrollPaneVBox);
        paramInputVBox.getChildren().addAll(paramInputScrollPane);
        paramButtonBarHBox.getChildren().addAll(paramButtonBarResetButton);
        paramVBox.getChildren().addAll(paramInputVBox, paramButtonBarHBox);
    }

    @Override
    public void createSettingElement() {
        settingInputScrollPane.setContent(settingInputScrollPaneVBox);
        settingInputVBox.getChildren().addAll(settingInputScrollPane);
        settingButtonBarHBox.getChildren().addAll(settingButtonBarResetButton);
        settingVBox.getChildren().addAll(settingInputVBox, settingButtonBarHBox);
    }

    @Override
    public void createRenewElement() {
        renewAutoCheckRenewOnButton.setToggleGroup(renewAutoCheckRenewGroup);
        renewAutoCheckRenewOffButton.setToggleGroup(renewAutoCheckRenewGroup);

        renewAutoDownloadRenewOnButton.setToggleGroup(renewAutoDownloadRenewGroup);
        renewAutoDownloadRenewOffButton.setToggleGroup(renewAutoDownloadRenewGroup);

        renewManualCheckRenewHBox.getChildren().addAll(renewManualCheckRenewButton);

        renewManualShowRenewScrollPane.setContent(renewManualShowRenewTextArea);
        renewManualShowRenewHBox.getChildren().addAll(renewManualShowRenewScrollPane);

        renewManualDownloadRenewHBox.getChildren().addAll(renewManualDownloadRenewButton);

        renewAutoCheckRenewHBox.getChildren().addAll(renewAutoCheckRenewLabel, renewAutoCheckRenewOnButton, renewAutoCheckRenewOffButton);

        renewAutoDownloadRenewHBox.getChildren().addAll(renewAutoDownloadRenewLabel, renewAutoDownloadRenewOnButton, renewAutoDownloadRenewOffButton);

        renewManualRenewVBox.getChildren().addAll(renewManualCheckRenewHBox, renewManualShowRenewHBox, renewManualDownloadRenewHBox);

        renewAutoRenewHBox.getChildren().addAll(renewAutoCheckRenewHBox, renewAutoDownloadRenewHBox);

        renewVBox.getChildren().addAll(renewManualRenewVBox, renewAutoRenewHBox);
    }

}