/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.Residuo;
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
public class MPResiduo {
   public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;


    public static Residuo getResiduo(int codigo) throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();

         ResultSet rs = null;
         PreparedStatement pst = null;
         Residuo Residuo = null;
     try {
         pst = con.prepareStatement("select * from Residuo where codigo = ?");
         pst.setInt(1, codigo);
         //System.out.println(pst.toString());
         rs = pst.executeQuery();
         while(rs.next()) {
             Residuo = Residuo.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return Residuo;
    }
}
    public static Residuo getResiduoN(String nombre) throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();

         ResultSet rs = null;
         PreparedStatement pst = null;
         Residuo Residuo = null;
     try {
         pst = con.prepareStatement("select * from Residuo where nombre = ?");
         pst.setString(1, nombre);
         rs = pst.executeQuery();
         while(rs.next()) {
             Residuo = Residuo.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return Residuo;
    }
}
public static void crearResiduo(Residuo residuo) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException(" no hay conexion" );
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into Residuo values(?,?,?,?)");
          pst.setString(1,residuo.getNombre());
          pst.setInt(2,residuo.getCodigo());
          pst.setString(3,residuo.getDescripcion());
          pst.setInt(4, residuo.getCategoria().getCodigo());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}
  public static boolean updateResiduo (Residuo residuo, int cod) throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();
      if ( getResiduo(cod) == null) {
           throw new SQLException ("Residuo no registrada ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update Residuo set nombre = ?, codigo = ?, descripcion = ?,codCategoria=? where codigo = ? ");
         pst.setString(1,residuo.getNombre());
         pst.setInt(2,residuo.getCodigo());
         pst.setString(3,residuo.getDescripcion());
         pst.setInt(4, residuo.getCategoria().getCodigo());
         pst.setInt(5 , cod);
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
 public static boolean deleteResiduo(int codigo) throws SQLException, Exception {
     mbd.conectar();
      con=mbd.getConexion();
     if (getResiduo(codigo) == null) {
        throw new SQLException("No hay elemento clave de la clase Residuo");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from Residuo where codigo = ?");
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

 public static List<Residuo> listarResiduo() throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Residuo> listaResiduo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from Residuo");
        rs = pst.executeQuery();
        while(rs.next()) {
           listaResiduo.add(Residuo.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listaResiduo;
    }
public static int ultimoResiduo(int codCategoria) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     int ult=0;
     try {
        pst = con.prepareStatement("select max(codigo)+1 as ultima from residuo where codCategoria=?");
        pst.setInt(1, codCategoria);
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

public static List<Residuo> listarResiduoFaltantes(String fecha,String horario) throws SQLException, Exception {
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Residuo> listamuestraresiduo = new LinkedList();
     try {
        pst = con.prepareStatement("select r.* from residuo r "
                + "where r.codigo not in(select mr.residuo from muestraresiduo mr "
                + "where mr.fecha = ? and mr.horario = ?)");
        pst.setString(1, fecha);
        pst.setString(2, horario);
        System.out.println(pst.toString());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestraresiduo.add(Residuo.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestraresiduo;
    }
}
