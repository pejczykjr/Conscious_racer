package com.game.main;

import java.awt.*;
import static java.lang.Math.round;

//This class ...
public class Hud {

//  VARIABLES
//  ---------
    private final int TIME = 360; //Time that is needed to finish game
    static float timeBar;  //Progress bar

//  CONSTRUCTOR
//  -----------

//  METHODS
//  -------
    public void tick(){
        if(timeBar<=TIME) {
            timeBar+=1./Game.amountOfTicks; //Every one second moves timeBar towards end
        }
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(611,963,TIME,15);

        g.setColor(Color.GREEN);
        g.fillRect(611,963,round(timeBar),15); //The round(timeBar) rounds value down, so after passing total number it increased

        g.setColor(Color.WHITE);
        g.drawRect(611,963,TIME,15);
    }

}
