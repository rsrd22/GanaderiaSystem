/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import BaseDeDatos.gestorMySQL;
import Modelo.ModeloMedicamentosPorPesaje;
import Modelo.ModeloPesaje;
import Utilidades.datosUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlPesaje implements IControl {

    private gestorMySQL mySQL;
    private final List<Map<String, String>> LISTA_VACIA = new ArrayList<Map<String, String>>();

    public ControlPesaje() {
        mySQL = new gestorMySQL();
    }

    @Override
    public Object ObtenerDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        String consulta = "SELECT * FROM pesaje\n"
                + " WHERE id_animal=" + ID + " ORDER BY CONVERT(fecha, DATE) DESC";
        List<Map<String, String>> pesajes = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPesaje> lista = new ArrayList<>();
        pesajes = mySQL.ListSQL(consulta);

        if (pesajes.size() > 0) {
            for (Map<String, String> pesaje : pesajes) {
                lista.add(new ModeloPesaje(
                        pesaje.get("descornado"),
                        pesaje.get("destete"),
                        pesaje.get("fecha"),
                        pesaje.get("fecha_pesado"),
                        pesaje.get("hierro"),
                        pesaje.get("id"),
                        pesaje.get("id_animal"),
                        pesaje.get("id_usuario"),
                        pesaje.get("implante"),
                        pesaje.get("notas"),
                        pesaje.get("peso"),
                        "", "", "",
                        pesaje.get("peso_anterior"),
                        "0",
                        pesaje.get("estado")
                ));
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPesaje modelo = (ModeloPesaje) o;

        if (modelo.getEstado().equals("Activo")) {
            consultas.add(
                    "UPDATE pesaje SET estado='Inactivo' WHERE id_animal=" + modelo.getId_animal() + " and estado='Activo'"
            );

            //<editor-fold defaultstate="collapsed" desc="ACTUALIZO EL PESO EN LA TABLA ANIMALES">
            consultas.add("update ranimales\n"
                    + "set \n"
                    + "peso = " + modelo.getPeso() + "\n"
                    + "where id = " + modelo.getId_animal() + "");
//</editor-fold>
        }
        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,notas,"
                + "hierro,descornado,implante,destete,fecha,id_usuario,"
                + "peso_anterior,estado) VALUES(\n"
                + "" + modelo.getId() + ",\n"
                + "" + modelo.getId_animal() + ",\n"
                + "'" + modelo.getFecha_pesado() + "',\n"
                + "" + modelo.getPeso() + ",\n"
                + "'" + modelo.getNotas() + "',\n"
                + "'" + modelo.getHierro() + "',\n"
                + "'" + modelo.getDescornado() + "',\n"
                + "'" + modelo.getImplante() + "',\n"
                + "'" + modelo.getDestete() + "',\n"
                + "" + modelo.getFecha() + ",\n"
                + "" + modelo.getId_usuario() + ",\n"
                + "" + modelo.getPeso_anterior() + ",\n"
                + "'" + modelo.getEstado() + "'\n"
                + ")" //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJEPORMEDICAMENTOS">
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
                    + "0,\n"
                    + "(SELECT id FROM pesaje WHERE id_animal = " + modelo.getId_animal() + " AND DATE_FORMAT(fecha,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n"
                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                    + ")"
            //</editor-fold>
            );
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update ranimales\n"
                + "set \n"
                + "hierro_fisico = '" + modelo.getHierro() + "',\n"
                + "implante = '" + modelo.getImplante() + "',\n"
                + "descornado = '" + modelo.getDescornado() + "',\n"
                + "destete = '" + modelo.getDestete() + "',\n"
                + "fecha_destete = '" + modelo.getFechaDestete() + "',\n"
                + "peso_destete = '" + modelo.getPeso_destete() + "',\n"
                + "hierro = " + modelo.getIdHierro() + "\n"
                + "where id = " + modelo.getId_animal() + "");
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
        ModeloPesaje modelo = (ModeloPesaje) o;

        if (modelo.getEstado().equals("Activo")) {
            consultas.add(
                    "UPDATE pesaje SET estado='Inactivo' WHERE id_animal=" + modelo.getId_animal() + " and estado='Activo'"
            );

            //<editor-fold defaultstate="collapsed" desc="ACTUALIZO EL PESO EN LA TABLA ANIMALES">
            consultas.add("update animales\n"
                    + "set \n"
                    + "peso = " + modelo.getPeso() + "\n"
                    + "where id = " + modelo.getId_animal() + "");
//</editor-fold>
        }
        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="UPDATE">
                "UPDATE pesaje SET\n"
                + "id_animal=" + modelo.getId_animal() + ",\n"
                + "fecha_pesado='" + modelo.getFecha_pesado() + "',\n"
                + "peso=" + modelo.getPeso() + ",\n"
                + "notas='" + modelo.getNotas() + "',\n"
                + "estado='" + modelo.getEstado() + "',\n"
                + "hierro='" + modelo.getHierro() + "',\n"
                + "descornado='" + modelo.getDescornado() + "',\n"
                + "implante='" + modelo.getImplante() + "',\n"
                + "destete='" + modelo.getDestete() + "',\n"
                + "fecha=" + modelo.getFecha() + ",\n"
                + "id_usuario=" + modelo.getId_usuario() + "\n"
                + " WHERE id=" + modelo.getId() //</editor-fold>
        );
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJEPORMEDICAMENTOS">
        if (modelo.getListaMedicamentos().size() > 0) {
            if (modelo.getListaMedicamentos().get(0).isEliminar()) {
                consultas.add(
                        //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS MEDICAMENTOS">
                        "DELETE FROM pesajexmedicamento WHERE id_pesaje=" + modelo.getId()
                //</editor-fold>
                );
            }
        }
        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="INSERT">
                    "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
                    + "0,\n"
                    + "" + modelo.getId() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
                    + ")"
            //</editor-fold>
            );
        }
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update animales\n"
                + "set \n"
                + "hierro_fisico = '" + modelo.getHierro() + "',\n"
                + "implante = '" + modelo.getImplante() + "',\n"
                + "descornado = '" + modelo.getDescornado() + "',\n"
                + "destete = '" + modelo.getDestete() + "',\n"
                + "fecha_destete = '" + modelo.getFechaDestete() + "',\n"
                + "peso_destete = '" + modelo.getPeso_destete() + "',\n"
                + "peso_destete = " + modelo.getPeso_destete() + ",\n"
                + "hierro = " + modelo.getIdHierro() + "\n"
                + "where id = " + modelo.getId_animal() + "");
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
        ModeloPesaje modelo = (ModeloPesaje) o;

        ArrayList<ModeloMedicamentosPorPesaje> listaMedicamentos = new ArrayList<>();
        listaMedicamentos = (ArrayList<ModeloMedicamentosPorPesaje>) ObtenerListaMedicamentos(modelo.getId());
        String campo = "(SELECT $||$ FROM pesaje WHERE id_animal=" + modelo.getId_animal() + " ORDER BY CONVERT(fecha_pesado, DATE) DESC LIMIT 1)";

        //<editor-fold defaultstate="collapsed" desc="SE ESTABLECEN LOS VALORES DEL REGISTRO MAS RECIENTE">
        modelo.setPeso(campo.replace("$||$", "peso"));
        modelo.setHierro(campo.replace("$||$", "hierro"));
        modelo.setImplante(campo.replace("$||$", "implante"));
        modelo.setDescornado(campo.replace("$||$", "descornado"));
        modelo.setDestete(campo.replace("$||$", "destete"));
        modelo.setFechaDestete(campo.replace("$||$", "fecha_destete"));
        modelo.setIdHierro("");
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="SE ELIMINA EL REGISTRO DEL PESO">
        consultas.add(
                "DELETE FROM pesaje WHERE id=" + modelo.getId()
        );
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="PROCEDIMIENTO PARA ACTUALIZAR HISTORICO DE PESOS">
        consultas.add(
                "CALL actualizarPesos("
                + modelo.getId_animal() + ", "
                + datosUsuario.datos.get(0).get("ID_USUARIO") + ""
                + ");"
        );
//</editor-fold>

        if (listaMedicamentos.size() > 0) {
            consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS MEDICAMENTOS">
                    "DELETE FROM pesajexmedicamento WHERE id_pesaje=" + modelo.getId()
            //</editor-fold>
            );
        }

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update animales\n"
                + "set \n"
                + "peso = " + modelo.getPeso() + ",\n"
                + "hierro_fisico = " + modelo.getHierro() + ",\n"
                + "implante = " + modelo.getImplante() + ",\n"
                + "descornado = " + modelo.getDescornado() + ",\n"
                + "destete = " + modelo.getDestete() + ",\n"
                + "fecha_destete = " + modelo.getFechaDestete() + ",\n"
                + "peso_destete = " + modelo.getPeso_destete() + ",\n"
                + "hierro = " + (modelo.getIdHierro().isEmpty() ? "hierro" : modelo.getIdHierro()) + "\n"
                + "where id = " + modelo.getId_animal() + ""
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

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        String consulta = "SELECT a.id,a.id_animal,a.fecha_pesado,a.peso,\n"
                + "a.peso_anterior,a.notas,b.hierro_fisico AS hierro,b.descornado,\n"
                + "b.implante,\n"
                + "b.destete as destete,\n"
                + "a.estado,a.fecha,a.id_usuario,\n"
                + "b.hierro AS IDHIERRO,\n"
                + "c.descripcion AS DESCRIPCION_HIERRO,\n"
                + "b.fecha_destete AS FECHA_DESTETE,\n"
                + "b.peso_destete AS PESO_DESTETE\n"
                + "FROM pesaje a\n"
                + "LEFT JOIN ranimales b ON a.id_animal=b.id\n"
                + "LEFT JOIN propietarioxhierro c ON b.hierro=c.id\n"
                + "WHERE id_animal=" + o.toString() + " ORDER BY fecha_pesado DESC";
        List<Map<String, String>> pesajes = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPesaje> lista = new ArrayList<>();
        pesajes = mySQL.ListSQL(consulta);

        if (pesajes.size() > 0) {

            for (Map<String, String> pesaje : pesajes) {
                ModeloPesaje modelo = new ModeloPesaje(
                        pesaje.get("descornado"),
                        pesaje.get("destete"),
                        pesaje.get("fecha"),
                        pesaje.get("fecha_pesado"),
                        pesaje.get("hierro"),
                        pesaje.get("id"),
                        pesaje.get("id_animal"),
                        pesaje.get("id_usuario"),
                        pesaje.get("implante"),
                        pesaje.get("notas"),
                        pesaje.get("peso"),
                        pesaje.get("DESCRIPCION_HIERRO"),
                        pesaje.get("IDHIERRO"),
                        pesaje.get("FECHA_DESTETE"),
                        pesaje.get("peso_anterior"),
                        pesaje.get("PESO_DESTETE"),
                        pesaje.get("estado")
                );
                consulta = "SELECT \n"
                        + "a.id AS ID, \n"
                        + "a.id_medicamento AS IDMEDICAMENTO,\n"
                        + "b.descripcion AS MEDICAMENTO, \n"
                        + "a.dosis AS DOSIS, \n"
                        + "b.unidad_medida AS UNIDAD_MEDIDA\n"
                        + "FROM pesajexmedicamento a\n"
                        + "INNER JOIN medicamentos b ON b.id = a.id_medicamento\n"
                        + "WHERE a.id_pesaje = " + modelo.getId();
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
                modelo.setListaMedicamentos(lisMed);
                lista.add(modelo);
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    public int GuardarPesajeDescarte(Object o) {
        ArrayList<String> consultas = new ArrayList<>();
        ModeloPesaje modelo = (ModeloPesaje) o;

        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJE">
        consultas.add(
                //<editor-fold defaultstate="collapsed" desc="INSERT">
                "INSERT INTO pesaje (id,id_animal,fecha_pesado,peso,notas,hierro,descornado,"
                + "implante,destete,fecha,id_usuario,peso_anterior) VALUES(\n"
                + "0,\n"
                + "" + modelo.getId_animal() + ",\n"
                + "'" + modelo.getFecha_pesado() + "',\n"
                + "" + modelo.getPeso() + ",\n"
                + "'" + modelo.getNotas() + "',\n"
                + "'" + modelo.getHierro() + "',\n"
                + "'" + modelo.getDescornado() + "',\n"
                + "'" + modelo.getImplante() + "',\n"
                + "'" + modelo.getDestete() + "',\n"
                + "" + modelo.getFecha() + ",\n"
                + "" + modelo.getId_usuario() + ",\n"
                + "" + modelo.getPeso_anterior() + "\n"
                + ")" //</editor-fold>
        );
//</editor-fold>
//
//        //<editor-fold defaultstate="collapsed" desc="INSERTO EN LA TABLA PESAJEPORMEDICAMENTOS">
//        for (int i = 0; i < modelo.getListaMedicamentos().size(); i++) {
//            consultas.add(
//                    //<editor-fold defaultstate="collapsed" desc="INSERT">
//                    "INSERT INTO pesajexmedicamento (id,id_pesaje,id_medicamento,dosis) VALUES(\n"
//                    + "0,\n"
//                    + "(SELECT id FROM pesaje WHERE id_animal = " + modelo.getId_animal() + " AND DATE_FORMAT(fecha,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')),\n"
//                    + "" + modelo.getListaMedicamentos().get(i).getId_medicamento() + ",\n"
//                    + "" + modelo.getListaMedicamentos().get(i).getDosis() + "\n"
//                    + ")"
//            //</editor-fold>
//            );
//        }
////</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
        consultas.add("update animales\n"
                + "set \n"
                + "peso = " + modelo.getPeso() + "\n"
                + "where id = " + modelo.getId_animal() + "");
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

    public String getFechaPesajeActiva(String idAnimal) {
        String consulta = "SELECT DATE_FORMAT(fecha_pesado, '%d/%m/%Y') AS FECHAPESADO FROM pesaje\n"
                + " WHERE id_animal=" + idAnimal + " and estado = 'Activo'";
        String ret = "";
        List<Map<String, String>> pesaje = new ArrayList<Map<String, String>>();
        ArrayList<ModeloPesaje> lista = new ArrayList<>();
        pesaje = mySQL.ListSQL(consulta);
        if (pesaje.size() > 0) {
            ret = pesaje.get(0).get("FECHAPESADO");
        }
        return ret;
    }

    public int ActualizarPesos(String procedimientoAlmacenado) {
        try {
            ArrayList<String> consultas = new ArrayList<>();
            consultas.add(procedimientoAlmacenado);
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

    public Object ObtenerListaMedicamentos(String idPesaje) {
        String consulta = "SELECT * FROM pesajexmedicamento WHERE id_pesaje=" + idPesaje;
        List<Map<String, String>> medicamentos = new ArrayList<Map<String, String>>();
        ArrayList<ModeloMedicamentosPorPesaje> lista = new ArrayList<>();
        medicamentos = mySQL.ListSQL(consulta);

        if (medicamentos.size() > 0) {
            for (Map<String, String> medicamento : medicamentos) {
                lista.add(new ModeloMedicamentosPorPesaje());
            }
            return lista;
        } else {
            return lista;
        }
    }

    public int AnularPesaje(ModeloPesaje modelo) {
        ArrayList<String> consultas = new ArrayList<>();
        consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="SE ELIMINA EL REGISTRO DEL PESO">
                "DELETE FROM pesaje WHERE id=" + modelo.getId()
        //</editor-fold>
        );

        if (modelo.getListaMedicamentos().size() > 0) {
            for (ModeloMedicamentosPorPesaje lista : modelo.getListaMedicamentos()) {
                consultas.add(
                    //<editor-fold defaultstate="collapsed" desc="SE ELIMINAN LOS MEDICAMENTOS">
                    "DELETE FROM pesajexmedicamento WHERE id=" + lista.getId()
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

    public int ActualizarDatosAnimal(ModeloPesaje modelo){
        ArrayList<String> consultas = new ArrayList<>();
        ArrayList<ModeloPesaje> lista = (ArrayList<ModeloPesaje>) ObtenerDatosKey(modelo.getId_animal());
        
        if(lista.size()>0){
            //<editor-fold defaultstate="collapsed" desc="ACTUALIZO LA TABLA ANIMALES">
                consultas.add("update animales\n"
                        + "set \n"
                        + "peso = " + lista.get(0).getPeso() + ",\n"
                        + "hierro_fisico = " + (lista.get(0).getHierro().isEmpty() ? "hierro_fisico" : "'" + lista.get(0).getHierro() + "'") + ",\n"
                        + "implante = " + (lista.get(0).getImplante().isEmpty() ? "implante" : "'" + lista.get(0).getImplante() + "'") + ",\n"
                        + "descornado = " + (lista.get(0).getDescornado().isEmpty() ? "descornado" : "'" + lista.get(0).getDescornado() + "'") + ",\n"
                        + "destete = " + (lista.get(0).getDestete().isEmpty() ? "destete" : "'" + lista.get(0).getDestete() + "'") + ",\n"
                        + "fecha_destete = " + (lista.get(0).getFechaDestete().isEmpty() ? "fecha_destete" : "'" + lista.get(0).getFechaDestete() + "'") + ",\n"
                        + "peso_destete = " + (lista.get(0).getPeso_destete().isEmpty() ? "peso_destete" : "'" + lista.get(0).getPeso_destete() + "'") + ",\n"
                        + "hierro = " + (lista.get(0).getIdHierro().isEmpty() ? "hierro" : lista.get(0).getIdHierro()) + "\n"
                        + "where id = " + lista.get(0).getId_animal() + "");
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
    
    

}
