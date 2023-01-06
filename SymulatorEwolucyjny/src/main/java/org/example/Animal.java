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
    AbstractWorld map;
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry
    private Random generator = new Random();


    public Animal(AbstractWorld map, Vector2d randomPosition) throws FileNotFoundException {

        int z = generator.nextInt(MapDirection.values().length);
        this.orientation = MapDirection.values()[z];
        this.days = 0;
        this.children = 0;
        this.position = randomPosition;
        this.energy = parameters.getAnimalsStartEnergy();
        this.genom = new Genotype();
        this.currentGeneIndex = 0;
        this.map= map;
    }

    public Animal(AbstractWorld map,  Animal parent1, Animal parent2) throws FileNotFoundException {

        int z = generator.nextInt(MapDirection.values().length);
        this.orientation = MapDirection.values()[z];
        this.days = 0;
        this.children = 0;
        this.position = parent1.getPosition();
        this.energy = 2*parameters.getMakingChildCost();//parent1.getEnergy() / 4 + parent2.getEnergy() / 4;
        this.genom = new Genotype(parent1, parent2);
        int w = generator.nextInt(parameters.getGenotypeLength());
        this.currentGeneIndex = w;
        this.map = map;
    }

    public void changeOrientation(int currentGene){
        for(int i=0; i<currentGene; i++)
            this.orientation = this.orientation.next();


    }

    public void move() {

        Vector2d oldPosition = this.position;
        this.position = this.position.add(this.orientation.toUnitVector());

        map.moveTo(this);

        map.animals.remove(oldPosition);

        this.currentGeneIndex = getNextGeneIndex();

        this.changeEnergy(-parameters.getAnimalsStepEnergyLoss());
        this.days++;
    }

    public int getNextGeneIndex(){

        int newGeneIndex;
        if(parameters.getBehaviour() == 1){ // pełna predyscynacja
            newGeneIndex = currentGeneIndex;
            newGeneIndex++;
            if(newGeneIndex == parameters.getGenotypeLength())
                newGeneIndex = 0;
        }
        else{ // nieco szaleństwa
            int rand = generator.nextInt(10);

            if(rand < 8){
                newGeneIndex = currentGeneIndex;
                newGeneIndex++;
                if(newGeneIndex == parameters.getGenotypeLength())
                    newGeneIndex = 0;
            }
            else{
                newGeneIndex = generator.nextInt(parameters.getGenotypeLength());
            }
        }

        return newGeneIndex;

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


        return "Energy " + energy + " Genotype " + getGenotypeInt() + " orientation " + this.orientation
                + " Position " + position
                + " CurrentgeneIndex " + this.currentGeneIndex
                + " CurrentGene "  + this.genom.getGenotype().get(currentGeneIndex);
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
