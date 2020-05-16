/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Busqueda.VistaBusqueda;
import Control.ControlActividades;
import Control.Retorno;
import GestionControles.Control;
import GestionControles.EstadoControles;
import GestionControles.GestionEstadoControles;
import Modelo.ModeloActividades;
import Modelo.ModeloGestorBusqueda;
import Utilidades.Estado;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaActividades extends javax.swing.JPanel implements IControlesUsuario{

    private int editar;
    private ModeloGestorBusqueda objetoBusqueda;
    private ModeloActividades modelo;
    private ControlActividades control;
    private GestionEstadoControles controles;
    private JButton[] botones;

    public VistaActividades() {
        initComponents();
        iniciarComponentes();
        modelo = new ModeloActividades();
        control = new ControlActividades();
        editar = Estado.GUARDAR;
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

    @Override
    public void iniciarComponentes() {
        controles = new GestionEstadoControles();

        Control control = new Control(true, txtDescripcion);
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

        txtCodigoActividad = new javax.swing.JLabel();
        btnDescartar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lbltitle6 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtDescripcion = new javax.swing.JTextField();
        lbltitle5 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));

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

        lbltitle6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle6.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle6.setText("Estado");

        jSeparator10.setBackground(new java.awt.Color(59, 123, 50));

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(59, 123, 50));
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDescripcion.setBorder(null);
        txtDescripcion.setCaretColor(new java.awt.Color(59, 123, 50));
        txtDescripcion.setSelectionColor(new java.awt.Color(59, 123, 50));

        lbltitle5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle5.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle5.setText("Descripción de la actividad");

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

        cbEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbEstado.setForeground(new java.awt.Color(59, 123, 50));
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Activo", "Inactivo" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbltitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbltitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lbltitle5)
                    .addGap(5, 5, 5)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(lbltitle6)
                    .addGap(5, 5, 5)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDescartar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        Descartar();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Modificar();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    public javax.swing.JComboBox cbEstado;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JLabel lbltitle5;
    private javax.swing.JLabel lbltitle6;
    private javax.swing.JLabel txtCodigoActividad;
    public javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables

    public void RetornoBusqueda(ModeloGestorBusqueda objeto, Map<String, String> retorno) {
        if (objeto.getOpcion() == 0) {// SE LLAMA DESDE LA MISMA VISTA
            String id = retorno.get("ID");
            modelo = ((ArrayList<ModeloActividades>) control.ObtenerDatosKey(id)).get(0);

            txtCodigoActividad.setText(modelo.getId());
            txtDescripcion.setText(modelo.getDescripcion());
            cbEstado.setSelectedItem(modelo.getEstado());

            Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_BUSCAR, controles);
            Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_BUSCAR, botones);
        }
    }
    
    private void Guardar() {
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (txtDescripcion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar una descripción para la actividad a crear.");
            txtDescripcion.requestFocusInWindow();
            return;
        }
//</editor-fold>

        String codigoEstadoAnimal = (editar == Estado.ACTUALIZAR) ? txtCodigoActividad.getText() : "0";
        modelo.setId(codigoEstadoAnimal);
        modelo.setDescripcion(txtDescripcion.getText().trim().toUpperCase());
        modelo.setEstado(cbEstado.getSelectedItem().toString());
        modelo.setFechaRegistro("NOW()");
        modelo.setUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));

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
        editar = Estado.ACTUALIZAR;
    }

    private void Descartar() {
        Utilidades.estadoFormulario(EstadoControles.DESPUES_DE_DESCARTAR, controles);
        Utilidades.estadoBotonesDeControl(EstadoControles.DESPUES_DE_DESCARTAR, botones);
        editar = Estado.GUARDAR;
    }

    private void Consultar() {
        objetoBusqueda = new ModeloGestorBusqueda(this, "BUSQUEDA_ACTIVIDADES", 0);
        VistaBusqueda vistaBusqueda = new VistaBusqueda(objetoBusqueda);
        btnConsultar.setEnabled(false);
    }

    private void Eliminar() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Esta Seguro de Eliminar el Registro?");
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

}
