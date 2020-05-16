/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloActividades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlActividades implements IControl {
    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlActividades() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.* FROM actividades a ORDER BY a.descripcion ASC";
        List<Map<String, String>> actividades = new ArrayList<Map<String, String>>();
        ArrayList<ModeloActividades> lista = new ArrayList<>();
        actividades = mySQL.ListSQL(consulta);

        if (actividades.size() > 0) {

            for (Map<String, String> actividad : actividades) {
                lista.add(new ModeloActividades(
                    actividad.get("id"),
                    actividad.get("descripcion"),
                    actividad.get("estado"),
                    actividad.get("fecha_registro"),
                    actividad.get("usuario")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.* FROM actividades a WHERE a.id=" + ID;
        List<Map<String, String>> actividades = new ArrayList<Map<String, String>>();
        ArrayList<ModeloActividades> lista = new ArrayList<>();
        actividades = mySQL.ListSQL(consulta);

        if (actividades.size() > 0) {

            for (Map<String, String> actividad : actividades) {
                lista.add(new ModeloActividades(
                        actividad.get("id"),
                        actividad.get("descripcion"),
                        actividad.get("estado"),
                        actividad.get("fecha_registro"),
                        actividad.get("usuario")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _actividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloActividades actividad = (ModeloActividades) _actividad;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "insert into actividades (id,descripcion,estado,fecha_registro,usuario)\n"
                + "values (\n"
                + "0,\n"
                + "'" + actividad.getDescripcion() + "',\n"
                + "'" + actividad.getEstado() + "',\n"
                + "" + actividad.getFechaRegistro() + ",\n"
                + "" + actividad.getUsuario() + "\n"
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
    public int Actualizar(Object _actividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloActividades actividad = (ModeloActividades) _actividad;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE actividades\n"
                + "SET\n"
                + "descripcion = '" + actividad.getDescripcion() + "',\n"
                + "estado = '" + actividad.getEstado() + "',\n"
                + "fecha_registro = " + actividad.getFechaRegistro() + ",\n"
                + "usuario = " + actividad.getUsuario() + "\n"
                + "WHERE id = " + actividad.getId()
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
    public int Eliminar(Object _actividad) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloActividades actividad = (ModeloActividades) _actividad;
        
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM actividades WHERE id = " + actividad.getId()
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
