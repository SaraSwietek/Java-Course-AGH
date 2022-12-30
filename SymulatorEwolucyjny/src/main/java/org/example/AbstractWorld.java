package org.example;

import java.util.LinkedHashMap;
import java.util.Map;

abstract public class AbstractWorld extends ParametersLoader implements IWorldMap{

    //protected int width; są dziedziczone po ParametersLoader
    //protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected Map<Vector2d, IMapElement> animals = new LinkedHashMap<Vector2d, IMapElement>();
    protected Map<Vector2d, IMapElement> grasses = new LinkedHashMap<Vector2d, IMapElement>();


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

    public boolean isOccupied(Vector2d position) {

        return objectAt(position) != null;
    }

    public boolean place(IMapElement element) {
        Vector2d newElementPosition = element.getPosition();
        if (newElementPosition.follows(lowerLeft) && newElementPosition.precedes(upperRight)){ //jesli miesci sie w ramach mapy
            if(element instanceof Animal)
                this.animals.put(newElementPosition, element);
            else
                this.grasses.put(newElementPosition, element);

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