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
public class ModeloInventario {
    private String entrada;
    private String fecha;
    private String id;
    private String id_finca;
    private String id_producto;
    private String id_usuario;
    private String salidas;
    private String stock;
    
    public ModeloInventario() {
        this.entrada = "";
        this.fecha = "";
        this.id = "";
        this.id_finca = "";
        this.id_producto = "";
        this.id_usuario = "";
        this.salidas = "";
        this.stock = "";
    }

    public ModeloInventario(String entrada, String fecha, String id, String id_finca, String id_producto, String id_usuario, String salidas, String stock) {
        this.entrada = entrada;
        this.fecha = fecha;
        this.id = id;
        this.id_finca = id_finca;
        this.id_producto = id_producto;
        this.id_usuario = id_usuario;
        this.salidas = salidas;
        this.stock = stock;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
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

    public String getSalidas() {
        return salidas;
    }

    public void setSalidas(String salidas) {
        this.salidas = salidas;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    
}
