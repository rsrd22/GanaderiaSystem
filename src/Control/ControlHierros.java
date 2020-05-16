/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlHierros implements IControl {

    private gestorMySQL mySQL;
    private ArrayList<ModeloPropietarios> listaModelo;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlHierros() {
        mySQL = new gestorMySQL();
        listaModelo = new ArrayList<>();
    }

    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Guardar(Object _hierro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloHierros hierro = (ModeloHierros) _hierro;

        consultas.add(
                //                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `propietarioxhierro`\n"
                + "(`id`, `id_propietario`, `descripcion`, `nombre_imagen`,\n"
                + "`imagen`, `estado`, `fecha`, `id_usuario`) VALUES \n"
                + "(" + hierro.getId() + ",\n"
                + "        " + hierro.getId_propietario() + ",\n"
                + "        '" + hierro.getDescripcion() + "',\n"
                + "        '" + hierro.getNombre_imagen() + "',\n"
                + "        '" + hierro.getImagen() + "',\n"
                + "        '" + hierro.getEstado() + "',\n"
                + "        " + hierro.getFecha() + ",\n"
                + "        " + hierro.getId_usuario() + ");"
        //        //</editor-fold>
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
    public int Actualizar(Object _hierro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloHierros hierro = (ModeloHierros) _hierro;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `propietarioxhierro`\n"
                + "SET `descripcion` = '" + hierro.getDescripcion() + "',\n"
                + "  `nombre_imagen` = '" + hierro.getNombre_imagen() + "',\n"
                + "  `imagen` = '" + hierro.getImagen() + "',\n"
                + "  `estado` = '" + hierro.getEstado() + "',\n"
                + "  `fecha` = " + hierro.getFecha() + ",\n"
                + "  `id_usuario` = " + hierro.getId_usuario() + "'\n"
                + "WHERE `id` = " + hierro.getId() + ";" 
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
    public int Eliminar(Object _hierro) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloHierros hierro = (ModeloHierros) _hierro;

        consultas.add(
                //                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM `propietarioxhierro`\n"
                + "WHERE `id` = " + hierro.getId() + ";"
        //        //</editor-fold>
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
