package Conceptos;

import MPersistencia.MPMuestraDensidad;
import MPersistencia.MPPlaya;
import MPersistencia.MPPuntoMuestreo;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class RelacionMuestras implements Serializable {
	private Playa _playa;
	private PuntoMuestreo _puntoMuestreo;
        private MuestraDensidad muestradensidaA1;
        private MuestraDensidad muestradensidaA2;

        public static RelacionMuestras load(ResultSet rs) throws SQLException, Exception {
           RelacionMuestras rel= new RelacionMuestras();
           rel.setPlaya(MPPlaya.getPlaya(Integer.parseInt(rs.getString(1))));
           rel.setPuntoMuestreo(MPPuntoMuestreo.getPuntoMuestreo(rs.getString(2)));
           rel.setMuestradensidaA1(MPMuestraDensidad.getMuestraDensidadC(rs.getInt(3)));
           rel.setMuestradensidaA1(MPMuestraDensidad.getMuestraDensidadC(rs.getInt(4)));
           rel.setLongitud(rs.getDouble(5));
           rel.setAnchoSeco(rs.getDouble(6));
           rel.setAnchoSumergido(rs.getDouble(7));
           return rel;
        }

        public double media(MuestraDensidad mdA1,MuestraDensidad mdA2){
            return (getMuestradensidaA1().getDensidadUsuarios()+getMuestradensidaA2().getDensidadUsuarios())/2;
        }

        public double desviacionEstandar(MuestraDensidad mdA1,MuestraDensidad mdA2){
            double varianza=0;
            double desviacion=0;
            double med=media(mdA1,mdA2);
            varianza=(Math.pow(getMuestradensidaA1().getDensidadUsuarios()-med, 2)+Math.pow(getMuestradensidaA2().getDensidadUsuarios()-med, 2))/2;
            desviacion=Math.sqrt(varianza);
            return desviacion;
        }

	/**
	 * promedio de medias de las muestras de densidad
	 */
	private double _totalmedia;
	/**
	 * longitud de la playa en metros
	 */
	private double _longitud;
	/**
	 * ancho de la parte seca de la playa en metros
	 */
	private double _anchoSeco;
	/**
	 * ancho de la parte sumergida de la playa en metros
	 */
	private double _anchoSumergido;
	/**
	 * area de la playa en metros cuadrado
	 */
	private double _area = this._longitud*(this._anchoSeco+this._anchoSumergido);
	/**
	 * promedio de desviaciones de las muestras de densidad
	 */
	private double _totalDesviacionEstandar;
	//Vector<MuestraDensidad> _unnamed_MuestraDensidad_ = new Vector<MuestraDensidad>();
	

	public void setPuntoMuestreo(PuntoMuestreo aPuntoMuestreo) {
		this._puntoMuestreo = aPuntoMuestreo;
	}

	public PuntoMuestreo getPuntoMuestreo() {
		return this._puntoMuestreo;
	}

	public void setTotalmedia(double aTotalmedia) {
		this._totalmedia = aTotalmedia;
	}

	public double getTotalmedia() {
		return this._totalmedia;
	}

	public void setLongitud(double aLongitud) {
		this._longitud = aLongitud;
	}

	public double getLongitud() {
		return this._longitud;
	}

	public void setAnchoSeco(double aAnchoSeco) {
		this._anchoSeco = aAnchoSeco;
	}

	public double getAnchoSeco() {
		return this._anchoSeco;
	}

	public void setAnchoSumergido(double aAnchoSumergido) {
		this._anchoSumergido = aAnchoSumergido;
	}

	public double getAnchoSumergido() {
		return this._anchoSumergido;
	}

	public void setArea(double aArea) {
		this._area = aArea;
	}

	public double getArea() {
		return this._area;
	}

	public void setTotalDesviacionEstandar(double aTotalDesviacionEstandar) {
		this._totalDesviacionEstandar = aTotalDesviacionEstandar;
	}

	public double getTotalDesviacionEstandar() {
		return this._totalDesviacionEstandar;
	}

    /**
     * @return the muestradensidaA1
     */
    public MuestraDensidad getMuestradensidaA1() {
        return muestradensidaA1;
    }

    /**
     * @param muestradensidaA1 the muestradensidaA1 to set
     */
    public void setMuestradensidaA1(MuestraDensidad muestradensidaA1) {
        this.muestradensidaA1 = muestradensidaA1;
    }

    /**
     * @return the muestradensidaA2
     */
    public MuestraDensidad getMuestradensidaA2() {
        return muestradensidaA2;
    }

    /**
     * @param muestradensidaA2 the muestradensidaA2 to set
     */
    public void setMuestradensidaA2(MuestraDensidad muestradensidaA2) {
        this.muestradensidaA2 = muestradensidaA2;
    }

    /**
     * @return the _playa
     */
    public Playa getPlaya() {
        return _playa;
    }

    /**
     * @param playa the _playa to set
     */
    public void setPlaya(Playa playa) {
        this._playa = playa;
    }
}