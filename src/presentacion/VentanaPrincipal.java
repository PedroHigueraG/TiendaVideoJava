package presentacion;

import javax.swing.JFrame;

/**
 *
 * @author Cristian Meneses y Pedro Higuera
 */
public class VentanaPrincipal extends JFrame{
    
    //ATRIBUTOS
    public static VentanaPrincipal instancia;
    
    //CONSTRUCTOR
    public VentanaPrincipal(){
        initComponents();
    }
    
    //INICIAR COMPONENTES
    public void initComponents(){
        setResizable(false);
        setTitle("Tienda Video");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }
    
    //SINGLETON
    public static VentanaPrincipal getInstancia(){
        if(instancia==null){
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }
}
