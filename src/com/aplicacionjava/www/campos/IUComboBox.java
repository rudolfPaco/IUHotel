/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.campos;
import com.aplicacionjava.www.recursos.CustomUI;
import com.aplicacionjava.www.recursos.Limitacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author cero
 */
public class IUComboBox extends JComboBox<String>{
    
    private Limitacion limitacion;
    private String tipo;
    private boolean estadoTexto;
    
    /**
     * Interfaz de Usuario Combo Box, componente que hereda del JComboBox, recibe dos parametros; una lista de opciones y su limitacion. por defecto es editable, pero se puede restringir numeros decimales, enteros o letras, mayusculas.
     * @param opciones lista de opciones que se reflejara en el componente.
     * @param limitacion determina la posicion y tamaño del componente.
     */
    public IUComboBox(String[] opciones, Limitacion limitacion){
        super(opciones);
        this.limitacion = limitacion;
        construirComboBox();
        setEventos();
    }
    
    private void construirComboBox(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setFocusable(true);
        setEditable(true);        
        setOpaque(false);
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));
        setUI(CustomUI.createUI(this));
        setColorLetra(new Color(2, 67, 109));
        setTipo("");
        setEstadoTexto(true);        
        setOpacidad(false);
    }

    public String getTexto(){
        return ((JTextField)getEditor().getEditorComponent()).getText();
    }
    public void setColorLetra(Color colorLetra){
        ((JTextField)getEditor().getEditorComponent()).setForeground(colorLetra);
    }
    public void setOpacidad(boolean opacidad){
        ((JTextField)getEditor().getEditorComponent()).setOpaque(opacidad);
    }
    public void setPosicionHorizontal(int posicionHorizontal){
        ((JTextField)getEditor().getEditorComponent()).setHorizontalAlignment(posicionHorizontal);
    }

    /**
     * restringe la entrada de solo numeros decimales.
     */
    public void setRestriccionNumerosDecimales() {
        setTipo("DECIMAL");
        
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.'){
                    e.consume();
                }
                if(e.getKeyChar() == '.' && ((JTextField)getEditor().getEditorComponent()).getText().contains(".")){
                    e.consume();
                }
            }
        });
    }

    /**
     * restringe la entrada de solo letras.
     */
    public void setResctriccionLetras() {
        setTipo("LETRAS");
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isAlphabetic(c) && e.getKeyChar() != ' ' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE && e.getKeyChar() != KeyEvent.VK_ENTER && e.getKeyChar() != KeyEvent.VK_ESCAPE){
                    e.consume();
                }
            }            
        });
    }
    
    /**
     * restringe la entrada de solo numero enteros positivos
     */
    public void setRestriccionNumerosEnteros() {
        setTipo("ENTEROS");
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

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
     * restringe la entrada de solo letras Mayusculas
     */
    public void setRestriccionLetrasMayusculas() {
        setTipo("MAYUSCULAS");
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ((JTextField)getEditor().getEditorComponent()).setText(((JTextField)getEditor().getEditorComponent()).getText().toUpperCase());
            }
        });
    }

    private void setEventos() {        
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.VK_ENTER == e.getKeyCode()){
                    getEditor().getEditorComponent().transferFocus();
                }
                if(KeyEvent.VK_ESCAPE == e.getKeyCode())
                    getEditor().getEditorComponent().transferFocusBackward();
            }
        });
        getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {                
                ((JTextField)getEditor().getEditorComponent()).setSelectionStart(0);
                ((JTextField)getEditor().getEditorComponent()).setSelectionEnd(getTexto().length());                
                ((JTextField)getEditor().getEditorComponent()).setSelectionColor(new Color(0, 0, 0, 0));
                ((JTextField)getEditor().getEditorComponent()).setSelectedTextColor(new Color(2, 67, 109));
            }
        });
        getEditor().getEditorComponent().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                switch(getTipo()){
                    case "DECIMAL":
                        if(!getTexto().isEmpty()){
                            addItem(new DecimalFormat("0.00").format(new BigDecimal(Double.parseDouble(getTexto())).setScale(2, RoundingMode.HALF_UP).doubleValue()).replaceAll(",", "."));
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("debe agregar un numero decimal...!");
                        }
                            
                    break;
                    case "LETRAS":
                        if(getTexto().isEmpty()){
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("el campo No puede estar vacio, debe ingresar letras...!");
                        }                            
                    break;
                    case "ENTEROS":
                        if(getTexto().isEmpty()){
                            setBorder(new BevelBorder(BevelBorder.RAISED, new Color(180, 0, 0), Color.WHITE, new Color(180, 0, 0), Color.WHITE));
                            setToolTipText("");
                        }                            
                        else{
                            setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, Color.WHITE, Color.LIGHT_GRAY, Color.WHITE));        
                            setToolTipText("el campo No puede estar vacio, debe ingresar numeros enteros...!");
                        }
                    break;
                    case "MAYUSCULAS":
                        if(getTexto().isEmpty()){
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
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                if(!isEstadoTexto())
                    e.consume();
            }
        });
    }

    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirComboBox();
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public boolean isEstadoTexto() {
        return estadoTexto;
    }
    public void setEstadoTexto(boolean estado) {
        this.estadoTexto = estado;
    }
}
