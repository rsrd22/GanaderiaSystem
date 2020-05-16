/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Color;

/**
 *
 * @author MERRY
 */
public class ModeloDatosMultiple {
    private int id;
    private String DatosMostrar;
    private int x;
    private int y;
    private int widthPnl;
    private int widthLblD;
    private int widthLblC;
    private int heightPnl;
    private int height;
    private int padding;
    private Color color;
    private boolean isfinca;

    public ModeloDatosMultiple(int id, String DatosMostrar, int x, int y, int widthPnl, int widthLblD, int widthLblC, int heightPnl, int height, int padding) {
        this.id = id;
        this.DatosMostrar = DatosMostrar;
        this.x = x;
        this.y = y;
        this.widthPnl = widthPnl;
        this.widthLblD = widthLblD;
        this.widthLblC = widthLblC;
        this.heightPnl = heightPnl;
        this.height = height;
        this.padding = padding;
        this.color = null;
        this.isfinca = false;
    }
    public ModeloDatosMultiple(int id, String DatosMostrar, int x, int y, int widthPnl, int widthLblD, int widthLblC, int heightPnl, int height, int padding, Color color, boolean isfinca) {
        this.id = id;
        this.DatosMostrar = DatosMostrar;
        this.x = x;
        this.y = y;
        this.widthPnl = widthPnl;
        this.widthLblD = widthLblD;
        this.widthLblC = widthLblC;
        this.heightPnl = heightPnl;
        this.height = height;
        this.padding = padding;
        this.color = color;
        this.isfinca = isfinca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatosMostrar() {
        return DatosMostrar;
    }

    public void setDatosMostrar(String DatosMostrar) {
        this.DatosMostrar = DatosMostrar;
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

    public int getWidthPnl() {
        return widthPnl;
    }

    public void setWidthPnl(int widthPnl) {
        this.widthPnl = widthPnl;
    }

    public int getWidthLblD() {
        return widthLblD;
    }

    public void setWidthLblD(int widthLblD) {
        this.widthLblD = widthLblD;
    }

    public int getWidthLblC() {
        return widthLblC;
    }

    public void setWidthLblC(int widthLblC) {
        this.widthLblC = widthLblC;
    }

    public int getHeightPnl() {
        return heightPnl;
    }

    public void setHeightPnl(int heightPnl) {
        this.heightPnl = heightPnl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isIsfinca() {
        return isfinca;
    }

    public void setIsfinca(boolean isfinca) {
        this.isfinca = isfinca;
    }
    
    
    
    
}
