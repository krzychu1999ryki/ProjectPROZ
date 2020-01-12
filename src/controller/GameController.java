package controller;

import javafx.animation.AnimationTimer;
import model.Player;
import model.PlayersBullet;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private AnimationTimer gameTimer;
    private GameViewManager viewManager;
    private Player player;
    //private PlayersBullet[] playersBullets;
    private List<PlayersBullet> playersBullets;
    private double playersBulletCoolDown;


    public GameController(GameViewManager viewManager){
        this.viewManager = viewManager;
        this.playersBullets = new ArrayList<>();
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

                if(viewManager.getAKeyPressed() == true && playersBulletCoolDown == 0){
                    playersBullets.add(new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            -player.getBulletSpeed(), player.getSpeedY()/2));
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getWKeyPressed() == true && playersBulletCoolDown == 0){
                    playersBullets.add(new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getSpeedX()/2, -player.getBulletSpeed()));
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getSKeyPressed() == true && playersBulletCoolDown == 0){
                    playersBullets.add(new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getSpeedX()/2, player.getBulletSpeed()));
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(viewManager.getDKeyPressed() == true && playersBulletCoolDown == 0){
                    playersBullets.add(new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            player.getBulletSpeed(), player.getSpeedY()/2));
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                viewManager.movePlayer(player.getPositionX(),player.getPositionY());

                //deleteOutOfScreenBullets();

                for(int i =0; i < playersBullets.size(); ++i){
                    playersBullets.get(i).setPositionX(playersBullets.get(i).getPositionX() + playersBullets.get(i).getSpeedX());
                    playersBullets.get(i).setPositionY(playersBullets.get(i).getPositionY() + playersBullets.get(i).getSpeedY());
                    System.out.println(playersBullets.get(i).getPositionX());
                    System.out.println(playersBullets.get(i).getPositionY());

                }

                for(int i = 0; i < playersBullets.size(); ++i){
                    viewManager.movePlayersBullet(playersBullets.get(i).getPositionX(),playersBullets.get(i).getPositionY(),i);
                }

                if(playersBulletCoolDown != 0){
                    playersBulletCoolDown -= 1;
                }
            }
        };
        gameTimer.start();
    }

    //private void deleteOutOfScreenBullets(){
    //    for(int i = 0; i < playersBulletsNumber; ++i ){
     //       if(playersBullets[i].getPositionY() < 0 || playersBullets[i].getPositionX() < 0 ||
     //               playersBullets[i].getPositionX() > viewManager.getGameWidth() ||
      //              playersBullets[i].getPositionY() > viewManager.getGameHeight()){
      //          playersBulletsNumber -= 1;
       //         viewManager.removePlayersBullet(i, playersBulletsNumber);
      //          for(int a = i; a < playersBulletsNumber - 1; ++a){
      //          playersBullets[a] = playersBullets[a+1];
       //         }
      //          playersBullets[playersBulletsNumber - 1] = null;
      //          --i;
      //      }
      //  }




}

