/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.*;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlPalpacion implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlPalpacion() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Guardar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPalpacion modelo = (ModeloPalpacion) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO `palpacion`\n"
                + "(`id`, `id_animal`, `fecha_palpacion`, `diagnostico`, `notas`, `num_meses`, `fecha_ultimo_parto`, `descarte`, razondescarte, estado, `fecha`, `id_usuario`)\n"
                + "VALUES (0,\n"
                + "        " + modelo.getId_animal() + ",\n"
                + "        " + Utilidades.ValorNULL(modelo.getFecha_palpacion()) + ",\n"
                + "        '" + modelo.getDiagnostico().toLowerCase() + "',\n"
                + "        '" + modelo.getNotas() + "',\n"
                + "        " + modelo.getNum_meses() + ",\n"
                + "        " + Utilidades.ValorNULL(modelo.getFecha_ultimo_parto()) + ",\n"
                + "        '" + modelo.getDescarte() + "',\n"
                + "        '" + modelo.getRazondescarte() + "',\n"
                + "        '" + modelo.getEstado() + "',\n"
                + "        " + modelo.getFecha() + ",\n"
                + "        " + modelo.getId_usuario() + ");"
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACIONPORMEDICAMENTOS">
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO palpacionxtratamiento (id,id_palpacion,id_medicamento,dosis) VALUES(\n"
                    + "0,\n"
                    + " (SELECT id FROM palpacion where id_animal = " + modelo.getId_animal() + " AND DATE_FORMAT(`fecha`,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n" // AND fecha_palpacion = '"+modelo.getFecha_palpacion()+"' AND diagnostico = '"+modelo.getDiagnostico()+"'
                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                    + ")"
            //</editor-fold>
            );
        }
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

    @Override
    public int Actualizar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPalpacion modelo = (ModeloPalpacion) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACION">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "UPDATE `palpacion`\n"
                + "SET \n"
                + "  `fecha_palpacion` = " + Utilidades.ValorNULL(modelo.getFecha_palpacion()) + ",\n"
                + "  `diagnostico` = '" + modelo.getDiagnostico().toLowerCase() + "',\n"
                + "  `notas` = '" + modelo.getNotas() + "',\n"
                + "  `num_meses` = " + modelo.getNum_meses() + ",\n"
                + "  `fecha_ultimo_parto` = " + Utilidades.ValorNULL(modelo.getFecha_ultimo_parto()) + ",\n"
                + "  `descarte` = '" + modelo.getDescarte() + "',\n"
                + "  `razondescarte` = '" + modelo.getRazondescarte() + "'\n"
                + "WHERE `id` = " + modelo.getId() + ";"
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PALPACIONPORMEDICAMENTOS">
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            if (modelo.getListaMedicamentos().get(i).getEstadoG().equals("0")) {
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="INSERT">
                        "INSERT INTO palpacionxtratamiento (id,id_palpacion,id_medicamento,dosis) VALUES(\n"
                        + "0,\n"
                        + "" + modelo.getId() + " ,\n"
                        + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                        + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                        + ")"
                //</editor-fold>
                );
            } else if (modelo.getListaMedicamentos().get(i).getEstadoG().equals("1")) {
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="UPDATE">
                        "UPDATE `palpacionxtratamiento`\n"
                        + "SET `id_medicamento` = " + modelo.getListaMedicamentos().get(i).getId_medicamento() + ", \n"
                        + "`dosis` = " + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                        + "WHERE `id` =" + modelo.getListaMedicamentos().get(i).getId() + ";"
                //</editor-fold>
                );
            }
        }
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

    @Override
    public int Eliminar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPalpacion palpacion = (ModeloPalpacion) o;

        if (palpacion.getListaMedicamentos().size() > 0) {
            consultas.add("DELETE\n"
                    + "FROM palpacionxtratamiento\n"
                    + "WHERE id_palpacion = " + palpacion.getId());
        }

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE\n"
                + "FROM palpacion\n"
                + "WHERE id = " + palpacion.getId()
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

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        String consulta = "SELECT * FROM palpacion\n"
                + " WHERE id_animal=" + o.toString() + "";
//        consulta = "SELECT palp.`id` AS ID, palp.`id_animal` AS IDANIMAL, palp.`fecha_palpacion` AS FECHAPALP,\n" +
//                    "palp.`diagnostico` AS DIAGNOSTICO, palp.`notas` AS NOTAS, palp.`num_meses` AS NUM_MESES,\n" +
//                    "palp.`fecha_ultimo_parto` AS FECHAULTPARTO, palp.`descarte` AS DESCARTE, palp.`razondescarte` AS RAZON_DESCARTE,\n" +
//                    "palpm.`id` AS IDPALPMEDICAMENTOS, palpm.`id_medicamento` AS IDMEDICAMENTO,\n" +
//                    "med.`descripcion` AS MEDICAMENTO, palpm.`dosis` AS DOSIS, med.`unidad_medida` AS UNIDAD_MEDIDA\n" +
//                    "FROM palpacion palp\n" +
//                    "LEFT JOIN palpacionxtratamiento palpm ON palpm.`id_palpacion` = palp.id\n" +
//                    "LEFT JOIN medicamentos med ON med.`id` = palpm.`id_medicamento`\n" +
//                    "WHERE palp.id_animal = '" + o.toString() + "'\n" +
//                    "ORDER BY palp.`id` DESC";

        List<Map<String, String>> palpaciones = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPalpacion> lista = new ArrayList<>();
        palpaciones = mySQL.ListSQL(consulta);
//        List<Map<String, String>> listaPalpaciones = Utilidades.data_list(1, palpaciones, new String[]{"ID"});
//        
//        if(listaPalpaciones.size()>0){
//            for (Map<String, String> palpacion : listaPalpaciones) {
//                List<Map<String, String>> listaDatosPalpaciones = Utilidades.data_list(3, palpaciones, new String[]{"ID<->" + palpacion.get("ID")});
//                ModeloPalpacion mod = new ModeloPalpacion();
//                mod.setId(palpacion.get("ID"));
//                mod.setId_animal(palpacion.get("IDANIMAL"));
//                mod.setFecha_palpacion(palpacion.get("FECHAPALP"));
//                mod.setDiagnostico(palpacion.get("DIAGNOSTICO"));
//                mod.setNotas(palpacion.get("NOTAS"));
//                mod.setNum_meses(palpacion.get("NUM_MESES"));
//                mod.setFecha_ultimo_parto(palpacion.get("FECHAULTPARTO"));
//                mod.setDescarte(palpacion.get("DESCARTE"));
//                mod.setRazondescarte(palpacion.get("RAZON_DESCARTE"));
//                
//                if(listaDatosPalpaciones.size()>0){
//                    
//                }
//            }
//        }

        if (palpaciones.size() > 0) {
            for (Map<String, String> palpacion : palpaciones) {
                ModeloPalpacion mod = new ModeloPalpacion();
                mod.setId(palpacion.get("id"));
                mod.setId_animal(palpacion.get("id_animal"));
                mod.setFecha_palpacion(palpacion.get("fecha_palpacion"));
                mod.setDiagnostico(palpacion.get("diagnostico"));
                mod.setNotas(palpacion.get("notas"));
                mod.setNum_meses(palpacion.get("num_meses"));
                mod.setFecha_ultimo_parto(palpacion.get("fecha_ultimo_parto"));
                mod.setDescarte(palpacion.get("descarte"));
                mod.setRazondescarte(palpacion.get("razondescarte"));
                mod.setFecha(palpacion.get("fecha"));
                mod.setId_usuario(palpacion.get("id_usuario"));

                consulta = "SELECT palpm.`id` AS ID, palpm.`id_medicamento` AS IDMEDICAMENTO,\n"
                        + "med.`descripcion` AS MEDICAMENTO, palpm.`dosis` AS DOSIS, med.`unidad_medida` AS UNIDAD_MEDIDA\n"
                        + "FROM `palpacionxtratamiento` palpm\n"
                        + "INNER JOIN `medicamentos` med ON med.`id` = palpm.`id_medicamento`\n"
                        + "WHERE palpm.`id_palpacion` = " + palpacion.get("id");
                List<Map<String, String>> medicamentos = new ArrayList<Map<String, String>>();
                ArrayList<ModeloMedicamentosPorPesaje> lisMed = new ArrayList<>();
                medicamentos = mySQL.ListSQL(consulta);
                if (medicamentos.size() > 0) {
                    for (Map<String, String> meds : medicamentos) {
                        ModeloMedicamentosPorPesaje m = new ModeloMedicamentosPorPesaje();
                        m.setId(meds.get("ID"));
                        m.setId_medicamento(meds.get("IDMEDICAMENTO"));
                        m.setMedicamento(meds.get("MEDICAMENTO"));
                        m.setDosis(meds.get("DOSIS"));
                        m.setUnidad_medida(meds.get("UNIDAD_MEDIDA"));
                        lisMed.add(m);
                    }

                }
                mod.setListaMedicamentos(lisMed);
                lista.add(mod);
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public int deletePalpacionMedicamento(String id) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add(
                //                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM `palpacionxtratamiento` WHERE `id` = " + id + ";"
        //              //</editor-fold>
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

    public Object ObtenerDatosFiltroNew(Object o) {
        String consulta = "SELECT * FROM palpacion\n"
                + " WHERE id_animal=" + o.toString() + "";
        consulta = "SELECT palp.`id` AS ID, palp.`id_animal` AS IDANIMAL, palp.`fecha_palpacion` AS FECHAPALP,\n"
                + "palp.`diagnostico` AS DIAGNOSTICO, palp.`notas` AS NOTAS, palp.`num_meses` AS NUM_MESES,\n"
                + "palp.`fecha_ultimo_parto` AS FECHAULTPARTO, palp.`descarte` AS DESCARTE, palp.`razondescarte` AS RAZON_DESCARTE,\n"
                + "palpm.`id` AS IDPALPMEDICAMENTOS, palpm.`id_medicamento` AS IDMEDICAMENTO, palpm.`id_palpacion` AS IDPALPM,\n"
                + "med.`descripcion` AS MEDICAMENTO, palpm.`dosis` AS DOSIS, med.`unidad_medida` AS UNIDAD_MEDIDA, palp.estado AS ESTPALP\n"
                + "FROM palpacion palp\n"
                + "LEFT JOIN palpacionxtratamiento palpm ON palpm.`id_palpacion` = palp.id\n"
                + "LEFT JOIN medicamentos med ON med.`id` = palpm.`id_medicamento`\n"
                + "WHERE palp.id_animal = '" + o.toString() + "'\n"
                + "ORDER BY palp.`fecha_palpacion` DESC";

        List<Map<String, String>> palpaciones = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPalpacion> lista = new ArrayList<>();
        palpaciones = mySQL.ListSQL(consulta);
        List<Map<String, String>> listaPalpaciones = Utilidades.data_list(1, palpaciones, new String[]{"ID"});
//        
        if (listaPalpaciones.size() > 0) {
            for (Map<String, String> palpacion : listaPalpaciones) {
                List<Map<String, String>> listaDatosPalpaciones = Utilidades.data_list(3, palpaciones, new String[]{"IDPALPM<->" + palpacion.get("ID")});
                ModeloPalpacion mod = new ModeloPalpacion();
                mod.setId(palpacion.get("ID"));
                mod.setId_animal(palpacion.get("IDANIMAL"));
                mod.setFecha_palpacion(palpacion.get("FECHAPALP"));
                mod.setDiagnostico(palpacion.get("DIAGNOSTICO"));
                mod.setNotas(palpacion.get("NOTAS"));
                mod.setNum_meses(palpacion.get("NUM_MESES"));
                mod.setFecha_ultimo_parto(palpacion.get("FECHAULTPARTO"));
                mod.setDescarte(palpacion.get("DESCARTE"));
                mod.setRazondescarte(palpacion.get("RAZON_DESCARTE"));
                mod.setEstado(palpacion.get("ESTPALP"));
                ArrayList<ModeloMedicamentosPorPesaje> lisMed = new ArrayList<>();
                System.out.println("listaDatosPalpaciones-->" + listaDatosPalpaciones.size());
                if (listaDatosPalpaciones.size() > 0) {
                    for (Map<String, String> meds : listaDatosPalpaciones) {
                        ModeloMedicamentosPorPesaje m = new ModeloMedicamentosPorPesaje();
                        m.setId(meds.get("IDPALPMEDICAMENTOS"));
                        m.setId_medicamento(meds.get("IDMEDICAMENTO"));
                        m.setMedicamento(meds.get("MEDICAMENTO"));
                        m.setDosis(meds.get("DOSIS"));
                        m.setUnidad_medida(meds.get("UNIDAD_MEDIDA"));
                        lisMed.add(m);
                    }
                }
                mod.setListaMedicamentos(lisMed);
                lista.add(mod);
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public Map<String, String> getDatosPalpacion(String id) {
        String consulta = "SELECT IFNULL(palp.`diagnostico`, '') AS ESTADO\n"
                + "FROM `palpacion` palp \n"
                + "INNER JOIN `animales` a ON a.`id` = palp.`id_animal`\n"
                + "WHERE id_animal = '" + id + "'\n"
                + "ORDER BY palp.id DESC\n"
                + "LIMIT 1;";

        List<Map<String, String>> datos = new ArrayList<Map<String, String>>();
        datos = mySQL.ListSQL(consulta);
        Map<String, String> retorno = new HashMap<>();
//        
        if (datos.size() > 0) {
            retorno.put("ESTADO", datos.get(0).get("ESTADO"));

            return retorno;
        } else {
            retorno.put("ESTADO", "");
            return retorno;
        }
    }

    public Map<String, String> getDatosPartos(String id) {
        String consulta = "SELECT IFNULL(`NumeroPartos`(a.numero),0) NUM_PARTOS, IFNULL(`NumeroHijos`(a.`numero`,2), '') CRIA,\n"
                + "NumMeses(palp.`diagnostico`, palp.`num_meses`, palp.`fecha_palpacion`) NUM_MESES\n"
                + "FROM `animales` a ON a.`id` = '" + id + "'\n";

        consulta = "SELECT CASE WHEN a.numero=a.numero_mama THEN\n"
                + "0 ELSE IFNULL(`NumeroPartos`(a.numero),0) END NUM_PARTOS, "
                + "IFNULL(`NumeroHijos`(a.`numero`,2), '') CRIA,\n"
                + "IFNULL(`NumeroHijos`(a.`numero`,3), '') CRIA_ADOPTIVA,\n"
                + "NumMeses(palp.`diagnostico`, palp.`num_meses`, palp.`fecha_palpacion`) NUM_MESES, IFNULL(DATE_FORMAT(`NumeroHijos`(a.`numero`, 1), '%d/%m/%Y'), '') FECHA_ULT_PARTO\n"
                + "FROM `animales` a \n"
                + "LEFT JOIN `palpacion` palp ON palp.`id_animal` = a.`id` and palp.estado = 'Activo'\n"
                + "WHERE a.`id` = '" + id + "'";

        List<Map<String, String>> datos = new ArrayList<Map<String, String>>();
        datos = mySQL.ListSQL(consulta);
        Map<String, String> retorno = new HashMap<>();
//        
        if (datos.size() > 0) {
            retorno.put("NUM_PARTOS", datos.get(0).get("NUM_PARTOS"));
            retorno.put("CRIA", datos.get(0).get("CRIA"));
            retorno.put("CRIA_ADOPTIVA", datos.get(0).get("CRIA_ADOPTIVA"));
            retorno.put("NUM_MESES", datos.get(0).get("NUM_MESES"));
            retorno.put("FECHA_ULT_PARTO", datos.get(0).get("FECHA_ULT_PARTO"));

            return retorno;
        } else {
            retorno.put("NUM_PARTOS", "");
            retorno.put("CRIA", "");
            retorno.put("NUM_MESES", "");
            retorno.put("FECHA_ULT_PARTO", "");
            return retorno;
        }
    }

    public String getFechaPalpActiva(String idAnimal) {
        String consulta = "SELECT DATE_FORMAT(`fecha_palpacion`, '%d/%m/%Y') AS FECHAPALP FROM palpacion\n"
                + " WHERE id_animal=" + idAnimal + " and estado = 'Activo'";
        String ret = "";
        List<Map<String, String>> palpaciones = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPalpacion> lista = new ArrayList<>();
        palpaciones = mySQL.ListSQL(consulta);
        if (palpaciones.size() > 0) {
            ret = palpaciones.get(0).get("FECHAPALP");
        }
        return ret;
    }

    public int InactivarEstadoAnterior(String idAnimal) {
        ArrayList<String> consultas = new ArrayList<>();

        consultas.add("UPDATE `palpacion`\n"
                + "SET `estado` = 'Inactivo'\n"
                + "WHERE `id_animal` = '" + idAnimal + "' AND estado = 'Activo';");

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

    public List<Map<String, String>> getDatosParto(ModeloAnimales modelo) {
        try {
            String consulta = "select an.id as IDANIMAL, an.`numero` as NUMERO, "
                    + "DATE_FORMAT(an.`fecha_nacimiento`, '%d/%m/%Y') as FEC_NAC, "
                    + "an.`numero_descendiente` AS NUMPARTO, \n"
                    + "gr.`descripcion` as GRUPO, an.`genero` as SEXO, "
                    + "ifnull(pes.`peso`, '') as PES_NAC, ifnull(an.notas, '') as NOTAS\n"
                    + "from animales an\n"
                    + "inner join `grupos` gr on gr.`id` = an.grupo\n"
                    + "left join `pesaje` pes on pes.`id_animal` = an.`id` "
                    + "and pes.notas = 'REGISTRO AUTOMATICO (VISTA ANIMAL), PESO DE NACIMIENTO'\n"
                    + "where an.numero_mama = '" + modelo.getNumero() + "' "
                    + "and an.`id_tipo_animal` = '" + modelo.getIdTipoAnimal() + "'\n"
                    + "ORDER BY an.`numero_descendiente` ASC";

            System.out.println("getDatosParto...>" + consulta);

            List<Map<String, String>> partos = new ArrayList<Map<String, String>>();

            partos = mySQL.ListSQL(consulta);

            return partos;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int AnularPalpacion(ModeloPalpacion modelo){
        ArrayList<String> consultas = new ArrayList<>();
        consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="SE ELIMINA EL REGISTRO DE PALPACION">
                "DELETE FROM palpacion WHERE id=" + modelo.getId()
        //</editor-fold>
        );

        if (modelo.getListaMedicamentos().size() > 0) {
            for (ModeloMedicamentosPorPesaje lista : modelo.getListaMedicamentos()) {
                consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS MEDICAMENTOS PALPACION">
                    "DELETE FROM palpacionxtratamiento WHERE id=" + lista.getId()
            //</editor-fold>
                );
            }
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


}
