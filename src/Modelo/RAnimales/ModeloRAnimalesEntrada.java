/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.RAnimales;

import Modelo.ModeloTraslado;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloRAnimalesEntrada {
    private ModeloRAnimales animal;
    private ModeloTraslado traslado;
    private String actualizarRegistroMadre;

    public ModeloRAnimalesEntrada(ModeloRAnimales animal, ModeloTraslado traslado, String actualizarRegistroMadre) {
        this.animal = animal;
        this.traslado = traslado;
        this.actualizarRegistroMadre = actualizarRegistroMadre;
    }

    public ModeloRAnimalesEntrada() {
    }

    public ModeloRAnimales getAnimal() {
        return animal;
    }

    public void setAnimal(ModeloRAnimales animal) {
        this.animal = animal;
    }

    public ModeloTraslado getTraslado() {
        return traslado;
    }

    public void setTraslado(ModeloTraslado traslado) {
        this.traslado = traslado;
    }

    public String getActualizarRegistroMadre() {
        return actualizarRegistroMadre;
    }

    public void setActualizarRegistroMadre(String actualizarRegistroMadre) {
        this.actualizarRegistroMadre = actualizarRegistroMadre;
    }
    
    
}
