/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import Modelo.ModeloFincas;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlRotar implements IControl{
    
    private gestorMySQL mySQL;
    //private ArrayList<ModeloRotacionLotes> listaModeloRotacionLotes;
    
    public ControlRotar() {
        this.mySQL = new gestorMySQL();
        //listaModeloRotacionLotes = new ArrayList<>();
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
        ModeloRotacionLotes rotacion = (ModeloRotacionLotes) _rotacion;

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

    public String getId(String idLote) {
        String consulta = "SELECT MAX(id) FROM `rotacion_lotesxestado` WHERE id_lote = '"+idLote+"' AND estado = 'Activo'";
        
        
        String idrotacion_lotesxestado = mySQL.SELECT(consulta).get(0)[0];
        
        return idrotacion_lotesxestado;
    }

    public int GuardarRotacionGrupos(ModeloRotacionGrupos _ListamodeloRotacionGrupos) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloRotacionGrupos ListamodeloRotacionGrupos = (ModeloRotacionGrupos) _ListamodeloRotacionGrupos;

        consultas.add(
            //<editor-fold defaultstate="collapsed" desc="INSERT">
            "INSERT INTO `rotacion_lotesxgrupo`\n" +
            "(`id`, `id_rotacion_lotesxestado`, `id_grupo`, `fecha`, `id_usuario`)\n" +
            "VALUES \n" +
            "("+ListamodeloRotacionGrupos.getId()+",\n" +
            "        "+ListamodeloRotacionGrupos.getId_rotacion_lotesxestado()+",\n" +
            "        "+ListamodeloRotacionGrupos.getId_grupo()+",\n" +
            "        "+ListamodeloRotacionGrupos.getFecha()+",\n" +
            "        "+ListamodeloRotacionGrupos.getId_usuario()+");"
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
    
    public int ActualizarRotacionLote(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
        
        
        
        consultas.add("UPDATE `rotacion_lotesxgrupo` \n" +
                        "SET estado = 'Inactivo'\n" +
                        "WHERE id_rotacion_lotesxestado IN (\n" +
                        "SELECT id\n" +
                        "FROM rotacion_lotesxestado\n" +
                        "WHERE id_lote = '"+datos.get("IDLOTE")+"' AND estado = 'Activo'\n" +
                        ")");
        
        
        consultas.add(
            //<editor-fold defaultstate="collapsed" desc="UPDATE">
            "UPDATE `rotacion_lotesxestado`\n" +
                "SET `fecha_salida` = '"+datos.get("FECHA_SALIDA")+"',\n" +
                "  `estado` = 'Inactivo'\n" +
                "WHERE `id_lote` = '"+datos.get("IDLOTE")+"' AND estado = 'Activo'"
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

    public List<Map<String, String>> getGruposxFinca(String idFinca) {
        
        String consulta = "SELECT gr.`id` AS ID, gr.`descripcion` AS DESCRIPCION\n" +
                            "FROM grupos gr \n" +
                            "INNER JOIN `tipo_animales` tip ON tip.`id` = gr.`id_tipo_animal`\n" +
                            "WHERE tip.`id_finca` = '"+idFinca+"'\n" +
                            "ORDER BY gr.`id` ASC ";
        
        List<Map<String, String>> listaModelo = new ArrayList<Map<String, String>>();
        listaModelo = mySQL.ListSQL(consulta);
        
        return listaModelo;
    }
}
