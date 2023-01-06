package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    Labels labels = new Labels();
    Scene paramsScene, simScene;
    ParametersLoader parameters = ParametersLoader.loadPropFromFile();


    public App() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        stage.setTitle("Symulator Ewolucyjny");

//Scene 1
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(labels.labWidth,0,0);
        GridPane.setConstraints(labels.txtWidth,1,0);
        GridPane.setConstraints(labels.labHeight,0,1);
        GridPane.setConstraints(labels.txtHeight,1,1);
        GridPane.setConstraints(labels.labGrassStartNumber,0,2);
        GridPane.setConstraints(labels.txtGrassStartNumber,1,2);
        GridPane.setConstraints(labels.labGrassEnergyProfit,0,3);
        GridPane.setConstraints(labels.txtGrassEnergyProfit,1,3);
        GridPane.setConstraints(labels.labGrassDailyGrowthNumber,0,4);
        GridPane.setConstraints(labels.txtGrassDailyGrowthNumber,1,4);
        GridPane.setConstraints(labels.labAnimalsStartNumber,0,5);
        GridPane.setConstraints(labels.txtAnimalsStartNumber,1,5);
        GridPane.setConstraints(labels.labAnimalsStartEnergy,0,6);
        GridPane.setConstraints(labels.txtAnimalsStartEnergy,1,6);
        GridPane.setConstraints(labels.labAnimalsStepEnergyLoss,0,7);
        GridPane.setConstraints(labels.txtAnimalsStepEnergyLoss,1,7);
        GridPane.setConstraints(labels.labCopulationMinimumEnergy,0,8);
        GridPane.setConstraints(labels.txtCopulationMinimumEnergy,1,8);
        GridPane.setConstraints(labels.labMakingChildCost,0,9);
        GridPane.setConstraints(labels.txtMakingChildCost,1,9);
        GridPane.setConstraints(labels.labMaxMutationCount,0,10);
        GridPane.setConstraints(labels.txtMaxMutationCount,1,10);
        GridPane.setConstraints(labels.labMinMutationCount,0,11);
        GridPane.setConstraints(labels.txtMinMutationCount,1,11);
        GridPane.setConstraints(labels.labGenotypeLength,0,12);
        GridPane.setConstraints(labels.txtGenotypeLength,1,12);
        GridPane.setConstraints(labels.labBehaviour,0,13);
        GridPane.setConstraints(labels.txtBehaviour,1,13);
        GridPane.setConstraints(labels.labMutationKind,0,14);
        GridPane.setConstraints(labels.txtMutationKind,1,14);
        GridPane.setConstraints(labels.labGrassGrowing,0,15);
        GridPane.setConstraints(labels.txtGrassGrowing,1,15);
        GridPane.setConstraints(labels.labMapVariant,0,16);
        GridPane.setConstraints(labels.txtMapVariant,1,16);

        Button saveButton = new Button("Save changes");
        Button startButton = new Button("Start Simulation");
        GridPane.setConstraints(saveButton, 1,17);
        GridPane.setConstraints(startButton, 1,18);


        saveButton.setOnAction(e -> {

        //modyfikuję parameters.json zgodnie z ustawieniami
        FileReader reader = null;
        try {
            reader = new FileReader("src\\main\\java\\org\\example\\parameters.json");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (IOException eeee) {
            throw new RuntimeException(eeee);
        } catch (ParseException ee) {
            throw new RuntimeException(ee);
        }

        jsonObject.remove("height");
        jsonObject.put("height", Integer.parseInt(labels.txtHeight.getText()));
        jsonObject.remove("width");
        jsonObject.put("width", Integer.parseInt(labels.txtWidth.getText()));
        jsonObject.remove("grassStartNumber");
        jsonObject.put("grassStartNumber", Integer.parseInt(labels.txtGrassStartNumber.getText()));
        jsonObject.remove("grassEnergyProfit");
        jsonObject.put("grassEnergyProfit", Integer.parseInt(labels.txtGrassEnergyProfit.getText()));
        jsonObject.remove("grassDailyGrowthNumber");
        jsonObject.put("grassDailyGrowthNumber", Integer.parseInt(labels.txtGrassDailyGrowthNumber.getText()));
        jsonObject.remove("animalsStartNumber");
        jsonObject.put("animalsStartNumber", Integer.parseInt(labels.txtAnimalsStartNumber.getText()));
        jsonObject.remove("animalsStartEnergy");
        jsonObject.put("animalsStartEnergy", Integer.parseInt(labels.txtAnimalsStartEnergy.getText()));
        jsonObject.remove("animalsStepEnergyLoss");
        jsonObject.put("animalsStepEnergyLoss", Integer.parseInt(labels.txtAnimalsStepEnergyLoss.getText()));
        jsonObject.remove("copulationMinimumEnergy");
        jsonObject.put("copulationMinimumEnergy", Integer.parseInt(labels.txtCopulationMinimumEnergy.getText()));
        jsonObject.remove("makingChildCost");
        jsonObject.put("makingChildCost", Integer.parseInt(labels.txtMakingChildCost.getText()));
        jsonObject.remove("maxMutationCount");
        jsonObject.put("maxMutationCount", Integer.parseInt(labels.txtMaxMutationCount.getText()));
        jsonObject.remove("minMutationCount");
        jsonObject.put("minMutationCount", Integer.parseInt(labels.txtMinMutationCount.getText()));
        jsonObject.remove("genotypeLength");
        jsonObject.put("genotypeLength", Integer.parseInt(labels.txtGenotypeLength.getText()));
        jsonObject.remove("behaviour");
        jsonObject.put("behaviour", Integer.parseInt(labels.txtBehaviour.getText()));
        jsonObject.remove("mutationKind");
        jsonObject.put("mutationKind", Integer.parseInt(labels.txtMutationKind.getText()));
        jsonObject.remove("grassGrowing");
        jsonObject.put("grassGrowing", Integer.parseInt(labels.txtGrassGrowing.getText()));
        jsonObject.remove("mapVariant");
        jsonObject.put("mapVariant", Integer.parseInt(labels.txtMapVariant.getText()));

        try (FileWriter file = new FileWriter("src\\main\\java\\org\\example\\parameters.json")) {
            file.write(jsonObject.toJSONString());
            file.flush();
            MainView mainView = new MainView(); //MainView z symulacją tworzymy po dopasowaniu parametrów w parameters.json
            simScene = new Scene(mainView, 640, 480);

        } catch (IOException eee) {
            eee.printStackTrace();
        }



        });


        startButton.setOnAction(e -> stage.setScene(simScene)); //startButton uruchamia simScene - scenę z symulacją

        grid.getChildren().addAll(labels.labWidth,labels.txtWidth,labels.labHeight,labels.txtHeight, labels.labGrassStartNumber, labels.txtGrassStartNumber,
                labels.labGrassEnergyProfit, labels.txtGrassEnergyProfit, labels.labGrassDailyGrowthNumber, labels.txtGrassDailyGrowthNumber, labels.labAnimalsStartNumber,
                labels.txtAnimalsStartNumber, labels.labAnimalsStartEnergy, labels.txtAnimalsStartEnergy, labels.labAnimalsStepEnergyLoss, labels.txtAnimalsStepEnergyLoss,
                labels.labCopulationMinimumEnergy,labels.txtCopulationMinimumEnergy, labels.labMakingChildCost,labels.txtMakingChildCost,labels.labMaxMutationCount,
                labels.txtMaxMutationCount,labels.labMinMutationCount,labels.txtMinMutationCount,labels.labGenotypeLength,labels.txtGenotypeLength,labels.labBehaviour,
                labels.txtBehaviour,labels.labMutationKind,labels.txtMutationKind,labels.labGrassGrowing, labels.txtGrassGrowing, labels.labMapVariant,labels.txtMapVariant,
                saveButton,startButton);

        paramsScene = new Scene(grid, 300, 700);


        stage.setScene(paramsScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
