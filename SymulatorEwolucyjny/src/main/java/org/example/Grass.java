package org.example;

public class Grass {
    protected Vector2d position;
    IWorldMap map;

    public Grass(IWorldMap map, Vector2d position){

        this.position = position;
        this.map= map;

    }

    public Vector2d getPosition(){
        return this.position;
    }



    @Override
    public String toString(){
        return "*";
    }

}
