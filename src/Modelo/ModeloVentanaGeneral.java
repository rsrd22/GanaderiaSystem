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
public class ModeloVentanaGeneral {

    private Object panelPadre;
    private Object panelHijo;
    private int opcion;
    private Object modeloDatos;
    private Object frameVentana;
    private int fila;

    public ModeloVentanaGeneral() {
        panelPadre = new JPanel();
        panelHijo = new JPanel();
        opcion = -1;
        modeloDatos = new Object();
        frameVentana = new Object();
        fila = -1;
    }

    public ModeloVentanaGeneral(Object panelPadre, Object panelHijo, int opcion) {
        this.panelPadre = panelPadre;
        this.panelHijo = panelHijo;
        this.opcion = opcion;
        this.modeloDatos = null;
        this.frameVentana = null;
        this.fila = -2;
    }
/**
 * contructor #3
 * 
 * @param panelPadre
 * @param panelHijo
 * @param opcion
 * @param modeloDatos 
 */
    public ModeloVentanaGeneral(Object panelPadre, Object panelHijo, int opcion, Object modeloDatos) {
        this.panelPadre = panelPadre;
        this.panelHijo = panelHijo;
        this.opcion = opcion;
        this.modeloDatos = modeloDatos;
        this.frameVentana = null;
        this.fila = -2;
    }

    public Object getPanelPadre() {
        return panelPadre;
    }

    public void setPanelPadre(Object panelPadre) {
        this.panelPadre = panelPadre;
    }

    public Object getPanelHijo() {
        return panelHijo;
    }

    public void setPanelHijo(Object panelHijo) {
        this.panelHijo = panelHijo;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public Object getModeloDatos() {
        return modeloDatos;
    }

    public void setModeloDatos(Object modeloDatos) {
        this.modeloDatos = modeloDatos;
    }

    public Object getFrameVentana() {
        return frameVentana;
    }

    public void setFrameVentana(Object frameVentana) {
        this.frameVentana = frameVentana;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

}
