/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloEstadoActividades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlEstadoActividades implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlEstadoActividades() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.* FROM estado_actividad a ORDER BY a.descripcion ASC";
        List<Map<String, String>> estados = new ArrayList<Map<String, String>>();
        ArrayList<ModeloEstadoActividades> lista = new ArrayList<>();
        estados = mySQL.ListSQL(consulta);

        if (estados.size() > 0) {

            for (Map<String, String> estado : estados) {
                lista.add(new ModeloEstadoActividades(
                        estado.get("id"),
                        estado.get("descripcion"),
                        estado.get("estado"),
                        estado.get("fecha_registro"),
                        estado.get("usuario"),
                        estado.get("color")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.* FROM estado_actividad a WHERE a.id=" + ID;
        List<Map<String, String>> estados = new ArrayList<Map<String, String>>();
        ArrayList<ModeloEstadoActividades> lista = new ArrayList<>();
        estados = mySQL.ListSQL(consulta);

        if (estados.size() > 0) {

            for (Map<String, String> estado : estados) {
                lista.add(new ModeloEstadoActividades(
                        estado.get("id"),
                        estado.get("descripcion"),
                        estado.get("estado"),
                        estado.get("fecha_registro"),
                        estado.get("usuario"),
                        estado.get("color")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _estadoActividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloEstadoActividades estadoAct = (ModeloEstadoActividades) _estadoActividad;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "insert into estado_actividad(id,descripcion,estado,fecha_registro,usuario,color)\n"
                + "values (\n"
                + "0,\n"
                + "'" + estadoAct.getDescripcion() + "',\n"
                + "'" + estadoAct.getEstado() + "',\n"
                + "" + estadoAct.getFechaRegistro() + ",\n"
                + "" + estadoAct.getUsuario() + ",\n"
                + "" + estadoAct.getColor()+ "\n"
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
    public int Actualizar(Object _estadoActividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloEstadoActividades estadoAct = (ModeloEstadoActividades) _estadoActividad;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE estado_actividad\n"
                + "SET\n"
                + "descripcion = '" + estadoAct.getDescripcion() + "',\n"
                + "estado = '" + estadoAct.getEstado() + "',\n"
                + "fecha_registro = " + estadoAct.getFechaRegistro() + ",\n"
                + "color = " + estadoAct.getColor()+ ",\n"
                + "usuario = " + estadoAct.getUsuario() + "\n"
                + "WHERE id = " + estadoAct.getId()
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
    public int Eliminar(Object _estadoActividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloEstadoActividades estadoAct = (ModeloEstadoActividades) _estadoActividad;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM estado_actividad WHERE id = " + estadoAct.getId()
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
    public Object ObtenerDatosFiltro(Object o) {
        return o;
    }

}
