/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Configuracion.InformacionGlobal;
import Control.*;
import Control.ControlAnimales;
import Control.ControlGeneral;
import Control.RAnimales.ControlRAnimales;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
public class VistaPalpacion extends javax.swing.JPanel {

    public DefaultTableModel modeloTblAnimales;
    public String[] EncabezadoTblAnimales;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idTipoAnimal;
    public Boolean BanFiltroColor;
    public List<Map<String, String>> ListaAnimales;
    public List<Map<String, String>> ListaAnimalesMostrar;
    private ControlRAnimales controlAnimales = new ControlRAnimales();
    private ControlPalpacion controlPalpacion = new ControlPalpacion();
    public int allFincas;
    public ModeloVentanaGeneral objetoVentana;
    public String[] NameColumnas;
    public ArrayList<String> NameColumnasFiltro;
    public ArrayList<String> NameColumnasOrden;
    public List<Map<String, String>> ListadoFechas;
    Map<String, Map<String, String>> PropiedadesColumnas = new HashMap<>();
    public int band = 0;
    public int filaSeleccionada = 0;
    public String fechaAnterior = "";
    public String fecha = "";
    public String ban = "0";
    public int idModulo = 21;
    public int bandOrden = 0;
    public int colOrden = 0;
    public String Orden = "";
    

    /**
     * Creates new form VistaVerAnimales
     */
    public VistaPalpacion() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        idFinca = "";
        idTipoAnimal = "";
        BanFiltroColor = true;
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("NUMERO_ANIMAL");
        NameColumnasFiltro.add("NUMERO_MAMA");
        NameColumnasFiltro.add("NUMERO_HIJOS");
        NameColumnasFiltro.add("NUMERO_PARTOS");
        NameColumnasFiltro.add("FECHA_NOVILLA");
        NameColumnasFiltro.add("ESTADO");
        NameColumnasFiltro.add("NUMERO_MESES");
        NameColumnasFiltro.add("FECHA_ULT_PARTO");
        NameColumnasFiltro.add("EST");
        
        //<editor-fold defaultstate="collapsed" desc="ORDEN TABLA">
            NameColumnasOrden = new ArrayList<>();
            NameColumnasOrden.add("NUMERO_ANIMAL");
            NameColumnasOrden.add("NUMERO_MAMA");
            NameColumnasOrden.add("NUMERO_HIJOS");
            NameColumnasOrden.add("NUMERO_PARTOS");
            NameColumnasOrden.add("FECHA_NOVILLA");
            NameColumnasOrden.add("ESTADO");
            NameColumnasOrden.add("NUMERO_MESES");
            NameColumnasOrden.add("FECHA_ULT_PARTO");
//</editor-fold>
        
        EncabezadoTblAnimales = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Animal</p></html>",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Mamá</p></html>",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\"> de hijos</p></html>",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\"> de partos</p></html>",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Novilla</p></html>",
            "Estado",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Meses</p></html>",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">ultimo parto</p></html>",
            "Acción", 
            "Est"
        };
        ListaAnimales = new ArrayList<>();
        listaFincas = new ArrayList<>();
        listaTipoAnimales = new ArrayList<>();
        InicializarTblAnimales();
        IniciarFecha();
        CargarListaFincas();
        
        InformacionGlobal.setFincaDesdeConstructor(cbFinca);
        InformacionGlobal.setTipoAnimalDesdeConstructor(cbTipoAnimales);
    }

    public void InicializarTblAnimales() {
        tbl_Animales.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblAnimales = new DefaultTableModel(EncabezadoTblAnimales, 0) {
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

        tbl_Animales.setModel(modeloTblAnimales);

        tbl_Animales.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Animales.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(6).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(7).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(8).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(9).setPreferredWidth(80);
        tbl_Animales.getColumnModel().getColumn(10).setPreferredWidth(5);

        tbl_Animales.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblAnimales.getColumnCount(); i++) {
            tbl_Animales.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            tcr.setForeground(new Color(59, 123, 50));
            tbl_Animales.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Animales.getTableHeader();

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

        lblTid = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        cbTipoAnimales = new javax.swing.JComboBox();
        lblTid2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblTid1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Animales = new Tablas.PalpacionTable();
        lbltitle19 = new javax.swing.JLabel();
        jdFechaPalpacion = new com.toedter.calendar.JDateChooser();
        cbListadoFechas = new javax.swing.JComboBox();
        lblTid3 = new javax.swing.JLabel();
        btnFiltroColor = new javax.swing.JButton();

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 230;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 0);
        add(cbFinca, gridBagConstraints);

        cbTipoAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(cbTipoAnimales, gridBagConstraints);

        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(59, 123, 50));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid2.setText("Listado Fechas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lblTid2, gridBagConstraints);

        txtFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro.setForeground(new java.awt.Color(59, 123, 50));
        txtFiltro.setBorder(null);
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
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtFiltro, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jSeparator6, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Filtro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(lblTid1, gridBagConstraints);

        tbl_Animales.setForeground(new java.awt.Color(59, 123, 50));
        tbl_Animales.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Animales.setFocusTraversalPolicyProvider(true);
        tbl_Animales.setGridColor(new java.awt.Color(59, 123, 50));
        tbl_Animales.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_Animales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_AnimalesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Animales);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle19, gridBagConstraints);

        jdFechaPalpacion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaPalpacionPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaPalpacion, gridBagConstraints);

        cbListadoFechas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbListadoFechas.setForeground(new java.awt.Color(59, 123, 50));
        cbListadoFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListadoFechasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbListadoFechas, gridBagConstraints);

        lblTid3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid3.setForeground(new java.awt.Color(59, 123, 50));
        lblTid3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid3.setText("Tipo Animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lblTid3, gridBagConstraints);

        btnFiltroColor.setBackground(new java.awt.Color(255, 255, 255));
        btnFiltroColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/verAmarilla.png"))); // NOI18N
        btnFiltroColor.setBorderPainted(false);
        btnFiltroColor.setContentAreaFilled(false);
        btnFiltroColor.setMaximumSize(new java.awt.Dimension(33, 30));
        btnFiltroColor.setMinimumSize(new java.awt.Dimension(33, 30));
        btnFiltroColor.setPreferredSize(new java.awt.Dimension(33, 30));
        btnFiltroColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroColorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        add(btnFiltroColor, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        InformacionGlobal.setFincaDesdeEventoChange(cbFinca);
        
        if (cbFinca.getItemCount() > 0) {
            //EventoComboFincas();
            System.out.println("cbFinca.getSelectedIndex()--" + cbFinca.getSelectedIndex());
            if (cbFinca.getSelectedIndex() >= 0) {
                idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
                CargarListaTipoAnimales();
            } else {
                cbTipoAnimales.removeAllItems();
                Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalesActionPerformed
        InformacionGlobal.setTipoAnimalDesdeEventoChange(cbTipoAnimales);
        
        if (cbTipoAnimales.getItemCount() > 0) {
            //EventoComboFincas();
            ban = "1";
            if (cbTipoAnimales.getSelectedIndex() >= 0) {
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
                CargarListadoFechas();
                EventoComboFincas();
            } else {
                Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

    private void txtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFiltroFocusLost
        //MostrarTabla();
    }//GEN-LAST:event_txtFiltroFocusLost

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            MostrarTabla();
        }
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        if (txtFiltro.getText().equals("")) {
            MostrarTabla();
        }
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void tbl_AnimalesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_AnimalesMouseReleased
        filaSeleccionada = tbl_Animales.getSelectedRow();
        int cola = tbl_Animales.getSelectedColumn();
        String dato = tbl_Animales.getValueAt(filaSeleccionada, cola).toString();
        System.out.println("band-->"+band);
        if (band == 0) {
            if(cola == 10 && dato.equals("*")){
                band = 1;
                objetoVentana = new ModeloVentanaGeneral(this, new VistaIngresoPalpacion(), 2, ListaAnimalesMostrar.get(filaSeleccionada));
                new VistaGeneral(objetoVentana).setVisible(true);
            }else if (dato.equalsIgnoreCase("PALPAJE") && tbl_Animales.getValueAt(filaSeleccionada, cola+1).equals("")) {
                tbl_Animales.setValueAt("*", filaSeleccionada, cola+1);
                if(fechaAnterior.equals("")){
                    fechaAnterior = fecha;
                }
                band = 1;
                String idAnimal = ListaAnimalesMostrar.get(filaSeleccionada).get("IDANIMAL");
                String numero = ListaAnimalesMostrar.get(filaSeleccionada).get("NUMERO_ANIMAL");
                ArrayList<String> datosRef = new ArrayList<>();

                objetoVentana = new ModeloVentanaGeneral(this, new VistaIngresoPalpacion(), 1, ListaAnimalesMostrar.get(filaSeleccionada));
                new VistaGeneral(objetoVentana).setVisible(true);
            }
        }
    }//GEN-LAST:event_tbl_AnimalesMouseReleased

    private void jdFechaPalpacionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaPalpacionPropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fechaC = jdFechaPalpacion.getCalendar();
        fecha = sdf.format(fechaC.getTime());
        if(ban.equals("1"))
            EventoComboFincas();
    }//GEN-LAST:event_jdFechaPalpacionPropertyChange

    private void cbListadoFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListadoFechasActionPerformed
        if (cbListadoFechas.getItemCount() > 0) {
            //EventoComboFincas();
            //ban = "1";
            if (cbListadoFechas.getSelectedIndex() > 0) {
                try {
                    String fechaSeleccionada = ""+cbListadoFechas.getSelectedItem();
                    String[] f = fechaSeleccionada.split("/");
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formato.parse(f[2]+"-"+f[1]+"-"+f[0]);
                    jdFechaPalpacion.setDate(fecha);
                    
                } catch (ParseException ex) {
                    Logger.getLogger(VistaPalpacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbListadoFechasActionPerformed

    private void btnFiltroColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroColorActionPerformed
        if(!BanFiltroColor){
            BanFiltroColor =true;
            btnFiltroColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/verAmarilla.png")));
        }else{
            BanFiltroColor =false;
            btnFiltroColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/verSlashAmarillo.png")));
        }
        MostrarTabla();
    }//GEN-LAST:event_btnFiltroColorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltroColor;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbListadoFechas;
    public javax.swing.JComboBox cbTipoAnimales;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private com.toedter.calendar.JDateChooser jdFechaPalpacion;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid2;
    private javax.swing.JLabel lblTid3;
    private javax.swing.JLabel lbltitle19;
    public javax.swing.JTable tbl_Animales;
    public javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n"
                + "FROM `fincas`\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        CargarListaTipoAnimales();
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
        EventoComboFincas();
    }

    public void EventoComboFincas() {
        System.out.println("EventoComboFincas >> idFinca-->" + idFinca);
        if (idFinca.equals("ALL")) {
            allFincas = 1;
        } else {
            allFincas = 0;
        }
        if (Integer.parseInt(idFinca) > 0) {
            ListaAnimales = (List<Map<String, String>>) controlAnimales.ObtenerDatosAnimalesPalpacion(idFinca, idTipoAnimal, fecha);
            if (ListaAnimales.size() > 0) {
                String col = "";
                for (Map.Entry<String, String> entry : ListaAnimales.get(0).entrySet()) {
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

    public void MostrarTabla() {
        System.out.println("****************MostrarTabla*****************");
        String filtro = Utilidades.CodificarElemento(txtFiltro.getText());
        System.out.println("filtro--" + filtro);
        ListaAnimalesMostrar = getFiltroLista(filtro);
        
        Utilidades.LimpiarTabla(tbl_Animales);
        for (int i = 0; i < ListaAnimalesMostrar.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTblAnimales,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        ListaAnimalesMostrar.get(i).get("NUMERO_ANIMAL"),
                        ListaAnimalesMostrar.get(i).get("NUMERO_MAMA"),
                        ListaAnimalesMostrar.get(i).get("NUMERO_HIJOS"),
                        ListaAnimalesMostrar.get(i).get("NUMERO_PARTOS"),
                        ListaAnimalesMostrar.get(i).get("FECHA_NOVILLA"),
                        ListaAnimalesMostrar.get(i).get("ESTADO"),
                        ListaAnimalesMostrar.get(i).get("NUMERO_MESES"),
                        ListaAnimalesMostrar.get(i).get("FECHA_ULT_PARTO"),
                        "PALPAJE",
                        ListaAnimalesMostrar.get(i).get("EST")
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
        for (int i = 0; i < ListaAnimales.size(); i++) {
            b = 1;
            if (filtro.isEmpty()) {
                if(BanFiltroColor){
                    retorno.add(ListaAnimales.get(i));
                }else{
                    if (!ListaAnimales.get(i).get("EST").equals("*")) {
                        retorno.add(ListaAnimales.get(i));
                    }
                }   
            } else {
                valores = "";
                for (int j = 0; j < NameColumnasFiltro.size(); j++) {
                    System.out.println("NAme-" + j + "->" + NameColumnasFiltro.get(j));
                    String value = ListaAnimales.get(i).get(NameColumnasFiltro.get(j));
                    valores += "" + value;
                }
                boolean encontro = Expresiones.filtrobusqueda(filtros, valores);
                System.out.println("i-" + i + "-b-" + b);
                if (encontro) {
                    if(BanFiltroColor)
                        retorno.add(ListaAnimales.get(i));
                    else{
                        if(!ListaAnimales.get(i).get("EST").equals("*"))
                            retorno.add(ListaAnimales.get(i));
                    }
                }
            }
        }
        System.out.println("********************retorno --> " + retorno.size() + "***********************");
        return retorno;
    }
    
    public void IniciarFecha(){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        jdFechaPalpacion.setCalendar(Calendar.getInstance());    
    }

    public void CargarListadoFechas(){     
        ListadoFechas = controlgen.GetComboBox("SELECT 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT tbl.DESCRIPCION\n" +
                                                "FROM (SELECT DISTINCT DATE_FORMAT(`fecha_palpacion`, '%d/%m/%Y')  AS DESCRIPCION\n" +
                                                "FROM `palpacion` palp\n" +
                                                "INNER JOIN animales anim ON anim.`id` = palp.`id_animal`\n" +
                                                "INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n" +
                                                "WHERE tpo.`id_finca` = '"+idFinca+"' AND tpo.`id` = '"+idTipoAnimal+"' \n" +
                                                "ORDER BY palp.fecha_palpacion DESC) tbl");

        Utilidades.LlenarComboBox(cbListadoFechas, ListadoFechas, "DESCRIPCION");
        cbListadoFechas.setSelectedIndex(0);
    }
    
    
    public void EventoOrdenTabla(MouseEvent e){
        if(!tbl_Animales.isEnabled())
            return;
        
        int col = tbl_Animales.columnAtPoint(e.getPoint());
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
            EventoComboFincas();
        }
    }

    private String TipoDato(int ind, String Dato) {
        String ret = "";
        if(ind < 4){
            ret = "CONVERT("+Dato+",INT)";
        }else{
            ret = Dato;
        }
        return ret;
    }
    
}
