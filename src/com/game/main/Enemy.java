package com.game.main;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class
public class Enemy extends GameObject{

//  VARIABLES
//  ---------
    private Image pic;

//  CONSTRUCTOR
//  -----------
    public Enemy(int x, int y, ID id) {
        super(x, y, id);    //Calls a constructor with the same parameters as the equivalent from the class which we inherited

        velX=5;
        velY=5;
    }

//  METHODS
//  -------
    public void tick() {
        x+=velX;
        y+=velY;

        if(y<=0 || y>=Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight) velY*=-1;
        if(x<=0 || x>=Game.WIDTH-Game.carWidth-Game.widthCorrection) velX*=-1;
    }

    public void render(Graphics g) {
        if(id == ID.Enemy1) {
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Conscious_racer\\Pictures\\Cars and light\\green_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
        else if(id == ID.Enemy2) {
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Conscious_racer\\Pictures\\Cars and light\\purple_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
        else if (id == ID.Enemy3){
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Conscious_racer\\Pictures\\Cars and light\\blue_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
    }

}
