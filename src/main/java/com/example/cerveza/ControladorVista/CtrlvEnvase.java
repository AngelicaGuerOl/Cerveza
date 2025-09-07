package com.example.cerveza.ControladorVista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class CtrlvEnvase {

    @FXML private Button btn_nvoEnv;
    @FXML private Button btn_admEnv;
    @FXML private StackPane subContentArea;

    @FXML
    public void initialize() {
        btn_nvoEnv.setOnAction(e -> cargarPanel("AgregarEnvase.fxml"));
        cargarPanel("AgregarEnvase.fxml");

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
