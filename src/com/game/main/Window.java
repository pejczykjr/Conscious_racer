package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class creates visible window

/**
 * Class responsible for app window creation.
 * @author Mateusz Pieczykolan
 */
public class Window extends Canvas {
    /**
     * App window constructor.
     * @param width width of the game window
     * @param height height of the game window
     * @param title window name
     * @param game passing the game
     */
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Allows to stop the program from frame after clicking X.
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);                      //Sets location of the window to center after initialization.
        frame.add(game);
        frame.setVisible(true);
        game.start();                                           //Begins the execution of a thread and starts the game.
    }

}