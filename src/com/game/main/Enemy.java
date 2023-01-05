package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class allows to create enemy object
public class Enemy extends GameObject{

//  VARIABLES
//  ---------
    final private ImageIcon greenCar;
    final private ImageIcon purpleCar;
    final private ImageIcon blueCar;
    private final Spawn spawn;

//  CONSTRUCTOR
//  -----------
    public Enemy(int x, int y, ID id, Spawn spawn) {
        super(x, y, id);    //Calls a constructor with the same parameters as the equivalent from the class which we inherited
        this.spawn=spawn;

        greenCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\green_car_rev_resized.png");
        purpleCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\purple_car_rev_resized.png");
        blueCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_rev_resized.png");
    }

//  METHODS
//  -------
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

    public void tick() {
        velY = spawn.getEnemyVelocity(); //Velocity of enemy's car in y-axis
        y+=velY;
    }

    public void render(Graphics g) {
        Image pic = null;

        switch (id){
            case Enemy1 -> pic = greenCar.getImage();
            case Enemy2 -> pic = purpleCar.getImage();
            case Enemy3 -> pic= blueCar.getImage();
        }
        g.drawImage(pic, x, y, null);
    }

}