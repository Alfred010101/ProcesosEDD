package vista;

import clases.Proceso;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Alfred
 */
public class VentanaProceso extends JDialog
{

    private final Proceso proceso;
    private GridBagConstraints gbConstraints;

    public VentanaProceso(JFrame frame, Proceso proceso)
    {
        super(frame, "Siguiente proceso", true);
        this.proceso = proceso;
        this.setSize(350, 160);
        this.setLocationRelativeTo(frame);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(66, 165, 245));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        gbConstraints = new GridBagConstraints();
        JLabel label1 = new JLabel("Prioridad : ");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel label2 = new JLabel("Nombre : ");
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel label3 = new JLabel("Quantums : ");
        label3.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel prioridad = new JLabel("" + proceso.getNoPrioridad());
        prioridad.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel nombre = new JLabel(proceso.getNombre());
        nombre.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel num = new JLabel("" + proceso.getQuantums());
        num.setFont(new Font("Arial", Font.BOLD, 20));

        this.addComponent(label1, 0, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(prioridad, 0, 1, 1, 2, GridBagConstraints.WEST);
        this.addComponent(label2, 2, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(nombre, 2, 1, 2, 1, GridBagConstraints.WEST);
        this.addComponent(label3, 4, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(num, 4, 1, 1, 1, GridBagConstraints.WEST);
    }

    private void addComponent(Component c, int row, int column, int width, int height, int pos)
    {
        gbConstraints.gridx = column;
        gbConstraints.gridy = row;
        gbConstraints.gridwidth = width;
        gbConstraints.gridheight = height;
        gbConstraints.insets = new Insets(5, 5, 5, 5);
        gbConstraints.anchor = pos;
        this.add(c, gbConstraints);
    }
}
