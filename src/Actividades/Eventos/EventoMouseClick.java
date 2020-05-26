/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades.Eventos;

import Actividades.ActividadesPorPeriodo;
import Actividades.Boton;
import Actividades.BotonesDeProgreso;
import Actividades.Colores;
import Actividades.Opciones;
import Actividades.PanelActividades;
import Actividades.Periodo;
import Utilidades.DireccionPintado;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class EventoMouseClick implements MouseListener {

    private PanelActividades panRef;
    private int x, y, nx, ny;
    private boolean seRealizoAccion;

    public EventoMouseClick(PanelActividades panRef) {
        this.panRef = panRef;
        x = 0;
        y = 0;
        nx = 0;
        ny = 0;
        seRealizoAccion = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                clickIzquierdo(e);
                break;
            case MouseEvent.BUTTON2:
                clickCentral(e);
                break;
            case MouseEvent.BUTTON3:
                clickDerecho(e);
                break;
            default:
        }
        panRef.Actualizar();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        panRef.arrastreDelMouse = true;
        panRef.cursor = Cursor.MOVE_CURSOR;
        iniciarArrastreDelMouse(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        finalizarArrastreDelMouse(e);
        panRef.cursor = Cursor.DEFAULT_CURSOR;
        panRef.arrastreDelMouse = false;
        panRef.Actualizar();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void clickIzquierdo(MouseEvent e) {
        if (e.getClickCount() == 2) {
            dobleClick(e);
        } else {
            click(e);
        }
    }

    private void dobleClick(MouseEvent e) {

    }

    private void click(MouseEvent e) {
        panRef.clickDerechoPresionado = false;
        seRealizoAccion = false;
        clickBotonesDesplazamiento(e);
        clickOpcionDelMenu(e);
        clickActividadesPorPeriodo(e);
    }

    private void clickBotonesDesplazamiento(MouseEvent e) {
        for (BotonesDeProgreso boton : panRef.botones) {
            if (boton.estaSobreElemento(e.getPoint())) {
                switch (boton.getId()) {
                    case Boton.ANTERIOR:
                        periodoAnterior();
                        seRealizoAccion = true;
                        return;
                    case Boton.SIGUIENTE:
                        periodoSiguiente();
                        seRealizoAccion = true;
                        return;
                }
            }
        }
    }

    private void clickOpcionDelMenu(MouseEvent e) {
        if (seRealizoAccion) {
            return;
        }
        for (Opciones opcion : panRef.menu.getOpciones()) {
            if (opcion.estaSobreElemento(e.getPoint())) {

                if (!opcion.getId().equals("99")) {
                    establecerPeriodoSeleccionado(panRef.menu.getPunto(), opcion);
                    seRealizoAccion = true;
                    break;
                } else {
                    quitarPeriodoSeleccionado(panRef.menu.getPunto(), opcion);
                    seRealizoAccion = true;
                    break;
                }
            }
        }
    }

    private void establecerPeriodoSeleccionado(Point punto, Opciones opcion) {
        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadPorPeriodo.estaSobreElemento(punto)) {
                actividadPorPeriodo.setSeleccionado(true);
                actividadPorPeriodo.setEstado(opcion.getId());
                actividadPorPeriodo.setColorFondo(opcion.getColorEstado());
                return;
            }
        }
    }

    private void quitarPeriodoSeleccionado(Point punto, Opciones opcion) {
        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadPorPeriodo.estaSobreElemento(punto)) {
                actividadPorPeriodo.setSeleccionado(false);
                actividadPorPeriodo.setEstado("-1");
                actividadPorPeriodo.setColorFondo(opcion.getColorEstado());
                return;
            }
        }
    }

    private void clickActividadesPorPeriodo(MouseEvent e) {
        if (seRealizoAccion) {
            return;
        }
        for (ActividadesPorPeriodo actividadesPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadesPorPeriodo.estaSobreElemento(e.getPoint())) {
                actividadesPorPeriodo.setSeleccionado(true);
                seRealizoAccion = true;
                return;
            }
        }
    }

    private void clickCentral(MouseEvent e) {
        panRef.clickDerechoPresionado = false;
        panRef.Actualizar();
    }

    private void clickDerecho(MouseEvent e) {
        for (ActividadesPorPeriodo actividadesPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadesPorPeriodo.estaSobreElemento(e.getPoint())) {
                int altoContenedor = panRef.getSize().height;
                int cantidadOpciones = panRef.menu.getOpciones().size();
                int altoOpcion = panRef.menu.getOpciones().get(0).getAlto();
                double altoMenuMasPuntoDePartida = e.getPoint().getY() + cantidadOpciones * altoOpcion;
                int direccion = altoMenuMasPuntoDePartida > altoContenedor ? DireccionPintado.ARRIBA : DireccionPintado.ABAJO;
                panRef.menu.setPunto(e.getPoint(), direccion);

                break;
            }
        }
        panRef.clickDerechoPresionado = true;
    }

    private void periodoAnterior() {
        Periodo periodo = panRef.periodos.get(0);

        int anio = periodo.getAnio();
        int mes = periodo.getMes();

        anio = (mes == 1 ? anio - 1 : anio);
        mes = (mes == 1 ? 12 : mes - 1);

        panRef.limpiarPeriodos();
        panRef.cargarPeriodos(panRef.xinicial, panRef.yinicial, anio, mes, 4);
    }

    private void periodoSiguiente() {
        Periodo periodo = panRef.periodos.get(0);

        int anio = periodo.getAnio();
        int mes = periodo.getMes();

        anio = (mes == 12 ? anio + 1 : anio);
        mes = (mes == 12 ? 1 : mes + 1);

        panRef.limpiarPeriodos();
        panRef.cargarPeriodos(panRef.xinicial, panRef.yinicial, anio, mes, 4);
    }

    private void iniciarArrastreDelMouse(MouseEvent e) {
        x = e.getX();
        nx = 0;
        y = e.getY();
        ny = 0;
    }

    private void finalizarArrastreDelMouse(MouseEvent e) {
        if (!panRef.clickDerechoPresionado && panRef.arrastreDelMouse) {
            nx = e.getX();
            ny = e.getY();
            if (ny - y >= -18 && ny - y <= 18) {
                establecerSeleccionado();
            } else {
                JOptionPane.showMessageDialog(panRef, "la operacion a realizar debe ser sobre la misma actividad");
            }
        }
    }

    private void establecerSeleccionado() {
        Color color = null;
        String estado = "-1";
        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadPorPeriodo.estaSobreElemento(new Point(x, y))) {
                actividadPorPeriodo.setSeleccionado(false);
                estado = actividadPorPeriodo.getEstado();
                actividadPorPeriodo.setEstado("-1");
                color = actividadPorPeriodo.getColorFondo();
                actividadPorPeriodo.setColorFondo(Colores.FADED);
                break;
            }
        }

        for (ActividadesPorPeriodo actividadPorPeriodo : panRef.actividadesPeriodos) {
            if (actividadPorPeriodo.estaSobreElemento(new Point(nx, ny))) {
                actividadPorPeriodo.setSeleccionado(true);
                actividadPorPeriodo.setEstado(estado);
                actividadPorPeriodo.setColorFondo(color);
                break;
            }
        }

        x = 0;
        y = 0;
        nx = 0;
        ny = 0;
    }

}
