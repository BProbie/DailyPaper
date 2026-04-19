package com.probie.dailypaper.Plugin;

import lombok.Data;
import javafx.stage.Stage;
import com.probie.dailypaper.Plugin.Interface.IPluginElement;

@Data
public class PluginElement implements IPluginElement {

    /// 维护一个懒加载的类单例对象
    private volatile static PluginElement INSTANCE;

    /// 获取一个懒加载的类单例对象
    public synchronized static PluginElement getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PluginElement();
        }
        return INSTANCE;
    }

    private Stage stage;

    @Override
    public void createElement(Stage stage) {
        setStage(stage);
    }

}