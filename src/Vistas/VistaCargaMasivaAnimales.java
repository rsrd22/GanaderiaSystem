/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Control.ControlGeneral;
import Utilidades.Utilidades;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class VistaCargaMasivaAnimales extends javax.swing.JPanel {

    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idTipoAnimal;
    
    /**
     * Creates new form VistaCargaMasivaAnimales
     */
    public VistaCargaMasivaAnimales() {
        initComponents();
        CargarListaFincas();
    }

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        lblTid3 = new javax.swing.JLabel();
        cbTipoAnimales = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRespuesta = new javax.swing.JTextArea();
        lblTid1 = new javax.swing.JLabel();
        txtURL = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnSelectArchivo = new javax.swing.JButton();
        lblCargar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        add(cbFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, 34));

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        add(lblTid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 195, -1));

        lblTid3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid3.setForeground(new java.awt.Color(59, 123, 50));
        lblTid3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid3.setText("Tipo Animales");
        add(lblTid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 135, -1));

        cbTipoAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalesActionPerformed(evt);
            }
        });
        add(cbTipoAnimales, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 190, 34));

        txtRespuesta.setColumns(20);
        txtRespuesta.setRows(5);
        jScrollPane1.setViewportView(txtRespuesta);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 530, 240));

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(26, 82, 118));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Archivo");
        add(lblTid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, -1));

        txtURL.setEditable(false);
        txtURL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtURL.setCaretColor(new java.awt.Color(26, 82, 118));
        txtURL.setSelectionColor(new java.awt.Color(26, 82, 118));
        txtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtURLActionPerformed(evt);
            }
        });
        add(txtURL, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 360, 30));

        jSeparator1.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 360, 10));

        jPanel4.setBackground(new java.awt.Color(59, 123, 50));

        btnSelectArchivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSelectArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectArchivo.setText("Seleccionar Archivo");
        btnSelectArchivo.setBorderPainted(false);
        btnSelectArchivo.setContentAreaFilled(false);
        btnSelectArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectArchivoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectArchivoMouseExited(evt);
            }
        });
        btnSelectArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 1, Short.MAX_VALUE)
                    .addComponent(btnSelectArchivo)
                    .addGap(0, 2, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSelectArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, -1, 30));

        lblCargar.setBackground(new java.awt.Color(59, 123, 50));
        lblCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/importar.png"))); // NOI18N
        lblCargar.setToolTipText("Cargar Animales");
        add(lblCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        if (cbFinca.getItemCount() > 0) {
            if (cbFinca.getSelectedIndex() >= 0) {
                idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
                CargarListaTipoAnimales();
            } 
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalesActionPerformed
        if (cbTipoAnimales.getItemCount() > 0) {
            if (cbTipoAnimales.getSelectedIndex() >= 0) {
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
            } 
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

    private void txtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtURLActionPerformed

    private void btnSelectArchivoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectArchivoMouseEntered
        jPanel4.setBackground(new Color(31, 97, 141));
    }//GEN-LAST:event_btnSelectArchivoMouseEntered

    private void btnSelectArchivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectArchivoMouseExited
        jPanel4.setBackground(new Color(26, 82, 118));
    }//GEN-LAST:event_btnSelectArchivoMouseExited

    private void btnSelectArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectArchivoActionPerformed
        txtURL.setText(Expresiones.seleccionarArchivoExcel(false, "C:/"));
        //System.out.println("txtURL-->"+txtURL.getText());
    }//GEN-LAST:event_btnSelectArchivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnSelectArchivo;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipoAnimales;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCargar;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid3;
    private javax.swing.JTextArea txtRespuesta;
    public javax.swing.JTextField txtURL;
    // End of variables declaration//GEN-END:variables
    
    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n"
                + "FROM `fincas`\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
        CargarListaTipoAnimales();
        //EventoComboFincas();
    }

    private void CargarListaTipoAnimales() {
        listaTipoAnimales = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM `tipo_animales`\n"
                + "WHERE id_finca = '" + idFinca + "' AND estado = 'Activo'\n"
                + "ORDER BY id ASC");

        Utilidades.LlenarComboBox(cbTipoAnimales, listaTipoAnimales, "DESCRIPCION");
        cbTipoAnimales.setSelectedIndex(0);
    }


}

