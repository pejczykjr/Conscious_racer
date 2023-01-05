package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class allows to create player object
public class Player extends GameObject {

//  VARIABLES
//  ---------
    private final ImageIcon greenCar;
    private final ImageIcon purpleCar;
    private final ImageIcon blueCar;
    private final Handler handler;
    private final Hud hud;

//  CONSTRUCTOR
//  -----------
    Player(int x, int y, ID id, Handler handler, Hud hud){
        super(x,y,id);  //Calls a constructor with the same parameters as the equivalent from the class which we inherited
        this.handler=handler;
        this.hud=hud;

        blueCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\blue_car_resized.png");
        greenCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\green_car_resized.png");
        purpleCar = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Cars and light\\purple_car_resized.png");
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

    public void render(Graphics g) {
        Image pic = null;

        switch (id){
            case Player1 -> pic = greenCar.getImage();
            case Player2 -> pic = purpleCar.getImage();
            case Player3 -> pic= blueCar.getImage();
        }
        g.drawImage(pic, x, y, null);
    }

    public void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Enemy1 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3)
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision code

                    //After collision resets position of the player to x coordinate where was collision and y to bottom
                    if(tempObject.getX()==Game.traces[0])
                        this.setX(Game.traces[0]);
                    else if(tempObject.getX()==Game.traces[1])
                        this.setX((Game.WIDTH - Game.carWidth) / 2);
                    else if(tempObject.getX()==Game.traces[2])
                        this.setX(Game.traces[2]);

                    this.setY(Game.HEIGHT - Game.carHeight - Game.heightCorrection - Game.downBarHeight);

                    hud.setScore(hud.getScore()-1);
                    handler.removeObject(tempObject);
                }
        }
    }

}