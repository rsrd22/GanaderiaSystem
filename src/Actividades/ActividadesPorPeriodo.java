/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import Utilidades.Estado;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author DOLFHANDLER
 */
public class ActividadesPorPeriodo extends Rectangulo implements IGraficos {

    private FontMetrics fm;
    private Actividad actividad;
    private Periodo periodo;
    private Semana semana;
    private String estado;
    private boolean seleccionado;
    private String descripcion;

    /**
     * constructor
     *
     * @param actividad
     * @param periodo
     * @param semana
     * @param estado
     * @param seleccionado
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @param colorFondo
     * @param colorTexto
     */
    public ActividadesPorPeriodo(Actividad actividad, Periodo periodo, Semana semana, String estado, boolean seleccionado, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.fm = fm;
        this.actividad = actividad;
        this.periodo = periodo;
        this.semana = semana;
        this.estado = estado;
        this.seleccionado = seleccionado;
        descripcion = "x";
    }

    public ActividadesPorPeriodo() {
        super(0, 0, 0, 0);
    }

    @Override
    public void pintar(Graphics2D g) {
        fm = g.getFontMetrics();
        Rectangle2D recTexto = fm.getStringBounds(descripcion, g);
        int anchoTexto = (int) recTexto.getWidth();
        int altoTexto = (int) recTexto.getHeight();

        int ny = alto / 2 - altoTexto / 2;
        int nx = ancho / 2 - anchoTexto / 2;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        if (seleccionado) {
            g.drawString(descripcion, x + nx, y - ny + alto);
        }
        g.drawRect(x, y, ancho, alto);
    }

    public Semana getSemana() {
        return semana;
    }

    public String getNombreSemana() {
        switch (semana.getDescripcion()) {
            case "1":
                return "primera";
            case "2":
                return "segunda";
            case "3":
                return "tercera";
            case "4":
                return "cuarta";
            default:
                return "primera";
        }
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

    public boolean estaSobreElemento(Point p) {
        return detectarActividadSobreElemento(p);
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean Igual(ActividadesPorPeriodo app) {
        return (this.periodo.getAnio() == app.getPeriodo().getAnio()
                && this.periodo.getMes() == app.getPeriodo().getMes()
                && this.semana.getId() == app.getSemana().getId()
                && this.actividad.getId().equals(app.getActividad().getId()));
    }

}
