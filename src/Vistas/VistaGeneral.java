/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import AjustarControles.Ajustar;
import AjustarControles.AjustarControles;
import AjustarControles.ControlDeUsuario;
import AjustarControles.tiposDeAjuste;
import Modelo.ModeloVentanaGeneral;
import javax.swing.JFrame;
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

    /**
     * Creates new form VistaGeneral
     */
    public VistaGeneral(ModeloVentanaGeneral modeloVista) {
        initComponents();
        pnlContenedor.setSize(330, 340);
        this.setLocationRelativeTo(null);
        this.modeloVista = modeloVista;
        controles = null;
        CargarVista();
        iniciarComponentes();
    }

    public void MostrarPanel(JPanel panel) {
        this.panel = panel;
        pnlContenedor.removeAll();
        pnlContenedor.add(panel);

        if (panel instanceof VistaTrasladar) {
            VistaTrasladar vista = (VistaTrasladar) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
        } else if (panel instanceof VistaRotar) {
            VistaRotar vista = (VistaRotar) panel;
            this.setSize(vista.getWidth(), vista.getHeight());
            pnlContenedor.setSize(vista.getWidth(), vista.getHeight());
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
        }
        pnlContenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
//        pnlContenedor.revalidate();
//        pnlContenedor.repaint();

//        controles.ajustarControlesPorDimension();
//        controles.ajustarControlesPorPosicion();
//        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblMinimizar = new javax.swing.JLabel();
        pnlContenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(59, 123, 50));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel6MousePressed(evt);
            }
        });
        jPanel6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel6MouseDragged(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cerrar_1.png"))); // NOI18N
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        jPanel6.add(lblCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 5, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Ventana General");
        jPanel6.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 220, -1));

        lblMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/minimizar.png"))); // NOI18N
        lblMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimizarMouseClicked(evt);
            }
        });
        jPanel6.add(lblMinimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 5, -1, 30));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 40));

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
            .addGap(0, 328, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 338, Short.MAX_VALUE)
        );

        getContentPane().add(pnlContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 330, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void lblMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizarMouseClicked
        // TODO add your handling code here:
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
    }//GEN-LAST:event_formWindowClosed

    private void pnlContenedorComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlContenedorComponentResized
        if (controles != null) {
            controles.ajustarControlesPorDimension();
            controles.ajustarControlesPorPosicion();
            pnlContenedor.repaint();
        }
    }//GEN-LAST:event_pnlContenedorComponentResized

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

//        } else if (modeloVista.getPanelHijo() instanceof VistaFuentesHidricas) {
//
//            VistaFuentesHidricas vista = new VistaFuentesHidricas(modeloVista);
//            MostrarPanel(vista);
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
        } else if (modeloVista.getPanelHijo() instanceof VistaDuplicarGrupos) {

            VistaDuplicarGrupos vista = new VistaDuplicarGrupos(modeloVista);
            MostrarPanel(vista);
        } else if (modeloVista.getPanelHijo() instanceof VistaModificarVenta) {

            VistaModificarVenta vista = new VistaModificarVenta(modeloVista);
            MostrarPanel(vista);  
        } else if (modeloVista.getPanelHijo() instanceof VistaModificarMuerte) {

            VistaModificarMuerte vista = new VistaModificarMuerte(modeloVista);
            MostrarPanel(vista);  

        }

    }

    public void Packer() {
        pack();
    }

    private void iniciarComponentes() {
        controles = new AjustarControles();

        ControlDeUsuario controlUsuario = new ControlDeUsuario(jPanel6);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.DERECHA);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_DIMENSION;
        controles.agregarControl(controlUsuario);

        controlUsuario = new ControlDeUsuario(lblTitulo);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.IZQUIERDA);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_POSICION;
        controles.agregarControl(controlUsuario);

        controlUsuario = new ControlDeUsuario(lblCerrar);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.DERECHA);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_POSICION;
        controles.agregarControl(controlUsuario);

        controlUsuario = new ControlDeUsuario(lblMinimizar);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.DERECHA);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_POSICION;
        controles.agregarControl(controlUsuario);

        controlUsuario = new ControlDeUsuario(pnlContenedor);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.DERECHA);
        controlUsuario.addAjuste(Ajustar.ABAJO);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_DIMENSION;
        controles.agregarControl(controlUsuario);

        controlUsuario = new ControlDeUsuario(panel);
        controlUsuario.ajustable = true;
        controlUsuario.addAjuste(Ajustar.DERECHA);
        controlUsuario.addAjuste(Ajustar.ABAJO);
        controlUsuario.tipoAjuste = tiposDeAjuste.POR_DIMENSION;
        controles.agregarControl(controlUsuario);

        controles.ajustarControlesPorDimension();
        controles.ajustarControlesPorPosicion();
    }

}
