package controller;


import model.Player;
import view.ViewManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class SaveController {


    public void savePlayer(Player loadedPlayer) throws IOException {
            File saveFile = new File("C:\\Users\\Arcangelo\\IdeaProjects\\Project\\src\\SavedPlayer.txt");
            if (!saveFile.exists()) {
                try {
                    saveFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            PrintWriter saving = new PrintWriter("C:\\Users\\Arcangelo\\IdeaProjects\\Project\\src\\SavedPlayer.txt");

            saving.println(loadedPlayer.getMaxSpeed());
            saving.println(loadedPlayer.getAcceleration());
            saving.close();
    }



}
