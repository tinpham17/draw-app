package draw;

public class Rectangle extends Graphic {
    final Point topLeft;
    final Point bottomRight;
    final Character color;

    public Rectangle(Point topLeft, Point bottomRight, Character color) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.topLeft.x < this.bottomRight.x && this.topLeft.y < this.bottomRight.y) {
            for (int i = topLeft.x; i <= bottomRight.x; i++) {
                canvas.set(new Point(i, topLeft.y), this.color);
                canvas.set(new Point(i, bottomRight.y), this.color);
            }
            for (int i = topLeft.y; i <= bottomRight.y; i++) {
                canvas.set(new Point(topLeft.x, i), this.color);
                canvas.set(new Point(bottomRight.x, i), this.color);
            }
        }
    }
}
