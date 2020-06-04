/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class Consultas {

    public static Map<String, String> consultas = new HashMap<String, String>();

    /**
     * Consultas<br>
     * AA__BB__CC<br>
     * AA= NOMBRE EN COLUMNA<br>
     * BB= TAMAÑO COLUMNA TABLA <br>
     * CC= ALINEACION CONTENIDO COLUMNA TABLA ("L", "C", "R")
     */
    public static void Agregar() {

        consultas.put("BQD_USER", "SELECT usr.`id` AS IDUSUARIO, usr.`usuario` AS USUARIO, \n"
                + "perf.`descripcion` AS PERFIL, usr.`estado` AS ESTADO\n"
                + "FROM usuarios usr \n"
                + "INNER JOIN perfiles perf ON perf.id = usr.`id_perfil`\n"
                + "ORDER BY usr.id ASC ");

        consultas.put("BQD_FNT_HDRC", "SELECT id AS ID__10__C, descripcion AS DESCRIPCION__70__L, estado  AS ESTADO__20__L\n"
                + "FROM `fuentes_hidricas`\n"
                + "ORDER BY id ASC");

        consultas.put("BQD_PROP", "SELECT id as ID__20__R, "
                + "CONCAT_WS(' ', primer_apellido, IFNULL(segundo_apellido, ''), primer_nombre, IFNULL(segundo_nombre, '')) PROPIETARIO__80__L \n"
                + "FROM propietarios\n "
                + " ORDER BY primer_apellido ASC, primer_nombre ASC");

        consultas.put("BQD_FNCS", "SELECT fnc.id AS ID, fnc.descripcion AS DESCRIPCION, \n"
                + "`MascaraMonedaDecimal`(REPLACE(fnc.area, '.', ',')) AS AREAT, fnc.`direccion` AS DIRECCION, \n"
                + "CONCAT_WS(' ',\n"
                + " prop.`primer_nombre`, \n"
                + " IFNULL(prop.`segundo_nombre`, ''),\n"
                + "  prop.`primer_apellido`, \n"
                + "  IFNULL(prop.`segundo_apellido`, '')\n"
                + "  ) AS PROPIETARIO,\n"
                + "prop.`id` AS IDPROP\n"
                + "FROM fincas fnc\n"
                + "INNER JOIN `propietarios` prop ON prop.`id` = fnc.`id_propietario`");

        consultas.put("BUSQUEDA_TIPOS_ANIMALES", "SELECT\n"
                + "a.id ID,\n"
                + "a.id_finca ID_FINCA,\n"
                + "b.descripcion DESFINCA,\n"
                + "a.descripcion DESCRIPCION,\n"
                + "a.estado ESTADO,\n"
                + "a.fecha FECHA,\n"
                + "a.id_usuario ID_USUARIO\n"
                + "FROM \n"
                + "tipo_animales a\n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id");

        consultas.put("BUSQUEDA_ESTADO_ACTIVIDADES", "SELECT\n"
                + "id ID__10__L,\n"
                + "descripcion DESCRIPCION__200__L,\n"
                + "estado ESTADO__50__L\n"
                + "FROM estado_actividad");

        consultas.put("BUSQUEDA_ESTADO_ACTIVIDADES_SIN_ESPECIFICACIONES", "SELECT\n"
                + "id id,\n"
                + "descripcion descripcion,\n"
                + "color color,\n"
                + "estado estado\n"
                + "FROM estado_actividad WHERE estado='Activo'");

        consultas.put("BUSQUEDA_ACTIVIDADES", "SELECT\n"
                + "id ID__10__L,\n"
                + "descripcion DESCRIPCION__200__L,\n"
                + "estado ESTADO__50__L\n"
                + "FROM actividades");

        consultas.put("BUSQUEDA_ACTIVIDADES_SIN_ESPECIFICACIONES", "SELECT\n"
                + "id id,\n"
                + "descripcion descripcion,\n"
                + "estado estado\n"
                + "FROM actividades WHERE estado='Activo'");

        consultas.put("BUSQUEDA_MACRO_GRUPOS", "SELECT\n"
                + "id ID,\n"
                + "descripcion DESCRIPCION,\n"
                + "estado ESTADO,\n"
                + "fecha FECHA_REGISTRO,\n"
                + "id_usuario USUARIO\n"
                + "FROM \n"
                + "macrogrupos\n"
                + "ORDER BY\n"
                + "descripcion ASC");

        consultas.put("BUSQUEDA_GRUPOS", "SELECT\n"
                + "a.id ID__10__L,\n"
                + "IFNULL(d.descripcion, '') DES_FINCA__200__L,\n"
                + "IFNULL(c.descripcion, '') TIPO_ANIMAL__150__L, \n"
                + "IFNULL(a.descripcion, '') GRUPO__100__L,\n"
                + "IFNULL(b.descripcion, '') MACRO_GRUPO__80__L\n"
                + "FROM grupos a\n"
                + "LEFT JOIN macrogrupos b ON a.id_macrogrupo=b.id\n"
                + "LEFT JOIN tipo_animales c ON a.id_tipo_animal=c.id\n"
                + "LEFT JOIN fincas d ON c.id_finca=d.id\n"
                + "ORDER BY\n"
                + "a.descripcion ASC");

        consultas.put("BUSQUEDA_ANIMALES", "SELECT\n"
                + "a.id ID__10__L,\n"
                + "c.descripcion FINCA__200__L,\n"
                + "b.descripcion TIPO_ANIMAL__150__L,\n"
                + "CONCAT(\n"
                + "TRIM(CONCAT(d.primer_nombre,' ',d.segundo_nombre)),' ',\n"
                + "TRIM(CONCAT(d.primer_apellido,' ',d.segundo_apellido))\n"
                + ") PROPIETARIO__200__L,\n"
                + "e.descripcion GRUPO__120__L,\n"
                + "UPPER(IFNULL(f.descripcion,'')) HIERRO__150__L,\n"
                + "a.numero_mama NRO_MAMA__100__L,\n"
                + "a.numero NUMERO__100__L,\n"
                + "UPPER(a.genero) GENERO__80__L\n"
                //                + "a.calificacion CALIFICACION,\n"
                //                + "a.notas NOTAS,\n"
                //                + "a.peso PESO\n"
                + "FROM \n"
                + "animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN fincas c ON b.id_finca=c.id\n"
                + "LEFT JOIN propietarios d ON c.id_propietario=d.id\n"
                + "LEFT JOIN grupos e ON e.id=a.`grupo`\n"
                + "LEFT JOIN propietarioxhierro f ON f.id_propietario=d.id AND a.`hierro`=f.`id`\n"
                + "ORDER BY\n"
                + "a.numero ASC");

        consultas.put("CARGAR_COMBO_GRUPOS", "SELECT \n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion,\n"
                + "NULL idTipoAnimal,\n"
                + "NULL descTipoAnimal,\n"
                + "NULL idFinca,\n"
                + "NULL descFinca\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion,\n"
                + "a.id_tipo_animal idTipoAnimal,\n"
                + "b.descripcion descTipoAnimal,\n"
                + "b.id_finca idFinca,\n"
                + "c.descripcion descFinca\n"
                + "FROM \n"
                + "grupos a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN fincas c ON b.id_finca=c.id\n"
                + "WHERE\n"
                + "a.estado='Activo'\n"
                + "AND c.id=PARAMETRO1 and b.id=PARAMETRO2");

        consultas.put("CARGAR_COMBO_TIPO_ANIMALES", "SELECT \n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion\n"
                + "FROM\n"
                + "tipo_animales a\n"
                + "WHERE\n"
                + "a.estado='Activo'\n"
                + "AND a.id_finca=");

        consultas.put("CARGAR_COMBO_HIERROS", "SELECT\n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion\n"
                + "FROM\n"
                + "propietarioxhierro a\n"
                + "LEFT JOIN fincas b ON a.id_propietario=b.id_propietario\n"
                + "WHERE\n"
                + "a.estado='Activo' AND a.id_propietario=");

        consultas.put("CARGAR_COMBO_FINCAS", "SELECT\n"
                + "'-1' id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT\n"
                + "id id,\n"
                + "descripcion descripcion\n"
                + "FROM fincas");

        consultas.put("CARGAR_COMBO_MACROGRUPOS", "SELECT \n"
                + "'-1' id, \n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT \n"
                + "id id, \n"
                + "descripcion descripcion\n"
                + "FROM macrogrupos\n"
                + "WHERE estado='Activo' and id_finca=");

        consultas.put("CARGAR_COMBO_PROPIETARIOS", "SELECT\n"
                + "'-1' id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT\n"
                + "a.id id,\n"
                + "CONCAT(\n"
                + "	a.identificacion,' - ',\n"
                + "	CONCAT(\n"
                + "		TRIM(\n"
                + "			CONCAT(a.primer_nombre,' ',a.segundo_nombre)\n"
                + "		),\n"
                + "		' ',\n"
                + "		TRIM(\n"
                + "			CONCAT(a.primer_apellido,' ',a.segundo_apellido)\n"
                + "		)\n"
                + "	)\n"
                + ") descripcion\n"
                + "FROM propietarios a\n"
                + "INNER JOIN fincas b ON a.id=b.id_propietario\n"
                + "WHERE b.id=PARAMETRO1");

        consultas.put("OBTENER_ULTIMO_DESCENDIENTE", "select \n"
                + "case when numero_descendiente is null then 1 else max(numero_descendiente)+1 end numeroDescendiente \n"
                + "from \n"
                + "animales \n"
                + "where \n"
                + "numero_mama=");

        consultas.put("OBTENER_HISTORICO_VENTAS", "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario\n"
                + "FROM animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE\n"
                + "a.venta='1' AND a.id_tipo_animal=PARAMETRO1\n"
                + "ORDER BY a.fecha_venta ASC");

        consultas.put("OBTENER_HISTORICO_MUERTES", "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario\n"
                + "FROM animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE\n"
                + "a.muerte='1' AND a.id_tipo_animal=PARAMETRO1\n"
                + "ORDER BY a.fecha_venta ASC");

    }

}
