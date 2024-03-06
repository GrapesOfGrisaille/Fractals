package UI;
import javax.swing.JFrame;

import Fractals.FractalType;

import java.awt.BorderLayout;

public class Runner extends JFrame{
    private FractalType currentState;
    private ControlPanel cPan;
    private FractPanel fPan;
    
    public static Runner controlInstance = new Runner();
    public static Runner getInstance(){
        return controlInstance;
    }

    private Runner(){
        super("Fractal Painter");
        setSize(700, 500);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fPan = new FractPanel();
        cPan = new ControlPanel();
        add(cPan, BorderLayout.SOUTH);
        add(fPan, BorderLayout.CENTER);
        
        setVisible(true);
    }
    public void changeState(FractalType type){
        currentState = type;
        fPan.setFractal(type);
    }
    
    public void passAndRender(){
        fPan.renderImage();
        revalidate();
        repaint();
    }

    public void setZoom(){
        fPan.enableZoom(0.1);
    }
    
    public static void main(String[] args){
        Runner run = Runner.getInstance();
    }
}

