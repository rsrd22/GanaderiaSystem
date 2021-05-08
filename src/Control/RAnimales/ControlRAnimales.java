/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.RAnimales;

import BaseDeDatos.gestorMySQL;
import Control.ControlTraslado;
import Control.IControl;
import Modelo.RAnimales.ModeloRAnimales;
import Modelo.RAnimales.ModeloRAnimalesSalida;
import Utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DOLFHANDLER
 */
public class ControlRAnimales implements IControl {

    private gestorMySQL mySQL;
    private ControlTraslado control;
    private final ArrayList<ModeloRAnimales> LISTA_VACIA = new ArrayList<ModeloRAnimales>();

    public ControlRAnimales() {
        mySQL = new gestorMySQL();
        control = new ControlTraslado();
    }

    @Override
    public Object ObtenerDatos() {
        //<editor-fold defaultstate="collapsed" desc="selectDatosFromQuery">
        String consulta = "SELECT \n"
                + "a.*,\n"
                + "b.descripcion descTipoAnimal, \n"
                + "c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,\n"
                + "b.id_finca idFinca,\n"
                + "e.descripcion descFinca,\n"
                + "d.id_propietario idPropietario,\n"
                + "CONCAT(\n"
                + "f.identificacion,\n"
                + "   ' - ',\n"
                + "   CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)),\n"
                + "   ' ',\n"
                + "   TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))\n"
                + ") descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,\n"
                + "IFNULL(a.peso_destete,0) pesodestete\n"
                + "FROM ranimales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "ORDER BY a.numero ASC";
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<ModeloRAnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new ModeloRAnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("cantidad_parto"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
                        animal.get("es_madre"),
                        animal.get("fecha"),
                        animal.get("fecha_destete"),
                        animal.get("fecha_muerte"),
                        animal.get("fecha_nacimiento"),
                        animal.get("fecha_novilla"),
                        animal.get("fecha_venta"),
                        animal.get("genero"),
                        animal.get("grupo"),
                        animal.get("hierro"),
                        animal.get("hierro_fisico"),
                        animal.get("id"),
                        animal.get("id_tipo_animal"),
                        animal.get("id_usuario"),
                        animal.get("implante"),
                        animal.get("muerte"),
                        animal.get("notas"),
                        animal.get("numero"),
                        animal.get("numero_descendiente"),
                        animal.get("numero_mama"),
                        animal.get("numero_mama_adoptiva"),
                        animal.get("numero_parto"),
                        animal.get("peso"),
                        animal.get("peso_canal"),
                        animal.get("peso_destete"),
                        animal.get("precio_venta"),
                        animal.get("tipo_venta"),
                        animal.get("venta"),
                        animal.get("descTipoAnimal"),
                        animal.get("descGrupo"),
                        animal.get("descHierro"),
                        animal.get("idFinca"),
                        animal.get("descFinca"),
                        animal.get("idPropietario"),
                        animal.get("descPropietario"),
                        animal.get("pesocanal"),
                        animal.get("pesodestete")
                ));
                //</editor-fold>
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public Object ObtenerDatosKey(String ID) {
        //<editor-fold defaultstate="collapsed" desc="selectDatosFromQuery">
        String consulta = "SELECT \n"
                + "a.*,\n"
                + "b.descripcion descTipoAnimal, \n"
                + "c.descripcion descGrupo, \n"
                + "d.descripcion descHierro,\n"
                + "b.id_finca idFinca,\n"
                + "e.descripcion descFinca,\n"
                + "d.id_propietario idPropietario,\n"
                + "CONCAT(\n"
                + "f.identificacion,\n"
                + "   ' - ',\n"
                + "   CONCAT(TRIM(CONCAT(f.primer_nombre,' ',f.segundo_nombre)),\n"
                + "   ' ',\n"
                + "   TRIM(CONCAT(f.primer_apellido,' ',f.segundo_apellido)))\n"
                + ") descPropietario,\n"
                + "IFNULL(a.peso_canal,0) pesocanal,\n"
                + "IFNULL(a.peso_destete,0) pesodestete\n"
                + "FROM _animales a\n"
                + "LEFT JOIN tipo_animales b ON a.id_tipo_animal=b.id\n"
                + "LEFT JOIN grupos c ON a.grupo=c.id\n"
                + "LEFT JOIN propietarioxhierro d ON a.hierro=d.id\n"
                + "LEFT JOIN fincas e ON b.id_finca=e.id\n"
                + "LEFT JOIN propietarios f ON d.id_propietario=f.id\n"
                + "WHERE a.id=" + ID;
        //</editor-fold>
        List<Map<String, String>> _animales = new ArrayList<Map<String, String>>();
        _animales = mySQL.ListSQL(consulta);

        ArrayList<ModeloRAnimalesSalida> lista = new ArrayList<>();

        if (Utilidades.contieneElementos(_animales)) {
            for (Map<String, String> animal : _animales) {
                //<editor-fold defaultstate="collapsed" desc="setDatosToList">
                lista.add(new ModeloRAnimalesSalida(
                        animal.get("calificacion"),
                        animal.get("cantidad_parto"),
                        animal.get("capado"),
                        animal.get("descornado"),
                        animal.get("descripcion_muerte"),
                        animal.get("destete"),
                        animal.get("es_madre"),
                        animal.get("fecha"),
                        animal.get("fecha_destete"),
                        animal.get("fecha_muerte"),
                        animal.get("fecha_nacimiento"),
                        animal.get("fecha_novilla"),
                        animal.get("fecha_venta"),
                        animal.get("genero"),
                        animal.get("grupo"),
                        animal.get("hierro"),
                        animal.get("hierro_fisico"),
                        animal.get("id"),
                        animal.get("id_tipo_animal"),
                        animal.get("id_usuario"),
                        animal.get("implante"),
                        animal.get("muerte"),
                        animal.get("notas"),
                        animal.get("numero"),
                        animal.get("numero_descendiente"),
                        animal.get("numero_mama"),
                        animal.get("numero_mama_adoptiva"),
                        animal.get("numero_parto"),
                        animal.get("peso"),
                        animal.get("peso_canal"),
                        animal.get("peso_destete"),
                        animal.get("precio_venta"),
                        animal.get("tipo_venta"),
                        animal.get("venta"),
                        animal.get("descTipoAnimal"),
                        animal.get("descGrupo"),
                        animal.get("descHierro"),
                        animal.get("idFinca"),
                        animal.get("descFinca"),
                        animal.get("idPropietario"),
                        animal.get("descPropietario"),
                        animal.get("pesocanal"),
                        animal.get("pesodestete")
                ));
                //</editor-fold>
            }
            return lista;
        } else {
            return LISTA_VACIA;
        }
    }

    @Override
    public int Guardar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Actualizar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Eliminar(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object ObtenerDatosFiltro(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
