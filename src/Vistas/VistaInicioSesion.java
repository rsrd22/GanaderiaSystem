/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import BaseDeDatos.gestorMySQL;
import Configuracion.ConfiguracionPropiedades;
import Control.ControlUsuarios;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Acer
 */
public class VistaInicioSesion extends javax.swing.JFrame {

    private gestorMySQL gsql;
    boolean estadoContrasenia = false;
    private ArrayList<String[]> usuarios;
    private int indice;
    private VistaPrincipal vprin;
    ControlUsuarios usuario; 
    private int x, y;
    private final char MASCARA_CONTRASENIA = '●';

    public VistaInicioSesion(/*VentanaPrincipal vprin*/) {
        initComponents();

//        Utilidades.EstablecerIcono(this);
        Utilidades.EstablecerIcono(this);
        gsql = new gestorMySQL();
        usuarios = new ArrayList<>();
        this.setLocationRelativeTo(null);
        vprin = new VistaPrincipal();
        vprin.setVisible(false);
        usuario = new ControlUsuarios();
        txtcontrasena.setText("12345");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblmensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtcontrasena = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        btniniciarsesion = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnVerContrasenia = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión");
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(59, 123, 50));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("He olvidado mi contraseña");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 190, -1));

        lblmensaje.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblmensaje.setForeground(new java.awt.Color(21, 67, 96));
        lblmensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 320, 60));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(59, 123, 50));
        jLabel1.setText("Nombre de usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, -1, -1));

        txtusuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtusuario.setForeground(new java.awt.Color(59, 123, 50));
        txtusuario.setText("rsrd22");
        txtusuario.setBorder(null);
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });
        txtusuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtusuarioFocusLost(evt);
            }
        });
        jPanel1.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 140, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(59, 123, 50));
        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        txtcontrasena.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtcontrasena.setForeground(new java.awt.Color(59, 123, 50));
        txtcontrasena.setBorder(null);
        txtcontrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontrasenaActionPerformed(evt);
            }
        });
        txtcontrasena.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcontrasenaFocusLost(evt);
            }
        });
        txtcontrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontrasenaKeyReleased(evt);
            }
        });
        jPanel1.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 140, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ganaderia32.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 170, 10));

        jSeparator2.setForeground(new java.awt.Color(59, 123, 50));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 170, 10));

        jPanel2.setBackground(new java.awt.Color(59, 123, 50));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));

        btniniciarsesion.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btniniciarsesion.setForeground(new java.awt.Color(255, 255, 255));
        btniniciarsesion.setText("Iniciar Sesión");
        btniniciarsesion.setBorderPainted(false);
        btniniciarsesion.setContentAreaFilled(false);
        btniniciarsesion.setFocusPainted(false);
        btniniciarsesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btniniciarsesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btniniciarsesionMouseExited(evt);
            }
        });
        btniniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btniniciarsesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btniniciarsesion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btniniciarsesion, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 150, 40));

        jPanel3.setBackground(new java.awt.Color(59, 123, 50));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/cerrar_1.png"))); // NOI18N
        jLabel5.setToolTipText("Cerrar");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 5, 30, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/minimizar.png"))); // NOI18N
        jLabel6.setToolTipText("Minimizar");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 5, 30, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Iniciar Sesión");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 40));

        btnVerContrasenia.setBackground(new java.awt.Color(255, 255, 255));
        btnVerContrasenia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/ver.png"))); // NOI18N
        btnVerContrasenia.setToolTipText("Ver Contraseña");
        btnVerContrasenia.setBorder(null);
        btnVerContrasenia.setBorderPainted(false);
        btnVerContrasenia.setContentAreaFilled(false);
        btnVerContrasenia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerContraseniaActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerContrasenia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 30, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/usuario.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 30, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        habilitarUnidadDeRed();
        getUsuario();
    }//GEN-LAST:event_formWindowOpened

    private void txtusuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtusuarioFocusLost
        int bandera = 0;
        if (!txtusuario.getText().equals("")) {
            for (int i = 0; i < usuarios.size(); i++) {
                if (usuarios.get(i)[1].toUpperCase().equals(txtusuario.getText().toUpperCase())) {
                    if (usuarios.get(i)[3].equals("Inactivo".toUpperCase())) {
                        bandera = 2;
                        indice = i;
                        break;
                    } else {
                        bandera = 1;
                        indice = i;
                        break;
                    }
                }
            }
            if (bandera == 0) {
                lblmensaje.setForeground(Color.red);
                lblmensaje.setText("<html><p align='center' color='#E67E22'>El usuario no existe.</p></html>");
                txtusuario.setText("");
                txtusuario.requestFocusInWindow();
            } else if (bandera == 2) {
                lblmensaje.setForeground(Color.red);
                lblmensaje.setText("<html><p align='center' color='#E67E22'>El usuario  se encuentra Inactivo, por favor comuniquese con el administrador del sistema.</p></html>");
                txtusuario.setText("");
                txtusuario.requestFocusInWindow();
            } else {
                lblmensaje.setText("");
                txtcontrasena.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_txtusuarioFocusLost

    private void txtcontrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontrasenaActionPerformed

    private void txtcontrasenaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcontrasenaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontrasenaFocusLost

    private void txtcontrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontrasenaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Iniciarsesion();
        }
    }//GEN-LAST:event_txtcontrasenaKeyReleased

    private void btniniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btniniciarsesionActionPerformed
        Iniciarsesion();
    }//GEN-LAST:event_btniniciarsesionActionPerformed

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        String user = txtusuario.getText();
        //new VistaRecuperarContrasenia(this).setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void btniniciarsesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btniniciarsesionMouseEntered
        Utilidades.establecerColorDeFondo(jPanel2, true);
    }//GEN-LAST:event_btniniciarsesionMouseEntered

    private void btniniciarsesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btniniciarsesionMouseExited
        Utilidades.establecerColorDeFondo(jPanel2, false);
    }//GEN-LAST:event_btniniciarsesionMouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void btnVerContraseniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerContraseniaActionPerformed
        if (!estadoContrasenia) { // Muestro los carateres
            txtcontrasena.setEchoChar((char) 0);
            estadoContrasenia = true;
            btnVerContrasenia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/verSlash.png")));
        } else { // Oculto los  caracteres
            txtcontrasena.setEchoChar(MASCARA_CONTRASENIA);   
            estadoContrasenia = false;
            btnVerContrasenia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/ver.png")));
        }
    }//GEN-LAST:event_btnVerContraseniaActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfiguracionPropiedades.cargarConfiguracion();
                VistaInicioSesion vistaInicioSesion = new VistaInicioSesion();
                vistaInicioSesion.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerContrasenia;
    private javax.swing.JButton btniniciarsesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblmensaje;
    private javax.swing.JPasswordField txtcontrasena;
    public javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables

    public void getUsuario() {
        //<editor-fold defaultstate="collapsed" desc="comment">
        System.out.println("getUsuario");
        //ID, USUARIO, CLAVE, ESTADO, CLAVE_DINAMICA
        usuarios = gsql.SELECT("SELECT id, usuario, clave, estado, clave_dinamica FROM usuarios");
        //</editor-fold>
    }

    private void Iniciarsesion() {
        if (!txtcontrasena.getText().equals("")) {
            String clve = gsql.getClaveEncryptada(txtusuario.getText().toUpperCase(), txtcontrasena.getText());
            System.out.println("clve-->" + clve);
            if (!usuarios.get(indice)[2].equals(clve)) {
                lblmensaje.setForeground(Color.red);
                lblmensaje.setText("<html><p color='#E67E22'>La contraseña es incorrecta.</p></html>");
                txtcontrasena.setText("");
                txtcontrasena.requestFocusInWindow();
            } else {
                lblmensaje.setText("");
                //            control.getUsuarios(usuarios.get(indice)[0]);---
//                vprin.control = usuario.getUsuarios(usuarios.get(indice)[0]);  
                datosUsuario.datos = usuario.getUsuarios(usuarios.get(indice)[0]); 
//                vprin.passdin = usuarios.get(indice)[4]; 
                this.dispose();
                vprin.setVisible(true);
                vprin.IniciarComponentes();
                
            }
        }
    }

    private void habilitarUnidadDeRed() {
        File unidadDeRed = new File("Y:\\");
        
//        if (!unidadDeRed.exists()) {   
//            JOptionPane.showMessageDialog(this, "Verifique la conexion de la unidad de red Z:\\ y vuelva a iniciar la aplicación.");
//            System.exit(0);
//        }
    }
}
