package vista;

import clases.Proceso;
import controlador.Ctrl;
import controlador.ManipulacionArchivos;
import controlador.Var;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Alfred
 */
public class VentanaAlta extends JDialog
{

    private GridBagConstraints gbConstraints;
    private JTextField nombre;
    private JTextField quantums;
    //private JComboBox prioridad;
    private JTextField prioridad;
    private JButton cargar;
    private final DrawingPanel drawingPanel;

    public VentanaAlta(JFrame frame, DrawingPanel drawingPanel)
    {
        super(frame, "Alta proceso", true);
        this.drawingPanel = drawingPanel;
        this.setSize(400, 260);
        this.setLocationRelativeTo(frame);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
//        String[] items =
//        {
//            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
//        };

        //prioridad = new JComboBox(items);
        prioridad = new JTextField(20);
        nombre = new JTextField(20);
        quantums = new JTextField(20);
        cargar = new JButton("Cargar Proceso");

        gbConstraints = new GridBagConstraints();

        prioridad.setMinimumSize(new Dimension(45, quantums.getPreferredSize().height));
        nombre.setMinimumSize(new Dimension(180, nombre.getPreferredSize().height));
        quantums.setMinimumSize(new Dimension(45, quantums.getPreferredSize().height));

        this.addComponent(new JLabel("Prioridad : "), 0, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(prioridad, 0, 1, 1, 2, GridBagConstraints.WEST);
        this.addComponent(new JLabel("Nombre : "), 2, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(nombre, 2, 1, 2, 1, GridBagConstraints.WEST);
        this.addComponent(new JLabel("Quantums : "), 4, 0, 1, 1, GridBagConstraints.EAST);
        this.addComponent(quantums, 4, 1, 1, 1, GridBagConstraints.WEST);
        this.addComponent(cargar, 6, 2, 1, 1, GridBagConstraints.WEST);

        prioridad.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, prioridad.getText(), nombre);
            }
        });
        
        nombre.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, nombre.getText(), quantums);
            }
        });

        quantums.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                enterKeyPressed(e, quantums.getText(), cargar);
            }
        });

        cargar.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyChar() == '\n')
                {
                    cargarPoceso();
                }
            }
        });

        cargar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cargarPoceso();
            }
        });
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

    private void cargarPoceso()
    {
        //if (Ctrl.esNumeroValido(quantums.getText().trim()) && !nombre.getText().trim().isBlank())
        if (Ctrl.esNumeroValido(quantums.getText().trim()) && !nombre.getText().trim().isBlank() && Ctrl.esNumeroValido(prioridad.getText().trim()))
        {
            if (!Ctrl.buscarProceso(Var.getLista(), nombre.getText()))
            {
                //if (Ctrl.cargarPoceso(Var.getLista(), new Proceso(prioridad.getSelectedIndex() + 1, nombre.getText(), Integer.parseInt(quantums.getText().trim()))))
                if (Ctrl.cargarPoceso(Var.getLista(), new Proceso(Integer.parseInt(prioridad.getText().trim())
                        , nombre.getText(), Integer.parseInt(quantums.getText().trim()))))
                {
                    if (ManipulacionArchivos.guardar(Var.getLista(), "datos.dat"))
                    {
                        JOptionPane.showMessageDialog(this, "Proceso " + nombre.getText() + " cargado exitosamente", "Cargar Proceso", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        drawingPanel.repaint();
                    } else
                    {
                        //elimar proceso insertado
                    }
                } else
                {
                    JOptionPane.showMessageDialog(this, "No se ha podido cargar el proceso", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else
            {
                JOptionPane.showMessageDialog(this, "Ya existe un proceso con el nombre " + nombre.getText(), "Error de Datos", JOptionPane.WARNING_MESSAGE);
            }

        } else
        {
            JOptionPane.showMessageDialog(this, "Verifique que los datos sean validos", "Error de Datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void enterKeyPressed(KeyEvent e, String s, Object obj)
    {
        if (!s.isEmpty())
        {
            Ctrl.enter(e, obj);
        }
    }
}
