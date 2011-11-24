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
public class Usuario implements Serializable {
    private String usuario;
    private String clave;
    private String identificacion;
    private String perfil;

    public static Usuario load(ResultSet rs) throws SQLException {
       Usuario usu= new Usuario();
       usu.setUsuario(rs.getString(1));
       usu.setClave(rs.getString(2));
       usu.setIdentificacion(rs.getString(3));
       usu.setPerfil(rs.getString(4));
       return usu;
    }
    
    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * @param identificacion the identificacion to set
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}
