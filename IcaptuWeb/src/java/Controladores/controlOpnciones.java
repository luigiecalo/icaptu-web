/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.io.Serializable;
import java.sql.SQLException;


/**
 *
 * @author USUARIO
 */
public class controlOpnciones implements Serializable{
    private boolean mostDensidad=false;
    private boolean mostRSolidos=false;
    private boolean mostFisitu=false;
    private boolean mostFislab=false;
    private boolean mostMicrobio=false;


public void mostrarDensidad(){
    setMostDensidad(true);
    setMostRSolidos(false);
    setMostFisitu(false);
    setMostFislab(false);
    setMostMicrobio(false);
}

public void mostrarRSolidos() throws SQLException, Exception{
    System.out.println("active los solidos");
    setMostDensidad(false);
    setMostRSolidos(true);
    setMostFisitu(false);
    setMostFislab(false);
    setMostMicrobio(false);
    controlMuestraResiduo.obtenerListaR();
    controlMuestraResiduo.optenerMuestras();
}
public void mostrarFisitu(){
    setMostDensidad(false);
    setMostRSolidos(false);
    setMostFisitu(true);
    setMostFislab(false);
    setMostMicrobio(false);
}

public void mostrarFislab(){
    setMostDensidad(false);
    setMostRSolidos(false);
    setMostFisitu(false);
    setMostFislab(true);
    setMostMicrobio(false);
   }
public void mostrarMicrobio(){
    setMostDensidad(false);
    setMostRSolidos(false);
    setMostFisitu(false);
    setMostFislab(false);
    setMostMicrobio(true);
}
    /** Creates a new instance of controlOpnciones */
    public controlOpnciones() {
    }

    /**
     * @return the mostDensidad
     */
    public boolean getMostDensidad() {
        return mostDensidad;
    }

    /**
     * @param mostDensidad the mostDensidad to set
     */
    public void setMostDensidad(boolean mostDensidad) {
        this.mostDensidad = mostDensidad;
    }

    /**
     * @return the mostRSolidos
     */
    public boolean getMostRSolidos() {
        return mostRSolidos;
    }

    /**
     * @param mostRSolidos the mostRSolidos to set
     */
    public void setMostRSolidos(boolean mostRSolidos) {
        this.mostRSolidos = mostRSolidos;
    }

    /**
     * @return the mostFisitu
     */
    public boolean getMostFisitu() {
        return mostFisitu;
    }

    /**
     * @param mostFisitu the mostFisitu to set
     */
    public void setMostFisitu(boolean mostFisitu) {
        this.mostFisitu = mostFisitu;
    }

    /**
     * @return the mostFislab
     */
    public boolean getMostFislab() {
        return mostFislab;
    }

    /**
     * @param mostFislab the mostFislab to set
     */
    public void setMostFislab(boolean mostFislab) {
        this.mostFislab = mostFislab;
    }

    /**
     * @return the mostMicrobio
     */
    public boolean getMostMicrobio() {
        return mostMicrobio;
    }

    /**
     * @param mostMicrobio the mostMicrobio to set
     */
    public void setMostMicrobio(boolean mostMicrobio) {
        this.mostMicrobio = mostMicrobio;
    }

}
