/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Actividades.Colores;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author AT-DESARROLLO
 */
public class PesajeTable extends JTable {

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component component = super.prepareRenderer(renderer, row, col);
        String dato = String.valueOf(getValueAt(row, 10));
        if (dato.equals("*")) {
            component.setBackground(Colores.DARK_FADED);
            component.setForeground(Colores.TEXT_FADED);
        }else{
            component.setForeground(new Color(59, 123, 50));
            component.setBackground(Color.WHITE);
        }
        
        return component;
    }

}
