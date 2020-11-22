/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Inventario;

/**
 *
 * @author MERRY
 */
public class ModeloProducto {
    private String descripcion;
    private String estado;
    private String fecha;
    private String id;
    private String id_usuario;
    private String tipo_salida;
    
    public ModeloProducto() {
        this.descripcion = "";
        this.estado = "";
        this.fecha = "";
        this.id = "";
        this.id_usuario = "";
        this.tipo_salida = "";
    }

    public ModeloProducto(String descripcion, String estado, String fecha, String id, String id_usuario, String tipo_salida) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.id = id;
        this.id_usuario = id_usuario;
        this.tipo_salida = tipo_salida;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo_salida() {
        return tipo_salida;
    }

    public void setTipo_salida(String tipo_salida) {
        this.tipo_salida = tipo_salida;
    }
    
    
}
