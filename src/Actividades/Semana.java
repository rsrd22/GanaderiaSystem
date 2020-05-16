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
public class Semana extends Rectangulo implements IGraficos {

    private FontMetrics fm;
    private int id;
    private String descripcion;

    public Semana(int id, String descripcion, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void pintar(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(descripcion);

        int nx = ancho / 2 - anchoTexto / 2;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString(descripcion, x + nx, y + alto - 5);
        g.drawRect(x, y, ancho, alto);
    }

}
