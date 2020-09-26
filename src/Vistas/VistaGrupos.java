/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Busqueda.VistaBusqueda;
import Control.ControlGeneral;
import Control.ControlGrupos;
import Control.Retorno;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.ModeloGestorBusqueda;
import Modelo.ModeloGrupos;
import Modelo.ModeloMacroGrupo;
import Modelo.ModeloTipoAnimales;
import Modelo.ModeloVentanaGeneral;
import static Utilidades.Consultas.consultas;
import Utilidades.Estado;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaGrupos extends javax.swing.JPanel implements IControlesUsuario {

    private ModeloGestorBusqueda objetoBusqueda;
    private ModeloVentanaGeneral objetoVentana;
    private ModeloGrupos modelo;
    private ControlGrupos control;
    private int editar;
    private GestionEstadoControles controles;
    private JButton[] botones;
    private List<Map<String, String>> fincas;
    private List<Map<String, String>> tipoAnimales;
    private List<Map<String, String>> macroGrupos;
    private ControlGeneral controlGral;
    private JCheckBox[] checks;
    public int idModulo = 9;

    /**
     * Creates new form VistaGrupos
     */
    public VistaGrupos() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        iniciarComponentes();
        modelo = new ModeloGrupos();
        control = new ControlGrupos();
        controlGral = new ControlGeneral();
        editar = Estado.GUARDAR;
        botones = new JButton[]{
            btnConsultar,
            btnDescartar,
            btnEliminar,
            btnGuardar,
            btnModificar,
            btnDuplicar
        };
        cargarComboFincas();
        checks = new JCheckBox[]{
            cbCriaH,
            cbCriaM,
            cbDescarte,
            cbMuerte
        };
        controles.habilitarControles();
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    private void cargarComboFincas() {
        String consulta = consultas.get("CARGAR_COMBO_FINCAS");
        fincas = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbFinca, fincas, "descripcion");
    }

    private void cargarComboTipoAnimales() {
        String consulta = consultas.get("CARGAR_COMBO_TIPO_ANIMALES") + txtCodigoFinca.getText();
        tipoAnimales = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbTipoAnimal, tipoAnimales, "descripcion");
    }

    private void cargarComboMacroGrupos() {
        String consulta = consultas.get("CARGAR_COMBO_MACROGRUPOS") + txtCodigoFinca.getText();
        macroGrupos = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbMacroGrupo, macroGrupos, "descripcion");
    }

    @Override
    public void iniciarComponentes() {
        controles = new GestionEstadoControles();

        Control control = new Control(true, txtCodigoGrupo);
        controles.addControl(control);

        control = new Control(true, cbFinca);
        controles.addControl(control);

        control = new Control(true, chkPesable);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, chkPalpable);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbTipoAnimal);
        controles.addControl(control);

        control = new Control(true, cbMacroGrupo);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbMuerte);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbCriaH);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbCriaM);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbDescarte);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtCodigoTipoAnimal);
        controles.addControl(control);

        control = new Control(true, txtCodigoMacroGrupo);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtDescripcion);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbEstado);
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

        txtCodigoMacroGrupo = new javax.swing.JLabel();
        txtCodigoTipoAnimal = new javax.swing.JLabel();
        txtCodigoGrupo = new javax.swing.JLabel();
        txtCodigoFinca = new javax.swing.JLabel();
        bgTipoGrupos = new javax.swing.ButtonGroup();
        lbltitle4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        lbltitle5 = new javax.swing.JLabel();
        lbltitle6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        lbltitle7 = new javax.swing.JLabel();
        lbltitle8 = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        cbTipoAnimal = new javax.swing.JComboBox();
        cbMacroGrupo = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDuplicar = new javax.swing.JButton();
        chkPesable = new javax.swing.JCheckBox();
        chkPalpable = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        cbCriaH = new javax.swing.JCheckBox();
        cbCriaM = new javax.swing.JCheckBox();
        cbDescarte = new javax.swing.JCheckBox();
        cbMuerte = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle4.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle4.setText("Tipo de animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 109, 0, 109);
        add(lbltitle4, gridBagConstraints);

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(59, 123, 50));
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcion.setBorder(null);
        txtDescripcion.setCaretColor(new java.awt.Color(59, 123, 50));
        txtDescripcion.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(txtDescripcion, gridBagConstraints);

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 210;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(jSeparator10, gridBagConstraints);

        lbltitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle5.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle5.setText("Descripción");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 109, 0, 109);
        add(lbltitle5, gridBagConstraints);

        lbltitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle6.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle6.setText("Estado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(1, 109, 0, 109);
        add(lbltitle6, gridBagConstraints);

        cbEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbEstado.setForeground(new java.awt.Color(59, 123, 50));
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(cbEstado, gridBagConstraints);

        lbltitle7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle7.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle7.setText("Macro grupo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 109, 0, 109);
        add(lbltitle7, gridBagConstraints);

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Fincas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(lbltitle8, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(cbFinca, gridBagConstraints);

        cbTipoAnimal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimal.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTipoAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(cbTipoAnimal, gridBagConstraints);

        cbMacroGrupo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbMacroGrupo.setForeground(new java.awt.Color(59, 123, 50));
        cbMacroGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbMacroGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMacroGrupoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 109, 0, 109);
        add(cbMacroGrupo, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnGuardar, gridBagConstraints);

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
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnModificar, gridBagConstraints);

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
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnDescartar, gridBagConstraints);

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
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnConsultar, gridBagConstraints);

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
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnEliminar, gridBagConstraints);

        btnDuplicar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/duplicar.png"))); // NOI18N
        btnDuplicar.setToolTipText("Duplicar registro");
        btnDuplicar.setBorderPainted(false);
        btnDuplicar.setContentAreaFilled(false);
        btnDuplicar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnDuplicar.setName("btnEliminar"); // NOI18N
        btnDuplicar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/duplicar_over.png"))); // NOI18N
        btnDuplicar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/duplicar_over.png"))); // NOI18N
        btnDuplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -20;
        gridBagConstraints.ipady = -20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(btnDuplicar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jPanel1, gridBagConstraints);

        chkPesable.setBackground(new java.awt.Color(255, 255, 255));
        chkPesable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkPesable.setForeground(new java.awt.Color(59, 123, 50));
        chkPesable.setText("Pesable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 109, 0, 109);
        add(chkPesable, gridBagConstraints);

        chkPalpable.setBackground(new java.awt.Color(255, 255, 255));
        chkPalpable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkPalpable.setForeground(new java.awt.Color(59, 123, 50));
        chkPalpable.setText("Palpable");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 109);
        add(chkPalpable, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        cbCriaH.setBackground(new java.awt.Color(255, 255, 255));
        cbCriaH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbCriaH.setForeground(new java.awt.Color(59, 123, 50));
        cbCriaH.setText("Cria Hembra");
        cbCriaH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbCriaHMousePressed(evt);
            }
        });
        jPanel2.add(cbCriaH);

        cbCriaM.setBackground(new java.awt.Color(255, 255, 255));
        cbCriaM.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbCriaM.setForeground(new java.awt.Color(59, 123, 50));
        cbCriaM.setText("Cria Macho");
        cbCriaM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbCriaMMousePressed(evt);
            }
        });
        jPanel2.add(cbCriaM);

        cbDescarte.setBackground(new java.awt.Color(255, 255, 255));
        cbDescarte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbDescarte.setForeground(new java.awt.Color(59, 123, 50));
        cbDescarte.setText("Descarte");
        cbDescarte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbDescarteMousePressed(evt);
            }
        });
        jPanel2.add(cbDescarte);

        cbMuerte.setBackground(new java.awt.Color(255, 255, 255));
        cbMuerte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cbMuerte.setForeground(new java.awt.Color(59, 123, 50));
        cbMuerte.setText("Muerte");
        cbMuerte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbMuerteMousePressed(evt);
            }
        });
        jPanel2.add(cbMuerte);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 109, 0, 109);
        add(jPanel2, gridBagConstraints);
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

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        int indice = cbFinca.getSelectedIndex();
        if (indice > 0) {
            String idFinca = fincas.get(indice).get("id");
            txtCodigoFinca.setText(idFinca);
            cargarComboTipoAnimales();
            cargarComboMacroGrupos();
        } else {
            txtCodigoFinca.setText("");
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalActionPerformed
        int indice = cbTipoAnimal.getSelectedIndex();
        if (indice > 0) {
            String idTipoAnimal = tipoAnimales.get(indice).get("id");
            txtCodigoTipoAnimal.setText(idTipoAnimal);
        } else {
            txtCodigoTipoAnimal.setText("");
        }
    }//GEN-LAST:event_cbTipoAnimalActionPerformed

    private void cbMacroGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMacroGrupoActionPerformed
        int indice = cbMacroGrupo.getSelectedIndex();
        if (indice > 0) {
            String idMacroGrupo = macroGrupos.get(indice).get("id");
            txtCodigoMacroGrupo.setText(idMacroGrupo);
        } else {
            txtCodigoMacroGrupo.setText("");
        }
    }//GEN-LAST:event_cbMacroGrupoActionPerformed

    private void btnDuplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicarActionPerformed
        Duplicar();
    }//GEN-LAST:event_btnDuplicarActionPerformed

    private void cbCriaHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCriaHMousePressed
        establecerTipoGrupo(cbCriaH);
    }//GEN-LAST:event_cbCriaHMousePressed

    private void cbCriaMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCriaMMousePressed
        establecerTipoGrupo(cbCriaM);
    }//GEN-LAST:event_cbCriaMMousePressed

    private void cbDescarteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbDescarteMousePressed
        establecerTipoGrupo(cbDescarte);
    }//GEN-LAST:event_cbDescarteMousePressed

    private void cbMuerteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbMuerteMousePressed
        establecerTipoGrupo(cbMuerte);
    }//GEN-LAST:event_cbMuerteMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoGrupos;
    public javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnDuplicar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JCheckBox cbCriaH;
    private javax.swing.JCheckBox cbCriaM;
    private javax.swing.JCheckBox cbDescarte;
    public javax.swing.JComboBox cbEstado;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbMacroGrupo;
    private javax.swing.JCheckBox cbMuerte;
    public javax.swing.JComboBox cbTipoAnimal;
    private javax.swing.JCheckBox chkPalpable;
    private javax.swing.JCheckBox chkPesable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JLabel lbltitle4;
    private javax.swing.JLabel lbltitle5;
    private javax.swing.JLabel lbltitle6;
    private javax.swing.JLabel lbltitle7;
    private javax.swing.JLabel lbltitle8;
    public javax.swing.JLabel txtCodigoFinca;
    private javax.swing.JLabel txtCodigoGrupo;
    private javax.swing.JLabel txtCodigoMacroGrupo;
    private javax.swing.JLabel txtCodigoTipoAnimal;
    public javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables

    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (cbTipoAnimal.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el tipo de animal para el grupo a crear.");
            cbTipoAnimal.requestFocusInWindow();
            return;
        }

        if (cbFinca.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la finca para el grupo a crear.");
            cbFinca.requestFocusInWindow();
            return;
        }

        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar el nombre del grupo.");
            txtDescripcion.requestFocusInWindow();
            return;
        }

        if (cbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione el estado del grupo.");
            cbEstado.requestFocusInWindow();
            return;
        }
//</editor-fold>

        String macroGrupo = (txtCodigoMacroGrupo.getText().trim().isEmpty()) ? "NULL" : cbMacroGrupo.getSelectedItem().toString();
        String codigoMacroGrupo = (txtCodigoMacroGrupo.getText().trim().isEmpty()) ? "NULL" : txtCodigoMacroGrupo.getText().trim();
        String codigoGrupo = (editar == Estado.ACTUALIZAR) ? txtCodigoGrupo.getText() : "0";
        String tipo = "'default'";
        for (int i = 0; i < checks.length; i++) {
            if (checks[i].isSelected()) {
                tipo = "'" + checks[i].getText().toLowerCase() + "'";
            }
        }

        modelo.setTipo(tipo.isEmpty() ? "null" : tipo);
        modelo.setPesable(chkPesable.isSelected() ? "1" : "0");
        modelo.setPalpable(chkPalpable.isSelected() ? "1" : "0");
        modelo.setId(codigoGrupo);
        modelo.setFecha("NOW()");
        modelo.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setDescripcion(Utilidades.CodificarElemento(txtDescripcion.getText().trim()));
        modelo.setEstado(cbEstado.getSelectedItem().toString());
        modelo.setDescMacrogrupo(macroGrupo);
        modelo.setIdMacrogrupo(codigoMacroGrupo);
        modelo.setDescTipoAnimal(cbTipoAnimal.getSelectedItem().toString());
        modelo.setIdTipoAnimal(txtCodigoTipoAnimal.getText().trim());

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
                txtDescripcion.requestFocusInWindow();
                bgTipoGrupos.clearSelection();
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
        editar = Estado.ACTUALIZAR;
    }

    private void Descartar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_DESCARTAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_DESCARTAR, botones);
        editar = Estado.GUARDAR;
    }

    private void Consultar() {
        objetoBusqueda = new ModeloGestorBusqueda(this, "BUSQUEDA_GRUPOS", 0);
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
                    bgTipoGrupos.clearSelection();
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

    private void Duplicar() {
        objetoVentana = new ModeloVentanaGeneral(this, new VistaDuplicarGrupos(), 1);
        new VistaGeneral(objetoVentana).setVisible(true);
    }

    public void RetornoBusqueda(ModeloGestorBusqueda objeto, Map<String, String> retorno) {
        if (objeto.getOpcion() == 0) {// SE LLAMA DESDE LA MISMA VISTA
            String id = retorno.get("ID");
            modelo = ((ArrayList<ModeloGrupos>) control.ObtenerDatosKey(id)).get(0);
            String macroGrupo = modelo.getDescMacrogrupo().equalsIgnoreCase("null") ? "Seleccionar" : modelo.getDescMacrogrupo();
            txtCodigoFinca.setText(modelo.getIdFinca());
            cbFinca.setSelectedItem(modelo.getDescFinca());
            boolean pesable = modelo.getPesable().equalsIgnoreCase("1");
            boolean palpable = modelo.getPalpable().equalsIgnoreCase("1");
            String tipoGrupo = modelo.getTipo();
            if (!tipoGrupo.isEmpty()) {
                for (int i = 0; i < checks.length; i++) {
                    if (checks[i].getText().toLowerCase().equals(tipoGrupo)) {
                        checks[i].setSelected(true);
                    }else{
                        checks[i].setSelected(false);
                    }
                }
            }

            cargarComboTipoAnimales();
            cargarComboMacroGrupos();

            chkPesable.setSelected(pesable);
            chkPalpable.setSelected(palpable);
            txtCodigoGrupo.setText(modelo.getId());
            cbMacroGrupo.setSelectedItem(macroGrupo);
            txtCodigoMacroGrupo.setText(modelo.getIdMacrogrupo());
            cbTipoAnimal.setSelectedItem(modelo.getDescTipoAnimal());
            txtCodigoTipoAnimal.setText(modelo.getIdTipoAnimal());
            txtDescripcion.setText(Utilidades.decodificarElemento(modelo.getDescripcion()));
            cbEstado.setSelectedItem(modelo.getEstado());

            Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_BUSCAR, controles);
            Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_BUSCAR, botones);
        } else if (objeto.getOpcion() == 1) {// SE ENVIO DESDE LA VISTA GRUPOS
            cbTipoAnimal.setSelectedItem(retorno.get("DESCRIPCION"));
            txtCodigoTipoAnimal.setText(retorno.get("ID"));
        } else if (objeto.getOpcion() == 2) {// SE ENVIO DESDE LA VISTA GRUPOS
            cbMacroGrupo.setSelectedItem(retorno.get("DESCRIPCION"));
            txtCodigoMacroGrupo.setText(retorno.get("ID"));
        }
    }

    public void RetornoVistaGeneral(ModeloVentanaGeneral referenciaVentanaGeneral, Object modelo) {
        if (referenciaVentanaGeneral.getOpcion() == 2) { //VIENE DE LA VISTA TIPO ANIMALES
            ModeloTipoAnimales tipoDeAnimal = new ModeloTipoAnimales();
            tipoDeAnimal = (ModeloTipoAnimales) modelo;
            cbTipoAnimal.setSelectedItem(tipoDeAnimal.getDescripcion());
            txtCodigoTipoAnimal.setText(tipoDeAnimal.getId());
        } else if (referenciaVentanaGeneral.getOpcion() == 3) { //VIENE DE LA VISTA MACRO GRUPOS
            ModeloMacroGrupo macroGrupo = new ModeloMacroGrupo();
            macroGrupo = (ModeloMacroGrupo) modelo;
            cbMacroGrupo.setSelectedItem(macroGrupo.getDescripcion());
            txtCodigoMacroGrupo.setText(macroGrupo.getId());
        }
    }

    private void establecerTipoGrupo(JCheckBox checkBox) {
        if (!checkBox.isSelected()) {
            for (JCheckBox check : checks) {
                if (!check.equals(checkBox)) {
                    check.setSelected(false);
                }
            }
        }
    }

}
