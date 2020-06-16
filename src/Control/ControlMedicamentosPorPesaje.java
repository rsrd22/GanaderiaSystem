/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloMedicamentosPorPesaje;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlMedicamentosPorPesaje implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlMedicamentosPorPesaje() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.* FROM pesajexmedicamento a";
        List<Map<String, String>> select = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentosPorPesaje> lista = new ArrayList<>();
        select = mySQL.ListSQL(consulta);

        if (select.size() > 0) {

            for (Map<String, String> elemento : select) {
                lista.add(new ModeloMedicamentosPorPesaje(
                        elemento.get("dosis"),
                        elemento.get("id"),
                        elemento.get("id_medicamento"),
                        elemento.get("id_pesaje")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.* FROM pesajexmedicamento a WHERE a.id=" + ID;
        List<Map<String, String>> select = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentosPorPesaje> lista = new ArrayList<>();
        select = mySQL.ListSQL(consulta);

        if (select.size() > 0) {

            for (Map<String, String> elemento : select) {
                lista.add(new ModeloMedicamentosPorPesaje(
                        elemento.get("dosis"),
                        elemento.get("id"),
                        elemento.get("id_medicamento"),
                        elemento.get("id_pesaje")
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
        ModeloMedicamentosPorPesaje elemento = (ModeloMedicamentosPorPesaje) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
                + "0,\n"
                + "" + elemento.getId_pesaje() + ",\n"
                + "" + elemento.getId_medicamento() + ",\n"
                + "" + elemento.getDosis() + ",\n"
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

}
