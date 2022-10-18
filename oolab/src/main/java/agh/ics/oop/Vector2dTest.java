package agh.ics.oop;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Vector2dTest {

    @Test
    void equalsTest() {
        Vector2d position1 = new Vector2d(1,2);
        Vector2d position2 = new Vector2d(1,2);
        AssertJUnit.assertTrue(position1.equals(position2));
    }

    @Test
    void toStringTest() {
        Vector2d position1 = new Vector2d(1,2);
        AssertJUnit.assertTrue(position1.toString().equals("(1,2)"));
    }

    @Test
    void precedesTest(){
        Vector2d position1 = new Vector2d(1,2);
        Vector2d position2 = new Vector2d(2,3);
        AssertJUnit.assertTrue(position1.precedes(position2));
    }

    @Test
    void followsTest(){
        Vector2d position1 = new Vector2d(2,3);
        Vector2d position2 = new Vector2d(1,2);
        AssertJUnit.assertTrue(position1.follows(position2));
    }

    @Test
    void upperRightTest(){
        Vector2d position1 = new Vector2d(2,3);
        Vector2d position2 = new Vector2d(5,2);
        AssertJUnit.assertTrue(position1.upperRight(position2).equals(new Vector2d(5,3)));
    }

    @Test
    void lowerLeftTest(){
        Vector2d position1 = new Vector2d(2,3);
        Vector2d position2 = new Vector2d(5,2);
        AssertJUnit.assertTrue(position1.lowerLeft(position2).equals(new Vector2d(2,2)));
    }

    @Test
    void addTest(){
        Vector2d position1 = new Vector2d(2,3);
        Vector2d position2 = new Vector2d(5,2);
        AssertJUnit.assertTrue(position1.add(position2).equals(new Vector2d(7,5)));
    }

    @Test
    void subtractTest(){
        Vector2d position1 = new Vector2d(2,3);
        Vector2d position2 = new Vector2d(5,2);
        AssertJUnit.assertTrue(position1.subtract(position2).equals(new Vector2d(-3,1)));
    }

    @Test
    void oppositeTest(){
        Vector2d position1 = new Vector2d(4,-3);
        AssertJUnit.assertTrue(position1.opposite().equals(new Vector2d(-4,3)));
    }


}
