package agh.ics.oop;

import java.sql.SQLOutput;
import java.util.Scanner;

public class World {
    public static void main(String[] args) {
        System.out.println("System wystartowal.");
        String[] givenDirections = giveDirections(args);
        run(givenDirections);
        System.out.println("System zakonczyl dzialanie.");
    }

    public static void run(String[] tab) {
        //Nie jestem pewna czy o to chodzilo w zadaniu
        //prosze sie nie smiac! :(

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
        //String str = "fbrrlnsnsj";
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

