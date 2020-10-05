package com.swingy.app;
import java.util.Scanner;
import com.swingy.app.controllers.*;
import com.swingy.app.views.*;
import com.swingy.app.models.*;

public class App
{
    public static void main( String[] args )
    {
        System.out.println((int)Math.round(Math.random() * 15));
        
        if (args[0].matches("console")){
            Player player = new Player();
            ConsoleView consoleView = new ConsoleView();
            ConsoleController consoleController = new ConsoleController(player, consoleView);
        }
        else if (args[0].matches("gui")){
            Player player = new Player();
            GuiView gui = new GuiView();
            GuiController guiController = new GuiController(player, gui);
        }
        else{
            return;
        }
    }
}
