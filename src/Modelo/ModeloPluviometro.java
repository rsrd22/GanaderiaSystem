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
public class ModeloPluviometro {
    private String id;
    private String id_finca;
    private String fecha_registro;
    private String cantidad;
    private String id_usuario;
    private String fecha;

    public ModeloPluviometro() {
        this.id = null;
        this.id_finca = null;
        this.fecha_registro = null;
        this.cantidad = null;
        this.id_usuario = null;
        this.fecha = null;
    }
    
    public ModeloPluviometro(String id, String id_finca, String fecha_registro, String cantidad, String id_usuario, String fecha) {
        this.id = id;
        this.id_finca = id_finca;
        this.fecha_registro = fecha_registro;
        this.cantidad = cantidad;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
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

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

    
    
    
}
