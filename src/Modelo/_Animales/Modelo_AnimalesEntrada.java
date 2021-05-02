/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo._Animales;

import Modelo.ModeloTraslado;

/**
 *
 * @author DOLFHANDLER
 */
public class Modelo_AnimalesEntrada {
    private Modelo_Animales animal;
    private Modelo_AnimalesDescendientes descendiente;
    private ModeloTraslado traslado;
    private String actualizarRegistroMadre;

    public Modelo_AnimalesEntrada() {
    }

    public Modelo_AnimalesEntrada(Modelo_Animales animal, Modelo_AnimalesDescendientes descendiente, ModeloTraslado traslado) {
        this.animal = animal;
        this.descendiente = descendiente;
        this.traslado = traslado;
    }

    public String getActualizarRegistroMadre() {
        return actualizarRegistroMadre;
    }

    public void setActualizarRegistroMadre(String actualizarRegistroMadre) {
        this.actualizarRegistroMadre = actualizarRegistroMadre;
    }

    public Modelo_Animales getAnimal() {
        return animal;
    }

    public void setAnimal(Modelo_Animales animal) {
        this.animal = animal;
    }

    public Modelo_AnimalesDescendientes getDescendiente() {
        return descendiente;
    }

    public void setDescendiente(Modelo_AnimalesDescendientes descendiente) {
        this.descendiente = descendiente;
    }

    public ModeloTraslado getTraslado() {
        return traslado;
    }

    public void setModeloTraslado(ModeloTraslado traslado) {
        this.traslado = traslado;
    }

}
