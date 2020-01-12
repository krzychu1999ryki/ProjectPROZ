package controller;

import javafx.animation.AnimationTimer;
import model.Player;
import model.PlayersBullet;
import view.GameViewManager;

public class GameController {
    private AnimationTimer gameTimer;
    private GameViewManager viewManager;
    private Player player;
    private PlayersBullet[] playersBullets;
    private int playersBulletsNumber;
    private double playersBulletCoolDown;


    public GameController(GameViewManager viewManager){
        this.viewManager = viewManager;
        this.playersBullets = new PlayersBullet[10];
        this.playersBulletsNumber = 0;
        createGameLoop();
        player = new Player();
        playersBulletCoolDown = 0;
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(viewManager.getLeftPressed()){
                    if(player.getSpeedX() > -player.getMaxSpeed()){
                        player.setSpeedX(player.getSpeedX() - player.getAcceleration());
                    }
                }else if(player.getSpeedX() < 0){
                    player.setSpeedX(player.getSpeedX() + player.getAcceleration());
                }

                if(viewManager.getRightPressed()){
                    if(player.getSpeedX() < player.getMaxSpeed()){
                        player.setSpeedX(player.getSpeedX() + player.getAcceleration());
                    }
                }else if(player.getSpeedX() > 0){
                    player.setSpeedX(player.getSpeedX() - player.getAcceleration());
                }

                if(viewManager.getDownPressed()){
                    if(player.getSpeedY() < player.getMaxSpeed()){
                        player.setSpeedY(player.getSpeedY() + player.getAcceleration());
                    }
                }else if(player.getSpeedY() > 0){
                    player.setSpeedY(player.getSpeedY() - player.getAcceleration());
                }

                if(viewManager.getUpPressed()){
                    if(player.getSpeedY() > -player.getMaxSpeed()){
                        player.setSpeedY(player.getSpeedY() - player.getAcceleration());
                    }
                }else if(player.getSpeedY() < 0){
                    player.setSpeedY(player.getSpeedY() + player.getAcceleration());
                }

                if(viewManager.getRightPressed() && viewManager.getLeftPressed()){
                    if(player.getSpeedX() > 0){
                        player.setSpeedX(player.getSpeedX() - player.getAcceleration());
                    }else if(player.getSpeedX() < 0){
                        player.setSpeedX(player.getSpeedX() + player.getAcceleration());
                    }
                }

                if(viewManager.getUpPressed() && viewManager.getDownPressed()){
                    if(player.getSpeedY() > 0){
                        player.setSpeedY(player.getSpeedY() - player.getAcceleration());
                    }else if(player.getSpeedY() < 0){
                        player.setSpeedY(player.getSpeedY() + player.getAcceleration());
                    }
                }

                player.setPositionX(player.getPositionX() + player.getSpeedX());
                player.setPositionY(player.getPositionY() + player.getSpeedY());

                if(player.getPositionX() > viewManager.getGameWidth() - viewManager.getPlayerWidth()){
                    player.setPositionX(viewManager.getGameWidth() - viewManager.getPlayerWidth());
                    player.setSpeedX(0);
                }

                if(player.getPositionX() < 0){
                    player.setPositionX(0);
                    player.setSpeedX(0);
                }

                if(player.getPositionY() > viewManager.getGameHeight() - viewManager.getPlayerHeight()){
                    player.setPositionY(viewManager.getGameHeight() - viewManager.getPlayerHeight());
                    player.setSpeedY(0);
                }

                if(player.getPositionY() < 0){
                    player.setPositionY(0);
                    player.setSpeedY(0);
                }

                if(viewManager.getAKeyPressed() == true && playersBulletCoolDown == 0 && playersBulletsNumber < 10){
                    playersBullets[playersBulletsNumber] = new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            -player.getBulletSpeed(), player.getSpeedY()/2);
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBulletsNumber);
                    playersBulletsNumber += 1;
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getWKeyPressed() == true && playersBulletCoolDown == 0 && playersBulletsNumber < 10){
                    playersBullets[playersBulletsNumber] = new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getSpeedX()/2, -player.getBulletSpeed());
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBulletsNumber);
                    playersBulletsNumber += 1;
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getSKeyPressed() == true && playersBulletCoolDown == 0 && playersBulletsNumber < 10){
                    playersBullets[playersBulletsNumber] = new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getSpeedX()/2, player.getBulletSpeed());
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBulletsNumber);
                    playersBulletsNumber += 1;
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getDKeyPressed() == true && playersBulletCoolDown == 0 && playersBulletsNumber < 10){
                    playersBullets[playersBulletsNumber] = new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getBulletSpeed(), player.getSpeedY()/2);
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBulletsNumber);
                    playersBulletsNumber += 1;
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                for(int i =0; i < playersBulletsNumber; ++i){
                    playersBullets[i].setPositionX(playersBullets[i].getPositionX() + playersBullets[i].getSpeedX());
                    playersBullets[i].setPositionY(playersBullets[i].getPositionY() + playersBullets[i].getSpeedY());
                    System.out.println(playersBullets[i].getPositionX());
                    System.out.println(playersBullets[i].getPositionY());

                }

                viewManager.movePlayer(player.getPositionX(),player.getPositionY());

                for(int i = 0; i < playersBulletsNumber; ++i){
                    viewManager.movePlayersBullet(playersBullets[i].getPositionX(),playersBullets[i].getPositionY(),i);
                }

                if(playersBulletCoolDown != 0){
                    playersBulletCoolDown -= 1;
                }
            }
        };
        gameTimer.start();
    }


}
