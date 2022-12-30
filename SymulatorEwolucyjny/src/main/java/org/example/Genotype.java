package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Genotype {
    protected static final int GENE_LENGTH = 8; // zrobić jakoś żeby była tylko w jednym miejscu a nie 3, klasa z parametrami
    private ArrayList<Integer> genotype = new ArrayList<>();
    private final Random generator = new Random();




    public Genotype(int nGenes){
        for(int i = 0; i < nGenes; i++)
            this.genotype.add(generator.nextInt(nGenes));
    }

    public Genotype(Animal parent1, Animal parent2){

//        this.genotype = afterParentsGenotype(parent1, parent2);
    }

    public int getCrazyGenotype(int i){      //nieco szaleństwa , dlaczego tutaj i w symulatorze ?
        int drawNumber = generator.nextInt(10)+1; //losowanie od 1 do 10
        if (drawNumber <= 8){
            return this.genotype.get(i);
        }
        return this.genotype.get(generator.nextInt(GENE_LENGTH));
    }

    public ArrayList<Integer> getGenotype(){

        return this.genotype;
    }
/// rozkminić od tego momentu w dół


    public ArrayList<Integer> calculateGenotype(Animal parent1, Animal parent2, double sumEnergy){ // potrzebne do obliczania genotypu dziecka,
        // jest sens przekazywania sumEnergy jako osobny parametr ?
        // dodać losowanie strony z której dziedziczymy od rodzica
        ArrayList<Integer> newGenotype = new ArrayList<>();
        int genesFromFirstParent = (int) ((double) parent1.getEnergy() / sumEnergy * GENE_LENGTH );
        for (int i = 0; i < GENE_LENGTH ; i++) { // + losowanie strony
            if (i < genesFromFirstParent) {
                newGenotype.add(parent1.getGenotype().get(i));
            } else {
                newGenotype.add(parent2.getGenotype().get(i));
            }
        }
        return newGenotype;
    }

    //Inna implementacje tego co niżej

//    public ArrayList<Integer> mutateGenotype(ArrayList<Integer> genotype){ // mutacja genów, wykonywana po obliczeniu genotypu
//
//        Random r1 = new Random(); // finalny generator co jest wyżej zamiast nowej zmiennej
//        List<Integer> indexesList = IntStream.rangeClosed(0, GENE_LENGTH-1) // ????
//                .boxed().toList(); // lista indexów długości genomu
//        int mutationsNumber = r1.nextInt(GENE_LENGTH + 1); // ranodmowa liczba genów do mutacji
//        // tutaj inny parametr musi być bo minimalna i maksymalna ilość mutacji jest podawana jako parametr
//
//        // get random subset of size mutationsNumber
//        List<Integer> rangeLinkedList = new LinkedList<Integer>(indexesList); // po co nowa lista jak już mamy indexesList
//        Collections.shuffle(rangeLinkedList);
//        Set<Integer> indexesToMutate = new HashSet<Integer>(rangeLinkedList.subList(0, mutationsNumber));
//
//        for (Integer indexToMutate : indexesToMutate) {
//            Random r2 = new Random(); // mamy generator
//            int newGene = r2.nextInt(8);
//            genotype.set(indexToMutate, newGene);
//        }
//
//        return genotype;
//    }




    //ilość energi, potem ilość przeżytych dni, i potem liczba dzieci
//    public ArrayList<Integer> afterParentsGenotype(Animal parent1, Animal parent2){   //dziedziczenie genotypu po rodzicach
//
//        int sumOfParentsEnergy = parent1.getEnergy() + parent2.getEnergy();
//        int sumOfParentsDays = parent1.howOld() + parent2.howOld();
//        int sumOfParentsChildren = parent1.getChildren() + parent2.getChildren();
//
//        if (parent1.getEnergy() < parent2.getEnergy()) {        // co za różnica którego rodzica podamy jako pierwszego ?
//            return calculateGenotype(parent1, parent2, sumOfParentsEnergy);
//        }
//        else if (parent1.getEnergy() > parent2.getEnergy()) {
//            return calculateGenotype(parent2, parent1, sumOfParentsEnergy);
//        }
//        // niżej tym bardziej nie rozumiem. Dlaczego w miejsce energii czasem jest suma dni albo suma dzieci ?
//        if (parent1.howOld() < parent2.howOld()) {
//            return calculateGenotype(parent1, parent2, sumOfParentsDays);
//        }
//        else if (parent1.howOld() > parent2.howOld()){
//            return calculateGenotype(parent2, parent1, sumOfParentsDays);
//        }
//        if (parent1.getChildren() < parent2.getChildren()){
//            return calculateGenotype(parent1, parent2, sumOfParentsChildren);
//        }
//        else if (parent1.getChildren() > parent2.getChildren()){
//            calculateGenotype(parent2, parent1, sumOfParentsChildren);
//        }
//        ArrayList<Integer> newGenArr = new ArrayList<>();       //losowo, jak wszystko jest równe
//        int l = generator.nextInt(GENE_LENGTH);
//        for (int i = 0; i < GENE_LENGTH; i++) {
//            if (i < l) {
//                newGenArr.add(parent1.getGenotype().get(i));
//            } else {
//                newGenArr.add(parent2.getGenotype().get(i));
//            }
//        }
//        return newGenArr;
//    }
}
