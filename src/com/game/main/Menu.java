package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

//This class handles menu
public class Menu extends MouseAdapter{

//  VARIABLES
//  ---------
    private final Hud hud;
    private final Spawn spawn;
    private final Handler handler;
    private final HighestScore highestScore;
//  CONSTRUCTOR
//  -----------
    public Menu(Handler handler, Hud hud, Spawn spawn, HighestScore highestScore){
        this.handler = handler;
        this.hud = hud;
        this.spawn = spawn;
        this.highestScore = highestScore;
    }

//  METHODS
//  -------
    public void mousePressed(MouseEvent e){
        int mPressedX = e.getX();
        int mPressedY = e.getY();

        if(Game.gameState == STATE.Menu) {
            //Play button
            if (mouseOver(mPressedX, mPressedY, 130, 334, 228, 575)) {
                Game.gameState = STATE.Game;
            }
            //Highest score button
            else if (mouseOver(mPressedX, mPressedY, 520, 338, 228, 575)) {
                Game.gameState = STATE.HighestScore;
            }
            //Exit button
            else if (mouseOver(mPressedX, mPressedY, 921, 341, 228, 575)) {
                System.exit(0);
            }
        }
        else if(Game.gameState == STATE.HighestScore){
            //BACK TO MENU
            if (mouseOver(mPressedX, mPressedY, 470, 360, 100, 60)){
                Game.gameState = STATE.Menu;
            }
            //ERASE HIGHEST SCORE
            else if (mouseOver(mPressedX, mPressedY, 650, 360, 100, 60)){
                if(highestScore.getFileScoreExists()){
                    highestScore.setFileScoreExists(false);
                    try {
                        highestScore.writeHighestScore(highestScore.noData);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
        if(mouseX > x && mouseX < x + width){
            return mouseY > y && mouseY < y + height;
        }
        else
            return false;
    }

//  TICK AND RENDER METHODS
//  -----------------------
    public void tick(){
        hud.setScore(0);
        hud.setLevel(1);
        hud.timeBar = 0;
        spawn.setEnemyVelocity(1);
        handler.object.clear();
    }

    public void render (Graphics g){

        Font font1 = new Font("Arial", Font.PLAIN, 44);
        Font font2 = new Font("Arial", Font.PLAIN, 22);

        ImageIcon menuImage = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_9.png");  //Background of game in menu mode
        Image pic = menuImage.getImage();
        g.drawImage(pic, 0, 0, null);   //Draw that background

        if(Game.gameState == STATE.Menu) {
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Play", 220, 885);
            g.drawString("Score", 600, 885);
            g.drawString("Exit", 1010, 885);
        }
        else if(Game.gameState == STATE.HighestScore){
            //MAIN BOX
            g.setColor(Color.BLACK);
            g.fillRect(400, 200, 450, 280);
            g.setColor(Color.WHITE);
            g.drawRect(399, 199, 451, 281);

            //TITLE
            g.setFont(font1);
            g.drawString("HIGHEST SCORE", 445, 250);

            //SCORE
            g.setFont(font2);
            if(highestScore.getFileScoreExists())
                g.drawString("Your highest achieved score is: " + highestScore.getFileScore(), 420, 320);
            else
                g.drawString(highestScore.noData, 420, 320);


            //CLOSE
            g.drawRect(470,360, 100, 60);
            g.drawString("CLOSE", 480, 400);

            //RESET BUTTON
            g.drawRect(650,360, 100, 60);
            g.drawString("RESET", 660, 400);
        }
    }

}
