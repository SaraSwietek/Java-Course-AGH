package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private SortedSet<Vector2d> xSet = new TreeSet<>(Comparator.comparingInt(point -> point.x)); //,,lambdy''
    private SortedSet<Vector2d> ySet = new TreeSet<>(Comparator.comparingInt(point -> point.y));
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.xSet.remove(oldPosition);
        this.ySet.remove(oldPosition);
        this.xSet.add(newPosition);
        this.ySet.add(newPosition);
    }

    public void addPosition(Vector2d position) {
        xSet.add(position);
        ySet.add(position);
    }

    public Vector2d getLowerLeft() {
        return xSet.first().lowerLeft(ySet.first());
    }

    public Vector2d getUpperRight() {
        return xSet.last().upperRight(ySet.last());
    }

}
