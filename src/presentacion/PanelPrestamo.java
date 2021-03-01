package presentacion;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class PanelPrestamo extends JPanel{
    
     public static PanelPrestamo instancia;

    //CONSTRUCTOR
    public PanelPrestamo() {
        initComponents();
    }

    //METODO QUE INICIA
    public void initComponents() {
        this.setBackground(new Color(243, 243, 243));
        this.setLayout(null);
        this.setBounds(0, 0, 500, 350);

        titulo = new JLabel("Prestamo");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(215, 25, 70, 10);
        this.add(titulo);

        
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
        
        
        totaltext = new JLabel("Total");
        totaltext.setBounds(30, 290, 140, 20);
        this.add(totaltext);
        
        total = new JTextField();
        total.setBounds(190, 290, 140, 20);
        this.add(total);
        
        guardar = new JButton("Guardar");
        guardar.setBounds(30, 320, 140, 20);
        guardar.setBackground(Color.black);
        guardar.setForeground(Color.WHITE);
        this.add(guardar);

        atras = new JButton("Atrás");
        atras.setBounds(190, 320, 140, 20);
        atras.setBackground(Color.black);
        atras.setForeground(Color.WHITE);
        this.add(atras);
    }

    //SINGLETON
    public static PanelPrestamo getInstancia() {
        if (instancia == null) {
            instancia = new PanelPrestamo();
        }
        return instancia;
    }

    //ATRIBUTOS
    private JLabel titulo,totaltext;
    private JTextField total;
    private JButton guardar, atras;
    private JTable tabla;
    private JCheckBox p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    
    //GETTERS

    public JTextField getTotal() {
        return total;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public JButton getAtras() {
        return atras;
    }

    public JTable getTabla() {
        return tabla;
    }
    
}
