package com.probie.dailypaper.DailyPaper;

import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.Config.LiveImageConfig;
import com.probie.dailypaper.Config.SettingConfig;
import com.probie.dailypaper.System.ComputerSystem;
import com.probie.dailypaper.System.GIFSystem;
import com.probie.dailypaper.System.ImageSystem;
import javafx.stage.FileChooser;
import lombok.Data;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import java.util.function.Supplier;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import javafx.scene.input.Clipboard;
import java.util.concurrent.TimeUnit;
import javafx.stage.DirectoryChooser;
import javafx.scene.input.ClipboardContent;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperEvent;

@Data
public class DailyPaperEvent implements IDailyPaperEvent {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperEvent INSTANCE;

    /**
     * 获取一个懒加载的类单例对象
     * */
    public synchronized static DailyPaperEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperEvent();
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
     * 动态变量
     * */
    private Supplier<Double> mouseStartX;
    private Supplier<Double> mouseStartY;

    @Override
    public void createEvent() {

        createStageEvent();
        createSceneEvent();
        createRootEvent();
        createChatEvent();
        createLiveEvent();
        createDailyEvent();
        createHobbyEvent();
        createSettingEvent();
        createRenewEvent();

    }

    @Override
    public void createStageEvent() {

    }

    @Override
    public void createSceneEvent() {

    }

    @Override
    public void createRootEvent() {
        /// 拖拽窗口
        dailyPaperElement.getRootPaneTopTitleBarHBox().setOnMousePressed(mouseEvent -> {
            mouseStartX = mouseEvent::getScreenX;
            mouseStartY = mouseEvent::getScreenY;
            mouseEvent.consume();
        });
        dailyPaperElement.getRootPaneTopTitleBarHBox().setOnMouseDragged(mouseEvent -> {
            dailyPaperElement.getStage().setX(dailyPaperElement.getStage().getX() + (mouseEvent.getScreenX() - mouseStartX.get()));
            dailyPaperElement.getStage().setY(dailyPaperElement.getStage().getY() + (mouseEvent.getScreenY() - mouseStartY.get()));
            mouseStartX = mouseEvent::getScreenX;
            mouseStartY = mouseEvent::getScreenY;
            mouseEvent.consume();
        });

        /// 最小化 最大化 关闭
        dailyPaperElement.getRootPaneTopTitleBarMinButton().setOnAction(actionEvent -> dailyPaperElement.getStage().setIconified(true));
        dailyPaperElement.getRootPaneTopTitleBarMaxButton().setOnAction(actionEvent -> {
            dailyPaperElement.getStage().setMaximized(!dailyPaperElement.getStage().isMaximized());
            dailyPaperElement.getRootPaneTopTitleBarMaxButton().setText(dailyPaperElement.getStage().isMaximized() ? "⊟" : "□");
        });
        dailyPaperElement.getRootPaneTopTitleBarCloseButton().setOnAction(actionEvent -> dailyPaperApplication.stop());

        /// 菜单选择
        dailyPaperElement.getRootPaneLeftMenuBarChatButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getChatVBox()));
        dailyPaperElement.getRootPaneLeftMenuBarLiveButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getLiveVBox()));
        dailyPaperElement.getRootPaneLeftMenuBarDailyButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getDailyVBox()));
        dailyPaperElement.getRootPaneLeftMenuBarParamButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getParamVBox()));
        dailyPaperElement.getRootPaneLeftMenuBarSettingButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getSettingVBox()));
        dailyPaperElement.getRootPaneLeftMenuBarRenewButton().setOnAction(actionEvent -> dailyPaperFunction.chooseMenu(dailyPaperElement.getRenewVBox()));
    }

    @Override
    public void createChatEvent() {
        dailyPaperElement.getChatTextInputTextArea().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (keyEvent.isShiftDown() || keyEvent.isControlDown() || keyEvent.isAltDown()) {
                    if (!dailyPaperElement.getChatTextInputTextArea().getText().isEmpty()) {
                        /// User
                        VBox chatUserMessageBar = new VBox();
                        VBox chatUserMessageContent = new VBox();
                        Label chatUserMessageLabel = new Label();

                        chatUserMessageBar.prefWidthProperty().bind(dailyPaperElement.getChatTextShowMessageVBox().widthProperty());
                        chatUserMessageBar.setFillWidth(true);
                        chatUserMessageBar.setAlignment(Pos.CENTER_RIGHT);

                        chatUserMessageContent.maxWidthProperty().bind(chatUserMessageBar.widthProperty().divide(9.0).multiply(4.0));
                        chatUserMessageContent.setFillWidth(true);
                        chatUserMessageContent.setAlignment(Pos.CENTER_RIGHT);

                        chatUserMessageLabel.setText(dailyPaperElement.getChatTextInputTextArea().getText());
                        chatUserMessageLabel.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
                        chatUserMessageLabel.setAlignment(Pos.CENTER_RIGHT);
                        chatUserMessageLabel.setWrapText(true);

                        chatUserMessageLabel.setPadding(new Insets(dailyPaperData.getSpacingSizeSmall().get()));
                        chatUserMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                        chatUserMessageLabel.setBorder(new Border(new BorderStroke(
                                Color.PURPLE,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(dailyPaperData.getSpacingSizeSmall().get()),
                                new BorderWidths(dailyPaperData.getSpacingSizeSmallSmall().get())
                        )));

                        chatUserMessageContent.getChildren().addAll(chatUserMessageLabel);
                        chatUserMessageBar.getChildren().addAll(chatUserMessageContent);
                        dailyPaperElement.getChatTextShowMessageVBox().getChildren().addAll(chatUserMessageBar);
                        dailyPaperData.getChatUserMessageArrayList().add(chatUserMessageLabel.getText());

                        dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextShowScrollPane());

                        /// AI
                        VBox chatAgentMessageBar = new VBox();
                        VBox chatAgentMessageContent = new VBox();
                        Label chatAgentMessageLabel = new Label();

                        chatAgentMessageBar.prefWidthProperty().bind(dailyPaperElement.getChatTextShowMessageVBox().widthProperty());
                        chatAgentMessageBar.setAlignment(Pos.CENTER_LEFT);

                        chatAgentMessageContent.maxWidthProperty().bind(chatAgentMessageBar.widthProperty().divide(9.0).multiply(4.0));
                        chatAgentMessageContent.setAlignment(Pos.CENTER_LEFT);

                        chatAgentMessageLabel.setText("等待响应中...");
                        chatAgentMessageLabel.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
                        chatAgentMessageLabel.setAlignment(Pos.CENTER_LEFT);
                        chatAgentMessageLabel.setWrapText(true);

                        chatAgentMessageLabel.setPadding(new Insets(dailyPaperData.getSpacingSizeSmall().get()));
                        chatAgentMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                        chatAgentMessageLabel.setBorder(new Border(new BorderStroke(
                                Color.GOLD,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(dailyPaperData.getSpacingSizeSmall().get()),
                                new BorderWidths(dailyPaperData.getSpacingSizeSmallSmall().get())
                        )));

                        chatAgentMessageContent.getChildren().addAll(chatAgentMessageLabel);
                        chatAgentMessageBar.getChildren().addAll(chatAgentMessageContent);
                        dailyPaperElement.getChatTextShowMessageVBox().getChildren().addAll(chatAgentMessageBar);

                        dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextInputScrollPane());
                        dailyPaperElement.getChatTextInputTextArea().clear();

                        /// AI 回复
                        dailyPaper.getDailyPaperPool().submit(() -> {
                            /// 收集上下文
                            try {
                                Platform.runLater(() -> chatAgentMessageLabel.setText("收集上下文..."));
                                StringBuilder content = new StringBuilder();
                                if (dailyPaperData.getChatUserMessageArrayList().size() > 1) {
                                    for (int i = dailyPaperData.getChatUserMessageArrayList().size() - 1 - 1; i >= 0 && content.length() <= 10000; i--) {
                                        if (dailyPaperData.getChatAgentMessageArrayList().size() >= i + 1) {
                                            content = new StringBuilder("\nAI：%s".formatted(dailyPaperData.getChatAgentMessageArrayList().get(i)) + content);
                                        }
                                        content = new StringBuilder("\n用户：%s".formatted(dailyPaperData.getChatUserMessageArrayList().get(i)) + content);
                                    }
                                    content = new StringBuilder("\n*历史对话记录：%s".formatted(content));
                                }
                                content = new StringBuilder("*信息背景：\n%s".formatted(dailyPaperData.getPromptInformationPrompt().get() + content));
                                content.append("\n*用户现在需求：\n%s".formatted(dailyPaperData.getChatUserMessageArrayList().getLast()));

                                Platform.runLater(() -> chatAgentMessageLabel.setText("分析需求中..."));
                                String[] ifImage = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(dailyPaperData.getPromptIfImagePrompt().get() + content);

                                /// 生成文本
                                if (!ifImage[0].contains("是")) {
                                    Platform.runLater(() -> chatAgentMessageLabel.setText("文本生成中..."));
                                    String[] result = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(content.toString());
                                    Platform.runLater(() -> chatAgentMessageLabel.setText(result[0]));

                                    Platform.runLater(() -> {
                                        HBox chatPaneAgentMessageCopyTextButtonBar = new HBox();
                                        Button chatPaneAgentMessageCopyTextButton = new Button("点击复制文本");

                                        chatPaneAgentMessageCopyTextButtonBar.setMaxWidth(chatAgentMessageLabel.widthProperty().get());

                                        chatPaneAgentMessageCopyTextButton.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));

                                        chatPaneAgentMessageCopyTextButton.setOnAction(actionEvent -> {
                                            ClipboardContent clipboardContent = new ClipboardContent();
                                            clipboardContent.putString(chatAgentMessageLabel.getText());
                                            Clipboard.getSystemClipboard().setContent(clipboardContent);
                                        });

                                        chatPaneAgentMessageCopyTextButtonBar.getChildren().addAll(chatPaneAgentMessageCopyTextButton);
                                        chatAgentMessageBar.getChildren().addAll(chatPaneAgentMessageCopyTextButtonBar);

                                    });
                                    dailyPaperData.getChatAgentMessageArrayList().add(result[0]);
                                    dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextShowScrollPane());
                                }

                                /// 生成图片
                                else {
                                    Platform.runLater(() -> chatAgentMessageLabel.setText("图片生成中..."));

                                    String[] result = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(dailyPaperData.getPromptSpawnImageResultPrompt().get() + content);

                                    HBox chatPaneAgentMessageSetWallpaperButtonBar = new HBox();
                                    HBox chatPaneAgentMessageDownloadImageButtonBar = new HBox();
                                    FileChooser fileChooser = new FileChooser();

                                    chatPaneAgentMessageSetWallpaperButtonBar.maxWidthProperty().bind(chatAgentMessageLabel.widthProperty());
                                    chatPaneAgentMessageDownloadImageButtonBar.maxWidthProperty().bind(chatAgentMessageLabel.widthProperty());
                                    fileChooser.getExtensionFilters().addAll(
                                            new FileChooser.ExtensionFilter("ALL", "*.*"),
                                            new FileChooser.ExtensionFilter("GIF", "*.gif"),
                                            new FileChooser.ExtensionFilter("PNG", "*.png"),
                                            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                                            new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
                                    );

                                    String prompt = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(dailyPaperData.getPromptSpawnImagePrompt().get() + content)[0];
                                    String[] imageURIs = TextToImageAIAgentSiliconFlow.getInstance().turnTextToImage(prompt);
                                    for (int i = 0; i < imageURIs.length; i++) {
                                        BufferedImage bufferedImage = ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[i]);
                                        bufferedImage = ImageSystem.getInstance().setBufferedImageSize(bufferedImage, (int) ((chatAgentMessageContent.widthProperty().get())), (int) ((double) Integer.parseInt(dailyPaper.getSpawnImageSize().get().split("x")[1]) * (((chatAgentMessageContent.widthProperty().get())) / (double) Integer.parseInt(dailyPaper.getSpawnImageSize().get().split("x")[0]))));
                                        ImageView imageView = new ImageView(ImageSystem.getInstance().turnBufferedImageToFXImage(bufferedImage));
                                        int index = i;
                                        Platform.runLater(() -> {
                                            chatAgentMessageBar.getChildren().addAll(imageView);
                                            Button chatPaneAgentMessagesetWallPaperButton = new Button("点击设为壁纸");
                                            Button chatPaneAgentMessagedownLoadImageButton = new Button("点击下载图片");
                                            chatPaneAgentMessagesetWallPaperButton.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
                                            chatPaneAgentMessagedownLoadImageButton.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
                                            if (imageURIs.length > 1) {
                                                chatPaneAgentMessagesetWallPaperButton.setText("点击设置图片%d为壁纸".formatted(index + 1));
                                                chatPaneAgentMessagedownLoadImageButton.setText("点击下载图片%d到本地".formatted(index + 1));
                                            }
                                            chatPaneAgentMessagesetWallPaperButton.setOnAction(actionEvent -> {
                                                dailyPaperFunction.clearLiveImageWallpaper();
                                                if (ImageSystem.getInstance().turnBufferedImageToLocalFile(ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[index]), dailyPaper.getTempFilePath().get(), dailyPaper.getTempImageFileName().get())) {
                                                    dailyPaperFunction.showButtonInformation(chatPaneAgentMessagesetWallPaperButton, "设置壁纸成功");
                                                    ImageSystem.getInstance().setWallPaper(dailyPaper.getTempFilePath().get(), dailyPaper.getTempImageFileName().get());
                                                } else {
                                                    dailyPaperFunction.showButtonInformation(chatPaneAgentMessagesetWallPaperButton, "设置壁纸失败");
                                                }
                                            });

                                            chatPaneAgentMessagedownLoadImageButton.setOnAction(actionEvent -> {
                                                fileChooser.setInitialDirectory(new File(dailyPaper.getChatImageDownloadFilePath().get()));
                                                fileChooser.setInitialFileName(dailyPaper.getChatImageDownloadFileName().get());
                                                File file = fileChooser.showSaveDialog(dailyPaperElement.getStage());
                                                if (file != null) {
                                                    dailyPaper.setChatImageDownloadFilePath(file::getParent);
                                                    ImageSystem.getInstance().turnBufferedImageToLocalFile(ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[index]), file.getAbsolutePath());
                                                }
                                            });
                                            
                                            chatPaneAgentMessageSetWallpaperButtonBar.getChildren().addAll(chatPaneAgentMessagesetWallPaperButton);
                                            chatPaneAgentMessageDownloadImageButtonBar.getChildren().addAll(chatPaneAgentMessagedownLoadImageButton);
                                        });
                                    }

                                    Platform.runLater(() -> chatAgentMessageLabel.setText(result[0]));
                                    Platform.runLater(() -> chatAgentMessageBar.getChildren().addAll(chatPaneAgentMessageSetWallpaperButtonBar, chatPaneAgentMessageDownloadImageButtonBar));

                                    dailyPaperData.getChatAgentMessageArrayList().add(result[0]);
                                    dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextShowScrollPane());
                                }
                            }

                            /// 异常处理
                            catch (Exception exception) {
                                Platform.runLater(() -> chatAgentMessageLabel.setText("不小心出错了:\n" + exception));
                                dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextShowScrollPane());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void createLiveEvent() {
        dailyPaperElement.getLiveImageChooseButton().setOnAction(actionEvent -> {
            dailyPaperElement.getLiveImageChooseFileChooser().setInitialDirectory(new File(dailyPaper.getLiveImageChosenFilePath().get()));
            File file = dailyPaperElement.getLiveImageChooseFileChooser().showOpenDialog(dailyPaperElement.getStage());
            dailyPaperFunction.clearLive();
            if (file != null) {
                dailyPaperElement.getLiveImageChooseLabel().setText(file.getAbsolutePath());
                dailyPaper.setLiveImageChosenFilePath(file::getParent);
                dailyPaperFunction.waitADelay(10);

                /// 图片壁纸
                if (file.getAbsolutePath().toLowerCase().endsWith(".png") || file.getAbsolutePath().toLowerCase().endsWith(".jpg") || file.getAbsolutePath().toLowerCase().endsWith(".jpeg")) {
                    BufferedImage bufferedImage = ImageSystem.getInstance().turnLocalFileToBufferedImage(file.getAbsolutePath());
                    BufferedImage fitBufferedImage = bufferedImage;
                    if (fitBufferedImage.getWidth() > dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get()) {
                        int width = (int) dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get();
                        int height = (int) (fitBufferedImage.getHeight() * (dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get() / fitBufferedImage.getWidth()));
                        fitBufferedImage = ImageSystem.getInstance().setBufferedImageSize(fitBufferedImage, width, height);
                    }
                    if (fitBufferedImage.getHeight() > dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get()) {
                        int height = (int) dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get();
                        int width = (int) (fitBufferedImage.getWidth() * (dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get() / fitBufferedImage.getHeight()));
                        fitBufferedImage =  ImageSystem.getInstance().setBufferedImageSize(fitBufferedImage, width, height);
                    }
                    dailyPaperElement.getLiveImageShowImageView().setImage(ImageSystem.getInstance().turnBufferedImageToFXImage(fitBufferedImage));

                    Button livePaneImageSureButton = new Button("点击设为壁纸");
                    livePaneImageSureButton.setFont(new Font(dailyPaperData.getFontSizeMedium().get()));
                    livePaneImageSureButton.setPrefWidth(fitBufferedImage.getWidth());
                    livePaneImageSureButton.setPrefHeight(dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().get());
                    livePaneImageSureButton.setOnAction(actionEvent1 -> {
                        if (ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImage, dailyPaper.getTempFilePath().get(), dailyPaper.getTempImageFileName().get())) {
                            dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置成功");
                            dailyPaperFunction.clearLiveImageWallpaper();
                            ComputerSystem.getInstance().setWallPaper(dailyPaper.getTempFilePath().get(), dailyPaper.getTempImageFileName().get());
                        } else {
                            dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置失败");
                        }
                    });
                    dailyPaperElement.getLiveImageSureHBox().getChildren().addAll(livePaneImageSureButton);
                }

                /// 动图壁纸
                else if (file.getAbsolutePath().toLowerCase().endsWith(".gif")) {
                    BufferedImage[] bufferedImages  = GIFSystem.getInstance().turnGIFToBufferedImages(file.getAbsolutePath());
                    BufferedImage[] fitBufferedImages = bufferedImages.clone();
                    for (int i = 0; i < fitBufferedImages.length; i++) {
                        if (fitBufferedImages[i].getWidth() > dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get()) {
                            int width = (int) dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get();
                            int height = (int) (fitBufferedImages[i].getHeight() * (dailyPaperElement.getLiveImageShowHBox().maxWidthProperty().get() / fitBufferedImages[i].getWidth()));
                            fitBufferedImages[i] = ImageSystem.getInstance().setBufferedImageSize(fitBufferedImages[i], width, height);
                        }
                        if (fitBufferedImages[i].getHeight() > dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get()) {
                            int height = (int) dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get();
                            int width = (int) (fitBufferedImages[i].getWidth() * (dailyPaperElement.getLiveImageShowHBox().maxHeightProperty().get() / fitBufferedImages[i].getHeight()));
                            fitBufferedImages[i] = ImageSystem.getInstance().setBufferedImageSize(fitBufferedImages[i], width, height);
                        }
                    }
                    Image[] images = new Image[fitBufferedImages.length];
                    for (int i = 0; i < fitBufferedImages.length; i++) images[i] = ImageSystem.getInstance().turnBufferedImageToFXImage(fitBufferedImages[i]);
                    Integer[] gifPlaySpeed = GIFSystem.getInstance().getGIFPlaySpeed(file.getAbsolutePath());

                    dailyPaperData.setIsLiveImageShowing(() -> true);
                    dailyPaper.getDailyPaperPool().submit(() -> {
                        do {
                            for (int i = 0; i < images.length; i++) {
                                dailyPaperElement.getLiveImageShowImageView().setImage(images[i]);
                                try {
                                    Thread.sleep(gifPlaySpeed[i]);
                                } catch (InterruptedException interruptedException) {
                                    throw new RuntimeException(interruptedException);
                                }
                                if (!dailyPaperData.getIsLiveImageShowing().get()) {
                                    dailyPaperElement.getLiveImageShowImageView().setImage(null);
                                    break;
                                }
                            }
                        } while (dailyPaperData.getIsLiveImageShowing().get());
                    });

                    Button livePaneImageSureButton = new Button("点击设为壁纸");
                    livePaneImageSureButton.setPrefWidth(fitBufferedImages[0].getWidth());
                    livePaneImageSureButton.setPrefHeight(dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().get());
                    livePaneImageSureButton.setOnAction(event -> {
                        dailyPaperFunction.clearLiveImageWallpaper();

                        /// 下载切片到本地
                        File imagesWallpaperFullFilePathFile = new File(dailyPaper.getLiveImageFilePath().get());
                        for (int i = 0; i < bufferedImages.length; i++) {
                            ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImages[i], imagesWallpaperFullFilePathFile.getAbsolutePath() + File.separator + i + ".png");
                        }
                        StringBuilder speed = new StringBuilder(String.valueOf(gifPlaySpeed[0]));
                        for (int i = 1; i < gifPlaySpeed.length; i++) {
                            speed.append(dailyPaper.getSplitMark().get()).append(gifPlaySpeed[i] / dailyPaper.getLiveImagePlaySpeed().get());
                        }
                        LiveImageConfig.getInstance().getLocalDB().set("Speed", speed.toString());
                        LiveImageConfig.getInstance().getLocalDB().commit();

                        dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置成功");
                        dailyPaperFunction.launchLiveImageWallpaper();
                    });
                    dailyPaperElement.getLiveImageSureHBox().getChildren().addAll(livePaneImageSureButton);
                }
            }
        });
    }

    @Override
    public void createDailyEvent() {
        dailyPaperElement.getDailyWallpaperGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dailyPaperElement.getDailyWallpaperOnButton()) {
                dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(true);
                dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(true);
                dailyPaper.setDailyAutoWallpaper(() -> true);
            } else {
                dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(false);
                dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(false);
                dailyPaper.setDailyAutoWallpaper(() -> false);
            }
        });

        dailyPaperElement.getDailyLaunchGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dailyPaperElement.getDailyLaunchOnButton()) {
                boolean isSystem = ComputerSystem.getInstance().addSystemAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isUser = ComputerSystem.getInstance().addUserAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isSuccess = isSystem || isUser;
                if (isSuccess) {
                    dailyPaper.setDailyPaperAutoLaunch(() -> true);
                } else {
                    dailyPaperElement.getDailyLaunchOffButton().setSelected(true);
                }
            } else {
                boolean isSystem = ComputerSystem.getInstance().deleteSystemAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isUser = ComputerSystem.getInstance().deleteUserAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isSuccess = isSystem || isUser;
                if (isSuccess) {
                    dailyPaper.setDailyPaperAutoLaunch(() -> false);
                } else {
                    dailyPaperElement.getDailyLaunchOnButton().setSelected(true);
                }
            }
        });

        dailyPaperElement.getDailyLaunchWallpaperGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> dailyPaper.setDailyAutoWallpaperWhenLaunch(() -> dailyPaperElement.getDailyLaunchWallpaperOnButton().isSelected()));

        dailyPaperElement.getDailyTimeWallpaperTextField().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> dailyPaper.getDailyPaperPool().submit(() -> {
            dailyPaperFunction.waitADelay(10);
            int delay = 0;
            try {
                delay = Integer.parseInt(dailyPaperElement.getDailyTimeWallpaperTextField().getText());
                delay = Math.max(delay, 0);
            } catch (NumberFormatException ignored) {}
            int finalDelay = delay;
            dailyPaper.setDailyAutoWallpaperWhenTime(() -> finalDelay);

            if (DailyPaper.getInstance().getDailyAutoWallpaper().get() && DailyPaper.getInstance().getDailyAutoWallpaperWhenTime().get() >= 1) {
                if (!dailyPaperData.getIsAutoDailyWallpaperRunning().get()) {
                    dailyPaperData.setAutoDailyWallpaperStartTime(System.currentTimeMillis());
                    dailyPaper.getScheduledExecutorService().scheduleAtFixedRate(dailyPaperData.getAutoDailyWallpaper(), 1, 1, TimeUnit.MINUTES);
                    dailyPaperData.setIsAutoDailyWallpaperRunning(() -> true);
                }
            }
        }));

        dailyPaperElement.getDailyHobbyTextArea().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> dailyPaper.getDailyPaperPool().submit(() -> {
            dailyPaperFunction.waitADelay(10);
            dailyPaper.setDailyImageHobby(() -> dailyPaperElement.getDailyHobbyTextArea().getText());
        }));
    }

    @Override
    public void createHobbyEvent() {

    }

    @Override
    public void createSettingEvent() {

    }

    @Override
    public void createRenewEvent() {
        dailyPaperElement.getRenewManualCheckRenewButton().setOnAction(actionEvent -> dailyPaperFunction.checkRenewDailyPaper());

        dailyPaperElement.getRenewManualDownloadRenewButton().setOnAction(actionEvent -> dailyPaperFunction.downloadRenewDailyPaper());

        dailyPaperElement.getRenewAutoCheckRenewGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dailyPaperElement.getRenewAutoCheckRenewOnButton()) {
                dailyPaper.setRenewAutoCheckRenew(() -> true);
            } else {
                if (dailyPaperElement.getRenewAutoDownloadRenewOnButton().isSelected()) {
                    dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
                } else {
                    dailyPaper.setRenewAutoCheckRenew(() -> false);
                }
            }
        });
        dailyPaperElement.getRenewAutoDownloadRenewGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue ==  dailyPaperElement.getRenewAutoDownloadRenewOnButton()) {
                dailyPaper.setRenewAutoDownloadRenew(() -> true);
                if (!dailyPaperElement.getRenewAutoCheckRenewOnButton().isSelected()) {
                    dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
                    dailyPaper.setRenewAutoCheckRenew(() -> true);
                }
            } else {
                dailyPaper.setRenewAutoDownloadRenew(() -> false);
            }
        });
    }

}