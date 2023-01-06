package org.example;

import java.io.FileNotFoundException;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Simulator {


    private final Random generator = new Random();

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

                        i++;

                    }
                } catch (IllegalArgumentException | FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    public void grassFight(AbstractWorld map, Vector2d position){

        List<Animal> energyDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getEnergy).reversed()).filter(animal -> animal.position.equals(position)).
                toList();
        List<Animal> ageDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getDays).reversed()).filter(animal -> animal.position.equals(position)).
                toList();
        List<Animal> childrenDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getChildren).reversed()).filter(animal -> animal.position.equals(position)).
                toList();


        if(energyDecreasingList.get(0) != energyDecreasingList.get(1)){
            grassEating(map, energyDecreasingList.get(0));
        }
        else{
            if(ageDecreasingList.get(0) != ageDecreasingList.get(1)){
                grassEating(map, ageDecreasingList.get(0));
            }
            else{
                grassEating(map, childrenDecreasingList.get(0));
            }
        }

    }

    public void reproductionFight(AbstractWorld map, Vector2d position) throws FileNotFoundException {

        List<Animal> energyDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getEnergy).reversed()).
                filter(animal -> animal.position.equals(position)).
                toList();
        List<Animal> ageDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getDays).reversed()).
                filter(animal -> animal.position.equals(position)).
                toList();
        List<Animal> childrenDecreasingList = map.animals.stream().
                sorted(Comparator.comparing(Animal::getChildren).reversed()).
                filter(animal -> animal.position.equals(position)).
                toList();

        if(energyDecreasingList.get(1) != energyDecreasingList.get(2)){
            reproduction(map, energyDecreasingList.get(0), energyDecreasingList.get(1));
        }
        else{
            if(ageDecreasingList.get(1) != ageDecreasingList.get(2)){
                reproduction(map, ageDecreasingList.get(0), ageDecreasingList.get(1));
            }
            else{
                reproduction(map, childrenDecreasingList.get(0), childrenDecreasingList.get(1));
            }
        }

    }

    public void isFight(AbstractWorld map, Vector2d position) throws FileNotFoundException {

        List<Animal> animalAtposition = map.animals.stream().
                filter(animal -> animal.position.equals(position)).
                toList();


        if(map.grasses.containsKey(position)){ // czy jest trawa na polu

            if(animalAtposition.size() == 1){ // czy jest tylko zwierze - walka o trawę niepotrzebna

                grassEating(map, animalAtposition.get(0));
            }
            else if(animalAtposition.size() > 1){ // więcej zwierząt - walka o trawę potrzebna
                grassFight(map, position);

                if(animalAtposition.size() == 2){ // tylko dwa zwierzęta walka o reprodukcje niepotrzebna
                    reproduction(map, animalAtposition.get(0), animalAtposition.get(1));
                }
                else{ // więcej zwierząt walka o reprodukcje potrzebna
                    reproductionFight(map, position);
                }
            }
        }
        else{
            if(animalAtposition.size() == 2){ // tylko dwa zwierzęta walka o reprodukcje niepotrzebna
                reproduction(map, animalAtposition.get(0), animalAtposition.get(1));
            }
            else if(animalAtposition.size() > 2){ // więcej zwierząt walka o reprodukcje potrzebna
                reproductionFight(map, position);
            }
        }

    }




    public void grassEating( AbstractWorld map, Animal animal){

        animal.changeEnergy(parameters.getGrassEnergyProfit());
        //usunac trawe
        map.grasses.remove(animal.getPosition());
    }

    public void removeDeathAnimals(AbstractWorld map){// dorzucić dodawanie do listy śmierci

        List<Animal> animalsToRemove = new ArrayList<>();
        for (Animal animal : map.animals) {
            if (animal.getEnergy() <= 0) {
                animalsToRemove.add(animal);
                Integer deathCounter = map.deathCount.get(animal.getPosition());
                deathCounter++;
                map.deathCount.put(animal.getPosition(),deathCounter);
            }
        }
        map.animals.removeAll(animalsToRemove);


    }

    public void reproduction(AbstractWorld map, Animal animal1, Animal animal2) throws FileNotFoundException {

        if (animal1.isEnergyMoreThan(parameters.getCopulationMinimumEnergy()) &&
                animal2.isEnergyMoreThan(parameters.getCopulationMinimumEnergy())){
            animal1.changeEnergy(-parameters.getMakingChildCost());
            animal2.changeEnergy(-parameters.getMakingChildCost());
            animal1.children++;
            animal2.children++;

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

        while (i < initGrassNumber && map.grasses.size() < parameters.getWidth()*parameters.getHeight()) {

            int rand = generator.nextInt(10) + 1;

            if (parameters.getGrassGrowing() == 2) { // zielone równiki
                //80% rownik

                if (rand <= 8) {
                    x = generator.nextInt(map.getWidth());
                    y = (int) (generator.nextInt((int) (map.getHeight() * 0.2)) + (0.4 * map.getHeight()) );

                } else { //20% w innym miejscu
                    x = generator.nextInt(map.getWidth());

                    if (generator.nextBoolean()) {
                        y = generator.nextInt((int) (map.getHeight() * 0.4) );
                    } else {
                        y = (int) (generator.nextInt((int) (map.getHeight() * 0.4) + 1) + (0.6 * map.getHeight()));
                    }

                }
            } else { // trupy

                Map<Vector2d, Integer> sorted = map.deathCount.entrySet()
                        .stream().sorted(Map.Entry.comparingByValue())
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

                Vector2d[] deathPlace = sorted.keySet().toArray(new Vector2d[(int) (sorted.size())]);

                int j = generator.nextInt((int) (sorted.size() * 0.8));
                if (rand <= 2) {

                    x = deathPlace[j].x;
                    y = deathPlace[j].y;

                } else {
                    j = generator.nextInt(deathPlace.length - j) + j;

                    x = deathPlace[j].x;
                    y = deathPlace[j].y;

                }

            }

            Vector2d position = new Vector2d(x, y);



            Grass sampleGrass = new Grass(map, position);
            if (map.place(sampleGrass)) {
                i++;
            }

        }
    }

    public Simulator(AbstractWorld map) throws FileNotFoundException {
        addRandomGrass(map, parameters.getGrassStartNumber());
        addRandomAnimals(map, parameters.getAnimalsStartNumber());



        for( int i=0; i<parameters.getHeight(); i++ ){
            for(int j=0; j<parameters.getWidth(); j++){
                Vector2d position = new Vector2d(i,j);
                isFight(map, position);

            }
        }

    }

    public void simulate(AbstractWorld map) throws FileNotFoundException {

        removeDeathAnimals(map);


        for (Animal animal : map.animals) {



            int newGeneIndex = animal.currentGeneIndex;


            animal.changeOrientation(animal.getGenotype().get(newGeneIndex));
            animal.move();

        }

        for( int i=0; i<parameters.getHeight(); i++ ){
            for(int j=0; j<parameters.getWidth(); j++){
                Vector2d position = new Vector2d(i,j);
                isFight(map, position);

            }
        }


        addRandomGrass(map,parameters.getGrassDailyGrowthNumber());

    }


}