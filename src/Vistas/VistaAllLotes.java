/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Control.ControLotes;
import Control.ControlBloques;
import Control.ControlFincas;
import Control.ControlGeneral;
import Control.ControlMultiComboBox;
import Modelo.ModeloBloques;
import Modelo.ModeloLotes;
import Modelo.ModeloOpcionesMultiples;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MERRY
 */
public class VistaAllLotes extends javax.swing.JPanel {
    private ControlFincas controlFinca;
    private ControLotes controlLote;
    private ModeloLotes  modeloLotes;
    private ArrayList<ModeloLotes>  ListamodeloLotes;
    public DefaultTableModel modeloTblLotes;
    public String[] EncabezadoTblLotes; 
    public String[] idsFuentesAux; 
    public ArrayList<Map<String, String>> listaActualizarFuentes; 
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String AreaBloque;
    public int fila;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaBloques;
    public List<Map<String, String>> listaFuentesHidricas;
    
    public ArrayList<ModeloOpcionesMultiples> ListaDatosMultiple = new ArrayList<>();
    public ArrayList<ModeloOpcionesMultiples> ListaSeleccionados = new ArrayList<>();
    public ArrayList<JPanel> ListaPnlOpciones= new ArrayList<>();
    public JPanel pnlOpcionesAux = new JPanel();
    public boolean ban = false;
    public ControlMultiComboBox obj;
    public DefaultListModel modlistFuentes;
        
    /**
     * Creates new form VistaAllLotes
     */
    
    public VistaAllLotes() {
        initComponents();
        lstFuentesHidricas.setSelectionModel(new DefaultListSelectionModel() {
            private int i0 = -1;
            private int i1 = -1;

            public void setSelectionInterval(int index0, int index1) {
                if(i0 == index0 && i1 == index1){
                    if(getValueIsAdjusting()){
                         setValueIsAdjusting(false);
                         setSelection(index0, index1);
                    }
                }else{
                    i0 = index0;
                    i1 = index1;
                    setValueIsAdjusting(false);
                    setSelection(index0, index1);
                }
            }
            private void setSelection(int index0, int index1){
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });
        controlFinca = new ControlFincas();
        controlLote = new ControLotes();
        ListamodeloLotes = new ArrayList<>();
        listaActualizarFuentes = new ArrayList<>();
        EncabezadoTblLotes = new String[]{
            "No","Bloque", "Número", "Área Total", "Fuente Hidrica", "Modificar", "Eliminar"
        };
        lblId_Bloque.setVisible(false);
        modeloLotes = new ModeloLotes();
        modlistFuentes = new DefaultListModel();
        idFinca = "";
        AreaBloque = "";
        fila = -1;
        LimpiarFomulario();
        CargarListaFuentesHidricas();
        CargarListaFincas();
        
        
        InicializarTblLotes();
        
    }
    
    public void InicializarTblLotes() {
        tbl_Lotes.setDefaultRenderer(Object.class, new TablaRender());
        
        modeloTblLotes = new DefaultTableModel(EncabezadoTblLotes, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Lotes.setModel(modeloTblLotes);

        tbl_Lotes.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_Lotes.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_Lotes.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Lotes.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_Lotes.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_Lotes.getColumnModel().getColumn(5).setPreferredWidth(80);
        tbl_Lotes.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        tbl_Lotes.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblLotes.getColumnCount(); i++) {
            tbl_Lotes.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            
//            if(i == 3 ){
//                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//               
//            }else{
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                
//            }
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Lotes.getColumnModel().getColumn(i).setCellRenderer(tcr);
            
        }
        JTableHeader header = tbl_Lotes.getTableHeader();

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        pnlAgregarLote = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lbltitle1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        lblId_Bloque = new javax.swing.JLabel();
        cbBloque = new javax.swing.JComboBox();
        lblTid1 = new javax.swing.JLabel();
        lblTid2 = new javax.swing.JLabel();
        lbltitle3 = new javax.swing.JLabel();
        txtAreaT = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstFuentesHidricas = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Lotes = new javax.swing.JTable();

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

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
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 20);
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
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        add(lblTid, gridBagConstraints);

        pnlAgregarLote.setBackground(new java.awt.Color(255, 255, 255));
        pnlAgregarLote.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Agregar Lote", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        pnlAgregarLote.setForeground(new java.awt.Color(59, 123, 50));
        pnlAgregarLote.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 180;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 35, 0, 0);
        pnlAgregarLote.add(txtNumero, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 179;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 35, 0, 0);
        pnlAgregarLote.add(jSeparator6, gridBagConstraints);

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 35, 0, 0);
        pnlAgregarLote.add(lbltitle1, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setText("HA");
        jLabel13.setToolTipText("Hectárea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        pnlAgregarLote.add(jLabel13, gridBagConstraints);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_2.png"))); // NOI18N
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
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = -25;
        gridBagConstraints.ipady = -9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnlAgregarLote.add(btnGuardar, gridBagConstraints);

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
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = -59;
        gridBagConstraints.ipady = -43;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        pnlAgregarLote.add(btnDescartar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlAgregarLote.add(lblId_Bloque, gridBagConstraints);

        cbBloque.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbBloque.setForeground(new java.awt.Color(59, 123, 50));
        cbBloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBloqueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 175;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 0, 0);
        pnlAgregarLote.add(cbBloque, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Bloque");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 108;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 25, 0, 0);
        pnlAgregarLote.add(lblTid1, gridBagConstraints);

        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(59, 123, 50));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid2.setText("Fuente Hidrica");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 30, 0, 20);
        pnlAgregarLote.add(lblTid2, gridBagConstraints);

        lbltitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle3.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle3.setText("Area Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        pnlAgregarLote.add(lbltitle3, gridBagConstraints);

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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 215;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlAgregarLote.add(txtAreaT, gridBagConstraints);

        jSeparator8.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 214;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlAgregarLote.add(jSeparator8, gridBagConstraints);

        lstFuentesHidricas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lstFuentesHidricas.setForeground(new java.awt.Color(59, 123, 50));
        lstFuentesHidricas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstFuentesHidricas.setMaximumSize(new java.awt.Dimension(39, 65));
        lstFuentesHidricas.setMinimumSize(new java.awt.Dimension(39, 65));
        lstFuentesHidricas.setName(""); // NOI18N
        lstFuentesHidricas.setPreferredSize(new java.awt.Dimension(39, 65));
        lstFuentesHidricas.setSelectionBackground(new java.awt.Color(59, 123, 50));
        jScrollPane3.setViewportView(lstFuentesHidricas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 107;
        gridBagConstraints.ipady = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 10);
        pnlAgregarLote.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        add(pnlAgregarLote, gridBagConstraints);

        tbl_Lotes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Lotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_LotesMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Lotes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 617;
        gridBagConstraints.ipady = 233;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 17, 20);
        add(jScrollPane2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        System.out.println("cbFincaActionPerformed cb-->"+idFinca);
        
        AccionCombo();
    }//GEN-LAST:event_cbFincaActionPerformed

    private void AccionCombo(){
        Utilidades.LimpiarTabla(tbl_Lotes);
        modeloLotes.setId_finca(""+idFinca);
        if(Integer.parseInt(idFinca)>0){
            ListamodeloLotes = (ArrayList<ModeloLotes>) controlFinca.ObtenerLotesxFinca(""+idFinca);
            CargarListaBloques();
            System.out.println("");
            for(int i =0; i < ListamodeloLotes.size(); i++){
                String f = "";
                for(String d:ListamodeloLotes.get(i).getFuente_Hidrica()){
                    f += (f.equals("")?"":", ")+d;
                }
                
                agregarFilaTabla(modeloTblLotes, 
                        new Object[]{
                            tbl_Lotes.getRowCount()+1,
                            "Bloque "+ListamodeloLotes.get(i).getNumero_bloque(),
                            ListamodeloLotes.get(i).getNumero(),
                            Utilidades.MascaraMonedaConDecimales(ListamodeloLotes.get(i).getArea().replace(".", ",")),
                            f,
                            "Modificar",
                            "Eliminar"
                        });
            }
        }else{
            Utilidades.LimpiarTabla(tbl_Lotes);
        }
    }
    
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

    private void cbBloqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBloqueActionPerformed
        if(listaBloques.size()>0 && cbBloque.getItemCount()>0){
            AreaBloque = listaBloques.get(cbBloque.getSelectedIndex()).get("AREA");
        }
    }//GEN-LAST:event_cbBloqueActionPerformed

    private void tbl_LotesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LotesMouseReleased
        int fila = tbl_Lotes.getSelectedRow();
        int cola = tbl_Lotes.getSelectedColumn();
        if(cola == 5){// ACTUALIZAR
            this.fila = fila;
            modeloLotes = ListamodeloLotes.get(fila);
            modeloLotes.setId_finca(""+idFinca);
            
            int[] indices = getIndicesLista(); 
            //System.arraycopy(modeloLotes.getId_fuente_hidrica(), 0, idsFuentesAux, 0, modeloLotes.getId_fuente_hidrica().length);
            //idsFuentesAux = modeloLotes.getId_fuente_hidrica();
            
            cbBloque.setSelectedIndex(getIndexLista(modeloLotes.getId_bloque(), listaBloques));
            //cbFuenteHidrica.setSelectedIndex(getIndexLista(modeloLotes.getId_fuente_hidrica(), listaFuentesHidricas));
            lstFuentesHidricas.setSelectedIndices(indices);
            txtNumero.setText(""+modeloLotes.getNumero());
            txtAreaT.setText(""+Utilidades.MascaraMonedaConDecimales(modeloLotes.getArea().replace(".", ",")));
            txtNumero.requestFocusInWindow();
            
//            objetoVentana = new ModeloVentanaGeneral(this, new VistaLotes(), 3,modeloLotes);
//            objetoVentana.setFila(fila);
//
//            new VistaGeneral(objetoVentana).setVisible(true);

        }else if(cola == 6){ //ELIMINAR
            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
            if(resp == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this, "HAz EL MEtodo ");
            }
        }
    }//GEN-LAST:event_tbl_LotesMouseReleased

    public int getIndexLista(String id, List<Map<String, String>> lista){
        int ind = -1;
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).get("ID").equals(""+id)){
                ind = i;
                break;
            }
        }
        return ind;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbBloque;
    public javax.swing.JComboBox cbFinca;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid2;
    public javax.swing.JLabel lbltitle1;
    public javax.swing.JLabel lbltitle3;
    private javax.swing.JList lstFuentesHidricas;
    private javax.swing.JPanel pnlAgregarLote;
    private javax.swing.JTable tbl_Lotes;
    public javax.swing.JTextField txtAreaT;
    public javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n" +
                                                "FROM `fincas`\n"+
                                                "/*UNION \n"+
                                                "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");
         
        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
       
    }
    
    private void CargarListaBloques() {
        listaBloques = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION, '0' as AREA\n" +
                                                "UNION\n" +
                                                "SELECT id AS ID, CONCAT('Bloque ', numero) AS DESCRIPCION, area as AREA\n" +
                                                "FROM `bloques` \n" +
                                                "WHERE id_finca = '"+idFinca+"'");
         
        Utilidades.LlenarComboBox(cbBloque, listaBloques, "DESCRIPCION");
       
    }
    
    private void CargarListaFuentesHidricas() {
        listaFuentesHidricas = controlgen.GetComboBox("SELECT id AS ID, descripcion AS DESCRIPCION FROM fuentes_hidricas WHERE estado = 'Activo'");
        LlenarListaFuentes();
    }
    
    private void agregarFilaTabla(DefaultTableModel modelotbl, Object[] fila) {
        modelotbl.addRow(fila);
    }

    private void Guardar() {
        String id_Bloque = ""+listaBloques.get(cbBloque.getSelectedIndex()).get("ID");
        
        String AreaT = txtAreaT.getText().trim().replace(".", "").replace(",", ".");
        double AcuAreaLotesxBloques = getAcumuladoArea(id_Bloque, modeloLotes.getId());
        double AreaTo = Double.parseDouble(AreaT);
        
        System.out.println("AcuAreaLotesxBloques--->"+AcuAreaLotesxBloques);
        System.out.println("AreaTo--->"+AreaTo);
        System.out.println("AreaBloque--->"+AreaBloque);
        
        String[] idsFuentes = getIDs();
        String[] Fuentes = getValues();
        if(modeloLotes.getId().equals("0")){
            boolean Valnumero = controlLote.VerificarNumeroLotexBloque(id_Bloque, txtNumero.getText().trim());
            if(Valnumero){
                JOptionPane.showMessageDialog(this, "El número ingresado a se encuentra registrado en el sistema.");
                return;
            }
        }
        if(Double.parseDouble(AreaBloque) < AcuAreaLotesxBloques+AreaTo){
            JOptionPane.showMessageDialog(this, "El area debe ser menor al area total de la finca");
            return;
        }
        if(idsFuentes.length<=0){
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una fuente hidrica");
            return;
        }
        
        if(!modeloLotes.getId().equals("0")){
            LlenarListaFuentesActualizar(idsFuentes);
            RecorrerLista();
        }
        
        modeloLotes.setId_bloque(id_Bloque);
        modeloLotes.setNumero(txtNumero.getText().trim());
        modeloLotes.setArea(txtAreaT.getText().trim().replace(".", "").replace(",", "."));
        modeloLotes.setId_fuente_hidrica(idsFuentes);
        modeloLotes.setFuente_Hidrica(Fuentes);
        modeloLotes.setFecha("NOW()");
        modeloLotes.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        if(modeloLotes.getId_finca().equals("-1")){ 
            JOptionPane.showMessageDialog(this, "Por favor seleccione una finca para realizar la operación.");
            return;
        }
        
        int ret = -1;
        System.out.println("modeloLotes.getId()-->"+modeloLotes.getId());
        if(modeloLotes.getId().equals("0")){//INSERT
            System.out.println("HOLA");
            ret = controlLote.Guardar(modeloLotes);
        }else{
            System.out.println("ENTRE PRO ACA");
            
            ret = controlLote.Actualizar(modeloLotes);
            
            int rret = controlLote.ActualizarFuentexLotes(listaActualizarFuentes);
            
        }
        
        if(ret == 0){
            System.out.println("fila-->"+fila);
            if(fila > -1){//ESTA EN TABLA ACTUALIZAR
                String f = "";
                for(String d:modeloLotes.getFuente_Hidrica()){
                    f += (f.equals("")?"":", ")+d;
                }
                System.out.println("f--->"+f);
                ListamodeloLotes.set(fila, modeloLotes);
                tbl_Lotes.setValueAt("Bloque "+modeloLotes.getNumero_bloque(), fila, 1);
                tbl_Lotes.setValueAt(modeloLotes.getNumero(), fila, 2);
                tbl_Lotes.setValueAt(modeloLotes.getNumero(), fila, 2);
                tbl_Lotes.setValueAt(Utilidades.MascaraMonedaConDecimales(modeloLotes.getArea().replace(".", ",")), fila, 3);
                tbl_Lotes.setValueAt(f, fila, 4);
                
                
            }else{  
//                ListamodeloLotes.add(modeloLotes);
//                agregarFilaTabla(modeloTblLotes,
//                        new Object[]{
//                            tbl_Lotes.getRowCount()+1,
//                            "Bloque "+modeloLotes.getId_bloque(),
//                            modeloLotes.getNumero(),
//                            Utilidades.MascaraMonedaConDecimales(modeloLotes.getArea().replace(".", ",")),
//                            "Modificar",
//                            "Eliminar"
//                        });
                AccionCombo();
            }
            LimpiarFomulario();
        }
    }

    private void LimpiarFomulario() {
        if(cbBloque.getItemCount()>0)
            cbBloque.setSelectedIndex(0);
        
        //cbFuenteHidrica.setSelectedIndex(0);
        InicializarMultiComboBox();
        txtNumero.setText("");
        txtAreaT.setText("");
        modeloLotes = new ModeloLotes();
        modeloLotes.setId_finca(""+idFinca);
        modeloLotes.setId("0");
        fila = -1;
        LimpiarLstFuentes();
    }
    
    public void InicializarMultiComboBox(){
        for(ModeloOpcionesMultiples dato: ListaDatosMultiple){
            dato.setEstado(false);
        }
    }

    private double getAcumuladoArea(String id_Bloque, String id_Lote) {
        double ret = 0;
        String valor = "";
        for(int i = 0; i < ListamodeloLotes.size(); i++){
            if(ListamodeloLotes.get(i).getId_bloque().equals(id_Bloque) && !ListamodeloLotes.get(i).getId().equals(id_Lote)){
                valor = ListamodeloLotes.get(i).getArea().trim();
                ret += Double.parseDouble(valor);
            }
        }
        return ret;
    }
    

    private void LlenarListaFuentes() {
        
        for (Map<String, String> lista: listaFuentesHidricas) {
            modlistFuentes.addElement(lista.get("DESCRIPCION"));
        }
        lstFuentesHidricas.setModel(modlistFuentes);
    }
 
    private void LimpiarLstFuentes() {
        lstFuentesHidricas.setModel(modlistFuentes);
    }

    private int[] getIndicesLista() {
        int[] ret = new int[modeloLotes.getFuente_Hidrica().length];
        int ind = 0;
        for(String fuente: modeloLotes.getFuente_Hidrica()){
            for(int i = 0; i < modlistFuentes.size(); i++){
                if(modlistFuentes.getElementAt(i).equals(fuente)){
                    ret[ind] = i;
                    ind++;
                }
            }
        }
        return ret;
    }

    private String[] getIDs() {
        String[] ids = new String[lstFuentesHidricas.getSelectedIndices().length];
        int x =0;
        for(int ind :lstFuentesHidricas.getSelectedIndices()){
            ids[x] = listaFuentesHidricas.get(ind).get("ID");
            x++;
        }
        return ids;
    }

    private void LlenarListaFuentesActualizar(String[] idsFuentes) {
        int ban = 0;
        System.out.println("****************LlenarListaFuentesActualizar*****************"+idsFuentes.length);
            
            for(int i= 0; i < ListamodeloLotes.get(fila).getId_fuente_hidrica().length; i++){
                ban=0;
                for(String ids: idsFuentes){
                    if(ids.equals(ListamodeloLotes.get(fila).getId_fuente_hidrica()[i])){
                        ban = 1;
                        break;
                    }
                }
                if(ban == 0){
                    Map<String, String> map =  new HashMap<>();
                    map.put("INSERT", "1");
                    map.put("IDLOTE", ""+ListamodeloLotes.get(fila).getId());
                    map.put("IDFUENTE", ""+ListamodeloLotes.get(fila).getId_fuente_hidrica()[i]);
                    map.put("IDUSUARIO", ""+ListamodeloLotes.get(fila).getId_usuario());
                    map.put("ID", ""+ListamodeloLotes.get(fila).getIdxfuentehidrica()[i]);
                    listaActualizarFuentes.add(map);
                }
            }
            for(String ids: idsFuentes){
                ban=0;
                for(int i= 0; i < ListamodeloLotes.get(fila).getId_fuente_hidrica().length; i++){
                    if(ids.equals(ListamodeloLotes.get(fila).getId_fuente_hidrica()[i])){
                        ban = 1;
                        break;
                    }
                }
                if(ban == 0){
                    Map<String, String> map =  new HashMap<>();
                    map.put("INSERT", "0");
                    map.put("IDLOTE", ""+ListamodeloLotes.get(fila).getId());
                    map.put("IDFUENTE", ""+ids);
                    map.put("IDUSUARIO", ""+ListamodeloLotes.get(fila).getId_usuario());
                    map.put("ID", "0");
                    listaActualizarFuentes.add(map);
                }
            }
            System.out.println("*****************LlenarListaFuentesActualizar*******************"+listaActualizarFuentes.size());
    }

    private void RecorrerLista() {
        System.out.println("*********RecorrerLista**********");
        for(Map<String, String> map : listaActualizarFuentes){
            System.out.println("*********************************************");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println("key-->"+key+"___________value-->"+value);
            }
        }
        System.out.println("*****************RecorrerLista******************");
    }

    private String[] getValues() {
        String[] ids = new String[lstFuentesHidricas.getSelectedIndices().length];
        int x =0;
        for(int ind :lstFuentesHidricas.getSelectedIndices()){
            ids[x] = listaFuentesHidricas.get(ind).get("DESCRIPCION");
            x++;
        }
        return ids;
    }
}
