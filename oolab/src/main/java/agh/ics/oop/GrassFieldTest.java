package agh.ics.oop;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class GrassFieldTest {

    private final IWorldMap map = new GrassField(10);

    @Test
    void canMoveToTest(){
        this.map.place(new Animal(new Vector2d(2,4), this.map));
        AssertJUnit.assertFalse(this.map.canMoveTo(new Vector2d(2,4)));
        AssertJUnit.assertTrue(this.map.canMoveTo(new Vector2d(5,5)));
        AssertJUnit.assertTrue(this.map.canMoveTo(new Vector2d(-6,-6)));
    }

    @Test
    void placeTest(){
        AssertJUnit.assertTrue(this.map.place(new Animal(new Vector2d(3,4), map)));
        AssertJUnit.assertFalse(this.map.place(new Animal(new Vector2d(3,4), map)));
        AssertJUnit.assertTrue(this.map.place(new Animal(new Vector2d(-7,-7), map)));
        AssertJUnit.assertFalse(this.map.place(new Animal(new Vector2d(-7,-7), map)));
    }

    @Test
    void isOccupiedTest(){
        this.map.place(new Animal(new Vector2d(2,4), this.map));
        this.map.place(new Animal(new Vector2d(-5,-5), this.map));
        AssertJUnit.assertFalse(this.map.isOccupied(new Vector2d(0,0)));
        AssertJUnit.assertTrue(this.map.isOccupied(new Vector2d(2,4)));
        AssertJUnit.assertTrue(this.map.isOccupied(new Vector2d(-5,-5)));
    }

}
