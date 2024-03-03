import javax.swing.JComponent;
import java.awt.Dimension;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mandelbrot extends JComponent{

    private static final int ITERATIONS = 100;

    private double xOrigin, yOrigin, xEnd, yEnd;
    private int imageWidth, imageHeight;
    private BufferedImage plot;

    public Mandelbrot(double xOrigin, double xEnd, double yOrigin, double yEnd, int imageWidth, int imageHeight){
        System.out.println("Calculating");
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;

        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        setPreferredSize(new Dimension(imageWidth, imageHeight));
        plot = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        plotMandelbrot();
        System.out.println("Done");
    }
    // Return type is intensity of pixel at current point, based on # iterations
    int calcPoint(double x, double y){
        ComplexNum z0 = new ComplexNum(x, y);
        ComplexNum zn = z0;
        for(int i = 0; i < ITERATIONS; i++){
            zn = zn.multiply(zn).add(z0); // z_n+1 = z_n^2 + z_0
            if(zn.magnitude() > 2.0){
                return 255*i/ITERATIONS;
            }
        }
        return 0;
    }
    
    public void plotMandelbrot(){
        // Designed to avoid floating point accumulation
        double stepSizeX = (xEnd - xOrigin) / imageWidth;
        double stepSizeY = (yEnd - yOrigin) / imageHeight;
        System.out.println(stepSizeX + " step " + stepSizeY);
        double x, y;
        int count = 0;
        for(int i = 0; i < imageWidth; i++){
            x = xOrigin + ((double)i * stepSizeX);
            for(int j = 0; j < imageHeight; j++){
                y = yOrigin + ((double)j * stepSizeY);
                // Calculate 
                if(count == 25000){
                    System.out.println("Calculating " + x + ", " + y + " at pixels " + i + ", " + j);
                    count = 0;
                }
                count++;
                int color = calcPoint(x, y);
                plot.setRGB(i, j, color);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(plot, 0, 0, this);
    }
}
