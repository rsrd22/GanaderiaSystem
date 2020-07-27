/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excel;

import Utilidades.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import org.apache.poi.ss.usermodel.DateUtil;
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
                Iterator rows  = sheet.rowIterator();
                while (rows.hasNext()) {
//                    System.out.println("*****************************************");
                    col=0;
                    XSSFRow row = (XSSFRow) rows.next();
                    Iterator iterator = row.cellIterator();
                    obj = new HashMap<String, String>();
                    while (iterator.hasNext()) {
                        XSSFCell xssfCell = (XSSFCell) iterator.next();
                        if(col >= keys.size() && k == 1){
                            break;
                        }  
                        if(k == 0){
                            keys.add(""+xssfCell.toString());
                        }else{
                            if(XSSFCell.CELL_TYPE_NUMERIC == xssfCell.getCellType() ){
                                if(DateUtil.isCellDateFormatted(xssfCell)){
                                    String value = destFormat.format(xssfCell.getDateCellValue());
                                    obj.put(keys.get(col), ""+value);
                                }else{
                                    obj.put(keys.get(col), ""+((long) xssfCell.getNumericCellValue()));
                                }
                            }else{
                                conten = xssfCell.getStringCellValue();
                                if(conten.isEmpty() || conten.equals("null")){
                                    conten = "_";
                                }
                                obj.put(keys.get(col), ""+Utilidades.CodificarElemento(conten));
                            }
                        }
                        col++;
                    }
                    
                    if(k == 0){
                        k =1;
                    }else{
                        if(!obj.isEmpty())
                            campos.add(obj);
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

}
