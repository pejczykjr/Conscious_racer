package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1280, HEIGHT = 1024;
    private Thread thread;
    private boolean running = false;

    Game(){
        new Window(WIDTH,HEIGHT,"Conscious racer", this);
    }

    public synchronized void start(){ //synchronized to prevent thread interference
        thread = new Thread(this);
        thread.start(); //starts thread
        running = true; //indicates thread on
    }

    public synchronized void stop(){
        try{
            thread.join(); //kills a thread
            running = false; //indicates thread off
        }
        catch (Exception e){
            e.printStackTrace(); //throws exception if any error occurs
        }
    }

    public void run() { //game loop, refreshes display and shows fps
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){

    }
    private void render(){ //it renders the game
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3); //it creates 3 buffers in game, recommended 2/3
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
