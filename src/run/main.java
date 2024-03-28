package run;

import clases.ListaCircularDoblementeLigada;
import controlador.ManipulacionArchivos;
import controlador.Var;
import java.awt.EventQueue;
import vista.VentanaPrincipal;

/**
 *
 * @author Alfred
 */
public class main
{

    public static void main(String[] args)
    {
        if (ManipulacionArchivos.cargar("datos.dat") instanceof ListaCircularDoblementeLigada x)
        {
            Var.setLista(x);
        } else
        {
            Var.setLista(new ListaCircularDoblementeLigada());
        }
        EventQueue.invokeLater(() ->
        {
            try
            {
                VentanaPrincipal ventana = new VentanaPrincipal();
                ventana.setVisible(true);
            } catch (Exception e)
            {
            }
        });
    }
}
