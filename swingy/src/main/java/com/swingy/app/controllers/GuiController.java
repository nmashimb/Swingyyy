package com.swingy.app.controllers;
import com.swingy.app.views.*;
import com.swingy.app.models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GuiController
{
    private GuiView guiView;
    private Player player;
    private Map map;

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
            System.out.println("SHOW PREVIOUS PLAYERS!");
        }
    }

    class CreateHeroNameButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            
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
            //SAVE PLAYER INFORMATION VIA TEXT
        }
    }

    class AlphClassButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setupAlph();
            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
            guiView.addPlayerStatsTextAreaToPanel();
            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            //SAVE PLAYER INFORMATION VIA TEXT
        }
    }

    class WitcherClassButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            player.setupWitcher();
            guiView.setPlayerStatsTextArea(player.getCurrentPlayerStats());
            guiView.addPlayerStatsTextAreaToPanel();
            guiView.addContinueGameButtonListener(new ContinueGameButtonListener());
            //SAVE PLAYER INFORMATION VIA TEXT
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

            guiView.showMapAndDirectionsGui(map.getMap(), map.getDimensions());
            guiView.addGoNorthButtonListener(new GoNorthButtonListener());
            guiView.addGoEastButtonListener(new GoEastButtonListener());
            guiView.addGoSouthButtonListener(new GoSouthButtonListener());
            guiView.addGoWestButtonListener(new GoWestButtonListener());
        }
    }

    class GoNorthButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            if (player.getPlayerXPos() != 0){
                map.removePlayerOnMap(player.getPlayerXPos(), player.getPlayerYPos());
                player.setPlayerXPos(player.getPlayerXPos() - 1);
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

