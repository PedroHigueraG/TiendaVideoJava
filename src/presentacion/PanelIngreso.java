package presentacion;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class PanelIngreso extends JPanel{
    public static PanelIngreso instancia;
    
    //CONSTRUCTOR
    public PanelIngreso() {
        initComponents();
    }
    
    //METODO INICIAL
    public void initComponents(){
        
        this.setBackground(new Color(243,243,243));
        this.setLayout(null);
        this.setBounds(0,0,500,350);
        
        titulo = new JLabel("INGRESAR");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(215, 25, 70, 10);
        this.add(titulo);
        
        parrafo1 = new JLabel("Ingresa los correspondientes datos para poder acceder");
        parrafo1.setBounds(75, 60, 350, 20);
        parrafo1.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(parrafo1);
        
        user = new JLabel("Usuario:");
        user.setBounds(84,160,80,20);
        this.add(user);
        
        usuario = new JTextField();
        usuario.setBounds(160,160,260,25);
        this.add(usuario);
        
        pass = new JLabel("Contraseña:");
        pass.setBounds(84,190,80,20);
        this.add(pass);
        
        contraseña = new JTextField();
        contraseña.setBounds(160,190,260,25);
        this.add(contraseña);
        
        ingresar = new JButton("Ingresar");
        ingresar.setBounds(84, 320, 165, 20);
        ingresar.setBackground(Color.black);
        ingresar.setForeground(Color.WHITE);
        this.add(ingresar);
        
        atras = new JButton("Atrás");
        atras.setBounds(255, 320, 165, 20);
        atras.setBackground(Color.black);
        atras.setForeground(Color.WHITE);
        this.add(atras);
    }
    
     //SINGLETON
    public static PanelIngreso getInstancia(){
        if(instancia==null){
            instancia = new PanelIngreso();
        }
        return instancia;
    }
    
    private JLabel titulo,parrafo1,user,pass;
    private JTextField usuario,contraseña;
    private JButton ingresar,atras;

    public JButton getIngresar() {
        return ingresar;
    }

    public JButton getAtras() {
        return atras;
    }

    public JTextField getUsuario() {
        return usuario;
    }

    public JTextField getContraseña() {
        return contraseña;
    }
    
}
