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
public class ModeloFincas {
    private String id;
    private String descripcion;
    private String direccion;
    private String areat;
    private String id_propietario;
    private String propietario;
    private String fecha;
    private String idUsuario;
    private String idTipoMoneda;

    public ModeloFincas() {
        this.id = null;
        this.descripcion = null;
        this.direccion = null;
        this.areat = null;
        this.id_propietario = null;
        this.propietario = null;
        this.fecha = null;
        this.idUsuario = null;
        this.idTipoMoneda = null;
    }
    
    public ModeloFincas(String id, String descripcion, String direccion, String areat, String id_propietario, String propietario, String fecha, String idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.areat = areat;
        this.id_propietario = id_propietario;
        this.propietario = propietario;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idTipoMoneda = null;
        
    }
    public ModeloFincas(String id, String descripcion, String direccion, String areat, String id_propietario, String propietario, String fecha, String idUsuario, String idTipoMoneda) {
        this.id = id;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.areat = areat;
        this.id_propietario = id_propietario;
        this.propietario = propietario;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.idTipoMoneda = idTipoMoneda;
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getAreat() {
        return areat;
    }

    public void setAreat(String areat) {
        this.areat = areat;
    }

    public String getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(String id_propietario) {
        this.id_propietario = id_propietario;
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

    public String getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(String idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }
    
    
    
    
}
