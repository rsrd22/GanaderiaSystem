/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import Actividades.Eventos.*;
import Control.ControlCronograma;
import Control.ControlGeneral;
import Control.Retorno;
import Modelo.ModeloCronograma;
import static Utilidades.Consultas.consultas;
import Utilidades.datosUsuario;
import Vistas.VistaPrincipal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author DOLFHANDLER
 */
public class PanelActividades extends JPanel {

    public int cursor;
    public Font font;
    public VistaPrincipal vp;
    public ArrayList<Periodo> periodos;
    public ArrayList<Actividad> actividades;
    public ArrayList<ActividadesPorPeriodo> actividadesPeriodos;
    private ArrayList<ActividadesPorPeriodo> actividadesSeleccionadas;
    private ArrayList<ActividadesPorPeriodo> actividadesParaGuardar;
    private ArrayList<ModeloCronograma> listaDatos;
    private ControlCronograma controlCronograma;
    public BotonesDeProgreso[] botones;
    public BotonMasOpciones btnMasOpciones;
    private ControlGeneral controlGral;
    private List<Map<String, String>> listaActividades;
    private List<Map<String, String>> listaEstados;
    public int xinicial = 300, yinicial = 20;
    public int mesesamostrar = 4;
    private ArrayList<Opciones> opciones;
    public MenuContextual menu;
    public static boolean clickDerechoPresionado;
    public static boolean arrastreDelMouse;
    public Calendar cal = Calendar.getInstance();

    public PanelActividades(VistaPrincipal vp) {
        this.vp = vp;
        listaDatos = new ArrayList<>();
        controlCronograma = new ControlCronograma();
        clickDerechoPresionado = false;
        arrastreDelMouse = false;
        setPreferredSize(new Dimension(vp.getSize()));
        this.setSize(vp.getSize());
        font = new Font("Tahoma", Font.BOLD, 12);
        controlGral = new ControlGeneral();
        this.cursor = Cursor.DEFAULT_CURSOR;
        menu = null;
        opciones = new ArrayList<>();
        periodos = new ArrayList<>();
        actividades = new ArrayList<>();
        actividadesPeriodos = new ArrayList<>();
        actividadesSeleccionadas = new ArrayList<>();
        actividadesParaGuardar = new ArrayList<>();
        botones = new BotonesDeProgreso[2];
        int an = 50, margen = 50;
        btnMasOpciones = new BotonMasOpciones(
                0,
                vp.getSize().width - vp.desplazamiento - margen - an,
                vp.getSize().height - 52 - margen - an,
                an,
                "Guardar",
                Colores.SUCCESS,
                Colores.TEXT_SUCCESS
        );

        SimpleDateFormat sdfa = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfm = new SimpleDateFormat("MM");
        cal.add(Calendar.MONTH, -1);
        int anio = Integer.parseInt(sdfa.format(cal.getTime()));
        int mes = Integer.parseInt(sdfm.format(cal.getTime()));
        cargarPeriodos(xinicial, yinicial, anio, mes, mesesamostrar);
        cargarBotones();
        cargarActividades();
        cargarEstados();
        cargarActividadesPorPeriodo();
        establecerActividadesSeleccionadas();

        this.addMouseMotionListener(new EventoMouseMotion(this));
        this.addMouseListener(new EventoMouseClick(this));
        vp.addComponentListener(new EventoVentana(this));
    }

    public void establecerActividadesSeleccionadas() {
        establecerListaDeActividadesSeleccionadas(controlCronograma.ObtenerDatosKey(vp.idFinca));
    }

    private void establecerListaDeActividadesSeleccionadas(Object listado) {
        ArrayList<ModeloCronograma> modeloActividades = (ArrayList<ModeloCronograma>) listado;

        if (modeloActividades.size() > 0) {
            for (ModeloCronograma modelo : modeloActividades) {
                ActividadesPorPeriodo appdb = new ActividadesPorPeriodo();
                Periodo per = new Periodo();
                Semana sem = new Semana();
                Actividad act = new Actividad(modelo.getIdActividad(), "", 0, 0, 0, 0, Color.orange, Color.red);
                per.setAnio(Integer.parseInt(modelo.getAnio()));
                per.setMes(Integer.parseInt(modelo.getMes()));
                sem.setId(Integer.parseInt(modelo.getSemana()));
                appdb.setPeriodo(per);
                appdb.setSemana(sem);
                appdb.setActividad(act);
                for (ActividadesPorPeriodo app : actividadesPeriodos) {
                    if (app.Igual(appdb)) {
                        app.setId(modelo.getId());
                        app.setEstado(modelo.getIdEstado());
                        int color = Integer.parseInt(modelo.getColor().isEmpty() ? "0" : modelo.getColor());
                        app.setColorFondo(new Color(color));
                        agregarActividadesSeleccionadasDB(app);
                        break;
                    }
                }
            }
        } else {
        }
    }

    public void limpiarPeriodos() {
        periodos = new ArrayList<>();
    }

    public synchronized void Actualizar() {
        setPreferredSize(new Dimension(vp.getSize()));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        setCursor(new Cursor(cursor));
        pintarFondoPanel(g2d);
        g2d.setFont(font);
        pintarPeriodos(g2d);
        pintarBotonesDeProgreso(g2d);
        pintarActividades(g2d);
        pintarActividadesPorPeriodo(g2d);
        pintarBotonMas(g2d);

        if (clickDerechoPresionado) {
            pintarEstados(g2d);
        } else {
            menu = new MenuContextual(opciones, new Point(-500, -500));
        }

        g2d.dispose();
    }

    private void pintarFondoPanel(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void cargarPeriodos(int xInicial, int yInicial, int anioInicio, int mesInicio, int mesesAMostrar) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfDescripcionMes = new SimpleDateFormat("MMMM");
        SimpleDateFormat sdfMes = new SimpleDateFormat("M");

        int ancho = 160, alto = 20;
        int band = 0;
        boolean inicia = false;

        cal.set(anioInicio, mesInicio - 1, 1);
        for (int i = 0; i < mesesAMostrar; i++) {
            String nombreMes = sdfDescripcionMes.format(cal.getTime());
            String anio = sdfAnio.format(cal.getTime());
            String mes = sdfMes.format(cal.getTime());

            if (band == 0) {
                band = 1;
                inicia = true;
            } else {
                inicia = false;
            }

            periodos.add(new Periodo(
                    i,
                    Integer.parseInt(anio),
                    Integer.parseInt(mes),
                    nombreMes,
                    inicia,
                    xInicial + i * ancho,
                    yInicial,
                    ancho,
                    alto,
                    Colores.SUCCESS,
                    Colores.TEXT_SUCCESS
            ));
            cal.add(Calendar.MONTH, 1);
        }
    }

    private void pintarPeriodos(Graphics2D g) {
        for (int i = 0; i < periodos.size(); i++) {
            periodos.get(i).pintar(g);
        }
    }

    private void cargarBotones() {
        botones[0] = new BotonesDeProgreso(Boton.ANTERIOR, "<", xinicial - 60, yinicial - 20, 60, 60, Colores.SUCCESS, Colores.TEXT_SUCCESS);
        botones[1] = new BotonesDeProgreso(Boton.SIGUIENTE, ">", xinicial + (160 * mesesamostrar), yinicial - 20, 60, 60, Colores.SUCCESS, Colores.TEXT_SUCCESS);
    }

    private void pintarBotonesDeProgreso(Graphics2D g) {
        for (BotonesDeProgreso boton : botones) {
            boton.pintar(g);
        }
    }

    public void cargarActividades() {
        String consulta = consultas.get("BUSQUEDA_ACTIVIDADES_SIN_ESPECIFICACIONES");
        listaActividades = controlGral.GetConsulta(consulta);

        if (listaActividades.size() > 0) {
            int i = 0, alto = 30, yini = yinicial + 40, ancho = 290;
            for (Map<String, String> actividad : listaActividades) {
                actividades.add(new Actividad(
                        actividad.get("id"),
                        actividad.get("descripcion"),
                        10, yini + i * alto, ancho, alto, Colores.SUCCESS, Colores.TEXT_SUCCESS));
                i++;
            }
        }
    }

    private void pintarActividades(Graphics2D g) {
        for (Actividad actividad : actividades) {
            actividad.pintar(g);
        }
    }

    public void cargarActividadesPorPeriodo() {
        actividadesPeriodos = new ArrayList<>();
        for (int i = 0; i < actividades.size(); i++) {
            Actividad actividad = actividades.get(i);
            for (int j = 0; j < periodos.size(); j++) {
                Periodo periodo = periodos.get(j);
                for (int k = 0; k < periodo.getSemanas().length; k++) {
                    Semana semana = periodo.getSemanas()[k];

                    actividadesPeriodos.add(
                            new ActividadesPorPeriodo(
                                    actividad,
                                    periodo,
                                    semana,
                                    "-1",
                                    false,
                                    semana.getX(),
                                    actividad.getY(),
                                    semana.getAncho(),
                                    actividad.getAlto(),
                                    Colores.FADED,
                                    Colores.TEXT_FADED
                            )
                    );
                }
            }
        }
    }

    private void pintarActividadesPorPeriodo(Graphics2D g) {
        for (ActividadesPorPeriodo actividad : actividadesPeriodos) {
            actividad.pintar(g);
        }
        for (ActividadesPorPeriodo actividad : actividadesPeriodos) {
            for (ActividadesPorPeriodo app : actividadesSeleccionadas) {
                if (actividad.Igual(app)) {
                    actividad.setColorFondo(app.getColorFondo());
                    actividad.setColorTexto(app.getColorTexto());
                    actividad.setSeleccionado(true);

                    actividad.pintar(g);
                    System.out.println("------------------------------------------------------------");
                    System.out.println("actividad: " + actividad.getActividad().getId());
                    System.out.println("año      : " + actividad.getPeriodo().getAnio());
                    System.out.println("mes      : " + actividad.getPeriodo().getMes());
                    System.out.println("semana   : " + actividad.getSemana().getDescripcion());
                    System.out.println("------------------------------------------------------------");
                    break;
                }
            }
        }
    }

    private void cargarEstados() {
        String consulta = consultas.get("BUSQUEDA_ESTADO_ACTIVIDADES_SIN_ESPECIFICACIONES");
        listaEstados = controlGral.GetConsulta(consulta);

        if (listaEstados.size() > 0) {
            int alto = 30;
            for (Map<String, String> estados : listaEstados) {
                int ncolor = Integer.parseInt(estados.get("color"));
                Color color = new Color(ncolor);
                opciones.add(new Opciones(
                        estados.get("descripcion"),
                        estados.get("id"),
                        0, 0, 150, alto, Colores.FADED, Colores.DARK_SUCCESS, color)
                );
            }
            opciones.add(new Opciones(
                    "DESMARCAR",
                    "99",
                    0, 0, 150, alto, Colores.FADED, Colores.DARK_SUCCESS, Colores.FADED)
            );
            menu = new MenuContextual(opciones, new Point(-500, -500));
        }
    }

    private void pintarEstados(Graphics2D g) {
        menu.pintar(g);
    }

    public void pintarBotonMas(Graphics2D g2d) {
        btnMasOpciones.pintar(g2d);
    }

    public void agregarActividadesSeleccionadasDB(ActividadesPorPeriodo actividadPorPeriodo) {
        for (int i = 0; i < actividadesSeleccionadas.size(); i++) {
            if (actividadesSeleccionadas.get(i).Igual(actividadPorPeriodo)) {
                actividadesSeleccionadas.get(i).setActividad(actividadPorPeriodo.getActividad());
                actividadesSeleccionadas.get(i).setPeriodo(actividadPorPeriodo.getPeriodo());
                actividadesSeleccionadas.get(i).setEstado(actividadPorPeriodo.getEstado());
                return;
            }
        }

        actividadesSeleccionadas.add(actividadPorPeriodo);
    }
    
    public void agregarActividadesSeleccionadas(ActividadesPorPeriodo actividadPorPeriodo) {
        for (int i = 0; i < actividadesSeleccionadas.size(); i++) {
            if (actividadesSeleccionadas.get(i).Igual(actividadPorPeriodo)) {
                actividadesSeleccionadas.get(i).setActividad(actividadPorPeriodo.getActividad());
                actividadesSeleccionadas.get(i).setPeriodo(actividadPorPeriodo.getPeriodo());
                actividadesSeleccionadas.get(i).setEstado(actividadPorPeriodo.getEstado());

                actividadesParaGuardar.add(actividadesSeleccionadas.get(i));
                return;
            }
        }

        actividadesSeleccionadas.add(actividadPorPeriodo);
    }

    public void removerActividadesSeleccionadas(ActividadesPorPeriodo actividadPorPeriodo) {
        for (int i = 0; i < actividadesSeleccionadas.size(); i++) {
            if (actividadesSeleccionadas.get(i).Igual(actividadPorPeriodo)) {
                actividadesSeleccionadas.get(i).setRemovida(true);
                
                actividadesParaGuardar.add(actividadesSeleccionadas.get(i));
                
                actividadesSeleccionadas.remove(i);
                break;
            }
        }
    }

    public void guardarCronograma() {
        cargarDatosAlModelo();
        Guardar();
    }

    private void cargarDatosAlModelo() {
        listaDatos = new ArrayList<>();
        ModeloCronograma modelo;
        HashSet hs = new HashSet();
        
        hs.addAll(actividadesParaGuardar);
        actividadesParaGuardar.clear();
        actividadesParaGuardar.addAll(hs);
        
        if (actividadesParaGuardar.size() > 0) {
            for (ActividadesPorPeriodo app : actividadesParaGuardar) {
                modelo = new ModeloCronograma();
                modelo.setAnio("" + app.getPeriodo().getAnio());
                modelo.setFecha("NOW()");
                modelo.setId(app.getId());
                modelo.setIdActividad(app.getActividad().getId());
                modelo.setIdEstado(app.getEstado());
                modelo.setIdFinca(vp.idFinca);
                modelo.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
                modelo.setMes("" + app.getPeriodo().getMes());
                modelo.setSemana("" + app.getSemana().getId());
                modelo.setRemovida(app.isRemovida());

                listaDatos.add(modelo);
            }
        }
//        listaDatos = new ArrayList<>();
//        ModeloCronograma modelo;
//        if (actividadesSeleccionadas.size() > 0) {
//            for (ActividadesPorPeriodo app : actividadesSeleccionadas) {
//                modelo = new ModeloCronograma();
//                modelo.setAnio("" + app.getPeriodo().getAnio());
//                modelo.setFecha("NOW()");
//                modelo.setId(app.getId());
//                modelo.setIdActividad(app.getActividad().getId());
//                modelo.setIdEstado(app.getEstado());
//                modelo.setIdFinca(vp.idFinca);
//                modelo.setIdUsuario(datosUsuario.datos.get(0).get("ID_USUARIO"));
//                modelo.setMes("" + app.getPeriodo().getMes());
//                modelo.setSemana("" + app.getSemana().getId());
//
//                listaDatos.add(modelo);
//            }
//        }
    }

    private void Guardar() {
        int retorno = Retorno.DEFECTO;

        retorno = controlCronograma.Guardar(listaDatos);

        String mensaje = "";
        switch (retorno) {
            case Retorno.EXITO:
                mensaje = "Registro guardado satisfactoriamente.";
                break;
            case Retorno.ERROR:
                mensaje = "los registro no pudeden ser guardados.";
                break;
            case Retorno.EXCEPCION_SQL:
                mensaje = "Ocurrio un error en la base de datos\nOperación no realizada.";
                break;
            case Retorno.CLASE_NO_ENCONTRADA:
                mensaje = "Ocurrio un error con el conector de la base de datos\nOperación no realizada.";
                break;
        }

        JOptionPane.showMessageDialog(this, mensaje);
    }

}
