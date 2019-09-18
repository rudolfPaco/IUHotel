/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

/**
 *
 * @author rudolf
 */
public class Limitacion {
    private int x;
    private int y;
    private int ancho;
    private int alto;

    public Limitacion(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }
    public Limitacion(int ancho, int alto) {
        this.x = 0;
        this.y = 0;
        this.ancho = ancho;
        this.alto = alto;
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getAncho() {
        return ancho;
    }
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }
    public int getAlto() {
        return alto;
    }
    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    /**
     *
     * @param porcentaje toma un valor del 1% al 100%, multiplicando por el ancho y dividiendo por el 100%.
     * @return el porcentaje del ancho establecido.
     */
    public int getPorcentajeAncho(int porcentaje){
        int valor = (ancho*porcentaje)/100;
        return valor;
    }
    /**
     *
     * @param porcentaje toma un valor del 1% al 100%, multiplicando por el alto y dividiendo por el 100%.
     * @return el porcentaje del ancho establecido.
     */
    public int getPorcentajeAlto(int porcentaje){
        int valor = (alto*porcentaje)/100;
        return valor;
    }

    /**
     * metodo que multiplica la distancia en x y suma el ancho establecido. (ej. nroVeces*X + ancho)
     * @param nroVeces multiplica la distancia en x, para tener espacio
     * @return todo el tamaño horizontal, incluyendo la distancia en x
     */
    public int getTamX(int nroVeces){
        return nroVeces*x + ancho;
    }
    /**
     * metodo que multiplica la distancia en y, suma el alto establecido. (ej. nroVeces*Y + alto)
     * @param nroVeces multiplica la distancia en y, para tener espacio
     * @return todo el tamaño vertical, incluyendo la distancia en y
     */
    public int getTamY(int nroVeces){
        return nroVeces*y + alto;
    }

    @Override
    public String toString() {
        return "Limitacion{" + "x=" + x + ", y=" + y + ", ancho=" + ancho + ", alto=" + alto + '}';
    }    
}