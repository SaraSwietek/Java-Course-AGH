package org.example;

import javafx.application.Application;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class World {
    public static void main(String[] args) throws FileNotFoundException {


        //Simulator simulator = new Simulator(map);

        Application.launch(App.class, args);

    }
}