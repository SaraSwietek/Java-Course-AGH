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
        Vector2d lowLeft = new Vector2d(0,0);
        Set<Vector2d> animalsSet = this.animals.keySet();
        Set<Vector2d> grassSet = this.grassBlades.keySet();

        for (Vector2d vector2d : animalsSet) {
            lowLeft = lowLeft.lowerLeft(vector2d); //korzystam z funkcji lowerLeft w klasie Vector2d
        }
        for (Vector2d vector2d : grassSet) {
            lowLeft = lowLeft.lowerLeft(vector2d);
        }
        return lowLeft;
    }

    public Vector2d searchUpperRight(){
        Vector2d topRight = new Vector2d(0,0);
        Set<Vector2d> animalsSet = this.animals.keySet();
        Set<Vector2d> grassSet = this.grassBlades.keySet();

        for (Vector2d vector2d : animalsSet) {
            topRight = topRight.upperRight(vector2d); //korzystam z funkcji upperRight w klasie Vector2d
        }
        for (Vector2d vector2d : grassSet) {
            topRight = topRight.upperRight(vector2d);
        }
        return topRight;
    }

    @Override
    public String toString() {
        //dynamicznie obliczam skrajne punkty ktore powinny zostac wyswietlone
        return new MapVisualizer(this).draw(searchLowerLeft(), searchUpperRight());
    }


}
