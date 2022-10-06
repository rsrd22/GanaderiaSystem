/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import BaseDeDatos.baseDeDatos;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class ConfiguracionPropiedades {

    private static Properties propiedades;
    private static String SERVIDOR;
    private static String USUARIO;
    private static String CLAVE;
    private static String BASE_DE_DATOS;
    private static String ANIO_FISCAL;
    private static String PUERTO;
    private static String EST_CARGA_MASIVA_ANIMALES;
    private static String EST_CARGA_MASIVA_PESAJE;
    private static String EST_CARGA_MASIVA_PALPACION;
    private static final String KEY_SERVIDOR = "servidor";
    private static final String KEY_USUARIO = "usuario";
    private static final String KEY_CLAVE = "clave";
    private static final String KEY_PUERTO = "puerto";
    private static final String KEY_BASE_DE_DATOS = "baseDeDatos";
    private static final String KEY_ANIO_FISCAL = "anioFiscal";
    private static final String KEY_EST_CARGA_MASIVA_ANIMALES = "EncabezadosCargaMasivaAnimal";
    private static final String KEY_EST_CARGA_MASIVA_PESAJE = "EncabezadosCargaMasivaPesaje";
    private static final String KEY_EST_CARGA_MASIVA_PALPACION = "EncabezadosCargaMasivaPalpacion";

    public static void cargarConfiguracion() {
        propiedades = new Properties();
        try {
            //String urlRelativa = "/home/dolf/Config.properties"; // LINUX
            String urlRelativa = "C:\\CONFIG GANADERIA\\Config.properties";
            FileInputStream archivoConfig = new FileInputStream(urlRelativa);

            propiedades.load(archivoConfig);
            SERVIDOR = propiedades.getProperty(KEY_SERVIDOR);
            BASE_DE_DATOS = propiedades.getProperty(KEY_BASE_DE_DATOS);
            USUARIO = propiedades.getProperty(KEY_USUARIO);
            CLAVE = propiedades.getProperty(KEY_CLAVE);
            PUERTO = propiedades.getProperty(KEY_PUERTO);
            ANIO_FISCAL = propiedades.getProperty(KEY_ANIO_FISCAL);
            EST_CARGA_MASIVA_ANIMALES = propiedades.getProperty(KEY_EST_CARGA_MASIVA_ANIMALES);
            EST_CARGA_MASIVA_PESAJE = propiedades.getProperty(KEY_EST_CARGA_MASIVA_PESAJE);
            EST_CARGA_MASIVA_PALPACION = propiedades.getProperty(KEY_EST_CARGA_MASIVA_PALPACION);
            cargarPropiedades();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "El archivo de configuracion no se encuentra en la ruta esperada.\nDetalles:\n" + ioe.getMessage());
        }
    }

    public static String[] getEST_CARGA_MASIVA_ANIMALES() {
        return EST_CARGA_MASIVA_ANIMALES.split(",");
    }

    public static void setEST_CARGA_MASIVA_ANIMALES(String EST_CARGA_MASIVA_ANIMALES) {
        ConfiguracionPropiedades.EST_CARGA_MASIVA_ANIMALES = EST_CARGA_MASIVA_ANIMALES;
    }

    public static String[] getEST_CARGA_MASIVA_PESAJE() {
        return EST_CARGA_MASIVA_PESAJE.split(",");
    }

    public static void setEST_CARGA_MASIVA_PESAJE(String EST_CARGA_MASIVA_PESAJE) {
        ConfiguracionPropiedades.EST_CARGA_MASIVA_PESAJE = EST_CARGA_MASIVA_PESAJE;
    }

    public static String[] getEST_CARGA_MASIVA_PALPACION() {
        return EST_CARGA_MASIVA_PALPACION.split(",");
    }

    public static void setEST_CARGA_MASIVA_PALPACION(String EST_CARGA_MASIVA_PALPACION) {
        ConfiguracionPropiedades.EST_CARGA_MASIVA_PALPACION = EST_CARGA_MASIVA_PALPACION;
    }
    

    public static String getANIO_FISCAL() {
        return propiedades.getProperty(KEY_ANIO_FISCAL);
    }

    public static void setANIO_FISCAL(String valor) {
        propiedades.setProperty(KEY_ANIO_FISCAL, valor);
    }

    public static String getBaseDeDatos() {
        return propiedades.getProperty(KEY_BASE_DE_DATOS);
    }

    public static void setBaseDeDatos(String valor) {
        propiedades.setProperty(KEY_BASE_DE_DATOS, valor);
    }

    public static String getUsuario() {
        return propiedades.getProperty(KEY_USUARIO);
    }

    public static void setUsuario(String valor) {
        propiedades.setProperty(KEY_USUARIO, valor);
    }

    public static String getServidor() {
        return propiedades.getProperty(KEY_SERVIDOR);
    }

    public static void setServidor(String valor) {
        propiedades.setProperty(KEY_SERVIDOR, valor);
    }

    public static String getClave() {
        return propiedades.getProperty(KEY_CLAVE);
    }

    public static void setClave(String valor) {
        propiedades.setProperty(KEY_CLAVE, valor);
    }

    private static String getPuerto() {
        return propiedades.getProperty(KEY_PUERTO);
    }

    private static void cargarPropiedades() {
        baseDeDatos.HOSTALLONE = getServidor();
        baseDeDatos.BIENESUSER = getUsuario();
        baseDeDatos.PASSWORD_DB_BIENES = getClave();
        baseDeDatos.PASSWORD_DB_SERVIDOR_CASA = getClave();
        baseDeDatos.BIENESRAICES = getBaseDeDatos();
        baseDeDatos.PUERTO = getPuerto();
    }
}
