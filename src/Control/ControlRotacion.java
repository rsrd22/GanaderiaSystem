/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloRotacion;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlRotacion implements IControl{
    private gestorMySQL mySQL;
    
    public ControlRotacion(){
        this.mySQL = new gestorMySQL();
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
    public int Guardar(Object _rotacion) {
        ArrayList<String> consultas = new ArrayList<>();
        ArrayList<ModeloRotacion> Listarotacion = (ArrayList<ModeloRotacion>) _rotacion;
        
        for(ModeloRotacion rotacion:Listarotacion){
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO `rotacion_lote`\n" +
                            "(`id`, `id_lote`, `id_grupo`, `fecha_registro`, `fecha_entrada`, `fecha_salida`, `estado`, `fecha`, `id_usuario`)\n" +
                            "VALUES \n" +
                            "("+rotacion.getId()+",\n" +
                            "        "+rotacion.getId_lote()+",\n" +
                            "        "+rotacion.getId_grupo()+",\n" +
                            "        "+Utilidades.ValorNULL(rotacion.getFecha_registro())+",\n" +
                            "        "+Utilidades.ValorNULL(rotacion.getFecha_entrada())+",\n" +
                            "        NULL,\n" +
                            "        '"+rotacion.getEstado()+"',\n" +
                            "        "+rotacion.getFecha()+",\n" +
                            "        "+rotacion.getId_usuario()+");"
            //</editor-fold>
            );
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
    public int Actualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Eliminar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<Map<String, String>> ObtenerRotacion(String idFinca){
        try{
            String consulta = "";
            
            consulta = "SELECT \n" +
                            "IFNULL(rot.`id`, '') AS IDROTACION,\n" +
                            "grup.`id` AS IDGRUPO, grup.`descripcion` AS GRUPO, \n" +
                            "IFNULL(finc.`id`, '') AS IDFINCA, IFNULL(finc.`descripcion`, '') AS FINCA, \n" +
                            "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n" +
                            "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE,\n" +
                            "IFNULL(DATE_FORMAT(rot.`fecha_entrada`, '%d/%m/%Y'), '') FECHA_IN, IFNULL(DATE_FORMAT(rot.`fecha_registro`, '%d/%m/%Y'), '') FECHA_REG, \n" +
                            "IFNULL(DATE_FORMAT(rot.fecha_salida, '%d/%m/%Y'), '') FECHA_OUT, \n" +
                            "IFNULL(rot.`estado`, '') AS ESTADO, tpoani.`id` AS IDTPOANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL\n" +
                            "FROM `grupos` grup \n" +
                            "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = grup.`id_tipo_animal`\n" +
                            "LEFT JOIN `rotacion_lote` rot ON rot.`id_grupo` = grup.`id` AND rot.`estado` = 'Activo'\n" +
                            "LEFT JOIN `lotes` lot ON lot.`id` = rot.id_lote \n" +
                            "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n" +
                            "LEFT JOIN fincas finc ON finc.`id` = tpoani.`id_finca`\n" +
                            "WHERE `tpoani`.`id_finca` = '"+idFinca+"'\n" +
                            "ORDER BY grup.`id` ASC;";

            List<Map<String, String>> rotaciones = new ArrayList<Map<String, String>>();
            rotaciones = mySQL.ListSQL(consulta);

            return rotaciones;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
        
    }
    
    public int CancelarRegistroRotacion(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
        if(mySQL.SELECT("select * from rotacion_lote where id_grupo = '"+datos.get("IDGRUPO")+"'").size() >1)
            consultas.add("UPDATE `rotacion_lote` \n" +
                        "SET estado = 'Cancelado'\n" +
                        "WHERE id = '"+datos.get("IDROTACION")+"'");
        else
            return  Retorno.MENSAJE;
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
    
    public int ActivarAnteriorRegistroRotacion(Map<String, String> datos){
        ArrayList<String> consultas = new ArrayList<>();
        
        Map<String, String> datosant = mySQL.ListSQL("SELECT MAX(rot.id) IDROTACION \n" +
                                                        "FROM rotacion_lote  rot\n" +
                                                        "WHERE rot.`id_grupo` = '"+datos.get("IDGRUPO")+"' AND rot.`estado` = 'Inactivo'").get(0);
        
        consultas.add("UPDATE `rotacion_lote` \n" +
                        "SET estado = 'Activo'\n" +
                        "WHERE id = '"+datosant.get("IDROTACION")+"'");
        
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

    public int EliminarRotaciones(List<Map<String, String>> ListaDatosRotacionEliminar) {
        ArrayList<String> consultas = new ArrayList<>();
        for(Map<String, String> datos:ListaDatosRotacionEliminar){
            consultas.add(
            //                //<editor-fold defaultstate="collapsed" desc="DELETE">
                    "DELETE FROM `rotacion_lote` WHERE `id` = "+datos.get("ID")+";"
    //        //</editor-fold>
            );
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
    
    
    public String getId(String idLote) {
        //Revisar Metodo
        
        String consulta = "SELECT MAX(id) FROM `rotacion_lote` WHERE id_lote = '"+idLote+"' AND estado = 'Activo'";
        
        String idrotacion = mySQL.SELECT(consulta).get(0)[0];
        
        return idrotacion;
    }

    public int ActualizarRotacion(Object lista) { 
        ArrayList<ModeloRotacion> ListamodeloRotacion = (ArrayList<ModeloRotacion>) lista;
        ArrayList<String> consultas = new ArrayList<>();
        
        for(ModeloRotacion datos: ListamodeloRotacion){
            if(mySQL.ExistenDatos("select * from rotacion_lote WHERE `id_grupo` = '"+datos.getId_grupo()+"' AND estado = 'Activo'")){
                consultas.add("UPDATE `rotacion_lote`\n" +
                                "SET `fecha_salida` = '"+datos.getFecha_salida()+"',\n" +
                                "  `estado` = 'Inactivo'\n" +
                                "WHERE `id_grupo` = '"+datos.getId_grupo()+"' AND estado = 'Activo'");
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
    
    public int ActualizarRotacionLote(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
        
        //REVISAR METODO   
        
        consultas.add(
            //<editor-fold defaultstate="collapsed" desc="UPDATE">
            "UPDATE `rotacion_lotes`\n" +
                "SET `fecha_salida` = '"+datos.get("FECHA_SALIDA")+"',\n" +
                "  `estado` = 'Inactivo'\n" +
                "WHERE `id` = '"+datos.get("ID")+"' AND estado = 'Activo'"
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

    public List<Map<String, String>> getGruposxFinca(String idFinca) {
        
        String consulta = "SELECT gr.`id` AS ID, gr.`descripcion` AS DESCRIPCION\n" +
                            "FROM grupos gr \n" +
                            "INNER JOIN `tipo_animales` tip ON tip.`id` = gr.`id_tipo_animal`\n" +
                            "WHERE tip.`id_finca` = '"+idFinca+"'\n" +
                            "ORDER BY gr.`id` ASC ";
        
        List<Map<String, String>> listaModelo = new ArrayList<Map<String, String>>();
        listaModelo = mySQL.ListSQL(consulta);
        
        return listaModelo;
    }
}
