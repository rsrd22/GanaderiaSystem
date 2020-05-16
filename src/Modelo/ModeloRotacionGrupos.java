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
public class ModeloRotacionGrupos {
    private String id;
    private String id_rotacion_lotesxestado;
    private String id_grupo;
    private String descripcion_grupo;
    private String estado;
    private String fecha;
    private String id_usuario;
    
    public ModeloRotacionGrupos() {
        this.id = null;
        this.id_rotacion_lotesxestado = null;
        this.id_grupo = null;
        this.descripcion_grupo = null;
        this.estado = null;
        this.fecha = null;
        this.id_usuario = null;
    }

    public ModeloRotacionGrupos(String id, String id_rotacion_lotesxestado, String id_grupo, String descripcion_grupo, String estado, String fecha, String id_usuario) {
        this.id = id;
        this.id_rotacion_lotesxestado = id_rotacion_lotesxestado;
        this.id_grupo = id_grupo;
        this.descripcion_grupo = descripcion_grupo;
        this.estado = estado;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_rotacion_lotesxestado() {
        return id_rotacion_lotesxestado;
    }

    public void setId_rotacion_lotesxestado(String id_rotacion_lotesxestado) {
        this.id_rotacion_lotesxestado = id_rotacion_lotesxestado;
    }

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getDescripcion_grupo() {
        return descripcion_grupo;
    }

    public void setDescripcion_grupo(String descripcion_grupo) {
        this.descripcion_grupo = descripcion_grupo;
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
