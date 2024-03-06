package UI;
import Fractals.Fractal;
import Fractals.FractalType;

import javax.swing.JPanel;


import Fractals.Mandelbrot;
public class FractPanel extends JPanel{
    private Fractal fract;
    

    public void setFractal(FractalType type){
        if(fract != null){
            remove(fract);
        }
        switch(type){
            case MANDELBROT:
                fract = new Mandelbrot(-2, 1, 1, -1,  2, false);
                add(fract);
                break;
            case MANDELBAR:
                fract = new Mandelbrot(-2, 1, 1.5, -1.5, 2, true);
                add(fract);
        }
    }
    public void enableZoom(double percentMagnify){
        if(fract != null){
            fract.enableZoom(percentMagnify);
        }
        
    }
    public void renderImage(){
        if(fract != null){
            revalidate();
            fract.scaleImage(getWidth(), getHeight());
            fract.render();
        }
    }

    
}
