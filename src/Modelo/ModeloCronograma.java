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
public class ModeloCronograma {

    private String Anio;
    private String Fecha;
    private String Id;
    private String IdActividad;
    private String IdEstado;
    private String IdFinca;
    private String IdUsuario;
    private String Mes;
    private String Semana;
    private String color;

    /**
     *Constructor del modelo.
     * 
     * @param Anio
     * @param Fecha
     * @param Id
     * @param IdActividad
     * @param IdEstado
     * @param IdFinca
     * @param IdUsuario
     * @param Mes
     * @param Semana
     */
    public ModeloCronograma(String Anio, String Fecha, String Id, String IdActividad, String IdEstado, String IdFinca, String IdUsuario, String Mes, String Semana, String color) {
        this.Anio = Anio;
        this.Fecha = Fecha;
        this.Id = Id;
        this.IdActividad = IdActividad;
        this.IdEstado = IdEstado;
        this.IdFinca = IdFinca;
        this.IdUsuario = IdUsuario;
        this.Mes = Mes;
        this.Semana = Semana;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ModeloCronograma() {
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getIdActividad() {
        return IdActividad;
    }

    public void setIdActividad(String IdActividad) {
        this.IdActividad = IdActividad;
    }

    public String getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(String IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getIdFinca() {
        return IdFinca;
    }

    public void setIdFinca(String IdFinca) {
        this.IdFinca = IdFinca;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String Mes) {
        this.Mes = Mes;
    }

    public String getSemana() {
        return Semana;
    }

    public void setSemana(String Semana) {
        this.Semana = Semana;
    }

}
