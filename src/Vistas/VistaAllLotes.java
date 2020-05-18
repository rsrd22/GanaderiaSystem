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
import java.util.List;
import java.util.Map;
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
    public boolean ban = false;
    public ControlMultiComboBox obj;
    /**
     * Creates new form VistaAllLotes
     */
    
    public VistaAllLotes() {
        initComponents();
        controlFinca = new ControlFincas();
        controlLote = new ControLotes();
        ListamodeloLotes = new ArrayList<>();
        EncabezadoTblLotes = new String[]{
            "No","Bloque", "Número", "Área Total", "Fuente Hidrica", "Modificar", "Eliminar"
        };
        lblId_Bloque.setVisible(false);
        modeloLotes = new ModeloLotes();
        idFinca = "";
        AreaBloque = "";
        fila = -1;
        txtOpcion.setEnabled(false);
        CargarListaFuentesHidricas();
        CargarListaFincas();
        
        System.out.println("ListaDatosMultiple---_>"+ListaDatosMultiple.size());
        obj = new ControlMultiComboBox(pnlOpciones, txtOpcion, ListaDatosMultiple);
        obj.LlenarPnlOpciones(); 
        LimpiarFomulario();
        EstadoOpciones();
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

        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        scrlPnlOpciones = new javax.swing.JScrollPane();
        pnlOpciones = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lbltitle1 = new javax.swing.JLabel();
        lbltitle3 = new javax.swing.JLabel();
        txtAreaT = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        lblId_Bloque = new javax.swing.JLabel();
        cbBloque = new javax.swing.JComboBox();
        lblTid1 = new javax.swing.JLabel();
        lblTid2 = new javax.swing.JLabel();
        cbFuenteHidrica = new javax.swing.JComboBox();
        txtOpcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Lotes = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        add(cbFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 30));

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        add(lblTid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Agregar Lote", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrlPnlOpciones.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlOpciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        scrlPnlOpciones.setViewportView(pnlOpciones);

        jPanel1.add(scrlPnlOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 70, 220, 80));

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
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 230, 30));

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 10));

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Número");
        jPanel1.add(lbltitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 20));

        lbltitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle3.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle3.setText("Area Total");
        jPanel1.add(lbltitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 100, 20));

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
        jPanel1.add(txtAreaT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 220, 30));

        jSeparator8.setBackground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 220, 10));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setText("HA");
        jLabel13.setToolTipText("Hectárea");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, 30));

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 30, 30));

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
        jPanel1.add(btnDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 30, 30));
        jPanel1.add(lblId_Bloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        cbBloque.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbBloque.setForeground(new java.awt.Color(59, 123, 50));
        cbBloque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBloqueActionPerformed(evt);
            }
        });
        jPanel1.add(cbBloque, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 230, 30));

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Bloque");
        jPanel1.add(lblTid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(59, 123, 50));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid2.setText("Fuente Hidrica");
        jPanel1.add(lblTid2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 150, -1));

        cbFuenteHidrica.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFuenteHidrica.setForeground(new java.awt.Color(59, 123, 50));
        cbFuenteHidrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFuenteHidricaActionPerformed(evt);
            }
        });
        jPanel1.add(cbFuenteHidrica, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 220, 30));

        txtOpcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtOpcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtOpcionMousePressed(evt);
            }
        });
        jPanel1.add(txtOpcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 190, 30));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setForeground(new java.awt.Color(102, 102, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("A");
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, 30, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 640, 150));

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

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 640, 260));
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
                agregarFilaTabla(modeloTblLotes, 
                        new Object[]{
                            tbl_Lotes.getRowCount()+1,
                            "Bloque "+ListamodeloLotes.get(i).getNumero_bloque(),
                            ListamodeloLotes.get(i).getNumero(),
                            Utilidades.MascaraMonedaConDecimales(ListamodeloLotes.get(i).getArea().replace(".", ",")),
                            ListamodeloLotes.get(i).getFuente_Hidrica(),
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
        AreaBloque = listaBloques.get(cbBloque.getSelectedIndex()).get("AREA");
    }//GEN-LAST:event_cbBloqueActionPerformed

    private void cbFuenteHidricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFuenteHidricaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFuenteHidricaActionPerformed

    private void tbl_LotesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LotesMouseReleased
        int fila = tbl_Lotes.getSelectedRow();
        int cola = tbl_Lotes.getSelectedColumn();
        if(cola == 5){// ACTUALIZAR
            this.fila = fila;
            modeloLotes = ListamodeloLotes.get(fila);
            modeloLotes.setId_finca(""+idFinca);
            
            cbBloque.setSelectedIndex(getIndexLista(modeloLotes.getId_bloque(), listaBloques));
            cbFuenteHidrica.setSelectedIndex(getIndexLista(modeloLotes.getId_fuente_hidrica(), listaFuentesHidricas));
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

    private void txtOpcionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOpcionMousePressed
        ban = !ban;
        EstadoOpciones();
    }//GEN-LAST:event_txtOpcionMousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        ban = !ban;
        EstadoOpciones();
    }//GEN-LAST:event_jLabel2MousePressed

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
    public javax.swing.JComboBox cbFuenteHidrica;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid2;
    public javax.swing.JLabel lbltitle1;
    public javax.swing.JLabel lbltitle3;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JScrollPane scrlPnlOpciones;
    private javax.swing.JTable tbl_Lotes;
    public javax.swing.JTextField txtAreaT;
    public javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtOpcion;
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
        System.out.println("listaFuentesHidricas--->"+listaFuentesHidricas.size());
        for(Map<String, String> dato: listaFuentesHidricas){
            ListaDatosMultiple.add(new ModeloOpcionesMultiples(Integer.parseInt(dato.get("ID")), 
                                                                dato.get("DESCRIPCION"), 
                                                                false));
        }
        System.out.println("ListaDatosMultiple--->"+ListaDatosMultiple.size());
        //Utilidades.LlenarComboBox(cbFuenteHidrica, listaFuentesHidricas, "DESCRIPCION");
       
    }
    
    private void agregarFilaTabla(DefaultTableModel modelotbl, Object[] fila) {
        modelotbl.addRow(fila);
    }

    private void Guardar() {
        String id_Bloque = ""+listaBloques.get(cbBloque.getSelectedIndex()).get("ID");
        
        String AreaT = txtAreaT.getText().trim().replace(".", "").replace(",", ".");
        double AcuAreaLotesxBloques = getAcumuladoArea(id_Bloque);
        double AreaTo = Double.parseDouble(AreaT);
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
        
        modeloLotes.setId_bloque(id_Bloque);
        modeloLotes.setNumero(txtNumero.getText().trim());
        modeloLotes.setArea(txtAreaT.getText().trim().replace(".", "").replace(",", "."));
        modeloLotes.setId_fuente_hidrica(""+listaFuentesHidricas.get(cbFuenteHidrica.getSelectedIndex()).get("ID"));
        modeloLotes.setFecha("NOW()");
        modeloLotes.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        if(modeloLotes.getId_finca().equals("-1")){ 
            JOptionPane.showMessageDialog(this, "Por favor seleccione una finca para realizar la operación.");
            return;
        }
        
        int ret = -1;
        if(modeloLotes.getId().equals("0")){//INSERT
            ret = controlLote.Guardar(modeloLotes);
        }else{
            ret = controlLote.Actualizar(modeloLotes);
        }
        
        if(ret == 0){
            if(fila > -1){//ESTA EN TABLA ACTUALIZAR
                ListamodeloLotes.set(fila, modeloLotes);
                tbl_Lotes.setValueAt("Bloque "+modeloLotes.getNumero_bloque(), fila, 1);
                tbl_Lotes.setValueAt(modeloLotes.getNumero(), fila, 2);
                tbl_Lotes.setValueAt(Utilidades.MascaraMonedaConDecimales(modeloLotes.getArea().replace(".", ",")), fila, 3);
                
                
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
    }
    
    public void InicializarMultiComboBox(){
        for(ModeloOpcionesMultiples dato: ListaDatosMultiple){
            dato.setEstado(false);
        }
    }

    private double getAcumuladoArea(String id_Bloque) {
        double ret = 0;
        String valor = "";
        for(int i = 0; i < ListamodeloLotes.size(); i++){
            if(ListamodeloLotes.get(i).getId_bloque().equals(id_Bloque)){
                valor = ListamodeloLotes.get(i).getArea().trim().replace(".", "").replace(",", ".");
                ret += Double.parseDouble(valor);
            }
        }
        
        return ret;
    }
 
    public void EstadoOpciones(){
        pnlOpciones.setVisible(ban);
        scrlPnlOpciones.setVisible(ban);
    }
}
