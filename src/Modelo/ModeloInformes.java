/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MERRY
 */
public class ModeloInformes {
    public String categoria;
    public String informe;
    public Map<String, String> informacion;
    public ModeloInformes() {
        this.categoria = "";
        this.informe = "";
    }

    public ModeloInformes(String categoria, String informe, Map<String, String> informacion) {
        this.categoria = categoria;
        this.informe = informe;
        this.informacion = informacion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public Map<String, String> getInformacion() {
        return informacion;
    }

    public void setInformacion(Map<String, String> informacion) {
        this.informacion = informacion;
    }
    
    
    
    
}
