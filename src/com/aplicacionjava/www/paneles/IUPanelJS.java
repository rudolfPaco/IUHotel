/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author rudolf
 */
public class IUPanelJS extends JPanel{
    public Limitacion limitacion;
    public JScrollPane deslizador;
    /**
     * panel sin borde y sin el foco.
     * @param limitacion objeto que se encarga de posicionar el componente panel dentro de el componente padre, dandole un tamaño fijo al panel.
     */
    public IUPanelJS(Limitacion limitacion){
        super(null);
        this.limitacion = limitacion;
        
        construirPanel();
    }
    private void construirPanel(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setBorder(null); 
        setFocusable(false);        
        setOpaque(false);
        
        deslizador = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        deslizador.setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        deslizador.setBackground(Color.WHITE);        
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirPanel();
    }
}
