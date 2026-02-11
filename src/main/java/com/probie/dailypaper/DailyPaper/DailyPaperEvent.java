package com.probie.dailypaper.DailyPaper;

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
import javafx.stage.DirectoryChooser;
import javafx.scene.input.ClipboardContent;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperEvent;

public class DailyPaperEvent implements IDailyPaperEvent {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperEvent INSTANCE;

    /**
     * 懒加载的工具类单例对象
     * */
    private DailyPaper dailyPaper;
    private DailyPaperElement dailyPaperElement;
    private DailyPaperStyle dailyPaperStyle;

    /**
     * 动态变量
     * */
    private Supplier<Double> mouseStartX;
    private Supplier<Double> mouseStartY;

    @Override
    public void createEvent() {

        /// 初始化工具类单例对象
        if (dailyPaper == null) {
            dailyPaper = DailyPaper.getInstance();
        }
        if (dailyPaperElement == null) {
            dailyPaperElement = DailyPaperElement.getInstance();
        }
        if (dailyPaperStyle == null) {
            dailyPaperStyle = DailyPaperStyle.getInstance();
        }

        /// 创建 Stage 舞台事件
        createStageEvent();

        /// 创建 Scene 帷幕事件
        createSceneEvent();

        /// 创建 RootPane 分页事件
        createRootPaneEvent();

        /// 创建 ChatPane 分页事件
        createChatPaneEvent();

        /// 创建 LivePane 分页事件
        createLivePaneEvent();

    }

    @Override
    public void createStageEvent() {

    }

    @Override
    public void createSceneEvent() {

    }

    @Override
    public void createRootPaneEvent() {
        /// 创建 RootPane 控件事件
        /// 拖拽窗口
        dailyPaperElement.getRootPaneTitleBar().setOnMousePressed(mouseEvent -> {
            mouseStartX = mouseEvent::getScreenX;
            mouseStartY = mouseEvent::getScreenY;
            mouseEvent.consume();
        });
        dailyPaperElement.getRootPaneTitleBar().setOnMouseDragged(mouseEvent -> {
            dailyPaperElement.getStage().setX(dailyPaperElement.getStage().getX() + (mouseEvent.getScreenX() - mouseStartX.get()));
            dailyPaperElement.getStage().setY(dailyPaperElement.getStage().getY() + (mouseEvent.getScreenY() - mouseStartY.get()));
            mouseStartX = mouseEvent::getScreenX;
            mouseStartY = mouseEvent::getScreenY;
            mouseEvent.consume();
        });

        /// 最小化 最大化 关闭
        dailyPaperElement.getRootPaneTileBarMinButton().setOnAction(actionEvent -> dailyPaperElement.getStage().setIconified(true));
        dailyPaperElement.getRootPaneTitleBarMaxButton().setOnAction(actionEvent -> {
            dailyPaperElement.getStage().setMaximized(!dailyPaperElement.getStage().isMaximized());
            dailyPaperElement.getRootPaneTitleBarMaxButton().setText(dailyPaperElement.getStage().isMaximized() ? "⊟" : "□");
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getChatPane()) {
                dailyPaperStyle.createChatPaneStyle();
            }
        });
        dailyPaperElement.getRootPaneTitleBarCloseButton().setOnAction(actionEvent -> {
            dailyPaperElement.getAgentConnectPool().shutdown();
            dailyPaperElement.getImagesShowPool().shutdown();
//            dailyPaperElement.getImagesWallpaperPool().shutdown();
            dailyPaper.getPool().shutdown();

            dailyPaperElement.getStage().close();
        });

        /// 菜单选择
        dailyPaperElement.getRootPaneMenuBarChatButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getChatPane()) {
                clearChatPane();
            }
            changeCenterPane(dailyPaperElement.getChatPane());
        });

        dailyPaperElement.getRootPaneMenuBarLiveButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getLivePane()) {
                clearLivePane();
            }
            changeCenterPane(dailyPaperElement.getLivePane());
        });

        dailyPaperElement.getRootPaneMenuBarDailyButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getDailyPane()) {
                clearDailyPane();
            }
            changeCenterPane(dailyPaperElement.getDailyPane());
        });

        dailyPaperElement.getRootPaneMenuBarHobbyButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getHobbyPane()) {
                clearHobbyPane();
            }
            changeCenterPane(dailyPaperElement.getHobbyPane());
        });

        dailyPaperElement.getRootPaneMenuBarSettingButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getSettingPane()) {
                clearSettingPane();
            }
            changeCenterPane(dailyPaperElement.getSettingPane());
        });

        dailyPaperElement.getRootPaneMenuBarRenewButton().setOnAction(actionEvent -> {
            if (dailyPaperElement.getRootPaneCenterPane().getChildren().getFirst() == dailyPaperElement.getRenewPane()) {
                clearRenewPane();
            }
            changeCenterPane(dailyPaperElement.getRenewPane());
        });
    }

    @Override
    public void createChatPaneEvent() {
        /// 创建 ChatPane 控件事件
        dailyPaperElement.getChatPaneTextInputArea().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (keyEvent.isShiftDown() || keyEvent.isControlDown() || keyEvent.isAltDown()) {
                    /// 用户
                    VBox chatPaneUserMessageVBox = new VBox();
                    Label chatPaneUserMessageLabel = new Label();

                    chatPaneUserMessageVBox.setMinHeight(Region.USE_COMPUTED_SIZE);
                    chatPaneUserMessageVBox.setAlignment(Pos.CENTER_RIGHT);
                    chatPaneUserMessageVBox.setFillWidth(true);

                    chatPaneUserMessageLabel.maxWidthProperty().bind(chatPaneUserMessageVBox.widthProperty().divide(2.0).subtract(dailyPaperElement.getOffset().get() * 2.0));
                    chatPaneUserMessageLabel.setFont(new Font(dailyPaperElement.getChatPaneTextShowAreaFontSize().get()));
                    chatPaneUserMessageLabel.setText(dailyPaperElement.getChatPaneTextInputArea().getText());
                    chatPaneUserMessageLabel.setPadding(new Insets(10));
                    chatPaneUserMessageLabel.setBorder(new Border(new BorderStroke(
                            Color.PURPLE,
                            BorderStrokeStyle.SOLID,
                            new CornerRadii(10),
                            new BorderWidths(2)
                    )));
                    chatPaneUserMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                    chatPaneUserMessageLabel.setAlignment(Pos.CENTER_RIGHT);
                    chatPaneUserMessageLabel.setWrapText(true);

                    chatPaneUserMessageVBox.getChildren().addAll(chatPaneUserMessageLabel);
                    dailyPaperElement.getChatPaneMessageVBox().getChildren().addAll(chatPaneUserMessageVBox);
                    dailyPaperElement.getChatPaneUserMessageArrayList().add(chatPaneUserMessageLabel.getText());
                    scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());

                    /// AI
                    VBox chatPaneAgentMessageVBox = new VBox();
                    Label chatPaneAgentMessageLabel = new Label();

                    chatPaneAgentMessageVBox.setMinHeight(Region.USE_COMPUTED_SIZE);
                    chatPaneAgentMessageVBox.setAlignment(Pos.CENTER_LEFT);
                    chatPaneAgentMessageVBox.setFillWidth(true);

                    chatPaneAgentMessageLabel.maxWidthProperty().bind(chatPaneAgentMessageVBox.widthProperty().divide(2.0).subtract(dailyPaperElement.getOffset().get() * 2.0));
                    chatPaneAgentMessageLabel.setFont(new Font(dailyPaperElement.getChatPaneTextShowAreaFontSize().get()));
                    chatPaneAgentMessageLabel.setPadding(new Insets(10));
                    chatPaneAgentMessageLabel.setBorder(new Border(new BorderStroke(
                            Color.GOLD,
                            BorderStrokeStyle.SOLID,
                            new CornerRadii(10),
                            new BorderWidths(2)
                    )));
                    chatPaneAgentMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                    chatPaneAgentMessageLabel.setText("...");
                    chatPaneAgentMessageLabel.setAlignment(Pos.CENTER_LEFT);
                    chatPaneAgentMessageLabel.setWrapText(true);
                    chatPaneAgentMessageLabel.setContentDisplay(ContentDisplay.TOP);

                    chatPaneAgentMessageVBox.getChildren().addAll(chatPaneAgentMessageLabel);
                    dailyPaperElement.getChatPaneMessageVBox().getChildren().addAll(chatPaneAgentMessageVBox);
                    scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                    dailyPaperElement.getChatPaneTextInputArea().clear();

                    /// 调用 API
                    dailyPaperElement.getAgentConnectPool().submit(() -> {
                        /// 收集上下文
                        try {
                            Platform.runLater(() -> chatPaneAgentMessageLabel.setText("收集上下文..."));
                            String prompt = "";
                            if (dailyPaperElement.getChatPaneUserMessageArrayList().size() > 1) {
                                for (int i = dailyPaperElement.getChatPaneUserMessageArrayList().size() - 1 - 1; i >= 0 && prompt.length() <= 10000; i--) {
                                    if (dailyPaperElement.getChatPaneAgentMessageArrayList().size() >= i + 1) {
                                        prompt = "\nAI：%s".formatted(dailyPaperElement.getChatPaneAgentMessageArrayList().get(i)) + prompt;
                                    }
                                    prompt = "\n用户：%s".formatted(dailyPaperElement.getChatPaneUserMessageArrayList().get(i)) + prompt;
                                }
                                prompt = "\n*历史对话记录：%s".formatted(prompt);
                            }
                            prompt = "*信息背景：\n%s".formatted(dailyPaperElement.getPromptInformationPrompt().get() + prompt);
                            prompt += "\n*用户现在需求：\n%s".formatted(dailyPaperElement.getChatPaneUserMessageArrayList().getLast());

                            Platform.runLater(() -> chatPaneAgentMessageLabel.setText("需求分析中..."));
                            String[] ifImage = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(dailyPaperElement.getPromptIfImagePrompt().get() + prompt);

                            /// 文本
                            if (!ifImage[0].contains("是")) {
                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText("文本生成中..."));
                                String[] result = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(prompt);
                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText(result[0]));

                                Platform.runLater(() -> {
                                    HBox chatPaneAgentMessageCopyTextButtonBar = new HBox();
                                    Button button = new Button("点击复制文本");

                                    chatPaneAgentMessageCopyTextButtonBar.setMaxWidth(chatPaneAgentMessageLabel.widthProperty().get());

                                    button.setOnAction(actionEvent -> {
                                        Clipboard clipboard = Clipboard.getSystemClipboard();
                                        ClipboardContent clipboardContent = new ClipboardContent();
                                        clipboardContent.putString(chatPaneAgentMessageLabel.getText());
                                        clipboard.setContent(clipboardContent);
                                    });
                                    button.setMinWidth(dailyPaperElement.getChatPaneButtonWidth().get());
                                    if (dailyPaperElement.getChatPaneButtonWidth().get() > chatPaneAgentMessageLabel.widthProperty().get()) {
                                        button.setMinWidth(chatPaneAgentMessageLabel.widthProperty().get());
                                    }
                                    button.setMinHeight(dailyPaperElement.getChatPaneButtonHeight().get());

                                    chatPaneAgentMessageCopyTextButtonBar.getChildren().addAll(button);
                                    chatPaneAgentMessageVBox.getChildren().addAll(chatPaneAgentMessageCopyTextButtonBar);
                                });

                                scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                                dailyPaperElement.getChatPaneAgentMessageArrayList().add(result[0]);
                            }

                            /// 图片
                            else {
                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText("图片生成中..."));

                                String result = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(dailyPaperElement.getPromptSpawnImageResultPrompt().get() + prompt)[0];

                                HBox chatPaneAgentMessageSetWallpaperButtonBar = new HBox();
                                HBox chatPaneAgentMessageDownloadImageButtonBar = new HBox();
                                DirectoryChooser directoryChooser = new DirectoryChooser();
                                directoryChooser.setInitialDirectory(new File(dailyPaper.getRootPath().get()));

                                chatPaneAgentMessageSetWallpaperButtonBar.setMaxWidth(chatPaneAgentMessageLabel.maxWidthProperty().get());
                                chatPaneAgentMessageDownloadImageButtonBar.setMaxWidth(chatPaneAgentMessageLabel.maxWidthProperty().get());

                                prompt = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(dailyPaperElement.getPromptSpawnImagePrompt().get() + prompt)[0];
                                String[] imageURIs = dailyPaper.getTextToImageAIAgentSiliconFlow().turnTextToImage(prompt);
                                for (int i = 0; i < imageURIs.length; i++) {
                                    BufferedImage bufferedImage = dailyPaper.getImageSystem().turnUrlToBufferedImage(imageURIs[i]);
                                    bufferedImage = dailyPaper.getImageSystem().setBufferedImageSize(bufferedImage, (int) ((chatPaneAgentMessageLabel.maxWidthProperty().get())), (int) ((double) Integer.parseInt(dailyPaper.getImageSize().get().split("x")[1]) * (((chatPaneAgentMessageLabel.maxWidthProperty().get())) / (double) Integer.parseInt(dailyPaper.getImageSize().get().split("x")[0]))));
                                    ImageView imageView = new ImageView(dailyPaper.getImageSystem().turnBufferedImageToFXImage(bufferedImage));
                                    int index = i;
                                    BufferedImage finalBufferedImage = bufferedImage;
                                    Platform.runLater(() -> {
                                        chatPaneAgentMessageVBox.getChildren().addAll(imageView);
                                        Button setWallPaperButton = new Button("点击设为壁纸");
                                        Button downLoadImageButton = new Button("点击下载图片");
                                        if (imageURIs.length > 1) {
                                            setWallPaperButton.setText("点击设置图片%d为壁纸".formatted(index + 1));
                                            downLoadImageButton.setText("点击下图片%d到本地".formatted(index + 1));
                                        }
                                        setWallPaperButton.setOnAction(actionEvent -> {
                                            clearLiveImageWallpaper();
                                            dailyPaper.getImageSystem().turnBufferedImageToLocalFile(dailyPaper.getImageSystem().turnUrlToBufferedImage(imageURIs[index]), dailyPaper.getRootPath().get(), dailyPaper.getImageFileName().get());
                                            dailyPaper.getImageSystem().setWallPaper(dailyPaper.getRootPath().get(), dailyPaper.getImageFileName().get());
                                        });

                                        downLoadImageButton.setOnAction(actionEvent -> {
                                            File file = directoryChooser.showDialog(dailyPaperElement.getStage());
                                            if (file != null) {
                                                int count = 0;
                                                while (new File(file.getAbsolutePath()+File.separator+count+".png").exists()) count++;
                                                dailyPaper.getImageSystem().turnBufferedImageToLocalFile(finalBufferedImage, file.getAbsolutePath()+File.separator+count+".png");
                                            }
                                        });
                                        setWallPaperButton.setMinWidth(dailyPaperElement.getChatPaneButtonWidth().get());
                                        downLoadImageButton.setMinWidth(dailyPaperElement.getChatPaneButtonWidth().get());
                                        if (imageURIs.length * dailyPaperElement.getChatPaneButtonWidth().get() > chatPaneAgentMessageLabel.maxWidthProperty().get()) {
                                            setWallPaperButton.setMinWidth(chatPaneAgentMessageLabel.widthProperty().get() / imageURIs.length);
                                            downLoadImageButton.setMinWidth(chatPaneAgentMessageLabel.widthProperty().get() / imageURIs.length);
                                        }
                                        setWallPaperButton.setMinHeight(dailyPaperElement.getChatPaneButtonHeight().get());
                                        downLoadImageButton.setMinHeight(dailyPaperElement.getChatPaneButtonHeight().get());
                                        chatPaneAgentMessageSetWallpaperButtonBar.getChildren().addAll(setWallPaperButton);
                                        chatPaneAgentMessageDownloadImageButtonBar.getChildren().addAll(downLoadImageButton);
                                    });
                                }

                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText(result));
                                Platform.runLater(() -> chatPaneAgentMessageVBox.getChildren().addAll(chatPaneAgentMessageSetWallpaperButtonBar, chatPaneAgentMessageDownloadImageButtonBar));
                                scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                                dailyPaperElement.getChatPaneAgentMessageArrayList().add(result);
                            }
                        } catch (Exception exception) {
                            Platform.runLater(() -> chatPaneAgentMessageLabel.setText("不小心出错了:\n" + exception));
                            scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void createLivePaneEvent() {
        dailyPaperElement.getLivePaneImageChooseButton().setOnAction(actionEvent -> {
            File file = dailyPaperElement.getLivePaneImageFileChooser().showOpenDialog(dailyPaperElement.getStage());
            clearLivePane();
            if (file != null) {
                dailyPaperElement.getLivePaneImageChooseLabel().setText(file.getAbsolutePath());
                try {
                    Thread.sleep(dailyPaperElement.getDelay().get());
                } catch (InterruptedException interruptedException) {
                    throw new RuntimeException(interruptedException);
                }

                /// 图片
                if (file.getAbsolutePath().toLowerCase().endsWith(".png") || file.getAbsolutePath().toLowerCase().endsWith(".jpg") || file.getAbsolutePath().toLowerCase().endsWith(".jpeg")) {
                    BufferedImage bufferedImage = dailyPaper.getImageSystem().turnLocalFileToBufferedImage(file.getAbsolutePath());
                    dailyPaperElement.getLivePaneImageShowImageView().setImage(dailyPaper.getImageSystem().turnBufferedImageToFXImage(bufferedImage));

                    Button button = new Button("设为壁纸");
                    button.setMinWidth(bufferedImage.getWidth());
                    button.setMinHeight(dailyPaperElement.getLivePaneImageSureHBox().prefHeightProperty().get());
                    button.setOnAction(event -> {
                        clearLiveImageWallpaper();
                        dailyPaper.getImageSystem().setWallPaper(file.getAbsolutePath());
                    });
                    dailyPaperElement.getLivePaneImageSureHBox().getChildren().addAll(button);
                }

                /// GIF
                else if (file.getAbsolutePath().toLowerCase().endsWith(".gif")) {
                    BufferedImage[] bufferedImages = dailyPaper.getGIFSystem().turnGIFToBufferedImages(file.getAbsolutePath());
                    Image[] images = new Image[bufferedImages.length];
                    for (int i = 0; i < bufferedImages.length; i++) images[i] = dailyPaper.getImageSystem().turnBufferedImageToFXImage(bufferedImages[i]);
                    Integer[] gifPlaySpeed = dailyPaper.getGIFSystem().getGIFPlaySpeed(file.getAbsolutePath());

                    dailyPaperElement.setLivePaneImagesShowing(() -> true);
                    dailyPaperElement.getImagesShowPool().submit(() -> {
                        do {
                            for (int i = 0; i < images.length; i++) {
                                dailyPaperElement.getLivePaneImageShowImageView().setImage(images[i]);
                                try {
                                    Thread.sleep(gifPlaySpeed[i]);
                                } catch (InterruptedException interruptedException) {
                                    throw new RuntimeException(interruptedException);
                                }
                                if (!dailyPaperElement.getLivePaneImagesShowing().get()) {
                                    dailyPaperElement.getLivePaneImageShowImageView().setImage(null);
                                    break;
                                }
                            }
                        } while (dailyPaperElement.getLivePaneImagesShowing().get());
                    });

                    Button button = new Button("设为壁纸");
                    button.setMinWidth(bufferedImages[0].getWidth());
                    button.setMinHeight(dailyPaperElement.getLivePaneImageSureHBox().prefHeightProperty().get());
                    button.setOnAction(event -> {
                        clearLiveImageWallpaper();

                        /// 下载切片到本地
                        File imagesWallpaperFullFilePathFile = new File(dailyPaper.getRootPath().get()+File.separator+dailyPaper.getImagesWallpaperFileName().get());
                        if (imagesWallpaperFullFilePathFile.exists()) imagesWallpaperFullFilePathFile.delete();
                        imagesWallpaperFullFilePathFile.mkdirs();
                        for (int i = 0; i < bufferedImages.length; i++) {
                            dailyPaper.getImageSystem().turnBufferedImageToLocalFile(bufferedImages[i], imagesWallpaperFullFilePathFile.getAbsolutePath()+File.separator+i+".png");
                        }
                        String speed = String.valueOf(gifPlaySpeed[0]);
                        for (int i = 1; i < gifPlaySpeed.length; i++) {
                            speed += dailyPaper.getSplitMark().get() + gifPlaySpeed[i];
                        }
                        dailyPaper.getConfig().getLiveImageConfig().getLocalDB().set("Speed", speed);

                        /// 获取本地切片数据
                        String[] speeds = dailyPaper.getConfig().getLiveImageConfig().getLocalDB().get("Speed").toString().split(dailyPaper.getSplitMark().get());
                        int[] imagesWallpaperSpeed = new int[speeds.length];
                        for (int i = 0; i < speeds.length; i++) {
                            imagesWallpaperSpeed[i] = Integer.parseInt(speeds[i]);
                        }
                        String[] imagesWallPaperFullFilePath = new String[imagesWallpaperSpeed.length];
                        for (int i = 0; i < imagesWallpaperSpeed.length; i++) {
                            imagesWallPaperFullFilePath[i] = new File(dailyPaper.getImagesWallpaperFilePath().get()+File.separator+dailyPaper.getImagesWallpaperFileName().get()+File.separator+i+".png").getAbsolutePath();
                        }

                        /// 循环播放切片
                        dailyPaperElement.getImagesWallpaperPool().submit(() -> {
                            dailyPaperElement.setLivePaneImagesWallPerShowing(() -> true);
                            do {
                                for (int i = 0; i < imagesWallPaperFullFilePath.length; i++) {
                                    dailyPaper.getImageSystem().setWallPaper(imagesWallPaperFullFilePath[i]);
                                    try {
                                        Thread.sleep(imagesWallpaperSpeed[i]);
                                    } catch (InterruptedException interruptedException) {
                                        throw new RuntimeException(interruptedException);
                                    }
                                    if (!dailyPaperElement.getLivePaneImagesWallPerShowing().get()) {
                                        break;
                                    }
                                }
                            } while (dailyPaperElement.getLivePaneImagesWallPerShowing().get());
                        });
                    });
                    dailyPaperElement.getLivePaneImageSureHBox().getChildren().addAll(button);
                }
            }
        });
    }

    @Override
    public void createDailyPaneEvent() {

    }

    @Override
    public void createHobbyPaneEvent() {

    }

    @Override
    public void createSettingPaneEvent() {

    }

    @Override
    public void createRenewPaneEvent() {

    }

    @Override
    public void clearChatPane() {
        dailyPaperElement.getChatPaneTextInputArea().clear();
        dailyPaperElement.getChatPaneMessageVBox().getChildren().clear();
        dailyPaperElement.getChatPaneUserMessageArrayList().clear();
        dailyPaperElement.getChatPaneAgentMessageArrayList().clear();
    }

    @Override
    public void clearLivePane() {
        dailyPaperElement.getLivePaneImageChooseLabel().setText("");
        dailyPaperElement.setLivePaneImagesShowing(() -> false);
        dailyPaperElement.getLivePaneImageShowImageView().setImage(null);
        dailyPaperElement.getLivePaneImageSureHBox().getChildren().clear();
    }

    @Override
    public void clearDailyPane() {

    }

    @Override
    public void clearHobbyPane() {

    }

    @Override
    public void clearSettingPane() {

    }

    @Override
    public void clearRenewPane() {

    }

    @Override
    public void changeCenterPane(Pane pane) {
        dailyPaperElement.getRootPaneCenterPane().getChildren().clear();
        dailyPaperElement.getRootPaneCenterPane().getChildren().addAll(pane);
    }

    @Override
    public void scrollToBottom(ScrollPane scrollPane) {
        ScrollBar verticalScrollBar = (ScrollBar) scrollPane.lookup(".scroll-bar:vertical");
        if (verticalScrollBar != null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
            Platform.runLater(() -> verticalScrollBar.setValue(verticalScrollBar.getMax()));
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
            Platform.runLater(() -> scrollPane.setVvalue(1.0));
        }
    }

    @Override
    public void clearLiveImageWallpaper() {
        if (dailyPaperElement.getLivePaneImagesWallPerShowing().get()) {
            dailyPaperElement.setLivePaneImagesWallPerShowing(() -> false);
            try {
                Thread.sleep(dailyPaperElement.getDelay().get());
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
        }
    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperEvent getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperEvent();
        }
        return INSTANCE;
    }

}