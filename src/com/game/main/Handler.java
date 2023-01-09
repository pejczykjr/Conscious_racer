package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Class responsible for looping through all the objects in game and individually update them<br>
 * and render them to the screen.
 * @author Mateusz Pieczykolan
 */
public class Handler {
    /**
     * Background image icons.
     */
    private ImageIcon raceImage, lightImage, lightImageMirror;
    /**
     * Represents graphical image.
     */
    private Image pic;

    /**
     * List containing all the game objects (enemies and player)
     */
    LinkedList<GameObject> object = new LinkedList<>();

    /**
     * Constructor, gets paths of pictures and assigns them to variables.
     */
    public Handler(){
        //TODO: get path where are saved those files instead of pasting path permanently
        raceImage = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_2.png");
        lightImage = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\traffic_light_resized.png");
        lightImageMirror = new ImageIcon("D:\\Mateusz\\Documents\\GRA JPWP\\Conscious_racer\\Pictures\\Cars and light\\traffic_light_mirror_resized.png");
    }

    /**
     * This method loops through all the game objects and updates them. If state is set to STATE.Game<br>
     * it actually renders them then. It must be under if statement here, because handler.tick() is first<br>
     * and bare in Game class in tick() method.
     */
    public void tick(){
        for(int i=0; i< object.size(); i++){
            GameObject tempObject = object.get(i);

            if(Game.gameState == STATE.Game)
                tempObject.tick();
        }
    }

    /**
     * This method loops through all the game objects and renders them. Game background is set here.<br>
     * Here are also rendered changing traffic lights.
     * @param g passing the graphic component
     */
    public void render(Graphics g){
        pic = raceImage.getImage();
        g.drawImage(pic, 0, 0, null);

        for(int i=0; i< object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }

        //DRAW LIGHTS IMAGES ON BOTH SIDES
        pic = lightImage.getImage();
        g.drawImage(pic, -80, 20, null);
        pic = lightImageMirror.getImage();
        g.drawImage(pic, 995,20,null);

        //TODO: Create new class TrafficLaw,
        // implement there lights change in render method,
        // in tick method call functions, based on Game state, don't tick handler,
        // render handler, add key listener for space

        //LIGHTS CHANGE
        //After 5 seconds change light from green to yellow, and the after 1 second changes to red. PLayer has
        //5 seconds for reaction, and then red with yellow lights, and then after 1 second changes back to green.
        if(Game.gameState == STATE.Game || Game.gameState == STATE.TrafficLaw) {
            if (Spawn.countRedLight <= 5 * Game.amountOfTicks) {

                //GREEN LIGHT
                g.setColor(Color.GREEN);
                g.fillOval(160, 126, 38, 38);
                g.fillOval(1066, 126, 38, 38);

            } else if (Spawn.countRedLight > 5 * Game.amountOfTicks && Spawn.countRedLight < 6 * Game.amountOfTicks) {//&& ){

                //YELLOW LIGHT
                g.setColor(Color.YELLOW);
                g.fillOval(105, 126, 38, 38);
                g.fillOval(1121, 126, 38, 38);

            } else {

                //RED LIGHT
                g.setColor(Color.RED);
                g.fillOval(49, 126, 38, 38);
                g.fillOval(1178, 126, 38, 38);

                if(Spawn.countRedLight > 11*Game.amountOfTicks){

                    //YELLOW LIGHT
                    g.setColor(Color.YELLOW);
                    g.fillOval(105, 126, 38, 38);
                    g.fillOval(1121, 126, 38, 38);

                    if(Spawn.countRedLight > 12*Game.amountOfTicks){

                        //BACK TO GREEN LIGHT
                        Spawn.countRedLight = 0;
                    }
                }
            }
        }
    }

    /**
     * This method is responsible for adding objects to list.
     * @param object passing object that is needed to be added
     */
    public void addObject(GameObject object){
        this.object.add(object);
    }

    /**
     * This method is responsible for removing objects from list.
     * @param object passing object that is needed to be removed
     */
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}