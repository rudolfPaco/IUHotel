/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.botones;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUBotonRadio extends JRadioButton{
    private Limitacion limitacion;
    private Color colorSeleccionado;
    private Color colorNoSeleccionado;
    
    /**
     * Interfaz Usuario Boton Radio, la clase hereda del componente JRadioButton. al momento de hacer click cambia el color a seleccionado, caso contrario retorna al color no seleccionado.
     * @param texto cadenad de caracteres que se muestra en el componente.
     * @param limitacion determina la posicion y el tamaño del componente. 
     * @param estado determina si el componente esta seleccionado si y solo si el estado es true, caso contrario el componente no esta seleccionado.
     */
    public IUBotonRadio(String texto, Limitacion limitacion, boolean estado){
        super(texto, estado);
        this.limitacion = limitacion;
        this.colorSeleccionado = new Color(2, 67, 109);
        this.colorNoSeleccionado = Color.LIGHT_GRAY;
        
        construirBotonRadio();
        agregarEvento();
    }
    private void construirBotonRadio(){        
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setIconTextGap(limitacion.getPorcentajeAncho(10));
        setFocusable(false);        
        setOpaque(false);
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setMargin(new Insets(1, 1, 1, 1));
        
        if(isSelected())
            setForeground(colorSeleccionado);        
        else
            setForeground(colorNoSeleccionado);
    }
    private void agregarEvento(){
        addItemListener((ItemEvent e) -> {
            if(isSelected())
                setForeground(colorSeleccionado);            
            else
                setForeground(colorNoSeleccionado);            
        });
    }

    /**
     * metodo que modifica los colores al momento de seleccionar o deseleccionar el componente.
     * @param colorSeleccionado el componente se pinta del colorSeleccionado si y solamente si esta seleccionado.
     * @param colorNoSeleccionado el componente se pinta del colorNoSeleccionado si y solamente si no esta seleccionado.
     */
    public void setColoresEstado(Color colorSeleccionado, Color colorNoSeleccionado){
        this.colorSeleccionado = colorSeleccionado;
        this.colorNoSeleccionado = colorNoSeleccionado;
        construirBotonRadio();
    }    

    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        
        construirBotonRadio();
        agregarEvento();
    }
}
