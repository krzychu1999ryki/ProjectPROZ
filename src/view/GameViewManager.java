package view;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private ImageView playerImage;
    private ImageView playersBulletImage;

    private GameController gameController;

    private static final String BACKGROUND_1 = "view/resources/ground_01.png";
    private static final String PLAYER_STOP = "view/resources/player_stop.png";
    private static final String PLAYERS_BULLET = "view/resources/meteorBrown_tiny1.png";


    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    private static final int PLAYER_WIDTH = 64;
    private static final int PLAYER_HEIGHT = 64;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean isWKeyPressed;
    private boolean isAKeyPressed;
    private boolean isSKeyPressed;
    private boolean isDKeyPressed;



    public GameViewManager(){
        initializeGame();
    }

    private void initializeGame() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        createPlayer();
        gameStage.setScene(gameScene);
        createKeysListeners();
        createGameController();
        gameStage.show();
        setBackground(BACKGROUND_1);
    }

    private void createPlayer() {
        playerImage = new ImageView(PLAYER_STOP);
        playerImage.setLayoutX(GAME_WIDTH/2);
        playerImage.setLayoutY(GAME_HEIGHT/2);
        gamePane.getChildren().add(playerImage);
    }

    private void createGameController() {
        gameController = new GameController(this);
    }

    private void createKeysListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.W){
                    isWKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.A){
                    isAKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.S){
                    isSKeyPressed = true;
                } else if(keyEvent.getCode() == KeyCode.D){
                    isDKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.A) {
                    isAKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.W) {
                    isWKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.S) {
                    isSKeyPressed = false;
                }else if (keyEvent.getCode() == KeyCode.D) {
                    isDKeyPressed = false;
                }
            }
        });
    };

    public void movePlayer(double x, double y){
        playerImage.setLayoutX(x);
        playerImage.setLayoutY(y);
    }

    private void setBackground(String background){
        Image backgroundImage = new Image(background, 64, 64, false, true);
        BackgroundImage newBackgroundImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(newBackgroundImage));
    }

    public void createPlayersBullet(double x, double y){
        playersBulletImage = new ImageView(PLAYERS_BULLET);
        playersBulletImage.setLayoutY(y);
        playersBulletImage.setLayoutX(x);
        gamePane.getChildren().add(playersBulletImage);
    }

    public void movePlayersBullets(double x, double y){
        playersBulletImage.setLayoutX(x);
        playersBulletImage.setLayoutY(y);
    }

    public boolean getLeftPressed(){
        return isLeftKeyPressed;
    }

    public boolean getRightPressed(){
        return isRightKeyPressed;
    }

    public boolean getDownPressed(){
        return isDownKeyPressed;
    }

    public boolean getUpPressed(){
        return isUpKeyPressed;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }

    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getPlayerWidth() {
        return PLAYER_WIDTH;
    }

    public static int getPlayerHeight() {
        return PLAYER_HEIGHT;
    }

    public boolean getAKeyPressed() {
        return isAKeyPressed;
    }
}
