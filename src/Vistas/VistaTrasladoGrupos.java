/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Configuracion.InformacionGlobal;
import Control.ControlGeneral;
import Control.ControlTraslado;
import Modelo.ModeloTraslado;
import Modelo.ModeloVentanaGeneral;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author MERRY
 */
public class VistaTrasladoGrupos extends javax.swing.JPanel {
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public List<Map<String, String>> listaFincasDestino;
    public List<Map<String, String>> listaTipoAnimalesDestino;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idFincaDestino;
    public String idTipoAnimal;
    public String idTipoAnimalDestino;
    public String motivo;
    private ArrayList<ModeloTraslado> ListamodeloTraslado;
    public List<Map<String, String>> listaGrupos;
    public List<Map<String, String>> listaGruposDestino;
    public DefaultListModel modlistGrupos;
    public DefaultListModel modlistGruposDestino;
    private ControlTraslado controlTraslado = new ControlTraslado();
    public int idModulo = 23;
    /**
     * Creates new form VistaTrasladoGrupos
     */
    public VistaTrasladoGrupos() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        lstGruposOrigen.setSelectionModel(new DefaultListSelectionModel() {
            private int i0 = -1;
            private int i1 = -1;

            public void setSelectionInterval(int index0, int index1) {
                if(i0 == index0 && i1 == index1){
                    if(getValueIsAdjusting()){
                         setValueIsAdjusting(false);
                         setSelection(index0, index1);
                    }
                }else{
                    i0 = index0;
                    i1 = index1;
                    setValueIsAdjusting(false);
                    setSelection(index0, index1);
                }
            }
            private void setSelection(int index0, int index1){
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });
        cbFinca.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    eventocbFinca();
                }
            }
        });
        cbFincaDestino.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    eventocbFincaDestino();
                }
            }
        });
        cbTipoAnimales.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    eventocbTipoAnimales();
                }
            }
        });
        cbTipoAnimalesDestino.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) {
                    eventocbTipoAnimalesDestino();
                }
            }
        });
        listaFincas = new ArrayList<>();
        listaTipoAnimales = new ArrayList<>();
        listaFincasDestino = new ArrayList<>();
        listaTipoAnimalesDestino = new ArrayList<>();
        ListamodeloTraslado = new ArrayList<>();
        listaGrupos = new ArrayList<>();
        listaGruposDestino = new ArrayList<>();
        idFinca = "";
        idTipoAnimal = "";
        idFincaDestino = "";
        idTipoAnimalDestino = "";
        motivo = "";
        modlistGrupos = new DefaultListModel();
        modlistGruposDestino = new DefaultListModel();
        IniciarFecha();
        LlenarListaFincas();
        CargarListaFincas(0, cbFinca);
        CargarListaFincas(1, cbFincaDestino);
        
        InformacionGlobal.setFincaDesdeConstructor(cbFinca);
        InformacionGlobal.setTipoAnimalDesdeConstructor(cbTipoAnimales);
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

        jPanel3 = new javax.swing.JPanel();
        lblTid = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        cbTipoAnimales = new javax.swing.JComboBox();
        lblTid3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstGruposOrigen = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jdFechaTraslado = new com.toedter.calendar.JDateChooser();
        lblTid4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGrupoDestino = new javax.swing.JList();
        lblTid1 = new javax.swing.JLabel();
        cbFincaDestino = new javax.swing.JComboBox();
        cbTipoAnimalesDestino = new javax.swing.JComboBox();
        lblTid5 = new javax.swing.JLabel();
        btnDescartar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Traslado Origen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        jPanel3.add(lblTid, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 28, -1, -1));

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        jPanel3.add(cbFinca, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 48, 232, 30));

        cbTipoAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimales.setForeground(new java.awt.Color(59, 123, 50));
        jPanel3.add(cbTipoAnimales, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 108, 232, 30));

        lblTid3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid3.setForeground(new java.awt.Color(59, 123, 50));
        lblTid3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid3.setText("Tipo Animales");
        jPanel3.add(lblTid3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 88, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N

        lstGruposOrigen.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstGruposOrigen.setSelectionBackground(new java.awt.Color(59, 123, 50));
        lstGruposOrigen.setValueIsAdjusting(true);
        jScrollPane1.setViewportView(lstGruposOrigen);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 144, -1, 300));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Traslado Destino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(jdFechaTraslado, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 165, 230, 30));

        lblTid4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid4.setForeground(new java.awt.Color(59, 123, 50));
        lblTid4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid4.setText("Fecha Traslado");
        jPanel4.add(lblTid4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 145, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grupo ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(59, 123, 50))); // NOI18N

        lstGrupoDestino.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstGrupoDestino.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstGrupoDestino.setSelectionBackground(new java.awt.Color(59, 123, 50));
        lstGrupoDestino.setValueIsAdjusting(true);
        jScrollPane2.setViewportView(lstGrupoDestino);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 206, 230, 237));

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Finca");
        jPanel4.add(lblTid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 28, -1, -1));

        cbFincaDestino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFincaDestino.setForeground(new java.awt.Color(59, 123, 50));
        jPanel4.add(cbFincaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 48, 230, 30));

        cbTipoAnimalesDestino.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimalesDestino.setForeground(new java.awt.Color(59, 123, 50));
        jPanel4.add(cbTipoAnimalesDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 109, 230, 30));

        lblTid5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid5.setForeground(new java.awt.Color(59, 123, 50));
        lblTid5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid5.setText("Tipo Animales");
        jPanel4.add(lblTid5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 89, -1, -1));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 13, 0, 24);
        add(jPanel4, gridBagConstraints);

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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 3, 13, 0);
        add(btnDescartar, gridBagConstraints);

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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 210, 13, 0);
        add(btnGuardar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public void eventocbFinca(){
        InformacionGlobal.setFincaDesdeEventoChange(cbFinca);
        
        if(cbFinca.getItemCount() > 0){
            idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
            FuncionLlenarTipoAnimales(0);
        }
    }
    
    public void eventocbTipoAnimales(){ 
        InformacionGlobal.setTipoAnimalDesdeEventoChange(cbTipoAnimales);
        
        if(cbTipoAnimales.getItemCount() > 0){
            idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
            String consulta = "SELECT * FROM ( SELECT gr.`id`, gr.`descripcion` AS DESCGRUPO, \n" +
                                "COUNT(ani.`id`) AS NUMANIMALES, CONCAT(gr.`descripcion`, ' (', COUNT(ani.`id`), tpo.`descripcion`, ')') AS DESCRIPCION\n" +
                                "FROM grupos gr \n" +
                                "INNER JOIN `tipo_animales` tpo ON tpo.`id` = gr.`id_tipo_animal`\n" +
                                "LEFT JOIN `traslado_animalxgrupo` traslado ON  traslado.`id_grupo` = gr.`id`  \n" +
                                "AND traslado.`id_finca` = tpo.`id_finca` AND traslado.`estado` = 'Activo'\n" +
                                "LEFT JOIN `ranimales` ani ON traslado.`id_animal` = ani.`id` AND ani.`id_tipo_animal` = gr.`id_tipo_animal` \n" +
                                "AND ani.muerte = '0' AND ani.`venta` = '0' \n" +
                                "WHERE tpo.`id_finca` = '"+idFinca+"' AND tpo.`id` = '"+idTipoAnimal+"'  \n" +
                                "GROUP BY gr.`id` ) AS tbl\n" +
                                "WHERE tbl.NUMANIMALES > 0";
            
            LimpiarLstGrupos();
            if(cbTipoAnimales.getSelectedIndex()>0){
                listaGrupos = controlgen.GetComboBox(consulta);
                LlenarJList(listaGrupos, modlistGrupos, lstGruposOrigen);
            }
        }
    }
    
    public void eventocbFincaDestino(){
        if(cbFincaDestino.getItemCount() > 0){
            idFincaDestino = listaFincas.get(cbFincaDestino.getSelectedIndex()).get("ID");
            FuncionLlenarTipoAnimales(1);
        }
    }
    
    private void eventocbTipoAnimalesDestino(){
        if(cbTipoAnimalesDestino.getItemCount() > 0){
            idTipoAnimalDestino = listaTipoAnimalesDestino.get(cbTipoAnimalesDestino.getSelectedIndex()).get("ID");
            String consulta = "SELECT gr.`id`, gr.`descripcion` AS DESCGRUPO, \n" +
                                "COUNT(ani.`id`) AS NUMANIMALES, CONCAT(gr.`descripcion`, ' (', COUNT(ani.`id`), tpo.`descripcion`, ')') AS DESCRIPCION\n" +
                                "FROM grupos gr \n" +
                                "INNER JOIN `tipo_animales` tpo ON tpo.`id` = gr.`id_tipo_animal`\n" +
                                "LEFT JOIN `traslado_animalxgrupo` traslado ON  traslado.`id_grupo` = gr.`id`  \n" +
                                "AND traslado.`id_finca` = tpo.`id_finca` AND traslado.`estado` = 'Activo'\n" +
                                "LEFT JOIN `ranimales` ani ON traslado.`id_animal` = ani.`id` AND ani.`id_tipo_animal` = gr.`id_tipo_animal` \n" +
                                "AND ani.muerte = '0' AND ani.`venta` = '0' \n" +
                                "WHERE tpo.`id_finca` = '"+idFincaDestino+"' AND tpo.`id` = '"+idTipoAnimalDestino+"'  \n" +
                                "GROUP BY gr.`id` ";
            LimpiarLstGruposDestino();
            if(cbTipoAnimalesDestino.getSelectedIndex()>0){
                listaGruposDestino = controlgen.GetComboBox(consulta);
                LlenarJList(listaGruposDestino, modlistGruposDestino, lstGrupoDestino);
            }
        }
    }
    
    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        LimpiarFomulario();
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        VistaIngresarMotivo vsf = new VistaIngresarMotivo();
        ModeloVentanaGeneral objetoVentana = new ModeloVentanaGeneral(this, vsf, 1);
        new VistaGeneral(objetoVentana).setVisible(true);
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnGuardar;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbFincaDestino;
    public javax.swing.JComboBox cbTipoAnimales;
    public javax.swing.JComboBox cbTipoAnimalesDestino;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdFechaTraslado;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid3;
    private javax.swing.JLabel lblTid4;
    private javax.swing.JLabel lblTid5;
    private javax.swing.JList lstGrupoDestino;
    private javax.swing.JList lstGruposOrigen;
    // End of variables declaration//GEN-END:variables

    private void LlenarListaFincas(){
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n" +
                                                "FROM `fincas`\n"+
                                                "/*UNION \n"+
                                                "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");
    }
    
    private void CargarListaFincas(int opc, JComboBox jcombo) {
        Utilidades.LlenarComboBox(jcombo, listaFincas, "DESCRIPCION");
    }
    
    private void FuncionLlenarTipoAnimales(int opc){
        if(opc == 0){//ORIGEN
            listaTipoAnimales = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                    "UNION\n" +
                                                    "SELECT id AS ID, descripcion AS DESCRIPCION\n" +
                                                    "FROM `tipo_animales`\n" +
                                                    "WHERE id_finca = '"+idFinca+"' AND estado = 'Activo'\n" +
                                                    "ORDER BY id ASC");
            Utilidades.LlenarComboBox(cbTipoAnimales, listaTipoAnimales, "DESCRIPCION");
            cbTipoAnimales.setSelectedIndex(0);
        }else{
            listaTipoAnimalesDestino = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                    "UNION\n" +
                                                    "SELECT id AS ID, descripcion AS DESCRIPCION\n" +
                                                    "FROM `tipo_animales`\n" +
                                                    "WHERE id_finca = '"+idFincaDestino+"' AND estado = 'Activo'\n" +
                                                    "ORDER BY id ASC");
            Utilidades.LlenarComboBox(cbTipoAnimalesDestino, listaTipoAnimalesDestino, "DESCRIPCION");
            cbTipoAnimalesDestino.setSelectedIndex(0);
        }
        
    }
    
    public void IniciarFecha() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        jdFechaTraslado.setCalendar(Calendar.getInstance());
    }

    private void LimpiarLstGrupos() {
        modlistGrupos.removeAllElements();
        lstGruposOrigen.setModel(modlistGrupos);
    }
    
    private void LimpiarLstGruposDestino() {
        modlistGruposDestino.removeAllElements();
        lstGrupoDestino.setModel(modlistGruposDestino);
    }
    
    private void LlenarJList(List<Map<String, String>> datos, DefaultListModel modelo, JList jlist) {
        for (Map<String, String> lista: datos) {
            modelo.addElement(lista.get("DESCRIPCION"));
        }
        jlist.setModel(modelo);
    }
    
    
    private void LimpiarFomulario() {
        cbTipoAnimales.setSelectedIndex(0);
        cbTipoAnimalesDestino.setSelectedIndex(0);
        
        LimpiarLstGrupos();
        LimpiarLstGruposDestino();
    }
    
    

    private void Guardar() {
        //String motivo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = jdFechaTraslado.getCalendar();
        String fechaT = sdf.format(fecha.getTime());
        
        String[] idsGrupoOrigen = getIDsGrupoOrigen();
        System.out.println("lstGrupoDestino.getSelectedIndex()--->"+lstGrupoDestino.getSelectedIndex());
        
        
        String ids = "";
        for(int i = 0; i < idsGrupoOrigen.length; i++){
            ids += (ids.equals("")?"":", ")+"'"+idsGrupoOrigen[i]+"'";
        }
        
        //<editor-fold defaultstate="collapsed" desc="VALIDACIONES">
        if (ids.equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione al menos un grupo a trasladar, e intentelo nuevamente.");
            return;
        }
        if (lstGrupoDestino.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione el grupo destino a trasladar, e intentelo nuevamente.");
            return;
        }
        
        
        //</editor-fold>
        
        String idGrupoDestino = listaGruposDestino.get(lstGrupoDestino.getSelectedIndex()).get("id");
        
        //<editor-fold defaultstate="collapsed" desc="Lista ID Animales">
        List<Map<String, String>> ListaidsAnimales = controlTraslado.getIdsAnimalesxGrupos(idTipoAnimal, ids);
        if (ListaidsAnimales.size() == 0) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error Al momento de realizar la operación. \nPor favor comuniquese con el administrador del sistema.");
            return;
        }
        for (int i = 0; i < ListaidsAnimales.size(); i++) {
            ListamodeloTraslado.add(
                    new ModeloTraslado(
                            "Activo",
                            "NOW()",
                            fechaT,
                            "0",
                            "" + ListaidsAnimales.get(i).get("ID"),
                            idFincaDestino,
                            idGrupoDestino,
                            datosUsuario.datos.get(0).get("ID_USUARIO"),
                            motivo,
                            "",
                            "",
                            "",
                            "",
                            ""+idTipoAnimalDestino)//IDTIPOANIMAL
            );
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Actualizar Animales">
        int ret = 0, ret1 = 0, ret2 = 0;
        for(int i = 0; i < idsGrupoOrigen.length; i++){
            Map<String, String> datos = new HashMap<String, String>();
            datos.put("IDTPO_DESTINO", idTipoAnimalDestino);
            datos.put("IDGPO_DESTINO", idGrupoDestino);
            datos.put("IDTPO_ORIGEN", idTipoAnimal);
            datos.put("IDGPO_ORIGEN", idsGrupoOrigen[i]);
            
            ret = controlTraslado.ActulizarAnimales(datos);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Inactivar Traslado">
        for(int i = 0; i < idsGrupoOrigen.length; i++){
            ret1 = controlTraslado.InactivarTraslados(idsGrupoOrigen[i]);
        }
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="GUARDAR TRASLADOS X ANIMALES">
        
        ret2 = controlTraslado.GuardarTrasladoGrupo(ListamodeloTraslado);
        
        //</editor-fold>
        
        if(ret == 0 && ret1 == 0 && ret2 == 0){
            JOptionPane.showMessageDialog(null, "La operacion se realizo satisfactoriamente");
            LimpiarFomulario();
        }
        
        
    }
    
    private String[] getIDsGrupoOrigen() {
        String[] ids = new String[lstGruposOrigen.getSelectedIndices().length];
        int x =0;
        for(int ind :lstGruposOrigen.getSelectedIndices()){
            ids[x] = listaGrupos.get(ind).get("id");
            x++;
        }
        return ids;
    }

    public void RetornoVistaGeneral(ModeloVentanaGeneral modeloVentanaGeneral, String motivo) {
        this.motivo = Utilidades.CodificarElemento(motivo);
        Guardar();
    }
    
}
