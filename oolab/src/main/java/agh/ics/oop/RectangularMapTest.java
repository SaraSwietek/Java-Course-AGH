package agh.ics.oop;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class RectangularMapTest {

    private final IWorldMap map = new RectangularMap(5, 5);

    @Test
    void canMoveToTest(){
        this.map.place(new Animal(new Vector2d(2,4), this.map));
        AssertJUnit.assertFalse(this.map.canMoveTo(new Vector2d(2,4)));
        AssertJUnit.assertTrue(this.map.canMoveTo(new Vector2d(2,3)));
        AssertJUnit.assertFalse(this.map.canMoveTo(new Vector2d(-5,-5)));
    }

    @Test
    void placeTest(){
        AssertJUnit.assertTrue(this.map.place(new Animal(new Vector2d(3,4), map)));
        AssertJUnit.assertFalse(this.map.place(new Animal(new Vector2d(3,4), map)));
        AssertJUnit.assertFalse(this.map.place(new Animal(new Vector2d(-5,-5), map)));
    }

    @Test
    void isOccupiedTest(){
        this.map.place(new Animal(new Vector2d(2,4), this.map));
        AssertJUnit.assertFalse(this.map.isOccupied(new Vector2d(0,0)));
        AssertJUnit.assertTrue(this.map.isOccupied(new Vector2d(2,4)));
    }

}
