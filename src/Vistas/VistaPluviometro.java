/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Configuracion.InformacionGlobal;
import Control.*;
import Modelo.*;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VistaPluviometro extends javax.swing.JPanel {
    public List<Map<String, String>> listaFincas;
    public String[] EncabezadoTblPluviometro; 
    public String[] EncabezadoTblPluviometroAux; 
    public DefaultTableModel modeloTblPluviometro;
    private ModeloPluviometro modeloPluviometro; 
    private ControlPluviometro controlPluviometro;
    private ArrayList<ModeloPluviometro>  ListamodeloPluviometro;
    private ControlFincas controlFinca;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public int fila;
    public int idModulo = 7;
    public ArrayList<String> NameColumnasOrden;
    public int bandOrden = 0;
    public int colOrden = 0;
    public String Orden = "";  
    
    /**
     * Creates new form VistaPubliometro
     */
    public VistaPluviometro() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        //<editor-fold defaultstate="collapsed" desc="ORDEN TABLA">
            NameColumnasOrden = new ArrayList<>();
            NameColumnasOrden.add("FECHAREGISTRO");
            NameColumnasOrden.add("CANTIDAD");
//</editor-fold>
        EncabezadoTblPluviometro = new String[]{
            "No", "Fecha", "Cantidad", "Modificar", "Eliminar"
        };
        EncabezadoTblPluviometroAux = new String[]{
            "No", "Finca", "Fecha", "Cantidad", "Modificar", "Eliminar"
        };
        controlFinca = new ControlFincas();
        controlPluviometro = new ControlPluviometro();
        ListamodeloPluviometro = new ArrayList<>();
        modeloPluviometro = new ModeloPluviometro();
        idFinca = "";
        fila = -1;
        LimpiarFomulario();
        CargarListaFincas();
        InicializarTblPluviometro();
        
        InformacionGlobal.setFincaDesdeConstructor(cbFinca);
    }

    public void InicializarTblPluviometro() {
        tbl_Pluviometro.setDefaultRenderer(Object.class, new TablaRender());
        
        modeloTblPluviometro = new DefaultTableModel(EncabezadoTblPluviometro, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Pluviometro.setModel(modeloTblPluviometro);

        tbl_Pluviometro.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_Pluviometro.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_Pluviometro.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Pluviometro.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbl_Pluviometro.getColumnModel().getColumn(4).setPreferredWidth(80);
        
        tbl_Pluviometro.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblPluviometro.getColumnCount(); i++) {
            tbl_Pluviometro.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Pluviometro.getColumnModel().getColumn(i).setCellRenderer(tcr); 
             
        }
        JTableHeader header = tbl_Pluviometro.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
        
        tbl_Pluviometro.getTableHeader().addMouseListener(new MouseAdapter() {
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
        jPanel1 = new javax.swing.JPanel();
        lbltitle1 = new javax.swing.JLabel();
        lbltitle3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Pluviometro = new javax.swing.JTable();

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
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
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
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 20);
        add(cbFinca, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Datos Pluviometro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbltitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle1.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle1.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.49;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(lbltitle1, gridBagConstraints);

        lbltitle3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle3.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle3.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(lbltitle3, gridBagConstraints);

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(59, 123, 50));
        txtCantidad.setBorder(null);
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(txtCantidad, gridBagConstraints);

        jSeparator8.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.48;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 0);
        jPanel1.add(jSeparator8, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setText("mm");
        jLabel13.setToolTipText("Hectárea");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.01;
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
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(btnGuardar, gridBagConstraints);

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
        gridBagConstraints.weightx = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel1.add(btnDescartar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 0.49;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jdFecha, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 90;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 19);
        add(jPanel1, gridBagConstraints);

        tbl_Pluviometro.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Pluviometro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_PluviometroMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Pluviometro);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 557;
        gridBagConstraints.ipady = 283;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 24, 19);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        InformacionGlobal.setFincaDesdeEventoChange(cbFinca);
        
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        LlenarDatosTabla();

    }//GEN-LAST:event_cbFincaActionPerformed

    public void LlenarDatosTabla(){
        Utilidades.LimpiarTabla(tbl_Pluviometro);
        System.out.println("CBFINCA ---->"+listaFincas.size());
        System.out.println("cbFincaActionPerformed cb-->"+idFinca);
        modeloPluviometro.setId_finca(""+idFinca);
        if(Integer.parseInt(idFinca)>0){
            ListamodeloPluviometro = (ArrayList<ModeloPluviometro>) controlFinca.ObtenerPluviometroxFinca(""+idFinca, Orden);
            for(int i =0; i < ListamodeloPluviometro.size(); i++){
                agregarFilaTabla(modeloTblPluviometro,
                    new Object[]{
                        tbl_Pluviometro.getRowCount()+1,
                        ListamodeloPluviometro.get(i).getFecha_registro(),
                        Utilidades.MascaraMonedaConDecimales(ListamodeloPluviometro.get(i).getCantidad().replace(".", ",")),
                        "Modificar",
                        "Eliminar"
                    });
                }
            }else{
                Utilidades.LimpiarTabla(tbl_Pluviometro);
            }
    }
    
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        LimpiarFomulario();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void tbl_PluviometroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PluviometroMouseReleased
        int fila = tbl_Pluviometro.getSelectedRow();
        int cola = tbl_Pluviometro.getSelectedColumn();
        if(cola == 3){// ACTUALIZAR
            this.fila = fila;
            modeloPluviometro = ListamodeloPluviometro.get(fila);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            
            try {
                Date fecha ;
                fecha = formato.parse(modeloPluviometro.getFecha_registro());
                jdFecha.setDate(fecha);
            } catch (ParseException ex) {
                Logger.getLogger(VistaPluviometro.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            txtCantidad.setText(""+Utilidades.MascaraMonedaConDecimales(modeloPluviometro.getCantidad().replace(".", ",")));
            jdFecha.requestFocusInWindow();
            //            objetoVentana = new ModeloVentanaGeneral(this, new VistaBloques(), 1,modeloBloques);
            //            objetoVentana.setFila(fila);
            //
            //            new VistaGeneral(objetoVentana).setVisible(true);

        }else if(cola == 4){ //ELIMINAR
            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
            if(resp == JOptionPane.YES_OPTION){
                modeloPluviometro = ListamodeloPluviometro.get(fila);
                int ret = controlPluviometro.Eliminar(modeloPluviometro);
                if(ret == 0){
                    JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
                    LlenarDatosTabla();
                }
            }
        }
    }//GEN-LAST:event_tbl_PluviometroMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator8;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel lblId_Bloque;
    private javax.swing.JLabel lblTid;
    public javax.swing.JLabel lbltitle1;
    public javax.swing.JLabel lbltitle3;
    private javax.swing.JTable tbl_Pluviometro;
    public javax.swing.JTextField txtCantidad;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        System.out.println("CARGAR LISTAS FINCAS");
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n" +
                                                "FROM `fincas`\n"+
                                                "/*UNION \n"+
                                                "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");
         
        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
       
    }
    
    private void agregarFilaTabla(DefaultTableModel modelotbl, Object[] fila) {
        modelotbl.addRow(fila);
    }

    private void LimpiarFomulario() {
        jdFecha.setCalendar(Calendar.getInstance());
        txtCantidad.setText("");
        modeloPluviometro = new ModeloPluviometro();
        modeloPluviometro.setId_finca(""+idFinca);
        modeloPluviometro.setId("0");
        fila = -1;
    }

    private void Guardar() {
        if(cbFinca.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Por favor seleccione una finca para realizar la operación.");
            return;
        }
        if(txtCantidad.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor especifique una cantidad.");
            return;
        }
        if (jdFecha.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe especificar una fecha para realizar la operación.");
            jdFecha.requestFocusInWindow();
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFecha.getCalendar();
        modeloPluviometro.setFecha_registro(sdf.format(fecha.getTime()));
        modeloPluviometro.setCantidad(txtCantidad.getText().trim().replace(".", "").replace(",", "."));
        modeloPluviometro.setFecha("NOW()");
        modeloPluviometro.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        
        int ret = -1;
        if(modeloPluviometro.getId().equals("0")){//INSERT
            ret = controlPluviometro.Guardar(modeloPluviometro);
        }else{
            ret = controlPluviometro.Actualizar(modeloPluviometro);
        }
        if(ret == 0){
            if(fila > -1){//ESTA EN TABLA ACTUALIZAR
                ListamodeloPluviometro.set(fila, modeloPluviometro);
                tbl_Pluviometro.setValueAt(modeloPluviometro.getFecha_registro(), fila, 1);
                tbl_Pluviometro.setValueAt(Utilidades.MascaraMonedaConDecimales(modeloPluviometro.getCantidad().replace(".", ",")), fila, 2);
            }else{  
                ListamodeloPluviometro.add(modeloPluviometro);
                agregarFilaTabla(modeloTblPluviometro,
                        new Object[]{
                            tbl_Pluviometro.getRowCount()+1,
                            modeloPluviometro.getFecha_registro(),
                            Utilidades.MascaraMonedaConDecimales(modeloPluviometro.getCantidad().replace(".", ",")),
                            "Modificar",
                            "Eliminar"
                        });
            }
            LimpiarFomulario();
        }
    }

    public void EventoOrdenTabla(MouseEvent e){
        if(!tbl_Pluviometro.isEnabled())
            return;
        
        int col = tbl_Pluviometro.columnAtPoint(e.getPoint());
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
            LlenarDatosTabla();
        }
    }

    private String TipoDato(int ind, String Dato) {
        String ret = "";
        
            ret = Dato;
        
        return ret;
    }
}
