/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Conceptos;

import MPersistencia.MPCategoria;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */

public class Residuo implements Serializable {

private String nombre;
private int codigo;
private String Descripcion;
private Categoria categoria=new Categoria();
private MuestraResiduo muestraR=new MuestraResiduo();
private boolean registrado=false;


public static Residuo load(ResultSet rs) throws SQLException, Exception {
   Residuo cat= new Residuo();
   cat.setNombre(rs.getString(1));
   cat.setCodigo(rs.getInt(2));
   cat.setDescripcion(rs.getString(3));
   cat.setCategoria(MPCategoria.getCategoria(rs.getInt(4)));
   return cat;
}

public void setNombre(String nom){
   this.nombre=nom;
}
public String getNombre(){
   return nombre;
}

public void setCodigo(int cod){
   this.codigo=cod;
}
public int getCodigo(){
   return codigo;
}

public void setDescripcion(String Des){
   this.Descripcion=Des;
}
public String getDescripcion(){
   return Descripcion;
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
     * @return the muestraR
     */
    public MuestraResiduo getMuestraR() {
        return muestraR;
    }

    /**
     * @param muestraR the muestraR to set
     */
    public void setMuestraR(MuestraResiduo muestraR) {
        this.muestraR = muestraR;
    }

    /**
     * @return the registrado
     */
    public boolean getRegistrado() {
        return registrado;
    }

    /**
     * @param registrado the registrado to set
     */
    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }



}