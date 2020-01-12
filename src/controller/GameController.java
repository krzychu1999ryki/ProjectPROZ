package controller;

import javafx.animation.AnimationTimer;
import model.Creature;
import model.PlayersBullet;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private AnimationTimer gameTimer;
    private GameViewManager viewManager;

    private Creature player;
    private List<PlayersBullet> playersBullets;
    private double playersBulletCoolDown;

    private List<Creature> enemies;


    public GameController(GameViewManager viewManager){
        this.viewManager = viewManager;
        this.playersBullets = new ArrayList<>();
        createGameLoop();
        player = new Creature();
        enemies = new ArrayList<>();
        enemies.add(new Creature(100,100,10,0,0,0, 64,64, 28 ));
        viewManager.createEnemy(100,100);
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

                for(int i =0; i < playersBullets.size(); ++i){
                    playersBullets.get(i).setPositionX(playersBullets.get(i).getPositionX() + playersBullets.get(i).getSpeedX());
                    playersBullets.get(i).setPositionY(playersBullets.get(i).getPositionY() + playersBullets.get(i).getSpeedY());
                }

                for(int i = 0; i < playersBullets.size(); ++i){
                    if(playersBullets.get(i).getPositionX() < -20 || playersBullets.get(i).getPositionX() > viewManager.getGameWidth()||
                    playersBullets.get(i).getPositionY() < -20 || playersBullets.get(i).getPositionY() > viewManager.getGameHeight()){
                        playersBullets.remove(i);
                        viewManager.deletePlayersBullet(i);
                    }
                }

                for(int i = 0; i < playersBullets.size(); ++i){
                    viewManager.movePlayersBullet(playersBullets.get(i).getPositionX(),playersBullets.get(i).getPositionY(),i);
                }

                handlePlayersBulletsCollisions();

                if(playersBulletCoolDown != 0){
                    playersBulletCoolDown -= 1;
                }
            }
        };
        gameTimer.start();
    }

    private double calculateDistance(Creature a, Creature b){
        return Math.sqrt(Math.pow(a.centreX() - b.centreX(), 2) +
                Math.pow(a.centreY() - b.centreY(), 2)) - (a.getRadius() + b.getRadius());
    }

    private double calculateDistance(Creature a, PlayersBullet b){
       return Math.sqrt(Math.pow(a.centreX() - b.centreX(), 2) +
                Math.pow(a.centreY() - b.centreY(), 2)) - (a.getRadius() + b.getRadius());
    }

    private void handlePlayersBulletsCollisions(){
        boolean bulletRemoved = false;
        for(int i = 0; i < playersBullets.size(); i++){
            for(int a = 0; a < enemies.size(); a++){
                if(calculateDistance(enemies.get(a), playersBullets.get(i)) <= 0){
                    playersBullets.remove(i);
                    viewManager.deletePlayersBullet(i);
                    enemies.get(a).setHitPoints(enemies.get(a).getHitPoints() - player.getAttack());
                    System.out.println(enemies.get(a).getHitPoints());
                    if(enemies.get(a).getHitPoints() <= 0){
                        enemies.remove(a);
                        viewManager.deleteEnemy(a);
                    }

                    bulletRemoved = true;
                }
            }
        }
        if(bulletRemoved == true){
            handlePlayersBulletsCollisions();
        }
    }
}

