/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.RAnimales;

import BaseDeDatos.gestorMySQL;
import Control.ControlTraslado;
import Control.IControl;
import Control.Retorno;
import Modelo.ModeloAnimales;
import Modelo.RAnimales.ModeloRAnimales;
import Modelo.RAnimales.ModeloRAnimalesSalida;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlRAnimales implements IControl {

    private gestorMySQL mySQL;
    private ControlTraslado control;
    private final ArrayList<ModeloRAnimales> LISTA_VACIA = new ArrayList<ModeloRAnimales>();

    public ControlRAnimales() {
        mySQL = new gestorMySQL();
        control = new ControlTraslado();
    }

    @Override
    public Object ObtenerDatos() {
        //<editor-fold defaultstate="collapsed" desc="selectDatosFromQuery">
        String consulta = "SELECT \n"
                + "a.*,\n"
                + "b.descripcion descTipoAnimal, \n"
                + "c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,\n"
                + "b.id_finca idFinca,\n"
                + "e.descripcion descFinca,\n"
                + "d.id_propietario idPropietario,\n"
                + "CONCAT(\n"
                + "f.identificacion,\n"
                + "   ' - ',\n"
                + "   CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)),\n"
                + "   ' ',\n"
                + "   TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))\n"
                + ") descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,\n"
                + "IFNULL(a.peso_destete,0) pesodestete\n"
                + "FROM ranimales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "ORDER BY a.numero ASC";
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<ModeloRAnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new ModeloRAnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("cantidad_parto"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
                        animal.get("estado_descendiente"),
                        animal.get("es_madre"),
                        animal.get("fecha"),
                        animal.get("fecha_destete"),
                        animal.get("fecha_muerte"),
                        animal.get("fecha_nacimiento"),
                        animal.get("fecha_novilla"),
                        animal.get("fecha_venta"),
                        animal.get("genero"),
                        animal.get("grupo"),
                        animal.get("hierro"),
                        animal.get("hierro_fisico"),
                        animal.get("id"),
                        animal.get("id_tipo_animal"),
                        animal.get("id_usuario"),
                        animal.get("implante"),
                        animal.get("muerte"),
                        animal.get("notas"),
                        animal.get("numero"),
                        animal.get("numero_descendiente"),
                        animal.get("numero_mama"),
                        animal.get("numero_mama_adoptiva"),
                        animal.get("numero_parto"),
                        animal.get("peso"),
                        animal.get("peso_canal"),
                        animal.get("peso_destete"),
                        animal.get("precio_venta"),
                        animal.get("tipo_venta"),
                        animal.get("venta"),
                        animal.get("descTipoAnimal"),
                        animal.get("descGrupo"),
                        animal.get("descHierro"),
                        animal.get("idFinca"),
                        animal.get("descFinca"),
                        animal.get("idPropietario"),
                        animal.get("descPropietario"),
                        animal.get("pesocanal"),
                        animal.get("pesodestete")
                ));
                //</editor-fold>
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        //<editor-fold defaultstate="collapsed" desc="selectDatosFromQuery">
        String consulta = "SELECT \n"
                + "a.*,\n"
                + "b.descripcion descTipoAnimal, \n"
                + "c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,\n"
                + "b.id_finca idFinca,\n"
                + "e.descripcion descFinca,\n"
                + "d.id_propietario idPropietario,\n"
                + "CONCAT(\n"
                + "f.identificacion,\n"
                + "   ' - ',\n"
                + "   CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)),\n"
                + "   ' ',\n"
                + "   TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))\n"
                + ") descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,\n"
                + "IFNULL(a.peso_destete,0) pesodestete\n"
                + "FROM ranimales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE a.id=" + ID;
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<ModeloRAnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new ModeloRAnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("cantidad_parto"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
                        animal.get("estado_descendiente"),
                        animal.get("es_madre"),
                        animal.get("fecha"),
                        animal.get("fecha_destete"),
                        animal.get("fecha_muerte"),
                        animal.get("fecha_nacimiento"),
                        animal.get("fecha_novilla"),
                        animal.get("fecha_venta"),
                        animal.get("genero"),
                        animal.get("grupo"),
                        animal.get("hierro"),
                        animal.get("hierro_fisico"),
                        animal.get("id"),
                        animal.get("id_tipo_animal"),
                        animal.get("id_usuario"),
                        animal.get("implante"),
                        animal.get("muerte"),
                        animal.get("notas"),
                        animal.get("numero"),
                        animal.get("numero_descendiente"),
                        animal.get("numero_mama"),
                        animal.get("numero_mama_adoptiva"),
                        animal.get("numero_parto"),
                        animal.get("peso"),
                        animal.get("peso_canal"),
                        animal.get("peso_destete"),
                        animal.get("precio_venta"),
                        animal.get("tipo_venta"),
                        animal.get("venta"),
                        animal.get("descTipoAnimal"),
                        animal.get("descGrupo"),
                        animal.get("descHierro"),
                        animal.get("idFinca"),
                        animal.get("descFinca"),
                        animal.get("idPropietario"),
                        animal.get("descPropietario"),
                        animal.get("pesocanal"),
                        animal.get("pesodestete")
                ));
                //</editor-fold>
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    
    
    public Map<String, String> GetDatosVenta(String id_Animal) {
        try {
            String consulta = "SELECT numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL OR animal.numero_mama_adoptiva = '',numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "DATE_FORMAT(fecha_venta, '%d/%m/%Y') AS FECHA_VENTA, tipo_venta AS TIPO_VENTA, \n"
                    + "MascaraMonedaDecimal(REPLACE(precio_venta, '.', ',')) AS PRECIO_VENTA, peso AS PESO, peso_canal AS PESO_CANAL, \n"
                    + "MascaraMonedaDecimal(REPLACE((PRECIO_VENTA * IF(peso_canal IS NULL, peso, peso_canal)), '.', ',')) PRECIO_TOTAL, \n"
                    + "ROUND((peso_canal/peso * 100), 0) PORCENTAJE_CANAL \n"
                    + "FROM ranimales \n"
                    + "WHERE id = '" + id_Animal + "'";

            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }
    
    public int ActualizarVenta(Object _animal) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloAnimales animal = (ModeloAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE ranimales SET\n"
                + "peso = " + animal.getPeso() + ",\n"
                + "precio_venta = " + animal.getPrecioVenta() + ",\n"
                + "peso_canal = " + animal.getPesoCanal() + ",\n"
                + "fecha_venta = '" + animal.getFechaVenta() + "',\n"
                + "tipo_venta = " + animal.getTipoVenta() + "\n"
                + "WHERE id = " + animal.getId()
        //</editor-fold>
        );

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

    public int ActualizarMuerte(Object _animal) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloAnimales animal = (ModeloAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE ranimales SET\n"
                + "fecha_muerte = '" + animal.getFechaMuerte() + "',\n"
                + "descripcion_muerte = '" + animal.getDescripcionMuerte() + "'\n"
                + "WHERE id = " + animal.getId()
        //</editor-fold>
        );

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

    public Map<String, String> GetDatosMuerte(String id_Animal) {
        try {
            String consulta = "SELECT numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL OR numero_mama_adoptiva = '',numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "DATE_FORMAT(fecha_muerte, '%d/%m/%Y') AS FECHA_MUERTE, descripcion_muerte AS MOTIVO \n"
                    + "FROM ranimales \n"
                    + "WHERE id = '" + id_Animal + "'";

            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String, String>();
        }
    }

    public List<Map<String, String>> GetDatosTraslado(String id_Animal) {
        try {
            String consulta = "SELECT anim.numero AS NUMERO_ANIMAL, grup.descripcion AS GRUPO,\n"
                    + "DATE_FORMAT(traslado.fecha_traslado, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "traslado.motivo AS MOTIVO, traslado.estado AS ESTADO, traslado.id as IDTRASLADO\n"
                    + "FROM traslado_animalxgrupo traslado\n"
                    + "INNER JOIN ranimales anim ON anim.id = traslado.id_animal\n"
                    + "INNER JOIN  grupos grup ON grup.id = traslado.id_grupo\n"
                    + "WHERE traslado.id_animal = '" + id_Animal + "'\n"
                    + "ORDER BY traslado.id DESC";

            System.out.println("GetDatosTraslado...>" + consulta);

            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Map<String, String>> GetDatosrotaciones(String id_Animal) {
        try {
            String consulta = "SELECT anim.numero AS NUMERO_ANIMAL,\n"
                    + "IFNULL(blo.id, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, \n"
                    + "IFNULL(lot.id, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE,\n"
                    + "traslado.id, anim.numero AS NUMERO_ANIMAL,\n"
                    + "grup.id AS IDGRUPO, grup.descripcion AS GRUPO,\n"
                    + "DATE_FORMAT(traslado.fecha_traslado, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "DATE_FORMAT(tbl.FECHA_ENTRADA, '%d/%m/%Y') AS FECHA_ENTRADA,\n"
                    + "IFNULL(DATE_FORMAT(tbl.FECHA_SALIDA, '%d/%m/%Y'), '') AS FECHA_SALIDA,\n"
                    + "IF(traslado.estado = 'Activo' AND tbl.ESTADO_GRUPO = 'Activo' AND tbl.ESTADO_LOTE = 'Activo', 'Activo', 'Inactivo') ESTADO\n"
                    + "FROM traslado_animalxgrupo traslado\n"
                    + "INNER JOIN ranimales anim ON anim.id = traslado.id_animal\n"
                    + "INNER JOIN  grupos grup ON grup.id = traslado.id_grupo\n"
                    + "LEFT JOIN (\n"
                    + "SELECT rot.id AS ID_ROTACION, rotgrup.id AS ID_ROT_GRUPO, rot.id_lote AS ID_LOTE, rotgrup.id_grupo AS ID_GRUPO,\n"
                    + "rot.fecha_entrada AS FECHA_ENTRADA, rot.fecha_registro AS FECHA_REGISTRO,\n"
                    + "rot.fecha_salida AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.estado AS ESTADO_GRUPO\n"
                    + "FROM rotacion_lotesxestado rot\n"
                    + "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.id_rotacion_lotesxestado = rot.id\n"
                    + "INNER JOIN traslado_animalxgrupo tras ON tras.id_grupo = rotgrup.id_grupo\n"
                    + " AND ((rot.fecha_salida IS NULL AND rot.fecha_entrada <= tras.fecha_traslado)  OR (rot.fecha_salida IS NOT NULL AND tras.fecha_traslado BETWEEN rot.fecha_entrada AND rot.fecha_salida))\n"
                    + "WHERE tras.id_animal = '" + id_Animal + "'\n"
                    + ") AS tbl ON tbl.ID_GRUPO = traslado.id_grupo\n"
                    + "LEFT JOIN lotes lot ON lot.id = tbl.ID_LOTE \n"
                    + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                    + "WHERE traslado.id_animal = '" + id_Animal + "'\n"
                    + "ORDER BY traslado.id DESC, tbl.ID_ROTACION DESC;";

            consulta = "SELECT anim.numero AS NUMERO_ANIMAL,\n"
                    + "IFNULL(blo.id, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, \n"
                    + "IFNULL(lot.id, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE,\n"
                    + "tras.id, anim.numero AS NUMERO_ANIMAL,\n"
                    + "grup.id AS IDGRUPO, grup.descripcion AS GRUPO,\n"
                    + "DATE_FORMAT(tras.fecha_traslado, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "DATE_FORMAT(rot.fecha_entrada, '%d/%m/%Y') AS FECHA_ENTRADA,\n"
                    + "IFNULL(DATE_FORMAT(rot.fecha_salida, '%d/%m/%Y'), '') AS FECHA_SALIDA, \n"
                    + "tras.motivo AS MOTIVO,\n"
                    + "lot.id, rot.id AS IDROTACION, tras.id AS IDTRASLADO,\n"
                    + "IF(tras.estado = 'Activo' AND rot.estado = 'Activo', 'Activo', 'Inactivo') ESTADO,\n"
                    + "tras.estado AS EST_TRASLADO, rot.estado AS EST_ROTACION\n"
                    + "FROM rotacion_lote rot\n"
                    + "INNER JOIN traslado_animalxgrupo tras ON tras.id_grupo = rot.id_grupo\n"
                    + "INNER JOIN ranimales anim ON anim.id = tras.id_animal\n"
                    + "INNER JOIN  grupos grup ON grup.id = tras.id_grupo\n"
                    + "LEFT JOIN lotes lot ON lot.id = rot.id_lote\n"
                    + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                    + "WHERE tras.id_animal = '" + id_Animal + "' \n"
                    + "ORDER BY tras.id DESC, rot.id DESC;	";
            System.out.println("GetDatosrotaciones...>" + consulta);

            List<Map<String, String>> rotaciones = new ArrayList<Map<String, String>>();

            rotaciones = mySQL.ListSQL(consulta);

            return rotaciones;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Object ObtenerDatosAnimalesPesables(String IDFINCA, String IDTIPOANIMAL, String FECHA, String Orden) {
        try {
            if(Orden.isEmpty()){
                Orden = "CONVERT(a.numero,DOUBLE) ASC";
            }
            String consulta = "SELECT\n"
                    + "a.id AS ID_ANIMAL,\n"
                    + "a.numero AS NUMERO_ANIMAL,\n"
                    + "IF(a.numero_mama_adoptiva IS NULL OR a.numero_mama_adoptiva = '', a.numero_mama, a.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "a.peso AS PESO,\n"
                    + "DATE_FORMAT(a.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO,\n"
                    + "a.genero AS GENERO,\n"
                    + "c.descripcion AS GRUPO,\n"
                    + "d.id AS IDFINCA,\n"
                    + "IFNULL(d.descripcion, '') AS FINCA,\n"
                    + "b.id AS IDTIPO_ANIMAL,\n"
                    + "b.descripcion AS TIPO_ANIMAL,\n"
                    + "IFNULL(a.capado, 'No') AS CAPADO,\n"
                    + "IF(a.muerte = '0', 'No', 'Si') AS MUERTE,\n"
                    + "IF(a.venta = '0', 'No', 'Si') AS VENTA,\n"
                    + "IF(a.destete = '0', 'No', 'Si') AS DESTETE,\n"
                    + "DATE_FORMAT(a.fecha_destete, '%d/%m/%Y') AS FECHA_DESTETE,\n"
                    + "IFNULL(a.peso_destete,0) AS PESO_DESTETE,\n"
                    + "a.implante AS IMPLANTE,\n"
                    + "a.descornado AS DESCORNADO,\n"
                    + "a.hierro_fisico AS HIERRO_FISICO,\n"
                    + "a.hierro AS IDHIERRO,\n"
                    + "e.descripcion AS DESC_HIERRO,\n"
                    + "IFNULL((SELECT '*' FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha_pesado,'%Y-%m-%d')='" + FECHA + "' ORDER BY m.id DESC LIMIT 1),'') AS EST,\n"
                    + "IFNULL((SELECT m.id FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha_pesado,'%Y-%m-%d')='" + FECHA + "' ORDER BY m.id DESC LIMIT 1),'') AS ID_PESAJE,\n"
                    + "IFNULL((SELECT m.notas FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha_pesado,'%Y-%m-%d')='" + FECHA + "' ORDER BY m.id DESC LIMIT 1),'') AS NOTAS,\n"
                    + "IFNULL((SELECT m.estado FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha_pesado,'%Y-%m-%d')='" + FECHA + "' ORDER BY m.id DESC LIMIT 1),'') AS ESTADO,\n"
                    + "IFNULL((SELECT DATE_FORMAT(m.fecha_pesado,'%d/%m/%Y') FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha_pesado,'%Y-%m-%d')='" + FECHA + "' ORDER BY m.id DESC LIMIT 1),'') AS FECHAPESADO,\n"
                    + "(SELECT peso_anterior FROM pesaje m WHERE m.id_animal=a.id ORDER BY m.fecha_pesado DESC LIMIT 1) AS PESO_ANTERIOR\n"
                    + "FROM\n"
                    + "ranimales a\n"
                    + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                    + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                    + "LEFT JOIN fincas d ON d.id=b.id_finca\n"
                    + "LEFT JOIN propietarioxhierro e ON a.hierro=e.id\n"
                    + "WHERE\n"
                    + "c.pesable='1' and a.muerte='0' AND venta='0' and d.id=" + IDFINCA + " and b.id=" + IDTIPOANIMAL + "\n"
                    + "ORDER BY "+Orden;
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Object ObtenerDatosAnimalesPalpacion(String IDFINCA, String IDTIPOFINCA, String FECHA) {
        try {

            String consulta = "SELECT anim.id AS IDANIMAL, anim.numero AS NUMERO_ANIMAL, anim.numero_mama AS NUMERO_MAMA,\n"
                    + " IFNULL(DATE_FORMAT(anim.fecha_novilla, '%d/%m/%Y'), '') FECHA_NOVILLA, anim.peso as PESO\n"
                    + ", NumeroHijos(anim.numero, 0) NUMERO_HIJOS, IFNULL(NumeroPartos(anim.numero), '0') NUMERO_PARTOS,\n"
                    + "IFNULL(CONCAT(UPPER(SUBSTRING(tbl.DIAG, 1, 1)), SUBSTRING(tbl.DIAG, 2)), '') ESTADO, IFNULL(tbl.FPALP, '') FECHA_PALP, IFNULL(tbl.IDPALP, '') IDPALPACION,\n"
                    + "IFNULL(DATE_FORMAT(NumeroHijos(anim.numero, 1), '%d/%m/%Y'), '') FECHA_ULT_PARTO,\n"
                    + "IFNULL(tbl.NMESES, '') AS NUMERO_MESES, tbl.NOTAS, tbl.ESTPALP,\n"
                    + "finc.id as IDFINCA, tpo.id as IDTIPOA, \n"
                    + "IF(DATEDIFF('" + FECHA + "',tbl.FPALP)<30, '*', '') AS EST\n"
                    + "FROM ranimales anim\n"
                    + "LEFT JOIN tipo_animales tpo ON anim.id_tipo_animal=tpo.id\n"
                    + "LEFT JOIN grupos grup ON anim.grupo=grup.id\n"
                    + "LEFT JOIN fincas finc ON tpo.id_finca=finc.id\n"
                    + "LEFT JOIN propietarioxhierro propxhi ON anim.hierro=propxhi.id \n"
                    + "LEFT JOIN (\n"
                    + "	SELECT MAX(palp.id) AS IDPALP, fecha_palpacion AS FPALP, diagnostico AS DIAG,\n"
                    + "	num_meses AS NMESES, fecha_ultimo_parto AS FULTPARTO, anim.id AS IDANIMAL, IFNULL(palp.notas, '') as NOTAS, palp.estado as ESTPALP\n"
                    + "	FROM palpacion palp\n"
                    + "	INNER JOIN animales anim ON anim.id = palp.id_animal\n"
                    + "	INNER JOIN tipo_animales tpo ON tpo.id = anim.id_tipo_animal\n"
                    + "	WHERE tpo.id_finca = '" + IDFINCA + "' AND tpo.id = '" + IDTIPOFINCA + "' \n"
                    + "       AND fecha_palpacion BETWEEN DATE_SUB('" + FECHA + "',  INTERVAL 15 DAY) AND DATE_ADD('" + FECHA + "',  INTERVAL 15 DAY)\n"
                    + "	GROUP BY palp.id_animal \n"
                    + ") tbl ON tbl.IDANIMAL = anim.id \n"
                    + "WHERE grup.palpable = '1' AND anim.muerte='0' AND anim.venta='0' AND finc.id = '" + IDFINCA + "' AND tpo.id = '" + IDTIPOFINCA + "'\n"
                    + "ORDER BY CONVERT(anim.numero,DOUBLE) ASC";

            List<Map<String, String>> palpacion = new ArrayList<Map<String, String>>();

            palpacion = mySQL.ListSQL(consulta);

            return palpacion;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Object buscarAnimalPorTipoyNumero(String tipoAnimal, String numeroAnimal) {
        String consulta = "SELECT * FROM ranimales\n"
                + "WHERE\n"
                + "numero='" + numeroAnimal + "'\n"
                + "AND id_tipo_animal='" + tipoAnimal + "'";
        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        animal = mySQL.ListSQL(consulta);

        if (animal.size() > 0) {

            for (Map<String, String> grupo : animal) {
                lista.add(new ModeloAnimales(
                        grupo.get("calificacion"),
                        grupo.get("capado"),
                        grupo.get("fecha"),
                        grupo.get("fecha_destete"),
                        grupo.get("fecha_muerte"),
                        grupo.get("fecha_nacimiento"),
                        grupo.get("fecha_venta"),
                        grupo.get("genero"),
                        grupo.get("id"),
                        grupo.get("id_tipo_animal"),
                        grupo.get("id_usuario"),
                        grupo.get("notas"),
                        grupo.get("numero"),
                        grupo.get("numero_mama"),
                        "",
                        grupo.get("peso"),
                        grupo.get("numero_mama_adoptiva"),
                        grupo.get("grupo"),
                        "",
                        grupo.get("hierro"),
                        "",
                        grupo.get("idFinca"),
                        "",
                        grupo.get("numero_descendiente"),
                        grupo.get("estado_descendiente"),
                        null,
                        grupo.get("idPropietario"),
                        "",
                        grupo.get("muerte"),
                        grupo.get("venta"),
                        grupo.get("precio_venta"),
                        grupo.get("tipo_venta"),
                        grupo.get("peso_canal"),
                        grupo.get("descripcion_muerte"),
                        grupo.get("fecha_novilla"),
                        grupo.get("peso_destete"),
                        grupo.get("hierro_fisico"),
                        grupo.get("implante"),
                        grupo.get("descornado"),
                        grupo.get("destete"),
                        grupo.get("numero_partos")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }
}
