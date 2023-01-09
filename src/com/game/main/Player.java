package com.game.main;

import java.awt.*;

/**
 * Class responsible for player's object creation.
 * @author Mateusz Pieczykolan
 */
public class Player extends GameObject {

    /**
     * Declaration of handler instance.
     */
    private final Handler handler;
    /**
     * Declaration of hud instance.
     */
    private final Hud hud;

    /**
     * Player object constructor. Calls the constructor of the GameObject class and receives its parameters.
     * @param x initial object's position on the x-axis
     * @param y initial object's position on the y-axis
     * @param id object's ID
     * @param handler passing the handler instance
     * @param hud passing the hud instance
     */
    Player(int x, int y, ID id, Handler handler, Hud hud){
        super(x,y,id);
        this.handler=handler;
        this.hud=hud;
    }

    /**
     * This method is responsible for refreshing Player object.<br>
     * It changes the Player object's position based on velocity on x-axis and y-axis.<br>
     */
    public void tick() {
        x+=velX;
        y+=velY;

        x=clamp(x,0, Game.WIDTH-Game.carWidth-Game.widthCorrection);                       //It checks if object is at the bounds in x-axis.
        y=clamp(y,0,Game.HEIGHT-Game.carHeight-Game.heightCorrection-Game.downBarHeight);  //It checks if object is at the bounds in y-axis.

        collision();
    }

    /**
     * This method is responsible for rendering Player object.<br>
     * Based on the Player object's ID assigns certain image from parent class as a texture.
     * @param g passing the graphic component
     */
    public void render(Graphics g) {
        switch (id){
            case Player1 -> pic = greenCar.getImage();
            case Player2 -> pic = purpleCar.getImage();
            case Player3 -> pic = blueCar.getImage();
        }
        g.drawImage(pic, x, y, null);
    }

    /**
     * This method is responsible for returning the object bounds.
     * @return returns object bounds
     */
    public Rectangle getBounds() {
        return new Rectangle(x,y,Game.carWidth,Game.carHeight);
    }

    /**
     * This method is responsible for checking if there is a collision of the Player object and Enemy object.<br>
     * During the collision user gets 1 penalty point and Enemy object is removed.
     */
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

    /**
     * This method keeps a value within a given range. For example for velocity values, <br>
     * it prevents the user from moving object out of the map bounds.
     * @param var our variable we want to clamp
     * @param min minimum value in range
     * @param max maximum value in range
     * @return returns value not less than "min" and not higher than "max"
     */
    public static int clamp(int var, int min, int max){
        if(var>=max)            //If value is higher than max, it returns declared max.
            return var=max;
        else if(var<=min)       //If value is lower than min, it returns declared min.
            return var=min;
        else
            return var;         //If value is in between bounds, stays same.
    }

}