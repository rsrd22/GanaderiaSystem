/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import AjustarControles.AjustarControles;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Utilidades;
import Vistas.Inventario.VistaInventario;
import Vistas.Inventario.VistaProducto;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author MERRY
 */
public class VistaGeneral extends javax.swing.JFrame {

    public int x, y;
    public ModeloVentanaGeneral modeloVista;
    private AjustarControles controles;
    private JPanel panel;
    private int band;
    private int flagResize;
    private Dimension dimensionPanel = new Dimension();
    private Dimension dimensionAnterior;
    private int ALTO_BARRA_INICIO = 40;

    /**
     * Creates new form VistaGeneral
     */
    public VistaGeneral(ModeloVentanaGeneral modeloVista) {
        initComponents();
        Utilidades.EstablecerIcono(this);
        pnlContenedor.setSize(330, 340);
        this.setLocationRelativeTo(null);
        this.modeloVista = modeloVista;
        controles = null;
        CargarVista();
        band = 0;
        flagResize = 1;
        this.setLocationRelativeTo(null);
    }

    public void MostrarPanel(JPanel panel) {
        this.panel = panel;
        pnlContenedor.removeAll();
        pnlContenedor.add(panel);
        dimensionPanel = new Dimension(panel.getSize());

        if (panel instanceof VistaTrasladar) {
            VistaTrasladar vista = (VistaTrasladar) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());

        } else if (panel instanceof VistaRotar) {
            VistaRotar vista = (VistaRotar) panel;
            this.setSize(vista.getWidth(), vista.getHeight() + 36);
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight() + 36);
        } else if (panel instanceof VistaDetalleVenta) {
            VistaDetalleVenta vista = (VistaDetalleVenta) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaDetalleMuerte) {
            VistaDetalleMuerte vista = (VistaDetalleMuerte) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaDetalleTraslado) {
            VistaDetalleTraslado vista = (VistaDetalleTraslado) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaDetalleRotacion) {
            VistaDetalleRotacion vista = (VistaDetalleRotacion) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaHistoriaAnimal) {
            VistaHistoriaAnimal vista = (VistaHistoriaAnimal) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaDuplicarGrupos) {
            VistaDuplicarGrupos vista = (VistaDuplicarGrupos) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaModificarVenta) {
            VistaModificarVenta vista = (VistaModificarVenta) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaModificarMuerte) {
            VistaModificarMuerte vista = (VistaModificarMuerte) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaHierros) {
            VistaHierros vista = (VistaHierros) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaAnimales) {
            VistaAnimales vista = (VistaAnimales) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaSeleccionarFinca) {
            VistaSeleccionarFinca vista = (VistaSeleccionarFinca) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Seleccionar Finca");
        } else if (panel instanceof VistaIngresarMotivo) {
            VistaIngresarMotivo vista = (VistaIngresarMotivo) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Ingrese Motivo");
        } else if (panel instanceof VistaIngresoPesaje) {
            VistaIngresoPesaje vista = (VistaIngresoPesaje) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Ingreso pesaje del animal");
        } else if (panel instanceof VistaIngresoPalpacion) {
            VistaIngresoPalpacion vista = (VistaIngresoPalpacion) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Ingreso palpación del animal");
        } else if (panel instanceof VistaInfoPalpacion) {
            VistaInfoPalpacion vista = (VistaInfoPalpacion) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Información de Palpación");
        } else if (panel instanceof VistaInfoPesaje) {
            VistaInfoPesaje vista = (VistaInfoPesaje) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Información de pesaje");
        } else if (panel instanceof VistaNacimientoAnimal) {
            VistaNacimientoAnimal vista = (VistaNacimientoAnimal) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Nacimiento");
        } else if (panel instanceof VistaEditarDatosAnimal) {
            VistaEditarDatosAnimal vista = (VistaEditarDatosAnimal) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Modificar");
        } else if (panel instanceof VistaObservacion) {
            VistaObservacion vista = (VistaObservacion) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Observaciones de anulación");
        } else if (panel instanceof VistaProducto) {
            VistaProducto vista = (VistaProducto) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
            this.lblTitulo.setText("Agregar productos");

        }
        pnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));

//        pnlContenedor.revalidate();
//        pnlContenedor.repaint();
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

        jPanel6 = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblMinimizar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel6.setBackground(new java.awt.Color(59, 123, 50));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });
        jPanel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel6MouseDragged(evt);
            }
        });
        jPanel6.setLayout(new java.awt.GridBagLayout());

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cerrar_1.png"))); // NOI18N
        lblCerrar.setToolTipText("Cerrar");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(lblCerrar, gridBagConstraints);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Ventana General");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel6.add(lblTitulo, gridBagConstraints);

        lblMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/minimizar.png"))); // NOI18N
        lblMinimizar.setToolTipText("Minimizar");
        lblMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizarMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(lblMinimizar, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/maximizar.png"))); // NOI18N
        jLabel1.setToolTipText("Maximizar");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel6.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel6, gridBagConstraints);

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        pnlContenedor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlContenedorComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 385;
        gridBagConstraints.ipady = 343;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnlContenedor, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void lblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizarMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizarMouseClicked

    private void jPanel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel6MousePressed

    private void jPanel6MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel6MouseDragged

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (modeloVista.getPanelPadre() instanceof VistaAllHierros) {
            if (modeloVista.getPanelHijo() instanceof VistaHierros) {
                ((VistaAllHierros) modeloVista.getPanelPadre()).btnAgregarHierro.setEnabled(true);
            }
        }
        if (modeloVista.getPanelPadre() instanceof VistaPesaje) {
            if (((VistaIngresoPesaje) modeloVista.getPanelHijo()).guardado != 0) {
                VistaPesaje vp = ((VistaPesaje) modeloVista.getPanelPadre());
                if (modeloVista.getOpcion() == 1) {
                    vp.tbl_Animales.setValueAt("", vp.filaSeleccionada, 11);
                }
                vp.band = 0;
            }
        }
        if (modeloVista.getPanelPadre() instanceof VistaInventario) {
            VistaInventario vp = ((VistaInventario) modeloVista.getPanelPadre());
            vp.band = 0;
        }
        if (modeloVista.getPanelPadre() instanceof VistaPalpacion) {
            ArrayList<String> datos = new ArrayList<>();
            System.out.println("guardado: " + ((VistaIngresoPalpacion) modeloVista.getPanelHijo()).guardado);
            if (((VistaIngresoPalpacion) modeloVista.getPanelHijo()).guardado != 0) {
                VistaPalpacion vp = ((VistaPalpacion) modeloVista.getPanelPadre());
                vp.MostrarTabla();
                vp.band = 0;
            }
        }
        if (modeloVista.getPanelPadre() instanceof VistaEditarDatosAnimal) {

        }
        if (modeloVista.getPanelPadre() instanceof VistaHistoriaAnimal) {

            VistaHistoriaAnimal vp = ((VistaHistoriaAnimal) modeloVista.getPanelPadre());
            vp.band = 0;
        }
    }//GEN-LAST:event_formWindowClosed

    private void pnlContenedorComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlContenedorComponentResized
        EstablecerPnlContenedor();
    }//GEN-LAST:event_pnlContenedorComponentResized

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if (evt.getClickCount() == 1) {
            JLabel lblIcono = (JLabel) (evt.getComponent());
            String icono = "";
            if (band == 0) {
                band = 1;
                dimensionAnterior = this.getSize();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight() - ALTO_BARRA_INICIO);
                dimensionPanel = new Dimension((int) dim.getWidth(), (int) dim.getHeight() - ALTO_BARRA_INICIO);
                setLocationRelativeTo(null);
                icono = "restaurar";
            } else {
                band = 0;
                setBounds(0, 0, (int) dimensionAnterior.getWidth(), (int) dimensionAnterior.getHeight());
                dimensionPanel = dimensionAnterior;
                setLocationRelativeTo(null);
                icono = "maximizar";
            }
            lblIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/" + icono + ".png")));
            lblIcono.setToolTipText(("" + icono.charAt(0)).toUpperCase() + icono.substring(1));
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        if (evt.getClickCount() == 2) {
            String icono = "";
            if (band == 0) {
                band = 1;
                dimensionAnterior = this.getSize();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight() - ALTO_BARRA_INICIO);
                dimensionPanel = new Dimension((int) dim.getWidth(), (int) dim.getHeight() - ALTO_BARRA_INICIO);
                setLocationRelativeTo(null);
                icono = "restaurar";
            } else {
                band = 0;
                setBounds(0, 0, (int) dimensionAnterior.getWidth(), (int) dimensionAnterior.getHeight());
                dimensionPanel = dimensionAnterior;
                setLocationRelativeTo(null);
                icono = "maximizar";
            }
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/" + icono + ".png")));
            jLabel1.setToolTipText(("" + icono.charAt(0)).toUpperCase() + icono.substring(1));
        }
    }//GEN-LAST:event_jPanel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGeneral(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblMinimizar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlContenedor;
    // End of variables declaration//GEN-END:variables

    private void CargarVista() {
        modeloVista.setFrameVentana(this);
        if (modeloVista.getPanelHijo() instanceof VistaPropietarios) {

            VistaPropietarios vista = new VistaPropietarios(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaTipoAnimales) {

            VistaTipoAnimales vista = new VistaTipoAnimales(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaMacroGrupo) {

            VistaMacroGrupo vista = new VistaMacroGrupo(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaBloques) {

            VistaBloques vista = new VistaBloques(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaLotes) {

            VistaLotes vista = new VistaLotes(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaSeleccionarFinca) {

            VistaSeleccionarFinca vista = new VistaSeleccionarFinca(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaHierros) {

            VistaHierros vista = new VistaHierros(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaAnimales) {

            VistaAnimales vista = new VistaAnimales(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaRotar) {

            VistaRotar vista = new VistaRotar(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaTrasladar) {

            VistaTrasladar vista = new VistaTrasladar(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaDetalleVenta) {

            VistaDetalleVenta vista = new VistaDetalleVenta(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaDetalleMuerte) {

            VistaDetalleMuerte vista = new VistaDetalleMuerte(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaDetalleTraslado) {

            VistaDetalleTraslado vista = new VistaDetalleTraslado(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaDetalleRotacion) {

            VistaDetalleRotacion vista = new VistaDetalleRotacion(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaHistoriaAnimal) {

            VistaHistoriaAnimal vista = new VistaHistoriaAnimal(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaDuplicarGrupos) {

            VistaDuplicarGrupos vista = new VistaDuplicarGrupos(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaModificarVenta) {

            VistaModificarVenta vista = new VistaModificarVenta(modeloVista);
            MostrarPanel(vista);

        } else if (modeloVista.getPanelHijo() instanceof VistaModificarMuerte) {

            VistaModificarMuerte vista = new VistaModificarMuerte(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaIngresarMotivo) {

            VistaIngresarMotivo vista = new VistaIngresarMotivo(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaIngresoPesaje) {

            VistaIngresoPesaje vista = new VistaIngresoPesaje(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaIngresoPalpacion) {

            VistaIngresoPalpacion vista = new VistaIngresoPalpacion(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaInfoPalpacion) {

            VistaInfoPalpacion vista = new VistaInfoPalpacion(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaInfoPesaje) {

            VistaInfoPesaje vista = new VistaInfoPesaje(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaNacimientoAnimal) {

            VistaNacimientoAnimal vista = new VistaNacimientoAnimal(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaEditarDatosAnimal) {

            VistaEditarDatosAnimal vista = new VistaEditarDatosAnimal(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaObservacion) {

            VistaObservacion vista = new VistaObservacion(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaProducto) {

            VistaProducto vista = new VistaProducto(modeloVista);
            MostrarPanel(vista);

        }

    }

    public void Packer() {
        pack();
    }

    public void EstablecerPnlContenedor() {
        if (pnlContenedor.getComponents().length > 0) {
            System.out.println("band--->" + flagResize);
            if (flagResize == 0) {
                flagResize = 1;
                pnlContenedor.getComponent(0).setSize(pnlContenedor.getWidth(), pnlContenedor.getHeight());
            } else {
                pnlContenedor.getComponent(0).setSize(dimensionPanel);
                flagResize = 0;
            }
            java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            pnlContenedor.add(pnlContenedor.getComponent(0), gridBagConstraints);

            //        pnlContenedor.add(panel);
            pnlContenedor.revalidate();
            pnlContenedor.repaint();
        }
    }

}
