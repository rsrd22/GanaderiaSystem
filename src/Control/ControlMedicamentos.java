package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloMedicamentos;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlMedicamentos implements IControl {

    private gestorMySQL mySQL;
    private final ArrayList<ModeloMedicamentos> LISTA_VACIA = new ArrayList<ModeloMedicamentos>();

    public ControlMedicamentos() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "select a.* "
                + "from "
                + "medicamentos a "
                + "order by a.descripcion asc";
        List<Map<String, String>> medicamentos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentos> lista = new ArrayList<>();
        medicamentos = mySQL.ListSQL(consulta);

        if (medicamentos.size() > 0) {

            for (Map<String, String> medicamento : medicamentos) {
                lista.add(new ModeloMedicamentos(
                        medicamento.get("descripcion"),
                        medicamento.get("estado"),
                        medicamento.get("fecha"),
                        medicamento.get("id"),
                        medicamento.get("id_usuario"),
                        medicamento.get("unidad_medida")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "select a.* "
                + "from "
                + "medicamentos a "
                + "WHERE a.id=" + id;
        List<Map<String, String>> medicamentos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentos> lista = new ArrayList<>();
        medicamentos = mySQL.ListSQL(consulta);

        if (medicamentos.size() > 0) {

            for (Map<String, String> medicamento : medicamentos) {
                lista.add(new ModeloMedicamentos(
                        medicamento.get("descripcion"),
                        medicamento.get("estado"),
                        medicamento.get("fecha"),
                        medicamento.get("id"),
                        medicamento.get("id_usuario"),
                        medicamento.get("unidad_medida")
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
        ModeloMedicamentos medicamento = (ModeloMedicamentos) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO medicamentos(id,descripcion,unidad_medida,estado,fecha,id_usuario) values (\n"
                + "0,\n"
                + "'" + medicamento.getDescripcion() + "',\n"
                + "'" + medicamento.getUnidadMedida() + "',\n"
                + "'" + medicamento.getEstado() + "',\n"
                + "" + medicamento.getFecha() + ",\n"
                + "" + medicamento.getIdUsuario() + ""
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
        ModeloMedicamentos medicamento = (ModeloMedicamentos) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE medicamentos\n"
                + "SET\n"
                + "descripcion='"+medicamento.getDescripcion()+"',\n"
                + "unidad_medida='"+medicamento.getUnidadMedida()+"',\n"
                + "estado='"+medicamento.getEstado()+"',\n"
                + "fecha="+medicamento.getFecha()+",\n"
                + "id_usuario="+medicamento.getIdUsuario()+"\n"
                + "WHERE id=" + medicamento.getId()
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
        ModeloMedicamentos medicamento = (ModeloMedicamentos) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM medicamentos\n"
                + "WHERE id = " + medicamento.getId()
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
        String consulta = "select a.* "
                + "from "
                + "medicamentos a "
                + "WHERE a.descripcion='" + descripcion + "'";
        List<Map<String, String>> medicamentos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentos> lista = new ArrayList<>();
        medicamentos = mySQL.ListSQL(consulta);

        if (medicamentos.size() > 0) {

            for (Map<String, String> medicamento : medicamentos) {
                lista.add(new ModeloMedicamentos(
                        medicamento.get("descripcion"),
                        medicamento.get("estado"),
                        medicamento.get("fecha"),
                        medicamento.get("id"),
                        medicamento.get("id_usuario"),
                        medicamento.get("unidad_medida")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

}
