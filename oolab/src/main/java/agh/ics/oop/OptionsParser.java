package agh.ics.oop;
import java.util.Scanner;

public class OptionsParser {
    public static String[] parser(String[] args) {

        String[] givenDirections = parserGiveDirections(args);
        return givenDirections;
    }

    //modyfikuje funkcje z lab 1: giveDirections, tak, aby przyjmowaly argumenty nie tylko f, b, r, l
    //ale rowniez "forward", "backward", "right", "left"

    public static String[] parserGiveDirections(String[] args) {

        //Printuje mozliwe argumenty

        System.out.println("Mozliwe kierunki: ");

        for(int i=0; i<args.length ;i++){
            System.out.print(args[i] + ", ");}

        String[] direc = {"forward", "backward", "right", "left"};

        for(int i=0; i<direc.length-1 ;i++){
            System.out.print(direc[i] + ", ");}
        System.out.println(direc[args.length-1]);

        //Uzytkownik podaje w jakich kierunkach zwierzak ma iść

        System.out.println("Podaj kierunki w jakich zwierzak ma isc ODDZIELONE SPACJA: ");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] directions = str.split(" ");

        //Tworze nowa tablice directionsSorted z samymi f b r l forward backward right left
        //gdyby uzytkownik wpisal inne znaki

        int x = 0; //ustalam wielkosc x nowej tablicy
        for (int i=0; i<directions.length; i++){
            if (directions[i].matches("f|b|r|l|forward|backward|right|left")){
                x++;}
        }

        String[] directionsSorted = new String[x];

        int j = 0;

        for (int i=0; i< directions.length; i++){
            if (directions[i].matches("f|b|r|l|forward|backward|right|left")){
                directionsSorted[j] = directions[i];
                j++;}
        }

        //Zamieniam ciagi znakow f, b, r, l na te z enum Direction

        String[] changeString = new String[directionsSorted.length];

        for (int i=0; i<directionsSorted.length; i++)
            switch (directionsSorted[i]) {
                case "f": case "forward":
                    changeString[i] = "FORWARD";
                    break;
                case "b": case "backward":
                    changeString[i] = "BACKWARD";
                    break;
                case "r": case "right":
                    changeString[i] = "RIGHT";
                    break;
                case "l": case "left":
                    changeString[i] = "LEFT";
                    break;
            }

        //zwracam tablice z samymi FORWARD BACKWARD RIGHT LEFT
        return changeString;

    }
}