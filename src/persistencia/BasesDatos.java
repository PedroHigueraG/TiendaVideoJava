/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author USER
 */
public class BasesDatos {

    // ATRIBUTOS
    private Connection connection;

    // METODO PARA ESTABLECER CONEXION CON LA BASE DE DATOS
    public void EstablecerConexion() {

        if (connection != null) {
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/Video";

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(url, "postgres", "12345");
            connection.setAutoCommit(false);
            if (connection != null) {
                System.out.println("Conectando a la base de datos...");
            }
        } catch (Exception e) {
            System.out.println("Problemas de conexion");
        }
    }

    // METODO PARA DETENER LA CONEXION
    public void DetenerConexion() {
        try {
            connection.close();
            System.out.println("Conexion detenida");
        } catch (Exception e) {
            System.out.println("Problema al cerrar la base de datos");
        }
    }

    // AGREGAR USUARIO
    public void AgregarUsuario(String id,String nombre, String apellido, int telefono, String direccion, int credito, String correo) {

        Statement s = null;
        
        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO usuario (idmembresia, nombre,apellido,telefono,direccion,credito,correo) VALUES ('"+ id +"','"
                    + nombre + "','" + apellido + "','" + telefono + "','" + direccion + "','" + credito + "','"
                    + correo + "');");
            connection.commit();
            if (z == 1) {
                System.out.println("Se agregó el registro de manera exitosa!");
            } else {
                System.out.println("Error al agregar el registro!");
            }
        } catch (Exception e) {
            
            System.out.println("Problema en insertar registro");
            e.printStackTrace();
        }

    }

}
