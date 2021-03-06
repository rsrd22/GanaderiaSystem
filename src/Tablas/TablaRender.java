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
 * @author Acer
 */
public class TablaRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            if (isSelected) {
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setForeground(table.getSelectionForeground());
                btn.setBackground(table.getSelectionBackground());
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
            }
            return btn;
        }
        if (value instanceof JCheckBox) {
            JCheckBox ch = (JCheckBox) value;
            return ch;
        }
        
        if(row%2 == 0){
            setBackground(Colores.bgOverTable);
            setForeground(Colores.TEXT_TABLE);
        }else{
            setBackground(Colores.bgNormalTable);
            setForeground(Colores.TEXT_TABLE);
        }
        
        if(table.getValueAt(row, 11).toString().equals("*")){
            setBackground(Colores.DANGER);
            setForeground(Colores.TEXT_DANGER);
        }
        
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    }
}
