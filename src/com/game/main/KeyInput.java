package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//This class manipulates keys
public class KeyInput extends KeyAdapter {

//  VARIABLES
//  ---------
    private Handler handler;

//  CONSTRUCTOR
//  -----------
    public KeyInput(Handler handler) {
        this.handler = handler;
    }

//  METHODS
//  -------
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //It loops through all the game objects and checks id
        for (GameObject tempObject : handler.object) {
            if (tempObject.id == ID.Player) {
                //Key events for Player when key is pressed

                if (key == KeyEvent.VK_W) tempObject.setVelY(-10);
                if (key == KeyEvent.VK_S) tempObject.setVelY(10);
                if (key == KeyEvent.VK_D) tempObject.setVelX(10);
                if (key == KeyEvent.VK_A) tempObject.setVelX(-10);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //It loops through all the game objects and checks id
        for (GameObject tempObject : handler.object) {
            if (tempObject.id == ID.Player) {
                //Key events for Player when key is released

                if (key == KeyEvent.VK_W) tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) tempObject.setVelX(0);
            }
        }
    }

}
