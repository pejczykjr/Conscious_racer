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
        super(x, y, id);
        velX=5;
        velY=5;
    }

//  METHODS
//  -------
    public void tick() {
        x+=velX;
        y+=velY;

        if(y<=0 || y>=Game.HEIGHT-235) velY*=-1;
        if(x<=0 || x>=Game.WIDTH-270) velX*=-1;
    }

    public void render(Graphics g) {
        if(id == ID.Enemy1) {
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Pictures\\green_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
        else if(id == ID.Enemy2) {
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Pictures\\purple_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
        else if (id == ID.Enemy3){
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Pictures\\blue_car_rev_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x, y, null);
        }
    }

}
