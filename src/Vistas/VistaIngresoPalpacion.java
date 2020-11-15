/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.*;
import Modelo.*;
import static Utilidades.Consultas.consultas;
import Utilidades.Estado;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import static Vistas.VistaIngresoPesaje.guardado;
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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaIngresoPalpacion extends javax.swing.JPanel {

    private int consecutivo;
    private int filaAModificar = -1;
    private String idAnimal;
    private String fechaA;
    private final String[] encabezados = {
        "No",
        "Medicamento",
        "Cantidad",
        "Modificar",
        "Eliminar"
    };
    private List<Map<String, String>> medicamentos;
    private List<Map<String, String>> select;
    private ControlGeneral controlGral;
    private ArrayList<Object[]> listaMedicamentosO;
    private ArrayList<Map<String, String>> listaMedicamentos;
    private DefaultTableModel dtm;
    private ModeloPalpacion modelo;
    private ModeloMedicamentosPorPesaje modelompp;
    private ControlPalpacion control;
    private ControlGrupos controlGrupo;
    private ControlTraslado controlTraslado;
    private ControlPesaje controlPesaje;
    private ModeloVentanaGeneral modeloVistaGeneral;
    private Map<String, String> datos;
    public static int guardado = -1;
    public int guardar = 0;
    public String pesoAnterior = "";
    private VistaPalpacion vp;
    public DefaultListModel modlistDiagnostico = new DefaultListModel();

    /**
     * Creates new form VistaIngresoPesaje
     */
    public VistaIngresoPalpacion() {
        initComponents();
        setSize(634, 600);
        consecutivo = 0;
        listaMedicamentos = new ArrayList<>();
        btnModificar.setVisible(false);
        modelo = new ModeloPalpacion();
        modelompp = new ModeloMedicamentosPorPesaje();
        control = new ControlPalpacion();
    }

    public VistaIngresoPalpacion(ModeloVentanaGeneral modeloVistaGeneral) {
        initComponents();
        setSize(634, 600);
        btnModificar.setVisible(false);
        consecutivo = 0;
        guardado = -1;
        controlGral = new ControlGeneral();
        cargarComboMedicamento();
        getIdPalpacion();
        this.modeloVistaGeneral = modeloVistaGeneral;
        datos = new HashMap<>();
        datos = (Map<String, String>) modeloVistaGeneral.getModeloDatos();
        idAnimal = datos.get("IDANIMAL");

        txtReferenciaAnimal.setText("<html><p>Número Mamá: <b>" + datos.get("NUMERO_MAMA") + "</b></p><p>Animal número: <b>" + datos.get("NUMERO_ANIMAL") + "</b></p></html>");
        pesoAnterior = datos.get("PESO");
        listaMedicamentos = new ArrayList<>();
        this.vp = ((VistaPalpacion) modeloVistaGeneral.getPanelPadre());
        fechaA = vp.fechaAnterior;
        dtm = new DefaultTableModel(encabezados, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        tablaMedicamentos.setModel(dtm);
        lblPeso.setVisible(false);
        lblrazon.setVisible(false);
        lblPesokg.setVisible(false);
        txtRazon.setVisible(false);
        txtPeso.setVisible(false);
        txtPesoKg.setVisible(false);
        jspeso.setVisible(false);
        jspesokg.setVisible(false);
        jsrazon.setVisible(false);
        modelo = new ModeloPalpacion();
        modelompp = new ModeloMedicamentosPorPesaje();
        control = new ControlPalpacion();
        txtNumMeses.setEnabled(false);
        btnAnularPalpacion.setEnabled(false);

        if (!datos.get("IDPALPACION").equals("")) {
            btnAnularPalpacion.setEnabled(true);
            guardar = 1;
            LlenarFormulario();
        }
        IniciarFecha();
    }

    private void cargarComboMedicamento() {
        String consulta = consultas.get("CARGAR_COMBO_MEDICAMENTOS");
        medicamentos = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbMedicamentos, medicamentos, "descripcion");
    }

    private void getIdPalpacion() {
        String consulta = consultas.get("GET_MAXIMO_ID_PALPACION");
        select = controlGral.GetComboBox(consulta);
        for (Map<String, String> lista : select) {
            txtCodigo.setText(lista.get("IDPALPACION"));
        }
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

        txtCodigo = new javax.swing.JLabel();
        txtCodigoMedicamento = new javax.swing.JLabel();
        lbltitle14 = new javax.swing.JLabel();
        lbltitle18 = new javax.swing.JLabel();
        txtNumMeses = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        chkDescarte = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        lbltitle10 = new javax.swing.JLabel();
        cbMedicamentos = new javax.swing.JComboBox();
        lbltitle16 = new javax.swing.JLabel();
        txtCantidadMedicamento = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMedicamentos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        txtReferenciaAnimal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAnularPalpacion = new javax.swing.JButton();
        jdFechaPalpacion = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstDiagnostico = new javax.swing.JList();
        lbltitle20 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        txtPesoKg = new javax.swing.JTextField();
        jspesokg = new javax.swing.JSeparator();
        jsrazon = new javax.swing.JSeparator();
        lblPeso = new javax.swing.JLabel();
        lblPesokg = new javax.swing.JLabel();
        lblrazon = new javax.swing.JLabel();
        txtRazon = new javax.swing.JTextField();
        jspeso = new javax.swing.JSeparator();
        txtPesoActual = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lbltitle14, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Diagnostico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle18, gridBagConstraints);

        txtNumMeses.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumMeses.setForeground(new java.awt.Color(59, 123, 50));
        txtNumMeses.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumMeses.setBorder(null);
        txtNumMeses.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumMeses.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumMeses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumMesesKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtNumMeses, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator11, gridBagConstraints);

        chkDescarte.setBackground(new java.awt.Color(255, 255, 255));
        chkDescarte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescarte.setForeground(new java.awt.Color(59, 123, 50));
        chkDescarte.setText("Descarte");
        chkDescarte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDescarteStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkDescarte, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), " Medicamentos aplicados ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle10.setText("Medicamento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.00475;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(lbltitle10, gridBagConstraints);

        cbMedicamentos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbMedicamentos.setForeground(new java.awt.Color(59, 123, 50));
        cbMedicamentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMedicamentosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.00475;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(cbMedicamentos, gridBagConstraints);

        lbltitle16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle16.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle16.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.00475;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        jPanel1.add(lbltitle16, gridBagConstraints);

        txtCantidadMedicamento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidadMedicamento.setForeground(new java.awt.Color(59, 123, 50));
        txtCantidadMedicamento.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCantidadMedicamento.setBorder(null);
        txtCantidadMedicamento.setCaretColor(new java.awt.Color(59, 123, 50));
        txtCantidadMedicamento.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtCantidadMedicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadMedicamentoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.00475;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(txtCantidadMedicamento, gridBagConstraints);

        jSeparator12.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.00475;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(jSeparator12, gridBagConstraints);

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        jPanel1.add(btnAgregar, gridBagConstraints);

        tablaMedicamentos.setForeground(new java.awt.Color(59, 123, 50));
        tablaMedicamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaMedicamentos.setFocusTraversalPolicyProvider(true);
        tablaMedicamentos.setGridColor(new java.awt.Color(59, 123, 50));
        tablaMedicamentos.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tablaMedicamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaMedicamentosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMedicamentos);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30.png"))); // NOI18N
        btnModificar.setToolTipText("Agregar");
        btnModificar.setBorderPainted(false);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnModificar.setName("btnAgregar"); // NOI18N
        btnModificar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30_over.png"))); // NOI18N
        btnModificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar30_over.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 5.0E-4;
        jPanel1.add(btnModificar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jPanel1, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setRows(5);
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jScrollPane2, gridBagConstraints);

        txtReferenciaAnimal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtReferenciaAnimal.setForeground(new java.awt.Color(59, 123, 50));
        txtReferenciaAnimal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        add(txtReferenciaAnimal, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar.png"))); // NOI18N
        btnGuardar1.setToolTipText("Guardar");
        btnGuardar1.setBorderPainted(false);
        btnGuardar1.setContentAreaFilled(false);
        btnGuardar1.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnGuardar1.setName("btnGuardar"); // NOI18N
        btnGuardar1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnGuardar1, gridBagConstraints);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("Eliminar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cancelar_over.png"))); // NOI18N
        btnCancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cancelar_over.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnCancelar, gridBagConstraints);

        btnAnularPalpacion.setBackground(new java.awt.Color(255, 255, 255));
        btnAnularPalpacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anularPalpacion.png"))); // NOI18N
        btnAnularPalpacion.setToolTipText("");
        btnAnularPalpacion.setContentAreaFilled(false);
        btnAnularPalpacion.setMaximumSize(new java.awt.Dimension(87, 73));
        btnAnularPalpacion.setMinimumSize(new java.awt.Dimension(87, 73));
        btnAnularPalpacion.setOpaque(false);
        btnAnularPalpacion.setPreferredSize(new java.awt.Dimension(87, 73));
        btnAnularPalpacion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anularPalpacion_over.png"))); // NOI18N
        btnAnularPalpacion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/anularPalpacion_over.png"))); // NOI18N
        btnAnularPalpacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularPalpacionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnAnularPalpacion, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaPalpacion, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle19, gridBagConstraints);

        lstDiagnostico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lstDiagnostico.setForeground(new java.awt.Color(59, 123, 50));
        lstDiagnostico.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Preñada", "Repaso", "Vacia" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstDiagnostico.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstDiagnostico.setSelectionBackground(new java.awt.Color(59, 123, 50));
        lstDiagnostico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstDiagnosticoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(lstDiagnostico);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jScrollPane3, gridBagConstraints);

        lbltitle20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle20.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle20.setText("# meses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle20, gridBagConstraints);

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(59, 123, 50));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPeso.setBorder(null);
        txtPeso.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPeso.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPeso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoFocusLost(evt);
            }
        });
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtPeso, gridBagConstraints);

        txtPesoKg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoKg.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoKg.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoKg.setBorder(null);
        txtPesoKg.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoKgFocusLost(evt);
            }
        });
        txtPesoKg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKgKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPesoKg, gridBagConstraints);

        jspesokg.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jspesokg, gridBagConstraints);

        jsrazon.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jsrazon, gridBagConstraints);

        lblPeso.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPeso.setForeground(new java.awt.Color(59, 123, 50));
        lblPeso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPeso.setText("Peso (Libras)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lblPeso, gridBagConstraints);

        lblPesokg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesokg.setForeground(new java.awt.Color(59, 123, 50));
        lblPesokg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesokg.setText("Peso (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lblPesokg, gridBagConstraints);

        lblrazon.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblrazon.setForeground(new java.awt.Color(59, 123, 50));
        lblrazon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblrazon.setText("Razon Descarte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lblrazon, gridBagConstraints);

        txtRazon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRazon.setForeground(new java.awt.Color(59, 123, 50));
        txtRazon.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtRazon.setBorder(null);
        txtRazon.setCaretColor(new java.awt.Color(59, 123, 50));
        txtRazon.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtRazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRazonKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtRazon, gridBagConstraints);

        jspeso.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jspeso, gridBagConstraints);

        txtPesoActual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPesoActual.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPesoActual.setToolTipText("Peso anterior (Kilogramos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtPesoActual, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    private void txtNumMesesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumMesesKeyReleased
        String peso = txtNumMeses.getText();
        String pesoSinPuntos = peso.indexOf(".") > -1 ? peso.replace(".", "") : peso;
        String pesoFormateado = Expresiones.procesarSoloNum(pesoSinPuntos);
        txtNumMeses.setText(pesoFormateado);

    }//GEN-LAST:event_txtNumMesesKeyReleased


    private void cbMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMedicamentosActionPerformed
        int indice = cbMedicamentos.getSelectedIndex();
        if (indice > 0) {
            String idMedicamento = medicamentos.get(indice).get("id");
            txtCodigoMedicamento.setText(idMedicamento);
        }
    }//GEN-LAST:event_cbMedicamentosActionPerformed

    private void tablaMedicamentosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMedicamentosMouseReleased
        int fila = tablaMedicamentos.getSelectedRow();
        int cola = tablaMedicamentos.getSelectedColumn();
        String infoCelda = tablaMedicamentos.getValueAt(fila, cola).toString();

        if (infoCelda.equalsIgnoreCase("eliminar")) {
            if (filaAModificar != -1) {
                JOptionPane.showMessageDialog(this, "Se encuentra modificando no es posible eliminar el registro.");
                return;
            }
            int opcion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de elimar el medicamento?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                if (!listaMedicamentos.get(fila).get("IDPALPMEDICAMENTO").equals("0")) {
                    int rep = control.deletePalpacionMedicamento(listaMedicamentos.get(fila).get("IDPALPMEDICAMENTO"));
                    JOptionPane.showMessageDialog(this, "El registro se elimino exitosamente.");
                }
                listaMedicamentos.remove(fila);
                reorganizarMedicamentos();
            }
        } else if (infoCelda.equalsIgnoreCase("modificar")) {
            filaAModificar = fila;
            cambiarMedicamento();
        }
    }//GEN-LAST:event_tablaMedicamentosMouseReleased

    private void txtCantidadMedicamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadMedicamentoKeyReleased
        Utilidades.formatearNumeros(txtCantidadMedicamento);
    }//GEN-LAST:event_txtCantidadMedicamentoKeyReleased

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarMedicamentos();
        txtCantidadMedicamento.setText("");
        cbMedicamentos.setSelectedIndex(0);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        guardarNuevoMedicamento();
        txtCantidadMedicamento.setText("");
        cbMedicamentos.setSelectedIndex(0);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vp.MostrarTabla();
        vp.band = 0;
        ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void lstDiagnosticoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDiagnosticoMouseReleased
        txtNumMeses.setEnabled(lstDiagnostico.getSelectedValue().equals("Preñada"));
        if (lstDiagnostico.getSelectedValue().equals("Preñada")) {
            txtNumMeses.requestFocus();
        }
    }//GEN-LAST:event_lstDiagnosticoMouseReleased

    private void txtPesoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKeyReleased
        String peso = txtPeso.getText();
        String pesoSinPuntos = peso.indexOf(".") > -1 ? peso.replace(".", "") : peso;
        String pesoFormateado = Expresiones.procesarSoloNumP(pesoSinPuntos);
        pesoFormateado = Utilidades.MascaraMonedaConDecimales(pesoFormateado);
        txtPeso.setText(pesoFormateado);

        int indice = pesoSinPuntos.lastIndexOf(",");
        int indiceFinal = pesoSinPuntos.length() - 1;
        pesoSinPuntos = pesoSinPuntos.replace(",", ".");
        boolean convertir = indice == indiceFinal;

        if (!convertir) {
            double pesoEnLibras = Double.parseDouble(pesoSinPuntos);
            txtPesoKg.setText(convertirAKilogramos(pesoEnLibras));
        }
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtPesoKgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKgKeyReleased
        calcularPesoEnLibras();
    }//GEN-LAST:event_txtPesoKgKeyReleased

    private void txtRazonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRazonKeyReleased

    }//GEN-LAST:event_txtRazonKeyReleased

    private void chkDescarteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkDescarteStateChanged
        lblPeso.setVisible(chkDescarte.isSelected());
        lblrazon.setVisible(chkDescarte.isSelected());
        lblPesokg.setVisible(chkDescarte.isSelected());
        txtRazon.setVisible(chkDescarte.isSelected());
        txtPeso.setVisible(chkDescarte.isSelected());
        txtPesoKg.setVisible(chkDescarte.isSelected());
        jspeso.setVisible(chkDescarte.isSelected());
        jspesokg.setVisible(chkDescarte.isSelected());
        jsrazon.setVisible(chkDescarte.isSelected());
    }//GEN-LAST:event_chkDescarteStateChanged

    private void txtPesoKgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoKgFocusLost
        calcularDiferenciaPesos();
    }//GEN-LAST:event_txtPesoKgFocusLost

    private void txtPesoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoFocusLost
        calcularDiferenciaPesos();
    }//GEN-LAST:event_txtPesoFocusLost

    private void btnAnularPalpacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularPalpacionActionPerformed
        Anular();
    }//GEN-LAST:event_btnAnularPalpacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnularPalpacion;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbMedicamentos;
    private javax.swing.JCheckBox chkDescarte;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private com.toedter.calendar.JDateChooser jdFechaPalpacion;
    private javax.swing.JSeparator jspeso;
    private javax.swing.JSeparator jspesokg;
    private javax.swing.JSeparator jsrazon;
    private javax.swing.JLabel lblPeso;
    private javax.swing.JLabel lblPesokg;
    private javax.swing.JLabel lblrazon;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle14;
    private javax.swing.JLabel lbltitle16;
    private javax.swing.JLabel lbltitle18;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JLabel lbltitle20;
    private javax.swing.JList lstDiagnostico;
    private javax.swing.JTable tablaMedicamentos;
    public javax.swing.JTextField txtCantidadMedicamento;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JLabel txtCodigoMedicamento;
    private javax.swing.JTextArea txtNotas;
    public javax.swing.JTextField txtNumMeses;
    public javax.swing.JTextField txtPeso;
    private javax.swing.JLabel txtPesoActual;
    public javax.swing.JTextField txtPesoKg;
    public javax.swing.JTextField txtRazon;
    private javax.swing.JLabel txtReferenciaAnimal;
    // End of variables declaration//GEN-END:variables

    private void AgregarMedicamentos() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (cbMedicamentos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un medicamento.");
            cbMedicamentos.requestFocusInWindow();
            return;
        }
        if (txtCantidadMedicamento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad.");
            txtCantidadMedicamento.requestFocusInWindow();
            return;
        }
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            if (txtCodigoMedicamento.getText().equals(listaMedicamentos.get(i).get("ID"))) {
                JOptionPane.showMessageDialog(this, "El medicamento ya se encuentra en la lista.");
                cbMedicamentos.setSelectedIndex(0);
                cbMedicamentos.requestFocusInWindow();
                return;
            }
        }
//</editor-fold>

        dtm = (DefaultTableModel) tablaMedicamentos.getModel();
        dtm.addRow(getFila());
        tablaMedicamentos.setModel(dtm);

// a.id_medicamento AS ID, b.descripcion AS DESCRIPCION,a.dosis AS CANTIDAD, b.unidad_medida AS UNIDAD_MEDIDA, a.`id` AS IDPALPMEDICAMENTO
        Map<String, String> mp = new HashMap<String, String>();
        mp.put("ID", txtCodigoMedicamento.getText());
        mp.put("DESCRIPCION", cbMedicamentos.getSelectedItem().toString());
        mp.put("CANTIDAD", txtCantidadMedicamento.getText());
        mp.put("IDPALPMEDICAMENTO", "0");
        mp.put("UPDATE", "0");
        listaMedicamentos.add(mp);

    }

    private Object[] getFila() {
        return new Object[]{
            ++consecutivo,
            cbMedicamentos.getSelectedItem().toString(),//descripcionMedicamento
            txtCantidadMedicamento.getText(),//CantidadMedicamento
            "Modificar",
            "Eliminar"
        };

    }

    private void reorganizarMedicamentos() {
        consecutivo = 0;
        dtm = (DefaultTableModel) tablaMedicamentos.getModel();
        Utilidades.LimpiarTabla(tablaMedicamentos);
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            dtm.addRow(new Object[]{
                ++consecutivo,
                listaMedicamentos.get(i).get("DESCRIPCION"),
                listaMedicamentos.get(i).get("CANTIDAD"),
                "Modificar",
                "Eliminar"
            });
        }
        tablaMedicamentos.setModel(dtm);
    }

    private void cambiarMedicamento() {
        btnModificar.setVisible(true);
        btnAgregar.setVisible(false);
        String medicamento = tablaMedicamentos.getValueAt(filaAModificar, 1).toString();
        String cantidad = tablaMedicamentos.getValueAt(filaAModificar, 2).toString();
        cbMedicamentos.setSelectedItem(medicamento);
        txtCantidadMedicamento.setText(cantidad);
    }

    private void guardarNuevoMedicamento() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (cbMedicamentos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un medicamento.");
            cbMedicamentos.requestFocusInWindow();
            return;
        }
        if (txtCantidadMedicamento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cantidad.");
            txtCantidadMedicamento.requestFocusInWindow();
            return;
        }
        for (int i = 0; i < listaMedicamentos.size(); i++) {
            if (filaAModificar != i) {
                if (txtCodigoMedicamento.getText().equals(listaMedicamentos.get(i).get("ID"))) {
                    JOptionPane.showMessageDialog(this, "El medicamento ya se encuentra en la lista.");
                    cbMedicamentos.setSelectedIndex(0);
                    cbMedicamentos.requestFocusInWindow();
                    return;
                }
            }
        }
//</editor-fold>

        listaMedicamentos.get(filaAModificar).put("ID", txtCodigoMedicamento.getText());
        listaMedicamentos.get(filaAModificar).put("DESCRIPCION", cbMedicamentos.getSelectedItem().toString());
        listaMedicamentos.get(filaAModificar).put("CANTIDAD", txtCantidadMedicamento.getText());
        listaMedicamentos.get(filaAModificar).put("UPDATE", "1");
        reorganizarMedicamentos();
        btnAgregar.setVisible(true);
        btnModificar.setVisible(false);
        filaAModificar = -1;
    }

    private void Guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfes = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fecha = jdFechaPalpacion.getCalendar();
        String idgrupoDescarte = "";

        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (chkDescarte.isSelected()) { // BUSCAR IDGRUPO DESCARTE
            controlGrupo = new ControlGrupos();
            ArrayList<ModeloGrupos> dat = (ArrayList<ModeloGrupos>) controlGrupo.ObtenerIdGrupoxDescripcion(datos.get("IDFINCA"), datos.get("IDTIPOA"), "DESCARTE");
            if (dat.size() > 0) {
                idgrupoDescarte = dat.get(0).getId();
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el grupo DESCARTE en la finca Seleccionada. \nPor favor crear el grupo para poder realizar la operación.");
                return;
            }

            if (txtRazon.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Se debe ingresar la razon por la cual se descarta al animal, para continuar la operacion.");
                return;
            }

        }
        if (listaMedicamentos.size() == 0) {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de guardar el pesaje sin agregar los medicamentos?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                return;
            }
        }
        if (lstDiagnostico.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el diagnostico del animal, para continuar la operacion.");
            return;
        }
        //</editor-fold>
        String estado = "";
        String fechaEstadoActivo = "";
        if (!datos.get("ESTPALP").equals("Activo")) {
            fechaEstadoActivo = control.getFechaPalpActiva(idAnimal);
        } else {
            fechaEstadoActivo = datos.get("FECHA_PALP");
        }
        estado = getEstadoGuardar(sdfes.format(fecha.getTime()), fechaEstadoActivo);

        modelo.setId(txtCodigo.getText());
        modelo.setId_animal(idAnimal);
        modelo.setNotas(Utilidades.CodificarElemento(txtNotas.getText().trim()));
        modelo.setDiagnostico("" + lstDiagnostico.getSelectedValue().toString());
        modelo.setFecha_palpacion(sdf.format(fecha.getTime()));
        modelo.setNum_meses((txtNumMeses.getText().equals("") ? "0" : txtNumMeses.getText()));
        modelo.setFecha_ultimo_parto("");
        modelo.setDescarte(chkDescarte.isSelected() ? "1" : "0");
        modelo.setRazondescarte(chkDescarte.isSelected() ? Utilidades.CodificarElemento(txtRazon.getText()) : "");
        modelo.setEstado(estado);
        modelo.setFecha("NOW()");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            modelompp = new ModeloMedicamentosPorPesaje();
            modelompp.setId(listaMedicamentos.get(i).get("IDPALPMEDICAMENTO"));
            modelompp.setId_medicamento(listaMedicamentos.get(i).get("ID"));
            modelompp.setDosis(listaMedicamentos.get(i).get("CANTIDAD").toString().replace(".", "").replace(",", "."));
            modelompp.setEstadoG(listaMedicamentos.get(i).get("IDPALPMEDICAMENTO").equals("0") ? "0" : (listaMedicamentos.get(i).get("UPDATE").equals("1") ? "1" : "-1"));
            modelo.addMedicamentos(modelompp);
        }

        int retorno = -1;

        if (guardar == 0) {
            if (estado.equals("Activo")) {
                int r = control.InactivarEstadoAnterior(idAnimal);
            }
            retorno = control.Guardar(modelo);
        } else {
            retorno = control.Actualizar(modelo);
        }
//        //<editor-fold defaultstate="collapsed" desc="DESCARTE">
//        if(chkDescarte.isSelected() && !idgrupoDescarte.equals("")){//REALIZAR TRASLADO
//                controlTraslado = new ControlTraslado();
//                ModeloTraslado modeloT = new ModeloTraslado();
//                modeloT.setIdAnimal(idAnimal);
//                modeloT.setIdFinca(datos.get("IDFINCA"));
//                modeloT.setIdGrupo(idgrupoDescarte);
//                modeloT.setEstado("Activo");
//                modeloT.setMotivo(""+txtRazon.getText());
//                modeloT.setLote(""+datos.get("IDTIPOA"));
//                modeloT.setFechaTraslado(sdf.format(fecha.getTime()));
//                modeloT.setFecha("NOW()");
//                modeloT.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
//                
//                int ret = controlTraslado.ActulizarAnimal(modeloT, false);
//                ret = controlTraslado.InactivarTraslado(modeloT);
//                if (ret == 0) {
//                    ret = controlTraslado.Guardar(modeloT);
//                }
//            
//            if(!txtPesoKg.getText().equals("")){ // REALIZAR PESAJE
//                controlPesaje = new ControlPesaje();
//                ModeloPesaje modeloP = new ModeloPesaje();
//                modeloP.setDescornado("0");
//                modeloP.setDestete("0");
//                modeloP.setFecha("NOW()");
//                modeloP.setFecha_pesado(sdf.format(fecha.getTime()));
//                modeloP.setHierro("0");
//                modeloP.setId_animal(idAnimal);
//                modeloP.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
//                modeloP.setImplante("0");
//                modeloP.setNotas(""+txtRazon.getText());
//                modeloP.setPeso(""+txtPesoKg.getText());
//                modeloP.setPeso_anterior(pesoAnterior);
//                
//                ret = controlPesaje.GuardarPesajeDescarte(modeloP);
//                
//            }
//        }
////</editor-fold>

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro " + (guardar == 0 ? "guardado" : "actualizado") + " satisfactoriamente.";
                guardado = 0;
                break;
            case Retorno.ERROR:
                mensaje = "El registro no pudo ser guardado.";
                break;
            case Retorno.EXCEPCION_SQL:
                mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                break;
            case Retorno.CLASE_NO_ENCONTRADA:
                mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                break;
        }

        JOptionPane.showMessageDialog(this, mensaje);
        if (retorno == Retorno.EXITO) {
            EstablecerRegistroPalpado(idAnimal);
            vp.band = 0;
            ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
        }
    }

    public void IniciarFecha() {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(fechaA);
            jdFechaPalpacion.setDate(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(VistaIngresoPalpacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EstablecerRegistroPalpado(String idAnimal) {
        System.out.println("id-->" + idAnimal);
        for (int i = 0; i < vp.ListaAnimales.size(); i++) {
            String id = vp.ListaAnimales.get(i).get("IDANIMAL");
            if (id.equals(idAnimal)) {
                vp.ListaAnimales.get(i).put("EST", "*");
                vp.ListaAnimales.get(i).put("NUMERO_MESES", "" + modelo.getNum_meses());
                vp.ListaAnimales.get(i).put("ESTADO", "" + modelo.getDiagnostico());
                vp.ListaAnimales.get(i).put("NOTAS", "" + modelo.getNotas());
                vp.fechaAnterior = modelo.getFecha_palpacion();
                vp.EventoComboFincas();
                vp.CargarListadoFechas();
                return;
            }
        }
    }

    private void calcularPesoEnLibras() {
        String peso = txtPesoKg.getText();
        String pesoSinPuntos = peso.indexOf(".") > -1 ? peso.replace(".", "") : peso;
        String pesoFormateado = Expresiones.procesarSoloNumP(pesoSinPuntos);
        pesoFormateado = Utilidades.MascaraMonedaConDecimales(pesoFormateado);
        txtPesoKg.setText(pesoFormateado);

        int indice = pesoSinPuntos.lastIndexOf(",");
        int indiceFinal = pesoSinPuntos.length() - 1;
        pesoSinPuntos = pesoSinPuntos.replace(",", ".");
        boolean convertir = indice == indiceFinal;

        if (!convertir) {
            double pesoEnKilogramos = Double.parseDouble(pesoSinPuntos);
            txtPeso.setText(convertirALibras(pesoEnKilogramos));
            Utilidades.formatearNumeros(txtPeso);
        }
    }

    private String convertirALibras(double pesoEnKilos) {
        Double resultado = pesoEnKilos * Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        return "" + resultadoRedondeado;
    }

    private String convertirAKilogramos(double pesoEnLibras) {
        Double resultado = pesoEnLibras / Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
//        txtCodigo.setText("" + resultadoRedondeado);

        return "" + resultadoRedondeado;
    }

    private void calcularDiferenciaPesos() {
        if (txtPesoKg.getText().length() == 0) {
            txtPesoActual.setText(datos.get("PESO") + " Kg");
        } else {
            int pesoAnterior = Integer.parseInt(datos.get("PESO"));
            int pesoActual = Integer.parseInt(txtPesoKg.getText());
            int diferencia = pesoActual - pesoAnterior;
            txtPesoActual.setText(pesoActual + " Kg - " + pesoAnterior + " Kg = " + diferencia + " Kg");
        }
    }

    private void LlenarFormulario() {
        lstDiagnostico.setSelectedValue(datos.get("ESTADO"), true);
        if (datos.get("ESTADO").equals("Preñada")) {
            txtNumMeses.setText("" + datos.get("NUMERO_MESES"));
            txtNumMeses.setEnabled(true);
        }
        txtCodigo.setText("" + datos.get("IDPALPACION"));
        fechaA = datos.get("FECHA_PALP");
        txtNotas.setText(Utilidades.decodificarElemento(datos.get("NOTAS")));
        getMedicamentos(datos.get("IDPALPACION"));

    }

    private void getMedicamentos(String id) {
        String consulta = consultas.get("GET_MEDICAMENTOS_POR_PALPACION") + id;
        select = controlGral.GetComboBox(consulta);
        dtm = (DefaultTableModel) tablaMedicamentos.getModel();

// a.id_medicamento AS ID, b.descripcion AS DESCRIPCION,a.dosis AS CANTIDAD, b.unidad_medida AS UNIDAD_MEDIDA, a.`id` AS IDPALPMEDICAMENTO
        for (Map<String, String> lista : select) {
            Object[] fila = new Object[]{
                ++consecutivo,//idMedicamento
                lista.get("DESCRIPCION"),//descripcionMedicamento
                lista.get("CANTIDAD"),//CantidadMedicamento
                "Modificar",
                "Eliminar"
            };
            dtm.addRow(fila);
            tablaMedicamentos.setModel(dtm);

            // a.id_medicamento AS ID, b.descripcion AS DESCRIPCION,a.dosis AS CANTIDAD, b.unidad_medida AS UNIDAD_MEDIDA, a.`id` AS IDPALPMEDICAMENTO
            listaMedicamentos.add(lista);
        }
    }

    private String getEstadoGuardar(String fechaForm, String fechaEstadoActivo) {
        String estado = "Activo";
        System.out.println("fechaForm--->" + fechaForm);
        System.out.println("fechaEstadoActivo--->" + fechaEstadoActivo);
        System.out.println("");
        if (!fechaEstadoActivo.equals("")) {
            int dif = Utilidades.CompararFechas(fechaForm, fechaEstadoActivo);
            System.out.println("dif-->" + dif);
            estado = (dif > 0 ? "Activo" : "Inactivo");
        }
        System.out.println("estado--->" + estado);
        return estado;
    }

    private void Anular() {
        modelo.setId(txtCodigo.getText());
        modelo.setId_animal(idAnimal);
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            modelompp = new ModeloMedicamentosPorPesaje();
            modelompp.setId(listaMedicamentos.get(i).get("IDPALPMEDICAMENTO"));
            modelompp.setId_medicamento(listaMedicamentos.get(i).get("ID"));
            modelompp.setDosis(listaMedicamentos.get(i).get("CANTIDAD").toString().replace(".", "").replace(",", "."));
            modelompp.setEstadoG(listaMedicamentos.get(i).get("IDPALPMEDICAMENTO").equals("0") ? "0" : (listaMedicamentos.get(i).get("UPDATE").equals("1") ? "1" : "-1"));
            modelo.addMedicamentos(modelompp);
        }

        int resp = JOptionPane.showConfirmDialog(this, "¿Esta seguro de anular este registro?", "Anular Pesaje", JOptionPane.YES_NO_OPTION);

        if (resp == JOptionPane.YES_OPTION) {
            int retorno = control.AnularPalpacion(modelo);
            String mensaje = "";
            switch (retorno) {
                case Retorno.EXITO:
                    mensaje = "Registro anulado satisfactoriamente.";
                    break;
                case Retorno.ERROR:
                    mensaje = "El registro no pudo ser anulado .";
                    break;
                case Retorno.EXCEPCION_SQL:
                    mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                    break;
                case Retorno.CLASE_NO_ENCONTRADA:
                    mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                    break;
            }
            JOptionPane.showMessageDialog(this, mensaje);
            if (retorno == Retorno.EXITO) {
                vp.EventoComboFincas();
                vp.band = 0;
                ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
            }
        }

    }

}
