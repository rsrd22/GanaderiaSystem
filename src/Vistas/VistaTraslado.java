/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vistas;

import Control.*;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.*;

/**
 *
 * @author MERRY
 */
public class VistaTraslado extends javax.swing.JPanel {
    public DefaultTableModel modeloTblTraslado;
    public String[] EncabezadoTblTraslado;
    public List<Map<String, String>> listaFincas;
    public List<Map<String, String>> listaTipoAnimales;
    public ControlGeneral controlgen = new ControlGeneral();
    public String idFinca;
    public String idTipoAnimal;
    public ArrayList<String> NameColumnasFiltro;
    
    private List<Map<String, String>>  ListaTraslado;
    private List<Map<String, String>>  ListaTrasladoMostrar;
    private List<Map<String, String>>  ListaAnimaleSeleccionados;
    private ControlTraslado controlTraslado = new ControlTraslado();
    public int allFincas;
    public ModeloVentanaGeneral objetoVentana;
    
    /**
     * Creates new form VistaTraslado
     */
    public VistaTraslado() {
        initComponents();
        idFinca = "";
        idTipoAnimal = "";
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("NUMERO_MAMA");
        NameColumnasFiltro.add("NUMERO_ANIMAL");
        NameColumnasFiltro.add("GRUPO");
        NameColumnasFiltro.add("FINCA");
        NameColumnasFiltro.add("BLOQUE");
        NameColumnasFiltro.add("LOTE");
        NameColumnasFiltro.add("FECHA_NACIMIENTO");
        NameColumnasFiltro.add("GENERO");
        NameColumnasFiltro.add("PESO");
        NameColumnasFiltro.add("ESTADO");
        EncabezadoTblTraslado = new String[]{
            "No", "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Mamá</p></html>", 
            "<html><p style=\"text-align:center;\">Número</p><p style=\"text-align:center;\">Animal</p></html>", "Grupo", "Lote", 
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Nacimiento</p></html>", "Genero", "Peso", "Estado", "Trasladar"
        };
        ListaTraslado = new ArrayList<>();
        listaFincas = new ArrayList<>();
        listaTipoAnimales = new ArrayList<>();
        ListaAnimaleSeleccionados = new ArrayList<>();
        
        InicializarTblBloques();
        CargarListaFincas();
        
    }
    
    public void InicializarTblBloques() {
        tbl_Traslado.setDefaultRenderer(Object.class, new TablaRender());
        
        modeloTblTraslado = new DefaultTableModel(EncabezadoTblTraslado, 0) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tbl_Traslado.setModel(modeloTblTraslado);

        tbl_Traslado.getColumnModel().getColumn(0).setPreferredWidth(25);
        tbl_Traslado.getColumnModel().getColumn(1).setPreferredWidth(70);
        tbl_Traslado.getColumnModel().getColumn(2).setPreferredWidth(70);
        tbl_Traslado.getColumnModel().getColumn(3).setPreferredWidth(90);
        tbl_Traslado.getColumnModel().getColumn(4).setPreferredWidth(130);
        tbl_Traslado.getColumnModel().getColumn(5).setPreferredWidth(70);
        tbl_Traslado.getColumnModel().getColumn(6).setPreferredWidth(70);
        tbl_Traslado.getColumnModel().getColumn(7).setPreferredWidth(50);
        tbl_Traslado.getColumnModel().getColumn(8).setPreferredWidth(60);
        tbl_Traslado.getColumnModel().getColumn(9).setPreferredWidth(70);
        
        tbl_Traslado.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTblTraslado.getColumnCount(); i++) {
            tbl_Traslado.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            if(i > 0){
//                if(i == 2 ){
//                    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//
//                }else{
                    tcr.setHorizontalAlignment(SwingConstants.CENTER);

//                }
                tcr.setForeground(new Color(26, 82, 118));
                tbl_Traslado.getColumnModel().getColumn(i).setCellRenderer(tcr);
            }
            
        }
        JTableHeader header = tbl_Traslado.getTableHeader();

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Traslado = new javax.swing.JTable();
        cbFinca = new javax.swing.JComboBox();
        lblTid = new javax.swing.JLabel();
        lblTid1 = new javax.swing.JLabel();
        cbTipoAnimales = new javax.swing.JComboBox();
        lblTid2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        tbl_Traslado.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_Traslado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_TrasladoMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Traslado);

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
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 15, 19);
        add(jScrollPane1, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        add(cbFinca, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
        add(lblTid, gridBagConstraints);

        lblTid1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTid1.setForeground(new java.awt.Color(59, 123, 50));
        lblTid1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTid1.setText("Filtro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        add(lblTid1, gridBagConstraints);

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
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 240;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 20);
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
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 0);
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 510;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 20);
        add(txtFiltro, gridBagConstraints);

        jSeparator6.setBackground(new java.awt.Color(59, 123, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 509;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        add(jSeparator6, gridBagConstraints);
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
                Utilidades.LimpiarTabla(tbl_Traslado);
            }
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void tbl_TrasladoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TrasladoMouseReleased
        int fila = tbl_Traslado.getSelectedRow();
        int cola = tbl_Traslado.getSelectedColumn();
        
        if(cola == 0){//SELECCIONAR
            boolean sel = (boolean) tbl_Traslado.getValueAt(fila, cola);
            tbl_Traslado.getModel().setValueAt(!sel, fila, cola);
            if(!sel){//SELECCIONADO
                ListaAnimaleSeleccionados.add(ListaTrasladoMostrar.get(fila));
            }else{// NO SELECCIONADO
                int ind = getIndiceLista(ListaTrasladoMostrar.get(fila).get("ID_GRUPO"));
                ListaAnimaleSeleccionados.remove(ind);
            }
        } else if(cola == 9){
            tbl_Traslado.setValueAt(true, fila, 0);
            System.out.println("ListaAnimaleSeleccionados--->"+ListaAnimaleSeleccionados.size());
            if(!ListaAnimaleSeleccionados.contains(ListaTrasladoMostrar.get(fila)))
                ListaAnimaleSeleccionados.add(ListaTrasladoMostrar.get(fila));
            System.out.println("ListaAnimaleSeleccionados--->"+ListaAnimaleSeleccionados.size());
            
            objetoVentana = new ModeloVentanaGeneral(this, new VistaTrasladar(), 1, ListaAnimaleSeleccionados);
            objetoVentana.setFila(fila);
            new VistaGeneral(objetoVentana).setVisible(true);
            boolean sel = (boolean) tbl_Traslado.getValueAt(fila, 0);
//            if(sel){
//                
//            }else{
//                JOptionPane.showMessageDialog(this, "Por favor seleccione esta fila para realizar la operación.");
//            }
        }
        
    }//GEN-LAST:event_tbl_TrasladoMouseReleased

    private void cbTipoAnimalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalesActionPerformed
        if(cbTipoAnimales.getItemCount() > 0){
            //EventoComboFincas();
            System.out.println("cbTipoAnimales.getSelectedIndex()>"+cbTipoAnimales.getSelectedIndex());
            if(cbTipoAnimales.getSelectedIndex() > 0){
                idTipoAnimal = listaTipoAnimales.get(cbTipoAnimales.getSelectedIndex()).get("ID");
                EventoComboFincas();
            }else{
                Utilidades.LimpiarTabla(tbl_Traslado);
                cbTipoAnimales.requestFocusInWindow();
            }
        }
    }//GEN-LAST:event_cbTipoAnimalesActionPerformed

    private void txtFiltroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFiltroFocusLost

    }//GEN-LAST:event_txtFiltroFocusLost

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            MostrarTabla();
        }
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        if (txtFiltro.getText().equals("")) {
            MostrarTabla();
        }
    }//GEN-LAST:event_txtFiltroKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipoAnimales;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblTid;
    private javax.swing.JLabel lblTid1;
    private javax.swing.JLabel lblTid2;
    private javax.swing.JTable tbl_Traslado;
    public javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
    
    public void RetornoVistaGeneral(ModeloVentanaGeneral objeto, Object retorno) {
        
        if(objeto.getOpcion() == 1){// VISTA ROTAR
            
            EventoComboFincas();
        }
    }
    
    private void CargarListaFincas() {
        listaFincas = controlgen.GetComboBox("SELECT '-1' AS ID, 'Seleccionar' AS DESCRIPCION\n" +
                                                "UNION\n" +
                                                "SELECT `id` AS ID, `descripcion` AS DESCRIPCION\n" +
                                                "FROM `fincas`\n"+
                                                "/*UNION \n"+
                                                "SELECT 'ALL' AS ID, 'TODOS' AS DESCRIPCION*/");
         
        Utilidades.LlenarComboBox(cbFinca, listaFincas, "DESCRIPCION");
        cbFinca.setSelectedIndex(1);
        cbTipoAnimales.requestFocusInWindow();
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
        cbTipoAnimales.requestFocusInWindow();
        //EventoComboFincas();
    }

    private int getIndiceLista(String Dato) {
        for(int i = 0; i < ListaAnimaleSeleccionados.size(); i++){
            if(ListaAnimaleSeleccionados.get(i).get("ID_GRUPO").equals(Dato)){
                return i;
            }
        }
        return -1;
    }

    private void EventoComboFincas() {
        System.out.println("cbFincaActionPerformed---_>"+listaFincas.size());
        System.out.println("cbFinca.--->"+cbFinca.getItemCount());
        System.out.println("cbFinca.getSelectedIndex()..>"+cbFinca.getSelectedIndex());
        ListaAnimaleSeleccionados = new ArrayList<>();
        
            //idFinca = listaFincas.get(cbFinca.getSelectedIndex()).get("ID");
        System.out.println("idFinca-->"+idFinca);
        if(idFinca.equals("ALL")){   
            allFincas = 1; 
        }else{
            allFincas = 0;
        }
        if(Integer.parseInt(idFinca)>0){
            ListaTraslado =(List<Map<String, String>>) controlTraslado.ObtenerDatosTraslado(""+idFinca, idTipoAnimal);
        
            MostrarTabla();
        }
        
    }
    
    private void MostrarTabla() {
        System.out.println("****************MostrarTabla*****************");
        String filtro = Utilidades.CodificarElemento(txtFiltro.getText());
        System.out.println("filtro--"+filtro);
        ListaTrasladoMostrar = getFiltroLista(filtro);
        
        if(Integer.parseInt(idFinca)>0){
            //ListaTraslado =(List<Map<String, String>>) controlTraslado.ObtenerDatosTraslado(""+idFinca, idTipoAnimal);
            Utilidades.LimpiarTabla(tbl_Traslado);
            for(int i =0; i < ListaTrasladoMostrar.size(); i++){
                Utilidades.agregarFilaTabla(
                        modeloTblTraslado,  
                        new Object[]{
                            false,//tbl_Grupos.getRowCount()+1,
                            ListaTrasladoMostrar.get(i).get("NUMERO_MAMA"),
                            ListaTrasladoMostrar.get(i).get("NUMERO_ANIMAL"),
                            Utilidades.decodificarElemento(ListaTrasladoMostrar.get(i).get("GRUPO")),
                            (allFincas == 1?Utilidades.decodificarElemento(ListaTrasladoMostrar.get(i).get("FINCA"))+" / ":"") + 
                            ListaTrasladoMostrar.get(i).get("BLOQUE") + " / " + ListaTraslado.get(i).get("LOTE"),
                            ListaTrasladoMostrar.get(i).get("FECHA_NACIMIENTO"), 
                            ListaTrasladoMostrar.get(i).get("GENERO").toUpperCase(), 
                            ListaTrasladoMostrar.get(i).get("PESO"), 
                            ListaTrasladoMostrar.get(i).get("ESTADO"), 
                            "Trasladar" 
                        } 
                    );
            }
            cbTipoAnimales.requestFocusInWindow();
        }
        
    }
    
    private List<Map<String, String>> getFiltroLista(String filtro) {
        List<Map<String, String>> retorno = new ArrayList<>();
        System.out.println("***************getFiltroLista*****************" + filtro);
        int b = -1;
        String[] filtros = filtro.isEmpty() ? null : filtro.replace(" ", "<::>").split("<::>");
        String valores = "";
        for (int i = 0; i < ListaTraslado.size(); i++) {
            b = 1;
            if (filtro.isEmpty()) {
                retorno.add(ListaTraslado.get(i));
            } else {
                valores = "";
                for (int j = 0; j < NameColumnasFiltro.size(); j++) {
                    System.out.println("NAme-" + j + "->" + NameColumnasFiltro.get(j));
                    String value = ListaTraslado.get(i).get(NameColumnasFiltro.get(j));
                    valores += "" + value;
                }
                boolean encontro = Expresiones.filtrobusqueda(filtros, valores);
                System.out.println("i-" + i + "-b-" + b);
                if (encontro) {
                    retorno.add(ListaTraslado.get(i));
                }
            }
        }
        System.out.println("********************retorno --> " + retorno.size() + "***********************");
        return retorno;
    }
    
}
