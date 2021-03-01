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

        /*
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
        */
        p1 = new JCheckBox("Peli 1");
        p1.setBounds(30,50,80,20);
        this.add(p1);
        
        p2 = new JCheckBox("Peli 1");
        p2.setBounds(30,80,80,20);
        this.add(p2);
        
        p3 = new JCheckBox("Peli 1");
        p3.setBounds(30,110,80,20);
        this.add(p3);
        
        p4 = new JCheckBox("Peli 1");
        p4.setBounds(30,140,80,20);
        this.add(p4);
        
        p5 = new JCheckBox("Peli 1");
        p5.setBounds(30,170,80,20);
        this.add(p5);
        
        p6 = new JCheckBox("Peli 1");
        p6.setBounds(30,200,80,20);
        this.add(p6);
        
        p7 = new JCheckBox("Peli 1");
        p7.setBounds(30,230,80,20);
        this.add(p7);
        
        p8 = new JCheckBox("Peli 1");
        p8.setBounds(30,260,80,20);
        this.add(p8);
        
        p9 = new JCheckBox("Peli 1");
        p9.setBounds(30,290,80,20);
        this.add(p9);
        
        p10 = new JCheckBox("Peli 1");
        p10.setBounds(30,310,80,20);
        this.add(p10);
        
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
