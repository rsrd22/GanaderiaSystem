/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Busqueda.VistaBusqueda;
import Configuracion.InformacionGlobal;
import Control.ControlGeneral;
import Control.ControlTipoAnimales;
import Control.Retorno;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.ModeloGestorBusqueda;
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
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaTipoAnimales extends javax.swing.JPanel implements IControlesUsuario {

    private ModeloGestorBusqueda objetoBusqueda;
    private ModeloTipoAnimales modelo;
    private ControlTipoAnimales control;
    private ControlGeneral controlGral;
    private ModeloVentanaGeneral modeloVentanaGeneral;
    private int editar;
    private GestionEstadoControles controles;
    private JButton[] botones;
    private List<Map<String, String>> fincas;
    public int idModulo = 10;

    /**
     * Creates new form VistaTipoAnimales
     */
    public VistaTipoAnimales() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        iniciarComponentes();
        modeloVentanaGeneral = new ModeloVentanaGeneral();
        modelo = new ModeloTipoAnimales();
        control = new ControlTipoAnimales();
        controlGral = new ControlGeneral();
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
        
        InformacionGlobal.setFincaDesdeConstructor(cbFinca);
    }

    VistaTipoAnimales(ModeloVentanaGeneral modeloVentanaGeneral) {
        initComponents();
        iniciarComponentes();
        modelo = new ModeloTipoAnimales();
        control = new ControlTipoAnimales();
        controlGral = new ControlGeneral();
        editar = Estado.GUARDAR;
        this.modeloVentanaGeneral = modeloVentanaGeneral;
        botones = new JButton[]{
            btnConsultar,
            btnDescartar,
            btnEliminar,
            btnGuardar,
            btnModificar
        };
        controles.habilitarControles();
        Utilidades.estadoBotonesDeControl(EstadoControles.POR_DEFECTO, botones);
    }

    private void cargarComboFincas() {
        String consulta = consultas.get("CARGAR_COMBO_FINCAS");
        fincas = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbFinca, fincas, "descripcion");
    }

    @Override
    public void iniciarComponentes() {
        controles = new GestionEstadoControles();

        Control control = new Control(true, txtCodigoFinca);
        controles.addControl(control);

        control = new Control(true, cbFinca);
        controles.addControl(control);

        control = new Control(true, txtCodigoTipoAnimal);
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

        txtCodigoFinca = new javax.swing.JLabel();
        txtCodigoTipoAnimal = new javax.swing.JLabel();
        panelcontenedor = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        lbltitle5 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbltitle6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        lbltitle8 = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        panelcontenedor.setBackground(new java.awt.Color(255, 255, 255));
        panelcontenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        panelcontenedor.setLayout(new java.awt.GridBagLayout());

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 262;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 54);
        panelcontenedor.add(jSeparator10, gridBagConstraints);

        lbltitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle5.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle5.setText("Descripción");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 41;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 56, 0, 54);
        panelcontenedor.add(lbltitle5, gridBagConstraints);

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(59, 123, 50));
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcion.setBorder(null);
        txtDescripcion.setCaretColor(new java.awt.Color(59, 123, 50));
        txtDescripcion.setSelectionColor(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 262;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 54);
        panelcontenedor.add(txtDescripcion, gridBagConstraints);

        lbltitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle6.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle6.setText("Estado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 138;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 56, 0, 54);
        panelcontenedor.add(lbltitle6, gridBagConstraints);

        cbEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbEstado.setForeground(new java.awt.Color(59, 123, 50));
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Activo", "Inactivo" }));
        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 176;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 54);
        panelcontenedor.add(cbEstado, gridBagConstraints);

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Fincas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 56, 0, 54);
        panelcontenedor.add(lbltitle8, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 176;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 56, 0, 54);
        panelcontenedor.add(cbFinca, gridBagConstraints);

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
        jPanel1.add(btnEliminar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        panelcontenedor.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(panelcontenedor, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoActionPerformed

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

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        InformacionGlobal.setFincaDesdeEventoChange(cbFinca);
        
        int indice = cbFinca.getSelectedIndex();
        if (indice > 0) {
            txtCodigoFinca.setText(fincas.get(indice).get("id"));
        }
    }//GEN-LAST:event_cbFincaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbEstado;
    public javax.swing.JComboBox cbFinca;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JLabel lbltitle5;
    private javax.swing.JLabel lbltitle6;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JPanel panelcontenedor;
    private javax.swing.JLabel txtCodigoFinca;
    private javax.swing.JLabel txtCodigoTipoAnimal;
    public javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables

    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar una descripción para el tipo de animal a crear.");
            txtDescripcion.requestFocusInWindow();
            return;
        }

        if (cbEstado.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar el estado del tipo de animal a crear.");
            cbEstado.requestFocusInWindow();
            return;
        }

        if (cbFinca.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una finca para guardar el tipo de animal.");
            cbFinca.requestFocusInWindow();
            return;
        }
        //</editor-fold>

        String codigoTipoAnimal = (editar == Estado.ACTUALIZAR) ? txtCodigoTipoAnimal.getText() : "0";
        modelo.setId(codigoTipoAnimal);
        modelo.setDescripcion(Utilidades.CodificarElemento(txtDescripcion.getText().trim()));
        modelo.setEstado(cbEstado.getSelectedItem().toString());
        modelo.setFecha("NOW()");
        modelo.setIdFinca(txtCodigoFinca.getText().trim());
        modelo.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));

        int retorno = Retorno.DEFECTO;

        if (editar == Estado.GUARDAR) {
            retorno = control.Guardar(modelo);
        } else {
            retorno = control.Actualizar(modelo);
        }

        if (retorno == Retorno.EXITO) {
            if (modeloVentanaGeneral.getPanelPadre() instanceof VistaGrupos) {//SI VIENE DE LA VISTA GRUPOS
                ArrayList<ModeloTipoAnimales> tiposAnimales = new ArrayList<>();
                tiposAnimales = (ArrayList<ModeloTipoAnimales>) control.ObtenerDatosFiltro(txtDescripcion.getText().trim());

                if (tiposAnimales.size() > 0) {
                    modelo = tiposAnimales.get(0);
                    ((VistaGrupos) modeloVentanaGeneral.getPanelPadre()).RetornoVistaGeneral(modeloVentanaGeneral, modelo);
                    ((VistaGeneral) modeloVentanaGeneral.getFrameVentana()).dispose();
                }
            } else if (modeloVentanaGeneral.getPanelPadre() instanceof VistaAnimales) {
                ArrayList<ModeloTipoAnimales> tiposAnimales = new ArrayList<>();
                tiposAnimales = (ArrayList<ModeloTipoAnimales>) control.ObtenerDatosFiltro(txtDescripcion.getText().trim());

                if (tiposAnimales.size() > 0) {
                    modelo = tiposAnimales.get(0);
                    ((VistaAnimales) modeloVentanaGeneral.getPanelPadre()).RetornoVistaGeneral(modeloVentanaGeneral, modelo);
                    ((VistaGeneral) modeloVentanaGeneral.getFrameVentana()).dispose();
                }
            } else {
                String mensaje = "";
                switch (retorno) {
                    case Retorno.EXITO:
                        mensaje = "Registro " + (editar == Estado.GUARDAR ? "guardado" : "actualizado") + " satisfactoriamente.";
                        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_GUARDAR, controles);
                        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_GUARDAR, botones);
                        editar = Estado.GUARDAR;
                        txtDescripcion.requestFocusInWindow();
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

        }
    }

    private void Modificar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_MODIFICAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_MODIFICAR, botones);
        editar = Estado.ACTUALIZAR;
    }

    private void Consultar() {
        //new ventanaBusquedaPaciente(1, "IDENTIFICACION:-:NOMBRE", estadoch, this);
        objetoBusqueda = new ModeloGestorBusqueda(this, "BUSQUEDA_TIPOS_ANIMALES", 0);
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

    private void Descartar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_DESCARTAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_DESCARTAR, botones);
        editar = Estado.GUARDAR;
    }

    public void RetornoBusqueda(ModeloGestorBusqueda objeto, Map<String, String> retorno) {
        if (objeto.getOpcion() == 0) {// SE LLAMA DESDE LA MISMA VISTA
            String id = retorno.get("ID");
            modelo = ((ArrayList<ModeloTipoAnimales>) control.ObtenerDatosKey(id)).get(0);

            txtCodigoTipoAnimal.setText(modelo.getId());
            cbFinca.setSelectedItem(modelo.getDescFinca());
            txtCodigoFinca.setText(modelo.getIdFinca());
            txtDescripcion.setText(Utilidades.decodificarElemento(modelo.getDescripcion()));
            cbEstado.setSelectedItem(modelo.getEstado());

            Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_BUSCAR, controles);
            Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_BUSCAR, botones);
        } else if (objeto.getOpcion() == 1) {//VISTA GRUPOS
            cbFinca.setSelectedItem(Utilidades.decodificarElemento(retorno.get("DESCRIPCION")));
            txtCodigoFinca.setText(retorno.get("ID"));

        }
    }

}
