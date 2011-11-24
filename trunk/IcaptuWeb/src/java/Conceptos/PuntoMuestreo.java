package Conceptos;

import MPersistencia.MPPlaya;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PuntoMuestreo implements Serializable {
	private String _codigo;
	private String _descripcion;
	private Playa playa;

        public static PuntoMuestreo load(ResultSet rs) throws SQLException, Exception {
           PuntoMuestreo pun= new PuntoMuestreo();
           pun.setCodigo(rs.getString(1));
           pun.setDescripcion(rs.getString(2));
           pun.setPlaya(MPPlaya.getPlaya(rs.getInt(3)));
           return pun;
        }
	public void setCodigo(String aCodigo) {
		this._codigo = aCodigo;
	}

	public String getCodigo() {
		return this._codigo;
	}

	public void setDescripcion(String aDescripcion) {
		this._descripcion = aDescripcion;
	}

	public String getDescripcion() {
		return this._descripcion;
	}

    /**
     * @return the playa
     */
    public Playa getPlaya() {
        return playa;
    }

    /**
     * @param playa the playa to set
     */
    public void setPlaya(Playa playa) {
        this.playa = playa;
    }
}