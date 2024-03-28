package clases;

import java.io.Serializable;

/**
 *
 * @author Alfred
 */
public class ListaCircularDoblementeLigada implements Serializable
{

    private NodoL raiz;

    /**
     * @return the raiz
     */
    public NodoL getRaiz()
    {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(NodoL raiz)
    {
        this.raiz = raiz;
    }

    public boolean esNull()
    {
        return raiz == null;
    }

    public boolean insertar(NodoL nodo)
    {
        if (nodo == null)
        {
            System.out.println("No se pueden insertar nodos nulos");
            return false;
        }

        if (raiz == null)
        {
            raiz = nodo;
            raiz.setSiguiente(raiz);
            raiz.setAnterior(raiz);
            return true;
        }

        if (nodo.getEtiqueta().compareTo(raiz.getSiguiente().getEtiqueta()) < 0)
        {
            nodo.setSiguiente(raiz.getSiguiente());
            nodo.setAnterior(raiz);
            raiz.getSiguiente().setAnterior(nodo);
            raiz.setSiguiente(nodo);
            return true;
        }

        if (nodo.getEtiqueta().compareTo(raiz.getEtiqueta()) > 0)
        {
            nodo.setSiguiente(raiz.getSiguiente());
            nodo.setAnterior(raiz);
            raiz.getSiguiente().setAnterior(nodo);
            raiz.setSiguiente(nodo);
            raiz = nodo;
            return true;
        }

        NodoL aux = raiz.getSiguiente();
        while (aux != raiz)
        {
            if (nodo.getEtiqueta().compareTo(aux.getSiguiente().getEtiqueta()) < 0)
            {
                nodo.setSiguiente(aux.getSiguiente());
                nodo.setAnterior(aux);
                aux.getSiguiente().setAnterior(nodo);
                aux.setSiguiente(nodo);
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public NodoL eliminar(Integer etiqueta)
    {
        if (raiz == null)
        {
            return null;
        }

        NodoL aux = raiz;
        if (raiz.getEtiqueta().equals(etiqueta) && raiz.getSiguiente() == raiz && raiz.getAnterior() == raiz)
        {
            raiz = null;
            return aux;
        }

        if (raiz.getSiguiente().getEtiqueta().equals(etiqueta))
        {
            aux = raiz.getSiguiente();
            raiz.setSiguiente(aux.getSiguiente());
            raiz.getSiguiente().setAnterior(raiz);
            aux.setSiguiente(null);
            aux.setAnterior(null);
            return aux;
        }

        if (raiz.getEtiqueta().equals(etiqueta))
        {
            raiz = aux.getAnterior();
            raiz.setSiguiente(aux.getSiguiente());
            raiz.getSiguiente().setAnterior(raiz);
            aux.setSiguiente(null);
            aux.setAnterior(null);
            return aux;
        }

        aux = aux.getSiguiente().getSiguiente();
        while (aux != raiz)
        {
            if (aux.getEtiqueta().compareTo(etiqueta) > 0)
            {
                System.out.print("No encontrado ");
                return null;
            }
            if (aux.getEtiqueta().equals(etiqueta))
            {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                aux.setSiguiente(null);
                aux.setAnterior(null);
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public NodoL getNodoL(Integer prioridad)
    {
        if (!this.esNull())
        {
            NodoL aux = raiz;
            do
            {
                if (aux.getEtiqueta().equals(prioridad))
                {
                    return aux;
                }
                aux = aux.getSiguiente();
            } while (aux != raiz);
        }
        return null;
    }

    //podemos eliminar este metodo y usar el de arriba
    public boolean existeNodo(Integer prioridad)
    {
        if (!this.esNull())
        {
            NodoL aux = raiz;
            do
            {
                if (aux.getEtiqueta().equals(prioridad))
                {
                    return true;
                }
                aux = aux.getSiguiente();
            } while (aux != raiz);
        }
        return false;
    }

    public void desplegarSig()
    {
        if (raiz != null)
        {
            NodoL aux = raiz.getSiguiente();
            do
            {
                System.out.print(aux.getEtiqueta() + "\t");
                aux = aux.getSiguiente();
            } while (aux != raiz.getSiguiente());
            System.out.println("");
        } else
        {
            System.out.println("Lista vacia");
        }
    }

    public void desplegarRA()
    {
        if (raiz != null)
        {
            desplegarRecursivoAtras(raiz);
            System.out.println("");
        } else
        {
            System.out.println("Lista vacia");
        }
    }

    private void desplegarRecursivoAtras(NodoL nodo)
    {
        if (nodo != raiz.getSiguiente())
        {
            desplegarRecursivoAtras(nodo.getAnterior());
        }
        System.out.print(nodo.getEtiqueta() + "\t");
    }

    public void desplegarAnt()
    {
        if (raiz != null)
        {
            NodoL aux = raiz;
            do
            {
                System.out.print(aux.getEtiqueta() + "\t");
                aux = aux.getAnterior();
            } while (aux != raiz);
            System.out.println("");
        } else
        {
            System.out.println("Lista vacia");
        }
    }

    public void desplegarRS()
    {
        if (raiz != null)
        {
            desplegarRecursivoSiguiente(raiz.getSiguiente());
            System.out.println("");
        } else
        {
            System.out.println("Lista vacia");
        }
    }

    private void desplegarRecursivoSiguiente(NodoL nodo)
    {
        if (nodo != raiz)
        {
            desplegarRecursivoSiguiente(nodo.getSiguiente());
        }
        System.out.print(nodo.getEtiqueta() + "\t");
    }
}
