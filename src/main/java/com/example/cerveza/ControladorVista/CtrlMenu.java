package com.example.cerveza.ControladorVista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class CtrlMenu {

    @FXML private Button btn_envase;
    @FXML private Button btn_produccion;
    @FXML private Button btn_marca;
    @FXML private StackPane contentArea;

    @FXML
    public void initialize() {
        btn_envase.setOnAction(e -> cargarPanel("envase.fxml"));
        btn_produccion.setOnAction(e -> cargarPanel("produccion.fxml"));
        btn_marca.setOnAction(e -> cargarPanel("marca.fxml"));
    }

    private void cargarPanel(String fxml) {
        try {
            Parent panel = FXMLLoader.load(getClass().getResource("/com/example/cerveza/" + fxml));
            contentArea.getChildren().setAll(panel); // reemplaza el contenido actual
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
