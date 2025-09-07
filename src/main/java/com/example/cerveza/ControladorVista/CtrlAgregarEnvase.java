package com.example.cerveza.ControladorVista;

import com.example.cerveza.ControladorLogico.CtrlEnvase;
import com.example.cerveza.Modelo.Envase;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

public class CtrlAgregarEnvase {

    @FXML private TextField txt_nombreEnvase;
    @FXML private TextField txt_materialEnvase;
    @FXML private Spinner<Integer> sp_cantEn;
    @FXML private ComboBox<String> cmb_uni;
    @FXML private TextField txt_desc;
    @FXML private Button btn_guardarEnvase;

    @FXML
    public void initialize() {
        // Configurar Spinner: valores de 1 a 10000, inicial en 500, paso de 50
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000, 500, 100);
        sp_cantEn.setValueFactory(valueFactory);
        sp_cantEn.setEditable(true);

        // Validar que solo se acepten números en el Spinner
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(),
                500, // valor inicial
                c -> {
                    if (c.getControlNewText().matches("\\d*")) {
                        return c; // permitir solo números
                    } else {
                        return null; // bloquear letras o símbolos
                    }
                });
        sp_cantEn.getEditor().setTextFormatter(formatter);

        cmb_uni.getItems().addAll("ml", "L", "cl", "oz");
        cmb_uni.setValue("ml"); // valor por defecto
    }

    @FXML
    private void guardarEnvase() {
        String nombre = txt_nombreEnvase.getText().trim();
        String material = txt_materialEnvase.getText().trim();
        Integer cantidad = sp_cantEn.getValue();
        String unidad = cmb_uni.getValue();
        String descripcion = txt_desc.getText().trim();

        if (nombre.isEmpty() || material.isEmpty() || cantidad == null || unidad == null || descripcion.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Complete todos los campos.");
            return;
        }

        String capacidadFinal = cantidad + " " + unidad;

        Envase envase = new Envase();
        envase.setNombre(nombre);
        envase.setMaterial(material);
        envase.setCapacidad(capacidadFinal);
        envase.setDescripcion(descripcion);

        CtrlEnvase ctrl = new CtrlEnvase();
        if (ctrl.insertar(envase)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Envase registrado con éxito.");
            limpiar();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el envase.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiar() {
        txt_nombreEnvase.clear();
        txt_materialEnvase.clear();
        sp_cantEn.getValueFactory().setValue(500);
        cmb_uni.setValue("ml");
        txt_desc.clear();
    }
}
