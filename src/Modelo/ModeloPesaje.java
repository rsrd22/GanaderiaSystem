package Modelo;

import java.util.ArrayList;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloPesaje {

    private String descornado;
    private String destete;
    private String fecha;
    private String fecha_pesado;
    private String hierro;
    private String idHierro;
    private String descripcionHierro;
    private String fechaDestete;
    private String id;
    private String id_animal;
    private String id_usuario;
    private String implante;
    private String notas;
    private String peso;
    private String peso_anterior;
    private String peso_destete;
    private ArrayList<ModeloMedicamentosPorPesaje> listaMedicamentos;

    public ModeloPesaje() {
        listaMedicamentos = new ArrayList<>();
    }

    public ModeloPesaje(String descornado, String destete, String fecha, 
            String fecha_pesado, String hierro, String id, String id_animal, 
            String id_usuario, String implante, String notas, String peso,
            String descripcionHierro,String idHierro,String fechaDestete,
            String peso_anterior,String peso_destete) {
        this.descornado = descornado;
        this.destete = destete;
        this.fecha = fecha;
        this.fecha_pesado = fecha_pesado;
        this.hierro = hierro;
        this.id = id;
        this.id_animal = id_animal;
        this.id_usuario = id_usuario;
        this.implante = implante;
        this.notas = notas;
        this.peso = peso;
        this.descripcionHierro = descripcionHierro;
        this.idHierro = idHierro;
        this.fechaDestete = fechaDestete;
        this.peso_anterior = peso_anterior;
        this.peso_destete = peso_destete;
        listaMedicamentos = new ArrayList<>();
    }

    public String getPeso_destete() {
        return peso_destete;
    }

    public void setPeso_destete(String peso_destete) {
        this.peso_destete = peso_destete;
    }

    public String getPeso_anterior() {
        return peso_anterior;
    }

    public void setPeso_anterior(String peso_anterior) {
        this.peso_anterior = peso_anterior;
    }

    public String getIdHierro() {
        return idHierro;
    }

    public void setIdHierro(String idHierro) {
        this.idHierro = idHierro;
    }

    public String getFechaDestete() {
        return fechaDestete;
    }

    public void setFechaDestete(String fechaDestete) {
        this.fechaDestete = fechaDestete;
    }

    public String getDescripcionHierro() {
        return descripcionHierro;
    }

    public void setDescripcionHierro(String descripcionHierro) {
        this.descripcionHierro = descripcionHierro;
    }
    
    public void addMedicamentos(ModeloMedicamentosPorPesaje mmpp){
        listaMedicamentos.add(mmpp);
    }

    public String getDescornado() {
        return descornado;
    }

    public void setDescornado(String descornado) {
        this.descornado = descornado;
    }

    public String getDestete() {
        return destete;
    }

    public void setDestete(String destete) {
        this.destete = destete;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_pesado() {
        return fecha_pesado;
    }

    public void setFecha_pesado(String fecha_pesado) {
        this.fecha_pesado = fecha_pesado;
    }

    public String getHierro() {
        return hierro;
    }

    public void setHierro(String hierro) {
        this.hierro = hierro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_animal() {
        return id_animal;
    }

    public void setId_animal(String id_animal) {
        this.id_animal = id_animal;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getImplante() {
        return implante;
    }

    public void setImplante(String implante) {
        this.implante = implante;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public ArrayList<ModeloMedicamentosPorPesaje> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(ArrayList<ModeloMedicamentosPorPesaje> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

}
