package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParametersLoader {

    private int width;
    private int height;
    private int grassStartNumber;
    private int grassEnergyProfit;
    private int grassDailyGrowthNumber;
    private int animalsStartNumber;
    private int animalsStartEnergy;
    private int animalsStepEnergyLoss;
    private int copulationMinimumEnergy;
    private int makingChildCost;
    private int maxMutationCount;
    private int genotypeLength;
    private int minMutationCount;
    private int behaviour; // 1 - pełna predysktynacja, 2 - niecoszaleństwa
    private int mutationKind; // 1 -pełna losowość, 2 - lekka korekta
    private int grassGrowing; // 1 -trupy, 2 - równik
    private int mapVariant; // 1 -kula ziemska, 2 - piekielny portal





    static public ParametersLoader loadPropFromFile() throws IllegalArgumentException, FileNotFoundException {
        Gson gson = new Gson();
        File f = new File("");
        System.out.println(f.getAbsolutePath());
        ParametersLoader properties =  (ParametersLoader)gson.fromJson(new FileReader("src\\main\\java\\org\\example\\parameters.json"), ParametersLoader.class);
        properties.validate();
        return properties;
    }

    public void validate() throws IllegalArgumentException{
        if(this.width <= 0){ throw new IllegalArgumentException("Invalid map width");}
        if(this.height <= 0){ throw new IllegalArgumentException("Invalid map height");}
        if(this.grassStartNumber <= 0){ throw new IllegalArgumentException("Invalid grass start number");}
        if(this.grassEnergyProfit <= 0){ throw new IllegalArgumentException("Invalid grass energy profit");}
        if(this.grassDailyGrowthNumber<= 0){ throw new IllegalArgumentException("Invalid grass daily growth number");}
        if(this.animalsStartNumber <= 0){ throw new IllegalArgumentException("Invalid animals start number");}
        if(this.animalsStartEnergy <= 0){ throw new IllegalArgumentException("Invalid animals start energy");}
        if(this.animalsStepEnergyLoss <= 0){ throw new IllegalArgumentException("Invalid animals step energy loss");}
        if(this.copulationMinimumEnergy <= 0){ throw new IllegalArgumentException("Invalid copulation minimum energy");}
        if(this.makingChildCost <= 0){ throw new IllegalArgumentException("Invalid making child cost");}
        if(this.maxMutationCount < 0){ throw new IllegalArgumentException("Max mutation count below 0");}
        if(this.genotypeLength < 0){ throw new IllegalArgumentException("Invalid genotype length");}
        if(this.maxMutationCount < this.minMutationCount){ throw new IllegalArgumentException("Max mutation count below min mutation count");}

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGrassStartNumber() {
        return grassStartNumber;
    }

    public int getMinMutationCount() {
        return minMutationCount;
    }

    public void setMinMutationCount(int minMutationCount) {
        this.minMutationCount = minMutationCount;
    }

    public void setGrassStartNumber(int grassStartNumber) {
        this.grassStartNumber = grassStartNumber;
    }

    public int getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(int behaviour) {
        this.behaviour = behaviour;
    }

    public int getGrassEnergyProfit() {
        return grassEnergyProfit;
    }

    public void setGrassEnergyProfit(int grassEnergyProfit) {
        this.grassEnergyProfit = grassEnergyProfit;
    }

    public int getGrassDailyGrowthNumber() {
        return grassDailyGrowthNumber;
    }

    public void setGrassDailyGrowthNumber(int grassDailyGrowthNumber) {
        this.grassDailyGrowthNumber = grassDailyGrowthNumber;
    }

    public int getAnimalsStartNumber() {
        return animalsStartNumber;
    }

    public void setAnimalsStartNumber(int animalsStartNumber) {
        this.animalsStartNumber = animalsStartNumber;
    }

    public int getAnimalsStartEnergy() {
        return animalsStartEnergy;
    }

    public void setAnimalsStartEnergy(int animalsStartEnergy) {
        this.animalsStartEnergy = animalsStartEnergy;
    }

    public int getAnimalsStepEnergyLoss() {
        return animalsStepEnergyLoss;
    }

    public void setAnimalsStepEnergyLoss(int animalsStepEnergyLoss) {
        this.animalsStepEnergyLoss = animalsStepEnergyLoss;
    }


    public int getCopulationMinimumEnergy() {
        return copulationMinimumEnergy;
    }

    public void setCopulationMinimumEnergy(int copulationMinimumEnergy) {
        this.copulationMinimumEnergy = copulationMinimumEnergy;
    }

    public int getMakingChildCost() {
        return makingChildCost;
    }

    public void setMakingChildCost(int makingChildCost) {
        this.makingChildCost = makingChildCost;
    }

    public int getMaxMutationCount() {
        return maxMutationCount;
    }

    public void setMaxMutationCount(int maxMutationCount) {
        this.maxMutationCount = maxMutationCount;
    }

    public int getGenotypeLength() {
        return genotypeLength;
    }

    public void setGenotypeLength(int genotypeLength) {
        this.genotypeLength = genotypeLength;
    }

    public int getMutationKind() {
        return mutationKind;
    }

    public void setMutationKind(int mutationKind) {
        this.mutationKind = mutationKind;
    }

    public int getGrassGrowing() {
        return grassGrowing;
    }

    public void setGrassGrowing(int grassGrowing) {
        this.grassGrowing = grassGrowing;
    }

    public int getMapVariant() {
        return mapVariant;
    }

    public void setMapVariant(int mapVariant) {
        this.mapVariant = mapVariant;
    }
}
