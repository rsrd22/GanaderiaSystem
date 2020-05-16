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
public class ModeloFuentesHidricas {
    private String id;
    private String descripcion;
    private String estado;
    private String fecha;
    private String id_usuario;

    public ModeloFuentesHidricas() {
        this.id = null;
        this.descripcion = null;
        this.estado = null;
        this.fecha = null;
        this.id_usuario = null;
    }

    public ModeloFuentesHidricas(String id, String descripcion, String estado, String fecha, String id_usuario) {
        this.id = id;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
