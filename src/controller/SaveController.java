package controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import model.Creature;


public class SaveController {


    public void savePlayer(Creature loadedPlayer) throws IOException {
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

    public void loadPlayer(Creature loadedPlayer) {
        File saveFile = new File("C:\\Users\\Arcangelo\\IdeaProjects\\Project\\src\\SavedPlayer.txt");
        if (saveFile.exists()) {
            Scanner loading = new Scanner("C:\\Users\\Arcangelo\\IdeaProjects\\Project\\src\\SavedPlayer.txt");

            //loadedPlayer.setMaxSpeed() = loading.nextLine();
            //loadedPlayer.setAcceleration() = loading.nextLine();

        }
    }

}
