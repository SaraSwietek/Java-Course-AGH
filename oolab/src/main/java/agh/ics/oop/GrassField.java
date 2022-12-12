package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GrassField extends AbstractWorldMap{

    private int grassCount;

    public GrassField(int grassCount) {
        this.grassCount = grassCount;

        while (grassBlades.size() < grassCount){
            Vector2d positionGrass = randomPositionGrass(grassCount);
            if (!isOccupied(positionGrass)){
                grassBlades.put(positionGrass, new Grass(positionGrass));
                this.boundary.addPosition(positionGrass);
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


    //obliczam na jakich skrajnych czesciach mapy znajduja sie obiekty
    public Vector2d searchLowerLeft(){
        return boundary.getLowerLeft(); //MapBoundary ustala wielkosc mapy
    }

    public Vector2d searchUpperRight(){
        return boundary.getUpperRight(); //MapBoundary ustala wielkosc mapy
    }

    @Override
    public String toString() {
        //dynamicznie obliczam skrajne punkty ktore powinny zostac wyswietlone
        return new MapVisualizer(this).draw(searchLowerLeft(), searchUpperRight());
    }


}
