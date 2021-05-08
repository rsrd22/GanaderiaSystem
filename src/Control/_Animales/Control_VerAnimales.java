/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control._Animales;

import BaseDeDatos.gestorMySQL;
import Control.ControlTraslado;
import Modelo._Animales.Modelo_AnimalesSalida;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class Control_VerAnimales {
    private gestorMySQL mySQL;
    private ControlTraslado control;
    private final ArrayList<Modelo_AnimalesSalida> LISTA_VACIA = new ArrayList<Modelo_AnimalesSalida>();

    public Control_VerAnimales() {
        mySQL = new gestorMySQL();
        control = new ControlTraslado();
    }
    
    public Object ObtenerDatosAnimales(String IDFINCA, String IDTIPOFINCA, String Orden) {
        try {
            if(Orden.isEmpty()){
                Orden = "CONVERT(animal.numero,INT) ASC";
            }
            String consulta = "SELECT traslado.estado AS ESTADO, traslado.fecha AS FECHA, IFNULL(DATE_FORMAT(traslado.fecha_traslado, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n"
                    + "traslado.id AS ID_TRASLADO, animal.id AS ID_ANIMAL, traslado.id_finca AS ID_FINCA, traslado.id_grupo AS ID_GRUPO,\n"
                    + "traslado.id_usuario AS ID_USUARIO, traslado.motivo AS MOTIVO, IF(animal.numero_mama_adoptiva IS NULL OR animal.numero_mama_adoptiva = '', (SELECT id_madre FROM _animales_descendientes WHERE id_animal=animal.id), animal.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "animal.numero AS NUMERO_ANIMAL, animal.peso AS PESO, DATE_FORMAT(animal.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.genero AS GENERO,\n"
                    + "grup.descripcion AS GRUPO, \n"
                    + "IFNULL(finc.id, '') AS IDFINCA, IFNULL(finc.descripcion, '') AS FINCA, \n"
                    + "IFNULL(blo.id, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, \n"
                    + "IFNULL(lot.id, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE\n"
                    + ", animal.id_tipo_animal AS IDTIPO_ANIMAL, tpoani.descripcion AS TIPO_ANIMAL, \n"
                    + "IFNULL(animal.capado, 'No') AS CAPADO,  IF(animal.muerte = '0', 'No', 'Si') AS MUERTE,\n"
                    + "IF(animal.venta = '0', 'No', 'Si') AS VENTA,  animal.hierro AS IDHIERRO, hierro.descripcion AS DESC_HIERRO,\n"
                    + "IF(animal.destete = '0', 'No', 'Si') AS DESTETE\n"
                    + "FROM _animales animal\n"
                    + "INNER JOIN propietarioxhierro hierro ON hierro.id = animal.hierro \n"
                    + "INNER JOIN tipo_animales tpoani ON tpoani.id = animal.id_tipo_animal \n"
                    + "LEFT JOIN traslado_animalxgrupo traslado ON traslado.id_animal = animal.id\n"
                    + "LEFT JOIN grupos grup ON grup.id = traslado.id_grupo\n"
                    + "LEFT JOIN rotacion_lote rot ON rot.id_grupo = traslado.id_grupo AND rot.estado = 'Activo'\n"
                    + "LEFT JOIN lotes lot ON lot.id = rot.id_lote\n"
                    + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                    + "LEFT JOIN fincas finc ON finc.id = traslado.id_finca\n"
                    + "WHERE traslado.id_finca = '" + IDFINCA + "' AND tpoani.id = '" + IDTIPOFINCA + "' AND traslado.estado = 'Activo' AND muerte = '0' and venta = '0'\n"
                    + "ORDER BY "+Orden;
            System.out.println("consulta:\n"+consulta);
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
