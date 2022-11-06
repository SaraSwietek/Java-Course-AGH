package agh.ics.oop;

public class Animal {

    private MapDirection dir = MapDirection.NORTH;
    private int x=2;
    private int y=2;
    private Vector2d position = new Vector2d(x,y);

    @Override
    public String toString() {
        return "polozenie: (%s,%s), orientacja: %s".formatted(x,y,dir);
    }

    boolean isAt(Vector2d position){
        if (position.x==x && position.y==y){
            return true;
        }
        else return false;
    }

    void move(MoveDirection direction) {
        if (direction == MoveDirection.RIGHT) {
            this.dir = MapDirection.valueOf(dir.next());

        }

        if (direction == MoveDirection.LEFT) {
            this.dir = MapDirection.valueOf(dir.previous());
        }

        if (direction == MoveDirection.FORWARD) {
            Vector2d position_change = dir.toUnitVector();
            if ((this.x+position_change.x)>=0&&(this.x+position_change.x)<=4&&(this.y+position_change.y)>=0&&(this.y+position_change.y)<=4){
                this.x+=position_change.x;
                this.y+=position_change.y;
            }

        }

        if (direction == MoveDirection.BACKWARD) {
            Vector2d position_change = dir.toUnitVector();
            if ((this.x-position_change.x)>=0&&(this.x-position_change.x)<=4&&(this.y-position_change.y)>=0&&(this.y-position_change.y)<=4){
                this.x-=position_change.x;
                this.y-=position_change.y;
            }

        }
    }

    public String getDir() {
        return "%s".formatted(dir);
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }



}
