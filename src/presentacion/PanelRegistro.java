package presentacion;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class PanelRegistro extends JPanel{
    
    public static PanelRegistro instancia;
    
    //CONSTRUCTOR
    public PanelRegistro() {
        initComponents();
    }
    
    public void initComponents(){
        
        this.setBackground(new Color(243,243,243));
        this.setLayout(null);
        this.setBounds(0,0,500,350);
        
        titulo = new JLabel("REGISTRO");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(215, 25, 70, 10);
        this.add(titulo);
        
        parrafo1 = new JLabel("Ingresa los correspondientes datos para poder registrarte");
        parrafo1.setBounds(75, 60, 350, 20);
        parrafo1.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(parrafo1);

        nom = new JLabel("Nombre:");
        nom.setBounds(84,100,60,20);
        this.add(nom);
        
        nombre = new JTextField();
        nombre.setBounds(160,100,260,25);
        this.add(nombre);
        
        ape = new JLabel("Apellido:");
        ape.setBounds(84,130,60,20);
        this.add(ape);
        
        apellido = new JTextField();
        apellido.setBounds(160,130,260,25);
        this.add(apellido);
        
        cor = new JLabel("Correo:");
        cor.setBounds(84,160,60,20);
        this.add(cor);
        
        correo = new JTextField();
        correo.setBounds(160,160,260,25);
        this.add(correo);
        
        tel = new JLabel("Telefono:");
        tel.setBounds(84,190,60,20);
        this.add(tel);
        
        telefono = new JTextField();
        telefono.setBounds(160,190,260,25);
        this.add(telefono);
        
        dir = new JLabel("Direccion:");
        dir.setBounds(84,220,60,20);
        this.add(dir);
        
        direccion = new JTextField();
        direccion.setBounds(160,220,260,25);
        this.add(direccion);
        
        cred = new JLabel("Credito:");
        cred.setBounds(84,250,60,20);
        this.add(cred);
        
        credito = new JTextField();
        credito.setBounds(160,250,260,25);
        this.add(credito);
        
        id = new JLabel("ID:");
        id.setBounds(84,280,60,20);
        this.add(id);
        
        idm = new JTextField();
        idm.setBounds(160,280,260,25);
        this.add(idm);
        
        registrarse = new JButton("Guardar");
        registrarse.setBounds(84, 320, 165, 20);
        registrarse.setBackground(Color.black);
        registrarse.setForeground(Color.WHITE);
        this.add(registrarse);
        
        atras = new JButton("Atrás");
        atras.setBounds(255, 320, 165, 20);
        atras.setBackground(Color.black);
        atras.setForeground(Color.WHITE);
        this.add(atras);
        
    }
    
    public static PanelRegistro getInstancia(){
        if(instancia==null){
            instancia = new PanelRegistro();
        }
        return instancia;
    }
    
    private JLabel titulo,parrafo1,nom,ape,cor,tel,dir,cred,id;
    private JTextField nombre,apellido,correo,telefono,direccion,credito,idm;
    private JButton registrarse,atras;

    //GETTERS
    public JTextField getNombre() {
        return nombre;
    }

    public JTextField getApellido() {
        return apellido;
    }

    public JTextField getTelefono() {
        return telefono;
    }

    public JTextField getDireccion() {
        return direccion;
    }

    public JTextField getCredito() {
        return credito;
    }

    public JButton getRegistrarse() {
        return registrarse;
    }

    public JTextField getCorreo() {
        return correo;
    }

    public JButton getAtras() {
        return atras;
    }

    public JTextField getIdm() {
        return idm;
    }

    

}
