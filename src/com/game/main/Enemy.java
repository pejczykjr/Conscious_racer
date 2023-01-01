package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class ...
public class Enemy extends GameObject{

//  VARIABLES
//  ---------
    final private ImageIcon greenCar;
    final private ImageIcon purpleCar;
    final private ImageIcon blueCar;
    private int countTicks;

//  CONSTRUCTOR
//  -----------
    public Enemy(int x, int y, ID id) {
        super(x, y, id);    //Calls a constructor with the same parameters as the equivalent from the class which we inherited

        greenCar = new ImageIcon("C:\\Users\\piecz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\green_car_rev_resized.png");
        purpleCar = new ImageIcon("C:\\Users\\piecz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\purple_car_rev_resized.png");
        blueCar = new ImageIcon("C:\\Users\\piecz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_rev_resized.png");

        velY=1; //Velocity of enemy's car in y-axis
        velX=0; //Velocity of enemy's car in x-axis
        countTicks=0;   //It will count ticks
    }

//  METHODS
//  -------
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

    public void tick() {
        //After 12s seconds velocity in y-axis will be increased by 1
        if(countTicks==12*Game.amountOfTicks){
            if(velY>0) velY++;
            else velY--;

            countTicks=0;
        }
        countTicks++;

        x+=velX;
        y+=velY;

        //Changes direction of car's movement to negative at bounds
        if(y<=0 || y>=Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight) velY*=-1;
        if(x<=0 || x>=Game.WIDTH-Game.carWidth-Game.widthCorrection) velX*=-1;
    }

    public void render(Graphics g) {
        Image pic = null;

        //switch (id){
          //  case Enemy1 -> pic = greenCar.getImage();
           // case Enemy2 -> pic = purpleCar.getImage();
            //case Enemy3 -> pic= blueCar.getImage();
        //}
        pic= blueCar.getImage();
        g.drawImage(pic, x, y, null);

    }

}