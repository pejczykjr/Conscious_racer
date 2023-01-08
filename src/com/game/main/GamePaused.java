package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GamePaused extends MouseAdapter {

//  VARIABLES
//  ---------
    private final Hud hud;
    private final HighestScore highestScore;
    private final Menu menu;

//  CONSTRUCTOR
//  -----------
    public GamePaused(Hud hud, HighestScore highestScore, Menu menu){
        this.hud = hud;
        this.highestScore = highestScore;
        this.menu = menu;
    }

//  METHODS
//  -------
    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();

        if(Game.gameState == STATE.GamePaused) {
            //Resume button
            if (menu.mouseOver(mouseX, mouseY, 470, 290, 315,60)) {
                Game.gameState = STATE.Game;
            }
            //Menu button
            else if (menu.mouseOver(mouseX, mouseY, 470, 370, 315, 60)) {
                Game.gameState = STATE.Menu;
            }
            //Exit button
            else if (menu.mouseOver(mouseX, mouseY, 470, 450, 315, 60)) {
                System.exit(0);
            }
        }
        else if(Game.gameState == STATE.GameOver){
            if(highestScore.getFileScoreExists()){
                if(highestScore.getFileScore()<hud.getScore())
                    highestScore.setFileScore(hud.getScore());
            }
            else{
                highestScore.setFileScore(hud.getScore());
                highestScore.setFileScoreExists(true);
            }

        try {
            highestScore.writeHighestScore(String.valueOf(highestScore.getFileScore()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

            //Menu button
            if(menu.mouseOver(mouseX, mouseY, 470, 360, 100, 60)){
                Game.gameState = STATE.Menu;
            }
            //Exit button
            else if(menu.mouseOver(mouseX, mouseY, 650, 360, 100, 60)){
                System.exit(0);
            }
        }
        else if(Game.gameState == STATE.TrafficLaw){

        }
    }

    public void mouseReleased(MouseEvent e) {

    }

//  TICK AND RENDER METHODS
//  -----------------------

    public void tick(){

    }

    public void render (Graphics g){

        Font font1 = new Font("Arial", Font.PLAIN, 44);
        Font font2 = new Font("Arial", Font.PLAIN, 30);

        if(Game.gameState == STATE.GamePaused) {
            //MAIN BOX
            g.setColor(Color.BLACK);
            g.fillRect(400, 200, 450, 380);
            g.setColor(Color.WHITE);
            g.drawRect(399, 199, 451, 381);

            //TITLE
            g.setFont(font1);
            g.drawString("GAME PAUSED", 470, 250);

            //RESUME BUTTON
            g.setFont(font2);
            g.drawRect(470, 290, 315, 60);
            g.drawString("Resume", 570, 330);

            //MENU BUTTON
            g.drawRect(470, 370, 315, 60);
            g.drawString("Menu", 590, 410);

            //EXIT BUTTON
            g.drawRect(470, 450, 315, 60);
            g.drawString("Exit", 600, 490);
        }
        else if(Game.gameState == STATE.GameOver){
            //MAIN BOX
            g.setColor(Color.BLACK);
            g.fillRect(400, 200, 450, 280);
            g.setColor(Color.WHITE);
            g.drawRect(399, 199, 451, 281);

            //TITLE
            g.setFont(font1);
            g.drawString("GAME OVER", 485, 250);

            //SCORE
            g.setFont(font2);
            g.drawString("Your score is: " + hud.getScore(), 420, 320);

            //BACK TO MENU BUTTON
            g.drawRect(470,360, 100, 60);
            g.drawString("MENU", 475, 400);

            //EXIT BUTTON
            g.drawRect(650,360, 100, 60);
            g.drawString("EXIT", 665, 400);
        }
        else if(Game.gameState == STATE.TrafficLaw){

        }
    }
}
