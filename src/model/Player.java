package model;

public class Player {
    private double positionX;
    private double positionY;
    private double hitPoints;
    private double speedX;
    private double speedY;
    private double maxSpeed;
    private double acceleration;
    private double bulletCoolDown;

    private final static double BULLET_SPEED = 10;

    public Player(){
        positionX = 500;
        positionY = 500;
        hitPoints = 10;
        speedX = 0;
        speedY = 0;
        maxSpeed = 8;
        acceleration = 0.5;
        bulletCoolDown = 20;
    }

    public Player(double x,double y,double hp, double maxSpeed, double acceleration, double bulletCoolDown){
        this.positionX = x;
        this.positionY = y;
        this.hitPoints = hp;
        speedX = 0;
        speedY = 0;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.bulletCoolDown = bulletCoolDown;
    }

    public void setPositionX(double x) {
        this.positionX = x;
    }

    public void setPositionY(double y){
        this.positionY = y;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getPositionX(){
        return positionX;
    }

    public double getPositionY(){
        return positionY;
    }

    public double getSpeedX(){
        return speedX;
    }

    public double getSpeedY(){
        return speedY;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public static double getBulletSpeed() {
        return BULLET_SPEED;
    }

    public double getBulletCoolDown() {
        return bulletCoolDown;
    }


}
