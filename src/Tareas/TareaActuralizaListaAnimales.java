/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tareas;

import Control.ControlAnimales;
import Utilidades.Parametros;
import Vistas.VistaVerAnimales;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class TareaActuralizaListaAnimales implements Runnable {

    private volatile boolean enEjecucion;
    private static Thread proceso;
    private VistaVerAnimales vva;

    public TareaActuralizaListaAnimales(VistaVerAnimales vva) {
        this.vva = vva;
        iniciar();
    }

    public void run() {
        while (enEjecucion) {
            System.out.println("HILO ACTUALIZA LISTA ANIMALES: "+Parametros.actualizarHistoricoAnimal);
            if (Parametros.actualizarHistoricoAnimal) {
                ControlAnimales controlAnimales = new ControlAnimales();
                vva.setListaAnimales((List<Map<String, String>>) controlAnimales.ObtenerDatosAnimales("" + vva.idFinca, vva.idTipoAnimal));
                vva.actualizarTablaAnimales();
                Parametros.actualizarHistoricoAnimal=false;
            }
            pausar();
        }
    }

    private synchronized void iniciar() {
        enEjecucion = true;
        proceso = new Thread(this, "proceso seguimiento del tratamiento");
        proceso.start();
    }

    public synchronized void finalizar() {
        try {
            enEjecucion = false;
            proceso.join();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "terminar -> " + ex.getMessage());
        }
    }

    private void pausar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "pausar -> " + ex.getMessage());
        }
    }

}
