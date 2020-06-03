/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlGeneral;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Utilidades;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaIngresarMotivo extends javax.swing.JPanel {

    private ControlGeneral controlGral;
    public ModeloVentanaGeneral modeloVentanaGeneral;

    /**
     * Creates new form VistaSeleccionarFinca
     */
    public VistaIngresarMotivo() {
        initComponents();
        controlGral = new ControlGeneral();
    }

    VistaIngresarMotivo(ModeloVentanaGeneral modeloVentanaGeneral) {
        initComponents();
        setSize(300, 200);
        this.modeloVentanaGeneral = modeloVentanaGeneral;
        controlGral = new ControlGeneral();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltitle8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btniniciarsesion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMotivo = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Motivo");
        add(lbltitle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(59, 123, 50));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));

        btniniciarsesion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btniniciarsesion.setForeground(new java.awt.Color(255, 255, 255));
        btniniciarsesion.setText("Guardar");
        btniniciarsesion.setBorderPainted(false);
        btniniciarsesion.setContentAreaFilled(false);
        btniniciarsesion.setFocusPainted(false);
        btniniciarsesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btniniciarsesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btniniciarsesionMouseExited(evt);
            }
        });
        btniniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniciarsesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btniniciarsesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btniniciarsesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        txtMotivo.setColumns(20);
        txtMotivo.setRows(5);
        jScrollPane1.setViewportView(txtMotivo);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 270, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btniniciarsesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btniniciarsesionMouseEntered
        Utilidades.establecerColorDeFondo(jPanel2, true);
    }//GEN-LAST:event_btniniciarsesionMouseEntered

    private void btniniciarsesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btniniciarsesionMouseExited
        Utilidades.establecerColorDeFondo(jPanel2, false);
    }//GEN-LAST:event_btniniciarsesionMouseExited

    private void btniniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniciarsesionActionPerformed
        Aceptar();
    }//GEN-LAST:event_btniniciarsesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btniniciarsesion;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JTextArea txtMotivo;
    // End of variables declaration//GEN-END:variables

    private void Aceptar() {
        String motivo = txtMotivo.getText().trim();
        
        if (modeloVentanaGeneral.getPanelPadre() instanceof VistaTrasladoGrupos) {
            ((VistaTrasladoGrupos) modeloVentanaGeneral.getPanelPadre()).RetornoVistaGeneral(modeloVentanaGeneral, motivo);
        }
        
        ((VistaGeneral)modeloVentanaGeneral.getFrameVentana()).dispose();  
    }

}
