/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Usuario;

import BaseDeDatos.gestorMySQL;
import Control.IControl;
import Control.Retorno;
import Modelo.Usuario.modeloPerfiles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlPerfiles implements IControl {

    private gestorMySQL mySQL;
    private final Object LISTA_VACIA = new Object();

    public ControlPerfiles() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.* FROM perfiles a ORDER BY a.id ASC";
        List<Map<String, String>> perfiles = new ArrayList<Map<String, String>>();
        ArrayList<modeloPerfiles> lista = new ArrayList<>();
        perfiles = mySQL.ListSQL(consulta);

        if (perfiles.size() > 0) {
            for (Map<String, String> perfil : perfiles) {
                lista.add(new modeloPerfiles(
                        perfil.get("descripcion"),
                        perfil.get("id")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.* FROM perfiles a WHERE a.id=" + ID + " ORDER BY a.id ASC";
        List<Map<String, String>> perfiles = new ArrayList<Map<String, String>>();
        ArrayList<modeloPerfiles> lista = new ArrayList<>();
        perfiles = mySQL.ListSQL(consulta);

        if (perfiles.size() > 0) {
            for (Map<String, String> perfil : perfiles) {
                lista.add(new modeloPerfiles(
                        perfil.get("descripcion"),
                        perfil.get("id")
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
        modeloPerfiles perfil = (modeloPerfiles) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO perfiles (id,descripcion)\n"
                + "VALUES (\n"
                + "" + perfil.getId() + ",\n"
                + "'" + perfil.getDescripcion() + "'\n"
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
        modeloPerfiles perfil = (modeloPerfiles) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE perfiles \n"
                + "SET descripcion='" + perfil.getDescripcion() + "'\n"
                + "WHERE id=" + perfil.getId()
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
        modeloPerfiles perfil = (modeloPerfiles) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM perfiles WHERE id=" + perfil.getId()
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
