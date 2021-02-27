/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class BasesDatos {

    // ATRIBUTOS
    private Connection connection;
    private Archivo ar;

    // METODO PARA ESTABLECER CONEXION CON LA BASE DE DATOS
    public void EstablecerConexion() {

        if (connection != null) {
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/TiendaVideo";

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
    public void AgregarUsuario(int id, String nombre, String apellido, int telefono, String direccion, int credito, String correo) {

        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO usuario (idmembresia, nombre,apellido,telefono,direccion,credito,correo) VALUES ('" + id + "','"
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

    public void consultarVacios() {
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from empleado");
            // si es false, la base está desocupada
            if (!rs.next()) {
                ar = new Archivo();
                ar.leerArchivo();
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
    }

    public int consultarId() {
        Statement s = null;
        String a = "";
        int id = 0;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from usuario");
            while (rs.next()) {
                a = rs.getString("idmembresia");
            }
            id = Integer.parseInt(a);
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return id;
    }

    public boolean consultarEmpleado(String user, String contrasena) {
        Statement s = null;
        int aux = 0;
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from empleado");
            while (rs.next()) {
                if (rs.getString("usuario").equals(user) && rs.getString("password").equals(contrasena)) {
                    aux++;
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        if (aux != 0) {
            return true;
        } else {
            return false;
        }

    }

    public String consultarNombre(String contrasena) {
        Statement s = null;
        String nombre = " ";
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from empleado");
            while (rs.next()) {
                if (rs.getString("password").equals(contrasena)) {
                    nombre = rs.getString("nombre");
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return nombre;
    }

    public void consultarPelicula(JTable tabla,String titulo) {
        Statement s = null;
        String nombre = " ";
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("id");
        model.addColumn("Titulo");
        model.addColumn("Estreno");
        
        tabla.setModel(model);
        
        String[] datos = new String[3];
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from pelicula where titulo like '%"+titulo+"%'");
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                model.addRow(datos);
            }
           
        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public void llenarEmpleado(int idEmpleado, String nombre, String apellido, String usuario, String password) {

        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO empleado (idempleado,nombre,apellido,usuario,password) VALUES ('"
                    + idEmpleado + "',' " + nombre + "',' " + apellido + "',' " + usuario + "',' "
                    + password + "');");
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

    public void llenarUsuario(int id, String nombre, String apellido, String telefono, String direccion, String credito) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO usuario (idmembresia,nombre,apellido,telefono,direccion,credito) VALUES ('"
                    + id + "',' " + nombre + "',' " + apellido + "',' " + telefono + "',' "
                    + direccion + "',' " + credito + "');");
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

    public void llenarActor(int id, String nombre, String fecha) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO actor (idactor,nombrereal,fechanacimiento) VALUES ('"
                    + id + "',' " + nombre + "',' " + fecha + "');");
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

    public void llenarPelicula(int id, String titulo, String estreno) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO pelicula (idpelicula,titulo,fechaestreno) VALUES ('"
                    + id + "',' " + titulo + "',' " + estreno + "');");
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

    public void llenarCategoria(int id, String tipo) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO categoria (idcategoria, tipo) VALUES ('"
                    + id + "',' " + tipo + "');");
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

    public void llenarPeliculaCategoria(int id, int idPeliculaFK, int idCategoriaFK) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO peliculacategoria (idconsecutivo,idpeliculafk,idcategoriafk) VALUES ('"
                    + id + "',' " + idPeliculaFK + "',' " + idCategoriaFK + "');");
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

    public void llenarPeliculaActor(int id, int idPeliculaFK, int idActorFK) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO peliculaactor (idsucesivo,idpeliculafk,idactorfk) VALUES ('"
                    + id + "',' " + idPeliculaFK + "',' " + idActorFK + "');");
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

    public void llenarFormato(int id, String tipo) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO formato (idformato,tipo) VALUES ('"
                    + id + "',' " + tipo + "');");
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

    public void llenarEstado(int id, String estado) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate("INSERT INTO estado (idestado,estado) VALUES ('" + id + "',' " + estado + "');");

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

    public void llenarCinta(int id, int idPeliculaFK, int idFormatoFK, int idEstadoFK) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO cinta (idcinta,idpeliculafk,idformatofk,idestadofk) VALUES ('"
                    + id + "',' " + idPeliculaFK + "',' " + idFormatoFK + "',' " + idEstadoFK + "');");
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
