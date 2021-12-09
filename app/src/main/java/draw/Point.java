package draw;

/**
 * Presents a point in 2D space.
 */
public class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof Point) {
            return ((Point) o).x == this.x && ((Point) o).y == this.y;
        }
        return false;
    }
}
