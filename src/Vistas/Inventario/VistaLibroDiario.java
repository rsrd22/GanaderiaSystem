/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas.Inventario;

import Control.ControlGeneral;
import Control.Inventario.*;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MERRY
 */
public class VistaLibroDiario extends javax.swing.JPanel {
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public DefaultTableModel modeloTblLibro;
    public String[] EncabezadoTblLibro;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaLibro;
    public List<Map<String, String>> ListaLibroMostrar;
    public String[] NameColumnas;
    public ArrayList<String> NameColumnasFiltro;
    Map<String, Map<String, String>> PropiedadesColumnas = new HashMap<>();
    public int idModulo = 1990;
    public int filaSeleccionada = -1;
    public int band =0 ;
    public ControlInventario controlInventario =  new ControlInventario();
    public ControlLibro controlLibro =  new ControlLibro();
    public Map<String, String> datoaModificar = new HashMap<String, String>();
    
    /**
     * Creates new form VistaLibroDiario
     */
    public VistaLibroDiario() {
        initComponents();
        //Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        idFinca = "";
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("FECHA");
        NameColumnasFiltro.add("DETALLE");
        NameColumnasFiltro.add("ID_PRODUCTO");
        NameColumnasFiltro.add("CANTIDAD");
        NameColumnasFiltro.add("PRECIO");
        NameColumnasFiltro.add("DEBE");
        NameColumnasFiltro.add("HABER");
        NameColumnasFiltro.add("SALDO");
        EncabezadoTblLibro = new String[]{
            "No",
            "Fecha",
            "Detalle",
            "Cantidad",
            "<html><p style=\"text-align:center;\">Precio/</p><p style=\"text-align:center;\">Unidad</p></html>",
            "Debe",
            "Haber",
            "Saldo",
            "M",
            "E"
        };
        EstadoPanelAdd(false);
        listaFincas = new ArrayList<>();
        CargarListaFincas();
        InicializarTblLibro();
    }
    
    public void InicializarTblLibro() {
        tbl_LibroDiario.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblLibro = new DefaultTableModel(EncabezadoTblLibro, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_LibroDiario.setModel(modeloTblLibro);

        tbl_LibroDiario.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_LibroDiario.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbl_LibroDiario.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(6).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(7).setPreferredWidth(70);
        tbl_LibroDiario.getColumnModel().getColumn(8).setPreferredWidth(80);
        tbl_LibroDiario.getColumnModel().getColumn(9).setPreferredWidth(80);

        tbl_LibroDiario.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblLibro.getColumnCount(); i++) {
            tbl_LibroDiario.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            tcr.setForeground(new Color(59, 123, 50));
            tbl_LibroDiario.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_LibroDiario.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);

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

        grpTipo = new javax.swing.ButtonGroup();
        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        btnAgregarLibro = new javax.swing.JButton();
        pnlAgregarLibro = new javax.swing.JPanel();
        txtDetalle = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        lblId_Bloque = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        rbHaber = new javax.swing.JRadioButton();
        rbDebe = new javax.swing.JRadioButton();
        txtPrecioUnidad = new javax.swing.JTextField();
        jdFecha = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LibroDiario = new Tablas.DiarioTable();
        txtFiltro = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 608;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        add(cbFinca, gridBagConstraints);

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(lblTid, gridBagConstraints);

        btnAgregarLibro.setText("Agregar");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        add(btnAgregarLibro, gridBagConstraints);

        pnlAgregarLibro.setBackground(new java.awt.Color(255, 255, 255));
        pnlAgregarLibro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Agregar Libro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        pnlAgregarLibro.setForeground(new java.awt.Color(59, 123, 50));
        pnlAgregarLibro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDetalle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDetalle.setForeground(new java.awt.Color(59, 123, 50));
        txtDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtDetalle.setCaretColor(new java.awt.Color(59, 123, 50));
        txtDetalle.setFocusCycleRoot(true);
        txtDetalle.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDetalleActionPerformed(evt);
            }
        });
        txtDetalle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDetalleFocusLost(evt);
            }
        });
        txtDetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDetalleKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDetalleKeyReleased(evt);
            }
        });
        pnlAgregarLibro.add(txtDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 660, 40));

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
        pnlAgregarLibro.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, -1));

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
        pnlAgregarLibro.add(btnDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, -1, -1));
        pnlAgregarLibro.add(lblId_Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        cbTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipo.setForeground(new java.awt.Color(59, 123, 50));
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "unidad", "peso", "metro" }));
        cbTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTipo.setEditor(null);
        cbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoActionPerformed(evt);
            }
        });
        pnlAgregarLibro.add(cbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 140, 40));

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(59, 123, 50));
        txtCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtCantidad.setCaretColor(new java.awt.Color(59, 123, 50));
        txtCantidad.setFocusCycleRoot(true);
        txtCantidad.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });
        pnlAgregarLibro.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 40));

        rbHaber.setBackground(new java.awt.Color(255, 255, 255));
        grpTipo.add(rbHaber);
        rbHaber.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbHaber.setForeground(new java.awt.Color(59, 123, 50));
        rbHaber.setText("Haber");
        pnlAgregarLibro.add(rbHaber, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        rbDebe.setBackground(new java.awt.Color(255, 255, 255));
        grpTipo.add(rbDebe);
        rbDebe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbDebe.setForeground(new java.awt.Color(59, 123, 50));
        rbDebe.setText("Debe");
        pnlAgregarLibro.add(rbDebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtPrecioUnidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioUnidad.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Precio/Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPrecioUnidad.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.setFocusCycleRoot(true);
        txtPrecioUnidad.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioUnidadFocusLost(evt);
            }
        });
        txtPrecioUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioUnidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioUnidadKeyReleased(evt);
            }
        });
        pnlAgregarLibro.add(txtPrecioUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 340, 40));

        jdFecha.setBackground(new java.awt.Color(255, 255, 255));
        jdFecha.setForeground(new java.awt.Color(59, 123, 50));
        jdFecha.setDateFormatString("dd/MM/yyyy");
        jdFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jdFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaPropertyChange(evt);
            }
        });
        pnlAgregarLibro.add(jdFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 150, 30));

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        pnlAgregarLibro.add(lbltitle19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(pnlAgregarLibro, gridBagConstraints);

        tbl_LibroDiario.setForeground(new java.awt.Color(59, 123, 50));
        tbl_LibroDiario.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_LibroDiario.setFocusTraversalPolicyProvider(true);
        tbl_LibroDiario.setGridColor(new java.awt.Color(59, 123, 50));
        tbl_LibroDiario.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_LibroDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_LibroDiarioMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_LibroDiario);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 687;
        gridBagConstraints.ipady = 303;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane1, gridBagConstraints);

        txtFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro.setForeground(new java.awt.Color(59, 123, 50));
        txtFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtFiltro.setCaretColor(new java.awt.Color(59, 123, 50));
        txtFiltro.setFocusCycleRoot(true);
        txtFiltro.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFiltroFocusLost(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 710;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(txtFiltro, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        System.out.println("cbFincaActionPerformed cb-->"+idFinca);

        AccionCombo();
    }//GEN-LAST:event_cbFincaActionPerformed

    private void txtDetalleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDetalleFocusLost

    }//GEN-LAST:event_txtDetalleFocusLost

    private void txtDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleKeyPressed

    }//GEN-LAST:event_txtDetalleKeyPressed

    private void txtDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleKeyReleased

    }//GEN-LAST:event_txtDetalleKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        LimpiarFomulario();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void cbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoActionPerformed
        
    }//GEN-LAST:event_cbTipoActionPerformed

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        String areat = txtCantidad.getText();
        String valorsin = areat.indexOf(".") > -1 ? areat.replace(".", "") : areat;
        String dato = Expresiones.procesarSoloNumP(valorsin);
        dato = Utilidades.MascaraMonedaConDecimales(dato);
        txtCantidad.setText(dato);
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtPrecioUnidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioUnidadFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnidadFocusLost

    private void txtPrecioUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnidadKeyPressed

    private void txtPrecioUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioUnidadKeyReleased

    private void jdFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaPropertyChange
        
    }//GEN-LAST:event_jdFechaPropertyChange

    private void tbl_LibroDiarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LibroDiarioMouseReleased
        filaSeleccionada = tbl_LibroDiario.getSelectedRow();
        int cola = tbl_LibroDiario.getSelectedColumn();
        String dato = tbl_LibroDiario.getValueAt(filaSeleccionada, cola).toString();

        if (band == 0) {
            if (dato.equalsIgnoreCase("Modificar")) {
                band = 1;
                datoaModificar = ListaLibroMostrar.get(filaSeleccionada);
                EditarRegistro();
//                objetoVentana = new ModeloVentanaGeneral(
//                    this,
//                    new VistaIngresoPesaje(),
//                    1,
//                    ListaAnimalesMostrar.get(filaSeleccionada)
//                );
//                new VistaGeneral(objetoVentana).setVisible(true);
            } else if (dato.equalsIgnoreCase("Eliminar")) {
                
                
            }
        }
    }//GEN-LAST:event_tbl_LibroDiarioMouseReleased

    private void txtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFiltroFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroFocusLost

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed
        rbDebe.setEnabled(true);
        rbHaber.setEnabled(true);
        EstadoPanelAdd(true);
    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void txtDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDetalleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipo;
    private javax.swing.ButtonGroup grpTipo;
    private javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JPanel pnlAgregarLibro;
    private javax.swing.JRadioButton rbDebe;
    private javax.swing.JRadioButton rbHaber;
    public javax.swing.JTable tbl_LibroDiario;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtDetalle;
    public javax.swing.JTextField txtFiltro;
    public javax.swing.JTextField txtPrecioUnidad;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM fincas\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
        AccionCombo();
    }
    private void EstadoPanelAdd(boolean estado){
        btnAgregarLibro.setEnabled(!estado);
        pnlAgregarLibro.setVisible(estado);
    }
    
    private void AccionCombo() {
        cargarTablaFiltro();
    }
    
    
    public void cargarTablaFiltro() {
        
        if (Integer.parseInt(idFinca) > 0) { 
            listaLibro = (List<Map<String, String>>) controlLibro.ObtenerDatosFiltro(idFinca);
            if (listaLibro.size() > 0) {
                String col = "";
                for (Map.Entry<String, String> entry : listaLibro.get(0).entrySet()) {
                    String key = entry.getKey();
                    String[] split = key.split(Utilidades.SeparadorBusqueda);
                    Map h = new HashMap<String, String>();
                    h.put("nameCol", key);
                    if (split.length > 1) {
                        h.put("tamanio", split[1]);
                    }
                    if (split.length > 2) {
                        h.put("alineacion", split[2]);
                    }
                    PropiedadesColumnas.put(split[0], h);
                    col += (col.equals("") ? "" : "<::>") + split[0];
                }

                NameColumnas = col.split("<::>");

            } else {
                //CERRAR VENTAANA
            }
            MostrarTabla();
        }
    }
    
    

    private void Guardar() {
        String detalle = txtDetalle.getText().trim();
        String cantidad = txtCantidad.getText().trim();
        String precio = txtPrecioUnidad.getText().trim();
        String tipo = ""+cbTipo.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFecha.getCalendar();
        String fechaFormateada = sdf.format(fecha.getTime());
        
        
    }

    private void LimpiarFomulario() {
        EstadoPanelAdd(false);
        rbDebe.setSelected(false);
        rbHaber.setSelected(false);
        datoaModificar = new HashMap<>();
        filaSeleccionada = -1;
    }

    private void MostrarTabla() {
        System.out.println("****************MostrarTabla*****************");
        String filtro = Utilidades.CodificarElemento(txtFiltro.getText());
        System.out.println("filtro--" + filtro);
        ListaLibroMostrar = getFiltroLista(filtro);

        Utilidades.LimpiarTabla(tbl_LibroDiario);
        for (int i = 0; i < ListaLibroMostrar.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTblLibro,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        ListaLibroMostrar.get(i).get("FECHA"),
                        ListaLibroMostrar.get(i).get("DETALLE"),
                        ListaLibroMostrar.get(i).get("CANTIDAD"),
                        ListaLibroMostrar.get(i).get("PRECIO"),
                        ListaLibroMostrar.get(i).get("DEBE"),
                        ListaLibroMostrar.get(i).get("HABER"),
                        ListaLibroMostrar.get(i).get("SALDO"),
                        "Modificar",
                        "Eliminar"
                    }
            );
        }
    }
    
    private List<Map<String, String>> getFiltroLista(String filtro) {
        List<Map<String, String>> retorno = new ArrayList<>();
        System.out.println("***************getFiltroLista*****************" + filtro);
        int b = -1;
        String[] filtros = filtro.isEmpty() ? null : filtro.replace(" ", "<::>").split("<::>");
        String valores = "";
        for (int i = 0; i < listaLibro.size(); i++) {
            b = 1;
            if (filtro.isEmpty()) {
                retorno.add(listaLibro.get(i));
            } else {
                valores = "";
                for (int j = 0; j < NameColumnasFiltro.size(); j++) {
                    System.out.println("NAme-" + j + "->" + NameColumnasFiltro.get(j));
                    String value = listaLibro.get(i).get(NameColumnasFiltro.get(j));
                    valores += "" + value;
                }
                boolean encontro = Expresiones.filtrobusqueda(filtros, valores);
                System.out.println("i-" + i + "-b-" + b);
                if (encontro) {
                    retorno.add(listaLibro.get(i));
                }

            }
        }
        System.out.println("********************retorno --> " + retorno.size() + "***********************");
        return retorno;
    }

    private void EditarRegistro() {
        EstadoPanelAdd(true);
        txtDetalle.setText(datoaModificar.get("DETALLE"));
        txtCantidad.setText(datoaModificar.get("CANTIDAD"));
        txtPrecioUnidad.setText(datoaModificar.get("PRECIO"));
        cbTipo.setSelectedItem(datoaModificar.get("TIPO"));
        rbDebe.setSelected(datoaModificar.get("RADIO").equals("DEBE"));
        rbHaber.setSelected(datoaModificar.get("RADIO").equals("HABER"));
        rbDebe.setEnabled(false);
        rbHaber.setEnabled(false);
        try {
            String fechaSeleccionada = datoaModificar.get("FECHA").toString();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formato.parse(fechaSeleccionada);
            jdFecha.setDate(fecha); 
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la fecha\nDetalle:\n" + ex.getMessage());
        }
        
    }
}
