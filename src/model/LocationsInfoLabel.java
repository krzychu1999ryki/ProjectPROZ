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

            roomThreeCreatures.add(new Creature(300,150,4,3,0.5,
                    50, 64,64, 28, 1 ));

            roomThreeCreatures.add(new Creature(100,450,4,3,0.5,
                    50, 64,64, 28, 1 ));

            doors = new Doors[3];
            doors[0] = new Doors(400, 200, false);
            doors[1] = new Doors(500, 400, false);
            doors[2] = new Doors(400, 300, false);
        }
    }


    public List<Creature> getRoomCreatures( int room) {
        if(room == 1){
            return roomOneCreatures;
        }else if(room == 2){
            return roomTwoCreatures;
        }else if(room == 3){
            return roomThreeCreatures;
        }else{
            return null;
        }

    }

    public Doors getDoors(int i){
        return doors[i];
    }
}
