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

        if(location == 2){
            roomOneCreatures = new ArrayList<>();
            roomTwoCreatures = new ArrayList<>();
            roomThreeCreatures = new ArrayList<>();

            roomOneCreatures.add(new Creature(100,400,10,4,0.5,
                    40, 64,64, 28 , 2));

            roomTwoCreatures.add(new Creature(200,500,10,4,0.5,
                    40, 64,64, 28 , 2));

            roomTwoCreatures.add(new Creature(600,200,10,4,0.5,
                    40, 64,64, 28 , 2 ));

            roomThreeCreatures.add(new Creature(600,200,10,4,0.5,
                    40, 64,64, 28 , 2 ));

            roomThreeCreatures.add(new Creature(300,100,10,4,0.5,
                    40, 64,64, 28 , 2 ));

            roomThreeCreatures.add(new Creature(100,450,10,4,0.5,
                    40, 64,64, 28 , 2 ));

            doors = new Doors[3];
            doors[0] = new Doors(200, 200, false);
            doors[1] = new Doors(100, 400, false);
            doors[2] = new Doors(200, 200, false);
        }

        if(location == 3){
            roomOneCreatures = new ArrayList<>();
            roomTwoCreatures = new ArrayList<>();
            roomThreeCreatures = new ArrayList<>();

            roomOneCreatures.add(new Creature(100,400,20,4,0.5,
                    30, 64,64, 28 , 5));

            roomTwoCreatures.add(new Creature(500,200,20,4,0.5,
                    30, 64,64, 28 , 5));

            roomTwoCreatures.add(new Creature(600,200,20,4,0.5,
                    30, 64,64, 28 , 5 ));

            roomThreeCreatures.add(new Creature(600,200,20,4,0.5,
                    30, 64,64, 28 , 5 ));

            roomThreeCreatures.add(new Creature(300,150,20,4,0.5,
                    30, 64,64, 28 , 5 ));

            roomThreeCreatures.add(new Creature(100,450,20,4,0.5,
                    30, 64,64, 28 , 5 ));

            doors = new Doors[3];
            doors[0] = new Doors(100, 200, false);
            doors[1] = new Doors(100, 400, false);
            doors[2] = new Doors(400, 200, false);
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
