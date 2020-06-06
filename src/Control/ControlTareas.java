package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloGrupos;
import Modelo.ModeloTarea;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlTareas implements IControl {

    private gestorMySQL mySQL;
    private final ArrayList<ModeloTarea> LISTA_VACIA = new ArrayList<ModeloTarea>();

    public ControlTareas() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "select a.*, b.descripcion descActividad "
                + "from "
                + "tareas a "
                + "left join actividades b on a.id_actividad=b.id";
        List<Map<String, String>> tareas = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTarea> lista = new ArrayList<>();
        tareas = mySQL.ListSQL(consulta);

        if (tareas.size() > 0) {

            for (Map<String, String> tarea : tareas) {
                lista.add(new ModeloTarea(
                        tarea.get("descripcion"),
                        tarea.get("estado"),
                        tarea.get("fecha_registro"),
                        tarea.get("id"),
                        tarea.get("id_actividad"),
                        tarea.get("usuario"),
                        tarea.get("descActividad")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "select a.*, b.descripcion descActividad "
                + "from "
                + "tareas a "
                + "left join actividades b on a.id_actividad=b.id WHERE a.id=" + id;
        List<Map<String, String>> tareas = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTarea> lista = new ArrayList<>();
        tareas = mySQL.ListSQL(consulta);

        if (tareas.size() > 0) {

            for (Map<String, String> tarea : tareas) {
                lista.add(new ModeloTarea(
                        tarea.get("descripcion"),
                        tarea.get("estado"),
                        tarea.get("fecha_registro"),
                        tarea.get("id"),
                        tarea.get("id_actividad"),
                        tarea.get("usuario"),
                        tarea.get("descActividad")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTarea tarea = (ModeloTarea) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "insert into tareas(id,id_actividad,descripcion,estado,fecha_registro,usuario) values (\n"
                + "0,\n"
                + "" + tarea.getIdActividad() + ",\n"
                + "'" + tarea.getDescripcion() + "',\n"
                + "'" + tarea.getEstado() + "',\n"
                + "" + tarea.getFechaRegistro() + ",\n"
                + "" + tarea.getUsuario() + "\n"
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
    public int Actualizar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTarea tarea = (ModeloTarea) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE tareas\n"
                + "SET\n"
                + "id_actividad=" + tarea.getIdActividad() + ",\n"
                + "descripcion='" + tarea.getDescripcion() + "',\n"
                + "estado='" + tarea.getEstado() + "',\n"
                + "fecha_registro=" + tarea.getFechaRegistro() + ",\n"
                + "usuario=" + tarea.getUsuario() + "\n"
                + "WHERE id=" + tarea.getId()
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
    public int Eliminar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTarea tarea = (ModeloTarea) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM tareas\n"
                + "WHERE id = " + tarea.getId()
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
    public Object ObtenerDatosFiltro(Object descripcion) {
        String consulta = "select a.*, b.descripcion descActividad "
                + "from "
                + "tareas a "
                + "left join actividades b on a.id_actividad=b.id WHERE a.descripcion='" + descripcion + "'";
        List<Map<String, String>> tareas = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTarea> lista = new ArrayList<>();
        tareas = mySQL.ListSQL(consulta);

        if (tareas.size() > 0) {

            for (Map<String, String> tarea : tareas) {
                lista.add(new ModeloTarea(
                        tarea.get("descripcion"),
                        tarea.get("estado"),
                        tarea.get("fecha_registro"),
                        tarea.get("id"),
                        tarea.get("id_actividad"),
                        tarea.get("usuario"),
                        tarea.get("descActividad")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

}
