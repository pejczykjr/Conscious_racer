package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class is going to be what we refer to as all the game objects
public abstract class GameObject {

//  VARIABLES
//  ---------
    protected int x, y;
    protected int velX, velY;
    protected ID id;
    protected ImageIcon greenCar, blueCar, purpleCar, greenCarRev, blueCarRev, purpleCarRev;
    protected Image pic;

//  CONSTRUCTOR
//  -----------
    public GameObject(int x, int y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;

        blueCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_resized.png");
        greenCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\green_car_resized.png");
        purpleCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\purple_car_resized.png");

        blueCarRev = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_rev_resized.png");
        greenCarRev = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\green_car_rev_resized.png");
        purpleCarRev = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\purple_car_rev_resized.png");

        pic = null;
    }

//  METHODS
//  -------
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

//  TICK AND RENDER METHODS
//  -----------------------
    public abstract void tick();
    public abstract void render (Graphics g);
}