package com.game.main;

import java.awt.*;
import java.util.Random;

//This class is used to spawn player and enemies, here are created objects
public class Spawn {

//  VARIABLES
//  ---------
    private final Handler handler;
    private final Hud hud;
    private ID id;
    private Player player;

    private final Random random = new Random();

    private int countTicks; //After certain number, speed of enemy car will be increased
    private int enemyVelocity;   //It will manipulate enemy's speed, to not lose current velocity after car removal

//  CONSTRUCTOR
//  -----------
    public Spawn(Handler handler, Hud hud){
        this.handler=handler;
        this.hud=hud;

        countTicks=0;
        enemyVelocity=1;
    }

//  METHODS
//  -------

    //Setters
    public ID setRandomEnemyID(){
        //Randomly chooses colour of the Enemy's car
        switch (random.nextInt(3) + 1) {
            case 1 -> id = ID.Enemy1;
            case 2 -> id = ID.Enemy2;
            case 3 -> id = ID.Enemy3;
        }
        return id;
    }

    public ID setRandomPlayerID(){
        //Randomly chooses colour of the Player's car
        switch(random.nextInt(3)+1){
            case 1 -> id = ID.Player1;
            case 2 -> id = ID.Player2;
            case 3 -> id = ID.Player3;
        }
        return id;
    }

    public void setEnemyVelocity(int enemyVelocity){
        this.enemyVelocity=enemyVelocity;
    }

    //Getters
    public int getEnemyVelocity(){
        return this.enemyVelocity;
    }

//  TICK AND RENDER METHODS
//  -------
    public void tick(){
        //Setting upper level
        if((hud.timeBar >= hud.TIME/2) && hud.getLevel() == 1)
            hud.setLevel(hud.getLevel()+1);

        //Creates player if it doesn't exist
        if(!handler.object.contains(player)){
            player = new Player((Game.WIDTH - Game.carWidth) / 2, Game.HEIGHT - Game.carHeight - Game.heightCorrection - Game.downBarHeight, setRandomPlayerID(), handler, hud);
            handler.addObject(player);
        }

        //Tick for level 1
        if(hud.getLevel()==1) {
            if (handler.object.size() < 2) { //One is reserved for Player object
                handler.addObject(new Enemy(Game.traces[random.nextInt(Game.traces.length)], -1 * Game.carHeight, setRandomEnemyID(), this));
                handler.addObject(new Enemy(Game.traces[random.nextInt(Game.traces.length)], -1 * Game.carHeight, setRandomEnemyID(), this));
            }
        }
        //Tick for level 2
        else{
            if (handler.object.size() < 2)  //First is reserved for Player object
                handler.addObject(new Enemy(Game.traces[random.nextInt(Game.traces.length)], -1 * Game.carHeight, setRandomEnemyID(), this));
        }

        //Enemy removal loop
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.Enemy1 || tempObject.id == ID.Enemy2 || tempObject.id == ID.Enemy3) {
                //After 6s seconds velocity in y-axis and score will be increased by 1
                if (countTicks == 6 * Game.amountOfTicks) {
                    enemyVelocity++;
                    hud.setScore(hud.getScore() + 1);
                    countTicks = 0;
                }
                countTicks++;
                //If tempObject is Enemy and car passed down without collision with Player, delete Enemy
                if (tempObject.getY() >= (Game.HEIGHT - Game.heightCorrection - Game.downBarHeight))
                    handler.removeObject(tempObject);
            }
        }
    }

    public void render(Graphics g){

    }

}
