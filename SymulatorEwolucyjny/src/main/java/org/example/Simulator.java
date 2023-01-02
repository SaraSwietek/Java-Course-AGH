package org.example;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Simulator {

    protected int day = 0;

    private final Random generator = new Random(); // dlaczego final ?

    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry


    public void addRandomAnimals(AbstractWorld map, int initAnimalsNumber) {
        int i = 0;
        Random rand = new Random();
        while (i < initAnimalsNumber) {
            int x = rand.nextInt(map.getWidth());
            int y = rand.nextInt(map.getHeight());
            Vector2d position = new Vector2d(x, y);
            if (!(map.objectAt(position) instanceof Animal)) {

                try {
                    Animal sampleAnimal = new Animal(map, new Vector2d(x, y));
                    if (map.place(sampleAnimal)) {

                        List<Animal> list;
                        list = map.animals.get(position);
                        list.add(sampleAnimal);
                        map.animals.put(position, list);
                        i++;

                    }
                } catch (IllegalArgumentException | FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    public void fight(AbstractWorld map, Vector2d position){

//        ustalenie czy jest trawa i czy są zwierzęta na pozycji position w mapie map
        // rozstrzygnięcie które zwierze je trawę i które się ze sobą rozmnażają
        Animal happyAnimal, secoundHappyAnimal;

        if(map.animals.containsKey(position)){
            if(map.grasses.containsKey(position)){
                if(map.animals.get(position).size() == 1){
                    grassEating(map.animals.get(position).get(0));
                }
                else{
//                    map.animals.get(position).stream().max(Comparator.comparing(Animal::getEnergy));
                    List<Animal> energyList = map.animals.get(position).stream().
                                                    sorted(Comparator.comparing(Animal::getEnergy).reversed()).toList();

                    if(energyList.get(0) != energyList.get(1)){
                        grassEating(energyList.get(0));
                        if(map.animals.get(position).size() > 2 && energyList.get(0) == energyList.get(3))
                    }
                    else{
                        List<Animal>
                    }

                }
            }
        }


    }

    public void grassEating( Animal animal){

    }

    public void reproduction(AbstractWorld map, Vector2d position, Animal animal1, Animal animal2){


    }


    public void addRandomGrass(AbstractWorld map, int initGrassNumber) {
        int i = 0;

        int x = 0;
        int y = 0;

        while (i < initGrassNumber) {

            int rand = generator.nextInt(10) + 1;

            if (parameters.getGrassGrowing() == 2) { // zielone równiki
                //80% rownik

                if (rand <= 8) {
                    x = generator.nextInt(map.getWidth() + 1);
                    y = (int) (generator.nextInt((int) (map.getHeight() * 0.2)) + (0.4 * map.getHeight()) + 1);

                } else { //20% w innym miejscu
                    x = generator.nextInt(map.getWidth());

                    if (generator.nextBoolean()) {
                        y = generator.nextInt((int) (map.getHeight() * 0.4) + 1);
                    } else {
                        y = (int) (generator.nextInt((int) (map.getHeight() * 0.4)) + (0.6 * map.getHeight()) + 1);
                    }

                }
            } else { // trupy

                Map<Vector2d, Integer> sorted = map.deathCount.entrySet()
                        .stream().sorted(Map.Entry.comparingByValue())
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

                Vector2d[] deathPlace = sorted.keySet().toArray(new Vector2d[(int) (sorted.size())]);

                i = generator.nextInt((int) (sorted.size() * 0.8));
                if (rand <= 2) {

                    x = deathPlace[i].x;
                    y = deathPlace[i].y;

                } else {
                    i = generator.nextInt(deathPlace.length - i) + i;

                    x = deathPlace[i].x;
                    y = deathPlace[i].y;

                }


            }

            Vector2d position = new Vector2d(x, y);

            if (!(map.objectAt(position) instanceof Grass)) {

                Grass sampleGrass = new Grass(map, new Vector2d(x, y));
                if (map.place(sampleGrass)) {
                    map.grasses.put(position, sampleGrass);
                    i++;
                }
            }
        }
    }

    public Simulator(AbstractWorld map) throws FileNotFoundException {
        addRandomGrass(map, parameters.getGrassStartNumber());
        addRandomAnimals(map, parameters.getAnimalsStartNumber());
        //for(int i=0; i<10; i++){
        //delete dead animals
        //changeOrientation();
        addRandomGrass(map, parameters.getGrassStartNumber());
        addRandomAnimals(map, parameters.getAnimalsStartNumber());
        //for(int i=0; i<10; i++){
        //delete dead animals

        //changeOrientation + move

        for (List<Animal> animalsAtposition : map.animals.values()) {

            for(Animal animal: animalsAtposition){
                int newGeneIndex;
                if(parameters.getBehaviour() == 1){ // pełna predyscynacja
                    newGeneIndex = animal.currentGeneIndex;
                }
                else{ // nieco szaleństwa
                    int rand = generator.nextInt(10);

                    if(rand < 8){
                        newGeneIndex = animal.currentGeneIndex;
                    }
                    else{
                        newGeneIndex = generator.nextInt(parameters.getGenotypeLength());
                    }
                }

                animal.changeOrientation(animal.getGenotype().get(newGeneIndex));
                animal.move();
            }


        }

//        for (Animal animal : animals) {
//            System.out.println(animal);
//        }
//
//        for (Animal animal : animals) {
//            if (animal.getEnergy()==18){
//                animals.remove(animal);
//            }
//        }
//
//        for (Animal animal : animals) {
//            System.out.println(animal);
//        }

        //eating grass
        //reproduction
        //grass growing

        //this.day++;
        //}
        //eating grass
        //reproduction
        //grass growing
        //animal decreasing energy
        //this.day++;
        //}
    }


}