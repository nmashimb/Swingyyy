package com.swingy.app.controllers;

import com.swingy.app.views.*;
import com.swingy.app.models.*;

import java.io.File;
import java.io.IOException;
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
        saveNewPlayer();
        consoleView.printArgument("\nPRESS ANY KEY TO CONTINUE...\n");
        in.nextLine();
        ////SAVE PLAYER INFORMATION VIA TEXT
        adventure();
    }

    public void previosPlayers(){}

    public void saveNewPlayer(){
        
        try{
            File filename = new File("players.txt");
            if (filename.createNewFile()){
                System.out.println("file created!");
            }
            else
                System.out.println("file already created!");
            writer = new PrintWriter(filename);
            //writer.println(""+player.getPlayerName()+" "+player.getPlayerClass() +" "+ player.getPlayerLevel() +" "+player.getPlayerHP()+" "+ player.getPlayerExperiance()+"");
            writer.print("x");
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