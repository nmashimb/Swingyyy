package com.swingy.app.controllers;
import com.swingy.app.views.*;
import com.swingy.app.models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//the controller knows about the view

public class CalculatorController {
    private CalculatorView theView;
    private CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel){
        this.theView = theView;
        this.theModel = theModel;
        //the the view, to execute action when button clicked


       this.theView.addCalculateListener(new CalculateListener());
    }
    
    class CalculateListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int firstNumber, secondNumber = 0;

            try {
                firstNumber = theView.getFirstNumber();
                secondNumber = theView.getSecondNumber();

                theModel.addTwoNumbers(firstNumber, secondNumber);
                System.out.println(firstNumber+" : "+secondNumber+" = "+theModel.getCalculationValue());
                theView.setCalcSolution(theModel.getCalculationValue());
            }
            catch(NumberFormatException ex){
                System.out.println("error");
            }
        }
    }
}