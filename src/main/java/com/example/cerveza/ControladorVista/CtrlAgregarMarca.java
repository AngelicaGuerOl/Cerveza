package com.example.cerveza.ControladorVista;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.ControladorLogico.CtrlMarca;
import com.example.cerveza.Modelo.Marca;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CtrlAgregarMarca {
    @FXML private ComboBox<String> cmb_fab;
    @FXML private TextField txt_nombreMarca;
    @FXML private TextField txt_descMarca;
    public void initialize(){
        cargarFabricantes();
    }
    @FXML private void guardarMarca(){
        CtrlMarca ctrlMarca = new CtrlMarca();
        String fabricante=cmb_fab.getValue();
        String descripcion=txt_descMarca.getText();
        String nombre=txt_nombreMarca.getText();
        if("Seleccione un fabricante".equals(cmb_fab.getValue())){
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione un fabricante");
            return;
        }
        if(descripcion.isEmpty()||nombre.isEmpty()){
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Complete todos los campos");
            return;
        }
        Marca marca=new Marca();
        int idFabricante=obtenerIdFabricante();
        marca.setIdFabricante(idFabricante);
        marca.setNombre(nombre);
        marca.setDescripcion(descripcion);
        if(ctrlMarca.insertar(marca)){
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Marca registrada con éxito.");
            cmb_fab.getSelectionModel().selectFirst();
            txt_nombreMarca.clear();
            txt_descMarca.clear();
        }else{
            mostrarAlerta(Alert.AlertType.ERROR,"Error","Error al registrar marca");
        }
    }
    private void cargarFabricantes(){
        String sql="SELECT nombre FROM fabricante";
        try{
            Connection cn=Conexion.Conectar();
            PreparedStatement ps=cn.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            cmb_fab.getItems().clear();
            cmb_fab.getItems().add("Seleccione un fabricante");
            while(rs.next()){
                String nombreFabricante=rs.getString("nombre");
                cmb_fab.getItems().add(nombreFabricante);

            }
            cmb_fab.getSelectionModel().selectFirst();
        }catch(Exception e){
            System.out.println("Error al cargar fabricante: "+e);
        }
    }
    private int obtenerIdFabricante(){
        int idFabricante=0;
        String sql="SELECT idfabricante FROM fabricante WHERE nombre=?";
        try{
            Connection cn= Conexion.Conectar();
            PreparedStatement ps=cn.prepareStatement(sql);
            String fabSel=cmb_fab.getValue();
            ps.setString(1,fabSel);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                idFabricante=rs.getInt("idfabricante");
            }
        }catch(Exception e){
            System.out.println("Error al obtener idfabricante"+e);
        }
        return idFabricante;
    }
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
