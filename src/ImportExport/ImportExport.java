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
import Utilidades.Utilidades;
import Vistas.VistaPrincipal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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

    public void exportar() {
        String url = Expresiones.guardarEn();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar la ruta donde desea exportar los datos."
            );
            return;
        }

        List<String[]> tablas = this.getTablas(baseDeDatos.BIENESRAICES);
        if (tablas.isEmpty()) {
            vp.mensaje.setText(
                    "<HTML><P ALIGN='CENTER'><b>"
                    + "No se encontraron datos a exportar</b>"
                    + "Proceso finalizado.</P></HTML>"
            );
            return;
        }

        String txtie = "";
        int valmax = 0;
        vp.progreso.setMaximum(tablas.size());

        for (int i = 0; i < tablas.size(); i++) {
            Optional<String> optTabla = Stream.of(tablas.get(i)).findFirst();
            if (!optTabla.isPresent()) {
                vp.progreso.setValue(tablas.size());
                vp.mensaje.setText(
                        "<HTML><P ALIGN='CENTER'><b>"
                        + "El proceso finalizo con errores,</b>"
                        + "Intentalo mas tarde.</P></HTML>"
                );
                return;
            }
            String tabla = optTabla.get();

            List<Integer> indices = this.getIndicesCampoTexto(
                    this.getCamposTabla(tabla)
            );

            txtie += this.getDatosTabla(tabla, indices);
            vp.progreso.setValue(++valmax);
            vp.mensaje.setText(
                    "<HTML><P ALIGN='CENTER'>"
                    + "Exportando los datos de <b>"
                    + tabla.toUpperCase() + "</b> espere..."
                    + "</P></HTML>"
            );
        }

        archivo.EscribirArchivo(txtie, url.concat("/ganadero.txt"));
        vp.progreso.setValue(++valmax);
        vp.mensaje.setText(
                "<HTML><P ALIGN='CENTER'>"
                + "<b>Datos exportados exitosamente.</b>"
                + "</P></HTML>"
        );

    }

    public List<String[]> getTablas(String nombreBaesDatos) {
        String consulta = "SHOW TABLES FROM " + nombreBaesDatos + "";
        return sql.SELECT(consulta);
    }

    public String getDatosTabla(String tabla, List<Integer> indices) {
        String texto = "";
        String consulta = "SELECT * FROM " + tabla;
        List<String[]> datos = sql.SELECT(consulta);

        texto += tabla.toUpperCase().concat("\n");
        if (datos.isEmpty()) {
            return texto.concat(tabla.toUpperCase()).concat("\n");
        }

        for (int i = 0; i < datos.size(); i++) {
            for (String elemento : datos.get(i)) {
                texto += Utilidades.codificarElementoIE(elemento)
                        .concat(Parametros.separadorIE);
            }
            texto += "a\n";
        }

        return texto.concat(tabla.toUpperCase()).concat("\n");
    }

    private List<Integer> getIndicesCampoTexto(List<String[]> camposTabla) {
        List<Integer> indices = new ArrayList<>();

        if (camposTabla.isEmpty()) {
            return indices;
        }

        for (int i = 0; i < camposTabla.size(); i++) {
            String tipo = this.getTipoDeDato(camposTabla.get(i)[1]);
            if (Expresiones.validarCamposDeTextoDB(tipo)) {
                indices.add(i);
            }
        }

        return indices;
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
                    
                    String consulta = this.importarDatosTabla(
                            camposTabla, nombreTabla, texto
                    );
                    
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
                String tipo = this.getTipoDeDato(datos.get(i)[1]);

                String comilla = "";
                if (Expresiones.validarCamposDeTextoDB(tipo)) {
                    comilla = "'";
                }

                String campo = comilla
                        .concat(Utilidades.decodificarElementoIE(datosLdt[i]))
                        .concat(comilla);
                insert += campo.concat((i < datos.size() - 1) ? "," : "");

                if (datos.get(i)[2].equalsIgnoreCase("yes") && band == 0) {
                    band = 1;
                    keyDuplicada = " ON DUPLICATE KEY UPDATE "
                            .concat(datos.get(i)[0]).concat("=")
                            .concat(campo);
                }
            }
            insert += ")".concat(keyDuplicada);
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

    private String getTipoDeDato(String datos) {
        if (datos.contains("(")) {
            return datos.substring(0, datos.indexOf("("));
        }
        return datos;
    }

}
