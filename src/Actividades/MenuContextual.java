/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author DOLFHANDLER
 */
public class MenuContextual {

    protected ArrayList<Opciones> opciones;
    protected Point punto;

    public MenuContextual(ArrayList<Opciones> opc, Point punto) {
        this.opciones = opc;
        this.punto = punto;
        cargarOpciones();
    }

    private void cargarOpciones() {
        for (int i = 0; i < opciones.size(); i++) {
            opciones.get(i).setX(punto.x);
            opciones.get(i).setY(punto.y + opciones.get(i).getAlto() * i);
        }
    }

    public void setPunto(Point punto) {
        this.punto = punto;
    }

    public Point getPunto() {
        return punto;
    }

    public ArrayList<Opciones> getOpciones() {
        return opciones;
    }

    public Opciones getOpcion(int i) {
        return opciones.size() < i ? null : opciones.get(i);
    }

    public void pintar(Graphics2D g) {
        cargarOpciones();
        for (int i = 0; i < opciones.size(); i++) {
            opciones.get(i).pintar(g);
        }
    }
}
