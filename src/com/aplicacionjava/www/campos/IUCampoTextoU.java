/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.campos;

import com.aplicacionjava.www.etiquetas.IUEtiqueta;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author neo
 */
public class IUCampoTextoU extends JTextField {
    private Limitacion limitacion;
    private String unidad;
    private String tipo;
    private final int porcentajeAncho;
    
    public IUEtiqueta iuUnidad;
        
    /**
     * Interfaz de Usuario Campo de Texto, se crea el componente al inicio de tipo texto, pero se puede restringir los datos de entrada al campo de texto.
     * @param texto cadena de caracteres que se mostraran al inicio en el componente.
     * @param unidad es la unidad de medida del campo de texto. 
     * @param limitacion determina la posicion y el tamaño del componente.
     * @param porcentajeAnchuraUnidad esteblece un porcentaje de ancho de la unidad que se encuentra dentro del componente campo de texto. si y solo si el valor del porcentaje se encuentre dentro el rango [1 - 100]
     */
    public IUCampoTextoU(String texto, String unidad, Limitacion limitacion, int porcentajeAnchuraUnidad){
        super(texto);
        this.unidad = unidad;
        this.limitacion = limitacion;
        this.porcentajeAncho = porcentajeAnchuraUnidad;
        
        construirCampoTexto();
        agregarEventos();
    }
    private void construirCampoTexto(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setSelectedTextColor(new Color(2, 67, 109));
        setForeground(new Color(2, 67, 109));
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setOpaque(false);
        setSelectionColor(new Color(0, 0, 0, 0));
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
        setTipo("");
        construirEtiqueta(getLimitacion());
        
    }    
    private void construirEtiqueta(Limitacion limite){
        iuUnidad = new IUEtiqueta(unidad, new Limitacion(limite.getPorcentajeAncho(100 - porcentajeAncho), limite.getPorcentajeAlto(5), limite.getPorcentajeAncho(porcentajeAncho), limite.getPorcentajeAlto(90)));
        iuUnidad.setForeground(new Color(120, 120, 120));
        iuUnidad.setHorizontalAlignment(SwingConstants.CENTER);
        add(iuUnidad);
    }    
    public void setPosicionHorizontalUnidad(String posicion){
        switch(posicion){
            case "izquierda":
                Limitacion limiteIzquierda = new Limitacion(limitacion.getPorcentajeAncho(0), limitacion.getPorcentajeAlto(5), limitacion.getPorcentajeAncho(porcentajeAncho), limitacion.getPorcentajeAlto(90));
                iuUnidad.setLimitacion(limiteIzquierda);
                setHorizontalAlignment(SwingConstants.RIGHT);
            break;
                
            case "derecha":
                Limitacion limiteDerecha = new Limitacion(limitacion.getPorcentajeAncho(100 - porcentajeAncho), limitacion.getPorcentajeAlto(5), limitacion.getPorcentajeAncho(porcentajeAncho), limitacion.getPorcentajeAlto(90));
                iuUnidad.setLimitacion(limiteDerecha);
                setHorizontalAlignment(SwingConstants.LEFT);
            break;
        }
    }
    private void agregarEventos(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER == e.getKeyCode()){
                    transferFocus();
                }
                if(KeyEvent.VK_ESCAPE == e.getKeyCode())
                    transferFocusBackward();
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                setSelectionStart(0);
                setSelectionEnd(getText().length());
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                switch(getTipo()){
                    case "DECIMAL":
                        if(!getText().isEmpty()){
                            setText(new DecimalFormat("0.00").format(new BigDecimal(Double.parseDouble(getText())).setScale(2, RoundingMode.HALF_UP).doubleValue()).replaceAll(",", "."));
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("debe agregar un numero decimal...!");
                        }
                            
                    break;
                    case "LETRAS":
                        if(getText().isEmpty()){
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("el campo No puede estar vacio, debe ingresar letras...!");
                        }                            
                    break;
                    case "ENTEROS":
                        if(getText().isEmpty()){
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("el campo No puede estar vacio, debe ingresar numeros enteros...!");
                        }
                    break;
                    case "MAYUSCULAS":
                        if(getText().isEmpty()){
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("el campo No puede estar vacio...!");
                        }
                    break;
                }
            }
        });
    }    

    /**
     * metodo que restringe los datos de entrada, a solo numeros decimales redondeando a dos decimales >= 5. (ej. 234.258 = 234.26)
     */
    public void setRestriccionNumeroDecimal() {
        //setText("");
        setTipo("DECIMAL");
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.'){                    
                    e.consume();                    
                }
                if(e.getKeyChar() == '.' && getText().contains(".")){
                    e.consume();
                }
            }
        });
    }

    /**
     * metodo que restringe los datos de entrada, a solo letras y espacios, pueden ser mayusculas o minusculas. (ej. abcdefGHIJK)
     */
    public void setRestriccionLetras() {
        setText("");
        setTipo("LETRAS");
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isAlphabetic(c) && e.getKeyChar() != ' ' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER){
                    e.consume();
                }
            }
        });
    }

    /**
     * metodo que restringe los datos de entrada, a solo numeros enteros positivos >= 0. (ej. 0 124 23456)
     */
    public void setRestriccionNumeroEnteros() {
        //setText("");
        setTipo("ENTEROS");
        
        addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c) && c > 0){                    
                    e.consume();
                }                    
            }
        });
    }

    /**
     * metodo que restringe los datos de entrada, a solo letras Mayusculas, al teclear una tecla, automaticamente cambia a letras Mayusculas.
     */
    public void setRestriccionLetrasMayusculas() {
        setText("");
        setTipo("MAYUSCULAS");
        
        addKeyListener(new KeyAdapter() {
            
            @Override            
            public void keyReleased(KeyEvent e) {                
                setText(getText().toUpperCase());
            }
        });
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirCampoTexto();
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setFuente(Font fuente){
        setFont(fuente);
        iuUnidad.setFont(fuente);
    }
    public void setUnidad(String unidad){
        this.unidad = unidad;
        iuUnidad.setText(unidad);
    }
}
