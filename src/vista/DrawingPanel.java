  package vista;

import clases.Cola;
import clases.ListaCircularDoblementeLigada;
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
import javax.swing.JPanel;

/**
 *
 * @author Alfred
 */
public class DrawingPanel extends JPanel
{

    private final int SQUARE_SIZE = 120;
    private final ListaCircularDoblementeLigada lista;

    public DrawingPanel(ListaCircularDoblementeLigada lista)
    {
        this.lista = lista;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int positionX = 70;
        int positionY = 70;
        g.setFont(new Font("Dialog", Font.BOLD, 16));
        int maxY = positionY;
        int n = 1;
        if (!lista.esNull())
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(5));
            NodoL aux = lista.getRaiz().getSiguiente();
            do
            {
                g2d.setColor(new Color(245, 127, 23));
                g2d.fillRect(positionX, positionY, SQUARE_SIZE, SQUARE_SIZE);
                g2d.setColor(new Color(255, 179, 0));
                g2d.drawRect(positionX, positionY, SQUARE_SIZE, SQUARE_SIZE);
                g2d.setColor(new Color(255, 255, 255));
                g.drawString("Prioridad: " + aux.getEtiqueta(), positionX + 15, positionY + 40);
                g.drawString("*Cola " + n, positionX + 30, positionY + 80);
                g2d.setColor(Color.BLACK);
                g.drawString("Cola " + n++, positionX + 35, positionY + 185);

                if (aux != lista.getRaiz())
                {
                    //siguiente()
                    g2d.drawLine(positionX + 120, positionY + 40, positionX + 190, positionY + 40);
                    g2d.drawLine(positionX + 165, positionY + 25, positionX + 185, positionY + 40);
                    g2d.drawLine(positionX + 165, positionY + 55, positionX + 185, positionY + 40);
                    //anterior
                    g2d.drawLine(positionX + 120, positionY + 80, positionX + 190, positionY + 80);
                    g2d.drawLine(positionX + 140, positionY + 65, positionX + 120, positionY + 80);
                    g2d.drawLine(positionX + 140, positionY + 95, positionX + 120, positionY + 80);
                }
                if (aux.getSiguiente() == lista.getRaiz().getSiguiente())
                {
                    //siguiente()
                    g2d.drawLine(positionX + 120, positionY + 40, positionX + 160, positionY + 40);   //linea
                    g2d.drawLine(positionX + 160, positionY + 40, positionX + 160, positionY - 20);   //quiebre_arriba
                    g2d.drawLine(positionX + 160, positionY - 20, 30, positionY - 20);             //quiebre_izquierda
                    g2d.drawLine(30, positionY - 20, 30, positionY + 40);                       //quiebre_abajo
                    g2d.drawLine(30, positionY + 40, 70, positionY + 40);                       //quiebre_derecha
                    g2d.drawLine(50, positionY + 25, 70, positionY + 40);
                    g2d.drawLine(50, positionY + 55, 70, positionY + 40);
                    //anterior
                    g2d.drawLine(positionX + 120, positionY + 80, positionX + 160, positionY + 80);   //linea
                    g2d.drawLine(positionX + 160, positionY + 80, positionX + 160, positionY + 140);  //quiebre_abajo
                    g2d.drawLine(positionX + 160, positionY + 140, 30, positionY + 140);           //quiebre_izquierda
                    g2d.drawLine(30, positionY + 140, 30, positionY + 80);                      //quiebre_arriba
                    g2d.drawLine(30, positionY + 80, 70, positionY + 80);                       //quiebre_derecha
                    g2d.drawLine(positionX + 140, positionY + 65, positionX + 120, positionY + 80);
                    g2d.drawLine(positionX + 140, positionY + 95, positionX + 120, positionY + 80);
                }

                if (aux.getObj() instanceof Prioridad x)
                {
                    Cola cola = new Cola();
                    Nodo nodo;
                    int posY = positionY + 60;
                    do
                    {
                        posY += 170;
                        nodo = x.getC().eliminar();
                        if (nodo.getObj() instanceof Proceso y)
                        {
                            g2d.setColor(new Color(85, 139, 47));
                            g2d.fillRect(positionX, posY, SQUARE_SIZE, SQUARE_SIZE);
                            g2d.setColor(new Color(129, 199, 132));
                            g2d.drawRect(positionX, posY, SQUARE_SIZE, SQUARE_SIZE);
                            g2d.setColor(new Color(255, 255, 255));
                            g.drawString("Prioridad: " + y.getNoPrioridad(), positionX + 15, posY + 30);
                            g.drawString(y.getNombre(), positionX + 15, posY + 60);
                            g.drawString("Qua: " + y.getQuantums(), positionX + 15, posY + 90);
                            g2d.setColor(Color.GRAY);
                            if (!x.getC().esNull())
                            {
                                g2d.drawLine(positionX + 58, posY + 120, positionX + 58, posY + 165);
                                g2d.drawLine(positionX + 45, posY + 145, positionX + 58, posY + 165);
                                g2d.drawLine(positionX + 73, posY + 145, positionX + 58, posY + 165);
                            } else
                            {
                                g2d.drawLine(positionX + 58, posY + 120, positionX + 58, posY + 145);           //quiebre_abajo
                                g2d.drawLine(positionX + 58, posY + 145, positionX - 25, posY + 145);           //quiebre_izquierda
                                g2d.drawLine(positionX - 25, posY + 145, positionX - 25, positionY + 200);      //quiebre_arriba
                                g2d.drawLine(positionX + 58, positionY + 200, positionX - 25, positionY + 200); //quiebre_derecha
                                g2d.drawLine(positionX + 58, positionY + 200, positionX + 58, positionY + 235); //quiebre_abajo
                                g2d.drawLine(positionX + 45, positionY + 215, positionX + 58, positionY + 235);
                                g2d.drawLine(positionX + 73, positionY + 215, positionX + 58, positionY + 235);
                            }
                        }
                        cola.insertar(nodo);
                    } while (!x.getC().esNull());
                    x.setC(cola);
                    maxY = (maxY < posY) ? posY : maxY;
                }
                positionX += SQUARE_SIZE + 70;
                aux = aux.getSiguiente();
            } while (aux != lista.getRaiz().getSiguiente());
            if (positionX > getHeight() - 50 || maxY > getWidth() - 170)
            {
                setPreferredSize(new Dimension(positionX, maxY + 170));
                revalidate();
            }
        }
    }
}
