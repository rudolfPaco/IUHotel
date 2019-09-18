/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author rudolf
 */
public class Documento {
    private String nombre;
    private String url;
    private String tipo;
    
    private BufferedImage buffer;
    private Image imagen;
    
    /**
     * es una clase que guarda algunos atributos de un archivo tipo, referentemente una imagen.     
     */
    public Documento(BufferedImage buffer){                
        this.buffer = buffer;
        this.imagen = null;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BufferedImage getBuffer() {
        return buffer;
    }
    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }
    public Image getImagen() {
        return imagen;
    }
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    @Override
    public String toString() {
        return "Documento{" + "nombre=" + nombre + ", direccion=" + url + ", tipo=" + tipo + ", buffer=" + buffer + ", imagen=" + imagen + '}';
    }
}
