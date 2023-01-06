package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainView extends VBox {

    private Button stopButton;
    private Button startButton;
    private Button continueButton;
    private Button showDominantGenotype;
    private Button selectAnimalToTrack;
    private Button pauseButton;
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
        this.startButton = new Button("start");
        this.continueButton = new Button("continue");
        this.stopButton = new Button("stop");
        this.showDominantGenotype = new Button("showDominantGenotype");



        this.timeline = new Timeline(new KeyFrame(Duration.millis(200),this::doStep));
        this.timeline.setCycleCount(Timeline.INDEFINITE);


        this.startButton.setOnAction(actionEvent -> {
            try {
                if(parameters.getMapVariant() == 1)
                    this.map = new SphereMap();
                else
                    this.map = new HellMap();

                simulator = new Simulator(map);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            timeline.play();
        });

        this.continueButton.setOnAction(actionEvent -> {
            timeline.play();
        });


        this.stopButton.setOnAction(actionEvent -> {
            timeline.stop();
        });

        this.showDominantGenotype.setOnAction(actionEvent ->{
            showDominantGenotype(map.animals);
        });




        this.canvas = new Canvas(400, 400);
        this.getChildren().addAll(this.startButton, this.continueButton, this.stopButton, this.showDominantGenotype, this.canvas);
        this.affine = new Affine();
        this.affine.appendScale(400/parameters.getWidth(), 400/parameters.getHeight());

    }



    private void showDominantGenotype(List<Animal> animals) {

        Map<Integer, Integer> genotypeCounts = new HashMap<>();
        for (Animal animal : animals) {
            Integer genotype = animal.getGenotypeInt();
            int count = genotypeCounts.getOrDefault(genotype, 0); // bierze wartość dla genotype lub ustawia na 0
            genotypeCounts.put(genotype, count + 1);
        }
        Integer dominantGenotype=0;
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry : genotypeCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                dominantGenotype = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        // Redraw the map
        draw(dominantGenotype);
    }

    private void draw(Integer dominantGenotype) {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, parameters.getWidth(), parameters.getHeight());

        for (Grass grass : map.grasses.values()) {
            g.setFill(Color.GREEN);
            g.fillRect(grass.getPosition().x, grass.getPosition().y, 1, 1);
        }

        for (Animal animal : map.animals) {

            Color fillColor;
            if (animal.getGenotypeInt().equals(dominantGenotype)) {
                fillColor = Color.RED;
            } else {
                fillColor = Color.BLUE;
            }

            g.setFill(fillColor);
            g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
        }
    }


    private void doStep(ActionEvent actionEvent) {
        try {
            this.simulator.simulate(map);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        draw();
        if(map.animals.isEmpty()){
            timeline.stop();
        }


    }


    public void draw() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, parameters.getWidth(), parameters.getHeight());

        for (Grass grass : map.grasses.values()) {
            g.setFill(Color.GREEN);
            g.fillRect(grass.getPosition().x, grass.getPosition().y, 1, 1);
        }

        for (Animal animal : map.animals) {

            if(animal.getEnergy()>100){
                g.setFill(Color.rgb(51,37,3));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            } else if (animal.getEnergy()>80) {
                g.setFill(Color.rgb(69,52,10));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            } else if (animal.getEnergy()>60) {
                g.setFill(Color.rgb(99, 85, 50));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            } else if (animal.getEnergy()>40) {
                g.setFill(Color.rgb(122, 108, 72));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            } else if (animal.getEnergy()>20) {
                g.setFill(Color.rgb(145, 133, 100));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            } else {
                g.setFill(Color.rgb(196,182,145));
                g.fillRoundRect(animal.getPosition().x, animal.getPosition().y, 1, 1, 1, 1);
            }


        }
    }
}