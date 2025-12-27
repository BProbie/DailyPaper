package com.probie.dailypaper.DailyPaper;

import com.probie.dailypaper.DailyPaper.Interface.IDailyPaperStyle;

public class DailyPaperStyle implements IDailyPaperStyle {

    /**
     * 维护一个懒加载的类单例对象
     * */
    private volatile static DailyPaperStyle INSTANCE;

    /**
     * 懒加载的工具类单例对象
     * */
    private DailyPaper dailyPaper;
    private DailyPaperElement dailyPaperElement;

    @Override
    public void createStyle() {

        /// 初始化工具类单例对象
        if (dailyPaper == null) {
            dailyPaper = DailyPaper.getInstance();
        }
        if (dailyPaperElement == null) {
            dailyPaperElement = DailyPaperElement.getInstance();
        }

        /// 舞台样式
        dailyPaperElement.getStage().setWidth(dailyPaper.getDailyPaperStageWidth().get());
        dailyPaperElement.getStage().setHeight(dailyPaper.getDailyPaperStageHeight().get());

        /// chatPane 样式
        dailyPaperElement.getChatPane().setMinWidth(dailyPaperElement.getStage().getWidth());
        dailyPaperElement.getChatPane().setMinHeight(dailyPaperElement.getStage().getHeight() - dailyPaperElement.getMenuHeightStage());

        /// chatPane 控件样式
        dailyPaperElement.getChatPaneTextShowArea().setMinWidth(dailyPaperElement.getChatPane().getMinWidth());
        dailyPaperElement.getChatPaneTextShowArea().setMinHeight((dailyPaperElement.getChatPane().getMinHeight()/5)*3);

        dailyPaperElement.getChatPaneTextInputArea().setMinWidth(dailyPaperElement.getChatPane().getMinWidth());
        dailyPaperElement.getChatPaneTextInputArea().setMinHeight((dailyPaperElement.getChatPane().getMinHeight()/5)*2);
        dailyPaperElement.getChatPaneTextInputArea().setLayoutY((dailyPaperElement.getChatPane().getMinHeight()/5)*3);

    }

    /**
     * 获取懒加载的类单例对象
     * */
    public synchronized static DailyPaperStyle getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DailyPaperStyle();
        }
        return INSTANCE;
    }

}