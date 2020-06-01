/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author DOLFHANDLER
 */
public class BotonMasOpciones extends Rectangulo implements IGraficos {

    private FontMetrics fm;
    private int id;
    private String texto;

    public BotonMasOpciones(int id, int x, int y, int radio, String texto, Color colorDeFondo, Color colorDelTexto) {
        super(x, y, radio, radio, colorDeFondo, colorDelTexto);
        this.id = id;
        this.texto = texto;
    }

    @Override
    public void pintar(Graphics2D g) {
        fm = g.getFontMetrics();
        Rectangle2D recTexto = fm.getStringBounds(texto, g);
        int anchoTexto = (int) recTexto.getWidth();
        int altoTexto = (int) recTexto.getHeight();

        int nx = ancho / 2 - anchoTexto / 2;
        int ny = alto / 2 - altoTexto / 2;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString(texto, x + nx, y - ny + alto);
    }

    public boolean estaSobreElemento(Point p) {
        return detectarActividadSobreElemento(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}
