/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Conceptos;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */

public class Categoria implements Serializable {

private String nombre;
private int codigo;
private String Descripcion;

public static Categoria load(ResultSet rs) throws SQLException {
   Categoria cat= new Categoria();
   cat.setNombre(rs.getString(1));
   cat.setCodigo(rs.getInt(2));
   cat.setDescripcion(rs.getString(3));
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

}