/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades.Eventos;

import Actividades.ActividadesPorPeriodo;
import Actividades.BotonesDeProgreso;
import Actividades.Colores;
import Actividades.Opciones;
import Actividades.PanelActividades;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoMouseMotion implements MouseMotionListener {

    private PanelActividades panRef;
    private boolean seRealizoAccion;

    public EventoMouseMotion(PanelActividades panRef) {
        this.panRef = panRef;
        seRealizoAccion = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        seRealizoAccion = false;
        movimientoSobreActividadesPorPeriodo(e);
        panRef.Actualizar();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        seRealizoAccion = false;
        movimientoSobreBotonesDesplazamiento(e);
        movimientoSobreMenuDeOpciones(e);
        movimientoSobreActividadesPorPeriodo(e);
        panRef.Actualizar();
    }

    private void movimientoSobreBotonesDesplazamiento(MouseEvent e) {
        for (BotonesDeProgreso boton : panRef.botones) {
            if (boton.estaSobreElemento(e.getPoint())) {
                panRef.cursor = Cursor.HAND_CURSOR;
                boton.setColorFondo(Colores.DARK_SUCCESS);
                seRealizoAccion = true;
                break;
            } else {
                panRef.cursor = Cursor.DEFAULT_CURSOR;
                boton.setColorFondo(Colores.SUCCESS);
            }
        }
    }

    private void movimientoSobreActividadesPorPeriodo(MouseEvent e) {
        if (seRealizoAccion) {
            return;
        }
        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadPorPeriodo.estaSobreElemento(e.getPoint())) {
                String nombreActividad = actividadPorPeriodo.getActividad().getDescripcion();
                String mes = actividadPorPeriodo.getPeriodo().getDescripcionMes();
                String anio = "" + actividadPorPeriodo.getPeriodo().getAnio();
                String semana = actividadPorPeriodo.getNombreSemana();
                panRef.setToolTipText(
                        "<html><p><b>"
                        + nombreActividad + "</b><br>"
                        + "en la <b>" + semana + " semana</b> del mes de <b>" + mes.toLowerCase() + "</b>"
                        + " del <b>" + anio + "</b></p></html>"
                );

                if (actividadPorPeriodo.isSeleccionado()) {
                    reinicarActividadesPorPeriodoMenos(actividadPorPeriodo);
                    continue;
                }
                actividadPorPeriodo.setColorFondo(Colores.DARK_FADED);
                reinicarActividadesPorPeriodoMenos(actividadPorPeriodo);

                seRealizoAccion = true;
                break;
            }
        }
    }

    private void reinicarActividadesPorPeriodoMenos(ActividadesPorPeriodo app) {
        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (!actividadPorPeriodo.Igual(app)) {
                if (!actividadPorPeriodo.isSeleccionado()) {
                    actividadPorPeriodo.setColorFondo(Colores.FADED);
                }
            }
        }
    }

    private void movimientoSobreMenuDeOpciones(MouseEvent e) {
        if (seRealizoAccion) {
            return;
        }
        for (Opciones opcion : panRef.menu.getOpciones()) {
            if (opcion.estaSobreElemento(e.getPoint())) {
                panRef.cursor = Cursor.HAND_CURSOR;
                opcion.setColorFondo(Colores.DARK_FADED);
                reiniciarOpcionesMenos(opcion);
                seRealizoAccion = true;
                break;
            }
        }
    }

    private void reiniciarOpcionesMenos(Opciones opc) {
        for (Opciones opcion : panRef.menu.getOpciones()) {
            if (!opcion.Igual(opc)) {
                opcion.setColorFondo(Colores.FADED);
            }
        }
    }

}
