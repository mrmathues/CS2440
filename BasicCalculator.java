package apps;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator{

    private static final int X_LOC = 100;
    private static final int Y_LOC = 100;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String NAME = "Max's Calculator";
    private static final String RESULT_PREAMBLE = "Result = ";
    private static final String ERROR_MESSAGE = "Error";
    private JFrame frame;
    private JTextField leftOpField;
    private JTextField rightOpField;
    private JLabel resultLabel;
    
    public Calculator(){
        createFrame();
        initializeComponents();
        displayFrame();
    }

    public JFrame getFrame(){
        return this.frame;
    }

    private void createFrame(){
        this.frame = new JFrame();
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocation(X_LOC,Y_LOC);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(NAME);
    }

    private void initializeComponents(){
        initializeButtons();
        initializeInputs();
        initializeResults();
    }

    private void displayFrame(){
        frame.setVisible(true);
    }

    private void initializeInputs(){
        JPanel panel1 = new JPanel();
        leftOpField = new JTextField(10);
        leftOpField.setName("leftOperand");
        panel1.add(leftOpField);
        rightOpField = new JTextField(10);
        rightOpField.setName("rightOperand");
        panel1.add(rightOpField);

        frame.add(panel1, BorderLayout.NORTH);
    }

    private void initializeResults(){
        JPanel panel2 = new JPanel();
        resultLabel = new JLabel(RESULT_PREAMBLE);
        resultLabel.setName("resultLabel");
        panel2.add(resultLabel);

        frame.add(panel2, BorderLayout.CENTER);
    }

    private void initializeButtons(){
        JPanel panel3 = new JPanel();
        JButton addButton = new JButton("ADD");
        JButton subButton = new JButton("SUB");
        JButton multButton = new JButton("MULT");
        JButton divButton = new JButton("DIV");

        addButton.setName("addButton");
        subButton.setName("subButton");
        multButton.setName("multButton");
        divButton.setName("divButton");

        panel3.add(addButton);
        panel3.add(subButton);
        panel3.add(multButton);
        panel3.add(divButton);

        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateResult(getLeftNum() + getRightNum());
            }
        });

        subButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateResult(getLeftNum() - getRightNum());
            }
        });

        multButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateResult(getLeftNum() * getRightNum());
            }
        });

        divButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(getRightNum() == 0){
                    updateResult(Double.NaN);
                }
                else{
                    updateResult(getLeftNum() / getRightNum());
                }
            }
        });
        frame.add(panel3, BorderLayout.SOUTH);
    }

    private double getLeftNum(){
        return Double.parseDouble(leftOpField.getText());

    }

    private double getRightNum(){
        return Double.parseDouble(rightOpField.getText());
    }

    private void updateResult(double result){
        if(Double.isNaN(result)){
            resultLabel.setText(RESULT_PREAMBLE + ERROR_MESSAGE);
        }
        else{
            resultLabel.setText(RESULT_PREAMBLE + result);
        }
    }
}