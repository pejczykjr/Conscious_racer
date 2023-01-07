package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//This class handles menu
public class Menu extends MouseAdapter{

//  VARIABLES
//  ---------
    private final Hud hud;
    private final Spawn spawn;
    private final Handler handler;
//  CONSTRUCTOR
//  -----------
    public Menu(Handler handler, Hud hud, Spawn spawn){
        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
    }

//  METHODS
//  -------
    public void mousePressed(MouseEvent e){
        int mPressedX = e.getX();
        int mPressedY = e.getY();

        //Play button
        if (mouseOver(mPressedX, mPressedY, 130, 334)){
            Game.gameState = STATE.Game;
        }
        //Highest score button
        else if(mouseOver(mPressedX, mPressedY, 520, 338)){
            ;//Game.gameState = STATE.HighestScore;
        }
        //Exit button
        else if(mouseOver(mPressedX, mPressedY, 921, 341)){
            System.exit(0);
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mouseX, int mouseY, int x, int y){
        if(mouseX > x && mouseX < x + 228){ //228 is weight of car in menu
            return mouseY > y && mouseY < y + 575;  //575 is height of car in menu
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
        hud.setScore(0);
        hud.timeBar = 0;
        spawn.setEnemyVelocity(1);
        handler.object.clear();
    }

    public void render (Graphics g){
        ImageIcon menuImage = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_9.png");  //Background of game in menu mode
        Image pic = menuImage.getImage();
        g.drawImage(pic, 0, 0, null);   //Draw that background

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Play", 220, 885);
        g.drawString("Score", 600, 885);
        g.drawString("Exit", 1010, 885);
    }

}
