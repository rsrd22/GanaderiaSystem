/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuario;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloUsuarios {

    private String clave;
    private String clave_dinamica;
    private String estado;
    private String id;
    private String id_empleado;
    private String id_perfil;
    private String usuario;
    private String descPerfil;

    public ModeloUsuarios() {
    }

    public ModeloUsuarios(String clave, String clave_dinamica, String estado, String id, String id_empleado, String id_perfil, String usuario, String descPerfil) {
        this.clave = clave;
        this.clave_dinamica = clave_dinamica;
        this.estado = estado;
        this.id = id;
        this.id_empleado = id_empleado;
        this.id_perfil = id_perfil;
        this.usuario = usuario;
        this.descPerfil = descPerfil;
    }

    public String getDescPerfil() {
        return descPerfil;
    }

    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave_dinamica() {
        return clave_dinamica;
    }

    public void setClave_dinamica(String clave_dinamica) {
        this.clave_dinamica = clave_dinamica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(String id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
