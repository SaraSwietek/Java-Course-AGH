package org.example;

//kula ziemska - lewa i prawa krawędź mapy zapętlają się (jeżeli zwierzak wyjdzie za lewą krawędź,
// to pojawi się po prawej stronie - a jeżeli za prawą, to po lewej); górna i dolna krawędź mapy
// to bieguny - nie można tam wejść (jeżeli zwierzak próbuje wyjść poza te krawędzie mapy, to
// pozostaje na polu na którym był, a jego kierunek zmienia się na odwrotny);

//chyba źle?

import java.io.FileNotFoundException;

public class SphereMap extends AbstractWorld{
    ParametersLoader parameters = ParametersLoader.loadPropFromFile(); //ładujemy parametry

    public SphereMap() throws FileNotFoundException {

        super();

    }



    public void moveTo(Animal pet) { // zmienić na canMoveTo ? mamy to też w AbstractWorldzie

        if (pet.position.y < 0 || pet.position.y >= parameters.getHeight()) {
            pet.position = pet.position.subtract(pet.orientation.toUnitVector()); // wektor został dodany w Animalu
            pet.changeOrientation(4);
        }
        if (pet.position.x >= parameters.getWidth())
            pet.position = new Vector2d(0, pet.position.y);
        if (pet.position.x < 0)
            pet.position = new Vector2d(parameters.getWidth()-1, pet.position.y);

    }



}
