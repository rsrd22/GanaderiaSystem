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
import Modelo.Inventario.ModeloSalida;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlInventario implements IControl {

    private gestorMySQL mySQL;

    public ControlInventario() {
        this.mySQL = new gestorMySQL();
    }

    public ArrayList<ModeloProducto> getProductosSistema() {
        try {
            String consulta = "SELECT * FROM `productos`";

            List<Map<String, String>> info = new ArrayList<Map<String, String>>();
            ArrayList<ModeloProducto> retorno = new ArrayList<>();

            info = mySQL.ListSQL(consulta);
            if (info.size() > 0) {
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
        ModeloProducto modeloProducto = (ModeloProducto) o;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `productos`\n"
                + "(`descripcion`, `tipo_salida`, `estado`, `fecha`, `id_usuario`)\n"
                + "VALUES \n"
                + "('" + modeloProducto.getDescripcion() + "', "
                + "'" + modeloProducto.getTipo_salida() + "', "
                + "'" + modeloProducto.getEstado() + "', "
                + "" + modeloProducto.getFecha() + ","
                + "'" + modeloProducto.getId_usuario() + "');"
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
        try {
            String consulta = "SELECT a.id,\n"
                    + "a.id_finca,\n"
                    + "a.id_producto,\n"
                    + "a.entrada,\n"
                    + "a.salidas,\n"
                    + "a.stock,\n"
                    + "b.tipo_salida,\n"
                    + "DATE_FORMAT(b.fecha, '%d/%m/%Y') FECHA,\n"
                    + "a.entrada ENTRADA,\n"
                    + "a.salidas SALIDA,\n"
                    + "a.stock EXISTENCIA,\n"
                    + "b.descripcion PRODUCTO\n"
                    + "FROM \n"
                    + "inventario a\n"
                    + "LEFT JOIN productos b ON a.id_producto=b.id\n"
                    + "WHERE \n"
                    + "a.id_finca = '" + o.toString() + "'\n"
                    + "ORDER BY a.fecha ASC";

            List<Map<String, String>> Libros = new ArrayList<Map<String, String>>();

            Libros = mySQL.ListSQL(consulta);

            return Libros;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int GuardarProducto(ModeloProducto modeloProducto) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `productos`\n"
                + "(`descripcion`, `tipo_salida`, `estado`, `fecha`, `id_usuario`)\n"
                + "VALUES \n"
                + "('" + modeloProducto.getDescripcion() + "',"
                + " '" + modeloProducto.getTipo_salida() + "', "
                + "'" + modeloProducto.getEstado() + "', "
                + "" + modeloProducto.getFecha() + ", "
                + "'" + modeloProducto.getId_usuario() + "');"
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

    public int ActualizarProducto(ModeloProducto modeloProducto) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE \n"
                + "productos\n"
                + "SET\n"
                + "descripcion = '" + modeloProducto.getDescripcion() + "',\n"
                + "tipo_salida = '" + modeloProducto.getTipo_salida() + "',\n"
                + "estado = '" + modeloProducto.getEstado() + "',\n"
                + "fecha = " + modeloProducto.getFecha() + ",\n"
                + "id_usuario = '" + modeloProducto.getId_usuario() + "'\n"
                + "WHERE \n"
                + "id = " + modeloProducto.getId() + ";"
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
                "INSERT INTO `entradas`\n"
                + "(`id_finca`, `id_producto`, `cantidad`, \n"
                + "`precioxunidad`, `fecha_entrada`, `fecha`, `id_usuario`)\n"
                + "VALUES \n"
                + "(" + modeloLibro.getId_finca() + ", " + modeloLibro.getId_producto() + ", " + modeloLibro.getCantidad() + ", \n"
                + "" + modeloLibro.getPrecioxunidad() + ", '" + modeloLibro.getFecha_libro() + "', NOW(), " + modeloLibro.getId_usuario() + ");"
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
                "INSERT INTO `inventario`\n"
                + "(`id_finca`, `id_producto`, `entrada`, \n"
                + "`salidas`, `stock`, `fecha`, `id_usuario`)\n"
                + "VALUES \n"
                + "(" + modeloLibro.getId_finca() + ", " + modeloLibro.getId_producto() + ", " + modeloLibro.getCantidad() + ", \n"
                + "0, " + modeloLibro.getCantidad() + ", NOW(), " + modeloLibro.getId_usuario() + ");"
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
                "UPDATE `inventario`\n"
                + "SET `entrada` = entrada + " + modeloLibro.getCantidad() + ",\n"
                + "  `stock` = stock + " + modeloLibro.getCantidad() + "\n"
                + "WHERE `id_producto` = " + modeloLibro.getId_producto() + ";"
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

    public boolean ExisteDatoFecha(ModeloLibro modeloLibro) {
        try {
            String consulta = "Select * from entradas where id_producto = '" + modeloLibro.getId_producto() + "' AND fecha_entrada = '" + modeloLibro.getFecha_libro() + "'";

            List<Map<String, String>> Libros = new ArrayList<Map<String, String>>();

            Libros = mySQL.ListSQL(consulta);
            if (Libros.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int GuardarLibroDiario(ModeloLibro o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloLibro modelo = (ModeloLibro) o;
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `libro_diario`\n"
                + "(`id_finca`,`detalle`,`id_producto`,`cantidad`,`precioxunidad`,\n"
                + "`debe`, `haber`, `saldo`, `fecha_libro`, `fecha`, `id_usuario`)\n"
                + "VALUES \n"
                + "('" + modelo.getId_finca() + "', '" + modelo.getDetalle() + "', '" + modelo.getId_producto() + "', " + modelo.getCantidad() + "," + modelo.getPrecioxunidad() + ", \n"
                + "" + modelo.getDebe() + ", " + modelo.getHaber() + ", " + modelo.getSaldo() + ", '" + modelo.getFecha_libro() + "', " + modelo.getFecha() + ", " + modelo.getId_usuario() + ");"
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

    public int ActualizarEntrada(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `entradas`\n"
                + "SET `cantidad` = cantidad + " + modeloLibro.getCantidad() + ",\n"
                + "  `precioxunidad` = " + modeloLibro.getPrecioxunidad() + ",\n"
                + "  `fecha` = NOW(),\n"
                + "  `id_usuario` = " + modeloLibro.getId_usuario() + "\n"
                + "WHERE `id_producto` = '" + modeloLibro.getId_producto() + "' AND `fecha_entrada` = '" + modeloLibro.getFecha_libro() + "';"
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

    public int ActualizarInventario(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE \n"
                + "inventario\n"
                + "SET\n"
                + "entrada = " + modeloLibro.getCantidad() + ",\n"
                + "salidas = salidas,\n"
                + "stock = " + modeloLibro.getCantidad() + ",\n"
                + "fecha = NOW(),\n"
                + "id_usuario = " + modeloLibro.getId_usuario() + "\n"
                + "WHERE\n"
                + "id_finca = " + modeloLibro.getId_finca() + " AND\n"
                + "id_producto = " + modeloLibro.getId_producto()
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

    public int ActualizarLibroDiario(ModeloLibro modeloLibro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object ObtenerEntradas(String finca, String producto) {
        try {
            String consulta = "SELECT\n"
                    + "DATE_FORMAT(a.fecha_entrada, '%d/%m/%Y') FECHA,\n"
                    + "a.cantidad CANTIDAD,\n"
                    + "a.precioxunidad PRECIO,\n"
                    + "(a.cantidad*a.precioxunidad) PRECIOXCANTIDAD\n"
                    + "FROM entradas a \n"
                    + "WHERE \n"
                    + "a.id_finca = " + finca + " AND\n"
                    + "a.id_producto = " + producto + " \n"
                    + "ORDER BY a.fecha_entrada DESC";

            List<Map<String, String>> Libros = new ArrayList<Map<String, String>>();

            Libros = mySQL.ListSQL(consulta);

            return Libros;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int GuardarSalida(ModeloSalida modelo) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO salidas\n"
                + "(id,id_finca,id_producto,cantidad,observacion,fecha_salida,fecha,id_usuario)\n"
                + "VALUES (\n"
                + "0,\n"
                + ""+modelo.getId_finca()+",\n"
                + ""+modelo.getId_producto()+",\n"
                + ""+modelo.getCantidad()+",\n"
                + "'"+modelo.getObservacion()+"',\n"
                + "'"+modelo.getFecha_salida()+"',\n"
                + ""+modelo.getFecha()+",\n"
                + ""+modelo.getId_usuario()+"\n"
                + ");"
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
    
    public int ActualizarSalidaInventario(ModeloLibro modeloLibro) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `inventario`\n"
                + "SET `salidas` = `salidas` + " + modeloLibro.getCantidad() + ",\n"
                + "  `stock` = entrada - salidas\n"
                + "WHERE `id_producto` = " + modeloLibro.getId_producto() + ";"
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
