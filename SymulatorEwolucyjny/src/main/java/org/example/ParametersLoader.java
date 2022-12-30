package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParametersLoader {

    protected int width;
    protected int height;
    protected int grassStartNumber;
    protected int grassEnergyProfit;
    protected int grassDailyGrowthNumber;
    protected int animalsStartNumber;
    protected int animalsStartEnergy;
    protected int copulationMinimumEnergy;
    protected int makingChildCost;
    protected int maxMutationCount;
    protected int genotypeLength;




    static public ParametersLoader loadPropFromFile() throws FileNotFoundException,IllegalArgumentException {
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
        if(this.copulationMinimumEnergy <= 0){ throw new IllegalArgumentException("Invalid copulation minimum energy");}
        if(this.makingChildCost <= 0){ throw new IllegalArgumentException("Invalid making child cost");}
        if(this.maxMutationCount <= 0){ throw new IllegalArgumentException("Invalid max mutation count");}
        if(this.genotypeLength < 0){ throw new IllegalArgumentException("Invalid genotype length");}

    }

    public int getMapWidth() {
        return width;
    }

    public void setMapWidth(int width) {
        this.width = width;
    }

    public int getMapHeight() {
        return height;
    }

    public void setMapHeight(int height) {
        this.height = height;
    }

    public int getGrassStartNumber() {
        return grassStartNumber;
    }

    public void setGrassStartNumber(int grassStartNumber) {
        this.grassStartNumber = grassStartNumber;
    }

    public int getGrassEatingEnergyProfit() {
        return grassEnergyProfit;
    }

    public void setGrassEatingEnergyProfit(int grassEatingEnergyProfit) {
        this.grassEnergyProfit = grassEatingEnergyProfit;
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
        return height;
    }

    public void setGenotypeLength(int genotypeLength) {
        this.genotypeLength = genotypeLength;
    }

}
