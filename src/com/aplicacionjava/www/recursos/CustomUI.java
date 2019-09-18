/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

/**
 *
 * @author hotel-felipez
 */
public class CustomUI extends BasicComboBoxUI {

    private final Color colorFondo = new Color(0, 0, 0, 0);//Color.WHITE;
    private final Color colorLetra = new Color(2, 67, 109);
    
    private final Color colorFondoSeleccinado = Color.YELLOW;//new Color(242, 238, 236);
    private final Color colorLetraSeleccionado = new Color(2, 67, 109);
    
    private final Color colorSombra = new Color(2, 67, 109); 
    private final Color colorOscuro = new Color(120, 120, 120);

    public static ComboBoxUI createUI(JComponent c) {
        return new CustomUI();
    }

    @Override
    protected JButton createArrowButton() {
        BasicArrowButton botonFlecha = new BasicArrowButton(BasicArrowButton.SOUTH, //Direccion de la flecha
                colorFondo, //Color de fondo
                colorOscuro,//sombra
                colorSombra,//darkShadow
                colorLetra //highlight
        );
        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
        botonFlecha.setBorder(BorderFactory.createLineBorder(colorFondo, 1));
        botonFlecha.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return botonFlecha;
    }
    
    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup comboBoxPopup = new BasicComboPopup(comboBox);
        comboBoxPopup.setBorder(new LineBorder(colorLetra));
        return comboBoxPopup;
    }
    
    //Pinta los elementos de la lista desplegada del comboBox
    @Override
    protected ListCellRenderer createRenderer() {
        return new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                
                //este metodo es llamado para generar un componente, lo cual ya tiene un tamaño predefinido en que se puede usar.
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                list.setSelectionBackground(colorFondoSeleccinado);
                list.setCursor(new Cursor(Cursor.HAND_CURSOR));                
                
                if (isSelected) {
                    setBackground(colorFondoSeleccinado);
                    setForeground(colorLetraSeleccionado);//new Color(100, 100, 100)
                } else {
                    setBackground(colorFondo);//Color.WHITE
                    setForeground(colorLetra);//new Color(2, 67, 109)
                }                
                return this;
            }
        };
    }
}
