/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades.Eventos;

import Actividades.PanelActividades;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoVentana implements ComponentListener {

    private PanelActividades panRef;

    public EventoVentana(PanelActividades panRef) {
        this.panRef = panRef;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        Redimension(e);
        panRef.Actualizar();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    private void Redimension(ComponentEvent e) {
        int an = 50, margen = 50;
        panRef.btnMasOpciones.setX(e.getComponent().getWidth() - panRef.vp.desplazamiento - margen - an);
        panRef.btnMasOpciones.setY(e.getComponent().getHeight() - 52 - margen - an);
    }

}
