package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Labels {
    Label labWidth = new Label("width");
    TextField txtWidth = new TextField("50");
    Label labHeight = new Label("height");

    TextField txtHeight = new TextField("50");

    Label labGrassStartNumber = new Label("grassStartNumber");
    TextField txtGrassStartNumber = new TextField("100");
    Label labGrassEnergyProfit = new Label("grassEnergyProfit");
    TextField txtGrassEnergyProfit = new TextField("20");
    Label labGrassDailyGrowthNumber = new Label("grassDailyGrowthNumber");
    TextField txtGrassDailyGrowthNumber = new TextField("10");

    Label labAnimalsStartNumber = new Label("animalsStartNumber");
    TextField txtAnimalsStartNumber = new TextField("50");
    Label labAnimalsStartEnergy = new Label("animalsStartEnergy");
    TextField txtAnimalsStartEnergy = new TextField("50");
    Label labAnimalsStepEnergyLoss = new Label("animalsStepEnergyLoss");
    TextField txtAnimalsStepEnergyLoss = new TextField("2");

    Label labCopulationMinimumEnergy = new Label("copulationMinimumEnergy");
    TextField txtCopulationMinimumEnergy = new TextField("20");
    Label labMakingChildCost = new Label("makingChildCost");
    TextField txtMakingChildCost = new TextField("10");

    Label labMaxMutationCount = new Label("maxMutationCount");
    TextField txtMaxMutationCount = new TextField("0");
    Label labMinMutationCount = new Label("minMutationCount");
    TextField txtMinMutationCount = new TextField("0");

    Label labGenotypeLength = new Label("genotypeLength");
    TextField txtGenotypeLength = new TextField("8");

    Label labBehaviour = new Label("behaviour (1 pp, 2 ns)");
    TextField txtBehaviour = new TextField("1");
    Label labMutationKind = new Label("mutationKind (1 pl, 2 lk)");
    TextField txtMutationKind = new TextField("1");
    Label labGrassGrowing = new Label("grassGrowing (1 tt, 2 r)");
    TextField txtGrassGrowing = new TextField("2");
    Label labMapVariant = new Label("mapVariant (1 kz 2 pp)");
    TextField txtMapVariant = new TextField("1");

}
