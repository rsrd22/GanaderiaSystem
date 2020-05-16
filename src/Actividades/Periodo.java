/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author DOLFHANDLER
 */
public class Periodo extends Rectangulo implements IGraficos {

    private int id;
    private int anio;
    private int mes;
    private String descripcionMes;
    private Semana[] semanas;
    private FontMetrics fm;
    private boolean periodoDeInicio;

    public Periodo() {
        super(0, 0, 0, 0, null, null);
        semanas = new Semana[4];
        this.id = 0;
        this.anio = 0;
        this.mes = 0;
        this.descripcionMes = "";
        this.periodoDeInicio = false;
    }

    /**
     * Constructor de la clase
     *
     * @param id
     * @param anio
     * @param mes
     * @param descripcionMes
     * @param periodoDeInicio
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @param colorFondo
     * @param colorTexto
     */
    public Periodo(int id, int anio, int mes, String descripcionMes, boolean periodoDeInicio, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        semanas = new Semana[4];
        this.id = id;
        this.anio = anio;
        this.mes = mes;
        this.descripcionMes = descripcionMes.toUpperCase();
        this.periodoDeInicio = periodoDeInicio;
        cargarSemanas(x, y + 20);
    }

    private void cargarSemanas(int xini, int yini) {
        int ancho = (int) this.ancho / 4;
        for (int i = 0; i < semanas.length; i++) {
            semanas[i] = new Semana(
                    i + 1,
                    "" + (i + 1),
                    xini + i * ancho,//x
                    yini,//y
                    ancho,//ancho
                    20,//alto
                    Colores.SUCCESS,
                    Colores.TEXT_SUCCESS
            );
        }
    }

    @Override
    public void pintar(Graphics2D g) {
        pintarAnio(g);
        pintarMes(g);
        pintarSemanas(g);
    }

    private void pintarAnio(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth("" + anio);

        y -= 20;
        int nx = ancho / 2 - anchoTexto / 2;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString("" + anio, x + nx, y + alto - 5);
        g.drawRect(x, y, ancho, alto);
    }

    private void pintarMes(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(descripcionMes);

        y += 20;
        int nx = ancho / 2 - anchoTexto / 2;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString(descripcionMes, x + nx, y + alto - 5);
        g.drawRect(x, y, ancho, alto);
    }

    private void pintarSemanas(Graphics2D g) {
        for (Semana semana : semanas) {
            semana.pintar(g);
        }
    }

    public Semana[] getSemanas() {
        return semanas;
    }

    public void setSemanas(Semana[] semanas) {
        this.semanas = semanas;
    }

    public boolean isPeriodoDeInicio() {
        return periodoDeInicio;
    }

    public void setPeriodoDeInicio(boolean periodoDeInicio) {
        this.periodoDeInicio = periodoDeInicio;
    }

    public String getDescripcionMes() {
        return descripcionMes.toUpperCase();
    }

    public void setDescripcionMes(String descripcionMes) {
        this.descripcionMes = descripcionMes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

}
