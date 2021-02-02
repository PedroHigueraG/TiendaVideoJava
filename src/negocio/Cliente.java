package negocio;

import persistencia.BasesDatos;
import persistencia.Correo;
import presentacion.VentanaPrincipal;

/**
 *
 * @author Cistian Meneses y Pedro Higuera
 */
public class Cliente {
        
    public static void main(String []args){
       
        VentanaPrincipal ventana = VentanaPrincipal.getInstancia();
        BasesDatos bd = new BasesDatos();
        Correo mail = new Correo();
        Controlador controller = new Controlador(ventana,bd,mail);
        
    }
}
