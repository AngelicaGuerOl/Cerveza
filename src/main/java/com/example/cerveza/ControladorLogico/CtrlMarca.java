package com.example.cerveza.ControladorLogico;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.Modelo.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CtrlMarca {
    public boolean insertar(Marca obj){
        Connection cn=null;
        PreparedStatement ps=null;
        String query="INSERT INTO marca(idmarca,idfabricante,nombre,descripcion) VALUES(?,?,?,?)";
        try{
            cn= Conexion.Conectar();
            ps=cn.prepareStatement(query);
            ps.setInt(1,0);
            ps.setInt(2,obj.getIdFabricante());
            ps.setString(3,obj.getNombre());
            ps.setString(4,obj.getDescripcion());
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            System.out.println("Fallo la inserción en marca: "+e);
            return false;
        }finally{
            try{
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }catch(Exception e){
                System.out.println("Error al cerrar recursos: "+e);
            }
        }
    }
}
