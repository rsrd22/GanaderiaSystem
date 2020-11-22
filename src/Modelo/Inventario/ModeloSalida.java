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
public class ModeloSalida {
    private String cantidad;
    private String fecha;
    private String fecha_salida;
    private String id;
    private String id_finca;
    private String id_producto;
    private String id_usuario;
    private String observacion;

    public ModeloSalida() {
        this.cantidad = "";
        this.fecha = "";
        this.fecha_salida = "";
        this.id = "";
        this.id_finca = "";
        this.id_producto = "";
        this.id_usuario = "";
        this.observacion = "";
    }
    
    public ModeloSalida(String cantidad, String fecha, String fecha_salida, String id, String id_finca, String id_producto, String id_usuario, String observacion) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.fecha_salida = fecha_salida;
        this.id = id;
        this.id_finca = id_finca;
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.observacion = observacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_finca() {
        return id_finca;
    }

    public void setId_finca(String id_finca) {
        this.id_finca = id_finca;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
