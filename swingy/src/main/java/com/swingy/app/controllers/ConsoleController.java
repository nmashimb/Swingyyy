package com.swingy.app.controllers;

import com.swingy.app.views.*;
import com.swingy.app.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleController 
{
    private ConsoleView consoleView;
    private Player player;
    private Map map;
    private PrintWriter writer;
    private boolean validClass = false;
    private Scanner in = new Scanner(System.in);
    private FileWriter myWriter;
    private File filename;

    public ConsoleController(Player player, ConsoleView consoleView){
        this.consoleView = consoleView;
        this.player = new Player();

        if (this.consoleView.getStartOption() == 1){
            playerSetup();
        }
        else if (this.consoleView.getStartOption() == 2){
            consoleView.printArgument("SELECTED PREV CREATED HERO");
            previosPlayers();
        }
        else
            consoleView.printArgument("OPTION WRONG!!!");
    }

    public void playerSetup(){
        consoleView.stepOne();
        player.setPlayerName(in.nextLine()); //VALIDATE?
        while (validClass == false){
            consoleView.stepTwo();
            String selectedPlayerClass = in.nextLine();
            try {
                int option = Integer.parseInt(selectedPlayerClass);
                if (option == 1){
                    player.setupGiant();
                    validClass = true;
                }
                else if (option == 2){
                    player.setupAlph();
                    validClass = true;
                }
                else if (option == 3){
                    player.setupWitcher();
                    validClass = true;
                }
                else{
                    consoleView.printArgument("NOT AN OPTION!! TRY AGAIN!!");
                }
            }
            catch(NumberFormatException e){
                consoleView.printArgument("INVALID INPUT!! TRY AGAIN!!\n");
            }
            
        }
        player.playerCurrentStats();
        saveNewPlayer(player.getPlayerClass());
        consoleView.printArgument("\nPRESS ANY KEY TO CONTINUE...\n");
        in.nextLine();
        ////SAVE PLAYER INFORMATION VIA TEXT
        adventure();
    }

    public void previosPlayers(){}

    public void saveNewPlayer(String playerClass){
        
        try{
            filename = new File("players.txt");
            Scanner readPlayerText = new Scanner(filename);
            if (readPlayerText.hasNextLine()) {
                while (readPlayerText.hasNextLine()) {
                    String data = readPlayerText.nextLine();
                    String[] player = data.split(" ");
                    System.out.println(player.length+" "+data);
                    if (player[1].equals(playerClass)){
                        break;
                    }
                }
                //myWriter = new FileWriter(filename);
                myWriter = new FileWriter(filename, true); 
                BufferedWriter bw = new BufferedWriter(myWriter);
                PrintWriter out = new PrintWriter(bw);
                //myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerHP()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()); //add weapon,armor
                out.println(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerHP()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense());
                myWriter.close();
                readPlayerText.close();
            }
            else {
                try {
                    myWriter = new FileWriter(filename);
                    myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerHP()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()); //add weapon,armor
                    myWriter.close();
                    readPlayerText.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            
            
            
            
            /*filename = new File("players.txt");
            if (filename.createNewFile()){
                System.out.println("file created!");
            }
            else
                System.out.println("file already created!");*/

            /*try {
                //myWriter = new FileWriter("players.txt");
                try {
                    Scanner readPlayerText = new Scanner(filename);
                    System.out.println("readPlayerText.nextLine() "+readPlayerText.hasNextLine());
                    if (readPlayerText.hasNextLine()){
                        while (readPlayerText.hasNextLine()) {
                            System.out.println(readPlayerText.nextLine());
                            String data = readPlayerText.nextLine();
                            String[] player = data.split(" ");
                            System.out.println("player 0 "+player[0]);
                            System.out.println(data);
                        }
                    }
                    else {
                        myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerHP()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()); //add weapon,armor
                        myWriter.close();
                        readPlayerText.close();
                    }
                }
                catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }*/
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void adventure(){
        int option;
        boolean validDirection = false;
        map = new Map(player.getPlayerLevel());

        player.setPlayerXPos(map.getDimensions()/2);
        player.setPlayerYPos(map.getDimensions()/2);
        map.placePlayerOnMap(player.getPlayerYPos(), player.getPlayerYPos());
        map.displayMapAndDirections();
        while (validDirection == false){
            String inputString = in.nextLine();
            try{
                option = Integer.parseInt(inputString);
                if (option == 1){
                    consoleView.printArgument("You went North...");
                    if (player.getPlayerXPos() != 0){
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        player.setPlayerXPos(player.getPlayerXPos() - 1);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        map.setMap(player.getPlayerLevel());
                        player.setPlayerXPos(map.getDimensions()/2);
                        player.setPlayerYPos(map.getDimensions()/2);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        consoleView.printArgument("\nLEVEL "+(player.getPlayerLevel() - 1) +" COMPLETE");
                        player.playerCurrentStats();
                        consoleView.printArgument("\nPRESS ANY KEY TO ADVANCE TO LEVEL "+player.getPlayerLevel()+"\n");
                        in.nextLine();
                        map.displayMapAndDirections();
                    }
                }
                else if (option == 2){
                    consoleView.printArgument("You went East...");
                    if (player.getPlayerYPos() != map.getDimensions() - 1){
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        player.setPlayerYPos(player.getPlayerYPos() + 1);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        map.setMap(player.getPlayerLevel());
                        player.setPlayerXPos(map.getDimensions()/2);
                        player.setPlayerYPos(map.getDimensions()/2);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        consoleView.printArgument("\nLEVEL "+(player.getPlayerLevel() - 1) +" COMPLETE");
                        player.playerCurrentStats();
                        consoleView.printArgument("\nPRESS ANY KEY TO ADVANCE TO LEVEL "+player.getPlayerLevel()+"\n");
                        in.nextLine();
                        map.displayMapAndDirections();
                    }
                }
                else if(option == 3){
                    consoleView.printArgument("You went South...");
                    if (player.getPlayerXPos() != map.getDimensions() - 1){
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        player.setPlayerXPos(player.getPlayerXPos() + 1);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        map.setMap(player.getPlayerLevel());
                        player.setPlayerXPos(map.getDimensions()/2);
                        player.setPlayerYPos(map.getDimensions()/2);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        consoleView.printArgument("\nLEVEL "+(player.getPlayerLevel() - 1) +" COMPLETE");
                        player.playerCurrentStats();
                        consoleView.printArgument("\nPRESS ANY KEY TO ADVANCE TO LEVEL "+player.getPlayerLevel()+"\n");
                        in.nextLine();
                        map.displayMapAndDirections();
                    }
                }
                else if(option == 4){
                    consoleView.printArgument("You went West...");
                    if (player.getPlayerYPos() != 0){
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        player.setPlayerYPos(player.getPlayerYPos() - 1);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        map.setMap(player.getPlayerLevel());
                        player.setPlayerXPos(map.getDimensions()/2);
                        player.setPlayerYPos(map.getDimensions()/2);
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        consoleView.printArgument("\nLEVEL "+(player.getPlayerLevel() - 1) +" COMPLETE");
                        player.playerCurrentStats();
                        consoleView.printArgument("\nPRESS ANY KEY TO ADVANCE TO LEVEL "+player.getPlayerLevel()+"\n");
                        in.nextLine();
                        map.displayMapAndDirections();
                    }
                }
                else
                    consoleView.printArgument("INVALID INPUT!! TRY AGAIN!!\n");
            }
            catch(NumberFormatException e){
                consoleView.printArgument("INVALID INPUT!! TRY AGAIN!!\n");
            }
        }
    }
}