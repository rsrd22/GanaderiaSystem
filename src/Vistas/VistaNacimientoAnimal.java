/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlAnimales;
import Control.ControlGeneral;
import Control.Retorno;
import Modelo.ModeloAnimales;
import Modelo.ModeloTraslado;
import Modelo.ModeloVentanaGeneral;
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

/**
 *
 * @author DOLFHANDLER
 */
public class VistaNacimientoAnimal extends javax.swing.JPanel {

    private List<Map<String, String>> grupos;
    private List<Map<String, String>> idGrupos;
    private ControlGeneral controlGral;
    private ModeloAnimales modeloDatos;
    private ModeloAnimales modelo;
    private ModeloVentanaGeneral modeloVistaGeneral;
    private ModeloTraslado modeloTraslado;
    private ControlAnimales control;
    private final String FECHA_POR_DEFECTO = "1900-01-01";
    private final String GRUPO_VACIAS = "VACIAS";

    public VistaNacimientoAnimal() {
        initComponents();
    }

    VistaNacimientoAnimal(ModeloVentanaGeneral modeloVista) {
        initComponents();
        setSize(644, 427);
        grupos = new ArrayList<>();
        controlGral = new ControlGeneral();
        modeloDatos = new ModeloAnimales();
        control = new ControlAnimales();
        modeloTraslado = new ModeloTraslado();
        modelo = new ModeloAnimales();
        modeloDatos = (ModeloAnimales) modeloVista.getModeloDatos();
        cargarComboGrupos(modeloDatos.getIdFinca(), modeloDatos.getIdTipoAnimal());
        modeloVistaGeneral = modeloVista;
        cbGrupos.setEnabled(false);
        chkMuerte.setSelected(false);
        mostrarDatosMuerte();
    }

    private void cargarComboGrupos(String idFinca, String idTipoAnimal) {
        String consulta = consultas.get("CARGAR_COMBO_GRUPOS")
                .replace("PARAMETRO1", idFinca)
                .replace("PARAMETRO2", idTipoAnimal);
        grupos = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbGrupos, grupos, "descripcion");
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

        txtCodigoGrupo = new javax.swing.JLabel();
        txtPesoOculto = new javax.swing.JLabel();
        lbltitle12 = new javax.swing.JLabel();
        cbGrupos = new javax.swing.JComboBox();
        cbGenero = new javax.swing.JComboBox();
        lbltitle10 = new javax.swing.JLabel();
        lbltitle14 = new javax.swing.JLabel();
        txtPesoKg = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        lbltitle18 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        lbltitle8 = new javax.swing.JLabel();
        slCalificacion = new javax.swing.JSlider();
        lbltitle19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        lbltitle11 = new javax.swing.JLabel();
        jdFechaNacimiento = new com.toedter.calendar.JDateChooser();
        chkMuerte = new javax.swing.JCheckBox();
        lblFechaMuerte = new javax.swing.JLabel();
        jdFechaMuerte = new com.toedter.calendar.JDateChooser();
        lblCausaMuerte = new javax.swing.JLabel();
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        btnGuardar3 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle12.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle12.setText("Grupos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 15);
        add(lbltitle12, gridBagConstraints);

        cbGrupos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGrupos.setForeground(new java.awt.Color(59, 123, 50));
        cbGrupos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGruposActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(cbGrupos, gridBagConstraints);

        cbGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenero.setForeground(new java.awt.Color(59, 123, 50));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbGenero, gridBagConstraints);

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle10.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lbltitle10, gridBagConstraints);

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Peso (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lbltitle14, gridBagConstraints);

        txtPesoKg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoKg.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoKg.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoKg.setBorder(null);
        txtPesoKg.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKgKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtPesoKg, gridBagConstraints);

        jSeparator13.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jSeparator13, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Peso (Libras)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 15);
        add(lbltitle18, gridBagConstraints);

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(59, 123, 50));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPeso.setBorder(null);
        txtPeso.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPeso.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(txtPeso, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(jSeparator11, gridBagConstraints);

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Calificación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 15);
        add(lbltitle8, gridBagConstraints);

        slCalificacion.setBackground(new java.awt.Color(255, 255, 255));
        slCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        slCalificacion.setMajorTickSpacing(1);
        slCalificacion.setMaximum(5);
        slCalificacion.setMinimum(1);
        slCalificacion.setPaintLabels(true);
        slCalificacion.setPaintTicks(true);
        slCalificacion.setValue(3);
        slCalificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(slCalificacion, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lbltitle19, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.75;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        add(jScrollPane2, gridBagConstraints);

        lbltitle11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle11.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle11.setText("Fecha de nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 15);
        add(lbltitle11, gridBagConstraints);

        jdFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(jdFechaNacimiento, gridBagConstraints);

        chkMuerte.setBackground(new java.awt.Color(255, 255, 255));
        chkMuerte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkMuerte.setForeground(new java.awt.Color(59, 123, 50));
        chkMuerte.setText("Muerte");
        chkMuerte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMuerteStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.01;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(chkMuerte, gridBagConstraints);

        lblFechaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaMuerte.setText("Fecha de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 15);
        add(lblFechaMuerte, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(jdFechaMuerte, gridBagConstraints);

        lblCausaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCausaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblCausaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCausaMuerte.setText("Causa de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 15);
        add(lblCausaMuerte, gridBagConstraints);

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setPreferredSize(new java.awt.Dimension(164, 40));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.weighty = 0.89;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 15);
        add(ScrollCausaMuerte, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        btnGuardar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar.png"))); // NOI18N
        btnGuardar3.setToolTipText("Guardar");
        btnGuardar3.setBorderPainted(false);
        btnGuardar3.setContentAreaFilled(false);
        btnGuardar3.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnGuardar3.setName("btnGuardar"); // NOI18N
        btnGuardar3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar_over.png"))); // NOI18N
        btnGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel4.add(btnGuardar3, gridBagConstraints);

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
        jPanel4.add(btnCancelar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        add(jPanel4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGruposActionPerformed
        int indice = cbGrupos.getSelectedIndex();
        if (indice > 0) {
            String idGrupo = grupos.get(indice).get("id");
            txtCodigoGrupo.setText(idGrupo);
        }
    }//GEN-LAST:event_cbGruposActionPerformed

    private void txtPesoKgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKgKeyReleased
        calcularPesoEnLibras();
    }//GEN-LAST:event_txtPesoKgKeyReleased

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
//        setCalculosVenta();
    }//GEN-LAST:event_txtPesoKeyReleased

    private void chkMuerteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMuerteStateChanged
        mostrarDatosMuerte();
    }//GEN-LAST:event_chkMuerteStateChanged

    private void btnGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar3ActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardar3ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        cbGrupos.setSelectedIndex(0);
        String tipo = "";
        if (cbGenero.getSelectedIndex() == 1) {//HEMBRA
            tipo = "cria hembra";
        } else if (cbGenero.getSelectedIndex() == 2) {//MACHO
            tipo = "cria macho";
        } else {
            return;
        }

        for (int i = 0; i < grupos.size(); i++) {
            if (grupos.get(i).get("tipo_grupo").equals(tipo)) {
                cbGrupos.setSelectedIndex(i);
                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "No existe un grupo al cual enviar las crias.\n"
                + "Diríjase al menú de grupos y cree el grupo correspondiente."
        );
    }//GEN-LAST:event_cbGeneroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollCausaMuerte;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnGuardar2;
    private javax.swing.JButton btnGuardar3;
    public javax.swing.JComboBox cbGenero;
    public javax.swing.JComboBox cbGrupos;
    private javax.swing.JCheckBox chkMuerte;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private com.toedter.calendar.JDateChooser jdFechaMuerte;
    private com.toedter.calendar.JDateChooser jdFechaNacimiento;
    private javax.swing.JLabel lblCausaMuerte;
    private javax.swing.JLabel lblFechaMuerte;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle11;
    private javax.swing.JLabel lbltitle12;
    private javax.swing.JLabel lbltitle14;
    private javax.swing.JLabel lbltitle18;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JSlider slCalificacion;
    private javax.swing.JLabel txtCodigoGrupo;
    private javax.swing.JTextArea txtNotas;
    private javax.swing.JTextArea txtObservacionMuerte;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPesoKg;
    private javax.swing.JLabel txtPesoOculto;
    // End of variables declaration//GEN-END:variables

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
        }
//        if (chkVenta.isSelected()) {
//            setCalculosVenta();
//        }
    }

    private String convertirALibras(double pesoEnKilos) {
        Double resultado = pesoEnKilos * Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        txtPesoOculto.setText("" + Math.round(pesoEnKilos));

        return "" + resultadoRedondeado;
    }

    private String convertirAKilogramos(double pesoEnLibras) {
        Double resultado = pesoEnLibras / Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        txtPesoOculto.setText("" + resultadoRedondeado);

        return "" + resultadoRedondeado;
    }

    private void mostrarDatosMuerte() {
        boolean chequeado = chkMuerte.isSelected();
        lblFechaMuerte.setVisible(chequeado);
        jdFechaMuerte.setVisible(chequeado);
        lblCausaMuerte.setVisible(chequeado);
        ScrollCausaMuerte.setVisible(chequeado);
        txtObservacionMuerte.setVisible(chequeado);
    }

    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (cbGrupos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un grupo para el animal a crear.");
            return;
        }

        if (jdFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe especificar la fecha de nacimiento del animal.");
            jdFechaNacimiento.requestFocusInWindow();
            return;
        }

        if (cbGenero.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione el genero del animal a crear.");
            cbGenero.requestFocusInWindow();
            return;
        }

        if (chkMuerte.isSelected()) {
            if (jdFechaMuerte.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de muerte del animal.");
                jdFechaMuerte.requestFocusInWindow();
                return;
            }
        }

        String idGrupoVacias = "";
        for (Map<String, String> grupo : grupos) {
            if (grupo.get("descripcion").equalsIgnoreCase(GRUPO_VACIAS)) {
                idGrupoVacias = grupo.get("id");
                break;
            }
        }
        if (idGrupoVacias.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encuentra el grupo VACIAS, Debe crear el grupo.");
            return;
        }
//</editor-fold>

        ArrayList<ModeloTraslado> traslados = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String numeroMadre = modeloDatos.getNumero();
        String nroDescendiente = control.ObtenerUltimoDescendiente(numeroMadre);
        String idAnimal = "(SELECT a.id FROM animales a WHERE a.numero='" + modeloDatos.getNumero() + "' "
                + "AND a.numero_descendiente=" + nroDescendiente+" AND a.estado_descendiente=0"
                + ")";
        String idMadre = modeloDatos.getId();

        //<editor-fold defaultstate="collapsed" desc="ESTABLECIENDO LOS DATOS DEL MODELO TRASLADO">
        String datosAdicionales = modeloDatos.getGrupo();
        int indiceGrupo = cbGrupos.getSelectedIndex();
        modeloTraslado.setId("0");
        modeloTraslado.setIdFinca(modeloDatos.getIdFinca());
        modeloTraslado.setFecha("NOW()");
        modeloTraslado.setEstado("Activo");
        modeloTraslado.setFechaTraslado("NOW()");
        modeloTraslado.setIdGrupo(grupos.get(indiceGrupo).get("id"));
        modeloTraslado.setIdUsuario("5");
        modeloTraslado.setMotivo("NACIMIENTO");
        modeloTraslado.setIdAnimal(idAnimal);
        traslados.add(modeloTraslado);
        modeloTraslado = new ModeloTraslado();
        modeloTraslado.setId("0");
        modeloTraslado.setIdFinca(modeloDatos.getIdFinca());
        modeloTraslado.setFecha("NOW()");
        modeloTraslado.setEstado("Activo");
        modeloTraslado.setFechaTraslado("NOW()");
        modeloTraslado.setIdGrupo(idGrupoVacias);
        modeloTraslado.setIdUsuario("5");
        modeloTraslado.setMotivo("PARTO");
        modeloTraslado.setIdAnimal(idMadre);
        traslados.add(modeloTraslado);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ESTABLECIENDO LOS DATOS DEL MODELO ANIMAL">
        modelo.setDescornada("0");
        modelo.setImplante("0");
        modelo.setHierroFisico("0");
        modelo.setMuerte(chkMuerte.isSelected() ? "1" : "0");
        modelo.setVenta("0");
        modelo.setModeloTraslado(traslados);
        modelo.setId(idAnimal);
        modelo.setFecha("NOW()");
        modelo.setDescHierro(modeloDatos.getDescHierro());
        modelo.setHierro(modeloDatos.getHierro());
        modelo.setGrupo(grupos.get(indiceGrupo).get("id"));
        modelo.setDescGrupo(cbGrupos.getSelectedItem().toString());
        modelo.setIdTipoAnimal(modeloDatos.getIdTipoAnimal());
        modelo.setCalificacion("" + slCalificacion.getValue());
        modelo.setCapado("NO");
        modelo.setDescTipoAnimal(modeloDatos.getDescTipoAnimal());
        modelo.setGenero(cbGenero.getSelectedItem().toString().toLowerCase());
        modelo.setIdUsuario("5");
        modelo.setNotas(txtNotas.getText().trim());
        modelo.setNumero(modeloDatos.getNumero());
        modelo.setNumeroMama(modeloDatos.getNumero());
        modelo.setPeso(txtPesoOculto.getText().replace(".", "").replace(",", "."));
        Calendar fechaNacimiento = jdFechaNacimiento.getCalendar();
        modelo.setFechaNacimiento(sdf.format(fechaNacimiento.getTime()));
        modelo.setFechaNovilla(FECHA_POR_DEFECTO);
        modelo.setNumeroDescendiente(nroDescendiente);
        modelo.setEstadoDescendiente("0");
        modelo.setDescripcionMuerte(txtObservacionMuerte.getText());
        modelo.setPrecioVenta("NULL");
        modelo.setPesoCanal("NULL");
        modelo.setTipoVenta("NULL");
        modelo.setFechaVenta(FECHA_POR_DEFECTO);
        modelo.setNumeroMamaAdoptiva("NULL");
        modelo.setFechaDestete(FECHA_POR_DEFECTO);
        modelo.setPesoDestete("0");

        if (chkMuerte.isSelected()) {
            Calendar fechaMuerte = jdFechaMuerte.getCalendar();
            modelo.setFechaMuerte(sdf.format(fechaMuerte.getTime()));
        } else {
            modelo.setFechaMuerte(FECHA_POR_DEFECTO);
        }
        //</editor-fold>
        
        int retorno = control.GuardarCria(modelo,datosAdicionales);

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro guardado satisfactoriamente.";
                ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
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
    }

}
