package Charts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author DOLFHANDLER
 */
public class Punto extends Rectangulo implements IGraficos{
    private String valorX;
    private String valorY;
    private String EtiquetaEjeY;
    private String EtiquetaEjeX;

    public Punto(String EtiquetaEjeX, String EtiquetaEjeY,String valorX, String valorY, int x, int y, int ancho, int alto, Color colorFondo, Color colorTexto) {
        super(x, y, ancho, alto, colorFondo, colorTexto);
        this.valorX = valorX;
        this.valorY = valorY;
        this.EtiquetaEjeY = EtiquetaEjeY;
        this.EtiquetaEjeX = EtiquetaEjeX;
    }

    @Override
    public void pintar(Graphics2D g) {
        g.setColor(colorFondo);
        g.fillOval(x-(ancho/2), y-alto/2, ancho, alto);
        System.out.println("--("+x+","+y+")");
    }

    public String getEtiquetaEjeY() {
        return EtiquetaEjeY;
    }

    public void setEtiquetaEjeY(String EtiquetaEjeY) {
        this.EtiquetaEjeY = EtiquetaEjeY;
    }

    public String getEtiquetaEjeX() {
        return EtiquetaEjeX;
    }

    public void setEtiquetaEjeX(String EtiquetaEjeX) {
        this.EtiquetaEjeX = EtiquetaEjeX;
    }
    
    public String getValorX() {
        return valorX;
    }

    public void setValorX(String valorX) {
        this.valorX = valorX;
    }

    public String getValorY() {
        return valorY;
    }

    public void setValorY(String valorY) {
        this.valorY = valorY;
    }

    public boolean estaSobreElemento(Point point) {
        return detectarActividadSobreElemento(point);
    }
}
