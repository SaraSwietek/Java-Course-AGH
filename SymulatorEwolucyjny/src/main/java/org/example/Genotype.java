package org.example;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.IntStream;

public class Genotype {

    private ArrayList<Integer> genotype = new ArrayList<>();
    private final Random generator = new Random();
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry




    public Genotype() throws FileNotFoundException {
        for(int i = 0; i < parameters.getGenotypeLength(); i++)
            this.genotype.add(generator.nextInt(8));
    }

    public Genotype(Animal parent1, Animal parent2) throws FileNotFoundException {

        int side = generator.nextInt(2);
        if(side == 1){
            this.genotype = calculateGenotype(parent1, parent2);
        }
        else{
            this.genotype = calculateGenotype(parent2, parent1);
        }
    }



    public ArrayList<Integer> getGenotype(){

        return this.genotype;
    }



    public ArrayList<Integer> calculateGenotype(Animal parent1, Animal parent2){
        ArrayList<Integer> newGenotype = new ArrayList<>();
        int sumEnergy = parent1.getEnergy() + parent2.getEnergy();
        int genesFromFirstParent = (int) ((double) parent1.getEnergy() / sumEnergy * parameters.getGenotypeLength());

        for (int i = 0; i < parameters.getGenotypeLength() ; i++) {
            if (i < genesFromFirstParent) {
                newGenotype.add(parent1.getGenotype().get(i));
            } else {
                newGenotype.add(parent2.getGenotype().get(i));
            }
        }

        newGenotype = mutateGenotype(newGenotype);

        return newGenotype;
    }

    public ArrayList<Integer> mutateGenotype(ArrayList<Integer> genotype){

        int genesToMutate = generator.nextInt(parameters.getMaxMutationCount() - parameters.getMinMutationCount() + 1)
                + parameters.getMinMutationCount();

        for(int i=0; i<genotype.size(); i++){

            if(generator.nextBoolean() && genesToMutate > 0){

                if(parameters.getMutationKind() == 1){

                    genotype.set(i, generator.nextInt(8));

                }
                else{

                    int newGene = genotype.get(i) + 1;

                    if(generator.nextBoolean())
                        genotype.set(i, (newGene + 1)%8);
                    else
                        genotype.set(i,(newGene - 1)%8);

                }
            }

        }

        return genotype;
    }


}
