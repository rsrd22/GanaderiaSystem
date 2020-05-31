/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author MERRY
 */
public class ModeloLotes {
    private String id;
    private String numero;
    private String id_bloque;
    private String numero_bloque;
    private String id_finca;
    private String[] idxfuentehidrica;
    private String[] id_fuente_hidrica;
    private String[] Fuente_Hidrica;
    private String area;
    private String fecha;
    private String id_usuario;

    public ModeloLotes() {
        this.id = null;
        this.numero = null;
        this.id_bloque = null;
        this.numero_bloque = null;
        this.id_finca = null;
        this.idxfuentehidrica = null;
        this.id_fuente_hidrica = null;
        this.Fuente_Hidrica = null;
        this.area = null;
        this.fecha = null;
        this.id_usuario = null;
    }

    public ModeloLotes(String id, String numero, String id_bloque, String numero_bloque, String[] idxfuentehidrica, String[] id_fuente_hidrica, String[] Fuente_Hidrica, String area, String fecha, String id_usuario) {
        this.id = id;
        this.numero = numero;
        this.id_bloque = id_bloque;
        this.numero_bloque = numero_bloque;
        this.id_finca = null;
        this.idxfuentehidrica = idxfuentehidrica;
        this.id_fuente_hidrica = id_fuente_hidrica;
        this.Fuente_Hidrica = Fuente_Hidrica;
        this.area = area;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
    }

    public String[] getFuente_Hidrica() {
        return Fuente_Hidrica;
    }

    public void setFuente_Hidrica(String[] Fuente_Hidrica) {
        this.Fuente_Hidrica = Fuente_Hidrica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getId_bloque() {
        return id_bloque;
    }

    public void setId_bloque(String id_bloque) {
        this.id_bloque = id_bloque;
    }

    public String[] getId_fuente_hidrica() {
        return id_fuente_hidrica;
    }

    public void setId_fuente_hidrica(String[] id_fuente_hidrica) {
        this.id_fuente_hidrica = id_fuente_hidrica;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getId_finca() {
        return id_finca;
    }

    public void setId_finca(String id_finca) {
        this.id_finca = id_finca;
    }

    public String getNumero_bloque() {
        return numero_bloque;
    }

    public void setNumero_bloque(String numero_bloque) {
        this.numero_bloque = numero_bloque;
    }

    public String[] getIdxfuentehidrica() {
        return idxfuentehidrica;
    }

    public void setIdxfuentehidrica(String[] idxfuentehidrica) {
        this.idxfuentehidrica = idxfuentehidrica;
    }
    
    
}
