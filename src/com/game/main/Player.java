package com.game.main;

import java.awt.*;

//This class allows to create player object
public class Player extends GameObject {

//  VARIABLES
//  ---------
    private final Handler handler;
    private final Hud hud;

//  CONSTRUCTOR
//  -----------
    Player(int x, int y, ID id, Handler handler, Hud hud){
        super(x,y,id);  //Calls a constructor with the same parameters as the equivalent from the class which we inherited
        this.handler=handler;
        this.hud=hud;
    }

//  METHODS
//  -------
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

    public void collision() {
        for (int i=0; i<handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Enemy1 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3)
                if (getBounds().intersects(tempObject.getBounds())) {
                    //Collision code

                    hud.setScore(hud.getScore()-1);
                    handler.removeObject(tempObject);
                }
        }
    }

    //It prevents the user to move object out of the map bounds
    public static int clamp(int var, int min, int max){
        if(var>=max)    //If value is higher than max, it returns declared max
            return var=max;
        else if(var<=min)   //If value is lower than min, it returns declared min
            return var=min;
        else
            return var; //If value is in between bounds, stays same
    }

//  TICK AND RENDER METHODS
//  -----------------------
    public void tick() {
        x+=velX;
        y+=velY;

        x=clamp(x,0, Game.WIDTH-Game.carWidth-Game.widthCorrection);  //It checks if object is at the bound in x-axis
        y=clamp(y,0,Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight);  //It checks if object is at the bound in y-axis

        collision();
    }

    public void render(Graphics g) {
        switch (id){
            case Player1 -> pic = greenCar.getImage();
            case Player2 -> pic = purpleCar.getImage();
            case Player3 -> pic = blueCar.getImage();
        }
        g.drawImage(pic, x, y, null);
    }

}