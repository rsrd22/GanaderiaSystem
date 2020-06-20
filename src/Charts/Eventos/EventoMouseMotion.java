/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.Eventos;

import Charts.Panel;
import Charts.Punto;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoMouseMotion implements MouseMotionListener {

    private Panel panel;

    public EventoMouseMotion(Panel panRef) {
        this.panel = panRef;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        actividadSobreElPunto(e);
    }

    private void actividadSobreElPunto(MouseEvent e) {
        for (Punto punto : panel.listaDePuntos) {
            if (punto.estaSobreElemento(e.getPoint())) {
                panel.setToolTipText(
                        "<html><p>"
                        + "<b>" + punto.getEtiquetaEjeX() + ": </b>"
                        + punto.getValorX()
                        + "<br>"
                        + "<b>" + punto.getEtiquetaEjeY() + ": </b>"
                        + punto.getValorY() + " Kg"
                        + "</p></html>"
                );
                return;
            }
        }
    }

}
