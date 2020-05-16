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
public class ModeloBloques {
    private String id;
    private String numero;
    private String id_finca;
    private String area;
    private String fecha;
    private String id_usuario;

    public ModeloBloques() {
        this.id = null;
        this.numero = null;
        this.id_finca = null;
        this.area = null;
        this.fecha = null;
        this.id_usuario = null;
    }
    
    public ModeloBloques(String id, String numero, String id_finca, String area, String fecha, String id_usuario) {
        this.id = id;
        this.numero = numero;
        this.id_finca = id_finca;
        this.area = area;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getId_finca() {
        return id_finca;
    }

    public void setId_finca(String id_finca) {
        this.id_finca = id_finca;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
