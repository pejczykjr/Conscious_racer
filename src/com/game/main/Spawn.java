package com.game.main;

import java.awt.*;
import java.util.Random;

//This class is used to spawn player and enemies, here are created objects
public class Spawn {

//  VARIABLES
//  ---------
    private final Handler handler;
    private final Hud hud;
    private final Random random = new Random();
    private ID id;
    private int countTicks; //After certain number speed of enemy car will be increased
    private int enemyVelocity;   //It will manipulate enemy's speed, to not lose current velocity after car removal
    private boolean hardMode;   //After half of time it will add second enemy on the different trace, enemy will be added after some ticks, according to first enemy car velocity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //or maybe in the first part to add 2 cars until it gets to half of time because of high speed later on
//  CONSTRUCTOR
//  -----------
    public Spawn(Handler handler, Hud hud){
        this.handler=handler;
        this.hud=hud;

        //Randomly chooses colour of the Player's car
        switch(random.nextInt(3)+1){
            case 1 -> id = ID.Player1;
            case 2 -> id = ID.Player2;
            case 3 -> id = ID.Player3;
        }

        countTicks=0;
        enemyVelocity=1;
        hardMode=false;
        handler.addObject(new Player((Game.WIDTH-Game.carWidth)/2,Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight,id, handler, hud));
    }

//  METHODS
//  -------
    public void tick(){
        //Randomly chooses colour of the Enemy's car
        switch(random.nextInt(3)+1){
            case 1 -> id = ID.Enemy1;
            case 2 -> id = ID.Enemy2;
            case 3 -> id = ID.Enemy3;
        }

        //Creates enemy if it doesn't exist
        if(hardMode==false && handler.object.size()<2) //First is reserved for Player object
            handler.addObject(new Enemy(Game.traces[random.nextInt(Game.traces.length)],-1*Game.carHeight,id, this));
        else if(hardMode==true)
            ;

        //If tempObject is Enemy and car passed down without collision with Player, delete Enemy
        for(int i=0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.id==ID.Enemy1 || tempObject.id==ID.Enemy2 || tempObject.id==ID.Enemy3) {
                //After 5s seconds velocity in y-axis will be increased by 1
                if (countTicks == 5 * Game.amountOfTicks) {
                    countTicks = 0;
                    enemyVelocity++;
                }
                countTicks++;

                if(tempObject.getY()>=(Game.HEIGHT-Game.heightCorrection-Game.downBarHeight))
                    handler.removeObject(tempObject);
            }
        }
    }

    public void render(Graphics g){

    }

    //Setters
    public void setEnemyVelocity(int enemyVelocity){
        this.enemyVelocity=enemyVelocity;
    }

    //Getters
    public int getEnemyVelocity(){
        return this.enemyVelocity;
    }
}
