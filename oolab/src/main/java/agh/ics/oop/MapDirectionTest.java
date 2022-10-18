package agh.ics.oop;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class MapDirectionTest {

    public void nextTestOptions(String a) {

        MapDirection direction = MapDirection.valueOf(a);
        String nextDirection = direction.next();
        MapDirection direction2 = MapDirection.valueOf(nextDirection);

        if (a=="EAST"){
            AssertJUnit.assertTrue(nextDirection.equals("SOUTH"));
        }
        if (a=="NORTH"){
            AssertJUnit.assertTrue(nextDirection.equals("EAST"));
        }
        if (a=="SOUTH"){
            AssertJUnit.assertTrue(nextDirection.equals("WEST"));
        }
        if (a=="WEST"){
            AssertJUnit.assertTrue(nextDirection.equals("NORTH"));
        }
    }

    @Test
    void nextTest() {
        nextTestOptions("EAST");
        nextTestOptions("SOUTH");
        nextTestOptions("WEST");
        nextTestOptions("NORTH");
    }

    public void previousTestOptions(String a) {

        MapDirection direction = MapDirection.valueOf(a);
        String previousDirection = direction.previous();
        MapDirection direction2 = MapDirection.valueOf(previousDirection);

        if (a=="EAST"){
            AssertJUnit.assertTrue(previousDirection.equals("NORTH"));
        }
        if (a=="NORTH"){
            AssertJUnit.assertTrue(previousDirection.equals("WEST"));
        }
        if (a=="SOUTH"){
            AssertJUnit.assertTrue(previousDirection.equals("EAST"));
        }
        if (a=="WEST"){
            AssertJUnit.assertTrue(previousDirection.equals("SOUTH"));
        }
    }

    @Test
    void previousTest() {
        previousTestOptions("EAST");
        previousTestOptions("SOUTH");
        previousTestOptions("WEST");
        previousTestOptions("NORTH");
    }



}


