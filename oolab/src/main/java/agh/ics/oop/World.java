package agh.ics.oop;

import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class World {
    public static void main(String[] args) {
        //lab3
        Animal animal = new Animal();
        System.out.println(animal.toString());

        //Sprawdzanie poprawnosci funkcji move
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        System.out.println(animal.toString());

        //Poruszanie zwierzeciem za pomoca klasy OptionsParser
        //1 krok: uzytkownik podaje kierunki f b r l forward backward right left
        //2 krok: parser majac liste wpisanych kierunkow porusza zwierzeciem za pomoca metody Animal.move
        OptionsParser proba = new OptionsParser();
        String[] animalMovements = proba.parser(args);

        for(int i=0; i<animalMovements.length ;i++){
            animal.move(MoveDirection.valueOf(animalMovements[i]));
            System.out.println(animal.toString());
        }
        //TESTY DO ZNALEZIENIA W KLASIE AnimalMoveTest!!!


        //lab2
        //test metod z Vector2d
        //Vector2d position1 = new Vector2d(1,2);
        //System.out.println(position1);
        //Vector2d position2 = new Vector2d(-2,1);
        //System.out.println(position2);
        //System.out.println(position1.add(position2));

        //uzycie metody next() z enum MapDirection
        //MapDirection direction = MapDirection.valueOf("NORTH");
        //String nextDirection = direction.next();
        //MapDirection direction2 = MapDirection.valueOf(nextDirection);
        //System.out.println(direction2);

        //uzycie metody toUnitVector() z enum MapDirection
        //MapDirection direction3 = MapDirection.valueOf("SOUTH");
        //Vector2d position3 = direction3.toUnitVector();
        //System.out.println(position3);

        //lab1
        //System.out.println("System wystartowal.");
        //String[] givenDirections = giveDirections(args);
        //run(givenDirections);
        //System.out.println("System zakonczyl dzialanie.");
    }

    public static void run(String[] tab) {

        for(int i=0; i<tab.length; i++) {
            Direction direction = Direction.valueOf(tab[i]);

            if (direction.equals(Direction.valueOf("FORWARD"))) {
                System.out.println("Zwierzak idzie do przodu");
            }

            if (direction.equals(Direction.valueOf("BACKWARD"))) {
                System.out.println("Zwierzak idzie do tylu");
            }

            if (direction.equals(Direction.valueOf("RIGHT"))) {
                System.out.println("Zwierzak idzie w prawo");
            }

            if (direction.equals(Direction.valueOf("LEFT"))) {
                System.out.println("Zwierzak idzie w lewo");
            }

        }

    }

    public static String[] giveDirections(String[] args) {

        //Printuje mozliwe argumenty

        System.out.println("Mozliwe kierunki: ");

        for(int i=0; i<args.length-1 ;i++){
            System.out.print(args[i] + ", ");}
        System.out.println(args[args.length-1]);

        //Uzytkownik podaje w jakich kierunkach zwierzak ma iść

        System.out.println("Podaj kierunki w jakich zwierzak ma isc: ");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] directions = str.split("");

        //Tworze nowa tablice directionsSorted z samymi f b r l
        //gdyby uzytkownik wpisal inne znaki

        int x = 0; //ustalam wielkosc x nowej tablicy
        for (int i=0; i<directions.length; i++){
            if (directions[i].matches("f|b|r|l")){
                x++;}
        }

        String[] directionsSorted = new String[x];

        int j = 0;

        for (int i=0; i< directions.length; i++){
            if (directions[i].matches("f|b|r|l")){
                directionsSorted[j] = directions[i];
                j++;}
        }

        //Zamieniam ciagi znakow f, b, r, l na te z enum Direction

        String[] changeString = new String[directionsSorted.length];

        for (int i=0; i<directionsSorted.length; i++)
            switch (directionsSorted[i]) {
                case "f":
                    changeString[i] = "FORWARD";
                    break;
                case "b":
                    changeString[i] = "BACKWARD";
                    break;
                case "r":
                    changeString[i] = "RIGHT";
                    break;
                case "l":
                    changeString[i] = "LEFT";
                    break;
            }

        //zwracam tablice z samymi FORWARD BACKWARD RIGHT LEFT
        return changeString;

        }



    }

