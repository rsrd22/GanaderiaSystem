/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloBloques;
import Modelo.ModeloFincas;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MERRY
 */
public class ControlBloques implements IControl{
    private gestorMySQL mySQL;
    private ArrayList<ModeloBloques> listaModelo;
    
    public ControlBloques(){
        mySQL = new gestorMySQL();
        listaModelo =  new ArrayList<>();
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
    public int Guardar(Object _bloque) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloBloques bloque = (ModeloBloques) _bloque;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `bloques`\n" +
                    "(`id`,`numero`, `id_finca`, `area`, `fecha`, `id_usuario`) VALUES \n" +
                    "("+bloque.getId()+",\n" +
                    "        "+bloque.getNumero()+",\n" +
                    "        "+bloque.getId_finca()+",\n" +
                    "        "+bloque.getArea()+",\n" +
                    "        "+bloque.getFecha()+",\n" +
                    "        "+bloque.getId_usuario()+");"
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
    public int Actualizar(Object _bloque) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloBloques bloque = (ModeloBloques) _bloque;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE `bloques`\n" +
                        "SET `numero` = "+bloque.getNumero()+",\n" +
                        "  `area` = "+bloque.getArea()+",\n" +
                        "  `fecha` = "+bloque.getFecha()+",\n" +
                        "  `id_usuario` = "+bloque.getId_usuario()+"\n" +
                        "WHERE `id` = "+bloque.getId()+";"
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
    public int Eliminar(Object _bloque) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloBloques bloque = (ModeloBloques) _bloque;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="DELETE">
                    "DELETE FROM `bloques` WHERE `id` = "+bloque.getId()+";"
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
    
    public boolean VerificarNumeroBloque(String idFinca, String Numero){
        String consulta = "SELECT * \n" +
                            "FROM bloques\n" +
                            "WHERE `id_finca` = '"+idFinca+"' AND numero = '"+Numero+"'";
        
        boolean ret  = mySQL.ExistenDatos(consulta);
        return ret;
    }
    
}
