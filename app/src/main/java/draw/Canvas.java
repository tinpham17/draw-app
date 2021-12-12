package draw;

/**
 * Represents a Canvas.
 */
public class Canvas {
    private final int width;
    private final int height;
    private final Character[][] pixels;

    public static Canvas create(int width, int height) {
        if (width <= 0 || height <= 0) {
            return null;
        }
        return new Canvas(width, height);
    }

    private Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = this.initPixels(width, height);
    }

    /**
     * Sets a point color. If the point is not valid (for example it's out of canvas size), nothing happens.
     * @param point the point
     * @param color the color
     */
    public void set(Point point, Character color) {
        if (this.isInside(point)) {
            this.pixels[point.y][point.x] = color;
        }
    }

    /**
     * Gets a point color. If the point is not valid (for example it's out of canvas size), null is returned.
     * @param point the point
     * @return the color
     */
    public Character get(Point point) {
        if (this.isInside(point)) {
            return this.pixels[point.y][point.x];
        }
        return null;
    }

    /**
     * Gets canvas output to display.
     * @return map pixels
     */
    public Character[][] output() {
        return this.pixels;
    }

    private boolean isInside(Point point) {
        int framedHeight = height + 2;
        int framedWidth = width + 2;
        return point.x > 0 && point.x < framedWidth - 1 && point.y > 0 && point.y < framedHeight - 1;
    }

    private Character[][] initPixels(int width, int height) {
        int framedHeight = height + 2;
        int framedWidth = width + 2;
        Character[][] pixels = new Character[framedHeight][framedWidth];
        for (int row = 0; row < framedHeight; row++) {
            for (int col = 0; col < framedWidth; col++) {
                char c = row == 0 || row == framedHeight - 1 ? '-' :
                    col == 0 || col == framedWidth - 1 ? '|' : ' ';
                pixels[row][col] = c;
            }
        }
        return pixels;
    }
}
