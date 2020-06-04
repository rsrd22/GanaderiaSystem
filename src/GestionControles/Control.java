/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionControles;

/**
 *
 * @author DOLFHANDLER
 */
public class Control {

    private boolean habilitado;
    private boolean limpiarDespuesDeGuardar;
    private Object control;

    /**
     * Constructor de la clase
     *
     * @param habilitado estado inicial del control
     * @param control objeto que representa el control de usuario
     */
    public Control(boolean habilitado, Object control) {
        this.habilitado = habilitado;
        this.control = control;
        this.limpiarDespuesDeGuardar = false;
    }

    public Control() {
        this.habilitado = false;
        this.limpiarDespuesDeGuardar = false;
    }

    public boolean isLimpiarDespuesDeGuardar() {
        return limpiarDespuesDeGuardar;
    }

    public void setLimpiarDespuesDeGuardar(boolean limpiarDespuesDeGuardar) {
        this.limpiarDespuesDeGuardar = limpiarDespuesDeGuardar;
    }

    public boolean estaHabilitado() {
        return habilitado;
    }

    public Object getControl() {
        return control;
    }

    public void setControl(Object control) {
        this.control = control;
    }

}
