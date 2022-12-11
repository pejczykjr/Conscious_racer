package com.game.main;

import java.awt.*;
import java.util.LinkedList;

//This class is going to loop through all the objects in game
//and individually update them and render them to the screen
public class Handler {

//  VARIABLES
//  ---------
    //List containing all the game objects, because it is not known how many objects will be
    LinkedList<GameObject> object = new LinkedList<>();

//  METHODS
//  -------
    //This method loops through all the game objects and updates them
    public void tick(){
        for(GameObject tempObject : object)
            tempObject.tick();
    }

    //This method loops through all the game objects and renders them
    public void render(Graphics g){
        for(GameObject tempObject : object)
            tempObject.render(g);
    }

    //Adds object to list
    public void addObject(GameObject object){
        this.object.add(object);
    }

    //Removes object from list
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}