import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ControlPanel extends JPanel implements ActionListener{
    private JButton mandelButton;
    public ControlPanel(){
        mandelButton = new JButton("Mandelbrot");

        add(mandelButton);

        mandelButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == mandelButton){
            Runner.getInstance().changeState(FractalType.MANDELBROT);
        }
    }
}
