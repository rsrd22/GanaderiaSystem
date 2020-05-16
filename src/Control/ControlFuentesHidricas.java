/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloFuentesHidricas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlFuentesHidricas implements IControl{
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();
    private gestorMySQL mySQL;
    private ArrayList<ModeloFuentesHidricas> listaModelo;
    
    public ControlFuentesHidricas(){
        mySQL = new gestorMySQL();
        listaModelo = new ArrayList<>();
    }
    
    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Guardar(Object _fuente) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloFuentesHidricas fuente = (ModeloFuentesHidricas) _fuente;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `fuentes_hidricas`\n" +
                    "(`id`, `descripcion`, `estado`, `fecha`, `id_usuario`) VALUES \n" +
                    "("+fuente.getId()+",\n" +
                    "        '"+fuente.getDescripcion()+"',\n" +
                    "        '"+fuente.getEstado()+"',\n" +
                    "        "+fuente.getFecha()+",\n" +
                    "        "+fuente.getId_usuario()+");"
        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
                return Retorno.ERROR;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.CLASE_NO_ENCONTRADA;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.EXCEPCION_SQL;
        }
    }

    @Override
    public int Actualizar(Object _fuente) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloFuentesHidricas fuente = (ModeloFuentesHidricas) _fuente;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `fuentes_hidricas`\n" +
                    "SET `descripcion` = '"+fuente.getDescripcion()+"',\n" +
                    "  `estado` = '"+fuente.getEstado()+"',\n" +
                    "  `fecha` = "+fuente.getFecha()+",\n" +
                    "  `id_usuario` = "+fuente.getId_usuario()+"\n" +
                    "WHERE `id` = "+fuente.getId()+";"
        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
                return Retorno.ERROR;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.CLASE_NO_ENCONTRADA;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.EXCEPCION_SQL;
        }
    }

    @Override
    public int Eliminar(Object _fuente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object ObtenerDatosFiltro(Object descripcion) {
        String consulta = "SELECT * FROM fuentes_hidricas WHERE descripcion='" + descripcion + "'";
        List<Map<String, String>> fuentes = new ArrayList<Map<String, String>>();
        ArrayList<ModeloFuentesHidricas> lista = new ArrayList<>();
        fuentes = mySQL.ListSQL(consulta);

        if (fuentes.size() > 0) {

            for (Map<String, String> fuente : fuentes) {
                lista.add(new ModeloFuentesHidricas(
                        fuente.get("id"),
                        fuente.get("descripcion"),
                        fuente.get("estado"),
                        fuente.get("fecha"),
                        fuente.get("id_usuario")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }
    
}
