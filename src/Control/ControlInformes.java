/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import Excel.*;
import Utilidades.Parametros;
import Utilidades.TipoInforme;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlInformes {

    private gestorMySQL mySQL;
    public ArrayList<String> Encabezado;
    public ArrayList<ArrayList<String>> ListaDatos;
    public ControlArchivo controlArchivo;
    private SimpleDateFormat sdf;
    private Calendar cal;
    private String complemento;

    /**
     *
     */
    public ControlInformes() {
        mySQL = new gestorMySQL();
        Encabezado = new ArrayList<>();
        ListaDatos = new ArrayList<>();
        sdf = new SimpleDateFormat("ddMMyyyy");
        cal = Calendar.getInstance();
        double random = Math.random();
        complemento = ("" + random).substring(2, 8) + "_" + (sdf.format(cal.getTime()));
    }

    public void GenerarInformes(Object _info) {
        ModeloInformes informe = (ModeloInformes) _info;
        Map<String, Object> datos = informe.informacion;
        if (informe.categoria.equals("0")) {//ARCHIVO EXCEL
            controlArchivo = new ControlArchivo();
            int tipoInforme = Integer.parseInt(informe.informe);
            switch (tipoInforme) {
                case TipoInforme.CARGA_MASIVA_PESAJE:
                    CrearArchivoPesaje(datos);
                    break;
                case TipoInforme.CARGA_MASIVA_PALPACION:
                    CrearArchivoPalpacion(datos);
                    break;
                case TipoInforme.CARGA_MASIVA_ANIMALES:
                    CrearArchivoAnimal();
                    break;
                case TipoInforme.INFORME_ANIMALES_POR_FINCA:
                    CrearArchivoAnimalesXFinca(datos);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void CrearArchivoPesaje(Map<String, Object> datos) {
        try {
            String consultaPesaje = "SELECT anim.`id` AS ID, anim.`numero` AS NUM_ANIMAL, \n"
                    + "IF(anim.numero_mama_adoptiva IS NULL OR anim.numero_mama_adoptiva = '', anim.numero_mama, anim.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "anim.`peso` AS PESO, hie.`descripcion` AS HIERRO, anim.`genero` AS SEXO, IF(anim.`fecha_destete`>'1900-01-01', DATE_FORMAT(anim.`fecha_destete`, '%d/%m/%Y'), '') FEC_DESTETE\n"
                    + "FROM `animales` anim\n"
                    + "INNER JOIN `propietarioxhierro` hie ON hie.`id` = anim.`hierro`\n"
                    + "INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n"
                    + "WHERE anim.`id_tipo_animal` = '" + datos.get("IDTIPO") + "' AND tpo.`id_finca` = '" + datos.get("IDFINCA") + "'\n"
                    + "" + (datos.get("SEXO").equals("") ? "" : "AND anim.`genero` = '" + datos.get("SEXO") + "' \n")
                    + "AND anim.grupo IN (" + datos.get("GRUPOS") + ") \n"
                    + "ORDER BY anim.genero DESC";

            String consultaMedicamentos = "";
            List<Map<String, String>> listaMedicamentos = new ArrayList<>();
            if (!datos.get("MEDICAMENTOS").equals("")) {
                consultaMedicamentos = "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                        + "FROM `medicamentos`\n"
                        + "WHERE id IN (" + datos.get("MEDICAMENTOS") + ")";
                listaMedicamentos = mySQL.ListSQL(consultaMedicamentos);
            }

            List<Map<String, String>> listaPesajes = mySQL.ListSQL(consultaPesaje);

            System.out.println("listaPesajes-->" + listaPesajes.size());
            System.out.println("listaMedicamentos-->" + listaMedicamentos.size());
            if (listaPesajes.size() > 0) {
                //<editor-fold defaultstate="collapsed" desc="ENCABEZADO">
                Encabezado.add("NUM");
                Encabezado.add("NUM_MAMA");
                Encabezado.add("NUM_HIJO");
                Encabezado.add("SEXO");
                Encabezado.add("HIERRO");
                Encabezado.add("FEC_DESTETE");
                Encabezado.add("PESO_ANT");
                Encabezado.add("TIPO_PESAJE");
                Encabezado.add("PESO");
                Encabezado.add("DIF");
                if (listaMedicamentos.size() > 0) {
                    for (Map<String, String> med : listaMedicamentos) {
                        Encabezado.add("MED_" + med.get("DESCRIPCION"));
                    }
                }
//</editor-fold>
                int fila = 0;
                for (Map<String, String> pes : listaPesajes) {
                    ArrayList listAux = new ArrayList();
                    fila++;

                    listAux.add("" + fila);
                    listAux.add("" + pes.get("NUMERO_MAMA"));
                    listAux.add("" + pes.get("NUM_ANIMAL"));
                    listAux.add("" + pes.get("SEXO"));
                    listAux.add("" + pes.get("HIERRO"));
                    listAux.add("" + pes.get("FEC_DESTETE"));
                    listAux.add("" + pes.get("PESO"));
                    listAux.add("");
                    listAux.add("");

                    listAux.add("IFERROR(IF((UPPER(MID(H" + (fila + 1) + ",1,1))=\"K\"),I" + (fila + 1) + "-G" + (fila + 1) + ",ROUND((I" + (fila + 1) + "/2.20462),0)-G" + (fila + 1) + "),\"\")");

                    for (int i = 0; i < 20; i++) {
                        listAux.add(" ");
                    }

                    ListaDatos.add(listAux);
                }
                System.out.println("COMIENZO DE ARCHIVO");
                String nombreArchivo = "ArchivoPesaje_" + complemento + ".xlsx";
                controlArchivo.EscribirExcelActFormula(Parametros.RutaWindows, nombreArchivo, Encabezado, ListaDatos, 9);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CrearArchivoPalpacion(Map<String, Object> datos) {
        try {
            String consultaPalpacion = "SELECT anim.`id` AS ID, anim.`numero` AS NUM_ANIMAL, \n"
                    + "IF(anim.numero_mama_adoptiva IS NULL OR anim.numero_mama_adoptiva = '', anim.numero_mama, anim.numero_mama_adoptiva) AS NUMERO_MAMA,\n"
                    + "IFNULL(NumeroPartos(anim.numero), '0') NUM_PARTOS, \n"
                    + "hie.`descripcion` AS HIERRO, \n"
                    + "IF(anim.`fecha_novilla`>'1900-01-01', DATE_FORMAT(anim.`fecha_novilla`, '%d/%m/%Y'), '') FEC_NOVILLA,\n"
                    + "IFNULL(DATE_FORMAT(NumeroHijos(anim.numero, 1), '%d/%m/%Y'), '') 'F.U.P', \n"
                    + "IFNULL(SUBSTRING(palp.`diagnostico`,1,1), '') ESTADO, IFNULL(DATE_FORMAT(palp.`fecha_palpacion`, '%d/%m/%Y'), '') FEC_PALP,\n"
                    + "REPLACE(GROUP_CONCAT(IFNULL(CONCAT(trat.`dosis`, ' ', med.`descripcion`), '')), ',', ', ') TRATAMIENTO,\n"
                    + " `NumMeses`(palp.`diagnostico`, anim.`numero`, palp.`fecha_palpacion`) NUM_MESES\n"
                    + "FROM animales anim\n"
                    + "LEFT JOIN `palpacion` palp ON palp.`id_animal` = anim.id AND palp.`estado` = 'Activo'\n"
                    + "LEFT JOIN `palpacionxtratamiento` trat ON trat.`id_palpacion` = palp.`id`\n"
                    + "LEFT JOIN `medicamentos` med ON med.`id` = trat.`id_medicamento`\n"
                    + "INNER JOIN `propietarioxhierro` hie ON hie.`id` = anim.`hierro`\n"
                    + "INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n"
                    + "WHERE anim.`id_tipo_animal` = '" + datos.get("IDTIPO") + "' AND tpo.`id_finca` = '" + datos.get("IDFINCA") + "'\n"
                    + "AND anim.grupo IN (" + datos.get("GRUPOS") + ")\n" + // 
                    "GROUP BY anim.`id`\n"
                    + "ORDER BY anim.id ASC";

            String consultaMedicamentos = "";
            List<Map<String, String>> listaMedicamentos = new ArrayList<>();

            if (!datos.get("MEDICAMENTOS").equals("")) {
                consultaMedicamentos = "SELECT id AS ID, descripcion AS DESCRIPCION\n"
                        + "FROM `medicamentos`\n"
                        + "WHERE id IN (" + datos.get("MEDICAMENTOS") + ")";

                listaMedicamentos = mySQL.ListSQL(consultaMedicamentos);
            }

            List<Map<String, String>> listaPalpacion = mySQL.ListSQL(consultaPalpacion);

            if (listaPalpacion.size() > 0) {
                //<editor-fold defaultstate="collapsed" desc="ENCABEZADO">
                Encabezado.add("CHECK");
                Encabezado.add("NUM");
                Encabezado.add("NUM_MAMA");
                Encabezado.add("NUM_HIJO");
                Encabezado.add("PARTO");
                Encabezado.add("HIERRO");
                Encabezado.add("FEC_NOVILLA");
                Encabezado.add("F.U.P");
                Encabezado.add("ULT_PALPACION");
                Encabezado.add("ESTADO_ANT");
                Encabezado.add("NUM_MESES_ANT");
                Encabezado.add("TRATAMIENTO");
                Encabezado.add("ESTADO");
                Encabezado.add("NUM_MESES");
                if (listaMedicamentos.size() > 0) {
                    for (Map<String, String> med : listaMedicamentos) {
                        Encabezado.add("MED_" + med.get("DESCRIPCION"));
                    }
                }
//</editor-fold>
                int fila = 0;
                for (Map<String, String> palp : listaPalpacion) {
                    ArrayList listAux = new ArrayList();
                    fila++;

                    listAux.add("");
                    listAux.add("" + fila);
                    listAux.add("" + palp.get("NUMERO_MAMA"));
                    listAux.add("" + palp.get("NUM_ANIMAL"));
                    listAux.add("" + palp.get("NUM_PARTOS"));
                    listAux.add("" + palp.get("HIERRO"));
                    listAux.add("" + palp.get("FEC_NOVILLA"));
                    listAux.add("" + palp.get("F.U.P"));
                    listAux.add("" + palp.get("FEC_PALP"));
                    listAux.add("" + palp.get("ESTADO"));
                    listAux.add("" + (palp.get("NUM_MESES").equals("0") ? "" : palp.get("NUM_MESES")));
                    listAux.add("" + palp.get("TRATAMIENTO"));
                    listAux.add("");
                    listAux.add("");

//                    if(listaMedicamentos.size() > 0){
                    for (int i = 0; i < 20; i++) {
                        listAux.add(" ");
                    }
//                    }

                    ListaDatos.add(listAux);
                }
                String nombreArchivo = "ArchivoPalpacion_" + complemento + ".xlsx";
                controlArchivo.EscribirExcelAct(Parametros.RutaWindows, nombreArchivo, Encabezado, ListaDatos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CrearArchivoAnimalesXFinca(Map<String, Object> datos){
        try{
            Map<String, String> infBasica = (Map<String, String>)datos.get("basica");
            ArrayList<String> infAdicional = (ArrayList<String>)datos.get("adicional");
            
            String consulta = "select finc.`descripcion` as FINCA, tpo.`descripcion` as TIPO_ANIMAL, anim.`numero` as NUM_ANIMAL,\n" +
                                "anim.`calificacion` as CALIFICACION, anim.`capado` as CAPADO, anim.`descornado` as DESCORNADO, anim.`descripcion_muerte` as DESCRIPCION_MUERTE,\n" +
                                "anim.`destete` as DESTETE, anim.`fecha_destete` as FECHA_DESTETE, anim.`fecha_muerte` as FECHA_MUERTE,\n" +
                                "anim.`fecha_nacimiento` as FECHA_NACIMIENTO, anim.`fecha_novilla` as FECHA_NOVILLA, anim.`fecha_venta` as FECHA_VENTA,\n" +
                                "grup.`descripcion` as GRUPO, anim.`hierro` as HIERRO, anim.`hierro_fisico` as HIERRO_FISICO, anim.`implante` as IMPLANTE,\n" +
                                "anim.`muerte` as MUERTE, anim.`notas` as NOTAS, anim.`numero_descendiente` as NUMERO_DESCENDIENTE, anim.`numero_mama` as NUMERO_MAMA,\n" +
                                "anim.`numero_mama_adoptiva` as NUMERO_MAMA_ADOPTIVA, anim.`peso` as PESO, anim.`peso_canal` as PESO_CANAL, anim.`peso_destete` as PESO_DESTETE,\n" +
                                "anim.`precio_venta` as PRECIO_VENTA, anim.`tipo_venta` as TIPO_VENTA, anim.`genero` as SEXO, anim.`venta` as VENTA\n" +
                                "from `animales` anim\n" +
                                "inner join grupos grup on grup.`id` = anim.`grupo`\n" +
                                "inner join `tipo_animales` tpo on tpo.`id` = anim.`id_tipo_animal`\n" +
                                "inner join `fincas` finc on finc.`id` = tpo.`id_finca`\n" +
                                "where tpo.`id` = '"+infBasica.get("IDTIPO")+"' and finc.`id` = '"+infBasica.get("IDFINCA")+"' \n" + 
                                "and grup.`id` in ("+infBasica.get("GRUPOS")+")\n" +
                                "order by anim.`numero` asc";

            List<Map<String, String>> listaAnimales = mySQL.ListSQL(consulta);
            
            if (listaAnimales.size() > 0) {
                //<editor-fold defaultstate="collapsed" desc="ENCABEZADO">
                Encabezado.add("NUM");
                Encabezado.add("FINCA");
                Encabezado.add("TIPO ANIMAL");
                Encabezado.add("NUMERO ANIMAL");
                if (infAdicional.size() > 0) {
                    for(String adi : infAdicional){
                        Encabezado.add("" +adi.toUpperCase()); 
                    }
                }
//</editor-fold>
                int fila = 0;
                for (Map<String, String> anim : listaAnimales) {
                    ArrayList listAux = new ArrayList();
                    fila++;

                    listAux.add("" + fila);
                    listAux.add("" + anim.get("FINCA"));
                    listAux.add("" + anim.get("TIPO_ANIMAL"));
                    listAux.add("" + anim.get("NUM_ANIMAL"));
                    
                    if (infAdicional.size() > 0) {
                        for(String adi : infAdicional){
                             listAux.add("" + anim.get(""+adi.toUpperCase().replace(" ", "_")));
                        }
                    }


                    ListaDatos.add(listAux);
                }
                String nombreArchivo = "ArchivoInformacionAnimales_" + complemento + ".xlsx";
                controlArchivo.EscribirExcelAct(Parametros.RutaWindows, nombreArchivo, Encabezado, ListaDatos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void CrearArchivoAnimal() {
        try {
            Encabezado.add("NUM_ANIMAL");
            Encabezado.add("NUM_MADRE");
            Encabezado.add("SEXO");
            Encabezado.add("HIERRO");
            Encabezado.add("HIERRO_C");
            Encabezado.add("NUM_PARTOS");
            Encabezado.add("CAPADO");
            Encabezado.add("DESCORNADO");
            Encabezado.add("IMPLANTE");
            Encabezado.add("PESO");
            Encabezado.add("FEC_NACIMIENTO");
            Encabezado.add("DESTETE");
            Encabezado.add("FEC_DESTETE");
            Encabezado.add("PESO_DESTETE");
            Encabezado.add("NOTAS");
            Encabezado.add("GRUPO");

            System.out.println("COMIENZO DE ARCHIVO");
            String nombreArchivo = "ArchivoCargaMasivaAnimales_" + complemento + ".xlsx";
            ArrayList<ArrayList<String>> datos = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < 300; i++) {
                ArrayList<String> d = new ArrayList<>();
                for (int j = 0; j < Encabezado.size(); j++) {
                    d.add(" ");
                }
                datos.add(d);
            }
            controlArchivo.EscribirExcelAct(Parametros.RutaWindows, nombreArchivo, Encabezado, datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
