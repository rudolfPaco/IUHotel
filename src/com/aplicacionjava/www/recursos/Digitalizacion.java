/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.recursos;

import com.aplicacionjava.www.etiquetas.IUEtiquetaI;
import java.applet.Applet;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import uk.co.mmscomputing.device.scanner.ScannerIOMetadata.Type;
import uk.co.mmscomputing.device.scanner.*;
import uk.co.mmscomputing.device.twain.TwainIOMetadata;
import uk.co.mmscomputing.device.twain.TwainSource;

/**
 *
 * @author Rudolf Felipez Mancilla
 */
public final class Digitalizacion extends Applet implements ScannerListener {

    private TwainSource ts;
    private Scanner scanner;
    private BufferedImage imagen;
    private IUEtiquetaI iuRetrato = null;
    private Archivo documento;
    private boolean estado;
    
    public Digitalizacion(IUEtiquetaI iuRetrato){
        this.iuRetrato = iuRetrato;        
        this.documento = null;
        this.estado = false;
        init();        
    }
    
    @Override
    public void init(){

        try {
            scanner = Scanner.getDevice();         
            if(scanner != null){
                System.out.println("info: "+scanner.toString());
                String scanners[]=scanner.getDeviceNames();
                for(String scann:scanners){
                    System.out.println(scann);
                }
                System.out.println("se hizo una busqueda de scanners");
                scanner.addListener(this);
                start();
                try {                    
                    if(scanner.getSelectedDeviceName().isEmpty()){
                        scanner.select();
                    }else{
                        System.out.println("entro a ACQUIRE");
                        scanner.acquire();
                    }                    
                } catch (ScannerIOException ex) { Logger.getLogger(Digitalizacion.class.getName()).log(Level.SEVERE, null, ex); }
                destroy();
            }else{
                System.out.println("el scanner no existe...!");
            }
            
        } catch (ScannerIOException ex) { Logger.getLogger(Digitalizacion.class.getName()).log(Level.SEVERE, null, ex); }
    }

            
    @Override
    public void update(Type type, ScannerIOMetadata metadata) {
        System.out.println("se hizo una actualizacion");
        if(type.equals(ScannerIOMetadata.ACQUIRED)){
            System.out.println("entro al objetivo");
            imagen = metadata.getImage();
            System.out.println("Have an image now!");
            iuRetrato.setIcon(new ImageIcon(new ImageIcon(imagen).getImage().getScaledInstance(iuRetrato.getWidth(), iuRetrato.getHeight(), Image.SCALE_DEFAULT)));
            /*System.out.println("la direccion es: "+direccionImagenes);
            File archivo_imagen = new File(direccionImagenes+nombreImagen+".png");
            ImageIO.write(imagen, "png", archivo_imagen);
            
            System.out.println("la direccion 1 es: "+archivo_imagen.getAbsolutePath());
            System.out.println("la direccion 2 es: "+archivo_imagen.getCanonicalPath());
            System.out.println("la direccion 3 es: "+archivo_imagen.getPath());
            
            documento = new Archivo(nombreImagen, archivo_imagen.getAbsolutePath(), tipoDocumento, imagen);
            */
            estado = true;
            //iuRetrato.setUrlImagen(documento.getUrlDocumento());              
            iuRetrato.setBuffered(imagen);
            try{
                new uk.co.mmscomputing.concurrent.Semaphore(0,true).tryAcquire(2000,null);
            } catch (InterruptedException ex) {
                System.out.println("error. : "+ex.getMessage());;
            }
        }else{
            if(type.equals(ScannerIOMetadata.NEGOTIATE)){
                System.out.println("entro a NEGOTIATE");
                ScannerDevice device = metadata.getDevice();

                try{
                    device.setShowUserInterface(false);
                    device.setResolution(100);
                    device.setRegionOfInterest(0,0,800,900);               // top-left corner 400x500 pixels                    

                    device.setShowProgressBar(true);
                    device.setShowUserInterface(true);
                    
                }catch(ScannerIOException e){}

            }else{ 
                if(type.equals(ScannerIOMetadata.STATECHANGE)){                    
                    System.out.println("Scanner State "+metadata.getStateStr());
                    System.out.println("Scanner State "+metadata.getState());
                    //switch (metadata.ACQUIRED){};
                    ts = ((TwainIOMetadata)metadata).getSource();
                    //ts.setCancel(false);
                    //ts.getState()
                    //TwainConstants.STATE_TRANSFERREADY
                     ((TwainIOMetadata)metadata).setState(6);
                    if ((metadata.getLastState() == 3) && (metadata.getState() == 4)){} 
                    // IJ.error(metadata.getStateStr());                    
                }else{
                    if(type.equals(ScannerIOMetadata.EXCEPTION)){}
                }
            }
        }
    }
    public boolean getEstado(){
        return estado;
    }
    public IUEtiquetaI getEtiqueta(){
        return iuRetrato;
    }
    public Archivo getDocumento(){
        return documento;
    }
}