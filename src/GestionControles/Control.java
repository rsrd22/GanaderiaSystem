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
    }

    public Control() {
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
