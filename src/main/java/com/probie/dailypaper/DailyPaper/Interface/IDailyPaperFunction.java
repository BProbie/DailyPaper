package com.probie.dailypaper.DailyPaper.Interface;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.beans.property.SimpleObjectProperty;

public interface IDailyPaperFunction {

    /**
     * 启动动态壁纸
     * */
    void launchLiveImageWallpaper();

    /**
     * 取消动态壁纸
     * */
    void clearLiveImageWallpaper();

    /**
     * 取消动态图片
     * */
    void clearLiveImage();

    /**
     * 每日壁纸推荐
     * */
    void dailyWallpaper();

    /**
     * 检查更新软件
     * @return 是否有新版本
     * */
    boolean checkRenewDailyPaper();

    /**
     * 下载更新软件
     * @return 是否下载成功
     * */
    boolean downloadRenewDailyPaper();

    /**
     * 菜单选择
     * @param node 功能模块
     * */
    void chooseMenu(Node node);

    /**
     * 清理 Chat 内容
     * */
    void clearChat();

    /**
     * 清理 Live 内容
     * */
    void clearLive();

    /**
     * 清理 Daily 内容
     * */
    void clearDaily();

    /**
     * 清理 Param 内容
     * */
    void clearParam();

    /**
     * 清理 Setting 内容
     * */
    void clearSetting();

    /**
     * 清理 Renew 内容
     * */
    void clearRenew();

    /**
     * 等待时间
     * */
    void waitADelay();
    void waitADelay(int count);

    /**
     * 将 ScrollPane 的滚轮滚到最下面
     * @param scrollPane ScrollPane 实例化对象
     * */
    void scrollToBottom(ScrollPane scrollPane);

    /**
     * 用按钮展示信息
     * @param button 按钮
     * @param information 信息
     * */
    void showButtonInformation(Button button, String information);

    /**
     * 创建文本标题
     * @param pane 父容器
     * @param information 提示
     * */
    HBox createTitleHBox(Pane pane, String information);

    /**
     * 创建文本标题
     * @param pane 父容器
     * @param information 提示
     * @param data 动态数据
     * */
    HBox createLabelHBox(Pane pane, String information, SimpleObjectProperty<Object> data );

    /**
     * 创建文本输入
     * @param pane 父容器
     * @param information 提示
     * @param data 动态数据
     * */
    HBox createTextFieldHBox(Pane pane, String information, SimpleObjectProperty<Object> data);

    /**
     * 创建按钮选择
     * @param pane 父容器
     * @param information 提示
     * @param data 动态数据
     * */
    HBox createChooseButtonHBox(Pane pane, String information, SimpleObjectProperty<Object> data);

}