/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control._Animales;

import BaseDeDatos.gestorMySQL;
import Control.ControlTraslado;
import Control.IControl;
import Control.Retorno;
import Modelo.ModeloMuertesVentasHistoricos;
import Modelo.ModeloTraslado;
import Modelo._Animales.*;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author DOLFHANDLER
 */
public class Control_Animales implements IControl {

    private gestorMySQL mySQL;
    private ControlTraslado control;
    private final ArrayList<Modelo_AnimalesSalida> LISTA_VACIA = new ArrayList<Modelo_AnimalesSalida>();

    public Control_Animales() {
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
                + "FROM _animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "ORDER BY a.numero ASC";
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<Modelo_AnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new Modelo_AnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
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
                        animal.get("numero_mama_adoptiva"),
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
                + "FROM _animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE a.id=" + ID;
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<Modelo_AnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new Modelo_AnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
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
                        animal.get("numero_mama_adoptiva"),
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
    public int Guardar(Object objeto) {
        ArrayList<String> consultas = new ArrayList<>();
        Modelo_AnimalesEntrada _objeto = (Modelo_AnimalesEntrada) objeto;
        Modelo_Animales animal = _objeto.getAnimal();
        Modelo_AnimalesDescendientes descendiente = _objeto.getDescendiente();
        ModeloTraslado traslado = _objeto.getTraslado();

        if (animal.getVenta().equals("1")) {
            //<editor-fold defaultstate="collapsed" desc="VERIFICAR SI HAY REGISTROS ANULADOS ACTIVOS">
            ModeloMuertesVentasHistoricos modelAnul = new ModeloMuertesVentasHistoricos(
                    "", "venta", animal.getId(),
                    "", "", "", "");
            if (mySQL.ExistenDatos("select * from anularventasymuertes where tipo = '" + modelAnul.getTipo() + "' "
                    + "and id_animal = '" + modelAnul.getIdAnimal() + "' and estado = 'Activo'")) {
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
                    + "and id_animal = '" + modelAnul.getIdAnimal() + "' and estado = 'Activo'")) {
                consultas.add("UPDATE `anularventasymuertes`\n"
                        + "SET `estado` = 'Inactivo'\n"
                        + "WHERE `tipo` = '" + modelAnul.getTipo() + "' AND `id_animal` = '" + modelAnul.getIdAnimal() + "' AND estado = 'Activo'");
            }
//</editor-fold>
        }

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDelAnimal">
        consultas.add("insert into `ganadero`.`_animales`\n"
                + "            (`id`,\n"
                + "             `id_tipo_animal`,\n"
                + "             `hierro`,\n"
                + "             `numero`,\n"
                + "             `es_madre`,\n"
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
                + "values (" + animal.getId() + ",\n"
                + "" + animal.getId_tipo_animal() + ",\n"
                + "" + animal.getHierro() + ",\n"
                + "'" + animal.getNumero() + "',\n"
                + "'" + animal.getEs_madre() + "',\n"
                + "'" + animal.getNumero_mama_adoptiva() + "',\n"
                + "" + animal.getPeso() + ",\n"
                + "'" + animal.getGenero() + "',\n"
                + "" + animal.getGrupo() + ",\n"
                + "'" + animal.getCalificacion() + "',\n"
                + "'" + animal.getNotas() + "',\n"
                + "'" + animal.getFecha_destete() + "',\n"
                + "'" + animal.getCapado() + "',\n"
                + "'" + animal.getFecha_nacimiento() + "',\n"
                + "'" + animal.getMuerte() + "',\n"
                + "" + animal.getPeso_destete() + ",\n"
                + "'" + animal.getDestete() + "',\n"
                + "'" + animal.getFecha_muerte() + "',\n"
                + "'" + animal.getDescripcion_muerte() + "',\n"
                + "'" + animal.getVenta() + "',\n"
                + "'" + animal.getFecha_venta() + "',\n"
                + "'" + animal.getTipo_venta() + "',\n"
                + "" + animal.getPrecio_venta() + ",\n"
                + "" + animal.getPeso_canal() + ",\n"
                + "'" + animal.getFecha_novilla() + "',\n"
                + "'" + animal.getHierro_fisico() + "',\n"
                + "'" + animal.getImplante() + "',\n"
                + "'" + animal.getDescornado() + "',\n"
                + "'" + animal.getFecha() + "',\n"
                + "" + animal.getId_usuario() + ");");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="guardarDatosDeLaGenealogiaDelAnimal">
        consultas.add("INSERT INTO `_animales_descendientes`\n"
                + "            (`id`,\n"
                + "             `id_animal`,\n"
                + "             `id_madre`,\n"
                + "             `nro_descendiente`,\n"
                + "             `nro_parto`)\n"
                + "VALUES ("+descendiente.getId()+",\n"
                + "        "+descendiente.getId_animal()+",\n"
                + "        "+descendiente.getId_madre()+",\n"
                + "        "+descendiente.getNro_descendiente()+",\n"
                + "        "+descendiente.getNro_parto()+");");
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
                + "" + traslado.getFecha() + ",\n"
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

        return EjecutarConsultas(consultas);
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
}
