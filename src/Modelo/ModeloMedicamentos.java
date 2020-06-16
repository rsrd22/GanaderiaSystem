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
public class ModeloMedicamentos {

    private String descripcion;
    private String estado;
    private String fecha;
    private String id;
    private String idUsuario;
    private String unidadMedida;

    /**
     * Constructor de la clase
     *
     * @param descripcion
     * @param estado
     * @param fecha
     * @param id
     * @param id_usuario
     * @param unidad_medida
     */
    public ModeloMedicamentos(String descripcion, String estado, String fecha, String id, String id_usuario, String unidad_medida) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.id = id;
        this.idUsuario = id_usuario;
        this.unidadMedida = unidad_medida;
    }

    public ModeloMedicamentos() {
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}
