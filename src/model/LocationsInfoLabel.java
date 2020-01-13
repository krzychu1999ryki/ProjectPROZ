package model;

import java.util.*;

public class LocationsInfoLabel {
    private List<Creature> roomOneCreatures;
    private List<Creature> roomTwoCreatures;
    private List<Creature> roomThreeCreatures;

    private Doors[] doors;

    public Creature creature;


    public LocationsInfoLabel(int location) {
        if(location == 1){
            roomOneCreatures = new ArrayList<>();
            roomTwoCreatures = new ArrayList<>();
            roomThreeCreatures = new ArrayList<>();

            roomOneCreatures.add(new Creature(100,400,4,3,0.5,
                    50, 64,64, 28 , 1));

            roomTwoCreatures.add(new Creature(100,100,4,3,0.5,
                    50, 64,64, 28 , 1));

            roomTwoCreatures.add(new Creature(600,200,4,3,0.5,
                    50, 64,64, 28, 1 ));

            roomThreeCreatures.add(new Creature(600,200,4,3,0.5,
                    50, 64,64, 28, 1 ));

            doors = new Doors[3];
            doors[0] = new Doors(400, 200, false);
            doors[1] = new Doors(400, 600, false);
            doors[2] = new Doors(300, 300, false);
        }
    }


    public List<Creature> getRoomOneCreatures() {
        return roomOneCreatures;
    }

    public List<Creature> getRoomTwoCreatures() {
        return roomTwoCreatures;
    }

    public List<Creature> getRoomThreeCreatures() {
        return roomThreeCreatures;
    }

    public Doors getDoors(int i){
        return doors[i];
    }
}
