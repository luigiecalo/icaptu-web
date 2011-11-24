/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.PuntoMuestreo;
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
public class MPPuntoMuestreo {
    public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;

    public static PuntoMuestreo getPuntoMuestreo(String codigo) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
        if (codigo == null) {
          throw new SQLException("No hay elemento clave de la clase PuntoMuestreo");
     }
         ResultSet rs = null;
         PreparedStatement pst = null;
         PuntoMuestreo puntomuestreo = null;
     try {
         pst = con.prepareStatement("select * from PuntoMuestreo where codigo = ?");
         pst.setString(1, codigo.trim());
         System.out.println(pst.toString());
         rs = pst.executeQuery();
         while(rs.next()) {
             puntomuestreo = PuntoMuestreo.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return puntomuestreo;
    }
}
public static void crearPuntoMuestreo(PuntoMuestreo puntomuestreo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException( "no hay conexion" );
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into PuntoMuestreo values(?,?,?)");
          pst.setString(1,puntomuestreo.getCodigo());
          pst.setString(2,puntomuestreo.getDescripcion());
          pst.setInt(3, puntomuestreo.getPlaya().getCodigo());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}
public boolean updatePuntoMuestreo (PuntoMuestreo puntomuestreo, String codigo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();   
    if ( getPuntoMuestreo(codigo) == null) {
           throw new SQLException ("PuntoMuestreo no registrado");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update puntomuestreo set codigo = ?, descripcion = ?,codPlaya=? where codigo = ?" ) ;
         pst.setString(1 , puntomuestreo.getCodigo());
         pst.setString(2 , puntomuestreo.getDescripcion());
         pst.setInt(3, puntomuestreo.getPlaya().getCodigo());
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
public static boolean deletePuntoMuestreo(String codigo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (getPuntoMuestreo(codigo) == null) {
        throw new SQLException("No hay elemento clave de la clase PuntoMuestreo");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from PuntoMuestreo where codigo = ?");
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

public static List<PuntoMuestreo> listarPuntoMuestreo() throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<PuntoMuestreo> listapuntomuestreo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from puntomuestreo ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listapuntomuestreo.add(PuntoMuestreo.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listapuntomuestreo;
    }


public static List<PuntoMuestreo> listarPuntoMuestreoPlaya(int codpla) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<PuntoMuestreo> listapuntomuestreo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from puntomuestreo where codPlaya=?");
        pst.setInt(1, codpla);
        System.out.println(pst.toString());
        rs = pst.executeQuery();
        while(rs.next()) {
           listapuntomuestreo.add(PuntoMuestreo.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listapuntomuestreo;
    }


public static int ultimoPunto(int cplaya) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     int ult=0;
     try {
        pst = con.prepareStatement("select max(codigo)+1 as ultima from puntomuestreo where codPlaya=?");
        pst.setInt(1, cplaya);
        rs = pst.executeQuery();
        while(rs.next()) {
           ult=rs.getInt("ultima");
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return ult;
    }
}
