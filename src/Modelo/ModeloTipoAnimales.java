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
public class ModeloTipoAnimales {

    private String id;
    private String idFinca;
    private String descFinca;
    private String descripcion;
    private String estado;
    private String fecha;
    private String idUsuario;

    public ModeloTipoAnimales() {

    }

    public ModeloTipoAnimales(String id, String idFinca, String descripcion, String estado, String fecha, String idUsuario, String desFinca) {
        this.id = id;
        this.idFinca = idFinca;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.descFinca = desFinca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(String idFinca) {
        this.idFinca = idFinca;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescFinca() {
        return descFinca;
    }

    public void setDescFinca(String descFinca) {
        this.descFinca = descFinca;
    }

}
