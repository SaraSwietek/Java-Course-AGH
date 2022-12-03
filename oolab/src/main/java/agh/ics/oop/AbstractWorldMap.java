package agh.ics.oop;
import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    public Map<Vector2d, Animal> animals = new HashMap<>();
    public Map<Vector2d, Grass> grassBlades = new HashMap<>();

    @Override
    public Object objectAt(Vector2d position) {

        if (animals.containsKey(position)) //containsKey returns True if that element is mapped in the map
            return animals.get(position);

        if (grassBlades.containsKey(position))
            return grassBlades.get(position); //get fetches the value mapped by a particular key mentioned in the parameter

        return null;
    }

    @Override
    public boolean place(Animal animal) {
        if (!(objectAt(animal.getPosition()) instanceof Animal)){
            animals.put(animal.getPosition(), animal); //put(key, value)
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {

        if (animals.containsKey(position))
            return true;

        if (grassBlades.containsKey(position))
            return true;

        return false;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if(!newPosition.equals(oldPosition)) {
            Animal a = animals.remove(oldPosition);
            animals.put(newPosition, a);
        }
    }


    public Map<Vector2d, Animal> getAnimals(){
        return this.animals;
    }
}
