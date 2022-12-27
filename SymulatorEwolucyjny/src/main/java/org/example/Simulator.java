package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {
    protected static final int GENE_LENGTH = 8;
    protected int day = 0;

    protected List<Animal> animals = new ArrayList<Animal>();
    protected List<Grass> grasses = new ArrayList<Grass>();

    private final Random generator = new Random();

    private ArrayList<Integer> GeneArr = new ArrayList<>();
    SphereMap map = new SphereMap(10,10);

    public void addRandomAnimals(IWorldMap map, int initAnimalsNumber) {
        int i = 0;
        Random rand = new Random();
        while (i < initAnimalsNumber) {
            int x = rand.nextInt(map.getWidth());
            int y = rand.nextInt(map.getHeight());

            Animal sampleAnimal = new Animal(map, new Vector2d(x, y));
            this.animals.add(sampleAnimal);
            i++;

        }
    }


    //public void addRandomAnimals(IWorldMap map, int initAnimalsNumber) {
      //  int i = 0;
        //Random rand = new Random();
        //while (i < initAnimalsNumber) {
          //  int x = rand.nextInt(map.getWidth());
            //int y = rand.nextInt(map.getHeight());
            //if (!(map.objectAt(new Vector2d(x, y)) instanceof Animal)) {

              //  Animal sampleAnimal = new Animal(map, new Vector2d(x, y));
                //if (map.place(sampleAnimal)) {
                  //  this.animals.add(sampleAnimal);
                    //i++;
                //}
            //}
        //}
    //}

    public int getCurrentGenotype(int i){      //nieco szaleństwa
        int drawNumber = generator.nextInt(10)+1; //losowanie od 1 do 10
        if (drawNumber <= 8){
            return this.GeneArr.get(i); //w obrebie rownika
        }
        //poza rownikiem
        return this.GeneArr.get(generator.nextInt(GENE_LENGTH));
    }

    public void addRandomGrass(IWorldMap map, int initGrassNumber) {
        int i = 0;
        Random rand = new Random();
        int x = 0;
        int y = 0;

        while (i < initGrassNumber) { //80% rownik
            int drawNumber = generator.nextInt(10)+1; //losowanie od 1 do 10
            if (drawNumber <= 8){
                x = rand.nextInt(map.getWidth()+1);
                y = (int) (rand.nextInt((int) (map.getHeight()*0.2))+(0.4*map.getHeight())+1);

            }
            else{ //20% w innym miejscu
                x = rand.nextInt(map.getWidth());
                int randd = (generator.nextInt(2)); //losowanie od 1 do 2
                if(randd==0){
                    y = rand.nextInt((int) (map.getHeight()*0.4)+1);
                }
                else{
                    y = (int) (rand.nextInt((int) (map.getHeight()*0.4))+(0.6*map.getHeight())+1);
                }

            }

            if (!(map.isOccupiedByGrass(new Vector2d(x, y)))) { //NIE DZIALA

                Grass sampleGrass = new Grass(map, new Vector2d(x, y));
                this.grasses.add(sampleGrass);
                this.map.grasses.put(sampleGrass.getPosition(), sampleGrass); //trzeba?
                i++;

            }


            //if (!(map.objectAt(new Vector2d(x, y)) instanceof Grass)) {

              //  Grass sampleGrass = new Grass(map, new Vector2d(x, y));
                //if (map.placeGrass(sampleGrass)) {
                  //  this.grasses.add(sampleGrass);
                    //i++;

                //}
            //}
        }
    }

    public Simulator(){
        addRandomGrass(map, 20);
        //addRandomAnimals(map, 1);
        //for(int i=0; i<10; i++){
            //delete dead animals
            //changeOrientation();
            //eating grass
            //reproduction
            //grass growing
            //animal decreasing energy
            //this.day++;
        //}
    }

    public List<Grass> getGrasses(){
        return this.grasses;
    }
}
