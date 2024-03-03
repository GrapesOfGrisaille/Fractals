import javax.swing.JPanel;
public class FractPanel extends JPanel {
    private Mandelbrot mandel;
    public void setFractal(FractalType type){
        switch(type){
            case MANDELBROT:
                mandel = new Mandelbrot(-2, 1, 1, -1, getWidth(), getHeight());
                add(mandel);
                break;
        }
    }
}
