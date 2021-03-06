/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.ControlInformes;
import Modelo.ModeloInformes;
import Utilidades.TipoInforme;
import Utilidades.Utilidades;
import Vistas.VistasInformes.VistaAnimalesPorFinca;
import Vistas.VistasInformes.VistaInformePalpacion;
import Vistas.VistasInformes.VistaInformePesaje;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author MERRY
 */
public class VistaInformes extends javax.swing.JPanel {

    public ArrayList<String[]> listacategorias = new ArrayList<>();
    public ArrayList<String[]> listainformes = new ArrayList<>();
    public DefaultListModel modlistCategorias;
    public DefaultListModel modlistInformes;
    private JPanel panel;
    private VistaInformePalpacion vistaPalpacion;
    private VistaInformePesaje vistaPesaje;
    private VistaAnimalesPorFinca vistaInformeAnimalesxFinca;
    public int idModulo = 25;

    /**
     * Creates new form VistaInformes
     */
    public VistaInformes() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        modlistCategorias = new DefaultListModel();
        modlistInformes = new DefaultListModel();
        cargarArray();
        llenarCategorias();
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

        jSeparator1 = new javax.swing.JSeparator();
        pnlOpciones = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        pnlInforme = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listInformes = new javax.swing.JList();
        pnlCategoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCategoria = new javax.swing.JList();
        pnlGenerar = new javax.swing.JPanel();
        btnGenerar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        jSeparator1.setForeground(new java.awt.Color(59, 123, 50));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        add(jSeparator1, gridBagConstraints);

        pnlOpciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlOpciones.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 10, 15, 15);
        add(pnlOpciones, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlInforme.setBackground(new java.awt.Color(255, 255, 255));
        pnlInforme.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        pnlInforme.setLayout(new java.awt.GridBagLayout());

        listInformes.setForeground(new java.awt.Color(59, 123, 50));
        listInformes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listInformes.setSelectionBackground(new java.awt.Color(59, 123, 50));
        listInformes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listInformesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(listInformes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlInforme.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(pnlInforme, gridBagConstraints);

        pnlCategoria.setBackground(new java.awt.Color(255, 255, 255));
        pnlCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        pnlCategoria.setLayout(new java.awt.GridBagLayout());

        listCategoria.setForeground(new java.awt.Color(59, 123, 50));
        listCategoria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listCategoria.setSelectionBackground(new java.awt.Color(59, 123, 50));
        listCategoria.setVerifyInputWhenFocusTarget(false);
        listCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listCategoriaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listCategoriaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(listCategoria);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        pnlCategoria.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(pnlCategoria, gridBagConstraints);

        pnlGenerar.setBackground(new java.awt.Color(59, 123, 50));

        btnGenerar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("Generar");
        btnGenerar.setBorderPainted(false);
        btnGenerar.setContentAreaFilled(false);
        btnGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerarMouseExited(evt);
            }
        });
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGenerarLayout = new javax.swing.GroupLayout(pnlGenerar);
        pnlGenerar.setLayout(pnlGenerarLayout);
        pnlGenerarLayout.setHorizontalGroup(
            pnlGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlGenerarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGenerar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlGenerarLayout.setVerticalGroup(
            pnlGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlGenerarLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnGenerar)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(pnlGenerar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 10);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void listCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCategoriaMousePressed
        SeleccionarCategoria();
    }//GEN-LAST:event_listCategoriaMousePressed

    private void listInformesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listInformesMousePressed
        MostrarOpciones();
    }//GEN-LAST:event_listInformesMousePressed

    private void listCategoriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listCategoriaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_listCategoriaMouseExited

    private void btnGenerarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseEntered
        Utilidades.establecerColorDeFondo(pnlGenerar, true);
    }//GEN-LAST:event_btnGenerarMouseEntered

    private void btnGenerarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseExited
        Utilidades.establecerColorDeFondo(pnlGenerar, false);
    }//GEN-LAST:event_btnGenerarMouseExited

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        int indexCategoria = listCategoria.getSelectedIndex();
        int indexInforme = listInformes.getSelectedIndex();

        if (indexCategoria == 0) {//Archivos Excel
            switch (indexInforme) {
                case TipoInforme.CARGA_MASIVA_PESAJE:
                    vistaPesaje.CrearInforme();
                    break;
                case TipoInforme.CARGA_MASIVA_PALPACION:
                    vistaPalpacion.CrearInforme();
                    break;
                case TipoInforme.INFORME_ANIMALES_POR_FINCA:
                    vistaInformeAnimalesxFinca.CrearInforme();
                    break;
                case TipoInforme.CARGA_MASIVA_ANIMALES:
                    CrearInformeCargaAnimales();
                    break;
                default:
                    return;
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGenerar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList listCategoria;
    private javax.swing.JList listInformes;
    private javax.swing.JPanel pnlCategoria;
    private javax.swing.JPanel pnlGenerar;
    private javax.swing.JPanel pnlInforme;
    private javax.swing.JPanel pnlOpciones;
    // End of variables declaration//GEN-END:variables

    private void cargarArray() {
        /////////////////////////// CAT, NOM
        listacategorias.add(new String[]{"0", "Archivos Excel"});
//        listacategorias.add(new String[]{"1","Historia Clinica"});
//        listacategorias.add(new String[]{"2","Factura"});
//        listacategorias.add(new String[]{"3","Paciente"});
        /////////////7////////////CAT, ID, NOm    
        listainformes.add(new String[]{"0", "0", "Carga Masiva Pesajes"});
        listainformes.add(new String[]{"0", "1", "Carga Masiva Palpación"});
        listainformes.add(new String[]{"0", "2", "Carga Masiva Animales"});
        listainformes.add(new String[]{"0", "3", "Informe"});
    }

    private void llenarCategorias() {
        //cbCate.addItem("Seleccionar");
        for (int i = 0; i < listacategorias.size(); i++) {
            modlistCategorias.addElement(listacategorias.get(i)[1]);
        }
        listCategoria.setModel(modlistCategorias);
    }

    private void llenarInforme(String idcat) {
        LimpiarPanelOpciones();
        modlistInformes.removeAllElements();
        listInformes.setModel(modlistInformes);
        for (String[] lst : listainformes) {
            if (lst[0].equals(idcat)) {
                modlistInformes.addElement(lst[2]);
            }
        }
        listInformes.setModel(modlistInformes);
    }

    private void SeleccionarCategoria() {
        int ind = listCategoria.getSelectedIndex();
        //System.out.println("ind----->"+ind);
        //ind = getCategoria(modlistCategorias.getElementAt());
        llenarInforme("" + ind);
    }

    private void MostrarOpciones() {
        int indexCategoria = listCategoria.getSelectedIndex();
        int indexInforme = listInformes.getSelectedIndex();

        if (indexCategoria == 0) {//Archivos Excel
            switch (indexInforme) {
                case TipoInforme.CARGA_MASIVA_PESAJE:
                    vistaPesaje = new VistaInformePesaje();
                    MostrarPanel(vistaPesaje);
                    break;
                case TipoInforme.CARGA_MASIVA_PALPACION:
                    vistaPalpacion = new VistaInformePalpacion();
                    MostrarPanel(vistaPalpacion);
                    break;
                case TipoInforme.INFORME_ANIMALES_POR_FINCA:
                    vistaInformeAnimalesxFinca = new VistaAnimalesPorFinca();
                    MostrarPanel(vistaInformeAnimalesxFinca);
                    break;
                default:
                    JPanel panel = new JPanel();
                    panel.setBackground(Color.white);
                    MostrarPanel(panel);
                    return;
            }
        }
    }

    private void LimpiarPanelOpciones() {
        pnlOpciones.removeAll();
        pnlOpciones.repaint();
    }

    private void MostrarPanel(JPanel panel) {
        this.panel = panel;
        this.panel.setBounds(0, 0, pnlOpciones.getWidth(), pnlOpciones.getHeight());
        pnlOpciones.removeAll();

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        pnlOpciones.add(this.panel, gridBagConstraints);

        pnlOpciones.revalidate();
        pnlOpciones.repaint();
    }

    private void CrearInformeCargaAnimales() {
        Map<String, Object> infor = new HashMap<>();
        infor.put("IDTIPO", "");
        infor.put("IDFINCA", "");
        infor.put("GRUPOS", "");
        infor.put("MEDICAMENTOS", "");        

        ModeloInformes informes = new ModeloInformes();
        informes.setCategoria("0");
        informes.setInforme("2");
        informes.setInformacion(infor);

        ControlInformes contInformes = new ControlInformes();
        contInformes.GenerarInformes(informes);
    }

}
