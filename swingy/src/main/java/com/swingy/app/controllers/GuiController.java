package com.swingy.app.controllers;
import com.swingy.app.views.*;
import com.swingy.app.models.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiController
{
    private GuiView guiView;

    public GuiController(GuiView guiView){
        this.guiView = guiView;

        this.guiView.addOptionOneListener(new StartButtonListener());
        //this.guiView.addOptionTwoListener(new StartButtonListener());
    }

    class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            System.out.println("worked");
        }
    }
}