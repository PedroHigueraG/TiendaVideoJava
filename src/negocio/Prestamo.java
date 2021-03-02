package negocio;

import java.util.ArrayList;
import java.util.Iterator;
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
    }

    public void agregarLista(String id) {
        lista.add(id);
    }

    public boolean validarDato(String dato) {
        if (dato.equals("1") || dato.equals("2") || dato.equals("3") || dato.equals("4")
                || dato.equals("5") || dato.equals("6") || dato.equals("7") || dato.equals("8")
                || dato.equals("9") || dato.equals("10")) {
            agregarLista(dato);
            return true;
        } else {
            return false;
        }
    }
    
    public void llenarTabla(JTable tabla){
        bd = new BasesDatos();
        bd.consultarPrestamo(lista,tabla);
    }
    
    public void mostrarLista() {
        for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            System.out.println(next);
            
        }
        
    }
    
}
