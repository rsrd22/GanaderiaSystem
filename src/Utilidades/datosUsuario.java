/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Control.ControlGeneral;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class datosUsuario {

    /**
     * datos[0]=ID USUARIO<BR>
     * datos[1]=NOMBRE DE USUARIO<BR>
     * datos[2]=NUMERO DE ID<BR>
     * datos[3]=TIPO DE ID<BR>
     * datos[4]=PRIMER NOMBRE<BR>
     * datos[5]=SEGUNDO NOMBRE<BR>
     * datos[6]=PRIMER APELLIDO<BR>
     * datos[7]=SEGUNDO APELLIDO<BR>
     * datos[8]=FECHA DE NACIMIENTO<BR>
     * datos[9]=SEXO<BR>
     * datos[10]=EMAIL<BR>
     * datos[11]=TELEFONO<BR>
     * datos[12]=CELULAR<BR>
     _____________________________
     * datos[13]=ID PERMISOS<BR>
     * datos[14]=ID MODULO(0,1,2,3)<BR>
     * datos[15]=ID INSERT(0,1)<BR>
     * datos[16]=ID UPDATE(0,1)<BR>
     * datos[17]=ID DELETE(0,1)<BR>
     * datos[18]=ID VISIBLE(0,1)<BR>
     * datos[19] = ID PROPIETARIOS<BR>
     * datos[20] = NOMBRE PROPIETARIOS
     * ________________________________
     */
    public ControlGeneral general = new ControlGeneral();
    
    public static List<Map<String, String>> datos = new ArrayList<>();
    
    public static List<Map<String, String>> ModulosUsuario = new ArrayList<>();
    public static List<Map<String, String>> PropietariosUsuario = new ArrayList<>();
    public static List<Map<String, String>> Moduloscrud = new ArrayList<>();
    
}
