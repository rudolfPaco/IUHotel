/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aplicacionjava.www.calendario;

import com.aplicacionjava.www.botones.IUBoton;
import com.aplicacionjava.www.campos.IUComboBox;
import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.paneles.IUPanel;
import com.aplicacionjava.www.recursos.Fecha;
import com.aplicacionjava.www.recursos.Limitacion;
import com.aplicacionjava.www.ventanas.IUVentanaT;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author rudolf
 */
public class IUCalendario extends IUVentanaT{
    
    private IUPanel iuPrimerPanel;
    private IUPanel panelTitulo;
    private IUBoton iuIzquierda;
    private IUEtiqueta iuTituloMes;
    private IUBoton iuDerecha;
    private IUComboBox iuAnos;
    private IUPanel panelSemana;    
    private IUEtiqueta[] iuPrimerDiasSemana;
    private IUPanel panelMes;
    private IUBoton[] iuBotonesFecha;
    
    private String[] listaAnos;
    private final String[] diasSemana = {"LUN", "MAR", "MIÉ", "JUE", "VIE", "SÁB", "DOM"};
    private final int columna = diasSemana.length;
    private final int fila = 6;
    private Fecha fecha;
    private boolean estado;
    private Fecha fechaSeleccionada;
    
    
    public IUCalendario(JFrame ventanaPrincipal, String titulo, Fecha fecha, Limitacion limitacion){
        super(ventanaPrincipal, titulo, limitacion, 10);
        this.fecha = fecha;
        this.estado = false;
        this.fechaSeleccionada = null;
        
        cargarAnosPredefinidos();
        construirPaneles(panelFondo.getLimitacion());
        
        cerrarVentana();
        moverVentana();
    }
    private void cargarAnosPredefinidos(){
        int inicio = 1910;
        int fin = new Fecha().getAno() + 10;
        int cantidad = fin - inicio + 1;
        listaAnos = new String[cantidad];
        for (int i = 0; i < listaAnos.length; i++) {
            listaAnos[i] = String.valueOf(fin);
            fin--;
        }        
    }
    private void construirPaneles(Limitacion limite){
        iuPrimerPanel = new IUPanel(new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(1), limite.getPorcentajeAncho(98), limite.getPorcentajeAlto(98)));
        iuPrimerPanel.setBorder(null);
        panelFondo.add(iuPrimerPanel);
        construirPrimerPanel(iuPrimerPanel.getLimitacion());
    }
    private void construirPrimerPanel(Limitacion limite){        
        panelTitulo = new IUPanel(new Limitacion(limite.getAncho(), limite.getPorcentajeAlto(10)));
        iuPrimerPanel.add(panelTitulo);
        construirPanelMesAnos(panelTitulo.getLimitacion());
        
        panelSemana = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(11), limite.getAncho(), limite.getPorcentajeAlto(7)));
        panelSemana.setBorder(new LineBorder(Color.LIGHT_GRAY));
        iuPrimerPanel.add(panelSemana);
        construirPanelDiasSemana(panelSemana.getLimitacion());
        
        panelMes = new IUPanel(new Limitacion(0, limite.getPorcentajeAlto(18), limite.getAncho(), limite.getPorcentajeAlto(82)));
        iuPrimerPanel.add(panelMes);
        construirPanelMes(panelMes.getLimitacion());
    }
    private void construirPanelMesAnos(Limitacion limite){        
        iuIzquierda = new IUBoton("◄", new Limitacion(limite.getPorcentajeAncho(1), limite.getPorcentajeAlto(5), limite.getPorcentajeAlto(90), limite.getPorcentajeAlto(90)));
        iuIzquierda.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(50)));
        iuIzquierda.iuTexto.setForeground(new Color(2, 67, 109));
        iuIzquierda.setArco(limite.getPorcentajeAlto(20));
        iuIzquierda.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fecha = fecha.getMesAnterior();
                actualizarFechaCalendario();
            }
        });
        panelTitulo.add(iuIzquierda);
        
        iuTituloMes = new IUEtiqueta(fecha.getFechaMes(), new Limitacion(limite.getPorcentajeAncho(8), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(55), limite.getPorcentajeAlto(90)));
        iuTituloMes.setHorizontalAlignment(SwingConstants.CENTER); 
        iuTituloMes.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(70)));
        panelTitulo.add(iuTituloMes);
        
        iuDerecha = new IUBoton("►", new Limitacion(limite.getPorcentajeAncho(63), limite.getPorcentajeAlto(5), limite.getPorcentajeAlto(90), limite.getPorcentajeAlto(90)));
        iuDerecha.iuTexto.setFont(new Font("Arial", Font.PLAIN, limite.getPorcentajeAlto(50)));
        iuDerecha.iuTexto.setForeground(new Color(2, 67, 109));
        iuDerecha.setArco(limite.getPorcentajeAlto(20));
        iuDerecha.addEventoRaton(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fecha = fecha.getMesProximo();
                actualizarFechaCalendario();
            }
        });
        panelTitulo.add(iuDerecha);
        
        iuAnos = new IUComboBox(listaAnos, new Limitacion(limite.getPorcentajeAncho(75), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(24), limite.getPorcentajeAlto(90)));
        iuAnos.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(60)));
        iuAnos.setFocusable(false);
        iuAnos.setEstadoTexto(false);
        iuAnos.setSelectedItem(String.valueOf(fecha.getAno()));        
        iuAnos.addItemListener((ItemEvent e) -> {
            String nombre = (String) e.getItem();
            iuAnos.setSelectedItem(nombre);
            fecha.setAno(Integer.valueOf(nombre));
            actualizarFechaCalendario();
        });
        panelTitulo.add(iuAnos);
    }
    private void construirPanelDiasSemana(Limitacion limite){        
        iuPrimerDiasSemana = new IUEtiqueta[diasSemana.length];
        for (int i = 0; i < diasSemana.length; i++) {
            iuPrimerDiasSemana[i] = new IUEtiqueta(diasSemana[i], new Limitacion(limite.getPorcentajeAncho(1) + i*limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(20), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(60)));
            iuPrimerDiasSemana[i].setFont(new Font("Verdana", Font.BOLD, limite.getPorcentajeAlto(60)));            
            iuPrimerDiasSemana[i].setHorizontalAlignment(SwingConstants.CENTER);
            panelSemana.add(iuPrimerDiasSemana[i]);
        }
        iuPrimerDiasSemana[6].setForeground(new Color(180, 0, 0));
    }
    private void construirPanelMes(Limitacion limite){
        iuBotonesFecha = new IUBoton[columna*fila];
        int contador = 0;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                iuBotonesFecha[contador] = new IUBoton("", new Limitacion( limite.getPorcentajeAncho(1) + j*limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(4) + i*limite.getPorcentajeAlto(15), limite.getPorcentajeAncho(14), limite.getPorcentajeAlto(15)));
                iuBotonesFecha[contador].iuTexto.setFont(new Font("Verdana", Font.PLAIN, limite.getPorcentajeAlto(6)));                
                panelMes.add(iuBotonesFecha[contador]);
                iuBotonesFecha[contador].iuTexto.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(presionoMesHoy(e)){
                            estado = true;
                            dispose();
                        }
                    }
                });
                contador++;
            }
        }        
        establecerDiasMes(iuBotonesFecha, fecha, fecha);
    }
    private void establecerDiasMes(IUBoton[] iuBotones, Fecha fechaVariable, Fecha fechaEstatica){
        fechaVariable = fechaVariable.getMesFecha(fechaVariable.getMes(), fechaVariable.getAno());
        fechaEstatica = fechaEstatica.getMesFecha(fechaEstatica.getMes(), fechaEstatica.getAno());
        
        int contador = 0;
        int puntero = 0;
        //los bucles for, se encargan de explorar cada boton del arreglo botones.
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                //la fechaVariable busca compararse el dia de la semana con el arreglo diasSemana[]
                if(diasSemana[j].equalsIgnoreCase(fechaVariable.getFechaDiaCorto())){
                    //la fechaVariable consiguio igualar los dias de la semana
                    //ahora verifica si el mes es igual al mes le pasaron al inicio
                    if(fechaVariable.getMes() == fechaEstatica.getMes()){
                        //esto significa que estan en el mismo mes por el momento.
                        iuBotones[contador].iuTexto.setForeground(new Color(2, 67, 109));
                        iuBotones[contador].setTexto(String.valueOf(fechaVariable.getDia()));
                        iuBotones[contador].iuTexto.setToolTipText(fechaVariable.fecha());
                        
                        if(fechaVariable.getFechaDiaCorto().equalsIgnoreCase("DOM"))
                            iuBotones[contador].iuTexto.setForeground(new Color(180, 0, 0));
                        if(fechaVariable.esIgual(new Fecha())){
                            iuBotones[contador].iuTexto.setForeground(Color.GREEN);
                            //la fecha es igual al dia de hoy
                        }                            
                    }                        
                    else{
                        //esto significa que la fechaVariable ya cambio al siguiente mes y pinta los dias del color LIGHT_GRAY
                        iuBotones[contador].setTexto(String.valueOf(fechaVariable.getDia()));
                        iuBotones[contador].iuTexto.setToolTipText(fechaVariable.fecha());
                        iuBotones[contador].iuTexto.setForeground(Color.LIGHT_GRAY); 
                    }                        
                    //cambia la fechaVariable al siguiente dia.
                    fechaVariable = fechaVariable.getProximoDia();
                    
                }else//la variable puntero incrementa, para saber cuantos dias pertenecen al mes anterior
                    puntero++;
                //la variable contador, incrementa su valor en 1 para explorar cada boton del arreglo botones[]
                contador++;
            }
        }
        //el contador sigue con el explorando los botones que no pertenecen al mes de la fecha establecida.
        contador = puntero-1;
        //aqui vamos a ir a cada boton que no pertenece al mes establecido por la fecha.
        for (int i = 0; i < puntero; i++) {
            //retrocedemos con la fechaEstatica un dia, para pintar el boton en color LIGHT_GRAY del mes anterior a la fecha establecida.
            fechaEstatica = fechaEstatica.getDiaAnterior();
            iuBotones[contador].setTexto(String.valueOf(fechaEstatica.getDia()));
            iuBotones[contador].iuTexto.setToolTipText(fechaEstatica.fecha());
            iuBotones[contador].iuTexto.setForeground(Color.LIGHT_GRAY);            
            contador--;
        }
    }    
    private boolean presionoMesHoy(MouseEvent e){
        int contador = 0;
        boolean verificador = false;
        while(contador < iuBotonesFecha.length && !verificador){
            if(e.getSource() == iuBotonesFecha[contador].iuTexto){
                verificador = true;
                fechaSeleccionada = new Fecha(iuBotonesFecha[contador].iuTexto.getToolTipText());                
            }
            contador++;
        }
        return verificador;
    }
    private void actualizarFechaCalendario(){
        iuTituloMes.setText(fecha.getFechaMes());  
        etiquetaTitulo.setText(fecha.getMesAno());
        iuAnos.setSelectedItem(String.valueOf(fecha.getAno()));

        establecerDiasMes(iuBotonesFecha, fecha, fecha);
    }
    
    public boolean getEstado(){
        return estado;
    }
    public void setAno(int ano){
        iuAnos.setSelectedItem(String.valueOf(ano));
        fecha.setAno(ano);
        actualizarFechaCalendario();
    }
    public Fecha getFecha(){
        return fechaSeleccionada;
    }
}