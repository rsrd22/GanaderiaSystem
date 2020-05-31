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
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControLotes implements IControl{
    private gestorMySQL mySQL;
    private ArrayList<ModeloLotes> listaModelo;
    
    
    public ControLotes(){
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
    public int Guardar(Object _lote) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloLotes lote = (ModeloLotes) _lote;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT"> 
                "insert into `lotes`\n" +         //`id_fuente_hidrica`,
                    "(`id`, `numero`, `id_bloque`,  `area`, `fecha`, `id_usuario`) values \n" +
                    "("+lote.getId()+",\n" +
                    "        "+lote.getNumero()+",\n" +
                    "        "+lote.getId_bloque()+",\n" +
                    //"        "+lote.getId_fuente_hidrica()+",\n" +
                    "        "+lote.getArea()+",\n" +
                    "        "+lote.getFecha()+",\n" +
                    "        "+lote.getId_usuario()+");"
//        //</editor-fold>
        );
        
        if(lote.getId_fuente_hidrica().length>0){
            for(String id:lote.getId_fuente_hidrica()){
                consultas.add("INSERT INTO `lotexfuente_hidrica`\n" +
                            "(`id`,`id_lote`, "+
                            "`id_fuente_hidrica`, `id_usuario`, `fecha`)\n" +
                            "VALUES \n" +
                            "('0', (SELECT id FROM lotes WHERE id_bloque = '"+lote.getId_bloque()+"' and numero = '"+lote.getNumero()+"'), "+
                            ""+id+", "+lote.getId_usuario()+", NOW());");
            }
        }

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
    public int Actualizar(Object _lote) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloLotes lote = (ModeloLotes) _lote;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `lotes`\n" +
                    "SET `numero` = "+lote.getNumero()+",\n" +
                    "  `id_bloque` = "+lote.getId_bloque()+",\n" +
                    "  `area` = "+lote.getArea()+",\n" +
                    "  `fecha` = "+lote.getFecha()+",\n" +
                    "  `id_usuario` = "+lote.getId_usuario()+"\n" +
                    "WHERE `id` = "+lote.getId()+";"
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
    public int Eliminar(Object _lote) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloLotes lote = (ModeloLotes) _lote;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM `lotes` WHERE `id` = "+lote.getId()+";"
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
    
    public Object ObtenerDatosFiltro(Object o){
        return null;
    }

    public boolean VerificarNumeroLotexBloque(String id_Bloque, String Numero) {
        String consulta = "SELECT * \n" +
                            "FROM lotes\n" +
                            "WHERE `id_bloque` = '"+id_Bloque+"' AND numero = '"+Numero+"'";
        
        boolean ret  = mySQL.ExistenDatos(consulta);
        return ret;
    }
    
    public int ActualizarFuentexLotes(ArrayList<Map<String, String>> idsFuentes) {
        ArrayList<String> consultas = new ArrayList<>();
        
        for(Map<String, String> lista: idsFuentes){
            if(lista.get("INSERT").equals("0")){
                consultas.add("INSERT INTO `lotexfuente_hidrica`\n" +
                                "(`id`,`id_lote`, "+
                                "`id_fuente_hidrica`, `id_usuario`, `fecha`)\n" +
                                "VALUES \n" +
                                "('0', "+lista.get("IDLOTE")+", "+
                                ""+lista.get("IDFUENTE")+", "+lista.get("IDUSUARIO")+", NOW());");
            }else{
                consultas.add("DELETE FROM lotexfuente_hidrica WHERE id = "+lista.get("ID"));
            }
            
        }
        
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
}
