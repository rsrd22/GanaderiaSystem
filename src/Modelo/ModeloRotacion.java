/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import BaseDeDatos.gestorMySQL;

/**
 *
 * @author MERRY
 */
public class ModeloRotacion {
    private String id;
    private String id_lote;
    private String descripcion_lote;
    private String id_grupo;
    private String descripcion_grupo;
    private String fecha_registro;
    private String fecha_entrada;
    private String fecha_salida;
    private String estado;
    private String fecha;
    private String id_usuario;
    
    public ModeloRotacion() {
        this.id = null;
        this.id_lote = null;
        this.descripcion_lote = null;
        this.id_grupo = null;
        this.descripcion_grupo = null;
        this.fecha_registro = null;
        this.fecha_entrada = null;
        this.fecha_salida = null;
        this.estado = null;
        this.fecha = null;
        this.id_usuario = null;
    }
    
    /**
     * 
     * @param id
     * @param id_lote
     * @param descripcion_lote
     * @param id_grupo
     * @param descripcion_grupo
     * @param fecha_registro
     * @param fecha_entrada
     * @param fecha_salida
     * @param estado
     * @param fecha
     * @param id_usuario 
     */
    public ModeloRotacion(String id, String id_lote, String descripcion_lote, String id_grupo, String descripcion_grupo, String fecha_registro, String fecha_entrada, String fecha_salida, String estado, String fecha, String id_usuario) {
        this.id = id;
        this.id_lote = id_lote;
        this.descripcion_lote = descripcion_lote;
        this.id_grupo = id_grupo;
        this.descripcion_grupo = descripcion_grupo;
        this.fecha_registro = fecha_registro;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
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

    public String getId_lote() {
        return id_lote;
    }

    public void setId_lote(String id_lote) {
        this.id_lote = id_lote;
    }

    public String getDescripcion_lote() {
        return descripcion_lote;
    }

    public void setDescripcion_lote(String descripcion_lote) {
        this.descripcion_lote = descripcion_lote;
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

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
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

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
