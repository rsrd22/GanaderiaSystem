package Modelo;

import java.util.ArrayList;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloPalpacion {

    private String id;
    private String id_animal;
    private String fecha_palpacion;
    private String diagnostico;
    private String notas;
    private String num_meses;
    private String fecha_ultimo_parto;
    private String descarte;
    private String razondescarte;
    private String estado;
    private String fecha;
    private String id_usuario;
    private ArrayList<ModeloMedicamentosPorPesaje> listaMedicamentos;

    public ModeloPalpacion() {
        listaMedicamentos = new ArrayList<>();
    }

    public ModeloPalpacion(String id, String id_animal, String fecha_palpacion, String diagnostico, String notas, String num_meses, String fecha_ultimo_parto, String descarte, String fecha, String id_usuario, String razondescarte, String estado) {
        this.id = id;
        this.id_animal = id_animal;
        this.fecha_palpacion = fecha_palpacion;
        this.diagnostico = diagnostico;
        this.notas = notas;
        this.num_meses = num_meses;
        this.fecha_ultimo_parto = fecha_ultimo_parto;
        this.descarte = descarte;
        this.razondescarte = razondescarte;
        this.estado = estado;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        listaMedicamentos = new ArrayList<>();
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

    public String getFecha_palpacion() {
        return fecha_palpacion;
    }

    public void setFecha_palpacion(String fecha_palpacion) {
        this.fecha_palpacion = fecha_palpacion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getNum_meses() {
        return num_meses;
    }

    public void setNum_meses(String num_meses) {
        this.num_meses = num_meses;
    }

    public String getFecha_ultimo_parto() {
        return fecha_ultimo_parto;
    }

    public void setFecha_ultimo_parto(String fecha_ultimo_parto) {
        this.fecha_ultimo_parto = fecha_ultimo_parto;
    }

    public String getDescarte() {
        return descarte;
    }

    public void setDescarte(String descarte) {
        this.descarte = descarte;
    }

    public String getRazondescarte() {
        return razondescarte;
    }

    public void setRazondescarte(String razondescarte) {
        this.razondescarte = razondescarte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    public void addMedicamentos(ModeloMedicamentosPorPesaje mmpp){
        listaMedicamentos.add(mmpp);
    }


    public ArrayList<ModeloMedicamentosPorPesaje> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(ArrayList<ModeloMedicamentosPorPesaje> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

}
