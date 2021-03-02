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
import javax.swing.JCheckBox;
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
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from empleado");
            while (rs.next()) {
                if (rs.getString("password").equals(contrasena)) {
                    usuarioEmpleado = rs.getString("nombre");
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return usuarioEmpleado;
    }

    public boolean consultarUsuario(String usuario) {
        Statement s = null;
        boolean result = true;
        String nombre = " ";
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from usuario");
            while (rs.next()) {
                if (rs.getString("nombre").equals(usuario)) {
                    nombre = rs.getString("nombre");
                    result = true;
                    break;
                } else {
                    result = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return result;
    }

    public void consultarPelicula(JTable tabla, String titulo, String estado) {
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
            ResultSet rs = s.executeQuery("select pelicula.idpelicula,pelicula.titulo,pelicula.fechaestreno from pelicula,estado,cinta \n"
                    + "where titulo like '%" + titulo + "%' and pelicula.idpelicula= cinta.idpeliculafk and cinta.idestadofk=estado.idestado and estado='" + estado + "';");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                model.addRow(datos);
            }

        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public void consultarPeliculaLibre(JTable tabla) {
        Statement s = null;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("Titulo");
        model.addColumn("Estreno");

        tabla.setModel(model);

        String[] datos = new String[3];
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select pelicula.idpelicula,pelicula.titulo,pelicula.fechaestreno from pelicula,estado,cinta \n"
                    + "where pelicula.idpelicula= cinta.idpeliculafk and cinta.idestadofk=estado.idestado and estado=' L';");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                model.addRow(datos);
            }

        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public void consultarActor(JTable tabla, String actor, String estado) {
        Statement s = null;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("Titulo");
        model.addColumn("Estreno");
        model.addColumn("Actor");
        model.addColumn("Nacimiento");

        tabla.setModel(model);

        String[] datos = new String[5];
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select pelicula.idpelicula,pelicula.titulo,pelicula.fechaestreno,actor.nombrereal,actor.fechanacimiento \n"
                    + "from pelicula,peliculaactor,actor,cinta,estado\n"
                    + "where pelicula.idpelicula=peliculaactor.idpeliculafk and actor.idactor=peliculaactor.idactorfk and actor.nombrereal like '%" + actor + "%' \n"
                    + "and pelicula.idpelicula=cinta.idpeliculafk and cinta.idestadofk=estado.idestado and estado='" + estado + "';");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                model.addRow(datos);
            }

        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public void consultarMembresia(JTable tabla, String titulo) {
        Statement s = null;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nombre");
        model.addColumn("Apellido");

        tabla.setModel(model);

        String[] datos = new String[2];
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select nombre,apellido from usuario where idmembresia = '" + titulo + "';");

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                model.addRow(datos);
            }

        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public void consultarPrestamo(ArrayList lista, JTable tabla) {
        Statement s = null;

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("Titulo");
        model.addColumn("Estreno");

        tabla.setModel(model);

        String[] datos = new String[3];
        try {
            EstablecerConexion();
            s = connection.createStatement();
            for (int i = 0; i < lista.size(); i++) {
                ResultSet rs = s.executeQuery("select pelicula.idpelicula,pelicula.titulo,pelicula.fechaestreno from pelicula,estado,cinta \n"
                        + "where pelicula.idpelicula= cinta.idpeliculafk and cinta.idestadofk=estado.idestado and estado=' L';");

                while (rs.next()) {
                    if (lista.get(i).equals(rs.getString("idpelicula"))) {
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2);
                        datos[2] = rs.getString(3);
                        model.addRow(datos);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public boolean consultarEstado(String id) {
        Statement s = null;
        boolean state = false;

        try {
            EstablecerConexion();
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select estado.estado from cinta,"
                    + "pelicula,estado where pelicula.idpelicula=cinta.idpeliculafk "
                    + "and estado.idestado=cinta.idestadofk and pelicula.idpelicula= " + id + ";");
            rs.next();
            if (rs.getString("estado").equals(" L")) {
                state = true;
            } else {
                state = false;
            }

        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return state;
    }

    public void actualizarEstado(ArrayList lista) {
        Statement s = null;
        try {
            EstablecerConexion();
            s = connection.createStatement();
            for (int i = 0; i < lista.size(); i++) {

                int z = s.executeUpdate("update cinta set idestadofk = 2"
                        + " from pelicula where pelicula.idpelicula = cinta.idpeliculafk"
                        + " and pelicula.idpelicula = " + lista.get(i) + ";");
                connection.commit();
                if (z == 1) {
                    System.out.println("Se agregó el registro de manera exitosa!");
                } else {
                    System.out.println("Error al agregar el registro!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error en la tabla");
            e.printStackTrace();
        }
    }

    public String consultarCorreo(String usuario) {
        Statement s = null;
        String correo = " ";
        try {

            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from usuario");
            while (rs.next()) {
                if (rs.getString("nombre").equals(usuario)) {
                    correo = rs.getString("correo");
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return correo;
    }

    public int consultarIdUsuario(String usuario) {
        int id = 0;
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from usuario");
            while (rs.next()) {
                if (rs.getString("nombre").equals(usuario)) {
                    id = rs.getInt("idmembresia");
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return id;
    }

    public int consultarIdEmpleado(String usuarioEmpleado) {
        int id = 0;
        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from empleado");
            while (rs.next()) {
                if (rs.getString("nombre").equals(usuarioEmpleado)) {
                    id = rs.getInt("idempleado");
                }
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return id;
    }

    public int consultarIdPrestamo() {
        int id = 0;
        String a = "";

        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from prestamo");
            if (rs.next()) {
                do {
                    a = rs.getString("idprestamo");
                } while (rs.next());

                id = Integer.parseInt(a);
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return id;
    }

    public int consultarIdDetallePrestamo() {
        int id = 0;
        String a = "";

        Statement s = null;
        try {
            s = connection.createStatement();
            ResultSet rs = s.executeQuery("select * from detalleprestamo");
            if (rs.next()) {
                do {
                    a = rs.getString("item");
                } while (rs.next());
                id = Integer.parseInt(a);
            }
        } catch (Exception e) {
            System.out.println("Problema en buscar registro");
            e.printStackTrace();
        }
        return id;
    }

    public void llenarDetallePrestamo(int item, int idprestamo, int i, int costo) {

        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO detalleprestamo (item,idprestamofk,idestadofk,valor) VALUES ('"
                    + item + "',' " + idprestamo + "',' " + i + "',' " + costo + "');");
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

    public void llenarUsuario(int id, String nombre, String apellido, String telefono, String direccion, String credito, String correo) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO usuario (idmembresia,nombre,apellido,telefono,direccion,credito,correo) VALUES ('"
                    + id + "',' " + nombre + "',' " + apellido + "',' " + telefono + "',' "
                    + direccion + "',' " + credito + "',' " + correo + "');");
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

            System.out.println("Problema en insertar registro estado");
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

    public void llenarPrestamo(int idprestamo, int iduser, int idemp) {
        Statement s = null;

        try {
            s = connection.createStatement();
            // INSERTA LOS DATOS
            int z = s.executeUpdate(
                    "INSERT INTO prestamo (idprestamo,idmembresiafk,idempleadofk,fechaprestamo) VALUES ('"
                    + idprestamo + "','" + iduser + "','" + idemp + "','" + "now() ');");
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

    public String getUsuarioEmpleado() {
        return usuarioEmpleado;
    }

}
