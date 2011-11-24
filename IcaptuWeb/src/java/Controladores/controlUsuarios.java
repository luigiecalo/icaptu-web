/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.Auxiliar;
import Conceptos.Usuario;
import MPersistencia.MPAuxiliar;
import MPersistencia.MPUsuario;
import java.io.Serializable;


/**
 *
 * @author USUARIO
 */
public class controlUsuarios implements Serializable{
    private String usuario;
    private String clave;
    private String identificacion;
    private Auxiliar auxiliar;

    /** Creates a new instance of controlUsuarios */
    public controlUsuarios() {
    }

     public int ValidarUsuario() throws Exception{
        int perf=0;

        Usuario u=MPUsuario.getUsuario(usuario, clave);
        if(Integer.parseInt(u.getPerfil())==1 || Integer.parseInt(u.getPerfil())==2){
        perf=Integer.parseInt(u.getPerfil());
        Auxiliar d=MPAuxiliar.getAuxiliar(u.getIdentificacion());
        System.out.println(d.getNombre()+" "+d.getPerfil());
        setAuxiliar(d);
        controlPlayas.listaPlayas();
        controlMuestraResiduo.ideAuxiliar=d.getIdentificacion();
        controlMuestraDensidad.ideAuxiliar=d.getIdentificacion();
       
        }
        return perf;
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

}
