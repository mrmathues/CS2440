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
    private JTextField infixExpression;
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
        initializeInput();
        initializeResult();
    }

    private void displayFrame(){
        frame.setVisible(true);
    }

    private void initializeInput(){
        JPanel panel1 = new JPanel();
        infixExpression = new JTextField(10);
        infixExpression.setName("infixExpression");
        panel1.add(infixExpression);

        frame.add(panel1, BorderLayout.NORTH);
    }

    private void initializeResult(){
        JPanel panel2 = new JPanel();
        resultLabel = new JLabel(RESULT_PREAMBLE);
        resultLabel.setName("resultLabel");
        panel2.add(resultLabel);

        frame.add(panel2, BorderLayout.CENTER);
    }

    private void initializeButtons(){
        JPanel panel3 = new JPanel();
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");

        calculateButton.setName("calculateButton");
        clearButton.setName("clearButton");

        panel3.add(calculateButton);
        panel3.add(clearButton);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateResult(calculate());
            }
        });
        
        clearButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resultLabel.setText("");
                infixExpression.setText("");
            }
        });
        frame.add(panel3, BorderLayout.SOUTH);
    }

    private String calculate(){
        Object str1 = ExpressionEvaluator.evaluate(infixExpression.toString());
        return str1.toString();
    }

    private void updateResult(String result){
        if(result.isEmpty()){
            resultLabel.setText(RESULT_PREAMBLE + ERROR_MESSAGE);
        }
        else{
            resultLabel.setText(RESULT_PREAMBLE + result);
        }
    }
}