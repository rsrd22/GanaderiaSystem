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
        cal=Calendar.getInstance();
        double random = Math.random();
        complemento=(""+random).substring(2,8)+"_"+(sdf.format(cal.getTime()));
    }
    
    public void GenerarInformes(Object _info){
        ModeloInformes informe = (ModeloInformes) _info; 
        Map<String, String> datos = informe.informacion;
        if(informe.categoria.equals("0")){//ARCHIVO EXCEL
            controlArchivo = new ControlArchivo();
            if(informe.informe.equals("0")){// PESAJE
                CrearArchivoPesaje(datos);
            }else if(informe.informe.equals("1")){// PALPACION
                CrearArchivoPalpacion(datos);
            }else{//ANIMAL
                CrearArchivoAnimal();
            }
        }else{
            
        }
        
    }

    private void CrearArchivoPesaje(Map<String, String> datos) {
        try{
            String consultaPesaje = "SELECT anim.`id` AS ID, anim.`numero` AS NUM_ANIMAL, \n" +
                                    "IF(anim.numero_mama_adoptiva IS NULL OR anim.numero_mama_adoptiva = '', anim.numero_mama, anim.numero_mama_adoptiva) AS NUMERO_MAMA,\n" +
                                    "anim.`peso` AS PESO, hie.`descripcion` AS HIERRO, anim.`genero` AS SEXO, IF(anim.`fecha_destete`>'1900-01-01', DATE_FORMAT(anim.`fecha_destete`, '%d/%m/%Y'), '') FEC_DESTETE\n" +
                                    "FROM `animales` anim\n" +
                                    "INNER JOIN `propietarioxhierro` hie ON hie.`id` = anim.`hierro`\n" +
                                    "INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n" +
                                    "WHERE anim.`id_tipo_animal` = '"+datos.get("IDTIPO")+"' AND tpo.`id_finca` = '"+datos.get("IDFINCA")+"'\n" +
                                    ""+(datos.get("SEXO").equals("")?"":"AND anim.`genero` = '"+datos.get("SEXO")+"' \n")+
                                    "AND anim.grupo IN ("+datos.get("GRUPOS")+") \n"+
                                    "ORDER BY anim.genero DESC";
            
            
            String consultaMedicamentos = "";
            List<Map<String, String>> listaMedicamentos = new ArrayList<>();
            if(!datos.get("MEDICAMENTOS").equals("")){
                consultaMedicamentos = "SELECT id AS ID, descripcion AS DESCRIPCION\n" +
                                    "FROM `medicamentos`\n" +
                                    "WHERE id IN ("+datos.get("MEDICAMENTOS")+")";
                listaMedicamentos = mySQL.ListSQL(consultaMedicamentos);
            }
            
            List<Map<String, String>> listaPesajes = mySQL.ListSQL(consultaPesaje);
            
            System.out.println("listaPesajes-->"+listaPesajes.size());
            System.out.println("listaMedicamentos-->"+listaMedicamentos.size());
            if(listaPesajes.size()>0){
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
                if(listaMedicamentos.size() > 0){
                    for (Map<String, String> med : listaMedicamentos) {
                        Encabezado.add("MED_"+med.get("DESCRIPCION"));
                    }
                }
//</editor-fold>
                int fila = 0;
                for (Map<String, String> pes : listaPesajes) {
                    ArrayList listAux = new ArrayList();
                    fila++;
                    
                    listAux.add(""+fila);
                    listAux.add(""+pes.get("NUMERO_MAMA"));  
                    listAux.add(""+pes.get("NUM_ANIMAL"));  
                    listAux.add(""+pes.get("SEXO"));  
                    listAux.add(""+pes.get("HIERRO"));  
                    listAux.add(""+pes.get("FEC_DESTETE"));  
                    listAux.add(""+pes.get("PESO"));  
                    listAux.add("");  
                    listAux.add("");   
//                    listAux.add("IFERROR(I"+(fila+1)+"-G"+(fila+1)+";\"\")");  
                    listAux.add("IFERROR(IF((UPPER(MID(H"+(fila+1)+",1,1))=\"K\"),I"+(fila+1)+"-G"+(fila+1)+",ROUND((I"+(fila+1)+"/2.20462),0)-G"+(fila+1)+"),\"\")");  
//                    listAux.add("MID(H"+(fila+1)+",1,1)");  
                    
//                    if(listaMedicamentos.size() > 0){
//                        for (Map<String, String> med : listaMedicamentos) {
//                            listAux.add(" ");
//                        }
//                    }
                    for (int i = 0; i < 20; i++) {
                        listAux.add(" ");
                    }
                    
                    ListaDatos.add(listAux);
                }
                System.out.println("COMIENZO DE ARCHIVO");
                String nombreArchivo = "ArchivoPesaje_"+complemento+".xlsx";
                controlArchivo.EscribirExcelActFormula(Parametros.RutaWindows, nombreArchivo, Encabezado, ListaDatos, 9);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void CrearArchivoPalpacion(Map<String, String> datos) {
        try{
            String consultaPalpacion = "SELECT anim.`id` AS ID, anim.`numero` AS NUM_ANIMAL, \n" +
                                        "IF(anim.numero_mama_adoptiva IS NULL OR anim.numero_mama_adoptiva = '', anim.numero_mama, anim.numero_mama_adoptiva) AS NUMERO_MAMA,\n" +
                                        "IFNULL(NumeroPartos(anim.numero), '0') NUM_PARTOS, \n" +
                                        "hie.`descripcion` AS HIERRO, \n" +
                                        "IF(anim.`fecha_novilla`>'1900-01-01', DATE_FORMAT(anim.`fecha_novilla`, '%d/%m/%Y'), '') FEC_NOVILLA,\n" +
                                        "IFNULL(DATE_FORMAT(NumeroHijos(anim.numero, 1), '%d/%m/%Y'), '') 'F.U.P', \n" +
                                        "IFNULL(SUBSTRING(palp.`diagnostico`,1,1), '') ESTADO, IFNULL(DATE_FORMAT(palp.`fecha_palpacion`, '%d/%m/%Y'), '') FEC_PALP,\n" +
                                        "REPLACE(GROUP_CONCAT(IFNULL(CONCAT(trat.`dosis`, ' ', med.`descripcion`), '')), ',', ', ') TRATAMIENTO,\n" +
                                        " `NumMeses`(palp.`diagnostico`, anim.`numero`, palp.`fecha_palpacion`) NUM_MESES\n" +
                                        "FROM animales anim\n" +
                                        "LEFT JOIN `palpacion` palp ON palp.`id_animal` = anim.id AND palp.`estado` = 'Activo'\n" +
                                        "LEFT JOIN `palpacionxtratamiento` trat ON trat.`id_palpacion` = palp.`id`\n" +
                                        "LEFT JOIN `medicamentos` med ON med.`id` = trat.`id_medicamento`\n" +
                                        "INNER JOIN `propietarioxhierro` hie ON hie.`id` = anim.`hierro`\n" +
                                        "INNER JOIN `tipo_animales` tpo ON tpo.`id` = anim.`id_tipo_animal`\n" +
                                        "WHERE anim.`id_tipo_animal` = '"+datos.get("IDTIPO")+"' AND tpo.`id_finca` = '"+datos.get("IDFINCA")+"'\n" +
                                        "AND anim.grupo IN ("+datos.get("GRUPOS")+")\n" + // 
                                        "GROUP BY anim.`id`\n" +
                                        "ORDER BY anim.id ASC";
            
            
            String consultaMedicamentos = "";
            List<Map<String, String>> listaMedicamentos = new ArrayList<>();
             
            if(!datos.get("MEDICAMENTOS").equals("")){
                consultaMedicamentos = "SELECT id AS ID, descripcion AS DESCRIPCION\n" +
                                        "FROM `medicamentos`\n" +
                                        "WHERE id IN ("+datos.get("MEDICAMENTOS")+")";
                
                listaMedicamentos = mySQL.ListSQL(consultaMedicamentos);
            }
            
            List<Map<String, String>> listaPalpacion = mySQL.ListSQL(consultaPalpacion);
            
            if(listaPalpacion.size()>0){
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
                if(listaMedicamentos.size() > 0){
                    for (Map<String, String> med : listaMedicamentos) {
                        Encabezado.add("MED_"+med.get("DESCRIPCION"));
                    }
                }
//</editor-fold>
                int fila = 0;
                for (Map<String, String> palp : listaPalpacion) {
                    ArrayList listAux = new ArrayList();
                    fila++;
                    
                    listAux.add("");
                    listAux.add(""+fila);
                    listAux.add(""+palp.get("NUMERO_MAMA"));  
                    listAux.add(""+palp.get("NUM_ANIMAL"));  
                    listAux.add(""+palp.get("NUM_PARTOS"));  
                    listAux.add(""+palp.get("HIERRO"));  
                    listAux.add(""+palp.get("FEC_NOVILLA"));  
                    listAux.add(""+palp.get("F.U.P"));  
                    listAux.add(""+palp.get("FEC_PALP"));  
                    listAux.add(""+palp.get("ESTADO"));  
                    listAux.add(""+(palp.get("NUM_MESES").equals("0")?"":palp.get("NUM_MESES")));  
                    listAux.add(""+palp.get("TRATAMIENTO"));  
                    listAux.add("");  
                    listAux.add("");  
                    
//                    if(listaMedicamentos.size() > 0){
                    for (int i = 0; i < 20; i++) {
                        listAux.add(" ");
                    }
//                    }
                    
                    ListaDatos.add(listAux);
                }
                String nombreArchivo = "ArchivoPalpacion_"+complemento+".xlsx";
                controlArchivo.EscribirExcelAct(Parametros.RutaWindows, nombreArchivo, Encabezado, ListaDatos);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void CrearArchivoAnimal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
