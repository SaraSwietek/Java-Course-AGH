package org.example;

import java.util.ArrayList;

public class Animal extends Simulator{
    protected int energy;
    protected MapDirection orientation;
    protected int days;
    protected int children;
    protected Genotype gene;
    protected Vector2d position;
    protected int currentGene;


    public Animal(){
        this.orientation = MapDirection.NORTH;
        this.days = 0;
        this.children = 0;
        this.position = new Vector2d(2,2);
        this.energy = 20;
        this.gene = new Genotype(GENE_LENGTH);
        this.currentGene = 0;
    }

    public Animal(Animal parent1, Animal parent2){
        this.orientation = MapDirection.NORTH;
        this.days = 0;
        this.children = 0;
        this.position = parent1.getPosition();
        //podzial energii probny
        this.energy = parent1.getEnergy() / 4 + parent2.getEnergy() / 4;
        this.gene = new Genotype(parent1, parent2);
        this.currentGene = 0;
    }

    public void changeOrientation(){
        int n = gene.getCurrentGenotype(this.currentGene);
        //zmiana kierunku uzależniona od genów (jaki gen, tyle razy funkcja next)
        for(int i=0; i<n; i++)
            this.orientation = this.orientation.next();
        this.energy = this.energy - 2;
        move(); //krok w odpowiednim kierunku
    }

    public void move() {
        this.position = this.position.add(this.orientation.toUnitVector());
        if (this.currentGene == GENE_LENGTH-1)
            this.currentGene = 0;
        else this.currentGene++;
    }

    public ArrayList<Integer> getGenotype(){
        return gene.getGenotype();
    }

    public int getEnergy(){
        return this.energy;
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
}
