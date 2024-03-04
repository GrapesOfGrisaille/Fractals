package UI;
import Fractals.Fractal;
import Fractals.FractalType;

import javax.swing.JPanel;

import Fractals.Mandelbrot;
public class FractPanel extends JPanel {
    private Fractal fract;
    public void setFractal(FractalType type){
        if(fract != null){
            remove(fract);
        }
        switch(type){
            case MANDELBROT:
                fract = new Mandelbrot(-2, 1, 1, -1, getWidth(), getHeight(), 2, false);
                add(fract);
                break;
            case MANDELBAR:
                fract = new Mandelbrot(-2, 1, 1, -1, getWidth(), getHeight(), 2, true);
                add(fract);
        }
    }
    public void renderImage(){
        fract.scaleImage(getWidth(), getHeight());
        fract.render();
    }
}
