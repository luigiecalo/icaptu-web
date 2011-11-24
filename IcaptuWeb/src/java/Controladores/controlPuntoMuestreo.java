/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.PuntoMuestreo;
import MPersistencia.MPMuestraDensidad;
import MPersistencia.MPPlaya;
import MPersistencia.MPPuntoMuestreo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 *
 * @author USUARIO
 */
public class controlPuntoMuestreo implements Serializable{
    private List<PuntoMuestreo> puntosMuestreo;
    private boolean show=false;
    private boolean mostPanel=false;
    private String codigo;
    private String descripcion;
    private int cplaya;

    public void registrarPunto() throws SQLException, Exception{
        PuntoMuestreo pm=new PuntoMuestreo();
        pm.setPlaya(MPPlaya.getPlaya(cplaya));
        pm.setCodigo(codigo);
        pm.setDescripcion(descripcion);
        MPPuntoMuestreo.crearPuntoMuestreo(pm);
    }

    public void asignarPlaya() throws SQLException, Exception{
        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         String codPlaya=map.get("codplaya").toString();
         setCplaya(Integer.parseInt(codPlaya));
         setCodigo(""+MPPuntoMuestreo.ultimoPunto(getCplaya()));
    }
    /**
     * @return the puntosMuestreo
     */
    public List<PuntoMuestreo> getPuntosMuestreo() {
        return puntosMuestreo;
    }

    /**
     * @param aPuntosMuestreo the puntosMuestreo to set
     */
    public void setPuntosMuestreo(List<PuntoMuestreo> aPuntosMuestreo) {
        puntosMuestreo = aPuntosMuestreo;
    }
    /** Creates a new instance of controlPuntoMuestreo */
    public controlPuntoMuestreo() {
       // puntosMuestreo=
    }

    public void ListarPuntosdePlaya() throws SQLException, Exception{
       //agregar listar punto muestreo por playa
         Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
         String codPlaya=map.get("codplaya").toString();
         //System.out.println("voy a listar "+codPlaya);
         controlMuestraDensidad.ListarMuestrasPlaya(Integer.parseInt(codPlaya));
         controlMuestraResiduo.listarMuestrasPorPlaya(Integer.parseInt(codPlaya));
         setPuntosMuestreo(MPPuntoMuestreo.listarPuntoMuestreoPlaya(Integer.parseInt(codPlaya)));
         
         // map.put("codplaya","0");
    }




    public void visualizar(ActionEvent ev) throws SQLException, Exception{
            setShow(true);
            Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            String codPm=map.get("codigoPm").toString();
            controlMuestraDensidad.ListarMuestrasPuntoMuestreo(codPm);
            controlMuestraDensidad.codigo=Integer.parseInt(codPm);
            controlMuestraResiduo.codpunto=codPm;
            
            //System.out.println("punto de muestreo escogido"+codPm);
    }
    public void ocultar(ActionEvent ev){
        setShow(false);
       
    }
    
    public void visualizarPanel() throws SQLException, Exception{
            controlPlayas.listaPlayas();
            setMostPanel(true);
            //System.out.println("punto de muestreo escogido"+codPm);
    }
    public void ocultarPanel(){
        setMostPanel(false);
       
    }
    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    /**
     * @return the mostPanel
     */
    public boolean getMostPanel() {
        return mostPanel;
    }

    /**
     * @param mostPanel the mostPanel to set
     */
    public void setMostPanel(boolean mostPanel) {
        this.mostPanel = mostPanel;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cplaya
     */
    public int getCplaya() {
        return cplaya;
    }

    /**
     * @param cplaya the cplaya to set
     */
    public void setCplaya(int cplaya) {
        this.cplaya = cplaya;
    }
    /**
     * @return the visualizar
     */


}
