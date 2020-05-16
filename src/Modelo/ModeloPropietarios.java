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
public class ModeloPropietarios {
    private String id;
    private String tipo_identificacion;
    private String identificacion;
    private String primer_nombre;
    private String segundo_nombre;
    private String primer_apellido;
    private String segundo_apellido;
    private String direccion;
    private String correo;
    private String estado;
    private String fecha;
    private String id_usuario;

    public ModeloPropietarios() {
        this.id = null;
        this.tipo_identificacion = null;
        this.identificacion = null;
        this.primer_nombre = null;
        this.segundo_nombre = null;
        this.primer_apellido = null;
        this.segundo_apellido = null;
        this.direccion = null;
        this.correo = null;
        this.estado = null;
        this.fecha = null;
        this.id_usuario = null;
    }
    
    public ModeloPropietarios(String id, String tipo_identificacion, String identificacion, String primer_nombre, String segundo_nombre, String primer_apellido, String segundo_apellido, String direccion, String correo, String estado, String fecha, String id_usuario) {
        this.id = id;
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.direccion = direccion;
        this.correo = correo;
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

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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
