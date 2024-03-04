package Fractals;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Fractal extends JComponent{
    protected double xOrigin, yOrigin, xEnd, yEnd;
    protected int exponent;

    protected int imageWidth, imageHeight;
    protected BufferedImage plot;
    private int offsetX, offsetY;

    public Fractal(double xOrigin, double xEnd, double yOrigin, double yEnd, int panelWidth, int panelHeight, int exponent){
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.exponent = exponent;
        scaleImage(panelWidth, panelHeight);
    }
    // Adjust image settings to account for adjusted panel size and aspect ratio
    public void scaleImage(int panelWidth, int panelHeight){
        double panelAspect = panelWidth / panelHeight;
        double graphAspect = (xEnd-xOrigin) / (yEnd - yOrigin);
        // Height is the limiting factor
        if(panelAspect > graphAspect){
            imageHeight = panelHeight;
            imageWidth = (int)((double)imageHeight * graphAspect);
        }
        // Width is the limiting factor
        else{
            imageWidth = panelWidth;
            imageHeight = (int)((double)imageWidth / graphAspect);
        }
        offsetX = (panelWidth - imageWidth) / 2;
        offsetY = (panelHeight - imageHeight) / 2;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        plot = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    }

    public void setExponent(int exponent){
        this.exponent = exponent;
    }

    // Should fill in the image to be drawn
    public abstract void render();
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(plot, offsetX, offsetY, imageWidth, imageHeight, this);
    }
}
