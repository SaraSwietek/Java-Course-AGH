package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class World {
    public static void main(String[] args){

        //lab7
        //Proszę o wpisanie argumentów f b r l  w ,,Edit configurations'' to program będzie działał
        //Program pyta sam o kierunki poruszania sie zwierzaka (input)

        //Rzucanie wyjatkow w OptionsParser i place (AbstractWorldMap)

        //App cos walczylam ale sie poddalam po punkcie 3 (Wyświetlanie mapy w GUI)

        try {
            String[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(engine.getAnimals());
            System.out.println(map);
        }
        catch (IllegalArgumentException ex){
            System.out.printf(String.valueOf(ex));
        }

        //Application.launch(App.class, args);


    }



}