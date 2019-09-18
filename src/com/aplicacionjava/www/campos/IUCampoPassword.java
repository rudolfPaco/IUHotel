/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.campos;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author rudolf
 */
public class IUCampoPassword extends JPasswordField {
    private Limitacion limitacion;
        
    public IUCampoPassword(String texto, Limitacion limitacion){
        super(texto);
        this.limitacion = limitacion;        
        
        construirCampoPassword();
        setEventos();
    }
    private void construirCampoPassword(){        
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setSelectedTextColor(new Color(2, 67, 109));
        setForeground(new Color(2, 67, 109));
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setOpaque(false);
        setSelectionColor(new Color(0, 0, 0, 0));
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
    }    
    private void setEventos(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER == e.getKeyCode())
                    transferFocus();
                
                if(KeyEvent.VK_ESCAPE == e.getKeyCode())
                    transferFocusBackward();
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setSelectionStart(0);
                setSelectionEnd(getPassword().length);
            }
        });
    }
    
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirCampoPassword();
    }
}
