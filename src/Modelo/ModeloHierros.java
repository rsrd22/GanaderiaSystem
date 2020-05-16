/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author MERRY
 */
public class ModeloHierros {
    private String id;
    private String id_propietario;
    private String descripcion;
    private String nombre_imagen;
    private String imagen;
    private String estado;
    private String fecha;
    private String id_usuario;

    public ModeloHierros() {
        this.id = null;
        this.id_propietario = null;
        this.descripcion = null;
        this.nombre_imagen = null;
        this.imagen = null;
        this.estado = null;
        this.fecha = null;
        this.id_usuario = null;
    }
    
    public ModeloHierros(String id, String id_propietario, String descripcion, String nombre_imagen, String imagen, String estado, String fecha, String id_usuario) {
        this.id = id;
        this.id_propietario = id_propietario;
        this.descripcion = descripcion;
        this.nombre_imagen = nombre_imagen;
        this.imagen = imagen;
        this.estado = estado;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_propietario() {
        return id_propietario;
    }

    public String getNombre_imagen() {
        return nombre_imagen;
    }

    public void setNombre_imagen(String nombre_imagen) {
        this.nombre_imagen = nombre_imagen;
    }

    public void setId_propietario(String id_propietario) {
        this.id_propietario = id_propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
    
}
