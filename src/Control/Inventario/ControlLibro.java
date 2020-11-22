/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control.Inventario;

import BaseDeDatos.gestorMySQL;
import Control.IControl;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            String consulta = "SELECT DATE_FORMAT(lbro.`fecha_libro`, '%d/%m/%Y') AS FECHA,lbro.`detalle` AS DETALLE, \n" +
                        "lbro.`id_producto` AS ID_PRODUCTO, lbro.`cantidad` AS CANTIDAD, lbro.`precioxunidad` AS PRECIO, \n" +
                        "lbro.debe AS DEBE, lbro.`haber` AS HABER, lbro.`saldo` AS SALDO, IFNULL(pdct.`tipo_salida`, '0') TIPO,\n" +
                        "IF(lbro.debe IS NULL, 'HABER', 'DEBE') RADIO\n" +
                        "FROM `libro_diario` lbro\n" +
                        "LEFT JOIN productos pdct ON pdct.`id` = lbro.`id_producto`\n" +
                        "WHERE lbro.`id_finca` = '"+o.toString()+"'\n" +
                        "ORDER BY lbro.`fecha_libro` ASC";
            
            List<Map<String, String>> Libros = new ArrayList<Map<String, String>>();

            Libros = mySQL.ListSQL(consulta);

            return Libros;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
