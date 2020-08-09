/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import Vistas.VistaCargaMasivaAnimales;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class CargaMasiva extends Thread implements Runnable {

    private int estado;
    private VistaCargaMasivaAnimales vista;
    private Thread proceso;
    private String mensaje;

    public CargaMasiva(Object vista, int estado) {
        mensaje = "";
        this.estado = estado;
        if (vista instanceof VistaCargaMasivaAnimales) {
            this.vista = (VistaCargaMasivaAnimales) vista;
        }
    }

    @Override
    public void run() {
        if (estado == Estados.CARGA_MASIVA_ANIMALES) {
            cargaMasivaDeAnimales();
        }else if (estado == Estados.CARGA_MASIVA_PESAJE) {
            cargaMasivaPesaje();
        }else if (estado == Estados.CARGA_MASIVA_PALPACION) {
            cargaMasivaPalpacion();
        }
    }

    private void cargaMasivaDeAnimales() {
        vista.CargarAnimales();
    }
    private void cargaMasivaPesaje() {
        vista.CargarPesaje();
    }
    private void cargaMasivaPalpacion() {
        vista.CargarPalpacion();
    }

    public synchronized void iniciar() {
        System.out.println("Inicia el hilo");
        proceso = new Thread(this, "procesosMasivos");
        proceso.start();
    }

    public synchronized void terminar() {
        try {
            System.out.println("Finaliza el hilo");
            proceso.join();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "terminar -> " + ex.getMessage());
        }
    }

}
