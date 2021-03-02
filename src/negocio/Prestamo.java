package negocio;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import persistencia.BasesDatos;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class Prestamo {

    private ArrayList lista;
    BasesDatos bd;

    public void crearLista() {
        if (lista == null) {
            lista = new ArrayList();
        }
        if (bd == null) {
            bd = new BasesDatos();
        }
    }

    public void agregarLista(String id) {
        lista.add(id);
    }

    public boolean validarDato(String dato) {
        if (dato.equals("1") || dato.equals("2") || dato.equals("3") || dato.equals("4")
                || dato.equals("5") || dato.equals("6") || dato.equals("7") || dato.equals("8")
                || dato.equals("9") || dato.equals("10")) {
            if (bd.consultarEstado(dato)) {
                agregarLista(dato);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "pelicula no disponible, seleccione otra");
                return false;
            }
        } else {
            return false;
        }
    }

    public void llenarTabla(JTable tabla) {
        bd.consultarPrestamo(lista, tabla);
    }

    public void limpiar() {
        bd.actualizarEstado(lista);
        lista.clear();
    }

    public ArrayList getLista() {
        return lista;
    }

}

