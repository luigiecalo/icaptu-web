/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.MuestraDensidad;
import MPersistencia.MPAuxiliar;
import MPersistencia.MPMuestraDensidad;
import MPersistencia.MPMuestraResiduo;
import MPersistencia.MPPuntoMuestreo;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;


/**
 *
 * @author USUARIO
 */
public class controlMuestraDensidad implements Serializable{
    private Date fecha = new Date();
    private String _horario="1:00";
    private int _nTuristas;
    private int _nVendedores;
    private int _nAutoridades;
    private double _densidadUsuarios;
    private String _auxiliar;
    public static int codigo;
    public static String ideAuxiliar;
    private boolean showMc;
    private static List<MuestraDensidad> listamuestras;
    /** Creates a new instance of controlMuestraDensidad */
    public controlMuestraDensidad() {
    }

    public void registrarMuestra(ActionEvent ev) throws SQLException, Exception{
        //Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       // String codPm=map.get("codigoPm").toString();
       // String codAux=map.get("codAux").toString();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
        //System.out.println("punto de muestreo escogido"+codigo+"   "+fech);
        
        MuestraDensidad md=new MuestraDensidad();
        md.setPuntoMuestreo(MPPuntoMuestreo.getPuntoMuestreo(""+codigo));
        md.setFecha(fech);
        md.setHorario(_horario);
        md.setNumeroAutoridades(_nAutoridades);
        md.setNumeroTuristas(_nTuristas);
        md.setNumeroVendedores(_nVendedores);
        md.setAuxiliar(MPAuxiliar.getAuxiliar(ideAuxiliar));
        MPMuestraDensidad.crearMuestraDensidad(md);
        setShowMc(true);
        ListarMuestrasFecha();
        
        
    }
    
    public static void ListarMuestrasPlaya(int codigo) throws SQLException, Exception{
        setListamuestras(MPMuestraDensidad.listarMuestraDensidadPlaya(codigo));
    }

    public static void ListarMuestrasPuntoMuestreo(String codPm) throws SQLException, Exception{
       //Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //String codPm=map.get("codigoPm").toString();
        setListamuestras(MPMuestraDensidad.listarMuestraDensidadPunto(codPm));
    }


    public void ListarMuestrasFecha() throws SQLException, Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
        setListamuestras(MPMuestraDensidad.listarMuestraDensidadFecha(fech));
    }
    public void ListarMuestrasFecha1(ValueChangeEvent ev) throws SQLException, Exception{
        //if(ev.getNewValue() != null){
        //System.out.println("Fecha capturada "+fecha);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
        setListamuestras(MPMuestraDensidad.listarMuestraDensidadFechaAuxiliar(ideAuxiliar,fech));
        controlMuestraResiduo.setListaMuestras(MPMuestraResiduo.listarMuestraResiduoFA(fech, ideAuxiliar));
        //}
    }
     public void visualizar(ActionEvent ev){
            setShowMc(true);
            //
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the _horario
     */
    public String getHorario() {
        return _horario;
    }

    /**
     * @param horario the _horario to set
     */
    public void setHorario(String horario) {
        this._horario = horario;
    }

    /**
     * @return the _nTuristas
     */
    public int getnTuristas() {
        return _nTuristas;
    }

    /**
     * @param nTuristas the _nTuristas to set
     */
    public void setnTuristas(int nTuristas) {
        this._nTuristas = nTuristas;
    }

    /**
     * @return the _nVendedores
     */
    public int getnVendedores() {
        return _nVendedores;
    }

    /**
     * @param nVendedores the _nVendedores to set
     */
    public void setnVendedores(int nVendedores) {
        this._nVendedores = nVendedores;
    }

    /**
     * @return the _nAutoridades
     */
    public int getnAutoridades() {
        return _nAutoridades;
    }

    /**
     * @param nAutoridades the _nAutoridades to set
     */
    public void setnAutoridades(int nAutoridades) {
        this._nAutoridades = nAutoridades;
    }

    /**
     * @return the _densidadUsuarios
     */
    public double getDensidadUsuarios() {
        return _densidadUsuarios;
    }

    /**
     * @param densidadUsuarios the _densidadUsuarios to set
     */
    public void setDensidadUsuarios(double densidadUsuarios) {
        this._densidadUsuarios = densidadUsuarios;
    }

 
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int cod) {
        codigo = cod;
    }

    /**
     * @return the showMc
     */
    public boolean isShowMc() {
        return showMc;
    }

    /**
     * @param showMc the showMc to set
     */
    public void setShowMc(boolean showMc) {
        this.showMc = showMc;
    }

    /**
     * @return the _auxiliar
     */
    public String getAuxiliar() {
        return _auxiliar;
    }

    /**
     * @param auxiliar the _auxiliar to set
     */
    public void setAuxiliar(String auxiliar) {
        this._auxiliar = auxiliar;
    }

    /**
     * @return the listamuestras
     */
    public List<MuestraDensidad> getListamuestras() {
        return listamuestras;
    }

    /**
     * @param listamuestras the listamuestras to set
     */
    public static void setListamuestras(List<MuestraDensidad> listam) {
        listamuestras = listam;
    }

}
