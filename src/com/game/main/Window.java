package com.game.main;

import javax.swing.*;
import java.awt.*;

//This class creates visible window
public class Window extends Canvas {

//  VARIABLES
//  ---------
    static Label label;

//  CONSTRUCTOR
//  -----------
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        label = new Label("0");
        label.setBackground(Color.GRAY);
        label.setFont(new Font(Font.DIALOG,Font.BOLD,18));
        label.setForeground(Color.WHITE);
        label.setSize(40,18);
        label.setLocation(238,962);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Allows to stop the program from frame after clicking X
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  //Sets location of the window to center after initialization
        frame.add(label);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

//  METHODS
//  -------

}