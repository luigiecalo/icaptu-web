/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.Categoria;
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
public class MPCategoria {
   public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;


    public static Categoria getCategoria(int codigo) throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();

         ResultSet rs = null;
         PreparedStatement pst = null;
         Categoria categoria = null;
     try {
         pst = con.prepareStatement("select * from Categoria where codigo = ?");
         pst.setInt(1, codigo);
         rs = pst.executeQuery();
         while(rs.next()) {
             categoria = Categoria.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return categoria;
    }
}

public static void crearCategoria(Categoria categoria) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException(" no hay conexion" );
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into Categoria values(?,?,?)");
          pst.setString(1,categoria.getNombre());
          pst.setInt(2,categoria.getCodigo());
          pst.setString(3,categoria.getDescripcion());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}
  public static boolean updateCategoria (Categoria categoria, int cod) throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();
      if ( getCategoria(cod) == null) {
           throw new SQLException ("Categoria no registrada ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update categoria set nombre = ?, codigo = ?, descripcion = ? where codigo = ? ");
         pst.setString(1 , categoria.getNombre());
         pst.setInt(2 , categoria.getCodigo());
         pst.setString(3 , categoria.getDescripcion());
         pst.setInt(4 , cod);
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
 public static boolean deleteCategoria(int codigo) throws SQLException, Exception {
     mbd.conectar();
      con=mbd.getConexion();
     if (getCategoria(codigo) == null) {
        throw new SQLException("No hay elemento clave de la clase Categoria");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from Categoria where codigo = ?");
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

 public static List<Categoria> listarCategoria() throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Categoria> listacategoria = new LinkedList();
     try {
        pst = con.prepareStatement("select * from categoria ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listacategoria.add(Categoria.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listacategoria;
    }

 public static List<Categoria> listarCategoriaN(String n) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<Categoria> listacategoria = new LinkedList();
     try {
        pst = con.prepareStatement("select * from categoria where nombre like '%"+n+"%'");
        rs = pst.executeQuery();
        System.out.println(pst.toString());
        while(rs.next()) {
           listacategoria.add(Categoria.load(rs));
           System.out.println(rs.getString(1));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listacategoria;
    }

  public static int ultimaCategoria() throws SQLException, Exception {
      mbd.conectar();
      con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     int ultima=0;
     try {
        pst = con.prepareStatement("select max(codigo)+1 as ultimo from categoria");
        rs = pst.executeQuery();
        while(rs.next()) {
           ultima=rs.getInt("ultimo");
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return ultima;
    }

}
