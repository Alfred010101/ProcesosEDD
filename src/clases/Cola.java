package clases;

import java.io.Serializable;

/**
 *
 * @author Alfred
 */
public class Cola implements Serializable
{

    private Nodo atras;

    /**
     * @return the atras
     */
    public Nodo getAtras()
    {
        return atras;
    }

    /**
     * @param atras the atras to set
     */
    public void setAtras(Nodo atras)
    {
        this.atras = atras;
    }

    public void insertar(Nodo nodo)
    {
        if (atras == null)
        {
            nodo.setSiguiente(nodo);
        } else
        {
            nodo.setSiguiente(atras.getSiguiente());
            atras.setSiguiente(nodo);
        }
        atras = nodo;
    }

    public Nodo eliminar()
    {
        if (atras == null)
        {
            System.out.println("Cola vacia");
            return null;
        }
        Nodo nodo = atras.getSiguiente();
        if (atras == nodo)
        {
            atras = null;
        } else
        {
            atras.setSiguiente(nodo.getSiguiente());
        }
        nodo.setSiguiente(null);
        return nodo;
    }

    public boolean esNull()
    {
        return atras == null;
    }
}
