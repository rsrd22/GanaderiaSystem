/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloTarea {

    private String descripcion;
    private String estado;
    private String fechaRegistro;
    private String id;
    private String idActividad;
    private String descripcionActividad;
    private String usuario;

    public ModeloTarea() {
    }

    public ModeloTarea(String descripcion, String estado, String fecha_registro, String id, String id_actividad, String usuario,String descripcionActividad) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fecha_registro;
        this.id = id;
        this.idActividad = id_actividad;
        this.usuario = usuario;
        this.descripcionActividad = descripcionActividad;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fecha_registro) {
        this.fechaRegistro = fecha_registro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(String id_actividad) {
        this.idActividad = id_actividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
