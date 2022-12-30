package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    //private MapDirection dir = MapDirection.NORTH;
    //private int x=2;
    //private int y=2;
    //private Vector2d position = new Vector2d(x,y);

    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = new RectangularMap(5,5);
        addObserver((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map){
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2,2);
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    public Animal(Vector2d position, IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = position;
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    @Override
    public String toString(){
        switch(orientation) {
            case NORTH: return "^";
            case SOUTH: return "v";
            case EAST: return ">";
            case WEST: return "<";
        }
        return "Zly kierunek";
    }
/*
    @Override
    public String toString() {
        return "polozenie: %s, orientacja: %s,xy: (%s,%s)".formatted(position,orientation,position.x,position.y);
    }
*/
    boolean isAt(Vector2d position){
        if (position.x==this.position.x && position.y==this.position.y){
            return true;
        }
        else return false;
    }

    void move(MoveDirection direction, IWorldMap map) {
        if (direction == MoveDirection.RIGHT) {
            this.orientation = MapDirection.valueOf(orientation.next());

        }

        if (direction == MoveDirection.LEFT) {
            this.orientation = MapDirection.valueOf(orientation.previous());
        }

        if (direction == MoveDirection.FORWARD) {
            Vector2d position_change = orientation.toUnitVector();
            int xNew = this.position.x;
            int yNew = this.position.y;

            if (map.canMoveTo(new Vector2d(xNew+=position_change.x,yNew+=position_change.y))){
                positionChanged(this.position, new Vector2d(xNew,yNew));
                this.position = new Vector2d(xNew,yNew);
            }

        }

        if (direction == MoveDirection.BACKWARD) {
            Vector2d position_change = orientation.toUnitVector();
            int xNew = this.position.x;
            int yNew = this.position.y;

            if (map.canMoveTo(new Vector2d(xNew-=position_change.x,yNew-=position_change.y))){
                positionChanged(this.position, new Vector2d(xNew,yNew));
                this.position = new Vector2d(xNew,yNew);
            }

        }

    }


    public String getDir() {
        return "%s".formatted(orientation);
    }


    public int getX() {
        return this.position.x;
    }

    public int getY() {
        return this.position.y;
    }

    public Vector2d getPosition() {
        return position;
    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }

}
