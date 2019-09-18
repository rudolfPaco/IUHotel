/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.ventanas;

import com.aplicacionjava.www.paneles.IUPanelBD;
import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author cero
 */
public class IUVentana extends JDialog{
    
    protected IUPanelBD panelContenedor;
    protected Limitacion limitacion;
    private int arco;
            
    public IUVentana(JFrame ventanaPrincipal, Limitacion limitacion){
        super(ventanaPrincipal, true);
        this.limitacion = limitacion;
        construirDialog();
    }    
    private void construirDialog(){     
        setSize(new Dimension(limitacion.getAncho(), limitacion.getAlto()));
        setUndecorated(true);
        setAlwaysOnTop(false);        
        setLocationRelativeTo(this);        
        setOpacity(1f);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        setModal(true);
        construirComponentes();        
    }
    private void construirComponentes(){
        panelContenedor = new IUPanelBD(new Limitacion(limitacion.getAncho(), limitacion.getAlto()));
        panelContenedor.setArco(limitacion.getPorcentajeAlto(1));
        getContentPane().add(panelContenedor);
    }    
    public Limitacion getLimitacion(){
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirDialog();
    }
    public int getArco() {
        return arco;
    }
    public void setArco(int arco) {
        this.arco = arco;
        panelContenedor.setArco(arco);
    }
}
