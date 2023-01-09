package com.game.main;

import java.awt.*;

/**
 * Class responsible for enemy's object creation.
 * @author Mateusz Pieczykolan
 */
public class Enemy extends GameObject{

    /**
     * Declaration of spawn instance.
     */
    private final Spawn spawn;

    /**
     * Enemy object constructor. Calls the constructor of the GameObject class and receives its parameters.
     * @param x initial object's position on the x-axis
     * @param y initial object's position on the y-axis
     * @param id object's ID
     * @param spawn passing the spawn instance
     */
    public Enemy(int x, int y, ID id, Spawn spawn) {
        super(x, y, id);
        this.spawn=spawn;
    }

    /**
     * This method is responsible for refreshing Enemy object.<br>
     * Gets velocity values on y-axis from spawn instance and changes the Enemy object's position based on that.
     */
    public void tick() {
        velY = spawn.getEnemyVelocity();
        y+=velY;
    }

    /**
     * This method is responsible for rendering Enemy object.<br>
     * Based on the Enemy object's ID assigns certain image from parent class as a texture.
     * @param g passing the graphic component
     */
    public void render(Graphics g) {
        switch (id){
            case Enemy1 -> pic = greenCarRev.getImage();
            case Enemy2 -> pic = purpleCarRev.getImage();
            case Enemy3 -> pic= blueCarRev.getImage();
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

}