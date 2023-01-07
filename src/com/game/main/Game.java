package com.game.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

//This is main class
public class Game extends Canvas implements Runnable {

//  VARIABLES
//  ---------
    static final int WIDTH = 1280, HEIGHT = 1024;    //1296x1063, actually 1296-1280=16 and 1063-1024=39 -> map has to be 1264x985
    static final int carWidth = 250, carHeight = 200;    //Dimensions of resized car
    static final int widthCorrection = 16, heightCorrection = 39;    //It is needed to correct dimensions, explanation above
    static final int downBarHeight = 26; //Object cannot cover bar with game status
    static final int[] traces = {110,515,900}; //Enemy spawn places in x-axis

    private Thread thread;
    private boolean running = false;
    static int amountOfTicks = 60;  //The max FPS for the game

    private final Menu menu;
    private final Handler handler;
    private final Hud hud;
    private final Spawn spawn;
    private final KeyInput keyInput;
    private final GamePaused gamePaused;

    static boolean gameTrue = false;
    static boolean gamePausedTrue = false;
    static boolean menuTrue = false;

    public static STATE gameState = STATE.Menu;

//  CONSTRUCTOR
//  -----------
    Game(){
        handler = new Handler();
        hud = new Hud();
        spawn = new Spawn(handler,hud);
        menu = new Menu(handler, hud, spawn);
        keyInput = new KeyInput(handler,spawn);
        gamePaused = new GamePaused();

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

    public static void main(String[] args){
        new Game();
    }

//  TICK AND RENDER METHODS
//  -----------------------
    //It updates the game
    private void tick(){
        if(gameState == STATE.Game){

            if(gameTrue == false) {
                this.addKeyListener(keyInput);
                this.removeMouseListener(gamePaused);
                this.removeMouseListener(menu);

                gameTrue = true;
                menuTrue = false;
                gamePausedTrue = false;
            }

            handler.tick(); //if handler is inside, it remembers state of paused game
            hud.tick();
            spawn.tick();
        }
        else if (gameState == STATE.Menu){

            if(menuTrue == false) {
                this.addMouseListener(menu);
                this.removeKeyListener(keyInput);
                this.removeMouseListener(gamePaused);

                menu.trueFalseState();
            }

            menu.tick();
        }
        else if (gameState == STATE.HighestScore){
            //-------------------------------------------
        }
        else if(gameState == STATE.GamePaused){
            if(gamePausedTrue == false){
                this.addMouseListener(gamePaused);
                this.removeMouseListener(menu);
                this.removeKeyListener(keyInput);

                gamePaused.trueFalseState();
            }
            gamePaused.tick();
        }
    }

    //It renders the game
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);   //It creates 3 buffers in game, recommended 2/3
            return;
        }
        Graphics g = bs.getDrawGraphics();  //It creates graphics component

        ImageIcon raceImage = new ImageIcon("D:\\Mateusz\\Documents\\Conscious_racer\\Pictures\\Tracks 1264x985\\track_2.png");  //Background of game in race mode
        Image pic = raceImage.getImage();

        if(gameState == STATE.Game){
            g.drawImage(pic, 0, 0, null);

            handler.render(g);
            hud.render(g);
            spawn.render(g);
        }
        else if (gameState == STATE.Menu){
            menu.render(g);
        }
        else if (gameState == STATE.HighestScore){
            //-------------------------------------------
        }
        else if(gameState == STATE.GamePaused){
            g.drawImage(pic, 0, 0, null);

            handler.render(g);
            hud.render(g);
            spawn.render(g);
            gamePaused.render(g);
        }

        g.dispose();
        bs.show();
    }

}