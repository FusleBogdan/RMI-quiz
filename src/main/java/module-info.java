module com.example.licenta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.rmi;
    requires javafx.media;
    requires java.desktop;
    requires org.xerial.sqlitejdbc;

    opens com.example.licenta to javafx.fxml;
    exports com.example.licenta;
}