package com.game.main;

import java.awt.*;

/**
 * Class responsible for displaying information about current game, simply head-up display.
 * @author Mateusz Pieczykolan
 */
public class Hud {

    /**
     * Declaration of handler instance.
     */
    private final Handler handler;

    /**
     * Time that is needed to finish game.
     */
    final int TIME = 180;
    /**
     * Progress bar, shows how much time is left.
     */
    float timeBar;
    /**
     * This variable keeps information about green colour that fills timeBar.
     */
    private int greenValue;

    /**
     * This is font size and height of progress and score bars.
     */
    private final int fontSize = 18;

    /**
     * Game score.
     */
    private int score = 0;
    /**
     * Game level.
     */
    private int level = 1;

    /**
     * Constructor.
     * @param handler passing the handler instance
     */
    public Hud(Handler handler){
        this.handler = handler;
    }

    /**
     * This method is responsible for refreshing head-up display. It also sets upper level.
     */
    public void tick(){
        if(timeBar<TIME)
            timeBar += 1. / Game.amountOfTicks;                   //Every one second moves timeBar towards end

        greenValue = 255 - (int) (timeBar * 255/TIME);            //Progress bar will change its colour from green to red
        greenValue = Player.clamp(greenValue, 0, 255);  //It checks if greenValue is at the bounds of colour scale

        //Setting upper level
        if((timeBar >= TIME/2) && getLevel() == 1)
            setLevel(getLevel()+1);
    }

    /**
     * This method is responsible for rendering head-up display. It shows information about progress in the game.
     * @param g passing the graphic component
     */
    public void render(Graphics g){

        //BOTTOM BLACK BAR
        g.setColor(Color.BLACK);
        g.fillRect(0,Game.HEIGHT-Game.heightCorrection-Game.downBarHeight,Game.WIDTH- Game.widthCorrection,Game.downBarHeight);

        //GRAY BARS ON THE BLACK BAR
        g.setColor(Color.GRAY);
        g.fillRect(600,962,90,fontSize);                    //For score.
        g.fillRect(940,962,TIME,fontSize);                        //For progress - time.

        //COLOURING PROGRESS BAR
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(940,962,(int)timeBar,fontSize);                //timeBar is increased after passing total number.

        //DRAWING STRINGS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.ITALIC,fontSize));
        //LEVEL
        g.drawString("Level: " + level, 175, 977);
        //SCORE
        g.drawString("Score",545,977);                        //x and y values are picked by atrial-and-error to fit well.
        g.drawString(String.valueOf(score),602,977);
        //TIME
        g.drawString("Time", 890,977);
        g.drawString(TIME-(int)timeBar +"s",940+TIME/3,977);  //Displays total time of game.
    }

    /**
     * "score" Setter
     * @param score sets score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * "level" Setter
     * @param level sets level
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * "score" Getter
     * @return returns score
     */
    public int getScore(){
        return score;
    }

    /**
     * "level" Getter
     * @return returns level
     */
    public int getLevel(){
        return level;
    }

}
