module com.probie.dailypaper {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    requires EasyDB;
    requires okhttp3;
    requires fastjson;
    requires Encryption;
    requires com.sun.jna;
    requires java.desktop;
    requires java.instrument;
    requires com.sun.jna.platform;
    requires com.alibaba.fastjson2;

    requires static lombok;
    requires javafx.swing;

    exports com.probie.dailypaper;
    exports com.probie.dailypaper.DailyPaper;

    opens com.probie.dailypaper to javafx.fxml;
    opens com.probie.dailypaper.DailyPaper to javafx.fxml;

}