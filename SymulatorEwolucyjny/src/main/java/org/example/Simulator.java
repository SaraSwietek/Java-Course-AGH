package org.example;

import java.io.FileNotFoundException;
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
                        ArrayList<Animal> list;
                        if(map.animals.get(position)==null){
                            list = new ArrayList<Animal>();
                            list.add((Animal) sampleAnimal);
                        }
                        else{
                            list = map.animals.get(position);
                            list.add(sampleAnimal);
                            map.animals.put(position, list);
                        }
                        //ArrayList<Animal> list;
                        //list = map.animals.get(position);
                        //list.add(sampleAnimal);
                        //map.animals.put(position, list);
                        i++;

                    }
                } catch (IllegalArgumentException | FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    public void fight(AbstractWorld map, Vector2d position) throws FileNotFoundException {

//        ustalenie czy jest trawa i czy są zwierzęta na pozycji position w mapie map
        // rozstrzygnięcie które zwierze je trawę i które się ze sobą rozmnażają
        Animal happyAnimal, secoundHappyAnimal;
        List<Animal> energyDecreasingList = map.animals.get(position).stream().
                sorted(Comparator.comparing(Animal::getEnergy).reversed()).toList();
        List<Animal> ageDecreasingList = map.animals.get(position).stream().
                sorted(Comparator.comparing(Animal::getDays).reversed()).toList();
        List<Animal> childrenDecreasingList = map.animals.get(position).stream().
                sorted(Comparator.comparing(Animal::getChildren).reversed()).toList();


        if(map.animals.containsKey(position)){
            if(map.grasses.containsKey(position)){
                if(map.animals.get(position).size() == 1){ // jedno zwierze - tylko trawa
                    grassEating(map, map.animals.get(position).get(0));
                }
                else if (map.animals.get(position).size() == 2){ // dwa zwierzęta
                    reproduction(map, map.animals.get(position).get(0), map.animals.get(position).get(1));

                }
                else{ // więcej niż dwa zwierzęta

                    if(energyDecreasingList.get(0) != energyDecreasingList.get(1)){ // różna energia conajmniej dwa zwierzęta
                        grassEating(map, energyDecreasingList.get(0));

                        if( energyDecreasingList.get(1) != energyDecreasingList.get(2)){

                            reproduction(map, energyDecreasingList.get(0), energyDecreasingList.get(1));

                        }
                        else{

                            if( ageDecreasingList.get(1) != ageDecreasingList.get(2)){
                                reproduction(map, ageDecreasingList.get(0), ageDecreasingList.get(1));
                            }
                            else{
                                reproduction(map, childrenDecreasingList.get(0), childrenDecreasingList.get(1));
                            }
                        }
                    }
                    else{ // conajmniej dwa zwięrzęta o tej samej energii

                        if(ageDecreasingList.get(0) != ageDecreasingList.get(1)){ // różny wiek
                            grassEating(map, ageDecreasingList.get(0));

                        }
                        else{ // ten sam wiek

                            grassEating(map, childrenDecreasingList.get(0));

                        }

                    }

                }
            }
        }


    }

    public void grassEating( AbstractWorld map, Animal animal){
        //obecnosc trawy będzie sprawdzana w walce ????
        animal.changeEnergy(parameters.getGrassEnergyProfit());
        //usunac trawe
        map.grasses.remove(animal.getPosition());
    }

    public void reproduction(AbstractWorld map, Animal animal1, Animal animal2) throws FileNotFoundException {

        if (animal1.isEnergyMoreThan(parameters.getCopulationMinimumEnergy()) &&
                animal2.isEnergyMoreThan(parameters.getCopulationMinimumEnergy())){
            animal1.changeEnergy(-parameters.getMakingChildCost());
            animal2.changeEnergy(-parameters.getMakingChildCost());

            //Narodzone dziecko pojawia się na tym samym polu co jego rodzice -> w konstruktorze!
            //Energia dziecka to suma energii, którą utracili rodzice (konstruktor)
            Animal child = new Animal(map, animal1, animal2);
            map.place(child);

        }

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
                    //map.grasses.put(position, sampleGrass);
                    i++;
                }
            }
        }
    }

    public Simulator(AbstractWorld map) throws FileNotFoundException {
//        addRandomGrass(map, parameters.getGrassStartNumber());
//        System.out.println(map.getGrasses());
//
//        //JAK TWORZYMY NOWY WEKTOR TO JEST NOWYM KLUCZEM NAWET NA TEJ SAMEJ POZYCJI
//        Vector2d vector = new Vector2d(2, 10);
//        Animal pet1 = new Animal(map, vector);
//        Animal pet2 = new Animal(map, vector);
//        map.place(pet1);
//        map.place(pet2);
//        System.out.println(map.getAnimals());
//        reproduction(map, pet1, pet2 );
//        System.out.println(map.getAnimals());



        addRandomAnimals(map, parameters.getAnimalsStartNumber());
        addRandomGrass(map, parameters.getGrassStartNumber());
        //for (List<Animal> animal : map.animals.values()) {
        //    System.out.println(animal);
        //}

        //for(int i=0; i<10; i++){
        //delete dead animals
        //changeOrientation();
        //addRandomGrass(map, parameters.getGrassStartNumber());
        //addRandomAnimals(map, parameters.getAnimalsStartNumber());
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
                fight(map,animal.getPosition());
            }


        }


        addRandomGrass(map, parameters.getGrassDailyGrowthNumber());
        System.out.println(map.getGrasses());

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