package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    GrassField map;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        Label label = new Label("Zwierzak");
        Scene scene = new Scene(label, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void init() throws IllegalArgumentException {
        try {
            String[] args = {"f","b","r","l"};
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
    }
}
