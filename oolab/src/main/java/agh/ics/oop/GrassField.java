package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{

    private int grassCount;
    //public List<Animal> animals = new ArrayList<>();
    //public List<Grass> grassBlades = new ArrayList<>();

    public GrassField(int grassCount) {
        this.grassCount = grassCount;

        while (grassBlades.size() < grassCount){
            Vector2d positionGrass = randomPositionGrass(grassCount);
            if (!isOccupied(positionGrass)){
                grassBlades.add(new Grass(positionGrass));
            }
        }
    }

    public Vector2d randomPositionGrass(int n){
        int xGrass = (int) (Math.random() * (Math.sqrt(n*10) + 1));
        int yGrass = (int) (Math.random() * (Math.sqrt(n*10) + 1));
        return new Vector2d(xGrass,yGrass);
    }

    public boolean canMoveTo(Vector2d position) {
        //nieograniczone poruszanie sie
        if(this.isOccupied(position))
            return false;

        return true;
    }

    /*@Override
    public boolean place(Animal animal) {
        if (isOccupiedByGrass(animal.getPosition())) {
            animals.add(animal);
            return true; } //zwierzak ma priorytet nad trawa
        if (isOccupied(animal.getPosition()))
            return false;
        animals.add(animal); //array do sprawdzania czy inny zwierzak znajduje sie na danym miejscu
        return true;
    }*/
/*
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

    /*public boolean isOccupiedByGrass(Vector2d position) {
        for (Grass i : grassBlades){
            if (i.getPosition().equals(position))
                return true;
        }
        return false;
    }

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
    }*/

    //obliczam na jakich skrajnych czesciach mapy znajduja sie obiekty
    public Vector2d searchLowerLeft(){
        Vector2d lowLeft = new Vector2d(0,0);
        for (Animal animal : animals) {
            lowLeft = lowLeft.lowerLeft(animal.getPosition()); //korzyszam z funkcji lowerLeft w klasie Vector2d
        }
        for (Grass grass : grassBlades) {
            lowLeft = lowLeft.lowerLeft(grass.getPosition());
        }
        return lowLeft;
    }

    public Vector2d searchUpperRight(){
        Vector2d topRight = new Vector2d(0,0);
        for (Animal animal : animals) {
            topRight = topRight.upperRight(animal.getPosition()); //korzyszam z funkcji upperRight w klasie Vector2d
        }
        for (Grass grass : grassBlades) {
            topRight = topRight.upperRight(grass.getPosition());
        }
        return topRight;
    }

    @Override
    public String toString() {
        //dynamicznie obliczam skrajne punkty ktore powinny zostac wyswietlone
        return new MapVisualizer(this).draw(searchLowerLeft(), searchUpperRight());
    }


}
