package org.example;

//kula ziemska - lewa i prawa krawędź mapy zapętlają się (jeżeli zwierzak wyjdzie za lewą krawędź,
// to pojawi się po prawej stronie - a jeżeli za prawą, to po lewej); górna i dolna krawędź mapy
// to bieguny - nie można tam wejść (jeżeli zwierzak próbuje wyjść poza te krawędzie mapy, to
// pozostaje na polu na którym był, a jego kierunek zmienia się na odwrotny);

//chyba źle?

public class SphereMap {
    protected static final int HEIGHT = 10;
    protected static final int WIDTH = 10;
//orientacja na pryeciwna
    public Vector2d moveTo(Vector2d position) {
        if (position.x >= WIDTH) return new Vector2d(0, position.y);
        if (position.y >= HEIGHT) return new Vector2d(position.x, HEIGHT);
        if (position.x < 0) return new Vector2d(WIDTH, position.y);
        if (position.y < 0) return new Vector2d(position.x, 0);
        return new Vector2d(position.x, position.y);
    }
}
