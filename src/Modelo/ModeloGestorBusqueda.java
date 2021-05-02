/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JPanel;

/**
 *
 * @author MERRY
 */
public class ModeloGestorBusqueda {

    private Object panelsolicitante;
    private String keyconsulta;
    private int opcion;
    private String param;

    public ModeloGestorBusqueda(JPanel panelsolicitante, String keyconsulta, int opcion) {
        this.panelsolicitante = panelsolicitante;
        this.keyconsulta = keyconsulta;
        this.opcion = opcion;
        this.param = "";
    }
    public ModeloGestorBusqueda(JPanel panelsolicitante, String keyconsulta, int opcion, String param) {
        this.panelsolicitante = panelsolicitante;
        this.keyconsulta = keyconsulta;
        this.opcion = opcion;
        this.param = param;
    }

    public Object getPanelsolicitante() {
        return panelsolicitante;
    }

    public void setPanelsolicitante(Object panelsolicitante) {
        this.panelsolicitante = panelsolicitante;
    }

    public String getKeyconsulta() {
        return keyconsulta;
    }

    public void setKeyconsulta(String keyconsulta) {
        this.keyconsulta = keyconsulta;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opc) {
        this.opcion = opc;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    
}
