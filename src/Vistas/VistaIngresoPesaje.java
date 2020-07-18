/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlGeneral;
import Control.ControlPesaje;
import Control.Retorno;
import Modelo.ModeloMedicamentosPorPesaje;
import Modelo.ModeloPesaje;
import Modelo.ModeloVentanaGeneral;
import static Utilidades.Consultas.consultas;
import Utilidades.Estado;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaIngresoPesaje extends javax.swing.JPanel {

    private int consecutivo;
    private int filaAModificar = -1;
    private String idAnimal;
    private final String[] encabezados = {
        "No",
        "Medicamento",
        "Cantidad",
        "U. medida",
        "Modificar",
        "Eliminar"
    };
    private List<Map<String, String>> medicamentos;
    private List<Map<String, String>> select;
    private ControlGeneral controlGral;
    private ArrayList<Object[]> listaMedicamentos;
    private DefaultTableModel dtm;
    private ModeloPesaje modelo;
    private ModeloMedicamentosPorPesaje modelompp;
    private ControlPesaje control;
    private ModeloVentanaGeneral modeloVistaGeneral;
    private Map<String, String> datos;
    public static int guardado = -1;
    private VistaPesaje vp;
    private List<Map<String, String>> hierros;
    private final String FECHA_POR_DEFECTO = "1900-01-01";
    private int editar = Estado.DEFECTO;
    private boolean eliminar;

    /**
     * Creates new form VistaIngresoPesaje
     */
    public VistaIngresoPesaje() {
        initComponents();
        setSize(634, 600);
        consecutivo = 0;
        listaMedicamentos = new ArrayList<>();
        btnModificar.setVisible(false);
        modelo = new ModeloPesaje();
        modelompp = new ModeloMedicamentosPorPesaje();
        control = new ControlPesaje();
    }

    public VistaIngresoPesaje(ModeloVentanaGeneral modeloVistaGeneral) {
        initComponents();
        eliminar = false;
        setSize(634, 600);
        btnModificar.setVisible(false);
        consecutivo = 0;
        controlGral = new ControlGeneral();
        cargarComboMedicamento();
        guardado = -1;
        this.modeloVistaGeneral = modeloVistaGeneral;
        datos = new HashMap<>();
        datos = (Map<String, String>) modeloVistaGeneral.getModeloDatos();
        this.vp = ((VistaPesaje) modeloVistaGeneral.getPanelPadre());
        listaMedicamentos = new ArrayList<>();
        editar = modeloVistaGeneral.getOpcion() == 1 ? Estado.GUARDAR : Estado.ACTUALIZAR;

        dtm = new DefaultTableModel(encabezados, 0) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        tablaMedicamentos.setModel(dtm);
        tablaMedicamentos.getTableHeader().setReorderingAllowed(false);
        modelo = new ModeloPesaje();
        modelompp = new ModeloMedicamentosPorPesaje();
        control = new ControlPesaje();

        IniciarFecha();
        if (editar == Estado.GUARDAR) {
            iniciarChecks();
            cargarComboHierros();
            cargarDatosActuales();
        } else {
            cargarDatosEditar();
        }

        lblFechaDestete.setVisible(chkDestete.isSelected());
        jdFechaDestete.setVisible(chkDestete.isSelected());

        lblPesoDestete.setVisible(chkDestete.isSelected());
        txtPesoDestete.setVisible(chkDestete.isSelected());
        sepPesoDestete.setVisible(chkDestete.isSelected());

        lblHierro.setVisible(chkHierro.isSelected());
        cbHierros.setVisible(chkHierro.isSelected());
    }

    public void IniciarFecha() {
        if (vp.fechaAnterior.isEmpty()) {
            Calendar cal = Calendar.getInstance();
            jdFechaPesaje.setCalendar(cal);
            return;
        }
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(vp.fechaAnterior);
            jdFechaPesaje.setDate(fecha);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error tratando de establecer la fecha de pesaje");
        }
    }

    private void iniciarChecks() {
        chkImplante.setSelected(datos.get("IMPLANTE").equals("1"));
        chkDescornada.setSelected(datos.get("DESCORNADO").equals("1"));
        chkDestete.setSelected(!datos.get("FECHA_DESTETE").equals(FECHA_POR_DEFECTO));
        chkHierro.setSelected(datos.get("DESC_HIERRO").length() > 0);
    }

    private void cargarDatosActuales() {
        idAnimal = datos.get("ID_ANIMAL");

        txtReferenciaAnimal.setText("<html><p>Número madre: <b>" + datos.get("NUMERO_MAMA") + "</b> Número animal: <b>" + datos.get("NUMERO_ANIMAL") + "</b></p></html>");
        txtPesoActual.setText(datos.get("PESO_ANTERIOR") + " Kg");
        cbHierros.setSelectedItem(datos.get("DESC_HIERRO"));
        txtCodigoHierro.setText(datos.get("IDHIERRO"));
        txtPesoDestete.setText(datos.get("PESO_DESTETE"));
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = formato.parse(datos.get("FECHA_DESTETE"));
            jdFechaDestete.setDate(fecha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando la fecha de destete.");
        }
    }

    private void cargarComboMedicamento() {
        String consulta = consultas.get("CARGAR_COMBO_MEDICAMENTOS");
        medicamentos = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbMedicamentos, medicamentos, "descripcion");
    }

    private void cargarComboHierros() {
        String consulta = consultas.get("CARGAR_COMBO_HIERROS_TOTAL");
        hierros = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbHierros, hierros, "descripcion");
    }

    private void cargarDatosEditar() {
        iniciarChecks();
        cargarComboHierros();
        cargarDatosActuales();
        txtPesoKg.setText(datos.get("PESO"));
        txtNotas.setText(Utilidades.decodificarElemento(datos.get("NOTAS")));
        txtCodigo.setText(datos.get("ID_PESAJE"));
        calcularPesoEnLibras();
        calcularDiferenciaPesos();
        getMedicamentos(datos.get("ID_PESAJE"));

    }

    private void getMedicamentos(String idPesaje) {
        String consulta = consultas.get("GET_MEDICAMENTOS_POR_PESAJE") + idPesaje;
        select = controlGral.GetComboBox(consulta);
        dtm = (DefaultTableModel) tablaMedicamentos.getModel();
        eliminar = select.size() > 0;

        for (Map<String, String> lista : select) {
            Object[] fila = new Object[]{
                ++consecutivo,//idMedicamento
                lista.get("DESCRIPCION"),//descripcionMedicamento
                lista.get("CANTIDAD").replace(".", ","),//CantidadMedicamento
                lista.get("UNIDAD_MEDIDA"),//UnidadDeMedida
                "Modificar",
                "Eliminar"
            };
            dtm.addRow(fila);
            tablaMedicamentos.setModel(dtm);
            listaMedicamentos.add(fila);
        }
    }

    public boolean isEditando() {
        return editar == Estado.ACTUALIZAR;
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
        txtCodigoHierro = new javax.swing.JLabel();
        txtPesoKg = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        lbltitle14 = new javax.swing.JLabel();
        lbltitle18 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        chkHierro = new javax.swing.JCheckBox();
        chkImplante = new javax.swing.JCheckBox();
        chkDescornada = new javax.swing.JCheckBox();
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
        lbltitle17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar1 = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        chkDestete = new javax.swing.JCheckBox();
        jdFechaPesaje = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        lblHierro = new javax.swing.JLabel();
        cbHierros = new javax.swing.JComboBox();
        jdFechaDestete = new com.toedter.calendar.JDateChooser();
        lblFechaDestete = new javax.swing.JLabel();
        txtPesoActual = new javax.swing.JLabel();
        lblPesoDestete = new javax.swing.JLabel();
        txtPesoDestete = new javax.swing.JTextField();
        sepPesoDestete = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPesoKg, gridBagConstraints);

        jSeparator13.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator13, gridBagConstraints);

        lbltitle14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle14.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle14.setText("Notas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(lbltitle14, gridBagConstraints);

        lbltitle18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle18.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle18.setText("Peso (Libras)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle18, gridBagConstraints);

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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPeso, gridBagConstraints);

        jSeparator11.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator11, gridBagConstraints);

        chkHierro.setBackground(new java.awt.Color(255, 255, 255));
        chkHierro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkHierro.setForeground(new java.awt.Color(59, 123, 50));
        chkHierro.setText("Hierro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkHierro, gridBagConstraints);

        chkImplante.setBackground(new java.awt.Color(255, 255, 255));
        chkImplante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkImplante.setForeground(new java.awt.Color(59, 123, 50));
        chkImplante.setText("Implante");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkImplante, gridBagConstraints);

        chkDescornada.setBackground(new java.awt.Color(255, 255, 255));
        chkDescornada.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDescornada.setForeground(new java.awt.Color(59, 123, 50));
        chkDescornada.setText("Descornada");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(chkDescornada, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.466666667;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jPanel1, gridBagConstraints);

        txtNotas.setColumns(20);
        txtNotas.setRows(5);
        jScrollPane2.setViewportView(txtNotas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.233333333;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jScrollPane2, gridBagConstraints);

        txtReferenciaAnimal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtReferenciaAnimal.setForeground(new java.awt.Color(59, 123, 50));
        txtReferenciaAnimal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 5, 15);
        add(txtReferenciaAnimal, gridBagConstraints);

        lbltitle17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle17.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle17.setText("Peso (Kilos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle17, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 15);
        add(jPanel2, gridBagConstraints);

        chkDestete.setBackground(new java.awt.Color(255, 255, 255));
        chkDestete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDestete.setForeground(new java.awt.Color(59, 123, 50));
        chkDestete.setText("Destete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(chkDestete, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaPesaje, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lbltitle19, gridBagConstraints);

        lblHierro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblHierro.setForeground(new java.awt.Color(59, 123, 50));
        lblHierro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHierro.setText("Hierros");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(lblHierro, gridBagConstraints);

        cbHierros.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbHierros.setForeground(new java.awt.Color(59, 123, 50));
        cbHierros.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbHierros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHierrosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(cbHierros, gridBagConstraints);

        jdFechaDestete.setDateFormatString("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jdFechaDestete, gridBagConstraints);

        lblFechaDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFechaDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblFechaDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblFechaDestete.setText("Fecha de destete");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lblFechaDestete, gridBagConstraints);

        txtPesoActual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPesoActual.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPesoActual.setToolTipText("Peso anterior (Kilogramos)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(txtPesoActual, gridBagConstraints);

        lblPesoDestete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        lblPesoDestete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPesoDestete.setText("Peso de destete (Kg)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 0);
        add(lblPesoDestete, gridBagConstraints);

        txtPesoDestete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPesoDestete.setForeground(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPesoDestete.setBorder(null);
        txtPesoDestete.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPesoDestete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPesoDesteteFocusLost(evt);
            }
        });
        txtPesoDestete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesoDesteteKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtPesoDestete, gridBagConstraints);

        sepPesoDestete.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(sepPesoDestete, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

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
            Utilidades.formatearNumeros(txtPeso);
        }
    }

    private String convertirALibras(double pesoEnKilos) {
        Double resultado = pesoEnKilos * Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        return "" + resultadoRedondeado;
    }

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

    private String convertirAKilogramos(double pesoEnLibras) {
        Double resultado = pesoEnLibras / Utilidades.FACTOR_CONVERSION;
        long resultadoRedondeado = Math.round(resultado);
        return "" + resultadoRedondeado;
    }

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
        System.out.println("editar: " + editar);
        if (!isEditando()) {
            vp.tbl_Animales.setValueAt("", vp.filaSeleccionada, 11);
        }
        vp.band = 0;
        ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbHierrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHierrosActionPerformed
        int indice = cbHierros.getSelectedIndex();
        if (indice > 0) {
            String idHierro = hierros.get(indice).get("id");
            txtCodigoHierro.setText(idHierro);
        }
    }//GEN-LAST:event_cbHierrosActionPerformed

    private void txtPesoKgFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoKgFocusLost
        calcularDiferenciaPesos();
    }//GEN-LAST:event_txtPesoKgFocusLost

    private void txtPesoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoFocusLost
        calcularDiferenciaPesos();
    }//GEN-LAST:event_txtPesoFocusLost

    private void txtPesoDesteteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPesoDesteteFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoDesteteFocusLost

    private void txtPesoDesteteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesoDesteteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoDesteteKeyReleased

    private void calcularDiferenciaPesos() {
        if (txtPesoKg.getText().length() == 0) {
            txtPesoActual.setText(datos.get("PESO_ANTERIOR") + " Kg");
        } else {
            int pesoAnterior = Integer.parseInt(datos.get("PESO_ANTERIOR"));
            int pesoActual = Integer.parseInt(txtPesoKg.getText());
            int diferencia = pesoActual - pesoAnterior;
            txtPesoActual.setText(pesoActual + " Kg - " + pesoAnterior + " Kg = " + diferencia + " Kg");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbHierros;
    public javax.swing.JComboBox cbMedicamentos;
    private javax.swing.JCheckBox chkDescornada;
    private javax.swing.JCheckBox chkDestete;
    private javax.swing.JCheckBox chkHierro;
    private javax.swing.JCheckBox chkImplante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private com.toedter.calendar.JDateChooser jdFechaDestete;
    private com.toedter.calendar.JDateChooser jdFechaPesaje;
    private javax.swing.JLabel lblFechaDestete;
    private javax.swing.JLabel lblHierro;
    private javax.swing.JLabel lblPesoDestete;
    private javax.swing.JLabel lbltitle10;
    private javax.swing.JLabel lbltitle14;
    private javax.swing.JLabel lbltitle16;
    private javax.swing.JLabel lbltitle17;
    private javax.swing.JLabel lbltitle18;
    private javax.swing.JLabel lbltitle19;
    private javax.swing.JSeparator sepPesoDestete;
    private javax.swing.JTable tablaMedicamentos;
    public javax.swing.JTextField txtCantidadMedicamento;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JLabel txtCodigoHierro;
    private javax.swing.JLabel txtCodigoMedicamento;
    private javax.swing.JTextArea txtNotas;
    public javax.swing.JTextField txtPeso;
    private javax.swing.JLabel txtPesoActual;
    public javax.swing.JTextField txtPesoDestete;
    public javax.swing.JTextField txtPesoKg;
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
            if (txtCodigoMedicamento.getText().equals(listaMedicamentos.get(i)[0].toString())) {
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
            cbMedicamentos.getSelectedItem().toString().split("\\(")[0].trim(),//descripcionMedicamento
            txtCantidadMedicamento.getText(),//CantidadMedicamento
            getUnidadMedida(),//CantidadMedicamento
            "Modificar",
            "Eliminar"
        });
        txtCantidadMedicamento.setText("");
        cbMedicamentos.setSelectedIndex(0);
        cbMedicamentos.requestFocusInWindow();
    }

    private Object[] getFila() {
        return new Object[]{
            ++consecutivo,
            cbMedicamentos.getSelectedItem().toString().split("\\(")[0].trim(),//descripcionMedicamento
            txtCantidadMedicamento.getText(),//CantidadMedicamento
            getUnidadMedida(),//CantidadMedicamento
            "Modificar",
            "Eliminar"
        };
    }

    private String getUnidadMedida() {
        String unidadMedida = cbMedicamentos.getSelectedItem().toString().split("\\(")[1];
        unidadMedida = unidadMedida.substring(0, unidadMedida.length() - 1);
        return unidadMedida;
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
                listaMedicamentos.get(i)[4],
                listaMedicamentos.get(i)[5]
            });
        }
        tablaMedicamentos.setModel(dtm);
    }

    private void cambiarMedicamento() {
        btnModificar.setVisible(true);
        btnAgregar.setVisible(false);
        String medicamento = tablaMedicamentos.getValueAt(filaAModificar, 1).toString();
        String cantidad = tablaMedicamentos.getValueAt(filaAModificar, 2).toString();
        String unidadMedida = tablaMedicamentos.getValueAt(filaAModificar, 3).toString();
        cbMedicamentos.setSelectedItem(medicamento + " (" + unidadMedida + ")");
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
        listaMedicamentos.get(filaAModificar)[1] = cbMedicamentos.getSelectedItem().toString().split("\\(")[0].trim();
        listaMedicamentos.get(filaAModificar)[2] = txtCantidadMedicamento.getText();
        listaMedicamentos.get(filaAModificar)[3] = getUnidadMedida();

        reorganizarMedicamentos();
        btnAgregar.setVisible(true);
        btnModificar.setVisible(false);
        filaAModificar = -1;
    }

    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (txtPesoKg.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nuevo peso del animal.");
            txtCantidadMedicamento.requestFocusInWindow();
            return;
        }

        if (listaMedicamentos.size() == 0) {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Esta seguro de guardar el pesaje sin agregar los medicamentos?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.NO_OPTION) {
                return;
            }
        }
//</editor-fold>

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfes = new SimpleDateFormat("dd/MM/yyyy");
        Calendar fecha = jdFechaPesaje.getCalendar();
        String estado = "";
        String fechaEstadoActivo = "";
        if (!datos.get("ESTADO").equals("Activo")) {
            fechaEstadoActivo = control.getFechaPesajeActiva(idAnimal);
        } else {
            fechaEstadoActivo = datos.get("FECHAPESADO");
        }
        estado = getEstadoGuardar(sdfes.format(fecha.getTime()), fechaEstadoActivo);
        System.out.println("estado: " + estado);
        modelo.setEstado(estado);
        modelo.setPeso_anterior(datos.get("PESO_ANTERIOR"));
        modelo.setId(editar == Estado.GUARDAR ? "0" : txtCodigo.getText());
        modelo.setId_animal(idAnimal);
        modelo.setFecha_pesado(sdf.format(fecha.getTime()));
        modelo.setPeso(txtPesoKg.getText().replace(".", "").replace(",", "."));
        modelo.setNotas(Utilidades.CodificarElemento(txtNotas.getText().trim()));
        modelo.setDescornado(chkDescornada.isSelected() ? "1" : "0");
        modelo.setImplante(chkImplante.isSelected() ? "1" : "0");
        modelo.setHierro(chkHierro.isSelected() ? "1" : "0");
        modelo.setDestete(chkDestete.isSelected() ? "1" : "0");
        modelo.setDescripcionHierro(cbHierros.getSelectedItem().toString());
        modelo.setFecha("NOW()");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setIdHierro(txtCodigoHierro.getText());

        Calendar fechaPesaje = jdFechaPesaje.getCalendar();
        modelo.setFecha_pesado(sdf.format(fechaPesaje.getTime()));

        if (chkDestete.isSelected()) {
            Calendar fechaDestete = jdFechaDestete.getCalendar();
            modelo.setFechaDestete(sdf.format(fechaDestete.getTime()));
            modelo.setPeso_destete(txtPesoDestete.getText());
        } else {
            modelo.setFechaDestete(FECHA_POR_DEFECTO);
        }

        for (int i = 0; i < listaMedicamentos.size(); i++) {
            modelompp.setEliminar(eliminar);
            modelompp = new ModeloMedicamentosPorPesaje();
            modelompp.setId_medicamento(listaMedicamentos.get(i)[0].toString());
            modelompp.setDosis(listaMedicamentos.get(i)[2].toString().replace(".", "").replace(",", "."));
            modelo.addMedicamentos(modelompp);
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
                guardado = editar;
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
        if (retorno == Retorno.EXITO) {
            int ret = control.ActualizarPesos("CALL actualizarPesos(" + idAnimal + ", " + datosUsuario.datos.get(0).get("ID_USUARIO") + ");");
            if (ret == Retorno.EXITO) {
                System.out.println("---------------------actualizarPesos OK");
            } else {
                System.out.println("---------------------actualizarPesos ERROR");
            }
            establecerRegistroPesado(idAnimal);
            vp.band = 0;
            ((VistaGeneral) modeloVistaGeneral.getFrameVentana()).dispose();
        }
    }

    private void establecerRegistroPesado(String idAnimal) {
        for (int i = 0; i < vp.ListaAnimales.size(); i++) {
            String id = vp.ListaAnimales.get(i).get("ID_ANIMAL");
            if (id.equals(idAnimal)) {
                vp.ListaAnimales.get(i).put("EST", "*");
                vp.ListaAnimales.get(i).put("PESO", modelo.getPeso());
                vp.ListaAnimales.get(i).put("DESC_HIERRO", modelo.getDescripcionHierro());
                vp.fechaAnterior = modelo.getFecha_pesado();
                try {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha = formato.parse(vp.fechaAnterior);
                    vp.jdFechaPesaje.setDate(fecha);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "Error tratando de establecer la fecha de pesaje");
                }
                vp.cargarTablaFiltro();
                return;
            }
        }
    }

    private String getEstadoGuardar(String fechaForm, String fechaEstadoActivo) {
        String estado = "Activo";
        if (!fechaEstadoActivo.equals("")) {
            int dif = Utilidades.CompararFechas(fechaForm, fechaEstadoActivo);
            estado = (dif > 0 ? "Activo" : "Inactivo");
        }
        return estado;
    }

}
