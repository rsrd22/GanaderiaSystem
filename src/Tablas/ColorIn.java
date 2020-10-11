/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tablas;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author MERRY
 */
public class ColorIn<String> extends JLabel implements ListCellRenderer{

    public ColorIn() {
            super();
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());
            
//              Object texto = modelo.getElementAt(index);
//              Object texto = COMBOBOX.getItemAt(index);

            if (index % 2 == 0) {
                setBackground(java.awt.Color.RED);
            } else {
                setBackground(java.awt.Color.GREEN);
            }
            setBorder(null);
//            if (isSelected) {
//                setBackground(java.awt.Color.LIGHT_GRAY);
//            }
                

            return this;
    }
    
}
