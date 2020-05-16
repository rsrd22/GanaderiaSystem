package pruebas;

import Actividades.Periodo;
import AjustarControles.tiposDeAjuste;
import Alerta.*;
import BaseDeDatos.gestorMySQL;
import Control.ControlGrupos;
import Control.Retorno;
import Modelo.ModeloGrupos;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
//        Configuracion.ConfiguracionPropiedades.cargarConfiguracion();
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
        
        DecimalFormat df = new DecimalFormat("#.0");
        double valor = 999999999999.999999999999999999999999;
        double mul = 9999.99999999;
        double acum=1;
        String val = "";
        for (int i = 0; i < 10; i++) {
            acum *= valor*mul;
            val = ""+df.format(acum);
            System.out.println(""+val);
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
}
