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
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlRotacionDosTablas {
    private gestorMySQL mySQL;
    private ArrayList<ModeloRotacionLotes> listaModeloRotacionLotes;
    private ArrayList<ModeloRotacionGrupos> listaModeloRotacionGrupos;

    public ControlRotacionDosTablas() {
        this.mySQL = new gestorMySQL();
        listaModeloRotacionLotes = new ArrayList<>();
        listaModeloRotacionGrupos = new ArrayList<>();
    }
    
    public List<Map<String, String>> ObtenerRotacion(String idFinca){
        try{
            String consulta = "SELECT IFNULL(rotacion.`id`, '') AS IDROTACION, IFNULL(`rotgrupo`.`id`, '') AS IDREOGRUPO,\n" +
                                "grup.`id` AS IDGRUPO, grup.`descripcion` AS GRUPO, \n" +
                                "IFNULL(finc.`id`, '') AS IDFINCA, IFNULL(finc.`descripcion`, '') AS FINCA, \n" +
                                "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n" +
                                "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE,\n" +
                                "IFNULL(DATE_FORMAT(rotacion.`fecha_entrada`, '%d/%m/%Y'), '') FECHA_IN, IFNULL(DATE_FORMAT(rotacion.`fecha_registro`, '%d/%m/%Y'), '') FECHA_REG, \n" +
                                "IFNULL(DATE_FORMAT(rotacion.`fecha_salida`, '%d/%m/%Y'), '') FECHA_OUT, \n" +
                                "IFNULL(rotacion.`estado`, '') AS ESTADO, tpoani.`id` AS IDTPOANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL\n" +
                                "FROM `grupos` grup \n" +
                                "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = grup.`id_tipo_animal`\n" +
                                "LEFT JOIN `rotacion_lotesxgrupo` rotgrupo ON  `rotgrupo`.`id_grupo` = grup.`id`\n" +
                                "LEFT JOIN `rotacion_lotesxestado` rotacion ON `rotgrupo`.`id_rotacion_lotesxestado` = rotacion.`id` AND rotacion.`estado` = 'Activo'\n" +
                                "LEFT JOIN `lotes` lot ON lot.`id` = rotacion.`id_lote` \n" +
                                "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n" +
                                "LEFT JOIN fincas finc ON finc.`id` = tpoani.`id_finca`\n" +
                                "WHERE `tpoani`.`id_finca` = '"+idFinca+"'\n" +
                                "ORDER BY grup.`id` ASC";
            consulta = "SELECT IFNULL(tblrot.ID_ROTACION, '') AS IDROTACION, IFNULL(tblrot.ID_ROT_GRUPO, '') AS IDROTGRUPO,\n" +
                        "grup.`id` AS IDGRUPO, grup.`descripcion` AS GRUPO, \n" +
                        "IFNULL(finc.`id`, '') AS IDFINCA, IFNULL(finc.`descripcion`, '') AS FINCA, \n" +
                        "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n" +
                        "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE,\n" +
                        "IFNULL(DATE_FORMAT(tblrot.FECHA_ENTRADA, '%d/%m/%Y'), '') FECHA_IN, IFNULL(DATE_FORMAT(tblrot.FECHA_REGISTRO, '%d/%m/%Y'), '') FECHA_REG, \n" +
                        "IFNULL(DATE_FORMAT(tblrot.FECHA_SALIDA, '%d/%m/%Y'), '') FECHA_OUT, \n" +
                        "IFNULL(tblrot.ESTADO_LOTE, '') AS ESTADO, tpoani.`id` AS IDTPOANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL,\n" +
                        "IFNULL(tblrot.ESTADO_GRUPO, '') AS ESTADO_GRUPO\n" +
                        "FROM `grupos` grup \n" +
                        "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = grup.`id_tipo_animal`\n" +
                        "LEFT JOIN (\n" +
                        "	SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n" +
                        "	rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n" +
                        "	rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n" +
                        "	FROM `rotacion_lotesxestado` rot\n" +
                        "	INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n" +
                        "	WHERE rot.estado = 'Activo' AND rotgrup.`estado` = 'Activo'\n" +
                        ") tblrot ON tblrot.ID_GRUPO = grup.`id`\n" +
                        "LEFT JOIN `lotes` lot ON lot.`id` = tblrot.ID_LOTE \n" +
                        "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n" +
                        "LEFT JOIN fincas finc ON finc.`id` = tpoani.`id_finca`\n" +
                        "WHERE `tpoani`.`id_finca` = '"+idFinca+"'\n" +
                        "ORDER BY grup.`id` ASC";

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
        
        consultas.add("UPDATE `rotacion_lotesxgrupo` \n" +
                        "SET estado = 'Cancelado'\n" +
                        "WHERE id = '"+datos.get("IDROTGRUPO")+"'");
        
        if(mySQL.ListSQL("SELECT *FROM rotacion_lotesxgrupo WHERE  id_rotacion_lotesxestado = '"+datos.get("IDROTACION")+"'").size() == 1){
            consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `rotacion_lotesxestado`\n" +
                    "SET `estado` = 'Cancelado' \n" +
                    "WHERE id = '"+datos.get("IDROTACION")+"' "
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
    
    public int ActivarAnteriorRegistroRotacion(Map<String, String> datos){
        ArrayList<String> consultas = new ArrayList<>();
        
        Map<String, String> datosant = mySQL.ListSQL("SELECT MAX(rotgru.id) IDROTGRUPO, rot.`id` IDROTACION\n" +
                                                        "FROM rotacion_lotesxgrupo  rotgru\n" +
                                                        "INNER JOIN `rotacion_lotesxestado` rot ON rot.`id` = rotgru.`id_rotacion_lotesxestado`\n" +
                                                        "WHERE rotgru.`id_grupo` = '"+datos.get("IDGRUPO")+"' AND rotgru.`estado` = 'Inactivo'").get(0);
        
        consultas.add("UPDATE `rotacion_lotesxgrupo` \n" +
                        "SET estado = 'Activo'\n" +
                        "WHERE id = '"+datosant.get("IDROTGRUPO")+"'");
        
        consultas.add("UPDATE `rotacion_lotesxestado` \n" +
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
                    "DELETE FROM `rotacion_lotesxgrupo` WHERE `id` = "+datos.get("IDROTGRUPO")+";"
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
    
}
