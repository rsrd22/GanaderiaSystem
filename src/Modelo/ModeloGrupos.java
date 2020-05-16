package Modelo;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloGrupos {

    private String id;
    private String idTipoAnimal;
    private String descTipoAnimal;
    private String descripcion;
    private String estado;
    private String idMacrogrupo;
    private String descMacrogrupo;
    private String fecha;
    private String idUsuario;
    private String idFinca;
    private String descFinca;
    private String idFincaOrigen;
    private String idTipoAnimalOrigen;
    private String descTipoAnimalOrigen;
    private String idFincaDestino;
    private String descFincaDestino;

    public ModeloGrupos() {
    }

    /**
     *
     * @param id
     * @param idTipoAnimal
     * @param descripcion
     * @param estado
     * @param idMacrogrupo
     * @param fecha
     * @param idUsuario
     * @param descTipoAnimal
     * @param descMacrogrupo
     */
    public ModeloGrupos(String id, String idTipoAnimal, String descripcion, 
            String estado, String idMacrogrupo, String fecha, String idUsuario, 
            String descTipoAnimal, String descMacrogrupo, String idFinca, String descFinca) {
        this.id = id;
        this.idTipoAnimal = idTipoAnimal;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idMacrogrupo = idMacrogrupo;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.descTipoAnimal = descTipoAnimal;
        this.descMacrogrupo = descMacrogrupo;
        this.idFinca = idFinca;
        this.descFinca = descFinca;
    }

    public String getDescTipoAnimalOrigen() {
        return descTipoAnimalOrigen;
    }

    public void setDescTipoAnimalOrigen(String descTipoAnimalOrigen) {
        this.descTipoAnimalOrigen = descTipoAnimalOrigen;
    }

    public String getDescFincaDestino() {
        return descFincaDestino;
    }

    public void setDescFincaDestino(String descFincaDestino) {
        this.descFincaDestino = descFincaDestino;
    }

    public String getIdFincaOrigen() {
        return idFincaOrigen;
    }

    public void setIdFincaOrigen(String idFincaOrigen) {
        this.idFincaOrigen = idFincaOrigen;
    }

    public String getIdTipoAnimalOrigen() {
        return idTipoAnimalOrigen;
    }

    public void setIdTipoAnimalOrigen(String idTipoAnimalOrigen) {
        this.idTipoAnimalOrigen = idTipoAnimalOrigen;
    }

    public String getIdFincaDestino() {
        return idFincaDestino;
    }

    public void setIdFincaDestino(String idFincaDestino) {
        this.idFincaDestino = idFincaDestino;
    }

    public String getIdFinca() {
        return idFinca;
    }

    public void setIdFinca(String idFinca) {
        this.idFinca = idFinca;
    }

    public String getDescFinca() {
        return descFinca;
    }

    public void setDescFinca(String descFinca) {
        this.descFinca = descFinca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdTipoAnimal() {
        return idTipoAnimal;
    }

    public void setIdTipoAnimal(String idTipoAnimal) {
        this.idTipoAnimal = idTipoAnimal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdMacrogrupo() {
        return idMacrogrupo;
    }

    public void setIdMacrogrupo(String idMacrogrupo) {
        this.idMacrogrupo = idMacrogrupo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescTipoAnimal() {
        return descTipoAnimal;
    }

    public void setDescTipoAnimal(String descTipoAnimal) {
        this.descTipoAnimal = descTipoAnimal;
    }

    public String getDescMacrogrupo() {
        return descMacrogrupo;
    }

    public void setDescMacrogrupo(String descMacrogrupo) {
        this.descMacrogrupo = descMacrogrupo;
    }

}
