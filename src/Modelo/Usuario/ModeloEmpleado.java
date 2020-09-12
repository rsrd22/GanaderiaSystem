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
public class ModeloEmpleado {

    private String correo;
    private String direccion;
    private String id;
    private String identificacion;
    private String primer_apellido;
    private String primer_nombre;
    private String segundo_apellido;
    private String segundo_nombre;
    private String tipo_identificacion;

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(String correo, String direccion, String id, String identificacion, String primer_apellido, String primer_nombre, String segundo_apellido, String segundo_nombre, String tipo_identificacion) {
        this.correo = correo;
        this.direccion = direccion;
        this.id = id;
        this.identificacion = identificacion;
        this.primer_apellido = primer_apellido;
        this.primer_nombre = primer_nombre;
        this.segundo_apellido = segundo_apellido;
        this.segundo_nombre = segundo_nombre;
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

}
