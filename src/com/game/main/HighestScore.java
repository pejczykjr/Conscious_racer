package com.game.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class responsible for File handling. It creates new file when necessary, reads it and writes to it highest<br>
 * achieved score.
 * @author Mateusz Pieczykolan
 */
public class HighestScore{

    /**
     * Declaration of hud instance.
     */
    private final Hud hud;

    /**
     * Declaration of file instance.
     */
    private File file;
    /**
     * Declaration of scanner instance.
     */
    private Scanner scanner;
    /**
     * Declaration of writer instance.
     */
    private FileWriter writer;

    /**
     * It keeps value of a score from a file.
     */
    private int fileScore;
    /**
     * It keeps string indicating that there is no score in a file.
     */
    String noData;
    /**
     * It keeps string indicating that there is a score in a file.
     */
    private String data;
    /**
     * It indicates if there is saved the highest score in a file.
     */
    private boolean fileScoreExists;

    /**
     * Constructor, initializes variables. It creates a new file (that will keep the highest score),<br>
     * if it doesn't exist. If it exists, it gets values from file and assigns to variable.
     * @param hud passing the hud instance
     */
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

    /**
     * This method saves the new highest score in the file.
     * @param fileScore passing a file we want to save in the highest score
     * @throws IOException when error occurs while file handling
     */
    public void writeHighestScore(String fileScore) throws IOException {
        writer = new FileWriter(file);
        writer.write(fileScore);

        writer.close();
        System.out.println(fileScore);
    }

    /**
     * "fileScore" Setter
     * @param fileScore sets "fileScore"
     */
    public void setFileScore(int fileScore){
        this.fileScore = fileScore;
    }

    /**
     * "fileScoreExists" Setter
     * @param fileScoreExists sets "fileScoreExists" to true if there is any score in the file
     */
    public void setFileScoreExists(boolean fileScoreExists){
        this.fileScoreExists = fileScoreExists;
    }

    /**
     * "fileScore" Getter
     * @return returns "fileScore"
     */
    public int getFileScore(){
        return this.fileScore;
    }

    /**
     * "fileScoreExists" Getter
     * @return returns "fileScoreExists""
     */
    public boolean getFileScoreExists(){
        return this.fileScoreExists;
    }

}