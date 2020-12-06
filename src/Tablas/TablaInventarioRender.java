/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Actividades.Colores;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author DOLFHANDLER
 */
public class TablaInventarioRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        if (col == 5) {
            double entrada = Double.parseDouble(table.getValueAt(row, 3).toString());
            double existencia = Double.parseDouble(table.getValueAt(row, 5).toString());

            double porcentajeExistencia = Math.round(existencia / entrada * 100);
            System.out.println("--------------------------------------------------------------------porcentajeExistencia: " + porcentajeExistencia);
            if (porcentajeExistencia > 30) {
                setBackground(Colores.SUCCESS);
                setForeground(Colores.TEXT_SUCCESS);
            }

            if (porcentajeExistencia < 30) {
                setBackground(Color.YELLOW);
                setForeground(Color.DARK_GRAY);
            }

            if (porcentajeExistencia < 15) {
                setBackground(Colores.DANGER);
                setForeground(Colores.TEXT_DANGER);
            }
        } else {
            setBackground(Color.WHITE);
            setForeground(new Color(59,123,50));
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    }

}
