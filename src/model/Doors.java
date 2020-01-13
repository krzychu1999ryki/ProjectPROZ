package model;

public class Doors {
    private double positionX;
    private double positionY;
    private boolean isOpen;

    private final static int DOORS_SIZE = 64;
    private final static int DOORS_RADIUS = 32;



    public Doors(double x, double y, boolean isOpen) {
        this.positionX = x;
        this.positionY = y;
        this.isOpen = isOpen;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double centreX(){
        return positionX+DOORS_SIZE/2;
    }

    public double centreY(){
        return positionY+DOORS_SIZE/2;
    }

    public static int getRadius() {
        return DOORS_RADIUS;
    }

    public static int getSize() {
        return DOORS_SIZE;
    }
}
