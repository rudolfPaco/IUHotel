/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neo
 */
public class Directorio {
    private File carpetaOrigen;
    private File carpetaDestino;
    private File carpetaFotos;
    
    private final String direccionOrigen;
    private final String direccionDestino;
    private final String direccionFotos;
    
    public Directorio(String direccionOrigen, String direccionDestino, String direccionFotos){
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.direccionFotos = direccionFotos;
        
        actualizarCarpetas();
    }
    private void actualizarCarpetas(){
        if(direccionCorrecta(System.getProperty("user.dir"), "dist"))
            this.carpetaOrigen = new File(obtenerDireccionDocumento(System.getProperty("user.dir"))+direccionOrigen);
        else     
            this.carpetaOrigen = new File(System.getProperty("user.dir")+direccionOrigen);
        
        this.carpetaDestino = new File(direccionDestino);
        this.carpetaFotos = new File(direccionFotos);
    }
    public void crearDirectorioOrigen(){
        carpetaDestino.mkdirs();        
        carpetaFotos.mkdirs();
    }
    public void copiarArchivoOrigen_Destino(String nombreArchivo){
        
        OutputStream out = null;
        InputStream in = null;        
        try {
            in = new FileInputStream(carpetaOrigen.getAbsolutePath()+"\\"+nombreArchivo);
            out = new FileOutputStream(direccionFotos+"\\"+nombreArchivo);
            
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
               out.write(buf, 0, len);
            }
            
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Directorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public String getDireccionOrigen(){
        return direccionOrigen;
    }
    public String getDireccionDestino(){
        return direccionDestino;
    }
    public String getDireccionFoto(){
        return direccionFotos;
    }
    public static String obtenerDireccionDocumento(String direccion){        
        String dir = direccion.substring(0, direccion.lastIndexOf("\\")+1);
        return dir;
    }
    public boolean direccionCorrecta(String direccion, String directorio){
        boolean verificador = false;
        StringTokenizer contenido = new StringTokenizer(direccion, "\\");
        while(contenido.hasMoreTokens() && !verificador){
            if(directorio.equalsIgnoreCase(contenido.nextToken())){
                verificador = true;
                System.out.println(direccion);
            }
        }
        return verificador;
    }
}
