/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlAnimales;
import Control.RAnimales.ControlRAnimales;
import Control.Retorno;
import Modelo.ModeloAnimales;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaEditarDatosAnimal extends javax.swing.JPanel {

    private Map.Entry<String[], JLabel> map;
    private ControlRAnimales control;
    public VistaHistoriaAnimal vha;
    private ModeloVentanaGeneral vistaGeneral;
    private String numeroActualAnimal;

    public VistaEditarDatosAnimal() {
        initComponents();
    }

    VistaEditarDatosAnimal(ModeloVentanaGeneral modeloVista) {
        initComponents();
        setSize(261, 200);
        control = new ControlRAnimales();
        map = (Map.Entry<String[], JLabel>) modeloVista.getModeloDatos();
        vha = (VistaHistoriaAnimal) modeloVista.getPanelPadre();
        vistaGeneral = modeloVista;
        numeroActualAnimal = "";
        cargarDatosIniciales();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblEtiqueta = new javax.swing.JLabel();
        cbCombo = new javax.swing.JComboBox();
        panelBtnGuardar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jdFechaDestete = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        txtPesoDestete = new javax.swing.JTextField();
        sepPesoDestete = new javax.swing.JSeparator();
        slCalificacion = new javax.swing.JSlider();
        txtNumeroAnimal = new javax.swing.JTextField();
        sepNumeroAnimal = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lblEtiqueta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEtiqueta.setForeground(new java.awt.Color(59, 123, 50));
        lblEtiqueta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEtiqueta.setText("label");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lblEtiqueta, gridBagConstraints);

        cbCombo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbCombo.setForeground(new java.awt.Color(59, 123, 50));
        cbCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Si", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 26;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbCombo, gridBagConstraints);

        panelBtnGuardar.setBackground(new java.awt.Color(59, 123, 50));
        panelBtnGuardar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBtnGuardarLayout = new javax.swing.GroupLayout(panelBtnGuardar);
        panelBtnGuardar.setLayout(panelBtnGuardarLayout);
        panelBtnGuardarLayout.setHorizontalGroup(
            panelBtnGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBtnGuardarLayout.setVerticalGroup(
            panelBtnGuardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 15, 15);
        add(panelBtnGuardar, gridBagConstraints);

        jdFechaDestete.setForeground(new java.awt.Color(59, 123, 50));
        jdFechaDestete.setDateFormatString("dd/MM/yyyy");
        jdFechaDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jdFechaDestete, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BELOW_BASELINE_TRAILING;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jScrollPane2, gridBagConstraints);

        txtPesoDestete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoDestete.setBorder(null);
        txtPesoDestete.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoDesteteKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtPesoDestete, gridBagConstraints);

        sepPesoDestete.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(sepPesoDestete, gridBagConstraints);

        slCalificacion.setBackground(new java.awt.Color(255, 255, 255));
        slCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        slCalificacion.setMajorTickSpacing(1);
        slCalificacion.setMaximum(5);
        slCalificacion.setMinimum(1);
        slCalificacion.setPaintLabels(true);
        slCalificacion.setPaintTicks(true);
        slCalificacion.setValue(3);
        slCalificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(slCalificacion, gridBagConstraints);

        txtNumeroAnimal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroAnimal.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroAnimal.setBorder(null);
        txtNumeroAnimal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroAnimalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtNumeroAnimal, gridBagConstraints);

        sepNumeroAnimal.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(sepNumeroAnimal, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        if (btnGuardar.isEnabled()) {
            Utilidades.establecerColorDeFondo(panelBtnGuardar, true);
        }
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        if (btnGuardar.isEnabled()) {
            Utilidades.establecerColorDeFondo(panelBtnGuardar, false);
        }
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        String[] key = map.getKey();
        JLabel value = map.getValue();

        if (Utilidades.validarSINO(key[0])) {
            if (cbCombo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Debe Seleccionar una opción.");
                return;
            }
        }

        if (key[0].equalsIgnoreCase("notas")) {
            if (txtNotas.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe especificar algo en las notas.");
                return;
            }
        }

        if (key[0].equalsIgnoreCase("Peso de destete")) {
            if (txtPesoDestete.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe especificar un peso.");
                return;
            }
        }

        if (key[0].equalsIgnoreCase("Fecha de destete")) {
            if (jdFechaDestete.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de destete.");
                return;
            }
        }

        if (key[0].equalsIgnoreCase("Número del animal")) {
            String nuevoNumeroAnimal = txtNumeroAnimal.getText().trim();
            if (nuevoNumeroAnimal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe especificar un número para el animal");
                return;
            }

            if (nuevoNumeroAnimal.equals(numeroActualAnimal)) {
                JOptionPane.showMessageDialog(this, "El número no puede ser el mismo");
                return;
            }

            String tipoAnimal = key[4];
            if (existeElAnimal(tipoAnimal, nuevoNumeroAnimal)) {
                JOptionPane.showMessageDialog(this, "El número " + nuevoNumeroAnimal + " se encuentra asignado a otro animal");
                return;
            }
        }
//</editor-fold>

        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtPesoDesteteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoDesteteKeyReleased
        String peso = txtPesoDestete.getText();
        String pesoSinPuntos = peso.indexOf(".") > -1 ? peso.replace(".", "") : peso;
        String pesoFormateado = Expresiones.procesarSoloNumP(pesoSinPuntos);
        pesoFormateado = Utilidades.MascaraMonedaConDecimales(pesoFormateado);
        txtPesoDestete.setText(pesoFormateado);
    }//GEN-LAST:event_txtPesoDesteteKeyReleased

    private void txtNumeroAnimalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroAnimalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroAnimalKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbCombo;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdFechaDestete;
    private javax.swing.JLabel lblEtiqueta;
    private javax.swing.JPanel panelBtnGuardar;
    private javax.swing.JSeparator sepNumeroAnimal;
    private javax.swing.JSeparator sepPesoDestete;
    private javax.swing.JSlider slCalificacion;
    private javax.swing.JTextArea txtNotas;
    public javax.swing.JTextField txtNumeroAnimal;
    public javax.swing.JTextField txtPesoDestete;
    // End of variables declaration//GEN-END:variables

    private void cargarDatosIniciales() {
        String[] key = map.getKey();
        JLabel value = map.getValue();

        boolean showTextBoxDestete = key[0].equalsIgnoreCase("Peso de destete");
        boolean showTextBoxNumero = key[0].equalsIgnoreCase("Número del animal");
        lblEtiqueta.setText(key[0]);
        cbCombo.setVisible(Utilidades.validarSINO(key[0]));
        txtNotas.setVisible(key[0].equalsIgnoreCase("notas"));
        jScrollPane2.setVisible(key[0].equalsIgnoreCase("notas"));
        txtPesoDestete.setVisible(showTextBoxDestete);
        sepPesoDestete.setVisible(showTextBoxDestete);
        txtNumeroAnimal.setVisible(showTextBoxNumero);
        sepNumeroAnimal.setVisible(showTextBoxNumero);
        jdFechaDestete.setVisible(key[0].equalsIgnoreCase("Fecha de destete"));
        slCalificacion.setVisible(key[0].equalsIgnoreCase("Calificación"));

        if (Utilidades.validarSINO(key[0])) {
            cbCombo.setSelectedItem(value.getText());
        } else if (key[0].equalsIgnoreCase("notas")) {
            txtNotas.setText(Utilidades.scapeHTML(value.getText()).trim());
        } else if (key[0].equalsIgnoreCase("Peso de destete")) {
            txtPesoDestete.setText(value.getText());
        } else if (key[0].equalsIgnoreCase("Número del animal")) {
            txtNumeroAnimal.setText(value.getText());
            numeroActualAnimal = value.getText();
        } else if (key[0].equalsIgnoreCase("Calificación")) {
            slCalificacion.setValue(Integer.parseInt(value.getText()));
        } else if (key[0].equalsIgnoreCase("Fecha de destete")) {
            try {
                if (value.getText().trim().isEmpty()) {
                    jdFechaDestete.setDate(Calendar.getInstance().getTime());
                } else {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formato.parse(value.getText());
                    jdFechaDestete.setDate(fecha);
                }
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error tratando de obtener la fecha\nDetalle:\n." + pe.getMessage());
            }
        }
    }

    private void Guardar() {
        /**
         * key[0]: identificador<br>
         * key[1]: nombre de la tabla de la base de datos<br>
         * key[2]: nombre del campo de la tabla
         */
        String[] key = map.getKey();
        JLabel value = map.getValue();
        ArrayList<String> consultas = new ArrayList<>();

        if (Utilidades.validarSINO(key[0])) {
            String valorSeleccionado = cbCombo.getSelectedItem().toString();
            if (key[2].equalsIgnoreCase("capado")) {
                consultas.add(
                        "update " + key[1] + " "
                        + "set " + key[2] + "='" + Utilidades.CapitaliceTexto(valorSeleccionado) + "' "
                        + "where id=" + key[3]
                );
            } else {
                consultas.add(
                        "update " + key[1] + " "
                        + "set " + key[2] + "='" + (valorSeleccionado.equalsIgnoreCase("no") ? "0" : "1") + "' "
                        + "where id=" + key[3]
                );
            }
        }
        if (key[0].equalsIgnoreCase("notas")) {
            consultas.add(
                    "update " + key[1] + " "
                    + "set " + key[2] + "='" + Utilidades.CodificarElemento(txtNotas.getText().trim()) + "' "
                    + "where id=" + key[3]
            );
        }
        if (key[0].equalsIgnoreCase("Peso de destete")) {
            consultas.add(
                    "update " + key[1] + " "
                    + "set " + key[2] + "=" + txtPesoDestete.getText().replace(".", "").replace(",", ".") + " "
                    + "where id=" + key[3]
            );
        }
        if (key[0].equalsIgnoreCase("Número del animal")) {
            consultas.add(
                    "update " + key[1] + " "
                    + "set " + key[2] + "=" + txtNumeroAnimal.getText().trim() + " "
                    + "where id=" + key[3]
            );
        }
        if (key[0].equalsIgnoreCase("Calificación")) {
            consultas.add(
                    "update " + key[1] + " "
                    + "set " + key[2] + "=" + slCalificacion.getValue() + " "
                    + "where id=" + key[3]
            );
        }
        if (key[0].equalsIgnoreCase("Fecha de destete")) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Calendar fecha = jdFechaDestete.getCalendar();
            consultas.add(
                    "update " + key[1] + " "
                    + "set " + key[2] + "='" + formato.format(fecha.getTime()) + "', "
                    + "destete='1' "
                    + "where id=" + key[3]
            );
        }

        if (consultas.size() > 0) {
            int retorno = control.EnviarConsultas(consultas);

            String mensaje = "";
            switch (retorno) {
                case Retorno.EXITO:
                    mensaje = "Registro actualizado satisfactoriamente.";
                    vha.ActualizarDatosAnimal();
                    ((VistaGeneral) vistaGeneral.getFrameVentana()).dispose();
                    break;
                case Retorno.ERROR:
                    mensaje = "El registro no pudo ser actualizado.";
                    break;
                case Retorno.EXCEPCION_SQL:
                    mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                    break;
                case Retorno.CLASE_NO_ENCONTRADA:
                    mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                    break;
            }

            JOptionPane.showMessageDialog(this, mensaje);
        }
    }

    private boolean existeElAnimal(String tipoAnimal, String nuevoNumeroAnimal) {
        ArrayList<ModeloAnimales> animales = new ArrayList<>();
        animales = (ArrayList<ModeloAnimales>) control.buscarAnimalPorTipoyNumero(tipoAnimal, nuevoNumeroAnimal);

        return animales.size() > 0;
    }
}
