package com.probie.dailypaper.DailyPaper;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;
import java.util.function.Supplier;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
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
            dailyPaperElement.getStage().close();
        });

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
    }

    @Override
    public void createChatPaneEvent() {
        /// 创建 ChatPane 控件事件
        dailyPaperElement.getChatPaneTextInputArea().addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (keyEvent.isShiftDown() || keyEvent.isControlDown() || keyEvent.isAltDown()) {
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

                    dailyPaperElement.getAgentConnectPool().submit(() -> scrollToBottom(dailyPaperElement.getChatPaneTextShowArea()));

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

                    dailyPaperElement.getAgentConnectPool().submit(() -> scrollToBottom(dailyPaperElement.getChatPaneTextShowArea()));
                    dailyPaperElement.getChatPaneTextInputArea().clear();

                    dailyPaperElement.getAgentConnectPool().submit(() -> {
                        try {
                            Platform.runLater(() -> chatPaneAgentMessageLabel.setText("收集上下文..."));
                            String prompt = "";
                            if (dailyPaperElement.getChatPaneUserMessageArrayList().size() > 1) {
                                for (int i = dailyPaperElement.getChatPaneUserMessageArrayList().size() - 1 - 1; i >= 0 && prompt.length() <= 1000; i--) {
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

                            if (!ifImage[0].contains("是")) {
                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText("文本生成中..."));
                                String[] result = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(prompt);
                                Platform.runLater(() -> {
                                    chatPaneAgentMessageLabel.setText(result[0]);
                                });
                                scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                                dailyPaperElement.getChatPaneAgentMessageArrayList().add(result[0]);
                            }

                            else {
                                Platform.runLater(() -> chatPaneAgentMessageLabel.setText("图片生成中..."));
                                prompt = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(dailyPaperElement.getPromptSpawnImagePrompt().get() + prompt)[0];
                                String[] imageURIs = dailyPaper.getTextToImageAIAgentSiliconFlow().turnTextToImage(prompt);
                                BufferedImage bufferedImage = dailyPaper.getImageSystem().turnUrlToBufferedImage(imageURIs[0]);
                                bufferedImage = dailyPaper.getImageSystem().setBufferedImageSize(bufferedImage, (int) ((chatPaneAgentMessageVBox.widthProperty().divide(2.0).get())), (int) ((double) Integer.parseInt(dailyPaper.getImageSize().get().split("x")[1]) * (((chatPaneAgentMessageVBox.widthProperty().divide(2.0).get())) / (double) Integer.parseInt(dailyPaper.getImageSize().get().split("x")[0]))));
                                ImageView imageView = new ImageView(dailyPaper.getImageSystem().turnBufferedImageToFXImage(bufferedImage));
                                String result = dailyPaper.getTextToTextAIAgentSiliconFlow().turnTextToText(dailyPaperElement.getPromptSpawnImageResultPrompt().get() + prompt)[0];
                                Platform.runLater(() -> {
                                    chatPaneAgentMessageVBox.getChildren().addAll(imageView);
                                    chatPaneAgentMessageLabel.setText(result);

                                    Button button = new Button("点击设为壁纸");
                                    button.setOnAction(actionEvent -> {
                                        dailyPaper.getImageSystem().turnBufferedImageToLocalFile(dailyPaper.getImageSystem().turnUrlToBufferedImage(imageURIs[0]), dailyPaper.getRootPath().get(), dailyPaper.getImageFileName().get());
                                        dailyPaper.getImageSystem().setWallPaper(dailyPaper.getRootPath().get(), dailyPaper.getImageFileName().get());
                                    });
                                    chatPaneAgentMessageVBox.getChildren().addAll(button);

                                    scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                                });
                                dailyPaperElement.getChatPaneAgentMessageArrayList().add(result);
                            }
                        } catch (Exception exception) {
                            Platform.runLater(() -> chatPaneAgentMessageLabel.setText("不小心出错了:\n" + exception.getMessage()));
                            scrollToBottom(dailyPaperElement.getChatPaneTextShowArea());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void createLivePaneEvent() {

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

    }

    @Override
    public void changeCenterPane(Pane pane) {
        dailyPaperElement.getRootPaneCenterPane().getChildren().clear();
        dailyPaperElement.getRootPaneCenterPane().getChildren().addAll(pane);
    }

    @Override
    public void scrollToBottom(ScrollPane scrollPane) {
        Platform.runLater(() -> {
            ScrollBar verticalScrollBar = (ScrollBar) scrollPane.lookup(".scroll-bar:vertical");
            if (verticalScrollBar != null) {
                verticalScrollBar.setValue(verticalScrollBar.getMax());
            }
        });
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