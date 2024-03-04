package UI;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Fractals.FractalType;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;

public class ControlPanel extends JPanel implements ActionListener{
    private JButton mandelbrotButton;
    private JButton mandelbarButton;
    private JTextField exponentField;
    private JButton renderButton;
    private JButton zoomButton;
    public ControlPanel(){
        setLayout(new GridLayout(1,2));

        JPanel fractSelectPanel = new JPanel();
        JPanel variablePanel = new JPanel();

        // fractSelect Panel components to choose the graph
        mandelbrotButton = new JButton("Mandelbrot");
        mandelbarButton = new JButton("Multicorn");

        fractSelectPanel.add(mandelbrotButton);
        fractSelectPanel.add(mandelbarButton);

        mandelbrotButton.addActionListener(this);
        mandelbarButton.addActionListener(this);
        // variablePanel components to adjust graph values and zoom
        exponentField = new JTextField(1);
        zoomButton = new JButton("Zoom Mode");
        renderButton = new JButton("Render");

        variablePanel.add(new JLabel("Exponent"));
        variablePanel.add(exponentField);
        variablePanel.add(zoomButton);
        variablePanel.add(renderButton);

        fractSelectPanel.setBorder(new TitledBorder(new EtchedBorder(), "Select Function"));
        variablePanel.setBorder(new TitledBorder(new EtchedBorder(), "Adjust and Render"));

        add(fractSelectPanel);
        add(variablePanel);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == mandelbrotButton){
            Runner.getInstance().changeState(FractalType.MANDELBROT);
        }
        else if(e.getSource() == mandelbarButton){
            Runner.getInstance().changeState(FractalType.MANDELBAR);
        }
    }
}
