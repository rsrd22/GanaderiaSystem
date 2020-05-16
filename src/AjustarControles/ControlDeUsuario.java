package AjustarControles;


import java.awt.Component;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DOLFHANDLER
 */
public class ControlDeUsuario {

    private Component controlUsuario;
    private final ArrayList<Integer> ajustes;
    public boolean ajustable;
    public int tipoAjuste;
    private int proporcionInicialAncho;
    private int proporcionInicialAlto;
    private int proporcionInicialX;
    private int proporcionInicialY;

    public ControlDeUsuario(Component controlUI) {
        ajustes = new ArrayList<>();
        ajustable = Ajustar.No;
        controlUsuario = controlUI;
        Component padre = controlUI.getParent();
        proporcionInicialAncho = padre.getWidth() - controlUI.getX();
        proporcionInicialAlto = padre.getHeight() - controlUI.getY();
        proporcionInicialX = padre.getX() - controlUI.getX();
        proporcionInicialY = padre.getY() - controlUI.getY();
    }

    public Component getControlUsuario() {
        return controlUsuario;
    }

    public void setControlUsuario(Component controlUsuario) {
        this.controlUsuario = controlUsuario;
    }

    public void addAjuste(Integer ajuste) {
        ajustes.add(ajuste);
    }

    public ArrayList<Integer> getAjustes() {
        return ajustes;
    }

    public int getProporcionInicialAncho() {
        return proporcionInicialAncho;
    }

    public int getProporcionInicialAlto() {
        return proporcionInicialAlto;
    }

    public int getProporcionInicialX() {
        return proporcionInicialX;
    }

    public int getProporcionInicialY() {
        return proporcionInicialY;
    }
}
