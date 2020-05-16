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
public class ModeloTraslado {

    private String estado;
    private String fecha;
    private String fechaTraslado;
    private String id;
    private String idAnimal;
    private String idFinca;
    private String idGrupo;
    private String idUsuario;
    private String motivo;
    private String numeroMama;
    private String numeroAnimal;
    private String Peso;
    private String grupo;
    private String lote;

    public ModeloTraslado() {
        this.estado = null;
        this.fecha = null;
        this.fechaTraslado = null;
        this.id = null;
        this.idAnimal = null;
        this.idFinca = null;
        this.idGrupo = null;
        this.idUsuario = null;
        this.motivo = null;
        this.numeroMama = null;
        this.numeroAnimal = null;
        this.Peso = null;
        this.grupo = null;
        this.lote = null;
    }
    
    public ModeloTraslado(String estado, String fecha, String fechaTraslado, String id, String idAnimal, String idFinca, String idGrupo, String idUsuario, String motivo, String numeroMama, String numeroAnimal, String Peso, String grupo, String lote) {
        this.estado = estado;
        this.fecha = fecha;
        this.fechaTraslado = fechaTraslado;
        this.id = id;
        this.idAnimal = idAnimal;
        this.idFinca = idFinca;
        this.idGrupo = idGrupo;
        this.idUsuario = idUsuario;
        this.motivo = motivo;
        this.numeroMama = numeroMama;
        this.numeroAnimal = numeroAnimal;
        this.Peso = Peso;
        this.grupo = grupo;
        this.lote = lote;
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

    public String getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(String fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(String idFinca) {
        this.idFinca = idFinca;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNumeroMama() {
        return numeroMama;
    }

    public void setNumeroMama(String numeroMama) {
        this.numeroMama = numeroMama;
    }

    public String getNumeroAnimal() {
        return numeroAnimal;
    }

    public void setNumeroAnimal(String numeroAnimal) {
        this.numeroAnimal = numeroAnimal;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String Peso) {
        this.Peso = Peso;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    
    
}
