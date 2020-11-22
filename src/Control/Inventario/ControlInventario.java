/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control.Inventario;

import BaseDeDatos.gestorMySQL;
import Control.IControl;
import Control.Retorno;
import Modelo.Inventario.ModeloLibro;
import Modelo.Inventario.ModeloProducto;
import Modelo.Usuario.modeloPerfiles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlInventario implements IControl{

     private gestorMySQL mySQL;
    
    public ControlInventario(){
        this.mySQL = new gestorMySQL();
    }
    
    public ArrayList<ModeloProducto> getProductosSistema() {
       try {
            String consulta = "SELECT * FROM `productos`";
            
            List<Map<String, String>> info = new ArrayList<Map<String, String>>();
            ArrayList<ModeloProducto> retorno = new ArrayList<>();
            
            info = mySQL.ListSQL(consulta);
            if(info.size()>0){
                for (Map<String, String> map : info) {
                    retorno.add(new ModeloProducto(map.get("descripcion"), map.get("estado"), map.get("fecha"), map.get("id"), map.get("id_usuario"), map.get("tipo_salida")));
                }
            }

            return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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
        ModeloProducto modeloProducto = (ModeloProducto)o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `productos`\n" +
                "(`descripcion`, `tipo_salida`, `estado`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "('"+modeloProducto.getDescripcion()+"', "
                        + "'"+modeloProducto.getTipo_salida()+"', "
                        + "'"+modeloProducto.getEstado()+"', "
                        + ""+modeloProducto.getFecha()+","
                        + " '"+modeloProducto.getId_usuario()+"');"
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

    public int GuardarProducto(ModeloProducto modeloProducto) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `productos`\n" +
                "(`descripcion`, `tipo_salida`, `estado`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "('"+modeloProducto.getDescripcion()+"', '"+modeloProducto.getTipo_salida()+"', '"+modeloProducto.getEstado()+"', "+modeloProducto.getFecha()+", '"+modeloProducto.getId_usuario()+"');"
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

    public int GuardarEntrada(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `entradas`\n" +
                "(`id_finca`, `id_producto`, `cantidad`, \n" +
                "`precioxunidad`, `fecha_entrada`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "("+modeloLibro.getId_finca()+", "+modeloLibro.getId_producto()+", "+modeloLibro.getCantidad()+", \n" +
                ""+modeloLibro.getPrecioxunidad()+", '"+modeloLibro.getFecha_libro()+"', NOW(), "+modeloLibro.getId_usuario()+");"
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

    public int GuardarInventario(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `inventario`\n" +
                "(`id_finca`, `id_producto`, `entrada`, \n" +
                "`salidas`, `stock`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "("+modeloLibro.getId_finca()+", "+modeloLibro.getId_producto()+", "+modeloLibro.getCantidad()+", \n" +
                "0, "+modeloLibro.getCantidad()+", NOW(), "+modeloLibro.getId_usuario()+");"
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

    public int ActualizarEntradaInventario(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `inventario`\n" +
            "SET `entrada` = entrada + "+modeloLibro.getCantidad()+",\n" +
            "  `stock` = stock + "+modeloLibro.getCantidad()+"\n" +
            "WHERE `id_producto` = "+modeloLibro.getId_producto()+";"
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
