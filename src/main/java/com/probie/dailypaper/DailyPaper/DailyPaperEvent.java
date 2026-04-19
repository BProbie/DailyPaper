package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
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
import com.probie.dailypaper.Config.*;
import com.probie.dailypaper.Plugin.Plugin;
import javafx.scene.input.ClipboardContent;
import com.probie.dailypaper.System.GIFSystem;
import com.probie.dailypaper.System.ImageSystem;
import com.probie.dailypaper.System.NetworkSystem;
import com.probie.dailypaper.System.ComputerSystem;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperEvent;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToTextAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.TextToImageAIAgentSiliconFlow;
import com.probie.dailypaper.AIAgent.SiliconFlow.Analysis.TextAIAgentSiliconFlowAnalysis;
import com.probie.dailypaper.AIAgent.SiliconFlow.Analysis.ImageAIAgentSiliconFlowAnalysis;

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
        createParamEvent();
        createSettingEvent();
        createRenewEvent();

    }

    @Override
    public void createStageEvent() {
        /// 响应式 stage 长宽
        dailyPaper.getDailyPaperStageWidth().addListener((observable, oldValue, newValue) -> {
            try {
                dailyPaper.getDailyPaperStageWidth().set(newValue);
                dailyPaperElement.getStage().setWidth(Math.clamp(Integer.parseInt(String.valueOf(dailyPaper.getDailyPaperStageWidth().get())), 500, 2000));
            } catch (NumberFormatException ignored) {}
        });

        dailyPaper.getDailyPaperStageHeight().addListener((observable, oldValue, newValue) -> {
            try {
                dailyPaper.getDailyPaperStageHeight().set(newValue);
                dailyPaperElement.getStage().setHeight(Math.clamp(Integer.parseInt(String.valueOf(dailyPaper.getDailyPaperStageHeight().get())), 500, 2000));
            } catch (NumberFormatException ignored) {}
        });
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
                        chatUserMessageLabel.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
                        chatUserMessageLabel.setAlignment(Pos.CENTER_RIGHT);
                        chatUserMessageLabel.setWrapText(true);

                        chatUserMessageLabel.setPadding(new Insets(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get()))));
                        chatUserMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                        chatUserMessageLabel.setBorder(new Border(new BorderStroke(
                                Color.PURPLE,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get()))),
                                new BorderWidths(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmallSmall().get())))
                        )));

                        chatUserMessageContent.getChildren().addAll(chatUserMessageLabel);
                        chatUserMessageBar.getChildren().addAll(chatUserMessageContent);
                        dailyPaperElement.getChatTextShowMessageVBox().getChildren().addAll(chatUserMessageBar);
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
                        chatAgentMessageLabel.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
                        chatAgentMessageLabel.setAlignment(Pos.CENTER_LEFT);
                        chatAgentMessageLabel.setWrapText(true);

                        chatAgentMessageLabel.setPadding(new Insets(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get()))));
                        chatAgentMessageLabel.setBackground(Background.fill(Color.LIGHTGRAY));
                        chatAgentMessageLabel.setBorder(new Border(new BorderStroke(
                                Color.GOLD,
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmall().get()))),
                                new BorderWidths(Integer.parseInt(String.valueOf(dailyPaperData.getSpacingSizeSmallSmall().get())))
                        )));

                        chatAgentMessageContent.getChildren().addAll(chatAgentMessageLabel);
                        chatAgentMessageBar.getChildren().addAll(chatAgentMessageContent);
                        dailyPaperElement.getChatTextShowMessageVBox().getChildren().addAll(chatAgentMessageBar);

                        dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextInputScrollPane());
                        dailyPaperElement.getChatTextInputTextArea().clear();

                        if (chatUserMessageLabel.getText().length() <= 10000) {
                            /// AI 回复
                            dailyPaper.getDailyPaperPool().submit(() -> {
                                /// 解析输入
                                Platform.runLater(() -> chatAgentMessageLabel.setText("解析输入中..."));
                                StringBuilder currentText = new StringBuilder(chatUserMessageLabel.getText());
                                if (String.valueOf(currentText).contains(String.valueOf(dailyPaper.getUploadImageFullFilePathMark().get()))) {
                                    String[] texts = String.valueOf(currentText).split(String.valueOf(dailyPaper.getUploadImageFullFilePathMark().get()), -1);
                                    for (int i = 0; i < texts.length; i++) {
                                        File currentFile = new File(texts[i]);
                                        String format = currentFile.getName().contains(".") ? currentFile.getName().toLowerCase().substring(currentFile.getName().lastIndexOf(".")) : currentFile.getName().toLowerCase();
                                        if (currentFile.exists() && ! currentFile.isDirectory() && dailyPaperData.getSupportImageFormat().contains(format)) {
                                            String[] imageData = ImageAIAgentSiliconFlowAnalysis.getInstance().analysisImage(currentFile.getAbsolutePath(), String.valueOf(dailyPaperData.getPromptDelineateImagePrompt().get()));
                                            String imageDelineate = imageData[0].isEmpty() ? imageData[1].isEmpty() ? "无" : imageData[1] : imageData[0];
                                            texts[i] = texts[i] + "(文件内容：" + imageDelineate + ")";
                                        }
                                    }
                                    currentText = new StringBuilder(String.join(String.valueOf(dailyPaper.getUploadImageFullFilePathMark().get()), texts));
                                }
                                dailyPaperData.getChatUserMessageArrayList().add(String.valueOf(currentText));

                                /// 收集上下文
                                try {
                                    Platform.runLater(() -> chatAgentMessageLabel.setText("收集上下文..."));

                                    /// 背景信息
                                    StringBuilder information = new StringBuilder();
                                    if (dailyPaperData.getChatUserMessageArrayList().size() > 1) {
                                        for (int i = dailyPaperData.getChatUserMessageArrayList().size() - 1 - 1; i >= 0 && information.length() <= 10000; i--) {
                                            if (dailyPaperData.getChatAgentMessageArrayList().size() >= i + 1) {
                                                information = new StringBuilder("\nAI：%s".formatted(dailyPaperData.getChatAgentMessageArrayList().get(i)) + information);
                                            }
                                            information = new StringBuilder("\n用户：%s".formatted(dailyPaperData.getChatUserMessageArrayList().get(i)) + information);
                                        }
                                        information = new StringBuilder("\n*历史对话记录：%s".formatted(information));
                                    }
                                    information = new StringBuilder("*信息背景：\n%s".formatted(String.valueOf(dailyPaperData.getPromptInformationPrompt().get()) + information));
                                    information.append("\n*用户现在需求：\n%s");

                                    /// 当前内容
                                    StringBuilder current = new StringBuilder(dailyPaperData.getChatUserMessageArrayList().getLast());

                                    /// 合成上下文
                                    StringBuilder content = new StringBuilder(String.valueOf(information).formatted(current));
                                    if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDebug().get()))) {
                                        String message = "Content" + "\n" + content;
                                        LogConfig.getInstance().debug(message);
                                    }

                                    Platform.runLater(() -> chatAgentMessageLabel.setText("分析需求中..."));
                                    String[] ifImage = TextAIAgentSiliconFlowAnalysis.getInstance().analysisText(String.valueOf(dailyPaperData.getPromptIfImagePrompt().get()) + content);

                                    /// 生成文本
                                    if (!ifImage[0].contains("是")) {
                                        Platform.runLater(() -> chatAgentMessageLabel.setText("文本生成中..."));
                                        String[] result = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(String.valueOf(content));
                                        Platform.runLater(() -> chatAgentMessageLabel.setText(result[0].replaceAll("^[\\s\\n]+", "").replaceAll("[\\s\\n]+$", "")));

                                        Platform.runLater(() -> {
                                            HBox chatPaneAgentMessageCopyTextButtonBar = new HBox();
                                            Button chatPaneAgentMessageCopyTextButton = new Button("复制文本");

                                            chatPaneAgentMessageCopyTextButtonBar.setMaxWidth(chatAgentMessageLabel.widthProperty().get());

                                            chatPaneAgentMessageCopyTextButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));

                                            chatPaneAgentMessageCopyTextButton.setOnAction(actionEvent -> {
                                                ClipboardContent clipboardContent = new ClipboardContent();
                                                clipboardContent.putString(chatAgentMessageLabel.getText());
                                                Clipboard.getSystemClipboard().setContent(clipboardContent);
                                                dailyPaperFunction.showButtonInformation(chatPaneAgentMessageCopyTextButton, "复制成功");
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

                                        String[] result = TextAIAgentSiliconFlowAnalysis.getInstance().analysisText(String.valueOf(dailyPaperData.getPromptSpawnImageResultPrompt().get()) + content);

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

                                        String prompt = TextAIAgentSiliconFlowAnalysis.getInstance().analysisText(String.valueOf(dailyPaperData.getPromptSpawnImagePrompt().get()) + content)[0];
                                        String[] imageURIs = TextToImageAIAgentSiliconFlow.getInstance().turnTextToImage(prompt);
                                        for (int i = 0; i < imageURIs.length; i++) {
                                            BufferedImage bufferedImage = ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[i]);
                                            bufferedImage = ImageSystem.getInstance().setBufferedImageSize(bufferedImage, (int) ((chatAgentMessageContent.widthProperty().get())), (int) ((double) Integer.parseInt(String.valueOf(dailyPaper.getSpawnImageSize().get()).split("x")[1]) * (((chatAgentMessageContent.widthProperty().get())) / (double) Integer.parseInt(String.valueOf(dailyPaper.getSpawnImageSize().get()).split("x")[0]))));
                                            ImageView imageView = new ImageView(ImageSystem.getInstance().turnBufferedImageToFXImage(bufferedImage));
                                            int index = i;
                                            Platform.runLater(() -> {
                                                chatAgentMessageBar.getChildren().addAll(imageView);
                                                Button chatPaneAgentMessagesetWallPaperButton = new Button("设为壁纸");
                                                Button chatPaneAgentMessagedownLoadImageButton = new Button("下载图片");
                                                chatPaneAgentMessagesetWallPaperButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
                                                chatPaneAgentMessagedownLoadImageButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
                                                if (imageURIs.length > 1) {
                                                    chatPaneAgentMessagesetWallPaperButton.setText("设置图片%d为壁纸".formatted(index + 1));
                                                    chatPaneAgentMessagedownLoadImageButton.setText("下载图片%d到本地".formatted(index + 1));
                                                }
                                                chatPaneAgentMessagesetWallPaperButton.setOnAction(actionEvent -> {
                                                    dailyPaperFunction.clearLiveImageWallpaper();
                                                    if (ImageSystem.getInstance().turnBufferedImageToLocalFile(ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[index]), String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()))) {
                                                        dailyPaperFunction.showButtonInformation(chatPaneAgentMessagesetWallPaperButton, "设置成功");
                                                        ImageSystem.getInstance().setWallPaper(String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()));
                                                    } else {
                                                        dailyPaperFunction.showButtonInformation(chatPaneAgentMessagesetWallPaperButton, "设置失败");
                                                    }
                                                });

                                                chatPaneAgentMessagedownLoadImageButton.setOnAction(actionEvent -> {
                                                    fileChooser.setInitialDirectory(new File(String.valueOf(dailyPaper.getChatImageDownloadFilePath().get())));
                                                    fileChooser.setInitialFileName(String.valueOf(dailyPaper.getChatImageDownloadFileName().get()));
                                                    File file = fileChooser.showSaveDialog(dailyPaperElement.getStage());
                                                    if (file != null) {
                                                        dailyPaper.getChatImageDownloadFilePath().set(file.getParent());
                                                        dailyPaper.getChatImageDownloadFileName().set(file.getName());
                                                        if (ImageSystem.getInstance().turnBufferedImageToLocalFile(ImageSystem.getInstance().turnUrlToBufferedImage(imageURIs[index]), file.getAbsolutePath())) {
                                                            dailyPaperFunction.showButtonInformation(chatPaneAgentMessagedownLoadImageButton, "下载成功");
                                                        } else {
                                                            dailyPaperFunction.showButtonInformation(chatPaneAgentMessagedownLoadImageButton, "下载失败");
                                                        }
                                                    }
                                                });

                                                chatPaneAgentMessageSetWallpaperButtonBar.getChildren().addAll(chatPaneAgentMessagesetWallPaperButton);
                                                chatPaneAgentMessageDownloadImageButtonBar.getChildren().addAll(chatPaneAgentMessagedownLoadImageButton);
                                            });
                                        }

                                        Platform.runLater(() -> chatAgentMessageLabel.setText(result[0].replaceAll("^[\\s\\n]+", "").replaceAll("[\\s\\n]+$", "")));
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

                        /// 字数超限
                        else {
                            Platform.runLater(() -> chatAgentMessageLabel.setText("超出单次最高字数上限: " + 10000));
                            dailyPaperFunction.scrollToBottom(dailyPaperElement.getChatTextShowScrollPane());
                        }

                    }
                }
            }
        });

        dailyPaperElement.getChatTextInputToolsUploadImageButton().setOnAction(actionEvent -> {
            dailyPaperElement.getChatTextInputToolsUploadImageFileChooser().setInitialDirectory(new File(String.valueOf(dailyPaper.getChatTextInputToolsImageUploadImageChosenFilePath().get())));
            dailyPaperElement.getChatTextInputToolsUploadImageFileChooser().setInitialFileName(String.valueOf(dailyPaper.getChatTextInputToolsImageUploadImageChosenFileName().get()));
            File file = dailyPaperElement.getChatTextInputToolsUploadImageFileChooser().showOpenDialog(dailyPaperElement.getStage());
            if (file != null) {
                dailyPaper.getChatTextInputToolsImageUploadImageChosenFilePath().set(file.getParent());
                dailyPaper.getChatTextInputToolsImageUploadImageChosenFileName().set(file.getName());
                String format = file.getName().contains(".") ? file.getName().toLowerCase().substring(file.getName().lastIndexOf(".")) : file.getName().toLowerCase();
                if (dailyPaperData.getSupportImageFormat().contains(format)) {
                    dailyPaperElement.getChatTextInputTextArea().setText(dailyPaperElement.getChatTextInputTextArea().getText() + dailyPaper.getUploadImageFullFilePathMark().get() + file.getAbsolutePath() + dailyPaper.getUploadImageFullFilePathMark().get());
                } else {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getChatTextInputToolsUploadImageButton(), "仅能静图");
                }
            }
        });

    }

    @Override
    public void createLiveEvent() {
        dailyPaperElement.getLiveImageChooseButton().setOnAction(actionEvent -> {
            dailyPaperElement.getLiveImageChooseFileChooser().setInitialDirectory(new File(String.valueOf(dailyPaper.getLiveImageChosenFilePath().get())));
            File file = dailyPaperElement.getLiveImageChooseFileChooser().showOpenDialog(dailyPaperElement.getStage());
            if (file != null) {
                dailyPaperElement.getLiveImageChooseLabel().setText(file.getAbsolutePath());
                dailyPaper.getLiveImageChosenFilePath().set(file.getParent());
                dailyPaperFunction.waitADelay(10);

                /// 图片壁纸
                String format = file.getName().contains(".") ? file.getName().toLowerCase().substring(file.getName().lastIndexOf(".")) : file.getName().toLowerCase();
                if (dailyPaperData.getSupportImageFormat().contains(format)) {
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
                    dailyPaperFunction.clearLiveImage();
                    dailyPaperElement.getLiveImageShowImageView().setImage(ImageSystem.getInstance().turnBufferedImageToFXImage(fitBufferedImage));

                    Button livePaneImageSureButton = new Button("点击设为壁纸");
                    livePaneImageSureButton.setFont(new Font(Integer.parseInt(String.valueOf(dailyPaperData.getFontSizeMedium().get()))));
                    livePaneImageSureButton.setPrefWidth(fitBufferedImage.getWidth());
                    livePaneImageSureButton.setPrefHeight(dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().get());
                    livePaneImageSureButton.setOnAction(actionEvent1 -> {
                        if (ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImage, String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()))) {
                            dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置成功");
                            dailyPaperFunction.clearLiveImageWallpaper();
                            ComputerSystem.getInstance().setWallPaper(String.valueOf(dailyPaper.getTempFilePath().get()), String.valueOf(dailyPaper.getTempImageFileName().get()));
                        } else {
                            dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置失败");
                        }
                    });
                    dailyPaperElement.getLiveImageSureHBox().getChildren().clear();
                    dailyPaperElement.getLiveImageSureHBox().getChildren().addAll(livePaneImageSureButton);
                }

                /// 动图壁纸
                else if (dailyPaperData.getSupportLiveImageFormat().contains(format)) {
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

                    dailyPaperFunction.clearLiveImage();
                    dailyPaperData.getIsLiveImageShowing().set(true);
                    dailyPaper.getDailyPaperPool().submit(() -> {
                        do {
                            for (int i = 0; i < images.length; i++) {
                                dailyPaperElement.getLiveImageShowImageView().setImage(images[i]);
                                try {
                                    Thread.sleep(gifPlaySpeed[i]);
                                } catch (InterruptedException interruptedException) {
                                    throw new RuntimeException(interruptedException);
                                }
                                if (!Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveImageShowing().get()))) {
                                    dailyPaperElement.getLiveImageShowImageView().setImage(null);
                                    break;
                                }
                            }
                        } while (Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsLiveImageShowing().get())));
                    });

                    Button livePaneImageSureButton = new Button("点击设为壁纸");
                    livePaneImageSureButton.setPrefWidth(fitBufferedImages[0].getWidth());
                    livePaneImageSureButton.setPrefHeight(dailyPaperElement.getLiveImageSureHBox().prefHeightProperty().get());
                    livePaneImageSureButton.setOnAction(event -> {
                        dailyPaperFunction.clearLiveImageWallpaper();

                        /// 下载切片到本地
                        File imagesWallpaperFullFilePathFile = new File(String.valueOf(dailyPaper.getLiveImageFilePath().get()));
                        for (int i = 0; i < bufferedImages.length; i++) {
                            ImageSystem.getInstance().turnBufferedImageToLocalFile(bufferedImages[i], imagesWallpaperFullFilePathFile.getAbsolutePath() + File.separator + i + ".png");
                        }
                        StringBuilder speed = new StringBuilder(String.valueOf(gifPlaySpeed[0]));
                        for (int i = 1; i < gifPlaySpeed.length; i++) {
                            speed.append(dailyPaper.getSplitMark().get()).append(gifPlaySpeed[i]);
                        }
                        LiveImageConfig.getInstance().getLocalDB().set("Speed", String.valueOf(speed));
                        LiveImageConfig.getInstance().getLocalDB().commit();

                        dailyPaperFunction.showButtonInformation(livePaneImageSureButton, "壁纸设置成功");
                        dailyPaperFunction.launchLiveImageWallpaper();
                    });
                    dailyPaperElement.getLiveImageSureHBox().getChildren().clear();
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
                dailyPaper.getDailyAutoWallpaper().set(true);
            } else {
                dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(false);
                dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(false);
                dailyPaper.getDailyAutoWallpaper().set(false);
            }
        });

        dailyPaperElement.getDailyLaunchGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dailyPaperElement.getDailyLaunchOnButton()) {
                boolean isSystem = ComputerSystem.getInstance().addSystemAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isUser = ComputerSystem.getInstance().addUserAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isSuccess = isSystem || isUser;
                if (isSuccess) {
                    dailyPaper.getDailyPaperAutoLaunch().set(true);
                } else {
                    dailyPaperElement.getDailyLaunchOffButton().setSelected(true);
                }
            } else {
                boolean isSystem = ComputerSystem.getInstance().deleteSystemAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isUser = ComputerSystem.getInstance().deleteUserAutoLaunch(dailyPaper.getCurrentFilePath().get() + File.separator + dailyPaper.getNAME() + ".exe");
                boolean isSuccess = isSystem || isUser;
                if (isSuccess) {
                    dailyPaper.getDailyPaperAutoLaunch().set(false);
                } else {
                    dailyPaperElement.getDailyLaunchOnButton().setSelected(true);
                }
            }
        });

        dailyPaperElement.getDailyLaunchWallpaperGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> dailyPaper.getDailyAutoWallpaperWhenLaunch().set(dailyPaperElement.getDailyLaunchWallpaperOnButton().isSelected()));

        dailyPaperElement.getDailyTimeWallpaperTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                dailyPaper.getDailyAutoWallpaperWhenTime().set(Math.max(Integer.parseInt(newValue), 0));
            } catch (NumberFormatException ignored) {}

            if (Boolean.parseBoolean(String.valueOf(DailyPaper.getInstance().getDailyAutoWallpaper().get())) && Integer.parseInt(String.valueOf(DailyPaper.getInstance().getDailyAutoWallpaperWhenTime().get())) >= 1) {
                if (!Boolean.parseBoolean(String.valueOf(dailyPaperData.getIsAutoDailyWallpaperRunning().get()))) {
                    dailyPaperData.setAutoDailyWallpaperStartTime(System.currentTimeMillis());
                    dailyPaper.getScheduledExecutorService().scheduleAtFixedRate(dailyPaperData.getAutoDailyWallpaper(), 1, 1, TimeUnit.MINUTES);
                    dailyPaperData.getIsAutoDailyWallpaperRunning().set(true);
                }
            }
        });

        dailyPaperElement.getDailyWallpaperHobbyTextArea().textProperty().addListener((observable, oldValue, newValue) -> dailyPaper.getDailyImageHobby().set(newValue));

        dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setOnAction(actionEvent -> {
            dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageFileChooser().setInitialDirectory(new File(String.valueOf(dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFilePath().get())));
            dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageFileChooser().setInitialFileName(String.valueOf(dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFileName().get()));
            File file = dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageFileChooser().showOpenDialog(dailyPaperElement.getStage());
            if (file != null) {
                dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFilePath().set(file.getParent());
                dailyPaper.getDailyWallpaperHobbyToolsImageUploadImageChosenFileName().set(file.getName());
                String format = file.getName().contains(".") ? file.getName().toLowerCase().substring(file.getName().lastIndexOf(".")) : file.getName().toLowerCase();
                if (dailyPaperData.getSupportImageFormat().contains(format)) {
                    dailyPaper.getDailyPaperPool().submit(() -> {
                        String temp = dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().getText();
                        try {
                            Platform.runLater(() -> {
                                dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setDisable(true);
                                dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setText("正在分析");
                            });
                            String[] imageData = ImageAIAgentSiliconFlowAnalysis.getInstance().analysisImage(file.getAbsolutePath(), String.valueOf(dailyPaperData.getPromptDelineateImagePrompt().get()));
                            String imageDelineate = imageData[0].isEmpty() ? imageData[1].isEmpty() ? "无" : imageData[1] : imageData[0];
                            String hobby = TextToTextAIAgentSiliconFlow.getInstance().turnTextToText(String.valueOf(dailyPaperData.getPromptSpawnDailyWallpaperHobbyPrompt().get()).formatted(dailyPaperElement.getDailyWallpaperHobbyTextArea().getText(), imageDelineate))[0];
                            dailyPaperElement.getDailyWallpaperHobbyTextArea().setText(hobby);
                            dailyPaper.getDailyImageHobby().set(hobby);
                        } catch (Exception ignored) {
                            Platform.runLater(() -> dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setText("分析超时"));
                            dailyPaperFunction.waitADelay(100);
                        } finally {
                            Platform.runLater(() -> {
                                dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setText(temp);
                                dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton().setDisable(false);
                            });
                        }
                    });
                } else {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getDailyWallpaperHobbyToolsUploadImageButton(), "仅能静图");
                }
            }
        });

        /// 响应式按钮属性
        dailyPaper.getDailyAutoWallpaper().addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(true);
                dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(true);
                dailyPaperElement.getDailyWallpaperOnButton().setSelected(true);
            } else {
                dailyPaperElement.getDailyWallpaperChooseHBox().setVisible(false);
                dailyPaperElement.getDailyWallpaperHobbyVBox().setVisible(false);
                dailyPaperElement.getDailyWallpaperOffButton().setSelected(true);
            }
        });

        dailyPaper.getDailyPaperAutoLaunch().addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                dailyPaperElement.getDailyLaunchOnButton().setSelected(true);
            } else {
                dailyPaperElement.getDailyLaunchOffButton().setSelected(true);
            }
        });

        dailyPaper.getDailyAutoWallpaperWhenLaunch().addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                dailyPaperElement.getDailyLaunchWallpaperOnButton().setSelected(true);
            } else {
                dailyPaperElement.getDailyLaunchWallpaperOffButton().setSelected(true);
            }
        });

        dailyPaper.getDailyAutoWallpaperWhenTime().addListener((observable, oldValue, newValue) -> {
            dailyPaperElement.getDailyTimeWallpaperTextField().setText(String.valueOf(newValue));
        });

        dailyPaper.getDailyImageHobby().addListener((observable, oldValue, newValue) -> {
            dailyPaperElement.getDailyWallpaperHobbyTextArea().setText(String.valueOf(newValue));
        });
    }

    @Override
    public void createParamEvent() {
        dailyPaperElement.getParamButtonBarResetButton().setOnAction(actionEvent -> {
            dailyPaper.getDailyPaperPool().submit(() -> {
                if (ParamConfig.getInstance().getLocalDB().deleteFile(ParamConfig.getInstance().getLocalDB().getFullFilePath()) && ParamConfig.getInstance().getLocalDB().createFile(ParamConfig.getInstance().getLocalDB().getFullFilePath())) {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getParamButtonBarResetButton(), "重启");
                    dailyPaperFunction.waitADelay(100);
                    System.exit(0);
                } else {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getParamButtonBarResetButton(), "失败");
                }
            });
        });
    }

    @Override
    public void createSettingEvent() {
        dailyPaperElement.getSettingButtonBarResetButton().setOnAction(actionEvent -> {
            dailyPaper.getDailyPaperPool().submit(() -> {
                if (SettingConfig.getInstance().getLocalDB().deleteFile(SettingConfig.getInstance().getLocalDB().getFullFilePath()) && SettingConfig.getInstance().getLocalDB().createFile(SettingConfig.getInstance().getLocalDB().getFullFilePath())) {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getSettingButtonBarResetButton(), "重启");
                    dailyPaperFunction.waitADelay(100);
                    System.exit(0);
                } else {
                    dailyPaperFunction.showButtonInformation(dailyPaperElement.getSettingButtonBarResetButton(), "失败");
                }
            });
        });

        dailyPaperElement.getSettingButtonBarPluginButton().setOnAction(actionEvent -> {
            dailyPaperFunction.showButtonInformation(dailyPaperElement.getSettingButtonBarPluginButton(), "期待");
            Plugin.getInstance().launch();
        });
    }

    @Override
    public void createRenewEvent() {
        dailyPaperElement.getRenewManualCheckRenewButton().setOnAction(actionEvent -> dailyPaper.getDailyPaperPool().submit(() -> {
            String temp = dailyPaperElement.getRenewManualCheckRenewButton().getText();
            try {
                Platform.runLater(() -> {
                    dailyPaperElement.getRenewManualShowRenewHBox().setVisible(true);
                    dailyPaperElement.getRenewManualCheckRenewButton().setDisable(true);
                    dailyPaperElement.getRenewManualCheckRenewButton().setText("正在检查");
                });
                if (NetworkSystem.getInstance().getHasNetwork()) {
                    Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("嘟嘟检查更新中..."));
                    if (dailyPaperFunction.checkRenewDailyPaper()) {
                        Platform.runLater(() -> {
                            dailyPaperElement.getRenewManualShowRenewTextArea().setText("发现更新版本呀！");
                            dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + RenewConfig.getInstance().getLocalRemoteDB().get("RENEW", "未知更新内容"));
                            dailyPaperElement.getRenewManualDownloadRenewHBox().setVisible(true);
                        });
                    } else {
                        Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("已是最新版本啦！"));
                    }
                } else {
                    Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("怎么好像没网络？"));
                }
            } catch (Exception exception) {
                Platform.runLater(() -> {
                    dailyPaperElement.getRenewManualShowRenewTextArea().setText("不小心出错了哦？");
                    dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + exception.getMessage());
                });
            } finally {
                Platform.runLater(() -> {
                    dailyPaperElement.getRenewManualCheckRenewButton().setText(temp);
                    dailyPaperElement.getRenewManualCheckRenewButton().setDisable(false);
                });
            }
        }));

        dailyPaperElement.getRenewManualDownloadRenewButton().setOnAction(actionEvent -> dailyPaper.getDailyPaperPool().submit(() -> {
            String temp = dailyPaperElement.getRenewManualDownloadRenewButton().getText();
            try {
                Platform.runLater(() -> {
                    dailyPaperElement.getRenewManualDownloadRenewButton().setDisable(true);
                    dailyPaperElement.getRenewManualDownloadRenewButton().setText("正在更新");
                });
                if (NetworkSystem.getInstance().getHasNetwork()) {
                    if (dailyPaperFunction.downloadRenewDailyPaper()) {
                        Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("版本更新完成！"));
                        if (Boolean.parseBoolean(String.valueOf(dailyPaper.getDailyPaperRenewAutoOpen().get()))) {
//                            安全关闭程序
//                            dailyPaperApplication.stop();
                            System.exit(0);
                        }
                    } else {
                        Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("版本更新失败？"));
                    }
                } else {
                    Platform.runLater(() -> dailyPaperElement.getRenewManualShowRenewTextArea().setText("找不到网络哦？"));
                }
            } catch (Exception exception) {
                dailyPaperElement.getRenewManualShowRenewTextArea().setText("不小心出错了哦？");
                dailyPaperElement.getRenewManualShowRenewTextArea().setText(dailyPaperElement.getRenewManualShowRenewTextArea().getText() + "\n" + exception.getMessage());
            } finally {
                Platform.runLater(() -> {
                    dailyPaperElement.getRenewManualDownloadRenewButton().setText(temp);
                    dailyPaperElement.getRenewManualDownloadRenewButton().setDisable(false);
                });
            }
        }));

        dailyPaperElement.getRenewAutoCheckRenewGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == dailyPaperElement.getRenewAutoCheckRenewOnButton()) {
                dailyPaper.getRenewAutoCheckRenew().set(true);
            } else {
                if (dailyPaperElement.getRenewAutoDownloadRenewOnButton().isSelected()) {
                    dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
                } else {
                    dailyPaper.getRenewAutoCheckRenew().set(false);
                }
            }
        });
        dailyPaperElement.getRenewAutoDownloadRenewGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue ==  dailyPaperElement.getRenewAutoDownloadRenewOnButton()) {
                dailyPaper.getRenewAutoDownloadRenew().set(true);
                if (!dailyPaperElement.getRenewAutoCheckRenewOnButton().isSelected()) {
                    dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
                    dailyPaper.getRenewAutoCheckRenew().set(true);
                }
            } else {
                dailyPaper.getRenewAutoDownloadRenew().set(false);
            }
        });

        /// 响应式按钮属性
        dailyPaper.getRenewAutoCheckRenew().addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                dailyPaperElement.getRenewAutoCheckRenewOnButton().setSelected(true);
            } else {
                dailyPaperElement.getRenewAutoCheckRenewOffButton().setSelected(true);
            }
        });

        dailyPaper.getRenewAutoDownloadRenew().addListener((observable, oldValue, newValue) -> {
            if (Boolean.parseBoolean(String.valueOf(newValue))) {
                dailyPaperElement.getRenewAutoDownloadRenewOnButton().setSelected(true);
            } else {
                dailyPaperElement.getRenewAutoDownloadRenewOffButton().setSelected(true);
            }
        });
    }

}