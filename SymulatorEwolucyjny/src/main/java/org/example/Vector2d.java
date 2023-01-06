package org.example;

import java.util.Objects;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(%d, %d)".formatted(x, y);
    }

    boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    boolean follows(Vector2d other) {

        return x >= other.x && y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);    //nowy obiekt naszej klasy, który jest sumą
    }

    public Vector2d subtract(Vector2d other) {

        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d opposite() {

        return new Vector2d((-1) * x, (-1) * y);
    }

    public Vector2d upperRight(Vector2d other) {

        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {

        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
