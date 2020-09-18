package com.swingy.app;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
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
    Container cont;

    public App(){
        window = new JFrame();
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //adds close option on window layout
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null); //making default layout null so we can make our own
        window.setVisible(true); //makes the window we created appear
        con.window.getContentPane()
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
            new App();
            System.out.println("gui ");
        }
        else{
            return;
        }
    }
}
