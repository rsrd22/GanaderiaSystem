/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.*;
import Excel.ControlArchivo;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author MERRY
 */
public class VistaCargaMasivaAnimales extends javax.swing.JPanel {

    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idTipoAnimal;
    public List<Map<String, String>> listaInfoLeida = new ArrayList<>();
    public List<Map<String, String>> listaIngresados = new ArrayList<>();
    public List<Map<String, String>> listaNoIngresados = new ArrayList<>();
    public ControlCargaMasivaAnimales controlCarga;

    /**
     * Creates new form VistaCargaMasivaAnimales
     */
    public VistaCargaMasivaAnimales() {
        initComponents();
        txtRespuesta.setContentType("text/html");
        List<Map<String, String>> lista = new ArrayList<>();
        controlCarga = new ControlCargaMasivaAnimales();
        CargarListaFincas();
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

        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        lblTid3 = new javax.swing.JLabel();
        cbTipoAnimales = new javax.swing.JComboBox();
        lblTid1 = new javax.swing.JLabel();
        txtURL = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        btnSelectArchivo = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        txtRespuesta = new javax.swing.JEditorPane();
        cbGenero = new javax.swing.JComboBox();
        lbltitle10 = new javax.swing.JLabel();
        progreso = new javax.swing.JProgressBar();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbFinca, gridBagConstraints);

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(lblTid, gridBagConstraints);

        lblTid3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid3.setForeground(new java.awt.Color(59, 123, 50));
        lblTid3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid3.setText("Tipo Animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(lblTid3, gridBagConstraints);

        cbTipoAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbTipoAnimales, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Archivo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        add(lblTid1, gridBagConstraints);

        txtURL.setEditable(false);
        txtURL.setForeground(new java.awt.Color(59, 123, 50));
        txtURL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        txtURL.setCaretColor(new java.awt.Color(59, 123, 50));
        txtURL.setSelectionColor(new java.awt.Color(26, 82, 118));
        txtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtURLActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(txtURL, gridBagConstraints);

        jSeparator1.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jSeparator1, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(59, 123, 50));

        btnSelectArchivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSelectArchivo.setForeground(new java.awt.Color(255, 255, 255));
        btnSelectArchivo.setText("Seleccionar Archivo");
        btnSelectArchivo.setBorderPainted(false);
        btnSelectArchivo.setContentAreaFilled(false);
        btnSelectArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSelectArchivoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSelectArchivoMouseExited(evt);
            }
        });
        btnSelectArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectArchivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 166, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 7, Short.MAX_VALUE)
                    .addComponent(btnSelectArchivo)
                    .addGap(0, 8, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnSelectArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(jPanel4, gridBagConstraints);

        btnCargar.setBackground(new java.awt.Color(255, 255, 255));
        btnCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/importarVerde.png"))); // NOI18N
        btnCargar.setToolTipText("Cargar Animales");
        btnCargar.setBorderPainted(false);
        btnCargar.setContentAreaFilled(false);
        btnCargar.setPreferredSize(new java.awt.Dimension(30, 30));
        btnCargar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconos/importarVerde_over.png"))); // NOI18N
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        add(btnCargar, gridBagConstraints);

        scroll.setViewportView(txtRespuesta);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(scroll, gridBagConstraints);

        cbGenero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenero.setForeground(new java.awt.Color(59, 123, 50));
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Hembra", "Macho" }));
        cbGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGeneroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(cbGenero, gridBagConstraints);

        lbltitle10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle10.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle10.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 0);
        add(lbltitle10, gridBagConstraints);

        progreso.setBackground(new java.awt.Color(255, 255, 255));
        progreso.setForeground(new java.awt.Color(36, 151, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(progreso, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        if (cbFinca.getItemCount() > 0) {
            if (cbFinca.getSelectedIndex() >= 0) {
                idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
                CargarListaTipoAnimales();
            }
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalesActionPerformed
        if (cbTipoAnimales.getItemCount() > 0) {
            if (cbTipoAnimales.getSelectedIndex() >= 0) {
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
            }
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

    private void txtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtURLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtURLActionPerformed

    private void btnSelectArchivoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectArchivoMouseEntered
        Utilidades.establecerColorDeFondo(jPanel4, true);
    }//GEN-LAST:event_btnSelectArchivoMouseEntered

    private void btnSelectArchivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelectArchivoMouseExited
        Utilidades.establecerColorDeFondo(jPanel4, false);
    }//GEN-LAST:event_btnSelectArchivoMouseExited

    private void btnSelectArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectArchivoActionPerformed
        txtURL.setText(Expresiones.seleccionarArchivoExcel(false, "C:/"));
        //System.out.println("txtURL-->"+txtURL.getText());
    }//GEN-LAST:event_btnSelectArchivoActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        CargarAnimales();
    }//GEN-LAST:event_btnCargarActionPerformed

    private void cbGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGeneroActionPerformed

    }//GEN-LAST:event_cbGeneroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    public javax.swing.JButton btnSelectArchivo;
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbGenero;
    public javax.swing.JComboBox cbTipoAnimales;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid3;
    private javax.swing.JLabel lbltitle10;
    public javax.swing.JProgressBar progreso;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JEditorPane txtRespuesta;
    public javax.swing.JTextField txtURL;
    // End of variables declaration//GEN-END:variables

    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n"
                + "FROM `fincas`\n"
                + "/*UNION \n"
                + "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");

        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
        CargarListaTipoAnimales();
        //EventoComboFincas();
    }

    private void CargarListaTipoAnimales() {
        listaTipoAnimales = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n"
                + "UNION\n"
                + "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                + "FROM `tipo_animales`\n"
                + "WHERE id_finca = '" + idFinca + "' AND estado = 'Activo'\n"
                + "ORDER BY id ASC");

        Utilidades.LlenarComboBox(cbTipoAnimales, listaTipoAnimales, "DESCRIPCION");
        cbTipoAnimales.setSelectedIndex(0);
    }

    private void CargarAnimales() {
        try {
            String ruta = txtURL.getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("ruta-->" + ruta);
            ControlArchivo con = new ControlArchivo();
            if (idTipoAnimal.equals("-1")) {
                return;
            }
            if (idFinca.equals("-1")) {
                return;
            }
            if (ruta.equals("")) {
                return;
            }

            String ext = ruta.substring(ruta.lastIndexOf(".") + 1);
            if (ext.equals("xlsx")) {
                listaInfoLeida = con.LeerExcelAct(ruta);
            } else {
                listaInfoLeida = con.LeerExcel(ruta);
            }

            int valor = 0;
            progreso.setMaximum(listaInfoLeida.size());
//            for (Map<String, String> map : listaInfoLeida) {
//                System.out.println("************************************************************************************************");
//                for (Map.Entry<String, String> entry : map.entrySet()) {
//                    Object key = entry.getKey();
//                    Object val = entry.getValue();
//                    System.out.println("key-->"+key+"-------+value-->"+val);
//
//                }
//            }

            String inGrupos = "", inHierros = "";
            int fila = 0;
            if (listaInfoLeida.size() > 0) {
                System.out.println("listaInfoLeida.size()" + listaInfoLeida.size());
                //<editor-fold defaultstate="collapsed" desc="Listar GRUPOS Y HIERRO">
                List<Map<String, String>> listaGrupos = Utilidades.data_list(1, listaInfoLeida, new String[]{"GRUPO"});
                List<Map<String, String>> listaHierros = Utilidades.data_list(1, listaInfoLeida, new String[]{"HIERRO"});
                System.out.println("listaGrupos-->" + listaGrupos.size());
                System.out.println("listaHierros-->" + listaHierros.size());
                inGrupos = getIN(listaGrupos, "GRUPO");
                inHierros = getIN(listaHierros, "HIERRO");
                if (inGrupos.equals("") || inHierros.equals("")) {
                    //<editor-fold defaultstate="collapsed" desc="RESPUESTA">
                    String motivo = "";
                    if (inGrupos.equals("")) {
                        motivo = "No se encontro ningún GRUPO que coincida con los que se encuentran en el sistema.";
                    } else {
                        motivo = "No se encontro ningún HIERRO que coincida con los que se encuentran en el sistema.";
                    }
                    txtRespuesta.setText("<html>\n"
                            + "<body>\n"
                            + "    <table>\n"
                            + "        <tr>\n"
                            + "            <td style=\"font-weight: bold; color: #990000;\">" + motivo + " </td>"
                            + "        </tr>\n"
                            + "    </table>\n"
                            + "</body>\n"
                            + "</html>");

                    //</editor-fold>
                    return;
                }
                List<Map<String, String>> listaInfoGrupos = controlgen.GetComboBox("SELECT id AS ID, descripcion AS DESCRIPCION\n"
                        + "FROM grupos \n"
                        + "WHERE `id_tipo_animal` = '" + idTipoAnimal + "' AND  UPPER(`descripcion`) IN (" + inGrupos + ")");

                List<Map<String, String>> listaInfoHierros = controlgen.GetComboBox("SELECT id AS ID, `id_propietario` AS IDPROPIETARIO, descripcion AS DESCRIPCION\n"
                        + "FROM `propietarioxhierro`\n"
                        + "WHERE UPPER(TRIM(descripcion)) IN (" + inHierros + ")");
//</editor-fold>

                for (Map<String, String> info : listaInfoLeida) {
                    fila++;
                    Map<String, String> infoGrupo = getInfo(listaInfoGrupos, info.get("GRUPO"), "DESCRIPCION");
                    Map<String, String> infoHierro = getInfo(listaInfoHierros, info.get("HIERRO"), "DESCRIPCION");
                    info.put("IDFINCA", "" + idFinca);
                    info.put("IDTIPOANIMAL", "" + idTipoAnimal);
                    Calendar c = Calendar.getInstance();

                    //<editor-fold defaultstate="collapsed" desc="Sexo">
                    String s = info.get("SEXO");
                    System.out.println("s-->" + s);
                    if (s.length() > 1) {
                        s = s.substring(0, 1);
                    }
                    System.out.println("s--" + s);
                    if (s.toUpperCase().equals("M")) {
                        info.put("SEXO", "macho");
                    } else if (s.toUpperCase().equals("H")) {
                        info.put("SEXO", "hembra");
                    } else {
                        info.put("SEXO", "");
                    }
//</editor-fold>

                    if (infoGrupo.isEmpty() || infoHierro.isEmpty() || info.get("SEXO").equals("") || info.get("FEC_NACIMIENTO").equals("_")) {
//                        Map<String, String> noIngresado = new HashMap<>();
//                        noIngresado = info;
                        String motivo = "";
                        //<editor-fold defaultstate="collapsed" desc="Motivo">
                        if (info.get("SEXO").equals("")) {
                            motivo = "No se encontro un sexo especifico para el animal. por favor verifique e intentelo nuevamente.";
                        }
                        if (infoGrupo.isEmpty()) {
                            motivo = "No se entontro el Grupo " + info.get("GRUPO") + " registrado en el sistema. por favor verifique e intentelo nuevamente.";
                        } else {
                            motivo = "No se entontro el Hierro " + info.get("HIERRO") + " registrado en el sistema. por favor verifique e intentelo nuevamente.";
                        }
                        if (info.get("FEC_NACIMIENTO").equals("_")) {
                            motivo = "La fecha de Nacimiento no puede estar vacia. por favor verifique e intentelo nuevamente.";
                        }
//</editor-fold>
                        info.put("MOTIVO", "" + motivo);
                        info.put("FILA", "" + fila);
                        listaNoIngresados.add(info);
                        continue;
                    }

                    //<editor-fold defaultstate="collapsed" desc="Validaciones">
                    info.put("IDGRUPO", infoGrupo.get("ID"));
                    info.put("IDHIERRO", infoHierro.get("ID"));
                    String aux = "";
                    aux = info.get("HIERRO_C");
                    if (aux.equals("_") || aux.substring(0, 1).toUpperCase().equals("N")) {
                        info.put("HIERRO_C", "0");
                    } else {
                        info.put("HIERRO_C", "1");
                    }
                    aux = info.get("CAPADO");
                    if (aux.equals("_") || aux.substring(0, 1).toUpperCase().equals("N")) {
                        info.put("CAPADO", "0");
                    } else {
                        info.put("CAPADO", "1");
                    }
                    aux = info.get("DESCORNADO");
                    if (aux.equals("_") || aux.substring(0, 1).toUpperCase().equals("N")) {
                        info.put("DESCORNADO", "0");
                    } else {
                        info.put("DESCORNADO", "1");
                    }
                    aux = info.get("IMPLANTE");
                    if (aux.equals("_") || aux.substring(0, 1).toUpperCase().equals("N")) {
                        info.put("IMPLANTE", "0");
                    } else {
                        info.put("IMPLANTE", "1");
                    }
                    aux = info.get("DESTETE");
                    if (aux.equals("") || aux.substring(0, 1).toUpperCase().equals("N")) {
                        info.put("DESTETE", "0");
                    } else {
                        info.put("DESTETE", "1");
                    }

                    if (!info.get("FEC_NACIMIENTO").equals("_")) {
                        String[] dat = info.get("FEC_NACIMIENTO").split("/");
                        info.put("FEC_NACIMIENTO", dat[2] + "-" + dat[1] + "-" + dat[0]);
                    }
                    if (!info.get("FEC_DESTETE").equals("_")) {
                        String[] dat = info.get("FEC_DESTETE").split("/");
                        info.put("FEC_DESTETE", dat[2] + "-" + dat[1] + "-" + dat[0]);
                    } else {
                        info.put("FEC_DESTETE", "1900-01-01");
                    }

                    if (info.get("PESO_DESTETE").equals("_")) {
                        info.put("PESO_DESTETE", "0");
                    }
                    if (info.get("PESO").equals("_")) {
                        info.put("PESO", "0");
                    }

                    //</editor-fold>
                    List<Map<String, String>> InfoAnimal = controlgen.GetComboBox("SELECT id AS ID FROM animales  WHERE `numero` = '" + info.get("NUM_ANIMAL") + "' ");
                    int resp = -10;
                    if (InfoAnimal.size() > 0) {
                        info.put("IDANIMAL", "" + InfoAnimal.get(0).get("ID"));
                        resp = controlCarga.ActualizarAnimal(info);
                    } else {
                        resp = controlCarga.GuardarAnimal(info);

                    }

                    if (resp == Retorno.EXITO) {
                        listaIngresados.add(info);
                    } else {
                        String motivo = "";
                        if (resp == Retorno.ERROR) {
                            motivo = "Ocurrio un error al momento de guardar al animal. por favor verifique la información e intentelo nuevamente.";
                        } else if (resp == Retorno.EXCEPCION_SQL) {
                            motivo = "Ocurrio un error de conexión con la Base de Datos al momento de guardar al animal. por favor Comuniquese on el ADMIN del sistema.";
                        } else {
                            motivo = "Ocurrio un error inesperado al momento de guardar al animal. por favor Comuniquese on el ADMIN del sistema.";
                        }
                        info.put("FILA", "" + fila);
                        info.put("MOTIVO", "" + motivo);
                        listaNoIngresados.add(info);
                    }
                    progreso.setValue(valor++);
                }
                //<editor-fold defaultstate="collapsed" desc="RESPUESTA">

                txtRespuesta.setText("<html>\n"
                        + "<body>\n"
                        + "    <table>\n"
                        + "        <tr>\n"
                        + "            <td style=\"text-align: right; font-weight: bold;\">Animales Ingresados:</td>\n"
                        + "            <td>" + listaIngresados.size() + "</td>\n"
                        + (listaNoIngresados.size() > 0
                        ? "            <td style=\"text-align: right; font-weight: bold;\">Animales No Ingresados:</td>\n"
                        + "            <td>" + listaNoIngresados.size() + "</td>\n"
                        : "")
                        + "        </tr>\n"
                        + "        <tr>\n"
                        + "            <td colspan=\"" + (listaNoIngresados.size() > 0 ? "4" : "2") + "\" style=\"text-align: center;\">###</td>"
                        + "        </tr>\n"
                        + "    </table>\n"
                        + "</body>\n"
                        + "</html>");

                if (listaNoIngresados.size() > 0) {
                    String tblNoIngresados = "<table style=\"border-collapse: collapse;\">\n"
                            + "                    <tr style=\"background-color: #3B7B32; color: white;\">\n"
                            + "                        <td style=\"text-align: center; border: 1px solid #3B7B32;\">#</td>\n"
                            + "                        <td style=\"text-align: center; border: 1px solid #3B7B32;\">Fila Excel</td>\n"
                            + "                        <td style=\"text-align: center; border: 1px solid #3B7B32;\">Numero Animal</td>\n"
                            + "                        <td style=\"text-align: center; border: 1px solid #3B7B32;\">Motivo</td>\n"
                            + "                    </tr>";
                    for (int i = 0; i < listaNoIngresados.size(); i++) {
                        tblNoIngresados += "<tr style=\"background-color: " + (i % 2 == 0 ? "#ededed" : "#62885d") + "; "
                                + "" + (i % 2 != 0 ? "color: #FFFFFF;" : "") + "\">\n"
                                + "                        <td style='text-align: center;'>" + (i + 1) + "</td>\n"
                                + "                        <td style='text-align: center;'>" + listaNoIngresados.get(i).get("FILA") + "</td>\n"
                                + "                        <td style='text-align: center;'>" + listaNoIngresados.get(i).get("NUM_ANIMAL") + "</td>\n"
                                + "                        <td>" + listaNoIngresados.get(i).get("MOTIVO") + "</td>\n"
                                + "                    </tr>";
                    }
                    tblNoIngresados += "</table>";
                    txtRespuesta.setText(txtRespuesta.getText().replace("###", tblNoIngresados));
                } else {
                    txtRespuesta.setText(txtRespuesta.getText().replace("###", ""));
                }
                //</editor-fold>
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error al momento de realizar la operación. Error: " + e.toString());
        }
    }

    private String getIN(List<Map<String, String>> lista, String Key) {
        try {
            String in = "";

            for (int i = 0; i < lista.size(); i++) {
                System.out.println("**************" + i + "*****************");
                for (Map.Entry<String, String> entry : lista.get(i).entrySet()) {
                    String k = entry.getKey();
                    String v = entry.getValue();
                    System.out.println("k-->" + k + "-----v->" + v);
                }
            }

            for (Map<String, String> map : lista) {
                if (map.get(Key).equals("_")) {
                    continue;
                }
                in += (in.equals("") ? "" : ",") + "'" + map.get(Key) + "'";
            }

            return in;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> getInfo(List<Map<String, String>> lista, String valorB, String Key) {
        Map<String, String> ret = new HashMap<String, String>();

        for (Map<String, String> map : lista) {
            if (map.get(Key).equals(valorB)) {
                return map;
            }
        }

        return ret;
    }

}
