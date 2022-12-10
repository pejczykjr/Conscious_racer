package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

//Main class
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1280, HEIGHT = 1024;
    private Thread thread;
    private boolean running = false;

    Game(){
        new Window(WIDTH,HEIGHT,"Conscious racer", this);
    }

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
        double amountOfTicks = 30;  //The max FPS for the game
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

    //It updates the game
    private void tick(){
    }

    //It renders the game
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);   //It creates 3 buffers in game, recommended 2/3
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.CYAN);
        g.fillRect(0,0,WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args){
        new Game();

    }

}