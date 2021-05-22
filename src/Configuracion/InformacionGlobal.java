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

    public static void setFincaDesdeConstructor(JComboBox comboFinca) {
        if (comboFinca.getModel().getSize() > 1) {
            if (!InformacionGlobal.finca.isEmpty()) {
                comboFinca.getModel().setSelectedItem(InformacionGlobal.finca);
            }
        }
    }

    public static void setFincaDesdeEventoChange(JComboBox comboFinca) {
        if (comboFinca.getModel().getSize() > 1) {
            if (comboFinca.getSelectedIndex() > 0) {
                InformacionGlobal.finca = comboFinca.getSelectedItem().toString();
            }
        }
    }

    public static void setTipoAnimalDesdeConstructor(JComboBox comboTipoAnimal) {
        if (comboTipoAnimal.getModel().getSize() > 1) {
            if (!InformacionGlobal.tipoAnimal.isEmpty()) {
                comboTipoAnimal.getModel().setSelectedItem(InformacionGlobal.tipoAnimal);
            }
        }
    }

    public static void setTipoAnimalDesdeEventoChange(JComboBox comboTipoAnimal) {
        if (comboTipoAnimal.getModel().getSize() > 1) {
            if (comboTipoAnimal.getSelectedIndex() > 0) {
                InformacionGlobal.tipoAnimal = comboTipoAnimal.getSelectedItem().toString();
            }
        }
    }

}
