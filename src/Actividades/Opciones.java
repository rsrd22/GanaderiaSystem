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
public class Opciones extends Rectangulo implements IGraficos {

    private FontMetrics fm;
    private String descripcion;
    private String id;
    private Color colorEstado;

    public Opciones(String descripcion, String id, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto, Color colorEstado) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.descripcion = descripcion;
        this.id = id;
        this.colorEstado = colorEstado;
    }
    
    public boolean Igual(Opciones opcion){
        return this.id.equals(opcion.getId());
    }

    public Color getColorEstado() {
        return colorEstado;
    }

    public void setColorEstado(Color colorEstado) {
        this.colorEstado = colorEstado;
    }

    public boolean estaSobreElemento(Point punto) {
        return super.detectarActividadSobreElemento(punto);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return id;
    }

    @Override
    public void pintar(Graphics2D g) {
        fm = g.getFontMetrics();
        Rectangle2D recTexto = fm.getStringBounds(descripcion, g);
        int anchoTexto = (int) recTexto.getWidth();
        int altoTexto = (int) recTexto.getHeight();

        int ny = alto / 2 - altoTexto / 2;
        int nx = 5;

        g.setColor(colorFondo);
        g.fillRect(x, y, ancho, alto);
        g.setColor(colorTexto);
        g.drawString(descripcion, nx + x, y - ny + alto);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, ancho, alto);
    }
}
