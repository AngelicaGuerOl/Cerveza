package com.example.cerveza.ControladorVista;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.ControladorLogico.CtrlMarca;
import com.example.cerveza.Modelo.Marca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CtrlAdministrarMarca {
    @FXML
    private TableView<Marca> tb_marca;
    @FXML
    private TableColumn<Marca,Integer> col_id;
    @FXML
    private TableColumn<Marca,String> col_fab;
    @FXML
    private TableColumn<Marca,String> col_nom;
    @FXML
    private TableColumn<Marca,String> col_desc;
    @FXML
    private TextField txt_nombreMarca;
    @FXML
    private TextField txt_descMarca;
    @FXML
    private ComboBox<String> cmb_fab;

    private ObservableList<Marca> listaMarcas;

    @FXML
    public void initialize(){
        tb_marca.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        col_id.setCellValueFactory(new PropertyValueFactory<>("idMarca"));
        col_fab.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        cargarTbMarcas();
        cargarFabricantes();
        tb_marca.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_nombreMarca.setText(newSelection.getNombre());
                txt_descMarca.setText(newSelection.getDescripcion());
                cmb_fab.getSelectionModel().select(newSelection.getFabricante());
            }
        });
    }

    @FXML
    private void actualizarMarca(){
        Marca seleccionada = tb_marca.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione una marca");
            return;
        }

        String descripcion = txt_descMarca.getText().trim();
        String nombre = txt_nombreMarca.getText().trim();
        String fabricante = cmb_fab.getValue();

        if (descripcion.isEmpty() || nombre.isEmpty() || fabricante == null || fabricante.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Complete todos los campos");
            return;
        }
        if (fabricante.equals("Seleccione un fabricante")) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Debe seleccionar un fabricante válido");
            return;
        }

        int idFabricante = obtenerIdFabricante();

        Marca marca = new Marca();
        marca.setIdMarca(seleccionada.getIdMarca());
        marca.setIdFabricante(idFabricante);
        marca.setNombre(nombre);
        marca.setDescripcion(descripcion);

        CtrlMarca ctrl = new CtrlMarca();
        if(ctrl.actualizar(marca)){
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Marca actualizada con éxito.");
            cargarTbMarcas(); //
            cmb_fab.getSelectionModel().selectFirst();
            txt_nombreMarca.clear();
            txt_descMarca.clear();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR,"Error","Error al actualizar marca");
        }
    }
    @FXML
    private void eliminarMarca() {
        Marca seleccionada = tb_marca.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Seleccione una marca para eliminar.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Está seguro de que desea eliminar la marca \"" + seleccionada.getNombre() + "\"?");
        if (confirm.showAndWait().get() != ButtonType.OK) {
            return;
        }

        CtrlMarca ctrl = new CtrlMarca();
        boolean exito = ctrl.eliminar(seleccionada.getIdMarca());

        if (exito) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Marca eliminada correctamente.");
            cargarTbMarcas(); // Refrescar tabla
            cmb_fab.getSelectionModel().selectFirst();
            txt_nombreMarca.clear();
            txt_descMarca.clear();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar la marca.");
        }
    }


    private void cargarTbMarcas(){
        listaMarcas= FXCollections.observableArrayList();
        String sql="SELECT m.idmarca, f.nombre AS fabricante, m.nombre, m.descripcion FROM mydb.marca AS m JOIN mydb.fabricante AS f ON f.idfabricante=m.idfabricante;";
        try{
            Connection cn= Conexion.Conectar();
            PreparedStatement ps=cn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("idmarca");
                String fabricante=rs.getString("fabricante");
                String nombre=rs.getString("nombre");
                String descripcion=rs.getString("descripcion");
                listaMarcas.add(new Marca(id,fabricante,nombre,descripcion));
            }
            tb_marca.setItems(listaMarcas);
        }catch(SQLException e){
            System.out.println("Error al cargar la tabla de marcas: "+e);
        }
    }
    private ObservableList<String> listaFabricantes;
    private void cargarFabricantes() {
        listaFabricantes = FXCollections.observableArrayList();
        listaFabricantes.add("Seleccione un fabricante");

        String sql = "SELECT nombre FROM mydb.fabricante;";
        try(Connection cn = Conexion.Conectar();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                listaFabricantes.add(rs.getString("nombre"));
            }
            cmb_fab.setItems(listaFabricantes);
            cmb_fab.getSelectionModel().selectFirst();
        } catch(SQLException e) {
            System.out.println("Error al cargar fabricantes: " + e);
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
