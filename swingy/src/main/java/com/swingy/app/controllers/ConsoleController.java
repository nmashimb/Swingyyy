package com.swingy.app.controllers;

import com.swingy.app.views.*;
import com.swingy.app.models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleController 
{
    private ConsoleView consoleView;
    private Player player;
    private Map map;
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
        adventure();
    }

    public void previosPlayers(){
        try{
            filename = new File("players.txt");
            boolean exists = false;
            Scanner readPlayerText = new Scanner(filename);
            int count = 0;
            if (!readPlayerText.hasNextLine()){
                consoleView.printArgument("NO PREVIOUS PLAYERS CREATED!!");
                consoleView.printArgument("PRESS ANY KEY TO CONTINUE!");
                in.nextLine();
                playerSetup();
            }
            while (readPlayerText.hasNextLine()) {
                count++;
                consoleView.printArgumentNoNewLine(count+". "+readPlayerText.nextLine());
            }
            boolean input = false;
            while (input == false){
                try {
                    int option = Integer.parseInt(in.nextLine());
                    if (option >= 1 && option <= count){
                        count = 0;
                        Scanner readPlayer = new Scanner(filename);
                        String playerString = null;
                        String correctPlayer = null;
                        while (readPlayer.hasNextLine()) {
                            count++;
                            playerString = readPlayer.nextLine();
                            if (option == count){
                                correctPlayer = playerString;
                                break;
                            }
                        }
                        if (correctPlayer != null){
                        String[] playerArray = correctPlayer.split(" ");
                        setupPreviousPlayer(playerArray);
                        }
                        input = true;
                    }
                    else{
                        consoleView.printArgument("NOT AN OPTION!! TRY AGAIN!!");
                    }

                }
                catch(NumberFormatException e){
                    consoleView.printArgument("INVALID INPUT!! TRY AGAIN!!\n");
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setupPreviousPlayer(String[] playerArray){
        player.setPlayerName(playerArray[0].trim());
        player.setPlayerLevel(Integer.parseInt(playerArray[2].trim()));
        player.setPlayerExperiance(player.getPlayerLevel() - 1);
        player.setPlayerAttack(Integer.parseInt(playerArray[4].trim()));
        player.setPlayerDefense(Integer.parseInt(playerArray[5].trim()));
        player.setPlayerHP(Integer.parseInt(playerArray[6].trim()));
    
        String playerClass = playerArray[1].trim().toLowerCase();
        if (playerClass.toLowerCase().matches("giant")){
            player.setPlayerClass(playerClass);
        }
        else if (playerClass.toLowerCase().matches("witcher")){
            player.setPlayerClass(playerClass);
        }
        else if (playerClass.toLowerCase().matches("alph")){
            player.setPlayerClass(playerClass);
        }
        else {
            previosPlayers();
        }
        player.playerCurrentStats();
        consoleView.printArgument("\nPRESS ANY KEY TO CONTINUE...\n");
        in.nextLine();
        adventure();
    }

    public void saveNewPlayer(){
        
        try{
            filename = new File("players.txt");
            boolean exists = false;
            Scanner readPlayerText = new Scanner(filename);
            if (readPlayerText.hasNextLine()) {
                while (readPlayerText.hasNextLine()) {
                    String data = readPlayerText.nextLine();
                    String[] player = data.split(" ");
                    if (player[0].equals(this.player.getPlayerName()) && player[1].equals(this.player.getPlayerClass())){
                        exists = true;
                        break;
                    }
                }
                if (exists == false){
                    myWriter = new FileWriter(filename, true); 
                    myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()+" "+player.getPlayerHP()+"\n");
                    myWriter.close();
                    readPlayerText.close();
                }
            }
            else {
                try {
                    myWriter = new FileWriter(filename);
                    myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+"\n"); //add weapon,armor
                    myWriter.close();
                    readPlayerText.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void fight(){
        int villainHP = player.getPlayerHP() - 5;
        while (villainHP > 0){
            int attacker = ((int)Math.round(Math.random() * (1)));
            if (attacker == 1){
                player.setPlayerHP(player.getPlayerHP() - player.getPlayerLevel());
            }
            else if (attacker == 1){
                villainHP = villainHP - 3;
            }
        }
        if (player.getPlayerHP() > 0){
            int gift = ((int)Math.round(Math.random() * (2)));
            if (gift == 0){ //helm
                player.setPlayerHP(player.getPlayerHP() + 5);
            }
            else if (gift == 1){ //weapon
                player.setPlayerAttack(player.getPlayerAttack() + 2);
            }
            else if (gift == 2){ //armor
                player.setPlayerDefense(player.getPlayerDefense() + 2);
            }
        }
        else {
            System.out.println("game over");
            return;
        }

    }

    public void encounteredVillain(int previousX, int previousY){
        if (map.spotOccuppied(player.getPlayerXPos(), player.getPlayerYPos())){
            boolean choiceValid = false;
            while (choiceValid == false){
                consoleView.printArgument("Encountered a monster!\n1. Fight\n2. Run");
                String choiceString = in.nextLine();
                try{
                    int choice = Integer.parseInt(choiceString);
                    if (choice == 1){ //fight
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        fight();
                        if (player.getPlayerHP() <= 0){
                            System.out.println("game over!");
                            return;
                        }
                        System.out.println("fight!");
                        choiceValid = true;
                    }
                    else if (choice == 2){ //run
                        boolean oddValid = false;
                        int odds = 0;
                        while (oddValid == false){
                            odds = ((int)Math.round(Math.random() * (2)));
                            if (odds == 1)
                                oddValid = true;
                            else if (odds == 2)
                                oddValid = true;
                        }
                        if (odds == 1 || odds == 0){
                            player.setPlayerXPos(previousX);
                            player.setPlayerYPos(previousY);
                            map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                            choiceValid = true;
                        }
                        else { ///fight
                            map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                            map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                            System.out.println("no choice, fight!");
                            choiceValid = true;
                        }
                    }
                    else{
                        consoleView.printArgument("NOT AN OPTION!! TRY AGAIN!!");
                    }
                }
                catch(NumberFormatException e){
                    consoleView.printArgument("INVALID INPUT!! TRY AGAIN!!\n");
                }
            }
        }
        else{
            map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
        }
    }

    public void adventure(){
        int option;
        boolean validDirection = false;
        map = new Map(player.getPlayerLevel());

        player.setPlayerXPos(map.getDimensions()/2);
        player.setPlayerYPos(map.getDimensions()/2);
        map.placePlayerOnMap(player.getPlayerYPos(), player.getPlayerYPos());
        putVillainsOnMap();
        map.displayMapAndDirections();

        while (validDirection == false){
            String inputString = in.nextLine();
            try{
                option = Integer.parseInt(inputString);
                int previousX = player.getPlayerXPos();
                int previousY = player.getPlayerYPos();
                if (option == 1){
                    consoleView.printArgument("You went North...");
                    if (player.getPlayerXPos() != 0){
                        map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                        player.setPlayerXPos(player.getPlayerXPos() - 1);
                        encounteredVillain(previousX, previousY);
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        
                        updatTextFile();
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
                        encounteredVillain(previousX, previousY);
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        
                        updatTextFile();
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
                        encounteredVillain(previousX, previousY);
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);

                        updatTextFile();
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
                        encounteredVillain(previousX, previousY);
                        map.displayMapAndDirections();
                    }
                    else {
                        player.setPlayerExperiance(player.getPlayerLevel());
                        player.setPlayerLevel(player.getPlayerLevel() + 1);
                        
                        updatTextFile();
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

    public void putVillainsOnMap(){
        int villains = map.getDimensions() + 1;
        for (int i = 2; i <= villains; i++){
            Player newVillain = new Player();
            newVillain.setPlayerName(i+"");
            newVillain.setPlayerHP(player.getPlayerHP() - 5);
            int x = (int)Math.round(Math.random() * (map.getDimensions() - 1));
            int y = (int)Math.round(Math.random() * (map.getDimensions() - 1));
            newVillain.setPlayerXPos(x);
            newVillain.setPlayerYPos(y);
            map.placeVillainOnMap(x, y, i);
        }
    }

    public void updatTextFile(){
        try{
            filename = new File("players.txt");
            Scanner readPlayerText = new Scanner(filename);
            String playerList = "";
            while (readPlayerText.hasNextLine()) {
                playerList = playerList + readPlayerText.nextLine()+"\n";
            }
            readPlayerText.close();
            String[] playerString = playerList.split("\n");
            String name = player.getPlayerName().trim().toLowerCase();
            String classs = player.getPlayerClass().trim().toLowerCase();
            for (int i = 0; i < playerString.length;i++){
                String nameTxt = playerString[i].split(" ")[0].trim().toLowerCase();
                String classTxt = playerString[i].split(" ")[1].trim().toLowerCase();
                if (name.matches(nameTxt) && classs.matches(classTxt)){
                    playerString[i] = player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()+" "+player.getPlayerHP();
                    myWriter = new FileWriter(filename); 
                    for (int j = 0; j < playerString.length;j++){
                        myWriter.write(playerString[j]+"\n");
                    }
                    myWriter.close();
                }
            }
        }
        catch (IOException ea){
            ea.printStackTrace();
        }
    }
}