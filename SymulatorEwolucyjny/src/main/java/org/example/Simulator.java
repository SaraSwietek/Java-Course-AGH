package org.example;

import java.io.FileNotFoundException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Simulator{ // odpowiedzialne tylko za symulacje ?
    //protected final static int GENE_LENGTH = 8; dziedziczy po ParametersLoader// stała do losowania tylko ? zrobić jakoś żeby była tylko w jednym miejscu a nie 3
    protected int day = 0;
    protected List<Animal> animals = new ArrayList<Animal>(); // trzeba ?
    protected List<Grass> grasses = new ArrayList<Grass>(); // trzeba ?

    private final Random generator = new Random(); // dlaczego final ?

    private ArrayList<Integer> GeneArr = new ArrayList<>(); /// po co ??
    SphereMap map = new SphereMap(10,10); // po co ?
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry



    public void addRandomAnimals(IWorldMap map, int initAnimalsNumber) {
        int i = 0;
        Random rand = new Random();
        while (i < initAnimalsNumber) {
            int x = rand.nextInt(map.getWidth());
            int y = rand.nextInt(map.getHeight());
            if (!(map.objectAt(new Vector2d(x, y)) instanceof Animal)) {

                try{
                    Animal sampleAnimal = new Animal(map, new Vector2d(x, y));
                    if (map.place(sampleAnimal)) {
                        this.animals.add(sampleAnimal);
                        i++;

                }}
                catch (IllegalArgumentException | FileNotFoundException ex) {
                    System.out.println(ex);
                }
            }
        }
    }



//    public int getCrazyGenotype(int i){      //nieco szaleństwa - przy poruszaniu czasami będzie zmieniał gen, czemu tu i w Genotype ?
//        int drawNumber = generator.nextInt(10)+1; //losowanie od 1 do 10
//        if (drawNumber <= 8){
//            return this.GeneArr.get(i); //w 80% przypatkach normalnie
//        }
//        //w 20% losowy gen
//        return this.GeneArr.get(generator.nextInt(GENE_LENGTH));
//    }

    public void addRandomGrass(AbstractWorld map, int initGrassNumber) {
        int i = 0;

        int x = 0;
        int y = 0;

        while (i < initGrassNumber) {

            int rand = generator.nextInt(10)+1;

            if( parameters.getGrassGrowing() == 2){
                //80% rownik

                if (rand <= 8){
                    x = generator.nextInt(map.getWidth()+1);
                    y = (int) (generator.nextInt((int) (map.getHeight()*0.2))+(0.4*map.getHeight())+1);

                }
                else{ //20% w innym miejscu
                    x = generator.nextInt(map.getWidth());

                    if(generator.nextBoolean()){
                        y = generator.nextInt((int) (map.getHeight()*0.4)+1);
                    }
                    else{
                        y = (int) (generator.nextInt((int) (map.getHeight()*0.4))+(0.6*map.getHeight())+1);
                    }

                }
            }
            else{

                Map<Vector2d, Integer> sorted = map.deathCount.entrySet()
                                                    .stream().sorted(Map.Entry.comparingByValue())
                                                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->  e2, LinkedHashMap::new));




            }



            if (!(map.objectAt(new Vector2d(x, y)) instanceof Grass)) {

                Grass sampleGrass = new Grass(map, new Vector2d(x, y));
                if (map.place(sampleGrass)) {
                    this.grasses.add(sampleGrass);
                    i++;
                }
            }
        }
    }

    public Simulator() throws FileNotFoundException {
        addRandomGrass(map, parameters.getGrassStartNumber());
        addRandomAnimals(map, parameters.getAnimalsStartNumber());
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
    public List<Animal> getAnimals(){
        return this.animals;
    }
}