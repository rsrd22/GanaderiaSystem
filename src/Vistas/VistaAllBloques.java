/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Configuracion.InformacionGlobal;
import Control.*;
import Modelo.*;
import Tablas.*;
import Utilidades.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author MERRY
 */
public class VistaAllBloques extends javax.swing.JPanel {

    private ModeloBloques modeloBloques;
    private ControlBloques controlBloque;
    private ArrayList<ModeloBloques> ListamodeloBloques;
    public List<Map<String, String>> listaFincas;
    public String[] EncabezadoTblBloques;
    public DefaultTableModel modeloTblBloques;
    private ControlFincas controlFinca;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String AreaFinca;
    public int fila;
    public int idModulo = 4;
    public ArrayList<String> NameColumnasOrden;
    public int bandOrden = 0;
    public int colOrden = 0;
    public String Orden = "";
    /**
     * Creates new form VistaAllBloques
     */
    public VistaAllBloques() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        //<editor-fold defaultstate="collapsed" desc="ORDEN TABLA">
            NameColumnasOrden = new ArrayList<>();
            NameColumnasOrden.add("numero");
            NameColumnasOrden.add("area");
//</editor-fold>
        EncabezadoTblBloques = new String[]{
            "No", "Número", "Área Total", "Modificar", "Eliminar"
        };
        lblId_Bloque.setVisible(false);
        controlFinca = new ControlFincas();
        controlBloque = new ControlBloques();
        ListamodeloBloques = new ArrayList<>();
        modeloBloques = new ModeloBloques();
        idFinca = "";
        fila = -1;
        CargarListaFincas();
        LimpiarFomulario();
        InicializarTblBloques();
        
        InformacionGlobal.setFincaDesdeConstructor(cbFinca);
    }

    public void InicializarTblBloques() {
        tbl_Bloques.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblBloques = new DefaultTableModel(EncabezadoTblBloques, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Bloques.setModel(modeloTblBloques);

        tbl_Bloques.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_Bloques.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_Bloques.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Bloques.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbl_Bloques.getColumnModel().getColumn(4).setPreferredWidth(80);

        tbl_Bloques.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblBloques.getColumnCount(); i++) {
            tbl_Bloques.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Bloques.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Bloques.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
        
        tbl_Bloques.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {     
                EventoOrdenTabla(e);
            }
        });

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

        lblId_Bloque = new javax.swing.JLabel();
        lblTid = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Bloques = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnDescartar = new javax.swing.JButton();
        txtNumero = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lbltitle1 = new javax.swing.JLabel();
        lbltitle3 = new javax.swing.JLabel();
        txtAreaT = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        add(lblTid, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbFinca, gridBagConstraints);

        tbl_Bloques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Bloques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_BloquesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Bloques);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 557;
        gridBagConstraints.ipady = 253;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Agregar Bloque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btnDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar30.png"))); // NOI18N
        btnDescartar.setToolTipText("Descartar");
        btnDescartar.setBorderPainted(false);
        btnDescartar.setContentAreaFilled(false);
        btnDescartar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnDescartar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar30_over.png"))); // NOI18N
        btnDescartar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar30_over.png"))); // NOI18N
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel1.add(btnDescartar, gridBagConstraints);

        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(59, 123, 50));
        txtNumero.setBorder(null);
        txtNumero.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumero.setFocusCycleRoot(true);
        txtNumero.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroFocusLost(evt);
            }
        });
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.475;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(txtNumero, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.475;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 0);
        jPanel1.add(jSeparator6, gridBagConstraints);

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.475;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(lbltitle1, gridBagConstraints);

        lbltitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle3.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle3.setText("Area Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.47;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        jPanel1.add(lbltitle3, gridBagConstraints);

        txtAreaT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAreaT.setForeground(new java.awt.Color(59, 123, 50));
        txtAreaT.setBorder(null);
        txtAreaT.setCaretColor(new java.awt.Color(59, 123, 50));
        txtAreaT.setFocusCycleRoot(true);
        txtAreaT.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtAreaT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAreaTFocusLost(evt);
            }
        });
        txtAreaT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAreaTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAreaTKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.47;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(txtAreaT, gridBagConstraints);

        jSeparator8.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.47;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 0);
        jPanel1.add(jSeparator8, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setText("HA");
        jLabel13.setToolTipText("Hectárea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.005;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30_over.png"))); // NOI18N
        btnGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30_over.png"))); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.025;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(btnGuardar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        InformacionGlobal.setFincaDesdeEventoChange(cbFinca);
        
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        AreaFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("AREA");
        AccionCombo();

    }//GEN-LAST:event_cbFincaActionPerformed

    public void AccionCombo() {
        Utilidades.LimpiarTabla(tbl_Bloques);
        modeloBloques.setId_finca("" + idFinca);
        if (Integer.parseInt(idFinca) > 0) {
            ListamodeloBloques = (ArrayList<ModeloBloques>) controlFinca.ObtenerBloquesxFinca("" + idFinca, Orden);
            for (int i = 0; i < ListamodeloBloques.size(); i++) {
                agregarFilaTabla(modeloTblBloques,
                        new Object[]{
                            tbl_Bloques.getRowCount() + 1,
                            ListamodeloBloques.get(i).getNumero(),
                            Utilidades.MascaraMonedaConDecimales(ListamodeloBloques.get(i).getArea().replace(".", ",")),
                            "Modificar",
                            "Eliminar"
                        });
            }
        } else {
            Utilidades.LimpiarTabla(tbl_Bloques);
        }
    }

    private void tbl_BloquesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_BloquesMouseReleased
        int fila = tbl_Bloques.getSelectedRow();
        int cola = tbl_Bloques.getSelectedColumn();
        if (cola == 3) {// ACTUALIZAR
            this.fila = fila;
            modeloBloques = ListamodeloBloques.get(fila);
            txtNumero.setText("" + modeloBloques.getNumero());
            txtAreaT.setText("" + Utilidades.MascaraMonedaConDecimales(modeloBloques.getArea().replace(".", ",")));
            txtNumero.requestFocusInWindow();
        } else if (cola == 4) { //ELIMINAR
            modeloBloques = ListamodeloBloques.get(fila);
            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
            if (resp == JOptionPane.YES_OPTION) {
                int ret = controlBloque.Eliminar(modeloBloques);
                if (ret == 0) {
                    JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
                    AccionCombo();
                }
            }
        }

    }//GEN-LAST:event_tbl_BloquesMouseReleased

    private void txtNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroFocusLost

    }//GEN-LAST:event_txtNumeroFocusLost

    private void txtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyPressed

    }//GEN-LAST:event_txtNumeroKeyPressed

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased

    }//GEN-LAST:event_txtNumeroKeyReleased

    private void txtAreaTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAreaTFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaTFocusLost

    private void txtAreaTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaTKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreaTKeyPressed

    private void txtAreaTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaTKeyReleased
        String areat = txtAreaT.getText();
        String valorsin = areat.indexOf(".") > -1 ? areat.replace(".", "") : areat;
        String dato = Expresiones.procesarSoloNumP(valorsin);
        dato = Utilidades.MascaraMonedaConDecimales(dato);
        txtAreaT.setText(dato);
    }//GEN-LAST:event_txtAreaTKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        LimpiarFomulario();
    }//GEN-LAST:event_btnDescartarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    public javax.swing.JLabel lbltitle1;
    public javax.swing.JLabel lbltitle3;
    private javax.swing.JTable tbl_Bloques;
    public javax.swing.JTextField txtAreaT;
    public javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION, '0' as AREA\n"
                + "UNION\n"
                + "SELECT `id` AS ID, `descripcion` AS DESCRIPCION, area as AREA\n"
                + "FROM `fincas`\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION, '0' as AREA*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");

    }

    private void LimpiarFomulario() {
        txtNumero.setText("");
        txtAreaT.setText("");
        modeloBloques = new ModeloBloques();
        modeloBloques.setId_finca("" + idFinca);
        modeloBloques.setId("0");
        fila = -1;
    }

    private void Guardar() {
        if (txtAreaT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar el área total.");
            return;
        }
        
        String AreaT = txtAreaT.getText().trim().replace(".", "").replace(",", ".");
        double AcuAreaBloques = getAcumuladoArea(modeloBloques.getId());
        double AreaTo = Double.parseDouble(AreaT);

        if (idFinca.equals("-1")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una finca para realizar la operación.");
            return;
        }

        if (modeloBloques.getId().equals("0")) {
            boolean Valnumero = controlBloque.VerificarNumeroBloque(idFinca, txtNumero.getText().trim());
            if (Valnumero) {
                JOptionPane.showMessageDialog(this, "El número ingresado a se encuentra registrado en el sistema.");
                return;
            }
        }

        if (AreaTo == 0) {
            JOptionPane.showMessageDialog(this, "El area ingresada debe ser mayor que cero.");
            return;
        }

        if (Double.parseDouble(AreaFinca) < AcuAreaBloques + AreaTo) {
            JOptionPane.showMessageDialog(this, "El area debe ser menor al area total de la finca. El area total es " + AreaFinca);
            return;
        }

        modeloBloques.setNumero(txtNumero.getText().trim());
        modeloBloques.setArea(AreaT);
        modeloBloques.setFecha("NOW()");
        modeloBloques.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        if (modeloBloques.getId_finca().equals("-1")) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una finca para realizar la operación.");
            return;
        }
        int ret = -1;
        if (modeloBloques.getId().equals("0")) {//INSERT
            ret = controlBloque.Guardar(modeloBloques);
        } else {
            ret = controlBloque.Actualizar(modeloBloques);
        }
        if (ret == 0) {
            if (fila > -1) {//ESTA EN TABLA ACTUALIZAR
                ListamodeloBloques.set(fila, modeloBloques);
                tbl_Bloques.setValueAt(modeloBloques.getNumero(), fila, 1);
                tbl_Bloques.setValueAt(Utilidades.MascaraMonedaConDecimales(modeloBloques.getArea().replace(".", ",")), fila, 2);
            } else {
                AccionCombo();
            }
            LimpiarFomulario();
        }
    }

    private void agregarFilaTabla(DefaultTableModel modelotbl, Object[] fila) {
        modelotbl.addRow(fila);
    }

    private double getAcumuladoArea(String id_Bloque) {
        double ret = 0;
        String valor = "";

        for (int i = 0; i < ListamodeloBloques.size(); i++) {
            if (!ListamodeloBloques.get(i).getId().equals(id_Bloque)) {
                valor = ListamodeloBloques.get(i).getArea().trim();
                ret += Double.parseDouble(valor);
            }
        }
        return ret;
    }
    
    public void EventoOrdenTabla(MouseEvent e){
        if(!tbl_Bloques.isEnabled())
            return;
        
        int col = tbl_Bloques.columnAtPoint(e.getPoint());
        System.out.println("col-->"+colOrden);
        if(col > 0){
            if(col != colOrden){
                colOrden = col;
                bandOrden = 1;//Ascendente
            }else{
                if(bandOrden > 0 )
                    bandOrden = -1;//Descendente
                else if(bandOrden < 0 )
                    bandOrden = 0;// Por Defecto
                else
                    bandOrden = 1;//Ascendente
                
            }
            String dat = "";
            String orden = NameColumnasOrden.get(col-1);
            String[] cols = orden.split("<::>");
            
            for(int i = 0; i < cols.length; i++){
                if(bandOrden == 0){
                    dat = "";
                }else{
                    dat += (dat.equals("")? "":", ") + TipoDato(col-1, cols[i])+" "+(bandOrden == 1?"ASC":"DESC");
                }
            }
            
            Orden = dat;
            AccionCombo();
        }
    }

    private String TipoDato(int ind, String Dato) {
        String ret = "";
        
            ret = Dato;
        
        return ret;
    }
    
}
