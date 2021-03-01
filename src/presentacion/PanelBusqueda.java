package presentacion;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Cistian Meneses y Pedro Higuera
 */
public class PanelBusqueda extends JPanel {

    public static PanelBusqueda instancia;

    //CONSTRUCTOR
    public PanelBusqueda() {
        initComponents();
    }

    //METODO QUE INICIA
    public void initComponents() {
        this.setBackground(new Color(243, 243, 243));
        this.setLayout(null);
        this.setBounds(0, 0, 500, 350);

        titulo = new JLabel("Buscar");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(215, 25, 70, 10);
        this.add(titulo);

        busqueda = new JTextField();
        busqueda.setBounds(30, 60, 260, 25);
        this.add(busqueda);

        opciones = new JComboBox();
        opciones.addItem("Titulo");
        opciones.addItem("Actor");
        opciones.addItem("Membresia");
        opciones.setBounds(300, 60, 120, 25);
        this.add(opciones);

        tabla = new JTable();
        tabla.setBounds(30, 100, 430, 180);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.doLayout();
        tabla.setEnabled(false);
        this.add(tabla);

        JScrollPane js = new JScrollPane(tabla);
        js.setBounds(30, 100, 430, 180);
        js.setVisible(true);
        this.add(js);

        libre = new JRadioButton("Libre",true);
        libre.setBounds(30, 290, 100, 25);
        this.add(libre);
        
        prestada = new JRadioButton("Prestada",false);
        prestada.setBounds(150, 290, 100, 25);
        this.add(prestada);
        
        dañada = new JRadioButton("Dañada",false);
        dañada.setBounds(280, 290, 100, 25);
        this.add(dañada);
        
        perdida = new JRadioButton("Perdida",false);
        perdida.setBounds(410, 290, 100, 25);
        this.add(perdida);
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(libre);
        grupo.add(prestada);
        grupo.add(dañada);
        grupo.add(perdida);
        
        buscar = new JButton("Buscar");
        buscar.setBounds(30, 320, 140, 20);
        buscar.setBackground(Color.black);
        buscar.setForeground(Color.WHITE);
        this.add(buscar);
        
        siguiente = new JButton("Siguiente");
        siguiente.setBounds(190, 320, 140, 20);
        siguiente.setBackground(Color.black);
        siguiente.setForeground(Color.WHITE);
        this.add(siguiente);

        atras = new JButton("Atrás");
        atras.setBounds(350, 320, 140, 20);
        atras.setBackground(Color.black);
        atras.setForeground(Color.WHITE);
        this.add(atras);
    }

    //SINGLETON
    public static PanelBusqueda getInstancia() {
        if (instancia == null) {
            instancia = new PanelBusqueda();
        }
        return instancia;
    }

    //ATRIBUTOS
    private JLabel titulo;
    private JTextField busqueda;
    private JComboBox opciones;
    private JButton buscar, atras, siguiente;
    private JTable tabla;
    private JRadioButton libre,prestada,dañada,perdida;

    //GETTERS
    public JTextField getBusqueda() {
        return busqueda;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public JButton getAtras() {
        return atras;
    }

    public JComboBox getOpciones() {
        return opciones;
    }

    public JTable getTabla() {
        return tabla;
    }

    public JRadioButton getLibre() {
        return libre;
    }

    public JRadioButton getPrestada() {
        return prestada;
    }

    public JRadioButton getDañada() {
        return dañada;
    }

    public JRadioButton getPerdida() {
        return perdida;
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    
}
