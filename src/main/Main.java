package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameViewManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{
            GameViewManager gameManager = new GameViewManager();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
