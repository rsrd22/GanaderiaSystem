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
public class ModeloMedicamentosPorPesaje {

    private String dosis;
    private String id;
    private String id_medicamento;
    private String id_pesaje;
    private String estadoG;

    public ModeloMedicamentosPorPesaje() {
    }

    public ModeloMedicamentosPorPesaje(String dosis, String id, String id_medicamento, String id_pesaje) {
        this.dosis = dosis;
        this.id = id;
        this.id_medicamento = id_medicamento;
        this.id_pesaje = id_pesaje;
    }
    
    public ModeloMedicamentosPorPesaje(String dosis, String id, String id_medicamento, String id_pesaje, String estadoG) {
        this.dosis = dosis;
        this.id = id;
        this.id_medicamento = id_medicamento;
        this.id_pesaje = id_pesaje;
        this.estadoG = estadoG;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_medicamento() {
        return id_medicamento;
    }

    public void setId_medicamento(String id_medicamento) {
        this.id_medicamento = id_medicamento;
    }

    public String getId_pesaje() {
        return id_pesaje;
    }

    public void setId_pesaje(String id_pesaje) {
        this.id_pesaje = id_pesaje;
    }

    public String getEstadoG() {
        return estadoG;
    }

    public void setEstadoG(String estadoG) {
        this.estadoG = estadoG;
    }
    
    
}
