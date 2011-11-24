package Conceptos;

import MPersistencia.MPAuxiliar;
import MPersistencia.MPPlaya;
import MPersistencia.MPPuntoMuestreo;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MuestraDensidad implements Serializable {
	private String _horario;
	private int _numeroTuristas;
	private int _numeroVendedores;
	private int _numeroAutoridades;
	//private RelacionMuestras _relacionMuestrasDensidad;
	private double _densidadUsuarios;
	private Auxiliar _auxiliar;
        private String fecha;
        private PuntoMuestreo puntoMuestreo;


public static MuestraDensidad load(ResultSet rs) throws SQLException, Exception {
   MuestraDensidad mue= new MuestraDensidad();
   mue.setHorario(rs.getString(1));
   mue.setNumeroTuristas(rs.getInt(2));
   mue.setNumeroVendedores(rs.getInt(3));
   mue.setNumeroAutoridades(rs.getInt(4));
   mue.setDensidadUsuarios(rs.getDouble(5));
   mue.setAuxiliar(MPAuxiliar.getAuxiliar(rs.getString(6)));
   mue.setFecha(rs.getString(7));
   PuntoMuestreo pm=MPPuntoMuestreo.getPuntoMuestreo(rs.getString(8));
   mue.densidadUsuario(pm);
   mue.setPuntoMuestreo(pm);
   return mue;
}


        public double densidadUsuario(RelacionMuestras rm){
            double densidad=rm.getArea()/(this._numeroAutoridades+this._numeroTuristas+this._numeroVendedores);
            return densidad;
        }
        
        public void densidadUsuario(Playa p){
            _densidadUsuarios=p.getArea()/(this._numeroAutoridades+this._numeroTuristas+this._numeroVendedores);
        }

        public void densidadUsuario(PuntoMuestreo p) throws SQLException, Exception{
            Playa pa=MPPlaya.getPlaya(p.getPlaya().getCodigo());
            _densidadUsuarios=pa.getArea()/(this._numeroAutoridades+this._numeroTuristas+this._numeroVendedores);
            System.out.println(p.getDescripcion()+"Densidad: "+_densidadUsuarios);
        }


        public double media(MuestraDensidad mdA1,MuestraDensidad mdA2){
            return (mdA1._densidadUsuarios+mdA2._densidadUsuarios)/2;
        }

        public double desviacionEstandar(MuestraDensidad mdA1,MuestraDensidad mdA2){
            double varianza=0;
            double desviacion=0;
            double med=media(mdA1,mdA2);
            varianza=(Math.pow(mdA1._densidadUsuarios-med, 2)+Math.pow(mdA2._densidadUsuarios-med, 2))/2;
            desviacion=Math.sqrt(varianza);
            return desviacion;
        }
        /**
	 * se calcula teniendo en cuanta los valores de la muestra implementando el procedimiento de desviacion estandar.
	 * varianza= ( (densidadUsuariosA1-Media) ^2+(densidadUsuariosA2-Media) ^2)/2  
	 * DesviacionEstandar= ?varianza
	 *  
	 */

         public MuestraDensidad(){

         }

	public MuestraDensidad(RelacionMuestras relMuestras){
            
            this._densidadUsuarios=relMuestras.getArea()/(this._numeroTuristas+this._numeroVendedores+this._numeroAutoridades);
            
        }


        public void setHorario(String aHorario) {
		this._horario = aHorario;
	}

	public String getHorario() {
		return this._horario;
	}

	public void setNumeroTuristas(int aNumeroTuristas) {
		this._numeroTuristas = aNumeroTuristas;
	}

	public int getNumeroTuristas() {
		return this._numeroTuristas;
	}

	public void setNumeroVendedores(int aNumeroVendedores) {
		this._numeroVendedores = aNumeroVendedores;
	}

	public int getNumeroVendedores() {
		return this._numeroVendedores;
	}

	public void setNumeroAutoridades(int aNumeroAutoridades) {
		this._numeroAutoridades = aNumeroAutoridades;
	}

	public int getNumeroAutoridades() {
		return this._numeroAutoridades;
	}

	/*public void setRelacionMuestrasDensidad(RelacionMuestras aRelacionMuestrasDensidad) {
		this._relacionMuestrasDensidad = aRelacionMuestrasDensidad;
	}

	public RelacionMuestras getRelacionMuestrasDensidad() {
		return this._relacionMuestrasDensidad;
	}
*/
	public void setDensidadUsuarios(double aDensidadUsuarios) {
		this._densidadUsuarios = aDensidadUsuarios;
	}

	public double getDensidadUsuarios() {
		return this._densidadUsuarios;
	}




    /**
     * @return the _auxiliar
     */
    public Auxiliar getAuxiliar() {
        return _auxiliar;
    }

    /**
     * @param auxiliar the _auxiliar to set
     */
    public void setAuxiliar(Auxiliar auxiliar) {
        this._auxiliar = auxiliar;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the puntoMuestreo
     */
    public PuntoMuestreo getPuntoMuestreo() {
        return puntoMuestreo;
    }

    /**
     * @param puntoMuestreo the puntoMuestreo to set
     */
    public void setPuntoMuestreo(PuntoMuestreo puntoMuestreo) {
        this.puntoMuestreo = puntoMuestreo;
    }


}