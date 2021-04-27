/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author DOLFHANDLER
 */
public class ModeloAnimales {

    private String calificacion;
    private String capado;
    private String fecha;
    private String fechaDestete;
    private String fechaMuerte;
    private String fechaNacimiento;
    private String fechaVenta;
    private String muerte;
    private String descripcionMuerte;
    private String genero;
    private String id;
    private String idTipoAnimal;
    private String idUsuario;
    private String notas;
    private String numero;
    private String numeroMama;
    private String numeroMamaAdoptiva;
    private String descTipoAnimal;
    private String peso;
    private String grupo;
    private String descGrupo;
    private String hierro;
    private String descHierro;
    private String idFinca;
    private String descFinca;
    private String numeroDescendiente;
    private String estadoDescendiente;
    private Object modelo;
    private String idPropietario;
    private String descPropietario;
    private String venta;
    private String precioVenta;
    private String tipoVenta;
    private String pesoCanal;
    private String pesoDestete;
    private String hierroFisico;
    private String implante;
    private String descornada;
    private String fechaNovilla;
    private String destete;
    private String numero_partos;

    public ModeloAnimales() {
    }

    public ModeloAnimales(String calificacion, String capado, String fecha,
            String fecha_destete, String fecha_muerte, String fecha_nacimiento,
            String fechaVenta, String genero, String id, String id_tipo_animal, String id_usuario,
            String notas, String numero, String numero_mama, String descTipoAnimal,
            String peso, String numero_mama_adoptiva, String grupo, String descGrupo,
            String hierro, String descHierro, String idFinca, String descFinca,
            String numeroDescendiente, String estadoDescendiente, Object modelo,
            String idPropietario, String descPropietario, String muerte, String venta,
            String precioVenta, String tipoVenta, String pesoCanal, String descripcionMuerte,
            String fechaNovilla, String pesoDestete, String hierroFisico, String implante,
            String descornada,String destete, String numero_partos) {
        this.numero_partos = numero_partos;
        this.destete = destete;
        this.descripcionMuerte = descripcionMuerte;
        this.precioVenta = precioVenta;
        this.tipoVenta = tipoVenta;
        this.pesoCanal = pesoCanal;
        this.calificacion = calificacion;
        this.capado = capado;
        this.fecha = fecha;
        this.fechaDestete = fecha_destete;
        this.fechaMuerte = fecha_muerte;
        this.fechaNacimiento = fecha_nacimiento;
        this.fechaVenta = fechaVenta;
        this.genero = genero;
        this.id = id;
        this.idTipoAnimal = id_tipo_animal;
        this.idUsuario = id_usuario;
        this.notas = notas;
        this.numero = numero;
        this.numeroMama = numero_mama;
        this.numeroMamaAdoptiva = numero_mama_adoptiva;
        this.descTipoAnimal = descTipoAnimal;
        this.peso = peso;
        this.grupo = grupo;
        this.descGrupo = descGrupo;
        this.hierro = hierro;
        this.descHierro = descHierro;
        this.idFinca = idFinca;
        this.descFinca = descFinca;
        this.modelo = modelo;
        this.numeroDescendiente = numeroDescendiente;
        this.estadoDescendiente = estadoDescendiente;
        this.idPropietario = idPropietario;
        this.descPropietario = descPropietario;
        this.muerte = muerte;
        this.venta = venta;
        this.pesoDestete = pesoDestete;
        this.hierroFisico = hierroFisico;
        this.implante = implante;
        this.descornada = descornada;
        this.fechaNovilla = fechaNovilla;
    }

    public String getNumero_partos() {
        return numero_partos;
    }

    public void setNumero_partos(String numero_partos) {
        this.numero_partos = numero_partos;
    }

    public String getDestete() {
        return destete;
    }

    public void setDestete(String destete) {
        this.destete = destete;
    }

    public String getPesoDestete() {
        return pesoDestete;
    }

    public void setPesoDestete(String pesoDestete) {
        this.pesoDestete = pesoDestete;
    }

    public String getHierroFisico() {
        return hierroFisico;
    }

    public void setHierroFisico(String hierroFisico) {
        this.hierroFisico = hierroFisico;
    }

    public String getImplante() {
        return implante;
    }

    public void setImplante(String implante) {
        this.implante = implante;
    }

    public String getDescornada() {
        return descornada;
    }

    public void setDescornada(String descornada) {
        this.descornada = descornada;
    }

    public String getFechaNovilla() {
        return fechaNovilla;
    }

    public void setFechaNovilla(String fechaNovilla) {
        this.fechaNovilla = fechaNovilla;
    }

    public String getDescripcionMuerte() {
        return descripcionMuerte;
    }

    public void setDescripcionMuerte(String descripcionMuerte) {
        this.descripcionMuerte = descripcionMuerte;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getPesoCanal() {
        return pesoCanal;
    }

    public void setPesoCanal(String pesoCanal) {
        this.pesoCanal = pesoCanal;
    }

    public String getMuerte() {
        return muerte;
    }

    public void setMuerte(String muerte) {
        this.muerte = muerte;
    }

    public String getVenta() {
        return venta;
    }

    public void setVenta(String venta) {
        this.venta = venta;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getDescPropietario() {
        return descPropietario;
    }

    public void setDescPropietario(String descPropietario) {
        this.descPropietario = descPropietario;
    }

    public String getNumeroDescendiente() {
        return numeroDescendiente;
    }

    public void setNumeroDescendiente(String numeroDescendiente) {
        this.numeroDescendiente = numeroDescendiente;
    }

    public String getEstadoDescendiente() {
        return estadoDescendiente;
    }

    public void setEstadoDescendiente(String estadoDescendiente) {
        this.estadoDescendiente = estadoDescendiente;
    }

    public Object getModeloTraslado() {
        return modelo;
    }

    public void setModeloTraslado(Object modelo) {
        this.modelo = modelo;
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

    public String getHierro() {
        return hierro;
    }

    public void setHierro(String hierro) {
        this.hierro = hierro;
    }

    public String getDescHierro() {
        return descHierro;
    }

    public void setDescHierro(String descHierro) {
        this.descHierro = descHierro;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public String getNumeroMamaAdoptiva() {
        return numeroMamaAdoptiva;
    }

    public void setNumeroMamaAdoptiva(String numeroMamaAdoptiva) {
        this.numeroMamaAdoptiva = numeroMamaAdoptiva;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fecha_venta) {
        this.fechaVenta = fecha_venta;
    }

    public String getDescTipoAnimal() {
        return descTipoAnimal;
    }

    public void setDescTipoAnimal(String descTipoAnimal) {
        this.descTipoAnimal = descTipoAnimal;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCapado() {
        return capado;
    }

    public void setCapado(String capado) {
        this.capado = capado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaDestete() {
        return fechaDestete;
    }

    public void setFechaDestete(String fechaDestete) {
        this.fechaDestete = fechaDestete;
    }

    public String getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroMama() {
        return numeroMama;
    }

    public void setNumeroMama(String numeroMama) {
        this.numeroMama = numeroMama;
    }

}
