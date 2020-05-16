/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloTipoAnimales;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlTipoAnimales implements IControl {

    private gestorMySQL mySQL;
    private ArrayList<ModeloTipoAnimales> lista;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlTipoAnimales() {
        mySQL = new gestorMySQL();
        lista = new ArrayList<>();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*,b.descripcion descFinca FROM tipo_animales a \n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id";
        List<Map<String, String>> tipoAnimales = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTipoAnimales> lista = new ArrayList<>();
        tipoAnimales = mySQL.ListSQL(consulta);

        if (tipoAnimales.size() > 0) {

            for (Map<String, String> grupo : tipoAnimales) {
                lista.add(new ModeloTipoAnimales(
                        grupo.get("id"),
                        grupo.get("id_finca"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "SELECT a.*,b.descripcion descFinca FROM tipo_animales a \n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id WHERE a.id=" + id;
        List<Map<String, String>> tipoAnimales = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTipoAnimales> lista = new ArrayList<>();
        tipoAnimales = mySQL.ListSQL(consulta);

        if (tipoAnimales.size() > 0) {

            for (Map<String, String> grupo : tipoAnimales) {
                lista.add(new ModeloTipoAnimales(
                        grupo.get("id"),
                        grupo.get("id_finca"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _tipoAnimales) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTipoAnimales tipoAnimales = (ModeloTipoAnimales) _tipoAnimales;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO tipo_animales(id,id_finca,descripcion,estado,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "" + tipoAnimales.getIdFinca() + ",\n"
                + "'" + tipoAnimales.getDescripcion() + "',\n"
                + "'" + tipoAnimales.getEstado() + "',\n"
                + "" + tipoAnimales.getFecha() + ",\n"
                + "" + tipoAnimales.getIdUsuario() + "\n"
                + ")"
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
    public int Actualizar(Object _tipoAnimales) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTipoAnimales tipoAnimales = (ModeloTipoAnimales) _tipoAnimales;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE tipo_animales\n"
                + "SET\n"
                + "id_finca = " + tipoAnimales.getIdFinca() + ",\n"
                + "descripcion = '" + tipoAnimales.getDescripcion() + "',\n"
                + "estado = '" + tipoAnimales.getEstado() + "',\n"
                + "fecha = " + tipoAnimales.getFecha() + ",\n"
                + "id_usuario = " + tipoAnimales.getIdUsuario() + "\n"
                + "WHERE \n"
                + "id = " + tipoAnimales.getId()
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
    public int Eliminar(Object _tipoAnimales) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTipoAnimales tipoAnimales = (ModeloTipoAnimales) _tipoAnimales;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM tipo_animales\n"
                + "WHERE id = " + tipoAnimales.getId()
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

    public Object ObtenerDatosFiltro(Object descripcion) {
        String consulta = "SELECT a.*,b.descripcion descFinca FROM tipo_animales a \n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id WHERE a.descripcion='" + descripcion + "'";
        List<Map<String, String>> tipoAnimales = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTipoAnimales> lista = new ArrayList<>();
        tipoAnimales = mySQL.ListSQL(consulta);

        if (tipoAnimales.size() > 0) {

            for (Map<String, String> grupo : tipoAnimales) {
                lista.add(new ModeloTipoAnimales(
                        grupo.get("id"),
                        grupo.get("id_finca"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }
}
