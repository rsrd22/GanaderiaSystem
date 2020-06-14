/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Charts;

import Charts.Eventos.EventoMouseMotion;
import Charts.Eventos.EventoVentana;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

    public Panel(Object datos, JPanel contenedor) {
        this.datos = datos;
        panelContenedor = contenedor;
        this.sepX = 1;
        this.sepY = 1;
        this.sepLogicaY = 0;
        this.margenX = 50;
        this.margenY = 50;
        listaDePuntos = new ArrayList<>();
        colores = new ArrayList<>();
        cargarColores();
        cargarDatos();
        cargarMaximoYMinimo();
        panelContenedor.addComponentListener(new EventoVentana(this));
        this.addMouseMotionListener(new EventoMouseMotion(this));
    }

    private void cargarDatos(){
//        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
//        data = (ArrayList<ArrayList<Object[]>>) datos;
//        for (int i = 0; i < data.size(); i++) {
//            data.get(i).add(1,new Object[]{"0",1});
//        }
    }

    private void cargarMaximoYMinimo(){
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        maximo=getMaximoValor(data);
        minimo=getMinimoValor(data);
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

        pintarFondo(g2d);
        pintarEjeX(g2d);
        pintarEtiquetasEjeX(g2d);
        pintarEtiquetasEjeY(g2d);
        pintarPuntos(g2d);
        pintarRectas(g2d);

        g2d.dispose();
    }
    
    public void pintarPunto(Punto p){
        p.pintar((Graphics2D)this.getGraphics());
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

        int anchoMax = getMaximoAnchoTexto(data, g);
        margenX = anchoMax + 10;
        g.setColor(Color.gray);
        g.fillRect(margenX, 50, anchoLinea, this.getHeight() - margenY * 2);//eje x
        g.fillRect(margenX, this.getHeight() - margenY, this.getWidth() - margenX * 2, anchoLinea);//eje y
    }

    private void pintarEjeY(Graphics2D g) {

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
        int espacioDisponible = this.getWidth() - margenX*2;
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        int cantidadDeDatos = data.get(0).size();
        int separacion = Math.round(espacioDisponible / cantidadDeDatos);
        sepX = separacion;

        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a = 1;
        for (int i = 0; a<data.get(0).size(); i+=sepX) {
            g.drawString(
                    data.get(0).get(a)[0].toString(),
                    getTX(i+sepX) - (fm.stringWidth(data.get(0).get(a)[0].toString()) / 2),
                    this.getHeight() - 30
            );
            a++;
        }
    }

    private void pintarEtiquetasEjeY(Graphics2D g) {
        int espacioDisponible = this.getHeight() - margenY*2;
        int separacion = Math.round(espacioDisponible / (maximo / minimo + 1));
        sepY = separacion;
        sepLogicaY = minimo;
        System.out.println("max " + maximo);
        System.out.println("min " + minimo);
        System.out.println("separacion: " + sepY);

        g.setColor(Color.darkGray);
        FontMetrics fm = g.getFontMetrics();
        int a=0;
        for (int i = 0; i < maximo+minimo; i+=minimo) {
            g.drawString(
                    "" + i,
                    5,
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

    private void pintarPuntos(Graphics2D g) {
        ArrayList<ArrayList<Object[]>> data = new ArrayList<>();
        data = (ArrayList<ArrayList<Object[]>>) datos;
        listaDePuntos = new ArrayList<>();

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
                        8, 8,
                        color,
                        color)
                );
            }
        }

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
        for (int i = 0; i < anchoLinea/2; i++) {
            g.drawLine(p0.getX(), p0.getY()-(i+1), p1.getX(), p1.getY()-(i+1));
            g.drawLine(p0.getX(), p0.getY()+(i+1), p1.getX(), p1.getY()+(i+1));
        }
        g.drawLine(p0.getX(), p0.getY(), p1.getX(), p1.getY());
    }

}
