/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloRotacion;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MERRY
 */
public class ControlRotacion implements IControl{
    private gestorMySQL mySQL;
    
    public ControlRotacion(){
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
    public int Guardar(Object _rotacion) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloRotacion rotacion = (ModeloRotacion) _rotacion;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `rotacion_lotesxestado`\n" +
                "(`id`, `id_lote`, `fecha_registro`, `fecha_entrada`, `fecha_salida`, `estado`, `fecha`, `id_usuario`)\n" +
                "VALUES \n" +
                "("+rotacion.getId()+"," +
                "        "+rotacion.getId_lote()+"," +
                "        "+Utilidades.ValorNULL(rotacion.getFecha_registro())+"," +
                "        "+Utilidades.ValorNULL(rotacion.getFecha_entrada())+"," +
                "        "+Utilidades.ValorNULL(rotacion.getFecha_salida())+"," +
                "        '"+rotacion.getEstado()+"'," +
                "        "+rotacion.getFecha()+"," +
                "        "+rotacion.getId_usuario()+");"
        //</editor-fold>
        );
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `rotacion_lote`\n" +
                        "(`id`, `id_lote`, `id_grupo`, `fecha_registro`, `fecha_entrada`, `fecha_salida`, `estado`, `fecha`, `id_usuario`)\n" +
                        "VALUES \n" +
                        "('id',\n" +
                        "        'id_lote',\n" +
                        "        'id_grupo',\n" +
                        "        'fecha_registro',\n" +
                        "        'fecha_entrada',\n" +
                        "        'fecha_salida',\n" +
                        "        'estado',\n" +
                        "        'fecha',\n" +
                        "        'id_usuario');"
        //</editor-fold>
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
