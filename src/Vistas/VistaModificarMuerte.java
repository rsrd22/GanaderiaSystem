/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Control.ControlAnimales;
import Control.Retorno;
import Modelo.ModeloAnimales;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Utilidades;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author MERRY
 */
public class VistaModificarMuerte extends javax.swing.JPanel {
    String id_animal ="";
    Map<String, String> datosMuerte= new HashMap<String, String>();
    private ModeloAnimales modeloAnimal;
    private ControlAnimales control;
    ModeloVentanaGeneral modeloVentanaGeneral;
    
    /**
     * Creates new form VistaModificarMuerte
     */
    public VistaModificarMuerte() {
        initComponents();
        setSize(405, 363);
    }
    
    public VistaModificarMuerte(ModeloVentanaGeneral modeloVentanaGeneral) {
        initComponents();
        setSize(484 ,459);
        datosMuerte = (HashMap<String, String>)modeloVentanaGeneral.getModeloDatos();
        this.modeloVentanaGeneral = modeloVentanaGeneral;
        txtNumeroAnimal.setEnabled(false);
        control = new ControlAnimales();
        LimpiarFormulario();
        LlenarFormulario();   
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

        lblTid1 = new javax.swing.JLabel();
        txtNumeroAnimal = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblFechaVenta = new javax.swing.JLabel();
        jdFechaMuerte = new com.toedter.calendar.JDateChooser();
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
        lblCausaMuerte = new javax.swing.JLabel();
        btnDescartar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Numero Animal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        add(lblTid1, gridBagConstraints);

        txtNumeroAnimal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroAnimal.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.setBorder(null);
        txtNumeroAnimal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.setFocusCycleRoot(true);
        txtNumeroAnimal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroAnimal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroAnimalFocusLost(evt);
            }
        });
        txtNumeroAnimal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroAnimalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroAnimalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 20);
        add(txtNumeroAnimal, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        add(jSeparator6, gridBagConstraints);

        lblFechaVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaVenta.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaVenta.setText("Fecha de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        add(lblFechaVenta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 163;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 24);
        add(jdFechaMuerte, gridBagConstraints);

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 337;
        gridBagConstraints.ipady = 67;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 24);
        add(ScrollCausaMuerte, gridBagConstraints);

        lblCausaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCausaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblCausaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCausaMuerte.setText("Causa de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        add(lblCausaMuerte, gridBagConstraints);

        btnDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar.png"))); // NOI18N
        btnDescartar.setToolTipText("Descartar");
        btnDescartar.setBorderPainted(false);
        btnDescartar.setContentAreaFilled(false);
        btnDescartar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnDescartar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar_over.png"))); // NOI18N
        btnDescartar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar_over.png"))); // NOI18N
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = -27;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 10, 22, 0);
        add(btnDescartar, gridBagConstraints);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = -27;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 22, 0);
        add(btnGuardar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumeroAnimalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroAnimalFocusLost

    }//GEN-LAST:event_txtNumeroAnimalFocusLost

    private void txtNumeroAnimalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroAnimalKeyPressed

    }//GEN-LAST:event_txtNumeroAnimalKeyPressed

    private void txtNumeroAnimalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroAnimalKeyReleased

    }//GEN-LAST:event_txtNumeroAnimalKeyReleased

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        Descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollCausaMuerte;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JSeparator jSeparator6;
    private com.toedter.calendar.JDateChooser jdFechaMuerte;
    private javax.swing.JLabel lblCausaMuerte;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblTid1;
    public javax.swing.JTextField txtNumeroAnimal;
    private javax.swing.JTextArea txtObservacionMuerte;
    // End of variables declaration//GEN-END:variables

    private void LimpiarFormulario() {
        id_animal = "";
        txtNumeroAnimal.setText("");
        txtObservacionMuerte.setText("");
        jdFechaMuerte.setCalendar(Calendar.getInstance());
    }

    private void LlenarFormulario() {
        id_animal = ""+datosMuerte.get("id");
        txtNumeroAnimal.setText(""+datosMuerte.get("numero"));
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(""+datosMuerte.get("fecha_muerte"));
            jdFechaMuerte.setDate(fecha);
        }catch(Exception e){
            
        }
        txtObservacionMuerte.setText(""+datosMuerte.get("descripcion_muerte"));
    }

    private void Descartar() {
        LimpiarFormulario();
    }

    private void Guardar() {
        if (jdFechaMuerte.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe especificar la fecha de venta del animal.");
            jdFechaMuerte.requestFocusInWindow();
            return;
        }

        if (txtObservacionMuerte.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar el precio de venta del animal.");
            txtObservacionMuerte.requestFocusInWindow();
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        modeloAnimal = new ModeloAnimales();
        modeloAnimal.setId(id_animal);
        modeloAnimal.setDescripcionMuerte(Utilidades.CodificarElemento(txtObservacionMuerte.getText().trim()));
        Calendar fechaVenta = jdFechaMuerte.getCalendar();
        modeloAnimal.setFechaMuerte(sdf.format(fechaVenta.getTime()));
        
        int retorno = control.ActualizarMuerte(modeloAnimal); 
        
        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro actualizado satisfactoriamente.";
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
        if(modeloVentanaGeneral.getPanelPadre() instanceof VistaHistoricoMuertes){
            ((VistaHistoricoMuertes)modeloVentanaGeneral.getPanelPadre()).RetornoVistaGeneral(modeloVentanaGeneral); 
        }
        ((VistaGeneral)modeloVentanaGeneral.getFrameVentana()).dispose();
        
    }
}
