package Conceptos;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Auxiliar implements Serializable{

private String identificacion;
private String nombre;
private String perfil;

public static Auxiliar load(ResultSet rs) throws SQLException {
   Auxiliar aux= new Auxiliar();
   aux.setIdentificacion(rs.getString(1));
   aux.setNombre(rs.getString(2));
   aux.setPerfil(rs.getString(3));
   return aux;
}


public void setNombre(String nom){
   this.nombre=nom;
}
public String getNombre(){
   return nombre;
}

public void setPerfil(String per){
   this.perfil=per;
}
public String getPerfil(){
   return perfil;
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