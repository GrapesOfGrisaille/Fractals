package Fractals;

import javax.swing.JComponent;

import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Graphics2D;


public abstract class Fractal extends JComponent implements MouseListener{
    protected double xOrigin, yOrigin, xEnd, yEnd;
    protected double nextX, nextY;
    protected int exponent;

    protected int imageWidth, imageHeight;
    protected BufferedImage plot;
    private int offsetX, offsetY;

    private boolean zoomEnabled, zoomPreview;
    private double percentMagnify;

    public Fractal(double xOrigin, double xEnd, double yOrigin, double yEnd, int exponent){
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.exponent = exponent;
        zoomEnabled = false;
        addMouseListener(this);
    }
    // Adjust image settings to account for adjusted panel size and aspect ratio
    public void scaleImage(int panelWidth, int panelHeight){
        if(zoomPreview){
            zoom();
        }
        double panelAspect = (double)panelWidth / (double)panelHeight;
        double graphAspect = (xEnd-xOrigin) / (yOrigin-yEnd);
        // Height is the limiting factor
        if(panelAspect > graphAspect){
            imageHeight = panelHeight;
            imageWidth = (int)((double)imageHeight * graphAspect);
        }
        else{ // Width is the limiting factor
            imageWidth = panelWidth;
            imageHeight = (int)((double)imageWidth / graphAspect);
        }
        offsetX = (panelWidth - imageWidth) / 2;
        offsetY = (panelHeight - imageHeight) / 2;
        setPreferredSize(new Dimension(imageWidth, imageHeight));
        plot = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        zoomPreview = false;
    }

    public void setExponent(int exponent){
        this.exponent = exponent;
    }

    // Conversion between screen and graph points
    public double[] screenGraphConversion(int x, int y){
        double[] graphPoint = new double[2];
        // Account for screen point offset
        x -= offsetX;
        y -= offsetY;
        graphPoint[0] = xOrigin + (xEnd - xOrigin) * (double)x / (double)imageWidth;
        graphPoint[1] = yOrigin - (yOrigin - yEnd) * (double)y / (double)imageHeight;
        System.out.printf("Screen Point %d %d Graph Point %4f %4f\n", x, y, graphPoint[0], graphPoint[1]);
        return graphPoint;
    }
    public int[] graphScreenConversion(double x, double y){
        int[] screenPoint = new int[2];
        screenPoint[0] = (int)((double)imageWidth * (x - xOrigin) / (xEnd - xOrigin));
        screenPoint[1] = (int)((double)imageHeight * (yOrigin - y) / (yOrigin-yEnd));
        System.out.printf("Graph Point %4f %4f Screen Point %d %d\n", x, y, screenPoint[0], screenPoint[1]);
        return screenPoint;
    }

    // Should fill in the image to be drawn
    public abstract void render();
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(plot, 0, 0, imageWidth, imageHeight, this);
        if(zoomPreview){
            int[] screenPoint = graphScreenConversion(nextX, nextY);
            // Draw Preview
            g.setColor(Color.white);
            System.out.println(screenPoint[0] + " " + screenPoint[1]);
            g.drawRect(screenPoint[0], screenPoint[1], (int)((double)imageWidth * percentMagnify), (int)((double)imageHeight*percentMagnify));
            
        }
        if(zoomEnabled){ // Draw image border to display mode
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(10));
            g2.drawRect(0, 0, imageWidth, imageHeight);
        }
    }

    public void zoom(){
        xEnd = nextX + (xEnd - xOrigin) * percentMagnify;
        yEnd = nextY - (yOrigin - yEnd) * percentMagnify;
        xOrigin = nextX;
        yOrigin = nextY;
    }
    public void enableZoom(double percentMagnify){
        zoomEnabled = !zoomEnabled;
        zoomPreview = false;
        this.percentMagnify = percentMagnify;
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e){
        if(zoomEnabled){
            int x = e.getX();
            int y = e.getY();
            if(x >= 0 && x <= imageWidth){
                if(y >= 0 && y <= imageHeight){
                    System.out.println("Old " + xOrigin + " " + yOrigin + " " + xEnd + " " + yEnd);
                    System.out.println("Mouse " + x + " " + y);
                    System.out.println("Dim " + imageWidth + " " + imageHeight);
                    double[] newOrigin = screenGraphConversion(x, y);
                    nextX = newOrigin[0];
                    nextY = newOrigin[1];
                    zoomPreview = true;
                    System.out.println("New " + xOrigin + " " + yOrigin);
                    System.out.println(imageHeight * percentMagnify);
                    repaint();
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
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
