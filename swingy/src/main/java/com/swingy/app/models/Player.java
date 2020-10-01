package com.swingy.app.models;

public class Player
{
    private String playerName;
    private String playerClass;
    private String playerWeapon;
    private String playerArmor;
    private String playerHelm;
    private int playerLevel;
    private int playerHP;
    private int playerExperiance;
    private int playerAttack;
    private int playerDefense;
    private int xPosition;
    private int yPosition;

    ///SETTERS
    public void setPlayerHP(int playerHP){
        this.playerHP = playerHP;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void setPlayerWeapon(String playerWeapon){
        this.playerWeapon = playerWeapon;
    }

    public void setPlayerLevel(int playerLevel){
        this.playerLevel = playerLevel;
    }

    public void setPlayerExperiance(int level){
        if (level == 0)
            playerExperiance = 0;
        else
            playerExperiance = playerLevel * 1000 + (playerLevel - 1) * 2 * 450;
    }

    public void setPlayerAttack(int playerAttack){
        this.playerAttack = playerAttack;
    }

    public void setPlayerDefense(int playerDefense){
        this.playerDefense = playerDefense;
    }

    public void setPlayerClass(String playerClass){
        this.playerClass = playerClass;
    }

    public void setPlayerXPos(int x){
        xPosition = x;
    }

    public void setPlayerYPos(int y){
        yPosition = y;
    }
    ////GETTERS
    public int getPlayerHP(){
        return this.playerHP;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public String getPlayerWeapon(){
        return this.playerWeapon;
    }

    public int getPlayerLevel(){
        return this.playerLevel;
    }

    public int getPlayerExperiance(){
        return this.playerExperiance;
    }

    public int getPlayerAttack(){
        return this.playerAttack;
    }

    public int getPlayerDefense(){
        return this.playerDefense;
    }

    public String getPlayerClass(){
        return this.playerClass;
    }

    public int getPlayerXPos(){
        return xPosition;
    }

    public int getPlayerYPos(){
        return yPosition;
    }

    ////HERO CLASS METHODS
    public void setupGiant(){
        setPlayerClass("Giant");
        setPlayerLevel(1);
        setPlayerHP(10);
        setPlayerExperiance(0);
        setPlayerAttack(10);
        setPlayerDefense(5);
    }

    public void setupAlph(){
        setPlayerClass("Alph");
        setPlayerLevel(1);
        setPlayerHP(10);
        setPlayerExperiance(0);
        setPlayerAttack(5);
        setPlayerDefense(10);
    }

    public void setupWitcher(){
        setPlayerClass("Witcher");
        setPlayerLevel(1);
        setPlayerHP(10);
        setPlayerExperiance(0);
        setPlayerAttack(7);
        setPlayerDefense(8);
    }

    public void playerCurrentStats(){
        System.out.println("\nYOUR STATS ARE:\nNAME: "+getPlayerName()+"\nCLASS: "+getPlayerClass()+"\nHP: "+getPlayerHP()+"\nLEVEL: "+getPlayerLevel()+"\nEXPERIANCE: "+getPlayerExperiance()+"\nATTACK: "+getPlayerAttack()+"\nDEFENSE: "+getPlayerDefense());
    }

    public String getCurrentPlayerStats(){
        return "\nYOUR STATS ARE:\n\nNAME: "+getPlayerName()+"\nCLASS: "+getPlayerClass()+"\nHP: "+getPlayerHP()+"\nLEVEL: "+getPlayerLevel()+"\nEXPERIANCE: "+getPlayerExperiance()+"\nATTACK: "+getPlayerAttack()+"\nDEFENSE: "+getPlayerDefense();
    }
}