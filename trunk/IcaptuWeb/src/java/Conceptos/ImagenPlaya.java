/*
 * Articulo.java
 *
 * Created on 7 de junio de 2008, 14:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Conceptos;

/**
 *
 * @author PC_6
 */

import Utilidades.*;
import java.sql.PreparedStatement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.sql.ResultSet;
import javax.imageio.ImageIO;


public class ImagenPlaya {
    private int id;
    private String nombre;
    private String titulo;
    private java.sql.Blob contenido;
    private java.sql.Blob contenidoG;
    private String tipo;
    private File fileImg;
    private FileInputStream flujo;
    private File fileImgG;
    private FileInputStream flujoG;

    //  operaciones de persistencia
       public static void registar(ImagenPlaya ar) throws Exception{
           ManejadorBaseDatos basedatos = ManejadorBaseDatos.getInstancia();
           if(consultarExistencia(ar.getId())){
               throw new Exception("EL articulo que desea registrar ya existe"); 
           }
       try{
           //SimpleDateFormat formatoFehca = new SimpleDateFormat("yyyy-MM-dd");
           //String sfecha = formatoFehca.format(this.fecha);
           String sql = "Insert into ImagenPlaya (id,nombre,titulo,contenido,contenidoG,tipo)"+
           "values(?,?,?,?,?,?)";
          // System.out.println(sql);
            PreparedStatement sentencia = (PreparedStatement) basedatos.getConexion().prepareStatement(sql);
            sentencia.setInt(1, ar.getId());
            sentencia.setString(2, ar.getNombre());
            sentencia.setString(3, ar.getTitulo());
            //resize(ar,100,70);
            sentencia.setBinaryStream(4, ar.getFlujo(), ar.getFileImg().length());
            sentencia.setBinaryStream(5, ar.getFlujoG(), ar.getFileImgG().length());
            sentencia.setString(6, ar.getTipo());
            //basedatos.conectar();
            sentencia.executeUpdate();
            
       }
       catch(Exception er){
                throw new Exception("Error al registar la Consulta"+er.getMessage());
       }
       finally{
          // basedatos.desconectar(null);
       }
   }

       public static void resize(ImagenPlaya ar, int width, int height) throws Exception {
              BufferedImage dest = ImageIO.read(ar.getFileImg());
              System.out.println("Escalar imagen: "+ar.getNombre()+" "+ar.getTitulo());
              BufferedImage bImgEscaladaP = ImageUtils.scaleToSize(width, height, dest);
              BufferedImage bImgEscaladaG = ImageUtils.scaleToSize(600, 400, dest);
              //ImageUtils.scaleToSize(width, height, dest);
              ImageUtils.saveImageToDisk(bImgEscaladaP,"C://Users//USUARIO//Documents//NetBeansProjects//ICaptu2//build//web//Imagenes//"+ar.titulo+".jpg","JPEG");
              ImageUtils.saveImageToDisk(bImgEscaladaG,"C://Users//USUARIO//Documents//NetBeansProjects//ICaptu2//build//web//Imagenes//"+ar.titulo+"G.jpg","JPEG");
              //Image i=dest.getScaledInstance(width, height, width);
              ar.setFileImg("C://Users//USUARIO//Documents//NetBeansProjects//ICaptu2//build//web//Imagenes//"+ar.titulo+".jpg");
              ar.setFlujo(ar.getFileImg());
              ar.setFileImgG("C://Users//USUARIO//Documents//NetBeansProjects//ICaptu2//build//web//Imagenes//"+ar.titulo+"G.jpg");
              ar.setFlujoG(ar.getFileImgG());

       }

       public static void eliminar(int cod) throws Exception{
        String sql = "delete from ImagenPlaya where id = "+cod+"";
        ManejadorBaseDatos basedatos =  ManejadorBaseDatos.getInstancia();
        try{
           basedatos.conectar();
           basedatos.modificar(sql);
         }
         catch(Exception er){
                throw new Exception("No se puede eliminar la imagen");
        }
       }

       public static void Actualizar(ImagenPlaya ar, int id) throws Exception{
           ManejadorBaseDatos basedatos = ManejadorBaseDatos.getInstancia();
       try{
           String sql = "Update ImagenPlaya set id=?,nombre=?,titulo=?,contenido=?,contenidoG=?,tipo=? where id="+id;
           PreparedStatement sentencia = (PreparedStatement) basedatos.getConexion().prepareStatement(sql);
           sentencia.setInt(1, ar.getId());
           sentencia.setString(2, ar.getNombre());
           sentencia.setString(3, ar.getTitulo());
            //resize(ar,100,70);
           sentencia.setBinaryStream(4, ar.getFlujo(), ar.getFileImg().length());
           sentencia.setBinaryStream(5, ar.getFlujoG(), ar.getFileImgG().length());
           sentencia.setString(6, ar.getTipo());
           // basedatos.conectar();
           sentencia.executeUpdate();

       }
       catch(Exception er){
                throw new Exception("Error al registar la Consulta"+er.getMessage());
       }
       finally{
          // basedatos.desconectar(null);
       }
   }

    
    
    public static ImagenPlaya consultar (int cod) throws Exception {
        
        ImagenPlaya tarticulo = null;
        String sql = "SELECT * From ImagenPlaya where id = "+cod+"";
        ManejadorBaseDatos basedatos =  ManejadorBaseDatos.getInstancia();
        try{
            basedatos.conectar();
            ResultSet res = basedatos.consultar(sql);
            if(res.next()){
                tarticulo = new ImagenPlaya();
                tarticulo.id = cod;
                tarticulo.setNombre(res.getString("Nombre"));
                tarticulo.setTitulo(res.getString("titulo"));
                tarticulo.setContenido(res.getBlob("contenido"));
                tarticulo.setContenidoG(res.getBlob("contenidoG"));
                tarticulo.setTipo(res.getString("tipo"));
          }
          //  basedatos.desconectar(res);
            return tarticulo;
         }
        catch(Exception er){
            throw new Exception("No se puede ejcutar la consulta"); 
        }
    }

    public static boolean consultarExistencia (int id) throws Exception {
        
        boolean articulo = false;
        String sql = "SELECT * From ImagenPlaya where id = "+id+"";
        ManejadorBaseDatos basedatos =  ManejadorBaseDatos.getInstancia();
        try{
            basedatos.conectar();
            ResultSet res = basedatos.consultar(sql);
            if(res.next()){
                articulo =true;
                
          }
          //  basedatos.desconectar(res);
            return articulo;
         }
        catch(Exception er){
            throw new Exception("No se puede ejcutar la consulta"); 
        }
    }
    
    public static Vector listar(String sql) throws Exception {
        if(sql == null){
           throw new Exception("Debe especificar una  consulta"); 
        }
        if(sql.trim().equals("")){
           throw new Exception("Debe especificar una  consulta"); 
        }
        Vector articulos = null;
        ManejadorBaseDatos basedatos =  ManejadorBaseDatos.getInstancia();
        try{
            basedatos.conectar();
            ResultSet res = basedatos.consultar(sql);
            while(res.next()){
                if(articulos == null){
                    articulos = new Vector();
                }
                ImagenPlaya tarticulo = new ImagenPlaya();
                tarticulo.id = res.getInt("id");
                tarticulo.setNombre(res.getString("Nombre"));
                tarticulo.setTitulo(res.getString("titulo"));
                tarticulo.setContenido(res.getBlob("contenido"));
                tarticulo.setContenidoG(res.getBlob("contenidoG"));
                tarticulo.setTipo(res.getString("tipo"));
            }
          //  basedatos.desconectar(res);
        }
        catch(Exception er){
            throw new Exception("No se puede ejcutar la consulta de articulos"); 
        }
        return articulos;

    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the contenido
     */
    public java.sql.Blob getContenido() {
        return contenido;
    }

    public void setContenido(java.sql.Blob blob){
        this.contenido = blob;
    }



    /**
     * @param contenido the contenido to set
     */
    
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the fileImg
     */
    public File getFileImg() {
        return fileImg;
    }

    /**
     * @param fileImg the fileImg to set
     */
    public void setFileImg(String url) {
        this.fileImg = new File( url );
    }

    /**
     * @return the flujo
     */
    public FileInputStream getFlujo() {
        return flujo;
    }

    /**
     * @param flujo the flujo to set
     */
    public void setFlujo(File fileImg) throws FileNotFoundException {
        this.flujo = new FileInputStream( fileImg );
    }

    /**
     * @return the contenidoG
     */
    public java.sql.Blob getContenidoG() {
        return contenidoG;
    }

    /**
     * @param contenidoG the contenidoG to set
     */
    public void setContenidoG(java.sql.Blob contenidoG) {
        this.contenidoG = contenidoG;
    }

/**
     * @return the fileImg
     */
    public File getFileImgG() {
        return fileImgG;
    }

    /**
     * @param fileImg the fileImg to set
     */
    public void setFileImgG(String url) {
        this.fileImgG = new File( url );
    }

    /**
     * @return the flujo
     */
    public FileInputStream getFlujoG() {
        return flujoG;
    }

    /**
     * @param flujo the flujo to set
     */
    public void setFlujoG(File fileImg) throws FileNotFoundException {
        this.flujoG = new FileInputStream( fileImg );
    }

    
}
