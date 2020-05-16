/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author DOLFHANDLER
 */
public class Actividad extends Rectangulo implements IGraficos {

    private FontMetrics fm;
    private String id;
    private String descripcion;

    /**
     * contructor de la clase
     *
     * @param id
     * @param descripcion
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @param colorFondo
     * @param colorTexto
     */
    public Actividad(String id, String descripcion, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.id = id;
        this.descripcion = descripcion;
    }

    @Override
    public void pintar(Graphics2D g) {
        fm = g.getFontMetrics();
        Rectangle2D recTexto = fm.getStringBounds(descripcion, g);
        int anchoTexto = (int) recTexto.getWidth();
        int altoTexto = (int) recTexto.getHeight();

        int ny = alto / 2 - altoTexto / 2;
        int nx = ancho - anchoTexto - 5;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString(descripcion, nx, y - ny + alto);
        g.drawRect(x, y, ancho, alto);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
