module com.example.cerveza {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires javafx.base;

    opens com.example.cerveza.ControladorVista to javafx.fxml;
    opens com.example.cerveza.Modelo to javafx.base;
    opens com.example.cerveza to javafx.fxml;
    exports com.example.cerveza;
}