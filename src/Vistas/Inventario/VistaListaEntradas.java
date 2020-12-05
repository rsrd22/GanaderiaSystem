/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas.Inventario;

import Control.ControlGeneral;
import Control.Inventario.ControlInventario;
import Modelo.ModeloVentanaGeneral;
import Tablas.TablaRender;
import Utilidades.Utilidades;
import Vistas.VistaGeneral;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author DOLFHANDLER
 */
public class VistaListaEntradas extends javax.swing.JPanel {

    private String idFinca;
    private String id_producto;
    private Map<String, String> producto;
    private VistaInventario vistaInventario;
    private VistaGeneral vistaGeneral;

    private ArrayList<String> NameColumnasFiltro;
    private String[] EncabezadoTblEntradas;
    private String[] NameColumnas;
    private DefaultTableModel modeloTabla;
    private ControlGeneral controlgen;
    private List<Map<String, String>> listaEntradas;
    private ControlInventario controlInventario;
    private String descProducto;

    public VistaListaEntradas() {
        initComponents();
        setSize(500, 350);
    }

    public VistaListaEntradas(ModeloVentanaGeneral modeloVista) {
        initComponents();
        setSize(500, 350);
        controlgen = new ControlGeneral();
        controlInventario = new ControlInventario();
        listaEntradas = new ArrayList<>();
        producto = new HashMap<String, String>();
        producto = (HashMap<String, String>) modeloVista.getModeloDatos();
        id_producto = producto.get("id_producto");
        idFinca = producto.get("id_finca");
        descProducto = producto.get("PRODUCTO");
        vistaInventario = (VistaInventario) modeloVista.getPanelPadre();
        vistaGeneral = (VistaGeneral) modeloVista.getFrameVentana();

        //<editor-fold defaultstate="collapsed" desc="parametros iniciales de la tabla">
        modeloTabla = new DefaultTableModel();
        NameColumnasFiltro = new ArrayList<>();
        NameColumnasFiltro.add("FECHA");
        NameColumnasFiltro.add("CANTIDAD");
        NameColumnasFiltro.add("PRECIO");
        NameColumnasFiltro.add("PRECIOXCANTIDAD");
        EncabezadoTblEntradas = new String[]{
            "No.",
            "Fecha",
            "Cantidad",
            "Precio",
            "Precio por cantidad"
        };
        InicializarTablaEntradas();
        cargarTablaEntradas();
//</editor-fold>
    }

    private void InicializarTablaEntradas() {
        tblEntradas.setDefaultRenderer(Object.class, new TablaRender());

        modeloTabla = new DefaultTableModel(EncabezadoTblEntradas, 0) {
            Class[] types = new Class[]{
                java.lang.Object.class, 
                java.lang.Object.class,
                java.lang.Object.class, 
                java.lang.Object.class,
                java.lang.Object.class
            };

            public Class getColumnClass(int col) {
                return types[col];
            }

            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        tblEntradas.setModel(modeloTabla);

        tblEntradas.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblEntradas.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblEntradas.getColumnModel().getColumn(2).setPreferredWidth(20);
        tblEntradas.getColumnModel().getColumn(3).setPreferredWidth(30);
        tblEntradas.getColumnModel().getColumn(4).setPreferredWidth(50);

        tblEntradas.getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
            tblEntradas.getColumnModel().getColumn(i).setResizable(false);
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
            tcr.setFont(new Font("Tahoma", 0, 20));
            tcr.setHorizontalAlignment(SwingConstants.CENTER);
            tcr.setForeground(new Color(59, 123, 50));
            tblEntradas.getColumnModel().getColumn(i).setCellRenderer(tcr);

        }
        JTableHeader header = tblEntradas.getTableHeader();

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setPreferredSize(new Dimension(0, 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setVerticalAlignment(JLabel.CENTER);
    }

    private void cargarTablaEntradas() {
        txtEncabezado.setText("<html>"
                + "<p>Listado de las entradas del producto "
                + "<b>" + descProducto + "</b>"
                + "</p>"
                + "</html>");
        listaEntradas = (List<Map<String, String>>) controlInventario.ObtenerEntradas(idFinca, id_producto);
        
        MostrarTabla();
    }

    private void MostrarTabla() {
        System.out.println("****************MostrarTabla*****************");

        Utilidades.LimpiarTabla(tblEntradas);
        for (int i = 0; i < listaEntradas.size(); i++) {
            Utilidades.agregarFilaTabla(
                    modeloTabla,
                    new Object[]{
                        (i + 1),//consecutivo,
                        listaEntradas.get(i).get("FECHA"),
                        Utilidades.setFormatoNumerico(listaEntradas.get(i).get("CANTIDAD")),
                        Utilidades.setFormatoNumerico(listaEntradas.get(i).get("PRECIO")),
                        Utilidades.setFormatoNumerico(listaEntradas.get(i).get("PRECIOXCANTIDAD"))
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntradas = new javax.swing.JTable();
        txtEncabezado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(59, 123, 50)));
        setLayout(new java.awt.GridBagLayout());

        tblEntradas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblEntradas.setForeground(new java.awt.Color(59, 123, 50));
        tblEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEntradas.setFocusTraversalPolicyProvider(true);
        tblEntradas.setGridColor(new java.awt.Color(59, 123, 50));
        tblEntradas.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        tblEntradas.setMinimumSize(new java.awt.Dimension(200, 200));
        tblEntradas.setPreferredSize(new java.awt.Dimension(200, 200));
        tblEntradas.setSelectionBackground(new java.awt.Color(59, 123, 50));
        jScrollPane1.setViewportView(tblEntradas);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);

        txtEncabezado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEncabezado.setForeground(new java.awt.Color(59, 123, 50));
        txtEncabezado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtEncabezado.setText("Fecha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 0, 15);
        add(txtEncabezado, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblEntradas;
    private javax.swing.JLabel txtEncabezado;
    // End of variables declaration//GEN-END:variables
}
