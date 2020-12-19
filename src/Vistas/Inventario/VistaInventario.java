/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Inventario;

import Control.ControlGeneral;
import Control.Inventario.ControlInventario;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaInventarioRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Vistas.VistaGeneral;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaInventario extends javax.swing.JPanel {

    private ControlGeneral controlgen;
    private ControlInventario controlInventario;
    public DefaultTableModel modeloTblInventario;
    public ArrayList<String> NameColumnasFiltro;
    public String[] NameColumnas;
    public String[] EncabezadoTblInventario;
    public ModeloVentanaGeneral objetoVentana;
    private List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaInventario;
    public List<Map<String, String>> ListaInventarioMostrar;
    private String idFinca;
    public int filaSeleccionada;
    public int band;
    public Map<String, String> datoaModificar;
    public ArrayList<String> NameColumnasOrden;
    public int bandOrden = 0;
    public int colOrden = 0;
    public String Orden = "";

    public VistaInventario() {
        initComponents();
        filaSeleccionada = -1;
        band = 0;
        datoaModificar = new HashMap<String, String>();
        listaFincas = new ArrayList<>();
        listaInventario = new ArrayList<>();
        ListaInventarioMostrar = new ArrayList<>();
        controlgen = new ControlGeneral();
        controlInventario = new ControlInventario();
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("FECHA");
        NameColumnasFiltro.add("PRODUCTO");
        NameColumnasFiltro.add("ENTRADA");
        NameColumnasFiltro.add("SALIDA");
        NameColumnasFiltro.add("EXISTENCIA");
        
        //<editor-fold defaultstate="collapsed" desc="ORDEN TABLA">
            NameColumnasOrden = new ArrayList<>();
            NameColumnasOrden.add("FECHA");
            NameColumnasOrden.add("PRODUCTO");
            NameColumnasOrden.add("ENTRADA");
            NameColumnasOrden.add("SALIDA");
            NameColumnasOrden.add("EXISTENCIA");
//</editor-fold>
        EncabezadoTblInventario = new String[]{
            "No",
            "Fecha",
            "Producto",
            "Entradas",
            "Salidas",
            "Existencia",
            "Mod",
            "Ent",
            "Sal",
            "ListEnt",
            "ListSal"
        };
        InicializarTblInventario();
        CargarListaFincas();
    }

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

    public void InicializarTblInventario() {
        tbl_inventario.setDefaultRenderer(Object.class, new TablaInventarioRender());

        modeloTblInventario = new DefaultTableModel(EncabezadoTblInventario, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_inventario.setModel(modeloTblInventario);

        tbl_inventario.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbl_inventario.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_inventario.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbl_inventario.getColumnModel().getColumn(3).setPreferredWidth(40);
        tbl_inventario.getColumnModel().getColumn(4).setPreferredWidth(40);
        tbl_inventario.getColumnModel().getColumn(5).setPreferredWidth(40);
        tbl_inventario.getColumnModel().getColumn(6).setPreferredWidth(20);
        tbl_inventario.getColumnModel().getColumn(7).setPreferredWidth(20);
        tbl_inventario.getColumnModel().getColumn(8).setPreferredWidth(20);
        tbl_inventario.getColumnModel().getColumn(9).setPreferredWidth(30);
        tbl_inventario.getColumnModel().getColumn(9).setPreferredWidth(20);

        tbl_inventario.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblInventario.getColumnCount(); i++) {
            tbl_inventario.getColumnModel().getColumn(i).setResizable(false);

        }
        JTableHeader header = tbl_inventario.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
        
        tbl_inventario.getTableHeader().addMouseListener(new MouseAdapter() {
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

        lbltitle1 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_inventario = new javax.swing.JTable();
        btnFiltro = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblConvencion = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Filtro de busqueda");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        add(lbltitle1, gridBagConstraints);

        txtFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro.setForeground(new java.awt.Color(59, 123, 50));
        txtFiltro.setBorder(null);
        txtFiltro.setCaretColor(new java.awt.Color(59, 123, 50));
        txtFiltro.setFocusCycleRoot(true);
        txtFiltro.setSelectionColor(new java.awt.Color(59, 123, 50));
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtFiltro, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator6, gridBagConstraints);

        tbl_inventario.setForeground(new java.awt.Color(59, 123, 50));
        tbl_inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_inventario.setFocusTraversalPolicyProvider(true);
        tbl_inventario.setGridColor(new java.awt.Color(59, 123, 50));
        tbl_inventario.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tbl_inventario.setMinimumSize(new java.awt.Dimension(200, 200));
        tbl_inventario.setPreferredSize(new java.awt.Dimension(200, 200));
        tbl_inventario.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_inventarioMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_inventario);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);

        btnFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/buscar30.png"))); // NOI18N
        btnFiltro.setToolTipText("Agregar");
        btnFiltro.setBorderPainted(false);
        btnFiltro.setContentAreaFilled(false);
        btnFiltro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFiltro.setFocusPainted(false);
        btnFiltro.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnFiltro.setMaximumSize(new java.awt.Dimension(30, 30));
        btnFiltro.setMinimumSize(new java.awt.Dimension(30, 30));
        btnFiltro.setName("btnFiltro"); // NOI18N
        btnFiltro.setPreferredSize(new java.awt.Dimension(30, 30));
        btnFiltro.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/buscar30.png"))); // NOI18N
        btnFiltro.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/buscar30.png"))); // NOI18N
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(btnFiltro, gridBagConstraints);

        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30.png"))); // NOI18N
        btnAgregarProducto.setToolTipText("Agregar Producto");
        btnAgregarProducto.setBorderPainted(false);
        btnAgregarProducto.setContentAreaFilled(false);
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProducto.setFocusPainted(false);
        btnAgregarProducto.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnAgregarProducto.setMaximumSize(new java.awt.Dimension(30, 30));
        btnAgregarProducto.setMinimumSize(new java.awt.Dimension(30, 30));
        btnAgregarProducto.setName("btnAgregar"); // NOI18N
        btnAgregarProducto.setPreferredSize(new java.awt.Dimension(30, 30));
        btnAgregarProducto.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30_over.png"))); // NOI18N
        btnAgregarProducto.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/agregar30_over.png"))); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(btnAgregarProducto, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbFinca, gridBagConstraints);

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Seleccione una finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(lblTid, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblConvencion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblConvencion.setForeground(new java.awt.Color(59, 123, 50));
        lblConvencion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConvencion.setText("Convencion de colores para la existencia en inventario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel1.add(lblConvencion, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(204, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><p align='justify'>La existencia del producto se encuentra por debajo del <b>15%</b> en comparación con las entradas</p></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setBackground(new java.awt.Color(0, 126, 51));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html><p align='justify'>La existencia del producto se encuentra por encima del <b>30%</b> en comparación con las entradas</p></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel3.setForeground(new java.awt.Color(59, 123, 50));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html><p align='justify'>La existencia del producto se encuentra entre el <b>15%</b> y el <b>30%</b> en comparación con las entradas</p></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel4.add(jLabel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel1.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        String texto = txtFiltro.getText();
        if (texto.length() == 0) {
            return;
        }
        
        if (evt.getKeyCode() == evt.VK_ENTER) {
            aplicarFiltro(texto);
        }
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        String texto = txtFiltro.getText();
        if (texto.length() == 0) {
            aplicarFiltro(texto);
        }
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void tbl_inventarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_inventarioMouseReleased
        filaSeleccionada = tbl_inventario.getSelectedRow();
        int cola = tbl_inventario.getSelectedColumn();
        String dato = tbl_inventario.getValueAt(filaSeleccionada, cola).toString();

        if (band == 0) {
            if (dato.equalsIgnoreCase("mod")) {
                band = 1;
                datoaModificar = ListaInventarioMostrar.get(filaSeleccionada);
                objetoVentana = new ModeloVentanaGeneral(this, new VistaModificarProducto(), 1, datoaModificar);
                VistaGeneral vis = new VistaGeneral(objetoVentana);
                vis.setVisible(true);
            }
            if (dato.equalsIgnoreCase("ent")) {
                band = 1;
                datoaModificar = ListaInventarioMostrar.get(filaSeleccionada);
                objetoVentana = new ModeloVentanaGeneral(this, new VistaProducto(), 2, datoaModificar);
                VistaGeneral vis = new VistaGeneral(objetoVentana);
                vis.setVisible(true);
            }
            if (dato.equalsIgnoreCase("sal")) {
                band = 1;
                datoaModificar = ListaInventarioMostrar.get(filaSeleccionada);
                objetoVentana = new ModeloVentanaGeneral(this, new VistaSalida(), 1, datoaModificar);
                VistaGeneral vis = new VistaGeneral(objetoVentana);
                vis.setVisible(true);
            }
            if (dato.equalsIgnoreCase("ListEnt")) {
                band = 1;
                datoaModificar = ListaInventarioMostrar.get(filaSeleccionada);
                objetoVentana = new ModeloVentanaGeneral(this, new VistaListaEntradas(), 1, datoaModificar);
                VistaGeneral vis = new VistaGeneral(objetoVentana);
                vis.setVisible(true);
            }
            if (dato.equalsIgnoreCase("ListSal")) {
                band = 1;
                datoaModificar = ListaInventarioMostrar.get(filaSeleccionada);
                objetoVentana = new ModeloVentanaGeneral(this, new VistaListaSalidas(), 1, datoaModificar);
                VistaGeneral vis = new VistaGeneral(objetoVentana);
                vis.setVisible(true);
            }
        }
    }//GEN-LAST:event_tbl_inventarioMouseReleased

    private void btnFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroActionPerformed
        String texto = txtFiltro.getText();
        if (texto.length() == 0) {
            return;
        }

        aplicarFiltro(texto);
    }//GEN-LAST:event_btnFiltroActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        objetoVentana = new ModeloVentanaGeneral(this, new VistaProducto(), 1, idFinca);
        VistaGeneral vis = new VistaGeneral(objetoVentana);
        vis.setVisible(true);
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        boolean habilitar = cbFinca.getSelectedIndex() > 0;
        btnAgregarProducto.setEnabled(habilitar);
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");

        AccionCombo();
    }//GEN-LAST:event_cbFincaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnFiltro;
    public javax.swing.JComboBox cbFinca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblConvencion;
    private javax.swing.JLabel lblTid;
    public javax.swing.JLabel lbltitle1;
    public javax.swing.JTable tbl_inventario;
    public javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    private void aplicarFiltro(String text) {
        cargarTablaFiltro();
    }

    public void AccionCombo() {
        cargarTablaFiltro();
    }

    private void cargarTablaFiltro() {

        if (Integer.parseInt(idFinca) > 0) {
            ArrayList<String> datos = new ArrayList<>();
            datos.add(idFinca);
            datos.add(Orden);
            listaInventario = (List<Map<String, String>>) controlInventario.ObtenerDatosFiltro(datos);
            if (listaInventario.size() > 0) {
                String col = "";
                for (Map.Entry<String, String> entry : listaInventario.get(0).entrySet()) {
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
                    col += (col.equals("") ? "" : "<::>") + split[0];
                }

                NameColumnas = col.split("<::>");

            } else {
            }
            MostrarTabla();
        }
    }

    private void MostrarTabla() {
        System.out.println("****************MostrarTabla*****************");
        String filtro = Utilidades.CodificarElemento(txtFiltro.getText());
        System.out.println("filtro--" + filtro);
        ListaInventarioMostrar = getFiltroLista(filtro);

        Utilidades.LimpiarTabla(tbl_inventario);
        for (int i = 0; i < ListaInventarioMostrar.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTblInventario,
                    new Object[]{
                        (i + 1),//consecutivo,
                        ListaInventarioMostrar.get(i).get("FECHA"),
                        ListaInventarioMostrar.get(i).get("PRODUCTO"),
                        ListaInventarioMostrar.get(i).get("ENTRADA"),
                        ListaInventarioMostrar.get(i).get("SALIDA"),
                        ListaInventarioMostrar.get(i).get("EXISTENCIA"),
                        "Mod",
                        "Ent",
                        "Sal",
                        "ListEnt",
                        "ListSal"
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
        for (int i = 0; i < listaInventario.size(); i++) {
            b = 1;
            if (filtro.isEmpty()) {
                retorno.add(listaInventario.get(i));
            } else {
                valores = "";
                for (int j = 0; j < NameColumnasFiltro.size(); j++) {
                    System.out.println("NAme-" + j + "->" + NameColumnasFiltro.get(j));
                    String value = listaInventario.get(i).get(NameColumnasFiltro.get(j));
                    valores += "" + value;
                }
                boolean encontro = Expresiones.filtrobusqueda(filtros, valores);
                System.out.println("i-" + i + "-b-" + b);
                if (encontro) {
                    retorno.add(listaInventario.get(i));
                }

            }
        }
        System.out.println("********************retorno --> " + retorno.size() + "***********************");
        return retorno;
    }

    public void EventoOrdenTabla(MouseEvent e){
        if(!tbl_inventario.isEnabled())
            return;
        
        int col = tbl_inventario.columnAtPoint(e.getPoint());
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
                    dat += (dat.equals("")? "":", ") + cols[i]+" "+(bandOrden == 1?"ASC":"DESC");
                }
            }
            
            Orden = dat;
            AccionCombo(); 
        }
    }

}
