package model;

public class PlayersBullet {

    private double speedX;
    private double speedY;
    private double positionY;
    private double positionX;
    private double rotation;

    private final static  int MAIN_SPEED = 15;

    public PlayersBullet() {
        speedX = 0;
        speedY = 0;
        positionX = 0;
        positionY = 0;
        rotation = 0;
    }

    public PlayersBullet(double x, double y, double speedX, double speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.positionX = x;
        this.positionY = y;
        rotation = 0;
    }




    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public static int getMainSpeed() {
        return MAIN_SPEED;
    }

}
