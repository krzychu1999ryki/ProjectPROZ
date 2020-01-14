package controller;


import model.Creature;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class SaveController {


    public void savePlayer(Creature loadedPlayer) throws IOException {
        File file = new File("C:\\Users\\krzyc\\IdeaProjects\\Project\\SavedPlayer.txt");
        file.delete();

        File saveFile = new File("SavedPlayer.txt");
        saveFile.createNewFile();

        PrintWriter saving = new PrintWriter("SavedPlayer.txt");

        saving.println(loadedPlayer.getAttack());
        saving.println(loadedPlayer.getMaxSpeed());
        saving.println(loadedPlayer.getMaxHitPoints());
        saving.println(loadedPlayer.getSkillPoints());
        saving.close();
    }

    public void loadPlayer(Creature loadedPlayer) throws IOException {
        File saveFile = new File("SavedPlayer.txt");
        if (saveFile.exists()) {
            Path path = Paths.get("SavedPlayer.txt");
            Scanner loading = new Scanner(path);

            //String attack1 = loading.nextLine();
            //System.out.println(attack1);

            loadedPlayer.setAttack(Double.parseDouble(loading.nextLine()));
            loadedPlayer.setMaxSpeed(Double.parseDouble(loading.nextLine()));
            loadedPlayer.setMaxHitPoints(Double.parseDouble(loading.nextLine()));
            loadedPlayer.setSkillPoints(Integer.parseInt(loading.nextLine()));

        }
    }

}
