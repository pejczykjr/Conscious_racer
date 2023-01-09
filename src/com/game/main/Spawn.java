package com.game.main;

import java.awt.*;
import java.util.Random;

/**
 * Class used to spawn player and enemies, here are added objects.
 * @author Mateusz Pieczykolan
 */
public class Spawn {

    /**
     * Declaration of handler instance.
     */
    private final Handler handler;
    /**
     * Declaration of hud instance.
     */
    private final Hud hud;
    /**
     * Object's ID.
     */
    private ID id;
    /**
     * Declaration of player instance.
     */
    private Player player;

    /**
     * Declaration and creation of random instance.
     */
    private final Random random = new Random();

    /**
     * It counts ticks. After certain number, speed of enemy car will be increased.
     */
    private int countTicks;
    /**
     * It counts ticks too, but is handled in a different way. It is currently used by Handler class to<br>
     * change lights after certain time.
     */
    static int countRedLight;
    /**
     * This is used to manipulate enemy's speed, to not lose current velocity after car removal.
     */
    private int enemyVelocity;

    /**
     * Constructor, sets initial values to variables.
     * @param handler passing the handler instance
     * @param hud passing the hud instance
     */
    public Spawn(Handler handler, Hud hud){
        this.handler=handler;
        this.hud=hud;

        countTicks=0;
        countRedLight=0;
        enemyVelocity=1;
    }

    /**
     * This method is responsible for spawning objects (enemies and player) and removing them.
     */
    public void tick(){
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
                //If tempObject is Enemy and car passed down without collision with Player, delete Enemy
                if (tempObject.getY() >= (Game.HEIGHT - Game.heightCorrection - Game.downBarHeight))
                    handler.removeObject(tempObject);
            }
        }

        countRedLight++;
        countTicks++;
    }

    /**
     * "enemyVelocity" Setter
     * @param enemyVelocity sets enemyVelocity
     */
    public void setEnemyVelocity(int enemyVelocity){
        this.enemyVelocity=enemyVelocity;
    }

    /**
     * Enemy ID Getter
     * @return returns Enemy object's random ID, to assign it later
     */
    public ID setRandomEnemyID(){
        //Randomly chooses colour of the Enemy's car
        switch (random.nextInt(3) + 1) {
            case 1 -> id = ID.Enemy1;
            case 2 -> id = ID.Enemy2;
            case 3 -> id = ID.Enemy3;
        }
        return id;
    }

    /**
     * Player ID Getter
     * @return returns Player object's random ID, to assign it later
     */
    public ID setRandomPlayerID(){
        //Randomly chooses colour of the Player's car
        switch(random.nextInt(3)+1){
            case 1 -> id = ID.Player1;
            case 2 -> id = ID.Player2;
            case 3 -> id = ID.Player3;
        }
        return id;
    }

    /**
     * "enemyVelocity" Getter
     * @return returns enemyVelocity
     */
    public int getEnemyVelocity(){
        return this.enemyVelocity;
    }

}
