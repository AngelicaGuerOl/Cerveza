package com.example.cerveza.ControladorLogico;

import com.example.cerveza.Conexion.Conexion;
import com.example.cerveza.Modelo.Produccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CtrlProduccion {
    public boolean insertar(Produccion obj) {
        Connection cn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO produccion(idproduccion,idcerveza,fecha,cantidad) VALUES(?,?,?,?)";
        try {
            cn = Conexion.Conectar();
            ps = cn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, obj.getIdCerveza());
            ps.setString(3, obj.getFechaProduccion());
            ps.setInt(4, obj.getCantidad());
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
