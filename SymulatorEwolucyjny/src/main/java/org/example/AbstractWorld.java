package org.example;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorld implements IWorldMap{

    protected int width;
    protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected Map<Vector2d,Animal> animals = new LinkedHashMap<>();
    protected Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    protected Map<Vector2d, Integer> deathCount = new LinkedHashMap<>();
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry



    public AbstractWorld(int width, int height) throws FileNotFoundException {
        this.width = parameters.getWidth();
        this.height = parameters.getHeight();
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);

    }

    @Override
    public IMapElement objectAt(Vector2d position) {

        if (animals.containsKey(position)) //containsKey returns True if that element is mapped in the map
            return animals.get(position);

        if (grasses.containsKey(position))
            return grasses.get(position); //get fetches the value mapped by a particular key mentioned in the parameter

        return null;
    }

    public boolean isOccupied(Vector2d position) {

        return objectAt(position) != null;
    }

    public boolean place(IMapElement element) {
        Vector2d newElementPosition = element.getPosition();
        if (newElementPosition.follows(lowerLeft) && newElementPosition.precedes(upperRight)){ //jesli miesci sie w ramach mapy
            if(element instanceof Animal)
                this.animals.put(newElementPosition, (Animal) element);
            else
                this.grasses.put(newElementPosition, (Grass) element);

            return true;
        }
        return false;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
}