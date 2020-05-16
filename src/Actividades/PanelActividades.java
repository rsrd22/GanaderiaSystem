/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Actividades;

import Actividades.Eventos.*;
import Control.ControlGeneral;
import static Utilidades.Consultas.consultas;
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
import java.util.List;
import java.util.Map;
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
    public BotonesDeProgreso[] botones;
    private ControlGeneral controlGral;
    private List<Map<String, String>> listaActividades;
    private List<Map<String, String>> listaEstados;
    public int xinicial = 300, yinicial = 20;
    public int mesesamostrar = 4;
    private ArrayList<Opciones> opciones;
    public MenuContextual menu;
    public static boolean clickDerechoPresionado;
    public static boolean arrastreDelMouse;

    public PanelActividades(VistaPrincipal vp) {
        this.vp = vp;
        clickDerechoPresionado = false;
        arrastreDelMouse = false;
        setPreferredSize(new Dimension(vp.getSize()));
        this.setSize(vp.getSize());
        font = new Font("Tahoma", Font.BOLD, 12);
        controlGral = new ControlGeneral();
        this.cursor = Cursor.DEFAULT_CURSOR;
        menu=null;
        opciones = new ArrayList<>();
        periodos = new ArrayList<>();
        actividades = new ArrayList<>();
        actividadesPeriodos = new ArrayList<>();
        botones = new BotonesDeProgreso[2];
        cargarPeriodos(xinicial, yinicial, 2019, 11, mesesamostrar);
        cargarBotones();
        cargarActividades();
        cargarEstados();
        cargarActividadesPorPeriodo();

        this.addMouseMotionListener(new EventoMouseMotion(this));
        this.addMouseListener(new EventoMouseClick(this));
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
//        periodos = new ArrayList<>();

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
        for (int i = 0; i < actividadesPeriodos.size(); i++) {
            actividadesPeriodos.get(i).pintar(g);
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
}
