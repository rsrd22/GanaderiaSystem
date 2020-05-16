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
    private List<Map<String, String>> grupos;
    private List<Map<String, String>> tipoAnimales;
    private List<Map<String, String>> hierros;
    private List<Map<String, String>> fincas;
    private List<Map<String, String>> propietarios;
    private ControlGeneral controlGral;
    private ModeloTraslado modeloTraslado;
    private ControlTraslado controlTraslado;

    /**
     * Creates new form VistaAnimales
     */
    public VistaAnimales() {
        initComponents();
        iniciarComponentes();
        lblAdoptiva.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jSeparatorAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);
        lblFechaDestete.setVisible(false);
        jdFechaMuerte.setVisible(false);
        lblFechaMuerte.setVisible(false);
        lblCausaMuerte.setVisible(false);
        lblFechaVenta.setVisible(false);
        lblPesoCanal.setVisible(false);
        txtPesoCanal.setVisible(false);
        sepPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);

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
        cargarComboPropietarios();
        controles.habilitarControles();
        txtObservacionMuerte.setVisible(false);
        ScrollCausaMuerte.setVisible(false);
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    public VistaAnimales(ModeloVentanaGeneral modeloVista) {
        initComponents();
        iniciarComponentes();
        lblAdoptiva.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jSeparatorAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);
        lblFechaDestete.setVisible(false);
        jdFechaMuerte.setVisible(false);
        lblFechaMuerte.setVisible(false);
        lblCausaMuerte.setVisible(false);
        lblFechaVenta.setVisible(false);
        lblPesoCanal.setVisible(false);
        txtPesoCanal.setVisible(false);
        sepPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);

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
        cargarComboPropietarios();
        controles.habilitarControles();
        txtObservacionMuerte.setVisible(false);
        ScrollCausaMuerte.setVisible(false);
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    private void cargarComboFincas() {
        String consulta = consultas.get("CARGAR_COMBO_FINCAS");
        fincas = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbFinca, fincas, "descripcion");
    }

    private void cargarComboPropietarios() {
        String consulta = consultas.get("CARGAR_COMBO_PROPIETARIOS");
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

        control = new Control(true, txtPesoKg);
        controles.addControl(control);

        control = new Control(true, txtObservacionMuerte);
        controles.addControl(control);

        control = new Control(true, cbTipoVenta);
        controles.addControl(control);

        control = new Control(true, txtPrecioVenta);
        controles.addControl(control);

        control = new Control(true, txtPesoCanal);
        controles.addControl(control);

        control = new Control(true, cbPropietario);
        controles.addControl(control);

        control = new Control(true, cbHierros);
        controles.addControl(control);

        control = new Control(true, txtNumero);
        controles.addControl(control);

        control = new Control(true, txtNumeroMama);
        controles.addControl(control);

        control = new Control(true, txtNumeroMamaAdoptiva);
        controles.addControl(control);

        control = new Control(true, chkAdoptivo);
        controles.addControl(control);

        control = new Control(true, cbGenero);
        controles.addControl(control);

        control = new Control(true, cbGrupos);
        controles.addControl(control);

        control = new Control(true, txtPeso);
        controles.addControl(control);

        control = new Control(true, slCalificacion);
        controles.addControl(control);

        control = new Control(true, txtNotas);
        controles.addControl(control);

        control = new Control(true, chkMuerte);
        controles.addControl(control);

        control = new Control(true, jdFechaMuerte);
        controles.addControl(control);

        control = new Control(true, jdFechaNacimiento);
        controles.addControl(control);

        control = new Control(true, chkDestete);
        controles.addControl(control);

        control = new Control(true, jdFechaDestete);
        controles.addControl(control);

        control = new Control(true, chkVenta);
        controles.addControl(control);

        control = new Control(true, jdFechaVenta);
        controles.addControl(control);

        control = new Control(true, chkCapado);
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
        lblCausaMuerte = new javax.swing.JLabel();
        lbltitle10 = new javax.swing.JLabel();
        lbltitle11 = new javax.swing.JLabel();
        lblFechaMuerte = new javax.swing.JLabel();
        chkCapado = new javax.swing.JCheckBox();
        lbltitle13 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
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
        jdFechaMuerte = new com.toedter.calendar.JDateChooser();
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
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
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

        setBackground(new java.awt.Color(255, 255, 255));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbltitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle4.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle4.setText("Tipo de animales");
        add(lbltitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

        lblNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        lblNumeroDescendiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumeroDescendiente.setText(" - ");
        add(lblNumeroDescendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, 40));

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
        add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 110, 30));

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 110, 10));

        lblAdoptiva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        lblAdoptiva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAdoptiva.setText("Número madre adoptiva");
        add(lblAdoptiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 150, -1));

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
        add(txtNumeroMama, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, 30));

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 120, 10));

        lblFechaDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDestete.setText("Fecha de destete");
        add(lblFechaDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 130, -1));

        cbGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenero.setForeground(new java.awt.Color(59, 123, 50));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });
        add(cbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 130, 30));

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Calificación");
        add(lbltitle8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 110, -1));

        txtNotas.setColumns(20);
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 210, 80));

        lblCausaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCausaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblCausaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCausaMuerte.setText("Causa de muerte");
        add(lblCausaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 110, -1));

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle10.setText("Genero");
        add(lbltitle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, -1));

        lbltitle11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle11.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle11.setText("Fecha de nacimiento");
        add(lbltitle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 130, -1));

        lblFechaMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaMuerte.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaMuerte.setText("Fecha de muerte");
        add(lblFechaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 130, -1));

        chkCapado.setBackground(new java.awt.Color(255, 255, 255));
        chkCapado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkCapado.setForeground(new java.awt.Color(59, 123, 50));
        chkCapado.setText("Capado");
        add(chkCapado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, 30));

        lbltitle13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle13.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle13.setText("- ");
        add(lbltitle13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 10, -1));

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
        add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 90, 30));

        jSeparator12.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 90, 10));

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
        add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 60, 64));

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
        add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 500, 60, 64));

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
        add(btnDescartar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 60, 64));

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
        add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 500, 60, 64));

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
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 60, 64));

        slCalificacion.setBackground(new java.awt.Color(255, 255, 255));
        slCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        slCalificacion.setMajorTickSpacing(1);
        slCalificacion.setMaximum(5);
        slCalificacion.setMinimum(1);
        slCalificacion.setPaintLabels(true);
        slCalificacion.setPaintTicks(true);
        slCalificacion.setValue(3);
        slCalificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(slCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 210, -1));

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Peso (Kilos)");
        add(lbltitle14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 90, -1));

        chkDestete.setBackground(new java.awt.Color(255, 255, 255));
        chkDestete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDestete.setForeground(new java.awt.Color(59, 123, 50));
        chkDestete.setText("Destete");
        chkDestete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDesteteStateChanged(evt);
            }
        });
        add(chkDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 70, 30));

        chkMuerte.setBackground(new java.awt.Color(255, 255, 255));
        chkMuerte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkMuerte.setForeground(new java.awt.Color(59, 123, 50));
        chkMuerte.setText("Muerte");
        chkMuerte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMuerteActionPerformed(evt);
            }
        });
        add(chkMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        chkVenta.setBackground(new java.awt.Color(255, 255, 255));
        chkVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkVenta.setForeground(new java.awt.Color(59, 123, 50));
        chkVenta.setText("Venta");
        chkVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkVentaActionPerformed(evt);
            }
        });
        add(chkVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, -1, -1));

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
        add(txtNumeroMamaAdoptiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 120, 30));

        jSeparatorAdoptiva.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparatorAdoptiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 120, 10));

        lbltitle7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle7.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle7.setText("Número de la madre");
        add(lbltitle7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 150, -1));

        chkAdoptivo.setBackground(new java.awt.Color(255, 255, 255));
        chkAdoptivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkAdoptivo.setForeground(new java.awt.Color(59, 123, 50));
        chkAdoptivo.setText("Madre adoptiva");
        chkAdoptivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAdoptivoMouseClicked(evt);
            }
        });
        add(chkAdoptivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lbltitle12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle12.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle12.setText("Grupos");
        add(lbltitle12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 70, -1));

        cbGrupos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGrupos.setForeground(new java.awt.Color(59, 123, 50));
        cbGrupos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGruposActionPerformed(evt);
            }
        });
        add(cbGrupos, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 160, 30));

        lbltitle15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle15.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle15.setText("Hierros");
        add(lbltitle15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 50, -1));

        cbHierros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbHierros.setForeground(new java.awt.Color(59, 123, 50));
        cbHierros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbHierros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHierrosActionPerformed(evt);
            }
        });
        add(cbHierros, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 150, 30));
        add(jdFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 120, 30));
        add(jdFechaDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 120, 30));
        add(jdFechaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 130, 30));

        lbltitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle6.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle6.setText("Finca");
        add(lbltitle6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, -1));

        cbTiposDeAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTiposDeAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTiposDeAnimales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTiposDeAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTiposDeAnimalesActionPerformed(evt);
            }
        });
        add(cbTiposDeAnimales, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 220, 30));

        lbltitle16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle16.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle16.setText("Número");
        add(lbltitle16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 110, -1));

        txtNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroDescendiente.setBorder(null);
        txtNumeroDescendiente.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setSelectionColor(new java.awt.Color(59, 123, 50));
        add(txtNumeroDescendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 40, 30));

        sepNumeroDescendiente.setBackground(new java.awt.Color(59, 123, 50));
        add(sepNumeroDescendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 40, 10));

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        add(cbFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 220, 30));

        lbltitle17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle17.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle17.setText("Propietarios");
        add(lbltitle17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 340, 90, -1));

        cbPropietario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbPropietario.setForeground(new java.awt.Color(59, 123, 50));
        cbPropietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPropietarioActionPerformed(evt);
            }
        });
        add(cbPropietario, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 320, 30));

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
        add(txtPesoKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 420, 90, 30));

        jSeparator13.setBackground(new java.awt.Color(59, 123, 50));
        add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 90, 10));

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Peso (Libras)");
        add(lbltitle18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 90, -1));

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        add(ScrollCausaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 210, 60));

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Notas");
        add(lbltitle19, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 110, -1));

        panelInfoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 102, 0))); // NOI18N
        panelInfoVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFechaVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaVenta.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaVenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaVenta.setText("Fecha de venta");
        panelInfoVenta.add(lblFechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 28, 130, -1));
        panelInfoVenta.add(jdFechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 48, 130, 30));

        lbltitle20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle20.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle20.setText("Precio de venta");
        panelInfoVenta.add(lbltitle20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 110, -1));

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
        panelInfoVenta.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, 30));

        jSeparator14.setBackground(new java.awt.Color(59, 123, 50));
        panelInfoVenta.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 110, 10));

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
        panelInfoVenta.add(txtPesoCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 110, 30));

        lblPesoCanal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoCanal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoCanal.setText("Peso del canal");
        panelInfoVenta.add(lblPesoCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 110, -1));

        sepPesoCanal.setBackground(new java.awt.Color(59, 123, 50));
        panelInfoVenta.add(sepPesoCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 110, 10));

        lbltitle21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle21.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle21.setText("Tipo de venta");
        panelInfoVenta.add(lbltitle21, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 28, 102, -1));

        cbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoVenta.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Particular", "Matadero" }));
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });
        panelInfoVenta.add(cbTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 48, 150, 30));

        txtCalculos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCalculos.setForeground(new java.awt.Color(59, 123, 50));
        txtCalculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelInfoVenta.add(txtCalculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 280, 50));

        add(panelInfoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 130, 320, 220));
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
        jdFechaDestete.setCalendar(Calendar.getInstance());
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
        boolean mostrar = txtNumeroMama.getText().trim().equals(txtNumero.getText().trim());
        lblNumeroDescendiente.setVisible(mostrar);
        sepNumeroDescendiente.setVisible(mostrar);
        txtNumeroDescendiente.setVisible(mostrar);
    }//GEN-LAST:event_txtNumeroFocusLost

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
        setCalculosVenta();
    }//GEN-LAST:event_txtPesoKgKeyReleased

    private void chkMuerteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMuerteActionPerformed
        casoMuerte();
    }//GEN-LAST:event_chkMuerteActionPerformed

    private void chkVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkVentaActionPerformed
        casoVenta();
    }//GEN-LAST:event_chkVentaActionPerformed

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
    private javax.swing.JCheckBox chkDestete;
    private javax.swing.JCheckBox chkMuerte;
    private javax.swing.JCheckBox chkVenta;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparatorAdoptiva;
    private com.toedter.calendar.JDateChooser jdFechaDestete;
    private com.toedter.calendar.JDateChooser jdFechaMuerte;
    private com.toedter.calendar.JDateChooser jdFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdFechaVenta;
    private javax.swing.JLabel lblAdoptiva;
    private javax.swing.JLabel lblCausaMuerte;
    private javax.swing.JLabel lblFechaDestete;
    private javax.swing.JLabel lblFechaMuerte;
    private javax.swing.JLabel lblFechaVenta;
    private javax.swing.JLabel lblNumeroDescendiente;
    private javax.swing.JLabel lblPesoCanal;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle11;
    private javax.swing.JLabel lbltitle12;
    private javax.swing.JLabel lbltitle13;
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
    private javax.swing.JPanel panelInfoVenta;
    private javax.swing.JSeparator sepNumeroDescendiente;
    private javax.swing.JSeparator sepPesoCanal;
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

                if (modelo.getFechaDestete().equals(FECHA_POR_DEFECTO)) {
                    chkDestete.setSelected(false);
                    lblFechaDestete.setVisible(false);
                    jdFechaDestete.setVisible(false);
                } else {
                    chkDestete.setSelected(true);
                    lblFechaDestete.setVisible(true);
                    jdFechaDestete.setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Debe el número con el que se identificara el animal.");
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
//</editor-fold>

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String codigoAnimal = (editar == Estado.ACTUALIZAR) ? txtCodigoAnimal.getText() : "0";
        String capado = chkCapado.isSelected() ? "Si" : "No";

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
            Calendar fechaDestete = jdFechaDestete.getCalendar();
            modelo.setFechaDestete(sdf.format(fechaDestete.getTime()));
        } else {
            modelo.setFechaDestete(FECHA_POR_DEFECTO);
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
//        cbGrupos.setEnabled(false);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        boolean chequeado = chkMuerte.isSelected();
        chkVenta.setEnabled(!chequeado);
        chkVenta.setSelected(false);
        EstadoVenta(false);
        EstadoMuerte(chequeado);
    }

    private void casoVenta() {
        boolean chequeado = chkVenta.isSelected();
        chkMuerte.setEnabled(!chequeado);
        chkMuerte.setSelected(false);
        EstadoMuerte(false);
        EstadoVenta(chequeado);
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
}
