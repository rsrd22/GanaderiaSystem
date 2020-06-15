/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tablas;

import Actividades.Colores;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author DOLFHANDLER
 */
public class TablaBackground extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col){
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        
        if(table.getValueAt(row, col).toString().equals("*")){
            setBackground(Colores.DANGER);
        }
        
        return this;
    }
}
