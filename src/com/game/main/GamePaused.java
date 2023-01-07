package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePaused extends MouseAdapter {

//  VARIABLES
//  ---------

//  CONSTRUCTOR
//  -----------

//  METHODS
//  -------
    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();

        //Resume button
        if (mouseOver(mouseX, mouseY, 470, 290)){
            Game.gameState = STATE.Game;
        }
        //Menu button
        else if(mouseOver(mouseX, mouseY, 470, 370)){
            Game.gameState = STATE.Menu;
        }
        //Exit button
        else if(mouseOver(mouseX, mouseY, 470, 450)){
            System.exit(0);
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mouseX, int mouseY, int x, int y){
        if(mouseX > x && mouseX < x + 315){ //228 is weight of car in menu
            return mouseY > y && mouseY < y + 60;  //575 is height of car in menu
        }
        else
            return false;
    }

    public void trueFalseState(){
        Game.gamePausedTrue = true;
        Game.menuTrue = false;
        Game.gameTrue = false;
    }

//  TICK AND RENDER METHODS
//  -----------------------

    public void tick(){

    }

    public void render (Graphics g){

        //MAIN BOX
        g.setColor(Color.BLACK);
        g.fillRect(400, 200, 450,380);
        g.setColor(Color.WHITE);
        g.drawRect(399,199,451,381);

        g.setFont(new Font("Arial", Font.PLAIN, 44));
        g.drawString("GAME PAUSED", 470, 250);

        //BUTTONS
        g.setFont(new Font("Arial", Font.PLAIN, 30));

        g.drawRect(470, 290, 315,60);
        g.drawString("Resume", 570, 330);

        g.drawRect(470, 370, 315,60);
        g.drawString("Menu", 590, 410);

        g.drawRect(470, 450, 315,60);
        g.drawString("Exit", 600, 490);
    }
}
