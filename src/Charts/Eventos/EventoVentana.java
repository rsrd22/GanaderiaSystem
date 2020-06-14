/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts.Eventos;

import Charts.Panel;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoVentana implements ComponentListener {

    private Panel panel;

    public EventoVentana(Panel panelRef) {
        panel = panelRef;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        panel.setSize(e.getComponent().getSize());
        panel.Actualizar();
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

}
