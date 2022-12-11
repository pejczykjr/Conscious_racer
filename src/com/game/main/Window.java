package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class creates visible window
public class Window extends Canvas {

//  CONSTRUCTOR
//  -----------
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Allows to stop the program from frame window
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  //Sets location to center after initialization
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}