/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.etiquetas;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUEtiquetaR extends JLabel implements Runnable{
    private String hora, minutos, segundos, ampm;
    private final Thread hilo;
    private boolean mostrarAMPM;
    private boolean tipoLetra;
    
    private Limitacion limitacion;
    
    /**
     * Interfaz de Usuario Etiqueta Reloj, clase que hereda del componente JLabel e implementa Runnable, para mostrar la hora en tiempo real.
     * @param limitacion determina la posicion y el tamaño del componente.
     */
    public IUEtiquetaR(Limitacion limitacion){
        this.limitacion = limitacion;                
        this.mostrarAMPM = true;
        this.tipoLetra = true;
        this.hilo = new Thread(this);
        this.hilo.start();
        
        construirEtiquetaReloj();
    }
    private void construirEtiquetaReloj(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setLayout(null);
        setOpaque(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFont(new Font("Verdana", Font.PLAIN, limitacion.getPorcentajeAlto(80)));
        setForeground(new Color(2, 67, 109));
        setFocusable(false);

    }
    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == hilo) {
            actualizarHora();
            if(mostrarAMPM)
                setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            else
                setText(hora + ":" + minutos + ":" + segundos);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("error...! del hilo: "+e.getMessage());
            }
        }
    }
    private void actualizarHora() {
        Calendar calendario = new GregorianCalendar();
        calendario.setTime(new Date());
        
        if(tipoLetra)
            ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        else
            ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "am" : "pm";
        
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }
    public Limitacion getLimitacion(){
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirEtiquetaReloj();
    }
    public boolean isMostrarAMPM() {
        return mostrarAMPM;
    }
    public void setMostrarAMPM(boolean mostrar) {
        this.mostrarAMPM = mostrar;
    }

    /**
     * establece el tipo letra en Mayuscula o Minuscula la variable AM/PM. asumiendo solo dos valores: MAYUSCULA o MINUSCULA.
     * @param letra es el tipo de letra que determina: Mayuscula si y solo si la letra = MAYUSCULA. caso contrario es Minuscula si la letra = MINUSCULA
     */
    public void setTipoLetraAMPM(String letra){
        switch(letra){
            case "MAYUSCULA":
                tipoLetra = true;
            break;
            case "MINUSCULA":
                tipoLetra = false;
            break;
            default:
                tipoLetra = true;
            break;
        }
    }
}
