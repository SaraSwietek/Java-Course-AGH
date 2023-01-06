package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainView extends VBox {

    private Button stopButton;
    private Button startButton;
    private Canvas canvas;
    private Affine affine;
    ParametersLoader parameters = ParametersLoader.loadPropFromFile();
    private Timeline timeline;
    //AbstractWorld map = new SphereMap(parameters.getWidth(), parameters.getHeight());
    //Simulator simulator = new Simulator(map);

    private AbstractWorld map;
    private Simulator simulator;

    public MainView() throws FileNotFoundException {


        //--------------
        this.stopButton = new Button("stop");
        this.startButton = new Button("start");
        this.timeline = new Timeline(new KeyFrame(Duration.millis(500),this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);



        this.startButton.setOnAction(actionEvent -> {
            try {
                map = new SphereMap(parameters.getWidth(), parameters.getHeight()); //uzaleznic od param
                simulator = new Simulator(map);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            timeline.play();
        });

        this.stopButton.setOnAction(actionEvent -> {
            timeline.stop();
        });


        this.canvas = new Canvas(400, 400);
        this.getChildren().addAll(this.stopButton, this.startButton, this.canvas);
        this.affine = new Affine();
        this.affine.appendScale(400/parameters.getWidth(), 400/parameters.getHeight());

    }


    private void doStep(ActionEvent actionEvent) {
        try {
            this.simulator.simulate(map);
            System.out.println(this.parameters.getWidth());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        draw();

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