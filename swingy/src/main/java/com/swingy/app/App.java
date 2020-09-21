package com.swingy.app;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
 /*public class PlayerSetup {
    private int playerHP;
    private String weapon;
    private String name;
    //private int level;
    //private int experiance;
    //private int attack;
    //private int defense;
    //private String class;
    //PlayerSetup(){}

    //setPlayerHP(){}
 }*/

public class App
{
    JFrame window; 
    JPanel maintextPanel,optionPanel;
    JButton optionOneButton,optionTwoButton;
    JTextArea textArea;
    Container con;

    public App(){}

    public void createGameScreen() //change name
    { 
        JFrame window = new JFrame("SWINGY");

        JTextArea textArea = new JTextArea("1. Create a hero\n2. Select a previously created hero.");  
        textArea.setBounds(50,10, 290,250);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        window.add(textArea);

        JButton optionOne = new JButton("1");
        JButton optionTwo = new JButton("2");
        optionOne.setBounds(130, 300, 100, 30);
        optionTwo.setBounds(130, 350, 100, 30);

        optionOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("option two selected");
            }
        });
        optionTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("option one selected");
            }
        });

        window.add(optionOne);window.add(optionTwo);
        window.setSize(400, 400);
        window.setLayout(null);
        window.setVisible(true);
    }

    public static void main( String[] args )
    { 
        Scanner in = new Scanner(System.in);
        String numInput;
        int num = 0;
        boolean valid = false;
        if (args[0].matches("console")){
            System.out.println("WELCOME TO SWINGY\n");
            while (valid == false){
                System.out.println("1. Create new hero");
                //show if available
                System.out.println("2. Select previously created hero");
                numInput = in.nextLine();
                try {
                    num = Integer.parseInt(numInput);
                    valid = true;
                }catch(NumberFormatException e){
                    System.out.println("Wrong iput, try again!!\n");
                }
            }
            System.out.println("You entered: "+num);
        }
        else if (args[0].matches("gui")){
            System.out.println("gui option");
            App game = new App();
            game.createGameScreen();
        }
        else{
            return;
        }
    }
}
