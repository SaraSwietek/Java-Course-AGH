package org.example;

import java.io.FileNotFoundException;

public class World {
    public static void main(String[] args) throws FileNotFoundException {
        SphereMap map = new SphereMap(10, 10);
        Animal pet1 = new Animal(map, new Vector2d(2, 10));
        Animal pet2 = new Animal(map, new Vector2d(0, 5));
        System.out.println(pet2.position);
        System.out.println(pet2.orientation);
        System.out.println(pet1.getGenotype());
        System.out.println(pet2.getGenotype());
//        pet2.changeOrientation(pet1.genom.getCrazyGenotype(pet1.currentGeneIndex));
//        pet2.move();
//        System.out.println(pet2.position);
//        System.out.println(pet2.orientation);
//        Animal pet3 = new Animal(map, pet1, pet2);
//        System.out.println(pet3.getGenotype());
//        System.out.println(pet3.getGenotypeInt());

        Simulator simulator = new Simulator();
        System.out.println(simulator.getGrasses());
        System.out.println(simulator.getAnimals());


        try {
            ParametersLoader properties = ParametersLoader.loadPropFromFile();

            //Integer[] defaultMapProperties = {
            //        properties.getMapWidth(),
            //        properties.getMapHeight(),
            //};

            System.out.println(properties.getMapWidth());

        }

        catch (IllegalArgumentException | FileNotFoundException ex) {
            System.out.println(ex);
        }




        //Application.launch(App.class, args);

    }
}