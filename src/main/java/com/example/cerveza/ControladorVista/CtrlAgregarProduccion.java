package com.example.cerveza.ControladorVista;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.ControladorLogico.CtrlProduccion;
import com.example.cerveza.Modelo.Produccion;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class CtrlAgregarProduccion {
    @FXML private ComboBox<String> cmb_cerv;
    @FXML private Spinner<Integer> sp_cantProd;
    @FXML
    public void initialize(){
        cargarCervezas();
        // Configurar Spinner: valores de 1 a 10000, inicial en 500, paso de 50
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1, 1);
        sp_cantProd.setValueFactory(valueFactory);
        sp_cantProd.setEditable(true);

        // Validar que solo se acepten números en el Spinner
        TextFormatter<Integer> formatter = new TextFormatter<>(
                new IntegerStringConverter(),
                1, // valor inicial
                c -> {
                    if (c.getControlNewText().matches("\\d*")) {
                        return c; // permitir solo números
                    } else {
                        return null; // bloquear letras o símbolos
                    }
                });
        sp_cantProd.getEditor().setTextFormatter(formatter);
    }
    @FXML
    private void guardarProduccion(){
        CtrlProduccion ctrlProduccion = new CtrlProduccion();
        String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        String cervezas = cmb_cerv.getValue();
        Integer cantidad=sp_cantProd.getValue();

        if("Seleccione una cerveza".equals(cmb_cerv.getValue())){
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione una cerveza");
            return;
        }
        if(cantidad==null){
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Agregue una cantidad");
            return;
        }
        int idCerveza=obtenerIdCerveza();
        Produccion produccion =new Produccion();
        produccion.setIdCerveza(idCerveza);
        produccion.setFechaProduccion(fechaActual);
        produccion.setCantidad(cantidad);
        if(ctrlProduccion.insertar(produccion)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Producción guardada correctamente");
            cmb_cerv.getSelectionModel().selectFirst();
            sp_cantProd.getValueFactory().setValue(1);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar la producción");
        }


    }
    private void cargarCervezas(){
        String sql="SELECT nombre FROM cerveza";
        try{
            Connection cn= Conexion.Conectar();
            PreparedStatement pst=cn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            cmb_cerv.getItems().clear();
            cmb_cerv.getItems().add("Seleccione una cerveza");
            while(rs.next()){
                String nombreCerveza=rs.getString("nombre");
                cmb_cerv.getItems().add(nombreCerveza);
            }
            cmb_cerv.getSelectionModel().selectFirst();
        }catch(Exception e){
            System.out.println("Error al cargar cervezas: "+e);
        }
    }
    private int obtenerIdCerveza(){
        int idCerveza=0;
        String sql="SELECT idcerveza FROM cerveza WHERE nombre=?";
        try{
            Connection cn= Conexion.Conectar();
            PreparedStatement ps=cn.prepareStatement(sql);
            String cervSel=cmb_cerv.getValue();
            ps.setString(1,cervSel);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                idCerveza=rs.getInt("idcerveza");
            }
        }catch(Exception e){
            System.out.println("Error al obtener idCerveza: "+e);
        }
        return idCerveza;
    }
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
