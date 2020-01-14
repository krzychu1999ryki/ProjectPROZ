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
import model.Creature;

import java.util.ArrayList;
import java.util.List;


public class GameViewManager {
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private ImageView playerImage;
    private ImageView doors;
    private List<ImageView> playersBulletsImages;
    private List<ImageView> enemiesImages;
    private List<ImageView> playersLife;
    private List<ImageView> enemiesBulletsImages;

    private GameController gameController;

    private static final String BACKGROUND_1 = "view/resources/ground_01.png";
    private static final String PLAYER_STOP = "view/resources/player_stop.png";
    private static final String PLAYERS_BULLET = "view/resources/meteorBrown_tiny1.png";
    private static final String ENEMY_BULLET = "view/resources/meteorGrey_tiny1.png";
    private static final String DOORS_CLOSED = "view/resources/rpgTile189.png";
    private static final String DOORS_OPENED = "view/resources/rpgTile169.png";
    private static final String PLAYERS_LIFE = "view/resources/powerupGreen_shield.png";
    private static final String ENEMY_1 = "view/resources/FlameDemon.png";

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


    public GameViewManager(Creature loadedPlayer, ViewManager menuManager, int location) {
        gamePane = new AnchorPane();
        doors = new ImageView(DOORS_OPENED);
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        playersLife = new ArrayList<>();
        playersBulletsImages = new ArrayList<>();
        enemiesBulletsImages = new ArrayList<>();
        enemiesImages = new ArrayList<>();
        createPlayer();
        gameStage.setScene(gameScene);
        createKeysListeners();
        createGameController(loadedPlayer, menuManager, location);
        gameStage.show();
        setBackground(BACKGROUND_1);
        createPlayersLife();
    }

    private void createPlayer() {
        playerImage = new ImageView(PLAYER_STOP);
        playerImage.setLayoutX(GAME_WIDTH / 2);
        playerImage.setLayoutY(GAME_HEIGHT / 2);
        gamePane.getChildren().add(playerImage);
    }

    private void createGameController(Creature loadedPlayer, ViewManager menuManager, int location) {
        gameController = new GameController(this, loadedPlayer, menuManager, location);
    }

    private void createKeysListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.W) {
                    isWKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.A) {
                    isAKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.S) {
                    isSKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.D) {
                    isDKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.A) {
                    isAKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.W) {
                    isWKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.S) {
                    isSKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.D) {
                    isDKeyPressed = false;
                }
            }
        });
    }

    public void movePlayer(double x, double y) {
        playerImage.setLayoutX(x);
        playerImage.setLayoutY(y);
    }

    public void moveEnemy(double x, double y, int number){
        enemiesImages.get(number).setLayoutX(x);
        enemiesImages.get(number).setLayoutY(y);
    }

    private void setBackground(String background) {
        Image backgroundImage = new Image(background, 64, 64, false, true);
        BackgroundImage newBackgroundImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(newBackgroundImage));
    }

    public void createPlayersBullet(double x, double y, int number) {
        playersBulletsImages.add(new ImageView(PLAYERS_BULLET));
        playersBulletsImages.get(number - 1).setLayoutY(y);
        playersBulletsImages.get(number - 1).setLayoutX(x);
        gamePane.getChildren().add(playersBulletsImages.get(number - 1));
    }

    public void movePlayersBullet(double x, double y, int number) {
        playersBulletsImages.get(number).setLayoutX(x);
        playersBulletsImages.get(number).setLayoutY(y);
    }

    public void createEnemyBullet(double x, double y, int number){
        enemiesBulletsImages.add(new ImageView(ENEMY_BULLET));
        enemiesBulletsImages.get(number - 1).setLayoutY(y);
        enemiesBulletsImages.get(number - 1).setLayoutX(x);
        gamePane.getChildren().add(enemiesBulletsImages.get(number - 1));
    }

    public void moveEnemyBullet(double x, double y, int number) {
        enemiesBulletsImages.get(number).setLayoutX(x);
        enemiesBulletsImages.get(number).setLayoutY(y);
    }

    public void deletePlayersBullet(int number) {
        gamePane.getChildren().remove(playersBulletsImages.get(number));
        playersBulletsImages.remove(number);
    }

    public void deleteEnemyBullet(int number) {
        gamePane.getChildren().remove(enemiesBulletsImages.get(number));
        enemiesBulletsImages.remove(number);
    }

    public void createEnemy(double x, double y) {
        ImageView newEnemy = new ImageView(ENEMY_1);
        newEnemy.setLayoutX(x);
        newEnemy.setLayoutY(y);
        enemiesImages.add(newEnemy);
        gamePane.getChildren().add(enemiesImages.get(enemiesImages.size() - 1));
    }

    public void deleteEnemy(int number) {
        gamePane.getChildren().remove(enemiesImages.get(number));
        enemiesImages.remove(number);
    }

    public void createDoors(double x, double y){
        doors.setLayoutX(x);
        doors.setLayoutY(y);
        gamePane.getChildren().add(doors);
    }

    public void deleteDoors(){
        gamePane.getChildren().remove(doors);
    }

    public void createPlayersLife(){
        int x = GAME_WIDTH - 44;
        int y = 10;
        for(int i = 0; i < gameController.getPlayersHitPoints(); ++i){
            playersLife.add(new ImageView(PLAYERS_LIFE));
            playersLife.get(i).setLayoutX(x);
            playersLife.get(i).setLayoutY(y);
            gamePane.getChildren().add(playersLife.get(i));
            x -= 35;
            if(i % 10 == 9){
                y += 35;
                x += 350;
            }
        }
    }

    public void removePlayersLife(double damage){
        for(int i=0; i < damage; i++ ){
            if( playersLife.size() > 0) {
                gamePane.getChildren().remove(playersLife.get(playersLife.size() - 1));
                playersLife.remove(playersLife.size() - 1);
            }
        }
    }


    public boolean getLeftPressed() {
        return isLeftKeyPressed;
    }

    public boolean getRightPressed() {
        return isRightKeyPressed;
    }

    public boolean getDownPressed() {
        return isDownKeyPressed;
    }

    public boolean getUpPressed() {
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

    public boolean getDKeyPressed() {
        return isDKeyPressed;
    }

    public boolean getSKeyPressed() {
        return isSKeyPressed;
    }

    public boolean getWKeyPressed() {
        return isWKeyPressed;
    }

    public Stage getGameStage() {
        return gameStage;
    }


}
