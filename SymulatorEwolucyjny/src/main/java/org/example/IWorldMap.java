package org.example;


public interface IWorldMap {

    boolean place(IMapElement element);

    boolean isOccupied(Vector2d position);

    Object objectAt(Vector2d position);

    void moveTo(Animal pet); // zmienić na canMoveto ?

    int getWidth();

    int getHeight();

}
