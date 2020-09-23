package com.swingy.app.views;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuiView extends JFrame
{
   /*  private JTextArea firstNumber = new JTextArea("1"); //10 wide
    private JLabel additionLabel = new JLabel("+");
    private JTextField secondNumber = new JTextField(10);
    private JButton calculateButton = new JButton("Calculate");
    private JTextField calcSolution = new JTextField(10);

    public GuiView(){
       JPanel calcPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);

        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calculateButton);
        calcPanel.add(calcSolution);

        this.add(calcPanel);
        this.setVisible(true);
    }*/
    private JTextArea startGameOptions;
    private JButton createHeroButton;
    private JButton previosHeroButton;

    public GuiView(){
        JPanel startGamePanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);

        startGameOptions = new JTextArea("1. Create a hero\n2. Select a previously created hero.");
        startGameOptions.setEditable(false);
        createHeroButton = new JButton("1");
        previosHeroButton = new JButton("2");

        startGamePanel.add(startGameOptions);
        startGamePanel.add(createHeroButton);
        startGamePanel.add(previosHeroButton);

        this.add(startGamePanel);
        this.setVisible(true);
    }

    public void addOptionOneListener(ActionListener listenForOptionOneButton){
        createHeroButton.addActionListener(listenForOptionOneButton);
    }

    public void addOptionTwoListener(ActionListener listenForOptionTwoButton){
        previosHeroButton.addActionListener(listenForOptionTwoButton);
    }
}