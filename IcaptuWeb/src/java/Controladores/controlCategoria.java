/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.Categoria;
import MPersistencia.MPCategoria;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author USUARIO
 */
public class controlCategoria implements Serializable{
    private boolean mostPanel=false;
    private int codigo;
    private String nombre;
    private String descripcion;
    private List<Categoria> listacategoria;

    /** Creates a new instance of controlCategoria */
    public controlCategoria() {
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

    public void registrarCategoria() throws SQLException, Exception{
        Categoria cat=new Categoria();
        cat.setCodigo(codigo);
        cat.setNombre(nombre);
        cat.setDescripcion(descripcion);
        MPCategoria.crearCategoria(cat);
        setListacategoria(MPCategoria.listarCategoria());
        setCodigo(MPCategoria.ultimaCategoria());
        setNombre("");
        setDescripcion("");
    }

    public void mostrarPanel() throws SQLException, Exception{
        setCodigo(MPCategoria.ultimaCategoria());
        setListacategoria(MPCategoria.listarCategoria());
        setMostPanel(true);

    }
    public void ocultarPanel(){
         setMostPanel(false);
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
     * @return the listacategoria
     */
    public List<Categoria> getListacategoria() {
        return listacategoria;
    }

    /**
     * @param listacategoria the listacategoria to set
     */
    public void setListacategoria(List<Categoria> listacategoria) {
        this.listacategoria = listacategoria;
    }

}
