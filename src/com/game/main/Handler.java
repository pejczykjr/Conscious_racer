package com.game.main;

import java.awt.*;
import java.util.LinkedList;

//This class is going to loop through all the objects in game
//and individually update them and render them to the screen
public class Handler {

//  VARIABLES
//  ---------
    LinkedList<GameObject> object = new LinkedList<>(); //List containing all the game objects, because it is not known how many objects will be

//  CONSTRUCTOR
//  -----------

//  METHODS
//  -------
    //Adds object to list
    public void addObject(GameObject object){
        this.object.add(object);
}

    //Removes object from list
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

//  TICK AND RENDER METHODS
//  -----------------------
    //This method loops through all the game objects and updates them
    public void tick(){
        for(int i=0; i< object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    //This method loops through all the game objects and renders them
    public void render(Graphics g){
        for(int i=0; i< object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

}