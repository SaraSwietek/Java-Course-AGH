package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractWorldMap implements IWorldMap {

    public List<Animal> animals = new ArrayList<>();
    public List<Grass> grassBlades = new ArrayList<>();

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal i :animals) {
            if (i.getPosition().equals(position))
                return i;
        }
        for (Grass i :grassBlades) {
            if (i.getPosition().equals(position))
                return i;
        }
        return null;
    }

    @Override
    public boolean place(Animal animal) {
        if (!(objectAt(animal.getPosition()) instanceof Animal)){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal i : animals){
            if (i.getPosition().equals(position))
                return true;
        }
        for (Grass i : grassBlades){
            if (i.getPosition().equals(position))
                return true;
        }
        return false;
    }



}
