/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Configuracion.ConfiguracionPropiedades;
import Utilidades.Expresiones;
import Utilidades.Utilidades;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author MERRY
 */
public class ControlArchivo {

    public ControlArchivo() {

    }

    public List<Map<String, String>> LeerExcelAct(String ruta) {
        try {
            List<Map<String, String>> campos = new ArrayList<Map<String, String>>();
            List<String> keys = new ArrayList<>();
            FileInputStream fileInput = new FileInputStream(new File(ruta));
            XSSFWorkbook book = new XSSFWorkbook(fileInput);
            String dat = "";
            String[] datos = null;
            String data = "";

            int col = 0;
            int k = 0;
            Map<String, String> obj = new HashMap<String, String>();
            DateFormat destFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int sheetNo = 0; sheetNo < 1; sheetNo++) {
                XSSFSheet sheet = book.getSheetAt(sheetNo);
                String conten = "";
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
//                    System.out.println("*****************************************");
                    col = 0;
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator iterator = row.cellIterator();
                    obj = new HashMap<String, String>();
                    while (iterator.hasNext()) {
                        XSSFCell xssfCell = (XSSFCell) iterator.next();
                        if (col >= keys.size() && k == 1) {
                            break;
                        }
                        if (k == 0) {
                            keys.add("" + xssfCell.toString());
                        } else {
                            if (XSSFCell.CELL_TYPE_NUMERIC == xssfCell.getCellType()) {
                                if (DateUtil.isCellDateFormatted(xssfCell)) {
                                    String value = destFormat.format(xssfCell.getDateCellValue());
                                    obj.put(keys.get(col), "" + value);
                                } else {
                                    obj.put(keys.get(col), "" + ((long) xssfCell.getNumericCellValue()));
                                }
                            } else {
                                conten = xssfCell.getStringCellValue();
                                if (conten.isEmpty() || conten.equals("null")) {
                                    conten = "_";
                                }
                                obj.put(keys.get(col), "" + Utilidades.CodificarElemento(conten));
                            }
                        }
                        col++;
                    }

                    if (k == 0) {
                        k = 1;
                    } else {
                        if (!obj.isEmpty()) {
                            campos.add(obj);
                        }
                    }

                }
            }
            return campos;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public List<Map<String, String>> LeerExcel(String ruta) {
        try {

            List<Map<String, String>> campos = new ArrayList<Map<String, String>>();
            //<editor-fold defaultstate="collapsed" desc="SETTINGS">
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setEncoding("ISO-8859-1");
            wbSettings.setLocale(new Locale("es", "ES"));
            wbSettings.setExcelDisplayLanguage("ES");
            wbSettings.setExcelRegionalSettings("ES");
            wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
//</editor-fold>
            Workbook archivoExcel = Workbook.getWorkbook(new File(ruta), wbSettings);
            String dat = "";
            String[] datos = null;
            String data = "";
            int numColumnas = 0;

            for (int sheetNo = 0; sheetNo < 1; sheetNo++) {
                Sheet hoja = archivoExcel.getSheet(sheetNo);
                numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                datos = new String[numFilas];
                boolean ban = false;
                String conten = "", sinCod = "";

                DateFormat destFormat = new SimpleDateFormat("dd/MM/yyyy");
                Map<String, String> obj = new HashMap<String, String>();
                for (int fila = 1; fila < numFilas; fila++) {
                    // Recorre cada fila de la hoja
                    obj = new HashMap<String, String>();
                    for (int columna = 0; columna < numColumnas; columna++) {
                        // Recorre cada columna de la hoja

                        if (hoja.getCell(0, fila).getContents().equals("")) {
                            ban = true;
                            break;
                        }
                        Cell e1 = hoja.getCell(columna, 0);
                        Cell a1 = hoja.getCell(columna, fila);

                        if (a1.getType() == CellType.DATE) {
                            DateCell dc = (DateCell) a1;
                            String value = destFormat.format(dc.getDate());
                            conten = value;
                        } else {
                            if (a1.getContents().equals("")) {
                                conten = "_";
                            } else {
                                conten = a1.getContents();
                            }
                        }
                        //obj.put(e1.getContents(), general.CodificarHTML(conten.trim()));
                        obj.put(e1.getContents(), conten.trim());
                    }
                    if (ban) {
                        break;
                    }
                    campos.add(obj);
                }
            }
            return campos;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public List<Map<String, String>> LeerExcelAct(String ruta, String[] keysConf) {
        try {
            List<Map<String, String>> campos = new ArrayList<Map<String, String>>();
            List<String> keys = new ArrayList<>();
            FileInputStream fileInput = new FileInputStream(new File(ruta));
            XSSFWorkbook book = new XSSFWorkbook(fileInput);
            String dat = "";
            String[] datos = null;
            String data = "";

            int col = 0;
            int k = 0;
            Map<String, String> obj = new HashMap<String, String>();
            DateFormat destFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (int sheetNo = 0; sheetNo < 1; sheetNo++) {
                XSSFSheet sheet = book.getSheetAt(sheetNo);
                String conten = "";
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {

                    col = 0;
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator iterator = row.cellIterator();
                    obj = new HashMap<String, String>();
                    while (iterator.hasNext()) {
                        XSSFCell xssfCell = (XSSFCell) iterator.next();
                        if (col >= keys.size() && k == 1) {
                            break;
                        }
                        if (k == 0) {
                            keys.add("" + xssfCell.toString());
                        } else {
                            if (XSSFCell.CELL_TYPE_FORMULA == xssfCell.getCellType()) {
                                String value = xssfCell.getRawValue();
                                System.out.println("value-->" + value);
                                obj.put(keys.get(col), "" + value);
                            } else if (XSSFCell.CELL_TYPE_NUMERIC == xssfCell.getCellType()) {
                                if (DateUtil.isCellDateFormatted(xssfCell)) {
                                    String value = destFormat.format(xssfCell.getDateCellValue());
                                    obj.put(keys.get(col), "" + value);
                                    System.out.println("value-->" + value);
                                } else {
                                    conten = "" + xssfCell.getNumericCellValue();
                                    conten = "" + xssfCell.getRawValue();
                                    conten = conten.replace(",", ".");
                                    if (conten.indexOf(".") > -1) {
                                        obj.put(keys.get(col), "" + Double.parseDouble(conten));
                                    } else {
                                        obj.put(keys.get(col), "" + Integer.parseInt(conten));
                                    }
                                }
                            } else {
                                conten = xssfCell.getStringCellValue();
                                conten = conten.trim();

                                System.out.println("keys.get(" + col + ")---" + keys.get(col));

                                if (conten.isEmpty() || conten.equals("null")) {
                                    conten = "_";
                                }
                                System.out.println("conten---" + conten);
                                obj.put(keys.get(col), "" + Utilidades.CodificarElemento(conten));
                            }
                        }
                        col++;
                    }

                    if (k == 0) {
                        k = 1;
                    } else {
                        if (!obj.isEmpty()) {
                            campos.add(obj);
                        }
                    }
                }
            }

            boolean encontrado = false;
            if (keysConf.length > 0 && keys.size() > 0) {
                for (int i = 0; i < keysConf.length; i++) {
                    for (String key : keys) {
                        if (key.equals(keysConf[i])) {
                            encontrado = true;
                            break;
                        } else {
                            encontrado = false;
                        }
                    }
                    if (!encontrado) {
                        JOptionPane.showMessageDialog(null, "El archivo no es el esperado por el sistema.");
                        return null;
                    }
                }
            }

            return campos;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    /**
     * *
     * @param ruta
     * @param nombreArchivo
     * @param Encabezado
     * @param ListaDatos
     * @param colFormula
     */
    public void EscribirExcelActFormula(String ruta, String nombreArchivo, ArrayList<String> Encabezado, ArrayList<ArrayList<String>> ListaDatos, int colFormula) {
        ruta = Expresiones.guardarEn();
        String rutaArchivo = ruta + "\\" + nombreArchivo;
        String hoja = "Hoja1";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        XSSFRow row = hoja1.createRow(0);//se crea las filas
        //<editor-fold defaultstate="collapsed" desc="ENCABEZADO">
        for (int i = 0; i < Encabezado.size(); i++) {
            XSSFCell cell = row.createCell(i);//se crea las celdas para la cabecera, junto con la posici�n
            cell.setCellStyle(style); // se a�ade el style crea anteriormente 
            cell.setCellValue(Encabezado.get(i));//se a�ade el contenido
        }
//</editor-fold>
        System.out.println("***********END ENCABEZADO**********");
        //<editor-fold defaultstate="collapsed" desc="CONTENIDO">
        for (int fila = 0; fila < ListaDatos.size(); fila++) {//FILAS
            System.out.println("FILA::.---" + fila);
            row = hoja1.createRow(fila + 1);//se crea las filas
            for (int col = 0; col < ListaDatos.get(fila).size(); col++) {//COLUMNAS
                XSSFCell cell = row.createCell(col);//se crea las celdas para la contenido, junto con la posici�n
                if (col == colFormula) {
                    cell.setCellFormula(ListaDatos.get(fila).get(col));
                } else {
                    cell.setCellValue(ListaDatos.get(fila).get(col)); //se a�ade el contenido
                }
                hoja1.autoSizeColumn(col);
            }
        }
        System.out.println("***********END CONTENIDO**********");
//</editor-fold>

        System.out.println("rutaArchivo:.::" + rutaArchivo);
        try (OutputStream fileOut = new FileOutputStream(rutaArchivo)) {
            libro.write(fileOut);
            File archivo = new File(rutaArchivo);
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "El archivo se genero exitosamente\n¿Desea ver el archivo " + nombreArchivo + "?\n"
            );
            if (opcion == JOptionPane.YES_NO_OPTION) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * *
     * @param ruta
     * @param nombreArchivo
     * @param Encabezado
     * @param ListaDatos
     */
    public void EscribirExcelAct(String ruta, String nombreArchivo, ArrayList<String> Encabezado, ArrayList<ArrayList<String>> ListaDatos) {
        ruta = Expresiones.guardarEn();
        String rutaArchivo = ruta + "\\" + nombreArchivo;
        String hoja = "Hoja1";

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja1 = libro.createSheet(hoja);

        //poner negrita a la cabecera
        CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
        XSSFRow row = hoja1.createRow(0);//se crea las filas
        //<editor-fold defaultstate="collapsed" desc="ENCABEZADO">
        for (int i = 0; i < Encabezado.size(); i++) {
            XSSFCell cell = row.createCell(i);//se crea las celdas para la cabecera, junto con la posici�n
            cell.setCellStyle(style); // se a�ade el style crea anteriormente 
            cell.setCellValue(Encabezado.get(i));//se a�ade el contenido
            hoja1.autoSizeColumn(i);
        }
//</editor-fold>
        System.out.println("***********END ENCABEZADO**********");
        //<editor-fold defaultstate="collapsed" desc="CONTENIDO">
        for (int fila = 0; fila < ListaDatos.size(); fila++) {//FILAS
            System.out.println("FILA::.---" + fila);
            row = hoja1.createRow(fila + 1);//se crea las filas
            for (int col = 0; col < ListaDatos.get(fila).size(); col++) {//COLUMNAS
                XSSFCell cell = row.createCell(col);//se crea las celdas para la contenido, junto con la posici�n
                cell.setCellValue(ListaDatos.get(fila).get(col)); //se a�ade el contenido
                hoja1.autoSizeColumn(col);
            }
        }
        System.out.println("***********END CONTENIDO**********");
//</editor-fold>

        System.out.println("rutaArchivo:.::" + rutaArchivo);

        try (OutputStream fileOut = new FileOutputStream(rutaArchivo)) {
            libro.write(fileOut);
            File archivo = new File(rutaArchivo);
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    "El archivo se genero exitosamente\n¿Desea ver el archivo " + nombreArchivo + "?\n"
            );
            if (opcion == JOptionPane.YES_NO_OPTION) {
                Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try (FileOutputStream fileOuS = new FileOutputStream(file)) {
//            System.out.println("Dentro del Try");
//            if (file.exists()) {// si el archivo existe se elimina
//                file.delete();
//                System.out.println("Archivo eliminado");
//            }
//            libro.write(fileOuS);
//            fileOuS.flush();
//            fileOuS.close();
//            
//            int opcion = JOptionPane.showConfirmDialog(null, "�Desea ver el documento " + nombreArchivo + "?\n");
//            if (opcion == JOptionPane.YES_NO_OPTION) {
//                Desktop.getDesktop().open(file);
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
