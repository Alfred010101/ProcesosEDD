package controlador;

import clases.Cola;
import clases.ListaCircularDoblementeLigada;
import clases.Nodo;
import clases.NodoL;
import clases.Prioridad;
import clases.Proceso;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Alfred
 */
public class Ctrl
{

    //Revisada OK
    public static boolean buscarProceso(ListaCircularDoblementeLigada lista, String nombre)
    {
        if (!lista.esNull())
        {
            NodoL aux = lista.getRaiz();
            do
            {
                if (aux.getObj() instanceof Prioridad x)
                {
                    if (buscarProceso(x, nombre))
                    {
                        return true;
                    }
                }
                aux = aux.getSiguiente();
            } while (aux != lista.getRaiz());
        }
        return false;
    }

    //Revisada OK
    public static boolean buscarProceso(Prioridad objPrioridad, String nombre)
    {
        boolean found = false;
        Cola aux = new Cola();
        Nodo nodo;
        do
        {
            nodo = objPrioridad.getC().eliminar();
            if (nodo.getEtiqueta().equals(nombre))
            {
                found = true;
            }
            aux.insertar(nodo);
        } while (!objPrioridad.getC().esNull());
        objPrioridad.setC(aux);
        return found;
    }

    //Revisada OK
    public static boolean cargarPoceso(ListaCircularDoblementeLigada lista, Proceso objProceso)
    {
        try
        {
            Nodo<Proceso> nodo = new Nodo<>(objProceso.getNombre(), objProceso);
            NodoL<Prioridad> nodoL;
            if (lista.esNull() || !lista.existeNodo(objProceso.getNoPrioridad()))
            {
                Cola c = new Cola();
                c.insertar(nodo);
                Prioridad objPrioridad = new Prioridad(objProceso.getNoPrioridad(), c);
                nodoL = new NodoL(objPrioridad.getNoPrioridad(), objPrioridad);
                return lista.insertar(nodoL);
            } else
            {
                nodoL = lista.getNodoL(objProceso.getNoPrioridad());
                if (nodoL != null)
                {
                    nodoL.getObj().getC().insertar(nodo);
                    return true;
                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error...", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //en revicion .....
    public static boolean procesarPoceso(ListaCircularDoblementeLigada lista, Nodo nodo, boolean banderaCola)
    {
        try
        {
            //NodoL<Prioridad> nodoL;
            if (nodo.getObj() instanceof Proceso x)
            {
                if (banderaCola)
                {
                    lista.eliminar(x.getNoPrioridad());
                }

                x.setQuantums(x.getQuantums() - 1);
                x.setNoPrioridad(x.getNoPrioridad() + 1);

                if (x.getQuantums() > 0)
                {
                    return cargarPoceso(lista, x);
                    //aqui habia mas codigo
                } else
                {
                    return true;
                }
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e, "Error...", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //.......................
    public static Nodo elimiarProceso(ListaCircularDoblementeLigada lista, String nombre)
    {
        if (!lista.esNull())
        {
            NodoL aux = lista.getRaiz();
            do
            {
                if (aux.getObj() instanceof Prioridad x)
                {
                    Nodo nodo = eliminarProceso(x, nombre);
                    if (nodo != null)
                    {
                        if (x.getC().esNull())
                        {
                            lista.eliminar(x.getNoPrioridad());
                        }
                        return nodo;
                    }
                }
                aux = aux.getSiguiente();
            } while (aux != lista.getRaiz());
        }
        return null;
    }

    //.......................
    public static Nodo eliminarProceso(Prioridad objPrioridad, String nombre)
    {
        Cola auxC = new Cola();
        Nodo auxN;
        Nodo nodo = null;
        do
        {
            auxN = objPrioridad.getC().eliminar();
            if (auxN.getEtiqueta().equals(nombre))
            {
                nodo = auxN;
            } else
            {
                auxC.insertar(auxN);
            }
        } while (!objPrioridad.getC().esNull());
        objPrioridad.setC(auxC);
        return nodo;
    }

    //Revisado OK //............
    public static Proceso siguienteProceso(ListaCircularDoblementeLigada lista)
    {
        Proceso objProceso = null;
        if (!lista.esNull())
        {
            NodoL nodoL = lista.getRaiz().getSiguiente();
            if (nodoL.getObj() instanceof Prioridad x)
            {
                if (!x.getC().esNull())
                {
                    Cola cola = new Cola();
                    Nodo nodo = x.getC().eliminar();
                    if (nodo.getObj() instanceof Proceso y)
                    {
                        objProceso = y;
                    }
                    cola.insertar(nodo);
                    while (!x.getC().esNull())
                    {
                        cola.insertar(x.getC().eliminar());
                    }
                    x.setC(cola);
                }
            }
        }
        return objProceso;
    }

    //Revisada OK
    public static boolean esNumeroValido(String s)
    {
        try
        {
            int a = Integer.parseInt(s);
            if (a > 0)
            {
                return true;
            }
        } catch (NumberFormatException e)
        {
        } catch (Exception e)
        {
        }
        return false;
    }

    //Revisado OK //............
    public static void enter(KeyEvent ke, Object obj)
    {
        if (ke.getKeyChar() == '\n')
        {
            if (obj != null)
            {
                if (obj instanceof JTextField jt)
                {
                    jt.setSelectionStart(0);
                    jt.setSelectionEnd(jt.getText().length());
                    jt.requestFocus();
                }
                if (obj instanceof JButton jt)
                {
                    jt.requestFocus();
                }
            }
        }
    }

    //Revisado OK //............
    public static void desplegar(ListaCircularDoblementeLigada lista)
    {
        if (!lista.esNull())
        {
            NodoL aux = lista.getRaiz().getSiguiente();
            do
            {
                System.out.println(aux.getEtiqueta() + " :: ");
                if (aux.getObj() instanceof Prioridad x)
                {
                    x.setC(desplegar(x.getC()));
                }
                aux = aux.getSiguiente();
            } while (aux != lista.getRaiz().getSiguiente());
            System.out.println("====================");
        } else
        {
            System.out.println("Lista vAcia");
        }
    }

    //Revisado OK //............
    public static Cola desplegar(Cola cola)
    {
        Cola aux = null;
        if (!cola.esNull())
        {
            aux = new Cola();
            Nodo nodo;
            do
            {
                nodo = cola.eliminar();
                System.out.println(nodo.getObj().toString());
                aux.insertar(nodo);
            } while (!cola.esNull());
        } else
        {
            System.out.println("Cola vAcia");
        }
        return aux;
    }
}
