/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo._Animales;

/**
 *
 * @author DOLFHANDLER
 */
public class Modelo_AnimalesDescendientes {

    private String id;
    private String id_animal;
    private String id_madre;
    private String nro_descendiente;
    private String nro_parto;
    private String fecha;

    public Modelo_AnimalesDescendientes() {
    }

    public Modelo_AnimalesDescendientes(String id, String id_animal, String id_madre, String nro_descendiente, String nro_parto, String fecha) {
        this.id = id;
        this.id_animal = id_animal;
        this.id_madre = id_madre;
        this.nro_descendiente = nro_descendiente;
        this.nro_parto = nro_parto;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_animal() {
        return id_animal;
    }

    public void setId_animal(String id_animal) {
        this.id_animal = id_animal;
    }

    public String getId_madre() {
        return id_madre;
    }

    public void setId_madre(String id_madre) {
        this.id_madre = id_madre;
    }

    public String getNro_descendiente() {
        return nro_descendiente;
    }

    public void setNro_descendiente(String nro_descendiente) {
        this.nro_descendiente = nro_descendiente;
    }

    public String getNro_parto() {
        return nro_parto;
    }

    public void setNro_parto(String nro_parto) {
        this.nro_parto = nro_parto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
