/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloMacroGrupo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlMacroGrupo implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlMacroGrupo() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*,b.descripcion descripcionFinca FROM macrogrupos a\n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id";
        List<Map<String, String>> macrogrupo = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMacroGrupo> lista = new ArrayList<>();
        macrogrupo = mySQL.ListSQL(consulta);

        if (macrogrupo.size() > 0) {

            for (Map<String, String> grupo : macrogrupo) {
                lista.add(new ModeloMacroGrupo(
                        grupo.get("id"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("id_finca"),
                        grupo.get("descripcionFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "SELECT a.*,b.descripcion descripcionFinca FROM macrogrupos a\n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id WHERE a.id=" + id;
        List<Map<String, String>> macrogrupo = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMacroGrupo> lista = new ArrayList<>();
        macrogrupo = mySQL.ListSQL(consulta);

        if (macrogrupo.size() > 0) {

            for (Map<String, String> grupo : macrogrupo) {
                lista.add(new ModeloMacroGrupo(
                        grupo.get("id"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("id_finca"),
                        grupo.get("descripcionFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _macrogrupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloMacroGrupo macrogrupo = (ModeloMacroGrupo) _macrogrupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO macrogrupos(id,descripcion,estado,fecha,id_usuario,id_finca) VALUES(\n"
                + "0,\n"
                + "'" + macrogrupo.getDescripcion() + "',\n"
                + "'" + macrogrupo.getEstado() + "',\n"
                + "" + macrogrupo.getFecha() + ",\n"
                + "" + macrogrupo.getIdUsuario() + ",\n"
                + "" + macrogrupo.getIdFinca()+ "\n"
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
    public int Actualizar(Object _macrogrupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloMacroGrupo macrogrupo = (ModeloMacroGrupo) _macrogrupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE macrogrupos\n"
                + "SET\n"
                + "descripcion = '" + macrogrupo.getDescripcion() + "',\n"
                + "estado = '" + macrogrupo.getEstado() + "',\n"
                + "fecha = " + macrogrupo.getFecha() + ",\n"
                + "id_finca = " + macrogrupo.getIdFinca()+ ",\n"
                + "id_usuario = " + macrogrupo.getIdUsuario() + "\n"
                + "WHERE \n"
                + "id = " + macrogrupo.getId()
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
    public int Eliminar(Object _macrogrupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloMacroGrupo macrogrupo = (ModeloMacroGrupo) _macrogrupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM macrogrupos\n"
                + "WHERE id = " + macrogrupo.getId()
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
        String consulta = "SELECT a.*,b.descripcion descripcionFinca FROM macrogrupos a\n"
                + "LEFT JOIN fincas b ON a.id_finca=b.id WHERE a.descripcion='" + descripcion + "'";
        List<Map<String, String>> macrogrupo = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMacroGrupo> lista = new ArrayList<>();
        macrogrupo = mySQL.ListSQL(consulta);

        if (macrogrupo.size() > 0) {

            for (Map<String, String> grupo : macrogrupo) {
                lista.add(new ModeloMacroGrupo(
                        grupo.get("id"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("id_finca"),
                        grupo.get("descripcionFinca")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

}
