package draw;

/**
 * Presents a line.
 */
public class Line implements Drawable  {
    final Point start;
    final Point end;
    final Character color;

    public Line(Point start, Point end, Character color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        if (this.start.x == this.end.x) {
            drawVertically(canvas);
            return;
        }
        if (this.start.y == this.end.y) {
            drawHorizontally(canvas);
        }
    }

    private void drawHorizontally(Canvas canvas) {
        int startX = Math.min(this.start.x, this.end.x);
        int endX = Math.max(this.start.x, this.end.x);
        for (int x = startX; x <= endX; x++) {
            canvas.set(new Point(x, this.start.y), this.color);
        }
    }

    private void drawVertically(Canvas canvas) {
        int startY = Math.min(this.start.y, this.end.y);
        int endY = Math.max(this.start.y, this.end.y);
        for (int y = startY; y <= endY; y++) {
            canvas.set(new Point(this.start.x, y), this.color);
        }
    }
}
