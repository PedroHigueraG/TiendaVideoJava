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
public class PanelPrincipal extends JPanel{
    
    //INSTANCIA
    public static PanelPrincipal instancia;
    
    
    //CONSTRUCTOR
    public PanelPrincipal() {
        initComponents();
    }
    
    //INICIAR COMPONENTES
    public void initComponents(){
        
        this.setBackground(new Color(243,243,243));
        this.setLayout(null);
        this.setBounds(0,0,500,350);
        
        titulo = new JLabel("MENÚ PRINCIPAL");
        titulo.setBounds(200, 25, 100, 10);
        this.add(titulo);
        
        registrarse = new JButton("Registrar Cliente");
        registrarse.setBounds(150, 115, 200, 40);
        registrarse.setBackground(Color.black);
        registrarse.setForeground(Color.WHITE);
        this.add(registrarse);
        
        ingresar = new JButton("Ingresar Empleado");
        ingresar.setBounds(150, 165, 200, 40);
        ingresar.setBackground(Color.black);
        ingresar.setForeground(Color.WHITE);
        this.add(ingresar);
        
        salir = new JButton("Salir");
        salir.setBounds(150, 305, 200, 20);
        salir.setBackground(Color.black);
        salir.setForeground(Color.WHITE);
        this.add(salir);
        
    }
    
    //SINGLETON
    public static PanelPrincipal getInstancia(){
        if(instancia==null){
            instancia = new PanelPrincipal();
        }
        return instancia;
    }
    
    //ATRIBUTOS
    private JLabel titulo;
    private JButton registrarse,ingresar, salir;

    public JButton getRegistrarse() {
        return registrarse;
    }

    public JButton getSalir() {
        return salir;
    }

    public JButton getIngresar() {
        return ingresar;
    }
    
    
}
