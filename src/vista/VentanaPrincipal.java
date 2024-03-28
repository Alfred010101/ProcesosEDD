package vista;

import clases.Nodo;
import clases.NodoL;
import clases.Prioridad;
import controlador.Ctrl;
import controlador.ManipulacionArchivos;
import controlador.Var;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 *
 * @author Alfred
 */
public class VentanaPrincipal extends JFrame
{

    private JMenuBar menu;
    private JMenu menu1;
    private JMenu menu2;

    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;
    private JMenuItem item5;

    private JScrollPane scrollPane;
    private DrawingPanel drawingPanel;

    public VentanaPrincipal()
    {
        super("Vista de procesos");
        this.setSize(900, 700);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents()
    {
        UIManager.put("Menu.selectionBackground", new Color(25, 118, 210));
        UIManager.put("MenuItem.selectionBackground", new Color(33, 150, 243));
        UIManager.put("Menu.selectionForeground", new Color(255, 255, 255));
        UIManager.put("MenuItem.selectionForeground", new Color(255, 255, 255));

        menu = new JMenuBar();
        menu1 = new JMenu("Proceso");
        menu2 = new JMenu("Consulatr");

        item1 = new JMenuItem("Crear Proceso");
        item2 = new JMenuItem("Procesar Proceso");
        item3 = new JMenuItem("Eliminar Proceso");
        item4 = new JMenuItem("Procesos por Prioridad");
        item5 = new JMenuItem("Proximo Proceso");

        actualizarItmes();

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(new JPopupMenu.Separator());
        menu1.add(item3);
        menu2.add(item4);
        menu2.add(item5);

        menu.add(menu1);
        menu.add(menu2);

        drawingPanel = new DrawingPanel(Var.getLista());

        scrollPane = new JScrollPane(drawingPanel);//drawingPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajusta la velocidad de desplazamiento vertical
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        item1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new VentanaAlta(VentanaPrincipal.this, drawingPanel).setVisible(true);
                actualizarItmes();
            }
        });

        item2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                procesarProceso();
                actualizarItmes();
            }
        });

        item3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                eliminarProceso();
                actualizarItmes();
            }
        });

        item4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                consultarPrioridad();
            }
        });

        item5.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new VentanaProceso(VentanaPrincipal.this, Ctrl.siguienteProceso(Var.getLista())).setVisible(true);
            }
        });

        this.setJMenuBar(menu);
        this.add(scrollPane);

        pack();
    }

    private void procesarProceso()
    {
        if (!Var.getLista().esNull())
        {
            int opcion = JOptionPane.showOptionDialog(this, "Continuar procesamiento . . .", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]
            {
                "Sí", "No"
            }, "Sí");
            if (opcion == JOptionPane.YES_OPTION)
            {
                if (Var.getLista().getRaiz().getSiguiente().getObj() instanceof Prioridad x)
                {
                    Nodo tmp = x.getC().eliminar();
                    if (Ctrl.procesarPoceso(Var.getLista(), tmp, x.getC().esNull()))
                    {
                        if (ManipulacionArchivos.guardar(Var.getLista(), "datos.dat"))
                        {
                            JOptionPane.showMessageDialog(this, "Proceso " + tmp.getEtiqueta() + " pocesado exitosamente", "Procesar", JOptionPane.INFORMATION_MESSAGE);
                            drawingPanel.repaint();
                        } else
                        {
                            //restabelcer proceso
                        }
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "No se pudo procesar el proceso" + tmp.getEtiqueta(), "Procesar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        actualizarItmes();
    }

    private void eliminarProceso()
    {
        String nombre = JOptionPane.showInputDialog(VentanaPrincipal.this, "Ingrese el nombre del proceso a eliminar");
        if (nombre != null)
        {
            if (!nombre.trim().isBlank())
            {
                Nodo nodo = Ctrl.elimiarProceso(Var.getLista(), nombre);
                if (nodo == null)
                {
                    JOptionPane.showMessageDialog(VentanaPrincipal.this, "El proceso que trata de eliminar no se encuentra registrado", "Proceso no encontrado", JOptionPane.WARNING_MESSAGE);
                } else
                {
                    if (ManipulacionArchivos.guardar(Var.getLista(), "datos.dat"))
                    {
                        JOptionPane.showMessageDialog(VentanaPrincipal.this, "El proceso se a eliminado correctamente", "Proceso Eliminado", JOptionPane.INFORMATION_MESSAGE);
                        drawingPanel.repaint();
                        actualizarItmes();
                    } else
                    {
                        //restaurar proceso
                    }
                }
            } else
            {
                JOptionPane.showMessageDialog(VentanaPrincipal.this, "Verifique el nombre y vuelva a intentar", "Dato Invalido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void consultarPrioridad()
    {
        String quantums = JOptionPane.showInputDialog(this, "Ingrese la prioridad");
        if (quantums != null)
        {
            if (Ctrl.esNumeroValido(quantums.trim()))
            {
                int prioridad = Integer.parseInt(quantums.trim());
                NodoL nodo = Var.getLista().getNodoL(prioridad);
                if (nodo != null)
                {
                    new VentanaPrioridad(VentanaPrincipal.this, nodo).setVisible(true);
                } else
                {
                    JOptionPane.showMessageDialog(this, "La prioridad proporcionada no esta registrada", "Prioridad no Encontrada", JOptionPane.WARNING_MESSAGE);
                }
            } else
            {
                JOptionPane.showMessageDialog(this, "La prioridad proporcionada no es valida", "Error de Datos", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void actualizarItmes()
    {
        if (Var.getLista().esNull())
        {
            item2.setEnabled(false);
            item3.setEnabled(false);
            item4.setEnabled(false);
            item5.setEnabled(false);
        } else
        {
            item2.setEnabled(true);
            item3.setEnabled(true);
            item4.setEnabled(true);
            item5.setEnabled(true);
        }
    }
}
