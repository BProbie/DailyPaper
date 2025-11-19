module com.probie.dailypaper {
    requires javafx.web;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.controls;
    requires javafx.graphics;

    requires EasyDB;
    requires okhttp3;
    requires fastjson;
    requires Encryption;
    requires com.sun.jna;
    requires java.desktop;
    requires static lombok;
    requires java.instrument;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires com.sun.jna.platform;
    requires com.alibaba.fastjson2;
    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.probie.dailypaper;

    exports com.probie.dailypaper;
    exports com.probie.dailypaper.DailyPaper;
    opens com.probie.dailypaper to javafx.fxml;
    opens com.probie.dailypaper.DailyPaper to javafx.fxml;
}