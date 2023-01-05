package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainView extends VBox {

    private Button stepButton;
    private Canvas canvas;
    private AbstractWorld map;
    private Affine affine;
    private final Random generator = new Random();
    ParametersLoader parameters = ParametersLoader.loadPropFromFile();

    //private Simulator simulator;

    public MainView() throws FileNotFoundException {

        this.stepButton = new Button("step");
        this.map = new SphereMap(parameters.getWidth(), parameters.getHeight());
        Simulator simulator = new Simulator(map);
//        simulator.addRandomGrass(map, parameters.getGrassStartNumber());
//        simulator.addRandomAnimals(map, parameters.getAnimalsStartNumber());

        this.stepButton.setOnAction(actionEvent -> {

            try {
                simulator.simulate(map);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            draw();
            //Grass grass = new Grass(map, new Vector2d(30,30));
            //map.place(grass);

            //for(Animal animal: map.getAnimals().values()){
            //    animal.move();
            //}

            //simulator.addRandomGrass(map, parameters.getGrassDailyGrowthNumber());


        });


        this.canvas = new Canvas(400, 400);

        this.getChildren().addAll(this.stepButton, this.canvas);



        this.affine = new Affine();
        this.affine.appendScale(400/parameters.getWidth(), 400/parameters.getHeight());

    }

    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, parameters.getWidth(), parameters.getHeight());

        for (int x = 0; x<this.map.width; x++) {
            for (int y = 0; y < this.map.height; y++) {
                for (Grass grass : map.grasses.values())
                    if (grass.getPosition().equals(new Vector2d(x, y))) {
                        g.setFill(Color.GREEN);
                        g.fillRect(x, y, 1, 1);
                    }

                for (ArrayList<Animal> animals : map.animals.values())
                    for(Animal animal : animals)
                        if (animal.getPosition().equals(new Vector2d(x, y))) {
                            g.setFill(Color.SANDYBROWN);
                            g.fillRoundRect(x,y,1,1,1,1);
                        }
            }
        }
    }
}