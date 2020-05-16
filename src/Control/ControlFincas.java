/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlFincas implements IControl{
    private gestorMySQL mySQL;
    private ArrayList<ModeloFincas> listaModelo;
    
    public ControlFincas() {
        this.mySQL = new gestorMySQL();
        listaModelo = new ArrayList<>();
    }
    
    public Object ObtenerDatosObject() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public int Guardar(Object _finca) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloFincas finca = (ModeloFincas) _finca;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `fincas`\n" +
                    "(`id`, `descripcion`, `area`, `direccion`, `id_propietario`, `fecha`, `id_usuario`, id_tipo_moneda)\n VALUES (\n" +
                    ""+finca.getId()+", "+
                    "'"+finca.getDescripcion()+"', "+
                    ""+finca.getAreat()+", "+
                    "'"+finca.getDireccion()+"', "+
                    ""+finca.getId_propietario()+", "+
                    ""+finca.getFecha()+", "+
                    ""+finca.getIdUsuario()+", "+
                    ""+finca.getIdTipoMoneda()+");"
        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
                return Retorno.ERROR;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.CLASE_NO_ENCONTRADA;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.EXCEPCION_SQL;
        }
    }

    @Override
    public int Actualizar(Object _finca) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloFincas finca = (ModeloFincas) _finca;

        consultas.add(
            //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE `fincas`\n" +
                    "SET `descripcion` = '"+finca.getDescripcion()+"',\n" +
                    "  `area` = "+finca.getAreat()+",\n" +
                    "  `direccion` = '"+finca.getDireccion()+"',\n" +
                    "  `id_propietario` = "+finca.getId_propietario()+",\n" +
                    "  `fecha` = "+finca.getFecha()+",\n" +
                    "  `id_usuario` = "+finca.getIdUsuario()+",\n" +
                    "  `id_tipo_moneda` = "+finca.getIdTipoMoneda()+"\n" +
                    "WHERE `id` = "+finca.getId() +""
        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
                return Retorno.ERROR;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.CLASE_NO_ENCONTRADA;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.EXCEPCION_SQL;
        }
    }

    @Override
    public int Eliminar(Object _finca) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloFincas finca = (ModeloFincas) _finca;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM fincas\n"
                + "WHERE id = " + finca.getId()
        //</editor-fold>
        );

        try {
            if(mySQL.EnviarConsultas(consultas)){
                return Retorno.EXITO;
            }else{
                return Retorno.ERROR;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.CLASE_NO_ENCONTRADA;
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            return Retorno.EXCEPCION_SQL;
        }
    }

    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String id) {
        listaModelo = new ArrayList<>();
        
        String consulta = "SELECT fnc.id AS ID, fnc.descripcion AS DESCRIPCION, \n" +
                            "`MascaraMonedaDecimal`(REPLACE(fnc.area, '.', ',')) AS AREAT, fnc.`direccion` AS DIRECCION, \n" +
                            "prop.`id` AS ID_PROPIETARIO,\n" +
                            "CONCAT_WS(' ', prop.`primer_nombre`, IFNULL(prop.`segundo_nombre`, ''), prop.`primer_apellido`, IFNULL(prop.`segundo_apellido`, '')) AS PROPIETARIO, ifnull(fnc.id_tipo_moneda, 0) AS IDTIPOMONEDA\n" +
                            "FROM fincas fnc\n" +
                            "INNER JOIN `propietarios` prop ON prop.`id` = fnc.`id_propietario`\n" +
                            "WHERE fnc.`id` = '"+id+"'\n" +
                            "ORDER BY id ASC";
        
        List<Map<String, String>> fincas = new ArrayList<Map<String, String>>();
        fincas = mySQL.ListSQL(consulta);

        if (fincas.size() > 0) {

            for (Map<String, String> finca : fincas) {
                listaModelo.add(new ModeloFincas(
                    finca.get("ID"),
                    finca.get("DESCRIPCION"),
                    finca.get("DIRECCION"),
                    finca.get("AREAT"),
                    finca.get("ID_PROPIETARIO"),
                    finca.get("PROPIETARIO"),
                    finca.get("FECHA"),
                    finca.get("ID_USUARIO"),
                    finca.get("IDTIPOMONEDA")
                ));
            }
            return listaModelo;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    public Object ObtenerBloquesxFinca(String idFinca){
        String consulta = "SELECT *\n" +
                            "FROM `bloques`\n" +
                            "WHERE id_finca = '"+idFinca+"'\n" +
                            "ORDER BY numero ASC";
        
        List<Map<String, String>> bloques = new ArrayList<Map<String, String>>();
        bloques = mySQL.ListSQL(consulta);
        ArrayList<ModeloBloques> listaModeloBloques = new ArrayList<>();
        if (bloques.size() > 0) {

            for (Map<String, String> bloque : bloques) {
                listaModeloBloques.add(new ModeloBloques(
                    bloque.get("id"),
                    bloque.get("numero"),
                    bloque.get("id_finca"),
                    bloque.get("area"),
                    bloque.get("fecha"),
                    bloque.get("id_usuario")
                ));
            }
            return listaModeloBloques;
        } else {
            return new ArrayList<>();
        }
    }
    
    public Object ObtenerLotesxFinca(String idFinca){
        String consulta = "SELECT lts.id AS ID, lts.`numero` AS NUMERO, lts.`id_bloque` AS ID_BLOQUE,  blqs.numero AS NUMERO_BLOQUE,\n" +
                            "lts.`id_fuente_hidrica` AS ID_FUENTE_HIDRICA, lts.`area` AS AREAT, lts.`fecha` AS FECHA, lts.`id_usuario` AS ID_USUARIO\n" +
                            "FROM lotes lts\n" +
                            "INNER JOIN `bloques` blqs ON blqs.`id` = lts.`id_bloque`\n" +
                            "WHERE blqs.id_finca = '"+idFinca+"'\n" +
                            "ORDER BY blqs.`numero`, lts.numero ASC";
        consulta = "SELECT lts.id AS ID, lts.`numero` AS NUMERO, lts.`id_bloque` AS ID_BLOQUE,  blqs.numero AS NUMERO_BLOQUE,\n" +
                    "lts.`area` AS AREAT, ltxhc.`id_fuente_hidrica` AS ID_FUENTE_HIDRICA, fh.`descripcion` AS FUENTE_HIDRICA,\n" +
                    "lts.`fecha` AS FECHA, lts.`id_usuario` AS ID_USUARIO\n" +
                    "FROM lotes lts\n" +
                    "INNER JOIN `lotexfuente_hidrica` ltxhc ON `ltxhc`.`id_lote` = lts.`id`\n" +
                    "INNER JOIN `bloques` blqs ON blqs.`id` = lts.`id_bloque`\n" +
                    "INNER JOIN `fuentes_hidricas` fh ON fh.`id` = ltxhc.`id_fuente_hidrica`\n" +
                    "WHERE blqs.id_finca = '"+idFinca+"'\n" +
                    "GROUP BY lts.`id`\n" +
                    "ORDER BY blqs.`numero`, lts.numero ASC";
        
        List<Map<String, String>> lotes = new ArrayList<Map<String, String>>();
        lotes = mySQL.ListSQL(consulta);
        ArrayList<ModeloLotes> listaModeloLotes = new ArrayList<>();
        if (lotes.size() > 0) {

            for (Map<String, String> lote : lotes) {
                listaModeloLotes.add(new ModeloLotes(
                    lote.get("ID"),
                    lote.get("NUMERO"),
                    lote.get("ID_BLOQUE"),
                    lote.get("NUMERO_BLOQUE"),
                    lote.get("ID_FUENTE_HIDRICA"),
                    lote.get("FUENTE_HIDRICA"),
                    lote.get("AREAT"),
                    lote.get("FECHA"),
                    lote.get("ID_USUARIO")
                ));
            }
            return listaModeloLotes;
        } else {
            return new ArrayList<>();
        }
    }
    
    public Object ObtenerPluviometroxFinca(String idFinca){
        String consulta = "SELECT `id` AS ID, `id_finca` AS IDFINCA, DATE_FORMAT(fecha_registro, '%d/%m/%Y') FECHAREGISTRO,\n" +
                            "`cantidad` AS CANTIDAD, `id_usuario` AS IDUSUARIO, `fecha` AS FECHA\n" +
                            "FROM `pluviometro`\n" +
                            "WHERE id_finca = '"+idFinca+"'\n" +
                            "ORDER BY id ASC";
        
        List<Map<String, String>> pluviometros = new ArrayList<Map<String, String>>();
        pluviometros = mySQL.ListSQL(consulta);
        ArrayList<ModeloPluviometro> listaModeloPluviometro = new ArrayList<>();
        if (pluviometros.size() > 0) {

            for (Map<String, String> pluvi : pluviometros) {
                listaModeloPluviometro.add(new ModeloPluviometro(
                    pluvi.get("ID"),
                    pluvi.get("IDFINCA"),
                    pluvi.get("FECHAREGISTRO"),
                    pluvi.get("CANTIDAD"),
                    pluvi.get("IDUSUARIO"),
                    pluvi.get("FECHA")
                ));
            }
            return listaModeloPluviometro;
        } else {
            return new ArrayList<>();
        }
    }
}
