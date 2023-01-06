package org.example;

public class Grass implements IMapElement {
    protected Vector2d position;
    IWorldMap map;

    public Grass(IWorldMap map, Vector2d position){

        this.position = position;
        this.map= map;

    }

    public Vector2d getPosition(){

        return this.position;
    }



//    @Override
//    public String toString(){
//
//        return this.position.toString();
//    }

    @Override
    public String toString(){

        return this.position.toString();
    }

}
