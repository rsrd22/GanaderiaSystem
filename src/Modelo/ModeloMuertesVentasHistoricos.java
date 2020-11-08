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
public class ModeloMuertesVentasHistoricos {
    private String id;
    private String tipo;
    private String idAnimal;
    private String estado;
    private String fecha;
    private String idUsuario;

    public ModeloMuertesVentasHistoricos() {
        this.id = "";
        this.tipo = "";
        this.idAnimal = "";
        this.estado = "";
        this.fecha = "";
        this.idUsuario = "";
    }

    public ModeloMuertesVentasHistoricos(String id, String tipo, String idAnimal, String estado, String fecha, String idUsuario) {
        this.id = id;
        this.tipo = tipo;
        this.idAnimal = idAnimal;
        this.estado = estado;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
