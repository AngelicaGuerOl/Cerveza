package com.example.cerveza.ControladorVista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CtrlvMarca {

    @FXML private Button btn_nvaMarca;
    @FXML private Button btn_admMarca;
    @FXML private StackPane subContentArea;

    @FXML
    public void initialize() {
        btn_nvaMarca.setOnAction(e -> cargarPanel("agregarMarca.fxml"));
        cargarPanel("agregarMarca.fxml");
        btn_admMarca.setOnAction(e -> cargarPanel("administrarMarca.fxml"));

    }

    private void cargarPanel(String fxml) {
        try {
            Parent panel = FXMLLoader.load(getClass().getResource("/com/example/cerveza/" + fxml));
            subContentArea.getChildren().setAll(panel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
