package Vistas;

import Charts.Panel;
import Control.*;
import Control.RAnimales.ControlRAnimales;
import Modelo.ModeloAnimales;
import Modelo.ModeloPalpacion;
import Modelo.ModeloPesaje;
import Modelo.ModeloVentanaGeneral;
import Modelo.RAnimales.*;
import Tablas.TablaRender;
import Utilidades.Utilidades;
import Vistas._Animales.Vista_VerAnimales;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
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

    private String id_Animal = "";
    private String numero_Animal = "";
    private String localizacion = "", pesoNac = "", FecUltPeso = "";
    private ControlRAnimales controlAnimales = new ControlRAnimales();
    private ControlPesaje controlPesaje = new ControlPesaje();
    private ControlPalpacion controlPalpacion = new ControlPalpacion();
    private ControlTraslado controlTraslado = new ControlTraslado();
    private ModeloPalpacion modeloPalpacion = new ModeloPalpacion();
    private ModeloPesaje modeloPesaje = new ModeloPesaje();
    private ControlRotacionDosTablas controlRotacion = new ControlRotacionDosTablas();
    private ArrayList<ModeloRAnimalesSalida> ListaDatos;
    private Map<String, String> DatosVenta;
    private Map<String, String> DatosMuerte;
    private Map<String[], JLabel> listaHistorico;
    private List<Map<String, String>> ListaDatosTraslado;
    private List<Map<String, String>> ListaDatosTrasladoEliminar;
    public DefaultTableModel modeloTblTraslado;
    public String[] EncabezadoTblTraslado;
    public String[] EncabezadoTblRotacion;
    public String[] EncabezadoTblPeso;
    public String[] EncabezadoTblPalpacion;
    private List<Map<String, String>> ListaDatosRotacion;
    private ModeloRAnimalesSalida modeloMadre = new ModeloRAnimalesSalida();
    private List<Map<String, String>> ListaDatosRotacionMostrar;
    private List<Map<String, String>> ListaDatosRotacionEliminar;
    private List<Map<String, String>> ListaDatosPartos;
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
    public int band;
    private String STRING_VACIO = "      ";
    private final String FECHA_POR_DEFECTO = "1900-01-01";
    private List<Map<String, String>> ListaAnimalesMostrar;
    private int filaLista;
    private JTable refTablaAnimales;

    private int[] flags;
    private int[] flagsPnlCrias;
    private JPanel[] panels;
    ArrayList<JPanel> panelesCria = new ArrayList<>();
    private final int size = 2;
    private int sizeCrias = 0;
    public String[] datoModificado = new String[]{};

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
        listaHistorico = new HashMap<String[], JLabel>();
        this.ListaAnimalesMostrar = ((Vista_VerAnimales) modeloVentanaGeneral.getPanelPadre()).ListaAnimalesMostrar;
        this.refTablaAnimales = ((Vista_VerAnimales) modeloVentanaGeneral.getPanelPadre()).tbl_Animales;
        filaLista = Integer.parseInt("" + modeloVentanaGeneral.getModeloDatos());
        id_Animal = ListaAnimalesMostrar.get(filaLista).get("ID_ANIMAL");
        band = 0;
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
            "Peso Anterior (Kg)",
            "dif (Kg)",
            "Notas",
            "Ver Más",
            "Eliminar"
        };
        EncabezadoTblPalpacion = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Palpación</p></html> ",
            "Estado",
            "Notas",
            "Ver Más",
            "Eliminar"
        };
        InicializarTblRotacion();
        InicializarTblTralado();
        InicializarTblPeso();
        InicializarTblPalpacion();

        GetDatosAnimal();
        pnlGrafico.setVisible(false);
        btnGrilla.setEnabled(false);

        //<editor-fold defaultstate="collapsed" desc="INIT ACORDEON CRIAS">
        flagsPnlCrias = new int[sizeCrias];
        initFlagsCrias();
        hidePanelsCrias();
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INIT ACORDEON">
        flags = new int[size];
        panels = new JPanel[]{
            panelBody1,
            panelBody2
        };
        initFlags();
        hidePanels();
//</editor-fold>
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,};

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tblDatosPeso.setModel(modeloTblPeso);

        tblDatosPeso.getColumnModel().getColumn(0).setPreferredWidth(25);
        tblDatosPeso.getColumnModel().getColumn(1).setPreferredWidth(80);
        tblDatosPeso.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDatosPeso.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDatosPeso.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblDatosPeso.getColumnModel().getColumn(3).setPreferredWidth(130);
        tblDatosPeso.getColumnModel().getColumn(5).setPreferredWidth(70);

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

        tbl_Palpacion.setModel(modeloTblPalpacion);

        tbl_Palpacion.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Palpacion.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl_Palpacion.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_Palpacion.getColumnModel().getColumn(3).setPreferredWidth(130);
        tbl_Palpacion.getColumnModel().getColumn(5).setPreferredWidth(70);

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

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlDatosBasicos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lbltitle8 = new javax.swing.JLabel();
        lblCalificacion = new javax.swing.JLabel();
        lblHierroColocado = new javax.swing.JLabel();
        lbltitle18 = new javax.swing.JLabel();
        lbltitle20 = new javax.swing.JLabel();
        lblDescornado = new javax.swing.JLabel();
        lblImplante = new javax.swing.JLabel();
        lbltitle27 = new javax.swing.JLabel();
        lbltitNovilla = new javax.swing.JLabel();
        lblNovilla = new javax.swing.JLabel();
        lblNotas = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        panelBody2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        panelBtnParto = new javax.swing.JPanel();
        btnParto = new javax.swing.JButton();
        lblNumMeses = new javax.swing.JLabel();
        lblFecUltParto = new javax.swing.JLabel();
        lblMesesAbierto = new javax.swing.JLabel();
        lblNumPartos = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        panelBody1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        lblPartoNumero = new javax.swing.JLabel();
        lblUltimoPeso = new javax.swing.JLabel();
        lblPesoDestete = new javax.swing.JLabel();
        lblPesoNecimiento = new javax.swing.JLabel();
        lblFecUltPeso = new javax.swing.JLabel();
        lblFechaDestete = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblTid1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        lblFinca = new javax.swing.JLabel();
        lblNumMama = new javax.swing.JLabel();
        lblGrupo = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblTipoAnimal = new javax.swing.JLabel();
        lblHierro = new javax.swing.JLabel();
        lblNumeroCria = new javax.swing.JLabel();
        lblLocalizacion = new javax.swing.JLabel();
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
        pnlPartos = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        pnlContenedorPartos = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblTid2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        jTabbedPane1.setForeground(new java.awt.Color(59, 123, 50));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        pnlDatosBasicos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatosBasicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pnlDatosBasicosMouseReleased(evt);
            }
        });
        pnlDatosBasicos.setLayout(new java.awt.GridBagLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel11.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lbltitle8.setBackground(new java.awt.Color(255, 255, 255));
        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle8.setText("Calificación");
        lbltitle8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lbltitle8, gridBagConstraints);

        lblCalificacion.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        lblCalificacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCalificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        lblCalificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lblCalificacion, gridBagConstraints);

        lblHierroColocado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHierroColocado.setForeground(new java.awt.Color(59, 123, 50));
        lblHierroColocado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHierroColocado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        lblHierroColocado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lblHierroColocado, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle18.setText("Colocado?");
        lbltitle18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lbltitle18, gridBagConstraints);

        lbltitle20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltitle20.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle20.setText("Descornado?");
        lbltitle20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lbltitle20, gridBagConstraints);

        lblDescornado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblDescornado.setForeground(new java.awt.Color(59, 123, 50));
        lblDescornado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescornado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        lblDescornado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lblDescornado, gridBagConstraints);

        lblImplante.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblImplante.setForeground(new java.awt.Color(59, 123, 50));
        lblImplante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImplante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        lblImplante.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lblImplante, gridBagConstraints);

        lbltitle27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltitle27.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitle27.setText("Implante?");
        lbltitle27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lbltitle27, gridBagConstraints);

        lbltitNovilla.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltitNovilla.setForeground(new java.awt.Color(59, 123, 50));
        lbltitNovilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitNovilla.setText("Novilla");
        lbltitNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lbltitNovilla, gridBagConstraints);

        lblNovilla.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNovilla.setForeground(new java.awt.Color(59, 123, 50));
        lblNovilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        lblNovilla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        jPanel2.add(lblNovilla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jPanel2, gridBagConstraints);

        lblNotas.setBackground(new java.awt.Color(255, 255, 255));
        lblNotas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNotas.setForeground(new java.awt.Color(59, 123, 50));
        lblNotas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblNotas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel3.add(lblNotas, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel11.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        jPanel7.add(jPanel11, gridBagConstraints);

        jPanel10.setBackground(new java.awt.Color(59, 123, 50));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel10.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Otros");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        jPanel10.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel10, gridBagConstraints);

        jPanel8.setBackground(new java.awt.Color(59, 123, 50));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Preñez");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        jPanel8.add(jLabel3, gridBagConstraints);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel8.add(jButton2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel8, gridBagConstraints);

        panelBody2.setBackground(new java.awt.Color(255, 255, 255));
        panelBody2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelBody2.setLayout(new java.awt.GridBagLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        panelBtnParto.setBackground(new java.awt.Color(59, 123, 50));
        panelBtnParto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelBtnParto.setLayout(new java.awt.GridBagLayout());

        btnParto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnParto.setForeground(new java.awt.Color(255, 255, 255));
        btnParto.setText("Ingresar Parto");
        btnParto.setBorderPainted(false);
        btnParto.setContentAreaFilled(false);
        btnParto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnParto.setFocusPainted(false);
        btnParto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPartoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPartoMouseExited(evt);
            }
        });
        btnParto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelBtnParto.add(btnParto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel12.add(panelBtnParto, gridBagConstraints);

        lblNumMeses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumMeses.setForeground(new java.awt.Color(59, 123, 50));
        lblNumMeses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumMeses.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Meses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel12.add(lblNumMeses, gridBagConstraints);

        lblFecUltParto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecUltParto.setForeground(new java.awt.Color(59, 123, 50));
        lblFecUltParto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFecUltParto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha Ultimo Parto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel12.add(lblFecUltParto, gridBagConstraints);

        lblMesesAbierto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMesesAbierto.setForeground(new java.awt.Color(59, 123, 50));
        lblMesesAbierto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMesesAbierto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Meses abierto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel12.add(lblMesesAbierto, gridBagConstraints);

        lblNumPartos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumPartos.setForeground(new java.awt.Color(59, 123, 50));
        lblNumPartos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumPartos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número de partos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel12.add(lblNumPartos, gridBagConstraints);

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(59, 123, 50));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEstado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Estado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel12.add(lblEstado, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(14, 14, 14, 14);
        panelBody2.add(jPanel12, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        jPanel7.add(panelBody2, gridBagConstraints);

        panelBody1.setBackground(new java.awt.Color(255, 255, 255));
        panelBody1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelBody1.setLayout(new java.awt.GridBagLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridBagLayout());

        lblPartoNumero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPartoNumero.setForeground(new java.awt.Color(59, 123, 50));
        lblPartoNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPartoNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# parto (hijo #x) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel9.add(lblPartoNumero, gridBagConstraints);

        lblUltimoPeso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUltimoPeso.setForeground(new java.awt.Color(59, 123, 50));
        lblUltimoPeso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUltimoPeso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peso (kg) o peso novilla o peso descarte ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblUltimoPeso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        jPanel9.add(lblUltimoPeso, gridBagConstraints);

        lblPesoDestete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoDestete.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peso destete", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblPesoDestete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        jPanel9.add(lblPesoDestete, gridBagConstraints);

        lblPesoNecimiento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPesoNecimiento.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoNecimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoNecimiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peso nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblPesoNecimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel9.add(lblPesoNecimiento, gridBagConstraints);

        lblFecUltPeso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFecUltPeso.setForeground(new java.awt.Color(59, 123, 50));
        lblFecUltPeso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFecUltPeso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha ultimo peso ó novilla ó descarte ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel9.add(lblFecUltPeso, gridBagConstraints);

        lblFechaDestete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFechaDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDestete.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de destete", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblFechaDestete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel9.add(lblFechaDestete, gridBagConstraints);

        lblFechaNacimiento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaNacimiento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblFechaNacimiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel9.add(lblFechaNacimiento, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        panelBody1.add(jPanel9, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        jPanel7.add(panelBody1, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(59, 123, 50));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pesos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        jPanel6.add(jLabel1, gridBagConstraints);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel6.add(jButton1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel6, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(59, 123, 50));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        lblTid1.setBackground(new java.awt.Color(59, 123, 50));
        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(255, 255, 255));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTid1.setText("Hoja de Vida Animal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.ABOVE_BASELINE_LEADING;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        jPanel4.add(lblTid1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(jPanel4, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.GridBagLayout());

        lblFinca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblFinca.setForeground(new java.awt.Color(59, 123, 50));
        lblFinca.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFinca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Finca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblFinca, gridBagConstraints);

        lblNumMama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumMama.setForeground(new java.awt.Color(59, 123, 50));
        lblNumMama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumMama.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número de la Madre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblNumMama, gridBagConstraints);

        lblGrupo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGrupo.setForeground(new java.awt.Color(59, 123, 50));
        lblGrupo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGrupo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblGrupo, gridBagConstraints);

        lblGenero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblGenero.setForeground(new java.awt.Color(59, 123, 50));
        lblGenero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblGenero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel13.add(lblGenero, gridBagConstraints);

        lblNumero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumero.setForeground(new java.awt.Color(59, 123, 50));
        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número animal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel13.add(lblNumero, gridBagConstraints);

        lblTipoAnimal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTipoAnimal.setForeground(new java.awt.Color(59, 123, 50));
        lblTipoAnimal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTipoAnimal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de animal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        jPanel13.add(lblTipoAnimal, gridBagConstraints);

        lblHierro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblHierro.setForeground(new java.awt.Color(59, 123, 50));
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHierro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hierro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblHierro, gridBagConstraints);

        lblNumeroCria.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblNumeroCria.setForeground(new java.awt.Color(59, 123, 50));
        lblNumeroCria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumeroCria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número de cria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        lblNumeroCria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblNumeroCria, gridBagConstraints);

        lblLocalizacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblLocalizacion.setForeground(new java.awt.Color(59, 123, 50));
        lblLocalizacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblLocalizacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Localización", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        jPanel13.add(lblLocalizacion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel5.add(jPanel13, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.25;
        jPanel7.add(jPanel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlDatosBasicos.add(jPanel7, gridBagConstraints);

        jTabbedPane1.addTab("Datos Basicos", pnlDatosBasicos);

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
            .addGap(0, 636, Short.MAX_VALUE)
        );
        pnlGraficoLayout.setVerticalGroup(
            pnlGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
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
        pnlPalpacion.setLayout(new java.awt.GridBagLayout());

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 35, 20);
        pnlPalpacion.add(jScrollPane5, gridBagConstraints);

        jTabbedPane1.addTab("Palpacion", pnlPalpacion);

        pnlPartos.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(null);

        pnlContenedorPartos.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedorPartos.setLayout(new java.awt.GridBagLayout());

        jPanel15.setBackground(new java.awt.Color(59, 123, 50));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        lblTid2.setBackground(new java.awt.Color(59, 123, 50));
        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(255, 255, 255));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTid2.setText("Datos de crias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        jPanel15.add(lblTid2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        pnlContenedorPartos.add(jPanel15, gridBagConstraints);

        jPanel14.setBackground(new java.awt.Color(153, 204, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(666, 50));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        pnlContenedorPartos.add(jPanel14, gridBagConstraints);

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        pnlContenedorPartos.add(jPanel16, gridBagConstraints);

        jPanel17.setBackground(new java.awt.Color(255, 204, 204));
        jPanel17.setPreferredSize(new java.awt.Dimension(666, 50));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        pnlContenedorPartos.add(jPanel17, gridBagConstraints);

        jPanel18.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        pnlContenedorPartos.add(jPanel18, gridBagConstraints);

        jPanel19.setBackground(new java.awt.Color(51, 255, 204));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlContenedorPartos.add(jPanel19, gridBagConstraints);

        jScrollPane6.setViewportView(pnlContenedorPartos);

        javax.swing.GroupLayout pnlPartosLayout = new javax.swing.GroupLayout(pnlPartos);
        pnlPartos.setLayout(pnlPartosLayout);
        pnlPartosLayout.setHorizontalGroup(
            pnlPartosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        pnlPartosLayout.setVerticalGroup(
            pnlPartosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );

        jTabbedPane1.addTab("Partos", pnlPartos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        add(jTabbedPane1, gridBagConstraints);

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anterior30.png"))); // NOI18N
        btnAnterior.setToolTipText("Registro anterior");
        btnAnterior.setBorderPainted(false);
        btnAnterior.setContentAreaFilled(false);
        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anterior30_over.png"))); // NOI18N
        btnAnterior.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anterior30_over.png"))); // NOI18N
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(btnAnterior, gridBagConstraints);

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/siguiente30.png"))); // NOI18N
        btnSiguiente.setToolTipText("Registro siguiente");
        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setContentAreaFilled(false);
        btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiguiente.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/siguiente30_over.png"))); // NOI18N
        btnSiguiente.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/siguiente30_over.png"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(btnSiguiente, gridBagConstraints);
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
        if (cola == 6) {// Ver MAs
            modeloPesaje = listaPesajes.get(fila);
            objetoVentana = new ModeloVentanaGeneral(this, new VistaInfoPesaje(), 1, modeloPesaje);
            new VistaGeneral(objetoVentana).setVisible(true);
        } else if (cola == 7) {
            modeloPesaje = listaPesajes.get(fila);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar este Registro?");
            if (respuesta == JOptionPane.YES_OPTION) {
                int retorno = controlPesaje.Eliminar(modeloPesaje);

                String mensaje = "";
                switch (retorno) {
                    case Retorno.EXITO:
                        mensaje = "Registro eliminado satisfactoriamente.";
                        GetDatosAnimal();
                        break;
                    case Retorno.ERROR:
                        mensaje = "El registro no pudo ser eliminado.";
                        break;
                    case Retorno.EXCEPCION_SQL:
                        mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                        break;
                    case Retorno.CLASE_NO_ENCONTRADA:
                        mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                        break;
                    case Retorno.MENSAJE:
                        mensaje = "No es posible eliminar el registro del peso.";
                        break;
                }

                JOptionPane.showMessageDialog(this, mensaje);
            }
        }
    }//GEN-LAST:event_tblDatosPesoMouseReleased

    private void tbl_PalpacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PalpacionMouseReleased
        int fila = tbl_Palpacion.getSelectedRow();
        int cola = tbl_Palpacion.getSelectedColumn();
        if (cola == 4) {// Ver MAs
            modeloPalpacion = ListaDatosPalpacion.get(fila);
            objetoVentana = new ModeloVentanaGeneral(this, new VistaInfoPalpacion(), 1, modeloPalpacion);
            new VistaGeneral(objetoVentana).setVisible(true);

        } else if (cola == 5) { //ELIMINAR
            modeloPalpacion = ListaDatosPalpacion.get(fila);
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar este Registro?");
            if (respuesta == JOptionPane.YES_OPTION) {
                int retorno = controlPalpacion.Eliminar(modeloPesaje);

                String mensaje = "";
                switch (retorno) {
                    case Retorno.EXITO:
                        mensaje = "Registro eliminado satisfactoriamente.";
                        break;
                    case Retorno.ERROR:
                        mensaje = "El registro no pudo ser eliminado.";
                        break;
                    case Retorno.EXCEPCION_SQL:
                        mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                        break;
                    case Retorno.CLASE_NO_ENCONTRADA:
                        mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                        break;
                    case Retorno.MENSAJE:
                        mensaje = "No es posible eliminar el registro del peso.";
                        break;
                }

                JOptionPane.showMessageDialog(this, mensaje);
            }
        }
    }//GEN-LAST:event_tbl_PalpacionMouseReleased

    private void btnPartoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPartoMouseEntered
        if (btnParto.isEnabled()) {
            Utilidades.establecerColorDeFondo(panelBtnParto, true);
        }
    }//GEN-LAST:event_btnPartoMouseEntered

    private void btnPartoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPartoMouseExited
        if (btnParto.isEnabled()) {
            Utilidades.establecerColorDeFondo(panelBtnParto, false);
        }
    }//GEN-LAST:event_btnPartoMouseExited

    private void btnPartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartoActionPerformed
        objetoVentana = new ModeloVentanaGeneral(
                this, //panelPadre
                new VistaNacimientoAnimal(), //panelHijo
                1, //opcion
                modeloMadre //modeloDeDatos
        );
        new VistaGeneral(objetoVentana).setVisible(true);
    }//GEN-LAST:event_btnPartoActionPerformed

    private void pnlDatosBasicosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlDatosBasicosMouseReleased
        try {
            Point p = evt.getPoint();
            JLabel label = (JLabel) pnlDatosBasicos.findComponentAt(p);
            String etiqueta = label.getText();

            /**
             * listaHistorico es un Map<String[], JLabel>
             * el array de string contiene lo siguiente: key[0]:
             * identificador<br>
             * key[1]: nombre de la tabla de la base de datos<br>key[2]: nombre
             * del campo de la tabla anterior
             */
            for (Map.Entry<String[], JLabel> entry : listaHistorico.entrySet()) {
                String[] key = entry.getKey();
                JLabel value = entry.getValue();
                if (label.equals(value)) {

                    if (key[2].equalsIgnoreCase("capado") && lbltitNovilla.getText().equalsIgnoreCase("novilla?")) {
                        return;
                    }

                    if (band == 0) {
                        band = 1;
                        datoModificado = key;
                        objetoVentana = new ModeloVentanaGeneral(
                                this, //panelPadre
                                new VistaEditarDatosAnimal(), //panelHijo
                                1, //opcion
                                entry //modeloDeDatos
                        );
                        new VistaGeneral(objetoVentana).setVisible(true);
                    }
                    break;
                }
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_pnlDatosBasicosMouseReleased

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        registroSiguiente();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        registroAnterior();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int panelNumber = 0;
        togglePanel(panelNumber);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int panelNumber = 1;
        togglePanel(panelNumber);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnEliminarRotaciones;
    private javax.swing.JButton btnEliminarTraslados;
    private javax.swing.JButton btnGrafico;
    private javax.swing.JButton btnGrilla;
    private javax.swing.JButton btnParto;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JLabel lblDescornado;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecUltParto;
    private javax.swing.JLabel lblFecUltPeso;
    private javax.swing.JLabel lblFechaDestete;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFinca;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblHierro;
    private javax.swing.JLabel lblHierroColocado;
    private javax.swing.JLabel lblImplante;
    private javax.swing.JLabel lblLocalizacion;
    private javax.swing.JLabel lblMesesAbierto;
    private javax.swing.JLabel lblNotas;
    private javax.swing.JLabel lblNovilla;
    private javax.swing.JLabel lblNumMama;
    private javax.swing.JLabel lblNumMeses;
    private javax.swing.JLabel lblNumPartos;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroCria;
    private javax.swing.JLabel lblPartoNumero;
    private javax.swing.JLabel lblPesoDestete;
    private javax.swing.JLabel lblPesoNecimiento;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid10;
    private javax.swing.JLabel lblTid11;
    private javax.swing.JLabel lblTid12;
    private javax.swing.JLabel lblTid2;
    private javax.swing.JLabel lblTid4;
    private javax.swing.JLabel lblTid5;
    private javax.swing.JLabel lblTid6;
    private javax.swing.JLabel lblTid7;
    private javax.swing.JLabel lblTid8;
    private javax.swing.JLabel lblTid9;
    private javax.swing.JLabel lblTipoAnimal;
    private javax.swing.JLabel lblUltimoPeso;
    private javax.swing.JLabel lbltitNovilla;
    private javax.swing.JLabel lbltitle18;
    private javax.swing.JLabel lbltitle20;
    private javax.swing.JLabel lbltitle27;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JPanel panelBody1;
    private javax.swing.JPanel panelBody2;
    private javax.swing.JPanel panelBtnParto;
    private javax.swing.JPanel pnlContenedorPartos;
    private javax.swing.JPanel pnlDatosBasicos;
    private javax.swing.JPanel pnlGrafico;
    private javax.swing.JPanel pnlGrilla;
    private javax.swing.JPanel pnlMuerte;
    private javax.swing.JPanel pnlPalpacion;
    private javax.swing.JPanel pnlPartos;
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
        LimpiarFormulario();
        ListaDatos = (ArrayList<ModeloRAnimalesSalida>) controlAnimales.ObtenerDatosKey(id_Animal);
        modeloMadre = ListaDatos.get(0);
        LlenarDatos();
    }

    private void LlenarDatos() {
        lblFinca.setText(Utilidades.decodificarElemento(ListaDatos.get(0).getDescFinca()));
        lblTipoAnimal.setText(ListaDatos.get(0).getDescTipoAnimal());
        lblHierro.setText(Utilidades.decodificarElemento(ListaDatos.get(0).getDescHierro()));
        lblNumMama.setText(ListaDatos.get(0).getNumero_mama());
        lblNumero.setText(ListaDatos.get(0).getNumero());
        lblGrupo.setText(Utilidades.decodificarElemento(ListaDatos.get(0).getDescGrupo()));
        lblGenero.setText(Utilidades.CapitalizeTexto(ListaDatos.get(0).getGenero()));
        lblLocalizacion.setText("");
        lblFechaNacimiento.setText(ListaDatos.get(0).getFecha_nacimiento());
        lblPesoNecimiento.setText(ListaDatos.get(0).getFecha_destete().equals("1900-01-01") ? STRING_VACIO : "" + ListaDatos.get(0).getFecha_nacimiento());
        lblPartoNumero.setText("" + ListaDatos.get(0).getNumero_descendiente());
        lblFechaDestete.setText(ListaDatos.get(0).getFecha_destete().equals("1900-01-01") ? STRING_VACIO : "" + ListaDatos.get(0).getFecha_destete());
        lblPesoDestete.setText("" + ListaDatos.get(0).getPeso_destete());

        lblFecUltParto.setText(ListaDatos.get(0).getFecha_destete().equals("1900-01-01") ? STRING_VACIO : "" + ListaDatos.get(0).getFecha_destete());
        lblUltimoPeso.setText(ListaDatos.get(0).getPeso());

        lblCalificacion.setText(ListaDatos.get(0).getCalificacion());

        lblHierroColocado.setText(ListaDatos.get(0).getHierro_fisico().equals("0") ? "No" : "Si");
        lblDescornado.setText(ListaDatos.get(0).getDescornado().equals("0") ? "No" : "Si");
        lblImplante.setText(ListaDatos.get(0).getImplante().equals("0") ? "No" : "Si");
        lblNovilla.setText(
                ListaDatos.get(0).getGenero().equals("hembra")
                ? (ListaDatos.get(0).getFecha_novilla().equals(FECHA_POR_DEFECTO) ? "No" : "Si")
                : ListaDatos.get(0).getCapado()
        );
        lbltitNovilla.setText(ListaDatos.get(0).getGenero().equals("hembra") ? "Novilla" : "Capado");
        lblNumeroCria.setVisible(ListaDatos.get(0).getGenero().equals("hembra"));

        lblNotas.setText(ListaDatos.get(0).getNotas().isEmpty() ? STRING_VACIO : Utilidades.decodificarElemento(ListaDatos.get(0).getNotas()));

        /**
         * listaHistorico es un Map<String[], JLabel>
         * el array de string contiene lo siguiente: key[0]: identificador<br>
         * key[1]: nombre de la tabla de la base de datos<br>key[2]: nombre del
         * campo de la tabla anterior
         */
        listaHistorico.put(new String[]{"Implante", "animales", "implante", ListaDatos.get(0).getId()}, lblImplante);
        listaHistorico.put(new String[]{"Descornado", "animales", "descornado", ListaDatos.get(0).getId()}, lblDescornado);
        listaHistorico.put(new String[]{"Fecha de destete", "animales", "fecha_destete", ListaDatos.get(0).getId()}, lblFechaDestete);
        listaHistorico.put(new String[]{"Hierro fisico", "animales", "hierro_fisico", ListaDatos.get(0).getId()}, lblHierroColocado);
        listaHistorico.put(new String[]{"Peso de destete", "animales", "peso_destete", ListaDatos.get(0).getId()}, lblPesoDestete);
        listaHistorico.put(new String[]{"Calificación", "animales", "calificacion", ListaDatos.get(0).getId()}, lblCalificacion);
        listaHistorico.put(new String[]{"Notas", "animales", "notas", ListaDatos.get(0).getId()}, lblNotas);
        listaHistorico.put(new String[]{"Capado", "animales", "capado", ListaDatos.get(0).getId()}, lblNovilla);
        if (ListaDatos.get(0).getNumero().equals(ListaDatos.get(0).getNumero_mama())) {
            listaHistorico.put(new String[]{
                "Número del animal",
                "animales",
                "numero",
                ListaDatos.get(0).getId(),
                ListaDatos.get(0).getId_tipo_animal()
            }, lblNumero);
            lblNumero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            lblNumero.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        boolean esHembra = ListaDatos.get(0).getGenero().toUpperCase().equals("HEMBRA");
        boolean estaDestetada = ListaDatos.get(0).getDestete().equals("1");
        btnParto.setEnabled(esHembra && estaDestetada);
        if (btnParto.isEnabled()) {
            panelBtnParto.setBackground(new Color(59, 123, 50));
        } else {
            panelBtnParto.setBackground(new Color(101, 101, 101));
        }

        lblFecUltParto.setVisible(esHembra);
        lblNumMeses.setVisible(esHembra);
        lblMesesAbierto.setVisible(esHembra);
        lblNumPartos.setVisible(esHembra);
        lblEstado.setVisible(esHembra);
        panelBtnParto.setVisible(esHembra);

        if (ListaDatos.get(0).getVenta().equals("1")) {
            GetDatosVentaAnimal();
            jTabbedPane1.setEnabledAt(2, true);
        } else {
            jTabbedPane1.setEnabledAt(2, false);
        }
        if (ListaDatos.get(0).getMuerte().equals("1")) {
            GetDatosMuerteAnimal();
            jTabbedPane1.setEnabledAt(1, true);
        } else {
            jTabbedPane1.setEnabledAt(1, false);
        }

        jTabbedPane1.setSelectedIndex(jTabbedPane1.isEnabledAt(jTabbedPane1.getSelectedIndex()) ? jTabbedPane1.getSelectedIndex() : 0);
        GetDatosTraslado();
        GetDatosRotaciones();
        cargarHistoricoPesos();
        actualizarGraficaPesos();
        if (esHembra) {
            getDatosHembra();
            GetDatosPalpacion();
            GetDatosParto();
        } else {
            lblNovilla.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jTabbedPane1.setEnabledAt(6,false);
            jTabbedPane1.setEnabledAt(7,false);
        }

        lblLocalizacion.setText("" + localizacion);
        lblFecUltPeso.setText("" + FecUltPeso);
        jTabbedPane1.repaint();
    }

    public void ActualizarDatosAnimal() {
        band = 0;
        GetDatosAnimal();
        if (datoModificado[0].equalsIgnoreCase("Número del animal")) {
            String numeroAnimal = ListaDatos.get(0).getNumero();
            ListaAnimalesMostrar.get(filaLista).put("NUMERO", numeroAnimal);
            refTablaAnimales.setValueAt(numeroAnimal, filaLista, 2);
        }
    }

    private void LimpiarFormulario() {
        LimpiarFormularioVenta();
        LimpiarFormularioMuerte();

        for (int i = 0; i < pnlDatosBasicos.getComponentCount(); i++) {
            if (pnlDatosBasicos.getComponent(i) instanceof JLabel) {
                ((JLabel) pnlDatosBasicos.getComponent(i)).setText("");
            }
        }
        lblTid1.setText("Hoja de Vida Animal");
    }

    //<editor-fold defaultstate="collapsed" desc="TabbetPane Venta">
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

    //<editor-fold defaultstate="collapsed" desc="TabblePane Muerte">
    private void BloquearFormularioMuerte() {
        txtFechaMuerte.setEnabled(false);
        txtMotivoMuerte.setEnabled(false);
    }

    private void GetDatosMuerteAnimal() {
        DatosMuerte = controlAnimales.GetDatosMuerte(id_Animal);
        LimpiarFormularioMuerte();
        txtFechaMuerte.setText(Utilidades.decodificarElemento(DatosMuerte.get("FECHA_MUERTE")));
        txtMotivoMuerte.setText(DatosMuerte.get("MOTIVO"));
    }

    private void LimpiarFormularioMuerte() {
        txtFechaMuerte.setText("");
        txtMotivoMuerte.setText("");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TabblePane Traslados">
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

    //<editor-fold defaultstate="collapsed" desc="TabbetPane Rotacion">
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
            if (i == 0) {
                localizacion = ListaDatosRotacionMostrar.get(i).get("BLOQUE") + " / " + ListaDatosRotacionMostrar.get(i).get("LOTE");
            }
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
        listaDatosPeso = new ArrayList<>();
        listaPesajes = (ArrayList<ModeloPesaje>) controlPesaje.ObtenerDatosFiltro(id_Animal);
        if (listaPesajes.size() > 0) {
            ///////////////////////////   X        Y
            datosPeso = new ArrayList<>();
            datosPeso.add(new Object[]{"Fecha", "Peso"});
            for (int i = listaPesajes.size() - 1; i >= 0; i--) {
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
            if (i == 0) {
                FecUltPeso = listaPesajes.get(i).getFecha_pesado();
            }
            Utilidades.agregarFilaTabla(
                    modeloTblPeso,
                    new Object[]{
                        (i + 1),//tbl_Grupos.getRowCount()+1,
                        listaPesajes.get(i).getFecha_pesado(),
                        listaPesajes.get(i).getPeso(),
                        listaPesajes.get(i).getPeso_anterior(),
                        Double.parseDouble(listaPesajes.get(i).getPeso()) - Double.parseDouble(listaPesajes.get(i).getPeso_anterior()),
                        listaPesajes.get(i).getNotas(),
                        "Ver Mas",
                        "Eliminar"
                    }
            );
            if (listaPesajes.get(i).getNotas().equals("REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO")) {
                pesoNac = listaPesajes.get(i).getPeso();
            }
        }
    }

    public int CompararFechas(String fechaDesde, String fechaHasta) {
        Date fd = new Date(Integer.parseInt(fechaDesde.split("/")[2]) - 1900, Integer.parseInt(fechaDesde.split("/")[1]) - 1, Integer.parseInt(fechaDesde.split("/")[0])),
                fh = new Date(Integer.parseInt(fechaHasta.split("/")[2]) - 1900, Integer.parseInt(fechaHasta.split("/")[1]) - 1, Integer.parseInt(fechaHasta.split("/")[0]));
        int ret = fd.compareTo(fh);
        return ret;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="TabbetPane Palpacion">
    private void GetDatosPalpacion() {
        ListaDatosPalpacion = (ArrayList<ModeloPalpacion>) controlPalpacion.ObtenerDatosFiltroNew(id_Animal);
        
        boolean tienePalpacion = ListaDatosPalpacion.size() > 0;
        if (tienePalpacion) {
            LlenarTablaPalpacion();
        }
        jTabbedPane1.setEnabledAt(6, tienePalpacion);
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
    public void getDatosHembra() {
        Map<String, String> datosHembra = new HashMap<>();
        Map<String, String> datosHembraPartos = new HashMap<>();
        datosHembra = controlPalpacion.getDatosPalpacion(id_Animal);
        datosHembraPartos = controlPalpacion.getDatosPartos(id_Animal);
        lbltitNovilla.setText("Novilla?");

        lblNovilla.setText(ListaDatos.get(0).getFecha_novilla().equals("1900-01-01") ? "No" : "Si");
        lblNumMeses.setText("" + datosHembraPartos.get("NUM_MESES"));
        lblNumeroCria.setText("" + datosHembraPartos.get("CRIA"));
        lblNumPartos.setText("" + datosHembraPartos.get("NUM_PARTOS"));
        lblFecUltParto.setText("" + datosHembraPartos.get("FECHA_ULT_PARTO"));
        lblEstado.setText("" + datosHembra.get("ESTADO"));
    }

    private void actualizarGraficaPesos() {
        pnlGrafico.removeAll();
        if (listaDatosPeso.size() > 0) {
            graficoPeso = new Panel(listaDatosPeso, pnlGrafico);
            graficoPeso.setBounds(0, 0, pnlGrafico.getWidth(), pnlGrafico.getHeight());
            pnlGrafico.add(graficoPeso);
        }
    }

    private void registroAnterior() {
        filaLista--;
        btnSiguiente.setEnabled(filaLista < ListaAnimalesMostrar.size());
        btnAnterior.setEnabled(filaLista >= 0);
        id_Animal = btnAnterior.isEnabled() ? ListaAnimalesMostrar.get(filaLista).get("ID_ANIMAL") : id_Animal;
        filaLista += btnAnterior.isEnabled() ? 0 : 1;
        GetDatosAnimal();
        refTablaAnimales.setRowSelectionInterval(filaLista, filaLista);
    }

    private void registroSiguiente() {
        filaLista++;
        btnSiguiente.setEnabled(filaLista < ListaAnimalesMostrar.size());
        btnAnterior.setEnabled(filaLista > 0);
        id_Animal = btnSiguiente.isEnabled() ? ListaAnimalesMostrar.get(filaLista).get("ID_ANIMAL") : id_Animal;
        filaLista -= btnSiguiente.isEnabled() ? 0 : 1;
        GetDatosAnimal();
        refTablaAnimales.setRowSelectionInterval(filaLista, filaLista);
    }

    //<editor-fold defaultstate="collapsed" desc="TabbetPane Parto">
    public void GetDatosParto() {
        ListaDatosPartos = controlPalpacion.getDatosParto(ListaDatos.get(0));

        boolean tienePartos = ListaDatosPartos.size() > 0;
        if (tienePartos) {
            LlenarPnlPartos();
        }
        jTabbedPane1.setEnabledAt(7, tienePartos);
    }

    private void LlenarPnlPartos() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        int fila = -1, y = 0, w = 0;
        sizeCrias = ListaDatosPartos.size();

        //<editor-fold defaultstate="collapsed" desc="INIT ACORDEON CRIAS">
        flagsPnlCrias = new int[sizeCrias];
        initFlagsCrias();
        panelesCria = new ArrayList<>();
        pnlContenedorPartos.removeAll();

        //<editor-fold defaultstate="collapsed" desc="ENCABEZADO CRIAS">
        JPanel panelEncabezado = new JPanel();
        JLabel lblEncabezadoCrias = new JLabel();
        panelEncabezado.setBackground(new java.awt.Color(59, 123, 50));
        panelEncabezado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelEncabezado.setLayout(new java.awt.GridBagLayout());

        lblEncabezadoCrias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEncabezadoCrias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEncabezadoCrias.setForeground(new java.awt.Color(255, 255, 255));
        lblEncabezadoCrias.setText("Datos de crias");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        panelEncabezado.add(lblEncabezadoCrias, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        pnlContenedorPartos.add(panelEncabezado, gridBagConstraints);
//</editor-fold>

//</editor-fold>
        for (Map<String, String> datos : ListaDatosPartos) {
            fila++;
//            if(fila == ListaDatosPartos.size()-1){
//                w=1;
//            }
            JPanel panelBtnIrCriaAux = new JPanel();
            JPanel pnlCriaAux = new JPanel();
            JPanel pnlTituloCriaAux = new JPanel();
            JLabel lbltitCria = new JLabel();
            JLabel lblPartoNumCriaAux = new JLabel();
            JLabel lblPesoNacCriaAux = new JLabel();
            JLabel lblNumCriaAux = new JLabel();
            JLabel lblSexoCriaAux = new JLabel();
            JLabel lblFecNacCriaAux = new JLabel();
            JLabel lblGrupoCriaAux = new JLabel();
            JLabel lblNumeroMamaCriaAux = new JLabel();
            JLabel lblNotasCriaAux = new JLabel();
            JCheckBox ch = new JCheckBox();
            JButton btn = new JButton();

            //<editor-fold defaultstate="collapsed" desc="Title Cria">
            pnlTituloCriaAux.setBackground(new java.awt.Color(59, 143, 50));
            pnlTituloCriaAux.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
            pnlTituloCriaAux.setLayout(new java.awt.GridBagLayout());

            lbltitCria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
            lbltitCria.setForeground(new java.awt.Color(255, 255, 255));
            lbltitCria.setText("Cria " + (fila + 1));

            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
            gridBagConstraints.weightx = 1.0;
//            gridBagConstraints.weighty = 0;
            gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
            pnlTituloCriaAux.add(lbltitCria, gridBagConstraints);

            ch.setName("" + fila);
            ch.addMouseListener(new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    AcordeonCria(e);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

            });

            ch.setBackground(new java.awt.Color(59, 143, 50));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            pnlTituloCriaAux.add(ch, gridBagConstraints);

            y++;
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = y;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 1;
            gridBagConstraints.ipady = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
            pnlContenedorPartos.add(pnlTituloCriaAux, gridBagConstraints);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Cuerpo Cria">
            //<editor-fold defaultstate="collapsed" desc="propiedades">
            pnlCriaAux.setBackground(new Color(255, 255, 255));
            pnlCriaAux.setBorder(BorderFactory.createLineBorder(new Color(59, 123, 50)));
            pnlCriaAux.setLayout(new GridBagLayout());

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblPartoNumCria">
            lblPartoNumCriaAux.setText("" + datos.get("NUMPARTO"));
            lblPartoNumCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblPartoNumCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblPartoNumCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblPartoNumCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "# parto ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
            pnlCriaAux.add(lblPartoNumCriaAux, gridBagConstraints);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblPesoNacCria">
            lblPesoNacCriaAux.setText("" + datos.get("PES_NAC"));
            lblPesoNacCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblPesoNacCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblPesoNacCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblPesoNacCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Peso nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblPesoNacCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 15);
            pnlCriaAux.add(lblPesoNacCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblNumCria">
            lblNumCriaAux.setText("" + datos.get("NUMERO"));
            lblNumCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblNumCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblNumCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblNumCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número animal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblNumCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
            pnlCriaAux.add(lblNumCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblSexoCria">
            lblSexoCriaAux.setText(datos.get("SEXO"));
            lblSexoCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblSexoCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblSexoCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblSexoCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblSexoCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
            pnlCriaAux.add(lblSexoCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblFecNacCria">
            lblFecNacCriaAux.setText(datos.get("FEC_NAC"));
            lblFecNacCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblFecNacCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblFecNacCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblFecNacCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 2;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 15);
            pnlCriaAux.add(lblFecNacCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblGrupoCria">
            lblGrupoCriaAux.setText(datos.get("GRUPO"));
            lblGrupoCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblGrupoCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblGrupoCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblGrupoCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblGrupoCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
            pnlCriaAux.add(lblGrupoCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblNumeroMamaCria">
            lblNumeroMamaCriaAux.setText("" + ListaDatos.get(0).getNumero());
            lblNumeroMamaCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblNumeroMamaCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblNumeroMamaCriaAux.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lblNumeroMamaCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numero Mamá", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblNumeroMamaCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 100;
            gridBagConstraints.ipady = 10;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.weightx = 0.3333333333333333;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
            pnlCriaAux.add(lblNumeroMamaCriaAux, gridBagConstraints);
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="lblNotasCria">
            lblNotasCriaAux.setText(datos.get("NOTAS"));
            lblNotasCriaAux.setBackground(new java.awt.Color(255, 255, 255));
            lblNotasCriaAux.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            lblNotasCriaAux.setForeground(new java.awt.Color(59, 123, 50));
            lblNotasCriaAux.setVerticalAlignment(javax.swing.SwingConstants.TOP);
            lblNotasCriaAux.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
            lblNotasCriaAux.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridheight = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 15);
            pnlCriaAux.add(lblNotasCriaAux, gridBagConstraints);

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Boton Ir Hoja Animal">
            panelBtnIrCriaAux.setBackground(new java.awt.Color(59, 123, 50));
            panelBtnIrCriaAux.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
            panelBtnIrCriaAux.setLayout(new java.awt.GridBagLayout());

            btn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
            btn.setForeground(new java.awt.Color(255, 255, 255));
            btn.setText("Ir HDV");
            btn.setToolTipText("Ver Hoja de Vida Animal");
            btn.setName(datos.get("IDANIMAL"));
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            btn.setFocusPainted(false);
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    //btnIrCriaMouseEntered(evt);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    //btnIrCriaMouseExited(evt);
                }
            });
            btn.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    IrVistaHojaAnimal(evt);
                }
            });
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            panelBtnIrCriaAux.add(btn, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.insets = new java.awt.Insets(10, 15, 10, 0);
            pnlCriaAux.add(panelBtnIrCriaAux, gridBagConstraints);
//</editor-fold>

            y++;
            System.out.println("********************w------" + w);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = y;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 0;
            gridBagConstraints.ipady = 0;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 0;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
            gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);

            pnlContenedorPartos.add(pnlCriaAux, gridBagConstraints);
            //</editor-fold>

            panelesCria.add(pnlCriaAux);
        }

        JPanel panelEnd = new JPanel();
        JLabel lblEnd = new JLabel();
        panelEnd.setBackground(new java.awt.Color(255, 255, 255));
        panelEnd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panelEnd.setLayout(new java.awt.GridBagLayout());

        lblEnd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEnd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnd.setForeground(new java.awt.Color(255, 255, 255));
        lblEnd.setText("D");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        panelEnd.add(lblEnd, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = y++;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        pnlContenedorPartos.add(panelEnd, gridBagConstraints);

//        hidePanelsCrias();
    }

    private void IrVistaHojaAnimal(ActionEvent e) {
        String idAnimal = ((JButton) e.getSource()).getName();

        for (int i = 0; i < ListaAnimalesMostrar.size(); i++) {
            if (ListaAnimalesMostrar.get(i).get("ID_ANIMAL").equals(idAnimal)) {
                filaLista = i;
                id_Animal = idAnimal;
                GetDatosAnimal();
                refTablaAnimales.setRowSelectionInterval(filaLista, filaLista);
                break;
            }
        }
    }

    private void AcordeonCria(MouseEvent e) {
        JCheckBox check = ((JCheckBox) e.getSource());
        check.setSelected(!check.isSelected());
        String numPanel = check.getName();
        togglePanelCrias(Integer.parseInt(numPanel));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="METODOS PARA EL ACORDEON">
    private void initFlags() {
        for (int flag : flags) {
            flag = 0;
        }
    }

    private void togglePanel(int panelNumber) {
        if (flags[panelNumber] == 0) {
            flags[panelNumber] = 1;
            panels[panelNumber].setVisible(true);

            for (int i = 0; i < panels.length; i++) {
                if (i != panelNumber) {
                    panels[i].setVisible(false);
                    flags[i] = 0;
                }
            }
        }
    }

    private void hidePanels() {
        for (JPanel panel : panels) {
            panel.setVisible(false);
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="METODOS PARA EL ACORDEON CRIAS">
    private void initFlagsCrias() {
        for (int flag : flagsPnlCrias) {
            flag = 0;
        }
    }

    private void togglePanelCrias(int panelNumber) {
        if (flagsPnlCrias[panelNumber] == 0) {
            flagsPnlCrias[panelNumber] = 1;
            panelesCria.get(panelNumber).setVisible(true);

            for (int i = 0; i < panelesCria.size(); i++) {
                if (i != panelNumber) {
                    panelesCria.get(i).setVisible(false);
                    flagsPnlCrias[i] = 0;
                }
            }
        } else {
            flagsPnlCrias[panelNumber] = 0;
        }
    }

    private void hidePanelsCrias() {
        for (JPanel panel : panelesCria) {
            panel.setVisible(false);
        }
    }
//</editor-fold>

}
