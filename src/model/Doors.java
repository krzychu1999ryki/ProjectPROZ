package model;

public class Doors {
    private double positionX;
    private double positionY;
    private boolean isOpen;



    public Doors(double x, double y, boolean isOpen) {
        this.positionX = positionX;
        this.positionY = positionY;
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
}
