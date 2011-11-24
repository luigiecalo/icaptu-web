/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.Playa;
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
public class MPPlaya {
    public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;


    public static Playa getPlaya(int codigo) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
      
         ResultSet rs = null;
         PreparedStatement pst = null;
         Playa playa = null;
     try {
         pst = con.prepareStatement("select * from Playa where codigo = ?");
         pst.setInt(1, codigo);
         //System.out.println(pst.toString());
         rs = pst.executeQuery();
         while(rs.next()) {
             playa = Playa.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return playa;
    }
}

        public static boolean existePlaya(int codigo) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();

         ResultSet rs = null;
         PreparedStatement pst = null;
         boolean existe=false;
     try {
         pst = con.prepareStatement("select * from Playa where codigo = ?");
         pst.setInt(1, codigo);
         rs = pst.executeQuery();
         while(rs.next()) {
             existe = true;
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return existe;
    }
}

 public static void crearPlaya(Playa playa) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();
     if (con == null ) {
          throw new SQLException(" no hay conexion" );
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into Playa values(?,?,?,?,?,?,?,?)");
          pst.setInt(1,playa.getCodigo());
          pst.setString(2,playa.getNombre());
          pst.setString(3,playa.getUbicacion());
          pst.setString(4,playa.getDescripcion());
          pst.setDouble(5, playa.getLongitud());
          pst.setDouble(6, playa.getAnchoSeco());
          pst.setDouble(7, playa.getAnchoSumergido());
          pst.setDouble(8, playa.getArea());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}

 public static boolean updatePlaya (Playa playa, int codigo) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();
     if ( getPlaya(codigo) == null) {
           throw new SQLException ("Playa no registrado ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update playa set codigo = ?, nombre = ?, ubicacion = ?, descripcion = ?, longitud ?, anchoseco=?, anchosumergido=?, area=? where codigo = ?" ) ;
         pst.setInt(1 , playa.getCodigo());
         pst.setString(2 , playa.getNombre());
         pst.setString(3 , playa.getUbicacion());
         pst.setString(4 , playa.getDescripcion());
         pst.setDouble(5, playa.getLongitud());
         pst.setDouble(6, playa.getAnchoSeco());
         pst.setDouble(7, playa.getAnchoSumergido());
         pst.setDouble(8, playa.getArea());
         pst.setInt(9 , codigo);
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

 public static boolean deletePlaya(int codigo) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();
     if (getPlaya(codigo) == null) {
        throw new SQLException("No hay elemento clave de la clase Playa");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from Playa where codigo = ?");
        pst.setInt(1, codigo);
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

 public static List<Playa> listarPlaya() throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Playa> listaplaya = new LinkedList();
     try {
        pst = con.prepareStatement("select * from playa ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listaplaya.add(Playa.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listaplaya;
    }
}
