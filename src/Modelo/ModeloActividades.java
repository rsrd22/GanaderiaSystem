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
public class ModeloActividades {
    private String id;
    private String descripcion;
    private String estado;
    private String fechaRegistro;
    private String usuario;

    public ModeloActividades() {
    }

    public ModeloActividades(String id, String descripcion, String estado, String fechaRegistro, String usuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
