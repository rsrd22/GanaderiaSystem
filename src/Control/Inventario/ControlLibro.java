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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlLibro implements IControl{
    private gestorMySQL mySQL;
    
    public ControlLibro(){
        this.mySQL = new gestorMySQL();
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
        ModeloLibro modelo = (ModeloLibro) o;
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `libro_diario`\n" +
                "(`id_finca`,`detalle`,`id_producto`,`cantidad`,`precioxunidad`,\n" +
                "`debe`, `haber`, `saldo`, `fecha_libro`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "('"+modelo.getId_finca()+"', '"+modelo.getDetalle()+"', '"+modelo.getId_producto()+"', "+modelo.getCantidad()+","+modelo.getPrecioxunidad()+", \n" +
                ""+modelo.getDebe()+", "+modelo.getHaber()+", "+modelo.getSaldo()+", '"+modelo.getFecha_libro()+"', "+modelo.getFecha()+", "+modelo.getId_usuario()+");"
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
        ModeloLibro modelo = (ModeloLibro) o;
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE `libro_diario`\n" +
                    "SET `detalle` = '"+modelo.getDetalle()+"',\n" +
                    "  `id_producto` = "+modelo.getId_producto()+",\n" +
                    "  `cantidad` = "+modelo.getCantidad()+",\n" +
                    "  `precioxunidad` = "+modelo.getPrecioxunidad()+",\n" +
                    "  `debe` = "+modelo.getDebe()+",\n" +
                    "  `haber` = "+modelo.getHaber()+",\n" +
                    "  `fecha_libro` = '"+modelo.getFecha_libro()+"'\n" +
                    "WHERE `id` = '"+modelo.getId()+"';"
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        try {
            String consulta = "SELECT DATE_FORMAT(lbro.`fecha_libro`, '%d/%m/%Y') AS FECHA,lbro.`detalle` AS DETALLE, \n" +
                        "lbro.id AS ID,lbro.`id_producto` AS ID_PRODUCTO, lbro.`cantidad` AS CANTIDAD, lbro.`precioxunidad` AS PRECIO, \n" +
                        "lbro.debe AS DEBE, lbro.`haber` AS HABER, lbro.`saldo` AS SALDO, IFNULL(pdct.`tipo_salida`, '0') TIPO,\n" +
                        "IF(lbro.debe IS NULL, 'HABER', 'DEBE') RADIO\n" +
                        "FROM `libro_diario` lbro\n" +
                        "LEFT JOIN productos pdct ON pdct.`id` = lbro.`id_producto`\n" +
                        "WHERE lbro.`id_finca` = '"+o.toString()+"'\n" +
                        "ORDER BY lbro.`fecha_libro` ASC, lbro.id ASC";
            
            List<Map<String, String>> Libros = new ArrayList<Map<String, String>>();

            Libros = mySQL.ListSQL(consulta);

            return Libros;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int ActualizarSaldos(String procedimientoAlmacenado) {
        try {
            ArrayList<String> consultas = new ArrayList<>();
            consultas.add(procedimientoAlmacenado);
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
