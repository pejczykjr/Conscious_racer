package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Class responsible for Game handling
 * @author Mateusz Pieczykolan
 */
public class Game extends Canvas implements Runnable {

    /**
     * Width and height of actual app window.
     */
    static final int WIDTH = 1280, HEIGHT = 1024;
    /**
     * The actual dimensions of a JFrame are smaller than app window, so here is correction.
     */
    static final int widthCorrection = 16, heightCorrection = 39;
    /**
     * Dimensions of resized car.
     */
    static final int carWidth = 250, carHeight = 200;
    /**
     * Place for a black bar at the bottom of the window, where information about level,<br>
     * time and score will be displayed.
     */
    static final int downBarHeight = 26;
    /**
     * Spawn places on the x-axis for enemies.
     */
    static final int[] traces = {110,515,900};

    /**
     * The number of FPS at which game will be rendered.
     */
    static int amountOfTicks = 60;

    private Thread thread;
    private boolean running = false;

    private final Menu menu;
    private final Handler handler;
    private final Hud hud;
    private final Spawn spawn;
    private final KeyInput keyInput;
    private final GamePaused gamePaused;
    private final HighestScore highestScore;

    /**
     * A variable that specifies a particular game state.
     */
    public static STATE gameState = STATE.Menu;

    /**
     * Constructor responsible for creation of instances of other classes.<br>
     * Requests focus on app window - the user doesn't have to click on the window with game to start playing.
     */
    Game(){
        handler = new Handler();
        hud = new Hud(handler);
        highestScore = new HighestScore(hud);
        spawn = new Spawn(handler,hud);
        menu = new Menu(handler, hud, spawn, highestScore);
        keyInput = new KeyInput(handler,spawn);
        gamePaused = new GamePaused(hud,highestScore,menu);

        this.addKeyListener(keyInput);
        this.addMouseListener(menu);
        this.addMouseListener(gamePaused);

        new Window(WIDTH,HEIGHT,"Conscious racer", this);
        this.requestFocus();
    }

    /**
     * Start of game-related thread. Synchronized is used to prevent thread interference.
     */
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();     //Starts a thread.
        running = true;     //Indicates the game on.
    }

    /**
     * Stops a game-related thread. Synchronized is used to prevent thread interference.<br>
     * Throws an exception if any error occurs.
     */
    public synchronized void stop(){
        try{
            thread.join();   //Kills a thread.
            running = false; //Indicates the game off.
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Game loop, that refreshes display with speed of specified above FPS.
     */
    public void run() {
        long lastTime = System.nanoTime();         //Time since the last iteration of the loop, helps compute delta.
        double ns = 1000000000. / amountOfTicks;   //The number of nanoseconds per frame.
        double delta = 0;                          //The 'progress' that must be elapsed until the next frame.
        int frames = 0;                            //The number of frames elapsed since the last time we displayed the FPS.
        double timer = System.currentTimeMillis(); //The current time, used to know when to display next FPS.

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

    /**
     * This method updates the whole game.<br>
     * According to a particular state, tick() methods from different classes are called.
     */
    private void tick(){
        handler.tick();

        if(gameState == STATE.Game){
            hud.tick();
            spawn.tick();

            if((int)hud.timeBar == hud.TIME){
                gameState = STATE.GameOver;
            }
        }
        else if (gameState == STATE.Menu || gameState == STATE.HighestScore){
            menu.tick();
        }
        else if(gameState == STATE.GamePaused || gameState == STATE.TrafficLaw || gameState == STATE.GameOver) {

        }
    }

    /**
     * This method renders the whole game.<br>
     * Depending on the order of graphic components and render methods inside, the position of objects,<br>
     * maps and other graphic components on the z-axis changes.<br>
     * According to a particular state, render() methods from different classes are called.
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);  //It creates 3 buffers in game, recommended 2/3.
            return;
        }
        Graphics g = bs.getDrawGraphics();           //It creates graphic component.

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
        }
        else if (gameState == STATE.Menu || gameState == STATE.HighestScore){
            menu.render(g);
        }
        else if(gameState == STATE.GamePaused || gameState == STATE.TrafficLaw || gameState == STATE.GameOver){
            hud.render(g);
            gamePaused.render(g);
        }

        g.dispose();                                 //Releases graphics component.
        bs.show();                                   //Makes the next available buffer visible.
    }

    /**
     * Main method. Creates Game constructor and initializes game start.
     * @param args
     */
    public static void main(String[] args) {
        new Game();
    }

}