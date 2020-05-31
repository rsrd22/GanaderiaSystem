/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloCronograma;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlCronograma implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlCronograma() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*, '' color FROM cronograma a";
        List<Map<String, String>> cronograma = new ArrayList<Map<String, String>>();
        ArrayList<ModeloCronograma> lista = new ArrayList<>();
        cronograma = mySQL.ListSQL(consulta);

        if (cronograma.size() > 0) {

            for (Map<String, String> actividad : cronograma) {
                lista.add(new ModeloCronograma(
                        actividad.get("anio"),
                        actividad.get("fecha"),
                        actividad.get("id"),
                        actividad.get("id_actividad"),
                        actividad.get("id_estado"),
                        actividad.get("id_finca"),
                        actividad.get("id_usuario"),
                        actividad.get("mes"),
                        actividad.get("semana"),
                        actividad.get("color")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.*,b.color color FROM cronograma a LEFT JOIN estado_actividad b ON a.id_estado=b.id WHERE a.id_finca=" + ID;
        List<Map<String, String>> cronograma = new ArrayList<Map<String, String>>();
        ArrayList<ModeloCronograma> lista = new ArrayList<>();
        cronograma = mySQL.ListSQL(consulta);

        if (cronograma.size() > 0) {

            for (Map<String, String> actividad : cronograma) {
                lista.add(new ModeloCronograma(
                        actividad.get("anio"),
                        actividad.get("fecha"),
                        actividad.get("id"),
                        actividad.get("id_actividad"),
                        actividad.get("id_estado"),
                        actividad.get("id_finca"),
                        actividad.get("id_usuario"),
                        actividad.get("mes"),
                        actividad.get("semana"),
                        actividad.get("color")
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
        ArrayList<ModeloCronograma> listModelo = new ArrayList<>();
        listModelo = (ArrayList<ModeloCronograma>) o;

        if (listModelo.size() > 0) {
            consultas.add("DELETE FROM cronograma WHERE id_finca=" + listModelo.get(0).getIdFinca());
            for (ModeloCronograma modelo : listModelo) {
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="INSERT">
                        "insert into cronograma(id,id_finca,id_actividad,anio,mes,semana,id_estado,id_usuario,fecha)\n"
                        + "values (\n"
                        + "" + modelo.getId() + ",\n"
                        + "" + modelo.getIdFinca() + ",\n"
                        + "" + modelo.getIdActividad() + ",\n"
                        + "" + modelo.getAnio() + ",\n"
                        + "" + modelo.getMes() + ",\n"
                        + "" + modelo.getSemana() + ",\n"
                        + "" + modelo.getIdEstado() + ",\n"
                        + "" + modelo.getIdUsuario() + ",\n"
                        + "" + modelo.getFecha() + "\n"
                        + ")\n"
                        + "ON DUPLICATE KEY UPDATE\n"
                        + "id_actividad=" + modelo.getIdFinca() + ",\n"
                        + "anio=" + modelo.getAnio() + ",\n"
                        + "mes=" + modelo.getMes() + ",\n"
                        + "semana=" + modelo.getSemana() + ",\n"
                        + "id_estado=" + modelo.getIdEstado() + ",\n"
                        + "id_usuario=" + modelo.getIdUsuario() + ",\n"
                        + "fecha=" + modelo.getFecha() + ""
                //</editor-fold>
                );
            }

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
        } else {
            return Retorno.ERROR;
        }
    }

    @Override
    public int Actualizar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloCronograma modelo = (ModeloCronograma) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE grupos\n"
                + "SET\n"
                + "id = " + modelo.getId() + "\n"
                + "id_finca = " + modelo.getIdFinca() + "\n"
                + "id_actividad = " + modelo.getIdActividad() + "\n"
                + "anio = " + modelo.getAnio() + "\n"
                + "mes = " + modelo.getMes() + "\n"
                + "semana = " + modelo.getSemana() + "\n"
                + "id_estado = " + modelo.getIdEstado() + "\n"
                + "id_usuario = " + modelo.getIdUsuario() + "\n"
                + "fecha = " + modelo.getFecha() + "\n"
                + "WHERE \n"
                + "id = " + modelo.getId()
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
        ModeloCronograma modelo = (ModeloCronograma) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM cronograma\n"
                + "WHERE id = " + modelo.getId()
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
        return LISTA_VACIA;
    }

}
