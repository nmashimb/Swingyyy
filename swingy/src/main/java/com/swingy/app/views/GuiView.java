package com.swingy.app.views;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuiView extends JFrame
{
    private JTextArea startGameOptions;
    private JTextArea playerStatsTextArea;
    private JTextArea gameMap;
    private JTextArea availablePlayers;
    private JTextField playerNameField;
    private JTextField HeroOption;
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
    private JButton selectHeroButton;
    private JButton submitNameButton;
    private JButton fighting;
    private JButton running;
    private JPanel startGamePanel;
    private JPanel stepOnePanel;
    private JPanel stepTwoPanel;
    private JPanel playerStatsPanel;
    private JPanel mapPanel;
    private JPanel previousPlayersPanel;
    private JPanel fightOrRunPanel;
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

    public void addChoosePlayerButtonListener(ActionListener listenForChoosePlayerButton){
        selectHeroButton.addActionListener(listenForChoosePlayerButton);
    }

    public void addFightButtonListener(ActionListener listenForFightButton){
        fighting.addActionListener(listenForFightButton);
    }

    public void addRunButtonListener(ActionListener listenForRunButton){
        running.addActionListener(listenForRunButton);
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

    public JPanel getpreviousPlayersPanel(){
        return previousPlayersPanel;
    }

    public JTextArea getPreviosPlayers(){
        return availablePlayers;
    }

    public String getHeroOption(){
        return HeroOption.getText();
    }

    public JPanel getFightOrRunPanel(){
        return fightOrRunPanel;
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
        if (stepTwoPanel != null)
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
        gameMap.setEditable(false);
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

    public void displayPlayers(String playersList){
        startGamePanel.setVisible(false);
        previousPlayersPanel = new JPanel();
        availablePlayers = new JTextArea(playersList);
        availablePlayers.setEditable(false);
        HeroOption = new JTextField(3); 
        selectHeroButton = new JButton("Submit");

        previousPlayersPanel.add(availablePlayers);
        previousPlayersPanel.add(HeroOption);
        previousPlayersPanel.add(selectHeroButton);

        this.add(previousPlayersPanel);
    }

    public void addFightOrRunToPanel(){
        fightOrRunPanel = new JPanel();
        JTextArea fightRunInstr = new JTextArea("Encountered a monster!\n1. Fight\n2. Run");
        fightRunInstr.setEditable(false);
        JButton fighting = new JButton("Fight");
        JButton running = new JButton("Run");
        this.fighting = fighting;
        this.running = running;
 
        fightOrRunPanel.add(fightRunInstr);
        fightOrRunPanel.add(fighting);
        fightOrRunPanel.add(running);
        this.add(fightOrRunPanel);
    }

    public void gameOver(){
        JPanel gameOverPanel = new JPanel();
        JTextArea msg = new JTextArea("GAME OVER!");
        msg.setEditable(false);

        gameOverPanel.add(msg);
        
        this.add(gameOverPanel);
    }
}