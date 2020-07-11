package pruebas;

import Actividades.Periodo;
import AjustarControles.tiposDeAjuste;
import Alerta.*;
import Archivos.ControlArchivos;
import BaseDeDatos.gestorMySQL;
import Control.ControlGrupos;
import Control.Retorno;
import Modelo.ModeloGrupos;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DOLFHANDLER
 */
public class NewClass {

    public static void main(String[] args) {
//        //<editor-fold defaultstate="collapsed" desc="Prueba crud">
        Configuracion.ConfiguracionPropiedades.cargarConfiguracion();
//
//        ModeloGrupos mg = new ModeloGrupos("2", "1", "qwertyuiop", "Inactivo", "", "now()", "1","","","","");
//        ControlGrupos cg = new ControlGrupos();
//
//        int guardado = cg.Guardar(mg);
//
//        if (guardado == Retorno.EXITO) {
//            System.out.println("guardado");
//        } else {
//            System.out.println("no guardado");
//        }
//        //</editor-fold>

    }

    private static void cargarPeriodos() {
        ArrayList<Periodo> periodos = new ArrayList<Periodo>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfDescripcionMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat sdfMes = new SimpleDateFormat("M");
        int anioActual = Integer.parseInt(sdfAnio.format(cal.getTime()));

        cal.set(anioActual, 0, 1);

        for (int i = 0; i < 12; i++) {
            System.out.println("" + sdfDescripcionMes.format(cal.getTime()));
            System.out.println("" + sdfMes.format(cal.getTime()));
            cal.add(Calendar.MONTH, 1);
        }

    }

    public static boolean ActualizarPesajes() {
        List<Map<String, String>> pesajes = new ArrayList<>();
        ArrayList<String> consultas = new ArrayList<>();
        gestorMySQL g = new gestorMySQL();
        pesajes = g.ListSQL("SELECT a.*,(SELECT COUNT(id_animal) FROM pesaje WHERE id_animal=a.id_animal) cantidad FROM pesaje a ORDER BY a.id_animal,a.fecha_pesado DESC");

        int cant = 0;
        String consulta = "";
        for (int i = 0; i < pesajes.size(); i++) {
            Map<String, String> pesajeAct = pesajes.get(i);
            Map<String, String> pesajeSig = pesajes.get(i);
            if (i < pesajes.size() - 1) {
                pesajeSig = pesajes.get(i + 1);
            }

            int cantidad = Integer.parseInt(pesajeAct.get("cantidad"));
            if (cantidad > 1) {
                cant++;
                if (cant == cantidad) {
                    consulta = "update pesaje set peso_anterior=0 where id_animal=" + pesajeAct.get("id_animal") + " and fecha_pesado='" + pesajeAct.get("fecha_pesado") + "' and peso=" + pesajeAct.get("peso");
                } else {
                    consulta = "update pesaje set peso_anterior=" + pesajeSig.get("peso") + " where id_animal=" + pesajeAct.get("id_animal") + " and fecha_pesado='" + pesajeAct.get("fecha_pesado") + "' and peso=" + pesajeAct.get("peso");
                }
                System.out.println("consulta: " + consulta);
                consultas.add(consulta);
                cant = (cant == cantidad ? 0 : cant);
            }
        }

        try {
            if (g.EnviarConsultas(consultas)) {
                System.out.println("PESAJES ACTUALIZADOS...");
                return true;
            } else {
                System.out.println("ERROR");
                return false;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return false;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return false;
        }
    }

//    public static boolean diferenciaEntreFechas(String fechaDesde, String fechaHasta)//si retorna false es porque la fecha desde es mayor que la hasta lo cual es erroneo
//   {
//       Date fd = new Date(Integer.parseInt(fechaDesde.split("/")[2]) - 1900, Integer.parseInt(fechaDesde.split("/")[1]) - 1, Integer.parseInt(fechaDesde.split("/")[0])),
//               fh = new Date(Integer.parseInt(fechaHasta.split("/")[2]) - 1900, Integer.parseInt(fechaHasta.split("/")[1]) - 1, Integer.parseInt(fechaHasta.split("/")[0]));
//       int d = fd.compareTo(fh);
//       System.out.println("d-->>>"+d);
//       
//   }
}
