/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aplicacionjava.www.etiquetas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;

/**
 *
 * @author rudolf
 */
public class IUEtiquetaD extends JLabel implements MouseMotionListener,MouseListener   {

    Image img;
    BufferedImage Imagmemoria;
    BufferedImage tmp_Recorte;
   
    public File archivo = null;
    
    Graphics2D g2D;
    
    private final JSlider iuDeslizador;

    float x=0;
    float y=0;
    float ancho=0;
    float alto=0;
    
    public IUEtiquetaD(JSlider iuDeslizador, BufferedImage buffered){
        this.img = buffered;
        this.iuDeslizador = iuDeslizador;
        this.setSize(buffered.getWidth(), buffered.getHeight());
        this.setVisible(true);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;      
      if(img!=null){        
        
        int scale = iuDeslizador.getValue();
        int nw = img.getWidth(this) * scale / 100;
        int nh = img.getHeight(this) * scale / 100;
                
        Imagmemoria = new BufferedImage(nw, nh, BufferedImage.TYPE_INT_RGB);
        g2D = Imagmemoria.createGraphics();
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.drawImage(img, 0, 0, nw, nh, this);        
        g2D.setStroke(new BasicStroke(2f));
        g2D.setColor(Color.LIGHT_GRAY);
        Rectangle2D r2 = new Rectangle2D.Float( x, y, ancho, alto );
        g2D.draw(r2);
        g2.drawImage(Imagmemoria, 0, 0, nw, nh, null);
      }
    }

    public void recortar(IUEtiquetaI foto){
        if(x + ancho < Imagmemoria.getWidth(null) && y + alto < Imagmemoria.getHeight(null)){
            tmp_Recorte = ((BufferedImage)Imagmemoria).getSubimage((int)x+1,(int) y+1, (int) ancho-1, (int) alto-1) ;
            foto.setIcon(new ImageIcon(new ImageIcon(tmp_Recorte.getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_AREA_AVERAGING)).getImage()));
            //foto.setBufferedImage(tmp_Recorte);            
            foto.setBuffered(tmp_Recorte);
            /*try {          
                archivo = new File(direccion);
                ImageIO.write(tmp_Recorte, "png", archivo);
                
                documento = new Archivo(archivo.getName(), archivo.getAbsolutePath(), "FOTO_PERSONAL", tmp_Recorte);
                JOptionPane.showMessageDialog(null, "Se ha guardado Correctamente la imagen recortada");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error, Trate nuevamente");
            }*/
        }
    }
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        ancho = e.getX()-x;
        alto = e.getY()-y;

        if(ancho<0) ancho=0;
        if(alto<0) alto=0;
        if(x > this.getWidth()) x = img.getWidth(this) - ancho;        
        if(y > this.getHeight()) y = img.getHeight(this) - alto;
        
        this.repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        System.out.println("el x: "+x+"     el y: "+y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
