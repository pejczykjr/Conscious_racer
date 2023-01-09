package com.game.main;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that forms the basis for other objects in the game.<br>
 * It is going to be what is referred to as all the game objects.
 * @author Mateusz Pieczykolan
 */
public abstract class GameObject {

    /**
     * Object's position.
     */
    protected int x, y;
    /**
     * Object's velocity on the x-axis and y-axis.
     */
    protected int velX, velY;
    /**
     * Object's ID.
     */
    protected ID id;
    /**
     * Object's image icons.
     */
    protected ImageIcon greenCar, blueCar, purpleCar, greenCarRev, blueCarRev, purpleCarRev;
    /**
     * Represents graphical image.
     */
    protected Image pic;

    /**
     * Game objects' constructor, gets paths of pictures and assigns them to variables.
     * @param x object's position on the x-axis
     * @param y object's position on the y-axis
     * @param id object's ID
     */
    public GameObject(int x, int y, ID id){
        this.x=x;
        this.y=y;
        this.id=id;

        //TODO: get path where are saved those files instead of pasting path permanently
        blueCar = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\blue_car_resized.png");
        greenCar = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\green_car_resized.png");
        purpleCar = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\purple_car_resized.png");

        blueCarRev = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\blue_car_rev_resized.png");
        greenCarRev = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\green_car_rev_resized.png");
        purpleCarRev = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\purple_car_rev_resized.png");

        pic = null;
    }

    /**
     * Object refresh method.
     */
    public abstract void tick();

    /**
     * Object render method.
     * @param g passing the graphic component
     */
    public abstract void render (Graphics g);

    /**
     * "x" Setter
     * @param x sets object's "x" position
     */
    public void setX(int x){
        this.x=x;
    }

    /**
     * "y" Setter
     * @param y sets object's "y" position
     */
    public void setY(int y){
        this.y=y;
    }

    /**
     * ID Setter
     * @param id sets object's ID
     */
    public void setId(ID id){
        this.id=id;
    }

    /**
     * "velX" Setter
     * @param velX sets object's "velX"
     */
    public void setVelX(int velX){
        this.velX=velX;
    }

    /**
     * "velY" Setter
     * @param velY sets object's "velY"
     */
    public void setVelY(int velY){
        this.velY=velY;
    }

    /**
     * "x" Getter
     * @return returns object's "x" position
     */
    public int getX(){
        return x;
    }

    /**
     * "y" Getter
     * @return returns object's "y" position
     */
    public int getY(){
        return y;
    }

    /**
     * ID Getter
     * @return returns object's ID
     */
    public ID getId(){
        return id;
    }

    /**
     * "velX" Getter
     * @return returns object's "velX"
     */
    public int getVelX(){
        return velX;
    }

    /**
     * "velY" Getter
     * @return returns object's "velY"
     */
    public int getVelY(){
        return velY;
    }

    /**
     * A method that checks object bounds. It is performed to verify that objects don't intersect.
     * @return returns object bounds
     */
    public abstract Rectangle getBounds();

}