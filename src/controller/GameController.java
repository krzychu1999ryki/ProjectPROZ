package controller;

import javafx.animation.AnimationTimer;
import model.Player;
import model.PlayersBullet;
import view.GameViewManager;

public class GameController {
    private AnimationTimer gameTimer;
    private GameViewManager viewManager;
    private Player player;
    private PlayersBullet bullet;
    private double playersBulletCoolDown;


    public GameController(GameViewManager viewManager){
        this.viewManager = viewManager;
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
                    bullet = new PlayersBullet(player.getPositionX(), player.getPositionY(),
                            -player.getBulletSpeed(), player.getSpeedY()/2);
                    viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY());
                    playersBulletCoolDown = player.getBulletCoolDown();
                }

                if(bullet != null){
                    bullet.setPositionX(bullet.getPositionX() + bullet.getSpeedX());
                    bullet.setPositionY(bullet.getPositionY() + bullet.getSpeedY());
                    System.out.println(bullet.getPositionX());
                    System.out.println(bullet.getPositionY());
                }



                viewManager.movePlayer(player.getPositionX(),player.getPositionY());
                if(bullet != null){
                    viewManager.movePlayersBullets(bullet.getPositionX(),bullet.getPositionY());
                }
                if(playersBulletCoolDown != 0){
                    playersBulletCoolDown -= 1;
                }
                System.out.println(playersBulletCoolDown);
            }
        };
        gameTimer.start();
    }
}
