/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import BaseDeDatos.gestorMySQL;
import static Utilidades.Consultas.consultas;
import Utilidades.Utilidades;
import Utilidades.datosUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        int banPesoNac = 0;
        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL ANIMAL">
        consultas.add(
                
                //<editor-fold defaultstate="collapsed" desc="INSERT RANIMALES">
                "INSERT INTO `ranimales`\n" +
                "(`id`,`id_tipo_animal`,`hierro`,`numero`,`numero_descendiente`,`estado_descendiente`,\n" +
                "`numero_parto`,`cantidad_parto`,`es_madre`,`numero_mama`,`numero_mama_adoptiva`,\n" +
                "`peso`,`genero`,`grupo`,`calificacion`,`notas`,`fecha_destete`,`capado`,\n" +
                "`fecha_nacimiento`,`muerte`,`peso_destete`,`destete`,`fecha_muerte`,`descripcion_muerte`,\n" +
                "`venta`,`fecha_venta`,`tipo_venta`,`precio_venta`,`peso_canal`,`fecha_novilla`,`hierro_fisico`,\n" +
                "`implante`,`descornado`,`fecha`,`id_usuario`)\n" +
                "VALUES (0,\n" +
                "        " + datos.get("IDTIPOANIMAL")+ ",\n" +
                "        " + datos.get("IDHIERRO") + ",\n" +
                "        '" + datos.get("NUM_ANIMAL") + "',\n" +
                "        " + datos.get("NUMERO_DESCENDIENTE") + ",\n" +
                "        '1',\n" +
                "        NULL,\n" +
                "        " + datos.get("NUM_PARTOS") + ",\n" +
                "        " + Utilidades.CampoNULL(datos.get("ES_MADRE")) + ",\n" +
                "        '" + datos.get("NUM_MADRE").replace("_", "") + "',\n" +
                "        '',\n" +
                "        " + datos.get("PESO") + ",\n" +
                "        '" + datos.get("SEXO") + "',\n" +
                "        " + datos.get("IDGRUPO") + ",\n" +
                "        '3',\n" +
                "        '" + datos.get("NOTAS").replace("_", "")+ "',\n" +
                "        '" + datos.get("FEC_DESTETE") + "',\n" +
                "        '" + datos.get("CAPADO") + "',\n" +
                "        '" + datos.get("FEC_NACIMIENTO") + "',\n" +
                "        '0',\n" +
                "        " + datos.get("PESO_DESTETE") + ",\n" +
                "        '" + datos.get("DESTETE") + "',\n" +
                "        '1900-01-01',\n" +
                "        '',\n" +
                "        '0',\n" +
                "        '1900-01-01',\n" +
                "        NULL,\n" +
                "        NULL,\n" +
                "        NULL,\n" +
                "        '1900-01-01',\n" +
                "        '" + datos.get("HIERRO_C") + "',\n" +
                "        '" + datos.get("IMPLANTE") + "',\n" +
                "        '" + datos.get("DESCORNADO") + "',\n" +
                "        NOW(),\n" +
                "        " + datos.get("ID_USUARIO") + ");"
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
                + "(SELECT id FROM ranimales WHERE numero = '"+datos.get("NUM_ANIMAL")+"' and id_tipo_animal='"+ datos.get("IDTIPOANIMAL")+"'),\n"
                + "" + datos.get("IDFINCA")+ ",\n"
                + "" + datos.get("IDGRUPO") + ",\n"
                + "     NOW(),\n"
                + "'"+Utilidades.CodificarElemento("CREACIÓN DEL ANIMAL")+"',\n"
                + "'Activo',\n"
                + " NOW(),\n"
                + "" + datosUsuario.datos.get(0).get("ID_USUARIO") + ")"
        //</editor-fold>
        );
//</editor-fold>

        
        //<editor-fold defaultstate="collapsed" desc="PESO NACIEMIENTo">
        if(!datos.get("PESO_NACIMIENTO").equals("0"))  {  
            consultas.add(
                       //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,peso_anterior,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                    + "0,\n"
                    + "(SELECT id FROM ranimales WHERE numero = '"+datos.get("NUM_ANIMAL")+"' and id_tipo_animal='"+ datos.get("IDTIPOANIMAL")+"'),\n"
                    + "" + Utilidades.CampoNULL(datos.get("FEC_NACIMIENTO")) + ",\n"
                    + "" + datos.get("PESO_NACIMIENTO") + ",\n"
                    + "0,\n"
                    + "'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO',\n"
                    + "'0',\n"
                    + "'0',\n"
                    + "'0',\n"
                    + "'" + datos.get("DESTETE") + "',\n"//AGREGADA
                    + "NOW(),\n"
                    + "" + datosUsuario.datos.get(0).get("ID_USUARIO") + "\n"
                    + ")" //</editor-fold>
            );
            banPesoNac = 1;
        }
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="GUARDAR DATOS DEL PRIMER PESO">
        String add = "";
        if(banPesoNac == 0){
            add = ", PRIMER REGISTRO";
        }
        
        if(!datos.get("PESO").equals("0"))    
        consultas.add(
                   //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,peso_anterior,notas,hierro,descornado,implante,destete,fecha,id_usuario) VALUES(\n"
                + "0,\n"
                + "(SELECT id FROM ranimales WHERE numero = '"+datos.get("NUM_ANIMAL")+"' and id_tipo_animal='"+ datos.get("IDTIPOANIMAL")+"'),\n"
                + "" + Utilidades.CampoNULL(datos.get("FEC_PESAJE")) + ",\n"
                + "" + datos.get("PESO") + ",\n"
                + "0,\n"
                + "'REGISTRO AUTOMATICO (VISTA ANIMAL)"+add+"',\n"
                + "'0',\n"
                + "'0',\n"
                + "'0',\n"
                + "'" + datos.get("DESTETE") + "',\n"//AGREGADA
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
                "UPDATE ranimales SET\n"
                + "id_tipo_animal = " + datos.get("IDTIPOANIMAL") + ",\n"
                + "numero_mama = '" + datos.get("NUM_MADRE").replace("_", "") + "',\n"
                + "peso = " + datos.get("PESO") + ",\n"
                + "cantidad_parto =  "+datos.get("NUM_PARTOS") + ",\n"
                + "numero_descendiente = " + datos.get("NUMERO_DESCENDIENTE") + ",\n"
                + "es_madre = " + Utilidades.CampoNULL(datos.get("ES_MADRE")) + ",\n"
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
                + "destete = '" + datos.get("DESTETE") + "',\n"//AGREGADA
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

    public int GuardarPesaje(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
        
        //<editor-fold defaultstate="collapsed" desc="INACTIVAR REGISTRO ANTERIOR">
        if(mySQL.ExistenDatos("SELECT * FROM `pesaje` WHERE `id_animal` = " + datos.get("IDANIMAL")+ " AND estado = 'Activo'")){
            consultas.add("UPDATE `pesaje`\n" +
                            "SET `estado` = 'Inactivo'\n" +
                            "WHERE `id_animal` = " + datos.get("IDANIMAL")+ " AND estado = 'Activo';");
        }
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="GUARDAR PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `pesaje`\n" +
                    "(`id`, `id_animal`, `fecha_pesado`, \n" +
                    "`peso`, `peso_anterior`, `notas`, \n" +
                    "`hierro`, `descornado`, `implante`, \n" +
                    "`destete`, `estado`, `fecha`, `id_usuario`)\n" +
                    "VALUES \n" +
                    "(0, " + datos.get("IDANIMAL")+ ", '" + datos.get("FEC_PESAJE") + "', \n" +
                    "" + datos.get("PESO") + ", " + datos.get("PESO_ANT") + ", 'PESO POR CARGA MASIVA', \n" +
                    "'" + datos.get("HIERRO") + "', '" + datos.get("DESCORNADO") + "', '" + datos.get("IMPLANTE") + "', \n" +
                    "'" + datos.get("DESTETE") + "', 'Activo', NOW(), "+datosUsuario.datos.get(0).get("ID_USUARIO")+");"
        //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Actualiza peso tbl Animal">
        consultas.add(
                "update `ranimales`\n" +
                    "set `peso` = " + datos.get("PESO") + "\n" +
                    "where `id` = " + datos.get("IDANIMAL")+ ";"
        
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

    public int GuardarMedicamentoxPesaje(List<Map<String, String>> ListaMedicamentosxPesaje) {
        ArrayList<String> consultas = new ArrayList<>();
        
        for (Map<String, String> med : ListaMedicamentosxPesaje) {
            //<editor-fold defaultstate="collapsed" desc="GUARDAR PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `pesajexmedicamento`\n" +
                    "(`id`,`id_pesaje`,`id_medicamento`,`dosis`)\n" +
                    "VALUES \n" +
                    "(0,(SELECT id FROM `pesaje` WHERE id_animal = "+med.get("IDANIMAL")+" AND estado ='Activo'),"+med.get("IDMEDICAMENTO")+","+med.get("DOSIS")+");"
        //</editor-fold>
        );
//</editor-fold>
        }
        
        
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

    public int GuardarPalpacion(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
        
        //<editor-fold defaultstate="collapsed" desc="INACTIVAR REGISTRO ANTERIOR">
        if(mySQL.ExistenDatos("SELECT * FROM `palpacion` WHERE `id_animal` = " + datos.get("IDANIMAL")+ " AND estado = 'Activo'")){
            consultas.add("UPDATE `palpacion`\n" +
                            "SET `estado` = 'Inactivo'\n" +
                            "WHERE `id_animal` = " + datos.get("IDANIMAL")+ " AND estado = 'Activo';");
        }
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="GUARDAR PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `palpacion`\n" +
                    "(`id`, `id_animal`, `fecha_palpacion`, `diagnostico`, \n" +
                    "`notas`, `num_meses`, `estado`, `fecha_ultimo_parto`, \n" +
                    "`descarte`, `razondescarte`, `fecha`, `id_usuario`)\n" +
                    "VALUES \n" +
                    "(0, " + datos.get("IDANIMAL")+ ", '" + datos.get("FEC_PALPACION")+ "', '" + datos.get("ESTADO")+ "', \n" +
                    "'PALPACION POR CARGA MASIVA', " + datos.get("NUM_MESES")+ ", 'Activo', " + Utilidades.ValorNULL(datos.get("F.U.P"))+ ", \n" +
                    "'" + datos.get("DESCARTE")+ "', '" + datos.get("RAZON_DESCARTE")+ "', NOW(), "+datosUsuario.datos.get(0).get("ID_USUARIO")+");"
        //</editor-fold>
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

    public int GuardarMedicamentoxPalpacion(List<Map<String, String>> ListaMedicamentosxPesaje) {
        ArrayList<String> consultas = new ArrayList<>();
        
        for (Map<String, String> med : ListaMedicamentosxPesaje) {
            //<editor-fold defaultstate="collapsed" desc="GUARDAR Med PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `palpacionxtratamiento`\n" +
                    "(`id`,`id_palpacion`,`id_medicamento`,`dosis`)\n" +
                    "VALUES \n" +
                    "(0,(SELECT id FROM `palpacion` WHERE id_animal = "+med.get("IDANIMAL")+" AND estado ='Activo'),"+med.get("IDMEDICAMENTO")+","+med.get("DOSIS")+");"
        //</editor-fold>
        );
//</editor-fold>
        }
        
        
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
    
    public String ObtenerUltimoDescendiente(String numeroMadre, String tipoAnimal) {
        System.out.println(""+numeroMadre);
        List<Map<String, String>> animal = new ArrayList<Map<String, String>>();
        ControlGeneral controlGral = new ControlGeneral();
        String consulta = consultas.get("OBTENER_ULTIMO_DESCENDIENTE")
                .replace("ID_TIPO_ANIMAL", tipoAnimal)
                .replace("NUMERO_MAMA", numeroMadre);
        animal = controlGral.GetComboBox(consulta);

        return animal.get(0).get("numeroDescendiente");
    }
}
