package com.game.main;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class ...
public class Player extends GameObject {

//  VARIABLES
//  ---------
    private ImageIcon greenCar;
    private ImageIcon purpleCar;
    private ImageIcon blueCar;
    private Handler handler;
    private int score = 0;

//  CONSTRUCTOR
//  -----------
    Player(int x, int y, ID id, Handler handler){
        super(x,y,id);  //Calls a constructor with the same parameters as the equivalent from the class which we inherited
        this.handler=handler;

        blueCar = new ImageIcon("C:\\Users\\piecz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_resized.png");
    }

//  METHODS
//  -------
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

    public void tick() {
        x+=velX;
        y+=velY;

        x=Game.clamp(x,0, Game.WIDTH-Game.carWidth-Game.widthCorrection);  //It checks if object is at the bound in x-axis
        y=Game.clamp(y,0,Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight);  //It checks if object is at the bound in y-axis

        collision();
    }

    public void collision(){
        for(GameObject tempObject: handler.object){
            if(tempObject.getId()==ID.Enemy1 || tempObject.getId()==ID.Enemy2 || tempObject.getId()==ID.Enemy3)
                if(getBounds().intersects(tempObject.getBounds())){
                    //Collision code
                    //After collision resets position of the player
                    this.setX((Game.WIDTH-Game.carWidth)/2);
                    this.setY(Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight);
                    score-=1;

                    Window.label.setText(String.valueOf(score));
                    handler.removeObject(tempObject);
                }

        }
    }

    public void render(Graphics g) {
        Image pic = null;

        if(id == ID.Player)
            pic = blueCar.getImage();

        g.drawImage(pic, x,y ,null);
    }

}