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
public class ModeloLibro {
    private String cantidad;
    private String debe;
    private String detalle;
    private String fecha;
    private String fecha_libro;
    private String haber;
    private String id;
    private String id_finca;
    private String id_producto;
    private String id_usuario;
    private String precioxunidad;
    private String saldo;
    
    public ModeloLibro() {
        this.cantidad = "";
        this.debe = "";
        this.detalle = "";
        this.fecha = "";
        this.fecha_libro = "";
        this.haber = "";
        this.id = "";
        this.id_finca = "";
        this.id_producto = "";
        this.id_usuario = "";
        this.precioxunidad = "";
        this.saldo = "";
    }

    public ModeloLibro(String cantidad, String debe, String detalle, String fecha, String fecha_libro, String haber, String id, String id_finca, String id_producto, String id_usuario, String precioxunidad, String saldo) {
        this.cantidad = cantidad;
        this.debe = debe;
        this.detalle = detalle;
        this.fecha = fecha;
        this.fecha_libro = fecha_libro;
        this.haber = haber;
        this.id = id;
        this.id_finca = id_finca;
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.precioxunidad = precioxunidad;
        this.saldo = saldo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDebe() {
        return debe;
    }

    public void setDebe(String debe) {
        this.debe = debe;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_libro() {
        return fecha_libro;
    }

    public void setFecha_libro(String fecha_libro) {
        this.fecha_libro = fecha_libro;
    }

    public String getHaber() {
        return haber;
    }

    public void setHaber(String haber) {
        this.haber = haber;
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

    public String getPrecioxunidad() {
        return precioxunidad;
    }

    public void setPrecioxunidad(String precioxunidad) {
        this.precioxunidad = precioxunidad;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
    
}
