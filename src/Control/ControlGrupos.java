package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloGrupos;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlGrupos implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlGrupos() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*, c.descripcion descTipoAnimales,b.descripcion descMacroGrupo,"
                + "d.id idFinca,d.descripcion desFinca FROM grupos a\n"
                + "LEFT JOIN macrogrupos b ON a.id_macrogrupo=b.id\n"
                + "LEFT JOIN tipo_animales c ON a.id_tipo_animal=c.id \n"
                + "LEFT JOIN fincas d ON c.id_finca=d.id ORDER BY a.descripcion ASC";
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloGrupos> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
                lista.add(new ModeloGrupos(
                        grupo.get("id"),
                        grupo.get("id_tipo_animal"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("id_macrogrupo"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descTipoAnimales"),
                        grupo.get("descMacroGrupo"),
                        grupo.get("idFinca"),
                        grupo.get("desFinca"),
                        grupo.get("pesable"),
                        grupo.get("palpable")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        String consulta = "SELECT a.*, c.descripcion descTipoAnimales,b.descripcion descMacroGrupo,"
                + "d.id idFinca,d.descripcion desFinca FROM grupos a\n"
                + "LEFT JOIN macrogrupos b ON a.id_macrogrupo=b.id\n"
                + "LEFT JOIN tipo_animales c ON a.id_tipo_animal=c.id \n"
                + "LEFT JOIN fincas d ON c.id_finca=d.id WHERE a.id=" + id;
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloGrupos> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
                lista.add(new ModeloGrupos(
                        grupo.get("id"),
                        grupo.get("id_tipo_animal"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("id_macrogrupo"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descTipoAnimales"),
                        grupo.get("descMacroGrupo"),
                        grupo.get("idFinca"),
                        grupo.get("desFinca"),
                        grupo.get("pesable"),
                        grupo.get("palpable")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object _grupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloGrupos grupo = (ModeloGrupos) _grupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO grupos(id,id_tipo_animal,descripcion,estado,id_macrogrupo,fecha,id_usuario,pesable,palpable) VALUES(\n"
                + "0,\n"
                + "" + grupo.getIdTipoAnimal() + ",\n"
                + "'" + grupo.getDescripcion() + "',\n"
                + "'" + grupo.getEstado() + "',\n"
                + "" + grupo.getIdMacrogrupo() + ",\n"
                + "" + grupo.getFecha() + ",\n"
                + "" + grupo.getIdUsuario() + ",\n"
                + "'" + grupo.getPesable() + "',\n"
                + "'" + grupo.getPalpable()+ "'\n"
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
    public int Actualizar(Object _grupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloGrupos grupo = (ModeloGrupos) _grupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE grupos\n"
                + "SET\n"
                + "id_tipo_animal = " + grupo.getIdTipoAnimal() + ",\n"
                + "descripcion = '" + grupo.getDescripcion() + "',\n"
                + "estado = '" + grupo.getEstado() + "',\n"
                + "id_macrogrupo = " + grupo.getIdMacrogrupo() + ",\n"
                + "fecha = " + grupo.getFecha() + ",\n"
                + "pesable = '" + grupo.getPesable()+ "',\n"
                + "palpable = '" + grupo.getPalpable()+ "',\n"
                + "id_usuario = " + grupo.getIdUsuario() + "\n"
                + "WHERE \n"
                + "id = " + grupo.getId()
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
    public int Eliminar(Object _grupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloGrupos grupo = (ModeloGrupos) _grupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM grupos\n"
                + "WHERE id = " + grupo.getId()
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
        String consulta = "SELECT a.*, c.descripcion descTipoAnimales,b.descripcion descMacroGrupo,"
                + "d.id idFinca,d.descripcion desFinca FROM grupos a\n"
                + "LEFT JOIN macrogrupos b ON a.id_macrogrupo=b.id\n"
                + "LEFT JOIN tipo_animales c ON a.id_tipo_animal=c.id \n"
                + "LEFT JOIN fincas d ON c.id_finca=d.id WHERE a.descripcion='" + descripcion + "'";
        List<Map<String, String>> grupos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloGrupos> lista = new ArrayList<>();
        grupos = mySQL.ListSQL(consulta);

        if (grupos.size() > 0) {

            for (Map<String, String> grupo : grupos) {
                lista.add(new ModeloGrupos(
                        grupo.get("id"),
                        grupo.get("id_tipo_animal"),
                        grupo.get("descripcion"),
                        grupo.get("estado"),
                        grupo.get("id_macrogrupo"),
                        grupo.get("fecha"),
                        grupo.get("id_usuario"),
                        grupo.get("descTipoAnimales"),
                        grupo.get("descMacroGrupo"),
                        grupo.get("idFinca"),
                        grupo.get("desFinca"),
                        grupo.get("pesable"),
                        grupo.get("palpable")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public int Duplicar(Object _grupo) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloGrupos grupo = (ModeloGrupos) _grupo;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="COPIA TIPO DE ANIMAL">
                "INSERT INTO tipo_animales (id,id_finca,descripcion,estado,fecha,id_usuario)\n"
                + "SELECT 0," + grupo.getIdFincaDestino() + ","
                + "descripcion,estado,NOW()," + grupo.getIdUsuario() + "\n"
                + "FROM tipo_animales WHERE "
                + "id_finca=" + grupo.getIdFincaOrigen() + " "
                + "AND id=" + grupo.getIdTipoAnimalOrigen()
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="COPIA GRUPOS DE ANIMALES">
                "INSERT INTO grupos (id,id_tipo_animal,descripcion,estado,id_macrogrupo,fecha,id_usuario)\n"
                + "SELECT 0,(SELECT id FROM tipo_animales WHERE descripcion='"+grupo.getDescTipoAnimalOrigen()+"' AND id_finca=" + grupo.getIdFincaDestino()+ "),"
                + "a.descripcion,a.estado,a.id_macrogrupo,NOW(),"
                + "" + grupo.getIdUsuario() + "\n"
                + "FROM grupos a LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "WHERE b.id_finca=" + grupo.getIdFincaOrigen()+ " "
                + "AND a.id_tipo_animal="+grupo.getIdTipoAnimalOrigen()
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
}
