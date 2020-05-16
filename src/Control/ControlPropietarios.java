/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import Modelo.ModeloBloques;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlPropietarios implements IControl{
    private gestorMySQL mySQL;
    private ArrayList<ModeloPropietarios> listaModelo;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();
    
    public ControlPropietarios(){
        mySQL = new gestorMySQL();
        listaModelo = new ArrayList<>();
    }
    
    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        try{
        System.out.println("id--->"+id);
        String consulta = "SELECT\n" +
                            "  `id`, `tipo_identificacion`, `identificacion`, `primer_nombre`, IFNULL(`segundo_nombre`, '') AS segundo_nombre, \n" +
                            "  `primer_apellido`, IFNULL(`segundo_apellido`, '') segundo_apellido, IFNULL(`direccion`, '') AS direccion, IFNULL(`correo`, '') AS correo, \n" +
                            "  IFNULL(`estado`, '') AS estado, `fecha`, `id_usuario`" +
                            "FROM `propietarios`\n" +
                            "WHERE id = '"+id+"'";
        
        List<Map<String, String>> propietarios = new ArrayList<Map<String, String>>();
        propietarios = mySQL.ListSQL(consulta);

        if (propietarios.size() > 0) {

            for (Map<String, String> propietario : propietarios) {
                listaModelo.add(new ModeloPropietarios(
                    propietario.get("id"),
                    propietario.get("tipo_identificacion"),
                    propietario.get("identificacion"),
                    propietario.get("primer_nombre"),
                    propietario.get("segundo_nombre"),
                    propietario.get("primer_apellido"),
                    propietario.get("segundo_apellido"),
                    propietario.get("direccion"),  
                    propietario.get("correo"),
                    propietario.get("estado"),
                    propietario.get("fecha"),
                    propietario.get("id_usuario")
                ));
            }
            return listaModelo;
        } else {
            return null;
        }
        }catch(Exception e){
            System.out.println("e.getMessage()--<"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int Guardar(Object _propietario) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPropietarios propietario = (ModeloPropietarios) _propietario;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `propietarios`\n" +
                    "(`id`, `tipo_identificacion`, `identificacion`, `primer_nombre`, `segundo_nombre`, `primer_apellido`, \n" +
                    "`segundo_apellido`, `direccion`, `correo`, `estado`, `fecha`, `id_usuario`) VALUES \n" +
                    "("+propietario.getId()+",\n" +
                    "        '"+propietario.getTipo_identificacion()+"',\n" +
                    "        '"+propietario.getIdentificacion()+"',\n" +
                    "        '"+propietario.getPrimer_nombre()+"',\n" +
                    "        '"+propietario.getSegundo_nombre()+"',\n" +
                    "        '"+propietario.getPrimer_apellido()+"',\n" +
                    "        '"+propietario.getSegundo_apellido()+"',\n" +
                    "        '"+propietario.getDireccion()+"',\n" +
                    "        '"+propietario.getCorreo()+"',\n" +
                    "        '"+propietario.getEstado()+"',\n" +
                    "        "+propietario.getFecha()+",\n" +
                    "        "+propietario.getId_usuario()+");"
//        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
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
    public int Actualizar(Object _propietario) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPropietarios propietario = (ModeloPropietarios) _propietario;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE `propietarios`\n" +
                    "SET `tipo_identificacion` = '"+propietario.getTipo_identificacion()+"',\n" +
                    "  `identificacion` = '"+propietario.getId_usuario()+"',\n" +
                    "  `primer_nombre` = '"+propietario.getPrimer_nombre()+"',\n" +
                    "  `segundo_nombre` = '"+propietario.getSegundo_nombre()+"',\n" +
                    "  `primer_apellido` = '"+propietario.getPrimer_apellido()+"',\n" +
                    "  `segundo_apellido` = '"+propietario.getSegundo_apellido()+"',\n" +
                    "  `direccion` = '"+propietario.getDireccion()+"',\n" +
                    "  `correo` = '"+propietario.getCorreo()+"',\n" +
                    "  `estado` = '"+propietario.getEstado()+"',\n" +
                    "  `fecha` = "+propietario.getFecha()+",\n" +
                    "  `id_usuario` = "+propietario.getId_usuario()+" \n" +
                    "WHERE `id` = "+propietario.getId()+";"
//        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
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
    public int Eliminar(Object _propietario) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPropietarios propietario = (ModeloPropietarios) _propietario;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "DELETE FROM propietarios WHERE id = "+propietario.getId()
//                //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
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
    public Object ObtenerDatosFiltro(Object filtro) {
        String consulta = "SELECT * FROM `propietarios` WHERE CONCAT(tipo_identificacion, identificacion) = '" + filtro + "'";
        List<Map<String, String>> propietarios = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPropietarios> lista = new ArrayList<>();
        propietarios = mySQL.ListSQL(consulta);

        if (propietarios.size() > 0) {

            for (Map<String, String> propietario : propietarios) {
                lista.add(new ModeloPropietarios(
                        propietario.get("id"),
                        propietario.get("tipo_identificacion"),
                        propietario.get("identificacion"),
                        propietario.get("primer_nombre"),
                        propietario.get("segundo_nombre"),
                        propietario.get("primer_apellido"),
                        propietario.get("segundo_apellido"),
                        propietario.get("direccion"),
                        propietario.get("correo"),
                        propietario.get("estado"),
                        propietario.get("fecha"),
                        propietario.get("id_usuario")));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }
    
    
    public Object ObtenerHierrosxPropietario(String id){
        String consulta = "SELECT `id`, `id_propietario`, `descripcion`, `nombre_imagen`, `imagen`, `estado`, `fecha`, `id_usuario`\n" +
                            "FROM `propietarioxhierro`\n" +
                            "WHERE id_propietario = '"+id+"'";
        
        List<Map<String, String>> hierros = new ArrayList<Map<String, String>>();
        hierros = mySQL.ListSQL(consulta);
        ArrayList<ModeloHierros> listaModeloHierros = new ArrayList<>();
        if (hierros.size() > 0) {

            for (Map<String, String> hierro : hierros) {
                listaModeloHierros.add(new ModeloHierros(
                    hierro.get("id"),
                    hierro.get("id_propietario"),
                    hierro.get("descripcion"),
                    hierro.get("nombre_imagen"),
                    hierro.get("imagen"),
                    hierro.get("estado"),
                    hierro.get("fecha"),
                    hierro.get("id_usuario")
                ));
            }
            return listaModeloHierros;
        } else {
            return LISTA_VACIA;
        }
    }

    public Object ObtenerDatosxLlave(String id) {
        try{
        System.out.println("id--->"+id);
        String consulta = "SELECT\n" +
                            "  `id`, `tipo_identificacion`, `identificacion`, `primer_nombre`, IFNULL(`segundo_nombre`, '') AS segundo_nombre, \n" +
                            "  `primer_apellido`, IFNULL(`segundo_apellido`, '') segundo_apellido, IFNULL(`direccion`, '') AS direccion, IFNULL(`correo`, '') AS correo, \n" +
                            "  IFNULL(`estado`, '') AS estado, `fecha`, `id_usuario`" +
                            "FROM `propietarios`\n" +
                            "WHERE concat(`tipo_identificacion`, identificacion ) = '"+id+"'";
        
        List<Map<String, String>> propietarios = new ArrayList<Map<String, String>>();
        propietarios = mySQL.ListSQL(consulta);

        if (propietarios.size() > 0) {

            for (Map<String, String> propietario : propietarios) {
                listaModelo.add(new ModeloPropietarios(
                    propietario.get("id"),
                    propietario.get("tipo_identificacion"),
                    propietario.get("identificacion"),
                    propietario.get("primer_nombre"),
                    propietario.get("segundo_nombre"),
                    propietario.get("primer_apellido"),
                    propietario.get("segundo_apellido"),
                    propietario.get("direccion"),  
                    propietario.get("correo"),
                    propietario.get("estado"),
                    propietario.get("fecha"),
                    propietario.get("id_usuario")
                ));
            }
            return listaModelo;
        } else {
            return LISTA_VACIA;
        }
        }catch(Exception e){
            System.out.println("e.getMessage()--<"+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
