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

        this.guiView.addOptionOneListener(new CreateHeroButtonListener());
        this.guiView.addOptionTwoListener(new SelectHeroButtonListener());
    }

    class CreateHeroButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            System.out.println("one worked");
        }
    }

      class SelectHeroButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e){
            System.out.println("two worked");
        }
    }
}