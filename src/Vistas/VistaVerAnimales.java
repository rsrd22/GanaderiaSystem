/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Control.*;
import Control.ControlAnimales;
import Control.ControlGeneral;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author MERRY
 */
public class VistaVerAnimales extends javax.swing.JPanel {
    public DefaultTableModel modeloTblAnimales;
    public String[] EncabezadoTblAnimales;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idTipoAnimal;
    
    private List<Map<String, String>>  ListaAnimales;
    private List<Map<String, String>>  ListaAnimalesMostrar;
    private ControlAnimales controlAnimales = new ControlAnimales();
    public int allFincas;
    public ModeloVentanaGeneral objetoVentana;
    public String[] NameColumnas;
    Map<String, Map<String, String>> PropiedadesColumnas = new HashMap<>();
    
    /**
     * Creates new form VistaVerAnimales
     */
    public VistaVerAnimales() {
        initComponents();
        idFinca = "";
        idTipoAnimal = "";
        EncabezadoTblAnimales = new String[]{
            "No",
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Animal</p></html>", 
             "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Mamá</p></html>",  
             "Genero",
             "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Nacimiento</p></html>",
             "Peso", 
             "Hierro", 
             "Capado", 
             //"Muerte", 
             //"Venta",
             "Grupo", 
             "Lote", 
             "Ver Más"
        };
        ListaAnimales = new ArrayList<>();
        listaFincas = new ArrayList<>();
        listaTipoAnimales = new ArrayList<>();
        InicializarTblAnimales();
        CargarListaFincas();
    }

    public void InicializarTblAnimales() {
        tbl_Animales.setDefaultRenderer(Object.class, new TablaRender());
        
        modeloTblAnimales = new DefaultTableModel(EncabezadoTblAnimales, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,  java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,  java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class //, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Animales.setModel(modeloTblAnimales);

        tbl_Animales.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Animales.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(3).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(4).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(6).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(7).setPreferredWidth(50);
//        tbl_Animales.getColumnModel().getColumn(8).setPreferredWidth(60);
//        tbl_Animales.getColumnModel().getColumn(9).setPreferredWidth(70);
        tbl_Animales.getColumnModel().getColumn(8).setPreferredWidth(90);
        tbl_Animales.getColumnModel().getColumn(9).setPreferredWidth(130);
        tbl_Animales.getColumnModel().getColumn(10).setPreferredWidth(80);
        
        tbl_Animales.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblAnimales.getColumnCount(); i++) {
            tbl_Animales.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
//                if(i == 2 ){
//                    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//
//                }else{
                    tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
                tcr.setForeground(new Color(26, 82, 118));
                tbl_Animales.getColumnModel().getColumn(i).setCellRenderer(tcr);
            
            
        }
        JTableHeader header = tbl_Animales.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
        
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

        lblTid = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        cbTipoAnimales = new javax.swing.JComboBox();
        lblTid2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblTid1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Animales = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lblTid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid.setForeground(new java.awt.Color(59, 123, 50));
        lblTid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid.setText("Finca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 120;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(lblTid, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 230;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 0);
        add(cbFinca, gridBagConstraints);

        cbTipoAnimales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimales.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 0, 10);
        add(cbTipoAnimales, gridBagConstraints);

        lblTid2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid2.setForeground(new java.awt.Color(59, 123, 50));
        lblTid2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid2.setText("Tipo Animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(lblTid2, gridBagConstraints);

        txtFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro.setForeground(new java.awt.Color(59, 123, 50));
        txtFiltro.setBorder(null);
        txtFiltro.setCaretColor(new java.awt.Color(59, 123, 50));
        txtFiltro.setFocusCycleRoot(true);
        txtFiltro.setSelectionColor(new java.awt.Color(59, 123, 50));
        txtFiltro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFiltroFocusLost(evt);
            }
        });
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 510;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 0, 10);
        add(txtFiltro, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 509;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 10);
        add(jSeparator6, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Filtro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 10, 0, 0);
        add(lblTid1, gridBagConstraints);

        tbl_Animales.setForeground(new java.awt.Color(59, 123, 50));
        tbl_Animales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_Animales.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tbl_Animales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_AnimalesMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Animales);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 707;
        gridBagConstraints.ipady = 333;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        if(cbFinca.getItemCount() > 0){
            //EventoComboFincas();
            System.out.println("cbFinca.getSelectedIndex()--"+cbFinca.getSelectedIndex());
            if(cbFinca.getSelectedIndex() >= 0){
                idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
                CargarListaTipoAnimales();
            }else{
                cbTipoAnimales.removeAllItems();
                Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalesActionPerformed
        if(cbTipoAnimales.getItemCount() > 0){
            //EventoComboFincas();
            if(cbTipoAnimales.getSelectedIndex() >= 0){
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
                EventoComboFincas();
            }else{
                Utilidades.LimpiarTabla(tbl_Animales);
            }
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

    private void txtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFiltroFocusLost

    }//GEN-LAST:event_txtFiltroFocusLost

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed

    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased

    }//GEN-LAST:event_txtFiltroKeyReleased

    private void tbl_AnimalesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_AnimalesMouseReleased
        int fila = tbl_Animales.getSelectedRow();
        int cola = tbl_Animales.getSelectedColumn();
        String dato = ""+tbl_Animales.getValueAt(fila, cola);
//        if(cola == 8 && dato.toUpperCase().equals("SI")){//Muerte
//            String idAnimal = ListaAnimalesMostrar.get(fila).get("ID_ANIMAL");
//            
//                objetoVentana = new ModeloVentanaGeneral(this, new VistaDetalleMuerte(), 1, idAnimal);
//                objetoVentana.setFila(fila);
//                new VistaGeneral(objetoVentana).setVisible(true);
////            }
//        }
//        else if(cola == 9 && dato.toUpperCase().equals("SI")){//VENTA
//            
//            String idAnimal = ListaAnimalesMostrar.get(fila).get("ID_ANIMAL");
//            
//                objetoVentana = new ModeloVentanaGeneral(this, new VistaDetalleVenta(), 1, idAnimal);
//                objetoVentana.setFila(fila);
//                new VistaGeneral(objetoVentana).setVisible(true);
//            
//        
//        } 
//        if(cola == 8 ){//GRUPO
//            String idAnimal = ListaAnimalesMostrar.get(fila).get("ID_ANIMAL");
//            
//                objetoVentana = new ModeloVentanaGeneral(this, new VistaDetalleTraslado(), 1, idAnimal);
//                objetoVentana.setFila(fila);
//                new VistaGeneral(objetoVentana).setVisible(true);
//                
//        }  else if(cola == 9){//LOTE
//            String idAnimal = ListaAnimalesMostrar.get(fila).get("ID_ANIMAL");
//            
//            objetoVentana = new ModeloVentanaGeneral(this, new VistaDetalleRotacion(), 1, idAnimal);
//                objetoVentana.setFila(fila);
//                new VistaGeneral(objetoVentana).setVisible(true);
//        }  else 
        if(cola == 10){//VER MAS
            String idAnimal = ListaAnimalesMostrar.get(fila).get("ID_ANIMAL");
            
               objetoVentana = new ModeloVentanaGeneral(this, new VistaHistoriaAnimal(), 1, idAnimal); 
                objetoVentana.setFila(fila);
                new VistaGeneral(objetoVentana).setVisible(true);
        
           
        }

    }//GEN-LAST:event_tbl_AnimalesMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipoAnimales;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid2;
    private javax.swing.JTable tbl_Animales;
    public javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
    
    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n" +
                                                "FROM `fincas`\n"+
                                                "/*UNION \n"+
                                                "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");
         
        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
        CargarListaTipoAnimales();
        //EventoComboFincas();
    }
    private void CargarListaTipoAnimales() {
        listaTipoAnimales = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                    "UNION\n" +
                                                    "SELECT id AS ID, descripcion AS DESCRIPCION\n" +
                                                    "FROM `tipo_animales`\n" +
                                                    "WHERE id_finca = '"+idFinca+"' AND estado = 'Activo'\n" +
                                                    "ORDER BY id ASC");
         
        Utilidades.LlenarComboBox(cbTipoAnimales, listaTipoAnimales, "DESCRIPCION");
        cbTipoAnimales.setSelectedIndex(0);
        EventoComboFincas();
    }

    private void EventoComboFincas() {
        System.out.println("cbFincaActionPerformed---_>"+listaFincas.size());
        System.out.println("cbFinca.--->"+cbFinca.getItemCount());
        System.out.println("cbFinca.getSelectedIndex()..>"+cbFinca.getSelectedIndex());
        
        
            //idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        System.out.println("idFinca-->"+idFinca);
        if(idFinca.equals("ALL")){   
            allFincas = 1; 
        }else{
            allFincas = 0;
        }
        if(Integer.parseInt(idFinca)>0){
            ListaAnimales =(List<Map<String, String>>) controlAnimales.ObtenerDatosAnimales(""+idFinca, idTipoAnimal);
            if (ListaAnimales.size() > 0) {
                String col = "";
                for (Map.Entry<String, String> entry : ListaAnimales.get(0).entrySet()) {
                    String key = entry.getKey();
                    String[] split = key.split(Utilidades.SeparadorBusqueda);
                    Map h = new HashMap<String, String>();
                    h.put("nameCol", key);
                    if (split.length > 1) {
                        h.put("tamanio", split[1]);
                    }
                    if (split.length > 2) {
                        h.put("alineacion", split[2]);
                    }
                    PropiedadesColumnas.put(split[0], h);
                    col += (col.equals("") ? "" : "<::>") + split[0];
                }

                NameColumnas = col.split("<::>");

            } else {
                //CERRAR VENTAANA
            }
            MostrarTabla();
        }
    }

    private void MostrarTabla() {
        String filtro = Utilidades.CodificarElemento(txtFiltro.getText());
        
        ListaAnimalesMostrar = getFiltroLista(filtro);
        
        Utilidades.LimpiarTabla(tbl_Animales);
        for(int i =0; i < ListaAnimalesMostrar.size(); i++){ 
            Utilidades.agregarFilaTabla(
                    modeloTblAnimales,  
                    new Object[]{
                        (i+1),//tbl_Grupos.getRowCount()+1,
                        ListaAnimalesMostrar.get(i).get("NUMERO_ANIMAL"),
                        ListaAnimalesMostrar.get(i).get("NUMERO_MAMA"),
                        ListaAnimalesMostrar.get(i).get("GENERO").toUpperCase(), 
                        ListaAnimalesMostrar.get(i).get("FECHA_NACIMIENTO"), 
                        ListaAnimalesMostrar.get(i).get("PESO"), 
                        ListaAnimalesMostrar.get(i).get("DESC_HIERRO"), 
                        ListaAnimalesMostrar.get(i).get("CAPADO"), 
//                        ListaAnimalesMostrar.get(i).get("MUERTE"), 
//                        ListaAnimalesMostrar.get(i).get("VENTA"), 
                        ListaAnimalesMostrar.get(i).get("GRUPO"),
                        (allFincas == 1?ListaAnimalesMostrar.get(i).get("FINCA")+" / ":"") + 
                        ListaAnimalesMostrar.get(i).get("BLOQUE") + " / " + ListaAnimalesMostrar.get(i).get("LOTE"),
                        "Ver Más"
                    } 
                );
        }
    }
    
    private List<Map<String, String>> getFiltroLista(String filtro) {
        List<Map<String, String>> retorno = new ArrayList<>();
        System.out.println("***************getFiltroLista*****************" + filtro);
        int b = -1;
        String[] filtros = filtro.isEmpty() ? null : filtro.replace(" ", "<::>").split("<::>");
        String valores = "";
        for (int i = 0; i < ListaAnimales.size(); i++) {
            b = 1;
            if (filtro.isEmpty()) {
                retorno.add(ListaAnimales.get(i));
            } else {
                valores = "";
                for (int j = 0; j < NameColumnas.length; j++) {
                    System.out.println("NAme-" + j + "->" + NameColumnas[j]);
                    String value = ListaAnimales.get(i).get(PropiedadesColumnas.get(NameColumnas[j]).get("nameCol"));
                    valores += "" + value;
                }
                boolean encontro = Expresiones.filtrobusqueda(filtros, valores);
                System.out.println("i-" + i + "-b-" + b);
                if (encontro) {
                    retorno.add(ListaAnimales.get(i));
                }
            }
        }
        System.out.println("********************retorno --> " + retorno.size() + "***********************");
        return retorno;
    }



}
