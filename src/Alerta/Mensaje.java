/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alerta;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author DOLFHANDLER
 */
public class Mensaje {

    private static ReentrantLock bloqueo;
    private static String titulo;
    private static String mensaje;
    private static VentanaAlerta ventanaAlerta;
    private static final String iconoAlerta = "/Alerta/iconos/alerta.png";
    private static final String iconoInformacion = "/Alerta/iconos/informacion.png";
    private static final String iconoExito = "/Alerta/iconos/exito.png";
    private static final String iconoError = "/Alerta/iconos/error.png";
    private static final String iconoConfirmar = "/Alerta/iconos/confirmar.png";
    private static final String iconoPorDefecto = "/Alerta/iconos/alerta.png";
    public static volatile int respuesta = -1;

    public static void bloquear() {
        bloqueo = new ReentrantLock();
        bloqueo.lock();
    }

    public static void desbloquear() {
        bloqueo.unlock();
    }

    /**
     * muestra un mensaje emergente
     *
     * @param titulo
     * @param mensaje
     * @param tipo es el tipo de mensaje que desea mostrar. Utiliza la clase
     * TipoAlerta para escoger los tipos de alertas disponibles
     */
    public static void Dialogo(String titulo, String mensaje, int tipo) {
        ventanaAlerta = new VentanaAlerta();

        ventanaAlerta.panelAceptar.setVisible(true);
        ventanaAlerta.btnAceptar.setVisible(true);
        ventanaAlerta.txtTitulo.setText(titulo);
        ventanaAlerta.txtMensaje.setText("<html><body>"
                + "<p style='text-align: justify;'>" + mensaje + "</p>"
                + "</body></html>");
        switch (tipo) {
            case TipoAlerta.ALERTA:
                ventanaAlerta.cargarIcono(iconoAlerta);
                break;
            case TipoAlerta.ERROR:
                ventanaAlerta.cargarIcono(iconoError);
                break;
            case TipoAlerta.EXITO:
                ventanaAlerta.cargarIcono(iconoExito);
                break;
            case TipoAlerta.INFORMACION:
                ventanaAlerta.cargarIcono(iconoInformacion);
                break;
            default:
                ventanaAlerta.cargarIcono(iconoPorDefecto);
        }

        ventanaAlerta.setVisible(true);
    }

    public static void DialogoDeConfirmacion(String titulo, String mensaje) {
        ventanaAlerta = new VentanaAlerta();

        ventanaAlerta.panelSi.setVisible(true);
        ventanaAlerta.btnSi.setVisible(true);
        ventanaAlerta.panelNo.setVisible(true);
        ventanaAlerta.btnNo.setVisible(true);
        ventanaAlerta.txtTitulo.setText(titulo);
        ventanaAlerta.txtMensaje.setText("<html><body>"
                + "<p style='text-align: justify;'>" + mensaje + "</p>"
                + "</body></html>");
        ventanaAlerta.cargarIcono(iconoConfirmar);

        ventanaAlerta.setVisible(true);
    }
}
