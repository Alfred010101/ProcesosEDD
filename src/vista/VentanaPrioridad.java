package vista;

import clases.Cola;
import clases.Nodo;
import clases.NodoL;
import clases.Prioridad;
import clases.Proceso;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Alfred
 */
public class VentanaPrioridad extends JDialog
{

    JScrollPane scrollPane;
    DrawingPane drawingPane;
    NodoL nodoL;

    public VentanaPrioridad(JFrame frame, NodoL nodoL)
    {
        super(frame, true);
        if (nodoL != null)
        {
            this.setTitle("Vista de Procesos Prioridad " + nodoL.getEtiqueta());
        }
        this.nodoL = nodoL;
        this.setSize(700, 450);
        this.setLocationRelativeTo(frame);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents()
    {
        drawingPane = new DrawingPane(nodoL);
        scrollPane = new JScrollPane(drawingPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane);
    }
}

class DrawingPane extends JPanel
{

    private final int SQUARE_SIZE = 120;
    NodoL nodoL;

    public DrawingPane(NodoL nodoL)
    {
        this.nodoL = nodoL;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setFont(new Font("Dialog", Font.BOLD, 16));
        int positionX = 70;
        int positionY = 200;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        if (nodoL != null)
        {
            g2d.setColor(new Color(255, 112, 67));
            g2d.fillRect(positionX + 200, positionY - 180, SQUARE_SIZE, SQUARE_SIZE);
            g2d.setColor(new Color(230, 74, 25));
            g2d.drawRect(positionX + 200, positionY - 180, SQUARE_SIZE, SQUARE_SIZE);
            g2d.setColor(new Color(255, 255, 255));
            g.drawString("Prioridad: " + nodoL.getEtiqueta(), positionX + 215, positionY - 140);
            g.drawString("*Cola ", positionX + 235, positionY - 100);
            g2d.setColor(Color.BLACK);
            g.drawString("*Cola ", positionX + 40, positionY - 10);

            if (nodoL.getObj() instanceof Prioridad objPrioridad)
            {
                if (!objPrioridad.getC().esNull())
                {
                    Cola aux = new Cola();
                    Nodo nodo;
                    do
                    {
                        nodo = objPrioridad.getC().eliminar();

                        g2d.setColor(new Color(66, 165, 245));
                        g2d.fillRect(positionX, positionY, SQUARE_SIZE, SQUARE_SIZE);
                        g2d.setColor(new Color(25, 118, 210));
                        g2d.drawRect(positionX, positionY, SQUARE_SIZE, SQUARE_SIZE);
                        g2d.setColor(new Color(255, 255, 255));
                        if (nodo.getObj() instanceof Proceso objProceso)
                        {
                            g.drawString("Prioridad: " + objProceso.getNoPrioridad(), positionX + 15, positionY + 35);
                            g.drawString(objProceso.getNombre(), positionX + 15, positionY + 65);
                            g.drawString("Qua: " + objProceso.getQuantums(), positionX + 15, positionY + 95);
                        }
                        positionX += 200;
                        if (!objPrioridad.getC().esNull())
                        {
                            g2d.setColor(new Color(0, 0, 0));
                            int targetX = positionX - 5;
                            int targetY = positionY + 60;
                            g2d.drawLine(positionX - 80, positionY + 60, targetX, targetY);
                            g2d.drawLine(positionX - 25, positionY + 45, targetX, targetY);
                            g2d.drawLine(positionX - 25, positionY + 75, targetX, targetY);
                        }
                        aux.insertar(nodo);
                    } while (!objPrioridad.getC().esNull());
                    objPrioridad.setC(aux);

                    g2d.setColor(new Color(0, 0, 0));
                    g2d.drawLine(positionX - 80, positionY + 60, positionX - 40, positionY + 60);    //primera_linea
                    g2d.drawLine(positionX - 40, positionY + 60, positionX - 40, positionY + 140); //quiebre_abajo
                    g2d.drawLine(positionX - 40, positionY + 140, 20, positionY + 140);         //quiebre_izquierda
                    g2d.drawLine(20, positionY + 140, 20, positionY + 60);                   //quiebre_arriba
                    g2d.drawLine(20, positionY + 60, 65, positionY + 60);                    //linea_cabeza_65
                    g2d.drawLine(45, positionY + 45, 65, positionY + 60);                    //diagonal
                    g2d.drawLine(45, positionY + 75, 65, positionY + 60);                    //diagonal
                    positionY += SQUARE_SIZE + 30;
                    if (positionX > getWidth() || positionY > getHeight())
                    {
                        setPreferredSize(new Dimension(positionX, positionY));
                        revalidate();
                    }
                }
            }
        }
    }
}
