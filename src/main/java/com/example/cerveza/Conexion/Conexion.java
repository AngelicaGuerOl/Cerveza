package com.example.cerveza.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Angelica Guerrero
 */
public class Conexion {
    public static Connection Conectar(){
        try{
            var  url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Mexico_City";
            Connection conexion=DriverManager.getConnection(url,"root","root");
            System.out.println("Conectado con exito");
            return conexion;
        }catch(SQLException ex){

            System.out.println("Error al conectar con la base de datos "+ex);
        }
        return null;
    }

}



