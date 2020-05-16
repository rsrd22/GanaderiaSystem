/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MERRY
 */
public class ControlPluviometro implements IControl{
    private gestorMySQL mySQL;
    private ArrayList<ModeloPluviometro> listaModelo;
    
    public ControlPluviometro(){
        this.mySQL = new gestorMySQL();
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
    public int Guardar(Object _pluviometro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPluviometro pluviometro = (ModeloPluviometro) _pluviometro;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `pluviometro`\n" +
                    "(`id`, `id_finca`, `fecha_registro`, `cantidad`, `id_usuario`, `fecha`)\n" +
                    "VALUES \n" +
                    "("+pluviometro.getId()+", "+
                    ""+pluviometro.getId_finca()+", "+
                    "'"+pluviometro.getFecha_registro()+"', "+
                    ""+pluviometro.getCantidad()+", "+
                    ""+pluviometro.getId_usuario()+", "+
                    ""+pluviometro.getFecha()+");"
//        //</editor-fold>
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
    public int Actualizar(Object _pluviometro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPluviometro pluviometro = (ModeloPluviometro) _pluviometro;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `pluviometro`\n" +
                "SET `fecha_registro` = "+pluviometro.getFecha_registro()+",\n" +
                "  `cantidad` = "+pluviometro.getCantidad()+"',\n" +
                "  `id_usuario` = "+pluviometro.getId_usuario()+",\n" +
                "  `fecha` = "+pluviometro.getFecha()+"\n" +
                "WHERE `id` = "+pluviometro.getId()+";"
//        //</editor-fold>
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
    public int Eliminar(Object _pluviometro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPluviometro pluviometro = (ModeloPluviometro) _pluviometro;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM `pluviometro`\n" +
                "WHERE `id` = "+pluviometro.getId()+";"
//        //</editor-fold>
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
    public Object ObtenerDatosFiltro(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
