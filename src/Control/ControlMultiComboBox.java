/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Modelo.ModeloOpcionesMultiples;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author MERRY
 */
public class ControlMultiComboBox {
    
    public ArrayList<JPanel> ListaPnlOpciones= new ArrayList<>();
    public ArrayList<ModeloOpcionesMultiples> ListaDatosMultiple = new ArrayList<>();
    public ArrayList<ModeloOpcionesMultiples> ListaSeleccionados = new ArrayList<>();
    public javax.swing.JPanel pnlOpciones;
    public javax.swing.JTextField texto;
    public int tam = 30;
    

    public ControlMultiComboBox(JPanel pnlOpcionesVista, JTextField texto, ArrayList<ModeloOpcionesMultiples> ListaDatosMultiple){
        this.pnlOpciones = pnlOpcionesVista;
        this.ListaDatosMultiple = ListaDatosMultiple;
        this.texto = texto;
    }
    
    public void LlenarPnlOpciones() {                  
        pnlOpciones.removeAll();
        int ind = -1;
        System.out.println("ListaDatosMultiple--Control---->"+ListaDatosMultiple.size());
        System.out.println("pnlOpciones--_>"+pnlOpciones);
        for (ModeloOpcionesMultiples dato : ListaDatosMultiple) {
            ind++;
            javax.swing.JPanel pnlOpcion = new javax.swing.JPanel();
            javax.swing.JLabel lblDescripcion = new javax.swing.JLabel();
            javax.swing.JLabel lblEstado = new javax.swing.JLabel();

            if(dato.isEstado())
                pnlOpcion.setBackground(new java.awt.Color(59, 123, 50));
            else
                pnlOpcion.setBackground(new java.awt.Color(255, 123, 50));
            pnlOpcion.setForeground(new java.awt.Color(255, 255, 255));
            
            pnlOpcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            pnlOpcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(59, 123, 50), 1, true));
            pnlOpcion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            pnlOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            pnlOpcion.setName("" + ind);
            pnlOpcion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    int indr = Integer.parseInt(evt.getComponent().getName());
                    CambiarEstadoOpcion(indr);
                }
            });

            lblDescripcion.setText(dato.getDescripcion());
            lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));

            lblEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
            //lblEstado.setOpaque(true);
            //lblEstado.setBackground(new java.awt.Color(59, 123, 50));
            lblEstado.setText("+");//MousePressed
            lblEstado.setName("Estado" + ind);
            lblEstado.setForeground(new java.awt.Color(255, 255, 255));
            

            pnlOpcion.setToolTipText("Agregar Grupo " + dato.getDescripcion());
            pnlOpcion.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, pnlOpciones.getWidth()-tam, tam));
            pnlOpcion.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(3 + pnlOpciones.getWidth()-tam, 3, tam, tam));
            ListaPnlOpciones.add(pnlOpcion);
            pnlOpciones.add(pnlOpcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3+(tam*ind), pnlOpciones.getWidth(), tam));
        }
        
        
        
        
    }
    public void LlenarPnlOpcionesFiltro() {
        pnlOpciones.removeAll();
        int ind = -1;
        javax.swing.JTextField txtfiltro = new javax.swing.JTextField();
        pnlOpciones.add(txtfiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, pnlOpciones.getWidth(), tam));
        for (ModeloOpcionesMultiples dato : ListaDatosMultiple) {
            ind++;
            javax.swing.JPanel pnlOpcion = new javax.swing.JPanel();
            javax.swing.JLabel lblDescripcion = new javax.swing.JLabel();
            javax.swing.JLabel lblEstado = new javax.swing.JLabel();

            if(dato.isEstado())
                pnlOpcion.setBackground(new java.awt.Color(59, 123, 50));
            else
                pnlOpcion.setBackground(new java.awt.Color(255, 123, 50));
            pnlOpcion.setForeground(new java.awt.Color(255, 255, 255));
            
            pnlOpcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
            pnlOpcion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(59, 123, 50), 1, true));
            pnlOpcion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            pnlOpcion.setCursor(new Cursor(Cursor.HAND_CURSOR));
            pnlOpcion.setName("" + ind);
            pnlOpcion.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    int indr = Integer.parseInt(evt.getComponent().getName());
                    CambiarEstadoOpcion(indr);
                }
            });

            lblDescripcion.setText(dato.getDescripcion());
            lblDescripcion.setForeground(new java.awt.Color(255, 255, 255));

            lblEstado.setFont(new java.awt.Font("Tahoma", 1, 12));
            //lblEstado.setOpaque(true);
            //lblEstado.setBackground(new java.awt.Color(59, 123, 50));
            lblEstado.setText("+");//MousePressed
            lblEstado.setName("Estado" + ind);
            lblEstado.setForeground(new java.awt.Color(255, 255, 255));
            

            pnlOpcion.setToolTipText("Agregar Grupo " + dato.getDescripcion());
            pnlOpcion.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, pnlOpciones.getWidth()-tam, tam));
            pnlOpcion.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(3 + pnlOpciones.getWidth()-tam, 3, tam, tam));
            ListaPnlOpciones.add(pnlOpcion);
            pnlOpciones.add(pnlOpcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3+(tam*(ind+1)), pnlOpciones.getWidth(), tam));
        }
        
        
        
        
    }
    
    
    public void CambiarEstadoOpcion(int indice){
        System.out.println("indice-->"+indice);
        boolean  estado = ListaDatosMultiple.get(indice).isEstado();
        ListaDatosMultiple.get(indice).setEstado(!estado);
        if(!estado){
            ListaSeleccionados.add(ListaDatosMultiple.get(indice));
            ListaPnlOpciones.get(indice).setBackground(new java.awt.Color(59, 123, 50));
            ((JLabel)ListaPnlOpciones.get(indice).getComponent(1)).setText("-");
        }else{
            ListaSeleccionados.remove(getIndiceLista(ListaDatosMultiple.get(indice).getDescripcion()));
            ListaPnlOpciones.get(indice).setBackground(new java.awt.Color(255, 123, 50));
            ((JLabel)ListaPnlOpciones.get(indice).getComponent(1)).setText("+");
        }
        CambiarTextoOpcion();
    }

    public void CambiarTextoOpcion() {
        String tooltext = "";
        String text = "";
        for(int i = 0 ; i < ListaSeleccionados.size(); i++){
            tooltext += (i==0?"":" ,")+ListaSeleccionados.get(i).getDescripcion();
            text += (i==0?"":" ,")+(ListaSeleccionados.get(i).getDescripcion().length()>10?ListaSeleccionados.get(i).getDescripcion().substring(0, 10)+"...":ListaSeleccionados.get(i).getDescripcion());
        }
        texto.setText(text);
        texto.setToolTipText(tooltext);
    }

    public int getIndiceLista(String descripcion) {
        int ret = -1;
        for(int i = 0; i < ListaSeleccionados.size();i++){
            if(ListaSeleccionados.get(i).getDescripcion().equals(descripcion)){
                ret = i;
                break;
            }
        }
        return ret;
    }

    public String getIdSeleccionados() {
        String ret = "";
        
        for(int i = 0; i < ListaSeleccionados.size(); i++){
            ret += "<->"+ListaSeleccionados.get(i).getId();
        }
        return ret;
    }
    
    
}
