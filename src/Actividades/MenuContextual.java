/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import Utilidades.DireccionPintado;
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
    protected int direccionPintado = DireccionPintado.DEFAULT;

    public MenuContextual(ArrayList<Opciones> opc, Point punto) {
        this.opciones = opc;
        this.punto = punto;
        cargarOpciones();
    }

    private void cargarOpciones() {
        switch (direccionPintado) {
            case DireccionPintado.ABAJO:
                for (int i = 0; i < opciones.size(); i++) {
                    opciones.get(i).setX(punto.x);
                    opciones.get(i).setY(punto.y + opciones.get(i).getAlto() * i);
                }
                break;
            case DireccionPintado.ARRIBA:
                for (int i = opciones.size()-1; i >= 0; i--) {
                    opciones.get(i).setX(punto.x);
                    opciones.get(i).setY(punto.y -30- opciones.get(i).getAlto() * i);
                }
                break;
            default:
                for (int i = 0; i < opciones.size(); i++) {
                    opciones.get(i).setX(punto.x);
                    opciones.get(i).setY(punto.y + opciones.get(i).getAlto() * i);
                }
                break;
        }
    }

    public void setPunto(Point punto, int direccionDePintado) {
        this.punto = punto;
        this.direccionPintado = direccionDePintado;
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
