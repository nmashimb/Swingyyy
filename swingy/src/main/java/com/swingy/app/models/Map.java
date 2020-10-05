package com.swingy.app.models;

public class Map
{
    private String map[][];
    private String hiddenMap[][];
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
        hiddenMap = new String[dimensions][dimensions];
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

    public void placeVillainOnMap(int x, int y, int villain){
        if (!map[x][y].matches("1"))
            map[x][y] = ""+villain;
        //hiddenMap[x][y] = ""+villain;
    }
    public void removePlayerOnMap(int x, int y){
        map[x][y] = "0";
    }

    public boolean spotOccuppied(int x, int y){
        if (map[x][y].matches("0"))
            return false;
        else
            return true;
    }

    public String occupiedBy(int x, int y){
        return map[x][y];
    }
}