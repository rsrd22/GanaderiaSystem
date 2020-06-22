/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloAnimales;
import Modelo.ModeloTraslado;
import static Utilidades.Consultas.consultas;
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
public class ControlAnimales implements IControl {

    private gestorMySQL mySQL;
    private ControlTraslado control;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlAnimales() {
        mySQL = new gestorMySQL();
        control = new ControlTraslado();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,IFNULL(a.peso_destete,0) pesodestete\n"
                + "FROM animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "ORDER BY a.numero ASC";
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
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
                        grupo.get("descTipoAnimal"),
                        grupo.get("peso"),
                        grupo.get("numero_mama_adoptiva"),
                        grupo.get("grupo"),
                        grupo.get("descGrupo"),
                        grupo.get("hierro"),
                        grupo.get("descHierro"),
                        grupo.get("idFinca"),
                        grupo.get("descFinca"),
                        grupo.get("numero_descendiente"),
                        grupo.get("estado_descendiente"),
                        null,
                        grupo.get("idPropietario"),
                        grupo.get("descPropietario"),
                        grupo.get("muerte"),
                        grupo.get("venta"),
                        grupo.get("precio_venta"),
                        grupo.get("tipo_venta"),
                        grupo.get("pesocanal"),
                        grupo.get("descripcion_muerte"),
                        grupo.get("fecha_novilla"),
                        grupo.get("pesodestete"),
                        grupo.get("hierro_fisico"),
                        grupo.get("implante"),
                        grupo.get("descornado")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,IFNULL(a.peso_destete,0) pesodestete,IFNULL(a.precio_venta,0) precioventa\n"
                + "FROM animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE a.id=" + id;
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
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
                        grupo.get("descTipoAnimal"),
                        grupo.get("peso"),
                        grupo.get("numero_mama_adoptiva"),
                        grupo.get("grupo"),
                        grupo.get("descGrupo"),
                        grupo.get("hierro"),
                        grupo.get("descHierro"),
                        grupo.get("idFinca"),
                        grupo.get("descFinca"),
                        grupo.get("numero_descendiente"),
                        grupo.get("estado_descendiente"),
                        null,
                        grupo.get("idPropietario"),
                        grupo.get("descPropietario"),
                        grupo.get("muerte"),
                        grupo.get("venta"),
                        grupo.get("precioventa"),
                        grupo.get("tipo_venta"),
                        grupo.get("pesocanal"),
                        grupo.get("descripcion_muerte"),
                        grupo.get("fecha_novilla"),
                        grupo.get("pesodestete"),
                        grupo.get("hierro_fisico"),
                        grupo.get("implante"),
                        grupo.get("descornado")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _animal) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloAnimales animal = (ModeloAnimales) _animal;

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL ANIMAL">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO animales (id,id_tipo_animal,numero,numero_mama,numero_mama_adoptiva,genero,"
                + "calificacion,notas,fecha_destete,capado,fecha_nacimiento,fecha_venta,"
                + "fecha_muerte,fecha,id_usuario,peso,grupo,hierro,numero_descendiente,estado_descendiente,"
                + "muerte,venta,precio_venta,tipo_venta,peso_canal,descripcion_muerte,"
                + "fecha_novilla,peso_destete,hierro_fisico,implante,descornado)\n"
                + "VALUES (\n"
                + "" + animal.getId()+ ",\n"
                + "" + animal.getIdTipoAnimal() + ",\n"
                + "'" + animal.getNumero() + "',\n"
                + "'" + animal.getNumeroMama() + "',\n"
                + "" + animal.getNumeroMamaAdoptiva() + ",\n"
                + "'" + animal.getGenero() + "',\n"
                + "'" + animal.getCalificacion() + "',\n"
                + "'" + animal.getNotas() + "',\n"
                + "'" + animal.getFechaDestete() + "',\n"
                + "'" + animal.getCapado() + "',\n"
                + "'" + animal.getFechaNacimiento() + "',\n"
                + "'" + animal.getFechaVenta() + "',\n"
                + "'" + animal.getFechaMuerte() + "',\n"
                + "" + animal.getFecha() + ",\n"
                + "" + animal.getIdUsuario() + ",\n"
                + "" + animal.getPeso() + ",\n"
                + "" + animal.getGrupo() + ",\n"
                + "" + animal.getHierro() + ",\n"
                + "" + animal.getNumeroDescendiente() + ",\n"
                + "" + animal.getEstadoDescendiente() + ",\n"
                + "'" + animal.getMuerte() + "',\n"
                + "'" + animal.getVenta() + "',\n"
                + "" + animal.getPrecioVenta() + ",\n"
                + "" + animal.getTipoVenta() + ",\n"
                + "" + animal.getPesoCanal() + ",\n"
                + "'" + animal.getDescripcionMuerte() + "',\n"
                + "'" + animal.getFechaNovilla() + "',\n"
                + "" + animal.getPesoDestete() + ",\n"
                + "'" + animal.getHierroFisico() + "',\n"
                + "'" + animal.getImplante() + "',\n"
                + "'" + animal.getDescornada() + "'\n"
                + ")"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER TRASLADO">
        ModeloTraslado modeloTraslado = animal.getModeloTraslado();
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                + ")\n"
                + "VALUES (\n"
                + "0,\n"
                + "" + animal.getId() + ",\n"
                + "" + modeloTraslado.getIdFinca() + ",\n"
                + "" + modeloTraslado.getIdGrupo() + ",\n"
                + "" + Utilidades.ValorNULL(modeloTraslado.getFechaTraslado()) + ",\n"
                + "'" + modeloTraslado.getMotivo() + "',\n"
                + "'" + modeloTraslado.getEstado() + "',\n"
                + "" + modeloTraslado.getFecha() + ",\n"
                + "" + modeloTraslado.getIdUsuario() + ")"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER PESO">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "" + animal.getId()+ ",\n"
                + "'" + animal.getFechaNacimiento() + "',\n"
                + "" + animal.getPeso() + ",\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "" + animal.getFecha() + ",\n"
                + "" + animal.getIdUsuario() + "\n"
                + ")" //</editor-fold>
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

    @Override
    public int Actualizar(Object _animal) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloAnimales animal = (ModeloAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE animales SET\n"
                + "id_tipo_animal = " + animal.getIdTipoAnimal() + ",\n"
                + "numero = '" + animal.getNumero() + "',\n"
                + "numero_mama = '" + animal.getNumeroMama() + "',\n"
                + "numero_mama_adoptiva = " + animal.getNumeroMamaAdoptiva() + ",\n"
                + "peso = " + animal.getPeso() + ",\n"
                + "numero_descendiente = " + animal.getNumeroDescendiente() + ",\n"
                + "estado_descendiente = " + animal.getEstadoDescendiente() + ",\n"
                + "precio_venta = " + animal.getPrecioVenta() + ",\n"
                + "peso_canal = " + animal.getPesoCanal() + ",\n"
                + "grupo = " + animal.getGrupo() + ",\n"
                + "hierro = " + animal.getHierro() + ",\n"
                + "genero = '" + animal.getGenero() + "',\n"
                + "calificacion = '" + animal.getCalificacion() + "',\n"
                + "notas = '" + animal.getNotas() + "',\n"
                + "fecha_destete = '" + animal.getFechaDestete() + "',\n"
                + "fecha_venta = '" + animal.getFechaVenta() + "',\n"
                + "capado = '" + animal.getCapado() + "',\n"
                + "muerte = '" + animal.getMuerte() + "',\n"
                + "venta = '" + animal.getVenta() + "',\n"
                + "fecha_nacimiento = '" + animal.getFechaNacimiento() + "',\n"
                + "fecha_muerte = '" + animal.getFechaMuerte() + "',\n"
                + "tipo_venta = " + animal.getTipoVenta() + ",\n"
                + "descripcion_muerte = '" + animal.getDescripcionMuerte() + "',\n"
                + "fecha = " + animal.getFecha() + ","
                + "fecha_novilla = '" + animal.getFechaNovilla() + "',\n"
                + "peso_destete = " + animal.getPesoDestete() + ",\n"
                + "implante = '" + animal.getImplante() + "',\n"
                + "hierro_fisico = '" + animal.getHierroFisico() + "',\n"
                + "descornado = '" + animal.getDescornada() + "'\n"
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

    @Override
    public int Eliminar(Object _animal) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloAnimales animal = (ModeloAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINA EL ANIMAL">
                "DELETE FROM animales WHERE id = " + animal.getId()
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS REGISTROS EN EL TRASLADO">
                "DELETE FROM traslado_animalxgrupo WHERE id_animal=" + animal.getId()
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS REGISTROS DEL HISTORICO DE PESOS">
                "DELETE FROM pesaje WHERE id_animal=" + animal.getId()
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

    @Override
    public Object ObtenerDatosFiltro(Object numero) {
        String consulta = "SELECT a.*,b.descripcion descTipoAnimal, c.descripcion descGrupo, d.descripcion descHierro,\n"
                + "b.id_finca idFinca, e.descripcion descFinca, d.id_propietario idPropietario,\n"
                + "CONCAT(f.identificacion,' - ',CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)\n"
                + "),' ',TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))) descPropietario\n"
                + "FROM animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE a.numero=" + numero;
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloAnimales> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
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
                        grupo.get("descTipoAnimal"),
                        grupo.get("peso"),
                        grupo.get("numero_mama_adoptiva"),
                        grupo.get("grupo"),
                        grupo.get("descGrupo"),
                        grupo.get("hierro"),
                        grupo.get("descHierro"),
                        grupo.get("idFinca"),
                        grupo.get("descFinca"),
                        grupo.get("numero_descendiente"),
                        grupo.get("estado_descendiente"),
                        null,
                        grupo.get("idPropietario"),
                        grupo.get("descPropietario"),
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
                        grupo.get("descornado")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public String ObtenerUltimoDescendiente(String numeroMadre) {
        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("OBTENER_ULTIMO_DESCENDIENTE") + "'" + numeroMadre + "'";
        animal = controlGral.GetComboBox(consulta);

        return animal.get(0).get("numeroDescendiente");
    }

    public Object ObtenerDatosAnimales(String IDFINCA, String IDTIPOFINCA) {
        try {
            String consulta = "SELECT traslado.estado AS ESTADO, traslado.fecha AS FECHA, IFNULL(DATE_FORMAT(traslado.fecha_traslado, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n"
                    + "traslado.id AS ID_TRASLADO, animal.id AS ID_ANIMAL, traslado.id_finca AS ID_FINCA, traslado.id_grupo AS ID_GRUPO,\n"
                    + "traslado.id_usuario AS ID_USUARIO, traslado.motivo AS MOTIVO, IF(animal.numero_mama_adoptiva IS NULL, animal.numero_mama, animal.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "animal.numero AS NUMERO_ANIMAL, animal.peso AS PESO, DATE_FORMAT(animal.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.genero AS GENERO,\n"
                    + "grup.descripcion AS GRUPO, \n"
                    + "IFNULL(finc.id, '') AS IDFINCA, IFNULL(finc.descripcion, '') AS FINCA, \n"
                    + "IFNULL(blo.id, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, \n"
                    + "IFNULL(lot.id, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE\n"
                    + ", animal.id_tipo_animal AS IDTIPO_ANIMAL, tpoani.descripcion AS TIPO_ANIMAL, \n"
                    + "IFNULL(animal.capado, 'No') AS CAPADO,  IF(animal.muerte = '0', 'No', 'Si') AS MUERTE,\n"
                    + "IF(animal.venta = '0', 'No', 'Si') AS VENTA,  animal.hierro AS IDHIERRO, hierro.descripcion AS DESC_HIERRO \n"
                    + "FROM animales animal\n"
                    + "INNER JOIN propietarioxhierro hierro ON hierro.id = animal.hierro \n"
                    + "INNER JOIN tipo_animales tpoani ON tpoani.id = animal.id_tipo_animal \n"
                    + "LEFT JOIN traslado_animalxgrupo traslado ON traslado.id_animal = animal.id\n"
                    + "LEFT JOIN grupos grup ON grup.id = traslado.id_grupo\n"
                    + "LEFT JOIN (\n"
                    + "SELECT rot.id AS ID_ROTACION, rotgrup.id AS ID_ROT_GRUPO, rot.id_lote AS ID_LOTE, rotgrup.id_grupo AS ID_GRUPO,\n"
                    + "rot.fecha_entrada AS FECHA_ENTRADA, rot.fecha_registro AS FECHA_REGISTRO,\n"
                    + "rot.fecha_salida AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.estado AS ESTADO_GRUPO\n"
                    + "FROM rotacion_lotesxestado rot\n"
                    + "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.id_rotacion_lotesxestado = rot.id\n"
                    + "WHERE rot.estado = 'Activo' AND rotgrup.estado = 'Activo'\n"
                    + ") AS tbl ON tbl.ID_GRUPO = traslado.id_grupo\n"
                    + "LEFT JOIN lotes lot ON lot.id = tbl.ID_LOTE \n"
                    + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                    + "LEFT JOIN fincas finc ON finc.id = traslado.id_finca\n"
                    + "WHERE traslado.id_finca = '" + IDFINCA + "' AND tpoani.id = '" + IDTIPOFINCA + "' AND traslado.estado = 'Activo'\n"
                    + "ORDER BY animal.id ASC";

            consulta = "SELECT traslado.estado AS ESTADO, traslado.fecha AS FECHA, IFNULL(DATE_FORMAT(traslado.fecha_traslado, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n"
                    + "traslado.id AS ID_TRASLADO, animal.id AS ID_ANIMAL, traslado.id_finca AS ID_FINCA, traslado.id_grupo AS ID_GRUPO,\n"
                    + "traslado.id_usuario AS ID_USUARIO, traslado.motivo AS MOTIVO, IF(animal.numero_mama_adoptiva IS NULL, animal.numero_mama, animal.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "animal.numero AS NUMERO_ANIMAL, animal.peso AS PESO, DATE_FORMAT(animal.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.genero AS GENERO,\n"
                    + "grup.descripcion AS GRUPO, \n"
                    + "IFNULL(finc.id, '') AS IDFINCA, IFNULL(finc.descripcion, '') AS FINCA, \n"
                    + "IFNULL(blo.id, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.numero), '') AS BLOQUE, \n"
                    + "IFNULL(lot.id, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.numero), '') AS LOTE\n"
                    + ", animal.id_tipo_animal AS IDTIPO_ANIMAL, tpoani.descripcion AS TIPO_ANIMAL, \n"
                    + "IFNULL(animal.capado, 'No') AS CAPADO,  IF(animal.muerte = '0', 'No', 'Si') AS MUERTE,\n"
                    + "IF(animal.venta = '0', 'No', 'Si') AS VENTA,  animal.hierro AS IDHIERRO, hierro.descripcion AS DESC_HIERRO \n"
                    + "FROM animales animal\n"
                    + "INNER JOIN propietarioxhierro hierro ON hierro.id = animal.hierro \n"
                    + "INNER JOIN tipo_animales tpoani ON tpoani.id = animal.id_tipo_animal \n"
                    + "LEFT JOIN traslado_animalxgrupo traslado ON traslado.id_animal = animal.id\n"
                    + "LEFT JOIN grupos grup ON grup.id = traslado.id_grupo\n"
                    + "LEFT JOIN `rotacion_lote` rot ON rot.`id_grupo` = traslado.`id_grupo` AND rot.`estado` = 'Activo'\n"
                    + "LEFT JOIN lotes lot ON lot.id = rot.`id_lote`\n"
                    + "LEFT JOIN bloques blo ON blo.id = lot.id_bloque\n"
                    + "LEFT JOIN fincas finc ON finc.id = traslado.id_finca\n"
                    + "WHERE traslado.id_finca = '" + IDFINCA + "' AND tpoani.id = '" + IDTIPOFINCA + "' AND traslado.estado = 'Activo'\n"
                    + "ORDER BY animal.id ASC";

            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String, String> GetDatosVenta(String id_Animal) {
        try {
            String consulta = "SELECT numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL,numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "DATE_FORMAT(fecha_venta, '%d/%m/%Y') AS FECHA_VENTA, tipo_venta AS TIPO_VENTA, \n"
                    + "MascaraMonedaDecimal(REPLACE(precio_venta, '.', ',')) AS PRECIO_VENTA, peso AS PESO, peso_canal AS PESO_CANAL, \n"
                    + "MascaraMonedaDecimal(REPLACE((PRECIO_VENTA * IF(peso_canal IS NULL, peso, peso_canal)), '.', ',')) PRECIO_TOTAL, \n"
                    + "ROUND((peso_canal/peso * 100), 0) PORCENTAJE_CANAL \n"
                    + "FROM animales \n"
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
                "UPDATE animales SET\n"
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
                "UPDATE animales SET\n"
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
            String consulta = "SELECT numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL,numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "DATE_FORMAT(fecha_muerte, '%d/%m/%Y') AS FECHA_MUERTE, descripcion_muerte AS MOTIVO \n"
                    + "FROM animales \n"
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
            String consulta = "SELECT anim.`numero` AS NUMERO_ANIMAL, grup.`descripcion` AS GRUPO,\n"
                    + "DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "traslado.motivo AS MOTIVO, traslado.estado AS ESTADO, traslado.id as IDTRASLADO\n"
                    + "FROM `traslado_animalxgrupo` traslado\n"
                    + "INNER JOIN animales anim ON anim.`id` = traslado.`id_animal`\n"
                    + "INNER JOIN  grupos grup ON grup.`id` = traslado.`id_grupo`\n"
                    + "WHERE traslado.`id_animal` = '" + id_Animal + "'\n"
                    + "ORDER BY traslado.`id` DESC";

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
            String consulta = "SELECT anim.`numero` AS NUMERO_ANIMAL,\n"
                    + "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n"
                    + "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE,\n"
                    + "traslado.`id`, anim.`numero` AS NUMERO_ANIMAL,\n"
                    + "grup.`id` AS IDGRUPO, grup.`descripcion` AS GRUPO,\n"
                    + "DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "DATE_FORMAT(tbl.FECHA_ENTRADA, '%d/%m/%Y') AS FECHA_ENTRADA,\n"
                    + "IFNULL(DATE_FORMAT(tbl.FECHA_SALIDA, '%d/%m/%Y'), '') AS FECHA_SALIDA,\n"
                    + "IF(traslado.estado = 'Activo' AND tbl.ESTADO_GRUPO = 'Activo' AND tbl.ESTADO_LOTE = 'Activo', 'Activo', 'Inactivo') ESTADO\n"
                    + "FROM `traslado_animalxgrupo` traslado\n"
                    + "INNER JOIN animales anim ON anim.`id` = traslado.`id_animal`\n"
                    + "INNER JOIN  grupos grup ON grup.`id` = traslado.`id_grupo`\n"
                    + "LEFT JOIN (\n"
                    + "SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n"
                    + "rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n"
                    + "rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n"
                    + "FROM `rotacion_lotesxestado` rot\n"
                    + "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n"
                    + "INNER JOIN traslado_animalxgrupo tras ON tras.`id_grupo` = rotgrup.id_grupo\n"
                    + " AND ((rot.fecha_salida IS NULL AND rot.fecha_entrada <= tras.fecha_traslado)  OR (rot.fecha_salida IS NOT NULL AND tras.fecha_traslado BETWEEN rot.fecha_entrada AND rot.fecha_salida))\n"
                    + "WHERE tras.`id_animal` = '" + id_Animal + "'\n"
                    + ") AS tbl ON tbl.ID_GRUPO = traslado.`id_grupo`\n"
                    + "LEFT JOIN `lotes` lot ON lot.`id` = tbl.ID_LOTE \n"
                    + "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n"
                    + "WHERE traslado.`id_animal` = '" + id_Animal + "'\n"
                    + "ORDER BY traslado.`id` DESC, tbl.ID_ROTACION DESC;";

            consulta = "SELECT anim.`numero` AS NUMERO_ANIMAL,\n"
                    + "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n"
                    + "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE,\n"
                    + "tras.`id`, anim.`numero` AS NUMERO_ANIMAL,\n"
                    + "grup.`id` AS IDGRUPO, grup.`descripcion` AS GRUPO,\n"
                    + "DATE_FORMAT(tras.`fecha_traslado`, '%d/%m/%Y') AS FECHA_TRASLADO,\n"
                    + "DATE_FORMAT(rot.`fecha_entrada`, '%d/%m/%Y') AS FECHA_ENTRADA,\n"
                    + "IFNULL(DATE_FORMAT(rot.`fecha_salida`, '%d/%m/%Y'), '') AS FECHA_SALIDA, \n"
                    + "tras.motivo AS MOTIVO,\n"
                    + "lot.`id`, rot.id AS IDROTACION, tras.`id` AS IDTRASLADO,\n"
                    + "IF(tras.estado = 'Activo' AND rot.`estado` = 'Activo', 'Activo', 'Inactivo') ESTADO,\n"
                    + "tras.estado AS EST_TRASLADO, rot.`estado` AS EST_ROTACION\n"
                    + "FROM `rotacion_lote` rot\n"
                    + "INNER JOIN traslado_animalxgrupo tras ON tras.`id_grupo` = rot.id_grupo\n"
                    + "INNER JOIN animales anim ON anim.`id` = tras.`id_animal`\n"
                    + "INNER JOIN  grupos grup ON grup.`id` = tras.`id_grupo`\n"
                    + "LEFT JOIN `lotes` lot ON lot.`id` = rot.`id_lote`\n"
                    + "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n"
                    + "WHERE tras.`id_animal` = '" + id_Animal + "' \n"
                    + "ORDER BY tras.`id` DESC, rot.`id` DESC;	";
            System.out.println("GetDatosrotaciones...>" + consulta);

            List<Map<String, String>> rotaciones = new ArrayList<Map<String, String>>();

            rotaciones = mySQL.ListSQL(consulta);

            return rotaciones;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Object ObtenerDatosAnimalesPesables(String IDFINCA, String IDTIPOFINCA) {
        try {
            String consulta = "SELECT\n"
                    + "a.id as ID_ANIMAL,\n"
                    + "a.numero as NUMERO_ANIMAL,\n"
                    + "IF(a.numero_mama_adoptiva IS NULL, a.numero_mama, a.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "a.peso AS PESO,\n"
                    + "DATE_FORMAT(a.fecha_nacimiento, '%d/%m/%Y') AS FECHA_NACIMIENTO,\n"
                    + "a.genero AS GENERO,\n"
                    + "c.descripcion as GRUPO,\n"
                    + "d.id as IDFINCA,\n"
                    + "IFNULL(d.descripcion, '') AS FINCA,\n"
                    + "b.id as IDTIPO_ANIMAL,\n"
                    + "b.descripcion as TIPO_ANIMAL,\n"
                    + "IFNULL(a.capado, 'No') AS CAPADO,\n"
                    + "IF(a.muerte = '0', 'No', 'Si') AS MUERTE,\n"
                    + "IF(a.venta = '0', 'No', 'Si') AS VENTA,\n"
                    + "a.hierro AS IDHIERRO,\n"
                    + "e.descripcion AS DESC_HIERRO,\n"
                    + "IFNULL((SELECT '*' FROM pesaje m WHERE m.id_animal=a.id AND DATE_FORMAT(m.fecha,'%Y-%m-%d')=DATE_FORMAT(NOW(),'%Y-%m-%d')),'') AS EST\n"
                    + "FROM\n"
                    + "animales a\n"
                    + "left join tipo_animales b on a.id_tipo_animal=b.id\n"
                    + "left join grupos c on a.grupo=c.id\n"
                    + "left join fincas d on d.id=b.id_finca\n"
                    + "left join propietarioxhierro e on a.hierro=e.id\n"
                    + "WHERE\n"
                    + "c.pesable='1' and d.id=" + IDFINCA + " and b.id=" + IDTIPOFINCA + "\n"
                    + "ORDER BY\n"
                    + "a.id ASC";
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    
    public Object ObtenerDatosAnimalesPalpacion(String IDFINCA, String IDTIPOFINCA) {
        try {
//            NameColumnasFiltro.add("NUMERO_ANIMAL");
//            NameColumnasFiltro.add("NUMERO_MAMA");
//            NameColumnasFiltro.add("NUMERO_HIJOS");
//            NameColumnasFiltro.add("NUMERO_PARTOS");
//            NameColumnasFiltro.add("FECHA_NOVILLA");
//            NameColumnasFiltro.add("ESTADO");
//            NameColumnasFiltro.add("NUMERO_MESES");
//            NameColumnasFiltro.add("FECHA_ULT_PARTO");
//            NameColumnasFiltro.add("EST");
            String consulta = "SELECT anim.`id` AS IDANIMAL, anim.`numero` AS NUMERO_ANIMAL, anim.`numero_mama` AS NUMERO_MAMA,\n" +
                                " IFNULL(DATE_FORMAT(anim.`fecha_novilla`, '%d/%m/%Y'), '') FECHA_NOVILLA\n" +
                                ", `NumeroHijos`(anim.`numero`) NUMERO_HIJOS, IFNULL(`NumeroPartos`(anim.`numero`), '0') NUMERO_PARTOS,\n" +
                                "IFNULL(tbl.DIAG, '') ESTADO, IFNULL(DATE_FORMAT(tbl.FPALP, '%d/%m/%Y'), '') FECHA_PALP, IFNULL(tbl.IDPALP, '') IDPALPACION,\n" +
                                "IFNULL(DATE_FORMAT(tbl.FULTPARTO, '%d/%m/%Y'), '') FECHA_ULT_PARTO,\n" +
                                "IFNULL(tbl.NMESES, '') AS NUMERO_MESES,\n" +
                                "\n" +
                                "IF(DATEDIFF(NOW(),tbl.FPALP)<30, '*', '') AS EST\n" +
                                "FROM animales anim\n" +
                                "LEFT JOIN tipo_animales tpo ON anim.id_tipo_animal=tpo.id\n" +
                                "LEFT JOIN grupos grup ON anim.grupo=grup.id\n" +
                                "LEFT JOIN fincas finc ON tpo.id_finca=finc.`id`\n" +
                                "LEFT JOIN propietarioxhierro propxhi ON anim.hierro=propxhi.id \n" +
                                "LEFT JOIN (\n" +
                                "	SELECT MAX(palp.`id`) AS IDPALP, `fecha_palpacion` AS FPALP, diagnostico AS DIAG,\n" +
                                "	num_meses AS NMESES, fecha_ultimo_parto AS FULTPARTO, anim.id AS IDANIMAL\n" +
                                "	FROM `palpacion` palp\n" +
                                "	INNER JOIN `animales` anim ON anim.`id` = palp.`id_animal`\n" +
                                "	INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n" +
                                "	WHERE tpo.`id_finca` = '"+IDFINCA+"' AND tpo.`id` = '"+IDTIPOFINCA+"'\n" +
                                "	GROUP BY palp.`id_animal` \n" +
                                ") tbl ON tbl.IDANIMAL = anim.`id`\n" +
                                "WHERE grup.`palpable` = '1' AND finc.`id` = '"+IDFINCA+"' AND tpo.`id` = '"+IDTIPOFINCA+"'\n" +
                                "ORDER BY anim.id ASC";
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

            return traslados;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
