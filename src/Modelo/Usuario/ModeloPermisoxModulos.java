/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo.Usuario;

/**
 *
 * @author MERRY
 */
public class ModeloPermisoxModulos {
    private String id;
    private String id_modulo;
    private String modulo;
    private String id_permiso;
    private String s;
    private String i;
    private String u;
    private String d;
    private String v;
    private String fecha;
    private String id_usuario;

    public ModeloPermisoxModulos() {
    }
    /*** 
     * 
     * @param id
     * @param id_modulo
     * @param modulo
     * @param id_permiso
     * @param s
     * @param i
     * @param u
     * @param d
     * @param v
     * @param fecha
     * @param id_usuario 
     */
    public ModeloPermisoxModulos(String id, String id_modulo, String modulo, String id_permiso, String s, String i, String u, String d, String v, String fecha, String id_usuario) {
        this.id = id;
        this.id_modulo = id_modulo;
        this.modulo = modulo;
        this.id_permiso = id_permiso;
        this.s = s;
        this.i = i;
        this.u = u;
        this.d = d;
        this.v = v;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(String id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(String id_permiso) {
        this.id_permiso = id_permiso;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
    
}
