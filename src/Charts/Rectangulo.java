package Charts;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author DOLFHANDLER
 */
public class Rectangulo {

    protected int x, y, ancho, alto;
    protected Color colorFondo, colorTexto;

    public Rectangulo(int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = colorFondo;
        this.colorTexto = colorTexto;
    }

    public Rectangulo(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.colorFondo = Color.white;
        this.colorTexto = Color.black;
    }

    protected boolean detectarActividadSobreElemento(Point p) {
        return p.x > this.x
                && p.x < this.x + this.ancho
                && p.y > this.y
                && p.y < this.y + this.alto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public Color getColorFondo() {
        return colorFondo;
    }

    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    public Color getColorTexto() {
        return colorTexto;
    }

    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

}
