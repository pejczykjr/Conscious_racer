package com.game.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighestScore{

//  VARIABLES
//  ---------

    private final Hud hud;

    private File file;
    private Scanner scanner;
    private FileWriter writer;

    private int fileScore;
    String noData;
    private String data;
    private boolean fileScoreExists;

//  CONSTRUCTOR
//  -----------
    public HighestScore(Hud hud){
        this.hud = hud;

        fileScoreExists = true;
        noData = "There isn't any highest score. Play a game!";

        try {
            file = new File("Highest score.txt");

            if(file.createNewFile()){
                writer = new FileWriter(file);
                writer.write(noData);
                fileScoreExists = false;

                writer.close();
            }
            else {
                scanner = new Scanner(file);
                data = scanner.nextLine();

                if(!data.equals(noData))
                    fileScore = Integer.parseInt(data);
                else
                    fileScoreExists = false;

                scanner.close();
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

//  METHODS
//  -------
    public void writeHighestScore(String fileScore) throws IOException {
        writer = new FileWriter(file);
        writer.write(fileScore);

        writer.close();
        System.out.println(fileScore);
    }

    //Setters
    public void setFileScore(int bestScore){
        this.fileScore = bestScore;
    }

    public void setFileScoreExists(boolean fileScoreExists){
        this.fileScoreExists = fileScoreExists;
    }

    //Getters
    public int getFileScore(){
        return this.fileScore;
    }

    public boolean getFileScoreExists(){
        return this.fileScoreExists;
    }

//  TICK AND RENDER METHODS
//  -----------------------
}