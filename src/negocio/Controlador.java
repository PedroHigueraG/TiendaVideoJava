package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import persistencia.Archivo;
import persistencia.BasesDatos;
import persistencia.Correo;
import presentacion.VentanaPrincipal;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class Controlador implements ActionListener {

    //ATRIBUTOS
    private String nombre, apellido, correo, telefono, direccion, credito;
    private int id;
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
        ventana.pInicial.getIngresar().addActionListener(this);
        ventana.pIngreso.getAtras().addActionListener(this);
        ventana.pIngreso.getIngresar().addActionListener(this);
        ventana.pBusqueda.getBuscar().addActionListener(this);
        ventana.pBusqueda.getAtras().addActionListener(this);
        ventana.pBusqueda.getSiguiente().addActionListener(this);
        ventana.pPrestamo.getAtras().addActionListener(this);
        ventana.pPrestamo.getGuardar().addActionListener(this);
        bd.EstablecerConexion();
        bd.consultarVacios();
    }


    public void limpiarCampos(){
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
            nombre = ventana.pRegistro.getNombre().getText();
            apellido = ventana.pRegistro.getApellido().getText();
            correo = ventana.pRegistro.getCorreo().getText();
            direccion = ventana.pRegistro.getDireccion().getText();
            telefono = ventana.pRegistro.getTelefono().getText();
            credito = ventana.pRegistro.getCredito().getText();

            if (nombre.length() == 0 || apellido.length() == 0 || correo.length() == 0 || direccion.length() == 0 || telefono.length() == 0 || credito.length() == 0) {
                JOptionPane.showMessageDialog(ventana, "Por favor diligencie todos los datos");
            } else {
                int cred = Integer.parseInt(credito);
                int tel = Integer.parseInt(telefono);
                System.out.println(tel);
                id = bd.consultarId();
                id++;
                bd.AgregarUsuario(id, nombre, apellido, tel, direccion, cred, correo);
                JOptionPane.showMessageDialog(ventana, "Usuario registrado correctamente");
                mail.enviarCorreo(correo, nombre, apellido);
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
        if (ae.getSource() == ventana.pInicial.getIngresar()) {
            ventana.pInicial.setVisible(false);
            ventana.pIngreso.setVisible(true);
        }
        if (ae.getSource() == ventana.pIngreso.getAtras()) {
            ventana.pInicial.setVisible(true);
            ventana.pIngreso.setVisible(false);
        }
        if (ae.getSource() == ventana.pIngreso.getIngresar()) {
            String user = ventana.pIngreso.getUsuario().getText();
            String contrasena = new String(ventana.pIngreso.getContraseña().getPassword());

            boolean usuario = bd.consultarEmpleado(" "+user," "+contrasena);
            System.out.println(usuario);

            if (usuario) {
                JOptionPane.showMessageDialog(ventana, "bienvenido " + bd.consultarNombre(" "+contrasena));
                ventana.pBusqueda.setVisible(true);
                ventana.pIngreso.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrecta, por favor confirme");
                ventana.pIngreso.getUsuario().setText("");
                ventana.pIngreso.getContraseña().setText("");
            }
        }
        if (ae.getSource() == ventana.pBusqueda.getBuscar()) {
            String op = (String)ventana.pBusqueda.getOpciones().getSelectedItem();
            String titulo = ventana.pBusqueda.getBusqueda().getText();
            String estado="";
            if(ventana.pBusqueda.getLibre().isSelected()){
                estado=" L";
            }if(ventana.pBusqueda.getPrestada().isSelected()){
                estado=" P";
            }if(ventana.pBusqueda.getDañada().isSelected()){
                estado=" D";
            }if(ventana.pBusqueda.getPerdida().isSelected()){
                estado=" PP";
            }
            if("Titulo".equals(op)){
                bd.consultarPelicula(ventana.pBusqueda.getTabla(),titulo,estado);
            }
            if("Actor".equals(op)){
                bd.consultarActor(ventana.pBusqueda.getTabla(),titulo,estado);
            }
            if("Membresia".equals(op)){
                bd.consultarMembresia(ventana.pBusqueda.getTabla(), titulo);
            }
        }
        if (ae.getSource() == ventana.pBusqueda.getSiguiente()) {
           //bd.consultarPeliculaLibre(ventana.pPrestamo.getTabla());
            ventana.pBusqueda.setVisible(false);
            ventana.pPrestamo.setVisible(true);
        }
        if (ae.getSource() == ventana.pBusqueda.getAtras()) {
            ventana.pBusqueda.setVisible(false);
            ventana.pIngreso.setVisible(true);
        }
        if (ae.getSource() == ventana.pPrestamo.getAtras()) {
            ventana.pPrestamo.setVisible(false);
            ventana.pBusqueda.setVisible(true);
        }
        if (ae.getSource() == ventana.pInicial.getSalir()) {
            bd.DetenerConexion();
            System.exit(0);
        }
    }

}
