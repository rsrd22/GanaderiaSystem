/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloMuertesVentasHistoricos;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlMuertesVentasHistoricos {
    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlMuertesVentasHistoricos() {
        mySQL = new gestorMySQL();
    }

    public int GuardarAnular(ModeloMuertesVentasHistoricos modelo) {
        ArrayList<String> consultas = new ArrayList<>();
        //<editor-fold defaultstate="collapsed" desc="Editar Tabla Animales">
            String tipo = "`muerte` = '0', \n"
                        + "fecha_muerte = '1900-01-01',\n"
                        + "descripcion_muerte = ''\n";   
            
            if(modelo.getTipo().equals("venta")){
                tipo = "`venta` = '0', "
                        + "peso = (SELECT peso FROM `pesaje` WHERE id_animal = '"+modelo.getIdAnimal()+"' ORDER BY fecha_pesado DESC, id DESC  LIMIT 1;),\n"
                        + "precio_venta = NULL,\n"
                        + "peso_canal = NULL,\n"
                        + "fecha_venta = '1900-01-01',\n"
                        + "tipo_venta = NULL \n";
            }
            consultas.add("UPDATE `ranimales`\n" +
                            "SET "+tipo+"\n" +
                            "WHERE `id` = '"+modelo.getIdAnimal()+"';");
        
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR tbl de anuacion">
        consultas.add(
                "INSERT INTO `anularventasymuertes`\n" +
                    "(`tipo`, `id_animal`, `estado`, `fecha`, `id_usuario`)\n" +
                    "VALUES \n" +
                    "('"+modelo.getTipo()+"', '"+modelo.getIdAnimal()+"', '"+modelo.getEstado()+"', NOW(), '"+modelo.getIdUsuario()+"');"
        );
//</editor-fold>


        try {
            if (mySQL.EnviarConsultas(consultas)) {
                return Retorno.EXITO;
            } else {
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
    
    public int ActualizarAnulacion(ModeloMuertesVentasHistoricos modelo){
        ArrayList<String> consultas = new ArrayList<>();
        
        if(mySQL.ExistenDatos("select * from anularventasymuertes where tipo = '"+modelo.getTipo()+"' and id_animal = '"+modelo.getIdAnimal()+"' and estado = 'Activo'")){
            consultas.add("UPDATE `anularventasymuertes`\n" +
                            "SET `estado` = 'Inactivo'\n" +
                            "WHERE `tipo` = '"+modelo.getTipo()+"' AND `id_animal` = '"+modelo.getIdAnimal()+"' AND estado = 'Activo'");
        }else{
            return Retorno.DEFECTO;
        }
        
        try {
            if (mySQL.EnviarConsultas(consultas)) {
                return Retorno.EXITO;
            } else {
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
