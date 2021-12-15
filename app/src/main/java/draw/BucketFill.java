package draw;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bucket Fill similar to that tool in MS Paint.
 */
public class BucketFill implements Drawable  {
    final Point point;
    final Character color;

    public BucketFill(Point point, Character color) {
        this.point = point;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        // Flood-fill
        List<Point> points = new ArrayList<>();
        points.add(this.point);

        while (points.size() > 0) {
            Point p = points.remove(0);
            Character color = canvas.get(new Point(p.x, p.y));
            if (color != null && !color.equals(this.color)) {
                canvas.set(new Point(p.x, p.y), this.color);

                points.add(new Point(p.x + 1, p.y));
                points.add(new Point(p.x - 1, p.y));
                points.add(new Point(p.x, p.y + 1));
                points.add(new Point(p.x, p.y - 1));
            }
        }
    }
}
