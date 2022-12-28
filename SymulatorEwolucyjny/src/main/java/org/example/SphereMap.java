package org.example;

//kula ziemska - lewa i prawa krawędź mapy zapętlają się (jeżeli zwierzak wyjdzie za lewą krawędź,
// to pojawi się po prawej stronie - a jeżeli za prawą, to po lewej); górna i dolna krawędź mapy
// to bieguny - nie można tam wejść (jeżeli zwierzak próbuje wyjść poza te krawędzie mapy, to
// pozostaje na polu na którym był, a jego kierunek zmienia się na odwrotny);

//chyba źle?

public class SphereMap extends AbstractWorld{

    public SphereMap(int width, int height) {

        super(width, height);
    }

    public void moveTo(Animal pet) { // zmienić na canMoveTo ? mamy to też w AbstractWorldzie
        if (pet.position.x > width)
            pet.position = new Vector2d(0, pet.position.y);
        if (pet.position.x < 0)
            pet.position = new Vector2d(width, pet.position.y);
        if (pet.position.y < 0 || pet.position.y > height) {
            pet.position = pet.position.subtract(pet.orientation.toUnitVector()); // wektor został dodany w Animalu
            pet.changeOrientation(4);
        }
    }



}
