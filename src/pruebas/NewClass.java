package pruebas;

import Actividades.Periodo;
import AjustarControles.tiposDeAjuste;
import Alerta.*;
import BaseDeDatos.gestorMySQL;
import Control.ControlGrupos;
import Control.Retorno;
import Modelo.ModeloGrupos;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JPanel;
import Archivos.ControlArchivos;
import java.sql.SQLException;

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

        ArrayList<String> consultas = new ArrayList<>();
        gestorMySQL g = new gestorMySQL();

        ControlArchivos contArchivo = new ControlArchivos("D:\\relrecext.txt");
        contArchivo.LeerArchivo();
        BufferedReader br = contArchivo.getBuferDeLectura();
        String lineaDeTexto;
        String texto = "";

        if (br != null) {
            g.ConectarConnection();
            try {
                while ((lineaDeTexto = br.readLine()) != null) {
                    String[] linea = lineaDeTexto.split("\\t");
                    String pesoant = g.unicoDato("SELECT\n"
                            + "CASE WHEN (SELECT COUNT(*) FROM pesaje b WHERE b.id_animal="+linea[1]+") > 1 THEN\n"
                            + "(SELECT c.peso FROM pesaje c WHERE c.id_animal="+linea[1]+" AND c.fecha_pesado < "
                            + "(SELECT MAX(d.fecha_pesado) FROM pesaje d WHERE d.id_animal="+linea[1]+") ORDER BY c.fecha_pesado DESC LIMIT 1)\n"
                            + "ELSE a.peso END AS pesoAnterior\n"
                            + "FROM pesaje a WHERE a.id_animal="+linea[1]+" LIMIT 1");
                    consultas.add("UPDATE pesaje SET peso_anterior=" + pesoant + " WHERE id_animal=" + linea[1]);
                }

                try {
                    if (g.EnviarConsultas(consultas)) {
                        System.out.println("actualizado");
                    } else {
                        System.out.println("error");
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("" + ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("" + ex.getMessage());
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                g.DesconectarConexion();
            }
            g.DesconectarConexion();
        }

    }

    public static void mostrar() {
        System.out.println("hola a todos");
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

//    public static boolean diferenciaEntreFechas(String fechaDesde, String fechaHasta)//si retorna false es porque la fecha desde es mayor que la hasta lo cual es erroneo
//   {
//       Date fd = new Date(Integer.parseInt(fechaDesde.split("/")[2]) - 1900, Integer.parseInt(fechaDesde.split("/")[1]) - 1, Integer.parseInt(fechaDesde.split("/")[0])),
//               fh = new Date(Integer.parseInt(fechaHasta.split("/")[2]) - 1900, Integer.parseInt(fechaHasta.split("/")[1]) - 1, Integer.parseInt(fechaHasta.split("/")[0]));
//       int d = fd.compareTo(fh);
//       System.out.println("d-->>>"+d);
//       
//   }
}
