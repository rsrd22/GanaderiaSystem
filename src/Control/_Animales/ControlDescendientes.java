/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control._Animales;

import BaseDeDatos.gestorMySQL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlDescendientes {
    private gestorMySQL mySQL;

    public ControlDescendientes() {
        mySQL = new gestorMySQL();
        
    }
    
    public Map<String, String> getDatosHijo(String idAnimal){
        try {
            Map<String, String> retorno = new HashMap<String, String>();
            
            String consulta = "SELECT animal.id AS ID_ANIMAL, animal.numero AS NUMERO_ANIMAL,\n" +
                                "DATE_FORMAT(animal.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO, \n" +
                                "grup.descripcion AS GRUPO, animal.`genero` AS GENERO, IFNULL(desce.`nro_descendiente`, '') AS NUM_DESC,\n" +
                                "IFNULL(desce.`nro_parto`, '') AS NUM_PARTO\n" +
                                "FROM `_animales` animal\n" +
                                "LEFT JOIN `_animales_descendientes` desce ON desce.`id_animal` = animal.`id` \n" +
                                "INNER JOIN propietarioxhierro hierro ON hierro.id = animal.hierro \n" +
                                "INNER JOIN tipo_animales tpoani ON tpoani.id = animal.id_tipo_animal \n" +
                                "LEFT JOIN traslado_animalxgrupo traslado ON traslado.id_animal = animal.id\n" +
                                "LEFT JOIN grupos grup ON grup.id = traslado.id_grupo\n" +
                                "WHERE animal.id = '"+idAnimal+"'";
            
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);
            if(traslados.size()>0){
                retorno = traslados.get(0);
            }
            
            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }
    
}
