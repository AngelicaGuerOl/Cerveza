package com.example.cerveza.ControladorLogico;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.Modelo.Envase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class CtrlEnvase {
    public boolean insertar(Envase obj) {
        Connection cn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO envase(idenvase,nombre,material,capacidad,descripcion) VALUES(?,?,?,?,?)";
        try {
            cn = Conexion.Conectar();
            ps = cn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getMaterial());
            ps.setString(4, obj.getCapacidad());
            ps.setString(5, obj.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Falló la inserción en envase: " + e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e);
            }
        }
    }
}
