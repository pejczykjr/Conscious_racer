package com.game.main;

import java.awt.*;

//This class allows to create enemy object
public class Enemy extends GameObject{

//  VARIABLES
//  ---------
    private final Spawn spawn;

//  CONSTRUCTOR
//  -----------
    public Enemy(int x, int y, ID id, Spawn spawn) {
        super(x, y, id);    //Calls a constructor with the same parameters as the equivalent from the class which we inherited
        this.spawn=spawn;
    }

//  METHODS
//  -------
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

//  TICK AND RENDER METHODS
//  -----------------------
    public void tick() {
        velY = spawn.getEnemyVelocity();    //Velocity of enemy's car in y-axis
        y+=velY;
    }

    public void render(Graphics g) {
        switch (id){
            case Enemy1 -> pic = greenCarRev.getImage();
            case Enemy2 -> pic = purpleCarRev.getImage();
            case Enemy3 -> pic= blueCarRev.getImage();
        }
        g.drawImage(pic, x, y, null);
    }

}