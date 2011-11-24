/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.Auxiliar;
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
public class MPAuxiliar {
    public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;

public static  Auxiliar getAuxiliar(String codigo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (codigo == null) {
          throw new SQLException("No hay elemento clave de la clase Auxiliar");
     }
         ResultSet rs = null;
         PreparedStatement pst = null;
         Auxiliar auxiliar = null;
     try {
         pst = con.prepareStatement("select * from Auxiliar where identificacion = ?");
         pst.setString(1, codigo.trim());
         
         rs = pst.executeQuery();
         
         //System.out.println(pst.toString());
         while(rs.next()) {
             auxiliar = Auxiliar.load(rs);
          
         }
        
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return auxiliar;
    }
}

public static  void crearAuxiliar(Auxiliar auxiliar) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException(" no hay conexion ");
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into Auxiliar values(?,?,?)");
          pst.setString(1,auxiliar.getIdentificacion());
          pst.setString(2,auxiliar.getNombre());
          pst.setString(3,auxiliar.getPerfil());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}
public static  boolean updateAuxiliar (Auxiliar auxiliar, String codigo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if ( getAuxiliar(codigo) == null) {
           throw new SQLException ("Auxiliar no registrado ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update auxiliar set idenificacion = ?, nombre = ?, perfil = ? where idenificacion = ? ");
         pst.setString(1 , auxiliar.getIdentificacion());
         pst.setString(2 , auxiliar.getNombre());
         pst.setString(3 , auxiliar.getPerfil());
         pst.setString(4 , codigo.trim());
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
public static  boolean deleteAuxiliar(String codigo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (getAuxiliar(codigo) == null) {
        throw new SQLException("No hay elemento clave de la clase Auxiliar");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from Auxiliar where identificacion = ?");
        pst.setString(1, codigo.trim());
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

public static  List<Auxiliar> listarAuxiliar() throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<Auxiliar> listaauxiliar = new LinkedList();
     try {
        pst = con.prepareStatement("select * from auxiliar ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listaauxiliar.add(Auxiliar.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listaauxiliar;
    }

}
