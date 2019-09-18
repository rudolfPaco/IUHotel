/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.paneles;

import com.aplicacionjava.www.recursos.Limitacion;
import javax.swing.JPanel;

/**
 *
 * @author rudolf
 */
public class IUPanel extends JPanel{
    public Limitacion limitacion;
    
    /**
     * panel sin borde y sin el foco.
     * @param limitacion objeto que se encarga de posicionar el componente panel dentro de el componente padre, dandole un tamaño fijo al panel.
     */
    public IUPanel(Limitacion limitacion){
        super(null);
        this.limitacion = limitacion;
        
        construirPanel();
    }
    private void construirPanel(){
        setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());
        setBorder(null); 
        setFocusable(false);        
        setOpaque(false);
    }
    public Limitacion getLimitacion() {
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion) {
        this.limitacion = limitacion;
        construirPanel();
    }
}
