package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

//This is main class
public class Game extends Canvas implements Runnable {

//  VARIABLES
//  ---------
    public static final int WIDTH = 1280, HEIGHT = 1024;    //1296x1063, actually 1296-1280=16 and 1063-1024=39 -> map has to be 1264x985
    public static final int carWidth = 250, carHeight = 200;
    public static final int widthCorrection = 16, heightCorrection = 39;    //It is needed to correct dimensions, explanation above
    public static final int downBarHeight = 26; //Object cannot cover bar with game status

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Image pic;
    Random random = new Random();

//  CONSTRUCTOR
//  -----------
    Game(){
        handler = new Handler();

        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH,HEIGHT,"Conscious racer", this);

        handler.addObject(new Player((WIDTH-carWidth)/2,(HEIGHT-carHeight)/2,ID.Player));
        handler.addObject(new Enemy(random.nextInt(WIDTH-carWidth-widthCorrection),random.nextInt(HEIGHT-carHeight-heightCorrection-downBarHeight),ID.Enemy2));
    }

//  METHODS
//  -------
    public synchronized void start(){   //Synchronized to prevent thread interference
        thread = new Thread(this);
        thread.start(); //Starts a thread
        running = true; //Indicates a thread on
    }

    public synchronized void stop(){
        try{
            thread.join();  //Kills a thread
            running = false;    //Indicates a thread off
        }
        catch (Exception e){
            e.printStackTrace();    //Throws an exception if any error occurs
        }
    }

    //Game loop, refreshes display and shows fps
    public void run() {
        long lastTime = System.nanoTime();  //Time since the last iteration of the loop, helps compute delta
        double amountOfTicks = 60;  //The max FPS for the game
        double ns = 1000000000 / amountOfTicks; //The number of nanoseconds per frame
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
        if(var>=max)
            return var=max;
        else if(var<=min)
            return var=min;
        else
            return var;
    }

    //It updates the game
    private void tick(){
        handler.tick();
    }

    //It renders the game
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(2);   //It creates 3 buffers in game, recommended 2/3
            return;
        }
        Graphics g = bs.getDrawGraphics();

        ImageIcon bg = new ImageIcon("C:\\Users\\piecz\\Desktop\\JPWP gra\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_11.png");
        pic = bg.getImage();
        g.drawImage(pic, 0, 0, null);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();
    }

}