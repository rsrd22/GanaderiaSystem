/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloTraslado;
import Utilidades.Utilidades;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ControlTraslado implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlTraslado() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        String consulta = "";
        List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();
        ArrayList<ModeloTraslado> lista = new ArrayList<>();
        traslados = mySQL.ListSQL(consulta);

        if (traslados.size() > 0) {

            for (Map<String, String> traslado : traslados) {
                //lista.add("");
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    
    public Object ObtenerDatosTraslado(String IDFINCA, String IDTIPOFINCA) {
        try{
            String consulta = "SELECT traslado.`estado` AS ESTADO, traslado.`fecha` AS FECHA, IFNULL(DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n" +
                                "traslado.`id` AS ID_TRASLADO, animal.`id` AS ID_ANIMAL, traslado.`id_finca` AS ID_FINCA, traslado.`id_grupo` AS ID_GRUPO,\n" +
                                "traslado.`id_usuario` AS ID_USUARIO, traslado.`motivo` AS MOTIVO, IF(animal.`numero_mama_adoptiva` IS NULL, animal.`numero_mama`, animal.`numero_mama_adoptiva`) AS NUMERO_MAMA,\n" +
                                "animal.`numero` AS NUMERO_ANIMAL, animal.`peso` AS PESO, DATE_FORMAT(animal.`fecha_nacimiento`, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.`genero` AS GENERO,\n" +
                                "grup.`descripcion` AS GRUPO, \n" +
                                "IFNULL(finc.`id`, '') AS IDFINCA, IFNULL(finc.`descripcion`, '') AS FINCA, \n" +
                                "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n" +
                                "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE\n" +
                                ", animal.`id_tipo_animal` AS IDTIPO_ANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL \n"+
                                "FROM `ranimales` animal\n" +
                                "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = animal.`id_tipo_animal` \n"+
                                "LEFT JOIN `traslado_animalxgrupo` traslado ON traslado.`id_animal` = animal.`id`\n" +
                                "LEFT JOIN `grupos` grup ON grup.`id` = traslado.`id_grupo`\n" +
                                "LEFT JOIN (\n" +
                                "SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n" +
                                "rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n" +
                                "rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n" +
                                "FROM `rotacion_lotesxestado` rot\n" +
                                "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n" +
                                "WHERE rot.estado = 'Activo' AND rotgrup.`estado` = 'Activo'\n" +
                                ") AS tbl ON tbl.ID_GRUPO = traslado.`id_grupo`\n" +
                                "LEFT JOIN `lotes` lot ON lot.`id` = tbl.ID_LOTE \n" +
                                "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n" +
                                "LEFT JOIN fincas finc ON finc.`id` = `traslado`.`id_finca`\n" +
                                "WHERE traslado.`id_finca` = '"+IDFINCA+"' AND tpoani.`id` = '"+IDTIPOFINCA+"' \n"+
                                " AND traslado.`estado` = 'Activo' AND animal.venta = '0' AND animal.`muerte` = '0'\n" +
                                "ORDER BY animal.`id` ASC";
            
            consulta = "SELECT traslado.`estado` AS ESTADO, traslado.`fecha` AS FECHA, IFNULL(DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n" +
                                "traslado.`id` AS ID_TRASLADO, animal.`id` AS ID_ANIMAL, traslado.`id_finca` AS ID_FINCA, traslado.`id_grupo` AS ID_GRUPO,\n" +
                                "traslado.`id_usuario` AS ID_USUARIO, traslado.`motivo` AS MOTIVO, IF(animal.`numero_mama_adoptiva` IS NULL, animal.`numero_mama`, animal.`numero_mama_adoptiva`) AS NUMERO_MAMA,\n" +
                                "animal.`numero` AS NUMERO_ANIMAL, animal.`peso` AS PESO, DATE_FORMAT(animal.`fecha_nacimiento`, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.`genero` AS GENERO,\n" +
                                "grup.`descripcion` AS GRUPO, \n" +
                                "IFNULL(finc.`id`, '') AS IDFINCA, IFNULL(finc.`descripcion`, '') AS FINCA, \n" +
                                "IFNULL(blo.`id`, '') AS IDBLOQUE, IFNULL(CONCAT('Bloque ',blo.`numero`), '') AS BLOQUE, \n" +
                                "IFNULL(lot.`id`, '') AS IDLOTE, IFNULL(CONCAT('Lote ',lot.`numero`), '') AS LOTE\n" +
                                ", animal.`id_tipo_animal` AS IDTIPO_ANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL \n"+
                                "FROM `ranimales` animal\n" +
                                "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = animal.`id_tipo_animal` \n"+
                                "LEFT JOIN `traslado_animalxgrupo` traslado ON traslado.`id_animal` = animal.`id`\n" +
                                "LEFT JOIN `grupos` grup ON grup.`id` = traslado.`id_grupo`\n" +
                                "LEFT JOIN (\n" +
                                "SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n" +
                                "rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n" +
                                "rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n" +
                                "FROM `rotacion_lotesxestado` rot\n" +
                                "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n" +
                                "WHERE rot.estado = 'Activo' AND rotgrup.`estado` = 'Activo'\n" +
                                ") AS tbl ON tbl.ID_GRUPO = traslado.`id_grupo`\n" +
                                "LEFT JOIN `lotes` lot ON lot.`id` = tbl.ID_LOTE \n" +
                                "LEFT JOIN `bloques` blo ON blo.`id` = lot.`id_bloque`\n" +
                                "LEFT JOIN fincas finc ON finc.`id` = `traslado`.`id_finca`\n" +
                                "WHERE traslado.`id_finca` = '"+IDFINCA+"' AND tpoani.`id` = '"+IDTIPOFINCA+"' \n"+
                                " AND traslado.`estado` = 'Activo' AND animal.venta = '0' AND animal.`muerte` = '0'\n" +
                                "ORDER BY CONVERT(animal.numero,INT) ASC";
            
            List<Map<String, String>> traslados = new ArrayList<Map<String, String>>();

            traslados = mySQL.ListSQL(consulta);

        
            return traslados;
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int Guardar(Object _traslado) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTraslado traslado = (ModeloTraslado) _traslado;

        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                + ")\n"
                + "VALUES (\n"
                + "0,\n"
                + ""+traslado.getIdAnimal()+",\n"
                + ""+traslado.getIdFinca()+",\n"
                + ""+traslado.getIdGrupo()+",\n"
                + ""+Utilidades.ValorNULL(traslado.getFechaTraslado())+",\n"
                + "'"+traslado.getMotivo()+"',\n"
                + "'"+traslado.getEstado()+"',\n"
                + ""+traslado.getFecha()+",\n"
                + ""+traslado.getIdUsuario()+")"
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
    public int Actualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Eliminar(Object _traslado) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloTraslado traslado = (ModeloTraslado) _traslado;

        consultas.add(
//                //<editor-fold defaultstate="collapsed" desc="DELETE">
                "DELETE FROM `traslado_animalxgrupo` WHERE `id` = "+traslado.getId()+";"
//        //</editor-fold>
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
    public Object ObtenerDatosFiltro(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int InactivarTraslado(ModeloTraslado datos) {
        ArrayList<String> consultas = new ArrayList<>();
        
        consultas.add("UPDATE `traslado_animalxgrupo`\n" +
                        "SET `estado` = 'Inactivo'\n" +
                        "WHERE `id_animal` = '"+datos.getIdAnimal()+"' AND `estado` = 'Activo'");
        
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

    public List<Map<String, String>> getAnimalesxFinca(String idFinca, String idTipoAnimal) {
        String consulta = "SELECT traslado.`estado` AS ESTADO, traslado.`fecha` AS FECHA, IFNULL(DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n" +
                            "traslado.`id` AS ID_TRASLADO, animal.`id` AS ID_ANIMAL, traslado.`id_finca` AS ID_FINCA, traslado.`id_grupo` AS ID_GRUPO,\n" +
                            "traslado.`id_usuario` AS ID_USUARIO, traslado.`motivo` AS MOTIVO, IF(animal.`numero_mama_adoptiva` IS NULL, animal.`numero_mama`, animal.`numero_mama_adoptiva`) AS NUMERO_MAMA,\n" +
                            "animal.`numero` AS NUMERO_ANIMAL, animal.`peso` AS PESO, DATE_FORMAT(animal.`fecha_nacimiento`, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.`genero` AS GENERO,\n" +
                            "grup.`descripcion` AS GRUPO\n" +
                            ", animal.`id_tipo_animal` AS IDTIPO_ANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL \n" +
                            "FROM `ranimales` animal\n" +
                            "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = animal.`id_tipo_animal` \n" +
                            "LEFT JOIN `traslado_animalxgrupo` traslado ON traslado.`id_animal` = animal.`id`\n" +
                            "LEFT JOIN `grupos` grup ON grup.`id` = traslado.`id_grupo`\n" +
                            "LEFT JOIN (\n" +
                            "SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n" +
                            "rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n" +
                            "rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n" +
                            "FROM `rotacion_lotesxestado` rot\n" +
                            "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n" +
                            "WHERE rot.estado = 'Activo' AND rotgrup.`estado` = 'Activo'\n" +
                            ") AS tbl ON tbl.ID_GRUPO = traslado.`id_grupo`\n" +
                            "WHERE traslado.`id_finca` = '"+idFinca+"' AND tpoani.`id` = '"+idTipoAnimal+"' AND traslado.`estado` = 'Activo'\n" +
                            "ORDER BY animal.`id` ASC";
        
        consulta = "SELECT traslado.`estado` AS ESTADO, traslado.`fecha` AS FECHA, IFNULL(DATE_FORMAT(traslado.`fecha_traslado`, '%d/%m/%Y'), '') AS FECHA_TRASLADO,\n" +
                            "traslado.`id` AS ID_TRASLADO, animal.`id` AS ID_ANIMAL, traslado.`id_finca` AS ID_FINCA, traslado.`id_grupo` AS ID_GRUPO,\n" +
                            "traslado.`id_usuario` AS ID_USUARIO, traslado.`motivo` AS MOTIVO, IF(animal.`numero_mama_adoptiva` IS NULL, animal.`numero_mama`, animal.`numero_mama_adoptiva`) AS NUMERO_MAMA,\n" +
                            "animal.`numero` AS NUMERO_ANIMAL, animal.`peso` AS PESO, DATE_FORMAT(animal.`fecha_nacimiento`, '%d/%m/%Y') AS FECHA_NACIMIENTO, animal.`genero` AS GENERO,\n" +
                            "grup.`descripcion` AS GRUPO\n" +
                            ", animal.`id_tipo_animal` AS IDTIPO_ANIMAL, tpoani.`descripcion` AS TIPO_ANIMAL \n" +
                            "FROM `ranimales` animal\n" +
                            "INNER JOIN `tipo_animales` tpoani ON tpoani.`id` = animal.`id_tipo_animal` \n" +
                            "LEFT JOIN `traslado_animalxgrupo` traslado ON traslado.`id_animal` = animal.`id`\n" +
                            "LEFT JOIN `grupos` grup ON grup.`id` = traslado.`id_grupo`\n" +
                            "LEFT JOIN (\n" +
                            "SELECT rot.`id` AS ID_ROTACION, rotgrup.`id` AS ID_ROT_GRUPO, rot.`id_lote` AS ID_LOTE, rotgrup.`id_grupo` AS ID_GRUPO,\n" +
                            "rot.`fecha_entrada` AS FECHA_ENTRADA, rot.`fecha_registro` AS FECHA_REGISTRO,\n" +
                            "rot.`fecha_salida` AS FECHA_SALIDA, rot.estado AS ESTADO_LOTE, rotgrup.`estado` AS ESTADO_GRUPO\n" +
                            "FROM `rotacion_lotesxestado` rot\n" +
                            "INNER JOIN rotacion_lotesxgrupo rotgrup ON rotgrup.`id_rotacion_lotesxestado` = rot.`id`\n" +
                            "WHERE rot.estado = 'Activo' AND rotgrup.`estado` = 'Activo'\n" +
                            ") AS tbl ON tbl.ID_GRUPO = traslado.`id_grupo`\n" +
                            "WHERE traslado.`id_finca` = '"+idFinca+"' AND tpoani.`id` = '"+idTipoAnimal+"' AND traslado.`estado` = 'Activo'\n" +
                            "ORDER BY CONVERT(animal.numero,INT) ASC";
        
        List<Map<String, String>> listaModelo = new ArrayList<Map<String, String>>();
        listaModelo = mySQL.ListSQL(consulta);
        
        return listaModelo;
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int ActulizarAnimal(ModeloTraslado datos, boolean novilla) {
        ArrayList<String> consultas = new ArrayList<>();
        String add = "";
        
        boolean exist = mySQL.ExistenDatos("select * from animales where id = '"+datos.getIdAnimal()+"' and `fecha_novilla` is not null AND fecha_novilla > '1900-01-01' ");
        
        System.out.println("exist-->"+exist);
        
        if(novilla && !exist){
            add = ", fecha_novilla = "+Utilidades.ValorNULL(datos.getFechaTraslado())+" \n";
        }
        
        consultas.add("UPDATE `ranimales`\n" +
                        "SET `id_tipo_animal` = '"+datos.getLote()+"', \n" +
                        "grupo = '"+datos.getIdGrupo()+"'\n"+    
                        add+
                        "WHERE `id` = '"+datos.getIdAnimal()+"';");
        
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

    public List<Map<String, String>> getIdsAnimalesxGrupos(String id_tipo_animal, String ids_grupos) {
        String consulta = "SELECT id as ID FROM `ranimales` ani \n" +
                            "WHERE ani.`id_tipo_animal` = '"+id_tipo_animal+"' AND grupo in ("+ids_grupos+")\n" +
                            "AND venta = '0' AND muerte = '0'";
        
        List<Map<String, String>> listaModelo = new ArrayList<Map<String, String>>();
        listaModelo = mySQL.ListSQL(consulta);
        
        return listaModelo;
    }
    
    public int ActulizarAnimales(Map<String, String> datos) {
        ArrayList<String> consultas = new ArrayList<>();
                
        consultas.add("UPDATE `ranimales`\n" +
                            "SET `id_tipo_animal` = '"+datos.get("IDTPO_DESTINO")+"',  \n" +
                            "grupo = '"+datos.get("IDGPO_DESTINO")+"'   \n" +
                            "WHERE id_tipo_animal = '"+datos.get("IDTPO_ORIGEN")+"' AND grupo = '"+datos.get("IDGPO_ORIGEN")+"' AND venta = '0' AND muerte ='0';");
        
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
    
    public int InactivarTraslados(String id_grupo) {
        ArrayList<String> consultas = new ArrayList<>();
        
        consultas.add("UPDATE `traslado_animalxgrupo`\n" +
                        "SET `estado` = 'Inactivo' \n" +
                        "WHERE `id_grupo` = '"+id_grupo+"' AND `estado` = 'Activo'");
        
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
    
    public int GuardarTrasladoGrupo(Object _traslado) {
        ArrayList<String> consultas = new ArrayList<>();
        ArrayList<ModeloTraslado> ListamodeloTraslado = (ArrayList<ModeloTraslado>) _traslado;
        for (ModeloTraslado traslado: ListamodeloTraslado) {
            consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO traslado_animalxgrupo(id,id_animal,id_finca,\n"
                    + "id_grupo,fecha_traslado,motivo,estado,fecha,id_usuario\n"
                    + ")\n"
                    + "VALUES (\n"
                    + "0,\n"
                    + ""+traslado.getIdAnimal()+",\n"
                    + ""+traslado.getIdFinca()+",\n"
                    + ""+traslado.getIdGrupo()+",\n"
                    + ""+Utilidades.ValorNULL(traslado.getFechaTraslado())+",\n"
                    + "'"+traslado.getMotivo()+"',\n"
                    + "'"+traslado.getEstado()+"',\n"
                    + ""+traslado.getFecha()+",\n"
                    + ""+traslado.getIdUsuario()+")"
            //</editor-fold>
            );
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

    public int EliminarTraslados(List<Map<String, String>> ListaDatosTrasladoEliminar) {
        ArrayList<String> consultas = new ArrayList<>();
        for(Map<String, String> datos:ListaDatosTrasladoEliminar){
            consultas.add(
            //                //<editor-fold defaultstate="collapsed" desc="DELETE">
                    "DELETE FROM `traslado_animalxgrupo` WHERE `id` = "+datos.get("IDTRASLADO")+";"
    //        //</editor-fold>
            );
        }

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
}
