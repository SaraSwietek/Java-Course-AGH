package org.example;

import java.io.FileNotFoundException;
import java.util.*;

abstract public class AbstractWorld implements IWorldMap{

    protected int width;
    protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    //    protected Map<Vector2d, ArrayList<Animal>> animals = new LinkedHashMap<>();
    protected List<Animal> animals = new LinkedList<>();
    protected Map<Vector2d, Grass> grasses = new LinkedHashMap<>();
    protected Map<Vector2d, Integer> deathCount = new LinkedHashMap<>();
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry



    public AbstractWorld() throws FileNotFoundException {
        this.width = parameters.getWidth();
        this.height = parameters.getHeight();
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Vector2d position = new Vector2d(i,j);
                this.deathCount.put(position, 0);
            }
        }

    }

    @Override
    public Object objectAt(Vector2d position) {


        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }

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
            if (element instanceof Animal) {
                animals.add((Animal) element);
            }
            else {
                if (this.grasses.get(newElementPosition) != null && this.grasses.get(newElementPosition) != null) {
                    // There is already a grass at this position
                    return false;
                }
                this.grasses.put(newElementPosition, (Grass) element);
            }

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

    public LinkedHashMap getAnimals(){
        return (LinkedHashMap) this.animals;
    }

    public LinkedHashMap getGrasses(){
        return (LinkedHashMap) this.grasses;
    }

}