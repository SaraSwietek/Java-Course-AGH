package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private String[] directions;
    private IWorldMap map;
    private Vector2d[] positions;
    private List<Animal> animals = new ArrayList<Animal>();

    public SimulationEngine(String[] givenDirections, IWorldMap map, Vector2d[] positions) {
        this.directions = givenDirections;
        this.map = map;
        this.positions = positions;

        for (Vector2d position: positions){
            Animal animal = new Animal(position, map);
            if(map.place(animal)) {
                animals.add(animal);
            }

        }

    }
    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            animals.get(i % animals.size()).move(MoveDirection.valueOf(directions[i]), map);
        }
        System.out.println(animals);
    }


    public List<Animal> getAnimals() {
        return animals;
    }

    public Vector2d[] getPositions() {
        return this.positions;
    }

}
