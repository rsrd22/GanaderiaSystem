/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlGeneral;
import Control.ControlPalpacion;
import Control.ControlPesaje;
import Control.Retorno;
import Modelo.*;
import static Utilidades.Consultas.consultas;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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
    private ArrayList<Object[]> listaMedicamentos;
    private DefaultTableModel dtm;
    private ModeloPalpacion modelo;
    private ModeloMedicamentosPorPesaje modelompp;
    private ControlPalpacion control;
    private ModeloVentanaGeneral modeloVistaGeneral;
    private ArrayList<String> datos;
    public static int guardado = -1;

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
        controlGral = new ControlGeneral();
        cargarComboMedicamento();
        getIdPalpacion();
        this.modeloVistaGeneral = modeloVistaGeneral;
        datos = new ArrayList<>();
        datos = (ArrayList<String>) modeloVistaGeneral.getModeloDatos();
        idAnimal = datos.get(0);
        txtReferenciaAnimal.setText("<html><p>Animal número: <b>" + datos.get(1) + "</b></p></html>");
        listaMedicamentos = new ArrayList<>();

        dtm = new DefaultTableModel(encabezados, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        tablaMedicamentos.setModel(dtm);

        modelo = new ModeloPalpacion();
        modelompp = new ModeloMedicamentosPorPesaje();
        control = new ControlPalpacion();
        txtNumMeses.setEnabled(false);
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
        jdFechaPalpacion = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstDiagnostico = new javax.swing.JList();
        lbltitle20 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(lbltitle14, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Diagnostico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 20, 0, 0);
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(txtNumMeses, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jSeparator11, gridBagConstraints);

        chkDescarte.setBackground(new java.awt.Color(255, 255, 255));
        chkDescarte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescarte.setForeground(new java.awt.Color(59, 123, 50));
        chkDescarte.setText("Descarte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 15, 0, 0);
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
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 316;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(16, 16, 0, 44);
        add(jPanel1, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setRows(5);
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 417;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 44);
        add(jScrollPane2, gridBagConstraints);

        txtReferenciaAnimal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtReferenciaAnimal.setForeground(new java.awt.Color(59, 123, 50));
        txtReferenciaAnimal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 574;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 16, 0, 44);
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnCancelar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 16, 17, 44);
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 123;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jdFechaPalpacion, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 20, 0, 0);
        add(lbltitle19, gridBagConstraints);

        lstDiagnostico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lstDiagnostico.setForeground(new java.awt.Color(59, 123, 50));
        lstDiagnostico.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Parida", "Repaso", "Vacia" };
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 87;
        gridBagConstraints.ipady = 77;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(jScrollPane3, gridBagConstraints);

        lbltitle20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle20.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle20.setText("# meses");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 20, 0, 0);
        add(lbltitle20, gridBagConstraints);
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
        int fila = Integer.parseInt(datos.get(2));
        ((VistaPesaje) modeloVistaGeneral.getPanelPadre()).tbl_Animales.setValueAt("", fila, 11);
        ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void lstDiagnosticoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstDiagnosticoMouseReleased
        txtNumMeses.setEnabled(lstDiagnostico.getSelectedValue().equals("Parida"));
        if(lstDiagnostico.getSelectedValue().equals("Parida")){
            txtNumMeses.requestFocus();
        }
    }//GEN-LAST:event_lstDiagnosticoMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
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
            if (txtCodigoMedicamento.getText().equals(listaMedicamentos.get(i)[0])) {
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

        listaMedicamentos.add(new Object[]{
            txtCodigoMedicamento.getText(),//idMedicamento
            cbMedicamentos.getSelectedItem().toString(),//descripcionMedicamento
            txtCantidadMedicamento.getText(),//CantidadMedicamento
            "Modificar",
            "Eliminar"
        });
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
                listaMedicamentos.get(i)[1],
                listaMedicamentos.get(i)[2],
                listaMedicamentos.get(i)[3],
                listaMedicamentos.get(i)[4]
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
                if (txtCodigoMedicamento.getText().equals(listaMedicamentos.get(i)[0])) {
                    JOptionPane.showMessageDialog(this, "El medicamento ya se encuentra en la lista.");
                    cbMedicamentos.setSelectedIndex(0);
                    cbMedicamentos.requestFocusInWindow();
                    return;
                }
            }
        }
//</editor-fold>

        listaMedicamentos.get(filaAModificar)[0] = txtCodigoMedicamento.getText();
        listaMedicamentos.get(filaAModificar)[1] = cbMedicamentos.getSelectedItem().toString();
        listaMedicamentos.get(filaAModificar)[2] = txtCantidadMedicamento.getText();

        reorganizarMedicamentos();
        btnAgregar.setVisible(true);
        btnModificar.setVisible(false);
        filaAModificar = -1;
    }

    private void Guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFechaPalpacion.getCalendar();
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        
        //</editor-fold>
        
        modelo.setId(txtCodigo.getText());
        modelo.setId_animal(idAnimal);
        modelo.setNotas(txtNotas.getText().trim());
        modelo.setDiagnostico(""+lstDiagnostico.getSelectedValue().toString().toLowerCase());
        modelo.setFecha_palpacion(sdf.format(fecha.getTime()));
        modelo.setNum_meses((txtNumMeses.getText().equals("")?"0":txtNumMeses.getText()));    
        modelo.setFecha_ultimo_parto("");
        modelo.setDescarte(chkDescarte.isSelected() ? "1" : "0");
        modelo.setFecha("NOW()");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            modelompp = new ModeloMedicamentosPorPesaje();
            modelompp.setId_medicamento(listaMedicamentos.get(i)[0].toString());
            modelompp.setDosis(listaMedicamentos.get(i)[2].toString().replace(".", "").replace(",", "."));
            modelo.addMedicamentos(modelompp);
        }

        int retorno = control.Guardar(modelo);

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro guardado satisfactoriamente.";
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
            ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
        }
    }
}
