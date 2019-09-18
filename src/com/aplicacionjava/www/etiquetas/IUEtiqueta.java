/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.etiquetas;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author rudolf
 */
public class IUEtiqueta extends JLabel{
    private Limitacion limitacion;    
    /**
     * Interfaz de Usuario Etiqueta, hereda atributos y metodos del componente JLabel.
     * @param texto cadena de caractares, que se mostrara en el componente.
     * @param limitacion determina la posicion y el tamaño del componente.
     */
    public IUEtiqueta(String texto, Limitacion limitacion){
        super(texto);
        this.limitacion = limitacion;        
        
        construirEtiqueta();        
    }
    private void construirEtiqueta(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());        
        setOpaque(false);
        setLayout(null);
        setFocusable(false);
        setToolTipText("");
        setHorizontalTextPosition(SwingConstants.LEFT);
        setVerticalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setForeground(new Color(2, 67, 109));
    }    
    public Limitacion getLimitacion(){
        return limitacion;
    }    
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirEtiqueta();
    }
    public void setSubrayarTexto(boolean estado) {
        Map attributes = getFont().getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);        
        setFont(getFont().deriveFont(attributes));        
    }
}