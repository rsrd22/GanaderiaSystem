/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.RAnimales;

import BaseDeDatos.gestorMySQL;
import Control.ControlGeneral;
import Control.ControlTraslado;
import Control.IControl;
import Control.Retorno;
import Modelo.ModeloAnimales;
import Modelo.ModeloMuertesVentasHistoricos;
import Modelo.ModeloTraslado;
import Modelo.RAnimales.ModeloRAnimales;
import Modelo.RAnimales.ModeloRAnimalesEntrada;
import Modelo.RAnimales.ModeloRAnimalesSalida;
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
                + "a.id,a.id_tipo_animal,a.hierro,a.numero,a.numero_descendiente,a.estado_descendiente,\n"
                + "a.numero_parto,a.cantidad_parto,a.es_madre,a.numero_mama,a.numero_mama_adoptiva,\n"
                + "a.peso,a.genero,a.grupo,a.calificacion,a.notas,a.fecha_destete,a.capado,a.fecha_nacimiento,\n"
                + "a.muerte,a.peso_destete,a.destete,a.fecha_muerte,a.descripcion_muerte,a.venta,a.fecha_venta,\n"
                + "a.tipo_venta,a.precio_venta,a.peso_canal,a.fecha_novilla,a.hierro_fisico,a.implante,\n"
                + "a.descornado,a.fecha,a.id_usuario,b.descripcion descTipoAnimal,c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,b.id_finca idFinca,e.descripcion descFinca,d.id_propietario idPropietario,\n"
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
                + "a.id,a.id_tipo_animal,a.hierro,a.numero,a.numero_descendiente,a.estado_descendiente,\n"
                + "a.numero_parto,a.cantidad_parto,a.es_madre,a.numero_mama,a.numero_mama_adoptiva,\n"
                + "a.peso,a.genero,a.grupo,a.calificacion,a.notas,a.fecha_destete,a.capado,a.fecha_nacimiento,\n"
                + "a.muerte,a.peso_destete,a.destete,a.fecha_muerte,a.descripcion_muerte,a.venta,a.fecha_venta,\n"
                + "a.tipo_venta,a.precio_venta,a.peso_canal,a.fecha_novilla,a.hierro_fisico,a.implante,\n"
                + "a.descornado,a.fecha,a.id_usuario,b.descripcion descTipoAnimal,c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,b.id_finca idFinca,e.descripcion descFinca,d.id_propietario idPropietario,\n"
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
        ArrayList<String> consultas = new ArrayList<>();
        ModeloRAnimalesEntrada _objeto = (ModeloRAnimalesEntrada) o;
        ModeloRAnimales animal = _objeto.getAnimal();
        ModeloTraslado traslado = _objeto.getTraslado();

        if (animal.getVenta().equals("1")) {
            //<editor-fold defaultstate="collapsed" desc="VERIFICAR SI HAY REGISTROS ANULADOS ACTIVOS">
            ModeloMuertesVentasHistoricos modelAnul = new ModeloMuertesVentasHistoricos(
                    "", "venta", animal.getId(),
                    "", "", "", "");
            if (mySQL.ExistenDatos("select * from anularventasymuertes where tipo = '" + modelAnul.getTipo() + "' "
                    + "and id_animal = " + modelAnul.getIdAnimal() + " and estado = 'Activo'")) {
                consultas.add("UPDATE `anularventasymuertes`\n"
                        + "SET `estado` = 'Inactivo'\n"
                        + "WHERE `tipo` = '" + modelAnul.getTipo() + "' AND `id_animal` = '" + modelAnul.getIdAnimal() + "' AND estado = 'Activo'");
            }
//</editor-fold>
        }
        if (animal.getMuerte().equals("1")) {
            //<editor-fold defaultstate="collapsed" desc="VERIFICAR SI HAY REGISTROS ANULADOS ACTIVOS">
            ModeloMuertesVentasHistoricos modelAnul = new ModeloMuertesVentasHistoricos(
                    "", "muerte", animal.getId(),
                    "", "", "", "");
            if (mySQL.ExistenDatos("select * from anularventasymuertes where tipo = '" + modelAnul.getTipo() + "' "
                    + "and id_animal = " + modelAnul.getIdAnimal() + " and estado = 'Activo'")) {
                consultas.add("UPDATE `anularventasymuertes`\n"
                        + "SET `estado` = 'Inactivo'\n"
                        + "WHERE `tipo` = '" + modelAnul.getTipo() + "' AND `id_animal` = '" + modelAnul.getIdAnimal() + "' AND estado = 'Activo'");
            }
//</editor-fold>
        }

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelAnimal">
        consultas.add("insert into `ganadero`.`ranimales`\n"
                + "            (`id`,\n"
                + "             `id_tipo_animal`,\n"
                + "             `hierro`,\n"
                + "             `numero`,\n"
                + "             `numero_descendiente`,\n"
                + "             `estado_descendiente`,\n"
                + "             `numero_parto`,\n"
                + "             `cantidad_parto`,\n"
                + "             `es_madre`,\n"
                + "             `numero_mama`,\n"
                + "             `numero_mama_adoptiva`,\n"
                + "             `peso`,\n"
                + "             `genero`,\n"
                + "             `grupo`,\n"
                + "             `calificacion`,\n"
                + "             `notas`,\n"
                + "             `fecha_destete`,\n"
                + "             `capado`,\n"
                + "             `fecha_nacimiento`,\n"
                + "             `muerte`,\n"
                + "             `peso_destete`,\n"
                + "             `destete`,\n"
                + "             `fecha_muerte`,\n"
                + "             `descripcion_muerte`,\n"
                + "             `venta`,\n"
                + "             `fecha_venta`,\n"
                + "             `tipo_venta`,\n"
                + "             `precio_venta`,\n"
                + "             `peso_canal`,\n"
                + "             `fecha_novilla`,\n"
                + "             `hierro_fisico`,\n"
                + "             `implante`,\n"
                + "             `descornado`,\n"
                + "             `fecha`,\n"
                + "             `id_usuario`)\n"
                + "values (0,\n"
                + "" + animal.getId_tipo_animal() + ",\n"
                + "" + animal.getHierro() + ",\n"
                + "'" + animal.getNumero() + "',\n"
                + "" + animal.getNumero_descendiente() + ",\n"
                + "'" + animal.getEstado_descendiente() + "',\n"
                + "" + animal.getNumero_parto() + ",\n"
                + "" + animal.getCantidad_parto() + ",\n"
                + "" + Utilidades.CampoNULL(animal.getEs_madre()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getNumero_mama()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getNumero_mama_adoptiva()) + ",\n"
                + "" + animal.getPeso() + ",\n"
                + "'" + animal.getGenero() + "',\n"
                + "" + animal.getGrupo() + ",\n"
                + "'" + animal.getCalificacion() + "',\n"
                + "'" + animal.getNotas() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_destete()) + ",\n"
                + "'" + animal.getCapado() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "'" + animal.getMuerte() + "',\n"
                + "" + animal.getPeso_destete() + ",\n"
                + "'" + animal.getDestete() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_muerte()) + ",\n"
                + "'" + animal.getDescripcion_muerte() + "',\n"
                + "'" + animal.getVenta() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_venta()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getTipo_venta()) + ",\n"
                + "" + animal.getPrecio_venta() + ",\n"
                + "" + animal.getPeso_canal() + ",\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_novilla()) + ",\n"
                + "'" + animal.getHierro_fisico() + "',\n"
                + "'" + animal.getImplante() + "',\n"
                + "'" + animal.getDescornado() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha()) + ",\n"
                + "" + animal.getId_usuario() + ");");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelPrimerTrasladoDelAnimal">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                + ")\n"
                + "VALUES (\n"
                + "0,\n"
                + "" + traslado.getIdAnimal() + ",\n"
                + "" + traslado.getIdFinca() + ",\n"
                + "" + traslado.getIdGrupo() + ",\n"
                + "" + Utilidades.ValorNULL(traslado.getFechaTraslado()) + ",\n"
                + "'" + traslado.getMotivo() + "',\n"
                + "'" + traslado.getEstado() + "',\n"
                + "" + Utilidades.ValorNULL(traslado.getFecha()) + ",\n"
                + "" + traslado.getIdUsuario() + ")"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelPrimerPeso">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,peso_anterior,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "" + animal.getId() + ",\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "" + animal.getPeso() + ",\n"
                + "0,\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha()) + ",\n"
                + "" + animal.getId_usuario() + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="actualizarElRegistroDeLaMadre">
        if (!_objeto.getActualizarRegistroMadre().isEmpty()) {
            consultas.add(_objeto.getActualizarRegistroMadre());
        }
        //</editor-fold>

        return EjecutarConsultas(consultas);
    }

    @Override
    public int Actualizar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloRAnimalesEntrada _objeto = (ModeloRAnimalesEntrada) o;
        ModeloRAnimales animal = _objeto.getAnimal();

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelAnimal">
        consultas.add("UPDATE `ranimales` SET \n"
                + "id_tipo_animal=" + animal.getId_tipo_animal() + ",\n"
                + "hierro=" + animal.getHierro() + ",\n"
                + "numero='" + animal.getNumero() + "',\n"
                + "numero_descendiente=" + animal.getNumero_descendiente() + ",\n"
                + "estado_descendiente='" + animal.getEstado_descendiente() + "',\n"
                + "numero_parto=" + animal.getNumero_parto() + ",\n"
                + "es_madre=" + Utilidades.CampoNULL(animal.getEs_madre()) + ",\n"
                + "numero_mama=" + Utilidades.CampoNULL(animal.getNumero_mama()) + ",\n"
                + "numero_mama_adoptiva=" + Utilidades.CampoNULL(animal.getNumero_mama_adoptiva()) + ",\n"
                + "peso=" + animal.getPeso() + ",\n"
                + "genero='" + animal.getGenero() + "',\n"
                + "grupo=" + animal.getGrupo() + ",\n"
                + "calificacion='" + animal.getCalificacion() + "',\n"
                + "notas='" + animal.getNotas() + "',\n"
                + "fecha_destete=" + Utilidades.ValorNULL(animal.getFecha_destete()) + ",\n"
                + "capado='" + animal.getCapado() + "',\n"
                + "fecha_nacimiento=" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "muerte='" + animal.getMuerte() + "',\n"
                + "peso_destete=" + animal.getPeso_destete() + ",\n"
                + "destete='" + animal.getDestete() + "',\n"
                + "fecha_muerte=" + Utilidades.ValorNULL(animal.getFecha_muerte()) + ",\n"
                + "descripcion_muerte='" + animal.getDescripcion_muerte() + "',\n"
                + "venta='" + animal.getVenta() + "',\n"
                + "fecha_venta=" + Utilidades.ValorNULL(animal.getFecha_venta()) + ",\n"
                + "tipo_venta=" + Utilidades.CampoNULL(animal.getTipo_venta()) + ",\n"
                + "precio_venta=" + animal.getPrecio_venta() + ",\n"
                + "peso_canal=" + animal.getPeso_canal() + ",\n"
                + "fecha_novilla=" + Utilidades.ValorNULL(animal.getFecha_novilla()) + ",\n"
                + "hierro_fisico='" + animal.getHierro_fisico() + "',\n"
                + "implante='" + animal.getImplante() + "',\n"
                + "descornado='" + animal.getDescornado() + "',\n"
                + "fecha=" + Utilidades.ValorNULL(animal.getFecha()) + ",\n"
                + "id_usuario=" + animal.getId_usuario() + "\n"
                + "WHERE id=" + animal.getId());
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="actualizarElRegistroDeLaMadre">
        if (!_objeto.getActualizarRegistroMadre().isEmpty()) {
            consultas.add(_objeto.getActualizarRegistroMadre());
        }
        //</editor-fold>
        return EjecutarConsultas(consultas);
    }

    @Override
    public int Eliminar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        int id = Integer.parseInt(o.toString());

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINA EL ANIMAL">
                "DELETE FROM ranimales WHERE id = " + id
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS REGISTROS EN EL TRASLADO">
                "DELETE FROM traslado_animalxgrupo WHERE id_animal=" + id
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS REGISTROS DEL HISTORICO DE PESOS">
                "DELETE FROM pesaje WHERE id_animal=" + id
        //</editor-fold>
        );

        return EjecutarConsultas(consultas);
    }

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        String[] parametros = (String[]) o;

        //<editor-fold defaultstate="collapsed" desc="selectDatosFromQuery">
        String consulta = "SELECT \n"
                + "a.id,a.id_tipo_animal,a.hierro,a.numero,a.numero_descendiente,a.estado_descendiente,\n"
                + "a.numero_parto,a.cantidad_parto,a.es_madre,a.numero_mama,a.numero_mama_adoptiva,\n"
                + "a.peso,a.genero,a.grupo,a.calificacion,a.notas,a.fecha_destete,a.capado,a.fecha_nacimiento,\n"
                + "a.muerte,a.peso_destete,a.destete,a.fecha_muerte,a.descripcion_muerte,a.venta,a.fecha_venta,\n"
                + "a.tipo_venta,a.precio_venta,a.peso_canal,a.fecha_novilla,a.hierro_fisico,a.implante,\n"
                + "a.descornado,a.fecha,a.id_usuario,b.descripcion descTipoAnimal,c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,b.id_finca idFinca,e.descripcion descFinca,d.id_propietario idPropietario,\n"
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
                + "WHERE a.numero='" + parametros[0] + "'  AND a.id_tipo_animal='" + parametros[1] + "'";
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

    public Map<String, String> ObtenerIDMadre(String numeroMadre, String tipoAnimal) {
        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("ROBTENER_ID_MADRE")
                .replaceAll("ID_TIPO_ANIMAL", tipoAnimal)
                .replaceAll("NUMERO_MAMA", numeroMadre);
        animal = controlGral.GetComboBox(consulta);

        if (Utilidades.contieneElementos(animal)) {
            return animal.get(0);
        } else {
            return null;
        }
    }

    public Map<String, String> ObtenerCantidadPartos(String numeroMadre, String tipoAnimal) {
        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("ROBTENER_CANTIDAD_PARTOS")
                .replaceAll("ID_TIPO_ANIMAL", tipoAnimal)
                .replaceAll("NUMERO_MAMA", numeroMadre);
        animal = controlGral.GetComboBox(consulta);

        if (Utilidades.contieneElementos(animal)) {
            return animal.get(0);
        } else {
            return null;
        }
    }

    public String ObtenerUltimoDescendiente(String idMadre) {
        if (idMadre.equalsIgnoreCase("null")) {
            return "";
        }

        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("ROBTENER_ULTIMO_DESCENDIENTE").replaceAll("ID_MAMA", idMadre);
        animal = controlGral.GetComboBox(consulta);

        return animal.get(0).get("numeroDescendiente");
    }

    public String ObtenerNumeroParto(String idMadre, String fup) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("ROBTENER_NUMERO_PARTO")
                .replaceAll("ID_MAMA", idMadre)
                .replaceAll("FUP", fup);
        data = controlGral.GetComboBox(consulta);

        return data.get(0).get("NUMERO_PARTO");
    }

    public int GuardarCria(Object[] array, Object obj) {
        ArrayList<String> consultas = new ArrayList<>();
        ArrayList<ModeloTraslado> traslados = new ArrayList<>();
        ModeloRAnimalesEntrada me = (ModeloRAnimalesEntrada) array[0];
        ModeloRAnimales animal = me.getAnimal();

        String grupoAnteriorMama = (String) obj;
        String idMadre = "";
        String idAnimal = "(SELECT (AUTO_INCREMENT-1)\n"
                + "FROM information_schema.tables\n"
                + "WHERE table_name = 'ranimales'\n"
                + "AND table_schema = 'ganadero')";

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelAnimal">
        consultas.add("insert into `ganadero`.`ranimales`\n"
                + "            (`id`,\n"
                + "             `id_tipo_animal`,\n"
                + "             `hierro`,\n"
                + "             `numero`,\n"
                + "             `numero_descendiente`,\n"
                + "             `estado_descendiente`,\n"
                + "             `numero_parto`,\n"
                + "             `cantidad_parto`,\n"
                + "             `es_madre`,\n"
                + "             `numero_mama`,\n"
                + "             `numero_mama_adoptiva`,\n"
                + "             `peso`,\n"
                + "             `genero`,\n"
                + "             `grupo`,\n"
                + "             `calificacion`,\n"
                + "             `notas`,\n"
                + "             `fecha_destete`,\n"
                + "             `capado`,\n"
                + "             `fecha_nacimiento`,\n"
                + "             `muerte`,\n"
                + "             `peso_destete`,\n"
                + "             `destete`,\n"
                + "             `fecha_muerte`,\n"
                + "             `descripcion_muerte`,\n"
                + "             `venta`,\n"
                + "             `fecha_venta`,\n"
                + "             `tipo_venta`,\n"
                + "             `precio_venta`,\n"
                + "             `peso_canal`,\n"
                + "             `fecha_novilla`,\n"
                + "             `hierro_fisico`,\n"
                + "             `implante`,\n"
                + "             `descornado`,\n"
                + "             `fecha`,\n"
                + "             `id_usuario`)\n"
                + "values (0,\n"
                + "" + animal.getId_tipo_animal() + ",\n"
                + "" + animal.getHierro() + ",\n"
                + "'" + animal.getNumero() + "',\n"
                + "" + animal.getNumero_descendiente() + ",\n"
                + "'" + animal.getEstado_descendiente() + "',\n"
                + "" + animal.getNumero_parto() + ",\n"
                + "" + animal.getCantidad_parto() + ",\n"
                + "" + Utilidades.CampoNULL(animal.getEs_madre()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getNumero_mama()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getNumero_mama_adoptiva()) + ",\n"
                + "" + animal.getPeso() + ",\n"
                + "'" + animal.getGenero() + "',\n"
                + "" + animal.getGrupo() + ",\n"
                + "'" + animal.getCalificacion() + "',\n"
                + "'" + animal.getNotas() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_destete()) + ",\n"
                + "'" + animal.getCapado() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "'" + animal.getMuerte() + "',\n"
                + "" + animal.getPeso_destete() + ",\n"
                + "'" + animal.getDestete() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_muerte()) + ",\n"
                + "'" + animal.getDescripcion_muerte() + "',\n"
                + "'" + animal.getVenta() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_venta()) + ",\n"
                + "" + Utilidades.CampoNULL(animal.getTipo_venta()) + ",\n"
                + "" + animal.getPrecio_venta() + ",\n"
                + "" + animal.getPeso_canal() + ",\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_novilla()) + ",\n"
                + "'" + animal.getHierro_fisico() + "',\n"
                + "'" + animal.getImplante() + "',\n"
                + "'" + animal.getDescornado() + "',\n"
                + "" + Utilidades.ValorNULL(animal.getFecha()) + ",\n"
                + "" + animal.getId_usuario() + ");");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER TRASLADO">
        traslados = (ArrayList<ModeloTraslado>) array[1];
        idMadre = traslados.get(1).getIdAnimal();
        for (int i = 0; i < traslados.size(); i++) {
            System.out.println("i: " + i);
            System.out.println("traslados.get(i).getIdGrupo(): " + traslados.get(i).getIdGrupo());
            System.out.println("grupoAnteriorMama: " + grupoAnteriorMama);
            System.out.println("condicion: " + (i > 0 && !grupoAnteriorMama.equals(traslados.get(i).getIdGrupo())));
            if (i > 0 && !grupoAnteriorMama.equals(traslados.get(i).getIdGrupo())) {
                consultas.add("UPDATE traslado_animalxgrupo\n"
                        + "SET estado = 'Inactivo'\n"
                        + "WHERE id_animal = " + traslados.get(i).getIdAnimal()
                        + " AND estado = 'Activo' "
                );

                consultas.add("update ranimales set grupo=" + traslados.get(i).getIdGrupo() + " where id=" + traslados.get(i).getIdAnimal());
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="INSERT">
                        "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                        + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                        + ")\n"
                        + "VALUES (\n"
                        + "0,\n"
                        + "" + traslados.get(i).getIdAnimal() + ",\n"
                        + "" + traslados.get(i).getIdFinca() + ",\n"
                        + "" + traslados.get(i).getIdGrupo() + ",\n"
                        + "" + Utilidades.ValorNULL(traslados.get(i).getFechaTraslado()) + ",\n"
                        + "'" + traslados.get(i).getMotivo() + "',\n"
                        + "'" + traslados.get(i).getEstado() + "',\n"
                        + "" + traslados.get(i).getFecha() + ",\n"
                        + "" + traslados.get(i).getIdUsuario() + ");"
                //</editor-fold>
                );
            } else if (i == 0) {
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="INSERT">
                        "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                        + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                        + ")\n"
                        + "VALUES (\n"
                        + "0,\n"
                        + "" + traslados.get(i).getIdAnimal() + ",\n"
                        + "" + traslados.get(i).getIdFinca() + ",\n"
                        + "" + traslados.get(i).getIdGrupo() + ",\n"
                        + "" + Utilidades.ValorNULL(traslados.get(i).getFechaTraslado()) + ",\n"
                        + "'" + traslados.get(i).getMotivo() + "',\n"
                        + "'" + traslados.get(i).getEstado() + "',\n"
                        + "" + traslados.get(i).getFecha() + ",\n"
                        + "" + traslados.get(i).getIdUsuario() + ");"
                //</editor-fold>
                );
            }
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER PESO">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,peso_anterior,notas,hierro,descornado,implante,destete,fecha,id_usuario) "
                + "VALUES(\n"
                + "0,\n"
                + "" + idAnimal + ",\n"
                + "'" + animal.getFecha_nacimiento() + "',\n"
                + "" + animal.getPeso() + ",\n"
                + "0,\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "" + animal.getFecha() + ",\n"
                + "" + animal.getId_usuario() + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INACTIVAR LA ULTIMA PALPACION ACTIVA">
        List<Map<String, String>> palpacion = new ArrayList<>();
        String consulta = "SELECT id FROM palpacion WHERE id_animal=" + idMadre + " AND estado='Activo'";
        palpacion = mySQL.ListSQL(consulta);
        if (palpacion.size() > 0) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "UPDATE palpacion SET estado='Inactivo' WHERE id_animal=" + idMadre + " AND estado='Activo'"
            //</editor-fold>
            );
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DE PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO palpacion\n"
                + "(id, id_animal, fecha_palpacion, diagnostico, notas, num_meses, "
                + "fecha_ultimo_parto, descarte, razondescarte, fecha, id_usuario,estado)\n"
                + "VALUES (0,\n"
                + "" + idMadre + ",\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "'vacia',\n"
                + "'PARTO',\n"
                + "0,\n"
                + "" + Utilidades.ValorNULL(animal.getFecha_nacimiento()) + ",\n"
                + "'0',\n"
                + "'',\n"
                + "NOW(),\n"
                + "" + animal.getId_usuario() + ",'Activo');"
        //</editor-fold>
        );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="actualizarElRegistroDeLaMadre">
        if (!me.getActualizarRegistroMadre().isEmpty()) {
            consultas.add(me.getActualizarRegistroMadre());
        }
        //</editor-fold>

        return EjecutarConsultas(consultas);
    }

    private int EjecutarConsultas(ArrayList<String> consultas) {
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

    public Map<String, String> GetDatosVenta(String id_Animal) {
        try {
            String consulta = "SELECT venta as VENTA, numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL OR numero_mama_adoptiva = '',numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "fecha_venta AS FECHA_VENTA, tipo_venta AS TIPO_VENTA, \n"
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
        ModeloRAnimales animal = (ModeloRAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE ranimales SET\n"
                + "venta = '1',\n"
                + "peso = " + animal.getPeso() + ",\n"
                + "precio_venta = " + animal.getPrecio_venta() + ",\n"
                + "peso_canal = " + animal.getPeso_canal() + ",\n"
                + "fecha_venta = '" + animal.getFecha_venta() + "',\n"
                + "tipo_venta = " + Utilidades.CampoNULL(animal.getTipo_venta()) + "\n"
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
        ModeloRAnimales animal = (ModeloRAnimales) _animal;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE ranimales SET\n"
                + "muerte = '1',\n"
                + "fecha_muerte = '" + animal.getFecha_muerte() + "',\n"
                + "descripcion_muerte = '" + animal.getDescripcion_muerte() + "'\n"
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
            System.out.println("------------GetDatosMuerte------------");
            String consulta = "SELECT muerte AS MUERTE, numero AS NUMERO_ANIMAL, IF(numero_mama_adoptiva IS NULL OR numero_mama_adoptiva = '',numero_mama, numero_mama_adoptiva) AS NUMERO_MAMA, \n"
                    + "fecha_muerte AS FECHA_MUERTE, descripcion_muerte AS MOTIVO \n"
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
            if (Orden.isEmpty()) {
                Orden = "CONVERT(a.numero,DOUBLE) ASC";
            }
            String consulta = "SELECT\n"
                    + "a.id AS ID_ANIMAL,\n"
                    + "a.numero AS NUMERO_ANIMAL,\n"
                    + "IF(a.`numero_mama_adoptiva` IS NULL, IF(a.`numero_mama` IS NULL, '',a.`numero_mama`), a.`numero_mama_adoptiva`) AS NUMERO_MAMA,\n"
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
                    + "ORDER BY " + Orden;
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

            String consulta = "SELECT anim.id AS IDANIMAL, anim.numero AS NUMERO_ANIMAL, IFNULL(anim.numero_mama,'') AS NUMERO_MAMA,\n"
                    + "IFNULL(DATE_FORMAT(anim.fecha_novilla, '%d/%m/%Y'), '') FECHA_NOVILLA, anim.peso as PESO,\n"
                    + "IFNULL(anim.numero_descendiente,'') NUMERO_HIJOS,\n"
                    + "IFNULL(anim.cantidad_parto,'') NUMERO_PARTOS,\n"
                    + "IFNULL(CONCAT(UPPER(SUBSTRING(tbl.DIAG, 1, 1)), SUBSTRING(tbl.DIAG, 2)), '') ESTADO, IFNULL(tbl.FPALP, '') FECHA_PALP, IFNULL(tbl.IDPALP, '') IDPALPACION,\n"
                    + "CASE WHEN anim.es_madre='Si'\n"
                    + "THEN (SELECT IFNULL(DATE_FORMAT(MAX(a.fecha_nacimiento), '%d/%m/%Y'), '') FROM ranimales a WHERE a.numero_mama=anim.numero)\n"
                    + "ELSE ''\n"
                    + "END FECHA_ULT_PARTO,\n"
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
                    + "	INNER JOIN ranimales anim ON anim.id = palp.id_animal\n"
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

    public int EnviarConsultas(ArrayList<String> consultas) {
        return EjecutarConsultas(consultas);
    }

}
