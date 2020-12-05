/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Inventario;

import Control.ControlGeneral;
import Control.Inventario.*;
import Control.Retorno;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.Inventario.ModeloLibro;
import Modelo.Inventario.ModeloProducto;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import Vistas.IControlesUsuario;
import Vistas.VistaGeneral;
import com.toedter.calendar.JCalendar;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaProducto extends javax.swing.JPanel implements IControlesUsuario {

    private ControlGeneral controlgen = new ControlGeneral();
    private GestionEstadoControles controles;
    private ModeloProducto modelo;
    private ControlInventario control;
    private ModeloLibro modeloLibro;
    private ControlLibro controlLD;
    private List<Map<String, String>> listaProductos;
    private String idFinca;
    private String id_producto;
    private double resultado;
    public Map<String, String> producto;
    private VistaInventario vistaInventario;
    private VistaGeneral vistaGeneral;
    private int opcion;

    public VistaProducto() {
        initComponents();
        iniciarComponentes();
        setSize(430, 270);
        listaProductos = new ArrayList<>();
        modeloLibro = new ModeloLibro();
        controles.habilitarControles();
        modelo = new ModeloProducto();
        control = new ControlInventario();
        jdFecha.setCalendar(Calendar.getInstance());
        id_producto = "0";
        CargarListaProductos();
    }

    public VistaProducto(ModeloVentanaGeneral modeloVista) {
        initComponents();
        iniciarComponentes();
        controles.habilitarControles();
        setSize(430, 270);
        controlLD = new ControlLibro();
        vistaInventario = (VistaInventario) modeloVista.getPanelPadre();
        vistaGeneral = (VistaGeneral) modeloVista.getFrameVentana();
        listaProductos = new ArrayList<>();
        modeloLibro = new ModeloLibro();
        modelo = new ModeloProducto();
        control = new ControlInventario();
        jdFecha.setCalendar(Calendar.getInstance());
        opcion = modeloVista.getOpcion();

        boolean mostrar = opcion == 2;
        btnCancelar.setVisible(mostrar);
        btnDescartar.setVisible(!mostrar);
        if (opcion == 1) {
            idFinca = modeloVista.getModeloDatos().toString();
            id_producto = "0";
        }
        if (opcion == 2) {
            producto = new HashMap<String, String>();
            producto = (HashMap<String, String>) modeloVista.getModeloDatos();
            vistaInventario = (VistaInventario) modeloVista.getPanelPadre();
            vistaGeneral = (VistaGeneral) modeloVista.getFrameVentana();
            cargarProducto(producto);
        }
    }

    @Override
    public void iniciarComponentes() {
        controles = new GestionEstadoControles();

        Control control = new Control(true, txtProducto);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtCantidad);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, cbTipo);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, txtPrecioUnidad);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);

        control = new Control(true, jdFecha);
        control.setLimpiarDespuesDeGuardar(true);
        controles.addControl(control);
    }

    private void CargarListaProductos() {
        listaProductos = controlgen.GetComboBox("SELECT id AS ID, descripcion AS DESCRIPCION FROM productos");
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

        txtCantidad = new javax.swing.JTextField();
        txtPrecioUnidad = new javax.swing.JTextField();
        jdFecha = new com.toedter.calendar.JDateChooser();
        lbltitle19 = new javax.swing.JLabel();
        txtProducto = new javax.swing.JTextField();
        cbTipo = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCalculo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
        setLayout(layout);

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(59, 123, 50));
        txtCantidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Cantidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtCantidad.setCaretColor(new java.awt.Color(59, 123, 50));
        txtCantidad.setFocusCycleRoot(true);
        txtCantidad.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.25;
        add(txtCantidad, gridBagConstraints);

        txtPrecioUnidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioUnidad.setForeground(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Precio/Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtPrecioUnidad.setCaretColor(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.setFocusCycleRoot(true);
        txtPrecioUnidad.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtPrecioUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioUnidadKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        add(txtPrecioUnidad, gridBagConstraints);

        jdFecha.setBackground(new java.awt.Color(255, 255, 255));
        jdFecha.setForeground(new java.awt.Color(59, 123, 50));
        jdFecha.setDateFormatString("dd/MM/yyyy");
        jdFecha.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.25;
        add(jdFecha, gridBagConstraints);

        lbltitle19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle19.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle19.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(lbltitle19, gridBagConstraints);

        txtProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProducto.setForeground(new java.awt.Color(59, 123, 50));
        txtProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Nombre del producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        txtProducto.setCaretColor(new java.awt.Color(59, 123, 50));
        txtProducto.setFocusCycleRoot(true);
        txtProducto.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.75;
        add(txtProducto, gridBagConstraints);

        cbTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipo.setForeground(new java.awt.Color(59, 123, 50));
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona...", "unidad", "peso", "metros" }));
        cbTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTipo.setEditor(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = -3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.25;
        add(cbTipo, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/guardar.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnGuardar.setMaximumSize(new java.awt.Dimension(64, 64));
        btnGuardar.setMinimumSize(new java.awt.Dimension(64, 64));
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.setPreferredSize(new java.awt.Dimension(64, 64));
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

        btnDescartar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar.png"))); // NOI18N
        btnDescartar.setToolTipText("Descartar");
        btnDescartar.setBorderPainted(false);
        btnDescartar.setContentAreaFilled(false);
        btnDescartar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDescartar.setFocusPainted(false);
        btnDescartar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnDescartar.setMaximumSize(new java.awt.Dimension(64, 64));
        btnDescartar.setMinimumSize(new java.awt.Dimension(64, 64));
        btnDescartar.setPreferredSize(new java.awt.Dimension(64, 64));
        btnDescartar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar_over.png"))); // NOI18N
        btnDescartar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/descartar_over.png"))); // NOI18N
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnDescartar, gridBagConstraints);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cancelar.png"))); // NOI18N
        btnCancelar.setToolTipText("Eliminar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setMargin(new java.awt.Insets(2, 10, 2, 8));
        btnCancelar.setMaximumSize(new java.awt.Dimension(64, 64));
        btnCancelar.setMinimumSize(new java.awt.Dimension(64, 64));
        btnCancelar.setName("btnCancelar"); // NOI18N
        btnCancelar.setPreferredSize(new java.awt.Dimension(64, 64));
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        lblCalculo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCalculo.setForeground(new java.awt.Color(59, 123, 50));
        lblCalculo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(lblCalculo, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        Utilidades.setFormatoNumerico(txtCantidad);
        calcularPrecioPorCantidad();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (txtProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe especificar un nombre para el producto a crear.");
            txtProducto.requestFocusInWindow();
            return;
        }
        if (txtCantidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "La cantidad no puede ser vacia");
            txtCantidad.requestFocusInWindow();
            return;
        }
        if (txtPrecioUnidad.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Especifique el precio por unidad del producto.");
            txtPrecioUnidad.requestFocusInWindow();
            return;
        }
        if (cbTipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo.");
            cbTipo.requestFocusInWindow();
            return;
        }
//</editor-fold>

        if (opcion == 2) {
            AgregarEntrada();
        } else {
            Guardar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        Descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void txtPrecioUnidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioUnidadKeyReleased
        Utilidades.setFormatoNumerico(txtPrecioUnidad);
        calcularPrecioPorCantidad();
    }//GEN-LAST:event_txtPrecioUnidadKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        vistaGeneral.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbTipo;
    private javax.swing.JPanel jPanel2;
    public com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel lblCalculo;
    private javax.swing.JLabel lbltitle19;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtPrecioUnidad;
    public javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables

    private void Guardar() {
        String nombreProducto = txtProducto.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFecha.getCalendar();
        modelo.setFecha("NOW()");

        modelo.setDescripcion(Utilidades.CodificarElemento(nombreProducto));
        modelo.setEstado("Activo");
        modelo.setId("0");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setTipo_salida(cbTipo.getSelectedItem().toString());

        //<editor-fold defaultstate="collapsed" desc="LIBRO DIARIO">
        modeloLibro.setCantidad(txtCantidad.getText().replace(".", "").replace(",", "."));
        modeloLibro.setDebe(String.valueOf(resultado));
        modeloLibro.setDetalle(Utilidades.CodificarElemento(txtProducto.getText().trim()));
        modeloLibro.setFecha("NOW()");
        modeloLibro.setFecha_libro(sdf.format(fecha.getTime()));
        modeloLibro.setHaber("0");
        modeloLibro.setId("0");
        modeloLibro.setId_finca(idFinca);
        modeloLibro.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modeloLibro.setPrecioxunidad(txtPrecioUnidad.getText().replace(".", "").replace(",", "."));
        modeloLibro.setSaldo("0");
        modeloLibro.setId_producto(id_producto);
//</editor-fold>

        int retorno = Retorno.DEFECTO;

        retorno = control.Guardar(modelo);

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                CargarListaProductos();
                id_producto = getIDProducto(Utilidades.CodificarElemento(nombreProducto));
                modeloLibro.setId_producto(id_producto);
                break;
            case Retorno.ERROR:
                mensaje = "El registro no pudo ser guardado.";
                JOptionPane.showMessageDialog(this, mensaje);
                return;
            case Retorno.EXCEPCION_SQL:
                mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                JOptionPane.showMessageDialog(this, mensaje);
                return;
            case Retorno.CLASE_NO_ENCONTRADA:
                mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                JOptionPane.showMessageDialog(this, mensaje);
                return;
        }

        int ret_entrada = control.GuardarEntrada(modeloLibro);
        if (ret_entrada != Retorno.EXITO) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al momento de ingresar la entrada del producto.");
            return;
        }
        int ret_Inventario = control.GuardarInventario(modeloLibro);
        if (ret_Inventario != Retorno.EXITO) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al momento de Actualizar el producto en el Inventario.");
            return;
        }
        int ret_libroDiario = control.GuardarLibroDiario(modeloLibro);
        if (ret_libroDiario != Retorno.EXITO) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al momento de guardar en el libro diario.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Registro ingresado satisfactoriamente.");

        vistaInventario.AccionCombo();
        vistaGeneral.dispose();
    }

    private void AgregarEntrada() {
        //<editor-fold defaultstate="collapsed" desc="LIBRO DIARIO">
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFecha.getCalendar();
        modeloLibro.setCantidad(txtCantidad.getText().replace(".", "").replace(",", "."));
        modeloLibro.setDebe(String.valueOf(resultado));
        modeloLibro.setDetalle(Utilidades.CodificarElemento(txtProducto.getText().trim()));
        modeloLibro.setFecha("NOW()");
        modeloLibro.setFecha_libro(sdf.format(fecha.getTime()));
        modeloLibro.setHaber("0");
        modeloLibro.setId("0");
        modeloLibro.setId_finca(idFinca);
        modeloLibro.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modeloLibro.setPrecioxunidad(txtPrecioUnidad.getText().replace(".", "").replace(",", "."));
        modeloLibro.setSaldo("0");
        modeloLibro.setId_producto(id_producto);
//</editor-fold>

        int ret_entrada = control.GuardarEntrada(modeloLibro);
        if (ret_entrada != Retorno.EXITO) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de ingresar la Entrada del producto.");
            return;
        }

        int ret_Inventario = control.ActualizarEntradaInventario(modeloLibro);
        if (ret_Inventario != Retorno.EXITO) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de actualizar el producto en el inventario.");
            return;
        }

        int ret_libro = control.GuardarLibroDiario(modeloLibro);
        if (ret_libro != Retorno.EXITO) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de guardar el producto en el libro diario.");
            return;
        }

        int ret_splibro = controlLD.ActualizarSaldos("CALL ActualizarSaldoLibroDiario(" + idFinca + ");");
        if (ret_splibro != Retorno.EXITO) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de actualizar los saldos en el libro diario.");
            return;
        }
        JOptionPane.showMessageDialog(null, "La operación se realizo con exito");
        vistaInventario.AccionCombo();
        vistaGeneral.dispose();
    }

    private void Descartar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_DESCARTAR, controles);
    }

    private String getIDProducto(String producto) {
        String ret = "";
        for (Map<String, String> map : listaProductos) {
            if (producto.equals(map.get("DESCRIPCION"))) {
                ret = map.get("ID");
                break;
            }
        }
        return ret;
    }

    private void calcularPrecioPorCantidad() {
        String strCantidad = txtCantidad.getText();
        String strPrecio = txtPrecioUnidad.getText();

        strCantidad = strCantidad.length() == 0 ? "0" : strCantidad.replace(".", "").replace(",", ".");
        strPrecio = strPrecio.length() == 0 ? "0" : strPrecio.replace(".", "").replace(",", ".");

        double cantidad = Double.parseDouble(strCantidad);
        double precio = Double.parseDouble(strPrecio);
        resultado = precio * cantidad;
        DecimalFormat formato = new DecimalFormat("#,###.##");
        String resultadoFormat = formato.format(resultado);
        System.out.println("numero: " + resultadoFormat);

        lblCalculo.setText(resultadoFormat);
        Utilidades.setFormatoNumerico(lblCalculo);
        lblCalculo.setText("<html>"
                + "<p>"
                + "<b>Valor total: </b>"
                + "<span>" + lblCalculo.getText() + "</span>"
                + "</p>"
                + "</html>");
    }

    private void cargarProducto(Map<String, String> producto) {
        id_producto = producto.get("id_producto");
        idFinca = producto.get("id_finca");
        txtProducto.setText(producto.get("PRODUCTO"));
        txtProducto.setEnabled(false);
        txtCantidad.setText("");
        Utilidades.setFormatoNumerico(txtCantidad);
        txtPrecioUnidad.setText("");
        Utilidades.setFormatoNumerico(txtPrecioUnidad);
        cbTipo.setSelectedIndex(0);
        jdFecha.setCalendar(Calendar.getInstance());
        calcularPrecioPorCantidad();
    }

}
