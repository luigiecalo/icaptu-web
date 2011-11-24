/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.Categoria;
import Conceptos.Residuo;
import MPersistencia.MPCategoria;
import MPersistencia.MPResiduo;
import com.icesoft.faces.component.ext.ClickActionEvent;
import com.icesoft.faces.component.ext.RowSelectorEvent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;



/**
 *
 * @author USUARIO
 */
public class controlResiduo implements Serializable{
private boolean mostPanel;
private int codigo;
private String codigoc;
private String nombrec;
    private String nombre;
    private String descripcion;
    private Categoria categoria;

    private List<Residuo> listaresiduo;
    //private List<SelectItem> posiblesCategorias;
    private List<Categoria> categorias;
    private boolean mostTabc=false;
    private boolean mostEti=false;

    /** Creates a new instance of controlResiduo */
    public controlResiduo() {
    }

    public void mostrarPanel() throws SQLException, Exception{
        setListaresiduo(MPResiduo.listarResiduo());
        setMostPanel(true);

    }
    public void listarResiduos() throws SQLException, Exception{
        setListaresiduo(MPResiduo.listarResiduo());
    }
    public void buscarcategorias(ActionEvent ev) throws InterruptedException, SQLException, Exception{
        if (nombrec != null) {
             setCategorias(MPCategoria.listarCategoriaN(nombrec));
             setMostTabc(true);
             setMostEti(false);
         }else{
             setMostTabc(false);
         }
    }
    public void RegistrarResiduo() throws SQLException, Exception{
        Residuo r=new Residuo();
        r.setCategoria(categoria);
        r.setCodigo(codigo);
        r.setNombre(nombre);
        r.setDescripcion(descripcion);
        MPResiduo.crearResiduo(r);
        setListaresiduo(MPResiduo.listarResiduo());
    }

    public void asignarCategoria(ClickActionEvent event) throws SQLException, Exception{

    /*  FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String codcat1= (String) map.get("codcat");
        System.out.println("fila seleccionada: "+event.getRow()+"  "+codcat1);
*/
        setCategoria(MPCategoria.getCategoria(event.getRow()+1));
        setMostEti(true);
        setMostTabc(false);


    }

    public void ocultarPanel(){
        setMostPanel(false);
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
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the listacategoria
     */
    public List<Residuo> getListaresiduo() {
        return listaresiduo;
    }

    /**
     * @param listacategoria the listacategoria to set
     */
    public void setListaresiduo(List<Residuo> listare) {
        this.listaresiduo = listare;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the nombrec
     */
    public String getNombrec() {
        return nombrec;
    }

    /**
     * @param nombrec the nombrec to set
     */
    public void setNombrec(String nombrec) {
        this.nombrec = nombrec;
    }

    /**
     * @return the mostTabc
     */
    public boolean getMostTabc() {
        return mostTabc;
    }

    /**
     * @param mostTabc the mostTabc to set
     */
    public void setMostTabc(boolean mostTabc) {
        this.mostTabc = mostTabc;
    }

    /**
     * @return the codigoc
     */
    public String getCodigoc() {
        return codigoc;
    }

    /**
     * @param codigoc the codigoc to set
     */
    public void setCodigoc(String codgoc) {
        this.codigoc = codgoc;
    }

    /**
     * @return the mostEti
     */
    public boolean getMostEti() {
        return mostEti;
    }

    /**
     * @param mostEti the mostEti to set
     */
    public void setMostEti(boolean mostEti) {
        this.mostEti = mostEti;
    }




}
