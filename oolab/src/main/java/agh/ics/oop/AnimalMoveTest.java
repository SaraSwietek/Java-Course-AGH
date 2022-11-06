package agh.ics.oop;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class AnimalMoveTest {

    //Czy zwierze zwraca sie w odpowiednie kierunki?
    @Test
    void orientationTest() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        String dir = String.valueOf(animal.getDir());
        AssertJUnit.assertTrue(dir.equals("Zachód"));
    }

    //Czy zwierze staje na odpowiednich pozycjach?
    @Test
    void positionTest() {
        Animal animal = new Animal();
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        int x = animal.getX();
        int y = animal.getY();
        AssertJUnit.assertTrue(x==0);
        AssertJUnit.assertTrue(y==2);
    }

    //Czy zwierze nie wychodzi poza mape? (0,0) (4,4)
    @Test
    void mapTest() {
        Animal animal = new Animal();
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        int x = animal.getX();
        int y = animal.getY();
        AssertJUnit.assertTrue(x==2);
        AssertJUnit.assertTrue(y==0);
    }

}

