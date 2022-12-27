package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorld implements IWorldMap{

    protected int width;
    protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected Map<Vector2d, Animal> animals = new LinkedHashMap<Vector2d, Animal>();
    protected Map<Vector2d, Grass> grasses = new LinkedHashMap<Vector2d, Grass>();


    public AbstractWorld(int width, int height){
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);

    }

    @Override
    public Object objectAt(Vector2d position) {

        if (animals.containsKey(position)) //containsKey returns True if that element is mapped in the map
            return animals.get(position);

        if (grasses.containsKey(position))
            return grasses.get(position); //get fetches the value mapped by a particular key mentioned in the parameter

        return null;
    }

    public boolean isOccupiedByGrass(Vector2d position) {

        if (grasses.containsKey(position))
            return true;

        return false;
    }

    public boolean place(Animal animal) {
        Vector2d newAnimalPosition = animal.getPosition();
        if (newAnimalPosition.follows(lowerLeft) && newAnimalPosition.precedes(upperRight)){ //jesli miesci sie w ramach mapy
            this.animals.put(newAnimalPosition, animal);
            return true;
        }
        return false;
    }

    public boolean placeGrass(Grass grass) {
        Vector2d newGrassPosition = grass.getPosition();
        if (newGrassPosition.follows(lowerLeft) && newGrassPosition.precedes(upperRight) && !(objectAt(newGrassPosition) instanceof Grass)){
            this.grasses.put(newGrassPosition, grass);
            return true;
        }
        return false;
    }


}
