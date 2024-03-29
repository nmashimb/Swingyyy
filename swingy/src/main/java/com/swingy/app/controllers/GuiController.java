package com.swingy.app.controllers;

import com.swingy.app.views.*;
import com.swingy.app.models.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class GuiController
{
    private GuiView guiView;
    private Player player;
    private Map map;
    private Scanner in = new Scanner(System.in);
    private FileWriter myWriter;
    private File filename;

    public GuiController(Player player, GuiView guiView){
        this.player = player;
        this.guiView = guiView;
        map = new Map(player.getPlayerLevel());
        this.guiView.addOptionOneListener(new CreateHeroButtonListener());
        this.guiView.addOptionTwoListener(new SelectHeroButtonListener());
    }

    public void playerSetup(){
        guiView.stepOne();
        guiView.addNameButtonListener(new CreateHeroNameButtonListener());
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
                    myWriter.write(player.getPlayerName()+" "+player.getPlayerClass()+" "+player.getPlayerLevel()+" "+player.getPlayerExperiance()+" "+player.getPlayerAttack()+" "+player.getPlayerDefense()+" "+player.getPlayerHP()+"\n"); //add weapon,armor
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

    public void previosPlayers(){
        try{
            filename = new File("players.txt");
            Scanner readPlayerText = new Scanner(filename);
            int count = 0;
            if (!readPlayerText.hasNextLine()){
                playerSetup();
            }
           String playersList = "";
            while (readPlayerText.hasNextLine()) {
                count++;
                playersList = playersList+count+". "+readPlayerText.nextLine()+"\n";
            }
            guiView.displayPlayers(playersList);
            guiView.addChoosePlayerButtonListener(new CreateChoosePlayerButtonListener());
        }
        catch (IOException e){
            e.printStackTrace();
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

   

    //IMPLEMENT BUTTONS
    class CreateHeroButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            playerSetup();
        }
    }

    class SelectHeroButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            System.out.println("create file incase doesnt exist!");
            previosPlayers();
        }
    }

    class CreateChoosePlayerButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            String[] playersList = guiView.getPreviosPlayers().getText().split("\n");
            int playerCount = playersList.length;
            try {
                int option = Integer.parseInt(guiView.getHeroOption());
                if (option >= 1 && option <= playerCount){
                    for (int i = 1; i <= playerCount;i++) {

                        if (option == i) {
                            String playerClass = playersList[i - 1].split(" ")[2].toLowerCase();
                            player.setPlayerName(playersList[i - 1].split(" ")[1].toLowerCase());
                            player.setPlayerLevel(Integer.parseInt(playersList[i - 1].split(" ")[3]));
                            player.setPlayerExperiance(player.getPlayerLevel() - 1);
                            player.setPlayerAttack(Integer.parseInt(playersList[i - 1].split(" ")[5]));
                            player.setPlayerDefense(Integer.parseInt(playersList[i - 1].split(" ")[6]));
                            player.setPlayerHP(Integer.parseInt(playersList[i - 1].split(" ")[7]));
                            if (playerClass.matches("giant")){
                                player.setPlayerClass(playerClass);
                            }
                            if (playerClass.matches("witcher")){
                                player.setPlayerClass(playerClass);
                            }
                            if (playerClass.matches("alph")){
                                player.setPlayerClass(playerClass);
                            }
                            guiView.getpreviousPlayersPanel().setVisible(false);
                            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
                            guiView.addPlayerStatsTextAreaToPanel();
                            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
                        }
                    }
                    if (!(option >= 1 && option <= playerCount)){
                        //invalid player
                        guiView.getpreviousPlayersPanel().setVisible(false);
                        guiView.displayPlayers(guiView.getPreviosPlayers().getText());
                        guiView.addChoosePlayerButtonListener(new CreateChoosePlayerButtonListener());
                    }
                }
            }
            catch (NumberFormatException ee){
                guiView.getpreviousPlayersPanel().setVisible(false);
                guiView.displayPlayers(guiView.getPreviosPlayers().getText());
                guiView.addChoosePlayerButtonListener(new CreateChoosePlayerButtonListener());
            }
        }
    }

    class CreateHeroNameButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            
            if (!guiView.getPlayerNameField().isEmpty()){
                player.setPlayerName(guiView.getPlayerNameField());
                guiView.stepTwo();
                guiView.addGiantButtonListener(new GiantClassButtonListener());
                guiView.addAlphButtonListener(new AlphClassButtonListener());
                guiView.addWitcherButtonListener(new WitcherClassButtonListener());
            }
            else
                guiView.stepOne();
        }
    }

    class GiantClassButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setupGiant();
            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
            guiView.addPlayerStatsTextAreaToPanel();
            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            saveNewPlayer();
        }
    }

    class AlphClassButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setupAlph();
            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
            guiView.addPlayerStatsTextAreaToPanel();
            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            saveNewPlayer();
        }
    }

    class WitcherClassButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setupWitcher();
            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
            guiView.addPlayerStatsTextAreaToPanel();
            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            saveNewPlayer();
        }
    }

    class ContinueGameButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            guiView.getPlayerStatsPanel().setVisible(false);
            map.setMap(player.getPlayerLevel());
            player.setPlayerXPos(map.getDimensions()/2);
            player.setPlayerYPos(map.getDimensions()/2);
            map.placePlayerOnMap(player.getPlayerYPos(), player.getPlayerYPos());
            putVillainsOnMap();

            guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
            guiView.addGoNorthButtonListener(new GoNorthButtonListener());
            guiView.addGoEastButtonListener(new GoEastButtonListener());
            guiView.addGoSouthButtonListener(new GoSouthButtonListener());
            guiView.addGoWestButtonListener(new GoWestButtonListener());
        }
    }

    public void encounteredVillain(){
            guiView.getMapPanel().setVisible(false);
            guiView.addFightOrRunToPanel();
            guiView.addFightButtonListener(new FightButtonListenr());
            guiView.addRunButtonListener(new RunButtonListener());
            System.out.println("Encountered a monster!\n1. Fight\n2. Run");
  
    }

    public boolean fightVillain(){
        int villainHP = player.getPlayerHP() - 5;
        while (villainHP > 0){
            int attacker = ((int)Math.round(Math.random() * (1)));
            
            if (attacker == 0){ //villain attacking
                int attackerLevel = ((int)Math.round(Math.random() * (2)));
                if (attackerLevel == 0){
                    player.setPlayerHP(player.getPlayerHP() - player.getPlayerLevel());
                }
                else if (attackerLevel == 1){
                    player.setPlayerHP(player.getPlayerHP() - (player.getPlayerLevel() + 2));
                }
                else if (attackerLevel == 2){
                    player.setPlayerHP(player.getPlayerHP() - (player.getPlayerLevel() + 3));
                }
            }
            else if (attacker == 1){ //player attacking
                int attackerLevel = ((int)Math.round(Math.random() * (2)));
                if (attackerLevel == 0){
                    villainHP = villainHP - player.getPlayerLevel();
                }
                else if (attackerLevel == 1){
                    villainHP = villainHP - (player.getPlayerLevel() + 1);
                }
                else if (attackerLevel == 2){
                    villainHP = villainHP - (player.getPlayerLevel() + 2);
                }
            }
        }
        if (player.getPlayerHP() > 0){
            //increase player stats
            int gift = ((int)Math.round(Math.random() * (2)));
            int villainLevel = ((int)Math.round(Math.random() * (1)));
            if (gift == 0 && villainLevel == 1){
                player.setPlayerAttack(player.getPlayerAttack() + 2);
            }
            else if (gift == 1 && villainLevel == 1){
                player.setPlayerDefense(player.getPlayerDefense() + 2);
            }
            else if (gift == 2 && villainLevel == 1){
                player.setPlayerHP(player.getPlayerHP() + 5);
            }
            return true;
        }
        else
            return false;
    }

    class FightButtonListenr implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            System.out.println("fight");
            map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
            if (fightVillain()){
                guiView.getFightOrRunPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
        
                guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                guiView.addGoEastButtonListener(new GoEastButtonListener());
                guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                guiView.addGoWestButtonListener(new GoWestButtonListener());
            }
            else {
                guiView.getFightOrRunPanel().setVisible(false);
                guiView.getMapPanel().setVisible(false);
                guiView.gameOver();
                System.out.println("Game Over from fight! "+player.getPlayerHP());
            }
        }
    }

    class RunButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            System.out.println("run");
            boolean oddValid = false;
            int odds = 0;
            while (oddValid == false){
                odds = ((int)Math.round(Math.random() * (2)));
                if (odds == 1)
                    oddValid = true;
                else if (odds == 2)
                    oddValid = true;
            }
            if (odds == 1){ //run
                player.setPlayerXPos(player.getPreviousXposition());
                player.setPlayerYPos(player.getPreviousYposition());
                map.placePlayerOnMap(player.getPreviousXposition(), player.getPreviousYposition());
                guiView.getFightOrRunPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
        
                guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                guiView.addGoEastButtonListener(new GoEastButtonListener());
                guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                guiView.addGoWestButtonListener(new GoWestButtonListener());
                System.out.println("ran!");
            }
            else { ///fight
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                System.out.println("no choice, fight!");
                if (fightVillain()){
                    guiView.getFightOrRunPanel().setVisible(false);
                    guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
            
                    guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                    guiView.addGoEastButtonListener(new GoEastButtonListener());
                    guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                    guiView.addGoWestButtonListener(new GoWestButtonListener());
                }
                else {
                    guiView.getFightOrRunPanel().setVisible(false);
                    guiView.getMapPanel().setVisible(false);
                    guiView.gameOver();
                    System.out.println("Game Over from run! "+player.getPlayerHP());
                }
            }
        }
    } 

    class GoNorthButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setPreviousXposition(player.getPlayerXPos());
            player.setPreviousYposition(player.getPlayerYPos());

            if (player.getPlayerXPos() != 0){
                map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                player.setPlayerXPos(player.getPlayerXPos() - 1);
                if (map.spotOccuppied(player.getPlayerXPos(), player.getPlayerYPos()))
                    encounteredVillain();
                else{
                    map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                    guiView.getMapPanel().setVisible(false);
                    guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
                
                    guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                    guiView.addGoEastButtonListener(new GoEastButtonListener());
                    guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                    guiView.addGoWestButtonListener(new GoWestButtonListener());
                } 
            }
            else {
                player.setPlayerExperiance(player.getPlayerLevel());
                player.setPlayerLevel(player.getPlayerLevel() + 1);

                updatTextFile();
                map.setMap(player.getPlayerLevel());
                player.setPlayerXPos(map.getDimensions()/2);
                player.setPlayerYPos(map.getDimensions()/2);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
                guiView.addPlayerStatsTextAreaToPanel();
                guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            }
        }
    }

    class GoEastButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setPreviousXposition(player.getPlayerXPos());
            player.setPreviousYposition(player.getPlayerYPos());

            if (player.getPlayerYPos() != map.getDimensions() - 1){
                map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                player.setPlayerYPos(player.getPlayerYPos() + 1);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                guiView.addGoEastButtonListener(new GoEastButtonListener());
                guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                guiView.addGoWestButtonListener(new GoWestButtonListener());
            }
            else {
                player.setPlayerExperiance(player.getPlayerLevel());
                player.setPlayerLevel(player.getPlayerLevel() + 1);
                
                updatTextFile();
                map.setMap(player.getPlayerLevel());
                player.setPlayerXPos(map.getDimensions()/2);
                player.setPlayerYPos(map.getDimensions()/2);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
                guiView.addPlayerStatsTextAreaToPanel();
                guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            }
        }
    }

    class GoSouthButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setPreviousXposition(player.getPlayerXPos());
            player.setPreviousYposition(player.getPlayerYPos());
            if (player.getPlayerXPos() != map.getDimensions() - 1){
                map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                player.setPlayerXPos(player.getPlayerXPos() + 1);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                guiView.addGoEastButtonListener(new GoEastButtonListener());
                guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                guiView.addGoWestButtonListener(new GoWestButtonListener());
            }
            else {
                player.setPlayerExperiance(player.getPlayerLevel());
                player.setPlayerLevel(player.getPlayerLevel() + 1);
                
                updatTextFile();
                map.setMap(player.getPlayerLevel());
                player.setPlayerXPos(map.getDimensions()/2);
                player.setPlayerYPos(map.getDimensions()/2);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
                guiView.addPlayerStatsTextAreaToPanel();
                guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            }
        }
    }

    class GoWestButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setPreviousXposition(player.getPlayerXPos());
            player.setPreviousYposition(player.getPlayerYPos());
            
            if (player.getPlayerYPos() != 0){
                map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                player.setPlayerYPos(player.getPlayerYPos() - 1);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.addGoNorthButtonListener(new GoNorthButtonListener());
                guiView.addGoEastButtonListener(new GoEastButtonListener());
                guiView.addGoSouthButtonListener(new GoSouthButtonListener());
                guiView.addGoWestButtonListener(new GoWestButtonListener());
            }
            else {
                player.setPlayerExperiance(player.getPlayerLevel());
                player.setPlayerLevel(player.getPlayerLevel() + 1);
                
                updatTextFile();
                map.setMap(player.getPlayerLevel());
                player.setPlayerXPos(map.getDimensions()/2);
                player.setPlayerYPos(map.getDimensions()/2);
                map.placePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                guiView.getMapPanel().setVisible(false);
                guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());

                guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
                guiView.addPlayerStatsTextAreaToPanel();
                guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            }
        }
    }
}

