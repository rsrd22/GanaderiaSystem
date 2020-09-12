/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Usuario;

import java.util.ArrayList;

/**
 *
 * @author MERRY
 */
public class ModeloPermisos {
    
    
    private String id;
    private String tipo;
    private String valor_tipo;
    private String fecha;
    private String id_usuario;
    private ArrayList<ModeloPermisoxModulos> listaPermisoModulos;

    public ModeloPermisos() {
        this.id = "";
        this.tipo = "";
        this.valor_tipo = "";
        this.fecha = "";
        this.id_usuario = "";
        listaPermisoModulos = new ArrayList<>(); 
    }

    public ModeloPermisos(String id, String tipo, String valor_tipo, String fecha, String id_usuario, ArrayList<ModeloPermisoxModulos> listaPermisoModulos) {
        this.id = id;
        this.tipo = tipo;
        this.valor_tipo = valor_tipo;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.listaPermisoModulos = listaPermisoModulos;
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

    public String getValor_tipo() {
        return valor_tipo;
    }

    public void setValor_tipo(String valor_tipo) {
        this.valor_tipo = valor_tipo;
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

    public ArrayList<ModeloPermisoxModulos> getListaPermisoModulos() {
        return listaPermisoModulos;
    }

    public void setListaPermisoModulos(ArrayList<ModeloPermisoxModulos> listaPermisoModulos) {
        this.listaPermisoModulos = listaPermisoModulos;
    }
    
    
    
    
}
