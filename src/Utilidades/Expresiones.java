package Utilidades;

import BaseDeDatos.gestorMySQL;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Expresiones {

    public static String TEXTO_SIN_NUMEROS = "\\d";
    public static String SOLO_NUMEROS = "^\\-?[0-9]*$";
    public static String SOLO_NUMEROSP = "^[0-9]*([" + Parametros.separadorDecimal + "][0-9]*)?$";
    public static String PACIENTES_AUXILIARES_OCASIONALES = "^[A|O]$";
    public static String TEXTO_GRAL = "";
    public static String TEXTO_SIN_ESPACIOS = "";
    public static String TEXTO_SIN_COMAS = "";
    public static String TEXTO_SIN_COMILLAS_DOBLES = "";
    public static String TEXTO_SIN_COMILLAS_SIMPLES = "";
    public static String TEXTO_SIN_PUNTOS = "";
    public static String TEXTO_SIN_DOS_PUNTOS = "";
    public static String TEXTO_SIN_OPERADORES_ARITMETICOS = "";
    private static String CAMPOS_DE_TEXTO_BD="^varchar|date|datetime|enum|text$";

    /**
     * Este metodo evalua el texto pasado por parametro basado en una
     * <b>EXPRESIÓN REGULAR</b> y dependiendo de esto éste devuelve <b>true</b>
     * si se encuentra alguna coincidencia de lo contrario devuelve <b>false</b>
     *
     * @param texto
     * @return
     */
    public static boolean validarSoloNumeros(String texto) {
        Pattern p = Pattern.compile(SOLO_NUMEROS);
        Matcher m = p.matcher(texto);
        return m.find();
    }

    public static boolean validarSoloNumerosP(String texto) {
        Pattern p = Pattern.compile(SOLO_NUMEROSP);
        Matcher m = p.matcher(texto);
        return m.find();
    }
    

    public static boolean validarTextoSinNumeros(String texto) {
        Pattern p = Pattern.compile(TEXTO_SIN_NUMEROS);
        Matcher m = p.matcher(texto);
        return m.find();
    }

    public static JTextField procesarTextoSinNumeros(JTextField txt) {
        String texto = "";
        int cpos = 0;
        if (txt.getCaretPosition() > 0) {
            cpos = txt.getCaretPosition() - 1;
        }

        if (Expresiones.validarTextoSinNumeros(txt.getText())) {
            if (txt.getCaretPosition() <= txt.getText().length()) {//si el carret esta en algun lugar en medio de la frase
                texto = txt.getText().substring(0, txt.getCaretPosition() - 1)
                        + txt.getText().substring(txt.getCaretPosition());
                txt.setText(texto);
                txt.setCaretPosition(cpos);
            }
        } else if (txt.getCaretPosition() <= txt.getText().length()) {//si el carret esta en algun lugar en medio de la frase
            if (Expresiones.validarTextoSinNumeros("" + txt.getText().charAt(cpos))) {

                texto = txt.getText();
                txt.setText(texto);
                txt.setCaretPosition(cpos);
            }
        }
        return txt;
    }

    public static JTextField procesarSoloNumeros(JTextField txt) {
        String texto = eliminarSepDecimalYDemasCaracteres(!txt.getText().equals("") ? txt.getText() : "");
        txt.setText(texto);

        return txt;
    }

    public static JTextField ProcesarSoloNumerosSinPUNTOS(JTextField txt) {
        String texto = procesarSoloNum(!txt.getText().equals("") ? txt.getText() : "");
        txt.setText(texto);

        return txt;
    }

    public static String procesarSoloNum(String valor) {
        System.out.println("****procesarSoloNum***");
        String ret = "";
        for (int i = 0; i < valor.length(); i++) {
            if (validarSoloNumeros("" + valor.charAt(i))) {
                ret += "" + valor.charAt(i);
            }
        }
        return ret;
    }
    
    public static String procesarSoloNumP(String valor) {
        System.out.println("******procesarSoloNumP****+");
        String ret = "";
        if (!validarSoloNumerosP("" + valor)) {
            System.out.println("**validarSoloNumerosP*");
            int ind = valor.lastIndexOf(Parametros.separadorDecimal);
            int sind = valor.indexOf(",");
            if(sind < ind){
                ind = sind;
            }
            for (int i = 0; i < valor.length(); i++) {
                if(i != ind){
                    if (validarSoloNumeros("" + valor.charAt(i))) {
                        ret += "" + valor.charAt(i);
                    }
                }else{
                    ret += "" + valor.charAt(i);
                }
            }
            
            System.out.println("ret --"+ret);
            
        }else{
            ret = valor;
        }
        
        System.out.println("***END procesarSoloNumP***"+ret);
        return ret;
    }

    public static int contiene(String cadena, String cadenaABuscar) {
        Pattern p = Pattern.compile("[" + cadenaABuscar + "]");
        Matcher m = p.matcher(cadena);
        int a = cadenaABuscar.length();
        String scad = "";
        int veces = 0;
        for (int i = 0; i < cadena.length(); i++) {
            scad = cadena.substring(i, i + a);
            m = p.matcher(scad);
            if (m.find()) {
                veces++;
            }
        }
        return veces;
    }

    
    public static String seleccionarArchivo(boolean seleccionMultiple, String urlinicial) {
        String ret = "";
        JFileChooser selectorDeArchivos = new JFileChooser();

        selectorDeArchivos.setDialogTitle("Seleccionar Imagenes");
        selectorDeArchivos.setCurrentDirectory(new File(urlinicial));
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Imagenes", "jpg", "png", "tiff", "bmp", "dib", "tif");
        selectorDeArchivos.setAcceptAllFileFilterUsed(false);
        selectorDeArchivos.addChoosableFileFilter(filtro);
        selectorDeArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //para que se puedan seleccionar multiples imagenes
        selectorDeArchivos.setMultiSelectionEnabled(seleccionMultiple);

        int result = selectorDeArchivos.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                if (!seleccionMultiple) {
                    File archivo = selectorDeArchivos.getSelectedFile();
                    ret = String.valueOf(archivo);
                } else {
                    File[] archivos = selectorDeArchivos.getSelectedFiles();
                    String url = (String.valueOf(archivos[0]));
                    url = url.substring(0, url.lastIndexOf("\\") + 1);
                    for (int i = 0; i < archivos.length; i++) {
                        if (i != (archivos.length - 1)) {
                            ret += url + archivos[i].getName() + "#-#";//separador de los archivos
                        } else {
                            ret += url + archivos[i].getName();
                        }
                    }
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                ret = "";
                break;
            case JFileChooser.ABORT:
                ret = "";
                break;
            default:
                JOptionPane.showMessageDialog(selectorDeArchivos, "Ocurrio un error al tratar de seleccionar la ubicación, vuelve a intentarlo.");
                break;
        }
        return ret;
    }
 
    public static String seleccionarArchivoExcel(boolean seleccionMultiple, String urlinicial) {
        String ret = "";
        JFileChooser selectorDeArchivos = new JFileChooser();
        selectorDeArchivos.setDialogTitle("Seleccionar Archivo");
        selectorDeArchivos.setCurrentDirectory(new File(urlinicial));
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Excel", "msexcel", "x-msexcel", "x-ms-excel", "x-excel", "x-dos_ms_excel", "xls", "xlsx", "x-xls");
        selectorDeArchivos.setAcceptAllFileFilterUsed(false);
        selectorDeArchivos.addChoosableFileFilter(filtro);
        selectorDeArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //para que se puedan seleccionar multiples imagenes
        selectorDeArchivos.setMultiSelectionEnabled(seleccionMultiple);

        int result = selectorDeArchivos.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                if (!seleccionMultiple) {
                    File archivo = selectorDeArchivos.getSelectedFile();
                    ret = String.valueOf(archivo);
                } else {
                    File[] archivos = selectorDeArchivos.getSelectedFiles();
                    String url = (String.valueOf(archivos[0]));
                    url = url.substring(0, url.lastIndexOf("\\") + 1);
                    for (int i = 0; i < archivos.length; i++) {
                        if (i != (archivos.length - 1)) {
                            ret += url + archivos[i].getName() + "#-#";//separador de los archivos
                        } else {
                            ret += url + archivos[i].getName();
                        }
                    }
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                ret = "";
                break;
            case JFileChooser.ABORT:
                ret = "";
                break;
            default:
                JOptionPane.showMessageDialog(selectorDeArchivos, "Ocurrio un error al tratar de seleccionar la ubicación, vuelve a intentarlo.");
                break;
        }
        return ret;
    }
    
    public static String seleccionarArchivo(boolean seleccionMultiple, String urlinicial, String nombreGral, String extensiones) {
        String ret = "";
        JFileChooser selectorDeArchivos = new JFileChooser();

        selectorDeArchivos.setDialogTitle("Seleccionar Imagenes");
        selectorDeArchivos.setCurrentDirectory(new File(urlinicial));
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter(nombreGral, extensiones);
        selectorDeArchivos.setAcceptAllFileFilterUsed(false);
        selectorDeArchivos.addChoosableFileFilter(filtro);
        selectorDeArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);

        //para que se puedan seleccionar multiples imagenes
        selectorDeArchivos.setMultiSelectionEnabled(seleccionMultiple);

        int result = selectorDeArchivos.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                if (!seleccionMultiple) {
                    File archivo = selectorDeArchivos.getSelectedFile();
                    ret = String.valueOf(archivo);
                } else {
                    File[] archivos = selectorDeArchivos.getSelectedFiles();
                    String url = (String.valueOf(archivos[0]));
                    url = url.substring(0, url.lastIndexOf("\\") + 1);
                    for (int i = 0; i < archivos.length; i++) {
                        if (i != (archivos.length - 1)) {
                            ret += url + archivos[i].getName() + "#-#";//separador de los archivos
                        } else {
                            ret += url + archivos[i].getName();
                        }
                    }
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                ret = "";
                break;
            case JFileChooser.ABORT:
                ret = "";
                break;
            default:
                JOptionPane.showMessageDialog(selectorDeArchivos, "Ocurrio un error al tratar de seleccionar la ubicación, vuelve a intentarlo.");
                break;
        }
        return ret;
    }
    
    public static String guardarEn() {
        String ret = "";
        JFileChooser selectorDeArchivos = new JFileChooser();

        selectorDeArchivos.setDialogTitle("Guardar En...");
        selectorDeArchivos.setCurrentDirectory(new File("C:\\"));
        javax.swing.filechooser.FileFilter filtro = new FileNameExtensionFilter("Directorio", "dir");
        selectorDeArchivos.setAcceptAllFileFilterUsed(false);
        selectorDeArchivos.addChoosableFileFilter(filtro);
        selectorDeArchivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = selectorDeArchivos.showOpenDialog(null);
        switch (result) {
            case JFileChooser.APPROVE_OPTION:
                File archivo = selectorDeArchivos.getSelectedFile();
                ret = String.valueOf(archivo);
                break;
            case JFileChooser.CANCEL_OPTION:
                ret = "";
                break;
            case JFileChooser.ABORT:
                ret = "";
                break;
            default:
                JOptionPane.showMessageDialog(selectorDeArchivos, "Ocurrio un error al tratar de seleccionar la ubicación, vuelve a intentarlo.");
                break;
        }
        return ret;
    }

    public static boolean ValidarTipoPacienteAO(String texto) {
        Pattern p = Pattern.compile(PACIENTES_AUXILIARES_OCASIONALES);
        Matcher m = p.matcher(texto);
        return m.find();
    }

    public static boolean validarCorreo(JTextField txt) {
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(txt.getText());
        boolean ret = false;
        if (mat.matches()) {
            System.out.println("SI");
            ret = true;
        } else {
            txt.setText("");
            System.out.println("NO");
            JOptionPane.showMessageDialog(null, "Escriba un correo electronico valido");
            txt.requestFocus();
        }
        return ret;
    }

    public static String GenerarPassword() {
        String ret = "";
        int tam = 10;

        for (int i = 0; i < tam; i++) {
            int opc = (int) Math.floor(Math.random() * 2) + 1;
            int ran = 0;
            if (opc == 1) {//NUMEROS
                ran = (int) Math.floor(Math.random() * 10) + 1;
                ran += 47;
            } else {//LETRAS
                ran = (int) Math.floor(Math.random() * 26) + 1;
                ran += 64;
            }
            ret += "" + ((char) +ran);
        }
        return ret;
    }

    public static int getConsecutivoXHCYTI(int hc, int ti) {
        String consulta = "SELECT\n"
                + "CASE WHEN consecutivo IS NOT NULL THEN MAX(consecutivo)+1 ELSE 1 END\n"
                + "FROM\n"
                + "hc_imagenes\n"
                + "WHERE\n"
                + "pfk_historia_clinica = '" + hc + "'\n"
                + "AND pfk_tipo_imagen = '" + ti + "'";
        gestorMySQL sql = new gestorMySQL();
        return Integer.parseInt(sql.unicoDato(consulta));
    }

    private static String eliminarSepDecimalYDemasCaracteres(String t) {
        String r = "";
        while (contiene(t, Parametros.separadorDecimal) > 1) {
            if (t.charAt(0) == '.') {
                t = t.substring(1);
            }
            int indice = t.lastIndexOf(Parametros.separadorDecimal);
            r = t.substring(0, indice) + (indice < t.length() - 1 ? t.substring(indice + 1) : "");
            t = r;
        }
        r = "";
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == '.') {
                r += t.charAt(i);
            } else if (validarSoloNumeros("" + t.charAt(i))) {
                r += t.charAt(i);
            }
        }

        return r;
    }

    public static String calcularEdadPaciente(String tipoydoc) {
        gestorMySQL gsql = new gestorMySQL();
        String consulta = "SELECT\n"
                + "CASE WHEN DATEDIFF(NOW(),fecha_de_nacimiento) > 0 THEN\n"
                + "(CASE WHEN (DATEDIFF(NOW(),fecha_de_nacimiento) >= \n"
                + "ROUND(365.242 * (CAST(DATE_FORMAT(NOW(), '%Y') AS UNSIGNED) - CAST(DATE_FORMAT(fecha_de_nacimiento, '%Y') AS UNSIGNED)),0)) THEN\n"
                + "(CAST(DATE_FORMAT(NOW(), '%Y') AS UNSIGNED) - CAST(DATE_FORMAT(fecha_de_nacimiento, '%Y') AS UNSIGNED)) ELSE\n"
                + "(CAST(DATE_FORMAT(NOW(), '%Y') AS UNSIGNED) - CAST(DATE_FORMAT(fecha_de_nacimiento, '%Y') AS UNSIGNED))-1 END)\n"
                + "ELSE 0 END AS anio\n"
                + "FROM\n"
                + "personas\n"
                + "WHERE\n"
                + "CONCAT(pfk_tipo_documento,pk_persona) = '" + tipoydoc + "'";
        String edad = gsql.unicoDato(consulta);
        if (edad != null) {
            return edad;
        } else {
            return "";
        }
    }

    public static boolean filtrobusqueda(String[] filtros, String valores) {
        String exp = "";
        boolean ct = true;//coincidencia total
        //System.out.println("valores === "+valores);
        for (int i = 0; i < filtros.length; i++) {
            if (ct == false) {
                break;
            }
            exp = "(" + filtros[i].toUpperCase() + ")";
            //System.out.println("exp ---> "+exp);
            Pattern p = Pattern.compile(exp);
            Matcher m = p.matcher(valores.toUpperCase());
//            System.out.println("m.find() === "+m.find());
            ct &= m.find();
            //System.out.println("ctttttdespues = "+ct);
        }
        return ct;
    }

    public static String obtenerExtension(String url) {
        for (int i = url.length() - 1; i >= 0; i--) {
            if (url.charAt(i) == '.') {
                return url.substring(i + 1);
            }
        }
        return "jpg";
    }

    public static String DecodificarTipoPaciente(String tipo) {
        return tipo.equals("A") ? "AUXILIAR" : (tipo.equals("P") ? "PACIENTE" : "OCASIONAL");
    }

    public static String CodificarTipoPaciente(String tipo) {
        return "" + tipo.charAt(0);
    }
    
    public static boolean validarCamposDeTextoDB(String texto) {
        Pattern p = Pattern.compile(CAMPOS_DE_TEXTO_BD);
        Matcher m = p.matcher(texto);
        return m.find();
    }
}
