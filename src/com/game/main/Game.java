package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

//This is main class
public class Game extends Canvas implements Runnable {

//  VARIABLES
//  ---------
    //If classes are inside same package, they by default share variables (public inside same package)
    static final int WIDTH = 1280, HEIGHT = 1024;    //1296x1063, actually 1296-1280=16 and 1063-1024=39 -> map has to be 1264x985
    static final int carWidth = 250, carHeight = 200;    //Dimensions of resized car
    static final int widthCorrection = 16, heightCorrection = 39;    //It is needed to correct dimensions, explanation above
    static final int downBarHeight = 26; //Object cannot cover bar with game status
    static final int[] traces = {110,515,900}; //Enemy spawn places in x-axis

    private Thread thread;
    private boolean running = false;

    private final Handler handler;
    private final Hud hud;
    private final Spawn spawn;

    static int amountOfTicks = 60;  //The max FPS for the game, it is set to public (inside package) to allow to change timeBar in class Hud

//  CONSTRUCTOR
//  -----------
    Game(){
        handler = new Handler();
        hud = new Hud();
        spawn = new Spawn(handler,hud);
        this.addKeyListener(new KeyInput(handler, spawn));
        new Window(WIDTH,HEIGHT,"Conscious racer", this);
        this.requestFocus();
    }

//  METHODS
//  -------
    public synchronized void start(){   //Synchronized to prevent thread interference
        thread = new Thread(this);
        thread.start(); //Starts a thread
        running = true; //Indicates the game on
    }

    public synchronized void stop(){
        try{
            thread.join();  //Kills the thread
            running = false;    //Indicates the game off
        }
        catch (Exception e){
            e.printStackTrace();    //Throws an exception if any error occurs
        }
    }

    //Game loop, refreshes display and shows fps
    public void run() {
        long lastTime = System.nanoTime();  //Time since the last iteration of the loop, helps compute delta
        double ns = 1000000000. / amountOfTicks; //The number of nanoseconds per frame
        double delta = 0;   // The 'progress' that must be elapsed until the next frame
        int frames = 0; //The number of frames elapsed since the last time we displayed the FPS
        double timer = System.currentTimeMillis();  //The current time, used to know when to display next FPS

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;

            while(delta >= 1) {
                tick();
                delta--;

                if (running)
                    render();
                frames++;
            }

            if(System.currentTimeMillis() - timer >= 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
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

    //It updates the game
    private void tick(){
        handler.tick();
        hud.tick();
        spawn.tick();
    }

    //It renders the game
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);   //It creates 3 buffers in game, recommended 2/3
            return;
        }
        Graphics g = bs.getDrawGraphics();  //It creates graphics component

        ImageIcon bg = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_2.png");  //Background of game in race mode
        Image pic = bg.getImage();
        g.drawImage(pic, 0, 0, null);   //Draw that background

        handler.render(g);
        hud.render(g);
        spawn.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();
    }

}