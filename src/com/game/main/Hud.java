package com.game.main;

import java.awt.*;

//This class displays information about current game
public class Hud {

//  VARIABLES
//  ---------
    final int TIME = 180; //Time that is needed to finish game
    float timeBar;  //Progress bar
    private int greenValue;

    private final int fontSize = 18;  //Height of progress and score bars and font size

    private int score = 0; //Game score
    private int level = 1;  //Game level

//  CONSTRUCTOR
//  -----------

//  METHODS
//  -------
    //Setters
    public void setScore(int score){
        this.score = score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    //Getters
    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

//  TICK AND RENDER METHODS
//  -----------------------
    public void tick(){
        if(timeBar<=TIME)
            timeBar += 1. / Game.amountOfTicks; //Every one second moves timeBar towards end

        greenValue = 255 - (int) (timeBar * 255/TIME);   //Progress bar will change its colour from green to red, 255/360 because: TIME=360 - 100%, max greenValue=255 - ?
        greenValue = Player.clamp(greenValue, 0, 255);
    }

    public void render(Graphics g){
        //BOTTOM BLACK BAR
        g.setColor(Color.BLACK);
        g.fillRect(0,Game.HEIGHT-Game.heightCorrection-Game.downBarHeight,Game.WIDTH- Game.widthCorrection,Game.downBarHeight);  //Creates black bar on the bottom

        //GRAY BARS ON THE BLACK BAR
        g.setColor(Color.GRAY);
        g.fillRect(600,962,90,fontSize);  //For score
        g.fillRect(940,962,TIME,fontSize);    //For progress - time

        //COLOURING PROGRESS BAR
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(940,962,(int)timeBar,fontSize);  //TimeBar is increased after passing total number

        //STRINGS WITH WHITE FONT NEXT TO GRAY BARS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.ITALIC,fontSize));
        g.drawString("Level: " + level, 175, 977);
        g.drawString("Score",545,977);  //X and y values are picked by atrial-and-error to fit well
        g.drawString(String.valueOf(score),602,977);
        g.drawString("Time", 890,977);
        g.drawString(TIME +"s",940+TIME/3,977); //Displays total time of game

    }

}
