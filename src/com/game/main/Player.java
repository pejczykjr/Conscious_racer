package com.game.main;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//This class
public class Player extends GameObject {

//  VARIABLES
//  ---------
    private Image pic;

//  CONSTRUCTOR
//  -----------
    Player(int x, int y, ID id){
        super(x,y,id);  //Calls a constructor with the same parameters as the equivalent from the class which we inherited
    }

//  METHODS
//  -------
    public void tick() {
        x+=velX;
        y+=velY;

        x=Game.clamp(x,0, Game.WIDTH-Game.carWidth-Game.widthCorrection);  //It checks if object is at the bound in x-axis
        y=Game.clamp(y,0,Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight);  //It checks if object is at the bound in y-axis
    }

    public void render(Graphics g) {
        if(id == ID.Player) {
            ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Conscious_racer\\Pictures\\Cars and light\\blue_car_resized.png");
            pic = bg.getImage();
            g.drawImage(pic, x,y ,null);

            /*  IMAGE RESIZING
            BufferedImage bufferedImage;
            try {
                bufferedImage = ImageIO.read(new File("C:\\Users\\piecz\\Desktop\\JPWP gra\\Pictures\\blue_car.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Image image = bufferedImage.getScaledInstance(250, 200, Image.SCALE_DEFAULT);
            */
        }
    }

}