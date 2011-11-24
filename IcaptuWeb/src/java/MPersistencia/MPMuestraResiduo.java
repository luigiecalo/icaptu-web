/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MPersistencia;

import Conceptos.MuestraResiduo;
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
public class MPMuestraResiduo {
    public static ManejadorBaseDatos mbd=ManejadorBaseDatos.getInstancia();
    public static Connection con;
    
    public static MuestraResiduo getMuestraResiduo(String fecha,int codigor,String horario,String aux) throws SQLException, Exception {
     mbd.conectar();
    con=mbd.getConexion();
     
         ResultSet rs = null;
         PreparedStatement pst = null;
         MuestraResiduo muestraresiduo = null;
     try {
         pst = con.prepareStatement("select * from MuestraResiduo where fecha = ? and residuo=? and horario=? and auxiliar=?");
         pst.setString(1, fecha.trim());
         pst.setInt(2, codigor);
         pst.setString(3, horario.trim());
         pst.setString(4, aux.trim());
         rs = pst.executeQuery();
         while(rs.next()) {
             muestraresiduo = MuestraResiduo.load(rs);
         }
    } finally {
         if (rs != null) {
            rs.close();
         }
         if (pst != null) {
           pst.close();
         }
          return muestraresiduo;
    }
}

    public static void crearMuestraResiduo(MuestraResiduo muestraresiduo) throws SQLException, Exception {
     mbd.conectar();
     con=mbd.getConexion();
        if (con == null ) {
          throw new SQLException(" no hay conexion ");
     }
     PreparedStatement pst = null;
     try {
          pst = con.prepareStatement("Insert Into MuestraResiduo values(?,?,?,?,?,?,?,?,?,?)");
          pst.setBoolean(1,muestraresiduo.getEstActivo());
          pst.setInt(2,muestraresiduo.getZonaReposo());
          pst.setInt(3,muestraresiduo.getZonaActiva());
          pst.setInt(4,muestraresiduo.getZonaBañistas());
          pst.setInt(5,muestraresiduo.getResiduo().getCodigo());
          pst.setString(6,muestraresiduo.getAuxiliar().getIdentificacion());
          pst.setString(7,muestraresiduo.getHorario());
          pst.setString(8,muestraresiduo.getFecha());
          pst.setBoolean(9,muestraresiduo.getEstModificado());
          pst.setString(10, muestraresiduo.getPuntoMuestreo().getCodigo());
          pst.executeUpdate();
    }finally {
        if (pst != null) {
             pst.close();
        }
   }
}

  public static boolean updateMuestraResiduo (MuestraResiduo muestraresiduo) throws SQLException, Exception {
      mbd.conectar();
    con=mbd.getConexion();
      if (getMuestraResiduo(muestraresiduo.getFecha(),
                            muestraresiduo.getResiduo().getCodigo(),
                            muestraresiduo.getHorario(),muestraresiduo.getAuxiliar().getIdentificacion()) == null) {
           throw new SQLException ("MuestraResiduo no registrado" );
       }
       PreparedStatement pst = null;
       boolean res = false;
       try {
         pst = con.prepareStatement("Update muestraresiduo set estactivo = ?, zonareposo = ?, zonaactiva = ?,"
                 + " zonabañistas = ?, residuo = ?, auxiliar = ?, horario = ?, fecha = ?, estmodificado = ?, codPunto= ?"
                 + " where fecha = ? and residuo=? and horario=? ");
         pst.setBoolean(1 , muestraresiduo.getEstActivo());
         pst.setInt(2 , muestraresiduo.getZonaReposo());
         pst.setInt(3 , muestraresiduo.getZonaActiva());
         pst.setInt(4 , muestraresiduo.getZonaBañistas());
         pst.setInt(5 , muestraresiduo.getResiduo().getCodigo());
         pst.setString(6 , muestraresiduo.getAuxiliar().getIdentificacion());
         pst.setString(7 , muestraresiduo.getHorario());
         pst.setString(8 , muestraresiduo.getFecha());
         pst.setBoolean(9 , muestraresiduo.getEstModificado());
         pst.setString(10 , muestraresiduo.getPuntoMuestreo().getCodigo());
         pst.setString(11 , muestraresiduo.getFecha());
         pst.setInt(12 , muestraresiduo.getResiduo().getCodigo());
         pst.setString(13 , muestraresiduo.getHorario());
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

public static boolean deleteMuestraResiduo(MuestraResiduo muestraresiduo) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    if (getMuestraResiduo(muestraresiduo.getFecha(),
                            muestraresiduo.getResiduo().getCodigo(),
                            muestraresiduo.getHorario(),muestraresiduo.getAuxiliar().getIdentificacion()) == null) {
          throw new SQLException("No hay elemento clave de la clase MuestraResiduo");
     }
     PreparedStatement pst = null;
     boolean res = false;
     try {
        pst = con.prepareStatement("delete * from MuestraResiduo where fecha = ? and residuo=? and horario=?");
         pst.setString(1, muestraresiduo.getFecha());
         pst.setInt(2, muestraresiduo.getResiduo().getCodigo());
         pst.setString(3, muestraresiduo.getHorario());
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

public static List<MuestraResiduo> listarMuestraResiduo() throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraResiduo> listamuestraresiduo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestraresiduo ");
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestraresiduo.add(MuestraResiduo.load(rs));
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


public static List<MuestraResiduo> listarMuestraResiduoF(String fecha,String horario) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraResiduo> listamuestraresiduo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestraresiduo where fecha=? and horario=?");
        pst.setString(1, fecha);
        pst.setString(2, horario);
       // System.out.println(pst.toString());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestraresiduo.add(MuestraResiduo.load(rs));
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


public static List<MuestraResiduo> listarMuestraResiduoFA(String fecha,String auxiliar) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
     ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraResiduo> listamuestraresiduo = new LinkedList();
     try {
        pst = con.prepareStatement("select * from muestraresiduo where fecha=? and auxiliar=?");
        pst.setString(1, fecha);
        pst.setString(2, auxiliar);
       // System.out.println(pst.toString());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestraresiduo.add(MuestraResiduo.load(rs));
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



public static List<MuestraResiduo> listarMuestraResiduoPlaya(int cplaya) throws SQLException, Exception {
    mbd.conectar();
    con=mbd.getConexion();
    ResultSet rs = null;
     PreparedStatement pst = null;
     List<MuestraResiduo> listamuestraresiduo = new LinkedList();
     try {
         pst = con.prepareStatement("select a.* from muestraresiduo a,playa p,"
                + "puntomuestreo pm,auxiliar au "
                + "where p.codigo=pm.codPlaya and p.codigo="+cplaya
                + " and au.identificacion=a.auxiliar and a.codpunto=pm.codigo");
         System.out.println(pst.toString());
        rs = pst.executeQuery();
        while(rs.next()) {
           listamuestraresiduo.add(MuestraResiduo.load(rs));
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
