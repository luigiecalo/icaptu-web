/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Conceptos;

import MPersistencia.MPAuxiliar;
import MPersistencia.MPMuestraResiduo;
import MPersistencia.MPPuntoMuestreo;
import MPersistencia.MPResiduo;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class MuestraResiduo implements Serializable{
private boolean estActivo;
private int zonaReposo;
private int zonaActiva;
private int zonaBañistas;
private Residuo residuo;
private Auxiliar auxiliar;
private String horario;
private String fecha;
private boolean estModificado;
private PuntoMuestreo puntoMuestreo;

public MuestraResiduo(){

}

public MuestraResiduo(String fec,String hor,int codr,boolean esta,int zrep,int zonaact,int zonaba) throws SQLException, Exception{
    this.setFecha(fec);
    this.setHorario(hor);
    this.setResiduo(MPResiduo.getResiduo(codr));
    this.setEstActivo(esta);
    this.setZonaReposo(zrep);
    this.setZonaActiva(zonaact);
    this.setZonaBañistas(zonaba);
    this.setEstModificado(false);

}

public static MuestraResiduo load(ResultSet rs) throws SQLException, Exception {
   MuestraResiduo mue= new MuestraResiduo();
   mue.setEstActivo(rs.getBoolean(1));
   mue.setZonaReposo(rs.getInt(2));
   mue.setZonaActiva(rs.getInt(3));
   mue.setZonaBañistas(rs.getInt(4));
   mue.setResiduo(MPResiduo.getResiduo(rs.getInt(5)));
   mue.setAuxiliar(MPAuxiliar.getAuxiliar(rs.getString(6)));
   mue.setHorario(rs.getString(7));
   mue.setFecha(rs.getString(8));
   mue.setEstModificado(rs.getBoolean(9));
   mue.setPuntoMuestreo(MPPuntoMuestreo.getPuntoMuestreo(rs.getString(10)));
   //System.out.println(mue.getFecha()+" "+mue.getHorario()+" "+mue.getAuxiliar().getNombre()+" "+mue.getResiduo().getNombre());
   return mue;
}



    /**
     * @return the estActivo
     */
    public boolean getEstActivo() {
        return estActivo;
    }

    /**
     * @param estActivo the estActivo to set
     */
    public void setEstActivo(boolean estActivo) {
        this.estActivo = estActivo;
    }

    /**
     * @return the zonaReposo
     */
    public int getZonaReposo() {
        return zonaReposo;
    }

    /**
     * @param zonaReposo the zonaReposo to set
     */
    public void setZonaReposo(int zonaReposo) {
        this.zonaReposo = zonaReposo;
    }

    /**
     * @return the zonaActiva
     */
    public int getZonaActiva() {
        return zonaActiva;
    }

    /**
     * @param zonaActiva the zonaActiva to set
     */
    public void setZonaActiva(int zonaActiva) {
        this.zonaActiva = zonaActiva;
    }

    /**
     * @return the zonaBañistas
     */
    public int getZonaBañistas() {
        return zonaBañistas;
    }

    /**
     * @param zonaBañistas the zonaBañistas to set
     */
    public void setZonaBañistas(int zonaBañistas) {
        this.zonaBañistas = zonaBañistas;
    }

    /**
     * @return the residuo
     */
    public Residuo getResiduo() {
        return residuo;
    }

    /**
     * @param residuo the residuo to set
     */
    public void setResiduo(Residuo residuo) {
        this.residuo = residuo;
    }

    /**
     * @return the auxiliar
     */
    public Auxiliar getAuxiliar() {
        return auxiliar;
    }

    /**
     * @param auxiliar the auxiliar to set
     */
    public void setAuxiliar(Auxiliar auxiliar) {
        this.auxiliar = auxiliar;
    }

    /**
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the estModificado
     */
    public boolean getEstModificado() {
        return estModificado;
    }

    /**
     * @param estModificado the estModificado to set
     */
    public void setEstModificado(boolean estModificado) {
        this.estModificado = estModificado;
    }

    /**
     * @return the puntoMuestreo
     */
    public PuntoMuestreo getPuntoMuestreo() {
        return puntoMuestreo;
    }

    /**
     * @param puntoMuestreo the puntoMuestreo to set
     */
    public void setPuntoMuestreo(PuntoMuestreo puntoMuestreo) {
        this.puntoMuestreo = puntoMuestreo;
    }
}
