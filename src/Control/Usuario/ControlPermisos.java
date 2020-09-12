/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control.Usuario;

import BaseDeDatos.gestorMySQL;
import Control.IControl;
import Control.Retorno;
import Modelo.Usuario.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author MERRY
 */
public class ControlPermisos implements IControl{
    private gestorMySQL mySQL;
    
    public ControlPermisos(){
        this.mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        try{
            
            List<Map<String, String>> Info = new ArrayList<>();
            
            String sql = "";
            //     0           1        2    3  4  5  6  7  8    9
            //ID_PERMISO, ID_MODULO, MODULO, S, I, U, D, V, T, IDPXM
            
            sql = "SELECT IFNULL(tbl.id, '0') AS ID_PERMISO, m.id AS ID_MODULO, m.`Descripcion` AS MODULO, \n" +
                    " tbl.tipo as TIPO, tbl.valor_tipo as VALOR, \n"+
                    "per.`tipo`, per.`valor_tipo`, \n"+
                    "IFNULL(tbl.`s`, '0') AS S, IFNULL(tbl.`i`, '0') AS I, IFNULL(tbl.`u`, '0') AS U, IFNULL(tbl.`d`, '0') AS D, IFNULL(tbl.`v`, '0') AS V,  \n" +
                    "IF(tbl.`s` = '1' AND tbl.`i` = '1' AND tbl.`u` = '1' AND tbl.`d` = '1' AND tbl.`v` = '1', '1', '0') AS T, IFNULL(tbl.`idpxm`, '0') IDMOD\n" +
                    "FROM modulos m\n" +
                    "LEFT JOIN (\n" +
                    "	SELECT per.`id`, per.`tipo`, per.`valor_tipo`, \n" +
                    "	pxm.`id` AS idpxm, pxm.`id_modulo`, pxm.`s`, pxm.`i`, pxm.`u`, pxm.`d`, pxm.`v`\n" +
                    "	FROM permisos per \n" +
                    "	LEFT JOIN permisosxmodulos pxm ON per.`id` = pxm.`id_permiso` \n" +
                    "	WHERE per.id = "+ID+"\n" +
                    ") tbl ON tbl.`id_modulo` = m.`id` \n" +
                    "ORDER BY m.`id`";
            
            Info = mySQL.ListSQL(sql);
            ModeloPermisos modeloRetorno = new ModeloPermisos();
            ArrayList<ModeloPermisoxModulos> listaModeloPermisoModulos = new ArrayList<>();
            
            if(Info.size()>0){
                modeloRetorno.setId(Info.get(0).get("ID_PERMISO"));
                modeloRetorno.setTipo(Info.get(0).get("TIPO"));
                modeloRetorno.setValor_tipo(Info.get(0).get("VALOR"));
                
                for (Map<String, String> datTbl : Info) {
                    listaModeloPermisoModulos.add(
                            new ModeloPermisoxModulos( 
                                    datTbl.get("IDMOD"), 
                                    datTbl.get("ID_MODULO"), 
                                    datTbl.get("MODULO"), 
                                    datTbl.get("ID_PERMISO"), 
                                    datTbl.get("S"), 
                                    datTbl.get("I"), 
                                    datTbl.get("U"), 
                                    datTbl.get("D"), 
                                    datTbl.get("V"), 
                                    "NOW()", 
                                    "")
                    );
                    
                }
                modeloRetorno.setListaPermisoModulos(listaModeloPermisoModulos);
            }
            
            return modeloRetorno;
        }catch(Exception e){
            return new ModeloPermisos();
        }
    }

    @Override
    public int Guardar(Object _permisos) {
        try {
            ArrayList<String> consultas = new ArrayList<String>();
            ModeloPermisos permisos = (ModeloPermisos) _permisos;
            
            String id_permiso = "(SELECT id FROM permisos p WHERE p.`tipo` = '"+permisos.getTipo()+"' AND p.`valor_tipo` = '"+permisos.getValor_tipo()+"')";
            
            consultas.add("INSERT INTO `permisos`\n" +
                                "(`tipo`, `valor_tipo`, `id_usuario`, `fecha`)\n" +
                                "VALUES \n" +
                                "('"+permisos.getTipo()+"', '"+permisos.getValor_tipo()+"', "+permisos.getId_usuario()+", "+permisos.getFecha()+");");
            
            
            for(ModeloPermisoxModulos mod: permisos.getListaPermisoModulos()){
                consultas.add("INSERT INTO `permisosxmodulos`\n" +
                                "(`id_permiso`, `id_modulo`, `s`, `i`, `u`, `d`, `v`, `id_usuario`, `fecha`)\n" +
                                "VALUES \n" +
                                "("+id_permiso+", "+mod.getId_modulo()+", '"+mod.getS()+"', '"+mod.getI()+"', '"+mod.getU()+"', '"+mod.getD()+"', '"+mod.getV()+"', "+permisos.getId_usuario()+", "+permisos.getFecha()+");");
            }
            
            
            System.out.println("***********consultas***************");
            int ret = 0;
            if (consultas.size() > 0) {
                if (mySQL.EnviarConsultas(consultas)) {
                    ret =Retorno.EXITO;
                } else {
                    ret=Retorno.ERROR;
                    
                }
            } else {
                ret=Retorno.CLASE_NO_ENCONTRADA;
                
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Ocurrio un error al tratar de obtener los datos\n"
                    + "______________________________________________________\n"
                    + "Detalles:\n" + e.getMessage());
            return Retorno.DEFECTO;
        }
    }

    @Override
    public int Actualizar(Object _permisos) {
        try {
            ArrayList<String> consultas = new ArrayList<String>();
            ModeloPermisos permisos = (ModeloPermisos) _permisos;
            
            
            consultas.add("UPDATE `permisos`\n" +
                                "SET `tipo` = '"+permisos.getTipo()+"', `valor_tipo` = '"+permisos.getValor_tipo()+"'\n" +
                                "WHERE `id` = "+permisos.getId()+";");
            
            
            for(ModeloPermisoxModulos mod: permisos.getListaPermisoModulos()){
                consultas.add("UPDATE `permisosxmodulos`\n" +
                                "SET `s` = '"+mod.getS()+"',\n" +
                                "  `i` = '"+mod.getI()+"',\n" +
                                "  `u` = '"+mod.getU()+"',\n" +
                                "  `d` = '"+mod.getD()+"',\n" +
                                "  `v` = '"+mod.getV()+"',\n" +
                                "  `id_usuario` = "+permisos.getId_usuario()+",\n" +
                                "  `fecha` = "+permisos.getFecha()+"\n" +
                                "WHERE `id` = "+mod.getId()+";");
            }
            
            
            System.out.println("***********consultas***************");
            int ret = 0;
            if (consultas.size() > 0) {
                if (mySQL.EnviarConsultas(consultas)) {
                    ret = Retorno.EXITO;
                } else {
                    ret = Retorno.ERROR;
                }
            } else {
                ret = Retorno.CLASE_NO_ENCONTRADA;
                
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Ocurrio un error al tratar de obtener los datos\n"
                    + "______________________________________________________\n"
                    + "Detalles:\n" + e.getMessage());
            return Retorno.DEFECTO;
        }
    }

    @Override
    public int Eliminar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosFiltro(Object _datos) {
        try{
            Map<String, String> datos = (HashMap<String, String>) _datos;
            List<Map<String, String>> Info = new ArrayList<>();
            
            String sql = "";
            //     0           1        2    3  4  5  6  7  8    9
            //ID_PERMISO, ID_MODULO, MODULO, S, I, U, D, V, T, IDPXM
            
            sql = "SELECT IFNULL(tbl.id, '0') AS ID_PERMISO, m.id AS ID_MODULO, m.`Descripcion` AS MODULO, \n" +
                    "IFNULL(tbl.`s`, '0') AS S, IFNULL(tbl.`i`, '0') AS I, IFNULL(tbl.`u`, '0') AS U, IFNULL(tbl.`d`, '0') AS D, IFNULL(tbl.`v`, '0') AS V,  \n" +
                    "IF(tbl.`s` = '1' AND tbl.`i` = '1' AND tbl.`u` = '1' AND tbl.`d` = '1' AND tbl.`v` = '1', '1', '0') AS T, IFNULL(tbl.`idpxm`, '0') IDMOD\n" +
                    "FROM modulos m\n" +
                    "LEFT JOIN (\n" +
                    "	SELECT per.`id`, per.`tipo`, per.`valor_tipo`, \n" +
                    "	pxm.`id` AS idpxm, pxm.`id_modulo`, pxm.`s`, pxm.`i`, pxm.`u`, pxm.`d`, pxm.`v`\n" +
                    "	FROM permisos per \n" +
                    "	LEFT JOIN permisosxmodulos pxm ON per.`id` = pxm.`id_permiso` \n" +
                    "	WHERE (per.`tipo` = '"+datos.get("TIPO")+"' AND per.valor_tipo = '"+datos.get("VALOR")+"') OR per.`tipo` IS NULL\n" +
                    ") tbl ON tbl.`id_modulo` = m.`id` \n" +
                    "ORDER BY m.`id`";
            
            Info = mySQL.ListSQL(sql);
            ModeloPermisos modeloRetorno = new ModeloPermisos();
            ArrayList<ModeloPermisoxModulos> listaModeloPermisoModulos = new ArrayList<>();
            
            if(Info.size()>0){
                modeloRetorno.setId(Info.get(0).get("ID_PERMISO"));
                modeloRetorno.setTipo(datos.get("TIPO"));
                modeloRetorno.setValor_tipo(datos.get("VALOR"));
                
                for (Map<String, String> datTbl : Info) {
                    listaModeloPermisoModulos.add(
                            new ModeloPermisoxModulos( 
                                    datTbl.get("IDMOD"), 
                                    datTbl.get("ID_MODULO"), 
                                    datTbl.get("MODULO"), 
                                    datTbl.get("ID_PERMISO"), 
                                    datTbl.get("S"), 
                                    datTbl.get("I"), 
                                    datTbl.get("U"), 
                                    datTbl.get("D"), 
                                    datTbl.get("V"), 
                                    "NOW()", 
                                    "")
                    );
                    
                }
                modeloRetorno.setListaPermisoModulos(listaModeloPermisoModulos);
            }
            
            return modeloRetorno;
        }catch(Exception e){
            return new ModeloPermisos();
        }
    }

    public List<Map<String, String>> getModulos(String tipo, String valor) {
        try{
            List<Map<String, String>> Info = new ArrayList<>();
            
            String sql = "";
            //     0           1        2    3  4  5  6  7  8    9
            //ID_PERMISO, ID_MODULO, MODULO, S, I, U, D, V, T, IDPXM
            
            sql = "SELECT IFNULL(tbl.id, '-1') AS ID_PERMISO, m.id AS ID_MODULO, m.`Descripcion` AS MODULO, \n" +
                    "IFNULL(tbl.`s`, '0') AS S, IFNULL(tbl.`i`, '0') AS I, IFNULL(tbl.`u`, '0') AS U, IFNULL(tbl.`d`, '0') AS D, IFNULL(tbl.`v`, '0') AS V,  \n" +
                    "IF(tbl.`s` = '1' AND tbl.`i` = '1' AND tbl.`u` = '1' AND tbl.`d` = '1' AND tbl.`v` = '1', '1', '0') AS T, IFNULL(tbl.`idpxm`, '0') IDMOD\n" +
                    "FROM modulos m\n" +
                    "LEFT JOIN (\n" +
                    "	SELECT per.`id`, per.`tipo`, per.`valor_tipo`, \n" +
                    "	pxm.`id` AS idpxm, pxm.`id_modulo`, pxm.`s`, pxm.`i`, pxm.`u`, pxm.`d`, pxm.`v`\n" +
                    "	FROM permisos per \n" +
                    "	LEFT JOIN permisosxmodulos pxm ON per.`id` = pxm.`id_permiso` \n" +
                    "	WHERE (per.`tipo` = '"+tipo+"' AND per.valor_tipo = '"+valor+"') OR per.`tipo` IS NULL\n" +
                    ") tbl ON tbl.`id_modulo` = m.`id` \n" +
                    "ORDER BY m.`id`";
            
            System.out.println("sql---<"+sql);
            Info = mySQL.ListSQL(sql);
            return Info;
        }catch(Exception e){
            return null;
        }
    }
    
    
    
}
