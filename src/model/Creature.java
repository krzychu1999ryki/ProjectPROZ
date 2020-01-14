package model;

public class Creature {
    private double positionX;
    private double positionY;
    private double hitPoints;
    private double maxHitPoints;
    private double speedX;
    private double speedY;
    private double maxSpeed;
    private double acceleration;
    private double bulletCoolDown;
    private double sizeX;
    private double sizeY;
    private double radius;
    private double attack;
    private int skillPoints;

    private final static double BULLET_SPEED = 10;

    public Creature(){
        positionX = 500;
        positionY = 500;
        hitPoints = 10;
        maxHitPoints = 10;
        speedX = 0;
        speedY = 0;
        maxSpeed = 8;
        acceleration = 0.5;
        bulletCoolDown = 20;
        attack = 1;
        sizeX = 64;
        sizeY = 64;
        radius = 28;
        skillPoints = 0;
    }

    public Creature(double x, double y, double hp, double maxSpeed, double acceleration, double bulletCoolDown,
                    double sizeX, double sizeY, double radius, double attack){
        this.positionX = x;
        this.positionY = y;
        this.hitPoints = hp;
        this.maxHitPoints = hp;
        speedX = 0;
        speedY = 0;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.bulletCoolDown = bulletCoolDown;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.radius = radius;
        this.attack = attack;
        skillPoints = 0;
    }

    public Creature(double x, double y, double hp, double maxSpeed, double acceleration, double bulletCoolDown,
                    double sizeX, double sizeY, double radius, double attack, int skillPoints){
        this.positionX = x;
        this.positionY = y;
        this.hitPoints = hp;
        this.maxHitPoints = hp;
        speedX = 0;
        speedY = 0;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.bulletCoolDown = bulletCoolDown;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.radius = radius;
        this.attack = attack;
        this.skillPoints = skillPoints;
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

    public double getSizeY() {
        return sizeY;
    }

    public double getRadius() {
        return radius;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double centreX() {
        return positionX + sizeX/2;
    }

    public double centreY() {
        return positionY + sizeY/2;
    }

    public double getHitPoints() { return hitPoints; }

    public void setHitPoints(double hitPoints) { this.hitPoints = hitPoints; }

    public double getAttack() { return attack; }

    public void setAttack(double attack) { this.attack = attack; }

    public double getMaxHitPoints() { return maxHitPoints; }

    public void setMaxHitPoints(double maxHitPoints) { this.maxHitPoints = maxHitPoints; }

    public void setMaxSpeed(double maxSpeed) { this.maxSpeed = maxSpeed; }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }
}
