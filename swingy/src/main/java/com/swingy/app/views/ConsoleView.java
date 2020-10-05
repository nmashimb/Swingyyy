package com.swingy.app.views;
import java.util.Scanner;

public class ConsoleView {
    private boolean validInput = false;
    private Scanner in = new Scanner(System.in);
    private String numInput;
    private int startOption;
    
    public ConsoleView(){
        System.out.println("WELCOME TO SWINGY\n");
        while (validInput == false){
            System.out.println("1. Create new hero");
            System.out.println("2. Select previously created hero"); //show if available
            numInput = in.nextLine();
            try {
                
                if (Integer.parseInt(numInput) == 1){
                    startOption = Integer.parseInt(numInput);
                    validInput = true;
                }
                else if (Integer.parseInt(numInput) == 2){
                    startOption = Integer.parseInt(numInput);
                    validInput = true;
                }
                else{
                    System.out.println("NOT AN OPTION!! TRY AGAIN!!");
                }
            }catch(NumberFormatException e){
                System.out.println("INVALID INPUT, TRY AGAIN!!\n");
            }
        }
    }

    
    public int getStartOption(){
        return this.startOption;
    }

    public void stepOne(){
        System.out.println("Enter player name:");
    }

    public void stepTwo(){
        System.out.println("Select hero class:\n1. Giant\n2. Alph\n3. Witcher");
    }

    public void printArgument(String stringToPrint){
        System.out.println("\n"+stringToPrint+"\n");
    }
    public void printArgumentNoNewLine(String stringToPrint){
        System.out.println(stringToPrint);
    }
}