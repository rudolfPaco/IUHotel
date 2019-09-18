/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.campos;

import com.aplicacionjava.www.recursos.Contorno;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author rudolf
 */
public class IUAreaTexto extends JTextArea{
    private Limitacion limitacion;

    public IUAreaTexto(String texto, Limitacion limitacion){
        super(texto);
        this.limitacion = limitacion;
        
        construirAreaTexto();
    }    
    private void construirAreaTexto(){        
        setSelectionColor(new Color(0, 0, 0, 0));
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setSelectedTextColor(new Color(2, 67, 109));        
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);
        setForeground(new Color(2, 67, 109));
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));
        
    }
    public void setTextoEditable(boolean editable){
        setEditable(editable);
        setFocusable(editable);
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirAreaTexto();
    }
}
