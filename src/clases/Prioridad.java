package clases;

import java.io.Serializable;

/**
 *
 * @author Alfred
 */
public class Prioridad implements Serializable
{

    private int noPrioridad;
    private Cola c;

    public Prioridad(int noPrioridad, Cola c)
    {
        this.noPrioridad = noPrioridad;
        this.c = c;
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
     * @return the c
     */
    public Cola getC()
    {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Cola c)
    {
        this.c = c;
    }

    @Override
    public String toString()
    {
        return "Prioridad{" + "noPrioridad=" + noPrioridad + ", c=" + c + '}';
    }
}
