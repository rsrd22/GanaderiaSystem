/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import AjustarControles.AjustarControles;
import Busqueda.VistaBusqueda;
import Control.ControlAnimales;
import Control.ControlGeneral;
import Control.ControlPropietarios;
import Control.ControlTraslado;
import Control.Retorno;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.ModeloAnimales;
import Modelo.ModeloGestorBusqueda;
import Modelo.ModeloHierros;
import Modelo.ModeloTipoAnimales;
import Modelo.ModeloTraslado;
import Modelo.ModeloVentanaGeneral;
import static Utilidades.Consultas.consultas;
import Utilidades.Estado;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaAnimales extends javax.swing.JPanel implements IControlesUsuario {

    private ModeloGestorBusqueda objetoBusqueda;
    private ModeloVentanaGeneral objetoVentana;
    private ModeloAnimales modelo;
    private ControlAnimales control;
    private int editar;
    private GestionEstadoControles controles;
    private JButton[] botones;
    private final String FECHA_POR_DEFECTO = "1900-01-01";
    private final String NULL = "NULL";
    private List<Map<String, String>> grupos;
    private List<Map<String, String>> tipoAnimales;
    private List<Map<String, String>> hierros;
    private List<Map<String, String>> fincas;
    private List<Map<String, String>> propietarios;
    private List<Map<String, String>> select;
    private ControlGeneral controlGral;
    private ModeloTraslado modeloTraslado;
    private ControlTraslado controlTraslado;

    /**
     * Creates new form VistaAnimales
     */
    public VistaAnimales() {
        initComponents();
        iniciarComponentes();

        lblPesoDestete.setVisible(false);
        txtPesoDestete.setVisible(false);
        sepPesoDestete.setVisible(false);
        lblFechaNovilla.setVisible(false);
        jdFechaDeNovilla.setVisible(false);

        lblAdoptiva.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jSeparatorAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);
        lblFechaDestete.setVisible(false);
        lblPesoCanal.setVisible(false);
        txtPesoCanal.setVisible(false);
        sepPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
        sepNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setEnabled(false);

        controlGral = new ControlGeneral();
        modelo = new ModeloAnimales();
        control = new ControlAnimales();
        modeloTraslado = new ModeloTraslado();
        controlTraslado = new ControlTraslado();
        editar = Estado.GUARDAR;
        botones = new JButton[]{
            btnConsultar,
            btnDescartar,
            btnEliminar,
            btnGuardar,
            btnModificar
        };
        cargarComboFincas();
        controles.habilitarControles();
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    public VistaAnimales(ModeloVentanaGeneral modeloVista) {
        initComponents();
        iniciarComponentes();

        lblPesoDestete.setVisible(false);
        txtPesoDestete.setVisible(false);
        sepPesoDestete.setVisible(false);
        lblFechaNovilla.setVisible(false);
        jdFechaDeNovilla.setVisible(false);

        lblAdoptiva.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jSeparatorAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);
        lblFechaDestete.setVisible(false);

        lblPesoCanal.setVisible(false);
        txtPesoCanal.setVisible(false);
        sepPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
        sepNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setEnabled(false);

        controlGral = new ControlGeneral();
        objetoVentana = modeloVista;
        modelo = new ModeloAnimales();
        control = new ControlAnimales();
        modeloTraslado = new ModeloTraslado();
        controlTraslado = new ControlTraslado();
        editar = Estado.GUARDAR;
        botones = new JButton[]{
            btnConsultar,
            btnDescartar,
            btnEliminar,
            btnGuardar,
            btnModificar
        };
        cargarComboFincas();
        controles.habilitarControles();
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    private void cargarComboFincas() {
        String consulta = consultas.get("CARGAR_COMBO_FINCAS");
        fincas = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbFinca, fincas, "descripcion");
    }

    private void cargarComboPropietarios() {
        String consulta = consultas.get("CARGAR_COMBO_PROPIETARIOS").replace("PARAMETRO1",
                txtCodigoFinca.getText());
        propietarios = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbPropietario, propietarios, "descripcion");
    }

    private void cargarComboGrupos() {
        if (cbTiposDeAnimales.getSelectedIndex() != -1) {
            String consulta = consultas.get("CARGAR_COMBO_GRUPOS").replace("PARAMETRO1",
                    txtCodigoFinca.getText()).replace("PARAMETRO2", txtCodigoTipoAnimal.getText());
            grupos = controlGral.GetComboBox(consulta);

            Utilidades.LlenarComboBox(cbGrupos, grupos, "descripcion");
        }
    }

    private void cargarComboTipoAnimales() {
        String consulta = consultas.get("CARGAR_COMBO_TIPO_ANIMALES") + txtCodigoFinca.getText();
        tipoAnimales = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbTiposDeAnimales, tipoAnimales, "descripcion");
    }

    private void cargarComboHierros() {
        String consulta = consultas.get("CARGAR_COMBO_HIERROS") + txtCodigoPropietario.getText();
        hierros = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbHierros, hierros, "descripcion");
    }

    @Override
    public void iniciarComponentes() {
        controles = new GestionEstadoControles();

        Control control = new Control(true, cbTiposDeAnimales);
        controles.addControl(control);

        control = new Control(true, cbFinca);
        controles.addControl(control);

        control = new Control(true, jdFechaDeNovilla);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkHierro);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkImplante);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkDescornada);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkNovilla);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtPesoDestete);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtPesoKg);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtObservacionMuerte);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbTipoVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtPrecioVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtPesoCanal);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbPropietario);
        controles.addControl(control);

        control = new Control(true, cbHierros);
        controles.addControl(control);

        control = new Control(true, txtNumero);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtNumeroMama);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtNumeroMamaAdoptiva);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkAdoptivo);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbGenero);
        controles.addControl(control);

        control = new Control(true, cbGrupos);
        controles.addControl(control);

        control = new Control(true, txtPeso);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, slCalificacion);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtNotas);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkMuerte);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, jdFechaMuerte);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, jdFechaNacimiento);
        controles.addControl(control);

        control = new Control(true, chkDestete);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, jdFechaDestete);
        controles.addControl(control);

        control = new Control(true, chkVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, jdFechaVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkCapado);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);
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

        txtCodigoTipoAnimal = new javax.swing.JLabel();
        txtCodigoAnimal = new javax.swing.JLabel();
        txtCodigoFinca = new javax.swing.JLabel();
        txtCodigoHierro = new javax.swing.JLabel();
        txtCodigoGrupo = new javax.swing.JLabel();
        txtCodigoPropietario = new javax.swing.JLabel();
        txtPesoOculto = new javax.swing.JLabel();
        lbltitle4 = new javax.swing.JLabel();
        lblNumeroDescendiente = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        lblAdoptiva = new javax.swing.JLabel();
        txtNumeroMama = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        lblFechaDestete = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox();
        lbltitle8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        lbltitle10 = new javax.swing.JLabel();
        lbltitle11 = new javax.swing.JLabel();
        chkCapado = new javax.swing.JCheckBox();
        txtPeso = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        slCalificacion = new javax.swing.JSlider();
        lbltitle14 = new javax.swing.JLabel();
        chkDestete = new javax.swing.JCheckBox();
        chkMuerte = new javax.swing.JCheckBox();
        chkVenta = new javax.swing.JCheckBox();
        txtNumeroMamaAdoptiva = new javax.swing.JTextField();
        jSeparatorAdoptiva = new javax.swing.JSeparator();
        lbltitle7 = new javax.swing.JLabel();
        chkAdoptivo = new javax.swing.JCheckBox();
        lbltitle12 = new javax.swing.JLabel();
        cbGrupos = new javax.swing.JComboBox();
        lbltitle15 = new javax.swing.JLabel();
        cbHierros = new javax.swing.JComboBox();
        jdFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jdFechaDestete = new com.toedter.calendar.JDateChooser();
        lbltitle6 = new javax.swing.JLabel();
        cbTiposDeAnimales = new javax.swing.JComboBox();
        lbltitle16 = new javax.swing.JLabel();
        txtNumeroDescendiente = new javax.swing.JTextField();
        sepNumeroDescendiente = new javax.swing.JSeparator();
        cbFinca = new javax.swing.JComboBox();
        lbltitle17 = new javax.swing.JLabel();
        cbPropietario = new javax.swing.JComboBox();
        txtPesoKg = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        lbltitle18 = new javax.swing.JLabel();
        lbltitle19 = new javax.swing.JLabel();
        panelInfoVenta = new javax.swing.JPanel();
        lblFechaVenta = new javax.swing.JLabel();
        jdFechaVenta = new com.toedter.calendar.JDateChooser();
        lbltitle20 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        txtPesoCanal = new javax.swing.JTextField();
        lblPesoCanal = new javax.swing.JLabel();
        sepPesoCanal = new javax.swing.JSeparator();
        lbltitle21 = new javax.swing.JLabel();
        cbTipoVenta = new javax.swing.JComboBox();
        txtCalculos = new javax.swing.JLabel();
        panelInfoMuerte = new javax.swing.JPanel();
        lblFechaMuerte = new javax.swing.JLabel();
        lblCausaMuerte = new javax.swing.JLabel();
        jdFechaMuerte = new com.toedter.calendar.JDateChooser();
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblFechaNovilla = new javax.swing.JLabel();
        jdFechaDeNovilla = new com.toedter.calendar.JDateChooser();
        lblPesoDestete = new javax.swing.JLabel();
        txtPesoDestete = new javax.swing.JTextField();
        sepPesoDestete = new javax.swing.JSeparator();
        chkImplante = new javax.swing.JCheckBox();
        chkHierro = new javax.swing.JCheckBox();
        chkDescornada = new javax.swing.JCheckBox();
        chkNovilla = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        lbltitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle4.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle4.setText("Tipo de animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        add(lbltitle4, gridBagConstraints);

        lblNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        lblNumeroDescendiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumeroDescendiente.setText(" - ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.weightx = 0.011111116;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblNumeroDescendiente, gridBagConstraints);

        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumero.setForeground(new java.awt.Color(59, 123, 50));
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumero.setBorder(null);
        txtNumero.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumero.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.083333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(txtNumero, gridBagConstraints);

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 6);
        add(jSeparator10, gridBagConstraints);

        lblAdoptiva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        lblAdoptiva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAdoptiva.setText("Número madre adoptiva");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lblAdoptiva, gridBagConstraints);

        txtNumeroMama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMama.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMama.setBorder(null);
        txtNumeroMama.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroMama.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroMama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNumeroMamaFocusLost(evt);
            }
        });
        txtNumeroMama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMamaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(txtNumeroMama, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator11, gridBagConstraints);

        lblFechaDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDestete.setText("Fecha de destete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(lblFechaDestete, gridBagConstraints);

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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(cbGenero, gridBagConstraints);

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Calificación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.06;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lbltitle8, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BELOW_BASELINE_TRAILING;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jScrollPane2, gridBagConstraints);

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle10.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 5);
        add(lbltitle10, gridBagConstraints);

        lbltitle11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle11.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle11.setText("Fecha de nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(lbltitle11, gridBagConstraints);

        chkCapado.setBackground(new java.awt.Color(255, 255, 255));
        chkCapado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkCapado.setForeground(new java.awt.Color(59, 123, 50));
        chkCapado.setText("Capado");
        chkCapado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCapadoStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkCapado, gridBagConstraints);

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
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPeso, gridBagConstraints);

        jSeparator12.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(jSeparator12, gridBagConstraints);

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
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(slCalificacion, gridBagConstraints);

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Peso (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle14, gridBagConstraints);

        chkDestete.setBackground(new java.awt.Color(255, 255, 255));
        chkDestete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDestete.setForeground(new java.awt.Color(59, 123, 50));
        chkDestete.setText("Destete");
        chkDestete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDesteteStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkDestete, gridBagConstraints);

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
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        add(chkMuerte, gridBagConstraints);

        chkVenta.setBackground(new java.awt.Color(255, 255, 255));
        chkVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkVenta.setForeground(new java.awt.Color(59, 123, 50));
        chkVenta.setText("Venta");
        chkVenta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(chkVenta, gridBagConstraints);

        txtNumeroMamaAdoptiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMamaAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMamaAdoptiva.setBorder(null);
        txtNumeroMamaAdoptiva.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMamaAdoptivaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtNumeroMamaAdoptiva, gridBagConstraints);

        jSeparatorAdoptiva.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparatorAdoptiva, gridBagConstraints);

        lbltitle7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle7.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle7.setText("Número de la madre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 5);
        add(lbltitle7, gridBagConstraints);

        chkAdoptivo.setBackground(new java.awt.Color(255, 255, 255));
        chkAdoptivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkAdoptivo.setForeground(new java.awt.Color(59, 123, 50));
        chkAdoptivo.setText("Madre adoptiva");
        chkAdoptivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAdoptivoMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 5);
        add(chkAdoptivo, gridBagConstraints);

        lbltitle12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle12.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle12.setText("Grupos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 5);
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(cbGrupos, gridBagConstraints);

        lbltitle15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle15.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle15.setText("Hierros");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(lbltitle15, gridBagConstraints);

        cbHierros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbHierros.setForeground(new java.awt.Color(59, 123, 50));
        cbHierros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbHierros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHierrosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbHierros, gridBagConstraints);

        jdFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaNacimiento, gridBagConstraints);

        jdFechaDestete.setDateFormatString("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaDestete, gridBagConstraints);

        lbltitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle6.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle6.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 5);
        add(lbltitle6, gridBagConstraints);

        cbTiposDeAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTiposDeAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTiposDeAnimales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTiposDeAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTiposDeAnimalesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbTiposDeAnimales, gridBagConstraints);

        lbltitle16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle16.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle16.setText("Número");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 5);
        add(lbltitle16, gridBagConstraints);

        txtNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroDescendiente.setBorder(null);
        txtNumeroDescendiente.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.033333336;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(txtNumeroDescendiente, gridBagConstraints);

        sepNumeroDescendiente.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(sepNumeroDescendiente, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        add(cbFinca, gridBagConstraints);

        lbltitle17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle17.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle17.setText("Propietarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(lbltitle17, gridBagConstraints);

        cbPropietario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbPropietario.setForeground(new java.awt.Color(59, 123, 50));
        cbPropietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPropietarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbPropietario, gridBagConstraints);

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
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPesoKg, gridBagConstraints);

        jSeparator13.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator13, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Peso (Libras)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lbltitle18, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(lbltitle19, gridBagConstraints);

        panelInfoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N
        panelInfoVenta.setLayout(new java.awt.GridBagLayout());

        lblFechaVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaVenta.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaVenta.setText("Fecha de venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        panelInfoVenta.add(lblFechaVenta, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        panelInfoVenta.add(jdFechaVenta, gridBagConstraints);

        lbltitle20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle20.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle20.setText("Precio de venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        panelInfoVenta.add(lbltitle20, gridBagConstraints);

        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPrecioVenta.setBorder(null);
        txtPrecioVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        panelInfoVenta.add(txtPrecioVenta, gridBagConstraints);

        jSeparator14.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        panelInfoVenta.add(jSeparator14, gridBagConstraints);

        txtPesoCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoCanal.setBorder(null);
        txtPesoCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoCanalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        panelInfoVenta.add(txtPesoCanal, gridBagConstraints);

        lblPesoCanal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoCanal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoCanal.setText("Peso del canal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        panelInfoVenta.add(lblPesoCanal, gridBagConstraints);

        sepPesoCanal.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        panelInfoVenta.add(sepPesoCanal, gridBagConstraints);

        lbltitle21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle21.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle21.setText("Tipo de venta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        panelInfoVenta.add(lbltitle21, gridBagConstraints);

        cbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoVenta.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Particular", "Matadero" }));
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        panelInfoVenta.add(cbTipoVenta, gridBagConstraints);

        txtCalculos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCalculos.setForeground(new java.awt.Color(59, 123, 50));
        txtCalculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        panelInfoVenta.add(txtCalculos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 15);
        add(panelInfoVenta, gridBagConstraints);

        panelInfoMuerte.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoMuerte.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Datos de muerte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N
        panelInfoMuerte.setLayout(new java.awt.GridBagLayout());

        lblFechaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaMuerte.setText("Fecha de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        panelInfoMuerte.add(lblFechaMuerte, gridBagConstraints);

        lblCausaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCausaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblCausaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCausaMuerte.setText("Causa de muerte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        panelInfoMuerte.add(lblCausaMuerte, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        panelInfoMuerte.add(jdFechaMuerte, gridBagConstraints);

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setPreferredSize(new java.awt.Dimension(164, 40));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 0.7;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        panelInfoMuerte.add(ScrollCausaMuerte, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.33333333;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 15);
        add(panelInfoMuerte, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar.png"))); // NOI18N
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnGuardar, gridBagConstraints);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar.png"))); // NOI18N
        btnModificar.setToolTipText("Modificar");
        btnModificar.setBorderPainted(false);
        btnModificar.setContentAreaFilled(false);
        btnModificar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnModificar.setName("btnModificar"); // NOI18N
        btnModificar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar_over.png"))); // NOI18N
        btnModificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/modificar_over.png"))); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnModificar, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnDescartar, gridBagConstraints);

        btnConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar.png"))); // NOI18N
        btnConsultar.setToolTipText("Consutar");
        btnConsultar.setBorderPainted(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnConsultar.setName("btnConsultar"); // NOI18N
        btnConsultar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar_over.png"))); // NOI18N
        btnConsultar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/consultar_over.png"))); // NOI18N
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnConsultar, gridBagConstraints);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar_over.png"))); // NOI18N
        btnEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/eliminar_over.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jPanel2, gridBagConstraints);

        lblFechaNovilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaNovilla.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaNovilla.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaNovilla.setText("Fecha de novilla");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lblFechaNovilla, gridBagConstraints);

        jdFechaDeNovilla.setDateFormatString("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jdFechaDeNovilla, gridBagConstraints);

        lblPesoDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoDestete.setText("Peso de destete (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        add(lblPesoDestete, gridBagConstraints);

        txtPesoDestete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoDestete.setBorder(null);
        txtPesoDestete.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoDesteteKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(txtPesoDestete, gridBagConstraints);

        sepPesoDestete.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(sepPesoDestete, gridBagConstraints);

        chkImplante.setBackground(new java.awt.Color(255, 255, 255));
        chkImplante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkImplante.setForeground(new java.awt.Color(59, 123, 50));
        chkImplante.setText("Implante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(chkImplante, gridBagConstraints);

        chkHierro.setBackground(new java.awt.Color(255, 255, 255));
        chkHierro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkHierro.setForeground(new java.awt.Color(59, 123, 50));
        chkHierro.setText("Hierro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.166666667;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(chkHierro, gridBagConstraints);

        chkDescornada.setBackground(new java.awt.Color(255, 255, 255));
        chkDescornada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescornada.setForeground(new java.awt.Color(59, 123, 50));
        chkDescornada.setText("Descornada");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.11111111;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 0);
        add(chkDescornada, gridBagConstraints);

        chkNovilla.setBackground(new java.awt.Color(255, 255, 255));
        chkNovilla.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkNovilla.setForeground(new java.awt.Color(59, 123, 50));
        chkNovilla.setText("Es novilla");
        chkNovilla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkNovillaStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(chkNovilla, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        Descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void chkDesteteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkDesteteStateChanged
        boolean chequeado = chkDestete.isSelected();
        jdFechaDestete.setVisible(chequeado);
        lblPesoDestete.setVisible(chequeado);
        txtPesoDestete.setVisible(chequeado);
        sepPesoDestete.setVisible(chequeado);
//        jdFechaDestete.setCalendar(Calendar.getInstance());
        lblFechaDestete.setVisible(chequeado);
    }//GEN-LAST:event_chkDesteteStateChanged

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
        setCalculosVenta();
    }//GEN-LAST:event_txtPesoKeyReleased

    private void txtNumeroMamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroMamaKeyReleased
        if (!chkAdoptivo.isSelected()) {
            String numeroMadre = txtNumeroMama.getText().trim();
            if (!numeroMadre.isEmpty()) {
                txtNumero.setText(numeroMadre);
            }
        }
    }//GEN-LAST:event_txtNumeroMamaKeyReleased

    private void txtNumeroMamaAdoptivaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroMamaAdoptivaKeyReleased
        String numeroMadre = txtNumeroMamaAdoptiva.getText().trim();
        if (!numeroMadre.isEmpty()) {
            txtNumero.setText(numeroMadre);
        }
    }//GEN-LAST:event_txtNumeroMamaAdoptivaKeyReleased

    private void txtNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroFocusLost
        if (!txtNumero.getText().isEmpty()) {
            boolean mostrar = txtNumeroMama.getText().trim().equals(txtNumero.getText().trim());
            lblNumeroDescendiente.setVisible(mostrar);
            sepNumeroDescendiente.setVisible(mostrar);
            txtNumeroDescendiente.setVisible(mostrar);
            if (!mostrar) {
                if (editar == Estado.GUARDAR) {
                    if (verificarNroAnimal()) {
                        JOptionPane.showMessageDialog(null, "El número " + txtNumero.getText() + " pertenece a otro animal.\n");
                    }
                }
            }
        }
    }//GEN-LAST:event_txtNumeroFocusLost

    private boolean verificarNroAnimal() {
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        lista = (ArrayList<ModeloAnimales>) control.ObtenerDatosFiltro(txtNumero.getText().trim());
        return lista.size() > 0;
    }

    private void cbTiposDeAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTiposDeAnimalesActionPerformed
        int indice = cbTiposDeAnimales.getSelectedIndex();
        if (indice > 0) {
            String idTipoAnimal = tipoAnimales.get(indice).get("id");
            txtCodigoTipoAnimal.setText(idTipoAnimal);

            cargarComboGrupos();
        }
    }//GEN-LAST:event_cbTiposDeAnimalesActionPerformed

    private void chkAdoptivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkAdoptivoMouseClicked
        if (evt.getClickCount() == 1) {
            boolean mostrar = chkAdoptivo.isSelected();
            lblAdoptiva.setVisible(mostrar);
            txtNumeroMamaAdoptiva.setVisible(mostrar);
            jSeparatorAdoptiva.setVisible(mostrar);
            if (mostrar) {
                txtNumero.setText(txtNumeroMamaAdoptiva.getText());
            } else {
                txtNumero.setText(txtNumeroMama.getText());
            }
        }
    }//GEN-LAST:event_chkAdoptivoMouseClicked

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed
        boolean habilitar = cbGenero.getSelectedItem().toString().equalsIgnoreCase("macho");
        chkCapado.setEnabled(habilitar);
        chkCapado.setSelected(!habilitar ? false : chkCapado.isSelected());
    }//GEN-LAST:event_cbGeneroActionPerformed

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        int indice = cbFinca.getSelectedIndex();
        if (indice > 0) {
            String idFinca = fincas.get(indice).get("id");
            txtCodigoFinca.setText(idFinca);

            cargarComboPropietarios();
            cargarComboTipoAnimales();
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGruposActionPerformed
        int indice = cbGrupos.getSelectedIndex();
        if (indice > 0) {
            String idGrupo = grupos.get(indice).get("id");
            txtCodigoGrupo.setText(idGrupo);
        }
    }//GEN-LAST:event_cbGruposActionPerformed

    private void cbHierrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHierrosActionPerformed
        int indice = cbHierros.getSelectedIndex();
        if (indice > 0) {
            String idHierro = hierros.get(indice).get("id");
            txtCodigoHierro.setText(idHierro);
        }
    }//GEN-LAST:event_cbHierrosActionPerformed

    private void cbPropietarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPropietarioActionPerformed
        int indice = cbPropietario.getSelectedIndex();
        if (indice > 0) {
            String idPropietario = propietarios.get(indice).get("id");
            txtCodigoPropietario.setText(idPropietario);
            cargarComboHierros();
        }
    }//GEN-LAST:event_cbPropietarioActionPerformed

    private void txtPesoKgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoKgKeyReleased
        calcularPesoEnLibras();
    }//GEN-LAST:event_txtPesoKgKeyReleased

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
        if (chkVenta.isSelected()) {
            setCalculosVenta();
        }
    }

    public void EstadoVenta(boolean chequeado) {
        jdFechaVenta.setVisible(chequeado);
        jdFechaVenta.setCalendar(Calendar.getInstance());
        lblFechaVenta.setVisible(chequeado);
        panelInfoVenta.setVisible(chequeado);
    }

    public void EstadoMuerte(boolean chequeado) {
        jdFechaMuerte.setVisible(chequeado);
        jdFechaMuerte.setCalendar(Calendar.getInstance());
        lblFechaMuerte.setVisible(chequeado);
        lblCausaMuerte.setVisible(chequeado);
        txtObservacionMuerte.setVisible(chequeado);
        ScrollCausaMuerte.setVisible(chequeado);
        panelInfoMuerte.setVisible(chequeado);
    }

    private void cbTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoVentaActionPerformed
        boolean mostrar = cbTipoVenta.getSelectedIndex() == 2;
        lblPesoCanal.setVisible(mostrar);
        txtPesoCanal.setVisible(mostrar);
        sepPesoCanal.setVisible(mostrar);
    }//GEN-LAST:event_cbTipoVentaActionPerformed

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        String precioVenta = txtPrecioVenta.getText();
        String precioSinPuntos = precioVenta.indexOf(".") > -1 ? precioVenta.replace(".", "") : precioVenta;
        String precioFormateado = Expresiones.procesarSoloNumP(precioSinPuntos);
        precioFormateado = Utilidades.MascaraMonedaConDecimales(precioFormateado);
        txtPrecioVenta.setText(precioFormateado);

        setCalculosVenta();
    }//GEN-LAST:event_txtPrecioVentaKeyReleased

    private void txtPesoCanalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoCanalKeyReleased
        String pesoCanal = txtPesoCanal.getText();
        String pesoCanalSinPuntos = pesoCanal.indexOf(".") > -1 ? pesoCanal.replace(".", "") : pesoCanal;
        String pesoCanalFormateado = Expresiones.procesarSoloNumP(pesoCanalSinPuntos);
        pesoCanalFormateado = Utilidades.MascaraMonedaConDecimales(pesoCanalFormateado);
        txtPesoCanal.setText(pesoCanalFormateado);

        setCalculosVenta();
    }//GEN-LAST:event_txtPesoCanalKeyReleased

    private void txtNumeroMamaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroMamaFocusLost
        String numeroMadre = txtNumeroMama.getText();
        String nroDescendiente = control.ObtenerUltimoDescendiente(numeroMadre);
        txtNumeroDescendiente.setText(nroDescendiente);

        boolean mostrar = true;
        lblNumeroDescendiente.setVisible(mostrar);
        sepNumeroDescendiente.setVisible(mostrar);
        txtNumeroDescendiente.setVisible(mostrar);
    }//GEN-LAST:event_txtNumeroMamaFocusLost

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        if (objetoVentana != null) {
            this.setSize(((JFrame) objetoVentana.getFrameVentana()).getWidth(), ((JFrame) objetoVentana.getFrameVentana()).getHeight());
            ((JFrame) objetoVentana.getFrameVentana()).revalidate();
            ((JFrame) objetoVentana.getFrameVentana()).repaint();
        }
    }//GEN-LAST:event_formPropertyChange

    private void chkCapadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCapadoStateChanged
        boolean chequeado = chkCapado.isSelected();
        if (chequeado) {
            cbGenero.getModel().setSelectedItem("Macho");
        }
    }//GEN-LAST:event_chkCapadoStateChanged

    private void chkMuerteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkMuerteStateChanged
        boolean chequeado = chkMuerte.isSelected();
        panelInfoMuerte.setVisible(chequeado);
        if (panelInfoVenta.isVisible()) {
            panelInfoVenta.setVisible(!chequeado);
            chkVenta.setSelected(!chequeado);
        }
    }//GEN-LAST:event_chkMuerteStateChanged

    private void chkVentaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVentaStateChanged
        boolean chequeado = chkVenta.isSelected();
        panelInfoVenta.setVisible(chequeado);
        if (panelInfoMuerte.isVisible()) {
            panelInfoMuerte.setVisible(!chequeado);
            chkMuerte.setSelected(!chequeado);
        }
    }//GEN-LAST:event_chkVentaStateChanged

    private void txtPesoDesteteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoDesteteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoDesteteKeyReleased

    private void chkNovillaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkNovillaStateChanged
        boolean chequeado = chkNovilla.isSelected();
        lblFechaNovilla.setVisible(chequeado);
        jdFechaDeNovilla.setVisible(chequeado);
    }//GEN-LAST:event_chkNovillaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollCausaMuerte;
    public javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbGenero;
    public javax.swing.JComboBox cbGrupos;
    public javax.swing.JComboBox cbHierros;
    public javax.swing.JComboBox cbPropietario;
    public javax.swing.JComboBox cbTipoVenta;
    public javax.swing.JComboBox cbTiposDeAnimales;
    private javax.swing.JCheckBox chkAdoptivo;
    private javax.swing.JCheckBox chkCapado;
    private javax.swing.JCheckBox chkDescornada;
    private javax.swing.JCheckBox chkDestete;
    private javax.swing.JCheckBox chkHierro;
    private javax.swing.JCheckBox chkImplante;
    private javax.swing.JCheckBox chkMuerte;
    private javax.swing.JCheckBox chkNovilla;
    private javax.swing.JCheckBox chkVenta;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparatorAdoptiva;
    private com.toedter.calendar.JDateChooser jdFechaDeNovilla;
    private com.toedter.calendar.JDateChooser jdFechaDestete;
    private com.toedter.calendar.JDateChooser jdFechaMuerte;
    private com.toedter.calendar.JDateChooser jdFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdFechaVenta;
    private javax.swing.JLabel lblAdoptiva;
    private javax.swing.JLabel lblCausaMuerte;
    private javax.swing.JLabel lblFechaDestete;
    private javax.swing.JLabel lblFechaMuerte;
    private javax.swing.JLabel lblFechaNovilla;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblNumeroDescendiente;
    private javax.swing.JLabel lblPesoCanal;
    private javax.swing.JLabel lblPesoDestete;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle11;
    private javax.swing.JLabel lbltitle12;
    private javax.swing.JLabel lbltitle14;
    private javax.swing.JLabel lbltitle15;
    private javax.swing.JLabel lbltitle16;
    private javax.swing.JLabel lbltitle17;
    private javax.swing.JLabel lbltitle18;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JLabel lbltitle20;
    private javax.swing.JLabel lbltitle21;
    private javax.swing.JLabel lbltitle4;
    private javax.swing.JLabel lbltitle6;
    private javax.swing.JLabel lbltitle7;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JPanel panelInfoMuerte;
    private javax.swing.JPanel panelInfoVenta;
    private javax.swing.JSeparator sepNumeroDescendiente;
    private javax.swing.JSeparator sepPesoCanal;
    private javax.swing.JSeparator sepPesoDestete;
    private javax.swing.JSlider slCalificacion;
    private javax.swing.JLabel txtCalculos;
    private javax.swing.JLabel txtCodigoAnimal;
    private javax.swing.JLabel txtCodigoFinca;
    private javax.swing.JLabel txtCodigoGrupo;
    private javax.swing.JLabel txtCodigoHierro;
    private javax.swing.JLabel txtCodigoPropietario;
    private javax.swing.JLabel txtCodigoTipoAnimal;
    private javax.swing.JTextArea txtNotas;
    public javax.swing.JTextField txtNumero;
    public javax.swing.JTextField txtNumeroDescendiente;
    public javax.swing.JTextField txtNumeroMama;
    public javax.swing.JTextField txtNumeroMamaAdoptiva;
    private javax.swing.JTextArea txtObservacionMuerte;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPesoCanal;
    public javax.swing.JTextField txtPesoDestete;
    public javax.swing.JTextField txtPesoKg;
    private javax.swing.JLabel txtPesoOculto;
    public javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables

    public void RetornoBusqueda(ModeloGestorBusqueda objeto, Map<String, String> retorno) {
        if (objeto.getOpcion() == 0) {//SE LLAMA LA BUSQUEDA DESDE LA MISMA VISTA
            //<editor-fold defaultstate="collapsed" desc="SE ESTABLECEN LOS DATOS DEL FORMULARIO">
            String id = retorno.get("ID");
            modelo = ((ArrayList<ModeloAnimales>) control.ObtenerDatosKey(id)).get(0);
            boolean capado = modelo.getCapado().equalsIgnoreCase("si");
            boolean venta = modelo.getVenta().equalsIgnoreCase("1");
            boolean muerte = modelo.getMuerte().equalsIgnoreCase("1");
            boolean implante = modelo.getImplante().equalsIgnoreCase("1");
            boolean hierroFisico = modelo.getHierroFisico().equalsIgnoreCase("1");
            boolean descornado = modelo.getDescornada().equalsIgnoreCase("1");
            boolean esNovilla = !(modelo.getFechaNovilla().equals(FECHA_POR_DEFECTO) || modelo.getFechaNovilla().equalsIgnoreCase(NULL));

            String numeroMamaAdoptiva = modelo.getNumeroMamaAdoptiva();
            boolean adoptado = true;
            if (numeroMamaAdoptiva.isEmpty()) {
                adoptado = false;
            } else if (numeroMamaAdoptiva.equalsIgnoreCase("null")) {
                adoptado = false;
            }

            cargarComboFincas();
            txtCodigoFinca.setText(modelo.getIdFinca());
            cbFinca.setSelectedItem(modelo.getDescFinca());

            cargarComboTipoAnimales();
            cbTiposDeAnimales.setSelectedItem(modelo.getDescTipoAnimal());
            txtCodigoTipoAnimal.setText(modelo.getIdTipoAnimal());

            cargarComboGrupos();
            cbGrupos.setSelectedItem(modelo.getDescGrupo());
            txtCodigoGrupo.setText(modelo.getGrupo());

            cargarComboPropietarios();
            cbPropietario.setSelectedItem(modelo.getDescPropietario());
            txtCodigoPropietario.setText(modelo.getIdPropietario());

            cargarComboHierros();
            cbHierros.setSelectedItem(modelo.getDescHierro());
            txtCodigoHierro.setText(modelo.getHierro());

            txtCodigoAnimal.setText(modelo.getId());
            txtNumeroMama.setText(modelo.getNumeroMama());
            txtNumero.setText(modelo.getNumero());
            cbGenero.setSelectedItem(Utilidades.CapitaliceTexto(modelo.getGenero()));
            txtPesoKg.setText(modelo.getPeso());
            txtPesoOculto.setText(modelo.getPeso());
            slCalificacion.setValue(Integer.parseInt(modelo.getCalificacion()));
            chkCapado.setSelected(capado);
            txtNotas.setText(modelo.getNotas());
            chkAdoptivo.setSelected(adoptado);

            chkNovilla.setSelected(esNovilla);
            chkHierro.setSelected(hierroFisico);
            chkImplante.setSelected(implante);
            chkDescornada.setSelected(descornado);
            chkVenta.setSelected(venta);
            chkMuerte.setSelected(muerte);
            txtObservacionMuerte.setVisible(chkMuerte.isSelected());
            txtObservacionMuerte.setText(modelo.getDescripcionMuerte());
            cbTipoVenta.setSelectedItem(Utilidades.CapitaliceTexto(modelo.getTipoVenta()));
            txtPrecioVenta.setText(modelo.getPrecioVenta());
            txtPesoCanal.setText(modelo.getPesoCanal());

            if (chkAdoptivo.isSelected()) {
                txtNumeroMamaAdoptiva.setText(modelo.getNumeroMamaAdoptiva());
            }

            boolean mostrar = modelo.getNumeroDescendiente().length() > 0;
            lblNumeroDescendiente.setVisible(mostrar);
            sepNumeroDescendiente.setVisible(mostrar);
            txtNumeroDescendiente.setVisible(mostrar);

            txtNumeroDescendiente.setText(modelo.getNumeroDescendiente().equals("null") ? "" : modelo.getNumeroDescendiente());

            try {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha = formato.parse(modelo.getFechaNacimiento());
                jdFechaNacimiento.setDate(fecha);

                if (modelo.getFechaNovilla().equals(FECHA_POR_DEFECTO) || modelo.getFechaNovilla().equalsIgnoreCase(NULL)) {
                    jdFechaDeNovilla.setCalendar(Calendar.getInstance());
                } else {
                    fecha = formato.parse(modelo.getFechaNovilla());
                    jdFechaDeNovilla.setDate(fecha);
                }

                if (modelo.getFechaDestete().equals(FECHA_POR_DEFECTO)) {
                    chkDestete.setSelected(false);
                    lblFechaDestete.setVisible(false);
                    jdFechaDestete.setVisible(false);
                    txtPesoDestete.setVisible(false);
                } else {
                    chkDestete.setSelected(true);
                    lblFechaDestete.setVisible(true);
                    jdFechaDestete.setVisible(true);
                    txtPesoDestete.setVisible(true);
                    txtPesoDestete.setText(modelo.getPesoDestete());
                    fecha = formato.parse(modelo.getFechaDestete());
                    jdFechaDestete.setDate(fecha);
                }

                if (modelo.getFechaMuerte().equals(FECHA_POR_DEFECTO)) {
                    lblFechaMuerte.setVisible(false);
                    jdFechaMuerte.setVisible(false);
                } else {
                    chkMuerte.setSelected(true);
                    lblFechaMuerte.setVisible(true);
                    jdFechaMuerte.setVisible(true);
                    fecha = formato.parse(modelo.getFechaMuerte());
                    jdFechaMuerte.setDate(fecha);
                }

                if (muerte) {
                    chkMuerte.setSelected(muerte);
                    casoMuerte();
                    panelInfoMuerte.setVisible(muerte);
                }

                if (venta) {
                    casoVenta();
                    panelInfoVenta.setVisible(venta);
                    fecha = formato.parse(modelo.getFechaVenta());
                    jdFechaVenta.setDate(fecha);
                    Double precioVenta = Double.parseDouble(modelo.getPrecioVenta());
                    Double pesoCanal = Double.parseDouble(!modelo.getPesoCanal().equals("null") ? modelo.getPesoCanal() : "0");
                    setCalculosVenta();
                }
            } catch (ParseException pe) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error tratando de obtener las fechas\nDetalle:\n." + pe.getMessage());
            }

            Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_BUSCAR, controles);
            Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_BUSCAR, botones);

            calcularPesoEnLibras();
//</editor-fold>
        } else if (objeto.getOpcion() == 3) {// SE LLAMA LA VISTA TIPOS DE ANIMALES DESDE LA VISTA ANIMALES 
            cbFinca.setSelectedItem(retorno.get("DESCRIPCION"));
            txtCodigoFinca.setText(retorno.get("ID"));
            cargarComboTipoAnimales();
            cargarComboPropietarios();
        }
    }

    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (cbTiposDeAnimales.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe Seleccionar el tipo de animal a crear.");
            return;
        }

        if (cbGrupos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un grupo para el animal a crear.");
            return;
        }

        if (cbPropietario.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un propietario para cargar los hierros asociados a este.");
            return;
        }

        if (cbHierros.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un hierro para el animal a crear.");
            return;
        }

        if (txtNumero.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar el número con el que se identificara el animal.");
            txtNumero.requestFocusInWindow();
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

        if (chkVenta.isSelected()) {
            if (jdFechaVenta.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de venta del animal.");
                jdFechaVenta.requestFocusInWindow();
                return;
            }

            if (txtPrecioVenta.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe especificar el precio de venta del animal.");
                txtPrecioVenta.requestFocusInWindow();
                return;
            }

            if (cbTipoVenta.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione el tipo de venta del animal a crear.");
                cbTipoVenta.requestFocusInWindow();
                return;
            }

            if (cbTipoVenta.getSelectedIndex() == 2) {
                if (txtPesoCanal.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe especificar el peso de canal para los datos de la venta.");
                    txtPesoCanal.requestFocusInWindow();
                    return;
                }
            }
        }

        if (chkMuerte.isSelected()) {
            if (jdFechaMuerte.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de muerte del animal.");
                jdFechaMuerte.requestFocusInWindow();
                return;
            }
        }

        boolean mostrar = txtNumeroMama.getText().trim().equals(txtNumero.getText().trim());
        lblNumeroDescendiente.setVisible(mostrar);
        sepNumeroDescendiente.setVisible(mostrar);
        txtNumeroDescendiente.setVisible(mostrar);
        if (!mostrar) {
            if (editar == Estado.GUARDAR) {
                if (verificarNroAnimal()) {
                    JOptionPane.showMessageDialog(null, "El número " + txtNumero.getText() + " pertenece a otro animal.\n");
                    return;
                }
            }
        }
//</editor-fold>

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String capado = chkCapado.isSelected() ? "Si" : "No";
        Calendar fechaDestete = Calendar.getInstance();

        int indiceGrupo = cbGrupos.getSelectedIndex();
        modeloTraslado.setId("0");
        modeloTraslado.setIdFinca(txtCodigoFinca.getText());
        modeloTraslado.setFecha("NOW()");
        modeloTraslado.setEstado("Activo");
        modeloTraslado.setFechaTraslado("NOW()");
        modeloTraslado.setIdGrupo(grupos.get(indiceGrupo).get("id"));
        modeloTraslado.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modeloTraslado.setMotivo("CREACIÓN DEL ANIMAL");
        modeloTraslado.setIdAnimal("(SELECT id FROM animales WHERE numero='" + txtNumero.getText().trim() + "' "
                + (txtNumeroDescendiente.getText().length() == 0 ? "" : "AND numero_descendiente=" + txtNumeroDescendiente.getText())
                + ")");

        //<editor-fold defaultstate="collapsed" desc="ESTABLECIENDO LOS DATOS DEL MODELO A GUARDAR">
        
        String codigoAnimal = (editar == Estado.ACTUALIZAR) ? txtCodigoAnimal.getText() : getIdPesaje();
        
        modelo.setDescornada(chkDescornada.isSelected() ? "1" : "0");
        modelo.setImplante(chkImplante.isSelected() ? "1" : "0");
        modelo.setHierroFisico(chkHierro.isSelected() ? "1" : "0");
        modelo.setMuerte(chkMuerte.isSelected() ? "1" : "0");
        modelo.setVenta(chkVenta.isSelected() ? "1" : "0");
        modelo.setModeloTraslado(modeloTraslado);
        modelo.setId(codigoAnimal);
        modelo.setFecha("NOW()");
        modelo.setDescHierro(cbHierros.getSelectedItem().toString());
        modelo.setHierro(hierros.get(cbHierros.getSelectedIndex()).get("id"));
        modelo.setGrupo(grupos.get(indiceGrupo).get("id"));
        modelo.setDescGrupo(cbGrupos.getSelectedItem().toString());
        modelo.setIdTipoAnimal(tipoAnimales.get(cbTiposDeAnimales.getSelectedIndex()).get("id"));
        modelo.setCalificacion("" + slCalificacion.getValue());
        modelo.setCapado(capado);
        modelo.setDescTipoAnimal(cbTiposDeAnimales.getSelectedItem().toString());
        modelo.setGenero(cbGenero.getSelectedItem().toString().toLowerCase());
        modelo.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setNotas(txtNotas.getText().trim());
        modelo.setNumero(txtNumero.getText().trim());
        modelo.setNumeroMama(txtNumeroMama.getText().trim());
        modelo.setPeso(txtPesoOculto.getText().replace(".", "").replace(",", "."));
        Calendar fechaNacimiento = jdFechaNacimiento.getCalendar();
        modelo.setFechaNacimiento(sdf.format(fechaNacimiento.getTime()));

        if (chkNovilla.isSelected()) {
            Calendar fechaDeNovilla = jdFechaDeNovilla.getCalendar();
            modelo.setFechaNovilla(
                    fechaDeNovilla == null
                    ? FECHA_POR_DEFECTO
                    : sdf.format(fechaDeNovilla.getTime())
            );
        } else {
            modelo.setFechaNovilla(FECHA_POR_DEFECTO);
        }

        modelo.setNumeroDescendiente(txtNumeroDescendiente.getText().trim().length() == 0 ? "0" : txtNumeroDescendiente.getText().trim());
        modelo.setEstadoDescendiente(txtNumeroMama.getText().trim().equals(txtNumero.getText().trim()) ? "0" : "1");
        modelo.setDescripcionMuerte(txtObservacionMuerte.getText());

        if (chkMuerte.isSelected()) {
            Calendar fechaMuerte = jdFechaMuerte.getCalendar();
            modelo.setFechaMuerte(sdf.format(fechaMuerte.getTime()));
        } else {
            modelo.setFechaMuerte(FECHA_POR_DEFECTO);
        }

        if (chkVenta.isSelected()) {
            Calendar fechaVenta = jdFechaVenta.getCalendar();
            modelo.setFechaVenta(sdf.format(fechaVenta.getTime()));
            modelo.setPrecioVenta(txtPrecioVenta.getText().replace(".", "").replace(",", "."));
            String pesoCanal = txtPesoCanal.getText();
            pesoCanal = pesoCanal.isEmpty() ? "0" : pesoCanal;
            modelo.setPesoCanal(txtPesoCanal.getText().replace(".", "").replace(",", "."));
            modelo.setTipoVenta("'" + cbTipoVenta.getSelectedItem().toString().toLowerCase() + "'");
        } else {
            modelo.setPrecioVenta("NULL");
            modelo.setPesoCanal("NULL");
            modelo.setTipoVenta("NULL");
            modelo.setFechaVenta(FECHA_POR_DEFECTO);
        }
        //</editor-fold>

        if (chkAdoptivo.isSelected()) {
            modelo.setNumeroMamaAdoptiva("'" + txtNumeroMamaAdoptiva.getText() + "'");
        } else {
            modelo.setNumeroMamaAdoptiva("NULL");
        }

        if (chkDestete.isSelected()) {
            fechaDestete = jdFechaDestete.getCalendar();
            modelo.setFechaDestete(sdf.format(fechaDestete.getTime()));
            modelo.setPesoDestete(txtPesoDestete.getText().replace(".", "").replace(",", "."));
        } else {
            modelo.setFechaDestete(FECHA_POR_DEFECTO);
            modelo.setPesoDestete("NULL");
        }

        int retorno = Retorno.DEFECTO;

        if (editar == Estado.GUARDAR) {
            retorno = control.Guardar(modelo);
        } else {
            retorno = control.Actualizar(modelo);
        }

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro " + (editar == Estado.GUARDAR ? "guardado" : "actualizado") + " satisfactoriamente.";
                Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_GUARDAR, controles);
                Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_GUARDAR, botones);
                editar = Estado.GUARDAR;
                jdFechaDestete.setCalendar(fechaDestete);
                break;
            case Retorno.ERROR:
                mensaje = "El registro no pudo ser " + (editar == Estado.GUARDAR ? "guardado" : "actualizado") + ".";
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

    private void Modificar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_MODIFICAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_MODIFICAR, botones);
        cbGrupos.setEnabled(false);
        editar = Estado.ACTUALIZAR;
    }

    private void Descartar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_DESCARTAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_DESCARTAR, botones);
        editar = Estado.GUARDAR;
    }

    private void Consultar() {
        //new ventanaBusquedaPaciente(1, "IDENTIFICACION:-:NOMBRE", estadoch, this);
        objetoBusqueda = new ModeloGestorBusqueda(this, "BUSQUEDA_ANIMALES", 0);
        VistaBusqueda vistaBusqueda = new VistaBusqueda(objetoBusqueda);
        btnConsultar.setEnabled(false);
    }

    private void Eliminar() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar este Registro?");
        if (respuesta == JOptionPane.YES_OPTION) {
            int retorno = control.Eliminar(modelo);

            String mensaje = "";
            switch (retorno) {
                case Retorno.EXITO:
                    mensaje = "Registro eliminado satisfactoriamente.";
                    Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_ELIMINAR, controles);
                    Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_ELIMINAR, botones);
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
            }

            JOptionPane.showMessageDialog(this, mensaje);
        }
    }

    public void RetornoVistaGeneral(ModeloVentanaGeneral modeloVentanaGeneral, ModeloTipoAnimales modelo) {

    }

    private String convertirAKilogramos(double pesoEnLibras) {
        Double resultado = pesoEnLibras / Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        txtPesoOculto.setText("" + resultadoRedondeado);

        return "" + resultadoRedondeado;
    }

    private String convertirALibras(double pesoEnKilos) {
        Double resultado = pesoEnKilos * Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        txtPesoOculto.setText("" + Math.round(pesoEnKilos));

        return "" + resultadoRedondeado;
    }

    private String calcularPrecioDeVenta(double valorDeVenta) {
        if (txtPesoOculto.getText().length() == 0) {
            return "";
        } else {
            long peso = Integer.parseInt(txtPesoOculto.getText());
            long precioDeVenta = (long) (peso * valorDeVenta);
            return "Valor de Venta $ " + Utilidades.MascaraMonedaConDecimales("" + precioDeVenta);
        }
    }

    private String calcularPorcentajeDeCanal(double pesoCanal) {
        if (txtPesoOculto.getText().length() == 0) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat("#.00");

            double peso = Integer.parseInt(txtPesoOculto.getText());
            double porcentajeCanal = pesoCanal / peso * 100;
            return "Porcentaje de canal: " + df.format(porcentajeCanal) + "%";
        }
    }

    private void casoMuerte() {
        if (chkMuerte.isSelected()) {
            EstadoVenta(false);
            EstadoMuerte(true);
            chkVenta.setEnabled(false);
        } else {
            chkVenta.setEnabled(true);
        }
//                boolean chequeado = chkMuerte.isSelected();
//                chkVenta.setEnabled(!chequeado);
//                chkVenta.setSelected(false);
//                EstadoVenta(false);
//                EstadoMuerte(chequeado);
    }

    private void casoVenta() {
        if (chkVenta.isSelected()) {
            EstadoVenta(true);
            EstadoMuerte(false);
            chkMuerte.setEnabled(false);
        } else {
            chkMuerte.setEnabled(true);
        }
//        if (chkVenta.isEnabled()) {
//            if (editar == Estado.ACTUALIZAR) {
//                boolean chequeado = chkVenta.isSelected();
//                chkMuerte.setEnabled(!chequeado);
//                chkMuerte.setSelected(false);
//                EstadoMuerte(false);
//                EstadoVenta(chequeado);
//            }
//        }
    }

    private void setCalculosVenta() {
        String pv = txtPrecioVenta.getText().indexOf(".") > -1 ? txtPrecioVenta.getText().replace(".", "") : txtPrecioVenta.getText();
        String pc = txtPesoCanal.getText().indexOf(".") > -1 ? txtPesoCanal.getText().replace(".", "") : txtPesoCanal.getText();
        txtCalculos.setText(
                "<html>"
                + "<p>"
                + (txtPrecioVenta.getText().length() == 0 ? "" : calcularPrecioDeVenta(Double.parseDouble(pv)) + "<br>")
                + (txtPesoCanal.getText().length() == 0 ? "" : calcularPorcentajeDeCanal(Double.parseDouble(pc)))
                + "</p>"
                + "</html>"
        );
    }

    private String getIdPesaje() {
        String consulta = consultas.get("GET_MAXIMO_ID_PESAJE_ANIMAL");
        select = controlGral.GetComboBox(consulta);
        for (Map<String, String> lista : select) {
            consulta = lista.get("IDPESAJE");
        }
        return consulta;
    }

}
