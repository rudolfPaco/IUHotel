/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aplicacionjava.www.calendario;

import com.aplicacionjava.www.botones.IUBotonIT;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUCalendarioPantalla extends IUPanelBD implements MouseListener{

    private IUPanel iuPrimerPanel;
    private IUEtiqueta iuPrimerTituloMes;
    private IUPanel iuPrimerPanelDiasSemana;    
    private IUEtiqueta[] iuPrimerDiasSemana;
    private IUPanel iuPrimerPanelMes;
    private IUBotonIT[] iuBotonesPrimeraFecha;
    
    private IUPanel iuSegundoPanel;
    private IUEtiqueta iuSegundoTituloMes;
    private IUPanel iuSegundoPanelDiasSemana;    
    private IUEtiqueta[] iuSegundoDiasSemana;
    private IUPanel iuSegundoPanelMes;
    private IUBotonIT[] iuBotonesSegundaFecha;
    
    private final String[] diasSemana = {"LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB", "DOM"};
    private final int columna = diasSemana.length;
    private final int fila = 6;
    private final Fecha fecha;
    private boolean estado;
    
    
    public IUCalendarioPantalla(String titulo, Limitacion limitacion) {
        super(limitacion);
        this.fecha = new Fecha();        
        this.estado = false;        
        
        //construirPaneles();            
    }
    /*private void construirPaneles(){
        int espacio = 2;
        int ancho = panelFondo.getAncho() - 3*espacio;
        int alto = panelFondo.getAlto() - 2*espacio;
        
        iuPrimerPanel = new IUPanel(new Limite(espacio, espacio, ancho/2, alto));
        iuPrimerPanel.setBorder(null);
        panelFondo.add(iuPrimerPanel);
        construirPrimerPanel();
        
        iuSegundoPanel = new IUPanel(new Limite(2*espacio + iuPrimerPanel.getAncho(), espacio, ancho/2, alto));
        iuSegundoPanel.setBorder(null);
        panelFondo.add(iuSegundoPanel);
        construirSegundoPanel();
    }    
    private void construirPrimerPanel(){
        int espacio = 2;
        int ancho = iuPrimerPanel.getAncho() - 2*espacio;
        int alto = iuPrimerPanel.getAlto() - 2*espacio;        
        
        iuPrimerTituloMes = new IUEtiqueta(fecha.getMes_literal(), new Limite(espacio, espacio, ancho, alto/12), new Font("Arial", Font.PLAIN, (alto/12) - (alto/12)/10), Color.BLACK, "", false);
        iuPrimerTituloMes.setHorizontalAlignment(SwingConstants.CENTER);        
        iuPrimerPanel.add(iuPrimerTituloMes);
        
        iuPrimerPanelDiasSemana = new IUPanel(new Limite(espacio, iuPrimerTituloMes.getLimite().getAlto() + iuPrimerTituloMes.getLimite().getY(), ancho, alto/14));
        iuPrimerPanelDiasSemana.setBorder(null);
        iuPrimerPanel.add(iuPrimerPanelDiasSemana);
        construirPrimerPanelDiasSemana(ancho/diasSemana.length, alto/14);
        
        iuPrimerPanelMes = new IUPanel(new Limite(espacio, iuPrimerPanelDiasSemana.getAlto() + iuPrimerPanelDiasSemana.getY(), ancho, alto - alto/12 - alto/14));
        iuPrimerPanelMes.setBorder(null);
        iuPrimerPanel.add(iuPrimerPanelMes);
        construirPrimerMes(ancho/columna, (alto - alto/12 - alto/14)/fila - 1);
    }
    private void construirPrimerPanelDiasSemana(int ancho, int alto){
        iuPrimerDiasSemana = new IUEtiqueta[diasSemana.length];
        for (int i = 0; i < diasSemana.length; i++) {
            iuPrimerDiasSemana[i] = new IUEtiqueta(diasSemana[i], new Limite(i*ancho + 2, 0, ancho, alto), new Font("Arial", Font.PLAIN, alto - alto/3), Color.BLACK, "", true);
            iuPrimerDiasSemana[i].setHorizontalAlignment(SwingConstants.CENTER);
            iuPrimerPanelDiasSemana.add(iuPrimerDiasSemana[i]);
        }
    }
    private void construirPrimerMes(int ancho, int alto){
        iuBotonesPrimeraFecha = new IUBotonFecha[columna*fila];
        int contador = 0;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                iuBotonesPrimeraFecha[contador] = new IUBotonFecha("", new Limite(j*ancho + 2, i*alto + 2, ancho, alto), diasSemana[j]);
                iuBotonesPrimeraFecha[contador].addMouseListener(this);
                iuBotonesPrimeraFecha[contador].setHorizontalAlignment(SwingConstants.CENTER);
                iuPrimerPanelMes.add(iuBotonesPrimeraFecha[contador]);
                contador++;
            }
        }
        establecerFechas(iuBotonesPrimeraFecha, new Fecha().getFechaPrimera_mes(new Fecha().getMes(), new Fecha().getAno()), new Fecha().getFechaPrimera_mes(new Fecha().getMes(), new Fecha().getAno()));
    }
    private void construirSegundoPanel(){
        
        int espacio = 2;
        int ancho = iuSegundoPanel.getAncho() - 2*espacio;
        int alto = iuSegundoPanel.getAlto() - 2*espacio;
        
        iuSegundoTituloMes = new IUEtiqueta(fecha.getFechaPrimera_proximoMes(new Fecha().getMes(), new Fecha().getAno()).getMes_literal(), new Limite(espacio, espacio, ancho, alto/12), new Font("Arial", Font.PLAIN, (alto/12) - (alto/12)/10), Color.BLACK, "", false);        
        iuSegundoTituloMes.setHorizontalAlignment(SwingConstants.CENTER);
        iuSegundoPanel.add(iuSegundoTituloMes);
        
        iuSegundoPanelDiasSemana = new IUPanel(new Limite(espacio, iuSegundoTituloMes.getLimite().getAlto() + iuSegundoTituloMes.getLimite().getY(), ancho, alto/14));
        iuSegundoPanelDiasSemana.setBorder(null);
        iuSegundoPanel.add(iuSegundoPanelDiasSemana);
        construirSegundoPanelDiasSemana(ancho/diasSemana.length, alto/14);
        
        iuSegundoPanelMes = new IUPanel(new Limite(espacio, iuSegundoPanelDiasSemana.getAlto() + iuSegundoPanelDiasSemana.getY(), ancho, alto - alto/12 - alto/14));
        iuSegundoPanelMes.setBorder(null);
        iuSegundoPanel.add(iuSegundoPanelMes);
        construirSegundoMes(ancho/columna, (alto - alto/12 - alto/14)/fila - 1);
    }
    private void construirSegundoPanelDiasSemana(int ancho, int alto){
        iuSegundoDiasSemana = new IUEtiqueta[diasSemana.length];
        for (int i = 0; i < diasSemana.length; i++) {
            iuSegundoDiasSemana[i] = new IUEtiqueta(diasSemana[i], new Limite(i*ancho + 2, 0, ancho, alto), new Font("Arial", Font.PLAIN, alto - alto/3), Color.BLACK, "", true);
            iuSegundoDiasSemana[i].setHorizontalAlignment(SwingConstants.CENTER);
            //iuPrimerDiasSemana[i].agregarBorde(new Contorno(Color.orange, Color.red, 1, 1, "VERTICAL"));            
            iuSegundoPanelDiasSemana.add(iuSegundoDiasSemana[i]);
        }
    }
    private void construirSegundoMes(int ancho, int alto){
        iuBotonesSegundaFecha = new IUBotonFecha[columna*fila];
        int contador = 0;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                iuBotonesSegundaFecha[contador] = new IUBotonFecha("", new Limite(j*ancho + 2, i*alto + 2, ancho, alto), diasSemana[j]);
                iuBotonesSegundaFecha[contador].addMouseListener(this);
                iuBotonesSegundaFecha[contador].setHorizontalAlignment(SwingConstants.CENTER);
                iuSegundoPanelMes.add(iuBotonesSegundaFecha[contador]);
                contador++;
            }
        }        
        establecerFechas(iuBotonesSegundaFecha, fecha.getFechaPrimera_proximoMes(new Fecha().getMes(), new Fecha().getAno()), fecha.getFechaPrimera_proximoMes(new Fecha().getMes(), new Fecha().getAno()));
    }*/
    

    public boolean getEstado(){
        return estado;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if(presionoMesHoy(e)){
            estado = true;
        }            
        else
            if(presionoMesProximo(e)){
                estado = true;
            }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    private boolean presionoMesHoy(MouseEvent e){
        int contador = 0;
        boolean verificador = false;
        while(contador < iuBotonesPrimeraFecha.length && !verificador){
            if(e.getSource() == iuBotonesPrimeraFecha[contador]){
                verificador = true;
                //elemento = new Elemento(String.valueOf(iuBotonesPrimeraFecha[contador].fecha.getDia()), iuBotonesPrimeraFecha[contador].fecha);
            }
            contador++;
        }
        return verificador;
    }
    private boolean presionoMesProximo(MouseEvent e){
        int contador = 0;
        boolean verificador = false;
        while(contador < iuBotonesSegundaFecha.length && !verificador){
            if(e.getSource() == iuBotonesSegundaFecha[contador]){
                verificador = true;
                //elemento = new Elemento(String.valueOf(iuBotonesSegundaFecha[contador].fecha.getDia()), iuBotonesSegundaFecha[contador].fecha);
            }
            contador++;
        }
        return verificador;
    }
}
