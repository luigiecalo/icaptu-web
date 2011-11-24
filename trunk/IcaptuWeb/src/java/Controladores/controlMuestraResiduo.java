

package Controladores;

import Conceptos.Categoria;
import Conceptos.MuestraResiduo;
import Conceptos.Residuo;
import MPersistencia.MPAuxiliar;
import MPersistencia.MPMuestraResiduo;
import MPersistencia.MPPuntoMuestreo;
import MPersistencia.MPResiduo;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


/**
 *
 * @author USUARIO
 */
public class controlMuestraResiduo implements Serializable{

 
    private static Date fecha = new Date();
    public static String ideAuxiliar;
    private List<Residuo> listaResiduos=new LinkedList();
    private static List<SelectItem> ItemsResiduos;
    private Categoria categoria=new Categoria();
    private int zonaActiva;
    private int zonaBañistas;
    private int zonaReposo;
    private String codRes;
    private boolean mostTmues=false;
    private static List<MuestraResiduo> listaMuestras;
    public static String codpunto;
    private static String horario="1:00";
    private boolean activarMuest=false;
    private boolean activarEtiquM=true;

    public String getCodpunto() {
        return codpunto;
    }

    public void setCodpunto(String codp) {
        codpunto = codp;
    }
    public List<MuestraResiduo> getListaMuestras() {
        return listaMuestras;
    }

    public static void setListaMuestras(List<MuestraResiduo> listaM) {
        listaMuestras = listaM;
    }
    
   // private HtmlDataTable tabla;
    //FacesContext fc;
    /** Creates a new instance of controlMuestraResiduo */
    public controlMuestraResiduo() {
        try {
            setListaResiduos(MPResiduo.listarResiduo());
            setListaMuestras(MPMuestraResiduo.listarMuestraResiduo());
        } catch (SQLException ex) {
            Logger.getLogger(controlMuestraResiduo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(controlMuestraResiduo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void mostrarTmuestra(){
        setMostTmues(true);
    }
    public void ocultarTmuestra(){
        setMostTmues(false);
    }
    public void SeleccionarRes(ValueChangeEvent event){
        System.out.println(event.getNewValue()+"  ");
        if(event.getNewValue()!= null){
            try {
                //setCodRes();
                setCategoria(MPResiduo.getResiduo(Integer.parseInt(event.getNewValue().toString())).getCategoria());
                //  System.out.println(getCategoria().getNombre());
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public void modificarMuestra(){
        setActivarMuest(false);
        setActivarEtiquM(true);
    }
    public void activarModificado(){
        setActivarMuest(true);
        setActivarEtiquM(false);
    }
    /*public boolean Registrado(MuestraResiduo mu){
        boolean reg=false;
        if(mu.getFecha().equals(fecha.toString()) && mu.getHorario().equals(horario)){
            System.out.println("ya ha sido registrado");
            reg=true;
        }
        return reg;
    }*/

    public static void obtenerListaR() throws SQLException, Exception{
      //  System.out.println("que paso");
        ItemsResiduos=new ArrayList<SelectItem>();
        //
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
        //setListaResiduos();
       // System.out.println(fech+"  "+horario);
         Iterator it=MPResiduo.listarResiduoFaltantes(fech, horario).iterator();
         while(it.hasNext()){
             Residuo r=(Residuo)it.next();
             ItemsResiduos.add(new SelectItem(r.getCodigo(), r.getNombre()));
         }

    }

    public void listarResiduos(ActionEvent ev) throws SQLException, Exception{
        
        obtenerListaR();

        mostrarTmuestra();
      
        
    }
    public static void optenerMuestras() throws SQLException, Exception{
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
        setListaMuestras(MPMuestraResiduo.listarMuestraResiduoF(fech, horario));
    }



    public void listarMuestrasP(ValueChangeEvent event){
        if(event.getNewValue()!= null){
            try {
                optenerMuestras();
                obtenerListaR();
            } catch (SQLException ex) {
                 System.out.println(ex.toString());
            } catch (Exception ex) {
                 System.out.println(ex.toString());
            }
        }

    }

    public static void listarMuestrasPorPlaya(int codPlaya){
        try {
            setListaMuestras(MPMuestraResiduo.listarMuestraResiduoPlaya(codPlaya));
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void listarMuestras(ActionEvent ev) throws SQLException, Exception{
        optenerMuestras();
        obtenerListaR();
    }
   /* public void actualizartabresiduo(String fec,String hor) throws SQLException, Exception{
        Iterator it=MPMuestraResiduo.listarMuestraResiduoF(fec, hor).iterator();
        while(it.hasNext()){
            MuestraResiduo mr=(MuestraResiduo)it.next();
            int ind=listaResiduos.indexOf(mr.getResiduo());
            System.out.println(ind+"  "+mr.getResiduo().getNombre()+" "+listaResiduos.get(ind).getCodigo());
            listaResiduos.get(ind).setRegistrado(true);

        }
    }*/

    public void registrarMuestra(ActionEvent ev){
       // System.out.println("registrare");
        MuestraResiduo mu=new MuestraResiduo();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fech= formato.format(fecha);
       
        //String ide=map.get("codAux").toString();
        mu.setEstActivo(true);
        mu.setEstModificado(true);
        mu.setFecha(fech);
        mu.setHorario(horario);
        try {
        mu.setAuxiliar(MPAuxiliar.getAuxiliar(ideAuxiliar));
        mu.setResiduo(MPResiduo.getResiduo(Integer.parseInt(getCodRes())));
        mu.setZonaActiva(getZonaActiva());
        mu.setZonaBañistas(getZonaBañistas());
        mu.setZonaReposo(getZonaReposo());
        mu.setPuntoMuestreo(MPPuntoMuestreo.getPuntoMuestreo(codpunto));
        System.out.println(mu.getAuxiliar().getIdentificacion()+"   "+mu.getZonaActiva()+"  "+mu.getZonaBañistas()+"  "+mu.getFecha()+"   "+mu.getHorario());
        //setMuestraR(mu);
        MPMuestraResiduo.crearMuestraResiduo(mu);
        obtenerListaR();
        optenerMuestras();
         } catch (SQLException ex) {
             System.out.println(ex.toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());

        }
        //actualizartabresiduo(fech,hor);
    }

   /**
     * @return the ideAuxiliar
     */
    public static String getIdeAuxiliar() {
        return ideAuxiliar;
    }

    /**
     * @param aIdeAuxiliar the ideAuxiliar to set
     */
    public static void setIdeAuxiliar(String aIdeAuxiliar) {
        ideAuxiliar = aIdeAuxiliar;
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
     * @return the listarResiduos
     */
    public List<Residuo> getListaResiduos() {
        return listaResiduos;
    }

    /**
     * @param listarResiduos the listarResiduos to set
     */
    public void setListaResiduos(List<Residuo> listarResiduos) {
        this.listaResiduos = listarResiduos;
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
     * @return the ItemsResiduos
     */
    public List<SelectItem> getItemsResiduos() {
        return ItemsResiduos;
    }

    /**
     * @param ItemsResiduos the ItemsResiduos to set
     */
    public void setItemsResiduos(List<SelectItem> ItemsResiduos) {
        this.ItemsResiduos = ItemsResiduos;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
     * @return the codRes
     */
    public String getCodRes() {
        return codRes;
    }

    /**
     * @param codRes the codRes to set
     */
    public void setCodRes(String codRes) {
        this.codRes = codRes;
    }

    /**
     * @return the mostTmues
     */
    public boolean getMostTmues() {
        return mostTmues;
    }

    /**
     * @param mostTmues the mostTmues to set
     */
    public void setMostTmues(boolean mostTmues) {
        this.mostTmues = mostTmues;
    }


    /**
     * @return the activarMuest
     */
    public boolean getActivarMuest() {
        return activarMuest;
    }

    /**
     * @param activarMuest the activarMuest to set
     */
    public void setActivarMuest(boolean activarMuest) {
        this.activarMuest = activarMuest;
    }

    /**
     * @return the activarEtiquM
     */
    public boolean getActivarEtiquM() {
        return activarEtiquM;
    }

    /**
     * @param activarEtiquM the activarEtiquM to set
     */
    public void setActivarEtiquM(boolean activarEtiquM) {
        this.activarEtiquM = activarEtiquM;
    }



   

  

}
