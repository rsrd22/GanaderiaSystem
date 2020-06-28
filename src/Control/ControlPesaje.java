/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloPesaje;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlPesaje implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlPesaje() {
        mySQL = new gestorMySQL();
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
    public int Guardar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPesaje modelo = (ModeloPesaje) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "" + modelo.getId() + ",\n"
                + "" + modelo.getId_animal() + ",\n"
                + "" + modelo.getFecha_pesado() + ",\n"
                + "" + modelo.getPeso() + ",\n"
                + "'" + modelo.getNotas() + "',\n"
                + "'" + modelo.getHierro() + "',\n"
                + "'" + modelo.getDescornado() + "',\n"
                + "'" + modelo.getImplante() + "',\n"
                + "'" + modelo.getDestete() + "',\n"
                + "" + modelo.getFecha() + ",\n"
                + "" + modelo.getId_usuario() + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJEPORMEDICAMENTOS">
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
                    + "0,\n"
                    + "(SELECT id FROM pesaje WHERE id_animal = " + modelo.getId_animal() + " AND DATE_FORMAT(`fecha`,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n"
                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                    + ")"
            //</editor-fold>
            );
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update animales\n"
                + "set \n"
                + "peso = " + modelo.getPeso() + ",\n"
                + "hierro_fisico = '" + modelo.getHierro() + "',\n"
                + "implante = '" + modelo.getImplante() + "',\n"
                + "descornado = '" + modelo.getDescornado() + "',\n"
                + "fecha_destete = '" + modelo.getFechaDestete()+ "',\n"
                + "hierro = " + modelo.getIdHierro()+ "\n"
                + "where id = " + modelo.getId_animal() + "");
//</editor-fold>

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
        String consulta = "SELECT * FROM pesaje\n"
                + " WHERE id_animal=" + o.toString() + "";
        List<Map<String, String>> pesajes = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPesaje> lista = new ArrayList<>();
        pesajes = mySQL.ListSQL(consulta);

        if (pesajes.size() > 0) {

            for (Map<String, String> pesaje : pesajes) {
                lista.add(new ModeloPesaje(
                        pesaje.get("descornado"),
                        pesaje.get("destete"),
                        pesaje.get("fecha"),
                        pesaje.get("fecha_pesado"),
                        pesaje.get("hierro"),
                        pesaje.get("id"),
                        pesaje.get("id_animal"),
                        pesaje.get("id_usuario"),
                        pesaje.get("implante"),
                        pesaje.get("notas"),
                        pesaje.get("peso"),
                        "","",""
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public int GuardarPesajeDescarte(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPesaje modelo = (ModeloPesaje) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "" + modelo.getId_animal() + ",\n"
                + "" + modelo.getFecha_pesado() + ",\n"
                + "" + modelo.getPeso() + ",\n"
                + "'" + modelo.getNotas() + "',\n"
                + "'" + modelo.getHierro() + "',\n"
                + "'" + modelo.getDescornado() + "',\n"
                + "'" + modelo.getImplante() + "',\n"
                + "'" + modelo.getDestete() + "',\n"
                + "" + modelo.getFecha() + ",\n"
                + "" + modelo.getId_usuario() + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>
//
//        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJEPORMEDICAMENTOS">
//        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
//            consultas.add(
//                    //<editor-fold defaultstate="collapsed" desc="INSERT">
//                    "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
//                    + "0,\n"
//                    + "(SELECT id FROM pesaje WHERE id_animal = " + modelo.getId_animal() + " AND DATE_FORMAT(`fecha`,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n"
//                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
//                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
//                    + ")"
//            //</editor-fold>
//            );
//        }
////</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update animales\n"
                + "set \n"
                + "peso = " + modelo.getPeso() + "\n"
                + "where id = " + modelo.getId_animal() + "");
//</editor-fold>

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
