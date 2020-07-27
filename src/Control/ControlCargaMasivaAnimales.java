/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlCargaMasivaAnimales {
    private gestorMySQL mySQL;
    
    public ControlCargaMasivaAnimales(){
        mySQL = new gestorMySQL();
    }
    
    public int GuardarAnimal(Map<String, String> datos){
        ArrayList<String> consultas = new ArrayList<>();
        
        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL ANIMAL">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO animales (id,id_tipo_animal,numero,numero_mama,numero_mama_adoptiva,genero,"
                + "calificacion,notas,fecha_destete,capado,fecha_nacimiento,fecha_venta,"
                + "fecha_muerte,fecha,id_usuario,peso,grupo,hierro,numero_descendiente,estado_descendiente,"
                + "muerte,venta,precio_venta,tipo_venta,peso_canal,descripcion_muerte,"
                + "fecha_novilla,peso_destete,hierro_fisico,implante,descornado)\n"
                + "VALUES (\n"
                + "0,\n"
                + "" + datos.get("IDTIPOANIMAL")+ ",\n"
                + "'" + datos.get("NUM_ANIMAL") + "',\n"
                + "'" + datos.get("NUM_MADRE").replace("_", "") + "',\n"
                + "'',\n"
                + "'" + datos.get("SEXO") + "',\n"
                + "'3',\n"
                + "'" + datos.get("NOTAS").replace("_", "")+ "',\n"
                + "'" + datos.get("FEC_DESTETE") + "',\n"
                + "'" + datos.get("CAPADO") + "',\n"
                + "'" + datos.get("FEC_NACIMIENTO") + "',\n"
                + "'1900-01-01',\n"
                + "'1900-01-01',\n"
                + "NOW(),\n"
                + ""+datosUsuario.datos.get(0).get("ID_USUARIO")+",\n"
                + "" + datos.get("PESO") + ",\n"
                + "" + datos.get("IDGRUPO") + ",\n"
                + "" + datos.get("IDHIERRO") + ",\n"
                + "1,\n"
                + "1,\n"
                + "'0',\n"
                + "'0',\n"
                + "NULL,\n"
                + "NULL,\n"
                + "NULL,\n"
                + "'',\n"
                + "'1900-01-01',\n"
                + "" + datos.get("PESO_DESTETE") + ",\n"
                + "'" + datos.get("HIERRO_C") + "',\n"
                + "'" + datos.get("IMPLANTE") + "',\n"
                + "'" + datos.get("DESCORNADO") + "'\n"
                + ")"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER TRASLADO">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                + ")\n"
                + "VALUES (\n"
                + "0,\n"
                + "(SELECT id FROM animales WHERE numero = '"+datos.get("NUM_ANIMAL")+"'),\n"
                + "" + datos.get("IDFINCA")+ ",\n"
                + "" + datos.get("IDFINCA") + ",\n"
                + "     NOW(),\n"
                + "'"+Utilidades.CodificarElemento("CREACIÓN DEL ANIMAL")+"',\n"
                + "'Activo',\n"
                + " NOW(),\n"
                + "" + datosUsuario.datos.get(0).get("ID_USUARIO") + ")"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER PESO">
        if(!datos.get("PESO").equals("0"))    
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,peso_anterior,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "(SELECT id FROM animales WHERE numero = '"+datos.get("NUM_ANIMAL")+"'),\n"
                + "     NOW(),\n"
                + "" + datos.get("PESO") + ",\n"
                + "0,\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "NOW(),\n"
                + "" + datosUsuario.datos.get(0).get("ID_USUARIO") + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>
        
        try {
            if (mySQL.EnviarConsultas(consultas)) {
                return Retorno.EXITO;
            } else {
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
    
    public int ActualizarAnimal(Map<String, String> datos){
        ArrayList<String> consultas = new ArrayList<>();
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE animales SET\n"
                + "id_tipo_animal = " + datos.get("IDTIPOANIMAL") + ",\n"
                + "numero_mama = '" + datos.get("NUM_MADRE").replace("_", "") + "',\n"
                + "peso = " + datos.get("PESO") + ",\n"
                + "grupo = " + datos.get("IDGRUPO") + ",\n"
                + "hierro = " + datos.get("IDHIERRO") + ",\n"
                + "genero = '" + datos.get("SEXO") + "',\n"
                + "notas = '" + datos.get("NOTAS").replace("_", "") + "',\n"
                + "fecha_destete = '" + datos.get("FEC_DESTETE") + "',\n"
                + "capado = '" + datos.get("CAPADO") + "',\n"
                + "fecha_nacimiento = '" + datos.get("FEC_NACIMIENTO") + "',\n"
                + "fecha = NOW(),"
                + "peso_destete = " + datos.get("PESO_DESTETE") + ",\n"
                + "implante = '" + datos.get("IMPLANTE") + "',\n"
                + "hierro_fisico = '" + datos.get("HIERRO_C") + "',\n"
                + "descornado = '" + datos.get("DESCORNADO") + "'\n"
                + "WHERE id = " + datos.get("IDANIMAL")
        //</editor-fold>
        );

        try {
            if (mySQL.EnviarConsultas(consultas)) {
                return Retorno.EXITO;
            } else {
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
    
}
