package com.swingy.app.models;

public class Map
{
    private String map[][];
    private int dimensions;

    public Map(int level){
        dimensions = (level-1)*5+10-(level%2);
        map = new String[dimensions][dimensions];
        int x = 0;
        int y;
        while (x < dimensions){
            y = 0;
            while(y < dimensions){
                map[x][y] = "0"; 
                y++;
            }
            x++;
        }
    }

    public void displayMapAndDirections(){
        int x = 0;
        int y;
        while (x < dimensions){
            y = 0;
            while(y < dimensions){
               System.out.print(map[x][y]+"    ");
                y++;
            }
            System.out.println("\n");
            x++;
        }
        System.out.println("1. North\n2. East\n3. South\n4. West");
    }

    public String[][] getMap(){
        return map;
    }

    public int getDimensions(){
        return dimensions;
    }
    public void setMap(int level){
        dimensions = (level-1)*5+10-(level%2);
        map = new String[dimensions][dimensions];
        int x = 0;
        int y;
        while (x < dimensions){
            y = 0;
            while(y < dimensions){
                map[x][y] = "0"; 
                y++;
            }
            x++;
        }
    }
    public void placePlayerOnMap(int x, int y){
        map[x][y] = "1";
    }

    public void removePlayerOnMap(int x, int y){
        map[x][y] = "0";
    }
}