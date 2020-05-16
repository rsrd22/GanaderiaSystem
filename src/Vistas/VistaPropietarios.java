/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Busqueda.VistaBusqueda;
import Control.ControlBloques;
import Control.ControlHierros;
import Control.ControlPropietarios;
import Control.Retorno;
import Modelo.*;
import Modelo.ModeloGestorBusqueda;
import Modelo.ModeloPropietarios;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
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
public class VistaPropietarios extends javax.swing.JPanel {

    public int enterID = 0, lostID = 0;
    public int banBQD = 0;
    ModeloVentanaGeneral objetoVentana;
    ModeloGestorBusqueda objetoBusqueda;
    private ModeloPropietarios modelo;
    private ModeloVentanaGeneral modeloVentanaGeneral;
    private ControlPropietarios controlPropietario;
    private ControlHierros controlHierro;
    public String tipoId = "";
    public int estadoTID = 0;
    ArrayList<String> lista_tpoID;
    public int id_Propietario;
    public DefaultTableModel modeloTblHierro;
    public String[] EncabezadoTblHierro;
    private ModeloHierros modeloHierro;
    private ArrayList<ModeloHierros> ListamodeloHierros;

    /**
     * Creates new form VistaPropietarios
     */
    public VistaPropietarios() {
        initComponents();
        modelo = new ModeloPropietarios();
        controlPropietario = new ControlPropietarios();
        EncabezadoTblHierro = new String[]{
            "No", "Descripción", "Imagen", "Estado", "Ver", "Modificar", "Eliminar"
        };
        jTabbedPane1.removeTabAt(1);
        //jTabbedPane1.setEnabledAt(1, false);
        CargarTID();
        InicializarTblHierros();
        PropietariosEstadoFomulario(0);
    }

    public VistaPropietarios(ModeloVentanaGeneral modeloVentanaGeneral) {
        initComponents();
        this.modeloVentanaGeneral = modeloVentanaGeneral;
        modelo = new ModeloPropietarios();
        controlPropietario = new ControlPropietarios();
        EncabezadoTblHierro = new String[]{
            "No", "Descripción", "Imagen", "Estado", "Ver", "Modificar", "Eliminar"
        };
        jTabbedPane1.removeTabAt(1);
        CargarTID();
        InicializarTblHierros();
        PropietariosEstadoFomulario(0);
    }

    public void InicializarTblHierros() {
        tbl_Hierro.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblHierro = new DefaultTableModel(EncabezadoTblHierro, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Hierro.setModel(modeloTblHierro);

        tbl_Hierro.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_Hierro.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_Hierro.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Hierro.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbl_Hierro.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl_Hierro.getColumnModel().getColumn(5).setPreferredWidth(80);
        tbl_Hierro.getColumnModel().getColumn(6).setPreferredWidth(80);

        tbl_Hierro.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblHierro.getColumnCount(); i++) {
            tbl_Hierro.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));

            if (i == 2) {
                tcr.setHorizontalAlignment(SwingConstants.RIGHT);

            } else {
                tcr.setHorizontalAlignment(SwingConstants.CENTER);

            }
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Hierro.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Hierro.getTableHeader();

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnDescartar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblTid = new javax.swing.JLabel();
        cbTid = new javax.swing.JComboBox();
        txtId = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Hierro = new javax.swing.JTable();
        btnAgregarHierro = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setForeground(new java.awt.Color(59, 123, 50));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(btnDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 60, 64));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(59, 123, 50));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nombres");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 56, -1));

        txtNombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(59, 123, 50));
        txtNombres.setBorder(null);
        txtNombres.setCaretColor(new java.awt.Color(31, 97, 141));
        txtNombres.setSelectionColor(new java.awt.Color(36, 113, 163));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 260, 30));

        jSeparator4.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 260, 10));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Apellidos");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 70, -1));

        txtApellidos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(59, 123, 50));
        txtApellidos.setBorder(null);
        txtApellidos.setCaretColor(new java.awt.Color(31, 97, 141));
        txtApellidos.setSelectionColor(new java.awt.Color(36, 113, 163));
        jPanel1.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 260, 30));

        jSeparator5.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 140, 260, 10));

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Tipo Identificación ");
        jPanel1.add(lblTid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 150, -1));

        cbTid.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTid.setForeground(new java.awt.Color(59, 123, 50));
        cbTid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Cédula de Ciudadania", "Cédula de Extranjeria", "Tarjeta de Identidad", "Registro Civil", "Número de Identificación Tributario", "Número Unico de Identificación", "Pasaporte" }));
        cbTid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTidActionPerformed(evt);
            }
        });
        jPanel1.add(cbTid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 210, 30));

        txtId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtId.setForeground(new java.awt.Color(59, 123, 50));
        txtId.setBorder(null);
        txtId.setCaretColor(new java.awt.Color(31, 97, 141));
        txtId.setFocusCycleRoot(true);
        txtId.setSelectionColor(new java.awt.Color(36, 113, 163));
        txtId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIdFocusLost(evt);
            }
        });
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 180, 30));

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 180, 10));

        lblId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId.setForeground(new java.awt.Color(59, 123, 50));
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblId.setText("Identificación");
        jPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 100, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(59, 123, 50));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Dirección");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 99, -1));

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(59, 123, 50));
        txtDireccion.setBorder(null);
        txtDireccion.setCaretColor(new java.awt.Color(31, 97, 141));
        txtDireccion.setSelectionColor(new java.awt.Color(36, 113, 163));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 30));

        jSeparator3.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 10));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(59, 123, 50));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Correo");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 99, -1));

        txtCorreo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(59, 123, 50));
        txtCorreo.setBorder(null);
        txtCorreo.setCaretColor(new java.awt.Color(31, 97, 141));
        txtCorreo.setSelectionColor(new java.awt.Color(36, 113, 163));
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 260, 30));

        jSeparator1.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 220, 260, 10));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 60, 64));

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar.png"))); // NOI18N
        btnModificar.setToolTipText("Modificar");
        btnModificar.setBorderPainted(false);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar_over.png"))); // NOI18N
        btnModificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar_over.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 60, 64));

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("Consutar");
        btnConsultar.setBorderPainted(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnConsultar.setName("btnConsultar"); // NOI18N
        btnConsultar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar_over.png"))); // NOI18N
        btnConsultar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar_over.png"))); // NOI18N
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel1.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 60, 64));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar_over.png"))); // NOI18N
        btnEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar_over.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 60, 64));
        jPanel1.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 52, -1, -1));

        jTabbedPane1.addTab("Datos Basicos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_Hierro.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Hierro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_HierroMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Hierro);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 550, 260));

        btnAgregarHierro.setBackground(new java.awt.Color(0, 0, 204));
        btnAgregarHierro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/add.png"))); // NOI18N
        btnAgregarHierro.setToolTipText("Agregar Hierro");
        btnAgregarHierro.setBorder(null);
        btnAgregarHierro.setBorderPainted(false);
        btnAgregarHierro.setContentAreaFilled(false);
        btnAgregarHierro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarHierro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarHierroActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarHierro, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 30, 30));

        jTabbedPane1.addTab("Hierros", jPanel2);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 390));
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        banBQD=0;
        PropietariosEstadoFomulario(0);
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void cbTidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTidActionPerformed

    private void txtIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIdFocusLost
        System.out.println("enterID----->" + enterID);
        if (!txtId.getText().equals("")) {
            if (enterID == 0) {
                lostID = 1;
                String tip = lista_tpoID.get(cbTid.getSelectedIndex());
                String id = txtId.getText();
                ConsultarPropietario(tip + id);
            } else {
                enterID = 0;
            }
        }
    }//GEN-LAST:event_txtIdFocusLost

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            enterID = 1;
            String tip = lista_tpoID.get(cbTid.getSelectedIndex());
            String id = txtId.getText();
            ConsultarPropietario(tip + id);
        }
    }//GEN-LAST:event_txtIdKeyPressed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased
        Expresiones.ProcesarSoloNumerosSinPUNTOS(txtId);
    }//GEN-LAST:event_txtIdKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        if (!txtCorreo.getText().equals("")) {
            if (Expresiones.validarCorreo(txtCorreo)) {

            }
        }
    }//GEN-LAST:event_txtCorreoFocusLost

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tbl_HierroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HierroMouseReleased
        int fila = tbl_Hierro.getSelectedRow();
        int cola = tbl_Hierro.getSelectedColumn();
        if (cola == 5) {// ACTUALIZAR
            modeloHierro = new ModeloHierros();
            modeloHierro = ListamodeloHierros.get(fila);

            objetoVentana = new ModeloVentanaGeneral(this, new VistaHierros(), 1, modeloHierro);
            objetoVentana.setFila(fila);

            new VistaGeneral(objetoVentana).setVisible(true);

        } else if (cola == 6) { //ELIMINAR
            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
            if (resp == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "HAz EL MEtodo ");
            }
        } else if (cola == 4) { //VER IMAGEN

        }

    }//GEN-LAST:event_tbl_HierroMouseReleased

    private void btnAgregarHierroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarHierroActionPerformed
        btnAgregarHierro.setEnabled(false);

        modeloHierro = new ModeloHierros();
        modeloHierro.setId_propietario("" + id_Propietario);
        modeloHierro.setId("0");

        objetoVentana = new ModeloVentanaGeneral(this, new VistaHierros(), 1, modeloHierro);
        objetoVentana.setFila(-1);

        new VistaGeneral(objetoVentana).setVisible(true);
    }//GEN-LAST:event_btnAgregarHierroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarHierro;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbTid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lblID;
    public javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTid;
    private javax.swing.JTable tbl_Hierro;
    public javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables

    //<editor-fold defaultstate="collapsed" desc="Metodos CRUD">
    public void Guardar() {
        Map<String, String> names = new HashMap<>();
        modelo.setId("" + id_Propietario);
        modelo.setTipo_identificacion(lista_tpoID.get(cbTid.getSelectedIndex()));
        modelo.setIdentificacion(txtId.getText().trim());

        names = Utilidades.SplitNames(txtNombres.getText().trim());
        modelo.setPrimer_nombre(names.get("PRIMERO"));
        modelo.setSegundo_nombre(names.get("SEGUNDO"));

        names = Utilidades.SplitNames(txtApellidos.getText().trim());
        modelo.setPrimer_apellido(names.get("PRIMERO"));
        modelo.setSegundo_apellido(names.get("SEGUNDO"));

        modelo.setDireccion(txtDireccion.getText().trim());
        modelo.setCorreo(txtCorreo.getText().trim());
        modelo.setFecha("NOW()");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setEstado("Activo");

        int ret = -1;
        if (id_Propietario == 0) {//INSERT
            ret = controlPropietario.Guardar(modelo);
        } else {
            ret = controlPropietario.Actualizar(modelo);
        }

        if (ret == Retorno.EXITO) {
            JOptionPane.showMessageDialog(this, "La operación se realizo exitosamente.");
            PropietariosEstadoFomulario(0);
            banBQD=0;
//            if (modeloVentanaGeneral.getPanelPadre() instanceof VistaFincas) {
//                ArrayList<ModeloPropietarios> propietarios = new ArrayList<>();
//                propietarios = (ArrayList<ModeloPropietarios>) controlPropietario.ObtenerDatosFiltro(modelo.getTipo_identificacion() + modelo.getIdentificacion());
//                if (propietarios.size() > 0) {
//                    modelo = propietarios.get(0);
//                }
//
//                ((VistaFincas) modeloVentanaGeneral.getPanelPadre()).RetornoVistaGeneral(modeloVentanaGeneral, modelo);
//                ((VistaGeneral) modeloVentanaGeneral.getFrameVentana()).dispose();
//            } else {
//                PropietariosEstadoFomulario(0);
//            }

        }

    }

    public void Modificar() {
        PropietariosEstadoFomulario(2);
    }

    public void Consultar() {
        if (banBQD == 0) {
            banBQD = 1;
            //new ventanaBusquedaPaciente(1, "IDENTIFICACION:-:NOMBRE", estadoch, this);
            objetoBusqueda = new ModeloGestorBusqueda(this, "BQD_PROP", 0);
            new VistaBusqueda(objetoBusqueda);
        }
    }

    public void Eliminar() {
        
    }
    //</editor-fold>

    public void RetornoBusqueda(ModeloGestorBusqueda objeto, Map<String, String> retorno) {

        if (objeto.getOpcion() == 0) {// DEFAULT VISTA__VENTANA__
            if (retorno.containsKey("ID")) {
                System.out.println("////" + retorno.get("ID"));
            }
            id_Propietario = Integer.parseInt(retorno.get("ID"));
            System.out.println("asdfasdfasdfadsa--->" + id_Propietario);

            ArrayList<ModeloPropietarios> lista = (ArrayList<ModeloPropietarios>) controlPropietario.ObtenerDatosKey("" + id_Propietario);
            System.out.println("lista--->" + lista.size());
            modelo = lista.get(0);

            tipoId = "" + modelo.getTipo_identificacion();
            EstadoTIdentificacion();
            //cbTid.setSelectedItem(""+modelo.getTipo_identificacion());
            txtId.setText("" + modelo.getIdentificacion());
            txtNombres.setText("" + modelo.getPrimer_nombre() + " " + modelo.getSegundo_nombre());
            txtApellidos.setText("" + modelo.getPrimer_apellido() + " " + modelo.getSegundo_apellido());
            txtDireccion.setText("" + modelo.getDireccion());
            txtCorreo.setText("" + modelo.getCorreo());
            PropietariosEstadoFomulario(3);
//
//            ListamodeloHierros = (ArrayList<ModeloHierros>) controlPropietario.ObtenerHierrosxPropietario("" + id_Propietario);
//
//            for (int i = 0; i < ListamodeloHierros.size(); i++) {
//                agregarFilaTabla(modeloTblHierro,
//                        new Object[]{
//                            tbl_Hierro.getRowCount() + 1,
//                            ListamodeloHierros.get(i).getDescripcion(),
//                            ListamodeloHierros.get(i).getNombre_imagen(),
//                            ListamodeloHierros.get(i).getEstado(),
//                            "Ver",
//                            "Modificar",
//                            "Eliminar"
//                        });
//            }
        }
    }

    public void ConsultarPropietario(String llave) {

        ArrayList<ModeloPropietarios> lista = (ArrayList<ModeloPropietarios>) controlPropietario.ObtenerDatosxLlave("" + llave);
        if (lista.size() > 0) {
            System.out.println("lista--->" + lista.size());
            modelo = lista.get(0);
            id_Propietario = Integer.parseInt(modelo.getId());
            tipoId = "" + modelo.getTipo_identificacion();
            EstadoTIdentificacion();
            //cbTid.setSelectedItem(""+modelo.getTipo_identificacion());
            txtId.setText("" + modelo.getIdentificacion());
            txtNombres.setText("" + modelo.getPrimer_nombre() + " " + modelo.getSegundo_nombre());
            txtApellidos.setText("" + modelo.getPrimer_apellido() + " " + modelo.getSegundo_apellido());
            txtDireccion.setText("" + modelo.getDireccion());
            txtCorreo.setText("" + modelo.getCorreo());

            ListamodeloHierros = (ArrayList<ModeloHierros>) controlPropietario.ObtenerHierrosxPropietario("" + id_Propietario);

            for (int i = 0; i < ListamodeloHierros.size(); i++) {
                agregarFilaTabla(modeloTblHierro,
                        new Object[]{
                            tbl_Hierro.getRowCount() + 1,
                            ListamodeloHierros.get(i).getDescripcion(),
                            ListamodeloHierros.get(i).getNombre_imagen(),
                            ListamodeloHierros.get(i).getEstado(),
                            "Ver",
                            "Modificar",
                            "Eliminar"
                        });
            }
        } else {
            PropietariosEstadoFomulario(1);
        }
    }

    public void RetornoVistaGeneral(ModeloVentanaGeneral objeto, Object retorno) {

        if (objeto.getOpcion() == 1) {// VISTA BLOQUES
            modeloHierro = (ModeloHierros) retorno;

            //"No","Descripción", "Imagen", "Estado", "Ver", "Modificar", "Eliminar"
            if (objeto.getFila() > -1) {//ESTA EN TABLA ACTUALIZAR
                ListamodeloHierros.set(objeto.getFila(), modeloHierro);
                tbl_Hierro.setValueAt(modeloHierro.getDescripcion(), objeto.getFila(), 1);
                tbl_Hierro.setValueAt(modeloHierro.getNombre_imagen(), objeto.getFila(), 2);
                tbl_Hierro.setValueAt(modeloHierro.getEstado(), objeto.getFila(), 3);

            } else {
                ListamodeloHierros.add(modeloHierro);
                agregarFilaTabla(modeloTblHierro,
                        new Object[]{
                            tbl_Hierro.getRowCount() + 1,
                            modeloHierro.getDescripcion(),
                            modeloHierro.getNombre_imagen(),
                            modeloHierro.getEstado(),
                            "Ver",
                            "Modificar",
                            "Eliminar"
                        });
            }

        }
    }

    public void PropietariosEstadoFomulario(int opc) {
        PropietariosEstadoBotones(opc);
        switch (opc) {
            case 0: {//DESCARTAR
                txtId.setText("");
                cbTid.setSelectedIndex(0);
                txtNombres.setText("");
                txtApellidos.setText("");
                txtDireccion.setText("");
                txtCorreo.setText("");
                lblID.setText("");
                tipoId = "";
                estadoTID = 0;
                id_Propietario = 0;

                txtId.setEnabled(true);
                cbTid.setEnabled(true);
                txtNombres.setEnabled(false);
                txtApellidos.setEnabled(false);
                txtDireccion.setEnabled(false);
                txtCorreo.setEnabled(false);

                break;
            }
            case 1: {
                txtId.setEnabled(false);
                cbTid.setEnabled(false);

                txtNombres.setEnabled(true);
                txtApellidos.setEnabled(true);
                txtDireccion.setEnabled(true);
                txtCorreo.setEnabled(true);
                txtNombres.requestFocus();
                break;
            }
            case 2: {
                txtId.setEnabled(estadoTID == 1);
                cbTid.setEnabled(estadoTID == 1);
                txtNombres.setEnabled(true);
                txtApellidos.setEnabled(true);
                txtDireccion.setEnabled(true);
                txtCorreo.setEnabled(true);
                txtNombres.requestFocus();
                break;
            }
            case 3: {
                txtId.setEnabled(false);
                cbTid.setEnabled(false);
                txtNombres.setEnabled(false);
                txtApellidos.setEnabled(false);
                txtDireccion.setEnabled(false);
                txtCorreo.setEnabled(false);
                break;
            }
        }
    }

    public void PropietariosEstadoBotones(int opc) {
        switch (opc) {
            case 0: {//DESCARTAR         
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(false);
                btnDescartar.setEnabled(true);
                btnEliminar.setEnabled(false);
                btnConsultar.setEnabled(true);
                break;
            }
            case 1: {//NUEVO
                btnGuardar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnDescartar.setEnabled(true);
                btnEliminar.setEnabled(false);
                btnConsultar.setEnabled(false);
                break;
            }
            case 2: {//MODIFICAR
                btnGuardar.setEnabled(true);
                btnModificar.setEnabled(false);
                btnDescartar.setEnabled(true);
                btnEliminar.setEnabled(false);
                btnConsultar.setEnabled(false);
                break;
            }
            case 3: {//CONSULTADO
                btnGuardar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnDescartar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnConsultar.setEnabled(false);
                break;
            }
        }
    }

    public void CargarTID() {
        lista_tpoID = new ArrayList<>();
        //Seleccionar, CC, CE, TI, RC, NIT, NUI, PAS
        lista_tpoID.add("-1");
        lista_tpoID.add("CC");
        lista_tpoID.add("CE");
        lista_tpoID.add("TI");
        lista_tpoID.add("RC");
        lista_tpoID.add("NIT");
        lista_tpoID.add("NUI");
        lista_tpoID.add("PAS");
    }

    public void EstadoTIdentificacion() {
        System.out.println("******************EstadoTIdentificacion***************" + tipoId + "**********");
        for (int i = 0; i < lista_tpoID.size(); i++) {
            System.out.println("lista_tpoID.get(" + i + ")-->" + lista_tpoID.get(i));
            if (lista_tpoID.get(i).equals(tipoId)) {
                cbTid.setSelectedIndex(i);
                break;
            }
        }
        System.out.println("**********************END EstadoTIdentificacion*************************");
    }

    private void agregarFilaTabla(DefaultTableModel modelotbl, Object[] fila) {
        modelotbl.addRow(fila);
    }

}
