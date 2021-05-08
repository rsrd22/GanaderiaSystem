package Vistas._Animales;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Busqueda.VistaBusqueda;
import Control.ControlAnimales;
import Control.ControlGeneral;
import Control.ControlTraslado;
import Control.Retorno;
import Control._Animales.Control_Animales;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.ModeloAnimales;
import Modelo.ModeloGestorBusqueda;
import Modelo.ModeloTipoAnimales;
import Modelo.ModeloTraslado;
import Modelo.ModeloVentanaGeneral;
import Modelo._Animales.Modelo_Animales;
import Modelo._Animales.Modelo_AnimalesDescendientes;
import Modelo._Animales.Modelo_AnimalesEntrada;
import static Utilidades.Consultas.consultas;
import Utilidades.Estado;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import Vistas.IControlesUsuario;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class Vista_Animales extends javax.swing.JPanel implements IControlesUsuario {

    private ModeloGestorBusqueda objetoBusqueda;
    private ModeloVentanaGeneral objetoVentana;
    private ModeloAnimales modelo;
    private ControlAnimales control;
    private int editar;
    private GestionEstadoControles controles;
    private JButton[] botones;
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
    private String txtNumeroMadre;

    private String idMadre = "NULL";
    private Map<String, String> datosMadre;

    /**
     * Creates new form VistaAnimales
     */
    public Vista_Animales() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        iniciarComponentes();
        datosMadre = new HashMap<String, String>();
        txtValorVenta.setEnabled(false);
        txtPorcentajeCanal.setEnabled(false);
        cbFinca.setBackground(Color.YELLOW);

        panelFechaNovilla.setVisible(false);

        pnlDestete.setVisible(false);
        panelMadreAdoptiva.setVisible(false);
        txtPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setVisible(false);

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

    public Vista_Animales(ModeloVentanaGeneral modeloVista) {
        initComponents();
        iniciarComponentes();
        datosMadre = new HashMap<String, String>();
        cbFinca.setBackground(Color.cyan);

        panelFechaNovilla.setVisible(false);

        pnlDestete.setVisible(false);
        panelMadreAdoptiva.setVisible(false);

        txtPesoCanal.setVisible(false);
        panelInfoVenta.setVisible(false);
        panelInfoMuerte.setVisible(false);

        lblNumeroDescendiente.setVisible(false);
        txtNumeroDescendiente.setVisible(false);

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

        control = new Control(true, chkDestete);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkMuerte);
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

        control = new Control(true, txtValorVenta);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(false, txtPorcentajeCanal);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(false, chkCapado);
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
        slCalificacion = new javax.swing.JSlider();
        separador = new javax.swing.JSeparator();
        separador1 = new javax.swing.JSeparator();
        separador2 = new javax.swing.JSeparator();
        separador3 = new javax.swing.JSeparator();
        panelContainer1 = new javax.swing.JPanel();
        cbFinca = new javax.swing.JComboBox();
        cbHierros = new javax.swing.JComboBox();
        cbGenero = new javax.swing.JComboBox();
        cbTiposDeAnimales = new javax.swing.JComboBox();
        cbPropietario = new javax.swing.JComboBox();
        cbGrupos = new javax.swing.JComboBox();
        panelContainer2 = new javax.swing.JPanel();
        txtPesoKg = new javax.swing.JTextField();
        txtNumeroMama = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        txtNumero = new javax.swing.JTextField();
        txtNumeroDescendiente = new javax.swing.JTextField();
        lblNumeroDescendiente = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        chkNovilla = new javax.swing.JCheckBox();
        chkAdoptivo = new javax.swing.JCheckBox();
        lblNovilla = new javax.swing.JLabel();
        lblAdoptiva = new javax.swing.JLabel();
        panelMadreAdoptiva = new javax.swing.JPanel();
        txtNumeroMamaAdoptiva = new javax.swing.JTextField();
        separador4 = new javax.swing.JSeparator();
        panelFechaNovilla = new javax.swing.JPanel();
        txtNumeroPartos = new javax.swing.JTextField();
        panelContainer3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        lblHierro = new javax.swing.JLabel();
        chkHierro = new javax.swing.JCheckBox();
        chkDescornada = new javax.swing.JCheckBox();
        lblDescornado = new javax.swing.JLabel();
        lblImplante = new javax.swing.JLabel();
        chkImplante = new javax.swing.JCheckBox();
        lblCapado = new javax.swing.JLabel();
        chkCapado = new javax.swing.JCheckBox();
        panelContainer4 = new javax.swing.JPanel();
        lblDestete = new javax.swing.JLabel();
        chkDestete = new javax.swing.JCheckBox();
        pnlDestete = new javax.swing.JPanel();
        txtPesoDestete = new javax.swing.JTextField();
        lblVenta = new javax.swing.JLabel();
        chkVenta = new javax.swing.JCheckBox();
        panelInfoVenta = new javax.swing.JPanel();
        txtPrecioVenta = new javax.swing.JTextField();
        txtPesoCanal = new javax.swing.JTextField();
        cbTipoVenta = new javax.swing.JComboBox();
        txtPorcentajeCanal = new javax.swing.JTextField();
        txtValorVenta = new javax.swing.JTextField();
        lblMuerte = new javax.swing.JLabel();
        chkMuerte = new javax.swing.JCheckBox();
        panelInfoMuerte = new javax.swing.JPanel();
        ScrollCausaMuerte = new javax.swing.JScrollPane();
        txtObservacionMuerte = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setAutoscrolls(true);
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });
        setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowHeights = new int[] {0, 11, 0, 11, 0, 11, 0, 11, 0, 11, 0, 11, 0, 11, 0, 11, 0, 11, 0};
        jPanel3.setLayout(jPanel3Layout);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(slCalificacion, gridBagConstraints);

        separador.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(separador, gridBagConstraints);

        separador1.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(separador1, gridBagConstraints);

        separador2.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(separador2, gridBagConstraints);

        separador3.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(separador3, gridBagConstraints);

        panelContainer1.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWidths = new int[] {0, 15, 0, 15, 0};
        jPanel4Layout.rowHeights = new int[] {0, 11, 0};
        panelContainer1.setLayout(jPanel4Layout);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbFinca, gridBagConstraints);

        cbHierros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbHierros.setForeground(new java.awt.Color(59, 123, 50));
        cbHierros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbHierros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hierro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbHierros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHierrosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbHierros, gridBagConstraints);

        cbGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenero.setForeground(new java.awt.Color(59, 123, 50));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbGenero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sexo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbGenero, gridBagConstraints);

        cbTiposDeAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTiposDeAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTiposDeAnimales.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTiposDeAnimales.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de animales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTiposDeAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTiposDeAnimalesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbTiposDeAnimales, gridBagConstraints);

        cbPropietario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbPropietario.setForeground(new java.awt.Color(59, 123, 50));
        cbPropietario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbPropietario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Propietario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbPropietario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPropietarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbPropietario, gridBagConstraints);

        cbGrupos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGrupos.setForeground(new java.awt.Color(59, 123, 50));
        cbGrupos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbGrupos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGruposActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3333333333333333;
        panelContainer1.add(cbGrupos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(panelContainer1, gridBagConstraints);

        panelContainer2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout panelContainer2Layout = new java.awt.GridBagLayout();
        panelContainer2Layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        panelContainer2Layout.rowHeights = new int[] {0, 11, 0, 11, 0};
        panelContainer2.setLayout(panelContainer2Layout);

        txtPesoKg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoKg.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoKg.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoKg.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso(Kg)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPesoKg.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.setMargin(new java.awt.Insets(2, 5, 2, 5));
        txtPesoKg.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoKg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKgKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(txtPesoKg, gridBagConstraints);

        txtNumeroMama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMama.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMama.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMama.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Número de la madre", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtNumeroMama.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroMama.setMargin(new java.awt.Insets(2, 5, 2, 5));
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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(txtNumeroMama, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Número", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 15, 0, 15, 0};
        jPanel1Layout.rowHeights = new int[] {0};
        jPanel1.setLayout(jPanel1Layout);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 4, 0);
        jPanel1.add(txtNumero, gridBagConstraints);

        txtNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroDescendiente.setBorder(null);
        txtNumeroDescendiente.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroDescendiente.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 10);
        jPanel1.add(txtNumeroDescendiente, gridBagConstraints);

        lblNumeroDescendiente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNumeroDescendiente.setForeground(new java.awt.Color(59, 123, 50));
        lblNumeroDescendiente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumeroDescendiente.setText(" - ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(lblNumeroDescendiente, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(jPanel1, gridBagConstraints);

        txtPeso.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(59, 123, 50));
        txtPeso.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPeso.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso(lbs)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPeso.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPeso.setMargin(new java.awt.Insets(2, 5, 2, 5));
        txtPeso.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(txtPeso, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(59, 123, 50));
        jPanel5.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(chkNovilla, gridBagConstraints);

        chkAdoptivo.setBackground(new java.awt.Color(59, 123, 50));
        chkAdoptivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkAdoptivo.setForeground(new java.awt.Color(59, 123, 50));
        chkAdoptivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkAdoptivo.setBorderPainted(true);
        chkAdoptivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAdoptivo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAdoptivo.setMaximumSize(new java.awt.Dimension(25, 25));
        chkAdoptivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAdoptivoMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel5.add(chkAdoptivo, gridBagConstraints);

        lblNovilla.setBackground(new java.awt.Color(255, 255, 255));
        lblNovilla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNovilla.setForeground(new java.awt.Color(255, 255, 255));
        lblNovilla.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNovilla.setText("Novilla");
        lblNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(lblNovilla, gridBagConstraints);

        lblAdoptiva.setBackground(new java.awt.Color(255, 51, 51));
        lblAdoptiva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAdoptiva.setForeground(new java.awt.Color(255, 255, 255));
        lblAdoptiva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdoptiva.setText("Madre adoptiva");
        lblAdoptiva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel5.add(lblAdoptiva, gridBagConstraints);

        panelMadreAdoptiva.setBackground(new java.awt.Color(255, 255, 255));
        panelMadreAdoptiva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelMadreAdoptiva.setLayout(new java.awt.GridBagLayout());

        txtNumeroMamaAdoptiva.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroMamaAdoptiva.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroMamaAdoptiva.setToolTipText("Número madre adoptiva");
        txtNumeroMamaAdoptiva.setBorder(null);
        txtNumeroMamaAdoptiva.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtNumeroMamaAdoptiva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroMamaAdoptivaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 0, 15);
        panelMadreAdoptiva.add(txtNumeroMamaAdoptiva, gridBagConstraints);

        separador4.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        panelMadreAdoptiva.add(separador4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(panelMadreAdoptiva, gridBagConstraints);

        panelFechaNovilla.setBackground(new java.awt.Color(255, 255, 255));
        panelFechaNovilla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelFechaNovilla.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(panelFechaNovilla, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(jPanel5, gridBagConstraints);

        txtNumeroPartos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumeroPartos.setForeground(new java.awt.Color(59, 123, 50));
        txtNumeroPartos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNumeroPartos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Parto Nro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtNumeroPartos.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNumeroPartos.setMargin(new java.awt.Insets(2, 5, 2, 5));
        txtNumeroPartos.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.25;
        panelContainer2.add(txtNumeroPartos, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(panelContainer2, gridBagConstraints);

        panelContainer3.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel4Layout1 = new java.awt.GridBagLayout();
        jPanel4Layout1.columnWidths = new int[] {0, 15, 0};
        jPanel4Layout1.rowHeights = new int[] {0};
        panelContainer3.setLayout(jPanel4Layout1);

        jScrollPane2.setBorder(null);

        txtNotas.setColumns(20);
        txtNotas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNotas.setForeground(new java.awt.Color(59, 123, 50));
        txtNotas.setRows(5);
        txtNotas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtNotas.setCaretColor(new java.awt.Color(59, 123, 50));
        txtNotas.setSelectionColor(new java.awt.Color(59, 123, 50));
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        panelContainer3.add(jScrollPane2, gridBagConstraints);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        lblHierro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHierro.setForeground(new java.awt.Color(59, 123, 50));
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHierro.setText("Hierro");
        lblHierro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(lblHierro, gridBagConstraints);

        chkHierro.setBackground(new java.awt.Color(255, 255, 255));
        chkHierro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkHierro.setForeground(new java.awt.Color(59, 123, 50));
        chkHierro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkHierro.setBorderPainted(true);
        chkHierro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkHierro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        jPanel6.add(chkHierro, gridBagConstraints);

        chkDescornada.setBackground(new java.awt.Color(255, 255, 255));
        chkDescornada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescornada.setForeground(new java.awt.Color(59, 123, 50));
        chkDescornada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkDescornada.setBorderPainted(true);
        chkDescornada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDescornada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        jPanel6.add(chkDescornada, gridBagConstraints);

        lblDescornado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDescornado.setForeground(new java.awt.Color(59, 123, 50));
        lblDescornado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescornado.setText("Descornado");
        lblDescornado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(lblDescornado, gridBagConstraints);

        lblImplante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblImplante.setForeground(new java.awt.Color(59, 123, 50));
        lblImplante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImplante.setText("Implante");
        lblImplante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(lblImplante, gridBagConstraints);

        chkImplante.setBackground(new java.awt.Color(255, 255, 255));
        chkImplante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkImplante.setForeground(new java.awt.Color(59, 123, 50));
        chkImplante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        chkImplante.setBorderPainted(true);
        chkImplante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkImplante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        jPanel6.add(chkImplante, gridBagConstraints);

        lblCapado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCapado.setForeground(new java.awt.Color(59, 123, 50));
        lblCapado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCapado.setText("Capado");
        lblCapado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel6.add(lblCapado, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        jPanel6.add(chkCapado, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        panelContainer3.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(panelContainer3, gridBagConstraints);

        panelContainer4.setBackground(new java.awt.Color(59, 123, 50));
        panelContainer4.setLayout(new java.awt.GridBagLayout());

        lblDestete.setBackground(new java.awt.Color(59, 123, 50));
        lblDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDestete.setForeground(new java.awt.Color(255, 255, 255));
        lblDestete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDestete.setText("Destete");
        lblDestete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelContainer4.add(lblDestete, gridBagConstraints);

        chkDestete.setBackground(new java.awt.Color(59, 123, 50));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelContainer4.add(chkDestete, gridBagConstraints);

        pnlDestete.setBackground(new java.awt.Color(255, 255, 255));
        pnlDestete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        pnlDestete.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.15;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlDestete.add(txtPesoDestete, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelContainer4.add(pnlDestete, gridBagConstraints);

        lblVenta.setBackground(new java.awt.Color(59, 123, 50));
        lblVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVenta.setText("Venta");
        lblVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelContainer4.add(lblVenta, gridBagConstraints);

        chkVenta.setBackground(new java.awt.Color(59, 123, 50));
        chkVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkVenta.setForeground(new java.awt.Color(59, 123, 50));
        chkVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        chkVenta.setBorderPainted(true);
        chkVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chkVentaMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelContainer4.add(chkVenta, gridBagConstraints);

        panelInfoVenta.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        panelInfoVenta.setLayout(new java.awt.GridBagLayout());

        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPrecioVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Precio de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPrecioVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioVenta.setMargin(new java.awt.Insets(2, 5, 2, 5));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        panelInfoVenta.add(txtPrecioVenta, gridBagConstraints);

        txtPesoCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoCanal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Peso de canal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPesoCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoCanal.setMargin(new java.awt.Insets(2, 5, 2, 5));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        panelInfoVenta.add(txtPesoCanal, gridBagConstraints);

        cbTipoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoVenta.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoVenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Particular", "Matadero" }));
        cbTipoVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        panelInfoVenta.add(cbTipoVenta, gridBagConstraints);

        txtPorcentajeCanal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPorcentajeCanal.setForeground(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPorcentajeCanal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "% de canal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPorcentajeCanal.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.setMargin(new java.awt.Insets(2, 5, 2, 5));
        txtPorcentajeCanal.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPorcentajeCanal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeCanalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        panelInfoVenta.add(txtPorcentajeCanal, gridBagConstraints);

        txtValorVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtValorVenta.setForeground(new java.awt.Color(59, 123, 50));
        txtValorVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtValorVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Valor de venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtValorVenta.setCaretColor(new java.awt.Color(59, 123, 50));
        txtValorVenta.setMargin(new java.awt.Insets(2, 5, 2, 5));
        txtValorVenta.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtValorVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorVentaKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.3333333333333333;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        panelInfoVenta.add(txtValorVenta, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelContainer4.add(panelInfoVenta, gridBagConstraints);

        lblMuerte.setBackground(new java.awt.Color(255, 255, 255));
        lblMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMuerte.setForeground(new java.awt.Color(255, 255, 255));
        lblMuerte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMuerte.setText("Muerte");
        lblMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        panelContainer4.add(lblMuerte, gridBagConstraints);

        chkMuerte.setBackground(new java.awt.Color(59, 123, 50));
        chkMuerte.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkMuerte.setForeground(new java.awt.Color(59, 123, 50));
        chkMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        chkMuerte.setBorderPainted(true);
        chkMuerte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkMuerte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkMuerte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                chkMuerteMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        panelContainer4.add(chkMuerte, gridBagConstraints);

        panelInfoMuerte.setBackground(new java.awt.Color(255, 255, 255));
        panelInfoMuerte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50), 2));
        panelInfoMuerte.setLayout(new java.awt.GridBagLayout());

        ScrollCausaMuerte.setBackground(new java.awt.Color(255, 255, 255));
        ScrollCausaMuerte.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Causa de muerte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N

        txtObservacionMuerte.setColumns(20);
        txtObservacionMuerte.setForeground(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setRows(5);
        txtObservacionMuerte.setBorder(null);
        txtObservacionMuerte.setCaretColor(new java.awt.Color(59, 123, 50));
        txtObservacionMuerte.setSelectionColor(new java.awt.Color(59, 123, 50));
        ScrollCausaMuerte.setViewportView(txtObservacionMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.9;
        gridBagConstraints.insets = new java.awt.Insets(10, 15, 15, 15);
        panelInfoMuerte.add(ScrollCausaMuerte, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelContainer4.add(panelInfoMuerte, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(panelContainer4, gridBagConstraints);

        jScrollPane1.setViewportView(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);

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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        _Guardar();
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
        pnlDestete.setVisible(chequeado);
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
                                + "Verifique en el listado de animales o compruebe que este no se encuentre vendido o muerto."
                        );
                    }
                }
            }
        }
    }//GEN-LAST:event_txtNumeroFocusLost

    private boolean verificarNroAnimal() {
        Control_Animales _control = new Control_Animales();
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        String[] parametros = new String[]{
            txtNumero.getText().trim(),//numero del animal
            txtCodigoTipoAnimal.getText()//tipo del animal
        };
        lista = (ArrayList<ModeloAnimales>) _control.ObtenerDatosFiltro(parametros);
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
            panelMadreAdoptiva.setVisible(mostrar);
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
        if (txtNumeroMama.getText().trim().length() != 0) {
            Control_Animales _control = new Control_Animales();
            String numeroMadre = txtNumeroMama.getText();
            String tipoAnimal = txtCodigoTipoAnimal.getText();

            datosMadre = _control.ObtenerIDMadre(numeroMadre, tipoAnimal);
            idMadre = datosMadre.get("ID");

            String nroDescendiente = _control.ObtenerUltimoDescendiente(idMadre);
            if (nroDescendiente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La madre número " + numeroMadre + " no existe.");
                txtNumeroMama.setText("");
                return;
            }
            txtNumeroDescendiente.setText(nroDescendiente);

            boolean mostrar = true;
            lblNumeroDescendiente.setVisible(mostrar);
            txtNumeroDescendiente.setVisible(mostrar);
        }
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

    private void txtPesoDesteteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoDesteteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoDesteteKeyReleased

    private void chkNovillaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkNovillaStateChanged
        boolean chequeado = chkNovilla.isSelected();
        panelFechaNovilla.setVisible(chequeado);
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

    private void chkVentaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkVentaMouseReleased
        if (!chkVenta.isEnabled()) {
            return;
        }
        boolean venta = chkVenta.isSelected();
        casoVenta();
    }//GEN-LAST:event_chkVentaMouseReleased

    private void chkMuerteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkMuerteMouseReleased
        if (!chkMuerte.isEnabled()) {
            return;
        }
        boolean muerte = chkMuerte.isSelected();
        casoMuerte();
    }//GEN-LAST:event_chkMuerteMouseReleased

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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JPanel panelContainer1;
    private javax.swing.JPanel panelContainer2;
    private javax.swing.JPanel panelContainer3;
    private javax.swing.JPanel panelContainer4;
    private javax.swing.JPanel panelFechaNovilla;
    private javax.swing.JPanel panelInfoMuerte;
    private javax.swing.JPanel panelInfoVenta;
    private javax.swing.JPanel panelMadreAdoptiva;
    private javax.swing.JPanel pnlDestete;
    private javax.swing.JSeparator separador;
    private javax.swing.JSeparator separador1;
    private javax.swing.JSeparator separador2;
    private javax.swing.JSeparator separador3;
    private javax.swing.JSeparator separador4;
    private javax.swing.JSlider slCalificacion;
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
    public javax.swing.JTextField txtNumeroPartos;
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
            boolean esNovilla = !(modelo.getFechaNovilla().equals(Utilidades.FECHA_POR_DEFECTO) || modelo.getFechaNovilla().equalsIgnoreCase(NULL));

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
            chkVenta.setSelected(venta);
            chkMuerte.setSelected(muerte);

            chkNovilla.setSelected(esNovilla);
            chkHierro.setSelected(hierroFisico);
            chkImplante.setSelected(implante);
            chkDescornada.setSelected(descornado);
            chkDestete.setSelected(destetado);

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

                if (modelo.getFechaNovilla().equals(Utilidades.FECHA_POR_DEFECTO) || modelo.getFechaNovilla().equalsIgnoreCase(NULL)) {
                    jdFechaDeNovilla.setCalendar(Calendar.getInstance());
                } else {
                    fecha = formato.parse(modelo.getFechaNovilla());
                    jdFechaDeNovilla.setDate(fecha);
                }

                jdFechaDestete.setVisible(true);
                txtPesoDestete.setVisible(true);
                txtPesoDestete.setText(modelo.getPesoDestete());
                fecha = formato.parse(modelo.getFechaDestete());
                jdFechaDestete.setDate(fecha);

                if (modelo.getFechaMuerte().equals(Utilidades.FECHA_POR_DEFECTO)) {
                    jdFechaMuerte.setVisible(false);
                } else {
                    chkMuerte.setSelected(true);
                    jdFechaMuerte.setVisible(true);
                    fecha = formato.parse(modelo.getFechaMuerte());
                    jdFechaMuerte.setDate(fecha);
                }

                if (muerte) {
                    txtObservacionMuerte.setVisible(chkMuerte.isSelected());
                    txtObservacionMuerte.setText(Utilidades.decodificarElemento(modelo.getDescripcionMuerte()));
                    chkMuerte.setSelected(muerte);
                    casoMuerte();
                    panelInfoMuerte.setVisible(muerte);
                }

                if (venta) {
                    cbTipoVenta.setSelectedItem(Utilidades.CapitaliceTexto(modelo.getTipoVenta()));
                    txtPrecioVenta.setText(Utilidades.MascaraMonedaConDecimales(modelo.getPrecioVenta().replace(".", ",")));
                    txtPesoCanal.setText(Utilidades.MascaraMonedaConDecimales(modelo.getPesoCanal().replace(".", ",")));

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
        chkDestete.setEnabled(false);
        chkVenta.setEnabled(false);
        chkVenta.setEnabled(false);
        chkMuerte.setEnabled(false);
        chkMuerte.setEnabled(false);
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

        //<editor-fold defaultstate="collapsed" desc="validacionesParaVenta">
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
//</editor-fold>

        if (chkMuerte.isSelected()) {
            if (jdFechaMuerte.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de muerte del animal.");
                jdFechaMuerte.requestFocusInWindow();
                return;
            }
        }

        boolean numeroAnimalEsIgualAlDeLaMadre = txtNumeroMama.getText().trim().equals(txtNumero.getText().trim());
        lblNumeroDescendiente.setVisible(numeroAnimalEsIgualAlDeLaMadre);
        txtNumeroDescendiente.setVisible(numeroAnimalEsIgualAlDeLaMadre);
        if (!numeroAnimalEsIgualAlDeLaMadre) {
            if (editar == Estado.GUARDAR) {
                boolean numeroAnimalEsIgualAlDeLaMadreAdoptiva = txtNumeroMamaAdoptiva.getText().trim().equals(txtNumero.getText().trim());
                if (!numeroAnimalEsIgualAlDeLaMadreAdoptiva) {
                    if (verificarNroAnimal()) {
                        JOptionPane.showMessageDialog(null, "El número " + txtNumero.getText() + " pertenece a otro animal.\n");
                        return;
                    }
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
                + "and id_tipo_animal='" + tipoAnimales.get(cbTiposDeAnimales.getSelectedIndex()).get("id") + "'\n"
                + "and fecha=NOW()\n"
                + (txtNumeroDescendiente.getText().length() == 0 ? "" : "AND numero_descendiente=" + txtNumeroDescendiente.getText())
                + ")");

        //<editor-fold defaultstate="collapsed" desc="ESTABLECIENDO LOS DATOS DEL MODELO A GUARDAR">
        String codigoAnimal = (editar == Estado.ACTUALIZAR)
                ? txtCodigoAnimal.getText()
                : "(SELECT id FROM animales WHERE numero='" + txtNumero.getText().trim() + "' "
                + "and id_tipo_animal='" + tipoAnimales.get(cbTiposDeAnimales.getSelectedIndex()).get("id") + "'\n"
                + "and fecha=NOW()\n"
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
        modelo.setNumeroMama(txtNumeroMadre);
        modelo.setPeso(txtPesoOculto.getText().replace(".", "").replace(",", "."));
        Calendar fechaNacimiento = jdFechaNacimiento.getCalendar();
        modelo.setFechaNacimiento(sdf.format(fechaNacimiento.getTime()));

        if (chkNovilla.isSelected()) {
            Calendar fechaDeNovilla = jdFechaDeNovilla.getCalendar();
            modelo.setFechaNovilla(
                    fechaDeNovilla == null
                    ? Utilidades.FECHA_POR_DEFECTO
                    : sdf.format(fechaDeNovilla.getTime())
            );
        } else {
            modelo.setFechaNovilla(Utilidades.FECHA_POR_DEFECTO);
        }

        modelo.setNumeroDescendiente(txtNumeroDescendiente.getText().trim().length() == 0 ? "0" : txtNumeroDescendiente.getText().trim());
        modelo.setEstadoDescendiente(txtNumeroMama.getText().trim().equals(txtNumero.getText().trim()) ? "0" : "1");
        modelo.setDescripcionMuerte(Utilidades.CodificarElemento(txtObservacionMuerte.getText()));

        if (txtNumeroPartos.isVisible()) {
            modelo.setNumero_partos(txtNumeroPartos.getText().trim());
        } else {
            modelo.setNumero_partos(modelo.getNumeroDescendiente());
        }

        if (chkMuerte.isSelected()) {
            Calendar fechaMuerte = jdFechaMuerte.getCalendar();
            modelo.setFechaMuerte(sdf.format(fechaMuerte.getTime()));
        } else {
            modelo.setFechaMuerte(Utilidades.FECHA_POR_DEFECTO);
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
            modelo.setFechaVenta(Utilidades.FECHA_POR_DEFECTO);
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
            modelo.setFechaDestete(Utilidades.FECHA_POR_DEFECTO);
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

    private void _Guardar() {
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

        //<editor-fold defaultstate="collapsed" desc="validacionesParaVenta">
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
//</editor-fold>

        if (chkMuerte.isSelected()) {
            if (jdFechaMuerte.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Debe especificar la fecha de muerte del animal.");
                jdFechaMuerte.requestFocusInWindow();
                return;
            }
        }

        boolean numeroAnimalEsIgualAlDeLaMadre = txtNumeroMama.getText().trim().equals(txtNumero.getText().trim());
        lblNumeroDescendiente.setVisible(numeroAnimalEsIgualAlDeLaMadre);
        txtNumeroDescendiente.setVisible(numeroAnimalEsIgualAlDeLaMadre);
        if (!numeroAnimalEsIgualAlDeLaMadre) {
            if (editar == Estado.GUARDAR) {
                boolean numeroAnimalEsIgualAlDeLaMadreAdoptiva = txtNumeroMamaAdoptiva.getText().trim().equals(txtNumero.getText().trim());
                if (!numeroAnimalEsIgualAlDeLaMadreAdoptiva) {
                    if (verificarNroAnimal()) {
                        JOptionPane.showMessageDialog(null, "El número " + txtNumero.getText() + " pertenece a otro animal.\n");
                        return;
                    }
                }
            }
        }
//</editor-fold>

        Control_Animales _control = new Control_Animales();
        Modelo_AnimalesEntrada modelo = new Modelo_AnimalesEntrada();
        Modelo_Animales ma = new Modelo_Animales();
        Modelo_AnimalesDescendientes mad = new Modelo_AnimalesDescendientes();
        ModeloTraslado mt = new ModeloTraslado();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fechaDestete = Calendar.getInstance();
        int indiceGrupo = cbGrupos.getSelectedIndex();

        //<editor-fold defaultstate="collapsed" desc="setModeloAnimales">
        String idAnimal = (editar == Estado.ACTUALIZAR)
                ? txtCodigoAnimal.getText()
                : "(SELECT (AUTO_INCREMENT-1)\n"
                + "FROM information_schema.tables\n"
                + "WHERE table_name = '_animales'\n"
                + "AND table_schema = 'ganadero')";

        ma.setId(idAnimal);
        ma.setDescornado(chkDescornada.isSelected() ? "1" : "0");
        ma.setDestete(chkDestete.isSelected() ? "1" : "0");
        ma.setImplante(chkImplante.isSelected() ? "1" : "0");
        ma.setHierro_fisico(chkHierro.isSelected() ? "1" : "0");
        ma.setMuerte(chkMuerte.isSelected() ? "1" : "0");
        ma.setVenta(chkVenta.isSelected() ? "1" : "0");
        ma.setCapado(chkCapado.isSelected() ? "Si" : "No");
        ma.setFecha("NOW()");
        ma.setEs_madre("NULL");
        ma.setHierro(hierros.get(cbHierros.getSelectedIndex()).get("id"));
        ma.setGrupo(grupos.get(indiceGrupo).get("id"));
        ma.setId_tipo_animal(tipoAnimales.get(cbTiposDeAnimales.getSelectedIndex()).get("id"));
        ma.setCalificacion("" + slCalificacion.getValue());
        ma.setGenero(cbGenero.getSelectedItem().toString().toLowerCase());
        ma.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        ma.setNotas(Utilidades.CodificarElemento(txtNotas.getText().trim()));
        ma.setNumero(txtNumero.getText().trim());
        ma.setPeso(txtPesoOculto.getText().replace(".", "").replace(",", "."));
        Calendar fechaNacimiento = jdFechaNacimiento.getCalendar();
        ma.setFecha_nacimiento(sdf.format(fechaNacimiento.getTime()));

        if (chkNovilla.isSelected()) {
            Calendar fechaDeNovilla = jdFechaDeNovilla.getCalendar();
            ma.setFecha_novilla(
                    fechaDeNovilla == null
                    ? Utilidades.FECHA_POR_DEFECTO
                    : sdf.format(fechaDeNovilla.getTime())
            );
        } else {
            ma.setFecha_novilla(Utilidades.FECHA_POR_DEFECTO);
        }

        if (chkMuerte.isSelected()) {
            Calendar fechaMuerte = jdFechaMuerte.getCalendar();
            ma.setFecha_muerte(sdf.format(fechaMuerte.getTime()));
        } else {
            ma.setFecha_muerte(Utilidades.FECHA_POR_DEFECTO);
        }
        ma.setDescripcion_muerte(Utilidades.CodificarElemento(txtObservacionMuerte.getText()));

        if (chkVenta.isSelected()) {
            Calendar fechaVenta = jdFechaVenta.getCalendar();
            ma.setFecha_venta(sdf.format(fechaVenta.getTime()));
            ma.setPrecio_venta(txtPrecioVenta.getText().replace(".", "").replace(",", "."));
            String pesoCanal = txtPesoCanal.getText();
            pesoCanal = pesoCanal.isEmpty() ? "0" : pesoCanal;
            ma.setPeso_canal(pesoCanal.replace(".", "").replace(",", "."));
            ma.setTipo_venta("'" + cbTipoVenta.getSelectedItem().toString().toLowerCase() + "'");
        } else {
            ma.setPrecio_venta("NULL");
            ma.setPeso_canal("NULL");
            ma.setTipo_venta("NULL");
            ma.setFecha_venta(Utilidades.FECHA_POR_DEFECTO);
        }

        if (chkAdoptivo.isSelected()) {
            ma.setNumero_mama_adoptiva("'" + txtNumeroMamaAdoptiva.getText() + "'");
        } else {
            ma.setNumero_mama_adoptiva("NULL");
        }

        if (chkDestete.isSelected()) {
            fechaDestete = jdFechaDestete.getCalendar();
            ma.setFecha_destete(sdf.format(fechaDestete.getTime()));
            ma.setPeso_destete(txtPesoDestete.getText().replace(".", "").replace(",", "."));
        } else {
            ma.setFecha_destete(Utilidades.FECHA_POR_DEFECTO);
            ma.setPeso_destete("0");
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="setDatosModeloTraslado">
        mt.setId("0");
        mt.setIdFinca(txtCodigoFinca.getText());
        mt.setFecha("NOW()");
        mt.setEstado("Activo");
        mt.setFechaTraslado("NOW()");
        mt.setIdGrupo(grupos.get(indiceGrupo).get("id"));
        mt.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        mt.setMotivo("CREACIÓN DEL ANIMAL");
        mt.setIdAnimal(ma.getId());
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="setDatosModeloAnimalesDescendientes">
        mad.setId("0");
        mad.setId_animal(ma.getId());
        mad.setId_madre(idMadre);
        mad.setFecha("NOW()");
        mad.setNro_descendiente(txtNumeroDescendiente.getText().trim().length() == 0 ? "NULL" : txtNumeroDescendiente.getText().trim());
        mad.setNro_parto(txtNumeroPartos.getText().trim().length() == 0 ? "NULL" : txtNumeroPartos.getText().trim());
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="actualizarRegistroDeLaMadre">
        String consulta = "";
        if (datosMadre.size() > 0) {
            if (datosMadre.get("ES_MADRE").equalsIgnoreCase("FALSE")) {
                consulta = "UPDATE _animales SET es_madre='Si' WHERE id=" + datosMadre.get("ID");
            }
        }
//</editor-fold>

        modelo.setAnimal(ma);
        modelo.setDescendiente(mad);
        modelo.setModeloTraslado(mt);
        modelo.setActualizarRegistroMadre(consulta);

        int retorno = Retorno.DEFECTO;

        if (editar == Estado.GUARDAR) {
            retorno = _control.Guardar(modelo);
        } else {
            retorno = _control.Actualizar(modelo);
        }

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro " + (editar == Estado.GUARDAR ? "guardado" : "actualizado") + " satisfactoriamente.";
                Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_GUARDAR, controles);
                Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_GUARDAR, botones);
                editar = Estado.GUARDAR;
                jdFechaDestete.setCalendar(fechaDestete);

                datosMadre = new HashMap<String, String>();
                idMadre = "NULL";
                lblNumeroDescendiente.setVisible(false);
                txtNumeroDescendiente.setVisible(false);
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

        chkDestete.setEnabled(true);
        chkVenta.setEnabled(true);
        chkMuerte.setEnabled(true);
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
            return "$ " + Utilidades.MascaraMonedaConDecimales("" + precioDeVenta);
        }
    }

    private String calcularPorcentajeDeCanal(double pesoCanal) {
        if (txtPesoOculto.getText().length() == 0) {
            return "";
        } else {
            DecimalFormat df = new DecimalFormat("#.00");

            double peso = Integer.parseInt(txtPesoOculto.getText());
            double porcentajeCanal = pesoCanal / peso * 100;
            return df.format(porcentajeCanal) + "%";
        }
    }

    private void casoMuerte() {
        if (chkMuerte.isSelected()) {
            EstadoVenta(false);
            EstadoMuerte(true);
            chkVenta.setSelected(false);
        } else {
            chkVenta.setSelected(true);
            EstadoVenta(true);
            EstadoMuerte(false);
        }
    }

    private void casoVenta() {
        if (chkVenta.isSelected()) {
            EstadoVenta(true);
            EstadoMuerte(false);
            chkMuerte.setSelected(false);
        } else {
            chkMuerte.setSelected(true);
            EstadoVenta(false);
            EstadoMuerte(true);
        }
    }

    private void setCalculosVenta() {
        if (txtPrecioVenta.getText().lastIndexOf(",") == txtPrecioVenta.getText().length() - 1) {
            return;
        }

        String precioVenta = txtPrecioVenta.getText().contains(".") || txtPrecioVenta.getText().contains(",")
                ? txtPrecioVenta.getText().replace(".", "").replace(",", ".")
                : txtPrecioVenta.getText();
        String porcentajeCanal = txtPesoCanal.getText().contains(".") || txtPesoCanal.getText().contains(",")
                ? txtPesoCanal.getText().replace(".", "").replace(",", ".")
                : txtPesoCanal.getText();

        txtPorcentajeCanal.setEnabled(false);
        txtValorVenta.setEnabled(false);

        txtPorcentajeCanal.setText(
                txtPesoCanal.getText().length() == 0
                ? ""
                : calcularPorcentajeDeCanal(Double.parseDouble(porcentajeCanal))
        );
        txtValorVenta.setText(
                txtPrecioVenta.getText().length() == 0
                ? ""
                : calcularPrecioDeVenta(Double.parseDouble(precioVenta))
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
