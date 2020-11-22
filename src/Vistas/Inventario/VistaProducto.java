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
public class VistaProducto extends javax.swing.JPanel implements IControlesUsuario {

    private ControlGeneral controlgen = new ControlGeneral();
    private GestionEstadoControles controles;
    private ModeloProducto modelo;
    private ControlInventario control;
    private ModeloLibro modeloLibro;
    private ControlLibro controlLD;
    private List<Map<String, String>> listaFincas;
    private List<Map<String, String>> listaProductos;
    private String idFinca;
    private String id_producto;
    private ModeloVentanaGeneral vg;

    public VistaProducto() {
        initComponents();
        iniciarComponentes();
        setSize(600, 330);
        listaProductos = new ArrayList<>();
        listaFincas = new ArrayList<>();
        modeloLibro = new ModeloLibro();
        controles.habilitarControles();
        modelo = new ModeloProducto();
        control = new ControlInventario();
        jdFecha.setCalendar(Calendar.getInstance());
        id_producto = "0";
        CargarListaFincas();
    }

    public VistaProducto(ModeloVentanaGeneral modeloVista) {
        initComponents();
        iniciarComponentes();
        setSize(600, 330);
        vg = modeloVista;
        listaProductos = new ArrayList<>();
        listaFincas = new ArrayList<>();
        modeloLibro = new ModeloLibro();
        controles.habilitarControles();
        modelo = new ModeloProducto();
        control = new ControlInventario();
        jdFecha.setCalendar(Calendar.getInstance());
        id_producto = "0";
        CargarListaFincas();
        CargarListaProductos();
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

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM fincas\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
    }

    private void CargarListaProductos() {
        listaProductos = controlgen.GetComboBox("SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM productos");
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
        lblTid = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 15, 0, 15, 0, 15, 0, 15, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
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
        gridBagConstraints.gridy = 8;
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        add(txtPrecioUnidad, gridBagConstraints);

        jdFecha.setBackground(new java.awt.Color(255, 255, 255));
        jdFecha.setForeground(new java.awt.Color(59, 123, 50));
        jdFecha.setDateFormatString("dd/MM/yyyy");
        jdFecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(txtProducto, gridBagConstraints);

        cbTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipo.setForeground(new java.awt.Color(59, 123, 50));
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecciona...", "unidad", "peso", "metro" }));
        cbTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)), "Tipo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        cbTipo.setEditor(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel2.add(btnDescartar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(jPanel2, gridBagConstraints);

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(lblTid, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        add(cbFinca, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        String areat = txtCantidad.getText();
        String valorsin = areat.indexOf(".") > -1 ? areat.replace(".", "") : areat;
        String dato = Expresiones.procesarSoloNumP(valorsin);
        dato = Utilidades.MascaraMonedaConDecimales(dato);
        txtCantidad.setText(dato);
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
        if (cbFinca.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una finca.");
            cbFinca.requestFocusInWindow();
            return;
        }
//</editor-fold>
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        Descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
    }//GEN-LAST:event_cbFincaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipo;
    private javax.swing.JPanel jPanel2;
    public com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lbltitle19;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtPrecioUnidad;
    public javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables

    private void Guardar() {
        String nombreProducto = txtProducto.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFecha.getCalendar();
        modelo.setFecha("'" + sdf.format(fecha.getTime()) + "'");

        modelo.setDescripcion(Utilidades.CodificarElemento(nombreProducto));
        modelo.setEstado("Activo");
        modelo.setId("0");
        modelo.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modelo.setTipo_salida(cbTipo.getSelectedItem().toString());

        //<editor-fold defaultstate="collapsed" desc="LIBRO DIARIO">
        modeloLibro.setCantidad(txtCantidad.getText().trim());
        modeloLibro.setDebe("0");
        modeloLibro.setDetalle(Utilidades.CodificarElemento(txtProducto.getText().trim()));
        modeloLibro.setFecha("NOW()");
        modeloLibro.setFecha_libro("NOW()");
        modeloLibro.setHaber("0");
        modeloLibro.setId("0");
        modeloLibro.setId_finca(idFinca);
        modeloLibro.setId_usuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
        modeloLibro.setPrecioxunidad(txtPrecioUnidad.getText().trim());
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
            JOptionPane.showMessageDialog(this, "Hubo un error al momento de ingresar la entrada del producto.");
            return;
        }
        int ret_Inventario = control.GuardarInventario(modeloLibro);
        if (ret_Inventario != Retorno.EXITO) {
            JOptionPane.showMessageDialog(this, "Hubo un error al momento de Actualizar el producto al Inventario.");
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Registro ingresado satisfactoriamente.");

        ((VistaGeneral) vg.getFrameVentana()).dispose();
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

}
