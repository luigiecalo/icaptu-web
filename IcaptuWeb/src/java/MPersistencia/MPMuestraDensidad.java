/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.MuestraDensidad;
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
public class MPMuestraDensidad {
    public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;

    public static MuestraDensidad getMuestraDensidad(String codigo,String fecha,String horario) throws SQLException, Exception {
        mbd.conectar();
    con=mbd.getConexion();
        if (codigo == null) {
          throw new SQLException("No hay elemento clave de la clase MuestraDensidad");
     }
         ResultSet rs = null;
         PreparedStatement pst = null;
         MuestraDensidad muestradensidad = null;
     try {
         pst = con.prepareStatement("select * from MuestraDensidad where codAuxiliar = ? and fecha=? and horario=?");
         pst.setString(1, codigo.trim());
         rs = pst.executeQuery();
         while(rs.next()) {
             muestradensidad = MuestraDensidad.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return muestradensidad;
    }
}

     public static MuestraDensidad getMuestraDensidadC(int codigo) throws SQLException, Exception {
        mbd.conectar();
        con=mbd.getConexion();

         ResultSet rs = null;
         PreparedStatement pst = null;
         MuestraDensidad muestradensidad = null;
     try {
         pst = con.prepareStatement("select * from MuestraDensidad where codigo = ?");
         pst.setInt(1, codigo);
         rs = pst.executeQuery();
         while(rs.next()) {
             muestradensidad = MuestraDensidad.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return muestradensidad;
    }
}

public static void crearMuestraDensidad(MuestraDensidad muestradensidad) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (con == null ) {
          throw new SQLException(" no hay conexion ");
    }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into MuestraDensidad values(?,?,?,?,?,?,?,?)");
          pst.setString(1,muestradensidad.getHorario());
          pst.setInt(2,muestradensidad.getNumeroTuristas());
          pst.setInt(3,muestradensidad.getNumeroVendedores());
          pst.setInt(4,muestradensidad.getNumeroAutoridades());
          pst.setDouble(5,muestradensidad.getDensidadUsuarios());
          pst.setString(6,muestradensidad.getAuxiliar().getIdentificacion());
          pst.setString(7,muestradensidad.getFecha());
          pst.setInt(8,Integer.parseInt(muestradensidad.getPuntoMuestreo().getCodigo()));//          pst.setInt(8,);//
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}
public static boolean updateMuestraDensidad (MuestraDensidad muestradensidad, String codigo,String fecha,String horario) throws SQLException, Exception {
       mbd.conectar();
       con=mbd.getConexion();   
       if ( getMuestraDensidad(codigo,fecha,horario) == null) {
           throw new SQLException ("MuestraDensidad no registrado ");
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update muestradensidad set horario = ?, nturistas = ?, nvendedores = ?, nautoridades = ?, densidad = ?, codauxiliar = ?, fecha = ? where codAuxiliar = ? and fecha=? and horario=?");
          pst.setString(1,muestradensidad.getHorario());
          pst.setInt(2,muestradensidad.getNumeroTuristas());
          pst.setInt(3,muestradensidad.getNumeroVendedores());
          pst.setInt(4,muestradensidad.getNumeroAutoridades());
          pst.setDouble(5,muestradensidad.getDensidadUsuarios());
          pst.setString(6,muestradensidad.getAuxiliar().getIdentificacion());
          pst.setString(7,muestradensidad.getFecha());
          pst.setString(8 , codigo.trim());
          pst.setString(9 , fecha.trim());
          pst.setString(10 , horario.trim());
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

public static boolean deleteMuestraDensidad(String codigo,String fecha,String horario) throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    if (getMuestraDensidad(codigo,fecha,horario) == null) {
        throw new SQLException("No hay elemento clave de la clase MuestraDensidad");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from MuestraDensidad where codAuxiliar = ? and fecha=? and horario=?");
        pst.setString(1 , codigo.trim());
        pst.setString(2 , fecha.trim());
        pst.setString(3 , horario.trim());
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

public static List<MuestraDensidad> listarMuestraDensidad() throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestradensidad ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
    }

public static List<MuestraDensidad> listarMuestraDensidadPlaya(int cplaya) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select a.* from muestradensidad a,playa p,"
                + "puntomuestreo pm,auxiliar au "
                + "where p.codigo=pm.codPlaya and p.codigo="+cplaya
                + " and au.identificacion=a.codAuxiliar and a.codigo=pm.codigo");
        rs = pst.executeQuery();
        System.out.println(pst.toString());
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));

        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
    }

public static List<MuestraDensidad> listarMuestraDensidadPunto(String cpm) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select a.* from "
                + "muestradensidad a,puntomuestreo pm,auxiliar au "
                + "where  a.codigo=pm.codigo and pm.codigo='"+cpm+"' "
                + "and au.identificacion=a.codAuxiliar");
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
    }



public static List<MuestraDensidad> listarMuestraDensidadFecha(String fecha) throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestradensidad where fecha=?");
        pst.setString(1 , fecha.trim());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
  }

public static List<MuestraDensidad> listarMuestraDensidadAuxiliar(String codAuxiliar) throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestradensidad where codAuxiliar=?");
        pst.setString(1 , codAuxiliar.trim());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
    }

public static List<MuestraDensidad> listarMuestraDensidadFechaAuxiliar(String codAuxiliar,String fecha) throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraDensidad> listamuestradensidad = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestradensidad where codAuxiliar=? and fecha=?");
        pst.setString(1 , codAuxiliar.trim());
        pst.setString(2 , fecha.trim());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestradensidad.add(MuestraDensidad.load(rs));
        }
    } finally {
        if (rs != null) {
           rs.close();
       }
        if (pst != null) {
          pst.close();
       }
    }
    return listamuestradensidad;
    }




public static int ultimaMuestra() throws SQLException, Exception {
    mbd.conectar();
       con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     int ult=0;
     try {
        pst = con.prepareStatement("select max(codigo)+1 as ultima from muestradensidad");
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
