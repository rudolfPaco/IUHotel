/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.tablas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author hotel-felipez
 * @param <T>
 */
public class ModeloTabla<T> extends AbstractTableModel{
    private String[] nombreCabecera;
    public Class[] tipoColumnas;
    private ArrayList<T> lista = new ArrayList<>();
     
    public void setModelo(String[] nombreCabecera, Class[] tipoColumnas, ArrayList<T> lista){
        this.nombreCabecera = nombreCabecera;
        this.tipoColumnas = tipoColumnas;
        this.lista = lista;
    }
    public boolean isVacia(){
        return lista.isEmpty();
    }    
    public void setFila(T elemento) {
        lista.add(elemento);
        fireTableDataChanged();
    }
    public void removeFila(int rowIndex) {
        lista.remove(rowIndex);
        fireTableDataChanged();
    }    
    public void limpiarTabla() {
        lista.clear();
        fireTableDataChanged();
    }
    public T getFila(int rowIndex){
        return lista.get(rowIndex);
    }    
    @Override
    public String getColumnName(int columnIndex) {
        return nombreCabecera[columnIndex];
    }
    @Override
    public int getRowCount() {
        return lista.size();
    }
    @Override
    public int getColumnCount() {
        return nombreCabecera.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
    @Override
    public Class getColumnClass(int columnIndex) {
        return tipoColumnas[columnIndex];        
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {        
    }
}
