/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Charts.Eventos.EventoMouseMotion;
import Charts.Eventos.EventoVentana;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author DOLFHANDLER
 */
public class Panel extends JPanel {

    private Object datos;
    private JPanel panelContenedor;
    private int sepX;
    private int sepY;
    private int margenX;
    private int margenY;
    private int sepLogicaY;
    private ArrayList<Color> colores;
    public ArrayList<Punto> listaDePuntos;
    private final int anchoLinea = 3;
    private int maximo;
    private int minimo;
    private int anchoTextoMaximo;
    private String nombreEjeX;
    private String nombreEjeY;
    private FontMetrics fm;
    private String titulo;
    public int band = 0;

    public Panel(Object datos, JPanel contenedor) {
        titulo = "HISTORICO PESOS DEL ANIMAL";
        this.datos = datos;
        panelContenedor = contenedor;
        this.sepX = 1;
        this.sepY = 1;
        this.sepLogicaY = 0;
        this.margenX = 100;
        this.margenY = 100;
        listaDePuntos = new ArrayList<>();
        colores = new ArrayList<>();
        cargarColores();
        cargarMaximoYMinimo();
        cargarNombreEjes();

        panelContenedor.addComponentListener(new EventoVentana(this));
        this.addMouseMotionListener(new EventoMouseMotion(this));
    }

    private void cargarNombreEjes() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        nombreEjeX = data.get(0).get(0)[0].toString();
        nombreEjeY = data.get(0).get(0)[1].toString();
    }

    private void cargarMaximoYMinimo() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        maximo = getMaximoValor(data);
        minimo = getMinimoValor(data);
    }

    private void cargarColores() {
        colores.add(new Color(231, 76, 60));
        colores.add(new Color(52, 152, 219));
        colores.add(new Color(39, 174, 96));
        colores.add(new Color(142, 68, 173));
        colores.add(new Color(241, 196, 15));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g.setFont(new Font("Calibri", Font.PLAIN, 14));
        pintarFondo(g2d);
        pintarEjeX(g2d);
        pintarEjeY(g2d);
        pintarEtiquetasEjeX(g2d);
        pintarEtiquetasEjeY(g2d);
        pintarTituloGrafica(g2d);
        pintarTituloEjeX(g2d);
        pintarTituloEjeY(g2d);
        if (band == 0) {
            band = 1;
            cargarListaDePuntos();
        }
        pintarPuntos(g2d);
        pintarRectas(g2d);

        g2d.dispose();
    }

    public void pintarPunto(Punto p) {
        p.pintar((Graphics2D) this.getGraphics());
    }

    public void Actualizar() {
        repaint();
    }

    private void pintarFondo(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    private void pintarEjeX(Graphics2D g) {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;

        anchoTextoMaximo = getMaximoAnchoTexto(data, g);
        int margen = margenX + anchoTextoMaximo;
        g.setColor(Color.gray);
        g.fillRect(margen, this.getHeight() - margenY, this.getWidth() - margen * 2, anchoLinea);//eje x
    }

    private void pintarEjeY(Graphics2D g) {

        int espacioDisponibleX = this.getWidth() - margenX * 2;
        int espacioDisponibleY = this.getHeight() - margenY * 2;
        int separacion = Math.round(espacioDisponibleY / (maximo / minimo + 1));
        sepY = separacion;

        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        anchoTextoMaximo = getMaximoAnchoTexto(data, g);
        int margen = margenX + anchoTextoMaximo;

        g.setColor(Color.lightGray);
        int a = 0;
        for (int i = 0; i < maximo + minimo; i += minimo) {
            g.drawRect(margen, getTY(a * sepY), espacioDisponibleX - (anchoTextoMaximo * 2), 1);
            a++;
        }
    }

    private int getMaximoAnchoTexto(ArrayList<ArrayList<Object[]>> data, Graphics2D g) {
        int maximo = 0;
        FontMetrics fm = g.getFontMetrics();
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                String texto = "" + data.get(a).get(i)[1];
                if (maximo <= fm.stringWidth(texto)) {
                    maximo = fm.stringWidth(texto);
                }
            }
        }
        return maximo;
    }

    private void pintarEtiquetasEjeX(Graphics2D g) {
        int espacioDisponible = this.getWidth() - margenX * 2;
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        int cantidadDeDatos = data.get(0).size();
        int separacion = Math.round(espacioDisponible / cantidadDeDatos);
        sepX = separacion;

        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a = 1;
        for (int i = 0; a < data.get(0).size(); i += sepX) {
            Rectangle2D recTexto = fm.getStringBounds(data.get(0).get(a)[0].toString(), g);
            int anchoTexto = (int) recTexto.getWidth();
            int altoTexto = (int) recTexto.getHeight();
            g.drawString(
                    data.get(0).get(a)[0].toString(),
                    getTX(i + sepX) - (anchoTexto / 2),
                    this.getHeight() - margenY + altoTexto
            );
            a++;
        }
    }

    private void pintarEtiquetasEjeY(Graphics2D g) {
        int espacioDisponible = this.getHeight() - margenY * 2;
        int separacion = Math.round(espacioDisponible / (maximo / minimo + 1));
        sepY = separacion;
        sepLogicaY = minimo;

        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a = 0;
        for (int i = 0; i < maximo + minimo; i += minimo) {
            g.drawString(
                    "" + i,
                    margenX - (fm.stringWidth("" + i)),
                    getTY(a * separacion)
            );
            a++;
        }
    }

    private int getMaximoValor(ArrayList<ArrayList<Object[]>> data) {
        int max = 0;
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                if (max <= Integer.parseInt(data.get(a).get(i)[1].toString())) {
                    max = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
            }
        }
        return max;
    }

    private int getMinimoValor(ArrayList<ArrayList<Object[]>> data) {
        int min = 0;
        for (int a = 0; a < data.size(); a++) {
            for (int i = 1; i < data.get(a).size(); i++) {
                if (i == 1) {
                    min = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
                if (min >= Integer.parseInt(data.get(a).get(i)[1].toString())) {
                    min = Integer.parseInt(data.get(a).get(i)[1].toString());
                }
            }
        }
        return min;
    }

    private int getTY(int valor) {
        return this.getHeight() - valor - margenY;
    }

    private int getTX(int valor) {
        return valor + margenX;
    }

    private Color generarColor() {
        Random aleatorio = new Random();
        float rc = aleatorio.nextFloat();
        float gc = aleatorio.nextFloat();
        float bc = aleatorio.nextFloat();
        return new Color(rc, gc, bc);
    }

    private void cargarListaDePuntos() {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        listaDePuntos = new ArrayList<>();
        sepLogicaY = minimo;

        for (int a = 0; a < data.size(); a++) {
            Color color = colores.get(a);
            for (int i = 1; i < data.get(a).size(); i++) {
                int y = Integer.parseInt(data.get(a).get(i)[1].toString());
                int ty = Math.round(y * sepY / sepLogicaY);

                listaDePuntos.add(new Punto(
                        data.get(a).get(0)[0].toString(),
                        data.get(a).get(0)[1].toString(),
                        data.get(a).get(i)[0].toString(),
                        data.get(a).get(i)[1].toString(),
                        getTX(sepX * i),
                        getTY(ty),
                        10, 10,
                        color,
                        color)
                );
            }
        }
    }

    private void pintarPuntos(Graphics2D g) {
        for (int i = 0; i < listaDePuntos.size(); i++) {
            listaDePuntos.get(i).pintar(g);
        }
    }

    private void pintarRectas(Graphics2D g) {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;

        for (int i = 0; i < listaDePuntos.size() - 1; i++) {
            if (!((i + 1) % (data.get(0).size() - 1) == 0) && 1 > 0) {
                unirPuntos(g, listaDePuntos.get(i), listaDePuntos.get(i + 1));
            }
        }
    }

    private void unirPuntos(Graphics2D g, Punto p0, Punto p1) {
        g.setColor(p0.getColorTexto());
        for (int i = 0; i < anchoLinea / 2; i++) {
            g.drawLine(p0.getX(), p0.getY() - (i + 1), p1.getX(), p1.getY() - (i + 1));
            g.drawLine(p0.getX(), p0.getY() + (i + 1), p1.getX(), p1.getY() + (i + 1));
        }
        g.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

    private void pintarTituloEjeX(Graphics2D g) {
        int espacioDisponibleX = this.getWidth() - margenX * 2;
        int espacioDisponibleY = this.getHeight() - margenY * 2;
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(nombreEjeY);
        int x = margenX - anchoTextoMaximo - anchoTexto;
        int centroY = getTY(espacioDisponibleY / 2)-(anchoTexto/2);

        g.setColor(Color.darkGray);
        drawRotate(g, x, centroY, 90, nombreEjeY);
    }

    private void pintarTituloEjeY(Graphics2D g) {
        int espacioDisponibleY = this.getHeight() - margenY;
        int anchoTexto = fm.stringWidth(nombreEjeX);
        int y = espacioDisponibleY + margenY / 2;
        int x = this.getWidth() / 2 - anchoTexto / 2;

        g.setColor(Color.darkGray);
        g.drawString(nombreEjeX, x, y);
    }

    private void pintarTituloGrafica(Graphics2D g) {
        fm = g.getFontMetrics();
        int anchoTexto = fm.stringWidth(titulo);
        int y = margenY / 2;
        int x = this.getWidth() / 2 - anchoTexto / 2;

        g.setColor(Color.darkGray);
        g.drawString(titulo, x, y);
    }

    public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {
        g2d.translate((float) x, (float) y);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawString(text, 0, 0);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-(float) x, -(float) y);
    }
}
