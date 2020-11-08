/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.*;
import Control.Retorno;
import Modelo.*;
import Tablas.TablaRender;
import static Utilidades.Consultas.consultas;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
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
 * @author DOLFHANDLER
 */
public class VistaHistoricoVentas extends javax.swing.JPanel {

    private ControlGeneral controlGral;
    private ControlMuertesVentasHistoricos controlMyVHist;
    private ModeloMuertesVentasHistoricos modeloMyVHist;
    private List<Map<String, String>> fincas;
    private List<Map<String, String>> tipoAnimales;
    private List<Map<String, String>> ventas;
    private DefaultTableModel modelo;
    private String[] EncabezadoTblVentas;
    //Map<String, Map<String, String>> PropiedadesColumnas = new HashMap<>();
    public ModeloVentanaGeneral objetoVentana;
    public int idModulo = 14;
    /**
     * Creates new form VistaHistoricoVentas
     */
    public VistaHistoricoVentas() {
        initComponents();
        Utilidades.EstablecerPermisosVista2(this, idModulo, 0);
        modelo = new DefaultTableModel();
        EncabezadoTblVentas = new String[]{
            "Nro. Animal",    //0
            "Nro. Madre",     //1
            "Genero",         //2
            "<html><p style=\"text-align:center;\">Vendido</p><p style=\"text-align:center;\">a</p></html>",      //3
            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Venta</p></html>", //4
            "Peso (Kg)",      //5
            "<html><p style=\"text-align:center;\">Peso</p><p style=\"text-align:center;\">Canal (Kg)</p></html>",  //6
            "<html><p style=\"text-align:center;\">Porcentaje</p><p style=\"text-align:center;\">Canal (%)</p></html>",     //7
            "<html><p style=\"text-align:center;\">Precio</p><p style=\"text-align:center;\">Venta (Kg)</p></html>",//8 
            "<html><p style=\"text-align:center;\">Valor</p><p style=\"text-align:center;\">Venta</p></html>", //9
            "",                //10  
            ""                //11  
        };
        controlGral = new ControlGeneral();
        controlMyVHist = new ControlMuertesVentasHistoricos();
        modeloMyVHist = new ModeloMuertesVentasHistoricos();
        InicializarTblVentas();
        cargarComboFincas();
    }
    
    public void InicializarTblVentas() {
        tabla.setDefaultRenderer(Object.class, new TablaRender());
        
        modelo = new DefaultTableModel(EncabezadoTblVentas, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tabla.setModel(modelo);
//        "Nro. Animal",    //0
//            "Nro. Madre",     //1
//            "Genero",         //2
//            "<html><p style=\"text-align:center;\">Vendido</p><p style=\"text-align:center;\">a</p></html>",      //3
//            "<html><p style=\"text-align:center;\">Fecha</p><p style=\"text-align:center;\">Venta</p></html>", //4
//            "Peso (Kg)",      //5
//            "<html><p style=\"text-align:center;\">Peso</p><p style=\"text-align:center;\">Canal (Kg)</p></html>",  //6
//            "<html><p style=\"text-align:center;\">Porcentaje</p><p style=\"text-align:center;\">Canal (%)</p></html>",     //7
//            "<html><p style=\"text-align:center;\">Precio</p><p style=\"text-align:center;\">Venta (Kg)</p></html>",//8 
//            "<html><p style=\"text-align:center;\">Valor</p><p style=\"text-align:center;\">Venta</p></html>", //9
//            ""                //10  

        tabla.getColumnModel().getColumn(0).setPreferredWidth(80); //0
        tabla.getColumnModel().getColumn(1).setPreferredWidth(80); //1
        tabla.getColumnModel().getColumn(2).setPreferredWidth(50); //2
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);//3 
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100); //4
        tabla.getColumnModel().getColumn(5).setPreferredWidth(80); //5
        tabla.getColumnModel().getColumn(6).setPreferredWidth(80); //6
        tabla.getColumnModel().getColumn(7).setPreferredWidth(60); //7
        tabla.getColumnModel().getColumn(8).setPreferredWidth(90); //8
        tabla.getColumnModel().getColumn(9).setPreferredWidth(90); //9
        tabla.getColumnModel().getColumn(10).setPreferredWidth(60); //10
        tabla.getColumnModel().getColumn(11).setPreferredWidth(60); //11
        
        tabla.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modelo.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 12));
            
//            if(i == 3 ){
//                tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//               
//            }else{
                tcr.setHorizontalAlignment(SwingConstants.CENTER);
                
//            }
            tcr.setForeground(new Color(26, 82, 118));
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
            
        }
        JTableHeader header = tabla.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
    }

    private void cargarComboFincas() {
        String consulta = consultas.get("CARGAR_COMBO_FINCAS");
        fincas = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbFinca, fincas, "descripcion");
        cbFinca.setSelectedIndex(1);
    }

    private void cargarComboTipoAnimales() {
        String consulta = consultas.get("CARGAR_COMBO_TIPO_ANIMALES") + txtCodigoFinca.getText();
        tipoAnimales = controlGral.GetComboBox(consulta);

        Utilidades.LlenarComboBox(cbTipoAnimal, tipoAnimales, "descripcion");
        System.out.println("qwqwqwqwqwq");
    }

    public void cargarHistoricoVentas() {
        String consulta = consultas.get("OBTENER_HISTORICO_VENTAS").replace("PARAMETRO1", txtCodigoTipoAnimal.getText());
        System.out.println("consulta-->"+consulta);
        Utilidades.LimpiarTabla(tabla);
        ventas = controlGral.GetConsulta(consulta);

        if (ventas.size() == 0) {
            return;
        }

        MostrarTabla();
    }

    private void MostrarTabla() {
        Utilidades.LimpiarTabla(tabla);
        DecimalFormat df = new DecimalFormat("#.0");
        for (int i = 0; i < ventas.size(); i++) {
            double porc = 0;
            double valor_venta = 0;
            
            if(!ventas.get(i).get("peso_canal").equals("0")){
                porc = Math.round(Double.parseDouble(ventas.get(i).get("peso_canal")) / Double.parseDouble(ventas.get(i).get("peso")) * 100);
                
                valor_venta = Double.parseDouble(ventas.get(i).get("precio_venta"))*Double.parseDouble(ventas.get(i).get("peso_canal"));
            }else{
                valor_venta = Double.parseDouble(ventas.get(i).get("precio_venta"))*Double.parseDouble(ventas.get(i).get("peso"));
            }
            
            Utilidades.agregarFilaTabla(
                modelo,
                new Object[]{
                    ventas.get(i).get("numero"),
                    ventas.get(i).get("numero_mama"),
                    (""+ventas.get(i).get("genero").charAt(0)).toUpperCase(),
                    ventas.get(i).get("tipo_venta").toUpperCase(),
                    ventas.get(i).get("fecha_venta"),
                    ventas.get(i).get("peso"),
                    ventas.get(i).get("peso_canal"),
                    (!ventas.get(i).get("peso_canal").equals("0")?""+df.format(porc):""),
                    Utilidades.MascaraMonedaConDecimales(ventas.get(i).get("precio_venta").replace(".", ",")),
                    ""+Utilidades.MascaraMonedaConDecimales((""+valor_venta).replace(".", ",")),
                    "Modificar",
                    "Anular"
                }
            );
        }
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

        txtCodigoTipoAnimal = new javax.swing.JLabel();
        txtCodigoFinca = new javax.swing.JLabel();
        lbltitle8 = new javax.swing.JLabel();
        cbFinca = new javax.swing.JComboBox();
        lbltitle4 = new javax.swing.JLabel();
        cbTipoAnimal = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        lbltitle8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle8.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle8.setText("Fincas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        add(lbltitle8, gridBagConstraints);

        cbFinca.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbFinca.setForeground(new java.awt.Color(59, 123, 50));
        cbFinca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbFinca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFincaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 183;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 0);
        add(cbFinca, gridBagConstraints);

        lbltitle4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbltitle4.setForeground(new java.awt.Color(59, 123, 50));
        lbltitle4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltitle4.setText("Tipo de animales");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        add(lbltitle4, gridBagConstraints);

        cbTipoAnimal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbTipoAnimal.setForeground(new java.awt.Color(59, 123, 50));
        cbTipoAnimal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbTipoAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoAnimalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 133;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 20, 0, 20);
        add(cbTipoAnimal, gridBagConstraints);

        tabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla.setForeground(new java.awt.Color(59, 123, 50));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setSelectionBackground(new java.awt.Color(59, 123, 50));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaMouseReleased(evt);
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 867;
        gridBagConstraints.ipady = 323;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 22, 20);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFincaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFincaActionPerformed
        int indice = cbFinca.getSelectedIndex();
        if (indice > 0) {
            String idFinca = fincas.get(indice).get("id");
            txtCodigoFinca.setText(idFinca);
            cargarComboTipoAnimales();
        }
    }//GEN-LAST:event_cbFincaActionPerformed

    private void cbTipoAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoAnimalActionPerformed
        int indice = cbTipoAnimal.getSelectedIndex();
        if (indice > 0) {
            String idTipoAnimal = tipoAnimales.get(indice).get("id");
            txtCodigoTipoAnimal.setText(idTipoAnimal);
            
            cargarHistoricoVentas();
        } else {
            Utilidades.LimpiarTabla(tabla);
        }
    }//GEN-LAST:event_cbTipoAnimalActionPerformed

    private void tablaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyReleased
        
    }//GEN-LAST:event_tablaKeyReleased

    private void tablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseReleased
        int fila = tabla.getSelectedRow();
        int cola = tabla.getSelectedColumn();
        System.out.println("cola:"+cola);
        String dato = ""+tabla.getValueAt(fila, cola);
        if(cola == 10){
            objetoVentana = new ModeloVentanaGeneral(this, new VistaModificarVenta(), 1, ventas.get(fila));
            objetoVentana.setFila(fila);
            new VistaGeneral(objetoVentana).setVisible(true);
        }else if(cola == 11){
            int resp = JOptionPane.showConfirmDialog(null, "¿está seguro de Anular esta Venta?", "Anular Venta", JOptionPane.YES_NO_OPTION);
            if(resp == JOptionPane.YES_OPTION){
                modeloMyVHist.setEstado("Activo");
                modeloMyVHist.setIdAnimal(""+ventas.get(fila).get("id"));
                modeloMyVHist.setTipo("venta");
                modeloMyVHist.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
                
                int ret = controlMyVHist.GuardarAnular(modeloMyVHist);
                if(ret == Retorno.EXITO){
                    JOptionPane.showMessageDialog(null, "La operación se realizo con exito.");
                    cargarHistoricoVentas();
                }
                
            }
        }
    }//GEN-LAST:event_tablaMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox cbFinca;
    public javax.swing.JComboBox cbTipoAnimal;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbltitle4;
    private javax.swing.JLabel lbltitle8;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel txtCodigoFinca;
    private javax.swing.JLabel txtCodigoTipoAnimal;
    // End of variables declaration//GEN-END:variables

    public void RetornoVistaGeneral(ModeloVentanaGeneral modeloVentanaGeneral) {
        cargarHistoricoVentas();
    }
}
