package view;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.MenuButton;
import javafx.scene.text.Font;
import controller.SaveController;
import model.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ViewManager {

    private final String FONT_PATH1 = "src/view/resources/brotherDeluxe1350____2011.ttf";
    private final String FONT_PATH2 = "src/view/resources/Kenney High Square.ttf";
    private static final String BUTTON_RELEASED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('view/resources/buttonLong_brown.png');";
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('view/resources/buttonLong_brown_pressed.png');";
    //private static final String BUTTON_RELEASED_STYLE2 = "-fx-background-color: transparent; -fx-background-image: url('view/resources/buttonSquare_brown.png');";
    //private static final String BUTTON_PRESSED_STYLE2 = "-fx-background-color: transparent; -fx-background-image: url('view/resources/buttonSquare_brown_pressed.png');";
    private static final int STAGE_WIDTH = 800;
    private static final int STAGE_HEIGHT = 600;

    private AnchorPane mainPane;
    private Stage mainStage;

    private MenuButton newGame;
    private MenuButton loadGame;
    private MenuButton helpButton;

    private SaveController saveController;
    private Player loadedPlayer;

    public ViewManager() throws FileNotFoundException {
        this.loadedPlayer = new Player();
        this.saveController = new SaveController();
        mainStage = new Stage();
        loadMenu();
    }

    private void loadMenu() throws FileNotFoundException {
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, STAGE_WIDTH, STAGE_HEIGHT);
        mainStage.setScene(mainScene);
        mainStage.setTitle("The Ruins of Ustro 3");
        mainStage.setResizable(false);
        createButtons();
        createBackground();
        createLogo();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createButtons() throws FileNotFoundException {
        createNewGameButton();
        createLoadGameButton();
        createHelpButton();
        createQuitButton();
    }

    private void createNewGameButton() throws FileNotFoundException {
        newGame = new MenuButton("New Game", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        newGame.setLayoutX(100);
        newGame.setLayoutY(170);
        mainPane.getChildren().add(newGame);

        newGame.setOnAction(newGame -> {
            try {
                setCharacterScreen();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void createLoadGameButton() throws FileNotFoundException {
        loadGame = new MenuButton("Load Game", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        loadGame.setLayoutX(100);
        loadGame.setLayoutY(newGame.getLayoutY() + 100);
        mainPane.getChildren().add(loadGame);

        loadGame.setOnAction(newGame -> {
            try {
                setCharacterScreen();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void createHelpButton() throws FileNotFoundException {
        helpButton = new MenuButton("Help", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        helpButton.setLayoutX(100);
        helpButton.setLayoutY(loadGame.getLayoutY() + 100);
        mainPane.getChildren().add(helpButton);

        helpButton.setOnAction(actionEvent -> {
            try {
                setHelpScreen();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void createQuitButton() throws FileNotFoundException {
        MenuButton quitButton = new MenuButton("Quit", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        quitButton.setLayoutX(100);
        quitButton.setLayoutY(helpButton.getLayoutY() + 100);
        mainPane.getChildren().add(quitButton);

        quitButton.setOnAction(actionEvent -> mainStage.close());
    }

    void createBackButton(AnchorPane pane) throws FileNotFoundException {
        MenuButton backButton = new MenuButton("Back", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        backButton.setLayoutX(10);
        backButton.setLayoutY(540);
        pane.getChildren().add(backButton);

        backButton.setOnAction(actionEvent -> {
            try {
                loadMenu();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private MenuButton createPlayButton(AnchorPane pane, int X, int Y) throws FileNotFoundException {
        MenuButton playButton = new MenuButton("Play", BUTTON_RELEASED_STYLE, BUTTON_PRESSED_STYLE, FONT_PATH2);
        playButton.setLayoutX(X);
        playButton.setLayoutY(Y);
        pane.getChildren().add(playButton);

        return playButton;
    }

    private void setCharacterScreen() throws FileNotFoundException {
        mainPane = new AnchorPane();
        Text title1 = new Text();
        Text title2 = new Text();
        title1.setText("Your character");
        title2.setText("Choose level");
        title1.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 50));
        title2.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 30));

        title1.setLayoutX(210);
        title1.setLayoutY(70);
        title2.setLayoutX(370);
        title2.setLayoutY(150);
        mainPane.getChildren().add(title1);
        mainPane.getChildren().add(title2);

        createBackButton(mainPane);
        MenuButton level1 = createPlayButton(mainPane, 600, 200);
        MenuButton level2 = createPlayButton(mainPane, 600, 330);
        MenuButton level3 = createPlayButton(mainPane, 600, 460);

        Scene scene = new Scene(mainPane, STAGE_WIDTH, STAGE_HEIGHT);
        mainStage.setScene(scene);

        createBackground();

        level1.setOnAction(actionEvent -> {
            try{
                GameViewManager gameManager = new GameViewManager();

                //mainStage.close();
            } catch(Exception e) {
                e.printStackTrace();
            } });

        level2.setOnAction(ActionEvent -> {
            try {
                saveController.savePlayer(loadedPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        level3.setOnAction(ActionEvent -> { int i = 3; });
    }

    private void setHelpScreen() throws FileNotFoundException {
        mainPane = new AnchorPane();
        Text title = new Text();
        Text movement = new Text();
        Text shooting = new Text();
        Text controls = new Text();
        title.setText("Controls");
        movement.setText("Movement");
        shooting.setText("Shooting");
        controls.setText("Arrow keys                                        A S D F");
        title.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 50));
        movement.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 40));
        shooting.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 40));
        controls.setFont(Font.loadFont(new FileInputStream(FONT_PATH2), 40));

        title.setLayoutX(280);
        title.setLayoutY(100);

        movement.setLayoutX(150);
        movement.setLayoutY(250);

        shooting.setLayoutX(450);
        shooting.setLayoutY(250);

        controls.setLayoutX(170);
        controls.setLayoutY(350);

        mainPane.getChildren().add(title);
        mainPane.getChildren().add(movement);
        mainPane.getChildren().add(shooting);
        mainPane.getChildren().add(controls);

        createBackButton(mainPane);

        Scene scene = new Scene(mainPane, STAGE_WIDTH, STAGE_HEIGHT);
        mainStage.setScene(scene);

        createBackground();
    }

    private void createBackground() {
        Image backgroundImage = new Image("view/resources/ground_06.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() throws FileNotFoundException {
        Text logo = new Text();
        logo.setText("The Ruins of Ustro 3");
        logo.setFont(Font.loadFont(new FileInputStream(FONT_PATH1), 50));
        logo.setEffect(new DropShadow());
        logo.setLayoutX(140);
        logo.setLayoutY(100);
        mainPane.getChildren().add(logo);
    }

}
