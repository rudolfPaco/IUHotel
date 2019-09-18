/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.tablas;

import com.aplicacionjava.www.recursos.Limitacion;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.*;

/**
 *
 * @author cero
 */
public class IUTabla extends JTable{
    public JScrollPane deslizador;
    public Limitacion limitacion;    
    
    private Color colorFondoCabecera = new Color(2, 67, 109);
    private Color colorLetraCabecera = new Color(255, 255, 255);
    private Color colorSeleccionFondo = Color.YELLOW;//new Color(242, 238, 236);
    private Color colorLetra = new Color(2, 67, 109);
    private Color colorGrid = Color.LIGHT_GRAY;
    
    public IUTabla(AbstractTableModel modelo, Limitacion limitacion){
        super(modelo);
        this.limitacion = limitacion;
        
        construirTabla();
    }
    private void construirTabla(){
        deslizador = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        deslizador.setBounds(limitacion.getX(), limitacion.getY(), limitacion.getAncho(), limitacion.getAlto());        
        deslizador.setBorder(new BevelBorder(BevelBorder.RAISED, colorGrid, colorLetraCabecera, colorGrid, colorLetraCabecera));
        
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setShowHorizontalLines(false);
        setShowVerticalLines(true);
        setFillsViewportHeight(true);
        setCursor(new Cursor(Cursor.HAND_CURSOR));        
        setFocusable(false);
        setFuenteCabecera(new Font("Verdana", Font.PLAIN, 14), colorFondoCabecera, colorLetraCabecera);
        setFuente(new Font("Verdana", Font.PLAIN, 14));
        setRowSelectionAllowed(true);
        setGridColor(colorGrid);
        setSelectionBackground(colorSeleccionFondo);        
        setForeground(colorLetra);
    }
    public void setColorTabla(Color colorFondoCabecera, Color colorLetraCabecera, Color colorGrid, Color colorSeleccionFondo, Color colorLetra){
        this.colorFondoCabecera = colorFondoCabecera;
        this.colorLetraCabecera = colorLetraCabecera;
        this.colorGrid = colorGrid;
        this.colorSeleccionFondo = colorSeleccionFondo;
        this.colorLetra = colorLetra;
        construirTabla();
    }
    public void setColumnaRender(int columna){
        getColumnModel().getColumn(columna).setCellRenderer(getTableHeader().getDefaultRenderer());                
    }
    public void setPosicionTextoHorizontal(int numeroColumna, int posicionHorizontal){
        if(numeroColumna < getModel().getColumnCount()){
            DefaultTableCellRenderer columnaCeldasTabla = new DefaultTableCellRenderer();
            columnaCeldasTabla.setHorizontalAlignment(posicionHorizontal);
            getColumnModel().getColumn(numeroColumna).setCellRenderer(columnaCeldasTabla);            
        }
    }
    public void setFuenteCabecera(Font fuente, Color colorCabecera, Color colorLetra){
        JTableHeader th = getTableHeader();          
        th.setBackground(colorCabecera);
        th.setForeground(colorLetra);
        th.setFont(fuente);
    }
    public void setCellRender(int numeroColumna, TableCellRenderer render){
        if(numeroColumna < getModel().getColumnCount())
            getColumnModel().getColumn(numeroColumna).setCellRenderer(render);
    }
    public void agregarAnchoColumnas(int[] porcentajes) {
        int ancho = deslizador.getWidth();
        int anchoColumna;
        TableColumnModel modeloColumna = getColumnModel();
        TableColumn columnaTabla;
        for (int i = 0; i < getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            anchoColumna = (porcentajes[i] * ancho) / 100;            
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    public void setFuente(Font fuente_letra){
        setRowHeight(fuente_letra.getSize() + 7);
        setFont(fuente_letra);        
    }
    public boolean isFilaSeleccionado(){
        boolean filaSeleccionada = false;
        if(getSelectedRow() > -1)
            filaSeleccionada = true;
        return filaSeleccionada;
    }
    public Limitacion getLimitacion(){
        return limitacion;
    }
    public void setLimitacion(Limitacion limitacion){
        this.limitacion = limitacion;
        construirTabla();
    }
}
