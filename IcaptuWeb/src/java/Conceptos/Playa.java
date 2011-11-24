package Conceptos;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Playa implements Serializable {
	private int _codigo;
	private String _nombre;
	private String _ubicacion;
	private String _descripcion;
        private double longitud;
        private double anchoSeco;
        private double anchoSumergido;
        private double area;

	
public static Playa load(ResultSet rs) throws SQLException {
   Playa pla= new Playa();
   pla.setCodigo(rs.getInt(1));
   pla.setNombre(rs.getString(2));
   pla.setUbicacion(rs.getString(3));
   pla.setDescripcion(rs.getString(4));
   pla.setLongitud(rs.getDouble(5));
   pla.setAnchoSeco(rs.getDouble(6));
   pla.setAnchoSumergido(rs.getDouble(7));
   pla.setArea(rs.getDouble(8));

   return pla;
}
    public void setArea(){
        this.area=(this.anchoSeco+this.anchoSumergido)*this.longitud;
    }

	public void setCodigo(int aCodigo) {
		this._codigo = aCodigo;
	}

	public int getCodigo() {
		return this._codigo;
	}

	public void setNombre(String aNombre) {
		this._nombre = aNombre;
	}

	public String getNombre() {
		return this._nombre;
	}

	public void setUbicacion(String aUbicacion) {
		this._ubicacion = aUbicacion;
	}

	public String getUbicacion() {
		return this._ubicacion;
	}

	public void setDescripcion(String aDescripcion) {
		this._descripcion = aDescripcion;
	}

	public String getDescripcion() {
		return this._descripcion;
	}

    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the anchoSeco
     */
    public double getAnchoSeco() {
        return anchoSeco;
    }

    /**
     * @param anchoSeco the anchoSeco to set
     */
    public void setAnchoSeco(double anchoSeco) {
        this.anchoSeco = anchoSeco;
    }

    /**
     * @return the anchoSumergido
     */
    public double getAnchoSumergido() {
        return anchoSumergido;
    }

    /**
     * @param anchoSumergido the anchoSumergido to set
     */
    public void setAnchoSumergido(double anchoSumergido) {
        this.anchoSumergido = anchoSumergido;
    }

    /**
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(double area) {
        this.area = area;
    }
}