package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal implements IMapElement {

    protected int energy;
    protected MapDirection orientation;
    protected int days;
    protected int children;
    protected Genotype genom;
    protected Vector2d position;
    protected int currentGeneIndex;
    IWorldMap map;
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry
    private Random rand = new Random();


    public Animal(IWorldMap map, Vector2d randomPosition) throws FileNotFoundException {

        int z = rand.nextInt(MapDirection.values().length);
        this.orientation = MapDirection.values()[z];
        this.days = 0;
        this.children = 0;
        this.position = randomPosition;
        this.energy = 20;
        this.genom = new Genotype();
        this.currentGeneIndex = 0;
        this.map= map;
    }

    public Animal(IWorldMap map,  Animal parent1, Animal parent2) throws FileNotFoundException {

        int z = rand.nextInt(MapDirection.values().length);
        this.orientation = MapDirection.values()[z];
        this.days = 0;
        this.children = 0;
        this.position = parent1.getPosition();
        this.energy = 2*parameters.getMakingChildCost();//parent1.getEnergy() / 4 + parent2.getEnergy() / 4;
        this.genom = new Genotype(parent1, parent2);
        int w = rand.nextInt(parameters.getGenotypeLength());
        this.currentGeneIndex = w;
        this.map = map;
    }

    public void changeOrientation(int currentGene){
        for(int i=0; i<currentGene; i++)
            this.orientation = this.orientation.next();
        this.changeEnergy(-parameters.getAnimalsStepEnergyLoss()); // zmienić na parametr
    }

    public void move() {
        // if sprawdzający rodzaj mapy
        this.position = this.position.add(this.orientation.toUnitVector());
        map.moveTo(this);
        if (this.currentGeneIndex == parameters.getGenotypeLength()-1)
            this.currentGeneIndex = 0;
        else this.currentGeneIndex++;
    }

//    public String toString() {
//        return switch(this.orientation) {
//            case NORTH -> "N";
//            case NORTHEAST -> "NE";
//            case EAST -> "E";
//            case SOUTHEAST -> "SE";
//            case SOUTH -> "S";
//            case SOUTHWEST -> "SW";
//            case WEST -> "W";
//            case NORTHWEST -> "NW";
//        };
//    }

    public String toString() {
        return this.getGenotype().toString();
    }

    public ArrayList<Integer> getGenotype(){

        return genom.getGenotype();
    }

    public Integer getGenotypeInt() //  po  co ????
    {
        ArrayList<Integer> g = getGenotype();
        String gString = "";
        for (Integer integer : g) {
            gString = gString + integer.toString();
        }
        return Integer.valueOf(gString);
    }

    public int getEnergy(){

        return this.energy;
    }

    public boolean isEnergyMoreThan(int n)
    {
        return (this.getEnergy()>=n);
    }

    public boolean isEnergyLessThan(int n)
    {
        return (this.getEnergy()<=n);
    }

    public void changeEnergy(int energy){

        this.energy = this.energy + energy;
    }

    public int howOld(){

        return this.days;
    }

    public int getChildren(){

        return this.children;
    }

    public Vector2d getPosition(){

        return this.position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public int getDays() {
        return days;
    }
}
