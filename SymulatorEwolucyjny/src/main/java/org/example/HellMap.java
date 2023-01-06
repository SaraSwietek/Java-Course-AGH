package org.example;

import java.io.FileNotFoundException;
import java.util.Random;

public class HellMap extends AbstractWorld{

    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry

    public HellMap() throws FileNotFoundException {

        super();

    }

    public Vector2d generateRandomPosition(){ // potrzebne do teleportacji zwierzęcia jak wyjdzie poza mapę

        Random r1 = new Random();
        Random r2 = new Random();

        int x_r = r1.nextInt(width);
        int y_r = r2.nextInt(height);

        return new Vector2d(x_r, y_r);
    }

    public void moveTo(Animal pet) { // zmienić na canMoveTo ? i jest to też w AbstractWorld
        if (pet.position.x >= width || pet.position.x < 0 || pet.position.y < 0 || pet.position.y >= height)
        {
            pet.position = generateRandomPosition();
            pet.changeEnergy(-parameters.getAnimalsStepEnergyLoss());
        }
    }

}