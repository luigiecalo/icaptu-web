/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Conceptos.ImagenPlaya;
import Conceptos.Playa;
import MPersistencia.MPPlaya;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import org.icefaces.component.fileentry.FileEntry;
import org.icefaces.component.fileentry.FileEntryEvent;
import org.icefaces.component.fileentry.FileEntryResults;


/**
 *
 * @author USUARIO
 */
public class controlPlayas implements Serializable{

    /**
     * @param aPlayas the playas to set
     */
    public static void setPlayas(List<Playa> aPlayas) {
        playas = aPlayas;
    }
        private int codigo;
	private String nombre;
	private String ubicacion;
	private String descripcion;
        private ImagenPlaya img=new ImagenPlaya();
        private String direccion;
        private static List<Playa> playas;
        private double longitud;
        private double anchoSeco;
        private double anchoSumergido;
        private boolean showVista=false;
        private double latitudM;
        private double longitudM;

    /** Creates a new instance of controlPlayas */
    public controlPlayas() {
    }

      public void PrepararImagen(String direccion){
        getImg().setFileImg(direccion);
        try {
            getImg().setFlujo(getImg().getFileImg());
            getImg().setNombre(getImg().getFileImg().getName());
            getImg().setTipo(getImg().getFileImg().getName().substring(getImg().getFileImg().getName().length()-3));
            getImg().setTitulo(getImg().getFileImg().getName().substring(0,getImg().getFileImg().getName().length()-4));
            System.out.println("preparar imagen:"+getImg().getNombre()+" "+getImg().getTitulo());
            ImagenPlaya.resize(getImg(), 100, 70);
        } catch (FileNotFoundException ex) {
            //JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
        }
    }

      public static void listaPlayas() throws SQLException, Exception{
          setPlayas(MPPlaya.listarPlaya());
      }

public void listener(FileEntryEvent event) throws MalformedURLException, IOException {
    FileEntry fileEntry = (FileEntry) event.getSource();
    FileEntryResults results = fileEntry.getResults();
    for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
       
        if (fileInfo.isSaved()) {
             PrepararImagen(fileInfo.getFile().getCanonicalPath().toString());
             setDireccion(fileInfo.getFile().getCanonicalPath().toString());
             //System.out.println(fileInfo.getFile().getCanonicalPath().toString());
            // Process the file. Only save cloned copies of results or fileInfo
        }
    }
}
    public void visualizarPanel(){
        setShowVista(true);
        System.out.println("visualice el panel");
    }
    public void OcultarPanel(){
        setShowVista(false);
    }
        public static void mostrarImagen(ImagenPlaya img1){
        try {
           // panelImag.setImage(new ImageIcon("C:\\proyectos\\TPV\\tpvlibre\\imagen\\"+img1.getTitulo()+".jpg").getImage());
            //image.setIcon(new javax.swing.ImageIcon("C:\\proyectos\\TPV\\tpvlibre\\imagen\\"+img1.getTitulo()+".jpg"));
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
        }
    }


    public static Blob consultarImagen(int codigo){
       ImagenPlaya img1=new ImagenPlaya();
        try {
             img1 = ImagenPlaya.consultar(codigo);
            if(!img1.getNombre().equals("")){

               // panelImag.setImage(new javax.swing.ImageIcon(img1.getContenido().getBytes(1,(int) img1.getContenido().length())).getImage());
                //image.setIcon(new javax.swing.ImageIcon(img1.getContenido().getBytes(1,(int) img1.getContenido().length())));
            }else{
               img1 = ImagenPlaya.consultar(0);
                // panelImag.setImage(new javax.swing.ImageIcon(this.getClass().getResource("/tpvlibre/form/iconos/NoImagen.jpg")).getImage());
            }
        } catch (Exception ex) {
           // JOptionPane.showMessageDialog(null,"Error: "+ex.getMessage());
        }
        return img1.getContenidoG();
    }

    public void registrarPlaya() throws SQLException, Exception{
    Playa pla=new Playa();
    pla.setCodigo(codigo);
    pla.setNombre(nombre);
    pla.setDescripcion(descripcion);
    pla.setUbicacion(ubicacion);
    pla.setLongitud(longitud);
    pla.setAnchoSeco(anchoSeco);
    pla.setAnchoSumergido(anchoSumergido);
    pla.setArea();
    //PrepararImagen(direccion);
        if(MPPlaya.existePlaya(pla.getCodigo())){
            MPPlaya.updatePlaya(pla, pla.getCodigo());
            if(!img.getNombre().equals("")){
                getImg().setId(pla.getCodigo());
                if(ImagenPlaya.consultarExistencia(pla.getCodigo())){
                    ImagenPlaya.eliminar(pla.getCodigo());
                }
                ImagenPlaya.registar(getImg());
            }
        }
        else{
            MPPlaya.crearPlaya(pla);

            if(!img.getNombre().equals("")){
                getImg().setId(pla.getCodigo());
                ImagenPlaya.registar(getImg());
            }
        }
    }


    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the img
     */
    public ImagenPlaya getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(ImagenPlaya img) {
        this.img = img;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the playas
     */
    public List<Playa> getPlayas() {
        return playas;
    }

    /**
     * @return the showVista
     */
    public boolean getShowVista() {
        return showVista;
    }

    /**
     * @param showVista the showVista to set
     */
    public void setShowVista(boolean showVista) {
        this.showVista = showVista;
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
     * @return the latitudM
     */
    public double getLatitudM() {
        return latitudM;
    }

    /**
     * @param latitudM the latitudM to set
     */
    public void setLatitudM(double latitudM) {
        this.latitudM = latitudM;
    }

    /**
     * @return the longitudM
     */
    public double getLongitudM() {
        return longitudM;
    }

    /**
     * @param longitudM the longitudM to set
     */
    public void setLongitudM(double longitudM) {
        this.longitudM = longitudM;
    }

  

}
