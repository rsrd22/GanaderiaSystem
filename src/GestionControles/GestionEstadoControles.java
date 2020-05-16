/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionControles;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DOLFHANDLER
 */
public class GestionEstadoControles {

    private ArrayList<Control> controles;

    public GestionEstadoControles() {
        controles = new ArrayList<>();
    }

    public void addControl(Control control) {
        controles.add(control);
    }

    public void limpiarControles() {
        for (Control control : controles) {
            limpiarComponente(control);
        }
    }

    public void habilitarControles() {
        for (Control control : controles) {
            habilitarComponente(control);
        }
    }

    public void deshabilitarControles() {
        for (Control control : controles) {
            deshabilitarComponente(control);
        }
    }

    private void limpiarComponente(Control control) {

        if (control.getControl() instanceof JLabel) {
            JLabel componente = ((JLabel) control.getControl());
            componente.setText("");
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JTable) {
            JTable componente = ((JTable) control.getControl());
            componente.setModel(new DefaultTableModel());
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JComboBox) {
            JComboBox componente = ((JComboBox) control.getControl());
            componente.setSelectedIndex(0);
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JSpinner) {
            JSpinner componente = ((JSpinner) control.getControl());
            componente.setValue(0);
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JSlider) {
            JSlider componente = ((JSlider) control.getControl());
            componente.setValue(0);
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JTextArea) {
            JTextArea componente = ((JTextArea) control.getControl());
            componente.setText("");
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JTextField) {
            JTextField componente = ((JTextField) control.getControl());
            componente.setText("");
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JPasswordField) {
            JPasswordField componente = ((JPasswordField) control.getControl());
            componente.setText("");
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JTextPane) {
            JTextPane componente = ((JTextPane) control.getControl());
            componente.setText("");
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JCheckBox) {
            JCheckBox componente = ((JCheckBox) control.getControl());
            componente.setSelected(false);
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JRadioButton) {
            JRadioButton componente = ((JRadioButton) control.getControl());
            componente.setSelected(false);
            componente.setEnabled(control.estaHabilitado());
        } else if (control.getControl() instanceof JDateChooser) {
            JDateChooser componente = ((JDateChooser) control.getControl());
            componente.setCalendar(Calendar.getInstance());
            componente.setEnabled(control.estaHabilitado());
        }
    }

    private void habilitarComponente(Control control) {
        boolean habilitar = true;
        if (control.getControl() instanceof JLabel) {
            JLabel componente = ((JLabel) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTable) {
            JTable componente = ((JTable) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JComboBox) {
            JComboBox componente = ((JComboBox) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JSpinner) {
            JSpinner componente = ((JSpinner) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JSlider) {
            JSlider componente = ((JSlider) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextArea) {
            JTextArea componente = ((JTextArea) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextField) {
            JTextField componente = ((JTextField) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JPasswordField) {
            JPasswordField componente = ((JPasswordField) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextPane) {
            JTextPane componente = ((JTextPane) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JCheckBox) {
            JCheckBox componente = ((JCheckBox) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JRadioButton) {
            JRadioButton componente = ((JRadioButton) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JButton) {
            JButton componente = ((JButton) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JDateChooser) {
            JDateChooser componente = ((JDateChooser) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JPanel) {
            JPanel componente = ((JPanel) control.getControl());
            componente.setEnabled(habilitar);
        }
    }

    private void deshabilitarComponente(Control control) {
        boolean habilitar = false;
        if (control.getControl() instanceof JLabel) {
            JLabel componente = ((JLabel) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTable) {
            JTable componente = ((JTable) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JComboBox) {
            JComboBox componente = ((JComboBox) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JSpinner) {
            JSpinner componente = ((JSpinner) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JSlider) {
            JSlider componente = ((JSlider) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextArea) {
            JTextArea componente = ((JTextArea) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextField) {
            JTextField componente = ((JTextField) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JPasswordField) {
            JPasswordField componente = ((JPasswordField) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JTextPane) {
            JTextPane componente = ((JTextPane) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JCheckBox) {
            JCheckBox componente = ((JCheckBox) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JRadioButton) {
            JRadioButton componente = ((JRadioButton) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JButton) {
            JButton componente = ((JButton) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JDateChooser) {
            JDateChooser componente = ((JDateChooser) control.getControl());
            componente.setEnabled(habilitar);
        } else if (control.getControl() instanceof JPanel) {
            JPanel componente = ((JPanel) control.getControl());
            componente.setEnabled(habilitar);
        }
    }
}
