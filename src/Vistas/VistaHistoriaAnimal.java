package Vistas;

import Charts.Panel;
import Control.*;
import Modelo.ModeloAnimales;
import Modelo.ModeloPalpacion;
import Modelo.ModeloPesaje;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MERRY
 */
public class VistaHistoriaAnimal extends javax.swing.JPanel {

    String id_Animal = "";
    String numero_Animal = "";
    private ControlAnimales controlAnimales = new ControlAnimales();
    private ControlPesaje controlPesaje = new ControlPesaje();
    private ControlPalpacion controlPalpacion = new ControlPalpacion();
    private ControlTraslado controlTraslado = new ControlTraslado();
    private ModeloPalpacion modeloPalpacion = new ModeloPalpacion();
    private ControlRotacionDosTablas controlRotacion = new ControlRotacionDosTablas();
    private ArrayList<ModeloAnimales> ListaDatos;
    private Map<String, String> DatosVenta;
    private Map<String, String> DatosMuerte;
    private List<Map<String, String>> ListaDatosTraslado;
    private List<Map<String, String>> ListaDatosTrasladoEliminar;
    public DefaultTableModel modeloTblTraslado;
    public String[] EncabezadoTblTraslado;
    public String[] EncabezadoTblRotacion;
    public String[] EncabezadoTblPeso;
    public String[] EncabezadoTblPalpacion;
    private List<Map<String, String>> ListaDatosRotacion;
    //private List<Map<String, String>> ListaDatosPalpacion;
    private List<Map<String, String>> ListaDatosRotacionMostrar;
    private List<Map<String, String>> ListaDatosRotacionEliminar;
    public DefaultTableModel modeloTblRotacion;
    public DefaultTableModel modeloTblPeso;
    public DefaultTableModel modeloTblPalpacion;
    public Panel graficoPeso;
    private ArrayList<ArrayList<Object[]>> listaDatosPeso;
    private ArrayList<Object[]> datosPeso;
    private int countT = 0, countR = 0;
    private ArrayList<ModeloPesaje> listaPesajes;
    private ArrayList<ModeloPalpacion> ListaDatosPalpacion;
    public ModeloVentanaGeneral objetoVentana;
    private int ancho;
    private int alto;

    /**
     * Creates new form VistaHistoriaAnimal
     */
    public VistaHistoriaAnimal() {
        initComponents();
        setSize(781, 522);
    }

    public VistaHistoriaAnimal(ModeloVentanaGeneral modeloVentanaGeneral) {
        initComponents();
        setSize(781, 722);
        DatosVenta = new HashMap<String, String>();
        DatosMuerte = new HashMap<String, String>();
        id_Animal = "" + modeloVentanaGeneral.getModeloDatos();
        ListaDatos = new ArrayList<>();
        listaPesajes = new ArrayList<>();
        ListaDatosTraslado = new ArrayList<>();
        ListaDatosRotacion = new ArrayList<>();
        ListaDatosPalpacion = new ArrayList<>();
        ListaDatosRotacionMostrar = new ArrayList<>();
        ListaDatosTrasladoEliminar = new ArrayList<>();
        ListaDatosRotacionEliminar = new ArrayList<>();
        datosPeso = new ArrayList<>();
//        [772, 293]
        pnlPeso.setSize(739, 423);
        listaDatosPeso = new ArrayList<>();
        btnEliminarTraslados.setEnabled(false);
        btnEliminarRotaciones.setEnabled(false);
        EncabezadoTblTraslado = new String[]{
            "No",
            "Grupo",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Traslado</p></html>",
            "Motivo",
            "Estado"
        };
        ListaDatos = new ArrayList<>();
        EncabezadoTblRotacion = new String[]{
            "No",
            "Grupo",
            "Bloque / Lote",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Entrada</p></html>",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Salida</p></html>",
            "Estado"
        };
        EncabezadoTblPeso = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Pesado</p></html> ",
            "Peso (Kg)",
            "Notas",
            "Ver Más",
            "Modificar",
            "Eliminar"
        };
        EncabezadoTblPalpacion = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Palpación</p></html> ",
            "Estado",
            "Notas",
            "Ver Más",
            "Modificar",
            "Eliminar"
        };
        InicializarTblRotacion();
        InicializarTblTralado();
        InicializarTblPeso();
        InicializarTblPalpacion();

        GetDatosAnimal();
        pnlGrafico.setVisible(false);
        btnGrilla.setEnabled(false);
        if(listaDatosPeso.size()>0){
            graficoPeso = new Panel(listaDatosPeso, pnlGrafico);
            graficoPeso.setBounds(0, 0, pnlGrafico.getWidth(), pnlGrafico.getHeight());
            pnlGrafico.add(graficoPeso);
        }

    }

    public void InicializarTblRotacion() {
        tbl_Rotaciones.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblRotacion = new DefaultTableModel(EncabezadoTblRotacion, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Rotaciones.setModel(modeloTblRotacion);
        tbl_Rotaciones.setSelectionModel(new DefaultListSelectionModel() {
            private int i0 = -1;
            private int i1 = -1;

            public void setSelectionInterval(int index0, int index1) {
                if (i0 == index0 && i1 == index1) {
                    if (getValueIsAdjusting()) {
                        setValueIsAdjusting(false);
                        setSelection(index0, index1);
                    }
                } else {
                    i0 = index0;
                    i1 = index1;
                    setValueIsAdjusting(false);
                    setSelection(index0, index1);
                }
            }

            private void setSelection(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        tbl_Rotaciones.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Rotaciones.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_Rotaciones.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbl_Rotaciones.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl_Rotaciones.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl_Rotaciones.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_Rotaciones.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblRotacion.getColumnCount(); i++) {
            tbl_Rotaciones.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));

//                if(i == 2 ){
//                    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//
//                }else{
            tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Rotaciones.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Rotaciones.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);

    }

    public void InicializarTblTralado() {
        tbl_Traslados.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblTraslado = new DefaultTableModel(EncabezadoTblTraslado, 0) {
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

        tbl_Traslados.setModel(modeloTblTraslado);
        tbl_Traslados.setSelectionModel(new DefaultListSelectionModel() {
            private int i0 = -1;
            private int i1 = -1;

            public void setSelectionInterval(int index0, int index1) {
                if (i0 == index0 && i1 == index1) {
                    if (getValueIsAdjusting()) {
                        setValueIsAdjusting(false);
                        setSelection(index0, index1);
                    }
                } else {
                    i0 = index0;
                    i1 = index1;
                    setValueIsAdjusting(false);
                    setSelection(index0, index1);
                }
            }

            private void setSelection(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        tbl_Traslados.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Traslados.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_Traslados.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbl_Traslados.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl_Traslados.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl_Traslados.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblTraslado.getColumnCount(); i++) {
            tbl_Traslados.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));

//                if(i == 2 ){
//                    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//
//                }else{
            tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Traslados.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Traslados.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);

    }
    public void InicializarTblPeso() {
        tblDatosPeso.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblPeso = new DefaultTableModel(EncabezadoTblPeso, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tblDatosPeso.setSelectionModel(new DefaultListSelectionModel() {
            private int i0 = -1;
            private int i1 = -1;

            public void setSelectionInterval(int index0, int index1) {
                if (i0 == index0 && i1 == index1) {
                    if (getValueIsAdjusting()) {
                        setValueIsAdjusting(false);
                        setSelection(index0, index1);
                    }
                } else {
                    i0 = index0;
                    i1 = index1;
                    setValueIsAdjusting(false);
                    setSelection(index0, index1);
                }
            }

            private void setSelection(int index0, int index1) {
                if (super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                } else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });

        tblDatosPeso.setModel(modeloTblPeso);

        tblDatosPeso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblDatosPeso.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblDatosPeso.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDatosPeso.getColumnModel().getColumn(3).setPreferredWidth(130);
        tblDatosPeso.getColumnModel().getColumn(5).setPreferredWidth(70);
        tblDatosPeso.getColumnModel().getColumn(6).setPreferredWidth(70);
        
        tblDatosPeso.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblPeso.getColumnCount(); i++) {
            tblDatosPeso.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));

            tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
            tcr.setForeground(new Color(26, 82, 118));
            tblDatosPeso.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tblDatosPeso.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);

    }

    public void InicializarTblPalpacion() {
        tbl_Palpacion.setDefaultRenderer(Object.class, new TablaRender());

        modeloTblPalpacion = new DefaultTableModel(EncabezadoTblPalpacion, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
//        tbl_Palpacion.setSelectionModel(new DefaultListSelectionModel() {
//            private int i0 = -1;
//            private int i1 = -1;
//
//            public void setSelectionInterval(int index0, int index1) {
//                if (i0 == index0 && i1 == index1) {
//                    if (getValueIsAdjusting()) {
//                        setValueIsAdjusting(false);
//                        setSelection(index0, index1);
//                    }
//                } else {
//                    i0 = index0;
//                    i1 = index1;
//                    setValueIsAdjusting(false);
//                    setSelection(index0, index1);
//                }
//            }
//
//            private void setSelection(int index0, int index1) {
//                if (super.isSelectedIndex(index0)) {
//                    super.removeSelectionInterval(index0, index1);
//                } else {
//                    super.addSelectionInterval(index0, index1);
//                }
//            }
//        });

        tbl_Palpacion.setModel(modeloTblPalpacion);

        tbl_Palpacion.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Palpacion.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl_Palpacion.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Palpacion.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbl_Palpacion.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_Palpacion.getColumnModel().getColumn(6).setPreferredWidth(70);
        
        tbl_Palpacion.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblPalpacion.getColumnCount(); i++) {
            tbl_Palpacion.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));

            tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
            tcr.setForeground(new Color(26, 82, 118));
            tbl_Palpacion.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tbl_Palpacion.getTableHeader();

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

        lblNotas = new javax.swing.JLabel();
        lbltitle4 = new javax.swing.JLabel();
        lbltitle17 = new javax.swing.JLabel();
        lbltitle12 = new javax.swing.JLabel();
        lbltitle15 = new javax.swing.JLabel();
        lbltitle7 = new javax.swing.JLabel();
        lbltitle16 = new javax.swing.JLabel();
        lbltitle14 = new javax.swing.JLabel();
        lbltitle10 = new javax.swing.JLabel();
        lbltitle8 = new javax.swing.JLabel();
        lbltitle19 = new javax.swing.JLabel();
        lblTid1 = new javax.swing.JLabel();
        lbltitle9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlMuerte = new javax.swing.JPanel();
        lblTid4 = new javax.swing.JLabel();
        txtFechaMuerte = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        lblTid5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMotivoMuerte = new javax.swing.JTextArea();
        pnlVenta = new javax.swing.JPanel();
        lblTid6 = new javax.swing.JLabel();
        txtFechaVenta = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        txtTipoVenta = new javax.swing.JTextField();
        lblTid7 = new javax.swing.JLabel();
        lblTid8 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        lblTid9 = new javax.swing.JLabel();
        txtPesoCanal = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        lblTid10 = new javax.swing.JLabel();
        txtPorcentajeCanal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        lblTid11 = new javax.swing.JLabel();
        lblTid12 = new javax.swing.JLabel();
        txtPrecioTotal = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        txtPrecioVenta = new javax.swing.JTextField();
        pnlTraslados = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Traslados = new javax.swing.JTable();
        btnEliminarTraslados = new javax.swing.JButton();
        pnlRotaciones = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Rotaciones = new javax.swing.JTable();
        btnEliminarRotaciones = new javax.swing.JButton();
        pnlPeso = new javax.swing.JPanel();
        btnGrilla = new javax.swing.JButton();
        btnGrafico = new javax.swing.JButton();
        pnlGrilla = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDatosPeso = new javax.swing.JTable();
        pnlGrafico = new javax.swing.JPanel();
        pnlPalpacion = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_Palpacion = new javax.swing.JTable();
        lblPeso = new javax.swing.JLabel();
        lblTipoAnimal = new javax.swing.JLabel();
        lblPropietario = new javax.swing.JLabel();
        lblGrupo = new javax.swing.JLabel();
        lblHierro = new javax.swing.JLabel();
        lblFinca = new javax.swing.JLabel();
        lblNumMama = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblCalificacion = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lblNotas.setForeground(new java.awt.Color(59, 123, 50));
        lblNotas.setText("Finca");
        lblNotas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 26;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 21);
        add(lblNotas, gridBagConstraints);

        lbltitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle4.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle4.setText("Tipo de animal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        add(lbltitle4, gridBagConstraints);

        lbltitle17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle17.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle17.setText("Propietarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        add(lbltitle17, gridBagConstraints);

        lbltitle12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle12.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle12.setText("Grupo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        add(lbltitle12, gridBagConstraints);

        lbltitle15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle15.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle15.setText("Hierro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        add(lbltitle15, gridBagConstraints);

        lbltitle7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle7.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle7.setText("# de la madre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.001;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 0, 0);
        add(lbltitle7, gridBagConstraints);

        lbltitle16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle16.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle16.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 0, 0);
        add(lbltitle16, gridBagConstraints);

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle14.setText("Peso (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 0, 0);
        add(lbltitle14, gridBagConstraints);

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle10.setText("Genero");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 0, 0);
        add(lbltitle10, gridBagConstraints);

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle8.setText("Calificación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 8, 0, 0);
        add(lbltitle8, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        add(lbltitle19, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTid1.setText("Hoja de Vida Animal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        add(lblTid1, gridBagConstraints);

        lbltitle9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle9.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltitle9.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.001;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        add(lbltitle9, gridBagConstraints);

        jTabbedPane1.setForeground(new java.awt.Color(59, 123, 50));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnlMuerte.setBackground(new java.awt.Color(255, 255, 255));
        pnlMuerte.setLayout(new java.awt.GridBagLayout());

        lblTid4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid4.setForeground(new java.awt.Color(59, 123, 50));
        lblTid4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid4.setText("Fecha Muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        pnlMuerte.add(lblTid4, gridBagConstraints);

        txtFechaMuerte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFechaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtFechaMuerte.setBorder(null);
        txtFechaMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtFechaMuerte.setFocusCycleRoot(true);
        txtFechaMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtFechaMuerte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaMuerteFocusLost(evt);
            }
        });
        txtFechaMuerte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaMuerteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaMuerteKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 130;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 20);
        pnlMuerte.add(txtFechaMuerte, gridBagConstraints);

        jSeparator8.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 129;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        pnlMuerte.add(jSeparator8, gridBagConstraints);

        lblTid5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid5.setForeground(new java.awt.Color(59, 123, 50));
        lblTid5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid5.setText("Motivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 67;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 0, 0);
        pnlMuerte.add(lblTid5, gridBagConstraints);

        txtMotivoMuerte.setColumns(20);
        txtMotivoMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtMotivoMuerte.setRows(5);
        txtMotivoMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtMotivoMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane3.setViewportView(txtMotivoMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 267;
        gridBagConstraints.ipady = 67;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 20, 20);
        pnlMuerte.add(jScrollPane3, gridBagConstraints);

        jTabbedPane1.addTab("Muerte", pnlMuerte);

        pnlVenta.setBackground(new java.awt.Color(255, 255, 255));
        pnlVenta.setLayout(new java.awt.GridBagLayout());

        lblTid6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid6.setForeground(new java.awt.Color(59, 123, 50));
        lblTid6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid6.setText("Fecha Venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        pnlVenta.add(lblTid6, gridBagConstraints);

        txtFechaVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFechaVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtFechaVenta.setBorder(null);
        txtFechaVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtFechaVenta.setFocusCycleRoot(true);
        txtFechaVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtFechaVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFechaVentaFocusLost(evt);
            }
        });
        txtFechaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFechaVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaVentaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 0);
        pnlVenta.add(txtFechaVenta, gridBagConstraints);

        jSeparator9.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlVenta.add(jSeparator9, gridBagConstraints);

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlVenta.add(jSeparator10, gridBagConstraints);

        txtTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTipoVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtTipoVenta.setBorder(null);
        txtTipoVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtTipoVenta.setFocusCycleRoot(true);
        txtTipoVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtTipoVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTipoVentaFocusLost(evt);
            }
        });
        txtTipoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoVentaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        pnlVenta.add(txtTipoVenta, gridBagConstraints);

        lblTid7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid7.setForeground(new java.awt.Color(59, 123, 50));
        lblTid7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid7.setText("Tipo Venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        pnlVenta.add(lblTid7, gridBagConstraints);

        lblTid8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid8.setForeground(new java.awt.Color(59, 123, 50));
        lblTid8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid8.setText("Peso");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 81;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        pnlVenta.add(lblTid8, gridBagConstraints);

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(59, 123, 50));
        txtPeso.setBorder(null);
        txtPeso.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPeso.setFocusCycleRoot(true);
        txtPeso.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPeso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoFocusLost(evt);
            }
        });
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 0);
        pnlVenta.add(txtPeso, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlVenta.add(jSeparator11, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(59, 123, 50));
        jLabel14.setText("Kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        pnlVenta.add(jLabel14, gridBagConstraints);

        lblTid9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid9.setForeground(new java.awt.Color(59, 123, 50));
        lblTid9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid9.setText("Peso Canal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 44;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        pnlVenta.add(lblTid9, gridBagConstraints);

        txtPesoCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setBorder(null);
        txtPesoCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setFocusCycleRoot(true);
        txtPesoCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoCanalFocusLost(evt);
            }
        });
        txtPesoCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesoCanalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoCanalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 0);
        pnlVenta.add(txtPesoCanal, gridBagConstraints);

        jSeparator12.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlVenta.add(jSeparator12, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(59, 123, 50));
        jLabel15.setText("Kg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0E-4;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        pnlVenta.add(jLabel15, gridBagConstraints);

        lblTid10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid10.setForeground(new java.awt.Color(59, 123, 50));
        lblTid10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid10.setText("Porcentaje Canal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        pnlVenta.add(lblTid10, gridBagConstraints);

        txtPorcentajeCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPorcentajeCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setBorder(null);
        txtPorcentajeCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setFocusCycleRoot(true);
        txtPorcentajeCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPorcentajeCanalFocusLost(evt);
            }
        });
        txtPorcentajeCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPorcentajeCanalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeCanalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        pnlVenta.add(txtPorcentajeCanal, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(59, 123, 50));
        jLabel13.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0E-4;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 20);
        pnlVenta.add(jLabel13, gridBagConstraints);

        jSeparator13.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlVenta.add(jSeparator13, gridBagConstraints);

        lblTid11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid11.setForeground(new java.awt.Color(59, 123, 50));
        lblTid11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid11.setText("Precio Venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 30, 0, 0);
        pnlVenta.add(lblTid11, gridBagConstraints);

        lblTid12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid12.setForeground(new java.awt.Color(59, 123, 50));
        lblTid12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid12.setText("Precio Total");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        pnlVenta.add(lblTid12, gridBagConstraints);

        txtPrecioTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioTotal.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioTotal.setBorder(null);
        txtPrecioTotal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioTotal.setFocusCycleRoot(true);
        txtPrecioTotal.setName(""); // NOI18N
        txtPrecioTotal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioTotalFocusLost(evt);
            }
        });
        txtPrecioTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioTotalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioTotalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        pnlVenta.add(txtPrecioTotal, gridBagConstraints);

        jSeparator14.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        pnlVenta.add(jSeparator14, gridBagConstraints);

        jSeparator15.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 279;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        pnlVenta.add(jSeparator15, gridBagConstraints);

        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setBorder(null);
        txtPrecioVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setFocusCycleRoot(true);
        txtPrecioVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioVentaFocusLost(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 280;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 0, 0);
        pnlVenta.add(txtPrecioVenta, gridBagConstraints);

        jTabbedPane1.addTab("Venta", pnlVenta);

        pnlTraslados.setBackground(new java.awt.Color(255, 255, 255));
        pnlTraslados.setVerifyInputWhenFocusTarget(false);
        pnlTraslados.setLayout(new java.awt.GridBagLayout());

        tbl_Traslados.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Traslados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_Traslados.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_Traslados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tbl_Traslados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_TrasladosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Traslados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 20, 20);
        pnlTraslados.add(jScrollPane1, gridBagConstraints);

        btnEliminarTraslados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar30.png"))); // NOI18N
        btnEliminarTraslados.setBorderPainted(false);
        btnEliminarTraslados.setContentAreaFilled(false);
        btnEliminarTraslados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarTraslados.setPreferredSize(new java.awt.Dimension(30, 30));
        btnEliminarTraslados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar30_over.png"))); // NOI18N
        btnEliminarTraslados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTrasladosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -29;
        gridBagConstraints.ipady = -7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 20);
        pnlTraslados.add(btnEliminarTraslados, gridBagConstraints);

        jTabbedPane1.addTab("Traslados", pnlTraslados);

        pnlRotaciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlRotaciones.setVerifyInputWhenFocusTarget(false);
        pnlRotaciones.setLayout(new java.awt.GridBagLayout());

        tbl_Rotaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Rotaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_Rotaciones.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_Rotaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_RotacionesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Rotaciones);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 20, 20);
        pnlRotaciones.add(jScrollPane2, gridBagConstraints);

        btnEliminarRotaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar30.png"))); // NOI18N
        btnEliminarRotaciones.setBorderPainted(false);
        btnEliminarRotaciones.setContentAreaFilled(false);
        btnEliminarRotaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarRotaciones.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar30_over.png"))); // NOI18N
        btnEliminarRotaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarRotacionesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -29;
        gridBagConstraints.ipady = -7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 20);
        pnlRotaciones.add(btnEliminarRotaciones, gridBagConstraints);

        jTabbedPane1.addTab("Rotaciones", pnlRotaciones);

        pnlPeso.setBackground(new java.awt.Color(255, 255, 255));
        pnlPeso.setLayout(new java.awt.GridBagLayout());

        btnGrilla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/tabla30.png"))); // NOI18N
        btnGrilla.setToolTipText("Ver Datos");
        btnGrilla.setBorderPainted(false);
        btnGrilla.setContentAreaFilled(false);
        btnGrilla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrilla.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/tabla30_over.png"))); // NOI18N
        btnGrilla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrillaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = -29;
        gridBagConstraints.ipady = -7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        pnlPeso.add(btnGrilla, gridBagConstraints);

        btnGrafico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/grafica30.png"))); // NOI18N
        btnGrafico.setToolTipText("Ver Grafico");
        btnGrafico.setBorderPainted(false);
        btnGrafico.setContentAreaFilled(false);
        btnGrafico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGrafico.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/grafica30_over.png"))); // NOI18N
        btnGrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -29;
        gridBagConstraints.ipady = -7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 15);
        pnlPeso.add(btnGrafico, gridBagConstraints);

        pnlGrilla.setBackground(new java.awt.Color(255, 255, 255));
        pnlGrilla.setLayout(new java.awt.GridBagLayout());

        tblDatosPeso.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDatosPeso.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tblDatosPeso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDatosPesoMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblDatosPeso);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlGrilla.add(jScrollPane4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlPeso.add(pnlGrilla, gridBagConstraints);

        pnlGrafico.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlGraficoLayout = new javax.swing.GroupLayout(pnlGrafico);
        pnlGrafico.setLayout(pnlGraficoLayout);
        pnlGraficoLayout.setHorizontalGroup(
            pnlGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
        );
        pnlGraficoLayout.setVerticalGroup(
            pnlGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlPeso.add(pnlGrafico, gridBagConstraints);

        jTabbedPane1.addTab("Peso", pnlPeso);

        pnlPalpacion.setBackground(new java.awt.Color(255, 255, 255));

        tbl_Palpacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Palpacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_PalpacionMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_Palpacion);

        javax.swing.GroupLayout pnlPalpacionLayout = new javax.swing.GroupLayout(pnlPalpacion);
        pnlPalpacion.setLayout(pnlPalpacionLayout);
        pnlPalpacionLayout.setHorizontalGroup(
            pnlPalpacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPalpacionLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlPalpacionLayout.setVerticalGroup(
            pnlPalpacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPalpacionLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Palpacion", pnlPalpacion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 11, 20);
        add(jTabbedPane1, gridBagConstraints);

        lblPeso.setForeground(new java.awt.Color(59, 123, 50));
        lblPeso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPeso.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblPeso, gridBagConstraints);

        lblTipoAnimal.setForeground(new java.awt.Color(59, 123, 50));
        lblTipoAnimal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoAnimal.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 205;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblTipoAnimal, gridBagConstraints);

        lblPropietario.setForeground(new java.awt.Color(59, 123, 50));
        lblPropietario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPropietario.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 205;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblPropietario, gridBagConstraints);

        lblGrupo.setForeground(new java.awt.Color(59, 123, 50));
        lblGrupo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGrupo.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 205;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblGrupo, gridBagConstraints);

        lblHierro.setForeground(new java.awt.Color(59, 123, 50));
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHierro.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 205;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblHierro, gridBagConstraints);

        lblFinca.setForeground(new java.awt.Color(59, 123, 50));
        lblFinca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFinca.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 205;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblFinca, gridBagConstraints);

        lblNumMama.setForeground(new java.awt.Color(59, 123, 50));
        lblNumMama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumMama.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblNumMama, gridBagConstraints);

        lblNumero.setForeground(new java.awt.Color(59, 123, 50));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumero.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblNumero, gridBagConstraints);

        lblGenero.setForeground(new java.awt.Color(59, 123, 50));
        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGenero.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblGenero, gridBagConstraints);

        lblCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        lblCalificacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCalificacion.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 0, 0);
        add(lblCalificacion, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaMuerteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaMuerteFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaMuerteFocusLost

    private void txtFechaMuerteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaMuerteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaMuerteKeyPressed

    private void txtFechaMuerteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaMuerteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaMuerteKeyReleased

    private void txtFechaVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFechaVentaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVentaFocusLost

    private void txtFechaVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVentaKeyPressed

    private void txtFechaVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVentaKeyReleased

    private void txtTipoVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTipoVentaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVentaFocusLost

    private void txtTipoVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVentaKeyPressed

    private void txtTipoVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoVentaKeyReleased

    private void txtPesoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoFocusLost

    private void txtPesoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyPressed

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoCanalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoCanalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCanalFocusLost

    private void txtPesoCanalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoCanalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCanalKeyPressed

    private void txtPesoCanalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoCanalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCanalKeyReleased

    private void txtPorcentajeCanalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPorcentajeCanalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeCanalFocusLost

    private void txtPorcentajeCanalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCanalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeCanalKeyPressed

    private void txtPorcentajeCanalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCanalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeCanalKeyReleased

    private void txtPrecioTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioTotalFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTotalFocusLost

    private void txtPrecioTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTotalKeyPressed

    private void txtPrecioTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioTotalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTotalKeyReleased

    private void txtPrecioVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioVentaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaFocusLost

    private void txtPrecioVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaKeyPressed

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaKeyReleased

    private void tbl_TrasladosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TrasladosMousePressed
        int tamfilas = tbl_Traslados.getSelectedRows().length;
        if (tamfilas > 0) {
            btnEliminarTraslados.setEnabled(true);
        } else {
            btnEliminarTraslados.setEnabled(false);
        }
    }//GEN-LAST:event_tbl_TrasladosMousePressed

    private void tbl_RotacionesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_RotacionesMousePressed
        int tamfilas = tbl_Rotaciones.getSelectedRows().length;
        if (tamfilas > 0) {
            btnEliminarRotaciones.setEnabled(true);
        } else {
            btnEliminarRotaciones.setEnabled(false);
        }
    }//GEN-LAST:event_tbl_RotacionesMousePressed

    private void btnEliminarTrasladosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTrasladosActionPerformed
        ListaDatosTrasladoEliminar = new ArrayList<>();
        for (int fila : tbl_Traslados.getSelectedRows()) {
            ListaDatosTrasladoEliminar.add(ListaDatosTraslado.get(fila));
        }
        String text = (tbl_Traslados.getSelectedRows().length == 1 ? "la fila seleccionada" : "las filas seleccionadas");

        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar " + text + ".?");
        if (resp == JOptionPane.YES_OPTION) {
            int ret = controlTraslado.EliminarTraslados(ListaDatosTrasladoEliminar);
            if (ret == 0) {
                JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
                GetDatosTraslado();
            }
        }
    }//GEN-LAST:event_btnEliminarTrasladosActionPerformed

    private void btnEliminarRotacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarRotacionesActionPerformed
        ListaDatosRotacionEliminar = new ArrayList<>();
        for (int fila : tbl_Rotaciones.getSelectedRows()) {
            ListaDatosRotacionEliminar.add(ListaDatosRotacion.get(fila));
        }
        String text = (tbl_Rotaciones.getSelectedRows().length == 1 ? "la fila seleccionada" : "las filas seleccionadas");

        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar " + text + ".?");
        if (resp == JOptionPane.YES_OPTION) {
            int ret = controlRotacion.EliminarRotaciones(ListaDatosRotacionEliminar);
            if (ret == 0) {
                JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
                GetDatosRotaciones();
            }
        }
    }//GEN-LAST:event_btnEliminarRotacionesActionPerformed

    private void btnGrillaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrillaActionPerformed
        pnlGrafico.setVisible(false);
        pnlGrilla.setVisible(true);
        btnGrilla.setEnabled(false);
        btnGrafico.setEnabled(true);
    }//GEN-LAST:event_btnGrillaActionPerformed

    private void btnGraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficoActionPerformed
        pnlGrafico.setVisible(true);
        pnlGrilla.setVisible(false);
        btnGrilla.setEnabled(true);
        btnGrafico.setEnabled(false);
    }//GEN-LAST:event_btnGraficoActionPerformed

    private void tblDatosPesoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosPesoMouseReleased
        int fila = tblDatosPeso.getSelectedRow();
        int cola = tblDatosPeso.getSelectedColumn();
        if(cola == 4){// Ver MAs
            

        }else if(cola == 5){ //ELIMINAR
//            modeloLotes = ListamodeloLotes.get(fila);
//            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
//            if(resp == JOptionPane.YES_OPTION){
//                int ret = controlLote.Eliminar(modeloLotes);
//                if(ret == 0){
//                    JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
//                    AccionCombo();
//                }
//            }
        }
    }//GEN-LAST:event_tblDatosPesoMouseReleased

    private void tbl_PalpacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PalpacionMouseReleased
        int fila = tbl_Palpacion.getSelectedRow();
        int cola = tbl_Palpacion.getSelectedColumn();
        if(cola == 4){// Ver MAs
            System.out.println("---->"+fila);
            System.out.println("lis--<"+ListaDatosPalpacion.size());
            modeloPalpacion = ListaDatosPalpacion.get(fila);
            objetoVentana = new ModeloVentanaGeneral(this, new VistaInfoPalpacion(), 1, modeloPalpacion);
            new VistaGeneral(objetoVentana).setVisible(true);

        }else if(cola == 5){ //ELIMINAR
//            modeloLotes = ListamodeloLotes.get(fila);
//            int resp = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar esta Fila?");
//            if(resp == JOptionPane.YES_OPTION){
//                int ret = controlLote.Eliminar(modeloLotes);
//                if(ret == 0){
//                    JOptionPane.showMessageDialog(null, "La operación se realizo exitosamente.");
//                    AccionCombo();
//                }
//            }
        }
    }//GEN-LAST:event_tbl_PalpacionMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarRotaciones;
    private javax.swing.JButton btnEliminarTraslados;
    private javax.swing.JButton btnGrafico;
    private javax.swing.JButton btnGrilla;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblCalificacion;
    private javax.swing.JLabel lblFinca;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblHierro;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblNumMama;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPropietario;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid10;
    private javax.swing.JLabel lblTid11;
    private javax.swing.JLabel lblTid12;
    private javax.swing.JLabel lblTid4;
    private javax.swing.JLabel lblTid5;
    private javax.swing.JLabel lblTid6;
    private javax.swing.JLabel lblTid7;
    private javax.swing.JLabel lblTid8;
    private javax.swing.JLabel lblTid9;
    private javax.swing.JLabel lblTipoAnimal;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle12;
    private javax.swing.JLabel lbltitle14;
    private javax.swing.JLabel lbltitle15;
    private javax.swing.JLabel lbltitle16;
    private javax.swing.JLabel lbltitle17;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JLabel lbltitle4;
    private javax.swing.JLabel lbltitle7;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JLabel lbltitle9;
    private javax.swing.JPanel pnlGrafico;
    private javax.swing.JPanel pnlGrilla;
    private javax.swing.JPanel pnlMuerte;
    private javax.swing.JPanel pnlPalpacion;
    private javax.swing.JPanel pnlPeso;
    private javax.swing.JPanel pnlRotaciones;
    private javax.swing.JPanel pnlTraslados;
    private javax.swing.JPanel pnlVenta;
    private javax.swing.JTable tblDatosPeso;
    private javax.swing.JTable tbl_Palpacion;
    private javax.swing.JTable tbl_Rotaciones;
    private javax.swing.JTable tbl_Traslados;
    public javax.swing.JTextField txtFechaMuerte;
    public javax.swing.JTextField txtFechaVenta;
    private javax.swing.JTextArea txtMotivoMuerte;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPesoCanal;
    public javax.swing.JTextField txtPorcentajeCanal;
    public javax.swing.JTextField txtPrecioTotal;
    public javax.swing.JTextField txtPrecioVenta;
    public javax.swing.JTextField txtTipoVenta;
    // End of variables declaration//GEN-END:variables

    private void GetDatosAnimal() {
        ListaDatos = (ArrayList<ModeloAnimales>) controlAnimales.ObtenerDatosKey(id_Animal);
        LlenarDatos();
        
    }

   

    private void LlenarDatos() {
        lblCalificacion.setText(ListaDatos.get(0).getCalificacion());
        lblFinca.setText(ListaDatos.get(0).getDescFinca());
        lblGenero.setText(ListaDatos.get(0).getGenero());
        lblGrupo.setText(ListaDatos.get(0).getDescGrupo());
        lblHierro.setText(ListaDatos.get(0).getDescHierro());
        lblNotas.setText(ListaDatos.get(0).getNotas());
        lblNumMama.setText(ListaDatos.get(0).getNumeroMama());
        lblNumero.setText(ListaDatos.get(0).getNumero());
        lblPeso.setText(ListaDatos.get(0).getPeso());
        lblPropietario.setText(ListaDatos.get(0).getDescPropietario());
        lblTipoAnimal.setText(ListaDatos.get(0).getDescTipoAnimal());

        if (ListaDatos.get(0).getVenta().equals("1")) {
            GetDatosVentaAnimal();
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setSelectedIndex(1);
        } else {
            jTabbedPane1.setEnabledAt(1, false);
        }
        if (ListaDatos.get(0).getMuerte().equals("1")) {
            GetDatosMuerteAnimal();
            jTabbedPane1.setEnabledAt(0, true);
            jTabbedPane1.setSelectedIndex(0);
        } else {
            jTabbedPane1.setEnabledAt(0, false);
        }
        if(ListaDatos.get(0).getMuerte().equals("0") && ListaDatos.get(0).getVenta().equals("0"))
            jTabbedPane1.setSelectedIndex(2);
        
        jTabbedPane1.setEnabledAt(5, ListaDatos.get(0).getGenero().equals("hembra"));
        

        GetDatosTraslado();
        GetDatosRotaciones();
        cargarHistoricoPesos();
        if (ListaDatos.get(0).getGenero().equals("hembra")) {
            GetDatosPalpacion();
        }
    }

    private void LimpiarFormulario() {
        lblCalificacion.setText("");
        lblFinca.setText("");
        lblGenero.setText("");
        lblGrupo.setText("");
        lblHierro.setText("");
        lblNotas.setText("");
        lblNumMama.setText("");
        lblNumero.setText("");
        lblPeso.setText("");
        lblPropietario.setText("");
        lblTipoAnimal.setText("");
        LimpiarFormularioVenta();
        LimpiarFormularioMuerte();
    }

    //<editor-fold defaultstate="collapsed" desc="TabbletPane Venta">
    private void GetDatosVentaAnimal() {
        DatosVenta = controlAnimales.GetDatosVenta(id_Animal);

        LimpiarFormularioVenta();
        txtFechaVenta.setText("" + DatosVenta.get("FECHA_VENTA"));
        txtTipoVenta.setText("" + DatosVenta.get("TIPO_VENTA"));
        txtPeso.setText("" + DatosVenta.get("PESO"));
        if (DatosVenta.get("TIPO_VENTA").equals("matadero")) {
            txtPesoCanal.setText("" + DatosVenta.get("PESO_CANAL"));
            txtPorcentajeCanal.setText("" + DatosVenta.get("PORCENTAJE_CANAL"));
        }
        txtPrecioVenta.setText("" + DatosVenta.get("PRECIO_VENTA"));
        txtPrecioTotal.setText("" + DatosVenta.get("PRECIO_TOTAL"));
    }

    public void LimpiarFormularioVenta() {
        txtFechaVenta.setText("");
        txtTipoVenta.setText("");
        txtPeso.setText("");
        txtPesoCanal.setText("");
        txtPorcentajeCanal.setText("");
        txtPrecioVenta.setText("");
        txtPrecioTotal.setText("");
    }

    public void BloquearFormularioVenta() {
        txtFechaVenta.setEnabled(false);
        txtTipoVenta.setEnabled(false);
        txtPeso.setEnabled(false);
        txtPesoCanal.setEnabled(false);
        txtPorcentajeCanal.setEnabled(false);
        txtPrecioVenta.setEnabled(false);
        txtPrecioTotal.setEnabled(false);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TabbletPane Muerte">
    private void BloquearFormularioMuerte() {
        txtFechaMuerte.setEnabled(false);
        txtMotivoMuerte.setEnabled(false);
    }

    private void GetDatosMuerteAnimal() {
        DatosMuerte = controlAnimales.GetDatosMuerte(id_Animal);
        LimpiarFormularioMuerte();
        txtFechaMuerte.setText("" + DatosMuerte.get("FECHA_MUERTE"));
        txtMotivoMuerte.setText("" + DatosMuerte.get("MOTIVO"));
    }

    private void LimpiarFormularioMuerte() {
        txtFechaMuerte.setText("");
        txtMotivoMuerte.setText("");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TabbletPane Traslados">
    private void GetDatosTraslado() {
        ListaDatosTraslado = controlAnimales.GetDatosTraslado(id_Animal);

        numero_Animal = ListaDatosTraslado.get(0).get("NUMERO_ANIMAL");
        LlenarTablaTraslado();
    }

    private void LlenarTablaTraslado() {
        Utilidades.LimpiarTabla(tbl_Traslados);
//        SELECT anim.`numero` AS NUMERO_ANIMAL, grup.`descripcion` AS GRUPO,
//DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y') AS FECHA_TRASLADO,
//traslado.motivo AS MOTIVO, traslado.estado AS ESTADO
        for (int i = 0; i < ListaDatosTraslado.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTblTraslado,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        ListaDatosTraslado.get(i).get("GRUPO"),
                        ListaDatosTraslado.get(i).get("FECHA_TRASLADO"),
                        ListaDatosTraslado.get(i).get("MOTIVO"),
                        ListaDatosTraslado.get(i).get("ESTADO")
                    }
            );
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TabbletPane Rotacion">
    private void GetDatosRotaciones() {
        ListaDatosRotacion = controlAnimales.GetDatosrotaciones(id_Animal);

        if (ListaDatosRotacion.size() > 0) {

            numero_Animal = ListaDatosRotacion.get(0).get("NUMERO_ANIMAL");
            EstablecerDatosRotacion();
        }

    }

    private void EstablecerDatosRotacion() {
        ListaDatosRotacionMostrar.clear();
        int indAnt = -1;
        String fechaSig = "";
        List<Map<String, String>> listaTraslados = Utilidades.data_list(1, ListaDatosRotacion, new String[]{"IDTRASLADO"});
        for (Map<String, String> traslado : listaTraslados) {
            System.out.println("ind-->" + indAnt);
            List<Map<String, String>> listaDatosTraslado = Utilidades.data_list(3, ListaDatosRotacion, new String[]{"IDTRASLADO<->" + traslado.get("IDTRASLADO")});
            if (indAnt > -1) {
                fechaSig = listaTraslados.get(indAnt).get("FECHA_TRASLADO");
            }
            for (Map<String, String> datos : listaDatosTraslado) {
                if (fechaSig.equals("")) {
                    if (datos.get("FECHA_SALIDA").equals("") && datos.get("EST_TRASLADO").equals("Activo")) {//ACTUAL
                        ListaDatosRotacionMostrar.add(datos);
                    } else if (!datos.get("FECHA_SALIDA").equals("")
                            && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_SALIDA")) <= 0
                            && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_ENTRADA")) <= 0) {//MENOR  QUE LA FECHA SALIDA Y FECHA DE ENTRADA
                        ListaDatosRotacionMostrar.add(datos);
                    } else if (!datos.get("FECHA_SALIDA").equals("")
                            && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_SALIDA")) <= 0) {//MENOR  QUE LA FECHA SALIDA Y FECHA DE ENTRADA
                        ListaDatosRotacionMostrar.add(datos);
                    }
                } else {
                    if (!datos.get("FECHA_SALIDA").equals("") && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_ENTRADA")) <= 0
                            && CompararFechas(datos.get("FECHA_ENTRADA"), fechaSig) <= 0
                            && CompararFechas(datos.get("FECHA_SALIDA"), fechaSig) >= 0) {
                        ListaDatosRotacionMostrar.add(datos);
                    } else if (!datos.get("FECHA_SALIDA").equals("") && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_ENTRADA")) >= 0
                            && CompararFechas(datos.get("FECHA_TRASLADO"), datos.get("FECHA_SALIDA")) <= 0) {
                        ListaDatosRotacionMostrar.add(datos);
                    } else if (datos.get("FECHA_SALIDA").equals("") && datos.get("EST_ROTACION").equals("Activo")
                            && CompararFechas(fechaSig, datos.get("FECHA_ENTRADA")) >= 0) {
                        ListaDatosRotacionMostrar.add(datos);
                    }
                }

            }

            indAnt++;
        }

        LlenarTablaRotaciones();
    }

    private void LlenarTablaRotaciones() {
        Utilidades.LimpiarTabla(tbl_Rotaciones);

        for (int i = 0; i < ListaDatosRotacionMostrar.size(); i++) {

            Utilidades.agregarFilaTabla(
                    modeloTblRotacion,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        ListaDatosRotacionMostrar.get(i).get("GRUPO"),
                        ListaDatosRotacionMostrar.get(i).get("BLOQUE") + " / " + ListaDatosRotacionMostrar.get(i).get("LOTE"),
                        ListaDatosRotacionMostrar.get(i).get("FECHA_ENTRADA"),
                        ListaDatosRotacionMostrar.get(i).get("FECHA_SALIDA"),
                        ListaDatosRotacionMostrar.get(i).get("ESTADO")
                    }
            );
        }
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="PESO">
    private void cargarHistoricoPesos() {
        listaPesajes = (ArrayList<ModeloPesaje>) controlPesaje.ObtenerDatosFiltro(id_Animal);
        if (listaPesajes.size() > 0) {
            ///////////////////////////   X        Y
            datosPeso.add(new Object[]{"Fecha", "Peso"});
            for (int i = listaPesajes.size()-1; i >= 0; i--) {
                datosPeso.add(new Object[]{
                    listaPesajes.get(i).getFecha_pesado(),
                    listaPesajes.get(i).getPeso()
                });
            }
            listaDatosPeso.add(datosPeso);
            LlenarTablaPesos();
        }
    }
    
    private void LlenarTablaPesos() {
        Utilidades.LimpiarTabla(tblDatosPeso);

        for (int i = 0; i < listaPesajes.size(); i++) {

            Utilidades.agregarFilaTabla(
                    modeloTblPeso,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        listaPesajes.get(i).getFecha_pesado(),
                        listaPesajes.get(i).getPeso(),
                        listaPesajes.get(i).getNotas(),
                        "Ver Mas",
//                        listaPesajes.get(i).getHierro().equals("1")?"Si":"No",
//                        listaPesajes.get(i).getDescornado().equals("1")?"Si":"No",
//                        listaPesajes.get(i).getImplante().equals("1")?"Si":"No",
//                        listaPesajes.get(i).getDestete().equals("1")?"Si":"No",
                        "Eliminar"
                    }
            );
        }
    }

    public int CompararFechas(String fechaDesde, String fechaHasta) {
        Date fd = new Date(Integer.parseInt(fechaDesde.split("/")[2]) - 1900, Integer.parseInt(fechaDesde.split("/")[1]) - 1, Integer.parseInt(fechaDesde.split("/")[0])),
                fh = new Date(Integer.parseInt(fechaHasta.split("/")[2]) - 1900, Integer.parseInt(fechaHasta.split("/")[1]) - 1, Integer.parseInt(fechaHasta.split("/")[0]));
        int ret = fd.compareTo(fh);
        return ret;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="TabbletPane Palpacion">
    private void GetDatosPalpacion() {
        ListaDatosPalpacion =( ArrayList<ModeloPalpacion>) controlPalpacion.ObtenerDatosFiltro(id_Animal);

        if (ListaDatosPalpacion.size() > 0) {

            //numero_Animal = ListaDatosPalpacion.get(0).get("NUMERO_ANIMAL");
            LlenarTablaPalpacion();
        }
    }
    private void LlenarTablaPalpacion() {
        Utilidades.LimpiarTabla(tbl_Palpacion);
//        SELECT anim.`numero` AS NUMERO_ANIMAL, grup.`descripcion` AS GRUPO,
//DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y') AS FECHA_TRASLADO,
//traslado.motivo AS MOTIVO, traslado.estado AS ESTADO
        for (int i = 0; i < ListaDatosPalpacion.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTblPalpacion,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        ListaDatosPalpacion.get(i).getFecha_palpacion(),
                        ListaDatosPalpacion.get(i).getDiagnostico(),
                        ListaDatosPalpacion.get(i).getNotas(),
                        "Ver Mas",
                        "Eliminar"
                    }
            );
        }
    }
    
//</editor-fold>
    

    

}
