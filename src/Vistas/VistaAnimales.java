/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import AjustarControles.AjustarControles;
import Busqueda.VistaBusqueda;
import Control.ControlAnimales;
import Tablas.ColorIn;
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
import java.awt.Color;
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
    public int idModulo = 11;

    /**
     * Creates new form VistaAnimales
     */
    public VistaAnimales() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        iniciarComponentes();
        
        cbFinca.setRenderer(new ColorIn());
        cbFinca.setBackground(Color.YELLOW);

      
        txtPesoDestete.setVisible(false);
        jdFechaDeNovilla.setVisible(false);

        lblNovilla.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);
        txtPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
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
        
        cbFinca.setRenderer(new ColorIn());
        cbFinca.setBackground(Color.cyan);

        txtPesoDestete.setVisible(false);
        jdFechaDeNovilla.setVisible(false);

        lblNovilla.setVisible(false);
        txtNumeroMamaAdoptiva.setVisible(false);
        jdFechaDestete.setVisible(false);

        txtPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        txtNumeroMama = new javax.swing.JTextField();
        cbGenero = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        chkCapado = new javax.swing.JCheckBox();
        txtPeso = new javax.swing.JTextField();
        slCalificacion = new javax.swing.JSlider();
        chkDestete = new javax.swing.JCheckBox();
        chkMuerte = new javax.swing.JCheckBox();
        chkVenta = new javax.swing.JCheckBox();
        txtNumeroMamaAdoptiva = new javax.swing.JTextField();
        chkAdoptivo = new javax.swing.JCheckBox();
        cbGrupos = new javax.swing.JComboBox();
        cbHierros = new javax.swing.JComboBox();
        jdFechaNacimiento = new com.toedter.calendar.JDateChooser();
        cbTiposDeAnimales = new javax.swing.JComboBox();
        cbFinca = new javax.swing.JComboBox();
        cbPropietario = new javax.swing.JComboBox();
        txtPesoKg = new javax.swing.JTextField();
        panelInfoVenta = new javax.swing.JPanel();
        jdFechaVenta = new com.toedter.calendar.JDateChooser();
        txtPrecioVenta = new javax.swing.JTextField();
        txtPesoCanal = new javax.swing.JTextField();
        cbTipoVenta = new javax.swing.JComboBox();
        txtCalculos = new javax.swing.JLabel();
        txtPorcentajeCanal = new javax.swing.JTextField();
        txtValorVenta = new javax.swing.JTextField();
        panelInfoMuerte = new javax.swing.JPanel();
        jdFechaMuerte = new com.toedter.calendar.JDateChooser();
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jdFechaDeNovilla = new com.toedter.calendar.JDateChooser();
        chkImplante = new javax.swing.JCheckBox();
        chkHierro = new javax.swing.JCheckBox();
        chkDescornada = new javax.swing.JCheckBox();
        chkNovilla = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        txtNumeroDescendiente = new javax.swing.JTextField();
        lblNumeroDescendiente = new javax.swing.JLabel();
        lblNovilla = new javax.swing.JLabel();
        lblAdoptiva = new javax.swing.JLabel();
        lblHierro = new javax.swing.JLabel();
        lblDescornado = new javax.swing.JLabel();
        lblImplante = new javax.swing.JLabel();
        lblCapado = new javax.swing.JLabel();
        lblDestete = new javax.swing.JLabel();
        lblVenta = new javax.swing.JLabel();
        lblMuerte = new javax.swing.JLabel();
        pnlDestete = new javax.swing.JPanel();
        jdFechaDestete = new com.toedter.calendar.JDateChooser();
        txtPesoDestete = new javax.swing.JTextField();
        separador = new javax.swing.JSeparator();
        separador1 = new javax.swing.JSeparator();
        separador2 = new javax.swing.JSeparator();
        separador3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setAutoscrolls(true);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNumeroMama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMama.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMama.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Número de la madre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
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
        jPanel3.add(txtNumeroMama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 160, 50));

        cbGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenero.setForeground(new java.awt.Color(59, 123, 50));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbGenero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });
        jPanel3.add(cbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 113, 45));

        txtNotas.setColumns(20);
        txtNotas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 660, 120));

        chkCapado.setBackground(new java.awt.Color(255, 255, 255));
        chkCapado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkCapado.setForeground(new java.awt.Color(59, 123, 50));
        chkCapado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkCapado.setBorderPainted(true);
        chkCapado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkCapado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkCapado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCapadoStateChanged(evt);
            }
        });
        jPanel3.add(chkCapado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 30, 30));

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(59, 123, 50));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPeso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso(lbs)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPeso.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPeso.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });
        jPanel3.add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 195, 180, 50));

        slCalificacion.setBackground(new java.awt.Color(255, 255, 255));
        slCalificacion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        slCalificacion.setForeground(new java.awt.Color(59, 123, 50));
        slCalificacion.setMajorTickSpacing(1);
        slCalificacion.setMaximum(5);
        slCalificacion.setMinimum(1);
        slCalificacion.setPaintLabels(true);
        slCalificacion.setPaintTicks(true);
        slCalificacion.setValue(3);
        slCalificacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Calificación", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(59, 123, 50))); // NOI18N
        slCalificacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(slCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 850, -1));

        chkDestete.setBackground(new java.awt.Color(255, 255, 255));
        chkDestete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDestete.setForeground(new java.awt.Color(59, 123, 50));
        chkDestete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        chkDestete.setBorderPainted(true);
        chkDestete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDestete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkDestete.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkDesteteStateChanged(evt);
            }
        });
        jPanel3.add(chkDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 540, 30, 30));

        chkMuerte.setBackground(new java.awt.Color(255, 255, 255));
        chkMuerte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkMuerte.setForeground(new java.awt.Color(59, 123, 50));
        chkMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        chkMuerte.setBorderPainted(true);
        chkMuerte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkMuerte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkMuerte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkMuerteStateChanged(evt);
            }
        });
        jPanel3.add(chkMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 830, 30, 30));

        chkVenta.setBackground(new java.awt.Color(255, 255, 255));
        chkVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkVenta.setForeground(new java.awt.Color(59, 123, 50));
        chkVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        chkVenta.setBorderPainted(true);
        chkVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkVenta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentaStateChanged(evt);
            }
        });
        jPanel3.add(chkVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 640, 30, 30));

        txtNumeroMamaAdoptiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMamaAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMamaAdoptiva.setToolTipText("Número madre adoptiva");
        txtNumeroMamaAdoptiva.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10), new java.awt.Color(59, 123, 50))); // NOI18N
        txtNumeroMamaAdoptiva.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMamaAdoptivaKeyReleased(evt);
            }
        });
        jPanel3.add(txtNumeroMamaAdoptiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 225, 160, 30));

        chkAdoptivo.setBackground(new java.awt.Color(255, 255, 255));
        chkAdoptivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkAdoptivo.setForeground(new java.awt.Color(59, 123, 50));
        chkAdoptivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkAdoptivo.setBorderPainted(true);
        chkAdoptivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAdoptivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAdoptivo.setMaximumSize(new java.awt.Dimension(25, 25));
        chkAdoptivo.setPreferredSize(new java.awt.Dimension(25, 25));
        chkAdoptivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAdoptivoMouseClicked(evt);
            }
        });
        jPanel3.add(chkAdoptivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 195, 30, 30));

        cbGrupos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGrupos.setForeground(new java.awt.Color(59, 123, 50));
        cbGrupos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbGrupos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGruposActionPerformed(evt);
            }
        });
        jPanel3.add(cbGrupos, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 113, 45));

        cbHierros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbHierros.setForeground(new java.awt.Color(59, 123, 50));
        cbHierros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbHierros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hierro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbHierros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHierrosActionPerformed(evt);
            }
        });
        jPanel3.add(cbHierros, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 160, 45));

        jdFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaNacimiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha de nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jdFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        jPanel3.add(jdFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 160, 50));

        cbTiposDeAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTiposDeAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTiposDeAnimales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTiposDeAnimales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de animales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTiposDeAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTiposDeAnimalesActionPerformed(evt);
            }
        });
        jPanel3.add(cbTiposDeAnimales, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 230, 45));

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbFinca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Finca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbFinca.setDoubleBuffered(true);
        cbFinca.setEditor(null);
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        jPanel3.add(cbFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 150, 45));

        cbPropietario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbPropietario.setForeground(new java.awt.Color(59, 123, 50));
        cbPropietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbPropietario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPropietarioActionPerformed(evt);
            }
        });
        jPanel3.add(cbPropietario, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 313, 45));

        txtPesoKg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoKg.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoKg.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoKg.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso(Kg)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPesoKg.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKgKeyReleased(evt);
            }
        });
        jPanel3.add(txtPesoKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 195, 118, 50));

        panelInfoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        panelInfoVenta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdFechaVenta.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Fecha de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        panelInfoVenta.add(jdFechaVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 52));

        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPrecioVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Precio de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPrecioVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaActionPerformed(evt);
            }
        });
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
        });
        panelInfoVenta.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 200, 50));

        txtPesoCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoCanal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso de canal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPesoCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoCanalActionPerformed(evt);
            }
        });
        txtPesoCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoCanalKeyReleased(evt);
            }
        });
        panelInfoVenta.add(txtPesoCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 150, 50));

        cbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoVenta.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Particular", "Matadero" }));
        cbTipoVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });
        panelInfoVenta.add(cbTipoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 180, 52));

        txtCalculos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCalculos.setForeground(new java.awt.Color(59, 123, 50));
        txtCalculos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelInfoVenta.add(txtCalculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 637, 20));

        txtPorcentajeCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPorcentajeCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPorcentajeCanal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "% de canal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPorcentajeCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeCanalKeyReleased(evt);
            }
        });
        panelInfoVenta.add(txtPorcentajeCanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 140, 50));

        txtValorVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtValorVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtValorVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtValorVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Valor de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtValorVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtValorVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtValorVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorVentaKeyReleased(evt);
            }
        });
        panelInfoVenta.add(txtValorVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 190, 50));

        jPanel3.add(panelInfoVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 820, 160));

        panelInfoMuerte.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        panelInfoMuerte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdFechaMuerte.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaMuerte.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Fecha de muerte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        panelInfoMuerte.add(jdFechaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 50));

        ScrollCausaMuerte.setBackground(new java.awt.Color(255, 255, 255));
        ScrollCausaMuerte.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Causa de muerte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setBorder(null);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        panelInfoMuerte.add(ScrollCausaMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 440, 110));

        jPanel3.add(panelInfoMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 860, 820, 150));

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

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 1030, 825, -1));

        jdFechaDeNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jdFechaDeNovilla.setToolTipText("Fecha novilla");
        jdFechaDeNovilla.setDateFormatString("dd/MM/yyyy");
        jPanel3.add(jdFechaDeNovilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 285, 160, 30));

        chkImplante.setBackground(new java.awt.Color(255, 255, 255));
        chkImplante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkImplante.setForeground(new java.awt.Color(59, 123, 50));
        chkImplante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkImplante.setBorderPainted(true);
        chkImplante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkImplante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(chkImplante, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 30, 30));

        chkHierro.setBackground(new java.awt.Color(255, 255, 255));
        chkHierro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkHierro.setForeground(new java.awt.Color(59, 123, 50));
        chkHierro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkHierro.setBorderPainted(true);
        chkHierro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkHierro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(chkHierro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 30, 30));

        chkDescornada.setBackground(new java.awt.Color(255, 255, 255));
        chkDescornada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescornada.setForeground(new java.awt.Color(59, 123, 50));
        chkDescornada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkDescornada.setBorderPainted(true);
        chkDescornada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDescornada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(chkDescornada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 30, 30));

        chkNovilla.setBackground(new java.awt.Color(255, 255, 255));
        chkNovilla.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkNovilla.setForeground(new java.awt.Color(59, 123, 50));
        chkNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkNovilla.setBorderPainted(true);
        chkNovilla.setContentAreaFilled(false);
        chkNovilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkNovilla.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkNovilla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkNovillaStateChanged(evt);
            }
        });
        jPanel3.add(chkNovilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 255, 30, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Número", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, 72, 30));

        txtNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroDescendiente.setBorder(null);
        txtNumeroDescendiente.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setSelectionColor(new java.awt.Color(59, 123, 50));
        jPanel1.add(txtNumeroDescendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 14, 30, 30));

        lblNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        lblNumeroDescendiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumeroDescendiente.setText(" - ");
        jPanel1.add(lblNumeroDescendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 190, 50));

        lblNovilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNovilla.setForeground(new java.awt.Color(59, 123, 50));
        lblNovilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovilla.setText("Novilla");
        lblNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblNovilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 255, 130, 30));

        lblAdoptiva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        lblAdoptiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdoptiva.setText("Madre adoptiva");
        lblAdoptiva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblAdoptiva, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 195, 130, 30));

        lblHierro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHierro.setForeground(new java.awt.Color(59, 123, 50));
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHierro.setText("Hierro");
        lblHierro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblHierro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 130, 30));

        lblDescornado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDescornado.setForeground(new java.awt.Color(59, 123, 50));
        lblDescornado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescornado.setText("Descornado");
        lblDescornado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblDescornado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 130, 30));

        lblImplante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblImplante.setForeground(new java.awt.Color(59, 123, 50));
        lblImplante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImplante.setText("Implante");
        lblImplante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblImplante, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 130, 30));

        lblCapado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCapado.setForeground(new java.awt.Color(59, 123, 50));
        lblCapado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapado.setText("Capado");
        lblCapado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel3.add(lblCapado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 130, 30));

        lblDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblDestete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDestete.setText("Destete");
        lblDestete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        jPanel3.add(lblDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 790, 30));

        lblVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVenta.setForeground(new java.awt.Color(59, 123, 50));
        lblVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVenta.setText("Venta");
        lblVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        jPanel3.add(lblVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 640, 790, 30));

        lblMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMuerte.setForeground(new java.awt.Color(59, 123, 50));
        lblMuerte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuerte.setText("Muerte");
        lblMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        jPanel3.add(lblMuerte, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 830, 790, 30));

        pnlDestete.setBackground(new java.awt.Color(255, 255, 255));
        pnlDestete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        pnlDestete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jdFechaDestete.setBackground(new java.awt.Color(255, 255, 255));
        jdFechaDestete.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Fecha de destete", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jdFechaDestete.setDateFormatString("dd/MM/yyyy");
        pnlDestete.add(jdFechaDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 160, 50));

        txtPesoDestete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoDestete.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso de destete(Kg)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPesoDestete.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoDesteteKeyReleased(evt);
            }
        });
        pnlDestete.add(txtPesoDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 150, 50));

        jPanel3.add(pnlDestete, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 570, 820, 70));

        separador.setBackground(new java.awt.Color(59, 123, 50));
        jPanel3.add(separador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 455, 850, 10));

        separador1.setBackground(new java.awt.Color(59, 123, 50));
        jPanel3.add(separador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 850, 10));

        separador2.setBackground(new java.awt.Color(59, 123, 50));
        jPanel3.add(separador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 850, 10));

        separador3.setBackground(new java.awt.Color(59, 123, 50));
        jPanel3.add(separador3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 850, 5));

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
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
        txtPesoDestete.setVisible(chequeado);
//        jdFechaDestete.setCalendar(Calendar.getInstance());
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
            txtNumeroDescendiente.setVisible(mostrar);
            if (!mostrar) {
                if (editar == Estado.GUARDAR) {
                    if (verificarNroAnimal()) {
                        JOptionPane.showMessageDialog(
                                null, 
                                "El número " + txtNumero.getText() + " pertenece a otro animal.\n"
                                + "Verifique en el listado de animales o si este se encuentra vendido o muerto."
                        );
                    }
                }
            }
        }
    }//GEN-LAST:event_txtNumeroFocusLost

    private boolean verificarNroAnimal() {
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        String[] parametros = new String[]{
            txtNumero.getText().trim(),
            txtCodigoTipoAnimal.getText()
        };
        lista = (ArrayList<ModeloAnimales>) control.ObtenerDatosFiltro(parametros);
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
            lblNovilla.setVisible(mostrar);
            txtNumeroMamaAdoptiva.setVisible(mostrar);
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
        panelInfoVenta.setVisible(chequeado);
    }

    public void EstadoMuerte(boolean chequeado) {
        jdFechaMuerte.setVisible(chequeado);
        jdFechaMuerte.setCalendar(Calendar.getInstance());
        txtObservacionMuerte.setVisible(chequeado);
        ScrollCausaMuerte.setVisible(chequeado);
        panelInfoMuerte.setVisible(chequeado);
    }

    private void cbTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoVentaActionPerformed
        boolean mostrar = cbTipoVenta.getSelectedIndex() == 2;
        txtPesoCanal.setVisible(mostrar);
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
        jdFechaDeNovilla.setVisible(chequeado);
    }//GEN-LAST:event_chkNovillaStateChanged

    private void txtPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaActionPerformed

    private void txtPorcentajeCanalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeCanalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeCanalKeyReleased

    private void txtPesoCanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoCanalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoCanalActionPerformed

    private void txtValorVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaKeyReleased

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdFechaDeNovilla;
    private com.toedter.calendar.JDateChooser jdFechaDestete;
    private com.toedter.calendar.JDateChooser jdFechaMuerte;
    private com.toedter.calendar.JDateChooser jdFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdFechaVenta;
    private javax.swing.JLabel lblAdoptiva;
    private javax.swing.JLabel lblCapado;
    private javax.swing.JLabel lblDescornado;
    private javax.swing.JLabel lblDestete;
    private javax.swing.JLabel lblHierro;
    private javax.swing.JLabel lblImplante;
    private javax.swing.JLabel lblMuerte;
    private javax.swing.JLabel lblNovilla;
    private javax.swing.JLabel lblNumeroDescendiente;
    private javax.swing.JLabel lblVenta;
    private javax.swing.JPanel panelInfoMuerte;
    private javax.swing.JPanel panelInfoVenta;
    private javax.swing.JPanel pnlDestete;
    private javax.swing.JSeparator separador;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JSeparator separador3;
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
    public javax.swing.JTextField txtPorcentajeCanal;
    public javax.swing.JTextField txtPrecioVenta;
    public javax.swing.JTextField txtValorVenta;
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
            boolean destetado = modelo.getDestete().equalsIgnoreCase("1");
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
            txtNotas.setText(Utilidades.decodificarElemento(modelo.getNotas()));
            chkAdoptivo.setSelected(adoptado);

            chkNovilla.setSelected(esNovilla);
            chkHierro.setSelected(hierroFisico);
            chkImplante.setSelected(implante);
            chkDescornada.setSelected(descornado);
            chkDestete.setSelected(destetado);
            chkVenta.setSelected(venta);
            chkMuerte.setSelected(muerte);
            txtObservacionMuerte.setVisible(chkMuerte.isSelected());
            txtObservacionMuerte.setText(Utilidades.decodificarElemento(modelo.getDescripcionMuerte()));
            cbTipoVenta.setSelectedItem(Utilidades.CapitaliceTexto(modelo.getTipoVenta()));
            txtPrecioVenta.setText(modelo.getPrecioVenta());
            txtPesoCanal.setText(modelo.getPesoCanal());

            if (chkAdoptivo.isSelected()) {
                txtNumeroMamaAdoptiva.setText(modelo.getNumeroMamaAdoptiva());
            }

            boolean mostrar = modelo.getNumeroDescendiente().length() > 0;
            lblNumeroDescendiente.setVisible(mostrar);
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
                    jdFechaDestete.setVisible(false);
                    txtPesoDestete.setVisible(false);
                } else {
                    jdFechaDestete.setVisible(true);
                    txtPesoDestete.setVisible(true);
                    txtPesoDestete.setText(modelo.getPesoDestete());
                    fecha = formato.parse(modelo.getFechaDestete());
                    jdFechaDestete.setDate(fecha);
                }

                if (modelo.getFechaMuerte().equals(FECHA_POR_DEFECTO)) {
                    jdFechaMuerte.setVisible(false);
                } else {
                    chkMuerte.setSelected(true);
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
            cbFinca.setSelectedItem(Utilidades.decodificarElemento(retorno.get("DESCRIPCION")));
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
            JOptionPane.showMessageDialog(this, "Seleccione el sexo del animal a crear.");
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
        String codigoAnimal = (editar == Estado.ACTUALIZAR) ? txtCodigoAnimal.getText() : "(SELECT id FROM animales WHERE numero='" + txtNumero.getText().trim() + "' "
                + (txtNumeroDescendiente.getText().length() == 0 ? "" : "AND numero_descendiente=" + txtNumeroDescendiente.getText())
                + ")";

        modelo.setDescornada(chkDescornada.isSelected() ? "1" : "0");
        modelo.setDestete(chkDestete.isSelected() ? "1" : "0");
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
        modelo.setNotas(Utilidades.CodificarElemento(txtNotas.getText().trim()));
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
        modelo.setDescripcionMuerte(Utilidades.CodificarElemento(txtObservacionMuerte.getText()));

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
            modelo.setPesoCanal(pesoCanal.replace(".", "").replace(",", "."));
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
            modelo.setPesoDestete("0");
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
