/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImportExport;

import Archivos.ControlArchivos;
import BaseDeDatos.baseDeDatos;
import BaseDeDatos.gestorMySQL;
import Utilidades.Expresiones;
import Utilidades.Parametros;
import Vistas.VistaPrincipal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DOLFHANDLER
 */
public class ImportExport extends Thread implements Runnable {

    private gestorMySQL sql;
    private ControlArchivos archivo;
    public int estado;
    private VistaPrincipal vp;
    private Thread importExport;
    public String mensaje;

    public ImportExport(VistaPrincipal vp) {
        mensaje = "";
        this.vp = vp;
        sql = new gestorMySQL();
        archivo = new ControlArchivos();
    }

    @Override
    public void run() {
        if (estado == Estados.EXPORTAR) {
            exportar();
        } else {
            importar();
        }
    }

    public ArrayList<String[]> getTablas(String nombreDB) {
        String consulta = "SHOW TABLES FROM " + nombreDB + "";
        return sql.SELECT(consulta);
    }

    public String getDatosTabla(String tabla) {
        String texto = "";
        String consulta = "SELECT * FROM " + tabla;
        ArrayList<String[]> datos = sql.SELECT(consulta);

        texto += tabla.toUpperCase() + "\n";
        if (datos.size() > 0) {
            for (int i = 0; i < datos.size(); i++) {
                for (int j = 0; j < datos.get(i).length; j++) {
                    texto += datos.get(i)[j] + Parametros.separadorIE;
                }
                texto += "a\n";
            }
        }
        return texto + tabla.toUpperCase() + "\n";
    }

    public void escribirArchivo(String texto, String url) {
        archivo.EscribirArchivo(texto, url);
    }

    public void exportar() {
        String url = Expresiones.guardarEn();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar la ruta donde desea exportar los datos.");
            return;
        } else {
            ArrayList<String[]> tablas = getTablas(baseDeDatos.BIENESRAICES);
            String txtie = "";
            if (tablas.size() > 0) {
                int valmax = 0;
                vp.progreso.setMaximum(tablas.size());

                for (int i = 0; i < tablas.size(); i++) {
                    for (int j = 0; j < tablas.get(i).length; j++) {
                        if (!tablas.get(i)[j].equalsIgnoreCase("BIENXIMAGEN")) {
                            txtie += getDatosTabla(tablas.get(i)[j]);
                            vp.progreso.setValue(++valmax);
                            vp.mensaje.setText("<HTML><P ALIGN='CENTER'>Exportando los datos de <b>" + tablas.get(i)[j].toUpperCase() + "</b> espere...</P></HTML>");
                        }
                    }
                }
                escribirArchivo(txtie, url + "/BIENESRAICES.txt");
                vp.progreso.setValue(++valmax);
                vp.mensaje.setText("<HTML><P ALIGN='CENTER'>Datos exportados exitosamente</P></HTML>");
            }
        }
    }

    public void importar() {
        String texto = "", linea, nombreTabalaMay = "";
        ArrayList<String> consultas = new ArrayList<>();
        String url = Expresiones.seleccionarArchivo(false, "C:/", "texto", "txt");
        archivo = new ControlArchivos(url);
        vp.mensaje.setVisible(true);
        vp.mensaje.setText("");

        try {
            File arch = new File(url);
            FileReader fr = new FileReader(arch);
            BufferedReader br = new BufferedReader(fr);
            int valmax = -1;
            vp.progreso.setMaximum(archivo.getNumeroDeLineas());
            int banderaConsultaCampos = 0;
            List<String[]> camposTabla = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                vp.progreso.setValue(++valmax);
                vp.mensaje.setText(
                        "<HTML><P ALIGN='CENTER'>"
                        + "Importando los datos espere...<BR>Progreso en un "
                        + (Math.round(valmax * 100 / vp.progreso.getMaximum()))
                        + "%</P></HTML>"
                );
                texto = linea.trim();

                if (texto.contains(Parametros.separadorIE)) {
                    String nombreTabla = nombreTabalaMay.toLowerCase();
                    if (banderaConsultaCampos == 0) {
                        banderaConsultaCampos = 1;
                        camposTabla = this.getCamposTabla(nombreTabla);
                    }
                    String consulta = this.importarDatosTabla(camposTabla, nombreTabla, texto);
                    if (!consulta.isEmpty()) {
                        consultas.add(consulta);
                    }

                } else {
                    banderaConsultaCampos = 0;
                    nombreTabalaMay = linea.trim();
                }
            }

            if (!consultas.isEmpty()) {
                if (sql.EnviarConsultas(consultas, vp)) {
                    vp.mensaje.setText(
                            "<HTML><P ALIGN='CENTER'>"
                            + "<b>Los datos se han importado con exito.</b>"
                            + "</P></HTML>"
                    );
                } else {
                    vp.mensaje.setText(
                            "Algo salio mal, los datos no se pudieron importar."
                    );
                }
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            vp.mensaje.setText("");
            JOptionPane.showMessageDialog(vp, "Exepción general debido a: " + ex.getMessage());
        }
    }

    @Deprecated
    private String importarDatosTabla(String tabla, String lineaDeTexto) {
        String consulta = "SHOW COLUMNS FROM " + baseDeDatos.BIENESRAICES + "." + tabla;
        ArrayList<String[]> datos = sql.SELECT(consulta);
        String insert = "", keyDuplicada = "";
        String[] datosLdt = lineaDeTexto.replace(Parametros.separadorIE, ":-:").split(":-:");;
        int band = 0;

        if (datos.size() > 0) {
            insert += "INSERT INTO " + tabla + "() VALUES (";
            for (int i = 0; i < datos.size(); i++) {

                String campo = "", tipo = "";
                if (datos.get(i)[1].contains("(")) {
                    tipo = datos.get(i)[1].substring(0, datos.get(i)[1].indexOf("("));
                } else {
                    tipo = datos.get(i)[1];
                }

                if (Expresiones.validarCamposDeTextoDB(tipo)) {
                    campo = "'";
                }

                if (i < datos.size() - 1) {
                    insert += campo + datosLdt[i] + campo + ",";
                } else {
                    insert += campo + datosLdt[i] + campo;
                }

                if (datos.get(i)[2].equalsIgnoreCase("yes") && band == 0) {
                    band = 1;
                    keyDuplicada = " ON DUPLICATE KEY UPDATE " + datos.get(i)[0] + "=" + campo + datosLdt[i] + campo;
                }
            }
            insert += ")" + keyDuplicada;
        }

        return keyDuplicada.isEmpty() ? "" : insert.replaceAll("'null'", "NULL");
    }

    private String importarDatosTabla(
            List<String[]> datos, String tabla, String lineaDeTexto
    ) {
        String insert = "", keyDuplicada = "";
        String[] datosLdt = lineaDeTexto.replace(Parametros.separadorIE, ":-:")
                .split(":-:");
        int band = 0;

        if (!datos.isEmpty()) {
            insert += "INSERT INTO " + tabla + "() VALUES (";
            for (int i = 0; i < datos.size(); i++) {

                String comilla = "", tipo = "", campo = "";
                if (datos.get(i)[1].contains("(")) {
                    tipo = datos.get(i)[1].substring(0, datos.get(i)[1]
                            .indexOf("("));
                } else {
                    tipo = datos.get(i)[1];
                }

                if (Expresiones.validarCamposDeTextoDB(tipo)) {
                    comilla = "'";
                }

                campo = comilla + datosLdt[i] + comilla;
                insert += campo + ((i < datos.size() - 1) ? "," : "");

                if (datos.get(i)[2].equalsIgnoreCase("yes") && band == 0) {
                    band = 1;
                    keyDuplicada = " ON DUPLICATE KEY UPDATE " + datos.get(i)[0]
                            + "=" + campo;
                }
            }
            insert += ")" + keyDuplicada;
        }

        return keyDuplicada.isEmpty() ? "" : insert.replaceAll("'null'", "NULL");
    }

    private List<String[]> getCamposTabla(String tabla) {
        String consulta = "SHOW COLUMNS FROM " + baseDeDatos.BIENESRAICES + "."
                + tabla;
        return sql.SELECT(consulta);
    }

    public synchronized void iniciar() {
        importExport = new Thread(this, "proceso exportar");
        importExport.start();
    }

    public synchronized void terminar() {
        try {
            importExport.join();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "terminar -> " + ex.getMessage());
        }
    }

}
