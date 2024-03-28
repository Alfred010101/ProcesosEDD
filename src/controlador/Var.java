package controlador;

import clases.ListaCircularDoblementeLigada;

/**
 *
 * @author Alfred
 */
public class Var
{

    private static ListaCircularDoblementeLigada lista = null;

    /**
     * @return the lista
     */
    public static ListaCircularDoblementeLigada getLista()
    {
        return lista;
    }

    /**
     * @param list the lista to set
     */
    public static void setLista(ListaCircularDoblementeLigada list)
    {
        lista = list;
    }
}
