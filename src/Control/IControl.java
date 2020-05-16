package Control;

/**
 *
 * @author DOLFHANDLER
 */
public interface IControl {

    public Object ObtenerDatos();
    
    public Object ObtenerDatosKey(String ID);

    public int Guardar(Object o);

    public int Actualizar(Object o);

    public int Eliminar(Object o);
    
    public Object ObtenerDatosFiltro(Object o);
}
