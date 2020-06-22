/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlPalpacion implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlPalpacion() {
        mySQL = new gestorMySQL();
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
        ModeloPalpacion modelo = (ModeloPalpacion) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `palpacion`\n" +
                    "(`id`, `id_animal`, `fecha_palpacion`, `diagnostico`, `notas`, `num_meses`, `fecha_ultimo_parto`, `descarte`, `fecha`, `id_usuario`)\n" +
                    "VALUES (0,\n" +
                    "        " + modelo.getId_animal()+ ",\n" +
                    "        "+Utilidades.ValorNULL(modelo.getFecha_palpacion())+",\n" +
                    "        '" + modelo.getDiagnostico()+ "',\n" +
                    "        '" + modelo.getNotas()+ "',\n" +
                    "        " + modelo.getNum_meses()+ ",\n" +
                    "        "+Utilidades.ValorNULL(modelo.getFecha_ultimo_parto())+",\n" +
                    "        '" + modelo.getDescarte()+ "',\n" +
                    "        "+modelo.getFecha()+",\n" +
                    "        "+modelo.getId_usuario()+");"
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACIONPORMEDICAMENTOS">
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO palpacionxtratamiento (id,id_palpacion,id_medicamento,dosis) VALUES(\n"
                    + "0,\n"
                    + " (SELECT id FROM palpacion where id_animal = " + modelo.getId_animal()+ " AND DATE_FORMAT(`fecha`,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n" // AND fecha_palpacion = '"+modelo.getFecha_palpacion()+"' AND diagnostico = '"+modelo.getDiagnostico()+"'
                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                    + ")"
            //</editor-fold>
            );
        }
//</editor-fold>

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
        String consulta = "SELECT * FROM palpacion\n"
                + " WHERE id_animal=" + o.toString() + "";
        List<Map<String, String>> palpaciones = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPalpacion> lista = new ArrayList<>();
        palpaciones = mySQL.ListSQL(consulta);
 
        if (palpaciones.size() > 0) {

            for (Map<String, String> palpacion : palpaciones) {
                lista.add(new ModeloPalpacion(
                        palpacion.get("id"), 
                        palpacion.get("id_animal"), 
                        palpacion.get("fecha_palpacion"), 
                        palpacion.get("diagnostico"), 
                        palpacion.get("notas"), 
                        palpacion.get("num_meses"), 
                        palpacion.get("fecha_ultimo_parto"), 
                        palpacion.get("descarte"), 
                        palpacion.get("fecha"), 
                        palpacion.get("id_usuario")
                        
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

}
