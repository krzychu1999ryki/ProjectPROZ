package model;

public class Bullet {

    private double speedX;
    private double speedY;
    private double positionY;
    private double positionX;
    private double rotation;
    private double radius;
    private double sizeX;
    private double sizeY;
    private double damage;

    public Bullet() {
        speedX = 0;
        speedY = 0;
        positionX = 0;
        positionY = 0;
        rotation = 0;
        sizeX = 18;
        sizeY = 18;
        radius = 8;
        damage = 1;
    }

    public Bullet(double x, double y, double speedX, double speedY, double damage) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.positionX = x;
        this.positionY = y;
        rotation = 0;
        radius = 8;
        this.damage = damage;
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

    public double getRadius() { return radius; }

    public double centreX() {
        return positionX + sizeX/2;
    }

    public double centreY() {
        return positionY + sizeY/2;
    }

    public double getDamage() {
        return damage;
    }
}
