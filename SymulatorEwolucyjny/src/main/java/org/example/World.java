package org.example;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class World {
    public static void main(String[] args) throws FileNotFoundException {

        SphereMap map = new SphereMap(10, 10);

//        SphereMap map = new SphereMap(10, 10);

        //Animal pet1 = new Animal(map, new Vector2d(2, 10));
        //Animal pet2 = new Animal(map, new Vector2d(0, 5));
        //map.place(pet1);
        //map.place(pet1);
        //map.place(pet2);

        //System.out.println(map.getAnimals());
//        System.out.println(pet2.position);
//        System.out.println(pet2.orientation);
//        System.out.println(pet1.getGenotype());
//        System.out.println(pet2.getGenotype());
//        pet2.changeOrientation(pet1.genom.getCrazyGenotype(pet1.currentGeneIndex));
//        pet2.move();
//        System.out.println(pet2.position);
//        System.out.println(pet2.orientation);

//        System.out.println(pet3.getGenotypeInt());

          Simulator simulator = new Simulator(map);
//        System.out.println(simulator.getGrasses());
//        System.out.println(simulator.getAnimals());

//        Map<Vector2d, Integer> deathCount = new LinkedHashMap<>();
//        deathCount.put(new Vector2d(1,1),1);
//        deathCount.put(new Vector2d(1,2),6);
//        deathCount.put(new Vector2d(1,3),3);
//        deathCount.put(new Vector2d(1,4),4);
//        deathCount.put(new Vector2d(1,5),2);
//
//        System.out.println(deathCount);
//
//        Map<Vector2d, Integer> sorted = deathCount.entrySet()
//                .stream().sorted(Map.Entry.comparingByValue())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->  e2, LinkedHashMap::new));
//        System.out.println(sorted);
//
//        Vector2d[] deathPlace = sorted.keySet().toArray(new Vector2d[(int)(sorted.size())]);
//        System.out.println(Arrays.toString(deathPlace));


        //Map<Vector2d,Integer> an = new LinkedHashMap<>();
        //Vector2d pos = new Vector2d(1,1);
        //an.put(pos, 1);
        //an.put(pos, 2);

        //System.out.println(an);
        //System.out.println(an.get(pos));





    }
}