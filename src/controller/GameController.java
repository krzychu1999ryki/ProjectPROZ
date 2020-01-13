package controller;

import javafx.animation.AnimationTimer;
import model.Creature;
import model.Bullet;
import view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private AnimationTimer gameTimer;
    private GameViewManager viewManager;

    private Creature player;
    private List<Bullet> playersBullets;
    private double playersBulletCoolDown;
    private double enemiesBulletCoolDown;

    private List<Creature> enemies;
    private List<Bullet> enemiesBullets;


    public GameController(GameViewManager viewManager){
        this.viewManager = viewManager;
        this.playersBullets = new ArrayList<>();
        createGameLoop();
        player = new Creature();
        enemies = new ArrayList<>();
        enemiesBullets = new ArrayList<>();
        enemies.add(new Creature(100,100,4,0,0,50, 64,64, 28 , 1));
        viewManager.createEnemy(100,100);
        enemies.add(new Creature(600,200,4,0,0,50, 64,64, 28, 1 ));
        viewManager.createEnemy(600,200);
        playersBulletCoolDown = 0;
        enemiesBulletCoolDown = 50;
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                movePlayer();
                correctPlayersPosition();
                shootPlayersBullet();

                if(enemiesBulletCoolDown == 0){
                    for(int i = 0; i < enemies.size(); i++){
                        shootEnemyBullet(enemies.get(i));
                    }
                    if(enemies.size() > 0){
                        enemiesBulletCoolDown = enemies.get(0).getBulletCoolDown();
                    }
                }

                viewManager.movePlayer(player.getPositionX(),player.getPositionY());

                movePlayersBullets();
                moveEnemiesBullets();

                for(int i = 0; i < playersBullets.size(); ++i){
                    viewManager.movePlayersBullet(playersBullets.get(i).getPositionX(),playersBullets.get(i).getPositionY(),i);
                }

                for(int i = 0; i < enemiesBullets.size(); ++i){
                    viewManager.moveEnemyBullet(enemiesBullets.get(i).getPositionX(),enemiesBullets.get(i).getPositionY(),i);
                }

                handlePlayersBulletsCollisions();
                handleEnemiesBulletsCollisions();

                if(playersBulletCoolDown != 0){
                    playersBulletCoolDown -= 1;
                }

                if(enemiesBulletCoolDown != 0){
                    enemiesBulletCoolDown -= 1;
                }
            }
        };
        gameTimer.start();
    }

    private void movePlayer(){
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
    }

    private void correctPlayersPosition(){
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
    }

    private void shootPlayersBullet(){
        if(viewManager.getAKeyPressed() == true && playersBulletCoolDown == 0){
            playersBullets.add(new Bullet(player.getPositionX(), player.getPositionY(),
                    -player.getBulletSpeed(), player.getSpeedY()/2, player.getAttack()));
            viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
            playersBulletCoolDown = player.getBulletCoolDown();
        }

        if(viewManager.getWKeyPressed() == true && playersBulletCoolDown == 0){
            playersBullets.add(new Bullet(player.getPositionX(), player.getPositionY(),
                    player.getSpeedX()/2, -player.getBulletSpeed(), player.getAttack()));
            viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
            playersBulletCoolDown = player.getBulletCoolDown();
        }

        if(viewManager.getSKeyPressed() == true && playersBulletCoolDown == 0){
            playersBullets.add(new Bullet(player.getPositionX(), player.getPositionY(),
                    player.getSpeedX()/2, player.getBulletSpeed(), player.getAttack()));
            viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
            playersBulletCoolDown = player.getBulletCoolDown();
        }

        if(viewManager.getDKeyPressed() == true && playersBulletCoolDown == 0){
            playersBullets.add(new Bullet(player.getPositionX(), player.getPositionY(),
                    player.getBulletSpeed(), player.getSpeedY()/2, player.getAttack()));
            viewManager.createPlayersBullet(player.getPositionX(),player.getPositionY(),playersBullets.size());
            playersBulletCoolDown = player.getBulletCoolDown();
        }
    };

    private void movePlayersBullets(){
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
    };

    private void moveEnemiesBullets(){
        for(int i =0; i < enemiesBullets.size(); ++i){
            enemiesBullets.get(i).setPositionX(enemiesBullets.get(i).getPositionX() + enemiesBullets.get(i).getSpeedX());
            enemiesBullets.get(i).setPositionY(enemiesBullets.get(i).getPositionY() + enemiesBullets.get(i).getSpeedY());
        }

        for(int i = 0; i < enemiesBullets.size(); ++i){
            if(enemiesBullets.get(i).getPositionX() < -20 || enemiesBullets.get(i).getPositionX() > viewManager.getGameWidth()||
                    enemiesBullets.get(i).getPositionY() < -20 || enemiesBullets.get(i).getPositionY() > viewManager.getGameHeight()){
                enemiesBullets.remove(i);
                viewManager.deleteEnemyBullet(i);
            }
        }
    };

    private double calculateDistance(Creature a, Creature b){
        return Math.sqrt(Math.pow(a.centreX() - b.centreX(), 2) +
                Math.pow(a.centreY() - b.centreY(), 2)) - (a.getRadius() + b.getRadius());
    }

    private double calculateDistance(Creature a, Bullet b){
       return Math.sqrt(Math.pow(a.centreX() - b.centreX(), 2) +
                Math.pow(a.centreY() - b.centreY(), 2)) - (a.getRadius() + b.getRadius());
    }

    private void handlePlayersBulletsCollisions(){
        boolean bulletRemoved = false;
        for(int i = 0; i < playersBullets.size(); i++){
            for(int a = 0; a < enemies.size() && i < playersBullets.size(); a++){
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

    private void handleEnemiesBulletsCollisions(){
        boolean bulletRemoved = false;
        for(int i = 0; i < enemiesBullets.size(); i++){
                if(calculateDistance(player, enemiesBullets.get(i)) <= 0){
                    player.setHitPoints(player.getHitPoints() - enemiesBullets.get(i).getDamage());
                    enemiesBullets.remove(i);
                    viewManager.deleteEnemyBullet(i);
                    System.out.println(player.getHitPoints());
                    if(player.getHitPoints() <= 0){
                        viewManager.getGameStage().close();
                    }
                    bulletRemoved = true;
            }
        }
        if(bulletRemoved == true){
            handleEnemiesBulletsCollisions();
        }
    }

    private void shootEnemyBullet(Creature enemy){
        double distanceX = player.getPositionX() - enemy.getPositionX();
        double distanceY = player.getPositionY() - enemy.getPositionY();
        double ratio = distanceY/distanceX;
        if(ratio < 0) {
            ratio = -ratio;
        }
        double speedX = Math.sqrt(Math.pow(enemy.getBulletSpeed()/2,2)/(1 + Math.pow(ratio,2)));
        double speedY = speedX * ratio;
        if(distanceX < 0){
            speedX = -speedX;
        }
        if(distanceY < 0){
            speedY = -speedY;
        }
        enemiesBullets.add(new Bullet(enemy.getPositionX(), enemy.getPositionY(), speedX, speedY, enemy.getAttack()));
        viewManager.createEnemyBullet(enemy.getPositionX(), enemy.getPositionY(), enemiesBullets.size());
    }
}

