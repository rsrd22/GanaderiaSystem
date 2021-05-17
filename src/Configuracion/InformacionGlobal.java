/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

/**
 *
 * @author DOLFHANDLER
 */
public class InformacionGlobal {

    private static String idFinca = "";
    private static String idTipoAnimal = "";

    public static String getIdFinca() {
        return idFinca;
    }

    public static void setIdFinca(String aIdFinca) {
        idFinca = aIdFinca;
    }

    public static String getIdTipoAnimal() {
        return idTipoAnimal;
    }

    public static void setIdTipoAnimal(String aIdTipoAnimal) {
        idTipoAnimal = aIdTipoAnimal;
    }

}
