package com.swingy.app.views;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuiView extends JFrame
{
    private JTextArea startGameOptions;
    private JTextArea playerStatsTextArea;
    private JTextArea gameMap;
    private JTextField playerNameField;
    private JButton createHeroButton;
    private JButton previosHeroButton;
    private JButton selectGiant;
    private JButton selectAlph;
    private JButton selectWitcher;
    private JButton continueGame;
    private JButton goNorth;
    private JButton goEast;
    private JButton goSouth;
    private JButton goWest;
    private JPanel startGamePanel;
    private JPanel stepOnePanel;
    private JPanel stepTwoPanel;
    private JPanel playerStatsPanel;
    private JPanel mapPanel;
    private JButton submitNameButton;
    final String[] playerClassArray = {"Giant", "Alph", "Witcher"};

    public GuiView(){
        startGamePanel = new JPanel();
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

    //BUTTONS
    public void addOptionOneListener(ActionListener listenForOptionOneButton){
        createHeroButton.addActionListener(listenForOptionOneButton);
    }

    public void addOptionTwoListener(ActionListener listenForOptionTwoButton){
        previosHeroButton.addActionListener(listenForOptionTwoButton);
    }

    public void addNameButtonListener(ActionListener listenForNameButton){
        submitNameButton.addActionListener(listenForNameButton);
    }

    public void addGiantButtonListener(ActionListener listenForGiantButton){
        selectGiant.addActionListener(listenForGiantButton);
    }

    public void addAlphButtonListener(ActionListener listenForAlphButton){
        selectAlph.addActionListener(listenForAlphButton);
    }

    public void addWitcherButtonListener(ActionListener listenForWitcherButton){
        selectWitcher.addActionListener(listenForWitcherButton);
    }

    public void addContinueGameButtonListener(ActionListener listenForContinueGameButton){
        continueGame.addActionListener(listenForContinueGameButton);
    }
    
    public void addGoNorthButtonListener(ActionListener listenForGoNorthButton){
        goNorth.addActionListener(listenForGoNorthButton);
    }

    public void addGoEastButtonListener(ActionListener listenForGoEastButton){
        goEast.addActionListener(listenForGoEastButton);
    }

    public void addGoSouthButtonListener(ActionListener listenForGoSouthButton){
        goSouth.addActionListener(listenForGoSouthButton);
    }

    public void addGoWestButtonListener(ActionListener listenForGoWestButton){
        goWest.addActionListener(listenForGoWestButton);
    }
    
    //GETTERS
    public String getPlayerNameField(){
        return playerNameField.getText();
    }

    public String getGiantButton(){
        return selectGiant.getText();
    }

    public String getAlphButton(){
        return selectAlph.getText();
    }

    public String geWitcherButton(){
        return selectWitcher.getText();
    }

    public JPanel getStepTwoPanel(){
        return stepTwoPanel;
    }

    public JPanel getStatsPanel(){
        return playerStatsPanel;
    }

    public JPanel getPlayerStatsPanel(){
        return playerStatsPanel;
    }

    public JPanel getMapPanel(){
        return mapPanel;
    }
    //SETTERS
    public void setPlayerStatsTextArea(String playerCurrentStats){
        this.playerStatsTextArea = new JTextArea(playerCurrentStats);
    }

    //CREATE PLAYER STEPS
    public void stepOne(){
        startGamePanel.setVisible(false);
        stepOnePanel = new JPanel();

        JTextArea stepOne = new JTextArea("Enter player name(characters <= 20):");
        stepOne.setEditable(false);
        playerNameField = new JTextField(20);
        submitNameButton = new JButton("Submit");

        stepOnePanel.add(stepOne);
        stepOnePanel.add(playerNameField);
        stepOnePanel.add(submitNameButton);

        this.add(stepOnePanel);
    }

    public void stepTwo(){
        stepOnePanel.setVisible(false);
        stepTwoPanel = new JPanel();

        JTextArea stepTwo = new JTextArea("Select hero class:\n1. Giant\n2. Alph\n3. Witcher");
        stepTwo.setEditable(false);
        selectGiant = new JButton("1");
        selectAlph = new JButton("2");
        selectWitcher = new JButton("3");

        stepTwoPanel.add(stepTwo);
        stepTwoPanel.add(selectGiant);
        stepTwoPanel.add(selectAlph);
        stepTwoPanel.add(selectWitcher);

        this.add(stepTwoPanel);
    }

    public void addPlayerStatsTextAreaToPanel(){
        stepTwoPanel.setVisible(false);
        playerStatsPanel = new JPanel();
        continueGame = new JButton("CONTINUE...");

        playerStatsPanel.add(playerStatsTextArea);
        playerStatsPanel.add(continueGame);
        this.add(playerStatsPanel);
    }

    public void showMapAndDirectionsGui(String map[][], int dimensions){
        mapPanel = new JPanel();
        gameMap = new JTextArea();
        int x = 0;
        int y;

        while (x < dimensions){
            y = 0;
            while(y < dimensions){
               gameMap.append(map[x][y]+" ");
                y++;
            }
            gameMap.append("\n");
            x++;
        }
        goNorth = new JButton("Go North");
        goEast = new JButton("Go East");
        goSouth = new JButton("Go South");
        goWest = new JButton("Go West");

        mapPanel.add(gameMap);
        mapPanel.add(goNorth);
        mapPanel.add(goEast);
        mapPanel.add(goSouth);
        mapPanel.add(goWest);
        this.add(mapPanel);
    }
}