/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Inventario;

import Control.ControlGeneral;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MERRY
 */
public class VistaLibroDiarioCopia extends javax.swing.JPanel {

    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public DefaultTableModel modeloTblLibro;
    public String[] EncabezadoTblLibro;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaLibro;
    public String[] NameColumnas;
    public ArrayList<String> NameColumnasFiltro;
    public int idModulo = 1990;
    //<editor-fold defaultstate="collapsed" desc="filtro">
    private ListModel modeloGral;
    //</editor-fold>

    /**
     * Creates new form VistaLibroDiario
     */
    public VistaLibroDiarioCopia() {
        initComponents();
        //<editor-fold defaultstate="collapsed" desc="filtro">
        panelFiltro.setVisible(false);
        btnBorrar.setEnabled(false);
        modeloGral = listaFiltro.getModel();
//</editor-fold>

        //Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        idFinca = "";
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("NUMERO_ANIMAL");
        NameColumnasFiltro.add("NUMERO_MAMA");
        NameColumnasFiltro.add("GENERO");
        NameColumnasFiltro.add("FECHA_NACIMIENTO");
        NameColumnasFiltro.add("PESO");
        NameColumnasFiltro.add("DESC_HIERRO");
        NameColumnasFiltro.add("CAPADO");
        NameColumnasFiltro.add("GRUPO");
        NameColumnasFiltro.add("FINCA");
        NameColumnasFiltro.add("BLOQUE");
        NameColumnasFiltro.add("FINCA");
        NameColumnasFiltro.add("EST");
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
        lblTid = new javax.swing.JLabel();
        pnlAgregarLibro = new javax.swing.JPanel();
        panelFiltro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaFiltro = new javax.swing.JList();
        lblId_Bloque = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        txtPrecioUnidad = new javax.swing.JTextField();
        jdFechaPesaje = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        lbltitle1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        txtDetalle = new javax.swing.JTextField();
        btnBorrar = new javax.swing.JButton();
        chkPesable = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LibroDiario = new Tablas.DiarioTable();
        txtFiltro = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cbFinca = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();

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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lblTid, gridBagConstraints);

        pnlAgregarLibro.setBackground(new java.awt.Color(255, 255, 255));
        pnlAgregarLibro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Agregar Libro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        pnlAgregarLibro.setForeground(new java.awt.Color(59, 123, 50));
        pnlAgregarLibro.setLayout(new java.awt.GridBagLayout());

        panelFiltro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelFiltro.setMinimumSize(new java.awt.Dimension(384, 80));
        panelFiltro.setPreferredSize(new java.awt.Dimension(384, 80));
        panelFiltro.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(35, 80));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(35, 80));

        listaFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listaFiltro.setForeground(new java.awt.Color(59, 123, 50));
        listaFiltro.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "maria", "laura", "patricia", "martha", "yolanda", "aura", "esther", "camila", "lucia", "lucas", "mark", "augusto", "pepito perez", "camilo restrepo", "luisa fernanda" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listaFiltro.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listaFiltro.setMaximumSize(new java.awt.Dimension(33, 400));
        listaFiltro.setMinimumSize(new java.awt.Dimension(33, 400));
        listaFiltro.setPreferredSize(new java.awt.Dimension(33, 400));
        listaFiltro.setSelectionBackground(new java.awt.Color(59, 123, 50));
        listaFiltro.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaFiltroValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaFiltro);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelFiltro.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        pnlAgregarLibro.add(panelFiltro, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlAgregarLibro.add(lblId_Bloque, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = -3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        pnlAgregarLibro.add(cbTipo, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        pnlAgregarLibro.add(txtCantidad, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 15);
        pnlAgregarLibro.add(txtPrecioUnidad, gridBagConstraints);

        jdFechaPesaje.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaPesaje.setForeground(new java.awt.Color(59, 123, 50));
        jdFechaPesaje.setDateFormatString("dd/MM/yyyy");
        jdFechaPesaje.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jdFechaPesaje.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaPesajePropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 0);
        pnlAgregarLibro.add(jdFechaPesaje, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        pnlAgregarLibro.add(lbltitle19, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(btnGuardar, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel2.add(btnDescartar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 15);
        pnlAgregarLibro.add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 15, 0};
        jPanel3Layout.rowHeights = new int[] {0};
        jPanel3.setLayout(jPanel3Layout);

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        grpTipo.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(59, 123, 50));
        jRadioButton1.setText("Haber");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButton1, gridBagConstraints);

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        grpTipo.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(59, 123, 50));
        jRadioButton2.setText("Debe");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jRadioButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        pnlAgregarLibro.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Detalle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(lbltitle1, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jSeparator6, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        txtDetalle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDetalle.setForeground(new java.awt.Color(59, 123, 50));
        txtDetalle.setBorder(null);
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(txtDetalle, gridBagConstraints);

        btnBorrar.setBackground(new java.awt.Color(255, 255, 255));
        btnBorrar.setForeground(new java.awt.Color(255, 255, 255));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/borrar.png"))); // NOI18N
        btnBorrar.setToolTipText("Borrar detalle");
        btnBorrar.setBorder(null);
        btnBorrar.setBorderPainted(false);
        btnBorrar.setContentAreaFilled(false);
        btnBorrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBorrar.setFocusPainted(false);
        btnBorrar.setMaximumSize(new java.awt.Dimension(30, 30));
        btnBorrar.setMinimumSize(new java.awt.Dimension(30, 30));
        btnBorrar.setName("btnBorrar"); // NOI18N
        btnBorrar.setPreferredSize(new java.awt.Dimension(30, 30));
        btnBorrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/borrar.png"))); // NOI18N
        btnBorrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/borrar.png"))); // NOI18N
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(btnBorrar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        pnlAgregarLibro.add(jPanel4, gridBagConstraints);

        chkPesable.setBackground(new java.awt.Color(255, 255, 255));
        chkPesable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkPesable.setForeground(new java.awt.Color(59, 123, 50));
        chkPesable.setText("Inventariable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        pnlAgregarLibro.add(chkPesable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtFiltro, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(cbFinca, gridBagConstraints);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnAgregar.setName("btnAgregar"); // NOI18N
        btnAgregar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30_over.png"))); // NOI18N
        btnAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30_over.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btnAgregar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        System.out.println("cbFincaActionPerformed cb-->" + idFinca);

        AccionCombo();
    }//GEN-LAST:event_cbFincaActionPerformed

    private void txtDetalleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDetalleFocusLost

    }//GEN-LAST:event_txtDetalleFocusLost

    private void txtDetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleKeyPressed

    }//GEN-LAST:event_txtDetalleKeyPressed

    private void txtDetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDetalleKeyReleased
        //<editor-fold defaultstate="collapsed" desc="filtro">
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnBorrar.setEnabled(true);
            panelFiltro.setVisible(false);
            return;
        }

        buscarElementos(txtDetalle, panelFiltro, listaFiltro);
//</editor-fold>
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

    private void jdFechaPesajePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaPesajePropertyChange

    }//GEN-LAST:event_jdFechaPesajePropertyChange

    private void tbl_LibroDiarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LibroDiarioMouseReleased
//        filaSeleccionada = tbl_LibroDiario.getSelectedRow();
//        int cola = tbl_LibroDiario.getSelectedColumn();
//        String dato = tbl_LibroDiario.getValueAt(filaSeleccionada, cola).toString();
//
//        if (band == 0) {
//            if (dato.equalsIgnoreCase("PESAJE") && tbl_LibroDiario.getValueAt(filaSeleccionada, cola + 1).toString().isEmpty()) {
//                tbl_LibroDiario.setValueAt("*", filaSeleccionada, cola + 1);
//                band = 1;
//                if (fechaAnterior.equals("")) {
//                    fechaAnterior = fechaFiltro;
//                }
//                objetoVentana = new ModeloVentanaGeneral(
//                    this,
//                    new VistaIngresoPesaje(),
//                    1,
//                    ListaAnimalesMostrar.get(filaSeleccionada)
//                );
//                new VistaGeneral(objetoVentana).setVisible(true);
//            } else if (dato.equalsIgnoreCase("*")) {
//                band = 1;
//                if (fechaAnterior.equals("")) {
//                    fechaAnterior = fechaFiltro;
//                }
//                objetoVentana = new ModeloVentanaGeneral(
//                    this,
//                    new VistaIngresoPesaje(),
//                    2,
//                    ListaAnimalesMostrar.get(filaSeleccionada)
//                );
//                new VistaGeneral(objetoVentana).setVisible(true);
//            }
//        }
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

    private void txtDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDetalleActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        //<editor-fold defaultstate="collapsed" desc="filtro">
        txtDetalle.setText("");
        panelFiltro.setVisible(false);
//</editor-fold>
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void listaFiltroValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaFiltroValueChanged
        //<editor-fold defaultstate="collapsed" desc="filtro">
        if (listaFiltro.getSelectedValue() != null) {
            txtDetalle.setText(listaFiltro.getSelectedValue().toString());
            panelFiltro.setVisible(false);
        }
//</editor-fold>
    }//GEN-LAST:event_listaFiltroValueChanged

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        EstadoPanelAdd(true);
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipo;
    private javax.swing.JCheckBox chkPesable;
    private javax.swing.ButtonGroup grpTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator6;
    public com.toedter.calendar.JDateChooser jdFechaPesaje;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    public javax.swing.JLabel lbltitle1;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JList listaFiltro;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel pnlAgregarLibro;
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

    private void EstadoPanelAdd(boolean estado) {
        btnAgregarLibro.setEnabled(!estado);
        pnlAgregarLibro.setVisible(estado);
    }

    private void AccionCombo() {
        cargarTablaFiltro();
    }

//    SELECT DATE_FORMAT(lbro.`fecha_libro`, '5d/%m/%Y') AS FECHA,lbro.`detalle` AS DETALLE,
//lbro.`id_producto` AS ID_PRODUCTO, lbro.`cantidad` AS CANTIDAD, lbro.`precioxunidad` AS PRECIO, 
//lbro.debe AS DEBE, lbro.`haber` AS HABER, lbro.`saldo` AS SALDO
//FROM `libro_diario` lbro
//WHERE lbro.`id_finca` = ''
//ORDER BY lbro.`fecha_libro` ASC
    public void cargarTablaFiltro() {

//        if (Integer.parseInt(idFinca) > 0) { 
//            listaLibro = (List<Map<String, String>>) controlAnimales.ObtenerDatosAnimalesPesables(idFinca, idTipoAnimal, fechaFiltro);
//            if (ListaAnimales.size() > 0) {
//                String col = "";
//                for (Map.Entry<String, String> entry : ListaAnimales.get(0).entrySet()) {
//                    String key = entry.getKey();
//                    String[] split = key.split(Utilidades.SeparadorBusqueda);
//                    Map h = new HashMap<String, String>();
//                    h.put("nameCol", key);
//                    if (split.length > 1) {
//                        h.put("tamanio", split[1]);
//                    }
//                    if (split.length > 2) {
//                        h.put("alineacion", split[2]);
//                    }
//                    PropiedadesColumnas.put(split[0], h);
//                    col += (col.equals("") ? "" : "<::>") + split[0];
//                }
//
//                NameColumnas = col.split("<::>");
//
//            } else {
//                //CERRAR VENTAANA
//            }
//            MostrarTabla();
//        }
    }

    private void Guardar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void LimpiarFomulario() {
        EstadoPanelAdd(false);
    }
    
    //<editor-fold defaultstate="collapsed" desc="filtro">
    private void buscarElementos(JTextField txtFiltro, JPanel panelFiltro, JList listaFiltro) {
        String texto = txtFiltro.getText();
        if (texto.length() == 0) {
            panelFiltro.setVisible(false);
            btnBorrar.setEnabled(false);
            return;
        }

        ListModel modelo = modeloGral;
        DefaultListModel modeloFiltro = new DefaultListModel();
        System.out.println("----------------elementos filtrados------------------");
        for (int i = 0; i < modelo.getSize(); i++) {
            if (modelo.getElementAt(i).toString().toLowerCase().contains(texto.toLowerCase())) {
                modeloFiltro.addElement(modelo.getElementAt(i));
                System.out.println(modelo.getElementAt(i));
            }
        }

        listaFiltro.setModel(modeloFiltro);
        listaFiltro.setBounds(0, 0, listaFiltro.getWidth(), modeloFiltro.getSize() * 8);
        panelFiltro.setVisible(modeloFiltro.getSize() > 0);
        btnBorrar.setEnabled(txtFiltro.getText().length() > 0);
    }
//</editor-fold>
}
