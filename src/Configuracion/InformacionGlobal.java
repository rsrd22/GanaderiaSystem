/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import javax.swing.JComboBox;

/**
 *
 * @author DOLFHANDLER
 */
public class InformacionGlobal {

    private static String finca = "";
    private static String tipoAnimal = "";

    public static void setFincaDesdeConstructor(JComboBox combo) {
        if (combo.getModel().getSize() > 1) {
            if (!InformacionGlobal.finca.isEmpty()) {
                combo.getModel().setSelectedItem(InformacionGlobal.finca);
            }
        }
    }

    public static void setFincaDesdeEventoChange(JComboBox combo) {
        if (combo.getModel().getSize() > 1) {
            if (combo.getSelectedIndex() > 0) {
                InformacionGlobal.finca = combo.getSelectedItem().toString();
            }
        }
    }

    public static void setTipoAnimalDesdeConstructor(JComboBox combo) {
        if (combo.getModel().getSize() > 1) {
            if (!InformacionGlobal.tipoAnimal.isEmpty()) {
                combo.getModel().setSelectedItem(InformacionGlobal.finca);
            }
        }
    }

    public static void setTipoAnimalDesdeEventoChange(JComboBox combo) {
        if (combo.getModel().getSize() > 1) {
            if (combo.getSelectedIndex() > 0) {
                InformacionGlobal.tipoAnimal = combo.getSelectedItem().toString();
            }
        }
    }

}
