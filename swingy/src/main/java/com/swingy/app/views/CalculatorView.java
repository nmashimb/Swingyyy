package com.swingy.app.views;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalculatorView extends JFrame {
    private JTextField firstNumber = new JtextField(10); //10 wide
    private JLabel additionLabel = new JLabel("+");
    private JTextField secondNumber = new JtextField(10);
    private JButton CalculationButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);

    CalculatorView(){
        JPanel calcPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(CalculationButton);
        calcPanel.add(calcSolution);

        this.add(calcPanel); //this == JFrame
    }

    public int getFirstNumber(){
        return Integer.parseInt(firstNumber.getText());
    }

    public int getSecondNumber(){
        return Integer.parseInt(secondNumber.getText());
    }
    
    public int getCalcSolution(){
        return Integer.parseInt(calcSolution.getText());
    }

    public void setValueSolution(int solution){
        calcSolution.setText(Integer.toString(solution));
    }

    void addCalculationListener(ActionListener listenerForCalcButton){
        CalculationButton.addActionListener(listenerForCalcButton);
    }
}