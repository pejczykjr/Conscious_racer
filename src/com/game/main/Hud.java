package com.game.main;

import java.awt.*;

//This class displays information about current game
public class Hud {

//  VARIABLES
//  ---------
    private final int TIME = 180; //Time that is needed to finish game
    private float timeBar;  //Progress bar
    private int greenValue;
    private int score=0; //Game score
    private final int fontSize=18;  //Height of progress and score bars and font size

//  CONSTRUCTOR
//  -----------

//  METHODS
//  -------
    public void tick(){
        if(timeBar<=TIME) {
            timeBar += 1. / Game.amountOfTicks; //Every one second moves timeBar towards end
        }

        greenValue = 255 - (int) (timeBar * 255/TIME);   //Progress bar will change its colour from green to red, 255/360 because: TIME=360 - 100%, max greenValue=255 - ?
        greenValue = Game.clamp(greenValue, 0, 255);
    }

    public void render(Graphics g){
        //BOTTOM BLACK BAR
        g.setColor(Color.BLACK);
        g.fillRect(0,Game.HEIGHT-Game.heightCorrection-Game.downBarHeight,Game.WIDTH- Game.widthCorrection,Game.downBarHeight);  //Creates black bar on the bottom

        //GRAY BARS ON THE BLACK BAR
        g.setColor(Color.GRAY);
        g.fillRect(238,962,90,fontSize);  //For score
        g.fillRect(611,962,TIME,fontSize);    //For progress - time

        //COLOURING PROGRESS BAR
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(611,962,(int)timeBar,fontSize);  //TimeBar is increased after passing total number

        //WHITE FONT STRINGS NEXT TO GRAY BARS
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.DIALOG,Font.BOLD,fontSize));
        g.drawString("Score",180,977);  //X and y values are picked by atrial-and-error to fit well
        g.drawString("Time", 560,977);
        g.drawString(TIME +"s",611+TIME/3,977); //Displays total time of game
        g.drawString(String.valueOf(score),239,977);
    }

    //Setters
    public void setScore(int score){
        this.score = score;
    }

    //Getters
    public int getScore(){
        return score;
    }

}
