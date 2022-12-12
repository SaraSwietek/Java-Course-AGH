package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(%s,%s)".formatted(x,y);
    }

    public boolean precedes(Vector2d other){
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other){
        return x >= other.x && y >= other.y;
    }

    Vector2d add(Vector2d other){
        int x_add = x + other.x;
        int y_add = y + other.y;
        return new Vector2d(x_add,y_add);
    }

    Vector2d subtract(Vector2d other){
        int x_sub = x - other.x;
        int y_sub = y - other.y;
        return new Vector2d(x_sub,y_sub);
    }

    Vector2d upperRight(Vector2d other){

        if ((x >= other.x)&&(y >= other.y)){return new Vector2d(x,y); }
        else if ((x >= other.x)&&(y < other.y)){return new Vector2d(x,other.y); }
        else if ((x < other.x)&&(y >= other.y)){return new Vector2d(other.x,y); }
        else {return new Vector2d(other.x,other.y); }

    }

    Vector2d lowerLeft(Vector2d other){

        if ((x >= other.x)&&(y >= other.y)){return new Vector2d(other.x,other.y); }
        else if ((x >= other.x)&&(y < other.y)){return new Vector2d(other.x,y); }
        else if ((x < other.x)&&(y >= other.y)){return new Vector2d(x,other.y); }
        else {return new Vector2d(x,y); }

    }

    Vector2d opposite(){
        return new Vector2d(-x,-y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true; //jesli referencja wskazuje na ten sam obiekt -> true
        if (other == null || getClass() != other.getClass()) return false;
        Vector2d vector2d = (Vector2d) other;
        return x == vector2d.x && y == vector2d.y;
    }

    //hashCode musi isc razem z equals
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
