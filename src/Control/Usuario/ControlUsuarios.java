/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Usuario;

import BaseDeDatos.gestorMySQL;
import Control.IControl;
import Control.Retorno;
import Modelo.Usuario.ModeloUsuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlUsuarios implements IControl {

    private gestorMySQL mySQL;
    private final Object LISTA_VACIA = new Object();

    public ControlUsuarios() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "SELECT a.*,b.descripcion descPerfil FROM usuarios a LEFT JOIN perfiles b ON b.id=a.id_perfil ORDER BY a.usuario ASC";
        List<Map<String, String>> usuarios = new ArrayList<Map<String, String>>();
        ArrayList<ModeloUsuarios> lista = new ArrayList<>();
        usuarios = mySQL.ListSQL(consulta);

        if (usuarios.size() > 0) {
            for (Map<String, String> usuario : usuarios) {
                lista.add(new ModeloUsuarios(
                        usuario.get("clave"),
                        usuario.get("clave_dinamica"),
                        usuario.get("estado"),
                        usuario.get("id"),
                        usuario.get("id_empleado"),
                        usuario.get("id_perfil"),
                        usuario.get("usuario"),
                        usuario.get("descPerfil")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT a.*,b.descripcion descPerfil FROM usuarios a LEFT JOIN perfiles b ON b.id=a.id_perfil WHERE a.id=" + ID;
        List<Map<String, String>> usuarios = new ArrayList<Map<String, String>>();
        ArrayList<ModeloUsuarios> lista = new ArrayList<>();
        usuarios = mySQL.ListSQL(consulta);

        if (usuarios.size() > 0) {
            for (Map<String, String> usuario : usuarios) {
                lista.add(new ModeloUsuarios(
                        usuario.get("clave"),
                        usuario.get("clave_dinamica"),
                        usuario.get("estado"),
                        usuario.get("id"),
                        usuario.get("id_empleado"),
                        usuario.get("id_perfil"),
                        usuario.get("usuario"),
                        usuario.get("descPerfil")
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
        ModeloUsuarios usuario = (ModeloUsuarios) o;
        String clave = mySQL.getClaveEncryptada(usuario.getUsuario().toUpperCase(), usuario.getClave());

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO usuarios (id,usuario,clave,clave_dinamica,estado,id_perfil,id_empleado)\n"
                + "VALUES (\n"
                + "" + usuario.getId() + ",\n"
                + "'" + usuario.getUsuario() + "',\n"
                + "'" + clave + "',\n"
                + "'" + usuario.getClave_dinamica() + "',\n"
                + "'" + usuario.getEstado() + "',\n"
                + "" + usuario.getId_perfil() + ",\n"
                + "" + usuario.getId_empleado() + "\n"
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
        ModeloUsuarios usuario = (ModeloUsuarios) o;
        String clave = mySQL.getClaveEncryptada(usuario.getUsuario().toUpperCase(), usuario.getClave());

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE usuarios SET\n"
                + "usuario = '" + usuario.getUsuario() + "',\n"
                + "clave = '" + clave + "',\n"
                + "clave_dinamica = '" + usuario.getClave_dinamica() + "',\n"
                + "estado = '" + usuario.getEstado() + "',\n"
                + "id_perfil = " + usuario.getId_perfil() + ",\n"
                + "id_empleado = " + usuario.getId_empleado() + "\n"
                + "WHERE id=" + usuario.getId()
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
        ModeloUsuarios usuario = (ModeloUsuarios) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM usuarios WHERE id=" + usuario.getId()
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
