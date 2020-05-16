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
public class ModeloMacroGrupo {

    private String id;
    private String descripcion;
    private String idFinca;
    private String descripcionFinca;
    private String estado;
    private String fecha;
    private String idUsuario;

    public ModeloMacroGrupo() {
    }

    public ModeloMacroGrupo(String id, String descripcion, String estado, String fecha, String idUsuario,
            String idFinca, String descripcionFinca
    ) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idFinca = idFinca;
        this.descripcionFinca = descripcionFinca;
    }

    public String getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(String idFinca) {
        this.idFinca = idFinca;
    }

    public String getDescripcionFinca() {
        return descripcionFinca;
    }

    public void setDescripcionFinca(String descripcionFinca) {
        this.descripcionFinca = descripcionFinca;
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
