package clases;

import java.io.Serializable;

/**
 *
 * @author Alfred
 */
public class Proceso implements Serializable
{

    private int noPrioridad;
    private String nombre;
    private int quantums;

    public Proceso(int noPrioridad, String nombre, int quantums)
    {
        this.noPrioridad = noPrioridad;
        this.nombre = nombre;
        this.quantums = quantums;
    }

    /**
     * @return the noPrioridad
     */
    public int getNoPrioridad()
    {
        return noPrioridad;
    }

    /**
     * @param noPrioridad the noPrioridad to set
     */
    public void setNoPrioridad(int noPrioridad)
    {
        this.noPrioridad = noPrioridad;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the quantums
     */
    public int getQuantums()
    {
        return quantums;
    }

    /**
     * @param quantums the quantums to set
     */
    public void setQuantums(int quantums)
    {
        this.quantums = quantums;
    }

    @Override
    public String toString()
    {
        return "\tProceso{" + "nombre=" + nombre + ", noPrioridad=" + noPrioridad + ", quantums=" + quantums + '}';
    }
}
