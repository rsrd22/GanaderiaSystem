package AjustarControles;


import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DOLFHANDLER
 */
public class AjustarControles {

    private ArrayList<ControlDeUsuario> controles;
    private int margen;

    public AjustarControles() {
        controles = new ArrayList<>();
        margen = 0;
    }

    public void agregarControl(ControlDeUsuario control) {
        controles.add(control);
    }

    public void ajustarControlesPorDimension() {
        for (ControlDeUsuario control : controles) {
            if (control.ajustable) {
                if (control.tipoAjuste == tiposDeAjuste.POR_DIMENSION || control.tipoAjuste == tiposDeAjuste.POR_DEFECTO) {
                    for (int ajuste : control.getAjustes()) {
                        switch (ajuste) {
                            case Ajustar.ARRIBA:
                                ajustarArriba(control);
                                break;
                            case Ajustar.IZQUIERDA:
                                ajustarIzquierda(control);
                                break;
                            case Ajustar.ABAJO:
                                ajustarAbajo(control);
                                break;
                            case Ajustar.DERECHA:
                                ajustarDerecha(control);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public void ajustarControlesPorPosicion() {
        for (ControlDeUsuario control : controles) {
            if (control.ajustable && control.tipoAjuste == tiposDeAjuste.POR_POSICION) {
                for (int ajuste : control.getAjustes()) {
                    switch (ajuste) {
                        case Ajustar.ARRIBA:
                            ajustarPosicionArriba(control);
                            break;
                        case Ajustar.IZQUIERDA:
                            ajustarPosicionIzquierda(control);
                            break;
                        case Ajustar.ABAJO:
                            ajustarPosicionAbajo(control);
                            break;
                        case Ajustar.DERECHA:
                            ajustarPosicionDerecha(control);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void ajustarDerecha(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevoAncho = componente.getWidth() + (padre.getWidth() - (componente.getX() + componente.getWidth()));
        componente.setBounds(componente.getX(), componente.getY(), nuevoAncho - margen, componente.getHeight());
    }

    private void ajustarAbajo(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevoAlto = componente.getHeight() + (padre.getHeight() - (componente.getY() + componente.getHeight()));
        componente.setBounds(componente.getX(), componente.getY(), componente.getWidth(), nuevoAlto - margen);
    }

    private void ajustarArriba(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionY = margen;
        componente.setBounds(componente.getX(), nuevaPosicionY, componente.getWidth(), componente.getHeight() - margen * 2);
    }

    private void ajustarIzquierda(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionX = margen;
        componente.setBounds(nuevaPosicionX, componente.getY(), componente.getWidth() - margen * 2, componente.getHeight());
    }

    private void ajustarPosicionDerecha(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionX = padre.getWidth() - control.getProporcionInicialAncho();
        componente.setBounds(nuevaPosicionX, componente.getY(), componente.getWidth(), componente.getHeight());
    }

    private void ajustarPosicionArriba(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionY = padre.getY() - control.getProporcionInicialY();
        componente.setBounds(componente.getX(), nuevaPosicionY, componente.getWidth(), componente.getHeight());
    }

    private void ajustarPosicionIzquierda(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionX = padre.getX() - control.getProporcionInicialX();
        componente.setBounds(nuevaPosicionX, componente.getY(), componente.getWidth(), componente.getHeight());
    }

    private void ajustarPosicionAbajo(ControlDeUsuario control) {
        Component componente = getComponente(control);
        Component padre = componente.getParent();

        int nuevaPosicionY = padre.getHeight() - control.getProporcionInicialAlto();
        componente.setBounds(componente.getX(), nuevaPosicionY, componente.getWidth(), componente.getHeight());
    }

    private Component getComponente(ControlDeUsuario control) {
        Component componente = null;

        if (control.getControlUsuario() instanceof JLabel) {
            componente = ((JLabel) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JPanel) {
            componente = ((JPanel) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JButton) {
            componente = ((JButton) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JTabbedPane) {
            componente = ((JTabbedPane) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JTable) {
            componente = ((JTable) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JSpinner) {
            componente = ((JSpinner) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JSlider) {
            componente = ((JSlider) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JTextArea) {
            componente = ((JTextArea) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JTextField) {
            componente = ((JTextField) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JPasswordField) {
            componente = ((JPasswordField) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JDateChooser) {
            componente = ((JDateChooser) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JComboBox) {
            componente = ((JComboBox) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JCheckBox) {
            componente = ((JCheckBox) control.getControlUsuario());
        } else if (control.getControlUsuario() instanceof JScrollPane) {
            componente = ((JScrollPane) control.getControlUsuario());
        } else {
            componente = null;
        }

        return componente;
    }

}
