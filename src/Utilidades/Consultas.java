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

        consultas.put("BQD_USER", "SELECT usr.id AS IDUSUARIO, usr.usuario AS USUARIO, \n"
                + "perf.descripcion AS PERFIL, usr.estado AS ESTADO\n"
                + "FROM usuarios usr \n"
                + "INNER JOIN perfiles perf ON perf.id = usr.id_perfil\n"
                + "ORDER BY usr.id ASC ");

        consultas.put("BQD_FNT_HDRC", "SELECT id AS ID__10__C, descripcion AS DESCRIPCION__70__L, estado  AS ESTADO__20__L\n"
                + "FROM fuentes_hidricas\n"
                + "ORDER BY id ASC");

        consultas.put("BQD_PROP", "SELECT id as ID__20__R, "
                + "CONCAT_WS(' ', primer_apellido, IFNULL(segundo_apellido, ''), primer_nombre, IFNULL(segundo_nombre, '')) PROPIETARIO__80__L \n"
                + "FROM propietarios\n "
                + " ORDER BY primer_apellido ASC, primer_nombre ASC");

        consultas.put("BQD_FNCS", "SELECT fnc.id AS ID, fnc.descripcion AS DESCRIPCION, \n"
                + "MascaraMonedaDecimal(REPLACE(fnc.area, '.', ',')) AS AREAT, fnc.direccion AS DIRECCION, \n"
                + "CONCAT_WS(' ',\n"
                + " prop.primer_nombre, \n"
                + " IFNULL(prop.segundo_nombre, ''),\n"
                + "  prop.primer_apellido, \n"
                + "  IFNULL(prop.segundo_apellido, '')\n"
                + "  ) AS PROPIETARIO,\n"
                + "prop.id AS IDPROP\n"
                + "FROM fincas fnc\n"
                + "INNER JOIN propietarios prop ON prop.id = fnc.id_propietario");

        consultas.put("BQD_PRMS", "SELECT perm.id AS ID__10__C, perm.`tipo` AS TIPO__80__L, perm.`valor_tipo` AS IDTIPO__10__C, \n"
                + "IF(perm.`tipo` = 'perfil', perf.`descripcion`, users.`usuario`) VALOR_TIPO__100__L\n"
                + "FROM permisos perm\n"
                + "LEFT JOIN perfiles perf ON perf.`id` = perm.`valor_tipo` AND perm.`tipo` = 'perfil'\n"
                + "LEFT JOIN usuarios users ON users.`id` = perm.`valor_tipo` AND perm.`tipo` = 'usuario'\n"
                + "ORDER BY perm.id ASC");

        consultas.put("BQD_USERS", "SELECT users.`id` AS ID__10__C, users.`usuario` AS USUARIO__200__L, users.`id_perfil` AS IDPERFIL__10__C, perf.`descripcion` AS PERFIL__100__L\n"
                + "FROM `usuarios` users \n"
                + "INNER JOIN perfiles perf ON perf.`id` = users.`id_perfil`\n"
                + "WHERE users.`estado` = 'Activo'\n"
                + "ORDER BY users.`id` ASC");

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

        consultas.put("BUSQUEDA_MOD_USUARIOS", "SELECT \n"
                + "a.id ID__10__L,\n"
                + "a.usuario NOMBRE_USUARIO__80__L,\n"
                + "CASE WHEN a.clave_dinamica=0 THEN 'No' ELSE 'Si' END CLAVE_DINAM__30__L,\n"
                + "a.estado ESTADO__50__L,\n"
                + "b.descripcion PERFIL__50__L\n"
                + "FROM \n"
                + "usuarios a\n"
                + "LEFT JOIN perfiles b ON b.id=a.id_perfil");

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
                + "LEFT JOIN propietarioxhierro f ON a.hierro=f.id\n"
                + "LEFT JOIN propietarios d ON d.`id`=f.`id_propietario`\n"
                + "LEFT JOIN grupos e ON e.id=a.grupo\n"
                + "ORDER BY\n"
                + "a.numero ASC");

        consultas.put("RBUSQUEDA_ANIMALES", "SELECT\n"
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
                + "FROM \n"
                + "ranimales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN fincas c ON b.id_finca=c.id\n"
                + "LEFT JOIN propietarioxhierro f ON a.hierro=f.id\n"
                + "LEFT JOIN propietarios d ON d.`id`=f.`id_propietario`\n"
                + "LEFT JOIN grupos e ON e.id=a.grupo\n"
                + "ORDER BY\n"
                + "a.numero ASC");

        consultas.put("BUSQUEDA_PERFILES", "SELECT\n"
                + "a.id ID__10__L,\n"
                + "a.descripcion FINCA__200__L\n"
                + "FROM \n"
                + "perfiles a\n"
                + "ORDER BY\n"
                + "a.descripcion ASC");

        consultas.put("CARGAR_COMBO_GRUPOS", "SELECT \n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion,\n"
                + "NULL idTipoAnimal,\n"
                + "NULL descTipoAnimal,\n"
                + "NULL idFinca,\n"
                + "NULL descFinca,\n"
                + "NULL tipo_grupo\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion,\n"
                + "a.id_tipo_animal idTipoAnimal,\n"
                + "b.descripcion descTipoAnimal,\n"
                + "b.id_finca idFinca,\n"
                + "c.descripcion descFinca,\n"
                + "a.tipo_grupo\n"
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

        consultas.put("CARGAR_COMBO_PERFILES", "SELECT \n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion\n"
                + "FROM\n"
                + "perfiles a");

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

        consultas.put("CARGAR_COMBO_HIERROS_TOTAL", "SELECT\n"
                + "-1 id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT \n"
                + "a.id id,\n"
                + "a.descripcion descripcion\n"
                + "FROM\n"
                + "propietarioxhierro a\n"
                + "WHERE\n"
                + "a.estado='Activo'");

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
                + "SELECT prop.id id, \n"
                + "CONCAT(\n"
                + "prop.identificacion,' - ',\n"
                + "TRIM(\n"
                + "CONCAT(\n"
                + "TRIM(CONCAT_WS(' ', prop.primer_nombre, IFNULL(prop.segundo_nombre, ''))),' ',\n"
                + "TRIM(CONCAT_WS(' ',prop.primer_apellido, IFNULL(prop.segundo_apellido, '')))\n"
                + "))\n"
                + ") descripcion\n"
                + "FROM `propietarios` prop\n"
                + "INNER JOIN `propietarioxhierro` proph ON proph.`id_propietario` = prop.`id`");

        consultas.put("OBTENER_ULTIMO_DESCENDIENTE", "select \n"
                + "case when numero_descendiente is null then 1 else max(numero_descendiente)+1 end numeroDescendiente \n"
                + "from \n"
                + "animales \n"
                + "where \n"
                + "id_tipo_animal='ID_TIPO_ANIMAL'\n"
                + "AND numero_mama='NUMERO_MAMA'");

        consultas.put("_OBTENER_ULTIMO_DESCENDIENTE", "SELECT \n"
                + "CASE WHEN nro_descendiente IS NULL THEN 1\n"
                + "ELSE MAX(nro_descendiente)+1 END numeroDescendiente\n"
                + "FROM _animales_descendientes WHERE id_madre=ID_MAMA");

        consultas.put("ROBTENER_ULTIMO_DESCENDIENTE", "SELECT \n"
                + "CASE WHEN numero_descendiente IS NULL THEN 1 ELSE MAX(numero_descendiente)+1 END numeroDescendiente \n"
                + "FROM ranimales WHERE numero_mama=(SELECT numero FROM ranimales WHERE id=ID_MAMA)");

        consultas.put("OBTENER_ID_MADRE", "SELECT IF(b.`id` IS NULL , a.`id`, b.`id_madre`) ID, IFNULL(a.es_madre,'FALSE') ES_MADRE FROM _animales a \n"
                + "LEFT JOIN _animales_descendientes b ON a.id=b.id_madre WHERE numero='NUMERO_MAMA' AND id_tipo_animal='ID_TIPO_ANIMAL'");

        consultas.put("ROBTENER_ID_MADRE", "SELECT a.`id` ID, IFNULL(a.es_madre,'FALSE') ES_MADRE FROM ranimales a \n"
                + "WHERE numero='NUMERO_MAMA' AND id_tipo_animal='ID_TIPO_ANIMAL'");

        consultas.put("OBTENER_NRO_PARTOS", "SELECT id,numero_partos \n"
                + "FROM animales \n"
                + "WHERE \n"
                + "numero='NUMERO_MAMA' AND \n"
                + "numero_mama<>'NUMERO_MAMA' AND \n"
                + "id_tipo_animal='ID_TIPO_ANIMAL'");

        consultas.put(
                "OBTENER_HISTORICO_VENTAS",
                "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario\n"
                + "IF(`peso_canal` = 0, '', ROUND((`peso_canal`/peso*100),1)) AS PORC,\n"
                + "IF(`peso_canal` = 0, (`precio_venta` * peso), ROUND((`peso_canal`*`precio_venta`))) AS VVENTA\n"
                + "FROM ranimales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE\n"
                + "a.venta='1' AND a.id_tipo_animal=PARAMETRO1\n"
                + "ORDER BY ORDEN");

        consultas.put(
                "OBTENER_HISTORICO_MUERTES",
                "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
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
                + "ORDER BY ORDEN"
        );

        consultas.put(
                "BUSQUEDA_MEDICAMENTOS",
                "SELECT "
                + "a.id ID, \n"
                + "a.descripcion DESCRIPCION, \n"
                + "a.unidad_medida UNIDAD_DE_MEDIDA,\n"
                + "a.estado ESTADO\n"
                + "FROM \n"
                + "medicamentos a ORDER BY a.descripcion ASC"
        );

        consultas.put(
                "CARGAR_COMBO_ACTIVIDADES",
                "SELECT\n"
                + "'-1' id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT\n"
                + "id id,\n"
                + "descripcion descripcion\n"
                + "FROM actividades WHERE estado='Activo'"
        );

        consultas.put(
                "CARGAR_COMBO_MEDICAMENTOS",
                "SELECT\n"
                + "'-1' id,\n"
                + "'Seleccionar' descripcion\n"
                + "UNION\n"
                + "SELECT\n"
                + "id id,\n"
                + "CONCAT(descripcion,' (',unidad_medida,')') descripcion\n"
                + "FROM medicamentos WHERE estado='Activo'"
        );

        consultas.put(
                "GET_MEDICAMENTOS_POR_PESAJE",
                "SELECT\n"
                + "a.id_medicamento AS ID,\n"
                + "b.descripcion AS DESCRIPCION,\n"
                + "a.dosis AS CANTIDAD,\n"
                + "b.unidad_medida AS UNIDAD_MEDIDA\n"
                + "FROM pesajexmedicamento a\n"
                + "LEFT JOIN medicamentos b ON a.id_medicamento=b.id\n"
                + "WHERE id_pesaje="
        );

        consultas.put(
                "GET_MEDICAMENTOS_POR_PALPACION",
                "SELECT a.id_medicamento AS ID, CONCAT(b.descripcion, ' (', b.unidad_medida,')') AS DESCRIPCION,a.dosis AS CANTIDAD, b.unidad_medida AS UNIDAD_MEDIDA, a.`id` AS IDPALPMEDICAMENTO, '0' AS 'UPDATE'\n"
                + "FROM `palpacionxtratamiento` a\n"
                + "LEFT JOIN medicamentos b ON a.id_medicamento=b.id\n"
                + "WHERE a.`id_palpacion`="
        );

        consultas.put(
                "GET_MAXIMO_ID_PESAJE_ANIMAL",
                "SELECT IFNULL(MAX(id)+1,0) AS IDPESAJE FROM animales"
        );

        consultas.put(
                "GET_MAXIMO_ID_PALPACION",
                "SELECT IFNULL(MAX(id)+1,0) AS IDPALPACION FROM palpacion"
        );

        consultas.put("BQD_ANML_DESC",
                "SELECT animal.id AS ID_ANIMAL, animal.numero AS NUMERO_ANIMAL,\n"
                + "DATE_FORMAT(animal.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO, \n"
                + "grup.descripcion AS GRUPO,  IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE \n"
                + "FROM `_animales` animal\n"
                + "INNER JOIN propietarioxhierro hierro ON hierro.id = animal.hierro \n"
                + "INNER JOIN tipo_animales tpoani ON tpoani.id = animal.id_tipo_animal \n"
                + "LEFT JOIN traslado_animalxgrupo traslado ON traslado.id_animal = animal.id\n"
                + "LEFT JOIN grupos grup ON grup.id = traslado.id_grupo\n"
                + "LEFT JOIN rotacion_lote rot ON rot.id_grupo = traslado.id_grupo AND rot.estado = 'Activo'\n"
                + "LEFT JOIN lotes lot ON lot.id = rot.id_lote\n"
                + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                + "LEFT JOIN fincas finc ON finc.id = traslado.id_finca\n"
                + "WHERE traslado.id_finca = 'IDFINCA' AND tpoani.id = 'IDTIPO' AND traslado.estado = 'Activo' AND muerte = '0' AND venta = '0' \n"
                + "/* ADD */\n"
                + "ORDER BY CONVERT(animal.numero,INT) ASC");
    }

}
