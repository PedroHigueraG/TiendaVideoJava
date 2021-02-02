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
    private String id,nombre,apellido,correo,telefono,direccion,credito;
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
        bd.EstablecerConexion();
    }

    public void limpiarCampos(){
        ventana.pRegistro.getIdm().setText("");
        ventana.pRegistro.getNombre().setText("");
        ventana.pRegistro.getApellido().setText("");
        ventana.pRegistro.getCorreo().setText("");
        ventana.pRegistro.getDireccion().setText("");
        ventana.pRegistro.getTelefono().setText("");
        ventana.pRegistro.getCredito().setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ventana.pRegistro.getRegistrarse()) {
            id = ventana.pRegistro.getIdm().getText();
             nombre = ventana.pRegistro.getNombre().getText();
             apellido = ventana.pRegistro.getApellido().getText();
             correo = ventana.pRegistro.getCorreo().getText();
             direccion = ventana.pRegistro.getDireccion().getText();
             telefono = ventana.pRegistro.getTelefono().getText();
             credito = ventana.pRegistro.getCredito().getText();
            
            if(id.length()==0 || nombre.length()==0 || apellido.length()==0 || correo.length()==0 || direccion.length()==0 || telefono.length()==0 || credito.length()==0){
                JOptionPane.showMessageDialog(ventana, "Por favor diligencie todos los datos");
            }else{
                int cred = Integer.parseInt(credito);
                int tel = Integer.parseInt(telefono);
                bd.AgregarUsuario(id, nombre, apellido, tel, direccion, cred, correo);
                JOptionPane.showMessageDialog(ventana, "Usuario registrado correctamente");
                limpiarCampos();
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
            bd.DetenerConexion();
            System.exit(0);
        }
    }
    
}
