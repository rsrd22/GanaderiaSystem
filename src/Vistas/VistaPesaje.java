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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class VistaPesaje extends javax.swing.JPanel {

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
    public List<Map<String, String>> ListadoFechas;
    private ControlRAnimales controlAnimales = new ControlRAnimales();
    public int allFincas;
    public ModeloVentanaGeneral objetoVentana;
    public String[] NameColumnas;
    public ArrayList<String> NameColumnasFiltro;
    public ArrayList<String> NameColumnasOrden;
    Map<String, Map<String, String>> PropiedadesColumnas = new HashMap<>();
    public int band = 0;
    public int filaSeleccionada;
    public String fechaAnterior = "";
    private String fechaFiltro = "";
    private String ban = "0";
    public int idModulo = 20;
    public int bandOrden = 0;
    public int colOrden = 0;
    public String Orden = "";


    /**
     * Creates new form VistaVerAnimales
     */
    public VistaPesaje() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        idFinca = "";
        idTipoAnimal = "";
        BanFiltroColor = true;
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
        //<editor-fold defaultstate="collapsed" desc="ORDEN TABLA">
            NameColumnasOrden = new ArrayList<>();
            NameColumnasOrden.add("NUMERO_ANIMAL");
            NameColumnasOrden.add("NUMERO_MAMA");
            NameColumnasOrden.add("GENERO");
            NameColumnasOrden.add("FECHA_NACIMIENTO");
            NameColumnasOrden.add("PESO");
            NameColumnasOrden.add("DESC_HIERRO");
            NameColumnasOrden.add("CAPADO");
            NameColumnasOrden.add("GRUPO");
            NameColumnasOrden.add("FINCA");
//</editor-fold>
        EncabezadoTblAnimales = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Animal</p></html>",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Mamá</p></html>",
            "Genero",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Nacimiento</p></html>",
            "Peso",
            "Hierro",
            "Capado",
            "Grupo",
            "Finca",
            "Acción", "Est"
        };
        ListaAnimales = new ArrayList<>();
        listaFincas = new ArrayList<>();
        listaTipoAnimales = new ArrayList<>();
        InicializarTblAnimales();
        IniciarFechaFiltro();
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
        tbl_Animales.getColumnModel().getColumn(7).setPreferredWidth(50);
        tbl_Animales.getColumnModel().getColumn(8).setPreferredWidth(90);
        tbl_Animales.getColumnModel().getColumn(9).setPreferredWidth(130);
        tbl_Animales.getColumnModel().getColumn(10).setPreferredWidth(80);
        tbl_Animales.getColumnModel().getColumn(11).setPreferredWidth(5);

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

        tbl_Animales.getTableHeader().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {     
                EventoOrdenTabla(e);
            }
        });
    }

    public void IniciarFechaFiltro() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        jdFechaPesaje.setCalendar(Calendar.getInstance());
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
        tbl_Animales = new Tablas.DiarioTable();
        lbltitle19 = new javax.swing.JLabel();
        jdFechaPesaje = new com.toedter.calendar.JDateChooser();
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
        gridBagConstraints.weightx = 0.3;
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
        gridBagConstraints.weightx = 0.3;
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
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        add(cbTipoAnimales, gridBagConstraints);

        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(59, 123, 50));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid2.setText("Tipo Animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lblTid2, gridBagConstraints);

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
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtFiltro, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator6, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Filtro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
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
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(lbltitle19, gridBagConstraints);

        jdFechaPesaje.setDateFormatString("dd/MM/yyyy");
        jdFechaPesaje.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdFechaPesajePropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jdFechaPesaje, gridBagConstraints);

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
        lblTid3.setText("Listado Fechas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
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
            ban = "1";
            if (cbTipoAnimales.getSelectedIndex() >= 0) {
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
                cargarListaFechasPesajes();
                EventoComboFincas();
            } else {
                Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

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

        if (band == 0) {
            if (dato.equalsIgnoreCase("PESAJE") && tbl_Animales.getValueAt(filaSeleccionada, cola + 1).toString().isEmpty()) {
                tbl_Animales.setValueAt("*", filaSeleccionada, cola + 1);
                band = 1;
                if (fechaAnterior.equals("")) {
                    fechaAnterior = fechaFiltro;
                }
                objetoVentana = new ModeloVentanaGeneral(
                        this,
                        new VistaIngresoPesaje(),
                        1,
                        ListaAnimalesMostrar.get(filaSeleccionada)
                );
                new VistaGeneral(objetoVentana).setVisible(true);
            } else if (dato.equalsIgnoreCase("*")) {
                band = 1;
                if (fechaAnterior.equals("")) {
                    fechaAnterior = fechaFiltro;
                }
                objetoVentana = new ModeloVentanaGeneral(
                        this,
                        new VistaIngresoPesaje(),
                        2,
                        ListaAnimalesMostrar.get(filaSeleccionada)
                );
                new VistaGeneral(objetoVentana).setVisible(true);
            }
        }
    }//GEN-LAST:event_tbl_AnimalesMouseReleased

    private void jdFechaPesajePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdFechaPesajePropertyChange
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fechaC = jdFechaPesaje.getCalendar();
        fechaFiltro = sdf.format(fechaC.getTime());
        if (ban.equals("1")) {
            EventoComboFincas();
        }
    }//GEN-LAST:event_jdFechaPesajePropertyChange

    private void cbListadoFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListadoFechasActionPerformed
        if (cbListadoFechas.getItemCount() > 0) {
            if (cbListadoFechas.getSelectedIndex() > 0) {
                try {
                    String fechaSeleccionada = cbListadoFechas.getSelectedItem().toString();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = formato.parse(fechaSeleccionada);
                    jdFechaPesaje.setDate(fecha);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar la fecha\nDetalle:\n" + ex.getMessage());
                }
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
    public com.toedter.calendar.JDateChooser jdFechaPesaje;
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
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM fincas\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        CargarListaTipoAnimales();
    }

    private void CargarListaTipoAnimales() {
        listaTipoAnimales = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM tipo_animales\n"
                + "WHERE id_finca = '" + idFinca + "' AND estado = 'Activo'\n"
                + "ORDER BY id ASC");

        Utilidades.LlenarComboBox(cbTipoAnimales, listaTipoAnimales, "DESCRIPCION");
        cbTipoAnimales.setSelectedIndex(0);
        EventoComboFincas();
    }

    private void EventoComboFincas() {
        cargarTablaFiltro();
    }

    public void cargarTablaFiltro() {
        if (idFinca.equals("ALL")) {
            allFincas = 1;
        } else {
            allFincas = 0;
        }
        if (Integer.parseInt(idFinca) > 0) {
            ListaAnimales = (List<Map<String, String>>) controlAnimales.ObtenerDatosAnimalesPesables(idFinca, idTipoAnimal, fechaFiltro, Orden);
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

    private void MostrarTabla() {
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
                        ListaAnimalesMostrar.get(i).get("GENERO").toUpperCase(),
                        ListaAnimalesMostrar.get(i).get("FECHA_NACIMIENTO"),
                        ListaAnimalesMostrar.get(i).get("PESO"),
                        Utilidades.decodificarElemento(ListaAnimalesMostrar.get(i).get("DESC_HIERRO")),
                        ListaAnimalesMostrar.get(i).get("CAPADO"),
                        Utilidades.decodificarElemento(ListaAnimalesMostrar.get(i).get("GRUPO")),
                        Utilidades.decodificarElemento(ListaAnimalesMostrar.get(i).get("FINCA")),
                        "PESAJE",
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

    private void cargarListaFechasPesajes() {
        ListadoFechas = controlgen.GetComboBox("SELECT 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT tabla.DESCRIPCION FROM \n"
                + "(SELECT DISTINCT \n"
                + "DATE_FORMAT(pes.fecha_pesado, '%d/%m/%Y') AS DESCRIPCION\n"
                + "FROM pesaje pes\n"
                + "INNER JOIN animales anim ON anim.id = pes.id_animal\n"
                + "INNER JOIN grupos gr ON gr.id = anim.grupo\n"
                + "INNER JOIN tipo_animales ta ON anim.id_tipo_animal=ta.id\n"
                + "WHERE ta.id = '"+idTipoAnimal+"' AND ta.id_finca='"+idFinca+"' AND gr.pesable = '1' \n"
                + "AND anim.notas NOT IN(\n"
                + "'PRIMER PESO (AUTOMATICO)',\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO'\n"
                + ") ORDER BY pes.fecha_pesado DESC) tabla");

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
        if(ind == 0 || ind == 1){
            ret = "CONVERT("+Dato+",INT)";
        }else{
            ret = Dato;
        }
        return ret;
    }
    
}
