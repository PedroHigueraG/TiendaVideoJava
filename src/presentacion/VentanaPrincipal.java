package presentacion;

import javax.swing.JFrame;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class VentanaPrincipal extends JFrame{
    
    //ATRIBUTOS
    public static VentanaPrincipal instancia;
    public PanelPrincipal pInicial = PanelPrincipal.getInstancia();
    public PanelRegistro pRegistro = PanelRegistro.getInstancia();
    
    //CONSTRUCTOR
    public VentanaPrincipal(){
        initComponents();
    }
    
    //INICIAR COMPONENTES
    public void initComponents(){
        setResizable(false);
        setTitle("Tienda Video");
        setSize(500,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
        pInicial.setVisible(false);
        add(pInicial);
        add(pRegistro);
    }
    
    //SINGLETON
    public static VentanaPrincipal getInstancia(){
        if(instancia==null){
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }
}
