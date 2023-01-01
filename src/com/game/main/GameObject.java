package com.game.main;

import java.awt.*;

//This class is going to be what we refer to as all the game objects
public abstract class GameObject {

//  VARIABLES
//  ---------
    protected int x, y;
    protected ID id;
    protected int velX, velY;

//  CONSTRUCTOR
//  -----------
    public GameObject(int x, int y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;
    }

//  METHODS
//  -------
    public abstract void tick();
    public abstract void render (Graphics g);
    public abstract Rectangle getBounds();

    //Setters
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setId(ID id){
        this.id=id;
    }
    public void setVelX(int velX){
        this.velX=velX;
    }
    public void setVelY(int velY){
        this.velY=velY;
    }

    //Getters
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public ID getId(){
        return id;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }

}