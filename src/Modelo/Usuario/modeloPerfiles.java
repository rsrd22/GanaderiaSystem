/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Usuario;

/**
 *
 * @author DOLFHANDLER
 */
public class modeloPerfiles {

    private String descripcion;
    private String id;

    /**
     * Constructor del modelo
     * @param descripcion
     * @param id 
     */
    public modeloPerfiles(String descripcion, String id) {
        this.descripcion = descripcion;
        this.id = id;
    }

    /**
     * Constructor sin parametros de entrada
     */
    public modeloPerfiles() {
        this.descripcion = "";
        this.id = "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
