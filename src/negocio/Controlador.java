package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import persistencia.BasesDatos;
import persistencia.Correo;
import presentacion.VentanaPrincipal;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */

public class Controlador implements ActionListener{
    
    //ATRIBUTOS
    private String nombre,apellido,correo,telefono,direccion,credito;
    VentanaPrincipal ventana;
    BasesDatos bd;
    Correo mail;
    
    //CONTROLADOR
    public Controlador(VentanaPrincipal vista,BasesDatos base, Correo correo){
        
        ventana = vista;
        bd = base;
        mail = correo;
        ventana.pRegistro.getRegistrarse().addActionListener(this);
        ventana.pRegistro.getAtras().addActionListener(this);
        ventana.pInicial.getRegistrarse().addActionListener(this);
        ventana.pInicial.getSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ventana.pRegistro.getRegistrarse()) {
             nombre = ventana.pRegistro.getNombre().getText();
             apellido = ventana.pRegistro.getApellido().getText();
             correo = ventana.pRegistro.getCorreo().getText();
             direccion = ventana.pRegistro.getDireccion().getText();
             telefono = ventana.pRegistro.getTelefono().getText();
             credito = ventana.pRegistro.getCredito().getText();
            
            if(nombre.length()==0 || apellido.length()==0 || correo.length()==0 || direccion.length()==0 || telefono.length()==0 || credito.length()==0){
                JOptionPane.showMessageDialog(ventana, "Por favor diligencie todos los datos");
            }else{
                float cred = Float.parseFloat(credito);
            }
        }
        if (ae.getSource() == ventana.pRegistro.getAtras()) {
            ventana.pInicial.setVisible(true);
            ventana.pRegistro.setVisible(false);
        }
        if (ae.getSource() == ventana.pInicial.getRegistrarse()) {
            ventana.pInicial.setVisible(false);
            ventana.pRegistro.setVisible(true);
        }
        if (ae.getSource() == ventana.pInicial.getSalir()) {
            System.exit(0);
        }
    }
    
}
