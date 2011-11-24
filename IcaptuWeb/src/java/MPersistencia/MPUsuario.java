/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.Usuario;
import Utilidades.ManejadorBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class MPUsuario {
  public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;

    public static Usuario getUsuario(String usu,String clave) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
        if (clave == null) {
          throw new SQLException("No hay elemento clave de la clase Usuario");
        }
         ResultSet rs = null;
         PreparedStatement pst = null;
         Usuario usuario = null;
     try {
         pst = con.prepareStatement("select * from Usuario where usuario = ? and clave=?");
         pst.setString(1, usu.trim());
         pst.setString(2, clave.trim());
         rs = pst.executeQuery();
         while(rs.next()) {
             usuario = Usuario.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return usuario;
    }
}

public static void crearUsuario(Usuario usuario) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException(" no hay conexion ");
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into Usuario values(?,?,?,?)");
          pst.setString(1,usuario.getUsuario());
          pst.setString(2,usuario.getClave());
          pst.setString(3,usuario.getIdentificacion());
          pst.setString(4,usuario.getPerfil());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}

public static boolean updateUsuario (Usuario usuario, String usu,String clave) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if ( getUsuario(usu,clave) == null) {
           throw new SQLException ("Usuario no registrado ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update usuario set usuario = ?, clave = ?, identificacion = ?, perfil = ? where usuario = ? and clave=?" ) ;
         pst.setString(1 , usuario.getUsuario());
         pst.setString(2 , usuario.getClave());
         pst.setString(3 , usuario.getIdentificacion());
         pst.setString(4 , usuario.getPerfil());
         pst.setString(5 , usu.trim());
         pst.setString(5 , clave.trim());
         int r = pst.executeUpdate();
       if (r != 0) {
          res = true;
       }
       } finally {
          if (pst != null) {
            pst.close();
          }
       }
         return res;
 }

public static boolean deleteUsuario(String usu,String clave) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (getUsuario(usu,clave) == null) {
        throw new SQLException("No hay elemento clave de la clase Usuario");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from Usuario where usuario = ? and clave=?");
        pst.setString(1, usu.trim());
        pst.setString(2, clave.trim());
        int r = pst.executeUpdate();
        if (r != 0) {
           res = true;
        }
     } finally {
        if (pst != null) {
           pst.close();
        }
     }
     return res;
}

public static List<Usuario> listarUsuario() throws SQLException {
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Usuario> listausuario = new LinkedList();
     try {
        pst = con.prepareStatement("select * from usuario ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listausuario.add(Usuario.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listausuario;
    }

}
