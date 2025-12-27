package com.probie.dailypaper.DailyPaper;

import lombok.Data;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperElement;

@Data
public class DailyPaperElement implements IDailyPaperElement {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperElement INSTANCE;

    /**
     * 舞台
     * */
    private Stage stage;
    private int menuHeightStage = 30;

    /**
     * 分页
     * */
    private Pane chatPane = new Pane();

    /**
     * 帷幕
     * */
    private Scene scene = new Scene(chatPane);

    /**
     * chatPane
     * */
    private ScrollPane chatPaneTextShowArea = new ScrollPane();
    private TextArea chatPaneTextInputArea = new TextArea();

    @Override
    public void createElement(Stage stage) {
        setStage(stage);
        chatPane.getChildren().addAll(chatPaneTextShowArea, chatPaneTextInputArea);
        scene.setRoot(chatPane);
        getStage().setScene(scene);
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